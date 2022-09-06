package amazon.speech.simclient.common.queue;

import amazon.speech.simclient.common.queue.delay.Delay;
import android.os.Handler;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class QueueRequest {
    private final Handler mHandler;
    private final int mId;
    private volatile int mRetryCount;
    private final Delay mRetryDelay;
    private Runnable mRetryRunnable;
    private volatile State mState;
    private volatile StatusListener mStatusListener;
    private final Delay mTimeoutDelay;
    private TimeoutRunnable mTimeoutRunnable;
    private static final String TAG = GeneratedOutlineSupport1.outline39(QueueRequest.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private static final AtomicInteger INSTANCE_COUNT = new AtomicInteger();

    /* loaded from: classes.dex */
    public static class InvocationMetadata {
        private final int mQueueRequestId;
        private final int mRetryNumber;

        public InvocationMetadata(int i, int i2) {
            this.mQueueRequestId = i;
            this.mRetryNumber = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof InvocationMetadata)) {
                return false;
            }
            InvocationMetadata invocationMetadata = (InvocationMetadata) obj;
            return this.mQueueRequestId == invocationMetadata.mQueueRequestId && this.mRetryNumber == invocationMetadata.mRetryNumber;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mQueueRequestId), Integer.valueOf(this.mRetryNumber));
        }

        public int queueRequestId() {
            return this.mQueueRequestId;
        }

        public int retryNumber() {
            return this.mRetryNumber;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum State {
        Created,
        Initialized,
        Running,
        Finished
    }

    /* loaded from: classes.dex */
    interface StatusListener {
        void onFinished(QueueRequest queueRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TimeoutRunnable implements Runnable {
        private final InvocationMetadata mInvocationMetadata;

        TimeoutRunnable(InvocationMetadata invocationMetadata) {
            this.mInvocationMetadata = invocationMetadata;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.w(QueueRequest.TAG, String.format("Request (%d) timed out for retry (%d).", Integer.valueOf(QueueRequest.this.mId), Integer.valueOf(this.mInvocationMetadata.retryNumber())));
            QueueRequest.this.onTimeout(this.mInvocationMetadata);
        }
    }

    public QueueRequest() {
        this(null, null, null);
    }

    private void clearRunnables() {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mTimeoutRunnable = null;
        this.mHandler.removeCallbacks(this.mRetryRunnable);
        this.mRetryRunnable = null;
    }

    private boolean isInvocationMetadataValid(InvocationMetadata invocationMetadata) {
        if (invocationMetadata.queueRequestId() == this.mId) {
            if (invocationMetadata.retryNumber() >= this.mRetryCount) {
                return true;
            }
            Log.w(TAG, "This invocation is not for the current retry number, ignoring request.");
            return false;
        }
        throw new IllegalArgumentException("Invocation metadata can not be shared between requests.");
    }

    public int getId() {
        return this.mId;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean initialize(StatusListener statusListener) {
        if (this.mState != State.Created) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Request (");
            outline107.append(this.mId);
            outline107.append(") was already initialized, can't initialize again.");
            Log.w(str, outline107.toString());
            return false;
        }
        this.mState = State.Initialized;
        this.mStatusListener = statusListener;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invoke() {
        InvocationMetadata invocationMetadata;
        synchronized (this) {
            this.mState = State.Running;
            invocationMetadata = new InvocationMetadata(this.mId, this.mRetryCount);
            if (this.mHandler != null) {
                clearRunnables();
                this.mTimeoutRunnable = new TimeoutRunnable(invocationMetadata);
                this.mHandler.postDelayed(this.mTimeoutRunnable, this.mTimeoutDelay.delay(invocationMetadata.retryNumber()));
            }
        }
        if (invocationMetadata.retryNumber() > 0) {
            String.format("Running Request (%d). Retry number (%d).", Integer.valueOf(this.mId), Integer.valueOf(invocationMetadata.retryNumber()));
        }
        onInvoke(invocationMetadata.retryNumber());
        onInvoke(invocationMetadata);
    }

    public void onFinished() {
        synchronized (this) {
            if (this.mState == State.Finished) {
                String str = TAG;
                Log.w(str, "Ignoring repeated onFinished for request (" + this.mId + ").");
                return;
            }
            this.mState = State.Finished;
            if (this.mHandler != null) {
                clearRunnables();
            }
            StatusListener statusListener = this.mStatusListener;
            this.mStatusListener = null;
            if (statusListener == null) {
                return;
            }
            statusListener.onFinished(this);
        }
    }

    @Deprecated
    protected void onInvoke(int i) {
    }

    protected void onInvoke(InvocationMetadata invocationMetadata) {
    }

    protected void onTimeout(InvocationMetadata invocationMetadata) {
    }

    public boolean retry() {
        return retry(new InvocationMetadata(this.mId, this.mRetryCount));
    }

    public void retryWithDelay(InvocationMetadata invocationMetadata) {
        retryWithDelay(invocationMetadata, this.mRetryDelay);
    }

    public QueueRequest(Delay delay) {
        this(delay, null, new Handler());
    }

    public boolean retry(InvocationMetadata invocationMetadata) {
        synchronized (this) {
            if (!isInvocationMetadataValid(invocationMetadata)) {
                return false;
            }
            if (this.mHandler != null) {
                clearRunnables();
            }
            if (this.mState != State.Initialized && this.mState != State.Running) {
                Log.e(TAG, "Request (" + this.mId + ") not initialized or not running. Unable to retry");
                return false;
            }
            this.mRetryCount++;
            invoke();
            return true;
        }
    }

    public synchronized void retryWithDelay(final InvocationMetadata invocationMetadata, Delay delay) {
        if (!isInvocationMetadataValid(invocationMetadata)) {
            return;
        }
        clearRunnables();
        this.mRetryRunnable = new Runnable() { // from class: amazon.speech.simclient.common.queue.QueueRequest.1
            @Override // java.lang.Runnable
            public void run() {
                QueueRequest.this.retry(invocationMetadata);
            }
        };
        long delay2 = delay.delay(invocationMetadata.retryNumber());
        Log.i(TAG, String.format("Retrying request (%d) with a delay of (%d) ms", Integer.valueOf(this.mId), Long.valueOf(delay2)));
        this.mHandler.postDelayed(this.mRetryRunnable, delay2);
    }

    public QueueRequest(Delay delay, Delay delay2) {
        this(delay, delay2, new Handler());
    }

    public QueueRequest(Delay delay, Delay delay2, Handler handler) {
        this.mRetryCount = 0;
        this.mId = INSTANCE_COUNT.getAndIncrement();
        this.mState = State.Created;
        this.mTimeoutDelay = delay;
        this.mRetryDelay = delay2;
        this.mHandler = handler;
    }
}
