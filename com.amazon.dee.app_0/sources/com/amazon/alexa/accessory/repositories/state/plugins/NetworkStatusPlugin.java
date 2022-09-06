package com.amazon.alexa.accessory.repositories.state.plugins;

import com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor;
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
/* loaded from: classes6.dex */
public final class NetworkStatusPlugin implements PhoneStatePlugin {
    private static final int GOOD_HEALTH = 0;
    private static final int TOO_SLOW = 1;
    private static final int UNAVAILABLE = 2;
    private final NetworkStatusMonitor monitor;

    public NetworkStatusPlugin(NetworkStatusMonitor networkStatusMonitor) {
        Preconditions.notNull(networkStatusMonitor, "monitor");
        this.monitor = networkStatusMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FlowableEmitter flowableEmitter, boolean z) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(StateOuterClass.State.newBuilder().setFeature(StateFeature.DEVICE_NETWORK_CONNECTIVITY_STATUS.toInteger()).setInteger(z ? 0 : 2).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.just(StateOuterClass.State.newBuilder().setFeature(StateFeature.DEVICE_NETWORK_CONNECTIVITY_STATUS.toInteger()).setInteger(this.monitor.isConnected() ? 0 : 2).mo10084build());
    }

    public /* synthetic */ void lambda$null$1$NetworkStatusPlugin(NetworkStatusMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$NetworkStatusPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final NetworkStatusMonitor.Observer observer = new NetworkStatusMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$NetworkStatusPlugin$pfpLheUn-fpNBTbFehHhGFB6muI
            @Override // com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor.Observer
            public final void onNetworkStatusChanged(boolean z) {
                NetworkStatusPlugin.lambda$null$0(FlowableEmitter.this, z);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$NetworkStatusPlugin$pXTkk5RwbhWEBISiLFWKGIXHmFE
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                NetworkStatusPlugin.this.lambda$null$1$NetworkStatusPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$NetworkStatusPlugin$yLH5Yl6cSFPESECwCLqRdGqzpWE
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                NetworkStatusPlugin.this.lambda$queryState$2$NetworkStatusPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state DEVICE_NETWORK_CONNECTIVITY_STATUS"));
    }
}
