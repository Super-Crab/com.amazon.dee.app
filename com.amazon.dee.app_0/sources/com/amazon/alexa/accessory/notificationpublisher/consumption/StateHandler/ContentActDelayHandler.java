package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ContentActDelayHandler extends BaseStateHandler {
    private static final String TAG = "ContentActDelayHandler";
    private static ContentActDelayHandler instance;

    private ContentActDelayHandler() {
        super(StateManager.STATE_CONTENT_DELAY);
    }

    public static synchronized ContentActDelayHandler getInstance() {
        ContentActDelayHandler contentActDelayHandler;
        synchronized (ContentActDelayHandler.class) {
            if (instance == null) {
                instance = new ContentActDelayHandler();
            }
            contentActDelayHandler = instance;
        }
        return contentActDelayHandler;
    }

    public static synchronized void releaseInstance() {
        synchronized (ContentActDelayHandler.class) {
            instance = null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        AudioInteractionScheduler.getInstance().setNotificationPlaybackState(AudioInteractionScheduler.NotificationPlaybackState.PLAYING, getState());
        postEventMessageToTimerManager(1, 1);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        super.handleGestureEvent(i, payload);
        if (i != 1) {
            GeneratedOutlineSupport1.outline151("handleGestureEvent - Ignored event Id: ", i, TAG);
            return;
        }
        Log.i(TAG, "handleGestureEvent - Gesture Play");
        transitState(StateManager.STATE_CONTENT, 2, 1, null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        boolean isSameNotification = isSameNotification(jSONObject, NotificationQueue.getInstance().getLastNotification());
        super.handleNotificationEvent(i, jSONObject);
        if ((2 != i || !isSameNotification) && 3 != i) {
            return;
        }
        transitStateForNextNotification(1, Integer.valueOf(i), null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Invalid Event Type ", optInt, TAG);
        } else if (i != 1) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Ignored Event Id during ContentActDelayHandler state ", i, TAG);
        } else {
            Log.i(TAG, "handleTimerEvent - Action Delay timer expired");
            transitStateForNextNotification(4, 1, 3);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        postEventMessageToTimerManager(1, 2);
    }

    /* renamed from: clone */
    public ContentActDelayHandler m342clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
