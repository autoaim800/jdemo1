package com.billsoft.triptra;

import java.io.File;
import java.sql.Connection;

import com.billsoft.triptra.handlers.GetCountryStateArea;
import com.billsoft.triptra.handlers.GetDomesticRegionArea;
import com.billsoft.triptra.xsd.getcountrystatearea.Area_type0;
import com.billsoft.triptra.xsd.getcountrystatearea.Atdw_data_results;
import com.billsoft.triptra.xsd.getdomesticregionarea.Domestic_region_type0;

public class LocationReplicator extends PageReplicator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Connection cnn = DbHelper.obtainConnection();
        LocationReplicator lp = new LocationReplicator(Const.DIST_KEY, cnn, Const.COUNTRY_AUSTRALIA);
        lp.replicate();

    }

    private String country;

    public LocationReplicator(String distKey, Connection connection, String country) {
        super(distKey, connection);
        this.country = country;
    }

    private void procStateArea() {
        GetCountryStateArea sh = new GetCountryStateArea(Const.DIST_KEY, country);
        String fp = String.format("out/state_%s.xml", country);
        boolean needWriteFile = true;
        if ((new File(fp)).exists()) {
            sh.setRaw(readf(fp));
            needWriteFile = false;
        }
        try {
            Atdw_data_results result = sh.retrieveResult();
            if (null == result || null == result.getCountry()) {
                Const.logger.warn("null result for GetCountryStateArea()");
                return;
            }
            for (Area_type0 area : result.getCountry().getArea()) {
                String msg = String.format("state: %s/%s/%s area: %s/%s/%s ", area.getState_code(),
                        area.getState_name(), area.getState_product_id(), area.getArea_code(),
                        area.getArea_name(), area.getArea_type());

                Const.logger.info(msg);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (needWriteFile) {
            writef(fp, pretty(sh.getRaw()));
        }

    }

    @Override
    public boolean replicate() {
        // state
        procStateArea();

        // region
        // procRegionArea();

        // area
        // city/town
        // suburb
        return false;
    }

    private void procRegionArea() {

        GetDomesticRegionArea rh = new GetDomesticRegionArea(this.distKey);
        String fp = String.format("out/region_%s.xml", country);
        boolean needWriteFile = true;
        if ((new File(fp)).exists()) {
            rh.setRaw(readf(fp));
            needWriteFile = false;
        }
        try {
            com.billsoft.triptra.xsd.getdomesticregionarea.Atdw_data_results result = rh
                    .retrieveResult();
            if (null == result || null == result.getDomestic_region()) {
                return;
            } else {
                for (Domestic_region_type0 region : result.getDomestic_region()) {

                    String msg = String.format("+state: %s/%s/%s",
                            region.getDomestic_region_code(), region.getDomestic_region_name(),
                            region.getDomestic_region_id());

                    Const.logger.info(msg);

                    if (null == region.getArea()) {
                        continue;
                    }

                    for (com.billsoft.triptra.xsd.getdomesticregionarea.Area_type0 area : region
                            .getArea()) {

                        msg = String.format("-area: %s/%s/%s state: %s/%s/%s", area.getArea_code(),
                                area.getArea_name(), area.getArea_type(), area.getState_code(),
                                area.getState_name(), area.getState_product_id());
                        Const.logger.info(msg);

                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (needWriteFile) {
            writef(fp, pretty(rh.getRaw()));
        }
    }
}
