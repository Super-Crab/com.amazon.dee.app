package amazon.communication.connection;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.ResponseHandlerBase;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public interface IConnection {

    /* loaded from: classes.dex */
    public interface ConnectionListener {
        void onClosed(IConnection iConnection, ConnectionClosedDetails connectionClosedDetails);

        void onOpened(IConnection iConnection);
    }

    void addConnectionListener(ConnectionListener connectionListener);

    int getConnectionState();

    void release();

    void removeConnectionListener(ConnectionListener connectionListener);

    void sendMessage(Message message, int i) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException;

    void sendRequest(HttpRequestBase httpRequestBase, ResponseHandlerBase responseHandlerBase) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException;
}
