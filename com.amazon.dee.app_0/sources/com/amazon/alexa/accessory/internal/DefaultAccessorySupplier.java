package com.amazon.alexa.accessory.internal;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryLinkListener;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySupplier;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.CurrentTimeMillisProvider;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeInvalidator;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class DefaultAccessorySupplier implements AccessorySupplier {
    private final CurrentTimeMillisProvider currentTimeMillisProvider;
    private final DeviceSupplierV2 deviceSupplier;
    private final Subject<Accessory> expiredStandbys;
    private final Handler handler;
    private final KeyExchangeInvalidator keyExchangeInvalidator;
    private final SessionSupplier sessionSupplier;
    private final Map<String, Runnable> standbyExpirationRunnables;
    private final Subject<Accessory> standbys;

    public DefaultAccessorySupplier(DeviceSupplierV2 deviceSupplierV2, KeyExchangeInvalidator keyExchangeInvalidator, SessionSupplier sessionSupplier) {
        this(deviceSupplierV2, keyExchangeInvalidator, sessionSupplier, new CurrentTimeMillisProvider(), new HashMap(), new Handler(Looper.getMainLooper()));
    }

    private Runnable createStandbyRunnable(final Accessory accessory, final long j) {
        Preconditions.precondition(j > 0, "timeoutInMs > 0");
        return new Runnable() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$6DSpLFhwXzZHPMjAtqWFAnWLbl0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultAccessorySupplier.this.lambda$createStandbyRunnable$13$DefaultAccessorySupplier(accessory, j);
            }
        };
    }

    private void findAndRemoveExpirationRunnable(String str) {
        synchronized (this.standbyExpirationRunnables) {
            Runnable remove = this.standbyExpirationRunnables.remove(str);
            if (remove != null) {
                this.handler.removeCallbacks(remove);
            }
        }
    }

    private static boolean isAlreadyStandbyTimeout(DeviceGroup deviceGroup, long j) {
        return deviceGroup.getCondition(j) == DeviceGroup.Condition.STANDBY_TIMEOUT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$link$4(Accessory accessory, AccessoryLinkListener accessoryLinkListener) throws Throwable {
        Logger.d("Accessory %s is linked", accessory);
        accessoryLinkListener.onAccessoryLinked(accessory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$link$5(Accessory accessory, AccessoryLinkListener accessoryLinkListener, Throwable th) throws Throwable {
        Logger.d("ERROR: Failed to link an accessory " + accessory + "!", th);
        Logger.e("Failed to link an accessory " + RedactionUtil.redact(accessory) + "!", th);
        accessoryLinkListener.onAccessoryLinkFailed(accessory, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$12(Accessory accessory, Throwable th) throws Throwable {
        Logger.d("ERROR: Failed to get DeviceGroup for Accessory %s", th, accessory);
        Logger.e("Failed to get DeviceGroup for Accessory %s", th, RedactionUtil.redact(accessory));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$unlink$1(Accessory accessory, AccessoryLinkListener accessoryLinkListener) throws Throwable {
        Logger.d("An accessory %s was unlinked", accessory);
        accessoryLinkListener.onAccessoryUnlinked(accessory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$unlink$2(Accessory accessory, AccessoryLinkListener accessoryLinkListener, Throwable th) throws Throwable {
        Logger.d("ERROR: Unable to unlink an accessory " + accessory, th);
        Logger.e("Unable to unlink an accessory " + RedactionUtil.redact(accessory), th);
        accessoryLinkListener.onAccessoryUnlinkFailed(accessory, th);
    }

    private void setStandbyExpirationRunnable(Accessory accessory, long j) {
        Preconditions.precondition(j > 0, "timeoutInMs > 0");
        Runnable createStandbyRunnable = createStandbyRunnable(accessory, j);
        synchronized (this.standbyExpirationRunnables) {
            this.standbyExpirationRunnables.put(accessory.getAddress(), createStandbyRunnable);
        }
        this.handler.postDelayed(createStandbyRunnable, j);
    }

    @Override // com.amazon.alexa.accessory.AccessorySupplier
    public Single<Boolean> isConnectible(Accessory accessory) {
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        return this.deviceSupplier.getDeviceGroup(accessory.getAddress()).map(new Function() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$hDnNXHR8vyIIedeNYO7cxfl9LBs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessorySupplier.this.lambda$isConnectible$8$DefaultAccessorySupplier((DeviceGroup) obj);
            }
        });
    }

    public /* synthetic */ void lambda$createStandbyRunnable$13$DefaultAccessorySupplier(final Accessory accessory, long j) {
        Logger.d("AccessorySupplier: %s standby timed out after %dms", accessory, Long.valueOf(j));
        synchronized (this.standbyExpirationRunnables) {
            this.standbyExpirationRunnables.remove(accessory.getAddress());
        }
        this.deviceSupplier.getDeviceGroup(accessory.getAddress()).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$pagQ5MxJvqJ1KnvtInYRRZqkPi4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessorySupplier.this.lambda$null$10$DefaultAccessorySupplier((DeviceGroup) obj);
            }
        }).subscribe(new Action() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$A4JtYpy5eOtUWzVmrsElOZ_gIgU
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultAccessorySupplier.this.lambda$null$11$DefaultAccessorySupplier(accessory);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$aXuI1TWloa4d0R3hMHhFIt-PE_E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessorySupplier.lambda$null$12(Accessory.this, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ Boolean lambda$isConnectible$8$DefaultAccessorySupplier(DeviceGroup deviceGroup) throws Throwable {
        DeviceGroup.Condition condition = deviceGroup.getCondition(this.currentTimeMillisProvider.provideCurrentTimeMillis());
        return Boolean.valueOf(condition == DeviceGroup.Condition.ACTIVE || condition == DeviceGroup.Condition.STANDBY_INDEFINITE);
    }

    public /* synthetic */ CompletableSource lambda$link$3$DefaultAccessorySupplier(Accessory accessory, Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            return Completable.complete();
        }
        Logger.d("Linking an accessory %s", accessory);
        return this.deviceSupplier.addDeviceGroup(new DeviceGroup.Builder().setConditionActive().identifier(accessory.getAddress()).transport(accessory.getTransport()).build()).ignoreElement();
    }

    public /* synthetic */ CompletableSource lambda$monitorUnexpiredStandbyTimeoutAccessory$9$DefaultAccessorySupplier(Accessory accessory, DeviceGroup deviceGroup) throws Throwable {
        long standbyExpirationTimestamp = deviceGroup.getStandbyExpirationTimestamp() - this.currentTimeMillisProvider.provideCurrentTimeMillis();
        if (standbyExpirationTimestamp > 0) {
            setStandbyExpirationRunnable(accessory, standbyExpirationTimestamp);
            this.standbys.onNext(accessory);
            Logger.d("Accessory %s timer still valid. Installed runnable and notified of standby", accessory.getAddress());
        }
        return Completable.complete();
    }

    public /* synthetic */ CompletableSource lambda$null$10$DefaultAccessorySupplier(DeviceGroup deviceGroup) throws Throwable {
        return this.deviceSupplier.updateDeviceGroup(DeviceGroup.newBuilder(deviceGroup).setConditionActive().build());
    }

    public /* synthetic */ void lambda$null$11$DefaultAccessorySupplier(Accessory accessory) throws Throwable {
        this.expiredStandbys.onNext(accessory);
    }

    public /* synthetic */ void lambda$null$6$DefaultAccessorySupplier(int i, boolean z, Accessory accessory, long j) throws Throwable {
        if (i <= 0 || z) {
            return;
        }
        setStandbyExpirationRunnable(accessory, j);
        this.standbys.onNext(accessory);
    }

    public /* synthetic */ CompletableSource lambda$standby$7$DefaultAccessorySupplier(long j, final Accessory accessory, DeviceGroup.Condition condition, DeviceGroup.StandbyReason standbyReason, long j2, final int i, final long j3, DeviceGroup deviceGroup) throws Throwable {
        final boolean isAlreadyStandbyTimeout = isAlreadyStandbyTimeout(deviceGroup, j);
        if (isAlreadyStandbyTimeout) {
            findAndRemoveExpirationRunnable(accessory.getAddress());
        }
        DeviceGroup.Builder newBuilder = DeviceGroup.newBuilder(deviceGroup);
        if (condition == DeviceGroup.Condition.STANDBY_INDEFINITE) {
            newBuilder.setConditionStandbyIndefinite(standbyReason);
        } else {
            newBuilder.setConditionStandbyTimeout(j2, standbyReason);
        }
        return this.deviceSupplier.updateDeviceGroup(newBuilder.build()).andThen(Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$EBGOY6M2LC9ZHUethEpnnJjYTp4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultAccessorySupplier.this.lambda$null$6$DefaultAccessorySupplier(i, isAlreadyStandbyTimeout, accessory, j3);
            }
        }));
    }

    public /* synthetic */ CompletableSource lambda$unlink$0$DefaultAccessorySupplier(DeviceGroup deviceGroup) throws Throwable {
        return this.deviceSupplier.removeDeviceGroup(deviceGroup);
    }

    @Override // com.amazon.alexa.accessory.AccessorySupplier
    public void link(final Accessory accessory, final AccessoryLinkListener accessoryLinkListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(accessoryLinkListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.deviceSupplier.hasDeviceGroup(accessory.getAddress()).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$MWV7xYoeYY8t1RBLSTKZ9EBJxEU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessorySupplier.this.lambda$link$3$DefaultAccessorySupplier(accessory, (Boolean) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$SbMwm_jqI6dTNjuQl0puqpYhp64
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultAccessorySupplier.lambda$link$4(Accessory.this, accessoryLinkListener);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$ns8eBx7LiM6J5vDGd5STugaOI1o
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessorySupplier.lambda$link$5(Accessory.this, accessoryLinkListener, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.AccessorySupplier
    public Completable monitorUnexpiredStandbyTimeoutAccessory(final Accessory accessory) {
        Logger.d("Start remaining timer for accessory %s", accessory.getAddress());
        return this.deviceSupplier.getDeviceGroup(accessory.getAddress()).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$1LWMmj3fFK_ICfQygbwwhkvNQnA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessorySupplier.this.lambda$monitorUnexpiredStandbyTimeoutAccessory$9$DefaultAccessorySupplier(accessory, (DeviceGroup) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.AccessorySupplier
    public Observable<Accessory> queryExpiredStandbyAccessories() {
        return this.expiredStandbys;
    }

    @Override // com.amazon.alexa.accessory.AccessorySupplier
    public Observable<Accessory> queryStandbyAccessories() {
        return this.standbys;
    }

    @Override // com.amazon.alexa.accessory.AccessorySupplier
    public Completable standby(final Accessory accessory, final int i, final DeviceGroup.StandbyReason standbyReason) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNegative(i, "timeoutSeconds");
        Preconditions.notNull(standbyReason, "standbyReason");
        final long j = i * 1000;
        final long provideCurrentTimeMillis = this.currentTimeMillisProvider.provideCurrentTimeMillis();
        final DeviceGroup.Condition condition = j == DeviceGroup.Condition.STANDBY_INDEFINITE.getSpecialTimestamp() ? DeviceGroup.Condition.STANDBY_INDEFINITE : DeviceGroup.Condition.STANDBY_TIMEOUT;
        final long j2 = condition == DeviceGroup.Condition.STANDBY_INDEFINITE ? j : provideCurrentTimeMillis + j;
        return this.deviceSupplier.getDeviceGroup(accessory.getAddress()).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$k720YgetF772ovsokmQWTBxxjBE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessorySupplier.this.lambda$standby$7$DefaultAccessorySupplier(provideCurrentTimeMillis, accessory, condition, standbyReason, j2, i, j, (DeviceGroup) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.AccessorySupplier
    public void unlink(final Accessory accessory, final AccessoryLinkListener accessoryLinkListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(accessoryLinkListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        AccessorySession session = this.sessionSupplier.getSession(accessory);
        if (session != null) {
            session.release();
            AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_UNLINK, true, null);
        }
        this.keyExchangeInvalidator.invalidateAllCryptoKeys(accessory.getAddress());
        this.deviceSupplier.getDeviceGroup(accessory.getAddress()).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$7kqevJnq70l8j4VVO46tS_80gYw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessorySupplier.this.lambda$unlink$0$DefaultAccessorySupplier((DeviceGroup) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$c789STx2OBV6zcNnbW0kZhnUGXA
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DefaultAccessorySupplier.lambda$unlink$1(Accessory.this, accessoryLinkListener);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$DefaultAccessorySupplier$vBu5soplDjKJ5FInD3t13qc0pt0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessorySupplier.lambda$unlink$2(Accessory.this, accessoryLinkListener, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    DefaultAccessorySupplier(DeviceSupplierV2 deviceSupplierV2, KeyExchangeInvalidator keyExchangeInvalidator, SessionSupplier sessionSupplier, CurrentTimeMillisProvider currentTimeMillisProvider, HashMap<String, Runnable> hashMap, Handler handler) {
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        Preconditions.notNull(keyExchangeInvalidator, "keyExchangeInvalidator");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(currentTimeMillisProvider, "currentTimeMillisProvider");
        Preconditions.notNull(hashMap, "standbyExpirationRunnables");
        Preconditions.notNull(handler, "handler");
        this.deviceSupplier = deviceSupplierV2;
        this.keyExchangeInvalidator = keyExchangeInvalidator;
        this.sessionSupplier = sessionSupplier;
        this.handler = handler;
        this.currentTimeMillisProvider = currentTimeMillisProvider;
        this.standbyExpirationRunnables = hashMap;
        this.expiredStandbys = PublishSubject.create().toSerialized();
        this.standbys = PublishSubject.create().toSerialized();
    }
}
