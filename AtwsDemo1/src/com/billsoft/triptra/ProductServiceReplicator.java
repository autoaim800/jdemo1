package com.billsoft.triptra;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.billsoft.triptra.handlers.GetProductService;
import com.billsoft.triptra.inserter.getproductservice.GpsInserter;
import com.billsoft.triptra.xsd.getproductservice.Atdw_data_results;
import com.billsoft.triptra.xsd.getproductservice.Service_distribution_type0;

/**
 * this class is for direct service call of getproductservice
 * 
 * @author bill
 * 
 */
public class ProductServiceReplicator extends PageReplicator {
    public static void main(String[] args) {

        Connection conn = DbHelper.obtainConnection();

        // 9123438, 9110894 xx
        // 9001200, 9001183
        // 9011801, 9026835 null-distribution
        // 9023280, 9113599 alt_text exceed 100
        ProductServiceReplicator psp = new ProductServiceReplicator(Const.DIST_KEY, conn, 9023280,
                9113599);
        psp.replicate();

        try {
            conn.close();
        } catch (SQLException e) {
            // ignored
        }

    }

    private int productId;
    private int serviceId;

    public ProductServiceReplicator(String distKey, Connection conn, int productId, int serviceId) {
        super(distKey, conn);
        this.productId = productId;
        this.serviceId = serviceId;
    }

    private String buildServiceDir() {
        return String.format("out/service/%s", productId);
    }

    private String buildServiceFilePath() {
        return String.format("out/service/%s/%s.xml", productId, serviceId);
    }

    private void procResult(Atdw_data_results result) {

        for (Service_distribution_type0 dist : result.getService_distribution()) {

            GpsInserter.insert(conn, productId, serviceId, dist.getService_attribute());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_record());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_make_model_style());

            GpsInserter.insert(conn, productId, serviceId, dist.getService_minimum_period());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_multimedia());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_rate_basis_comment());

            GpsInserter.insert(conn, productId, serviceId, dist.getService_departure_date());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_configuration());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_tariff_child());

            GpsInserter.insert(conn, productId, serviceId, dist.getService_tariff_adult());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_comment());
            GpsInserter.insert(conn, productId, serviceId,
                    dist.getService_vertical_classification());

            GpsInserter.insert(conn, productId, serviceId, dist.getService_external_system());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_start_location());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_season_tariff_comment());

            GpsInserter.insert(conn, productId, serviceId, dist.getService_tariff_concession());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_indicative_adult_rate());
            GpsInserter.insert(conn, productId, serviceId, dist.getService_indicative_child_rate());

            GpsInserter.insert(conn, productId, serviceId, dist.getService_route());
        }
    }

    public boolean replicate() {
        Logger log = Const.logger;

        String fp = buildServiceFilePath();

        GetProductService gps = new GetProductService(key, String.valueOf(productId),
                String.valueOf(serviceId));

        File file = new File(fp);
        boolean needWriteFile = true;
        if (file.exists()) {
            gps.setRaw(readf(fp));
            needWriteFile = false;
        }

        long netTime = 0;
        long xmlTime = 0;

        boolean ret = false;

        try {

            long t1 = System.currentTimeMillis();
            Atdw_data_results result = gps.retrieveResult();
            long t2 = System.currentTimeMillis();

            if (null == result || null == result.getService_distribution()) {
                Const.logger.error(String.format("product:%s service:%s null result/distribution",
                        productId, serviceId));
            } else {
                procResult(result);
            }

            xmlTime = System.currentTimeMillis() - t2;
            netTime = t2 - t1;

            ret = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (needWriteFile) {
            File dir = new File(buildServiceDir());
            if (!dir.exists()) {
                dir.mkdir();
            }
            writef(fp, pretty(gps.getRaw()));
        }

        Const.logger.info(String.format("   service:%s result:%s net-time:%s xml-proc-time:%s",
                serviceId, ret, netTime, xmlTime));
        return ret;

    }
}
