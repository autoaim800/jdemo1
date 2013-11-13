package com.billsoft.triptra.inserter.getproduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.billsoft.triptra.inserter.DbInserter;
import com.billsoft.triptra.xsd.getproduct.Product_contact_address_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_contact_attribute_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_contact_comment_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_contact_communication_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Product_contact_multimedia_relationship_type0;
import com.billsoft.triptra.xsd.getproduct.Row_type27;
import com.billsoft.triptra.xsd.getproduct.Row_type29;

public class Contact extends DbInserter {

    public static int insert(Connection conn, Product_contact_address_relationship_type0[] cadr) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static int insert(Connection conn, Product_contact_attribute_relationship_type0[] catr) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static int insert(Connection conn, Product_contact_comment_relationship_type0[] ccer)
             {
        // TODO Auto-generated method stub
        return -1;
    }

    public static int insert(Connection conn,
            Product_contact_communication_relationship_type0[] ccur) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static int insert(Connection conn, Product_contact_multimedia_relationship_type0[] cmur) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static int insert(Connection conn, int productId, Row_type29 obj) {
        String cmd = "insert into t_product_contact (attribute_id_gender_description_mv, attribute_id_title_description, attribute_id_gender, surname, product_id, attribute_id_salutation_description_mv, position, attribute_id_title_description_mv, attribute_id_salutation, attribute_id_salutation_description, attribute_id_title, contact_text, date_of_birth, company_name, first_name, company_department, attribute_id_gender_description, initials) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            nullOrString(pstmt, 1, obj.getAttribute_id_gender_description_mv());
            nullOrString(pstmt, 2, obj.getAttribute_id_title_description());
            nullOrString(pstmt, 3, obj.getAttribute_id_gender());
            nullOrString(pstmt, 4, obj.getSurname());
            nullOrInt(pstmt, 5, productId);
            nullOrString(pstmt, 6, obj.getAttribute_id_salutation_description_mv());
            nullOrString(pstmt, 7, obj.getPosition());
            nullOrString(pstmt, 8, obj.getAttribute_id_title_description_mv());
            nullOrString(pstmt, 9, obj.getAttribute_id_salutation());
            nullOrString(pstmt, 10, obj.getAttribute_id_salutation_description());
            nullOrString(pstmt, 11, obj.getAttribute_id_title());
            nullOrString(pstmt, 12, obj.getContact_text());
            nullOrDate(pstmt, 13, obj.getDate_of_birth());
            nullOrString(pstmt, 14, obj.getCompany_name());
            nullOrString(pstmt, 15, obj.getFirst_name());
            nullOrString(pstmt, 16, obj.getCompany_department());
            nullOrString(pstmt, 17, obj.getAttribute_id_gender_description());
            nullOrString(pstmt, 18, obj.getInitials());

            afrc = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return afrc;

    }

}
