package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.AccessoryTtsStateManager;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class IdleStateHandler extends BaseStateHandler {
    private static final String TAG = "IdleStateHandler";
    private static IdleStateHandler instance;
    @VisibleForTesting
    volatile AtomicBoolean hasAudioFocus;
    private BaseStateHandler.StateTransitionInfo stateTransitionInfo;

    private IdleStateHandler() {
        super(StateManager.STATE_IDLE);
        this.hasAudioFocus = new AtomicBoolean(false);
    }

    private void clearQueueExceptLastNotification() {
        JSONObject lastNotification = NotificationQueue.getInstance().getLastNotification();
        if (lastNotification != null) {
            String optString = lastNotification.optString("uuid");
            GeneratedOutlineSupport1.outline166("clearQueueExceptLastNotification - last notification: ", optString, TAG);
            clearQueuedNotificationAudioFile(optString);
            return;
        }
        Log.i(TAG, "clearQueueExceptLastNotification - no last notification, clear all");
        clearQueuedNotificationAudioFile(null);
    }

    public static synchronized IdleStateHandler getInstance() {
        IdleStateHandler idleStateHandler;
        synchronized (IdleStateHandler.class) {
            if (instance == null) {
                instance = new IdleStateHandler();
            }
            idleStateHandler = instance;
        }
        return idleStateHandler;
    }

    public static synchronized void releaseInstance() {
        synchronized (IdleStateHandler.class) {
            instance = null;
        }
    }

    private void resetConsumptionEngine() {
        Log.d(TAG, "resetConsumptionEngine");
        postEventMessageToTimerManager(2, 2);
        clearQueuedNotificationAudioFile(null);
        clearLastNotificationWithTimer();
        clearLastNonInvitationNotificationWithAudioFile();
    }

    private void transitToPlayLastNotification(@NonNull JSONObject jSONObject, boolean z, SettingsStorageModule.FilterSettingsState filterSettingsState) throws Exception {
        if (jSONObject.optBoolean("isInvitation") && z && SettingsStorageModule.FilterSettingsState.NOT_DETERMINED == filterSettingsState) {
            Log.i(TAG, "transitToPlayLastNotification - retrieve last invitation");
            NotificationQueue.getInstance().insertToQueueHead(jSONObject.optString("uuid"), jSONObject);
            startReplayTimer();
            waitForAudioFocusBeforeTransit(StateManager.STATE_ANNOUNCEMENT, 2, 1, null);
            return;
        }
        Log.i(TAG, "transitToPlayLastNotification - play last notification");
        waitForAudioFocusBeforeTransit(StateManager.STATE_CONTENT, 2, 1, null);
    }

    private void transitToPlayNoNotificationAlert() throws Exception {
        Log.i(TAG, "transitToPlayNoNotificationAlert - play no recent phone notifications");
        if (!BluetoothA2dpConnectionHandler.getInstance().isAtLeastOneA2dpConnected()) {
            Log.i(TAG, "transitToPlayNoNotificationAlert - Ignore transit as no A2DP is active");
            return;
        }
        clearLastNotificationWithTimer();
        waitForAudioFocusBeforeTransit(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, 1, null);
    }

    private void transitToPlayPhoneNotificationsToggleTTS() {
        Log.i(TAG, "transitToPlayPhoneNotificationsToggleTTS - play Phone Notifications ON/OFF TTS");
        if (!BluetoothA2dpConnectionHandler.getInstance().isAtLeastOneA2dpConnected()) {
            Log.i(TAG, "transitToPlayPhoneNotificationsToggleTTS - Ignore transit as no A2DP is active");
        } else {
            waitForAudioFocusBeforeTransit(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, 5, null);
        }
    }

    private void transitToPlaySilentDistractionModeTTS() {
        Log.i(TAG, "transitToPlaySilentDistractionModeTTS - play Notification Alerts ON/OFF TTS");
        if (!BluetoothA2dpConnectionHandler.getInstance().isAtLeastOneA2dpConnected()) {
            Log.i(TAG, "transitToPlaySilentDistractionModeTTS - Ignore transit as no A2DP is active");
        } else {
            waitForAudioFocusBeforeTransit(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, 4, null);
        }
    }

    private void waitForAudioFocusBeforeTransit(@NonNull String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        Log.d(TAG, "waitForAudioFocusBeforeTransit");
        if (this.hasAudioFocus.get()) {
            Log.i(TAG, "waitForAudioFocusBeforeTransit - Already has audio focus");
            transitState(str, num, num2, num3);
            return;
        }
        Log.i(TAG, "waitForAudioFocusBeforeTransit - Request audio focus");
        this.stateTransitionInfo = new BaseStateHandler.StateTransitionInfo(this, str, num, num2, num3);
        requestAudioFocus();
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        AccessoryTtsStateManager.getInstance().updateAccessoryTtsState(StateManager.STATE_IDLE, 0L);
        AudioInteractionScheduler.getInstance().setNotificationPlaybackState(AudioInteractionScheduler.NotificationPlaybackState.NONE, getState());
        removeReplyReadBackAudioFile();
        try {
            if (num.intValue() == 7 && (num2.intValue() == 4 || num2.intValue() == 3 || num2.intValue() == 2)) {
                Log.i(TAG, "enterState - No Audio focus - Clear queued notification");
                clearQueueExceptLastNotification();
            } else if (num.intValue() == 6 && num2.intValue() == 18) {
                Log.i(TAG, "enterState - SilentDistractionMode ON, stop Audio & release audio focus.");
                RenderManager.getInstance().stopAllAudio();
                releaseAudioFocus();
            } else {
                Log.i(TAG, "enterState - Release audio focus");
                RenderManager.getInstance().stopAllAudio();
                releaseAudioFocus();
                if (num.intValue() == 6 && (num2.intValue() == 3 || num2.intValue() == 1 || num2.intValue() == 5 || num2.intValue() == 8 || num2.intValue() == 16)) {
                    resetConsumptionEngine();
                } else {
                    clearQueueExceptLastNotification();
                }
            }
            this.hasAudioFocus.set(false);
        } catch (Exception e) {
            Log.e(TAG, "enterState - Error when enter idle state, reset consumption engine.", e);
            resetConsumptionEngine();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleAudioFocusEvent(int i) {
        if (i == 1) {
            Log.i(TAG, "handleAudioFocusEvent - Acquired audio focus, transit to state");
            this.hasAudioFocus.set(true);
            BaseStateHandler.StateTransitionInfo stateTransitionInfo = this.stateTransitionInfo;
            if (stateTransitionInfo != null) {
                stateTransitionInfo.transit();
                return;
            }
            Log.w(TAG, "handleAudioFocusEvent - Acquired audio focus - Unexpected state");
            transitState(StateManager.STATE_IDLE);
        } else if (i != 2 && i != 3) {
            GeneratedOutlineSupport1.outline151("handleAudioFocusEvent - Unhandled event - ", i, TAG);
        } else {
            this.hasAudioFocus.set(false);
            String str = TAG;
            Log.i(str, "handleAudioFocusEvent - Event ID - " + i);
            transitState(StateManager.STATE_IDLE, 7, Integer.valueOf(i), null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0091, code lost:
        if ((r1.optLong("postTime") - java.lang.System.currentTimeMillis()) <= java.util.concurrent.TimeUnit.HOURS.toMillis(1)) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0093, code lost:
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG, "handleGestureEvent - play no notification alert - lastNonInvitation has expired && no valid lastNotification");
        clearLastNonInvitationNotificationWithAudioFile();
        transitToPlayNoNotificationAlert();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a1, code lost:
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG, "handleGestureEvent - play last non-invitation notification - no valid lastNotification");
        com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue.getInstance().setLastNotification(r1);
        waitForAudioFocusBeforeTransit(com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager.STATE_CONTENT, 2, 1, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:
        return;
     */
    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleGestureEvent(int r9, @androidx.annotation.Nullable com.amazon.alexa.accessory.notificationpublisher.consumption.Payload r10) {
        /*
            r8 = this;
            super.handleGestureEvent(r9, r10)
            r10 = 2
            r0 = 1
            if (r9 == r0) goto L39
            if (r9 == r10) goto L30
            r10 = 4
            if (r9 == r10) goto L24
            r10 = 5
            if (r9 == r10) goto L18
            java.lang.String r10 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG
            java.lang.String r0 = "handleGestureEvent - Invalid event Id: "
            com.android.tools.r8.GeneratedOutlineSupport1.outline151(r0, r9, r10)
            goto Ld9
        L18:
            java.lang.String r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG
            java.lang.String r10 = "handleGestureEvent - Gesture PhoneNotifications toggle"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r9, r10)
            r8.transitToPlayPhoneNotificationsToggleTTS()
            goto Ld9
        L24:
            java.lang.String r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG
            java.lang.String r10 = "handleGestureEvent - Gesture SDM toggle"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r9, r10)
            r8.transitToPlaySilentDistractionModeTTS()
            goto Ld9
        L30:
            java.lang.String r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG
            java.lang.String r10 = "handleGestureEvent - Gesture Stop - Nothing should happen"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r9, r10)
            goto Ld9
        L39:
            java.lang.String r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG
            java.lang.String r1 = "handleGestureEvent - Gesture Play"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r9, r1)
            com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue.getInstance()     // Catch: java.lang.Exception -> Lc8
            org.json.JSONObject r9 = r9.getLastNotification()     // Catch: java.lang.Exception -> Lc8
            com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue r1 = com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue.getInstance()     // Catch: java.lang.Exception -> Lc8
            org.json.JSONObject r1 = r1.getLastNotInvitationNotification()     // Catch: java.lang.Exception -> Lc8
            com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule r2 = com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.getInstance()     // Catch: java.lang.Exception -> Lc8
            java.lang.Boolean r2 = r2.getFocusFilterEnabledWithDefault()     // Catch: java.lang.Exception -> Lc8
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Exception -> Lc8
            r3 = 0
            if (r9 != 0) goto L61
            r4 = r3
            goto L65
        L61:
            com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule$FilterSettingsState r4 = r8.getFilterState(r9)     // Catch: java.lang.Exception -> Lc8
        L65:
            if (r9 == 0) goto L79
            if (r2 == 0) goto L6e
            com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule$FilterSettingsState r5 = com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.FilterSettingsState.REJECTED     // Catch: java.lang.Exception -> Lc8
            if (r4 != r5) goto L6e
            goto L79
        L6e:
            java.lang.String r10 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG     // Catch: java.lang.Exception -> Lc8
            java.lang.String r0 = "handleGestureEvent - play last notification - valid last notification"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r10, r0)     // Catch: java.lang.Exception -> Lc8
            r8.transitToPlayLastNotification(r9, r2, r4)     // Catch: java.lang.Exception -> Lc8
            goto Ld9
        L79:
            if (r1 == 0) goto Lbd
            java.lang.String r9 = "postTime"
            long r4 = r1.optLong(r9)     // Catch: java.lang.Exception -> Lc8
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> Lc8
            long r4 = r4 - r6
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.HOURS     // Catch: java.lang.Exception -> Lc8
            r6 = 1
            long r6 = r9.toMillis(r6)     // Catch: java.lang.Exception -> Lc8
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 <= 0) goto La1
            java.lang.String r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG     // Catch: java.lang.Exception -> Lc8
            java.lang.String r10 = "handleGestureEvent - play no notification alert - lastNonInvitation has expired && no valid lastNotification"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r9, r10)     // Catch: java.lang.Exception -> Lc8
            r8.clearLastNonInvitationNotificationWithAudioFile()     // Catch: java.lang.Exception -> Lc8
            r8.transitToPlayNoNotificationAlert()     // Catch: java.lang.Exception -> Lc8
            goto Ld9
        La1:
            java.lang.String r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG     // Catch: java.lang.Exception -> Lc8
            java.lang.String r2 = "handleGestureEvent - play last non-invitation notification - no valid lastNotification"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r9, r2)     // Catch: java.lang.Exception -> Lc8
            com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue.getInstance()     // Catch: java.lang.Exception -> Lc8
            r9.setLastNotification(r1)     // Catch: java.lang.Exception -> Lc8
            java.lang.String r9 = "STATE_CONTENT"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Exception -> Lc8
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Exception -> Lc8
            r8.waitForAudioFocusBeforeTransit(r9, r10, r0, r3)     // Catch: java.lang.Exception -> Lc8
            goto Ld9
        Lbd:
            java.lang.String r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG     // Catch: java.lang.Exception -> Lc8
            java.lang.String r10 = "handleGestureEvent - play no notification alert - no valid lastNotification && lastNonInvitation = null"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r9, r10)     // Catch: java.lang.Exception -> Lc8
            r8.transitToPlayNoNotificationAlert()     // Catch: java.lang.Exception -> Lc8
            goto Ld9
        Lc8:
            r9 = move-exception
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r10 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r0 = "FocusFilter_ce_error"
            r10.recordCounter(r0)
            java.lang.String r10 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.TAG
            java.lang.String r0 = "handleGestureEvent - Gesture Swipe - having error."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r10, r0, r9)
        Ld9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler.handleGestureEvent(int, com.amazon.alexa.accessory.notificationpublisher.consumption.Payload):void");
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleNotificationEvent(i, jSONObject);
        if (1 == i) {
            waitForAudioFocusBeforeTransit(matchRunningActiveWindow(jSONObject) ? StateManager.STATE_CONTENT : StateManager.STATE_ANNOUNCEMENT, 1, 1, null);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Invalid Event Type ", optInt, TAG);
        } else if (i != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Ignored Event Id during Idle state ", i, TAG);
        } else {
            Log.i(TAG, "handleTimerEvent - Replay timer expired");
            clearLastNotificationWithAudioFile();
            clearLastNonInvitationNotificationWithAudioFile();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
    }

    void requestAudioFocus() {
        Log.d(TAG, "requestAudioFocus");
        AudioFocusManager.getInstance().requestAudioFocus();
    }

    /* renamed from: clone */
    public IdleStateHandler m344clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
