package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.reactivestreams.Publisher;
/* loaded from: classes3.dex */
public final class SingleInternalHelper {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum NoSuchElementSupplier implements Supplier<NoSuchElementException> {
        INSTANCE;

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get  reason: collision with other method in class */
        public NoSuchElementException mo10365get() {
            return new NoSuchElementException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum ToFlowable implements Function<SingleSource, Publisher> {
        INSTANCE;

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Publisher mo10358apply(SingleSource v) {
            return new SingleToFlowable(v);
        }
    }

    /* loaded from: classes3.dex */
    static final class ToFlowableIterable<T> implements Iterable<Flowable<T>> {
        private final Iterable<? extends SingleSource<? extends T>> sources;

        ToFlowableIterable(Iterable<? extends SingleSource<? extends T>> sources) {
            this.sources = sources;
        }

        @Override // java.lang.Iterable
        public Iterator<Flowable<T>> iterator() {
            return new ToFlowableIterator(this.sources.iterator());
        }
    }

    /* loaded from: classes3.dex */
    static final class ToFlowableIterator<T> implements Iterator<Flowable<T>> {
        private final Iterator<? extends SingleSource<? extends T>> sit;

        ToFlowableIterator(Iterator<? extends SingleSource<? extends T>> sit) {
            this.sit = sit;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.sit.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        /* renamed from: next */
        public Flowable<T> mo10357next() {
            return new SingleToFlowable(this.sit.next());
        }
    }

    private SingleInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static Supplier<NoSuchElementException> emptyThrower() {
        return NoSuchElementSupplier.INSTANCE;
    }

    public static <T> Iterable<? extends Flowable<T>> iterableToFlowable(final Iterable<? extends SingleSource<? extends T>> sources) {
        return new ToFlowableIterable(sources);
    }

    public static <T> Function<SingleSource<? extends T>, Publisher<? extends T>> toFlowable() {
        return ToFlowable.INSTANCE;
    }
}
