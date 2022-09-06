package com.amazon.communication.connection;

import amazon.communication.connection.ConnectionClosedDetails;
import amazon.communication.connection.IConnection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public abstract class ConnectionBase implements IConnection {
    protected int mConnectionState;
    protected Set<IConnection.ConnectionListener> mListeners = Collections.synchronizedSet(new HashSet(1));

    private void notifyStateClosed(int i, String str) {
        synchronized (this.mListeners) {
            for (IConnection.ConnectionListener connectionListener : this.mListeners) {
                connectionListener.onClosed(this, new ConnectionClosedDetails(i, str));
            }
        }
    }

    private void notifyStateOpened() {
        synchronized (this.mListeners) {
            for (IConnection.ConnectionListener connectionListener : this.mListeners) {
                connectionListener.onOpened(this);
            }
        }
    }

    @Override // amazon.communication.connection.IConnection
    public void addConnectionListener(IConnection.ConnectionListener connectionListener) {
        if (connectionListener != null) {
            this.mListeners.add(connectionListener);
            return;
        }
        throw new IllegalArgumentException("Connection listener cannot be null");
    }

    @Override // amazon.communication.connection.IConnection
    public int getConnectionState() {
        return this.mConnectionState;
    }

    @Override // amazon.communication.connection.IConnection
    public void release() {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.connection.IConnection
    public void removeConnectionListener(IConnection.ConnectionListener connectionListener) {
        if (connectionListener != null) {
            if (this.mListeners.contains(connectionListener)) {
                this.mListeners.remove(connectionListener);
                return;
            }
            throw new IllegalArgumentException("Connection listener isn't registered to connection");
        }
        throw new IllegalArgumentException("Connection listener cannot be null");
    }

    public void setConnectionState(int i) {
        int i2 = this.mConnectionState;
        synchronized (this) {
            this.mConnectionState = i;
        }
        if (i2 != 4 && i == 4) {
            notifyStateClosed(0, null);
        } else if (i2 == 2 || i != 2) {
        } else {
            notifyStateOpened();
        }
    }
}
