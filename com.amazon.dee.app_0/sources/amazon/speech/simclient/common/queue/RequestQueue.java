package amazon.speech.simclient.common.queue;

import amazon.speech.simclient.common.queue.QueueRequest;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
/* loaded from: classes.dex */
public class RequestQueue {
    private static final String TAG = GeneratedOutlineSupport1.outline39(RequestQueue.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private volatile QueueRequest mCurrentRequest;
    private final Deque<QueueRequest> mQueue = new LinkedList();
    private final QueueRequest.StatusListener mStatusListener = new QueueRequest.StatusListener() { // from class: amazon.speech.simclient.common.queue.RequestQueue.1
        @Override // amazon.speech.simclient.common.queue.QueueRequest.StatusListener
        public void onFinished(QueueRequest queueRequest) {
            synchronized (RequestQueue.this) {
                if (!Objects.equals(RequestQueue.this.mCurrentRequest, queueRequest)) {
                    String str = RequestQueue.TAG;
                    Log.wtf(str, "Request " + queueRequest.getId() + " finished, but it wasn't the current request.");
                }
                RequestQueue.this.mCurrentRequest = null;
                RequestQueue.this.tryRunNext();
            }
        }
    };

    private synchronized void addToQueue(QueueRequest queueRequest, boolean z) {
        if (!queueRequest.initialize(this.mStatusListener)) {
            Log.e(TAG, "Request (" + queueRequest.getId() + ") could not be initialized and will not be run.");
            return;
        }
        if (z) {
            this.mQueue.addFirst(queueRequest);
        } else {
            this.mQueue.addLast(queueRequest);
        }
        String str = "Request (" + queueRequest.getId() + ") enqueued";
        tryRunNext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void tryRunNext() {
        if (this.mCurrentRequest != null) {
            String str = TAG;
            Log.i(str, "Request (" + this.mCurrentRequest.getId() + ") is already running, returning.");
        } else if (this.mQueue.isEmpty()) {
        } else {
            this.mCurrentRequest = this.mQueue.pop();
            this.mCurrentRequest.invoke();
        }
    }

    public RequestQueue addToFront(QueueRequest queueRequest) {
        addToQueue(queueRequest, true);
        return this;
    }

    public RequestQueue enqueue(QueueRequest queueRequest) {
        addToQueue(queueRequest, false);
        return this;
    }

    @Deprecated
    protected synchronized QueueRequest peek() {
        return this.mCurrentRequest != null ? this.mCurrentRequest : this.mQueue.peek();
    }
}
