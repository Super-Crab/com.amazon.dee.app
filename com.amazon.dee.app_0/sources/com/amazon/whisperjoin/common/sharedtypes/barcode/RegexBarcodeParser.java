package com.amazon.whisperjoin.common.sharedtypes.barcode;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BarcodeFormatException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes13.dex */
public final class RegexBarcodeParser implements BarcodeParser {
    private static final String ABV_PATTERN = "(IB[0-9]{2})";
    private static final String AMAZON_BARCODE_VERSION = "ABV";
    private static final String COLON_SUFFIX = ":";
    private static final String PID = "PID";
    private static final String PID_PATTERN = "([a-zA-Z0-9]{4})";
    private static final String PIN = "PIN";
    private static final String PIN_PATTERN = "([a-zA-Z0-9]{8,12})";
    private static final String PUBLIC_KEY = "PUK";
    private static final String PUK_PATTERN = "([-A-Za-z0-9+/=]+={0,3})";
    private static final String SEMICOLON_SUFFIX = ";";

    private Map getMapFromBarcodeString(String str) throws BarcodeFormatException {
        String replaceAll = str.replaceAll(SEMICOLON_SUFFIX, "\n").replaceAll(":", Config.Compare.EQUAL_TO);
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(replaceAll));
            return new HashMap(properties);
        } catch (IOException unused) {
            throw new BarcodeFormatException(GeneratedOutlineSupport1.outline72("failed to split the barcode data as format did not match: ", str));
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.barcode.BarcodeParser
    public BarcodeDetails parse(String str) throws BarcodeFormatException {
        if (str != null && !str.isEmpty()) {
            Map mapFromBarcodeString = getMapFromBarcodeString(str);
            String str2 = (String) mapFromBarcodeString.get(AMAZON_BARCODE_VERSION);
            String str3 = (String) mapFromBarcodeString.get(PID);
            String str4 = (String) mapFromBarcodeString.get(PUBLIC_KEY);
            String str5 = (String) mapFromBarcodeString.get(PIN);
            if (!StringUtils.isEmpty(str5) && Pattern.matches(PIN_PATTERN, str5) && !StringUtils.isEmpty(str2) && Pattern.matches(ABV_PATTERN, str2) && !StringUtils.isEmpty(str3) && Pattern.matches(PID_PATTERN, str3) && !StringUtils.isEmpty(str4) && Pattern.matches(PUK_PATTERN, str4)) {
                return new BarcodeDetails(str2, str3, str4, str5);
            }
            throw new BarcodeFormatException(str);
        }
        throw new IllegalArgumentException("barcodeContent cannot be null or empty.");
    }
}
