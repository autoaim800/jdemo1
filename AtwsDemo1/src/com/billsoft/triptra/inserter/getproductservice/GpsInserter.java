package com.billsoft.triptra.inserter.getproductservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.billsoft.triptra.Const;
import com.billsoft.triptra.inserter.DbInserter;
import com.billsoft.triptra.xsd.getproductservice.Decimal103;
import com.billsoft.triptra.xsd.getproductservice.Decimal129;
import com.billsoft.triptra.xsd.getproductservice.Decimal154;
import com.billsoft.triptra.xsd.getproductservice.Row_type0;
import com.billsoft.triptra.xsd.getproductservice.Row_type1;
import com.billsoft.triptra.xsd.getproductservice.Row_type10;
import com.billsoft.triptra.xsd.getproductservice.Row_type12;
import com.billsoft.triptra.xsd.getproductservice.Row_type13;
import com.billsoft.triptra.xsd.getproductservice.Row_type14;
import com.billsoft.triptra.xsd.getproductservice.Row_type15;
import com.billsoft.triptra.xsd.getproductservice.Row_type16;
import com.billsoft.triptra.xsd.getproductservice.Row_type17;
import com.billsoft.triptra.xsd.getproductservice.Row_type18;
import com.billsoft.triptra.xsd.getproductservice.Row_type19;
import com.billsoft.triptra.xsd.getproductservice.Row_type2;
import com.billsoft.triptra.xsd.getproductservice.Row_type20;
import com.billsoft.triptra.xsd.getproductservice.Row_type21;
import com.billsoft.triptra.xsd.getproductservice.Row_type22;
import com.billsoft.triptra.xsd.getproductservice.Row_type23;
import com.billsoft.triptra.xsd.getproductservice.Row_type24;
import com.billsoft.triptra.xsd.getproductservice.Row_type3;
import com.billsoft.triptra.xsd.getproductservice.Row_type4;
import com.billsoft.triptra.xsd.getproductservice.Row_type5;
import com.billsoft.triptra.xsd.getproductservice.Row_type6;
import com.billsoft.triptra.xsd.getproductservice.Row_type7;
import com.billsoft.triptra.xsd.getproductservice.Row_type8;
import com.billsoft.triptra.xsd.getproductservice.Row_type9;
import com.billsoft.triptra.xsd.getproductservice.Service_attribute_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_comment_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_configuration_attr_relationship_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_configuration_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_departure_date_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_departure_time_relationship_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_external_system_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_indicative_adult_rate_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_indicative_child_rate_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_make_model_style_attr_relationship_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_make_model_style_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_minimum_period_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_multimedia_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_rate_basis_comment_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_record_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_route_street_directory_relationship_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_route_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_season_tariff_comment_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_start_loc_domestic_region_relationship_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_start_loc_street_directory_relationship_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_start_location_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_tariff_adult_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_tariff_child_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_tariff_concession_type0;
import com.billsoft.triptra.xsd.getproductservice.Service_vertical_classification_type0;

/**
 * get-product-service inserter (not GPS). dirty insert methods, no comment for
 * these generated code
 * 
 * @author bill
 * 
 */
public class GpsInserter extends DbInserter {

