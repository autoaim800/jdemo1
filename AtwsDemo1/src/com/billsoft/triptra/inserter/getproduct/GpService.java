package com.billsoft.triptra.inserter.getproduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.billsoft.triptra.Const;
import com.billsoft.triptra.xsd.getproduct.Product_service_configuration_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_service_external_system_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_service_type0;
import com.billsoft.triptra.xsd.getproduct.Row_type62;
import com.billsoft.triptra.xsd.getproduct.Row_type63;
import com.billsoft.triptra.xsd.getproduct.Row_type64;

public class GpService extends GpInserter {

    private static int insert(Connection conn, int prodId, int svcId,
            Product_service_configuration_relationship_type0 conf) {
        if (null == conf || null == conf.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_service_configuration_relationship (product_id, minimum_capacity, attribute_id_service_config_definition, attribute_id_service_config, maximum_capacity, service_id, attribute_id_service_config_description, number_of_services) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;
        for (Row_type62 obj : conf.getRow()) {
            try {
                nullOrInt(pstmt, 1, prodId);

                nullOrInt(pstmt, 2, obj.getMinimum_capacity());
                nullOrString(pstmt, 3, obj.getAttribute_id_service_config_definition());
                nullOrString(pstmt, 4, obj.getAttribute_id_service_config());
                nullOrInt(pstmt, 5, obj.getMaximum_capacity());
                nullOrInt(pstmt, 6, svcId);
                nullOrString(pstmt, 7, obj.getAttribute_id_service_config_description());
                nullOrInt(pstmt, 8, obj.getNumber_of_services());

                afrc += pstmt.executeUpdate(); pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return afrc;
    }

    private static int insert(Connection conn, int prodId, int svcId,
            Product_service_external_system_relationship_type0 ext) {
        if (null == ext || null == ext.getRow()) {
            return 0;
        }
        int afrc = 0;

        String cmd = "insert into t2_product_service_external_system_relationship (service_id, external_system_text, product_id, external_system_code) values (?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        for (Row_type63 obj : ext.getRow()) {
            try {
                nullOrInt(pstmt, 1, svcId);

                nullOrString(pstmt, 2, obj.getExternal_system_text());
                nullOrInt(pstmt, 3, prodId);
                nullOrString(pstmt, 4, obj.getExternal_system_code());

                afrc += pstmt.executeUpdate(); pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;
    }

    public static int insert(Connection conn, int prodId, Product_service_type0 svc) {

        if (null == svc || null == svc.getRow()) {
            return 0;
        }

        int afrc = 0;

        String cmd = "insert into t2_product_service (rate_to, tariff_code_match, attribute_id_rate_basis, attribute_id_ceiling_height_unit, children_catered_for_text, attribute_id_rate_basis_definition, tour_duration, attribute_id_time_unit_description, room_available_ceiling_height, room_area, room_dimensions, attribute_id_ceiling_height_unit_description, service_name, pets_allowed_flag, international_ready_flag, attribute_id_rate_basis_description, attribute_id_room_shape, rate_from, pickup_available_text, pets_allowed_text, children_catered_for_flag, attribute_id_time_unit, attribute_id_time_unit_description_mv, disabled_access_flag, service_description, rate_comment, disabled_access_text, attribute_id_ceiling_height_unit_description_mv, product_id, pickup_available_flag, attribute_id_room_shape_description, service_id, attribute_id_room_shape_description_mv, sequence_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

        for (Row_type64 obj : svc.getRow()) {

            int svcId = obj.getService_id();

            GpService.insert(conn, prodId, svcId, obj.getProduct_service_configuration_relationship());
            GpService.insert(conn, prodId, svcId,
                    obj.getProduct_service_external_system_relationship());

            try {
                nullOrFloat(pstmt, 1, obj.getRate_to());

                nullOrString(pstmt, 2, obj.getTariff_code_match());
                nullOrString(pstmt, 3, obj.getAttribute_id_rate_basis());
                nullOrString(pstmt, 4, obj.getAttribute_id_ceiling_height_unit());
                nullOrString(pstmt, 5, obj.getChildren_catered_for_text());
                nullOrString(pstmt, 6, obj.getAttribute_id_rate_basis_definition());
                nullOrInt(pstmt, 7, obj.getTour_duration());
                nullOrString(pstmt, 8, obj.getAttribute_id_time_unit_description());
                nullOrFloat(pstmt, 9, obj.getRoom_available_ceiling_height());
                nullOrInt(pstmt, 10, obj.getRoom_area());
                nullOrString(pstmt, 11, obj.getRoom_dimensions());
                nullOrString(pstmt, 12, obj.getAttribute_id_ceiling_height_unit_description());
                nullOrString(pstmt, 13, obj.getService_name());
                nullOrBoolean(pstmt, 14, obj.getPets_allowed_flag());
                nullOrBoolean(pstmt, 15, obj.getInternational_ready_flag());
                nullOrString(pstmt, 16, obj.getAttribute_id_rate_basis_description());
                nullOrString(pstmt, 17, obj.getAttribute_id_room_shape());
                nullOrFloat(pstmt, 18, obj.getRate_from());
                nullOrString(pstmt, 19, obj.getPickup_available_text());
                nullOrString(pstmt, 20, obj.getPets_allowed_text());
                nullOrBoolean(pstmt, 21, obj.getChildren_catered_for_flag());
                nullOrString(pstmt, 22, obj.getAttribute_id_time_unit());
                nullOrString(pstmt, 23, obj.getAttribute_id_time_unit_description_mv());
                nullOrBoolean(pstmt, 24, obj.getDisabled_access_flag());
                nullOrString(pstmt, 25, obj.getService_description());
                nullOrString(pstmt, 26, obj.getRate_comment());
                nullOrString(pstmt, 27, obj.getDisabled_access_text());
                nullOrString(pstmt, 28, obj.getAttribute_id_ceiling_height_unit_description_mv());
                nullOrInt(pstmt, 29, prodId);
                nullOrBoolean(pstmt, 30, obj.getPickup_available_flag());
                nullOrString(pstmt, 31, obj.getAttribute_id_room_shape_description());
                nullOrInt(pstmt, 32, obj.getService_id());
                nullOrString(pstmt, 33, obj.getAttribute_id_room_shape_description_mv());
                nullOrInt(pstmt, 34, obj.getSequence_number());

                afrc += pstmt.executeUpdate(); pstmt.clearParameters();

            } catch (SQLException e) {
                if (Const.SQL_DUPLICATE == e.getErrorCode()) {
                    Const.logger.error(String.format("duplicate service %d of product %s", svcId,
                            prodId));
                } else {
                    e.printStackTrace();
                }
            }
        }
        return afrc;

    }

}
