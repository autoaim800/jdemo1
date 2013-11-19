package com.billsoft.triptra.inserter.getproduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.billsoft.triptra.Const;
import com.billsoft.triptra.inserter.DbInserter;
import com.billsoft.triptra.xsd.getproduct.Decimal103;
import com.billsoft.triptra.xsd.getproduct.Decimal129;
import com.billsoft.triptra.xsd.getproduct.Decimal154;
import com.billsoft.triptra.xsd.getproduct.Product_address_area_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_address_domestic_region_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_address_street_directory_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_record_type0;
import com.billsoft.triptra.xsd.getproduct.Row_type2;
import com.billsoft.triptra.xsd.getproduct.Row_type3;
import com.billsoft.triptra.xsd.getproduct.Row_type4;

public class GpAddress extends DbInserter {

    public static int insert(Connection conn, int prodId,
            com.billsoft.triptra.xsd.getproduct.Row_type5 obj) {
        if (null == obj) {
            return 0;
        }

        String cmd = "insert into t2_product_address (state_name, attribute_id_geocode_proj_sys_description, attribute_id_address_description_mv, override_domestic_region_flag, geocode_gda_longitude, geocode_amg_north, country_name, geocode_gda_latitude, area_name, attribute_id_address, attribute_id_address_description, same_postal_address_flag, city_name, address_line_4, suburb_name, product_id, attribute_id_geocode_proj_sys_description_mv, address_lot_plan, geocode_amg_zone, address_postal_code, geocode_amg_east, attribute_id_geocode_proj_sys, address_line_2, address_line_3, address_line_1) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        try {
            nullOrString(pstmt, 1, obj.getState_name());
            nullOrString(pstmt, 2, obj.getAttribute_id_geocode_proj_sys_description());
            nullOrString(pstmt, 3, obj.getAttribute_id_address_description_mv());
            nullOrBoolean(pstmt, 4, obj.getOverride_domestic_region_flag());
            nullOrFloat(pstmt, 5, obj.getGeocode_gda_longitude());
            nullOrFloat(pstmt, 6, obj.getGeocode_amg_north());
            nullOrString(pstmt, 7, obj.getCountry_name());
            nullOrFloat(pstmt, 8, obj.getGeocode_gda_latitude());
            nullOrString(pstmt, 9, obj.getArea_name());
            nullOrString(pstmt, 10, obj.getAttribute_id_address());
            nullOrString(pstmt, 11, obj.getAttribute_id_address_description());
            nullOrBoolean(pstmt, 12, obj.getSame_postal_address_flag());
            nullOrString(pstmt, 13, obj.getCity_name());
            nullOrString(pstmt, 14, obj.getAddress_line_4());
            nullOrString(pstmt, 15, obj.getSuburb_name());
            nullOrInt(pstmt, 16, prodId);
            nullOrString(pstmt, 17, obj.getAttribute_id_geocode_proj_sys_description_mv());
            nullOrString(pstmt, 18, obj.getAddress_lot_plan());
            nullOrInt(pstmt, 19, obj.getGeocode_amg_zone());
            nullOrString(pstmt, 20, obj.getAddress_postal_code());
            nullOrFloat(pstmt, 21, obj.getGeocode_amg_east());
            nullOrString(pstmt, 22, obj.getAttribute_id_geocode_proj_sys());
            nullOrString(pstmt, 23, obj.getAddress_line_2());
            nullOrString(pstmt, 24, obj.getAddress_line_3());
            nullOrString(pstmt, 25, obj.getAddress_line_1());

            afrc = pstmt.executeUpdate();

            int agi = queryAgi(pstmt);

            if (agi > 0) {
                insertProductAddressAreaRel(conn, prodId, agi,
                        obj.getProduct_address_area_relationship());
                insertProductAddressStreetDirectoryRel(conn, prodId, agi,
                        obj.getProduct_address_street_directory_relationship());
                insertProductAddressDomesticRegionRel(conn, prodId, agi,
                        obj.getProduct_address_domestic_region_relationship());
            } else {
                String tpl = "product: %s no found generated key for: product_address";
                Const.logger.error(String.format(tpl, prodId));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return afrc;
    }

    private static int insertProductAddressAreaRel(Connection conn, int productId,
            int productAddressId, Product_address_area_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_address_area_relationship (area_name, product_address_id, product_id, area_type) values (?, ?, ?, ?)";
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

                nullOrString(pstmt, 1, obj.getArea_name());
                nullOrInt(pstmt, 2, productAddressId);
                nullOrInt(pstmt, 3, productId);
                nullOrString(pstmt, 4, obj.getArea_type());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertProductAddressDomesticRegionRel(Connection conn, int productId,
            int productAddressId, Product_address_domestic_region_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_address_domestic_region_relationship (domestic_region_code, domestic_region_type, product_address_id, product_id, domestic_region_name) values (?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type3 obj : param.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getDomestic_region_code());
                nullOrString(pstmt, 2, obj.getDomestic_region_type());
                nullOrInt(pstmt, 3, productAddressId);
                nullOrInt(pstmt, 4, productId);
                nullOrString(pstmt, 5, obj.getDomestic_region_name());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    private static int insertProductAddressStreetDirectoryRel(Connection conn, int productId,
            int productAddressId, Product_address_street_directory_relationship_type0 param) {

        if (null == param || null == param.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_address_street_directory_relationship (attribute_id_street_directory_description, product_id, product_address_id, street_directory_reference, attribute_id_street_directory_description_mv, attribute_id_street_directory) values (?, ?, ?, ?, ?, ?)";
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

                nullOrString(pstmt, 1, obj.getAttribute_id_street_directory_description());
                nullOrInt(pstmt, 2, productId);
                nullOrInt(pstmt, 3, productAddressId);
                nullOrString(pstmt, 4, obj.getStreet_directory_reference());
                nullOrString(pstmt, 5, obj.getAttribute_id_street_directory_description_mv());
                nullOrString(pstmt, 6, obj.getAttribute_id_street_directory());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, Product_record_type0 obj) {

        String cmd = "insert into t2_product_record (nearest_airport_iata, temp_winter_low, attribute_id_rate_basis_description_mv, brochure_available_flag, frequency_start_date, temp_jul_low, rainfall_apr, temp_nov_low, iata_code, rainfall_may, rainfall_mar, nearest_highway, temp_dec_high, attribute_id_airport_distance_unit_description_mv, humidity_mar, humidity_oct, area_name, number_of_restaurants, temp_jun_low, product_category_description_mv, disabled_access_flag, atdw_expiry_date, lifts_flag, attribute_id_frequency, attribute_id_language_spoken_description_mv, attribute_id_currency_description, attribute_id_event_status_description_mv, humidity_may, temp_aug_low, temp_winter_high, humidity_jun, humidity_jul, rainfall_feb, nearest_airport, temp_mar_high, temp_aug_high, state_name, australian_business_number, nearest_airport_distance, rate_basis_text, humidity_nov, rainfall_jan, temp_summer_low, convention_capacity, temp_summer_high, temp_jul_high, humidity_dec, product_category_description, validity_date_from, pets_allowed_flag, convention_text, attribute_id_rate_basis_description, temp_apr_high, children_catered_for_flag, temp_oct_high, visitor_numbers, attribute_id_atdw_status_description, temp_mar_low, disabled_access_text, validity_date_to, suburb_name, product_id, temp_apr_low, population_year, attribute_id_atdw_status_description_mv, attribute_id_airport_distance_unit_description, humidity_apr, temp_feb_high, contributing_organisation_id, rate_to, total_capacity, domestic_region_code, temp_jan_high, free_entry_flag, temp_may_low, rainfall_nov, attribute_id_nearest_gtwy_iata_description, temp_jun_high, altitude, check_out_time, national_head_office_flag, temp_nov_high, humidity_jan, humidity_summer_average, international_ready_flag, attribute_id_gateway_distance_unit, rate_from, attribute_id_currency_description_mv, pets_allowed_text, dining_capacity, temp_may_high, attribute_id_airport_distance_unit, attribute_id_nearest_gtwy_iata_description_mv, country_name, humidity_aug, humidity_winter_average, population, bedding_configuration_text, temp_sep_high, frequency_end_date, temp_feb_low, product_description, owning_organisation_id, temp_jan_low, rainfall_dec, domestic_region_name, attribute_id_event_status_description, rainfall_aug, temp_sep_low, attribute_id_frequency_description_mv, temp_oct_low, attribute_id_currency, attribute_id_gateway_distance_unit_description, attribute_id_rate_basis, check_in_time, number_of_rooms, rainfall_summer_average, rainfall_oct, humidity_feb, temp_dec_low, product_category_id, rainfall_sep, rainfall_jul, attribute_id_frequency_description, rainfall_jun, attribute_id_atdw_status, product_name, brochure_available_text, international_region_name, market_variant_id, attribute_id_language_spoken_description, city_name, children_catered_for_text, nearest_gateway, rainfall_winter_average, attribute_id_language_spoken, nearest_gateway_distance, number_of_floors, attribute_id_nearest_gtwy_iata, attribute_id_event_status, attribute_id_gateway_distance_unit_description_mv, humidity_sep, reception_hours_text) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            nullOrString(pstmt, 1, obj.getNearest_airport_iata());
            nullOrInt(pstmt, 2, obj.getTemp_winter_low());
            nullOrString(pstmt, 3, obj.getAttribute_id_rate_basis_description_mv());
            nullOrBoolean(pstmt, 4, obj.getBrochure_available_flag());
            nullOrDate(pstmt, 5, obj.getFrequency_start_date());
            nullOrInt(pstmt, 6, obj.getTemp_jul_low());
            nullOrInt(pstmt, 7, obj.getRainfall_apr());
            nullOrInt(pstmt, 8, obj.getTemp_nov_low());
            nullOrString(pstmt, 9, obj.getIata_code());
            nullOrInt(pstmt, 10, obj.getRainfall_may());
            nullOrInt(pstmt, 11, obj.getRainfall_mar());
            nullOrString(pstmt, 12, obj.getNearest_highway());
            nullOrInt(pstmt, 13, obj.getTemp_dec_high());
            nullOrString(pstmt, 14, obj.getAttribute_id_airport_distance_unit_description_mv());
            nullOrInt(pstmt, 15, obj.getHumidity_mar());
            nullOrInt(pstmt, 16, obj.getHumidity_oct());
            nullOrString(pstmt, 17, obj.getArea_name());
            nullOrInt(pstmt, 18, obj.getNumber_of_restaurants());
            nullOrInt(pstmt, 19, obj.getTemp_jun_low());
            nullOrString(pstmt, 20, obj.getProduct_category_description_mv());
            nullOrBoolean(pstmt, 21, obj.getDisabled_access_flag());
            nullOrDate(pstmt, 22, obj.getAtdw_expiry_date());
            nullOrBoolean(pstmt, 23, obj.getLifts_flag());
            nullOrString(pstmt, 24, obj.getAttribute_id_frequency());
            nullOrString(pstmt, 25, obj.getAttribute_id_language_spoken_description_mv());
            nullOrString(pstmt, 26, obj.getAttribute_id_currency_description());
            nullOrString(pstmt, 27, obj.getAttribute_id_event_status_description_mv());
            nullOrInt(pstmt, 28, obj.getHumidity_may());
            nullOrInt(pstmt, 29, obj.getTemp_aug_low());
            nullOrInt(pstmt, 30, obj.getTemp_winter_high());
            nullOrInt(pstmt, 31, obj.getHumidity_jun());
            nullOrInt(pstmt, 32, obj.getHumidity_jul());
            nullOrInt(pstmt, 33, obj.getRainfall_feb());
            nullOrString(pstmt, 34, obj.getNearest_airport());
            nullOrInt(pstmt, 35, obj.getTemp_mar_high());
            nullOrInt(pstmt, 36, obj.getTemp_aug_high());
            nullOrString(pstmt, 37, obj.getState_name());
            nullOrString(pstmt, 38, obj.getAustralian_business_number());
            nullOrFloat(pstmt, 39, obj.getNearest_airport_distance());
            nullOrString(pstmt, 40, obj.getRate_basis_text());
            nullOrInt(pstmt, 41, obj.getHumidity_nov());
            nullOrInt(pstmt, 42, obj.getRainfall_jan());
            nullOrInt(pstmt, 43, obj.getTemp_summer_low());
            nullOrInt(pstmt, 44, obj.getConvention_capacity());
            nullOrInt(pstmt, 45, obj.getTemp_summer_high());
            nullOrInt(pstmt, 46, obj.getTemp_jul_high());
            nullOrInt(pstmt, 47, obj.getHumidity_dec());
            nullOrString(pstmt, 48, obj.getProduct_category_description());
            nullOrDate(pstmt, 49, obj.getValidity_date_from());
            nullOrBoolean(pstmt, 50, obj.getPets_allowed_flag());
            nullOrString(pstmt, 51, obj.getConvention_text());
            nullOrString(pstmt, 52, obj.getAttribute_id_rate_basis_description());
            nullOrInt(pstmt, 53, obj.getTemp_apr_high());
            nullOrBoolean(pstmt, 54, obj.getChildren_catered_for_flag());
            nullOrInt(pstmt, 55, obj.getTemp_oct_high());
            nullOrString(pstmt, 56, obj.getVisitor_numbers());
            nullOrString(pstmt, 57, obj.getAttribute_id_atdw_status_description());
            nullOrInt(pstmt, 58, obj.getTemp_mar_low());
            nullOrString(pstmt, 59, obj.getDisabled_access_text());
            nullOrDate(pstmt, 60, obj.getValidity_date_to());
            nullOrString(pstmt, 61, obj.getSuburb_name());
            nullOrInt(pstmt, 62, productId);
            nullOrInt(pstmt, 63, obj.getTemp_apr_low());
            nullOrInt(pstmt, 64, obj.getPopulation_year());
            nullOrString(pstmt, 65, obj.getAttribute_id_atdw_status_description_mv());
            nullOrString(pstmt, 66, obj.getAttribute_id_airport_distance_unit_description());
            nullOrInt(pstmt, 67, obj.getHumidity_apr());
            nullOrInt(pstmt, 68, obj.getTemp_feb_high());
            nullOrString(pstmt, 69, obj.getContributing_organisation_id());
            nullOrFloat(pstmt, 70, obj.getRate_to());
            nullOrInt(pstmt, 71, obj.getTotal_capacity());
            nullOrString(pstmt, 72, obj.getDomestic_region_code());
            nullOrInt(pstmt, 73, obj.getTemp_jan_high());
            nullOrBoolean(pstmt, 74, obj.getFree_entry_flag());
            nullOrInt(pstmt, 75, obj.getTemp_may_low());
            nullOrInt(pstmt, 76, obj.getRainfall_nov());
            nullOrString(pstmt, 77, obj.getAttribute_id_nearest_gtwy_iata_description());
            nullOrInt(pstmt, 78, obj.getTemp_jun_high());
            nullOrInt(pstmt, 79, obj.getAltitude());
            nullOrInt(pstmt, 80, obj.getCheck_out_time());
            nullOrBoolean(pstmt, 81, obj.getNational_head_office_flag());
            nullOrInt(pstmt, 82, obj.getTemp_nov_high());
            nullOrInt(pstmt, 83, obj.getHumidity_jan());
            nullOrInt(pstmt, 84, obj.getHumidity_summer_average());
            nullOrBoolean(pstmt, 85, obj.getInternational_ready_flag());
            nullOrString(pstmt, 86, obj.getAttribute_id_gateway_distance_unit());
            nullOrFloat(pstmt, 87, obj.getRate_from());
            nullOrString(pstmt, 88, obj.getAttribute_id_currency_description_mv());
            nullOrString(pstmt, 89, obj.getPets_allowed_text());
            nullOrInt(pstmt, 90, obj.getDining_capacity());
            nullOrInt(pstmt, 91, obj.getTemp_may_high());
            nullOrString(pstmt, 92, obj.getAttribute_id_airport_distance_unit());
            nullOrString(pstmt, 93, obj.getAttribute_id_nearest_gtwy_iata_description_mv());
            nullOrString(pstmt, 94, obj.getCountry_name());
            nullOrInt(pstmt, 95, obj.getHumidity_aug());
            nullOrInt(pstmt, 96, obj.getHumidity_winter_average());
            nullOrInt(pstmt, 97, obj.getPopulation());
            nullOrString(pstmt, 98, obj.getBedding_configuration_text());
            nullOrInt(pstmt, 99, obj.getTemp_sep_high());
            nullOrDate(pstmt, 100, obj.getFrequency_end_date());
            nullOrInt(pstmt, 101, obj.getTemp_feb_low());
            nullOrString(pstmt, 102, obj.getProduct_description());
            nullOrString(pstmt, 103, obj.getOwning_organisation_id());
            nullOrInt(pstmt, 104, obj.getTemp_jan_low());
            nullOrInt(pstmt, 105, obj.getRainfall_dec());
            nullOrString(pstmt, 106, obj.getDomestic_region_name());
            nullOrString(pstmt, 107, obj.getAttribute_id_event_status_description());
            nullOrInt(pstmt, 108, obj.getRainfall_aug());
            nullOrInt(pstmt, 109, obj.getTemp_sep_low());
            nullOrString(pstmt, 110, obj.getAttribute_id_frequency_description_mv());
            nullOrInt(pstmt, 111, obj.getTemp_oct_low());
            nullOrString(pstmt, 112, obj.getAttribute_id_currency());
            nullOrString(pstmt, 113, obj.getAttribute_id_gateway_distance_unit_description());
            nullOrString(pstmt, 114, obj.getAttribute_id_rate_basis());
            nullOrInt(pstmt, 115, obj.getCheck_in_time());
            nullOrInt(pstmt, 116, obj.getNumber_of_rooms());
            nullOrInt(pstmt, 117, obj.getRainfall_summer_average());
            nullOrInt(pstmt, 118, obj.getRainfall_oct());
            nullOrInt(pstmt, 119, obj.getHumidity_feb());
            nullOrInt(pstmt, 120, obj.getTemp_dec_low());
            nullOrString(pstmt, 121, obj.getProduct_category_id());
            nullOrInt(pstmt, 122, obj.getRainfall_sep());
            nullOrInt(pstmt, 123, obj.getRainfall_jul());
            nullOrString(pstmt, 124, obj.getAttribute_id_frequency_description());
            nullOrInt(pstmt, 125, obj.getRainfall_jun());
            nullOrString(pstmt, 126, obj.getAttribute_id_atdw_status());
            nullOrString(pstmt, 127, obj.getProduct_name());
            nullOrString(pstmt, 128, obj.getBrochure_available_text());
            nullOrString(pstmt, 129, obj.getInternational_region_name());
            nullOrString(pstmt, 130, obj.getMarket_variant_id());
            nullOrString(pstmt, 131, obj.getAttribute_id_language_spoken_description());
            nullOrString(pstmt, 132, obj.getCity_name());
            nullOrString(pstmt, 133, obj.getChildren_catered_for_text());
            nullOrString(pstmt, 134, obj.getNearest_gateway());
            nullOrInt(pstmt, 135, obj.getRainfall_winter_average());
            nullOrString(pstmt, 136, obj.getAttribute_id_language_spoken());
            nullOrFloat(pstmt, 137, obj.getNearest_gateway_distance());
            nullOrInt(pstmt, 138, obj.getNumber_of_floors());
            nullOrString(pstmt, 139, obj.getAttribute_id_nearest_gtwy_iata());
            nullOrString(pstmt, 140, obj.getAttribute_id_event_status());
            nullOrString(pstmt, 141, obj.getAttribute_id_gateway_distance_unit_description_mv());
            nullOrInt(pstmt, 142, obj.getHumidity_sep());
            nullOrString(pstmt, 143, obj.getReception_hours_text());

            afrc = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return afrc;

    }

    protected static void nullOrFloat(PreparedStatement pstmt, int i, Decimal103 obj)
            throws SQLException {
        if (null == obj) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, obj.getDecimal103().floatValue());
        }
    }

    /**
     * this method uses getproduct.Decimal129, different from its parent
     * queryproducts.Decimal129 due to atws flaw
     * 
     */
    protected static void nullOrFloat(PreparedStatement pstmt, int i, Decimal129 dec)
            throws SQLException {
        if (null == dec) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, dec.getDecimal129().floatValue());
        }
    }

    protected static void nullOrFloat(PreparedStatement pstmt, int i, Decimal154 dec)
            throws SQLException {
        if (null == dec) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, dec.getDecimal154().floatValue());
        }

    }

}
