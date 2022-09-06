package rx.observers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Notification;
import rx.Observer;
import rx.exceptions.CompositeException;
@Deprecated
/* loaded from: classes5.dex */
public class TestObserver<T> implements Observer<T> {
    private static final Observer<Object> INERT = new Observer<Object>() { // from class: rx.observers.TestObserver.1
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
    private final Observer<T> delegate;
    private final List<Notification<T>> onCompletedEvents;
    private final List<Throwable> onErrorEvents;
    private final List<T> onNextEvents;

    public TestObserver(Observer<T> observer) {
        this.onNextEvents = new ArrayList();
        this.onErrorEvents = new ArrayList();
        this.onCompletedEvents = new ArrayList();
        this.delegate = observer;
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.onNextEvents.size() != list.size()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of items does not match. Provided: ");
            outline107.append(list.size());
            outline107.append("  Actual: ");
            outline107.append(this.onNextEvents.size());
            outline107.append(".\n");
            outline107.append("Provided values: ");
            outline107.append(list);
            outline107.append("\n");
            outline107.append("Actual values: ");
            assertionError(GeneratedOutlineSupport1.outline95(outline107, this.onNextEvents, "\n"));
        }
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            T t2 = this.onNextEvents.get(i);
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
        if (this.onErrorEvents.size() > 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Too many onError events: ");
            outline107.append(this.onErrorEvents.size());
            assertionError(outline107.toString());
        }
        if (this.onCompletedEvents.size() > 1) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Too many onCompleted events: ");
            outline1072.append(this.onCompletedEvents.size());
            assertionError(outline1072.toString());
        }
        if (this.onCompletedEvents.size() == 1 && this.onErrorEvents.size() == 1) {
            assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (!this.onCompletedEvents.isEmpty() || !this.onErrorEvents.isEmpty()) {
            return;
        }
        assertionError("No terminal events received.");
    }

    final void assertionError(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 32);
        sb.append(str);
        sb.append(" (");
        int size = this.onCompletedEvents.size();
        sb.append(size);
        sb.append(" completion");
        if (size != 1) {
            sb.append('s');
        }
        sb.append(')');
        if (!this.onErrorEvents.isEmpty()) {
            int size2 = this.onErrorEvents.size();
            sb.append(" (+");
            sb.append(size2);
            sb.append(" error");
            if (size2 != 1) {
                sb.append('s');
            }
            sb.append(')');
        }
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.onErrorEvents.isEmpty()) {
            if (this.onErrorEvents.size() == 1) {
                assertionError.initCause(this.onErrorEvents.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.onErrorEvents));
            }
        }
        throw assertionError;
    }

    public List<Object> getEvents() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.onNextEvents);
        arrayList.add(this.onErrorEvents);
        arrayList.add(this.onCompletedEvents);
        return Collections.unmodifiableList(arrayList);
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return Collections.unmodifiableList(this.onCompletedEvents);
    }

    public List<Throwable> getOnErrorEvents() {
        return Collections.unmodifiableList(this.onErrorEvents);
    }

    public List<T> getOnNextEvents() {
        return Collections.unmodifiableList(this.onNextEvents);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.onCompletedEvents.add(Notification.createOnCompleted());
        this.delegate.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.onErrorEvents.add(th);
        this.delegate.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.onNextEvents.add(t);
        this.delegate.onNext(t);
    }

    public TestObserver() {
        this.onNextEvents = new ArrayList();
        this.onErrorEvents = new ArrayList();
        this.onCompletedEvents = new ArrayList();
        this.delegate = (Observer<T>) INERT;
    }
}
