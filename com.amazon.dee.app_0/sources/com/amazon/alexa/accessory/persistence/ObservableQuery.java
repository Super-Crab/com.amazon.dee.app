package com.amazon.alexa.accessory.persistence;

import android.database.Cursor;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
public final class ObservableQuery extends Observable<DatabaseQuery> {
    private final Observable<DatabaseQuery> upstream;

    public ObservableQuery(Observable<DatabaseQuery> observable) {
        Preconditions.notNull(observable, "upstream");
        this.upstream = observable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$mapToList$1(Function function, DatabaseQuery databaseQuery) throws Throwable {
        Cursor run = databaseQuery.run();
        try {
            ArrayList arrayList = new ArrayList(run.getCount());
            while (run.moveToNext()) {
                Object mo10358apply = function.mo10358apply(run);
                if (mo10358apply != null) {
                    arrayList.add(mo10358apply);
                } else {
                    throw new NullPointerException("Mapper returned null item!");
                }
            }
            run.close();
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (run != null) {
                    try {
                        run.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$mapToOne$0(Function function, DatabaseQuery databaseQuery) throws Throwable {
        Cursor run = databaseQuery.run();
        try {
            if (run.moveToFirst()) {
                if (run.getCount() <= 1) {
                    Object mo10358apply = function.mo10358apply(run);
                    if (mo10358apply != null) {
                        run.close();
                        return mo10358apply;
                    }
                    throw new NullPointerException("Mapper returned null item!");
                }
                throw new IllegalArgumentException("Cursor contains more than 1 element!");
            }
            throw new NoSuchElementException("Cursor is empty!");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (run != null) {
                    try {
                        run.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public <T> Observable<List<T>> mapToList(final Function<Cursor, T> function) {
        return (Observable<List<T>>) map(new Function() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableQuery$yKCM58nTNl4t6G3f4nDOUNEVYDQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ObservableQuery.lambda$mapToList$1(Function.this, (DatabaseQuery) obj);
            }
        });
    }

    public <T> Observable<T> mapToOne(final Function<Cursor, T> function) {
        return (Observable<T>) map(new Function() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableQuery$EBOIi0BOMqav3sDDM4XnSeFjzgY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ObservableQuery.lambda$mapToOne$0(Function.this, (DatabaseQuery) obj);
            }
        });
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super DatabaseQuery> observer) {
        this.upstream.subscribe(observer);
    }
}
