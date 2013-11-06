package com.billsoft.triptra.handlers;

import java.io.Reader;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axis2.AxisFault;
import org.tempuri.soap.australiantourismwebservice.AustralianTourismWebServiceStub;
import org.tempuri.soap.australiantourismwebservice.CommandHandler;
import org.tempuri.soap.australiantourismwebservice.CommandHandlerResponse;

public class MyCommandHandler {
    protected AustralianTourismWebServiceStub atws;
    protected String raw = null;
    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }

    private Map<String, String> dish = new HashMap<String, String>();
    private String distKey;

    private String name;

    private XMLInputFactory xfactory;

    public MyCommandHandler(String distKey, String name) {
        this.distKey = distKey;
        this.name = name;
        try {
            atws = new AustralianTourismWebServiceStub();
        } catch (AxisFault e) {
            // TODO do not know what to do at this moment
            e.printStackTrace();
        }

    }

    protected void append(String key, String val) {
        dish.put(key, val);
    }

    /**
     * build XmlStreamReader from String
     */
    protected XMLStreamReader buildXmlStreamReader(String rawResult) throws XMLStreamException {
        Reader reader = new StringReader(rawResult);
        if (null == xfactory) {
            xfactory = XMLInputFactory.newInstance();
        }
        return xfactory.createXMLStreamReader(reader);
    }

    public String getDistKey() {
        return distKey;
    }

    public String getName() {
        return name;
    }

    /**
     * <pre>
     * <parameters>
     * <row><param>PRODUCT_CATEGORY_LIST</param><value>ACCOMM</value></row>
     * <row><param>PRODUCT_NAME</param><value>motor inn</value></row>
     * <row><param>STATE</param><value>NORTHERN TERRITORY</value></row>
     * <row><param>RESULTS_PER_PAGE</param><value>100</value></row>
     * </parameters>
     * </pre>
     * 
     * @return
     */
    public String getParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("<parameters>");
        for (String key : dish.keySet()) {
            String val = dish.get(key);
            sb.append(String.format("<row><param>%s</param><value>%s</value></row>", key, val));
        }
        sb.append("</parameters>");
        return sb.toString();
    }

    /**
     * retrieve string-result via http soap
     */
    protected String retrieveRawResult() throws RemoteException {

        CommandHandler ch = new CommandHandler();
        ch.setDistributorKey(getDistKey());
        ch.setCommandName(getName());
        ch.setCommandParameters(getParams());

        CommandHandlerResponse resp = atws.commandHandler(ch);
        return resp.getCommandHandlerResult();
    }

    protected void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyCommandHandler name=" + name + ", param=" + getParams();
    }

}
