package amazon.communication.srr;

import amazon.communication.ConnectionInterruptedException;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandler;
import amazon.communication.ResponseHandlerWrapper;
import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionClosedDetails;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.communication.ReleaseConnectionResponseHandler;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.fireos.sdk.annotations.HideImplements;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
@HideImplements(interfaces = {IConnection.ConnectionListener.class})
/* loaded from: classes.dex */
public class SrrConnectionListener implements Connection.ConnectionListener, IConnection.ConnectionListener {
    private static final DPLogger log = new DPLogger("TComm.SrrConnectionListener");
    private final long mCreationTime = GlobalTimeSource.INSTANCE.currentTimeMillis();
    private final AtomicBoolean mOnOpenedCalled = new AtomicBoolean(false);
    private final HttpRequestBase mRequest;
    private final ResponseHandler mResponseHandler;
    private final int mTimeoutInMillis;

    @FireOsSdk
    @Deprecated
    public SrrConnectionListener(HttpRequestBase httpRequestBase, ResponseHandler responseHandler, int i, MetricEvent metricEvent) {
        this.mRequest = httpRequestBase;
        this.mResponseHandler = responseHandler;
        this.mTimeoutInMillis = i;
    }

    @Override // amazon.communication.connection.Connection.ConnectionListener
    @FireOsSdk
    @Deprecated
    public void onClosed(Connection connection, ConnectionClosedDetails connectionClosedDetails) {
        onClosed((IConnection) connection, connectionClosedDetails);
    }

    @Override // amazon.communication.connection.Connection.ConnectionListener
    @FireOsSdk
    @Deprecated
    public void onOpened(Connection connection) {
        onOpened((IConnection) connection);
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onClosed(IConnection iConnection, ConnectionClosedDetails connectionClosedDetails) {
        RequestFailedException requestFailedException;
        if (iConnection != null) {
            try {
                log.verbose("onClosed", "releasing connection", new Object[0]);
                iConnection.release();
            } catch (Exception e) {
                log.warn("onClosed", "unexpected exception while releasing connection", e);
            }
        }
        ConnectionInterruptedException connectionInterruptedException = null;
        if (connectionClosedDetails != null && connectionClosedDetails.getDetailsCode() != 0) {
            connectionInterruptedException = new ConnectionInterruptedException(connectionClosedDetails);
        }
        if (connectionInterruptedException != null || !this.mOnOpenedCalled.get()) {
            if (connectionInterruptedException != null) {
                requestFailedException = new RequestFailedException(connectionInterruptedException);
            } else {
                requestFailedException = new RequestFailedException("Connection closed even before onOpened was called");
            }
            this.mResponseHandler.onError(this.mRequest, requestFailedException);
        }
    }

    @Override // amazon.communication.connection.IConnection.ConnectionListener
    public void onOpened(IConnection iConnection) {
        log.verbose("onOpened", "connection opened; about to send request", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()));
        if (!this.mOnOpenedCalled.compareAndSet(false, true)) {
            log.verbose("onOpened", "seems like onOpened was already called for this instance; not firing a duplicate request", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()));
            return;
        }
        int currentTimeMillis = (int) (GlobalTimeSource.INSTANCE.currentTimeMillis() - this.mCreationTime);
        int i = this.mTimeoutInMillis - currentTimeMillis;
        if (i < 1) {
            DPFormattedMessage dPFormattedMessage = new DPFormattedMessage("onOpened", "no time left to make the actual request", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "mCreationTime", Long.valueOf(this.mCreationTime), "connectionEstablishmentTime", Integer.valueOf(currentTimeMillis), "mTimeoutInMillis", Integer.valueOf(this.mTimeoutInMillis));
            log.verbose(dPFormattedMessage);
            iConnection.release();
            this.mResponseHandler.onError(this.mRequest, new RequestFailedException(dPFormattedMessage.toString()));
            return;
        }
        BasicHttpParams params = this.mRequest.getParams();
        if (params == null) {
            params = new BasicHttpParams();
        }
        HttpConnectionParams.setConnectionTimeout(params, i);
        HttpConnectionParams.setSoTimeout(params, i);
        this.mRequest.setParams(params);
        try {
            iConnection.sendRequest(this.mRequest, new ReleaseConnectionResponseHandler(iConnection, new ResponseHandlerWrapper(this.mResponseHandler)));
        } catch (MissingCredentialsException e) {
            log.verbose("onOpened", ADMRegistrationConstants.CALL_EXCEPTION, "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), e);
            this.mResponseHandler.onError(this.mRequest, new RequestFailedException("No Amazon account on the device", e));
        } catch (IllegalConnectionStateException e2) {
            log.verbose("onOpened", ADMRegistrationConstants.CALL_EXCEPTION, "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), e2);
            this.mResponseHandler.onError(this.mRequest, new RequestFailedException("Illegal connection state when making http request", e2));
        } catch (TransmissionFailedException e3) {
            log.verbose("onOpened", ADMRegistrationConstants.CALL_EXCEPTION, "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), e3);
            this.mResponseHandler.onError(this.mRequest, new RequestFailedException("Cannot send data", e3));
        }
    }
}
