package org.apache.commons.net.ftp;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommand;
import java.text.DateFormatSymbols;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
/* loaded from: classes4.dex */
public class FTPClientConfig {
    private static Map LANGUAGE_CODE_MAP = new TreeMap();
    public static final String SYST_MVS = "MVS";
    public static final String SYST_NT = "WINDOWS";
    public static final String SYST_OS2 = "OS/2";
    public static final String SYST_OS400 = "OS/400";
    public static final String SYST_UNIX = "UNIX";
    public static final String SYST_VMS = "VMS";
    private String defaultDateFormatStr;
    private String recentDateFormatStr;
    private String serverLanguageCode;
    private final String serverSystemKey;
    private String serverTimeZoneId;
    private String shortMonthNames;

    static {
        LANGUAGE_CODE_MAP.put("en", Locale.ENGLISH);
        LANGUAGE_CODE_MAP.put("de", Locale.GERMAN);
        LANGUAGE_CODE_MAP.put("it", Locale.ITALIAN);
        LANGUAGE_CODE_MAP.put("es", new Locale("es", "", ""));
        LANGUAGE_CODE_MAP.put("pt", new Locale("pt", "", ""));
        LANGUAGE_CODE_MAP.put(ProvisioningCommand.DATA_KEY, new Locale(ProvisioningCommand.DATA_KEY, "", ""));
        LANGUAGE_CODE_MAP.put("sv", new Locale("sv", "", ""));
        LANGUAGE_CODE_MAP.put("no", new Locale("no", "", ""));
        LANGUAGE_CODE_MAP.put("nl", new Locale("nl", "", ""));
        LANGUAGE_CODE_MAP.put("ro", new Locale("ro", "", ""));
        LANGUAGE_CODE_MAP.put("sq", new Locale("sq", "", ""));
        LANGUAGE_CODE_MAP.put("sh", new Locale("sh", "", ""));
        LANGUAGE_CODE_MAP.put("sk", new Locale("sk", "", ""));
        LANGUAGE_CODE_MAP.put("sl", new Locale("sl", "", ""));
        LANGUAGE_CODE_MAP.put("fr", "jan|fév|mar|avr|mai|jun|jui|aoû|sep|oct|nov|déc");
    }

    public FTPClientConfig(String str) {
        this.defaultDateFormatStr = null;
        this.recentDateFormatStr = null;
        this.serverLanguageCode = null;
        this.shortMonthNames = null;
        this.serverTimeZoneId = null;
        this.serverSystemKey = str;
    }

    public static DateFormatSymbols getDateFormatSymbols(String str) {
        String[] splitShortMonthString = splitShortMonthString(str);
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(Locale.US);
        dateFormatSymbols.setShortMonths(splitShortMonthString);
        return dateFormatSymbols;
    }

    public static Collection getSupportedLanguageCodes() {
        return LANGUAGE_CODE_MAP.keySet();
    }

    public static DateFormatSymbols lookupDateFormatSymbols(String str) {
        Object obj = LANGUAGE_CODE_MAP.get(str);
        if (obj != null) {
            if (obj instanceof Locale) {
                return new DateFormatSymbols((Locale) obj);
            }
            if (obj instanceof String) {
                return getDateFormatSymbols((String) obj);
            }
        }
        return new DateFormatSymbols(Locale.US);
    }

    private static String[] splitShortMonthString(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, AccessoryMetricsConstants.DELIMITER);
        if (12 == stringTokenizer.countTokens()) {
            String[] strArr = new String[13];
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                strArr[i] = stringTokenizer.nextToken();
                i++;
            }
            strArr[i] = "";
            return strArr;
        }
        throw new IllegalArgumentException("expecting a pipe-delimited string containing 12 tokens");
    }

    public String getDefaultDateFormatStr() {
        return this.defaultDateFormatStr;
    }

    public String getRecentDateFormatStr() {
        return this.recentDateFormatStr;
    }

    public String getServerLanguageCode() {
        return this.serverLanguageCode;
    }

    public String getServerSystemKey() {
        return this.serverSystemKey;
    }

    public String getServerTimeZoneId() {
        return this.serverTimeZoneId;
    }

    public String getShortMonthNames() {
        return this.shortMonthNames;
    }

    public void setDefaultDateFormatStr(String str) {
        this.defaultDateFormatStr = str;
    }

    public void setRecentDateFormatStr(String str) {
        this.recentDateFormatStr = str;
    }

    public void setServerLanguageCode(String str) {
        this.serverLanguageCode = str;
    }

    public void setServerTimeZoneId(String str) {
        this.serverTimeZoneId = str;
    }

    public void setShortMonthNames(String str) {
        this.shortMonthNames = str;
    }

    public FTPClientConfig() {
        this(SYST_UNIX);
    }

    public FTPClientConfig(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str);
        this.defaultDateFormatStr = str2;
        this.recentDateFormatStr = str3;
        this.serverLanguageCode = str4;
        this.shortMonthNames = str5;
        this.serverTimeZoneId = str6;
    }
}
