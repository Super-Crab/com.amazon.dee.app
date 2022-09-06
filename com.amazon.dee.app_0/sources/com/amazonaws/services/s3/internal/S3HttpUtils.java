package com.amazonaws.services.s3.internal;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes13.dex */
public final class S3HttpUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Pattern ENCODED_CHARACTERS_PATTERN = Pattern.compile(Pattern.quote("+") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("*") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%7E") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%2F") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%3A") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%27") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%28") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%29") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%21") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%5B") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%5D") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%24"));

    public static String urlDecode(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlEncode(String str, boolean z) {
        if (str == null) {
            return "";
        }
        try {
            String encode = URLEncoder.encode(str, "UTF-8");
            Matcher matcher = ENCODED_CHARACTERS_PATTERN.matcher(encode);
            StringBuffer stringBuffer = new StringBuffer(encode.length());
            while (matcher.find()) {
                String group = matcher.group(0);
                if ("+".equals(group)) {
                    group = " ";
                } else if ("*".equals(group)) {
                    group = "%2A";
                } else if ("%7E".equals(group)) {
                    group = "~";
                } else if (z && "%2F".equals(group)) {
                    group = "/";
                } else if (z && "%3A".equals(group)) {
                    group = ":";
                } else if (z && "%27".equals(group)) {
                    group = "'";
                } else if (z && "%28".equals(group)) {
                    group = "(";
                } else if (z && "%29".equals(group)) {
                    group = ")";
                } else if (z && "%21".equals(group)) {
                    group = "!";
                } else if (z && "%5B".equals(group)) {
                    group = "[";
                } else if (z && "%5D".equals(group)) {
                    group = "]";
                }
                matcher.appendReplacement(stringBuffer, group);
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
