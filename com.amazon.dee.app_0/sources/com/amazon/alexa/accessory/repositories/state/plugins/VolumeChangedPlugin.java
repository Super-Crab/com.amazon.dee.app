package com.amazon.alexa.accessory.repositories.state.plugins;

import com.amazon.alexa.accessory.internal.monitor.VolumeChangedMonitor;
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
public final class VolumeChangedPlugin implements PhoneStatePlugin {
    private final VolumeChangedMonitor monitor;

    public VolumeChangedPlugin(VolumeChangedMonitor volumeChangedMonitor) {
        Preconditions.notNull(volumeChangedMonitor, "monitor");
        this.monitor = volumeChangedMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FlowableEmitter flowableEmitter) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(StateOuterClass.State.newBuilder().setFeature(StateFeature.VOLUME_CHANGED_NOTIFICATION.toInteger()).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.error(new UnsupportedOperationException("Get not supported for state VOLUME_CHANGED_NOTIFICATION"));
    }

    public /* synthetic */ void lambda$null$1$VolumeChangedPlugin(VolumeChangedMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$VolumeChangedPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final VolumeChangedMonitor.Observer observer = new VolumeChangedMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$VolumeChangedPlugin$aPs9_nb0T79MR5yPq7Bm2w2ORiU
            @Override // com.amazon.alexa.accessory.internal.monitor.VolumeChangedMonitor.Observer
            public final void onVolumeChanged() {
                VolumeChangedPlugin.lambda$null$0(FlowableEmitter.this);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$VolumeChangedPlugin$EaTwy443VSrb8Sn53roYayFaJPY
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                VolumeChangedPlugin.this.lambda$null$1$VolumeChangedPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$VolumeChangedPlugin$S5Hcy4tUidZfTDxPPs5HXpwPgWU
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                VolumeChangedPlugin.this.lambda$queryState$2$VolumeChangedPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state VOLUME_CHANGED_NOTIFICATION"));
    }
}
