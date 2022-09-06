package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.mobilytics.configuration.Constants;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public class TtaMessageSanitizer {
    private static final String ALEXA_PATTERN = "(?i)\\w*\\b(hi|hello|hey)\\p{Space}*\\p{Punct}*\\p{Space}*alexa\\p{Punct}*";
    private static final int ALLOWED_LENGTH = 1000;
    private static final String DATE_PATTERN = "\\?{4}\\d{4}";
    private static final String MESSAGE_SANITIZATION_PATTERN = "[^A-Za-z0-9!\\\"#$%&'()*÷+,-.\\/:;<=>?@\\[\\\\\\]\\^_`{\\|}~\\p{Space}]+";
    private static final String RESPONSE_SANITIZATION_PATTERN = "[\\^\\_]+";
    private static final String SHORT_AUDIO_PATTERN = "\\w*\\<(S|s)hort\\p{Space}+(A|a)udio>";

    private String getDaySuffix(int i) {
        if (i < 11 || i > 13) {
            int i2 = i % 10;
            return i2 != 1 ? i2 != 2 ? i2 != 3 ? "th" : "rd" : "nd" : Constants.TIMELINE_START_TIME_KEY;
        }
        return "th";
    }

    private String getMonth(int i, int i2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM", Locale.US);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(2, i - 1);
        gregorianCalendar.set(5, i2);
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }

    private String hackSanitizeForPhysicalShoppingResponse(String str) {
        int intValue;
        Matcher matcher = Pattern.compile(DATE_PATTERN).matcher(str);
        if (matcher.find()) {
            int end = matcher.end();
            int i = end - 2;
            int intValue2 = Integer.valueOf(str.substring(end - 4, i)).intValue();
            return hackSanitizeForPhysicalShoppingResponse(str.substring(0, end - 8) + getMonth(intValue2, intValue) + " " + Integer.valueOf(str.substring(i, end)).intValue() + getDaySuffix(intValue) + str.substring(end));
        }
        return str;
    }

    private String prepareRequestForSending(String str) {
        Matcher matcher = Pattern.compile(ALEXA_PATTERN).matcher(str);
        if (matcher.matches()) {
            String group = matcher.group(1);
            int length = group.length() + matcher.start();
            int end = matcher.end();
            return str.substring(0, length) + str.substring(end);
        }
        return str;
    }

    private String sanitizeMessage(String str) {
        String trim = Pattern.compile(MESSAGE_SANITIZATION_PATTERN).matcher(str).replaceAll("").trim();
        return trim.substring(0, Math.min(trim.length(), 1000));
    }

    public String sanitizeRequest(String str) {
        return prepareRequestForSending(sanitizeMessage(str));
    }

    public String sanitizeResponse(String str) {
        return hackSanitizeForPhysicalShoppingResponse(sanitizeMessage(str).trim().replaceAll(RESPONSE_SANITIZATION_PATTERN, "").replaceAll(SHORT_AUDIO_PATTERN, ""));
    }
}
