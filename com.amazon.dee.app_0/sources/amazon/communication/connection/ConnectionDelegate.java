package amazon.communication.connection;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.ResponseHandler;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.ResponseHandlerWrapper;
import amazon.communication.connection.Connection;
import amazon.communication.connection.IConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.communication.ConnectionProxy;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public class ConnectionDelegate implements Connection, IConnection {
    private ConnectionProxy delegate;
    private final Map<Connection.ConnectionListener, ConnectionListenerDelegate> listenerMap = new LinkedHashMap(3);

    public ConnectionDelegate(ConnectionProxy connectionProxy) {
        this.delegate = connectionProxy;
    }

    @Override // amazon.communication.connection.IConnection
    public void addConnectionListener(IConnection.ConnectionListener connectionListener) {
        this.delegate.addConnectionListener(connectionListener);
    }

    public IBinder asBinder() {
        return this.delegate.asBinder();
    }

    @Override // amazon.communication.connection.Connection, amazon.communication.connection.IConnection
    public int getConnectionState() {
        return this.delegate.getConnectionState();
    }

    public void onConnectionSetInitialState(int i) throws RemoteException {
        this.delegate.onConnectionSetInitialState(i);
    }

    public void onConnectionStateChanged(int i, int i2, String str) throws RemoteException {
        this.delegate.onConnectionStateChanged(i, i2, str);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        return this.delegate.onTransact(i, parcel, parcel2, i2);
    }

    @Override // amazon.communication.connection.Connection, amazon.communication.connection.IConnection
    public void release() {
        this.delegate.release();
    }

    @Override // amazon.communication.connection.IConnection
    public void removeConnectionListener(IConnection.ConnectionListener connectionListener) {
        this.delegate.removeConnectionListener(connectionListener);
    }

    @Override // amazon.communication.connection.IConnection
    public void sendMessage(Message message, int i) throws IllegalConnectionStateException, TransmissionFailedException, MissingCredentialsException {
        this.delegate.sendMessage(message, i);
    }

    public void sendReliableMessage(Message message, int i, int i2) throws IllegalConnectionStateException, TransmissionFailedException, MissingCredentialsException {
        this.delegate.sendReliableMessage(message, i, i2);
    }

    @Override // amazon.communication.connection.IConnection
    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandlerBase responseHandlerBase) throws IllegalConnectionStateException, TransmissionFailedException, MissingCredentialsException {
        this.delegate.sendRequest(httpRequestBase, responseHandlerBase);
    }

    public void setConnectionInterface(com.amazon.communication.IConnection iConnection) {
        this.delegate.setConnectionInterface(iConnection);
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

    @Override // amazon.communication.connection.Connection
    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandler responseHandler, MetricEvent metricEvent) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendRequest(httpRequestBase, new ResponseHandlerWrapper(responseHandler));
    }

    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandler responseHandler) throws TransmissionFailedException, IllegalConnectionStateException, MissingCredentialsException {
        this.delegate.sendRequest(httpRequestBase, new ResponseHandlerWrapper(responseHandler));
    }
}
