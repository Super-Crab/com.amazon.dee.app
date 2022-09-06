package com.amazon.alexa.accessory.repositories.state.plugins;

import com.amazon.alexa.accessory.internal.monitor.NotificationFilterStatusMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes6.dex */
public final class NotificationFilterStatusPlugin implements PhoneStatePlugin {
    private static final String TAG = "NotificationFilterStatusPlugin";
    private final NotificationFilterStatusMonitor monitor;

    public NotificationFilterStatusPlugin(NotificationFilterStatusMonitor notificationFilterStatusMonitor) {
        Preconditions.notNull(notificationFilterStatusMonitor, "monitor");
        this.monitor = notificationFilterStatusMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FlowableEmitter flowableEmitter, boolean z) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        Logger.d("NotificationFilterStatusPlugin - queryState - observer isEnabled: " + z);
        flowableEmitter.onNext(StateOuterClass.State.newBuilder().setFeature(StateFeature.NOTIFICATION_FORWARDING_ENABLED.toInteger()).setBoolean(z).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        Logger.d("NotificationFilterStatusPlugin - getState");
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$NotificationFilterStatusPlugin$ai5GhBaXzXDv3ZAhDSXECMpC_xQ
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return NotificationFilterStatusPlugin.this.lambda$getState$3$NotificationFilterStatusPlugin();
            }
        }).subscribeOn(Schedulers.io());
    }

    public /* synthetic */ SingleSource lambda$getState$3$NotificationFilterStatusPlugin() throws Throwable {
        return Single.just(StateOuterClass.State.newBuilder().setFeature(StateFeature.NOTIFICATION_FORWARDING_ENABLED.toInteger()).setBoolean(this.monitor.isNotificationForwardingEnabled()).mo10084build());
    }

    public /* synthetic */ void lambda$null$1$NotificationFilterStatusPlugin(NotificationFilterStatusMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$NotificationFilterStatusPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final NotificationFilterStatusMonitor.Observer observer = new NotificationFilterStatusMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$NotificationFilterStatusPlugin$CD04ICgpT5YKejl7l1d-I_eNht8
            @Override // com.amazon.alexa.accessory.internal.monitor.NotificationFilterStatusMonitor.Observer
            public final void onForwardNotificationChanged(boolean z) {
                NotificationFilterStatusPlugin.lambda$null$0(FlowableEmitter.this, z);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$NotificationFilterStatusPlugin$Sip1FNdsoML7NvQ4k973rx40CsE
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                NotificationFilterStatusPlugin.this.lambda$null$1$NotificationFilterStatusPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$NotificationFilterStatusPlugin$sBe0sm8Lo-pRArcT0cpjtaDT81Q
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                NotificationFilterStatusPlugin.this.lambda$queryState$2$NotificationFilterStatusPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state NOTIFICATION_FORWARDING_ENABLED"));
    }
}
