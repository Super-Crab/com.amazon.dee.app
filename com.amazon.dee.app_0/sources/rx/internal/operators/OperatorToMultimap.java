package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;
/* loaded from: classes5.dex */
public final class OperatorToMultimap<T, K, V> implements Observable.Operator<Map<K, Collection<V>>, T> {
    final Func1<? super K, ? extends Collection<V>> collectionFactory;
    final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, Collection<V>>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;

    /* loaded from: classes5.dex */
    public static final class DefaultMultimapCollectionFactory<K, V> implements Func1<K, Collection<V>> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.functions.Func1
        /* renamed from: call */
        public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
            return mo13102call((DefaultMultimapCollectionFactory<K, V>) obj);
        }

        @Override // rx.functions.Func1
        /* renamed from: call  reason: collision with other method in class */
        public Collection<V> mo13102call(K k) {
            return new ArrayList();
        }
    }

    /* loaded from: classes5.dex */
    public static final class DefaultToMultimapFactory<K, V> implements Func0<Map<K, Collection<V>>> {
        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: call  reason: collision with other method in class */
        public Map<K, Collection<V>> mo13098call() {
            return new HashMap();
        }
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, new DefaultToMultimapFactory(), new DefaultMultimapCollectionFactory());
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0) {
        this(func1, func12, func0, new DefaultMultimapCollectionFactory());
    }

    public Subscriber<? super T> call(final Subscriber<? super Map<K, Collection<V>>> subscriber) {
        try {
            final Map<K, Collection<V>> mo13098call = this.mapFactory.mo13098call();
            return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorToMultimap.1
                private Map<K, Collection<V>> map;

                {
                    this.map = mo13098call;
                }

                @Override // rx.Observer
                public void onCompleted() {
                    Map<K, Collection<V>> map = this.map;
                    this.map = null;
                    subscriber.onNext(map);
                    subscriber.onCompleted();
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    this.map = null;
                    subscriber.onError(th);
                }

                @Override // rx.Observer
                public void onNext(T t) {
                    try {
                        K mo13102call = OperatorToMultimap.this.keySelector.mo13102call(t);
                        V mo13102call2 = OperatorToMultimap.this.valueSelector.mo13102call(t);
                        Collection<V> collection = this.map.get(mo13102call);
                        if (collection == null) {
                            try {
                                collection = OperatorToMultimap.this.collectionFactory.mo13102call(mo13102call);
                                this.map.put(mo13102call, collection);
                            } catch (Throwable th) {
                                Exceptions.throwOrReport(th, subscriber);
                                return;
                            }
                        }
                        collection.add(mo13102call2);
                    } catch (Throwable th2) {
                        Exceptions.throwOrReport(th2, subscriber);
                    }
                }

                @Override // rx.Subscriber
                public void onStart() {
                    request(Long.MAX_VALUE);
                }
            };
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
            Subscriber<? super T> empty = Subscribers.empty();
            empty.unsubscribe();
            return empty;
        }
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0, Func1<? super K, ? extends Collection<V>> func13) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.mapFactory = func0;
        this.collectionFactory = func13;
    }
}
