package com.amazon.devicesetup.common.v1;
/* loaded from: classes12.dex */
public class SecurityProtocol {
    public static final String OPEN = "OPEN";
    public static final String OTHER = "OTHER";
    public static final String WEP = "WEP";
    public static final String WPA_PSK = "WPA_PSK";
    private static final String[] values = {WPA_PSK, "WEP", "OPEN", "OTHER"};

    private SecurityProtocol() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
