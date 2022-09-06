package com.amazon.alexa.accessorykit.interprocess;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessorykit.interprocess.utils.AccountManagerWrapper;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;
/* loaded from: classes6.dex */
public class InterprocessMarketplaceService implements MarketplaceService {
    private static final long TIMEOUT_MS = 15000;
    private final AccountManagerWrapper accountManagerWrapper;
    private final long timeoutMilliseconds;

    public InterprocessMarketplaceService(AccountManagerWrapper accountManagerWrapper) {
        this(accountManagerWrapper, TIMEOUT_MS);
    }

    static /* synthetic */ void lambda$null$0(Subscriber subscriber, Marketplace marketplace) throws Throwable {
        subscriber.onNext(marketplace);
        subscriber.onCompleted();
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceService
    public Observable<Marketplace> getEffectivePFM() {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessMarketplaceService$U9d8mUB_bUASop4AB0q151KitZk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                InterprocessMarketplaceService.this.lambda$getEffectivePFM$3$InterprocessMarketplaceService((Subscriber) obj);
            }
        }).timeout(this.timeoutMilliseconds, TimeUnit.MILLISECONDS);
    }

    public /* synthetic */ void lambda$getEffectivePFM$3$InterprocessMarketplaceService(final Subscriber subscriber) {
        final Disposable subscribe = this.accountManagerWrapper.getMarketplace().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessMarketplaceService$J9wL10qWssCYHU82bVoGWqB0seI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Subscriber subscriber2 = Subscriber.this;
                subscriber2.onNext((Marketplace) obj);
                subscriber2.onCompleted();
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessMarketplaceService$pAVeeF2XPL55twPNw9mN2vRySrQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Subscriber.this.onError((Throwable) obj);
            }
        });
        subscriber.add(Subscriptions.create(new Action0() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessMarketplaceService$ccpoBwAax8NG9uiuOLHT8NQLX04
            @Override // rx.functions.Action0
            public final void call() {
                Disposable.this.dispose();
            }
        }));
    }

    @VisibleForTesting
    InterprocessMarketplaceService(AccountManagerWrapper accountManagerWrapper, long j) {
        this.accountManagerWrapper = accountManagerWrapper;
        this.timeoutMilliseconds = j;
    }
}
