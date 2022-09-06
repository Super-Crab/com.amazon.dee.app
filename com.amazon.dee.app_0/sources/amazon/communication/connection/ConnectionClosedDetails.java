package amazon.communication.connection;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class ConnectionClosedDetails {
    @FireOsSdk
    public static final int AUTHENTICATION_ERROR = 1;
    @FireOsSdk
    public static final int CONNECTION_FAILURE_RECONNECTING = 2;
    @FireOsSdk
    public static final int RECONNECT_TERMINATED = 3;
    @FireOsSdk
    public static final int UNKNOWN = 0;
    private final int mDetailsCode;
    private final String mMessage;

    @FireOsSdk
    public ConnectionClosedDetails(int i, String str) {
        this.mDetailsCode = i;
        this.mMessage = str;
    }

    @FireOsSdk
    public int getDetailsCode() {
        return this.mDetailsCode;
    }

    @FireOsSdk
    public String getMessage() {
        return this.mMessage;
    }

    @FireOsSdk
    public String toString() {
        return String.format("detailsCode:%d, message:%s", Integer.valueOf(this.mDetailsCode), this.mMessage);
    }
}
