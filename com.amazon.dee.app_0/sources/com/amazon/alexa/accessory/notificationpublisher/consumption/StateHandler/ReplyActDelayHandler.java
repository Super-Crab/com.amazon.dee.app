package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ReplyActDelayHandler extends BaseStateHandler {
    private static final String TAG = "ReplyActDelayHandler";
    private static ReplyActDelayHandler instance;

    private ReplyActDelayHandler() {
        super(StateManager.STATE_REPLY_DELAY);
    }

    private void cancelActDelayTimer() {
        postEventMessageToTimerManager(1, 2);
    }

    public static synchronized ReplyActDelayHandler getInstance() {
        ReplyActDelayHandler replyActDelayHandler;
        synchronized (ReplyActDelayHandler.class) {
            if (instance == null) {
                instance = new ReplyActDelayHandler();
            }
            replyActDelayHandler = instance;
        }
        return replyActDelayHandler;
    }

    public static synchronized void releaseInstance() {
        synchronized (ReplyActDelayHandler.class) {
            instance = null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(@NonNull String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_TTS_PLAYED);
        postEventMessageToTimerManager(1, 1);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        super.handleGestureEvent(i, payload);
        if (i == 1) {
            Log.i(TAG, "handleGestureEvent - Gesture Play - Go to reply state handler");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_ACCEPT);
            transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, Integer.valueOf(i), null);
        } else if (i != 2) {
            GeneratedOutlineSupport1.outline151("handleGestureEvent - Invalid event Id: ", i, TAG);
        } else {
            Log.i(TAG, "handleGestureEvent - Gesture Stop - Go to next notification");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_IGNORE);
            transitStateForNextNotification(2, Integer.valueOf(i), null);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        boolean isSameNotification = isSameNotification(jSONObject, NotificationQueue.getInstance().peek());
        super.handleNotificationEvent(i, jSONObject);
        if ((2 != i || !isSameNotification) && 3 != i) {
            return;
        }
        Log.i(TAG, "handleNotificationEvent - Notification dismissed when waiting user action, go to next notification");
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_CANCELLED_NOTIF_DISMISSED);
        clearReplyTransientStorage();
        transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 1, Integer.valueOf(i), null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Invalid Event Type ", optInt, TAG);
        } else if (i != 1) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Ignored Event Id during reply delay state ", i, TAG);
        } else {
            Log.i(TAG, "handleTimerEvent - Action Delay timer expired for Reply");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_ACTION_TIMEOUT);
            JSONObject peek = NotificationQueue.getInstance().peek();
            if (peek != null && shouldPlayGroupMessagesUserEducation(peek)) {
                Log.i(TAG, "handleTimerEvent - Transit to instructional audio and playgroup message education TTS");
                transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 4, Integer.valueOf(i), Integer.valueOf(optInt));
                return;
            }
            Log.i(TAG, "handleTimerEvent - Remove head and transit for next notification");
            removeHeadThenTransitForNextNotification(4, Integer.valueOf(i), Integer.valueOf(optInt));
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        cancelActDelayTimer();
    }

    /* renamed from: clone */
    public ReplyActDelayHandler m346clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
