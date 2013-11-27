package com.billsoft.triptra.vthree;

import java.sql.Connection;

import org.apache.axis2.AxisFault;

import com.billsoft.triptra.PageReplicator;
import com.billsoft.triptra.vthree.client.SearchServiceStub;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_DistributionChannelRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_StatusRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.MessageRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rs_xsd.CABS_ProviderSearch_RS;

public abstract class VThreeReplicator extends PageReplicator {

    private String distId;
    protected SearchServiceStub ss;
    protected CO_DistributionChannelRQType channelType1;
    
    public VThreeReplicator(String distId, String distKey, Connection conn) {
        super(distKey, conn);
        this.distId = distId;

        try {
            ss = new SearchServiceStub();
        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        channelType1 = new CO_DistributionChannelRQType();
        channelType1.setId(Const.DIST_ID);
        channelType1.setKey(Const.DIST_KEY);
    }

    /**
     * returns true only when there is no error
     */
    protected boolean checkStatus(CABS_ProviderSearch_RS rs) {
        CO_StatusRSType status = rs.getStatus();
        if (null == status || null == status.getErrors() || null == status.getErrors().getError()) {
            return true;
        } else {
            for (MessageRSType err : status.getErrors().getError()) {
                Const.logger.error(String.format("%s | %s", err.getCode(), err.getMessage()));
            }
        }
        return false;
    }
}
