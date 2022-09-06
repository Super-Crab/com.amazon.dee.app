package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class AnnouncementActDelayStateHandler extends BaseStateHandler {
    private static final String TAG = "AnnouncementActDelayStateHandler";
    private static AnnouncementActDelayStateHandler instance;
    private boolean didEnterForLowDistractionInvitationEarconDelay;
    private boolean isInvitationActDelay;

    private AnnouncementActDelayStateHandler() {
        super(StateManager.STATE_ANNOUNCEMENT_DELAY);
        this.isInvitationActDelay = false;
        this.didEnterForLowDistractionInvitationEarconDelay = false;
    }

    private void cancelActionDelayTimer() {
        postEventMessageToTimerManager(this.isInvitationActDelay ? 4 : 1, 2);
    }

    public static synchronized AnnouncementActDelayStateHandler getInstance() {
        AnnouncementActDelayStateHandler announcementActDelayStateHandler;
        synchronized (AnnouncementActDelayStateHandler.class) {
            if (instance == null) {
                instance = new AnnouncementActDelayStateHandler();
            }
            announcementActDelayStateHandler = instance;
        }
        return announcementActDelayStateHandler;
    }

    public static synchronized void releaseInstance() {
        synchronized (AnnouncementActDelayStateHandler.class) {
            instance = null;
        }
    }

    private void startActionDelayTimer() {
        postEventMessageToTimerManager(this.isInvitationActDelay ? 4 : 1, 1);
    }

    private void transitToAnnounceForLowDistractionInvitation() {
        Log.i(TAG, "transitToAnnounceForLowDistractionInvitation");
        transitState(StateManager.STATE_ANNOUNCEMENT, 2, 1, 8);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        boolean z = false;
        this.didEnterForLowDistractionInvitationEarconDelay = false;
        if (NotificationQueue.getInstance().isEmpty()) {
            Log.e(TAG, "enterState - Queue should not be empty when entering Announcement Act-delay state");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
            transitState(StateManager.STATE_IDLE);
            return;
        }
        if (num != null && num.intValue() == 3 && num2 != null && num2.intValue() == 8 && num3 != null && num3.intValue() == 3) {
            Log.i(TAG, "enterState - Entering for low distraction invitation alert earcon delay.");
            this.didEnterForLowDistractionInvitationEarconDelay = true;
        }
        AudioInteractionScheduler.getInstance().setNotificationPlaybackState(AudioInteractionScheduler.NotificationPlaybackState.PLAYING, getState());
        if (NotificationQueue.getInstance().peek().optBoolean("isInvitation") || this.didEnterForLowDistractionInvitationEarconDelay) {
            z = true;
        }
        this.isInvitationActDelay = z;
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("enterState - Is act delay for invitation? - ");
        outline107.append(this.isInvitationActDelay);
        Log.d(str2, outline107.toString());
        startActionDelayTimer();
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        super.handleGestureEvent(i, payload);
        JSONObject peek = NotificationQueue.getInstance().peek();
        if (i != 1) {
            if (i != 2) {
                GeneratedOutlineSupport1.outline151("handleGestureEvent - Invalid event Id: ", i, TAG);
                return;
            }
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("handleGestureEvent - Gesture Stop - didEnterForLowDistractionInvitationEarconDelay = ");
            outline107.append(this.didEnterForLowDistractionInvitationEarconDelay);
            Log.i(str, outline107.toString());
            if (this.didEnterForLowDistractionInvitationEarconDelay || peek == null || !peek.optBoolean("isInvitation")) {
                return;
            }
            handleStopGestureWithInvitationAnnouncement(peek, payload != null ? payload.getDeviceType() : null);
            return;
        }
        Log.i(TAG, "handleGestureEvent - Gesture Play");
        if (this.didEnterForLowDistractionInvitationEarconDelay) {
            Log.i(TAG, "handleGestureEvent - Gesture play when waiting for low distractioninvitation earcon act delay");
            transitToAnnounceForLowDistractionInvitation();
        } else if (peek == null) {
        } else {
            cancelActionDelayTimer();
            if (peek.optBoolean("isInvitation")) {
                handlePlayGestureWithInvitationAnnouncement(peek);
                return;
            }
            Log.d(TAG, "handleGestureEvent - Gesture play to transit to Content state.");
            transitToContent();
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
        transitStateForNextNotification(1, Integer.valueOf(i), null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt == 3) {
            if (i != 1) {
                if (i == 3) {
                    Log.w(TAG, "handleTimerEvent - Replay timer expired - should not happen");
                    clearLastNotificationWithTimer();
                    clearLastNonInvitationNotificationWithAudioFile();
                    return;
                } else if (i != 4) {
                    GeneratedOutlineSupport1.outline151("handleTimerEvent - Ignored Event Id during AnnouncementActDelay state ", i, TAG);
                    return;
                }
            }
            Log.i(TAG, "handleTimerEvent - Action Delay timer expired");
            removeHeadThenTransitForNextNotification(4, 1, 3);
            return;
        }
        GeneratedOutlineSupport1.outline151("handleTimerEvent - Invalid Event Type ", optInt, TAG);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        this.didEnterForLowDistractionInvitationEarconDelay = false;
        cancelActionDelayTimer();
    }

    /* renamed from: clone */
    public AnnouncementActDelayStateHandler m340clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
