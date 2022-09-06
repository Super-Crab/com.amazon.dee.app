package com.amazon.alexa.accessory.repositories.state.plugins;

import com.amazon.alexa.accessory.internal.monitor.A2dpPlayingMonitor;
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
public final class A2dpPlayingPlugin implements PhoneStatePlugin {
    private final A2dpPlayingMonitor monitor;

    public A2dpPlayingPlugin(A2dpPlayingMonitor a2dpPlayingMonitor) {
        Preconditions.notNull(a2dpPlayingMonitor, "monitor");
        this.monitor = a2dpPlayingMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FlowableEmitter flowableEmitter, boolean z) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(StateOuterClass.State.newBuilder().setFeature(StateFeature.BLUETOOTH_A2DP_ACTIVE.toInteger()).setBoolean(z).mo10084build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$3(SingleEmitter singleEmitter, boolean z) {
        if (singleEmitter.isDisposed()) {
            return;
        }
        singleEmitter.onSuccess(StateOuterClass.State.newBuilder().setFeature(StateFeature.BLUETOOTH_A2DP_ACTIVE.toInteger()).setBoolean(z).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$A2dpPlayingPlugin$_qCuy90G8RDl6aaUZUcwOC7Kiiw
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                A2dpPlayingPlugin.this.lambda$getState$5$A2dpPlayingPlugin(singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$getState$5$A2dpPlayingPlugin(final SingleEmitter singleEmitter) throws Throwable {
        final A2dpPlayingMonitor.Observer observer = new A2dpPlayingMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$A2dpPlayingPlugin$zaQiPjY_Jc3vuIgNX-jdcoJfsQE
            @Override // com.amazon.alexa.accessory.internal.monitor.A2dpPlayingMonitor.Observer
            public final void onA2dpPlayingState(boolean z) {
                A2dpPlayingPlugin.lambda$null$3(SingleEmitter.this, z);
            }
        };
        this.monitor.addObserver(observer);
        singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$A2dpPlayingPlugin$tvla4pOGcFTJxPp2lR26L11WuzM
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                A2dpPlayingPlugin.this.lambda$null$4$A2dpPlayingPlugin(observer);
            }
        });
    }

    public /* synthetic */ void lambda$null$1$A2dpPlayingPlugin(A2dpPlayingMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$null$4$A2dpPlayingPlugin(A2dpPlayingMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$A2dpPlayingPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final A2dpPlayingMonitor.Observer observer = new A2dpPlayingMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$A2dpPlayingPlugin$bDbm02n5lCp3SPf2IoAkgOfAKRs
            @Override // com.amazon.alexa.accessory.internal.monitor.A2dpPlayingMonitor.Observer
            public final void onA2dpPlayingState(boolean z) {
                A2dpPlayingPlugin.lambda$null$0(FlowableEmitter.this, z);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$A2dpPlayingPlugin$7FCqmsS1fcgr01cUl93Rflc7fHk
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                A2dpPlayingPlugin.this.lambda$null$1$A2dpPlayingPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$A2dpPlayingPlugin$Qp4XuMPuQahKvY__qocIl-08604
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                A2dpPlayingPlugin.this.lambda$queryState$2$A2dpPlayingPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST).distinctUntilChanged();
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state BLUETOOTH_A2DP_ACTIVE"));
    }
}
