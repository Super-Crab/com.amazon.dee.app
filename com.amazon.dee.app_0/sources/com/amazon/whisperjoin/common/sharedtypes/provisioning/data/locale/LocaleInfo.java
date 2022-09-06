package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class LocaleInfo {
    private static final String TAG = "com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleInfo";

    /* loaded from: classes13.dex */
    public enum Locale {
        US(ObfuscatedMarketplaceId.US_MARKETPLACE_ID, Realm.US_REALM),
        UK(ObfuscatedMarketplaceId.UK_MARKETPLACE_ID, Realm.UK_REALM),
        DE(ObfuscatedMarketplaceId.DE_MARKETPLACE_ID, Realm.DE_REALM),
        FR(ObfuscatedMarketplaceId.FR_MARKETPLACE_ID, Realm.FR_REALM),
        ES(ObfuscatedMarketplaceId.ES_MARKETPLACE_ID, Realm.ES_REALM),
        IN(ObfuscatedMarketplaceId.IN_MARKETPLACE_ID, Realm.IN_REALM),
        IT(ObfuscatedMarketplaceId.IT_MARKETPLACE_ID, Realm.IT_REALM),
        CN(ObfuscatedMarketplaceId.CN_MARKETPLACE_ID, Realm.CN_REALM),
        BR(ObfuscatedMarketplaceId.BR_MARKETPLACE_ID, Realm.BR_REALM),
        MX(ObfuscatedMarketplaceId.MX_MARKETPLACE_ID, Realm.MX_REALM),
        AU(ObfuscatedMarketplaceId.AU_MARKETPLACE_ID, Realm.AU_REALM),
        RU(ObfuscatedMarketplaceId.RU_MARKETPLACE_ID, Realm.RU_REALM),
        NL(ObfuscatedMarketplaceId.NL_MARKETPLACE_ID, Realm.NL_REALM),
        AE(ObfuscatedMarketplaceId.AE_MARKETPLACE_ID, Realm.AE_REALM),
        SA(ObfuscatedMarketplaceId.SA_MARKETPLACE_ID, Realm.SA_REALM),
        TR(ObfuscatedMarketplaceId.TR_MARKETPLACE_ID, Realm.TR_REALM),
        JP(ObfuscatedMarketplaceId.JP_MARKETPLACE_ID, Realm.JP_REALM),
        CA(ObfuscatedMarketplaceId.CA_MARKETPLACE_ID, Realm.CA_REALM);
        
        private static final List<String> sLocaleNamesList;
        private final String mObfuscatedMarketplaceId;
        private final String mRealm;

        static {
            ArrayList arrayList = new ArrayList();
            for (Locale locale : values()) {
                arrayList.add(locale.name());
            }
            sLocaleNamesList = Collections.unmodifiableList(arrayList);
        }

        Locale(String str, String str2) {
            this.mObfuscatedMarketplaceId = str;
            this.mRealm = str2;
        }

        public static Locale getLocaleFromObfuscatedMarketplaceId(String str) {
            Locale[] values;
            for (Locale locale : values()) {
                if (locale.getObfuscatedMarketplaceId().equals(str)) {
                    return locale;
                }
            }
            WJLog.d(LocaleInfo.TAG, "No matching Locale was found for: " + str + ". Defaulting to US.");
            return US;
        }

        public static List<String> getLocaleNamesAsList() {
            return sLocaleNamesList;
        }

        public LocaleConfiguration getLocaleConfiguration() {
            LocaleConfiguration localeConfiguration = new LocaleConfiguration();
            localeConfiguration.setMarketplace(this.mObfuscatedMarketplaceId);
            localeConfiguration.setRealm(this.mRealm);
            return localeConfiguration;
        }

        public String getObfuscatedMarketplaceId() {
            return this.mObfuscatedMarketplaceId;
        }

        public String getRealm() {
            return this.mRealm;
        }
    }

    /* loaded from: classes13.dex */
    public static class ObfuscatedMarketplaceId {
        public static final String AE_MARKETPLACE_ID = "A2VIGQ35RCS4UG";
        public static final String AU_MARKETPLACE_ID = "A39IBJ37TRP1C6";
        public static final String BR_MARKETPLACE_ID = "A2Q3Y263D00KWC";
        public static final String CA_MARKETPLACE_ID = "A2EUQ1WTGCTBG2";
        public static final String CN_MARKETPLACE_ID = "AAHKV2X7AFYLW";
        public static final String DE_MARKETPLACE_ID = "A1PA6795UKMFR9";
        public static final String ES_MARKETPLACE_ID = "A1RKKUPIHCS9HS";
        public static final String FR_MARKETPLACE_ID = "A13V1IB3VIYZZH";
        public static final String IN_MARKETPLACE_ID = "A21TJRUUN4KGV";
        public static final String IT_MARKETPLACE_ID = "APJ6JRA9NG5V4";
        public static final String JP_MARKETPLACE_ID = "A1VC38T7YXB528";
        public static final String MX_MARKETPLACE_ID = "A1AM78C64UM0Y8";
        public static final String NL_MARKETPLACE_ID = "A1805IZSGTT6HS";
        public static final String RU_MARKETPLACE_ID = "AD2EMQ3L3PG8S";
        public static final String SA_MARKETPLACE_ID = "A17E79C6D8DWNP";
        public static final String TR_MARKETPLACE_ID = "A33AVAJ2PDY3EV";
        public static final String UK_MARKETPLACE_ID = "A1F83G8C2ARO7P";
        public static final String US_MARKETPLACE_ID = "ATVPDKIKX0DER";

        private ObfuscatedMarketplaceId() {
        }
    }

    /* loaded from: classes13.dex */
    public static class Realm {
        public static final String AE_REALM = "AEAmazon";
        public static final String AU_REALM = "AUAmazon";
        public static final String BR_REALM = "BRAmazon";
        public static final String CA_REALM = "CAAmazon";
        public static final String CN_REALM = "CNAmazon";
        public static final String DE_REALM = "DEAmazon";
        public static final String ES_REALM = "ESAmazon";
        public static final String FR_REALM = "FRAmazon";
        public static final String IN_REALM = "INAmazon";
        public static final String IT_REALM = "ITAmazon";
        public static final String JP_REALM = "JPAmazon";
        private static final Map<String, String> MARKETPLACE_TO_REALM_MAP = new HashMap();
        public static final String MX_REALM = "MXAmazon";
        public static final String NL_REALM = "NLAmazon";
        public static final String RU_REALM = "RUAmazon";
        public static final String SA_REALM = "SAAmazon";
        public static final String TR_REALM = "TRAmazon";
        public static final String UK_REALM = "UKAmazon";
        public static final String US_REALM = "USAmazon";

        static {
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.US_MARKETPLACE_ID, US_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.UK_MARKETPLACE_ID, UK_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.DE_MARKETPLACE_ID, DE_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.FR_MARKETPLACE_ID, FR_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.ES_MARKETPLACE_ID, ES_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.IN_MARKETPLACE_ID, IN_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.IT_MARKETPLACE_ID, IT_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.CN_MARKETPLACE_ID, CN_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.BR_MARKETPLACE_ID, BR_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.MX_MARKETPLACE_ID, MX_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.AU_MARKETPLACE_ID, AU_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.RU_MARKETPLACE_ID, RU_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.NL_MARKETPLACE_ID, NL_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.AE_MARKETPLACE_ID, AE_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.SA_MARKETPLACE_ID, SA_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.TR_MARKETPLACE_ID, TR_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.JP_MARKETPLACE_ID, JP_REALM);
            MARKETPLACE_TO_REALM_MAP.put(ObfuscatedMarketplaceId.CA_MARKETPLACE_ID, CA_REALM);
        }

        private Realm() {
        }

        public static final String fromObfuscatedMarketplaceId(String str) {
            String str2 = MARKETPLACE_TO_REALM_MAP.get(str);
            if (str2 != null) {
                return str2;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unrecognized marketplace id: ", str));
        }
    }

    private LocaleInfo() {
    }
}
