package com.amazon.alexa.accessory.persistence;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class ObservableDatabase implements DatabaseContract {
    private final PublishSubject<Set<String>> triggers = PublishSubject.create();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$null$5(SQLiteOpenHelper sQLiteOpenHelper, Observable observable) throws Throwable {
        final SQLiteDatabase writableDatabase = sQLiteOpenHelper.getWritableDatabase();
        Observable doOnSubscribe = observable.doOnSubscribe(new Consumer() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableDatabase$FzLL6qKRlyOyPEPmcKW6wF4syJE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Disposable disposable = (Disposable) obj;
                writableDatabase.beginTransaction();
            }
        });
        writableDatabase.getClass();
        Observable doOnComplete = doOnSubscribe.doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$n73cOIe_sYYf0qElALuiGhTVh2I
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                writableDatabase.setTransactionSuccessful();
            }
        });
        writableDatabase.getClass();
        return doOnComplete.doFinally(new Action() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$Ov2vK60s7G2J1aZjmcMURz-tVD4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                writableDatabase.endTransaction();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ObservableQuery createQuery(DatabaseQuery databaseQuery) {
        Logger.v("ObservableDatabase.createQuery(" + databaseQuery + ")");
        return (ObservableQuery) this.triggers.filter(databaseQuery).map(databaseQuery).startWithItem(databaseQuery).observeOn(Schedulers.io()).to($$Lambda$_5ybMjMDybATYWrTps2IYqfaOn8.INSTANCE);
    }

    protected <T> ObservableTransformer<T, T> inTransaction(final SQLiteOpenHelper sQLiteOpenHelper) {
        return new ObservableTransformer() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableDatabase$EKNmtzW2W4oB4AGKnbdRO_W3E8I
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                ObservableSource defer;
                defer = Observable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableDatabase$HlKrBHUepu45HatFtiW6AV2TEQU
                    @Override // io.reactivex.rxjava3.functions.Supplier
                    /* renamed from: get */
                    public final Object mo10365get() {
                        return ObservableDatabase.lambda$null$5(r1, observable);
                    }
                });
                return defer;
            }
        };
    }

    public /* synthetic */ void lambda$null$0$ObservableDatabase(Set set) throws Throwable {
        this.triggers.onNext(set);
    }

    public /* synthetic */ void lambda$null$2$ObservableDatabase(Set set, Object obj) throws Throwable {
        this.triggers.onNext(set);
    }

    public /* synthetic */ CompletableSource lambda$triggerCompletable$1$ObservableDatabase(final Set set, Completable completable) {
        return completable.doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableDatabase$HFvxFkVodUjFuf9q-WeXB3jgPew
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                ObservableDatabase.this.lambda$null$0$ObservableDatabase(set);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$triggerSingle$3$ObservableDatabase(final Set set, Single single) {
        return single.doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableDatabase$VMqdfVVHqfq2gTcWWFHIGqKOIzw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ObservableDatabase.this.lambda$null$2$ObservableDatabase(set, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CompletableTransformer triggerCompletable(String str) {
        return triggerCompletable(Collections.singleton(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <T> SingleTransformer<T, T> triggerSingle(String str) {
        return triggerSingle(Collections.singleton(str));
    }

    protected final CompletableTransformer triggerCompletable(String... strArr) {
        return triggerCompletable(new HashSet(Arrays.asList(strArr)));
    }

    protected final <T> SingleTransformer<T, T> triggerSingle(String... strArr) {
        return triggerSingle(new HashSet(Arrays.asList(strArr)));
    }

    private <T> SingleTransformer<T, T> triggerSingle(final Set<String> set) {
        return new SingleTransformer() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableDatabase$xsIx0BCtSI-9ZZ8UukTTdcNBxMg
            @Override // io.reactivex.rxjava3.core.SingleTransformer
            public final SingleSource apply(Single single) {
                return ObservableDatabase.this.lambda$triggerSingle$3$ObservableDatabase(set, single);
            }
        };
    }

    protected final CompletableTransformer triggerCompletable(final Set<String> set) {
        return new CompletableTransformer() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$ObservableDatabase$Fnd3E_9wJWksyqR5mwG29C9NJRg
            @Override // io.reactivex.rxjava3.core.CompletableTransformer
            public final CompletableSource apply(Completable completable) {
                return ObservableDatabase.this.lambda$triggerCompletable$1$ObservableDatabase(set, completable);
            }
        };
    }
}
