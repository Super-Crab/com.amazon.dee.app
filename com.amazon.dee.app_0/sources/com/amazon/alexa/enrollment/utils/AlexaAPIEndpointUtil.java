package com.amazon.alexa.enrollment.utils;

import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class AlexaAPIEndpointUtil {
    private static final String EU_DEVO_ALEXA_ENDPOINT = "https://api-amazonalexa-eu.integ.amazon.com";
    private static final String EU_GAMMA_ALEXA_ENDPOINT = "https://api-amazonalexa-gamma-eu-dub.dub.proxy.amazon.com";
    private static final String EU_PROD_ALEXA_ENDPOINT = "https://api.eu.amazonalexa.com";
    private static final String EU_REGION = "EU";
    private static final String FE_DEVO_ALEXA_ENDPOINT = "https://api-amazonalexa-fe.integ.amazon.com";
    private static final String FE_GAMMA_ALEXA_ENDPOINT = "https://api-amazonalexa-gamma-fe-pdx.pdx.proxy.amazon.com";
    private static final String FE_PROD_ALEXA_ENDPOINT = "https://api.fe.amazonalexa.com";
    private static final String FE_REGION = "FE";
    private static final String NA_DEVO_ALEXA_ENDPOINT = "https://api-amazonalexa.integ.amazon.com";
    private static final String NA_GAMMA_ALEXA_ENDPOINT = "https://api-amazonalexa-gamma.amazon.com";
    private static final String NA_PROD_ALEXA_ENDPOINT = "https://api.amazonalexa.com";
    private static final String NA_REGION = "NA";
    private static final String TAG = GeneratedOutlineSupport1.outline39(AlexaAPIEndpointUtil.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    /* loaded from: classes7.dex */
    public enum APIEndPoint {
        NA_ALPHA("https://api-amazonalexa.integ.amazon.com"),
        NA_BETA("https://api-amazonalexa.integ.amazon.com"),
        NA_GAMMA("https://api-amazonalexa-gamma.amazon.com"),
        NA_PROD("https://api.amazonalexa.com"),
        EU_ALPHA("https://api-amazonalexa-eu.integ.amazon.com"),
        EU_BETA("https://api-amazonalexa-eu.integ.amazon.com"),
        EU_GAMMA("https://api-amazonalexa-gamma-eu-dub.dub.proxy.amazon.com"),
        EU_PROD("https://api.eu.amazonalexa.com"),
        FE_ALPHA("https://api-amazonalexa-fe.integ.amazon.com"),
        FE_BETA("https://api-amazonalexa-fe.integ.amazon.com"),
        FE_GAMMA("https://api-amazonalexa-gamma-fe-pdx.pdx.proxy.amazon.com"),
        FE_PROD("https://api.fe.amazonalexa.com");
        
        private final String endPoint;

        APIEndPoint(String str) {
            this.endPoint = str;
        }

        public static APIEndPoint fromRegionAndStage(String str) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException unused) {
                GeneratedOutlineSupport1.outline162("Invalid region", str, AlexaAPIEndpointUtil.TAG);
                return NA_PROD;
            }
        }

        public String getEndPoint() {
            return this.endPoint;
        }
    }

    /* loaded from: classes7.dex */
    public enum CountryCodeToRegion {
        US("NA"),
        CA("NA"),
        MX("NA"),
        BR("NA"),
        ES("EU"),
        DE("EU"),
        FR("EU"),
        IT("EU"),
        IN("EU"),
        GB("EU"),
        AU("FE"),
        JP("FE");
        
        private final String region;

        CountryCodeToRegion(String str) {
            this.region = str;
        }

        public static CountryCodeToRegion fromCountryCodeName(String str) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException unused) {
                GeneratedOutlineSupport1.outline162("Invalid country code ", str, AlexaAPIEndpointUtil.TAG);
                return US;
            }
        }

        public String getRegion() {
            return this.region;
        }
    }

    public String getEndpoint(String str, String str2) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(CountryCodeToRegion.fromCountryCodeName(str).getRegion(), "_");
        outline113.append(str2.toUpperCase());
        return APIEndPoint.fromRegionAndStage(outline113.toString()).getEndPoint();
    }
}
