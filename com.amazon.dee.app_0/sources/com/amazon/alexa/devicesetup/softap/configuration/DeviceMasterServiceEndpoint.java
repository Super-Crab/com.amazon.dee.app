package com.amazon.alexa.devicesetup.softap.configuration;

import com.amazon.alexa.blueprints.BlueprintsEndpointConstants;
import com.amazon.alexa.marketplace.api.CountryCode;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public enum DeviceMasterServiceEndpoint {
    US_ALPHA("projectdee-ui-dev-registration.aka.amazon.com"),
    SA_ALPHA("projectdee-ui-dev-registration.aka.amazon.com"),
    US_BETA("projectdee-ui-registration.aka.amazon.com"),
    SA_BETA("projectdee-ui-registration.aka.amazon.com"),
    EU_DEVO("projectdee-ui-eu-registration.aka.amazon.com"),
    GB_DEVO("projectdee-ui-gb-registration.aka.amazon.com"),
    DE_DEVO("projectdee-ui-de-registration.aka.amazon.com"),
    JP_DEVO("projectdee-ui-jp-registration.aka.amazon.com"),
    IN_DEVO("projectdee-ui-in-registration.aka.amazon.com"),
    CA_DEVO("projectdee-ui-ca-registration.aka.amazon.com"),
    FR_DEVO("projectdee-ui-fr-registration.aka.amazon.com"),
    IT_DEVO("projectdee-ui-it-registration.aka.amazon.com"),
    NL_DEVO("projectdee-ui-nl-registration.aka.amazon.com"),
    MX_DEVO("projectdee-ui-mx-registration.aka.amazon.com"),
    BR_DEVO("projectdee-ui-br-registration.aka.amazon.com"),
    CN_DEVO("projectdee-ui-cn-registration.aka.amazon.com"),
    AU_DEVO("projectdee-ui-au-registration.aka.amazon.com"),
    ES_DEVO("projectdee-ui-es-registration.aka.amazon.com"),
    US_GAMMA("projectdee-ui-gamma-registration.aka.amazon.com"),
    SA_GAMMA("projectdee-ui-gamma-registration.aka.amazon.com"),
    EU_GAMMA("projectdee-ui-gamma-eu-registration.aka.amazon.com"),
    GB_GAMMA("projectdee-ui-gamma-registration.amazon.co.uk"),
    DE_GAMMA("projectdee-ui-gamma-registration.amazon.de"),
    JP_GAMMA("projectdee-ui-gamma-registration.amazon.co.jp"),
    IN_GAMMA("projectdee-ui-gamma-registration.amazon.in"),
    CA_GAMMA("projectdee-ui-gamma-registration.amazon.ca"),
    FR_GAMMA("projectdee-ui-gamma-registration.amazon.fr"),
    IT_GAMMA("projectdee-ui-gamma-registration.amazon.it"),
    NL_GAMMA("projectdee-ui-gamma-registration.amazon.nl"),
    MX_GAMMA("projectdee-ui-gamma-registration.amazon.com.mx"),
    BR_GAMMA("projectdee-ui-gamma-registration.amazon.com.br"),
    CN_GAMMA("projectdee-ui-gamma-registration.amazon.cn"),
    AU_GAMMA("projectdee-ui-gamma-registration.amazon.com.au"),
    ES_GAMMA("projectdee-ui-gamma-registration.amazon.es"),
    US_PRE_PROD("projectdee-ui-pre-prod-registration.aka.amazon.com"),
    SA_PRE_PROD("projectdee-ui-pre-prod-registration.aka.amazon.com"),
    EU_PRE_PROD("projectdee-ui-preprod-eu-registration.aka.amazon.com"),
    GB_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.co.uk"),
    DE_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.de"),
    JP_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.co.jp"),
    IN_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.in"),
    CA_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.ca"),
    FR_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.fr"),
    IT_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.it"),
    NL_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.nl"),
    MX_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.com.mx"),
    BR_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.com.br"),
    CN_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.cn"),
    AU_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.com.au"),
    ES_PRE_PROD("projectdee-ui-pre-prod-registration.amazon.es"),
    US_PROD("guipitan.amazon.com"),
    SA_PROD("guipitan.amazon.com"),
    EU_PROD("seamus.amazon.com"),
    GB_PROD("seamus.amazon.co.uk"),
    DE_PROD("seamus.amazon.de"),
    JP_PROD("guipitan.amazon.co.jp"),
    IN_PROD("guipitan.amazon.in"),
    CA_PROD("guipitan.amazon.ca"),
    FR_PROD("guipitan.amazon.fr"),
    IT_PROD("guipitan.amazon.it"),
    NL_PROD("guipitan.amazon.nl"),
    MX_PROD("guipitan.amazon.com.mx"),
    BR_PROD("guipitan.amazon.com.br"),
    CN_PROD("guipitan.amazon.cn"),
    AU_PROD("guipitan.amazon.com.au"),
    ES_PROD("guipitan.amazon.es");
    
    private final String endpoint;
    private static final Map<CountryCode, DeviceMasterServiceEndpoint> ALPHA_MARKETPLACE_ENDPOINT_MAP = new HashMap<CountryCode, DeviceMasterServiceEndpoint>() { // from class: com.amazon.alexa.devicesetup.softap.configuration.DeviceMasterServiceEndpoint.1
        {
            put(CountryCode.AU, DeviceMasterServiceEndpoint.AU_DEVO);
            put(CountryCode.BR, DeviceMasterServiceEndpoint.BR_DEVO);
            put(CountryCode.CA, DeviceMasterServiceEndpoint.CA_DEVO);
            put(CountryCode.CN, DeviceMasterServiceEndpoint.CN_DEVO);
            put(CountryCode.DE, DeviceMasterServiceEndpoint.DE_DEVO);
            put(CountryCode.ES, DeviceMasterServiceEndpoint.ES_DEVO);
            put(CountryCode.FR, DeviceMasterServiceEndpoint.FR_DEVO);
            put(CountryCode.GB, DeviceMasterServiceEndpoint.GB_DEVO);
            put(CountryCode.IN, DeviceMasterServiceEndpoint.IN_DEVO);
            put(CountryCode.IT, DeviceMasterServiceEndpoint.IT_DEVO);
            put(CountryCode.JP, DeviceMasterServiceEndpoint.JP_DEVO);
            put(CountryCode.MX, DeviceMasterServiceEndpoint.MX_DEVO);
            put(CountryCode.NL, DeviceMasterServiceEndpoint.NL_DEVO);
            put(CountryCode.US, DeviceMasterServiceEndpoint.US_ALPHA);
            put(CountryCode.SA, DeviceMasterServiceEndpoint.SA_ALPHA);
        }
    };
    private static final Map<CountryCode, DeviceMasterServiceEndpoint> BETA_MARKETPLACE_ENDPOINT_MAP = new HashMap<CountryCode, DeviceMasterServiceEndpoint>() { // from class: com.amazon.alexa.devicesetup.softap.configuration.DeviceMasterServiceEndpoint.2
        {
            put(CountryCode.AU, DeviceMasterServiceEndpoint.AU_DEVO);
            put(CountryCode.BR, DeviceMasterServiceEndpoint.BR_DEVO);
            put(CountryCode.CA, DeviceMasterServiceEndpoint.CA_DEVO);
            put(CountryCode.CN, DeviceMasterServiceEndpoint.CN_DEVO);
            put(CountryCode.DE, DeviceMasterServiceEndpoint.DE_DEVO);
            put(CountryCode.ES, DeviceMasterServiceEndpoint.ES_DEVO);
            put(CountryCode.FR, DeviceMasterServiceEndpoint.FR_DEVO);
            put(CountryCode.GB, DeviceMasterServiceEndpoint.GB_DEVO);
            put(CountryCode.IN, DeviceMasterServiceEndpoint.IN_DEVO);
            put(CountryCode.IT, DeviceMasterServiceEndpoint.IT_DEVO);
            put(CountryCode.JP, DeviceMasterServiceEndpoint.JP_DEVO);
            put(CountryCode.MX, DeviceMasterServiceEndpoint.MX_DEVO);
            put(CountryCode.NL, DeviceMasterServiceEndpoint.NL_DEVO);
            put(CountryCode.US, DeviceMasterServiceEndpoint.US_BETA);
            put(CountryCode.SA, DeviceMasterServiceEndpoint.SA_BETA);
        }
    };
    private static final Map<CountryCode, DeviceMasterServiceEndpoint> GAMMA_MARKETPLACE_ENDPOINT_MAP = new HashMap<CountryCode, DeviceMasterServiceEndpoint>() { // from class: com.amazon.alexa.devicesetup.softap.configuration.DeviceMasterServiceEndpoint.3
        {
            put(CountryCode.AU, DeviceMasterServiceEndpoint.AU_GAMMA);
            put(CountryCode.BR, DeviceMasterServiceEndpoint.BR_GAMMA);
            put(CountryCode.CA, DeviceMasterServiceEndpoint.CA_GAMMA);
            put(CountryCode.CN, DeviceMasterServiceEndpoint.CN_GAMMA);
            put(CountryCode.DE, DeviceMasterServiceEndpoint.DE_GAMMA);
            put(CountryCode.ES, DeviceMasterServiceEndpoint.ES_GAMMA);
            put(CountryCode.FR, DeviceMasterServiceEndpoint.FR_GAMMA);
            put(CountryCode.GB, DeviceMasterServiceEndpoint.GB_GAMMA);
            put(CountryCode.IN, DeviceMasterServiceEndpoint.IN_GAMMA);
            put(CountryCode.IT, DeviceMasterServiceEndpoint.IT_GAMMA);
            put(CountryCode.JP, DeviceMasterServiceEndpoint.JP_GAMMA);
            put(CountryCode.MX, DeviceMasterServiceEndpoint.MX_GAMMA);
            put(CountryCode.NL, DeviceMasterServiceEndpoint.NL_GAMMA);
            put(CountryCode.US, DeviceMasterServiceEndpoint.US_GAMMA);
            put(CountryCode.SA, DeviceMasterServiceEndpoint.SA_GAMMA);
        }
    };
    private static final Map<CountryCode, DeviceMasterServiceEndpoint> PRE_PROD_MARKETPLACE_ENDPOINT_MAP = new HashMap<CountryCode, DeviceMasterServiceEndpoint>() { // from class: com.amazon.alexa.devicesetup.softap.configuration.DeviceMasterServiceEndpoint.4
        {
            put(CountryCode.AU, DeviceMasterServiceEndpoint.AU_PRE_PROD);
            put(CountryCode.BR, DeviceMasterServiceEndpoint.BR_PRE_PROD);
            put(CountryCode.CA, DeviceMasterServiceEndpoint.CA_PRE_PROD);
            put(CountryCode.CN, DeviceMasterServiceEndpoint.CN_PRE_PROD);
            put(CountryCode.DE, DeviceMasterServiceEndpoint.DE_PRE_PROD);
            put(CountryCode.ES, DeviceMasterServiceEndpoint.ES_PRE_PROD);
            put(CountryCode.FR, DeviceMasterServiceEndpoint.FR_PRE_PROD);
            put(CountryCode.GB, DeviceMasterServiceEndpoint.GB_PRE_PROD);
            put(CountryCode.IN, DeviceMasterServiceEndpoint.IN_PRE_PROD);
            put(CountryCode.IT, DeviceMasterServiceEndpoint.IT_PRE_PROD);
            put(CountryCode.JP, DeviceMasterServiceEndpoint.JP_PRE_PROD);
            put(CountryCode.MX, DeviceMasterServiceEndpoint.MX_PRE_PROD);
            put(CountryCode.NL, DeviceMasterServiceEndpoint.NL_PRE_PROD);
            put(CountryCode.US, DeviceMasterServiceEndpoint.US_PRE_PROD);
            put(CountryCode.SA, DeviceMasterServiceEndpoint.SA_PRE_PROD);
        }
    };
    private static final Map<CountryCode, DeviceMasterServiceEndpoint> PROD_MARKETPLACE_ENDPOINT_MAP = new HashMap<CountryCode, DeviceMasterServiceEndpoint>() { // from class: com.amazon.alexa.devicesetup.softap.configuration.DeviceMasterServiceEndpoint.5
        {
            put(CountryCode.AU, DeviceMasterServiceEndpoint.AU_PROD);
            put(CountryCode.BR, DeviceMasterServiceEndpoint.BR_PROD);
            put(CountryCode.CA, DeviceMasterServiceEndpoint.CA_PROD);
            put(CountryCode.CN, DeviceMasterServiceEndpoint.CN_PROD);
            put(CountryCode.DE, DeviceMasterServiceEndpoint.DE_PROD);
            put(CountryCode.ES, DeviceMasterServiceEndpoint.ES_PROD);
            put(CountryCode.FR, DeviceMasterServiceEndpoint.FR_PROD);
            put(CountryCode.GB, DeviceMasterServiceEndpoint.GB_PROD);
            put(CountryCode.IN, DeviceMasterServiceEndpoint.IN_PROD);
            put(CountryCode.IT, DeviceMasterServiceEndpoint.IT_PROD);
            put(CountryCode.JP, DeviceMasterServiceEndpoint.JP_PROD);
            put(CountryCode.MX, DeviceMasterServiceEndpoint.MX_PROD);
            put(CountryCode.NL, DeviceMasterServiceEndpoint.NL_PROD);
            put(CountryCode.US, DeviceMasterServiceEndpoint.US_PROD);
            put(CountryCode.SA, DeviceMasterServiceEndpoint.SA_PROD);
        }
    };
    private static final Map<String, Map<CountryCode, DeviceMasterServiceEndpoint>> STAGE_TO_MARKETPLACE_ENDPOINT_MAP = new HashMap<String, Map<CountryCode, DeviceMasterServiceEndpoint>>() { // from class: com.amazon.alexa.devicesetup.softap.configuration.DeviceMasterServiceEndpoint.6
        {
            put("alpha", DeviceMasterServiceEndpoint.ALPHA_MARKETPLACE_ENDPOINT_MAP);
            put("beta", DeviceMasterServiceEndpoint.BETA_MARKETPLACE_ENDPOINT_MAP);
            put("gamma", DeviceMasterServiceEndpoint.GAMMA_MARKETPLACE_ENDPOINT_MAP);
            put(BlueprintsEndpointConstants.PREPROD, DeviceMasterServiceEndpoint.PRE_PROD_MARKETPLACE_ENDPOINT_MAP);
            put("prod", DeviceMasterServiceEndpoint.PROD_MARKETPLACE_ENDPOINT_MAP);
        }
    };

    DeviceMasterServiceEndpoint(String str) {
        this.endpoint = str;
    }

    public static String forStageAndMarketPlace(String str, CountryCode countryCode) {
        if (STAGE_TO_MARKETPLACE_ENDPOINT_MAP.containsKey(str.toLowerCase())) {
            Map<CountryCode, DeviceMasterServiceEndpoint> map = STAGE_TO_MARKETPLACE_ENDPOINT_MAP.get(str);
            if (map.containsKey(countryCode)) {
                return map.get(countryCode).endpoint;
            }
            return map.get(CountryCode.US).endpoint;
        }
        return null;
    }
}
