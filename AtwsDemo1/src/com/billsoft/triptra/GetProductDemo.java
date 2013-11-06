package com.billsoft.triptra;

import com.billsoft.triptra.handlers.GetProduct;
import com.billsoft.triptra.xsd.getproduct.Atdw_data_results;

public class GetProductDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        GetProduct gp = new GetProduct(Const.DIST_KEY, "9131945");
        String raw = FullReplicator.readf("out/9131945.xml");
        gp.setRaw(raw);
        try {
            Atdw_data_results result = gp.retrieveResult();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
