package com.amazon.alexa.accessorykit.finishsetup.presenters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.finishsetup.FinishSetupEvent;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.LinkedList;
import java.util.Queue;
/* loaded from: classes6.dex */
public final class FASCardPresenter implements FinishSetupPresenter, LifecycleEventListener {
    public static final String ACTION_DISMISS = "dismiss";
    public static final String ACTION_PRESENT = "present";
    public static final String DISMISS_EVENT = "accessories-fas-dismiss";
    public static final String INTENT_ACTION_FAS_CARD_PRESENTER = "com.amazon.alexa.accessorykit.action.fas.card.presenter";
    public static final String KEY_ACTION_TYPE = "action_type";
    public static final String KEY_FINISH_SETUP_EVENT = "finish_setup_event";
    public static final String PRESENT_EVENT = "accessories-fas-present";
    private static final String TAG = "FASCardPresenter:";
    private static FASCardPresenter instance;
    private final Context context;
    private final DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter;
    private final FinishSetupEvent.Transformer eventTransformer;
    private final FASCardEventReceiver fasCardEventReceiver = new FASCardEventReceiver();
    private final IntentFilter intentFilter = new IntentFilter(INTENT_ACTION_FAS_CARD_PRESENTER);
    private final Object lock = new Object();
    private boolean broadcastRegistered = false;
    private boolean shouldQueueEvents = true;
    private final Queue<Pair<String, FinishSetupEvent>> queuedEvents = new LinkedList();

    @VisibleForTesting
    /* loaded from: classes6.dex */
    class FASCardEventReceiver extends BroadcastReceiver {
        FASCardEventReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!FASCardPresenter.INTENT_ACTION_FAS_CARD_PRESENTER.equals(intent.getAction())) {
                Logger.d("%s received unsupported intent: %s", FASCardPresenter.TAG, intent.getAction());
                return;
            }
            String stringExtra = intent.getStringExtra(FASCardPresenter.KEY_ACTION_TYPE);
            FinishSetupEvent finishSetupEvent = new FinishSetupEvent(intent.getBundleExtra("finish_setup_event"));
            if (FASCardPresenter.ACTION_PRESENT.equals(stringExtra)) {
                Logger.d("%s received present event: %s", FASCardPresenter.TAG, finishSetupEvent);
                FASCardPresenter.this.present(finishSetupEvent);
            } else if (FASCardPresenter.ACTION_DISMISS.equals(stringExtra)) {
                Logger.d("%s received dismiss event: %s", FASCardPresenter.TAG, finishSetupEvent);
                FASCardPresenter.this.dismiss(finishSetupEvent);
            } else {
                Logger.d("%s received unknown event: %s", FASCardPresenter.TAG, finishSetupEvent);
            }
        }
    }

    public FASCardPresenter(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, ContainerProvider containerProvider, Context context) {
        this.eventEmitter = rCTDeviceEventEmitter;
        this.context = context;
        this.eventTransformer = new FinishSetupEvent.Transformer(containerProvider);
        instance = this;
    }

    @Nullable
    public static FASCardPresenter getInstance() {
        return instance;
    }

    private void sendEvent(String str, FinishSetupEvent finishSetupEvent) {
        Preconditions.precondition(!TextUtils.isEmpty(str), "eventName cannot be empty");
        Preconditions.notNull(finishSetupEvent, "event");
        this.eventEmitter.emit(str, this.eventTransformer.transformToMap(finishSetupEvent));
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.presenters.FinishSetupPresenter
    public boolean canPresent() {
        return true;
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.presenters.FinishSetupPresenter
    public void dismiss(FinishSetupEvent finishSetupEvent) {
        synchronized (this.lock) {
            if (this.shouldQueueEvents) {
                Logger.d("%s queueing FAS FinishSetupEvent dismiss event", TAG);
                this.queuedEvents.add(new Pair<>(DISMISS_EVENT, finishSetupEvent));
                return;
            }
            Logger.d("%s dismissed FAS Card with no delay", TAG);
            sendEvent(DISMISS_EVENT, finishSetupEvent);
        }
    }

    public void fasCardsAreReady() {
        synchronized (this.lock) {
            Logger.d("%s fasIsReady, flushing %d events", TAG, Integer.valueOf(this.queuedEvents.size()));
            this.shouldQueueEvents = false;
            while (!this.queuedEvents.isEmpty()) {
                Pair<String, FinishSetupEvent> poll = this.queuedEvents.poll();
                sendEvent((String) poll.first, (FinishSetupEvent) poll.second);
            }
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        synchronized (this.lock) {
            Logger.d("%s onHostDestroy, UI is destroyed, will queue events", TAG);
            this.shouldQueueEvents = true;
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.presenters.FinishSetupPresenter
    public void present(FinishSetupEvent finishSetupEvent) {
        synchronized (this.lock) {
            if (this.shouldQueueEvents) {
                Logger.d("%s queueing FAS FinishSetupEvent present event", TAG);
                this.queuedEvents.add(new Pair<>(PRESENT_EVENT, finishSetupEvent));
                return;
            }
            Logger.d("%s presented FAS Card with no delay", TAG);
            sendEvent(PRESENT_EVENT, finishSetupEvent);
        }
    }

    public void registerFASCardEventReceiver() {
        synchronized (this.lock) {
            if (this.broadcastRegistered) {
                Logger.d("%s FASCardEventReceiver already registered. Returning...", TAG);
                return;
            }
            Logger.d("%s FASCardEventReceiver registered.", TAG);
            this.broadcastRegistered = true;
            this.context.registerReceiver(this.fasCardEventReceiver, this.intentFilter, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION", null);
        }
    }

    public void unregisterFASCardEventReceiver() {
        synchronized (this.lock) {
            if (this.broadcastRegistered) {
                Logger.d("%s FASCardEventReceiver unregistered.", TAG);
                this.broadcastRegistered = false;
                this.context.unregisterReceiver(this.fasCardEventReceiver);
            }
        }
    }
}