    public static int insert(Connection conn, int productId, int serviceId,
            Service_attribute_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_attribute (attribute_type_id, product_id, attribute_type_id_description_mv, attribute_id_description, attribute_id_description_mv, attribute_id, attribute_type_id_description, service_id, attribute_text) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type0 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_type_id());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getAttribute_type_id_description_mv());
                nullOrString(pstmt, 4, obj.getAttribute_id_description());
                nullOrString(pstmt, 5, obj.getAttribute_id_description_mv());
                nullOrString(pstmt, 6, obj.getAttribute_id());
                nullOrString(pstmt, 7, obj.getAttribute_type_id_description());
                nullOrInt(pstmt, 8, serviceId);
                nullOrString(pstmt, 9, obj.getAttribute_text());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_comment_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_comment (attribute_id_service_comment_description, comment_date, comment_text, attribute_id_language_written, product_id, market_variant_id, sequence_number, attribute_id_language_written_description_mv, service_id, attribute_id_service_comment, attribute_id_language_written_description, comment_follow_up_date, attribute_id_service_comment_description_mv, comment_source) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type1 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_service_comment_description());
                nullOrDate(pstmt, 2, obj.getComment_date());
                nullOrString(pstmt, 3, obj.getComment_text());
                nullOrString(pstmt, 4, obj.getAttribute_id_language_written());
                nullOrInt(pstmt, 5, productId);
                nullOrString(pstmt, 6, obj.getMarket_variant_id());
                nullOrInt(pstmt, 7, obj.getSequence_number());
                nullOrString(pstmt, 8, obj.getAttribute_id_language_written_description_mv());
                nullOrInt(pstmt, 9, serviceId);
                nullOrString(pstmt, 10, obj.getAttribute_id_service_comment());
                nullOrString(pstmt, 11, obj.getAttribute_id_language_written_description());
                nullOrDate(pstmt, 12, obj.getComment_follow_up_date());
                nullOrString(pstmt, 13, obj.getAttribute_id_service_comment_description_mv());
                nullOrString(pstmt, 14, obj.getComment_source());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_configuration_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_configuration (product_id, minimum_capacity, maximum_capacity, attribute_id_service_configuration, attribute_id_service_configuration_description, service_id, number_of_services, attribute_id_service_configuration_description_mv) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type3 obj : param.getRow()) {
            try {

                nullOrInt(pstmt, 1, productId);
                nullOrInt(pstmt, 2, obj.getMinimum_capacity());
                nullOrInt(pstmt, 3, obj.getMaximum_capacity());
                nullOrString(pstmt, 4, obj.getAttribute_id_service_configuration());
                nullOrString(pstmt, 5, obj.getAttribute_id_service_configuration_description());
                nullOrInt(pstmt, 6, serviceId);
                nullOrInt(pstmt, 7, obj.getNumber_of_services());
                nullOrString(pstmt, 8, obj.getAttribute_id_service_configuration_description_mv());

                afrc += pstmt.executeUpdate();

                int agi = queryAgi(pstmt);

                if (agi > 0) {
                    insertConfigurationAttrRelationship(conn, productId, serviceId, agi,
                            obj.getService_configuration_attr_relationship());
                } else {
                    String tpl = " product: %s service: %s no found generated key for: service_configuration_attr_relationship";
                    Const.logger.error(String.format(tpl, productId, serviceId));
                }

                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_departure_date_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_departure_date (departure_date_to, service_id, departure_dates_text, departure_date_from, product_id) values (?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type5 obj : param.getRow()) {
            try {

                nullOrDate(pstmt, 1, obj.getDeparture_date_to());
                nullOrInt(pstmt, 2, serviceId);
                nullOrString(pstmt, 3, obj.getDeparture_dates_text());
                nullOrDate(pstmt, 4, obj.getDeparture_date_from());
                nullOrInt(pstmt, 5, productId);

                afrc += pstmt.executeUpdate();
                int agi = queryAgi(pstmt);

                if (agi > 0) {
                    insertDepartureTimeRelationship(conn, productId, serviceId, agi,
                            obj.getService_departure_time_relationship());
                } else {
                    String tpl = "product: %s service: %s no found generated key for: service_departure_date";
                    Const.logger.error(String.format(tpl, productId, serviceId));
                }

                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_external_system_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_external_system (external_system_code, service_id, product_id, external_system_text) values (?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type6 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getExternal_system_code());
                nullOrInt(pstmt, 2, serviceId);
                nullOrInt(pstmt, 3, productId);
                nullOrString(pstmt, 4, obj.getExternal_system_text());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_indicative_adult_rate_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_indicative_adult_rate (range_2_from_rate, attribute_id_rate_unit_description_mv, product_id, attribute_id_rate_unit_description, range_2_to_rate, attribute_id_rate_basis, attribute_id_rate_basis_description_mv, range_2_comment_text, attribute_id_rate_basis_description, service_id, attribute_id_rate_unit, range_1_comment_text, range_1_from_rate, range_1_to_rate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type7 obj : param.getRow()) {
            try {
                nullOrFloat(pstmt, 1, obj.getRange_2_from_rate());
                nullOrString(pstmt, 2, obj.getAttribute_id_rate_unit_description_mv());
                nullOrInt(pstmt, 3, productId);
                nullOrString(pstmt, 4, obj.getAttribute_id_rate_unit_description());
                nullOrFloat(pstmt, 5, obj.getRange_2_to_rate());
                nullOrString(pstmt, 6, obj.getAttribute_id_rate_basis());
                nullOrString(pstmt, 7, obj.getAttribute_id_rate_basis_description_mv());
                nullOrString(pstmt, 8, obj.getRange_2_comment_text());
                nullOrString(pstmt, 9, obj.getAttribute_id_rate_basis_description());
                nullOrInt(pstmt, 10, serviceId);
                nullOrString(pstmt, 11, obj.getAttribute_id_rate_unit());
                nullOrString(pstmt, 12, obj.getRange_1_comment_text());
                nullOrFloat(pstmt, 13, obj.getRange_1_from_rate());
                nullOrFloat(pstmt, 14, obj.getRange_1_to_rate());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_indicative_child_rate_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_indicative_child_rate (range_1_rate, attribute_id_rate_unit_description_mv, product_id, attribute_id_rate_unit_description, range_1_from_age, attribute_id_rate_basis, attribute_id_rate_basis_description_mv, service_id, attribute_id_rate_unit, range_1_to_age, range_1_comment_text, attribute_id_rate_basis_description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type8 obj : param.getRow()) {
            try {

                nullOrFloat(pstmt, 1, obj.getRange_1_rate());
                nullOrString(pstmt, 2, obj.getAttribute_id_rate_unit_description_mv());
                nullOrInt(pstmt, 3, productId);
                nullOrString(pstmt, 4, obj.getAttribute_id_rate_unit_description());
                nullOrInt(pstmt, 5, obj.getRange_1_from_age());
                nullOrString(pstmt, 6, obj.getAttribute_id_rate_basis());
                nullOrString(pstmt, 7, obj.getAttribute_id_rate_basis_description_mv());
                nullOrInt(pstmt, 8, serviceId);
                nullOrString(pstmt, 9, obj.getAttribute_id_rate_unit());
                nullOrInt(pstmt, 10, obj.getRange_1_to_age());
                nullOrString(pstmt, 11, obj.getRange_1_comment_text());
                nullOrString(pstmt, 12, obj.getAttribute_id_rate_basis_description());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_make_model_style_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_make_model_style (style, product_id, make, service_id, model, hire_capacity) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type18 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getStyle());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getMake());
                nullOrInt(pstmt, 4, serviceId);
                nullOrString(pstmt, 5, obj.getModel());
                nullOrInt(pstmt, 6, obj.getHire_capacity());

                afrc += pstmt.executeUpdate();

                int agi = queryAgi(pstmt);

                if (agi > 0) {
                    insertMakeModelStyleRel(conn, productId, serviceId, agi,
                            obj.getService_make_model_style_attr_relationship());
                } else {
                    String tpl = "product: %s service: %s no found generated key for: service_make_model_style";
                    Const.logger.error(String.format(tpl, productId, serviceId));
                }

                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;
    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_minimum_period_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_minimum_period (minimum_period_to_date, product_id, minimum_period_text, tariff_code_id, season_name, minimum_period, service_id, attribute_id_season_description_mv, attribute_id_season, attribute_id_season_description, minimum_period_from_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type19 obj : param.getRow()) {
            try {

                nullOrDate(pstmt, 1, obj.getMinimum_period_to_date());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getMinimum_period_text());
                nullOrString(pstmt, 4, obj.getTariff_code_id());
                nullOrString(pstmt, 5, obj.getSeason_name());
                nullOrInt(pstmt, 6, obj.getMinimum_period());
                nullOrInt(pstmt, 7, serviceId);
                nullOrString(pstmt, 8, obj.getAttribute_id_season_description_mv());
                nullOrString(pstmt, 9, obj.getAttribute_id_season());
                nullOrString(pstmt, 10, obj.getAttribute_id_season_description());
                nullOrDate(pstmt, 11, obj.getMinimum_period_from_date());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_multimedia_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_multimedia (attribute_id_multimedia_content, attribute_id_multimedia_file_description, keywords, attribute_id_multimedia_file_description_mv, authored_date, attribute_id_multimedia_file, server_path, copyright, photographer, attribute_id_size_orientation, width, attribute_id_size_orientation_description, attribute_id_multimedia_content_description, alt_text, market_variant_id, height, multimedia_description, product_id, attribute_id_multimedia_content_description_mv, caption, multimedia_id, service_id, attribute_id_size_orientation_description_mv, multimedia_source, sequence_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type9 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_multimedia_content());
                nullOrString(pstmt, 2, obj.getAttribute_id_multimedia_file_description());
                nullOrString(pstmt, 3, obj.getKeywords());
                nullOrString(pstmt, 4, obj.getAttribute_id_multimedia_file_description_mv());
                nullOrDate(pstmt, 5, obj.getAuthored_date());
                nullOrString(pstmt, 6, obj.getAttribute_id_multimedia_file());
                nullOrString(pstmt, 7, obj.getServer_path());
                nullOrString(pstmt, 8, obj.getCopyright());
                nullOrString(pstmt, 9, obj.getPhotographer());
                nullOrString(pstmt, 10, obj.getAttribute_id_size_orientation());
                nullOrInt(pstmt, 11, obj.getWidth());
                nullOrString(pstmt, 12, obj.getAttribute_id_size_orientation_description());
                nullOrString(pstmt, 13, obj.getAttribute_id_multimedia_content_description());
                nullOrString(pstmt, 14, obj.getAlt_text());
                nullOrString(pstmt, 15, obj.getMarket_variant_id());
                nullOrInt(pstmt, 16, obj.getHeight());
                nullOrString(pstmt, 17, obj.getMultimedia_description());
                nullOrInt(pstmt, 18, productId);
                nullOrString(pstmt, 19, obj.getAttribute_id_multimedia_content_description_mv());
                nullOrString(pstmt, 20, obj.getCaption());
                nullOrInt(pstmt, 21, obj.getMultimedia_id());
                nullOrInt(pstmt, 22, serviceId);
                nullOrString(pstmt, 23, obj.getAttribute_id_size_orientation_description_mv());
                nullOrString(pstmt, 24, obj.getMultimedia_source());
                nullOrInt(pstmt, 25, obj.getSequence_number());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                if (Const.SQL_DUPLICATE == e.getErrorCode()) {
                    String tpl = "product:%S service:%s duplicate multimedia";
                    Const.logger.error(String.format(tpl, productId, serviceId));
                } else {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_rate_basis_comment_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_rate_basis_comment (product_id, rate_basis_comment_text, attribute_id_rate_basis, attribute_id_rate_basis_description_mv, service_id, attribute_id_rate_basis_description) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type10 obj : param.getRow()) {
            try {

                nullOrInt(pstmt, 1, productId);
                nullOrString(pstmt, 2, obj.getRate_basis_comment_text());
                nullOrString(pstmt, 3, obj.getAttribute_id_rate_basis());
                nullOrString(pstmt, 4, obj.getAttribute_id_rate_basis_description_mv());
                nullOrInt(pstmt, 5, serviceId);
                nullOrString(pstmt, 6, obj.getAttribute_id_rate_basis_description());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId, Service_record_type0 obj) {

        if (null == obj) {
            return 0;
        }
        String cmd = "insert into t3_service_record (service_name, attribute_id_ceiling_height_unit, service_description, tour_duration, room_available_ceiling_height, room_area, attribute_id_ceiling_height_unit_description, room_dimensions, international_ready_flag, attribute_id_room_shape, attribute_id_room_shape_description, pickup_available_text, pets_allowed_text, children_catered_for_flag, attribute_id_time_unit, attribute_id_time_unit_description_mv, disabled_access_flag, pets_allowed_flag, children_catered_for_text, disabled_access_text, attribute_id_ceiling_height_unit_description_mv, product_id, pickup_available_flag, attribute_id_time_unit_description, service_id, attribute_id_room_shape_description_mv, sequence_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;
        try {

            nullOrString(pstmt, 1, obj.getService_name());
            nullOrString(pstmt, 2, obj.getAttribute_id_ceiling_height_unit());
            nullOrString(pstmt, 3, obj.getService_description());
            nullOrInt(pstmt, 4, obj.getTour_duration());
            nullOrFloat(pstmt, 5, obj.getRoom_available_ceiling_height());
            nullOrInt(pstmt, 6, obj.getRoom_area());
            nullOrString(pstmt, 7, obj.getAttribute_id_ceiling_height_unit_description());
            nullOrString(pstmt, 8, obj.getRoom_dimensions());
            nullOrBoolean(pstmt, 9, obj.getInternational_ready_flag());
            nullOrString(pstmt, 10, obj.getAttribute_id_room_shape());
            nullOrString(pstmt, 11, obj.getAttribute_id_room_shape_description());
            nullOrString(pstmt, 12, obj.getPickup_available_text());
            nullOrString(pstmt, 13, obj.getPets_allowed_text());
            nullOrBoolean(pstmt, 14, obj.getChildren_catered_for_flag());
            nullOrString(pstmt, 15, obj.getAttribute_id_time_unit());
            nullOrString(pstmt, 16, obj.getAttribute_id_time_unit_description_mv());
            nullOrBoolean(pstmt, 17, obj.getDisabled_access_flag());
            nullOrBoolean(pstmt, 18, obj.getPets_allowed_flag());
            nullOrString(pstmt, 19, obj.getChildren_catered_for_text());
            nullOrString(pstmt, 20, obj.getDisabled_access_text());
            nullOrString(pstmt, 21, obj.getAttribute_id_ceiling_height_unit_description_mv());
            nullOrInt(pstmt, 22, productId);
            nullOrBoolean(pstmt, 23, obj.getPickup_available_flag());
            nullOrString(pstmt, 24, obj.getAttribute_id_time_unit_description());
            nullOrInt(pstmt, 25, serviceId);
            nullOrString(pstmt, 26, obj.getAttribute_id_room_shape_description_mv());
            nullOrInt(pstmt, 27, obj.getSequence_number());

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_route_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_route (state_name, attribute_id_geocode_proj_sys_description, route_text, attribute_id_address_description_mv, override_domestic_region_flag, geocode_gda_longitude, geocode_amg_north, country_name, attribute_id_address_description, same_postal_address_flag, attribute_id_address, area_name, geocode_gda_latitude, city_name, attribute_id_geocode_proj_sys, suburb_name, product_id, attribute_id_geocode_proj_sys_description_mv, address_lot_plan, address_line_3, address_postal_code, geocode_amg_east, service_id, address_line_4, address_line_2, geocode_amg_zone, sequence_number, address_line_1) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type13 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getState_name());
                nullOrString(pstmt, 2, obj.getAttribute_id_geocode_proj_sys_description());
                nullOrString(pstmt, 3, obj.getRoute_text());
                nullOrString(pstmt, 4, obj.getAttribute_id_address_description_mv());
                nullOrBoolean(pstmt, 5, obj.getOverride_domestic_region_flag());
                nullOrFloat(pstmt, 6, obj.getGeocode_gda_longitude());
                nullOrFloat(pstmt, 7, obj.getGeocode_amg_north());
                nullOrString(pstmt, 8, obj.getCountry_name());
                nullOrString(pstmt, 9, obj.getAttribute_id_address_description());
                nullOrBoolean(pstmt, 10, obj.getSame_postal_address_flag());
                nullOrString(pstmt, 11, obj.getAttribute_id_address());
                nullOrString(pstmt, 12, obj.getArea_name());
                nullOrFloat(pstmt, 13, obj.getGeocode_gda_latitude());
                nullOrString(pstmt, 14, obj.getCity_name());
                nullOrString(pstmt, 15, obj.getAttribute_id_geocode_proj_sys());
                nullOrString(pstmt, 16, obj.getSuburb_name());
                nullOrInt(pstmt, 17, productId);
                nullOrString(pstmt, 18, obj.getAttribute_id_geocode_proj_sys_description_mv());
                nullOrString(pstmt, 19, obj.getAddress_lot_plan());
                nullOrString(pstmt, 20, obj.getAddress_line_3());
                nullOrString(pstmt, 21, obj.getAddress_postal_code());
                nullOrFloat(pstmt, 22, obj.getGeocode_amg_east());
                nullOrInt(pstmt, 23, serviceId);
                nullOrString(pstmt, 24, obj.getAddress_line_4());
                nullOrString(pstmt, 25, obj.getAddress_line_2());
                nullOrInt(pstmt, 26, obj.getGeocode_amg_zone());
                nullOrInt(pstmt, 27, obj.getSequence_number());
                nullOrString(pstmt, 28, obj.getAddress_line_1());

                afrc += pstmt.executeUpdate();

                int agi = queryAgi(pstmt);

                if (agi > 0) {
                    insertServiceRouteStreetDirectoryRel(conn, productId, serviceId, agi,
                            obj.getService_route_street_directory_relationship());
                } else {
                    String tpl = "product: %s service: %s no found generated key for: service_route";
                    Const.logger.error(String.format(tpl, productId, serviceId));
                }

                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_season_tariff_comment_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_season_tariff_comment (comment_date, comment_text, attribute_id_language_written, product_id, tariff_code_id, attribute_id_season_description, season_name, attribute_id_season_tariff_comment, attribute_id_season, market_variant_id, attribute_id_season_tariff_comment_description_mv, attribute_id_season_tariff_comment_description, service_id, attribute_id_language_written_description_mv, attribute_id_season_description_mv, attribute_id_language_written_description, comment_follow_up_date, sequence_number, comment_source) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type20 obj : param.getRow()) {
            try {

                nullOrDate(pstmt, 1, obj.getComment_date());
                nullOrString(pstmt, 2, obj.getComment_text());
                nullOrString(pstmt, 3, obj.getAttribute_id_language_written());
                nullOrInt(pstmt, 4, productId);
                nullOrString(pstmt, 5, obj.getTariff_code_id());
                nullOrString(pstmt, 6, obj.getAttribute_id_season_description());
                nullOrString(pstmt, 7, obj.getSeason_name());
                nullOrString(pstmt, 8, obj.getAttribute_id_season_tariff_comment());
                nullOrString(pstmt, 9, obj.getAttribute_id_season());
                nullOrString(pstmt, 10, obj.getMarket_variant_id());
                nullOrString(pstmt, 11, obj.getAttribute_id_season_tariff_comment_description_mv());
                nullOrString(pstmt, 12, obj.getAttribute_id_season_tariff_comment_description());
                nullOrInt(pstmt, 13, serviceId);
                nullOrString(pstmt, 14, obj.getAttribute_id_language_written_description_mv());
                nullOrString(pstmt, 15, obj.getAttribute_id_season_description_mv());
                nullOrString(pstmt, 16, obj.getAttribute_id_language_written_description());
                nullOrDate(pstmt, 17, obj.getComment_follow_up_date());
                nullOrInt(pstmt, 18, obj.getSequence_number());
                nullOrString(pstmt, 19, obj.getComment_source());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_start_location_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_start_location (state_name, attribute_id_geocode_proj_sys_description, attribute_id_address_description_mv, override_domestic_region_flag, geocode_gda_longitude, geocode_amg_north, country_name, attribute_id_address_description, area_name, attribute_id_address, same_postal_address_flag, geocode_gda_latitude, city_name, attribute_id_geocode_proj_sys, suburb_name, product_id, attribute_id_geocode_proj_sys_description_mv, address_lot_plan, address_line_3, address_postal_code, start_location_text, geocode_amg_east, service_id, address_line_4, address_line_2, geocode_amg_zone, sequence_number, address_line_1) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type16 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getState_name());
                nullOrString(pstmt, 2, obj.getAttribute_id_geocode_proj_sys_description());
                nullOrString(pstmt, 3, obj.getAttribute_id_address_description_mv());
                nullOrBoolean(pstmt, 4, obj.getOverride_domestic_region_flag());
                nullOrFloat(pstmt, 5, obj.getGeocode_gda_longitude());
                nullOrFloat(pstmt, 6, obj.getGeocode_amg_north());
                nullOrString(pstmt, 7, obj.getCountry_name());
                nullOrString(pstmt, 8, obj.getAttribute_id_address_description());
                nullOrString(pstmt, 9, obj.getArea_name());
                nullOrString(pstmt, 10, obj.getAttribute_id_address());
                nullOrBoolean(pstmt, 11, obj.getSame_postal_address_flag());
                nullOrFloat(pstmt, 12, obj.getGeocode_gda_latitude());
                nullOrString(pstmt, 13, obj.getCity_name());
                nullOrString(pstmt, 14, obj.getAttribute_id_geocode_proj_sys());
                nullOrString(pstmt, 15, obj.getSuburb_name());
                nullOrInt(pstmt, 16, productId);
                nullOrString(pstmt, 17, obj.getAttribute_id_geocode_proj_sys_description_mv());
                nullOrString(pstmt, 18, obj.getAddress_lot_plan());
                nullOrString(pstmt, 19, obj.getAddress_line_3());
                nullOrString(pstmt, 20, obj.getAddress_postal_code());
                nullOrString(pstmt, 21, obj.getStart_location_text());
                nullOrFloat(pstmt, 22, obj.getGeocode_amg_east());
                nullOrInt(pstmt, 23, serviceId);
                nullOrString(pstmt, 24, obj.getAddress_line_4());
                nullOrString(pstmt, 25, obj.getAddress_line_2());
                nullOrInt(pstmt, 26, obj.getGeocode_amg_zone());
                nullOrInt(pstmt, 27, obj.getSequence_number());
                nullOrString(pstmt, 28, obj.getAddress_line_1());

                afrc += pstmt.executeUpdate();

                int agi = queryAgi(pstmt);

                if (agi > 0) {
                    insertStartLocDirectoryRel(conn, productId, serviceId, agi,
                            obj.getService_start_loc_street_directory_relationship());

                    insertStartLocDomesticRegionRel(conn, productId, serviceId, agi,
                            obj.getService_start_loc_domestic_region_relationship());
                } else {
                    String tpl = "product: %s service: %s no found generated key for: service_start_location";
                    Const.logger.error(String.format(tpl, productId, serviceId));
                }

                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_tariff_adult_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_tariff_adult (attribute_id_accomm_std_description, rate_amount_per_unit, rate_for_5_adult, attribute_id_rate_unit, valid_on_friday_flag, attribute_id_extra_rate_unit_description, must_include_saturday_flag, extra_rate_period, rate_period, must_include_tuesday_flag, season_name, attribute_id_rate_basis, valid_on_tuesday_flag, attribute_id_rate_basis_description_mv, attribute_id_tariff_duration_description_mv, must_include_friday_flag, valid_on_wednesday_flag, attribute_id_tariff_duration, must_include_sunday_flag, valid_on_sunday_flag, rate_for_7_adult, extra_rate_amount_per_unit, rate_for_3_adult, attribute_id_extra_rate_unit_description_mv, tour_number_adult, attribute_id_accomm_std, attribute_id_season_description_mv, rate_for_4_adult, attribute_id_season_description, attribute_id_rate_basis_description, attribute_id_accomm_std_description_mv, tour_rate_amount_per_adult, attribute_id_tariff_duration_description, comment_text, attribute_id_rate_unit_description_mv, tariff_code_id, attribute_id_extra_rate_unit, must_include_monday_flag, valid_on_monday_flag, must_include_wednesday_flag, rate_for_8_adult, rate_for_2_adult, valid_on_saturday_flag, product_id, attribute_id_rate_unit_description, tariff_type_text, rate_for_1_adult, must_include_thursday_flag, rate_for_6_adult, valid_on_thursday_flag, attribute_id_season, service_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type21 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_accomm_std_description());
                nullOrFloat(pstmt, 2, obj.getRate_amount_per_unit());
                nullOrFloat(pstmt, 3, obj.getRate_for_5_adult());
                nullOrString(pstmt, 4, obj.getAttribute_id_rate_unit());
                nullOrBoolean(pstmt, 5, obj.getValid_on_friday_flag());
                nullOrString(pstmt, 6, obj.getAttribute_id_extra_rate_unit_description());
                nullOrBoolean(pstmt, 7, obj.getMust_include_saturday_flag());
                nullOrInt(pstmt, 8, obj.getExtra_rate_period());
                nullOrInt(pstmt, 9, obj.getRate_period());
                nullOrBoolean(pstmt, 10, obj.getMust_include_tuesday_flag());
                nullOrString(pstmt, 11, obj.getSeason_name());
                nullOrString(pstmt, 12, obj.getAttribute_id_rate_basis());
                nullOrBoolean(pstmt, 13, obj.getValid_on_tuesday_flag());
                nullOrString(pstmt, 14, obj.getAttribute_id_rate_basis_description_mv());
                nullOrString(pstmt, 15, obj.getAttribute_id_tariff_duration_description_mv());
                nullOrBoolean(pstmt, 16, obj.getMust_include_friday_flag());
                nullOrBoolean(pstmt, 17, obj.getValid_on_wednesday_flag());
                nullOrString(pstmt, 18, obj.getAttribute_id_tariff_duration());
                nullOrBoolean(pstmt, 19, obj.getMust_include_sunday_flag());
                nullOrBoolean(pstmt, 20, obj.getValid_on_sunday_flag());
                nullOrFloat(pstmt, 21, obj.getRate_for_7_adult());
                nullOrFloat(pstmt, 22, obj.getExtra_rate_amount_per_unit());
                nullOrFloat(pstmt, 23, obj.getRate_for_3_adult());
                nullOrString(pstmt, 24, obj.getAttribute_id_extra_rate_unit_description_mv());
                nullOrInt(pstmt, 25, obj.getTour_number_adult());
                nullOrString(pstmt, 26, obj.getAttribute_id_accomm_std());
                nullOrString(pstmt, 27, obj.getAttribute_id_season_description_mv());
                nullOrFloat(pstmt, 28, obj.getRate_for_4_adult());
                nullOrString(pstmt, 29, obj.getAttribute_id_season_description());
                nullOrString(pstmt, 30, obj.getAttribute_id_rate_basis_description());
                nullOrString(pstmt, 31, obj.getAttribute_id_accomm_std_description_mv());
                nullOrFloat(pstmt, 32, obj.getTour_rate_amount_per_adult());
                nullOrString(pstmt, 33, obj.getAttribute_id_tariff_duration_description());
                nullOrString(pstmt, 34, obj.getComment_text());
                nullOrString(pstmt, 35, obj.getAttribute_id_rate_unit_description_mv());
                nullOrString(pstmt, 36, obj.getTariff_code_id());
                nullOrString(pstmt, 37, obj.getAttribute_id_extra_rate_unit());
                nullOrBoolean(pstmt, 38, obj.getMust_include_monday_flag());
                nullOrBoolean(pstmt, 39, obj.getValid_on_monday_flag());
                nullOrBoolean(pstmt, 40, obj.getMust_include_wednesday_flag());
                nullOrFloat(pstmt, 41, obj.getRate_for_8_adult());
                nullOrFloat(pstmt, 42, obj.getRate_for_2_adult());
                nullOrBoolean(pstmt, 43, obj.getValid_on_saturday_flag());
                nullOrInt(pstmt, 44, productId);
                nullOrString(pstmt, 45, obj.getAttribute_id_rate_unit_description());
                nullOrString(pstmt, 46, obj.getTariff_type_text());
                nullOrFloat(pstmt, 47, obj.getRate_for_1_adult());
                nullOrBoolean(pstmt, 48, obj.getMust_include_thursday_flag());
                nullOrFloat(pstmt, 49, obj.getRate_for_6_adult());
                nullOrBoolean(pstmt, 50, obj.getValid_on_thursday_flag());
                nullOrString(pstmt, 51, obj.getAttribute_id_season());
                nullOrInt(pstmt, 52, serviceId);

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_tariff_child_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_tariff_child (range_2_to_age, rate_period, season_name, attribute_id_rate_basis, attribute_id_rate_basis_description_mv, attribute_id_tariff_duration_description_mv, attribute_id_tariff_duration, range_1_to_age, range_2_rate, range_4_rate, range_1_rate, range_3_comment_text, range_3_to_age, attribute_id_tariff_duration_description, attribute_id_season_description_mv, attribute_id_season_description, attribute_id_rate_basis_description, range_3_from_age, tariff_code_id, range_1_from_age, range_3_rate, service_id, range_2_comment_text, range_4_comment_text, product_id, tariff_type_text, range_4_to_age, range_4_from_age, attribute_id_season, range_1_comment_text, range_2_from_age) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type22 obj : param.getRow()) {
            try {
                nullOrInt(pstmt, 1, obj.getRange_2_to_age());
                nullOrInt(pstmt, 2, obj.getRate_period());
                nullOrString(pstmt, 3, obj.getSeason_name());
                nullOrString(pstmt, 4, obj.getAttribute_id_rate_basis());
                nullOrString(pstmt, 5, obj.getAttribute_id_rate_basis_description_mv());
                nullOrString(pstmt, 6, obj.getAttribute_id_tariff_duration_description_mv());
                nullOrString(pstmt, 7, obj.getAttribute_id_tariff_duration());
                nullOrInt(pstmt, 8, obj.getRange_1_to_age());
                nullOrFloat(pstmt, 9, obj.getRange_2_rate());
                nullOrFloat(pstmt, 10, obj.getRange_4_rate());
                nullOrFloat(pstmt, 11, obj.getRange_1_rate());
                nullOrString(pstmt, 12, obj.getRange_3_comment_text());
                nullOrInt(pstmt, 13, obj.getRange_3_to_age());
                nullOrString(pstmt, 14, obj.getAttribute_id_tariff_duration_description());
                nullOrString(pstmt, 15, obj.getAttribute_id_season_description_mv());
                nullOrString(pstmt, 16, obj.getAttribute_id_season_description());
                nullOrString(pstmt, 17, obj.getAttribute_id_rate_basis_description());
                nullOrInt(pstmt, 18, obj.getRange_3_from_age());
                nullOrString(pstmt, 19, obj.getTariff_code_id());
                nullOrInt(pstmt, 20, obj.getRange_1_from_age());
                nullOrFloat(pstmt, 21, obj.getRange_3_rate());
                nullOrInt(pstmt, 22, serviceId);
                nullOrString(pstmt, 23, obj.getRange_2_comment_text());
                nullOrString(pstmt, 24, obj.getRange_4_comment_text());
                nullOrInt(pstmt, 25, productId);
                nullOrString(pstmt, 26, obj.getTariff_type_text());
                nullOrInt(pstmt, 27, obj.getRange_4_to_age());
                nullOrInt(pstmt, 28, obj.getRange_4_from_age());
                nullOrString(pstmt, 29, obj.getAttribute_id_season());
                nullOrString(pstmt, 30, obj.getRange_1_comment_text());
                nullOrInt(pstmt, 31, obj.getRange_2_from_age());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_tariff_concession_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_tariff_concession (comment_text, product_id, tariff_code_id, season_name, attribute_id_rate_basis, attribute_id_rate_basis_description_mv, service_id, attribute_id_season_description_mv, attribute_id_season, attribute_id_season_description, attribute_id_rate_basis_description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type23 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getComment_text());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getTariff_code_id());
                nullOrString(pstmt, 4, obj.getSeason_name());
                nullOrString(pstmt, 5, obj.getAttribute_id_rate_basis());
                nullOrString(pstmt, 6, obj.getAttribute_id_rate_basis_description_mv());
                nullOrInt(pstmt, 7, serviceId);
                nullOrString(pstmt, 8, obj.getAttribute_id_season_description_mv());
                nullOrString(pstmt, 9, obj.getAttribute_id_season());
                nullOrString(pstmt, 10, obj.getAttribute_id_season_description());
                nullOrString(pstmt, 11, obj.getAttribute_id_rate_basis_description());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, int serviceId,
            Service_vertical_classification_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_vertical_classification (product_type_description_mv, product_sub_type_lowest, product_id, product_sub_type_1_id, product_sub_type_2_description, product_sub_type_2_description_mv, product_sub_type_1_description, product_sub_type_2_id, service_id, product_type_description, product_sub_type_1_description_mv, product_type_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type24 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getProduct_type_description_mv());
                nullOrString(pstmt, 2, obj.getProduct_sub_type_lowest());
                nullOrInt(pstmt, 3, productId);
                nullOrString(pstmt, 4, obj.getProduct_sub_type_1_id());
                nullOrString(pstmt, 5, obj.getProduct_sub_type_2_description());
                nullOrString(pstmt, 6, obj.getProduct_sub_type_2_description_mv());
                nullOrString(pstmt, 7, obj.getProduct_sub_type_1_description());
                nullOrString(pstmt, 8, obj.getProduct_sub_type_2_id());
                nullOrInt(pstmt, 9, serviceId);
                nullOrString(pstmt, 10, obj.getProduct_type_description());
                nullOrString(pstmt, 11, obj.getProduct_sub_type_1_description_mv());
                nullOrString(pstmt, 12, obj.getProduct_type_id());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertConfigurationAttrRelationship(Connection conn, int productId,
            int serviceId, int configId, Service_configuration_attr_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_configuration_attr_relationship (attribute_id_description_mv, product_id, attribute_id_description, configuration_id, attribute_type_id, attribute_id, service_id, attribute_text) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type2 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_description_mv());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getAttribute_id_description());
                nullOrInt(pstmt, 4, configId);
                nullOrString(pstmt, 5, obj.getAttribute_type_id());
                nullOrString(pstmt, 6, obj.getAttribute_id());
                nullOrInt(pstmt, 7, serviceId);
                nullOrString(pstmt, 8, obj.getAttribute_text());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertDepartureTimeRelationship(Connection conn, int productId,
            int serviceId, int departDateId, Service_departure_time_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_departure_time_relationship (thursday_depart_time, saturday_return_time, sunday_return_time, wednesday_depart_time, monday_depart_time, thursday_return_time, friday_return_time, saturday_depart_time, tuesday_return_time, departure_date_id, sunday_depart_time, wednesday_return_time, service_id, monday_return_time, friday_depart_time, tuesday_depart_time, product_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type4 obj : param.getRow()) {
            try {

                nullOrInt(pstmt, 1, obj.getThursday_depart_time());
                nullOrInt(pstmt, 2, obj.getSaturday_return_time());
                nullOrInt(pstmt, 3, obj.getSunday_return_time());
                nullOrInt(pstmt, 4, obj.getWednesday_depart_time());
                nullOrInt(pstmt, 5, obj.getMonday_depart_time());
                nullOrInt(pstmt, 6, obj.getThursday_return_time());
                nullOrInt(pstmt, 7, obj.getFriday_return_time());
                nullOrInt(pstmt, 8, obj.getSaturday_depart_time());
                nullOrInt(pstmt, 9, obj.getTuesday_return_time());
                nullOrInt(pstmt, 10, departDateId);
                nullOrInt(pstmt, 11, obj.getSunday_depart_time());
                nullOrInt(pstmt, 12, obj.getWednesday_return_time());
                nullOrInt(pstmt, 13, serviceId);
                nullOrInt(pstmt, 14, obj.getMonday_return_time());
                nullOrInt(pstmt, 15, obj.getFriday_depart_time());
                nullOrInt(pstmt, 16, obj.getTuesday_depart_time());
                nullOrInt(pstmt, 17, productId);

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertMakeModelStyleRel(Connection conn, int productId, int serviceId,
            int makeModelStyleId, Service_make_model_style_attr_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_make_model_style_attr_relationship (attribute_id_description_mv, product_id, attribute_id_description, attribute_type_id, attribute_id, service_id, attribute_text, make_model_style_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type17 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_description_mv());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getAttribute_id_description());
                nullOrString(pstmt, 4, obj.getAttribute_type_id());
                nullOrString(pstmt, 5, obj.getAttribute_id());
                nullOrInt(pstmt, 6, serviceId);
                nullOrString(pstmt, 7, obj.getAttribute_text());
                nullOrInt(pstmt, 8, makeModelStyleId);

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertServiceRouteStreetDirectoryRel(Connection conn, int productId,
            int serviceId, int serviceRouteId,
            Service_route_street_directory_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_route_street_directory_relationship (attribute_id_street_directory_description, product_id, street_directory_reference, attribute_id_street_directory_description_mv, service_id, attribute_id_street_directory, service_route_id) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type12 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_street_directory_description());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getStreet_directory_reference());
                nullOrString(pstmt, 4, obj.getAttribute_id_street_directory_description_mv());
                nullOrInt(pstmt, 5, serviceId);
                nullOrString(pstmt, 6, obj.getAttribute_id_street_directory());
                nullOrInt(pstmt, 7, serviceRouteId);

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertStartLocDirectoryRel(Connection conn, int productId, int serviceId,
            int startLocId, Service_start_loc_street_directory_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_start_loc_street_directory_relationship (attribute_id_street_directory_description, product_id, street_directory_reference, start_location_id, service_id, attribute_id_street_directory_description_mv, attribute_id_street_directory) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type14 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_street_directory_description());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getStreet_directory_reference());
                nullOrInt(pstmt, 4, startLocId);
                nullOrInt(pstmt, 5, serviceId);
                nullOrString(pstmt, 6, obj.getAttribute_id_street_directory_description_mv());
                nullOrString(pstmt, 7, obj.getAttribute_id_street_directory());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertStartLocDomesticRegionRel(Connection conn, int productId,
            int serviceId, int startLocId,
            Service_start_loc_domestic_region_relationship_type0 param) {
        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t3_service_start_loc_domestic_region_relationship (product_id, domestic_region_code, start_location_id, service_id, attribute_id_dom_region_type, domestic_region_name) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type15 obj : param.getRow()) {
            try {

                nullOrInt(pstmt, 1, productId);
                nullOrString(pstmt, 2, obj.getDomestic_region_code());
                nullOrInt(pstmt, 3, startLocId);
                nullOrInt(pstmt, 4, serviceId);
                nullOrString(pstmt, 5, obj.getAttribute_id_dom_region_type());
                nullOrString(pstmt, 6, obj.getDomestic_region_name());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;
    }

    public static void nullOrFloat(PreparedStatement pstmt, int i, Decimal103 obj)
            throws SQLException {
        if (null == obj) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, obj.getDecimal103().floatValue());
        }
    }

    public static void nullOrFloat(PreparedStatement pstmt, int i, Decimal129 dec)
            throws SQLException {
        if (null == dec) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, dec.getDecimal129().floatValue());
        }
    }

    public static void nullOrFloat(PreparedStatement pstmt, int i, Decimal154 dec)
            throws SQLException {
        if (null == dec) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, dec.getDecimal154().floatValue());
        }
    }
}
