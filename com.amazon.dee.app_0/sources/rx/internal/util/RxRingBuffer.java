package rx.internal.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.util.Queue;
import rx.Observer;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
/* loaded from: classes5.dex */
public class RxRingBuffer implements Subscription {
    private static final NotificationLite<Object> ON = NotificationLite.instance();
    public static final int SIZE;
    public static final ObjectPool<Queue<Object>> SPMC_POOL;
    public static final ObjectPool<Queue<Object>> SPSC_POOL;
    static int defaultSize;
    private final ObjectPool<Queue<Object>> pool;
    private Queue<Object> queue;
    private final int size;
    public volatile Object terminalState;

    static {
        defaultSize = 128;
        if (PlatformDependent.isAndroid()) {
            defaultSize = 16;
        }
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                defaultSize = Integer.parseInt(property);
            } catch (NumberFormatException e) {
                PrintStream printStream = System.err;
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Failed to set 'rx.buffer.size' with value ", property, " => ");
                outline115.append(e.getMessage());
                printStream.println(outline115.toString());
            }
        }
        SIZE = defaultSize;
        SPSC_POOL = new ObjectPool<Queue<Object>>() { // from class: rx.internal.util.RxRingBuffer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // rx.internal.util.ObjectPool
            public Queue<Object> createObject() {
                return new SpscArrayQueue(RxRingBuffer.SIZE);
            }
        };
        SPMC_POOL = new ObjectPool<Queue<Object>>() { // from class: rx.internal.util.RxRingBuffer.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // rx.internal.util.ObjectPool
            public Queue<Object> createObject() {
                return new SpmcArrayQueue(RxRingBuffer.SIZE);
            }
        };
    }

    private RxRingBuffer(Queue<Object> queue, int i) {
        this.queue = queue;
        this.pool = null;
        this.size = i;
    }

    public static RxRingBuffer getSpmcInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(SPMC_POOL, SIZE);
        }
        return new RxRingBuffer();
    }

    public static RxRingBuffer getSpscInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(SPSC_POOL, SIZE);
        }
        return new RxRingBuffer();
    }

    public boolean accept(Object obj, Observer observer) {
        return ON.accept(observer, obj);
    }

    public Throwable asError(Object obj) {
        return ON.getError(obj);
    }

    public int available() {
        return this.size - count();
    }

    public int capacity() {
        return this.size;
    }

    public int count() {
        Queue<Object> queue = this.queue;
        if (queue == null) {
            return 0;
        }
        return queue.size();
    }

    public Object getValue(Object obj) {
        return ON.getValue(obj);
    }

    public boolean isCompleted(Object obj) {
        return ON.isCompleted(obj);
    }

    public boolean isEmpty() {
        Queue<Object> queue = this.queue;
        if (queue == null) {
            return true;
        }
        return queue.isEmpty();
    }

    public boolean isError(Object obj) {
        return ON.isError(obj);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.queue == null;
    }

    public void onCompleted() {
        if (this.terminalState == null) {
            this.terminalState = ON.completed();
        }
    }

    public void onError(Throwable th) {
        if (this.terminalState == null) {
            this.terminalState = ON.error(th);
        }
    }

    public void onNext(Object obj) throws MissingBackpressureException {
        boolean z;
        boolean z2;
        synchronized (this) {
            Queue<Object> queue = this.queue;
            z = true;
            if (queue != null) {
                z2 = !queue.offer(ON.next(obj));
                z = false;
            } else {
                z2 = false;
            }
        }
        if (!z) {
            if (z2) {
                throw new MissingBackpressureException();
            }
            return;
        }
        throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
    }

    public Object peek() {
        synchronized (this) {
            Queue<Object> queue = this.queue;
            if (queue == null) {
                return null;
            }
            Object peek = queue.peek();
            Object obj = this.terminalState;
            if (peek == null && obj != null && queue.peek() == null) {
                peek = obj;
            }
            return peek;
        }
    }

    public Object poll() {
        synchronized (this) {
            Queue<Object> queue = this.queue;
            if (queue == null) {
                return null;
            }
            Object poll = queue.poll();
            Object obj = this.terminalState;
            if (poll == null && obj != null && queue.peek() == null) {
                this.terminalState = null;
                poll = obj;
            }
            return poll;
        }
    }

    public synchronized void release() {
        Queue<Object> queue = this.queue;
        ObjectPool<Queue<Object>> objectPool = this.pool;
        if (objectPool != null && queue != null) {
            queue.clear();
            this.queue = null;
            objectPool.returnObject(queue);
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        release();
    }

    private RxRingBuffer(ObjectPool<Queue<Object>> objectPool, int i) {
        this.pool = objectPool;
        this.queue = objectPool.borrowObject();
        this.size = i;
    }

    RxRingBuffer() {
        this(new SynchronizedQueue(SIZE), SIZE);
    }
}
