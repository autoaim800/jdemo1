package com.billsoft.triptra.vthree;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.billsoft.triptra.vthree.client.SearchService;
import com.billsoft.triptra.vthree.client.SearchServiceStub;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_DistributionChannelRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_StatusRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.MessageRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_ProviderLongRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PS_DistributionChannelType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PS_QueryRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PS_ResponseInclusionRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PS_ResponseInclusionsRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.SC_BooleanRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rq_xsd.ArrayOfCO_DistributionChannelRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rq_xsd.CABS_ProviderSearch_RQ;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rq_xsd.CABS_ProviderSearch_RQChoice_type0;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rq_xsd.CABS_ProviderSearch_RQE;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rs_xsd.CABS_ProviderSearch_RS;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rs_xsd.CABS_ProviderSearch_RSE;

public class ProviderSearch extends GenericSearch {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ProviderSearch ps = new ProviderSearch();
        ps.search();
    }

    private void search() {
        try {
            SearchService ss = new SearchServiceStub();

            PS_ResponseInclusionRQType yesPsResponseInclusionRq = new PS_ResponseInclusionRQType();
            yesPsResponseInclusionRq.setInclude(true);

            SC_BooleanRQType yesScBooleanRq = (new SC_BooleanRQType());
            yesScBooleanRq.setValue(true);

            CO_DistributionChannelRQType channelType1 = new CO_DistributionChannelRQType();
            channelType1.setId(Const.DIST_ID);
            channelType1.setKey(Const.DIST_KEY);

            PS_QueryRQType query = new PS_QueryRQType();
            query.setSearchCriteriaIncludeTestProviders(yesScBooleanRq);

            ArrayOfCO_DistributionChannelRQType channels = new ArrayOfCO_DistributionChannelRQType();
            channels.setCO_DistributionChannelRQType(new CO_DistributionChannelRQType[] { channelType1 });

            PS_ResponseInclusionsRQType response = new PS_ResponseInclusionsRQType();
            response.setIncludeContactDetails(yesPsResponseInclusionRq);

            CABS_ProviderSearch_RQChoice_type0 type1 = new CABS_ProviderSearch_RQChoice_type0();
            type1.setQuery(query);
            CABS_ProviderSearch_RQChoice_type0 type2 = new CABS_ProviderSearch_RQChoice_type0();
            type2.setChannels(channels);
            CABS_ProviderSearch_RQChoice_type0 type3 = new CABS_ProviderSearch_RQChoice_type0();
            type3.setResponse(response);

            CABS_ProviderSearch_RQ rq = new CABS_ProviderSearch_RQ();
            rq.setCABS_ProviderSearch_RQChoice_type0(new CABS_ProviderSearch_RQChoice_type0[] {
                    type1, type2, type3 });

            CABS_ProviderSearch_RQE rqe = new CABS_ProviderSearch_RQE();
            rqe.setCABS_ProviderSearch_RQ(rq);

            CABS_ProviderSearch_RSE rse = ss.providerSearch(rqe);

            CABS_ProviderSearch_RS rs = rse.getCABS_ProviderSearch_RS();
            if (null == rs) {
                Const.logger.error("null rs");
                return;
            }

            if (checkStatus(rs)) {
                procRs(rs);
            }

        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void procRs(CABS_ProviderSearch_RS rs) {
        if (null == rs.getChannels() || null == rs.getChannels().getChannel()) {
            Const.logger.error("null channel");
            return;
        }
        for (PS_DistributionChannelType channel : rs.getChannels().getChannel()) {
            if (null == channel.getProviders() || null == channel.getProviders().getProvider()){
                Const.logger.error("null provider");
                continue;
            }
            for (PR_ProviderLongRSType provider : channel.getProviders().getProvider()) {
                Const.logger.info(provider.getShort_name() + " " + provider.getContent_id());
            }
        }

    }

}
