package com.billsoft.triptra;

import org.apache.log4j.Logger;

public class Const {

    public static final String COUNTRY_AUSTRALIA = "AUSTRALIA";
    public static final String DIST_KEY = "f4d93b70-8021-400b-a238-aecfa4aa4c5f";

    public static final Logger logger = Logger.getLogger("com.billsoft.triptra");
    public static final int SQL_DUPLICATE = 1062;

    public static Logger getLogger() {
        return logger;
    }

}
