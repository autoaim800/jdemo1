package com.billsoft.triptra.vthree;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.axis2.AxisFault;

import com.billsoft.triptra.DbHelper;
import com.billsoft.triptra.vthree.client.SearchService;
import com.billsoft.triptra.vthree.client.SearchServiceStub;
import com.billsoft.triptra.vthree.inserter.Product;
import com.billsoft.triptra.vthree.inserter.Provider;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_DistributionChannelRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.PR_ProductType;
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

public class ProviderReplicator extends VThreePageReplicator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Connection conn = DbHelper.obtainConnection();
        ProviderReplicator ps = new ProviderReplicator(Const.DIST_ID, Const.DIST_KEY, conn);
        ps.replicate();
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ProviderReplicator(String distId, String distKey, Connection conn) {
        super(distId, distKey, conn);
    }

    private void procRs(CABS_ProviderSearch_RS rs) {
        if (null == rs.getChannels() || null == rs.getChannels().getChannel()) {
            Const.logger.error("null channel");
            return;
        }

        for (PS_DistributionChannelType channel : rs.getChannels().getChannel()) {
            if (null == channel.getProviders() || null == channel.getProviders().getProvider()) {
                Const.logger.error("null provider");
                continue;
            }
            for (PR_ProviderLongRSType provider : channel.getProviders().getProvider()) {

                Const.logger.info(provider.getShort_name() + " " + provider.getContent_id());

                Provider.insert(conn, provider);
                
                String providerId = provider.getId();
                Provider.insert(conn, providerId, provider.getBusinessDetails());
                Provider.insert(conn, providerId, provider.getBookingDetails());
                Provider.insert(conn, providerId, provider.getRegionGeocodeDetails());
                Provider.insert(conn, providerId, provider.getContactDetails());

                if (null != provider.getProducts() && null != provider.getProducts().getProduct()) {
                    for (PR_ProductType product : provider.getProducts().getProduct()) {
                        Product.insert(conn, providerId, product);

                        String productId = product.getId();
                        Product.insert(conn, providerId, productId, product.getImages());
                        Product.insert(conn, providerId, productId, product.getPickupLocations());
                        Product.insert(conn, providerId, productId, product.getMarketingDetails());
                    }
                }
                Provider.insert(conn, providerId, provider.getECommerceDetails());
                Provider.insert(conn, providerId, provider.getMerchantDetails());

            }
            // out of for-loop
        }
    }

    @Override
    public boolean replicate() {
        try {
            SearchService ss = new SearchServiceStub();

            PS_ResponseInclusionRQType yesPsResponseInclusionRq = new PS_ResponseInclusionRQType();
            yesPsResponseInclusionRq.setInclude(true);

            PS_ResponseInclusionRQType yesBookingDetails = new PS_ResponseInclusionRQType();
            yesBookingDetails.setInclude(true);

            PS_ResponseInclusionRQType yesMarketDetails;

            SC_BooleanRQType yesScBooleanRq = new SC_BooleanRQType();
            yesScBooleanRq.setValue(true);

            CO_DistributionChannelRQType channelType1 = new CO_DistributionChannelRQType();
            channelType1.setId(Const.DIST_ID);
            channelType1.setKey(Const.DIST_KEY);

            PS_QueryRQType query = new PS_QueryRQType();
            query.setSearchCriteriaIncludeTestProviders(yesScBooleanRq);

            ArrayOfCO_DistributionChannelRQType channels = new ArrayOfCO_DistributionChannelRQType();
            channels.setCO_DistributionChannelRQType(new CO_DistributionChannelRQType[] { channelType1 });

            PS_ResponseInclusionsRQType response = null;
            try {
                response = PS_ResponseInclusionsRQType.Factory
                        .parse(buildXmlStreamReader(readf("response_full.xml")));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // response.setIncludeContactDetails(yesPsResponseInclusionRq);
            // response.setIncludeBookingDetails(yesBookingDetails);

            CABS_ProviderSearch_RQChoice_type0 type1 = new CABS_ProviderSearch_RQChoice_type0();
            type1.setQuery(query);
            CABS_ProviderSearch_RQChoice_type0 type2 = new CABS_ProviderSearch_RQChoice_type0();
            type2.setChannels(channels);
            CABS_ProviderSearch_RQChoice_type0 type3 = new CABS_ProviderSearch_RQChoice_type0();
            type3.setResponse(response);

            // each type contains one sub-node
            CABS_ProviderSearch_RQ rq = new CABS_ProviderSearch_RQ();
            rq.setCABS_ProviderSearch_RQChoice_type0(new CABS_ProviderSearch_RQChoice_type0[] {
                    type1, type2, type3 });

            CABS_ProviderSearch_RQE rqe = new CABS_ProviderSearch_RQE();
            rqe.setCABS_ProviderSearch_RQ(rq);

            CABS_ProviderSearch_RSE rse = ss.providerSearch(rqe);

            CABS_ProviderSearch_RS rs = rse.getCABS_ProviderSearch_RS();
            if (null == rs) {
                Const.logger.error("null rs");
                return false;
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
        return false;
    }
}
