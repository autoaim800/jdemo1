package com.billsoft.triptra.vthree;

import java.sql.Connection;

import com.billsoft.triptra.PageReplicator;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_StatusRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.MessageRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rs_xsd.CABS_ProviderSearch_RS;

public abstract class VThreePageReplicator extends PageReplicator {

    private String distId;

    public VThreePageReplicator(String distId, String distKey, Connection conn) {
        super(distKey, conn);
        this.distId = distId;
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
