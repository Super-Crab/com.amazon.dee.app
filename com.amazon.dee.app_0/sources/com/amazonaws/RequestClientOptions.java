package com.amazonaws;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumMap;
import java.util.Map;
/* loaded from: classes13.dex */
public final class RequestClientOptions {
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 131073;
    private static final int STREAM_BUFFER_SHIFT_VAL = 17;
    private final Map<Marker, String> markers = new EnumMap(Marker.class);

    /* loaded from: classes13.dex */
    public enum Marker {
        USER_AGENT
    }

    private String createUserAgentMarkerString(String str, String str2) {
        return str.contains(str2) ? str : GeneratedOutlineSupport1.outline75(str, " ", str2);
    }

    @Deprecated
    public void addClientMarker(String str) {
        appendUserAgent(str);
    }

    public void appendUserAgent(String str) {
        String str2 = this.markers.get(Marker.USER_AGENT);
        if (str2 == null) {
            str2 = "";
        }
        putClientMarker(Marker.USER_AGENT, createUserAgentMarkerString(str2, str));
    }

    @Deprecated
    public String getClientMarker() {
        return getClientMarker(Marker.USER_AGENT);
    }

    public void putClientMarker(Marker marker, String str) {
        this.markers.put(marker, str);
    }

    public String getClientMarker(Marker marker) {
        return this.markers.get(marker);
    }
}
