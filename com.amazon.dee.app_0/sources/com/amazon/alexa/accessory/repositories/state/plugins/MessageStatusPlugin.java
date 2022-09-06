package com.amazon.alexa.accessory.repositories.state.plugins;

import android.os.Looper;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor;
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
public final class MessageStatusPlugin implements PhoneStatePlugin {
    public static final int NO_UNREAD_MESSAGES = 0;
    public static final int UNREAD_MESSAGES = 1;
    private final MessageNotificationStateMonitor messageNotificationStateMonitor;

    public MessageStatusPlugin(MessageNotificationStateMonitor messageNotificationStateMonitor) {
        Preconditions.notNull(messageNotificationStateMonitor, "monitor");
        this.messageNotificationStateMonitor = messageNotificationStateMonitor;
    }

    private static void assertNotMainThread() {
        if (!currentThreadIsMainThread()) {
            return;
        }
        throw new RuntimeException("Current thread is main thread");
    }

    private static boolean currentThreadIsMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    private StateOuterClass.State getMessageNotificationState(int i) {
        return StateOuterClass.State.newBuilder().setFeature(StateFeature.MESSAGE_NOTIFICATION.toInteger()).setInteger(i).mo10084build();
    }

    private int getMessageNotificationStateValue(boolean z) {
        return z ? 1 : 0;
    }

    private boolean isUnreadMessageAvailable() {
        try {
            return this.messageNotificationStateMonitor.isUnreadMessageAvailable();
        } catch (Exception e) {
            Logger.e("MessageNotificationStateMonitor has exception for isUnreadMessageAvailable", e);
            return false;
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$MessageStatusPlugin$VcHIbwm061WynpyzY7GIcK439xo
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return MessageStatusPlugin.this.lambda$getState$3$MessageStatusPlugin();
            }
        }).subscribeOn(Schedulers.io());
    }

    public /* synthetic */ SingleSource lambda$getState$3$MessageStatusPlugin() throws Throwable {
        assertNotMainThread();
        return Single.just(getMessageNotificationState(getMessageNotificationStateValue(isUnreadMessageAvailable())));
    }

    public /* synthetic */ void lambda$null$0$MessageStatusPlugin(FlowableEmitter flowableEmitter, boolean z) {
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(getMessageNotificationState(getMessageNotificationStateValue(z)));
    }

    public /* synthetic */ void lambda$null$1$MessageStatusPlugin(MessageNotificationStateMonitor.Observer observer) throws Throwable {
        this.messageNotificationStateMonitor.removeObserver(observer);
    }

    public /* synthetic */ void lambda$queryState$2$MessageStatusPlugin(final FlowableEmitter flowableEmitter) throws Throwable {
        final MessageNotificationStateMonitor.Observer observer = new MessageNotificationStateMonitor.Observer() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$MessageStatusPlugin$PXKUf4nd3YVD8MAH2Wjh0LFPMUE
            @Override // com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor.Observer
            public final void onUnreadMessageStatusChanged(boolean z) {
                MessageStatusPlugin.this.lambda$null$0$MessageStatusPlugin(flowableEmitter, z);
            }
        };
        this.messageNotificationStateMonitor.addObserver(observer);
        flowableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$MessageStatusPlugin$doF-LVLIBHgn5t2_qOq80_eMv6Y
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                MessageStatusPlugin.this.lambda$null$1$MessageStatusPlugin(observer);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        return Flowable.create(new FlowableOnSubscribe() { // from class: com.amazon.alexa.accessory.repositories.state.plugins.-$$Lambda$MessageStatusPlugin$w2L1M3W85y9sRSxgMJSm9emJMFM
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                MessageStatusPlugin.this.lambda$queryState$2$MessageStatusPlugin(flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        return Completable.error(new UnsupportedOperationException("Set not supported for state MESSAGE_NOTIFICATION"));
    }
}
