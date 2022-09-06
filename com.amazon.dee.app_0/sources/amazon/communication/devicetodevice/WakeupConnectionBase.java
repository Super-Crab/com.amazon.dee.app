package amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface WakeupConnectionBase {
    @FireOsSdk
    void sendMessageWithWakeup(Message message, int i, String str) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException;
}
