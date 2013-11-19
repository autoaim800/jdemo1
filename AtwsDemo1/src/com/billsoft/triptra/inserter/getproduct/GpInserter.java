package com.billsoft.triptra.inserter.getproduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.billsoft.triptra.Const;
import com.billsoft.triptra.inserter.DbInserter;
import com.billsoft.triptra.xsd.getproduct.Decimal103;
import com.billsoft.triptra.xsd.getproduct.Decimal154;
import com.billsoft.triptra.xsd.getproduct.Product_article_type0;
import com.billsoft.triptra.xsd.getproduct.Product_attribute_type0;
import com.billsoft.triptra.xsd.getproduct.Product_communication_type0;
import com.billsoft.triptra.xsd.getproduct.Product_entry_cost_type0;
import com.billsoft.triptra.xsd.getproduct.Product_licence_type0;
import com.billsoft.triptra.xsd.getproduct.Product_multimedia_type0;
import com.billsoft.triptra.xsd.getproduct.Product_name_type0;
import com.billsoft.triptra.xsd.getproduct.Product_open_time_type0;
import com.billsoft.triptra.xsd.getproduct.Product_proximity_type0;
import com.billsoft.triptra.xsd.getproduct.Product_related_product_type0;
import com.billsoft.triptra.xsd.getproduct.Product_related_service_type0;
import com.billsoft.triptra.xsd.getproduct.Product_sponsor_type0;
import com.billsoft.triptra.xsd.getproduct.Row_type1;
import com.billsoft.triptra.xsd.getproduct.Row_type15;
import com.billsoft.triptra.xsd.getproduct.Row_type16;
import com.billsoft.triptra.xsd.getproduct.Row_type23;
import com.billsoft.triptra.xsd.getproduct.Row_type31;
import com.billsoft.triptra.xsd.getproduct.Row_type32;
import com.billsoft.triptra.xsd.getproduct.Row_type33;
import com.billsoft.triptra.xsd.getproduct.Row_type34;
import com.billsoft.triptra.xsd.getproduct.Row_type35;
import com.billsoft.triptra.xsd.getproduct.Row_type37;
import com.billsoft.triptra.xsd.getproduct.Row_type38;
import com.billsoft.triptra.xsd.getproduct.Row_type60;

public class GpInserter extends DbInserter {

