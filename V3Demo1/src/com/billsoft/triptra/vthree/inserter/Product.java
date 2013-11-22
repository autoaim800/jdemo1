package com.billsoft.triptra.vthree.inserter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.ArrayOfCO_ImageType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.ArrayOfPR_PickupLocationType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_ProductClassificationType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_ProductType;

public class Product extends VThreeInserter {

    public static int insert(Connection conn, String providerId, PR_ProductType obj) {

        // if (null == param || null == param.getRow()){ return 0; }
        String cmd = "insert into t4_Product (MarketingDetails, code, name, provider_id, minimum_minimum_nights, multi_booking, maximum_capacity, Description, tax_exempt, obx_id, type, id, maximum_minimum_nights) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

            nullOrString(pstmt, 1, obj.getMarketingDetails());
            nullOrString(pstmt, 2, obj.getCode());
            nullOrString(pstmt, 3, obj.getName());
            nullOrString(pstmt, 4, providerId);
            nullOrInt(pstmt, 5, obj.getMinimum_minimum_nights());
            nullOrString(pstmt, 6, obj.getMulti_booking());
            nullOrInt(pstmt, 7, obj.getMaximum_capacity());
            nullOrString(pstmt, 8, obj.getDescription());
            nullOrString(pstmt, 9, obj.getTax_exempt());
            nullOrString(pstmt, 10, obj.getObx_id());
            nullOrString(pstmt, 11, obj.getType());
            nullOrString(pstmt, 12, obj.getId());
            nullOrInt(pstmt, 13, obj.getMaximum_minimum_nights());

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // }

        return afrc;

    }

    public static void insert(Connection conn, String providerId, String productId,
            ArrayOfCO_ImageType images) {
        // TODO Auto-generated method stub

    }

    public static void insert(Connection conn, String providerId, String productId,
            ArrayOfPR_PickupLocationType pickupLocations) {
        // TODO Auto-generated method stub

    }

    public static int insert(Connection conn, String providerId, String productId,
            PR_ProductClassificationType obj) {

        // if (null == param || null == param.getRow()){ return 0; }
        String cmd = "insert into t4_MarketingDetails (provider_id, industry_category_id, product_id, string, id) values (?, ?, ?, ?, ?)";
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
            nullOrString(pstmt, 2, obj.getIndustry_category_id());
            nullOrString(pstmt, 3, productId);
            nullOrString(pstmt, 4, obj.getString());
            nullOrString(pstmt, 5, obj.getId());

            afrc += pstmt.executeUpdate();
            pstmt.clearParameters();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // }

        return afrc;

    }

}
