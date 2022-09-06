package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.observers.DefaultObserver;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes3.dex */
public final class BlockingObservableMostRecent<T> implements Iterable<T> {
    final T initialValue;
    final ObservableSource<T> source;

    /* loaded from: classes3.dex */
    static final class MostRecentObserver<T> extends DefaultObserver<T> {
        volatile Object value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public final class MostRecentIterator implements Iterator<T> {
            private Object buf;

            MostRecentIterator() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                this.buf = MostRecentObserver.this.value;
                return !NotificationLite.isComplete(this.buf);
            }

            @Override // java.util.Iterator
            public T next() {
                try {
                    if (this.buf == null) {
                        this.buf = MostRecentObserver.this.value;
                    }
                    if (!NotificationLite.isComplete(this.buf)) {
                        if (!NotificationLite.isError(this.buf)) {
                            return (T) NotificationLite.getValue(this.buf);
                        }
                        throw ExceptionHelper.wrapOrThrow(NotificationLite.getError(this.buf));
                    }
                    throw new NoSuchElementException();
                } finally {
                    this.buf = null;
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }

        MostRecentObserver(T value) {
            this.value = NotificationLite.next(value);
        }

        public MostRecentObserver<T>.MostRecentIterator getIterable() {
            return new MostRecentIterator();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.value = NotificationLite.complete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable e) {
            this.value = NotificationLite.error(e);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T args) {
            this.value = NotificationLite.next(args);
        }
    }

    public BlockingObservableMostRecent(ObservableSource<T> source, T initialValue) {
        this.source = source;
        this.initialValue = initialValue;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        MostRecentObserver mostRecentObserver = new MostRecentObserver(this.initialValue);
        this.source.subscribe(mostRecentObserver);
        return mostRecentObserver.getIterable();
    }
}
