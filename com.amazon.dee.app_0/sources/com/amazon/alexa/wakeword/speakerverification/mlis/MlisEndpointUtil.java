package com.amazon.alexa.wakeword.speakerverification.mlis;

import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.authorization.RegionUtil;
/* loaded from: classes11.dex */
public final class MlisEndpointUtil {
    static final String EU_MLIS_ENDPOINT = "https://mlis.amazon.eu/v1";
    static final String FE_MLIS_ENDPOINT = "https://mlis.amazon.co.jp/v1";
    @VisibleForTesting
    static final String NA_MLIS_ENDPOINT = "https://mlis.amazon.com/v1";

    /* loaded from: classes11.dex */
    public enum RegionEndpoint {
        EU(RegionUtil.REGION_STRING_EU, MlisEndpointUtil.EU_MLIS_ENDPOINT),
        FE(RegionUtil.REGION_STRING_FE, MlisEndpointUtil.FE_MLIS_ENDPOINT),
        NA(RegionUtil.REGION_STRING_NA, MlisEndpointUtil.NA_MLIS_ENDPOINT);
        
        private final String endPoint;
        private final String region;

        RegionEndpoint(String str, String str2) {
            this.region = str;
            this.endPoint = str2;
        }

        public static RegionEndpoint fromRegion(String str) {
            char c;
            int hashCode = str.hashCode();
            if (hashCode == 2224) {
                if (str.equals(RegionUtil.REGION_STRING_EU)) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 2239) {
                if (hashCode == 2483 && str.equals(RegionUtil.REGION_STRING_NA)) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (str.equals(RegionUtil.REGION_STRING_FE)) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c == 1) {
                    return FE;
                }
                if (c != 2) {
                    return NA;
                }
                return NA;
            }
            return EU;
        }

        public String getEndPoint() {
            return this.endPoint;
        }

        public String getRegion() {
            return this.region;
        }
    }

    private MlisEndpointUtil() {
    }
}
