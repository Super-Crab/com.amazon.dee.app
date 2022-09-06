package amazon.communication.connection;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.ResponseHandler;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public interface Connection {

    /* loaded from: classes.dex */
    public interface ConnectionListener {
        @FireOsSdk
        void onClosed(Connection connection, ConnectionClosedDetails connectionClosedDetails);

        @FireOsSdk
        void onOpened(Connection connection);
    }

    @FireOsSdk
    void addConnectionListener(ConnectionListener connectionListener);

    @FireOsSdk
    int getConnectionState();

    @FireOsSdk
    void release();

    @FireOsSdk
    void removeConnectionListener(ConnectionListener connectionListener);

    @FireOsSdk
    @Deprecated
    void sendMessage(Message message, int i, MetricEvent metricEvent) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException;

    @FireOsSdk
    @Deprecated
    void sendRequest(HttpRequestBase httpRequestBase, ResponseHandler responseHandler, MetricEvent metricEvent) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException;
}
