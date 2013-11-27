package com.billsoft.triptra.vthree.inserter;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.billsoft.triptra.Const;
import com.billsoft.triptra.xsd.queryproducts.Decimal103;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.ArrayOfCO_TypeType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_IdValueType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_TypeType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_BookingInformationType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_BusinessDetailsType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_ContactDetailsType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_MarketingDetailsType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_MerchantDetailsType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_ProductType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_ProviderLongRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_RegionGeocodeDetailsType;

/**
 * dirty insert code here
 * 
 * @author bill
 * 
 */
public class Provider extends VThreeInserter {

    public static int insert(Connection conn, PR_ProviderLongRSType obj) {

        // if (null == param || null == param.getRow()){ return 0; }
        String cmd = "insert into t4_Provider (allow_pickup_locations, creation_date, minimum_guide_price_accomm, full_name, id, bookability, supplier_id, Description, modification_date, minimum_guide_price_aet, maximum_guide_price_aet, maximum_guide_price, type, status, integration_type, short_name, minimum_guide_price, obx_id, supplier_short_name, supplier_obx_id, maximum_guide_price_accomm, content_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;
        // for(SomeType obj:param.getRow()){
        try {

            nullOrString(pstmt, 1, obj.getAllow_pickup_locations());
            nullOrDate(pstmt, 2, obj.getCreation_date());
            nullOrInt(pstmt, 3, obj.getMinimum_guide_price_accomm());
            nullOrString(pstmt, 4, obj.getFull_name());
            nullOrString(pstmt, 5, obj.getId());
            nullOrString(pstmt, 6, obj.getBookability());
            nullOrString(pstmt, 7, obj.getSupplier_id());
            nullOrString(pstmt, 8, obj.getDescription());
            nullOrDate(pstmt, 9, obj.getModification_date());
            nullOrInt(pstmt, 10, obj.getMinimum_guide_price_aet());
            nullOrInt(pstmt, 11, obj.getMaximum_guide_price_aet());
            nullOrInt(pstmt, 12, obj.getMaximum_guide_price());
            nullOrString(pstmt, 13, obj.getType());
            nullOrString(pstmt, 14, obj.getStatus());
            nullOrString(pstmt, 15, obj.getIntegration_type());
            nullOrString(pstmt, 16, obj.getShort_name());
            nullOrInt(pstmt, 17, obj.getMinimum_guide_price());
            nullOrString(pstmt, 18, obj.getObx_id());
            nullOrString(pstmt, 19, obj.getSupplier_short_name());
            nullOrString(pstmt, 20, obj.getSupplier_obx_id());
            nullOrInt(pstmt, 21, obj.getMaximum_guide_price_accomm());
            nullOrString(pstmt, 22, obj.getContent_id());

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // }

        return afrc;

    }

    public static int insert(Connection conn, String providerId, PR_ContactDetailsType obj) {

        // if (null == param || null == param.getRow()){ return 0; }
        String cmd = "insert into t4_ContactDetails (provider_id) values (?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        // for(SomeType obj:param.getRow()){
        try {

            nullOrString(pstmt, 1, providerId);

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // }

        return afrc;

    }

    public static int insert(Connection conn, String providerId, PR_BookingInformationType obj) {

        // if (null == param || null == param.getRow()){ return 0; }
        String cmd = "insert into t4_BookingDetails (ConditionsOfUse, provider_id, BookingTerms, BookingSummaryMessage) values (?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        // for(SomeType obj:param.getRow()){
        try {

            nullOrString(pstmt, 1, obj.getConditionsOfUse());
            nullOrString(pstmt, 2, providerId);
            nullOrString(pstmt, 3, obj.getBookingTerms());
            nullOrString(pstmt, 4, obj.getBookingSummaryMessage());

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // }

        return afrc;

    }

    public static int insert(Connection conn, String providerId, PR_BusinessDetailsType obj) {

        // if (null == param || null == param.getRow()){ return 0; }
        String cmd = "insert into t4_BusinessDetails (provider_id, acn, abn) values (?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        // for(SomeType obj:param.getRow()){
        try {

            nullOrString(pstmt, 1, providerId);
            nullOrString(pstmt, 2, obj.getAcn());
            nullOrString(pstmt, 3, obj.getAbn());

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // }

        return afrc;

    }

    public static void insert(Connection conn, String providerId, PR_ProductType product) {
        // TODO Auto-generated method stub

    }

    public static int insert(Connection conn, String providerId, PR_RegionGeocodeDetailsType obj) {

        // if (null == param || null == param.getRow()){ return 0; }
        String cmd = "insert into t4_RegionGeocodeDetails (provider_id, region_id, region, longitude, state, state_abbreviation, latitude, state_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        // for(SomeType obj:param.getRow()){
        try {

            nullOrString(pstmt, 1, providerId);
            nullOrString(pstmt, 2, obj.getRegion_id());
            nullOrString(pstmt, 3, obj.getRegion());
            nullOrFloat(pstmt, 4, obj.getLongitude());
            nullOrString(pstmt, 5, obj.getState());
            nullOrString(pstmt, 6, obj.getState_abbreviation());
            nullOrFloat(pstmt, 7, obj.getLatitude());
            nullOrString(pstmt, 8, obj.getState_id());

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // }

        return afrc;

    }

    public static int insert(Connection conn, String providerId, ArrayOfCO_TypeType param) {

        if (null == param || null == param.getCreditCardAccepted()) {
            return 0;
        }
        String cmd = "insert into t4_ECommerceDetails (provider_id, CreditCardAccepted) values (?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (CO_TypeType obj : param.getCreditCardAccepted()) {
            try {

                nullOrString(pstmt, 1, providerId);
                nullOrString(pstmt, 2, obj.getType());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return afrc;
    }

    public static void insert(Connection conn, String providerId, PR_MerchantDetailsType merchant) {
        // TODO Auto-generated method stub
    }

    public static void nullOrFloat(PreparedStatement pstmt, int i, BigDecimal obj)
            throws SQLException {
        if (null == obj) {
            pstmt.setNull(i, java.sql.Types.FLOAT);
        } else {
            pstmt.setFloat(i, obj.floatValue());
        }
    }

    public static int insert(Connection conn, String providerId, PR_MarketingDetailsType md) {
        if (null == md || null == md.getIndustryCategory()) {
            Const.logger.info(String.format("product %s does not have industry category",
                    providerId));
            return 0;
        }

        String cmd = "insert into t4_ProviderIndustryCategory(product_id, cat_id) values (?, ?)";

        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;

        for (CO_IdValueType cat : md.getIndustryCategory()) {
            try {
                pstmt.setString(1, providerId);
                pstmt.setString(2, cat.getId());

                afrc += pstmt.executeUpdate();
                pstmt.clearParameters();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // out of for
        return afrc;
    }
}
