package com.amazon.alexa.accessorykit.interprocess.utils;

import android.os.Looper;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.NoOpMetricsService;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.marketplace.api.Marketplace;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes6.dex */
public class AccountManagerWrapper {
    private static final String TAG = "AccountManagerWrapper:";
    private AccountManager accountManager;
    private final AccountManagerProducer accountManagerProducer;
    private AccessoryMetricsService metricsService;

    /* loaded from: classes6.dex */
    public interface AccountManagerProducer {
        AccountManager getAccountManager();
    }

    public AccountManagerWrapper(AccountManagerProducer accountManagerProducer) {
        Preconditions.notNull(accountManagerProducer, "accountManagerProducer");
        this.accountManagerProducer = accountManagerProducer;
        this.metricsService = new NoOpMetricsService();
    }

    private void assertNotMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            return;
        }
        throw new RuntimeException("Called from main thread");
    }

    private synchronized AccountManager getAccountManager() {
        assertNotMainThread();
        if (this.accountManager == null) {
            this.accountManager = this.accountManagerProducer.getAccountManager();
        }
        return this.accountManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ User lambda$null$4(String str, String str2) throws Throwable {
        if (str2.isEmpty()) {
            return new User(null, str);
        }
        return new User(str2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMarketplaceAvailability(boolean z) {
        this.metricsService.recordOccurrence(MetricsConstants.InterprocessMarketplaceSupplier.INTERPROCESS_MARKETPLACE_SUPPLIER, MetricsConstants.InterprocessMarketplaceSupplier.MARKETPLACE_AVAILABLE, z, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMarketplaceEmpty(long j) {
        this.metricsService.recordTime(MetricsConstants.InterprocessMarketplaceSupplier.INTERPROCESS_MARKETPLACE_SUPPLIER, MetricsConstants.InterprocessMarketplaceSupplier.MARKETPLACE_EMPTY, j, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMarketplaceError(long j) {
        this.metricsService.recordTime(MetricsConstants.InterprocessMarketplaceSupplier.INTERPROCESS_MARKETPLACE_SUPPLIER, MetricsConstants.InterprocessMarketplaceSupplier.MARKETPLACE_ERROR, j, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMarketplaceLatency(long j) {
        this.metricsService.recordTime(MetricsConstants.InterprocessMarketplaceSupplier.INTERPROCESS_MARKETPLACE_SUPPLIER, MetricsConstants.InterprocessMarketplaceSupplier.MARKETPLACE_AVAILABLE_LATENCY, j, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordTokenAvailability(boolean z) {
        this.metricsService.recordOccurrence(MetricsConstants.InterprocessUserSupplier.INTERPROCESS_USER_SUPPLIER, MetricsConstants.InterprocessUserSupplier.ACCESS_TOKEN_RETRIEVED, z, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordUserAvailability(boolean z) {
        this.metricsService.recordOccurrence(MetricsConstants.InterprocessUserSupplier.INTERPROCESS_USER_SUPPLIER, MetricsConstants.InterprocessUserSupplier.USER_AVAILABLE, z, null);
    }

    public Completable clearCache() {
        return Completable.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.-$$Lambda$AccountManagerWrapper$lb3Pkx0ywrNwMZ5keeChB5joHak
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AccountManagerWrapper.this.lambda$clearCache$0$AccountManagerWrapper();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Single<Marketplace> getMarketplace() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.-$$Lambda$AccountManagerWrapper$r0un_IgmTFuKqpCsXhWROwDDwFQ
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AccountManagerWrapper.this.lambda$getMarketplace$1$AccountManagerWrapper(singleEmitter);
            }
        }).subscribeOn(Schedulers.io());
    }

    public /* synthetic */ CompletableSource lambda$clearCache$0$AccountManagerWrapper() throws Throwable {
        Logger.d("%s clearCache() called", TAG);
        getAccountManager().clearCache();
        return Completable.complete();
    }

    public /* synthetic */ void lambda$getMarketplace$1$AccountManagerWrapper(final SingleEmitter singleEmitter) throws Throwable {
        Logger.d("%s accountManager.getMarketplace() called.", TAG);
        getAccountManager().getMarketplace(new AccountManager.ResultCallback<String>() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.AccountManagerWrapper.1
            final long startTime = System.currentTimeMillis();

            private long getElapsedTime() {
                return System.currentTimeMillis() - this.startTime;
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(Exception exc) {
                if (singleEmitter.isDisposed()) {
                    Logger.e("%s accountManager.getMarketplace() downstream is disposed, ignoring result", AccountManagerWrapper.TAG);
                    return;
                }
                Logger.e("%s: accountManager.getMarketplace() called, result error", AccountManagerWrapper.TAG, exc);
                singleEmitter.onError(exc);
                AccountManagerWrapper.this.recordMarketplaceAvailability(false);
                AccountManagerWrapper.this.recordMarketplaceError(getElapsedTime());
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(String str) {
                if (singleEmitter.isDisposed()) {
                    Logger.e("%s accountManager.getMarketplace() downstream is disposed, ignoring result", AccountManagerWrapper.TAG);
                    return;
                }
                Logger.d("%s accountManager.getMarketplace() result: %s", AccountManagerWrapper.TAG, str);
                if (str != null && !str.isEmpty()) {
                    singleEmitter.onSuccess(Marketplace.findMarketplaceById(str, Marketplace.USA));
                    AccountManagerWrapper.this.recordMarketplaceAvailability(true);
                    AccountManagerWrapper.this.recordMarketplaceLatency(getElapsedTime());
                    return;
                }
                singleEmitter.onError(new IllegalStateException("marketplaceString was empty"));
                AccountManagerWrapper.this.recordMarketplaceAvailability(false);
                AccountManagerWrapper.this.recordMarketplaceEmpty(getElapsedTime());
            }
        });
    }

    public /* synthetic */ void lambda$obtainUser$2$AccountManagerWrapper(final SingleEmitter singleEmitter) throws Throwable {
        Logger.d("%s accountManager.getDirectedId() called.", TAG);
        getAccountManager().getDirectedID(new AccountManager.ResultCallback<String>() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.AccountManagerWrapper.2
            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(Exception exc) {
                if (singleEmitter.isDisposed()) {
                    Logger.e("%s accountManager.getDirectedId() downstream is disposed, ignoring result", AccountManagerWrapper.TAG);
                    return;
                }
                Logger.e("%s: accountManager.getDirectedId() called, result error", AccountManagerWrapper.TAG);
                singleEmitter.onError(new IllegalStateException("DirectedID could not be retrieved"));
                AccountManagerWrapper.this.recordUserAvailability(false);
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(String str) {
                if (singleEmitter.isDisposed()) {
                    Logger.e("%s accountManager.getDirectedId() downstream is disposed, ignoring result", AccountManagerWrapper.TAG);
                    return;
                }
                Logger.d("%s accountManager.getDirectedId() result: %s", AccountManagerWrapper.TAG, str);
                if (str != null && !str.isEmpty()) {
                    singleEmitter.onSuccess(str);
                    AccountManagerWrapper.this.recordUserAvailability(true);
                    return;
                }
                singleEmitter.onError(new IllegalStateException("DirectedID was empty"));
                AccountManagerWrapper.this.recordUserAvailability(false);
            }
        });
    }

    public /* synthetic */ void lambda$obtainUser$3$AccountManagerWrapper(final SingleEmitter singleEmitter) throws Throwable {
        Logger.d("%s accountManager.getToken() called.", TAG);
        getAccountManager().getToken(new AccountManager.ResultCallback<AccessToken>() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.AccountManagerWrapper.3
            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(Exception exc) {
                if (singleEmitter.isDisposed()) {
                    Logger.e("%s accountManager.getToken() downstream is disposed, ignoring result", AccountManagerWrapper.TAG);
                    return;
                }
                Logger.e("%s: accountManager.getToken() called result, error", exc, AccountManagerWrapper.TAG);
                singleEmitter.onSuccess("");
                AccountManagerWrapper.this.recordTokenAvailability(false);
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(AccessToken accessToken) {
                if (singleEmitter.isDisposed()) {
                    Logger.e("%s accountManager.getToken() downstream is disposed, ignoring result", AccountManagerWrapper.TAG);
                } else if (accessToken != null && accessToken.getValue() != null) {
                    Logger.d("%s accountManager.getToken() called result, success", AccountManagerWrapper.TAG);
                    AccountManagerWrapper.this.recordTokenAvailability(true);
                    singleEmitter.onSuccess(accessToken.getValue());
                } else {
                    Logger.e("%s accountManager.getToken() called, result empty or null", AccountManagerWrapper.TAG);
                    singleEmitter.onSuccess("");
                    AccountManagerWrapper.this.recordTokenAvailability(false);
                }
            }
        });
    }

    public Single<User> obtainUser() {
        Single subscribeOn = Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.-$$Lambda$AccountManagerWrapper$jvVaR2UbH4KNdve7AsFvbdW4Xho
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AccountManagerWrapper.this.lambda$obtainUser$2$AccountManagerWrapper(singleEmitter);
            }
        }).subscribeOn(Schedulers.io());
        final Single subscribeOn2 = Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.-$$Lambda$AccountManagerWrapper$dp29Sg030DOMBAXxpZcJi-6Qcdc
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AccountManagerWrapper.this.lambda$obtainUser$3$AccountManagerWrapper(singleEmitter);
            }
        }).subscribeOn(Schedulers.io());
        return subscribeOn.flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.-$$Lambda$AccountManagerWrapper$-zmsU18CWqbnH3drUt1NW-VQMfg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource map;
                map = Single.this.map(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.utils.-$$Lambda$AccountManagerWrapper$1gXCtyQ54pxwx1XHqGAp1qq9lOk
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        return AccountManagerWrapper.lambda$null$4(r1, (String) obj2);
                    }
                });
                return map;
            }
        });
    }

    public void setAccessoryMetricsService(AccessoryMetricsService accessoryMetricsService) {
        Preconditions.notNull(accessoryMetricsService, "metricsService");
        this.metricsService = accessoryMetricsService;
    }
}
