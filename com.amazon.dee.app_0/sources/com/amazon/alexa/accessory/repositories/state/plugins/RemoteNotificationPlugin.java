package com.amazon.alexa.accessory.repositories.state.plugins;

import com.amazon.alexa.accessory.internal.monitor.RemoteNotificationMonitor;
import com.amazon.alexa.accessory.internal.monitor.RemoteNotificationStatus;
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
import java.util.Set;
/* loaded from: classes6.dex */
public final class RemoteNotificationPlugin implements PhoneStatePlugin {
    private final RemoteNotificationMonitor monitor;

    public RemoteNotificationPlugin(RemoteNotificationMonitor remoteNotificationMonitor) {
        Preconditions.notNull(remoteNotificationMonitor, "monitor");
        this.monitor = remoteNotificationMonitor;
    }

    private StateOuterClass.State getRemoteNotificationState(Set<RemoteNotificationStatus> set) {
        int i;
        if (set.isEmpty()) {
            i = RemoteNotificationStatus.NO_ACTIVITY.value;
        } else {
            int i2 = 0;
            for (RemoteNotificationStatus remoteNotificationStatus : set) {
                i2 |= remoteNotificationStatus.value;
            }
            i = i2;
        }
        return StateOuterClass.State.newBuilder().setFeature(StateFeature.REMOTE_NOTIFICATION.toInteger()).setInteger(i).mo10084build();
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$RemoteNotificationPlugin$64TUJsy47p0k06LAjjknMIvVyX4
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                RemoteNotificationPlugin.this.lambda$getState$5$RemoteNotificationPlugin(singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$getState$5$RemoteNotificationPlugin(final SingleEmitter singleEmitter) throws Throwable {
        final RemoteNotificationMonitor.Observer observer = new RemoteNotificationMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$RemoteNotificationPlugin$vz809oSJWHzynJkFJqy6T-lLrkA
            @Override // com.amazon.alexa.accessory.internal.monitor.RemoteNotificationMonitor.Observer
            public final void onRemoteNotification(Set set) {
                RemoteNotificationPlugin.this.lambda$null$3$RemoteNotificationPlugin(singleEmitter, set);
            }
        };
        this.monitor.addObserver(observer);
        singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$RemoteNotificationPlugin$vXcy13WKN2tC8BJjCd0THsuYL7g
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                RemoteNotificationPlugin.this.lambda$null$4$RemoteNotificationPlugin(observer);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$RemoteNotificationPlugin(FlowableEmitter flowableEmitter, Set set) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(getRemoteNotificationState(set));
    }

    public /* synthetic */ void lambda$null$1$RemoteNotificationPlugin(RemoteNotificationMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$null$3$RemoteNotificationPlugin(SingleEmitter singleEmitter, Set set) {
        if (singleEmitter.isDisposed()) {
            return;
        }
        singleEmitter.onSuccess(getRemoteNotificationState(set));
    }

    public /* synthetic */ void lambda$null$4$RemoteNotificationPlugin(RemoteNotificationMonitor.Observer observer) throws Throwable {
        this.monitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$RemoteNotificationPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final RemoteNotificationMonitor.Observer observer = new RemoteNotificationMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$RemoteNotificationPlugin$s3kj7hSqEFLfijL6MNwd55i9-jE
            @Override // com.amazon.alexa.accessory.internal.monitor.RemoteNotificationMonitor.Observer
            public final void onRemoteNotification(Set set) {
                RemoteNotificationPlugin.this.lambda$null$0$RemoteNotificationPlugin(flowableEmitter, set);
            }
        };
        this.monitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$RemoteNotificationPlugin$XAqma5PCfyUEkKLYN_r52rbgxu0
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                RemoteNotificationPlugin.this.lambda$null$1$RemoteNotificationPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$RemoteNotificationPlugin$1ey7JHhCYgHmh30MGjaRmAN7uvM
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                RemoteNotificationPlugin.this.lambda$queryState$2$RemoteNotificationPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST).distinctUntilChanged();
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state: REMOTE_NOTIFICATION"));
    }
}
