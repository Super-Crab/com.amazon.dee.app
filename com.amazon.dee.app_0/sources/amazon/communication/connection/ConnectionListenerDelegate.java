package amazon.communication.connection;

import amazon.communication.connection.Connection;
import amazon.communication.connection.IConnection;
import com.amazon.communication.ConnectionProxy;
/* loaded from: classes.dex */
public class ConnectionListenerDelegate implements Connection.ConnectionListener, IConnection.ConnectionListener {
    Connection.ConnectionListener delegate;

    public ConnectionListenerDelegate(Connection.ConnectionListener connectionListener) {
        this.delegate = connectionListener;
    }

    @Override // amazon.communication.connection.Connection.ConnectionListener
    public void onClosed(Connection connection, ConnectionClosedDetails connectionClosedDetails) {
        this.delegate.onClosed(connection, connectionClosedDetails);
    }

    @Override // amazon.communication.connection.Connection.ConnectionListener
    public void onOpened(Connection connection) {
        this.delegate.onOpened(connection);
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onClosed(IConnection iConnection, ConnectionClosedDetails connectionClosedDetails) {
        Connection.ConnectionListener connectionListener = this.delegate;
        if (connectionListener != null) {
            connectionListener.onClosed(new ConnectionDelegate((ConnectionProxy) iConnection), connectionClosedDetails);
        }
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onOpened(IConnection iConnection) {
        Connection.ConnectionListener connectionListener = this.delegate;
        if (connectionListener != null) {
            connectionListener.onOpened(new ConnectionDelegate((ConnectionProxy) iConnection));
        }
    }
}
