package amazon.communication.identity;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public abstract class EndpointIdentity {

    /* loaded from: classes.dex */
    public enum Type {
        DEVICE,
        SERVICE,
        URL
    }

    @FireOsSdk
    public static String logSafe(EndpointIdentity endpointIdentity) {
        if (endpointIdentity != null) {
            return endpointIdentity.toLogSafeString();
        }
        return null;
    }

    @FireOsSdk
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        String endpointIdentity = toString();
        String obj2 = obj.toString();
        if ((endpointIdentity == null) ^ (obj2 == null)) {
            return false;
        }
        return endpointIdentity == null || endpointIdentity.equals(obj2);
    }

    @FireOsSdk
    public abstract Type getType();

    @FireOsSdk
    public int hashCode() {
        return toString().hashCode();
    }

    @FireOsSdk
    public abstract String toLogSafeString();

    @FireOsSdk
    public abstract String toString();
}
