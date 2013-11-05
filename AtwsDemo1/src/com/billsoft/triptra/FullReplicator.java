package com.billsoft.triptra;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.rmi.RemoteException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.axis2.AxisFault;
import org.tempuri.soap.australiantourismwebservice.AustralianTourismWebService;
import org.tempuri.soap.australiantourismwebservice.AustralianTourismWebServiceStub;
import org.tempuri.soap.australiantourismwebservice.CommandHandler;
import org.tempuri.soap.australiantourismwebservice.CommandHandlerResponse;

import com.billsoft.triptra.handlers.GetCountryStateArea;
import com.billsoft.triptra.handlers.QueryProducts;
import com.billsoft.triptra.xsd.queryproducts.Atdw_data_results;
import com.billsoft.triptra.xsd.queryproducts.Product_distribution_type0;

/**
 * @author bill
 * 
 */
public class FullReplicator {

    public static void pprint(String raw) {
        // Instantiate transformer input
        Source xmlInput = new StreamSource(new StringReader(raw));
        StreamResult xmlOutput = new StreamResult(new StringWriter());

        // Configure transformer
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);

            System.out.println(xmlOutput.getWriter().toString());

        } catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        FullReplicator fr = new FullReplicator(Const.DIST_KEY);
        fr.execute();
    }

    private String key;

    public FullReplicator(String distKey) {
        this.key = distKey;
    }

    private void execute() {
        // state
        // region
        // area
        // city
        queryProducts();
    }

    /**
     * <pre>
     * product_distribution{
     *     attr=product_id type=xs:int use=required
     *     elem=product_article min=0 max=1
     *     elem=product_multimedia min=0 max=1
     *     elem=event_frequency min=0 max=1
     *     elem=product_record min=1 max=1
     *     elem=product_address min=0 max=1
     *     elem=product_external_system min=0 max=1
     *     elem=product_comment min=0 max=1
     *     elem=product_vertical_classification min=0 max=1
     *     elem=product_service min=0 max=1
     * }
     * </pre>
     */
    private void queryProducts() {
        QueryProducts queryProducts = new QueryProducts(key, "ALL");
        try {
            Atdw_data_results result = queryProducts.retrieveResult();
            Product_distribution_type0[] dists = result.getProduct_distribution();
            for (Product_distribution_type0 dist : dists) {

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
