package amazon.communication.connection;

import amazon.communication.CommunicationBaseException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class IllegalConnectionStateException extends CommunicationBaseException {
    private int mState;

    @FireOsSdk
    public IllegalConnectionStateException(String str, int i) {
        super(str);
        this.mState = i;
    }

    @FireOsSdk
    public int getState() {
        return this.mState;
    }
}
