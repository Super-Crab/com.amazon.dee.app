package com.amazon.alexa.accessory.repositories.state.plugins;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Cancellable;
/* loaded from: classes6.dex */
public final class CallNotificationPlugin implements PhoneStatePlugin {
    private final CallNotificationStateMonitor monitor;

    public CallNotificationPlugin(CallNotificationStateMonitor callNotificationStateMonitor) {
        Preconditions.notNull(callNotificationStateMonitor, "monitor");
        this.monitor = callNotificationStateMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FlowableEmitter flowableEmitter, int i) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(StateOuterClass.State.newBuilder().setFeature(StateFeature.CALL_NOTIFICATION.toInteger()).setInteger(i).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.just(StateOuterClass.State.newBuilder().setFeature(StateFeature.CALL_NOTIFICATION.toInteger()).setInteger(this.monitor.getCallNotificationStatus()).mo10084build());
    }

    public /* synthetic */ void lambda$null$1$CallNotificationPlugin(CallNotificationStateMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$CallNotificationPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final CallNotificationStateMonitor.Observer observer = new CallNotificationStateMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$CallNotificationPlugin$2EybMFFPfffokjBXuYvgD7jd7P0
            @Override // com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor.Observer
            public final void onCallStatusChanged(int i) {
                CallNotificationPlugin.lambda$null$0(FlowableEmitter.this, i);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$CallNotificationPlugin$YNbX2xPMsUramw6KXYthX_iGzSA
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                CallNotificationPlugin.this.lambda$null$1$CallNotificationPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$CallNotificationPlugin$K0ggNx1uupORD-xtkM10nOOgokM
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                CallNotificationPlugin.this.lambda$queryState$2$CallNotificationPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state Call Notification"));
    }
}
