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
import com.google.common.base.Strings;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ReplyReadBackActDelayHandler extends BaseStateHandler {
    private static final String TAG = "ReplyReadBackActDelayHandler";
    private static ReplyReadBackActDelayHandler instance;

    private ReplyReadBackActDelayHandler() {
        super(StateManager.STATE_REPLY_READ_BACK_ACT_DELAY);
    }

    private void cancelReadBackActDelayTimer() {
        Log.i(TAG, "cancelReadBackActDelayTimer");
        postEventMessageToTimerManager(5, 2);
    }

    public static synchronized ReplyReadBackActDelayHandler getInstance() {
        ReplyReadBackActDelayHandler replyReadBackActDelayHandler;
        synchronized (ReplyReadBackActDelayHandler.class) {
            if (instance == null) {
                instance = new ReplyReadBackActDelayHandler();
            }
            replyReadBackActDelayHandler = instance;
        }
        return replyReadBackActDelayHandler;
    }

    public static synchronized void releaseInstance() {
        synchronized (ReplyReadBackActDelayHandler.class) {
            instance = null;
        }
    }

    private void startReadBackActDelayTimer() {
        Log.i(TAG, "startReadBackActDelayTimer");
        postEventMessageToTimerManager(5, 1);
    }

    private void transitToReplyStateForRecordingReply() {
        Log.i(TAG, "transitToReplyStateForRecordingReply");
        transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 9, 7, null);
    }

    private void transitToReplyStateForSendingReply() {
        Log.i(TAG, "transitToReplyStateForSendingReply");
        transitState(StateManager.STATE_REPLY, 9, 6, null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(@NonNull String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        try {
            if (str.equalsIgnoreCase(StateManager.STATE_REPLY_READ_BACK) && num.intValue() == 9 && num2.intValue() == 5) {
                Log.i(TAG, "enterState - Wait for user response after read back");
                startReadBackActDelayTimer();
            } else {
                Log.w(TAG, "enterState - Incorrect transition, transit to Idle");
                clearReplyTransientStorage();
                transitState(StateManager.STATE_IDLE);
            }
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "enterState - Exception, transit to idle - " + e);
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MetricsConstants.REPLY_READBACKACTDELAY_EXCEPTION);
            outline107.append(e.getClass().getSimpleName());
            metricsRecorder.recordCounter(outline107.toString(), MetricsRecorder.customAttributesForException(e, 512));
            clearReplyTransientStorage();
            transitState(StateManager.STATE_IDLE);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        super.handleGestureEvent(i, payload);
        JSONObject peek = NotificationQueue.getInstance().peek();
        if (peek == null) {
            Log.w(TAG, "handleGestureEvent - Error: Notification queue is empty");
            transitState(StateManager.STATE_IDLE);
        } else if (Strings.isNullOrEmpty(peek.optString("uuid"))) {
            Log.w(TAG, "handleGestureEvent - Error: Notification UUID is empty");
            transitState(StateManager.STATE_IDLE);
        } else if (i == 1) {
            Log.i(TAG, "handleGestureEvent - Play Gesture - Send the reply");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACKACTDELAY_APPROVED);
            transitToReplyStateForSendingReply();
        } else if (i != 2) {
        } else {
            Log.i(TAG, "handleGestureEvent - Stop Gesture - Record the reply again");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACKACTDELAY_RETRY);
            transitToReplyStateForRecordingReply();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public synchronized void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        boolean isSameNotification = isSameNotification(jSONObject, NotificationQueue.getInstance().peek());
        super.handleNotificationEvent(i, jSONObject);
        if ((2 == i && isSameNotification) || 3 == i) {
            Log.i(TAG, "handleNotificationEvent - Remove notification that user is currently replying to");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_CANCELLED_NOTIF_DISMISSED);
            clearReplyTransientStorage();
            transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 1, Integer.valueOf(i), null);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public synchronized void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt != 3) {
            String str = TAG;
            Log.w(str, "handleTimerEvent - Invalid Event Type " + optInt);
        } else if (i != 5) {
            String str2 = TAG;
            Log.w(str2, "handleTimerEvent - Ignored Event Id during reply read back act delay" + i);
        } else {
            Log.i(TAG, "handleTimerEvent - Read back act delay timer expired, Send the reply");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACKACTDELAY_TIMEOUT);
            transitToReplyStateForSendingReply();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        cancelReadBackActDelayTimer();
    }

    /* renamed from: clone */
    public ReplyReadBackActDelayHandler m347clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
