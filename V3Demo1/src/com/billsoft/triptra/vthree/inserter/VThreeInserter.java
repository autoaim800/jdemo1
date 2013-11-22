package com.billsoft.triptra.vthree.inserter;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import com.billsoft.triptra.inserter.DbInserter;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_BookabilityEnumType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_IntegrationModeEnumType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_ProviderEnumType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_StatusEnumType;

public class VThreeInserter extends DbInserter {

    public static void nullOrDate(PreparedStatement pstmt, int i, java.util.Date date) throws SQLException {
        if (null == date) {
            pstmt.setNull(i, java.sql.Types.DATE);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            pstmt.setString(i, sdf.format(date));
        }
    }

    public static void nullOrString(PreparedStatement pstmt, int i, Object object) throws SQLException {
        if (null == object) {
            pstmt.setNull(i, java.sql.Types.VARCHAR);
        } else {
            pstmt.setString(i, object.toString());
        }
    }

    public static void nullOrString(PreparedStatement pstmt, int i, String object) throws SQLException {
        if (null == object) {
            pstmt.setNull(i, java.sql.Types.VARCHAR);
        } else {
            pstmt.setString(i, object);
        }
    }

    public static void nullOrInt(PreparedStatement pstmt, int i, BigDecimal dec) throws SQLException {
        if (null == dec) {
            pstmt.setNull(i, Types.INTEGER);
        } else {
            pstmt.setInt(i, dec.intValue());
        }
    }

    public static void nullOrString(PreparedStatement pstmt, int i, boolean b) throws SQLException {
        pstmt.setString(i, String.valueOf(b));
    }

    public static void nullOrString(PreparedStatement pstmt, int i, CO_BookabilityEnumType obj) throws SQLException {
        nullOrString(pstmt, i, obj.getValue());
    }

    public static void nullOrString(PreparedStatement pstmt, int i, CO_IntegrationModeEnumType type) throws SQLException {
        nullOrString(pstmt, i, type.getValue());
    
    }

    public static void nullOrString(PreparedStatement pstmt, int i, CO_ProviderEnumType obj) throws SQLException {
        nullOrString(pstmt, i, obj.getValue());
    
    }

    public static void nullOrString(PreparedStatement pstmt, int i, CO_StatusEnumType status) throws SQLException {
        nullOrString(pstmt, i, status.getValue());
    }

}
