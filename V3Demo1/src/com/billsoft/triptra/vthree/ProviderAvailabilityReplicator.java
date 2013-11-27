package com.billsoft.triptra.vthree;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import com.billsoft.triptra.DbHelper;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.AV_QueryRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.ArrayOfCO_DistributionChannelRQType1;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.ArrayOfCO_ProviderShortType1;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.ArrayOfSC_ConsumerType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_DistributionChannelRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_IndustryCategoryEnumType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_IndustryCategoryGroupEnumType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_ProviderShortType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.SC_AvailabilityRQType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.SC_ConsumerType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.SC_DateSpecificType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.SC_LengthDaysType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.SC_LengthNightsType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_productavailability_rq_xsd.CABS_ProductAvailability_RQ;
import com.v3leisure.www.schemas.cabs._1_0.cabs_productavailability_rq_xsd.CABS_ProductAvailability_RQE;
import com.v3leisure.www.schemas.cabs._1_0.cabs_productavailability_rs_xsd.CABS_ProductAvailability_RSE;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rq_xsd.ArrayOfCO_DistributionChannelRQType;

public class ProviderAvailabilityReplicator extends VThreeReplicator {

    public static void main(String[] args) {

        Connection conn = DbHelper.obtainConnection();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        ProviderAvailabilityReplicator pap = new ProviderAvailabilityReplicator(Const.DIST_ID,
                Const.DIST_KEY, conn, "0770292f-c22b-4233-a417-26b55d5a80c2");

        pap.replicate();

        try {
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private ArrayOfCO_DistributionChannelRQType1 channels;

    public ProviderAvailabilityReplicator(String distId, String distKey, Connection conn,
            String providerId) {
        super(distId, distKey, conn);

        channels = new ArrayOfCO_DistributionChannelRQType1();
        channels.setDistributionChannelRQ(new CO_DistributionChannelRQType[] { channelType1 });

    }

    @Override
    public boolean replicate() {
        String providerShortName = "amyshouse";

        CO_ProviderShortType provider1 = new CO_ProviderShortType();
        provider1.setShort_name(providerShortName);

        ArrayOfCO_ProviderShortType1 providers = new ArrayOfCO_ProviderShortType1();
        providers.addProviderRQ(provider1);

        CO_IndustryCategoryEnumType category = CO_IndustryCategoryEnumType.value1;
        CO_IndustryCategoryGroupEnumType categoryGroup = CO_IndustryCategoryGroupEnumType.Accommodation;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(calendar.DATE, 1);
        Date tomorrow = calendar.getTime();

        SC_DateSpecificType specDate = new SC_DateSpecificType();
        specDate.setDate(tomorrow);

        SC_LengthDaysType length = new SC_LengthDaysType();
        length.setDays(5);

        SC_LengthNightsType nights = new SC_LengthNightsType();
        nights.setMinimum(3);
        nights.setMaximum(5);

        SC_ConsumerType consumer = new SC_ConsumerType();
        consumer.setAdults(1);
        consumer.setChildren(1);

        ArrayOfSC_ConsumerType consumers = new ArrayOfSC_ConsumerType();
        consumers.setConsumer(new SC_ConsumerType[] { consumer });

        SC_AvailabilityRQType criteria = new SC_AvailabilityRQType();
        criteria.setCommencingSpecific(specDate);
        // criteria.setLengthDays(length);
        criteria.setLengthNights(nights);

        criteria.setConsumers(consumers);

        AV_QueryRQType query = new AV_QueryRQType();
        query.setIndustryCategory(category);
        query.setIndustryCategoryGroup(categoryGroup);
        query.setSearchCriteria(criteria);

        CABS_ProductAvailability_RQ rq = new CABS_ProductAvailability_RQ();
        rq.setChannels(channels);
        rq.setProviders(providers);
        rq.setQuery(query);

        if (!rq.isChannelsSpecified()) {
            Const.logger.error("no channel");
        }

        CABS_ProductAvailability_RQE rqe = new CABS_ProductAvailability_RQE();
        rqe.setCABS_ProductAvailability_RQ(rq);

        try {
            CABS_ProductAvailability_RSE rse = ss.productAvailability(rqe);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

}
