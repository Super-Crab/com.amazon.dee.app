package amazon.communication;

import amazon.communication.connection.ConnectionClosedDetails;
import amazon.communication.connection.IConnection;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public class BlockingConnectionListenerBase implements IConnection.ConnectionListener {
    private static final DPLogger log = new DPLogger("TComm.BlockingConnectionListenerBase");
    private ConnectionClosedDetails mConnectionClosedDetails;
    private final IConnection.ConnectionListener mInnerListener;
    private final Lock mLock = new ReentrantLock();
    private final Condition mOpened = this.mLock.newCondition();
    private final int mTimeout;

    public BlockingConnectionListenerBase(IConnection.ConnectionListener connectionListener, int i) {
        this.mTimeout = i;
        this.mInnerListener = connectionListener;
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onClosed(IConnection iConnection, ConnectionClosedDetails connectionClosedDetails) {
        this.mLock.lock();
        try {
            this.mConnectionClosedDetails = connectionClosedDetails;
            this.mOpened.signal();
            this.mLock.unlock();
            IConnection.ConnectionListener connectionListener = this.mInnerListener;
            if (connectionListener == null) {
                return;
            }
            connectionListener.onClosed(iConnection, connectionClosedDetails);
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onOpened(IConnection iConnection) {
        this.mLock.lock();
        try {
            this.mOpened.signal();
            this.mLock.unlock();
            IConnection.ConnectionListener connectionListener = this.mInnerListener;
            if (connectionListener == null) {
                return;
            }
            connectionListener.onOpened(iConnection);
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
    }

    public void waitForConnectionOpen(IConnection iConnection) throws TimeoutException, InterruptedException, ConnectionAcquisitionFailedException {
        this.mLock.lock();
        try {
            int connectionState = iConnection.getConnectionState();
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
                    connectionState = iConnection.getConnectionState();
                } else {
                    log.verbose("waitForConnectionOpen", "setup connection timed out", "Thread.currentThread().getId()", Long.valueOf(Thread.currentThread().getId()), "mTimeout", Integer.valueOf(this.mTimeout));
                    throw new TimeoutException("Setup connection timed out");
                }
            }
        } finally {
            this.mLock.unlock();
        }
    }
}
