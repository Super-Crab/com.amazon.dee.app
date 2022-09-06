package com.amazon.deecomms.messaging.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public final class MessageTypes {
    public static final String EVENT_BASE_TYPE = "event";
    public static final String MESSAGE_BASE_TYPE = "message";
    private static final Pattern TYPE_FORMAT = Pattern.compile("^[a-zA-Z-]+/[a-zA-z-]+$");
    public static final Set<String> VALID_MESSAGE_TYPES = new HashSet(Arrays.asList("message/text", "message/contact-invitation", "message/contact-connection-success", "message/audio", "message/media", "event/call", "event/missed-call", "message/shared-content"));
    public static final Set<String> UNREAD_MESSAGE_TYPES = new HashSet(Arrays.asList("message/text", "message/contact-invitation", "message/contact-connection-success", "message/audio", "message/media", "message/shared-content"));
    public static final Set<String> MISSED_CALL_TYPES = new HashSet(Collections.singletonList("event/missed-call"));

    private MessageTypes() {
    }

    public static String getBaseType(String str) {
        if (str == null || !TYPE_FORMAT.matcher(str).matches()) {
            return null;
        }
        return str.split("/")[0];
    }

    public static String getSubType(String str) {
        if (str == null || !TYPE_FORMAT.matcher(str).matches()) {
            return null;
        }
        return str.split("/")[1];
    }
}
