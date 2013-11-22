package com.billsoft.triptra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;

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

public abstract class PageReplicator {
    private XMLInputFactory xfactory;

    protected XMLStreamReader buildXmlStreamReader(String rawStr) throws XMLStreamException {
        Reader reader = new StringReader(rawStr);
        if (null == xfactory) {
            xfactory = XMLInputFactory.newInstance();
        }
        return xfactory.createXMLStreamReader(reader);
    }

    public static void pprint(String raw) {
        System.out.println(pretty(raw));
    }

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

    public static String readf(String fp) {
        File file = new File(fp);

        Const.getLogger().info("reading " + file.getAbsolutePath());

        FileReader fr = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    // ignored
                }
            }
            if (null != fr) {
                try {
                    fr.close();
                } catch (IOException e) {
                    // ignored
                }
            }
        }
        return sb.toString();
    }

    public static void writef(String fp, String content) {
        File file = new File(fp);
        FileWriter fw = null;
        // BufferedWriter fw = null;
        try {
            fw = new FileWriter(file);
            // fw = new BufferedWriter(new OutputStreamWriter(new
            // FileOutputStream(fp), "UTF16"));
            fw.write(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    // ignored
                }
            }
        }
    }

    protected Connection conn;

    protected String distKey;

    public PageReplicator(String distKey, Connection connection) {
        this.distKey = distKey;
        conn = connection;
    }

    public abstract boolean replicate();

}