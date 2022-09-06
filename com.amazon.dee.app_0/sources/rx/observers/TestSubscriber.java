package rx.observers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.Observer;
import rx.Subscriber;
import rx.annotations.Experimental;
import rx.exceptions.CompositeException;
/* loaded from: classes5.dex */
public class TestSubscriber<T> extends Subscriber<T> {
    private static final Observer<Object> INERT = new Observer<Object>() { // from class: rx.observers.TestSubscriber.1
        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
        }

        @Override // rx.Observer
        public void onNext(Object obj) {
        }
    };
    private int completions;
    private final Observer<T> delegate;
    private final List<Throwable> errors;
    private volatile Thread lastSeenThread;
    private final CountDownLatch latch;
    private volatile int valueCount;
    private final List<T> values;

    public TestSubscriber(long j) {
        this(INERT, j);
    }

    public static <T> TestSubscriber<T> create() {
        return new TestSubscriber<>();
    }

    public void assertCompleted() {
        int i = this.completions;
        if (i == 0) {
            assertionError("Not completed!");
        } else if (i <= 1) {
        } else {
            assertionError(GeneratedOutlineSupport1.outline49("Completed multiple times: ", i));
        }
    }

    public void assertError(Class<? extends Throwable> cls) {
        List<Throwable> list = this.errors;
        if (list.isEmpty()) {
            assertionError("No errors");
        } else if (list.size() <= 1) {
            if (cls.isInstance(list.get(0))) {
                return;
            }
            AssertionError assertionError = new AssertionError("Exceptions differ; expected: " + cls + ", actual: " + list.get(0));
            assertionError.initCause(list.get(0));
            throw assertionError;
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Multiple errors: ");
            outline107.append(list.size());
            AssertionError assertionError2 = new AssertionError(outline107.toString());
            assertionError2.initCause(new CompositeException(list));
            throw assertionError2;
        }
    }

    public void assertNoErrors() {
        if (!getOnErrorEvents().isEmpty()) {
            assertionError("Unexpected onError events");
        }
    }

    public void assertNoTerminalEvent() {
        List<Throwable> list = this.errors;
        int i = this.completions;
        if (!list.isEmpty() || i > 0) {
            if (list.isEmpty()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found ");
                outline107.append(list.size());
                outline107.append(" errors and ");
                outline107.append(i);
                outline107.append(" completion events instead of none");
                assertionError(outline107.toString());
            } else if (list.size() == 1) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Found ");
                outline1072.append(list.size());
                outline1072.append(" errors and ");
                outline1072.append(i);
                outline1072.append(" completion events instead of none");
                assertionError(outline1072.toString());
            } else {
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Found ");
                outline1073.append(list.size());
                outline1073.append(" errors and ");
                outline1073.append(i);
                outline1073.append(" completion events instead of none");
                assertionError(outline1073.toString());
            }
        }
    }

    public void assertNoValues() {
        int size = this.values.size();
        if (size != 0) {
            assertionError(GeneratedOutlineSupport1.outline49("No onNext events expected yet some received: ", size));
        }
    }

    public void assertNotCompleted() {
        int i = this.completions;
        if (i == 1) {
            assertionError("Completed!");
        } else if (i <= 1) {
        } else {
            assertionError(GeneratedOutlineSupport1.outline49("Completed multiple times: ", i));
        }
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.values.size() != list.size()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of items does not match. Provided: ");
            outline107.append(list.size());
            outline107.append("  Actual: ");
            outline107.append(this.values.size());
            outline107.append(".\n");
            outline107.append("Provided values: ");
            outline107.append(list);
            outline107.append("\n");
            outline107.append("Actual values: ");
            assertionError(GeneratedOutlineSupport1.outline95(outline107, this.values, "\n"));
        }
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            T t2 = this.values.get(i);
            if (t == null) {
                if (t2 != null) {
                    assertionError("Value at index: " + i + " expected to be [null] but was: [" + t2 + "]\n");
                }
            } else if (!t.equals(t2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Value at index: ");
                sb.append(i);
                sb.append(" expected to be [");
                sb.append(t);
                sb.append("] (");
                sb.append(t.getClass().getSimpleName());
                sb.append(") but was: [");
                sb.append(t2);
                sb.append("] (");
                assertionError(GeneratedOutlineSupport1.outline91(sb, t2 != null ? t2.getClass().getSimpleName() : "null", ")\n"));
            }
        }
    }

    public void assertTerminalEvent() {
        if (this.errors.size() > 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Too many onError events: ");
            outline107.append(this.errors.size());
            assertionError(outline107.toString());
        }
        if (this.completions > 1) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Too many onCompleted events: ");
            outline1072.append(this.completions);
            assertionError(outline1072.toString());
        }
        if (this.completions == 1 && this.errors.size() == 1) {
            assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.completions != 0 || !this.errors.isEmpty()) {
            return;
        }
        assertionError("No terminal events received.");
    }

    public void assertUnsubscribed() {
        if (!isUnsubscribed()) {
            assertionError("Not unsubscribed.");
        }
    }

    public void assertValue(T t) {
        assertReceivedOnNext(Collections.singletonList(t));
    }

    public void assertValueCount(int i) {
        int size = this.values.size();
        if (size != i) {
            assertionError(GeneratedOutlineSupport1.outline53("Number of onNext events differ; expected: ", i, ", actual: ", size));
        }
    }

    public void assertValues(T... tArr) {
        assertReceivedOnNext(Arrays.asList(tArr));
    }

    final void assertionError(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 32);
        sb.append(str);
        sb.append(" (");
        int i = this.completions;
        sb.append(i);
        sb.append(" completion");
        if (i != 1) {
            sb.append('s');
        }
        sb.append(')');
        if (!this.errors.isEmpty()) {
            int size = this.errors.size();
            sb.append(" (+");
            sb.append(size);
            sb.append(" error");
            if (size != 1) {
                sb.append('s');
            }
            sb.append(')');
        }
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                assertionError.initCause(this.errors.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.errors));
            }
        }
        throw assertionError;
    }

    public void awaitTerminalEvent() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted", e);
        }
    }

    public void awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit) {
        try {
            if (this.latch.await(j, timeUnit)) {
                return;
            }
            unsubscribe();
        } catch (InterruptedException unused) {
            unsubscribe();
        }
    }

    @Experimental
    public final boolean awaitValueCount(int i, long j, TimeUnit timeUnit) throws InterruptedException {
        while (j != 0 && this.valueCount < i) {
            timeUnit.sleep(1L);
            j--;
        }
        return this.valueCount >= i;
    }

    @Experimental
    public final int getCompletions() {
        return this.completions;
    }

    public Thread getLastSeenThread() {
        return this.lastSeenThread;
    }

    @Deprecated
    public List<Notification<T>> getOnCompletedEvents() {
        int i = this.completions;
        ArrayList arrayList = new ArrayList(i != 0 ? i : 1);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Notification.createOnCompleted());
        }
        return arrayList;
    }

    public List<Throwable> getOnErrorEvents() {
        return this.errors;
    }

    public List<T> getOnNextEvents() {
        return this.values;
    }

    public final int getValueCount() {
        return this.valueCount;
    }

    @Override // rx.Observer
    public void onCompleted() {
        try {
            this.completions++;
            this.lastSeenThread = Thread.currentThread();
            this.delegate.onCompleted();
        } finally {
            this.latch.countDown();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        try {
            this.lastSeenThread = Thread.currentThread();
            this.errors.add(th);
            this.delegate.onError(th);
        } finally {
            this.latch.countDown();
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.lastSeenThread = Thread.currentThread();
        this.values.add(t);
        this.valueCount = this.values.size();
        this.delegate.onNext(t);
    }

    public void requestMore(long j) {
        request(j);
    }

    public TestSubscriber(Observer<T> observer, long j) {
        this.latch = new CountDownLatch(1);
        if (observer != null) {
            this.delegate = observer;
            if (j >= 0) {
                request(j);
            }
            this.values = new ArrayList();
            this.errors = new ArrayList();
            return;
        }
        throw new NullPointerException();
    }

    public static <T> TestSubscriber<T> create(long j) {
        return new TestSubscriber<>(j);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer, long j) {
        return new TestSubscriber<>(observer, j);
    }

    public void awaitTerminalEvent(long j, TimeUnit timeUnit) {
        try {
            this.latch.await(j, timeUnit);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted", e);
        }
    }

    public static <T> TestSubscriber<T> create(Subscriber<T> subscriber) {
        return new TestSubscriber<>((Subscriber) subscriber);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer) {
        return new TestSubscriber<>(observer);
    }

    public TestSubscriber(Subscriber<T> subscriber) {
        this(subscriber, -1L);
    }

    public TestSubscriber(Observer<T> observer) {
        this(observer, -1L);
    }

    public TestSubscriber() {
        this(-1L);
    }

    public void assertError(Throwable th) {
        List<Throwable> list = this.errors;
        if (list.isEmpty()) {
            assertionError("No errors");
        } else if (list.size() > 1) {
            assertionError("Multiple errors");
        } else if (th.equals(list.get(0))) {
        } else {
            assertionError("Exceptions differ; expected: " + th + ", actual: " + list.get(0));
        }
    }
}
