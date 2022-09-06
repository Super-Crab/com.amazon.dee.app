package amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.ResponseHandler;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.ResponseHandlerWrapper;
import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionListenerDelegate;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.communication.devicetodevice.WakeupConnectionImpl;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public class WakeupConnectionDelegate implements WakeupConnection {
    private WakeupConnectionImpl delegate;
    private final Map<Connection.ConnectionListener, ConnectionListenerDelegate> listenerMap = new LinkedHashMap(3);

    public WakeupConnectionDelegate(WakeupConnectionImpl wakeupConnectionImpl) {
        this.delegate = wakeupConnectionImpl;
    }

    public void addConnectionListener(IConnection.ConnectionListener connectionListener) {
        this.delegate.addConnectionListener(connectionListener);
    }

    @Override // amazon.communication.connection.Connection, amazon.communication.connection.IConnection
    public int getConnectionState() {
        return this.delegate.getConnectionState();
    }

    @Override // amazon.communication.connection.Connection, amazon.communication.connection.IConnection
    public void release() {
        this.delegate.release();
    }

    public void removeConnectionListener(IConnection.ConnectionListener connectionListener) {
        this.delegate.removeConnectionListener(connectionListener);
    }

    public void sendMessage(Message message, int i) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendMessage(message, i);
    }

    public void sendMessageWithWakeup(Message message, int i, String str) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendMessageWithWakeup(message, i, str);
    }

    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandlerBase responseHandlerBase) {
        this.delegate.sendRequest(httpRequestBase, responseHandlerBase);
    }

    @Override // amazon.communication.connection.Connection
    public void addConnectionListener(Connection.ConnectionListener connectionListener) {
        ConnectionListenerDelegate connectionListenerDelegate = new ConnectionListenerDelegate(connectionListener);
        this.listenerMap.put(connectionListener, connectionListenerDelegate);
        this.delegate.addConnectionListener(connectionListenerDelegate);
    }

    @Override // amazon.communication.connection.Connection
    public void removeConnectionListener(Connection.ConnectionListener connectionListener) {
        ConnectionListenerDelegate connectionListenerDelegate = this.listenerMap.get(connectionListener);
        if (connectionListenerDelegate != null) {
            this.delegate.removeConnectionListener(connectionListenerDelegate);
        }
    }

    @Override // amazon.communication.connection.Connection
    public void sendMessage(Message message, int i, MetricEvent metricEvent) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendMessage(message, i);
    }

    @Override // amazon.communication.devicetodevice.WakeupConnection
    public void sendMessageWithWakeup(Message message, int i, MetricEvent metricEvent, String str) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendMessageWithWakeup(message, i, str);
    }

    @Override // amazon.communication.connection.Connection
    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandler responseHandler, MetricEvent metricEvent) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendRequest(httpRequestBase, new ResponseHandlerWrapper(responseHandler));
    }

    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandler responseHandler) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendRequest(httpRequestBase, new ResponseHandlerWrapper(responseHandler));
    }
}
