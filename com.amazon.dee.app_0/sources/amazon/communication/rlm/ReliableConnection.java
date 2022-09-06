package amazon.communication.rlm;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
/* loaded from: classes.dex */
public interface ReliableConnection extends IConnection {
    void sendReliableMessage(Message message, int i, int i2) throws IllegalConnectionStateException, TransmissionFailedException, MissingCredentialsException;
}
