package com.amazon.alexa.accessorykit.interprocess;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.NoOpMetricsService;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.accessorykit.interprocess.utils.AccountManagerWrapper;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class InterprocessUserSupplier implements UserSupplier {
    private static final String TAG = "InterprocessUserSupplier";
    private static final long retrievalTimeoutSecondsConstant = 15;
    private static final int retryCountConstant = 3;
    private final AccountManagerWrapper accountManagerWrapper;
    private Boolean hasClearedCacheInitially;
    private final InterprocessAccountChangedListener interprocessAccountChangedListener;
    private AccessoryMetricsService metricsService;
    private final long retrievalTimeoutSeconds;
    private final int retryCount;
    private final Subject<User> userSubject;

    /* loaded from: classes6.dex */
    public static final class InterprocessAccountChangedListener {
        private final BroadcastReceiver accountChangedBroadcastReceiver;
        private final AccountChangedCallback accountChangedCallback;
        private boolean active;
        private final Context context;
        private final IntentFilter intentFilter;
        public static final String ACCOUNT_CHANGED_INTENT_ACTION = "com.amazon.alexa.accessorykit.action.accountchanged";
        public static final Intent ACCOUNT_CHANGED_INTENT = new Intent(ACCOUNT_CHANGED_INTENT_ACTION).setPackage(AccessoriesFactory.getAppName());

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public interface AccountChangedCallback {
            void onAccountChanged(BroadcastReceiver.PendingResult pendingResult);
        }

        private BroadcastReceiver createAccountChangedBroadcastReceiver() {
            return new BroadcastReceiver() { // from class: com.amazon.alexa.accessorykit.interprocess.InterprocessUserSupplier.InterprocessAccountChangedListener.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (!InterprocessAccountChangedListener.ACCOUNT_CHANGED_INTENT_ACTION.equals(intent.getAction())) {
                        return;
                    }
                    InterprocessAccountChangedListener.this.accountChangedCallback.onAccountChanged(goAsync());
                }
            };
        }

        synchronized void activate() {
            if (this.active) {
                return;
            }
            this.active = true;
            this.context.registerReceiver(this.accountChangedBroadcastReceiver, this.intentFilter, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION", null);
        }

        synchronized void deactivate() {
            if (!this.active) {
                return;
            }
            this.active = false;
            this.context.unregisterReceiver(this.accountChangedBroadcastReceiver);
        }

        private InterprocessAccountChangedListener(Context context, AccountChangedCallback accountChangedCallback) {
            this.accountChangedCallback = accountChangedCallback;
            this.context = context;
            this.accountChangedBroadcastReceiver = createAccountChangedBroadcastReceiver();
            this.intentFilter = new IntentFilter();
            this.intentFilter.addAction(ACCOUNT_CHANGED_INTENT_ACTION);
        }
    }

    public InterprocessUserSupplier(Context context, AccountManagerWrapper accountManagerWrapper) {
        this(context, accountManagerWrapper, new NoOpMetricsService(), retrievalTimeoutSecondsConstant, 3);
    }

    private Single<User> getUser() {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessUserSupplier$aXl2YOzaEj4Dm-RHPwJczTBfIao
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return InterprocessUserSupplier.this.lambda$getUser$5$InterprocessUserSupplier();
            }
        }).subscribeOn(Schedulers.io());
    }

    private void recordUserChangeDetected(boolean z) {
        this.metricsService.recordOccurrence("InterprocessUserSupplier", MetricsConstants.InterprocessUserSupplier.USER_CHANGE_DETECTED, z, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void userChanged(final BroadcastReceiver.PendingResult pendingResult) {
        this.accountManagerWrapper.clearCache().doOnComplete(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessUserSupplier$RHfsbTbTZWuV6MuKMiwkZFyUA9E
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                InterprocessUserSupplier.this.lambda$userChanged$1$InterprocessUserSupplier();
            }
        }).andThen(getUser()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessUserSupplier$tChq55PrWscXazDDkVAREgmtf-o
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InterprocessUserSupplier.this.lambda$userChanged$2$InterprocessUserSupplier(pendingResult, (User) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessUserSupplier$9ta7cQKQqZT9Z79s4SyvZ48W9oQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InterprocessUserSupplier.this.lambda$userChanged$3$InterprocessUserSupplier(pendingResult, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$getUser$5$InterprocessUserSupplier() throws Throwable {
        Completable complete;
        synchronized (this.hasClearedCacheInitially) {
            if (!this.hasClearedCacheInitially.booleanValue()) {
                complete = this.accountManagerWrapper.clearCache().doOnComplete(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessUserSupplier$deSSfSHoBuNCPGj3LLqh-LWp5uA
                    @Override // io.reactivex.rxjava3.functions.Action
                    public final void run() {
                        InterprocessUserSupplier.this.lambda$null$4$InterprocessUserSupplier();
                    }
                });
            } else {
                complete = Completable.complete();
            }
        }
        return complete.andThen(this.accountManagerWrapper.obtainUser()).timeout(this.retrievalTimeoutSeconds, TimeUnit.SECONDS).retry(this.retryCount).onErrorReturnItem(User.ABSENT);
    }

    public /* synthetic */ void lambda$null$4$InterprocessUserSupplier() throws Throwable {
        synchronized (this.hasClearedCacheInitially) {
            this.hasClearedCacheInitially = true;
        }
    }

    public /* synthetic */ ObservableSource lambda$queryUser$0$InterprocessUserSupplier() throws Throwable {
        this.interprocessAccountChangedListener.activate();
        return Observable.concatEager(Arrays.asList(getUser().toObservable(), this.userSubject));
    }

    public /* synthetic */ void lambda$userChanged$1$InterprocessUserSupplier() throws Throwable {
        synchronized (this.hasClearedCacheInitially) {
            this.hasClearedCacheInitially = true;
        }
    }

    public /* synthetic */ void lambda$userChanged$2$InterprocessUserSupplier(BroadcastReceiver.PendingResult pendingResult, User user) throws Throwable {
        recordUserChangeDetected(true);
        this.userSubject.onNext(user);
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }

    public /* synthetic */ void lambda$userChanged$3$InterprocessUserSupplier(BroadcastReceiver.PendingResult pendingResult, Throwable th) throws Throwable {
        recordUserChangeDetected(false);
        Logger.e("Exception in detecting user changed", th);
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }

    @Override // com.amazon.alexa.accessory.UserSupplier
    public Observable<User> queryUser() {
        return Observable.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessUserSupplier$qAKfTb2w1chwMZmZk3dpvGzOC1Q
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return InterprocessUserSupplier.this.lambda$queryUser$0$InterprocessUserSupplier();
            }
        });
    }

    public void setAccessoryMetricsService(AccessoryMetricsService accessoryMetricsService) {
        Preconditions.notNull(accessoryMetricsService, "metricsService");
        this.metricsService = accessoryMetricsService;
    }

    @VisibleForTesting
    InterprocessUserSupplier(Context context, AccountManagerWrapper accountManagerWrapper, AccessoryMetricsService accessoryMetricsService, long j, int i) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(accountManagerWrapper, "accountManagerWrapper");
        Preconditions.notNull(accessoryMetricsService, "metricsService");
        this.interprocessAccountChangedListener = new InterprocessAccountChangedListener(context, new InterprocessAccountChangedListener.AccountChangedCallback() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessUserSupplier$I9hhQJidPhAsXvpGW44JWPFO7uA
            @Override // com.amazon.alexa.accessorykit.interprocess.InterprocessUserSupplier.InterprocessAccountChangedListener.AccountChangedCallback
            public final void onAccountChanged(BroadcastReceiver.PendingResult pendingResult) {
                InterprocessUserSupplier.this.userChanged(pendingResult);
            }
        });
        this.accountManagerWrapper = accountManagerWrapper;
        this.metricsService = accessoryMetricsService;
        this.retrievalTimeoutSeconds = j;
        this.retryCount = i;
        this.hasClearedCacheInitially = false;
        this.userSubject = PublishSubject.create().toSerialized();
    }
}
