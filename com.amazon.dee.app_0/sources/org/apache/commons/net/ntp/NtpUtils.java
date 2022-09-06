package org.apache.commons.net.ntp;
/* loaded from: classes4.dex */
public final class NtpUtils {
    public static String getHostAddress(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append((i >>> 24) & 255);
        stringBuffer.append(".");
        stringBuffer.append((i >>> 16) & 255);
        stringBuffer.append(".");
        stringBuffer.append((i >>> 8) & 255);
        stringBuffer.append(".");
        stringBuffer.append((i >>> 0) & 255);
        return stringBuffer.toString();
    }

    public static String getModeName(int i) {
        switch (i) {
            case 0:
                return "Reserved";
            case 1:
                return "Symmetric Active";
            case 2:
                return "Symmetric Passive";
            case 3:
                return "Client";
            case 4:
                return "Server";
            case 5:
                return "Broadcast";
            case 6:
                return "Control";
            case 7:
                return "Private";
            default:
                return "Unknown";
        }
    }

    public static String getRefAddress(NtpV3Packet ntpV3Packet) {
        return getHostAddress(ntpV3Packet == null ? 0 : ntpV3Packet.getReferenceId());
    }

    public static String getReferenceClock(NtpV3Packet ntpV3Packet) {
        int referenceId;
        if (ntpV3Packet == null || (referenceId = ntpV3Packet.getReferenceId()) == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(4);
        for (int i = 24; i >= 0; i -= 8) {
            char c = (char) ((referenceId >>> i) & 255);
            if (c == 0) {
                break;
            } else if (!Character.isLetterOrDigit(c)) {
                return "";
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }
}
