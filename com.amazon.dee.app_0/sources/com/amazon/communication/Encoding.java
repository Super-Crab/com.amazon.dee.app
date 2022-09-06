package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public enum Encoding {
    GZIP;
    
    private static final String COMMA = ",";
    private static final String SUPPORTED_ENCODING_NAMES_STRING = "GZIP";
    private static final DPLogger log = new DPLogger("TComm.Encoding");

    public static Encoding chooseFirstSupportedEncoding(String str) {
        if (str != null && str.trim().length() != 0) {
            String[] split = str.split(",");
            Encoding encoding = null;
            for (int i = 0; i < split.length && (encoding = getEncodingFromName(split[i])) == null; i++) {
            }
            return encoding;
        }
        throw new IllegalArgumentException("Encoding name string cannot be null or empty.");
    }

    public static String getAllSupportedEncodings() {
        return SUPPORTED_ENCODING_NAMES_STRING;
    }

    public static Encoding getEncodingFromName(String str) {
        if (str != null && str.trim().length() != 0) {
            try {
                return (Encoding) Enum.valueOf(Encoding.class, str);
            } catch (IllegalArgumentException unused) {
                log.info("getEncodingFromName", "encoding type can't be found", "encodingName", str);
                return null;
            }
        }
        throw new IllegalArgumentException("Encoding name cannot be null or empty");
    }
}
