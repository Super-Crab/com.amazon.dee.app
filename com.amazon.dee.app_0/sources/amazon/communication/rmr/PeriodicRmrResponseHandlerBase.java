package amazon.communication.rmr;

import amazon.communication.Message;
import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
@Deprecated
/* loaded from: classes.dex */
public abstract class PeriodicRmrResponseHandlerBase implements RmrResponseHandler {
    private static final DPLogger log = new DPLogger("TComm.PeriodicRmrResponseHandlerBase");
    private static final ScheduledExecutorService sScheduleExecutor = Executors.newSingleThreadScheduledExecutor();
    private final int mNotificationPeriod;
    private final RmrResponseHandler mRmrResponseHandler;
    private ScheduledFuture<?> mScheduledFuture;
    private boolean mIsFinished = false;
    private boolean mHasReceivedEndOfResponses = false;

    @FireOsSdk
    @Deprecated
    public PeriodicRmrResponseHandlerBase(RmrResponseHandler rmrResponseHandler, int i) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    private synchronized void finish() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    private synchronized void startTimer(RmrRequest rmrRequest) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    private synchronized void stopTimer() {
        throw new UnsupportedOperationException();
    }

    @FireOsSdk
    @Deprecated
    public int getNotificationPeriod() {
        throw new UnsupportedOperationException();
    }

    @FireOsSdk
    @Deprecated
    public synchronized boolean hasReachedEndOfResponses() {
        throw new UnsupportedOperationException();
    }

    @FireOsSdk
    @Deprecated
    public synchronized boolean isFinished() {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.rmr.RmrResponseHandler
    @FireOsSdk
    @Deprecated
    public final synchronized void onError(RmrRequest rmrRequest, RmrResponseException rmrResponseException) {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.rmr.RmrResponseHandler
    @FireOsSdk
    @Deprecated
    public final synchronized void onFinish(RmrRequest rmrRequest) {
        throw new UnsupportedOperationException();
    }

    @FireOsSdk
    @Deprecated
    public abstract void onPeriodicNotification(RmrRequest rmrRequest);

    @Override // amazon.communication.rmr.RmrResponseHandler
    @FireOsSdk
    @Deprecated
    public final synchronized void onResponse(RmrRequest rmrRequest, Message message) {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.rmr.RmrResponseHandler
    @FireOsSdk
    @Deprecated
    public void onStart(RmrRequest rmrRequest) {
        throw new UnsupportedOperationException();
    }
}
