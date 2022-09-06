package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class ServiceSideResponseHandlerProxy implements ServiceSideResponseHandler, IBinder.DeathRecipient {
    private static final String METRICS_SOURCE_NAME = "ServiceSideResponseHandlerProxy";
    private static final DPLogger log = new DPLogger("TComm.ServiceSideResponseHandlerProxy");
    private final BandwidthToolByteAccountant mByteAccountant;
    private final int mCallerUid;
    private MetricEvent mMetricEvent;
    private final PeriodicMetricReporter mPeriodicMetricReporter;
    private int mRegisteredChannel = -1;
    private IResponseHandler mResponseHandler;
    private final ResponseRouter mResponseRouter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceSideResponseHandlerProxy(IResponseHandler iResponseHandler, ResponseRouter responseRouter, PeriodicMetricReporter periodicMetricReporter, BandwidthToolByteAccountant bandwidthToolByteAccountant) throws RemoteException {
        this.mResponseHandler = iResponseHandler;
        this.mResponseRouter = responseRouter;
        this.mByteAccountant = bandwidthToolByteAccountant;
        this.mPeriodicMetricReporter = periodicMetricReporter;
        this.mMetricEvent = periodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
        IBinder asBinder = iResponseHandler.asBinder();
        this.mCallerUid = Binder.getCallingUid();
        log.verbose("constructor", "the calling uid at creation of this response handler", "mCallerUid", Integer.valueOf(this.mCallerUid));
        try {
            asBinder.linkToDeath(this, 0);
        } catch (RemoteException e) {
            binderDied();
            throw e;
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public synchronized void binderDied() {
        if (this.mRegisteredChannel != -1) {
            this.mResponseRouter.unregisterResponseHandler(this.mRegisteredChannel);
            this.mRegisteredChannel = -1;
        }
        this.mResponseHandler = null;
    }

    public synchronized IResponseHandler getResponseHandler() {
        return this.mResponseHandler;
    }

    @Override // com.amazon.communication.ServiceSideResponseHandler
    public void onResponse(EndpointIdentity endpointIdentity, Message message, int i) {
        this.mResponseRouter.unregisterResponseHandler(i);
        int payloadSize = message.getPayloadSize();
        synchronized (this) {
            if (this.mResponseHandler == null) {
                log.warn("onResponse", "client-side callback object is now stale", new Object[0]);
                this.mMetricEvent.addCounter(TCommMetrics.COUNT_SRR_SERVICE_SIDE_FAILURE, 1.0d);
                this.mMetricEvent.addCounter(TCommMetrics.COUNT_TOTAL_RX_UNACCOUNTED_BYTES, payloadSize);
                return;
            }
            IResponseHandler iResponseHandler = this.mResponseHandler;
            this.mMetricEvent.addCounter(TCommMetrics.COUNT_SRR_SERVICE_SIDE_SUCCESS, 1.0d);
            if (payloadSize != -1) {
                this.mByteAccountant.accountBytesReceived(this.mCallerUid, payloadSize);
            } else {
                log.warn("onResponse", "Cannot determine payload size of message. Possible bug", "message's class name", message.getClass().getName(), "mCallerUid", Integer.valueOf(this.mCallerUid));
            }
            try {
                iResponseHandler.onResponse(MessageEnvelope.createInstance(message));
                iResponseHandler.asBinder().unlinkToDeath(this, 0);
                synchronized (this) {
                    this.mResponseHandler = null;
                }
            } catch (RemoteException e) {
                log.warn("onResponse", "client-side callback object is now stale", e);
            } catch (RuntimeException e2) {
                log.warn("onResponse", "RuntimeException caught from callback", e2);
            }
        }
    }

    public void setRegisteredChannel(int i) {
        int i2 = this.mRegisteredChannel;
        if (i2 == -1) {
            this.mRegisteredChannel = i;
        } else {
            log.error("setRegisteredChannel", "cannot set a new registered channel after a channel has already been set", "registeredChannel", Integer.valueOf(i2), "newChannel", Integer.valueOf(i));
            throw new IllegalStateException("Cannot set a new registered channel after a channel has already been set");
        }
    }
}
