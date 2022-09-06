package javax.mail.internet;

import java.util.concurrent.atomic.AtomicInteger;
import javax.mail.Session;
/* loaded from: classes3.dex */
class UniqueValue {
    private static AtomicInteger id = new AtomicInteger();

    UniqueValue() {
    }

    public static String getUniqueBoundaryValue() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("----=_Part_");
        stringBuffer.append(id.getAndIncrement());
        stringBuffer.append("_");
        stringBuffer.append(stringBuffer.hashCode());
        stringBuffer.append('.');
        stringBuffer.append(System.currentTimeMillis());
        return stringBuffer.toString();
    }

    public static String getUniqueMessageIDValue(Session session) {
        InternetAddress localAddress = InternetAddress.getLocalAddress(session);
        String address = localAddress != null ? localAddress.getAddress() : "javamailuser@localhost";
        int lastIndexOf = address.lastIndexOf(64);
        if (lastIndexOf >= 0) {
            address = address.substring(lastIndexOf);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringBuffer.hashCode());
        stringBuffer.append('.');
        stringBuffer.append(id.getAndIncrement());
        stringBuffer.append('.');
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append(address);
        return stringBuffer.toString();
    }
}
