package com.billsoft.triptra;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.billsoft.triptra.handlers.QueryProducts;
import com.billsoft.triptra.handlers.QueryProductsNextPage;
import com.billsoft.triptra.xsd.queryproducts.Atdw_data_results;
import com.billsoft.triptra.xsd.queryproducts.Product_distribution_type0;

/**
 * this class is for direct service call of getproducts
 * 
 * @author bill
 * 
 */
public class ProductListReplicator extends PageReplicator {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Connection cnn = DbHelper.obtainConnection();

        ProductListReplicator fr = new ProductListReplicator(Const.DIST_KEY, cnn);
        fr.replicate();

        try {
            cnn.close();
        } catch (SQLException e) {
            // ignored
        }
    }

    public ProductListReplicator(String distKey, Connection conn) {
        super(distKey, conn);
    }

    private void dealOnePageOfProducts(Atdw_data_results result, int pageNum) {
        Logger logger = Const.getLogger();

        logger.info(String.format("dealOnePageOfProducts() page=%s", pageNum));

        Product_distribution_type0[] dists = result.getProduct_distribution();

        try {
            conn.setAutoCommit(false);

            for (Product_distribution_type0 dist : dists) {

                int prodId = dist.getProduct_id();

                insertProductDistribution(prodId);

                ProductEntityReplicator spr = new ProductEntityReplicator(distKey, conn, prodId);
                spr.replicate();

                conn.commit();
            }
            // out of for each product

            conn.setAutoCommit(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private int insertProductDistribution(int prodId) {
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

    private void queryProducts() {
        int perPage = 50;
        int pageNum = 1;

        String fp = buildPageFp(pageNum);
        File file = new File(fp);

        QueryProducts qp = new QueryProducts(distKey, "ALL");
        qp.setResultsPerPage(String.valueOf(perPage));

        boolean needWriteFile = true;

        if (file.exists()) {
            needWriteFile = false;
            qp.setRaw(readf(fp));
        }

        try {
            Atdw_data_results result = qp.retrieveResult();

            int total = result.getTotal_records_found();
            int sum = result.getRecord_count_this_buffer();
            String queryId = result.getApi_query_id();

            if (needWriteFile) {
                writef(fp, pretty(qp.getRaw()));
            }

            dealOnePageOfProducts(result, pageNum);

            if (sum < total) {
                // need more page
                queryProductsNext(queryId, perPage, total);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void queryProductsNext(String queryId, int perPage, int totalRecord) {
        int pageNum = 2;
        int sum = perPage;

        try {
            do {
                QueryProductsNextPage qpn = new QueryProductsNextPage(distKey, queryId,
                        String.valueOf(pageNum));
                qpn.setResultsPerPage(String.valueOf(perPage));

                String fp = buildPageFp(pageNum);

                File file = new File(fp);
                boolean needWriteFile = true;
                if (file.exists()) {
                    needWriteFile = false;
                    qpn.setRaw(readf(fp));
                }

                Atdw_data_results result = null;
                try {
                    result = qpn.retrieveResult();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result = null;
                }
                if (needWriteFile) {
                    writef(fp, qpn.getRaw());
                }

                sum += result.getRecord_count_this_buffer();

                dealOnePageOfProducts(result, pageNum);

                Const.logger.info("queryProductsNextPage() " + pageNum + " done");

                pageNum++;

            } while (sum < totalRecord);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String buildPageFp(int pageNum) {
        return "out/products/page" + pageNum + ".xml";
    }

    /**
     * 1 queryproducts => product_id
     * 
     * 2 getproduct => service_id
     * 
     * 3 getproductservice => ends?
     */
    public boolean replicate() {
        // state
        // region
        // area
        // city
        // queryCities();
        queryProducts();
        return false;
    }
}
