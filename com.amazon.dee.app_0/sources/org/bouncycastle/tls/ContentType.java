package org.bouncycastle.tls;
/* loaded from: classes5.dex */
public class ContentType {
    public static final short alert = 21;
    public static final short application_data = 23;
    public static final short change_cipher_spec = 20;
    public static final short handshake = 22;
    public static final short heartbeat = 24;

    public static String getName(short s) {
        switch (s) {
            case 20:
                return "change_cipher_spec";
            case 21:
                return "alert";
            case 22:
                return "handshake";
            case 23:
                return "application_data";
            case 24:
                return "heartbeat";
            default:
                return "UNKNOWN";
        }
    }

    public static String getText(short s) {
        return getName(s) + "(" + ((int) s) + ")";
    }
}