    public static int insert(Connection conn, int prodId, Product_article_type0 article) {
        if (null == article || null == article.getRow()) {
            return 0;
        }

        String cmd = "insert into t2_product_article (article_name, article_keywords, article_description, product_id) values (?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type15 obj : article.getRow()) {
            try {
                nullOrString(pstmt, 1, obj.getArticle_name());
                nullOrString(pstmt, 2, obj.getArticle_keywords());
                nullOrString(pstmt, 3, obj.getArticle_description());
                nullOrInt(pstmt, 4, prodId);
                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return afrc;

    }

    public static int insert(Connection conn, int productId, Product_attribute_type0 attribute) {
        if (null == attribute || null == attribute.getRow()) {
            return 0;
        }

        String cmd = "insert into t2_product_attribute (attribute_type_id, product_id, attribute_type_id_description_mv, attribute_id_description, attribute_id_description_mv, attribute_id, attribute_type_id_description, attribute_text) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type16 obj : attribute.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_type_id());
                nullOrInt(pstmt, 2, productId);
                nullOrString(pstmt, 3, obj.getAttribute_type_id_description_mv());
                nullOrString(pstmt, 4, obj.getAttribute_id_description());
                nullOrString(pstmt, 5, obj.getAttribute_id_description_mv());
                nullOrString(pstmt, 6, obj.getAttribute_id());
                nullOrString(pstmt, 7, obj.getAttribute_type_id_description());
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

    public static int insert(Connection conn, int prodId, Product_communication_type0 communication) {
        if (null == communication || null == communication.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_communication (attribute_id_coverage_description_mv, communication_area_code, attribute_id_communication_description_mv, attribute_id_communication_description, attribute_id_communication, communication_detail, attribute_id_coverage_description, coverage_text, country_name, communication_isd_code, attribute_id_coverage, product_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type23 obj : communication.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_coverage_description_mv());
                nullOrString(pstmt, 2, obj.getCommunication_area_code());
                nullOrString(pstmt, 3, obj.getAttribute_id_communication_description_mv());
                nullOrString(pstmt, 4, obj.getAttribute_id_communication_description());
                nullOrString(pstmt, 5, obj.getAttribute_id_communication());
                nullOrString(pstmt, 6, obj.getCommunication_detail());
                nullOrString(pstmt, 7, obj.getAttribute_id_coverage_description());
                nullOrString(pstmt, 8, obj.getCoverage_text());
                nullOrString(pstmt, 9, obj.getCountry_name());
                nullOrString(pstmt, 10, obj.getCommunication_isd_code());
                nullOrString(pstmt, 11, obj.getAttribute_id_coverage());
                nullOrInt(pstmt, 12, prodId);

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int prodId, Product_entry_cost_type0 entryCost) {
        if (null == entryCost || null == entryCost.getRow()) {
            return 0;
        }

        String cmd = "insert into t2_product_entry_cost (comment_text, attribute_id_entry_cost_description_mv, product_id, entry_cost, valid_to_date, attribute_id_entry_cost_description, child_age_to, valid_from_date, attribute_id_entry_cost, child_age_from) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

        int afrc = 0;
        for (Row_type1 obj : entryCost.getRow()) {
            try {
                nullOrString(pstmt, 1, obj.getComment_text());
                nullOrString(pstmt, 2, obj.getAttribute_id_entry_cost_description_mv());
                nullOrInt(pstmt, 3, prodId);
                nullOrFloat(pstmt, 4, obj.getEntry_cost());
                nullOrDate(pstmt, 5, obj.getValid_to_date());
                nullOrString(pstmt, 6, obj.getAttribute_id_entry_cost_description());
                nullOrInt(pstmt, 7, obj.getChild_age_to());
                nullOrDate(pstmt, 8, obj.getValid_from_date());
                nullOrString(pstmt, 9, obj.getAttribute_id_entry_cost());
                nullOrInt(pstmt, 10, obj.getChild_age_from());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int prodId, Product_licence_type0 lic) {
        if (null == lic || null == lic.getRow()) {
            return 0;
        }

        String cmd = "insert into t2_product_licence (product_id, attribute_id_licence_type_description, attribute_id_licence_type, licence_text, licence_qualifications, licence_number, attribute_id_licence_type_description_mv) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

        int afrc = 0;

        for (Row_type31 obj : lic.getRow()) {
            try {
                nullOrInt(pstmt, 1, prodId);
                nullOrString(pstmt, 2, obj.getAttribute_id_licence_type_description());
                nullOrString(pstmt, 3, obj.getAttribute_id_licence_type());
                nullOrString(pstmt, 4, obj.getLicence_text());
                nullOrString(pstmt, 5, obj.getLicence_qualifications());
                nullOrString(pstmt, 6, obj.getLicence_number());
                nullOrString(pstmt, 7, obj.getAttribute_id_licence_type_description_mv());
                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return afrc;

    }

    public static int insert(Connection conn, int prodId, Product_multimedia_type0 mm) {
        if (null == mm || null == mm.getRow()) {
            return 0;
        }

        String cmd = "insert into t2_product_multimedia (attribute_id_multimedia_content, attribute_id_multimedia_file_description, keywords, attribute_id_multimedia_file_description_mv, authored_date, video_thumbnail_path, attribute_id_multimedia_file, server_path, copyright, photographer, attribute_id_size_orientation, width, attribute_id_size_orientation_description, attribute_id_multimedia_content_description, alt_text, market_variant_id, height, multimedia_description, product_id, video_id, attribute_id_multimedia_content_description_mv, caption, multimedia_id, attribute_id_size_orientation_description_mv, multimedia_source, sequence_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

        int afrc = 0;

        for (Row_type32 obj : mm.getRow()) {
            try {
                nullOrString(pstmt, 1, obj.getAttribute_id_multimedia_content());

                nullOrString(pstmt, 2, obj.getAttribute_id_multimedia_file_description());
                nullOrString(pstmt, 3, obj.getKeywords());
                nullOrString(pstmt, 4, obj.getAttribute_id_multimedia_file_description_mv());
                nullOrDate(pstmt, 5, obj.getAuthored_date());
                nullOrString(pstmt, 6, obj.getVideo_thumbnail_path());
                nullOrString(pstmt, 7, obj.getAttribute_id_multimedia_file());
                nullOrString(pstmt, 8, obj.getServer_path());
                nullOrString(pstmt, 9, obj.getCopyright());
                nullOrString(pstmt, 10, obj.getPhotographer());
                nullOrString(pstmt, 11, obj.getAttribute_id_size_orientation());
                nullOrInt(pstmt, 12, obj.getWidth());
                nullOrString(pstmt, 13, obj.getAttribute_id_size_orientation_description());
                nullOrString(pstmt, 14, obj.getAttribute_id_multimedia_content_description());
                nullOrString(pstmt, 15, obj.getAlt_text());
                nullOrString(pstmt, 16, obj.getMarket_variant_id());
                nullOrInt(pstmt, 17, obj.getHeight());
                nullOrString(pstmt, 18, obj.getMultimedia_description());
                nullOrInt(pstmt, 19, prodId);
                nullOrString(pstmt, 20, obj.getVideo_id());
                nullOrString(pstmt, 21, obj.getAttribute_id_multimedia_content_description_mv());
                nullOrString(pstmt, 22, obj.getCaption());
                nullOrInt(pstmt, 23, obj.getMultimedia_id());
                nullOrString(pstmt, 24, obj.getAttribute_id_size_orientation_description_mv());
                nullOrString(pstmt, 25, obj.getMultimedia_source());
                nullOrInt(pstmt, 26, obj.getSequence_number());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();
                pstmt.clearParameters();

            } catch (SQLException e) {
                if (Const.SQL_DUPLICATE == e.getErrorCode()) {
                    Const.logger
                            .error(String.format("product: %s duplicate multimedia", prodId));
                } else {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return afrc;
    }

    public static int insert(Connection conn, int prodId, Product_name_type0 prodName) {
        if (null == prodName || null == prodName.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_name (attribute_id_language_written, product_id, attribute_id_name_description, attribute_id_name_description_mv, market_variant_id, attribute_id_name, attribute_id_language_written_description_mv, attribute_id_language_written_description, name_detail) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type33 obj : prodName.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_language_written());
                nullOrInt(pstmt, 2, prodId);
                nullOrString(pstmt, 3, obj.getAttribute_id_name_description());
                nullOrString(pstmt, 4, obj.getAttribute_id_name_description_mv());
                nullOrString(pstmt, 5, obj.getMarket_variant_id());
                nullOrString(pstmt, 6, obj.getAttribute_id_name());
                nullOrString(pstmt, 7, obj.getAttribute_id_language_written_description_mv());
                nullOrString(pstmt, 8, obj.getAttribute_id_language_written_description());
                nullOrString(pstmt, 9, obj.getName_detail());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int prodId, Product_open_time_type0 openTime) {
        if (null == openTime || null == openTime.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_open_time (product_id, open_time_text, open_time_friday, open_time_thursday, open_time_tuesday, open_time_sunday, open_time_monday, open_time_christmas_day, open_time_wednesday, open_time_saturday, open_time_public_holiday, open_time_good_friday) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

        int afrc = 0;

        for (Row_type34 obj : openTime.getRow()) {
            try {
                nullOrInt(pstmt, 1, prodId);
                nullOrString(pstmt, 2, obj.getOpen_time_text());
                nullOrString(pstmt, 3, obj.getOpen_time_friday());
                nullOrString(pstmt, 4, obj.getOpen_time_thursday());
                nullOrString(pstmt, 5, obj.getOpen_time_tuesday());
                nullOrString(pstmt, 6, obj.getOpen_time_sunday());
                nullOrString(pstmt, 7, obj.getOpen_time_monday());
                nullOrString(pstmt, 8, obj.getOpen_time_christmas_day());
                nullOrString(pstmt, 9, obj.getOpen_time_wednesday());
                nullOrString(pstmt, 10, obj.getOpen_time_saturday());
                nullOrString(pstmt, 11, obj.getOpen_time_public_holiday());
                nullOrString(pstmt, 12, obj.getOpen_time_good_friday());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return afrc;

    }

    public static int insert(Connection conn, int productId, Product_proximity_type0 proximity) {
        if (null == proximity || null == proximity.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_proximity (attribute_id_fly_unit_description, attribute_id_drive_unit, attribute_id_proximity, attribute_id_walk_unit, attribute_id_walk_unit_description, time_fly, attribute_id_proximity_description, attribute_id_distance_unit_description_mv, attribute_id_proximity_description_mv, attribute_id_distance_unit, attribute_id_distance_unit_description, attribute_id_drive_unit_description_mv, attribute_id_direction, proximity_distance, attribute_id_walk_unit_description_mv, time_drive, attribute_id_fly_unit_description_mv, attribute_id_drive_unit_description, attribute_id_direction_description_mv, time_walk, attribute_id_fly_unit, attribute_id_direction_description, proximity_text) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

        int afrc = 0;
        for (Row_type35 obj : proximity.getRow()) {

            try {
                nullOrString(pstmt, 1, obj.getAttribute_id_fly_unit_description());
                nullOrString(pstmt, 2, obj.getAttribute_id_drive_unit());
                nullOrString(pstmt, 3, obj.getAttribute_id_proximity());
                nullOrString(pstmt, 4, obj.getAttribute_id_walk_unit());
                nullOrString(pstmt, 5, obj.getAttribute_id_walk_unit_description());
                nullOrFloat(pstmt, 6, obj.getTime_fly());
                nullOrString(pstmt, 7, obj.getAttribute_id_proximity_description());
                nullOrString(pstmt, 8, obj.getAttribute_id_distance_unit_description_mv());
                nullOrString(pstmt, 9, obj.getAttribute_id_proximity_description_mv());
                nullOrString(pstmt, 10, obj.getAttribute_id_distance_unit());
                nullOrString(pstmt, 11, obj.getAttribute_id_distance_unit_description());
                nullOrString(pstmt, 12, obj.getAttribute_id_drive_unit_description_mv());
                nullOrString(pstmt, 13, obj.getAttribute_id_direction());
                nullOrFloat(pstmt, 14, obj.getProximity_distance());
                nullOrString(pstmt, 15, obj.getAttribute_id_walk_unit_description_mv());
                nullOrFloat(pstmt, 16, obj.getTime_drive());
                nullOrString(pstmt, 17, obj.getAttribute_id_fly_unit_description_mv());
                nullOrString(pstmt, 18, obj.getAttribute_id_drive_unit_description());
                nullOrString(pstmt, 19, obj.getAttribute_id_direction_description_mv());
                nullOrFloat(pstmt, 20, obj.getTime_walk());
                nullOrString(pstmt, 21, obj.getAttribute_id_fly_unit());
                nullOrString(pstmt, 22, obj.getAttribute_id_direction_description());
                nullOrString(pstmt, 23, obj.getProximity_text());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return afrc;
    }

    public static int insert(Connection conn, int productId, Product_related_product_type0 product) {

        if (null == product || null == product.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_related_product (attribute_id_relationship, product_id, orig_product_id, related_product_category_id, attribute_id_relationship_description, attribute_id_relationship_description_mv) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type37 obj : product.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_relationship());
                nullOrString(pstmt, 2, obj.getProduct_id());
                nullOrInt(pstmt, 3, productId);
                nullOrString(pstmt, 4, obj.getRelated_product_category_id());
                nullOrString(pstmt, 5, obj.getAttribute_id_relationship_description());
                nullOrString(pstmt, 6, obj.getAttribute_id_relationship_description_mv());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int productId, Product_related_service_type0 service) {

        if (null == service || null == service.getRow()) {
            return 0;
        }
        String cmd = "insert into t2_product_related_service (attribute_id_relationship, product_id, service_name, orig_product_id, product_category_id, attribute_id_relationship_description, attribute_id_relationship_description_mv) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type38 obj : service.getRow()) {
            try {

                nullOrString(pstmt, 1, obj.getAttribute_id_relationship());
                nullOrString(pstmt, 2, obj.getProduct_id());
                nullOrString(pstmt, 3, obj.getService_name());
                nullOrInt(pstmt, 4, productId);
                nullOrString(pstmt, 5, obj.getProduct_category_id());
                nullOrString(pstmt, 6, obj.getAttribute_id_relationship_description());
                nullOrString(pstmt, 7, obj.getAttribute_id_relationship_description_mv());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;

    }

    public static int insert(Connection conn, int prodId, Product_sponsor_type0 sponsor) {
        if (null == sponsor || null == sponsor.getRow()) {
            return 0;
        }

        String cmd = "insert into t2_product_sponsor (sponsor_name, sponsorship_type_text, attribute_id_multimedia_content, attribute_id_multimedia_file_description, keywords, attribute_id_multimedia_file_description_mv, authored_date, attribute_id_multimedia_file, server_path, copyright, photographer, attribute_id_size_orientation, width, attribute_id_size_orientation_description, email, attribute_id_multimedia_content_description, alt_text, height, multimedia_description, product_id, url, attribute_id_multimedia_content_description_mv, caption, multimedia_id, attribute_id_size_orientation_description_mv, multimedia_source, sequence_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (Row_type60 obj : sponsor.getRow()) {

            try {
                nullOrString(pstmt, 1, obj.getSponsor_name());
                nullOrString(pstmt, 2, obj.getSponsorship_type_text());
                nullOrString(pstmt, 3, obj.getAttribute_id_multimedia_content());
                nullOrString(pstmt, 4, obj.getAttribute_id_multimedia_file_description());
                nullOrString(pstmt, 5, obj.getKeywords());
                nullOrString(pstmt, 6, obj.getAttribute_id_multimedia_file_description_mv());
                nullOrDate(pstmt, 7, obj.getAuthored_date());
                nullOrString(pstmt, 8, obj.getAttribute_id_multimedia_file());
                nullOrString(pstmt, 9, obj.getServer_path());
                nullOrString(pstmt, 10, obj.getCopyright());
                nullOrString(pstmt, 11, obj.getPhotographer());
                nullOrString(pstmt, 12, obj.getAttribute_id_size_orientation());
                nullOrInt(pstmt, 13, obj.getWidth());
                nullOrString(pstmt, 14, obj.getAttribute_id_size_orientation_description());
                nullOrString(pstmt, 15, obj.getEmail());
                nullOrString(pstmt, 16, obj.getAttribute_id_multimedia_content_description());
                nullOrString(pstmt, 17, obj.getAlt_text());
                nullOrInt(pstmt, 18, obj.getHeight());
                nullOrString(pstmt, 19, obj.getMultimedia_description());
                nullOrInt(pstmt, 20, prodId);
                nullOrString(pstmt, 21, obj.getUrl());
                nullOrString(pstmt, 22, obj.getAttribute_id_multimedia_content_description_mv());
                nullOrString(pstmt, 23, obj.getCaption());
                nullOrInt(pstmt, 24, obj.getMultimedia_id());
                nullOrString(pstmt, 25, obj.getAttribute_id_size_orientation_description_mv());
                nullOrString(pstmt, 26, obj.getMultimedia_source());
                nullOrInt(pstmt, 27, obj.getSequence_number());

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

    public static void nullOrFloat(PreparedStatement pstmt, int i, Decimal154 dec)
            throws SQLException {
        if (null == dec) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, dec.getDecimal154().floatValue());
        }
    }
}
