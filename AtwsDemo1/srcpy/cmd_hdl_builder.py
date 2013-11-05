'''
Created on 04/11/2013

@author: bill

generate source code for handlers

'''
QUERY_PRODUCTS_OPTS = """ADDRESS_RETURN
            AREA
            ARTICLE CUSTOMER REACH
            COMMENT_TYPE
            COUNTRY
            DELTA_DATE
            DESC_NAME_SEARCH_OPERATOR
            DISABLED_ACCESS_FILTER
            DOMESTIC
            EVENT_STATUS
            EXTERNAL_SYSTEM_CODE
            EXTERNAL_SYSTEM_RETURN
            FREQUENCY_DATE_END
            FREQUENCY_DATE_START
            FULL_SERVICE_MATCH
            KEYWORD_DESC_SEARCH
            KEYWORD_NAME_SEARCH
            LOCATION_MATCH_TYPE
            MARKET_VARIANT
            MARKET_VARIANT_RETURN
            MARKETING_PROGRAM
            MARKETING_PROGRAM_ONLY
            MAX_STAR_RATING
            MULTIMEDIA_RETURN
            MULTIMEDIA_TYPE
            ORGANISATION_CODE
            PETS_ALLOWED_FILTER
            PRD_SVC_ATTRIBUTE_OPERATOR
            PRD_VERTICAL_CLASS_RETURN
            PRODUCT_ATTRIBUTE
            PRODUCT_ARTICLE_RETURN
            PRODUCT_CLASSIFICATION
            PRODUCT_DESC
            PRODUCT_NAME
            RATE_FROM
            RATE_RANGE
            RATE_TO
            RESTRICT_MARKET_VARIANT
            RESULT_ORDER
            RESULTS_PER_PAGE
            SERVICE_ATTRIBUTE
            SERVICE_CLASSIFICATION
            SERVICE_OVERVIEW_RETURN
            SERVICE_RATE_FROM
            SERVICE_RATE_PERIOD
            SERVICE_RATE_RETURN
            SERVICE_RATE_TO
            SERVICE_RATE_UNIT
            START_LOCATION
            STATE
            SUBURB_OR_CITY
            TARIFF_CODE
            VERTICAL_CLASS_LEVEL"""
            
matrix = {"QueryProducts":("PRODUCT_CATEGORY_LIST", QUERY_PRODUCTS_OPTS),
          "QueryProductsNextPage":("API_QUERY_ID PAGE_NUMBER", "RESULTS_PER_PAGE"),
          "GetProduct":("PRODUCT_ID", """MARKET_VARIANT
                DATA_GROUP_LIST
                SERVICE_TARIFF_CODE
                ARTICLE CUSTOMER REACH"""),
          "GetProductService":("PRODUCT_ID SERVICE_ID", "MARKET_VARIANT DATA_GROUP_LIST"),
          "GetCountryStateArea":("COUNTRY_NAME", "STATE_NAME AREA_TYPE"),
          "GetDomesticRegionArea":("", """DOMESTIC_REGION_CODE
                DOMESTIC_REGION_NAME
                ORGANISATION_CODE
                DOMESTIC_REGION_TYPE
                STATE
                AREA_TYPE"""),
          "GetCities":("COUNTRY AREA", "STATE AREA_MATCH_TYPE"),
          "GetSuburbs":("COUNTRY AREA CITY", "STATE AREA_MATCH_TYPE CITY_MATCH_TYPE"),
          "GetAttributesInLocation":("PRD_SVC_LEVEL", """INTERNATIONAL
            ATTRIBUTE_TYPE_LIST
            DOMESTIC
            COUNTRY
            STATE
            AREA
            SUBURB_OR_CITY"""),
          "GetAttributeDefinition":("PRODUCT_CATEGORY", "ATTRIBUTE_TYPE_LIST MARKET_VARIANT")          
          }


class SourceBuilder:
    
    def _clazzName(self, name):
        subs = name.split("_")
        if len(subs) > 0:
            sb = ""
            for sub in subs:
                sb += sub.capitalize()
            return sb
        else:
            return name.capitalize()
    
    def _varName(self, name):
        subs = name.split("_")
        if len(subs) > 0:
            sb = subs[0].lower()
            for sub in subs[1:]:
                sb += sub.capitalize()
            return sb
        else:
            return name.capitalize()

