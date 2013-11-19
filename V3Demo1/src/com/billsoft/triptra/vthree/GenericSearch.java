package com.billsoft.triptra.vthree;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.CO_StatusRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_common_xsd.MessageRSType;
import com.v3leisure.www.schemas.cabs._1_0.cabs_providersearch_rs_xsd.CABS_ProviderSearch_RS;

public class GenericSearch {

    public static String pretty(String raw) {
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
            return xmlOutput.getWriter().toString();
        } catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
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
