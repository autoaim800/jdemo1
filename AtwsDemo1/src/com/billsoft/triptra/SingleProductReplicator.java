package com.billsoft.triptra;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.billsoft.triptra.handlers.GetProduct;
import com.billsoft.triptra.inserter.getproduct.Address;
import com.billsoft.triptra.inserter.getproduct.Contact;
import com.billsoft.triptra.inserter.getproduct.Inserter;
import com.billsoft.triptra.inserter.getproduct.Service;
import com.billsoft.triptra.xsd.getproduct.Atdw_data_results;
import com.billsoft.triptra.xsd.getproduct.Product_address_area_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_address_domestic_region_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_address_street_directory_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_address_type0;
import com.billsoft.triptra.xsd.getproduct.Product_contact_type0;
import com.billsoft.triptra.xsd.getproduct.Product_distribution_type0;
import com.billsoft.triptra.xsd.getproduct.Row_type29;
import com.billsoft.triptra.xsd.getproduct.Row_type5;

public class SingleProductReplicator extends PageReplicator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Connection conn = DbHelper.obtainConnection();

        SingleProductReplicator spr = new SingleProductReplicator(Const.DIST_KEY, conn, 9123438);
        spr.replicate();

        try {
            conn.close();
        } catch (SQLException e) {
            // ignored
        }
    }

    private int prodId;

    public SingleProductReplicator(String distKey) {
        this(distKey, null, 0);
    }

    public SingleProductReplicator(String distKey, Connection conn) {
        this(distKey, conn, 0);
    }

    public SingleProductReplicator(String distKey, Connection conn, int productId) {
        super(distKey, conn);

        prodId = productId;
    }

    private void insertProductAddress(Product_address_type0 productAddress) {
        if (null == productAddress || null == productAddress.getRow()) {
            // TODO should give warning?
        } else {

            for (Row_type5 row : productAddress.getRow()) {

                Address.insert(conn, prodId, row.getProduct_address_area_relationship());
                Address.insert(conn, prodId, row.getProduct_address_street_directory_relationship());
                Address.insert(conn, prodId, row.getProduct_address_domestic_region_relationship());

                Address.insert(conn, prodId, row);
            }
        }
    }

    private void procResult(Atdw_data_results result) {

        Logger logger = Const.getLogger();

        Product_distribution_type0 dist = result.getProduct_distribution();

        int productId = dist.getProduct_id();
        if (prodId > 0 && productId != prodId) {
            logger.error(String.format("productId %d <> %d", productId, prodId));
        } else {
            prodId = productId;
        }

        insertProductAddress(dist.getProduct_address());
        insertProductContact(dist.getProduct_contact());

        Address.insert(conn, prodId, dist.getProduct_record());

        Inserter.insert(conn, prodId, dist.getProduct_multimedia());
        Service.insert(conn, prodId, dist.getProduct_service());
        Inserter.insert(conn, prodId, dist.getProduct_open_time());

        Inserter.insert(conn, prodId, dist.getProduct_article());
        Inserter.insert(conn, prodId, dist.getProduct_entry_cost());
        Inserter.insert(conn, prodId, dist.getProduct_article());

        Inserter.insert(conn, prodId, dist.getProduct_proximity());
        Inserter.insert(conn, prodId, dist.getProduct_licence());
        Inserter.insert(conn, prodId, dist.getProduct_sponsor());

    }

    private int insertProductContact(Product_contact_type0 productContact) {
        if (null == productContact || null == productContact.getRow()) {
            return -1;
        }
        int afrc = 0;
        for (Row_type29 row : productContact.getRow()) {

            afrc += Contact.insert(conn, row.getProduct_contact_address_relationship());
            afrc += Contact.insert(conn, row.getProduct_contact_attribute_relationship());
            afrc += Contact.insert(conn, row.getProduct_contact_comment_relationship());
            afrc += Contact.insert(conn, row.getProduct_contact_communication_relationship());
            afrc += Contact.insert(conn, row.getProduct_contact_multimedia_relationship());

            afrc += Contact.insert(conn, prodId, row);

        }
        return afrc;
    }

    /**
     * @param fp
     *            a string of local file-path-name
     * @return a boolean indicates whether the product has been processed,
     *         returns true when no exception
     */
    public boolean replicate() {

        GetProduct gp = new GetProduct(key, String.valueOf(prodId));

        String fp = String.format("out/%s.xml", prodId);
        boolean needWriteFile = false;

        needWriteFile = true;
        File file = new File(fp);
        if (file.exists()) {
            gp.setRaw(readf(fp));
            needWriteFile = false;
        }

        com.billsoft.triptra.xsd.getproduct.Atdw_data_results prodResult;
        boolean ret = false;
        try {
            prodResult = gp.retrieveResult();

            if (null == prodResult) {
                Const.logger.error("null result retrieved for product: " + this.prodId);
            } else {

                procResult(prodResult);
                ret = true;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (needWriteFile) {
            writef(fp, gp.getRaw());
        }

        return ret;
    }
}
