package amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
/* loaded from: classes.dex */
public interface IWakeupConnection extends IConnection {
    void sendMessageWithWakeup(Message message, int i, String str) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException;
}
