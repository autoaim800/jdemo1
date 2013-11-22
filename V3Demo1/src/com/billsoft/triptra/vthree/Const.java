package com.billsoft.triptra.vthree;

import org.apache.log4j.Logger;

public class Const {

    public static final String DIST_ID = "Test_V3_Offload";
    public static final String DIST_KEY = "60E0533B-6E38-4779-A7D7-0D82BF824C55";
    public static final String CAT_ACCOMM = "Accommodation" ;
    
    public static final Logger logger = Logger.getLogger("com.billsoft.triptra");
    public static final int SQL_DUPLICATE = 1062;
    
    public static Logger getLogger() {
        return logger;
    }

}
