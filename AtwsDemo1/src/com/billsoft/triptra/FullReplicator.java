package com.billsoft.triptra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

import com.billsoft.triptra.handlers.GetProduct;
import com.billsoft.triptra.handlers.QueryProducts;
import com.billsoft.triptra.handlers.QueryProductsNextPage;
import com.billsoft.triptra.xsd.queryproducts.Atdw_data_results;
import com.billsoft.triptra.xsd.queryproducts.Product_distribution_type0;

/**
 * @author bill
 * 
 */
public class FullReplicator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FullReplicator fr = new FullReplicator(Const.DIST_KEY);
        fr.execute();
    }

    public static void pprint(String raw) {
        System.out.println(pretty(raw));
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

    public static String readf(String fp) {
        File file = new File(fp);
        System.out.println("reading " + file.getAbsolutePath());
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
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

    private String key;

    public FullReplicator(String distKey) {
        this.key = distKey;
    }

    private void execute() {
        // state
        // region
        // area
        // city
        // queryCities();
        queryProducts();
    }

    private void queryProduct(int prodId) {
        System.out.println("queryProduct() " + prodId);

        GetProduct gp = new GetProduct(key, String.valueOf(prodId));

        String fp = String.format("out/%s.xml", prodId);

        com.billsoft.triptra.xsd.getproduct.Atdw_data_results prodResult;
        try {
            prodResult = gp.retrieveResult();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        writef(fp, gp.getRaw());
    }

    /**
     * <pre>
     * product_record{
     *     elem=nearest_airport_iata min=0 max=1
     *     elem=temp_winter_low min=0 max=1
     *     elem=brochure_available_flag min=0 max=1
     *     elem=frequency_start_date min=0 max=1
     *     elem=temp_jul_low min=0 max=1
     *     elem=rainfall_apr min=0 max=1
     *     elem=temp_nov_low min=0 max=1
     *     elem=iata_code min=0 max=1
     *     elem=rainfall_may min=0 max=1
     *     elem=rainfall_mar min=0 max=1
     *     elem=nearest_highway min=0 max=1
     *     elem=temp_dec_high min=0 max=1
     *     elem=domestic_region_name min=0 max=1
     *     elem=attribute_id_frequency_description min=0 max=1
     *     elem=humidity_oct min=0 max=1
     *     elem=area_name min=0 max=1
     *     elem=number_of_restaurants min=0 max=1
     *     elem=temp_jun_low min=0 max=1
     *     elem=disabled_access_flag min=0 max=1
     *     elem=atdw_expiry_date min=1 max=1
     *     elem=lifts_flag min=0 max=1
     *     elem=attribute_id_frequency min=0 max=1
     *     elem=attribute_id_currency_description min=0 max=1
     *     elem=reception_hours min=0 max=1
     *     elem=service_attribute_match min=0 max=1
     *     elem=delete_indicator min=0 max=1
     *     elem=temp_jan_high min=0 max=1
     *     elem=product_name min=1 max=1
     *     elem=service_classifications min=0 max=1
     *     elem=temp_aug_low min=0 max=1
     *     elem=temp_winter_high min=0 max=1
     *     elem=humidity_jun min=0 max=1
     *     elem=humidity_jul min=0 max=1
     *     elem=rainfall_feb min=0 max=1
     *     elem=nearest_airport min=0 max=1
     *     elem=temp_mar_high min=0 max=1
     *     elem=temp_aug_high min=0 max=1
     *     elem=state_name min=0 max=1
     *     elem=country_name min=0 max=1
     *     elem=australian_business_number min=0 max=1
     *     elem=product_attribute_match min=0 max=1
     *     elem=nearest_airport_distance min=0 max=1
     *     elem=product_attributes min=0 max=1
     *     elem=rate_basis_text min=0 max=1
     *     elem=rainfall_jan min=0 max=1
     *     elem=temp_summer_low min=0 max=1
     *     elem=convention_capacity min=0 max=1
     *     elem=temp_summer_high min=0 max=1
     *     elem=temp_jul_high min=0 max=1
     *     elem=humidity_dec min=0 max=1
     *     elem=validity_date_from min=0 max=1
     *     elem=pets_allowed_flag min=0 max=1
     *     elem=children_catered_for_flag min=0 max=1
     *     elem=attribute_id_rate_basis_description min=0 max=1
     *     elem=temp_apr_high min=0 max=1
     *     elem=convention_text min=0 max=1
     *     elem=product_classifications min=0 max=1
     *     elem=temp_oct_high min=0 max=1
     *     elem=visitor_numbers min=0 max=1
     *     elem=attribute_id_atdw_status_description min=1 max=1
     *     elem=temp_mar_low min=0 max=1
     *     elem=check_out_time min=0 max=1
     *     elem=validity_date_to min=0 max=1
     *     elem=suburb_name min=0 max=1
     *     elem=product_id min=1 max=1
     *     elem=temp_apr_low min=0 max=1
     *     elem=attribute_id_event_status_description min=0 max=1
     *     elem=attribute_id_airport_distance_unit_description min=0 max=1
     *     elem=humidity_apr min=0 max=1
     *     elem=temp_feb_high min=0 max=1
     *     elem=contributing_organisation_id min=1 max=1
     *     elem=max_star_rating min=0 max=1
     *     elem=rate_to min=0 max=1
     *     elem=product_classification_match min=0 max=1
     *     elem=disabled_access_text min=0 max=1
     *     elem=free_entry_flag min=0 max=1
     *     elem=rainfall_nov min=0 max=1
     *     elem=attribute_id_nearest_gtwy_iata_description min=0 max=1
     *     elem=temp_jun_high min=0 max=1
     *     elem=altitude min=0 max=1
     *     elem=temp_may_low min=0 max=1
     *     elem=national_head_office_flag min=0 max=1
     *     elem=temp_nov_high min=0 max=1
     *     elem=humidity_jan min=0 max=1
     *     elem=humidity_summer_average min=0 max=1
     *     elem=total_criteria min=0 max=1
     *     elem=international_ready_flag min=0 max=1
     *     elem=attribute_id_gateway_distance_unit min=0 max=1
     *     elem=rate_from min=0 max=1
     *     elem=pets_allowed_text min=0 max=1
     *     elem=relevance min=0 max=1
     *     elem=dining_capacity min=0 max=1
     *     elem=temp_may_high min=0 max=1
     *     elem=attribute_id_airport_distance_unit min=0 max=1
     *     elem=nearest_gateway_distance min=0 max=1
     *     elem=humidity_aug min=0 max=1
     *     elem=service_attributes min=0 max=1
     *     elem=humidity_winter_average min=0 max=1
     *     elem=population min=0 max=1
     *     elem=bedding_configuration_text min=0 max=1
     *     elem=temp_sep_high min=0 max=1
     *     elem=frequency_end_date min=0 max=1
     *     elem=temp_feb_low min=0 max=1
     *     elem=product_description min=0 max=1
     *     elem=owning_organisation_id min=1 max=1
     *     elem=temp_jan_low min=0 max=1
     *     elem=rainfall_dec min=0 max=1
     *     elem=population_year min=0 max=1
     *     elem=rainfall_aug min=0 max=1
     *     elem=temp_sep_low min=0 max=1
     *     elem=attribute_id_nearest_gtwy_iata min=0 max=1
     *     elem=temp_oct_low min=0 max=1
     *     elem=attribute_id_currency min=0 max=1
     *     elem=attribute_id_gateway_distance_unit_description min=0 max=1
     *     elem=attribute_id_rate_basis min=0 max=1
     *     elem=check_in_time min=0 max=1
     *     elem=number_of_rooms min=0 max=1
     *     elem=rainfall_summer_average min=0 max=1
     *     elem=rainfall_oct min=0 max=1
     *     elem=humidity_feb min=0 max=1
     *     elem=article_flag min=0 max=1
     *     elem=temp_dec_low min=0 max=1
     *     elem=product_category_id min=1 max=1
     *     elem=rainfall_sep min=0 max=1
     *     elem=rainfall_jul min=0 max=1
     *     elem=humidity_mar min=0 max=1
     *     elem=rainfall_jun min=0 max=1
     *     elem=attribute_id_atdw_status min=1 max=1
     *     elem=humidity_may min=0 max=1
     *     elem=brochure_available_text min=0 max=1
     *     elem=international_region_name min=0 max=1
     *     elem=market_variant_id min=0 max=1
     *     elem=attribute_id_language_spoken_description min=0 max=1
     *     elem=city_name min=0 max=1
     *     elem=children_catered_for_text min=0 max=1
     *     elem=nearest_gateway min=0 max=1
     *     elem=percent_relevance min=0 max=1
     *     elem=rainfall_winter_average min=0 max=1
     *     elem=attribute_id_language_spoken min=0 max=1
     *     elem=total_capacity min=0 max=1
     *     elem=number_of_floors min=0 max=1
     *     elem=humidity_nov min=0 max=1
     *     elem=attribute_id_event_status min=0 max=1
     *     elem=service_classification_match min=0 max=1
     *     elem=humidity_sep min=0 max=1
     * }
     * </pre>
     * 
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
        QueryProducts qp = new QueryProducts(key, "ALL");
        int perPage = 50;
        int pageNum = 1;
        qp.setResultsPerPage(String.valueOf(perPage));
        try {
            Atdw_data_results result = qp.retrieveResult();

            int total = result.getTotal_records_found();
            int sum = result.getRecord_count_this_buffer();
            String queryId = result.getApi_query_id();

            writef("out/page" + pageNum + ".xml", qp.getRaw());
            dealOnePageOfProducts(result, pageNum);

            if (sum < total) {
                // need more page
                queryProductsNext(queryId, perPage, total);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void dealOnePageOfProducts(Atdw_data_results result, int pageNum) {
        Product_distribution_type0[] dists = result.getProduct_distribution();

        for (Product_distribution_type0 dist : dists) {
            int prodId = dist.getProduct_id();
            queryProduct(prodId);
        }
        // out of for echo product
    }

    private void queryProductsNext(String queryId, int perPage, int totalRecord) {
        int pageNum = 2;
        int sum = perPage;

        try {
            do {
                QueryProductsNextPage qpn = new QueryProductsNextPage(key, queryId,
                        String.valueOf(pageNum));
                qpn.setResultsPerPage(String.valueOf(perPage));

                Atdw_data_results result = null;
                try {
                    result = qpn.retrieveResult();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result = null;
                }
                writef("out/page" + pageNum + ".xml", qpn.getRaw());

                sum += result.getRecord_count_this_buffer();

                dealOnePageOfProducts(result, pageNum);

                System.out.println("queryProductsNextPage() " + pageNum + " done");

                pageNum++;

            } while (sum < totalRecord);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
}
