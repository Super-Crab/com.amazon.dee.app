package com.amazon.alexa.accessory.repositories.state.plugins;

import com.amazon.alexa.accessory.internal.monitor.ScoStatusMonitor;
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
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
/* loaded from: classes6.dex */
public final class ScoStatusPlugin implements PhoneStatePlugin {
    private final ScoStatusMonitor monitor;

    public ScoStatusPlugin(ScoStatusMonitor scoStatusMonitor) {
        Preconditions.notNull(scoStatusMonitor, "monitor");
        this.monitor = scoStatusMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FlowableEmitter flowableEmitter, boolean z) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(StateOuterClass.State.newBuilder().setFeature(StateFeature.BLUETOOTH_HFP_ACTIVE.toInteger()).setBoolean(z).mo10084build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$3(SingleEmitter singleEmitter, boolean z) {
        if (singleEmitter.isDisposed()) {
            return;
        }
        singleEmitter.onSuccess(StateOuterClass.State.newBuilder().setFeature(StateFeature.BLUETOOTH_HFP_ACTIVE.toInteger()).setBoolean(z).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$ScoStatusPlugin$rio814DOPfbdFOytiht6jcK-dMM
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                ScoStatusPlugin.this.lambda$getState$5$ScoStatusPlugin(singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$getState$5$ScoStatusPlugin(final SingleEmitter singleEmitter) throws Throwable {
        final ScoStatusMonitor.Observer observer = new ScoStatusMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$ScoStatusPlugin$qg4UgsQ5AhmQTa_HCmkjEAM0rE4
            @Override // com.amazon.alexa.accessory.internal.monitor.ScoStatusMonitor.Observer
            public final void onScoConnectionState(boolean z) {
                ScoStatusPlugin.lambda$null$3(SingleEmitter.this, z);
            }
        };
        this.monitor.addObserver(observer);
        singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$ScoStatusPlugin$WfMNtCke19Hbec7zDOzQ5-5yRSY
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                ScoStatusPlugin.this.lambda$null$4$ScoStatusPlugin(observer);
            }
        });
    }

    public /* synthetic */ void lambda$null$1$ScoStatusPlugin(ScoStatusMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$null$4$ScoStatusPlugin(ScoStatusMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$ScoStatusPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final ScoStatusMonitor.Observer observer = new ScoStatusMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$ScoStatusPlugin$Nq0D_LAEXyWZ7jC2a2I0_hcLhQQ
            @Override // com.amazon.alexa.accessory.internal.monitor.ScoStatusMonitor.Observer
            public final void onScoConnectionState(boolean z) {
                ScoStatusPlugin.lambda$null$0(FlowableEmitter.this, z);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$ScoStatusPlugin$JYLR5a5uLbVBzDUNHzi6SV4kgf4
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                ScoStatusPlugin.this.lambda$null$1$ScoStatusPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$ScoStatusPlugin$ndqgoGf1X0bmATZY2TgPCsRfEyg
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                ScoStatusPlugin.this.lambda$queryState$2$ScoStatusPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST).distinctUntilChanged();
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state BLUETOOTH_HFP_ACTIVE"));
    }
}
