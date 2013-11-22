package com.billsoft.triptra;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.billsoft.triptra.handlers.GetProduct;
import com.billsoft.triptra.inserter.getproduct.GpAddress;
import com.billsoft.triptra.inserter.getproduct.GpContact;
import com.billsoft.triptra.inserter.getproduct.GpInserter;
import com.billsoft.triptra.inserter.getproduct.GpService;
import com.billsoft.triptra.xsd.getproduct.Atdw_data_results;
import com.billsoft.triptra.xsd.getproduct.Product_address_type0;
import com.billsoft.triptra.xsd.getproduct.Product_contact_type0;
import com.billsoft.triptra.xsd.getproduct.Product_distribution_type0;
import com.billsoft.triptra.xsd.getproduct.Product_service_type0;
import com.billsoft.triptra.xsd.getproduct.Row_type29;
import com.billsoft.triptra.xsd.getproduct.Row_type5;
import com.billsoft.triptra.xsd.getproduct.Row_type64;

/**
 * this class is for direct service call of getProduct
 * 
 * @author bill
 * 
 */
public class ProductEntityReplicator extends PageReplicator {
    
    private static int insertProductDistribution(Connection conn, int prodId) {
        String cmd = String.format("insert into t_product_distribution(product_id) values (%s)",
                prodId);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // ignored
            }
        }
        return 0;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Connection conn = DbHelper.obtainConnection();

        // 9002472, 9036130 multimedia duplication
        // 9183365 journey_route?
        // 9074570 video_id? from its/service
        // 9036809 t3_service_external_system issue
        // 9011801 null service-distribution
        
        

        insertProductDistribution(conn, 9036809);
        
        ProductEntityReplicator spr = new ProductEntityReplicator(Const.DIST_KEY, conn, 9036809);
        spr.replicate();

        try {
            conn.close();
        } catch (SQLException e) {
            // ignored
        }
    }

    private int productId;

    public ProductEntityReplicator(String distKey) {
        this(distKey, null, 0);
    }

    public ProductEntityReplicator(String distKey, Connection conn) {
        this(distKey, conn, 0);
    }

    public ProductEntityReplicator(String distKey, Connection conn, int _productId) {
        super(distKey, conn);

        productId = _productId;
    }

    private void insertProductAddress(Product_address_type0 productAddress) {
        if (null == productAddress || null == productAddress.getRow()) {
            // TODO should give warning?
        } else {

            for (Row_type5 row : productAddress.getRow()) {

                GpAddress.insert(conn, productId, row);
            }
        }
    }

    private int insertProductContact(Product_contact_type0 productContact) {
        if (null == productContact || null == productContact.getRow()) {
            return -1;
        }
        int afrc = 0;
        for (Row_type29 row : productContact.getRow()) {

            afrc += GpContact.insert(conn, row.getProduct_contact_address_relationship());
            afrc += GpContact.insert(conn, row.getProduct_contact_attribute_relationship());
            afrc += GpContact.insert(conn, row.getProduct_contact_comment_relationship());
            afrc += GpContact.insert(conn, row.getProduct_contact_communication_relationship());
            afrc += GpContact.insert(conn, row.getProduct_contact_multimedia_relationship());

            afrc += GpContact.insert(conn, productId, row);

        }
        return afrc;
    }

    private void insertProductService(Product_service_type0 service) {
        if (null == service || null == service.getRow()) {
            return;
        }

        // sub product-service (t3)
        for (Row_type64 svc : service.getRow()) {
            ProductServiceReplicator psp = new ProductServiceReplicator(distKey, conn, productId,
                    svc.getService_id());
            psp.replicate();
        }

        // insert t2 service
        GpService.insert(conn, productId, service);

    }

    private void procResult(Atdw_data_results result) {

        Logger logger = Const.getLogger();

        Product_distribution_type0 dist = result.getProduct_distribution();

        int _prodId = dist.getProduct_id();
        if (productId > 0 && _prodId != productId) {
            logger.error(String.format("productId %d <> %d", _prodId, productId));
        } else {
            productId = _prodId;
        }

        insertProductAddress(dist.getProduct_address());
        insertProductContact(dist.getProduct_contact());
        insertProductService(dist.getProduct_service());

        GpAddress.insert(conn, productId, dist.getProduct_record());
        GpInserter.insert(conn, productId, dist.getProduct_multimedia());
        GpInserter.insert(conn, productId, dist.getProduct_open_time());

        GpInserter.insert(conn, productId, dist.getProduct_article());
        GpInserter.insert(conn, productId, dist.getProduct_entry_cost());
        GpInserter.insert(conn, productId, dist.getProduct_article());

        GpInserter.insert(conn, productId, dist.getProduct_proximity());
        GpInserter.insert(conn, productId, dist.getProduct_licence());
        GpInserter.insert(conn, productId, dist.getProduct_sponsor());

        GpInserter.insert(conn, productId, dist.getProduct_communication());
        GpInserter.insert(conn, productId, dist.getProduct_name());
        GpInserter.insert(conn, _prodId, dist.getProduct_attribute());

        GpInserter.insert(conn, _prodId, dist.getProduct_related_service());
        GpInserter.insert(conn, _prodId, dist.getProduct_related_product());
    }

    /**
     * @param fp
     *            a string of local file-path-name
     * @return a boolean indicates whether the product has been processed,
     *         returns true when no exception
     */
    public boolean replicate() {

        GetProduct gp = new GetProduct(distKey, String.valueOf(productId));

        String fp = String.format("out/product/%s.xml", productId);

        boolean needWriteFile = false;

        needWriteFile = true;
        File file = new File(fp);
        if (file.exists()) {
            gp.setRaw(readf(fp));
            needWriteFile = false;
        }

        com.billsoft.triptra.xsd.getproduct.Atdw_data_results prodResult;
        boolean ret = false;
        long netTime = 0;
        long xmlTime = 0;
        try {
            long t1 = System.currentTimeMillis();
            prodResult = gp.retrieveResult();
            long t2 = System.currentTimeMillis();

            if (null == prodResult) {
                Const.logger.error("null result retrieved for product: " + this.productId);
            } else {
                procResult(prodResult);

                ret = true;
            }

            xmlTime = System.currentTimeMillis() - t2;
            netTime = t2 - t1;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (needWriteFile) {
            writef(fp, pretty(gp.getRaw()));
        }

        Const.logger.info(String.format(" product:%s result:%s net-time:%s xml-proc-time:%s",
                productId, ret, netTime, xmlTime));
        return ret;
    }
}
