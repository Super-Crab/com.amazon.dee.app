package amazon.communication.connection;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public final class ConnectionState {
    @FireOsSdk
    public static final int CONNECTION_STATE_CLOSED = 4;
    @FireOsSdk
    public static final int CONNECTION_STATE_CLOSING = 3;
    private static final int CONNECTION_STATE_FIRST = 0;
    private static final int CONNECTION_STATE_LAST = 4;
    @FireOsSdk
    public static final int CONNECTION_STATE_OPEN = 2;
    @FireOsSdk
    public static final int CONNECTION_STATE_OPENING = 1;
    @FireOsSdk
    public static final int CONNECTION_STATE_UNDEFINED = 0;

    private ConnectionState() {
    }

    public static boolean isValidState(int i) {
        return i >= 0 && i <= 4;
    }
}
