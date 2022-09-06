package com.amazon.alexa.accessory.notificationpublisher.eventBus;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes.dex */
public final class EventBusManager {
    public static final int AUDIO_PRIORITY_FLAG_RESET_TIMER_IN_MILLISECOND = 10000;
    private static final String EVENT_TYPE_MESSAGE_FILTER = "Zion:EventBus:*";
    private static final String HIGHER_PRIORITY_AUDIO_FLOW_END_MESSAGE = "Zion:EventBus:HigherPriorityAudioFlowEnded";
    private static final String HIGHER_PRIORITY_AUDIO_FLOW_START_MESSAGE = "Zion:EventBus:HigherPriorityAudioFlowStarted";
    private static final String TAG = "EventBusManager";

    private EventBusManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startResetFlagTimer() {
        new Timer().schedule(new TimerTask() { // from class: com.amazon.alexa.accessory.notificationpublisher.eventBus.EventBusManager.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                AudioFocusManager.getInstance().setHigherPriorityPlaybackState(AudioFocusManager.HigherPriorityAudioPlaybackState.NONE);
            }
        }, 10000L);
    }

    public static void subscribeToEventBusMessages() {
        Log.d(TAG, "Subscribing to event bus message");
        DependencyProvider.getEventBus().getNewSubscriber().subscribeFilter(new EventTypeMessageFilter(EVENT_TYPE_MESSAGE_FILTER), new MessageHandler() { // from class: com.amazon.alexa.accessory.notificationpublisher.eventBus.EventBusManager.1
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                char c;
                String eventType = message.getEventType();
                int hashCode = eventType.hashCode();
                if (hashCode != -2063621416) {
                    if (hashCode == 1231673041 && eventType.equals(EventBusManager.HIGHER_PRIORITY_AUDIO_FLOW_END_MESSAGE)) {
                        c = 1;
                    }
                    c = 65535;
                } else {
                    if (eventType.equals(EventBusManager.HIGHER_PRIORITY_AUDIO_FLOW_START_MESSAGE)) {
                        c = 0;
                    }
                    c = 65535;
                }
                if (c == 0) {
                    EventBusManager.startResetFlagTimer();
                    AudioFocusManager.getInstance().setHigherPriorityPlaybackState(AudioFocusManager.HigherPriorityAudioPlaybackState.PLAYING);
                } else if (c != 1) {
                    GeneratedOutlineSupport1.outline165("Non actionable message ", eventType, EventBusManager.TAG);
                } else {
                    AudioFocusManager.getInstance().setHigherPriorityPlaybackState(AudioFocusManager.HigherPriorityAudioPlaybackState.NONE);
                }
            }
        });
    }
}
