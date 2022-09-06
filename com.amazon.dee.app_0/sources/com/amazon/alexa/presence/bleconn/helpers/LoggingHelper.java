package com.amazon.alexa.presence.bleconn.helpers;

import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.DateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public final class LoggingHelper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.setDateFormat(DateFormat.getDateTimeInstance(2, 2, Locale.getDefault()));
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private LoggingHelper() {
    }

    public static String dump(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String indent(String str) {
        return indentAfterLinebreaks(DeviceDatabaseUtils.DELIMITER + str);
    }

    public static String indentAfterLinebreaks(String str) {
        return str.replaceAll("\n", "\n\t");
    }
}
