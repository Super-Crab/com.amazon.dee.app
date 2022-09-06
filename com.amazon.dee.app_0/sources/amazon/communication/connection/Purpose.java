package amazon.communication.connection;

import com.amazon.communication.utils.StringUtils;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public final class Purpose {
    private final String mPurpose;
    @FireOsSdk
    public static final Purpose REGULAR = new Purpose("Regular");
    @FireOsSdk
    public static final Purpose HEARTBEAT_PROBING = new Purpose("HeartbeatProbing");
    @FireOsSdk
    public static final Purpose D2D_MESSAGING = new Purpose("PeerToPeerDeviceMessaging");

    @FireOsSdk
    public Purpose(String str) {
        if (!StringUtils.isNullOrEmpty(str)) {
            this.mPurpose = str;
            return;
        }
        throw new IllegalArgumentException("purpose must not be empty");
    }

    @FireOsSdk
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof Purpose)) {
            return this.mPurpose.equals(((Purpose) obj).mPurpose);
        }
        return false;
    }

    @FireOsSdk
    public String getPurpose() {
        return this.mPurpose;
    }

    @FireOsSdk
    public int hashCode() {
        return this.mPurpose.hashCode();
    }

    @FireOsSdk
    public String toString() {
        return this.mPurpose;
    }
}
