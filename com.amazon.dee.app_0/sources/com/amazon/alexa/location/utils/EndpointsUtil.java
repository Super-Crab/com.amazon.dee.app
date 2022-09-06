package com.amazon.alexa.location.utils;

import androidx.annotation.NonNull;
import com.amazon.alexa.blueprints.BlueprintsEndpointConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public final class EndpointsUtil {
    public static final String ENDPOINT_EU_BETA = "https://api-amazonalexa-eu.integ.amazon.com";
    public static final String ENDPOINT_EU_GAMMA = "https://api-amazonalexa-gamma-eu-dub.dub.proxy.amazon.com";
    public static final String ENDPOINT_EU_PROD = "https://api.eu.amazonalexa.com";
    public static final String ENDPOINT_FE_BETA = "https://api-amazonalexa-fe.integ.amazon.com";
    public static final String ENDPOINT_FE_GAMMA = "https://api-amazonalexa-gamma-fe-pdx.pdx.proxy.amazon.com";
    public static final String ENDPOINT_FE_PROD = "https://api.fe.amazonalexa.com";
    public static final String ENDPOINT_NA_BETA = "https://api-amazonalexa.integ.amazon.com";
    public static final String ENDPOINT_NA_GAMMA = "https://api-amazonalexa-gamma.amazon.com";
    public static final String ENDPOINT_NA_PROD = "https://api.amazonalexa.com";
    private static Map<String, Map<String, String>> endpointMap;

    private EndpointsUtil() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getEndpoint(@NonNull String str, @NonNull String str2) {
        char c;
        switch (str.hashCode()) {
            case -318354310:
                if (str.equals(BlueprintsEndpointConstants.PREPROD)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3020272:
                if (str.equals("beta")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3449687:
                if (str.equals("prod")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 98120615:
                if (str.equals("gamma")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 103145323:
                if (str.equals("local")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            return getEndpointMap().get("beta").get(str2);
        }
        if (c == 2) {
            return getEndpointMap().get("gamma").get(str2);
        }
        if (c != 3 && c != 4 && c != 5) {
            return getEndpointMap().get("prod").get(str2);
        }
        return getEndpointMap().get("prod").get(str2);
    }

    public static Map<String, Map<String, String>> getEndpointMap() {
        if (endpointMap == null) {
            endpointMap = new HashMap();
        }
        if (endpointMap.isEmpty()) {
            HashMap outline134 = GeneratedOutlineSupport1.outline134("US", ENDPOINT_NA_BETA, "CA", ENDPOINT_NA_BETA);
            outline134.put("MX", ENDPOINT_NA_BETA);
            outline134.put("BR", ENDPOINT_NA_BETA);
            outline134.put("DE", ENDPOINT_EU_BETA);
            outline134.put("IT", ENDPOINT_EU_BETA);
            outline134.put("GB", ENDPOINT_EU_BETA);
            outline134.put("FR", ENDPOINT_EU_BETA);
            outline134.put("ES", ENDPOINT_EU_BETA);
            outline134.put("IN", ENDPOINT_EU_BETA);
            outline134.put("JP", ENDPOINT_FE_BETA);
            outline134.put("AU", ENDPOINT_FE_BETA);
            outline134.put("NL", ENDPOINT_FE_BETA);
            endpointMap.put("beta", outline134);
            HashMap hashMap = new HashMap();
            hashMap.put("US", ENDPOINT_NA_GAMMA);
            hashMap.put("CA", ENDPOINT_NA_GAMMA);
            hashMap.put("MX", ENDPOINT_NA_GAMMA);
            hashMap.put("BR", ENDPOINT_NA_GAMMA);
            hashMap.put("DE", ENDPOINT_EU_GAMMA);
            hashMap.put("IT", ENDPOINT_EU_GAMMA);
            hashMap.put("GB", ENDPOINT_EU_GAMMA);
            hashMap.put("FR", ENDPOINT_EU_GAMMA);
            hashMap.put("ES", ENDPOINT_EU_GAMMA);
            hashMap.put("IN", ENDPOINT_EU_GAMMA);
            hashMap.put("JP", ENDPOINT_FE_GAMMA);
            hashMap.put("AU", ENDPOINT_FE_GAMMA);
            hashMap.put("NL", ENDPOINT_FE_GAMMA);
            endpointMap.put("gamma", hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("US", ENDPOINT_NA_PROD);
            hashMap2.put("CA", ENDPOINT_NA_PROD);
            hashMap2.put("MX", ENDPOINT_NA_PROD);
            hashMap2.put("BR", ENDPOINT_NA_PROD);
            hashMap2.put("DE", ENDPOINT_EU_PROD);
            hashMap2.put("IT", ENDPOINT_EU_PROD);
            hashMap2.put("GB", ENDPOINT_EU_PROD);
            hashMap2.put("FR", ENDPOINT_EU_PROD);
            hashMap2.put("ES", ENDPOINT_EU_PROD);
            hashMap2.put("IN", ENDPOINT_EU_PROD);
            hashMap2.put("JP", ENDPOINT_FE_PROD);
            hashMap2.put("AU", ENDPOINT_FE_PROD);
            hashMap2.put("NL", ENDPOINT_FE_PROD);
            endpointMap.put("prod", hashMap2);
        }
        return endpointMap;
    }
}