class ConstructorBuilder(SourceBuilder):
    
    TPL = """ public %s(String distKey%s){%s%s}\n"""
    
    def __init__(self, name, mans):
        self.name = name
        self.mans = mans

    def toJava(self):
        params = ""
        assigns = ""
        for vname in self.mans:
            varName = self._varName(vname)
            params += ", String _%s" % (varName)
            assigns += "append(\"%s\", _%s);\n" % (vname, varName)        
        return self.TPL % (self.name, params, "super(distKey, \"%s\");" % (self.name), assigns)


class SetterBuilder(SourceBuilder):
    TPL = """    private String %(VAR_NAME)s;\n
    public void set%(C_NAME)s(String _%(VAR_NAME)s){
        this.%(VAR_NAME)s = _%(VAR_NAME)s;
        super.append("%(ORIG_NAME)s", %(VAR_NAME)s); 
    }\n"""
    
    def __init__(self, sortedFields):
        self.fields = sortedFields    
    
    def toJava(self):
        buf = ""
        for name in self.fields:
            varName = self._varName(name)
            buf += self.TPL % {"C_NAME":self._clazzName(name), \
                "VAR_NAME":varName, "ORIG_NAME":name}
        return buf

class ClazzBuilder(SourceBuilder):
    
    def __init__(self, clazzName):
        self.name = clazzName
        self.mans = set()
        self.fields = set()
    
    def appendManField(self, name):
        self.mans.add(name)
    
    def appendOptField(self, name):
        self.fields.add(name)
    
    def toJava(self, pack):
        cb = ConstructorBuilder(self.name, sorted(self.mans))
            
        sb = SetterBuilder(sorted(self.fields))
        
        namespacePrefix = "com.billsoft.triptra.xsd"
        clazzPackName = self.name.lower()
        packPrefix = "%s.%s" % (namespacePrefix, clazzPackName)
        getResult = """public %(PACK_PREFIX)s.Atdw_data_results retrieveResult()throws Exception{
            
            CommandHandler ch = new CommandHandler();            
            ch.setDistributorKey(getDistKey());
            ch.setCommandName(getName());
            ch.setCommandParameters(getParams());
            
            CommandHandlerResponse resp = atws.commandHandler(ch);
            
            XMLStreamReader xsr = buildXmlStreamReader(resp);
            return %(PACK_PREFIX)s.Atdw_data_results.Factory.parse(xsr);            
        }""" % {"PACK_PREFIX":packPrefix}
            
        imports = """import javax.xml.stream.XMLStreamReader;import org.tempuri.soap.australiantourismwebservice.CommandHandler;
        import org.tempuri.soap.australiantourismwebservice.CommandHandlerResponse;"""

        return "package %s;%s\npublic class %s extends MyCommandHandler {\n%s%s%s\n}\n" % \
                 (pack, imports, self.name, cb.toJava(), sb.toJava(), getResult)

class CommandHandlerBuilderGenerator:

    def __str2Array(self, raw):
        subs = raw.split()
        pending = []
        for sub in subs:
            sub = sub.strip()
            if len(sub) > 0:
                pending.append(sub)
        return pending    
    

    def writef(self, fp, raw):
        with open(fp, "w") as f:
            f.write(raw)    
    
    def __procOneBuilder(self, pack, clazzName, mans, opts):
        
        cb = ClazzBuilder(clazzName)
        for name in mans:
            cb.appendManField(name)
        for name in opts:
            cb.appendOptField(name)
                
        self.writef("%s.java" % (cb.name), cb.toJava(pack))
    
    def toJava(self, pack):
        for clazzName in matrix:
            manStr, optStr = matrix[clazzName]
            mans = self.__str2Array(manStr)
            opts = self.__str2Array(optStr)            
            self.__procOneBuilder(pack, clazzName, mans, opts)

def main():
    chbg = CommandHandlerBuilderGenerator()
    chbg.toJava("com.billsoft.triptra.handlers");
    
if __name__ == '__main__':
    exit(main())
