package amazon.communication;

import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionClosedDetails;
import amazon.communication.connection.ConnectionDelegate;
import amazon.communication.connection.IConnection;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.communication.ConnectionProxy;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.fireos.sdk.annotations.HideImplements;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@HideImplements(interfaces = {IConnection.ConnectionListener.class})
/* loaded from: classes.dex */
public class BlockingConnectionListener implements Connection.ConnectionListener, IConnection.ConnectionListener {
    private static final DPLogger log = new DPLogger("TComm.BlockingConnectionListener");
    private ConnectionClosedDetails mConnectionClosedDetails;
    private final Connection.ConnectionListener mInnerListener;
    private final Lock mLock = new ReentrantLock();
    private final Condition mOpened = this.mLock.newCondition();
    private final int mTimeout;

    @FireOsSdk
    public BlockingConnectionListener(Connection.ConnectionListener connectionListener, int i) {
        this.mTimeout = i;
        this.mInnerListener = connectionListener;
    }

    @Override // amazon.communication.connection.Connection.ConnectionListener
    @FireOsSdk
    public void onClosed(Connection connection, ConnectionClosedDetails connectionClosedDetails) {
        this.mLock.lock();
        try {
            this.mConnectionClosedDetails = connectionClosedDetails;
            this.mOpened.signal();
            this.mLock.unlock();
            Connection.ConnectionListener connectionListener = this.mInnerListener;
            if (connectionListener == null) {
                return;
            }
            connectionListener.onClosed(connection, connectionClosedDetails);
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
    }

    @Override // amazon.communication.connection.Connection.ConnectionListener
    @FireOsSdk
    public void onOpened(Connection connection) {
        this.mLock.lock();
        try {
            this.mOpened.signal();
            this.mLock.unlock();
            Connection.ConnectionListener connectionListener = this.mInnerListener;
            if (connectionListener == null) {
                return;
            }
            connectionListener.onOpened(connection);
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
    }

    @FireOsSdk
    public void waitForConnectionOpen(Connection connection) throws InterruptedException, ConnectionAcquisitionFailedException, TimeoutException {
        waitForConnectionOpen(connection, null);
    }

    @FireOsSdk
    @Deprecated
    public void waitForConnectionOpen(Connection connection, MetricEvent metricEvent) throws TimeoutException, InterruptedException, ConnectionAcquisitionFailedException {
        this.mLock.lock();
        try {
            int connectionState = connection.getConnectionState();
            long currentTimeMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
            while (true) {
                if (connectionState != 0 && connectionState != 1) {
                    if (connectionState == 2) {
                        return;
                    }
                    if (connectionState != 3 && connectionState != 4) {
                        throw new IllegalStateException(connectionState + " is not a valid connection state");
                    }
                    if (this.mConnectionClosedDetails != null) {
                        throw new ConnectionAcquisitionFailedException(this.mConnectionClosedDetails.getMessage());
                    }
                    throw new ConnectionAcquisitionFailedException("Cannot aquire connection.  Connnection is already closed.");
                }
                if (this.mOpened.await(this.mTimeout - (GlobalTimeSource.INSTANCE.currentTimeMillis() - currentTimeMillis), TimeUnit.MILLISECONDS)) {
                    GlobalTimeSource.INSTANCE.currentTimeMillis();
                    connectionState = connection.getConnectionState();
                } else {
                    log.verbose("waitForConnectionOpen", "setup connection timed out", "Thread.currentThread().getId()", Long.valueOf(Thread.currentThread().getId()), "mTimeout", Integer.valueOf(this.mTimeout));
                    throw new TimeoutException("Setup connection timed out");
                }
            }
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onOpened(IConnection iConnection) {
        onOpened((Connection) new ConnectionDelegate((ConnectionProxy) iConnection));
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onClosed(IConnection iConnection, ConnectionClosedDetails connectionClosedDetails) {
        onClosed((Connection) new ConnectionDelegate((ConnectionProxy) iConnection), connectionClosedDetails);
    }
}
