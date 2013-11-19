package com.billsoft.triptra;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

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

/**
 * this class is for direct service call of getProduct
 * 
 * @author bill
 * 
 */
public class SingleProductReplicator extends PageReplicator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Connection conn = DbHelper.obtainConnection();

        // 9002472, 9036130 multimedia duplication
        SingleProductReplicator spr = new SingleProductReplicator(Const.DIST_KEY, conn, 9036130);
        spr.replicate();

        try {
            conn.close();
        } catch (SQLException e) {
            // ignored
        }
    }

    private int mProductId;

    public SingleProductReplicator(String distKey) {
        this(distKey, null, 0);
    }

    public SingleProductReplicator(String distKey, Connection conn) {
        this(distKey, conn, 0);
    }

    public SingleProductReplicator(String distKey, Connection conn, int _productId) {
        super(distKey, conn);

        mProductId = _productId;
    }

    private void insertProductAddress(Product_address_type0 productAddress) {
        if (null == productAddress || null == productAddress.getRow()) {
            // TODO should give warning?
        } else {

            for (Row_type5 row : productAddress.getRow()) {

                GpAddress.insert(conn, mProductId, row.getProduct_address_area_relationship());
                GpAddress.insert(conn, mProductId,
                        row.getProduct_address_street_directory_relationship());
                GpAddress.insert(conn, mProductId,
                        row.getProduct_address_domestic_region_relationship());

                GpAddress.insert(conn, mProductId, row);
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

            afrc += GpContact.insert(conn, mProductId, row);

        }
        return afrc;
    }

    private void insertProductService(Product_service_type0 service) {
        if (null == service || null == service.getRow()) {
            return;
        }

        // TODO not finish
        // for (Row_type64 svc : service.getRow()) {
        // ProductServiceReplicator psp = new
        // ProductServiceReplicator(this.mKey, mConn,
        // mProductId, svc.getService_id());
        // psp.replicate();
        // }

        // insert level-1 service
        GpService.insert(conn, mProductId, service);

    }

    private void procResult(Atdw_data_results result) {

        Logger logger = Const.getLogger();

        Product_distribution_type0 dist = result.getProduct_distribution();

        int _prodId = dist.getProduct_id();
        if (mProductId > 0 && _prodId != mProductId) {
            logger.error(String.format("productId %d <> %d", _prodId, mProductId));
        } else {
            mProductId = _prodId;
        }

        insertProductAddress(dist.getProduct_address());
        insertProductContact(dist.getProduct_contact());
        insertProductService(dist.getProduct_service());

        GpAddress.insert(conn, mProductId, dist.getProduct_record());
        GpInserter.insert(conn, mProductId, dist.getProduct_multimedia());
        GpInserter.insert(conn, mProductId, dist.getProduct_open_time());

        GpInserter.insert(conn, mProductId, dist.getProduct_article());
        GpInserter.insert(conn, mProductId, dist.getProduct_entry_cost());
        GpInserter.insert(conn, mProductId, dist.getProduct_article());

        GpInserter.insert(conn, mProductId, dist.getProduct_proximity());
        GpInserter.insert(conn, mProductId, dist.getProduct_licence());
        GpInserter.insert(conn, mProductId, dist.getProduct_sponsor());

        GpInserter.insert(conn, mProductId, dist.getProduct_communication());
        GpInserter.insert(conn, mProductId, dist.getProduct_name());
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

        GetProduct gp = new GetProduct(key, String.valueOf(mProductId));

        String fp = String.format("out/%s.xml", mProductId);
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
                Const.logger.error("null result retrieved for product: " + this.mProductId);
            } else {
                procResult(prodResult);
                
                ret = true;
            }
            long t3 = System.currentTimeMillis();
            xmlTime = t3 - t2;
            netTime = t2 - t1;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (needWriteFile) {
            writef(fp, gp.getRaw());
        }

        Const.logger.info(String.format("product: %s:%s, net-time: %s, xml-proc-time: %s",
                mProductId, ret, netTime, xmlTime));
        return ret;
    }
}
