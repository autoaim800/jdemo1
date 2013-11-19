package com.billsoft.triptra;

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

        ProductServiceReplicator psp = new ProductServiceReplicator(Const.DIST_KEY, conn, 9123438,
                9110894);
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

    private void procResult(Atdw_data_results result) {
        if (null == result) {
            return;
        }
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
            GpsInserter.insert(conn, productId, serviceId, dist.getService_vertical_classification());
            
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
        log.info(String.format("ProductServiceReplicator.replicate() product: %s service: %s",
                productId, serviceId));

        GetProductService gps = new GetProductService(key, String.valueOf(productId),
                String.valueOf(serviceId));

        try {
            
            procResult(gps.retrieveResult());
            
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }
}
