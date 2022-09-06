package com.amazon.alexa.accessory.avsclient.bootup;

import com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusMonitor;
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
import io.reactivex.rxjava3.functions.Cancellable;
/* loaded from: classes.dex */
public class DownChannelStatusPlugin implements PhoneStatePlugin {
    private final DownChannelStatusMonitor monitor;

    public DownChannelStatusPlugin(DownChannelStatusMonitor downChannelStatusMonitor) {
        Preconditions.notNull(downChannelStatusMonitor, "monitor");
        this.monitor = downChannelStatusMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FlowableEmitter flowableEmitter, boolean z) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(StateOuterClass.State.newBuilder().setFeature(StateFeature.CONNECTION_SUCCEEDED.toInteger()).setBoolean(z).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.just(StateOuterClass.State.newBuilder().setFeature(StateFeature.CONNECTION_SUCCEEDED.toInteger()).setBoolean(this.monitor.getIsConnected()).mo10084build());
    }

    public /* synthetic */ void lambda$null$1$DownChannelStatusPlugin(DownChannelStatusMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$DownChannelStatusPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final DownChannelStatusMonitor.Observer observer = new DownChannelStatusMonitor.Observer() { // from class: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$DownChannelStatusPlugin$e3FYS2atLzVbZtZMxTPvNan0gr8
            @Override // com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusMonitor.Observer
            public final void onDownChannelStatusChanged(boolean z) {
                DownChannelStatusPlugin.lambda$null$0(FlowableEmitter.this, z);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$DownChannelStatusPlugin$dYcXxCiMEA6o-dPA-bPuENge5aY
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                DownChannelStatusPlugin.this.lambda$null$1$DownChannelStatusPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$DownChannelStatusPlugin$IKMhF1-KjnDBhm0TzB3-esM6_6U
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                DownChannelStatusPlugin.this.lambda$queryState$2$DownChannelStatusPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state CONNECTION_SUCCEEDED"));
    }
}
