package com.billsoft.triptra.handlers;

import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamReader;

import org.apache.axis2.AxisFault;
import org.tempuri.soap.australiantourismwebservice.AustralianTourismWebService;
import org.tempuri.soap.australiantourismwebservice.AustralianTourismWebServiceStub;
import org.tempuri.soap.australiantourismwebservice.CommandHandlerResponse;

public class MyCommandHandler {
    private String distKey;
    protected AustralianTourismWebServiceStub atws;

    public String getDistKey() {
        return distKey;
    }

    public MyCommandHandler(String distKey, String name) {
        this.distKey = distKey;
        this.name = name;
        try {
            atws = new AustralianTourismWebServiceStub();
        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected XMLStreamReader buildXmlStreamReader(CommandHandlerResponse resp) {
        // TODO Auto-generated method stub
        return null;
    }

    public MyCommandHandler() {
    }

    private Map<String, String> dish = new HashMap<String, String>();
    private String name;

    protected void append(String key, String val) {
        dish.put(key, val);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyCommandHandler name=" + name + ", param=" + getParams();
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

    protected void setName(String name) {
        this.name = name;
    }

}
