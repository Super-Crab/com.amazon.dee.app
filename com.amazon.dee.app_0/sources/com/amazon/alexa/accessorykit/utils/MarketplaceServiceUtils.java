package com.amazon.alexa.accessorykit.utils;

import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
/* loaded from: classes6.dex */
public final class MarketplaceServiceUtils {
    private MarketplaceServiceUtils() {
    }

    public static Single<Marketplace> getMarketplace(final MarketplaceService marketplaceService) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessorykit.utils.-$$Lambda$MarketplaceServiceUtils$zv4uGwkrOBo-rr-smnXPhqPWHJ8
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                MarketplaceServiceUtils.lambda$getMarketplace$1(MarketplaceService.this, singleEmitter);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getMarketplace$1(MarketplaceService marketplaceService, final SingleEmitter singleEmitter) throws Throwable {
        Observable<Marketplace> effectivePFM = marketplaceService.getEffectivePFM();
        Action1<? super Marketplace> action1 = new Action1() { // from class: com.amazon.alexa.accessorykit.utils.-$$Lambda$MarketplaceServiceUtils$dDNSFPAMgFKSipAInECebfePHGo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MarketplaceServiceUtils.lambda$null$0(SingleEmitter.this, (Marketplace) obj);
            }
        };
        singleEmitter.getClass();
        final Subscription subscribe = effectivePFM.subscribe(action1, new Action1() { // from class: com.amazon.alexa.accessorykit.utils.-$$Lambda$zY-bWm-NHo2Fx_gBZclBApsUau8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SingleEmitter.this.onError((Throwable) obj);
            }
        });
        subscribe.getClass();
        singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessorykit.utils.-$$Lambda$xjv2drTSyKlInFDgf6AYP4aM4Pc
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                Subscription.this.unsubscribe();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(SingleEmitter singleEmitter, Marketplace marketplace) {
        if (marketplace != null) {
            singleEmitter.onSuccess(marketplace);
        } else {
            singleEmitter.onError(new IllegalStateException("Marketplace was null!"));
        }
    }
}
