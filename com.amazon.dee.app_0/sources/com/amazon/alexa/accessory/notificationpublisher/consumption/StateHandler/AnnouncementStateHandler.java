package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class AnnouncementStateHandler extends BaseStateHandler {
    private static final String TAG = "AnnouncementStateHandler";
    private static AnnouncementStateHandler instance;
    private JSONObject currentAnnouncing;
    private int currentRenderId;
    private boolean didPlayLowDistractionInvitationEarcon;

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.AnnouncementStateHandler$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState = new int[SettingsStorageModule.FilterSettingsState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.REJECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.ACCEPTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.NOT_DETERMINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private AnnouncementStateHandler() {
        super(StateManager.STATE_ANNOUNCEMENT);
        this.currentAnnouncing = null;
        this.didPlayLowDistractionInvitationEarcon = false;
        this.currentRenderId = 100;
    }

    public static synchronized AnnouncementStateHandler getInstance() {
        AnnouncementStateHandler announcementStateHandler;
        synchronized (AnnouncementStateHandler.class) {
            if (instance == null) {
                instance = new AnnouncementStateHandler();
            }
            announcementStateHandler = instance;
        }
        return announcementStateHandler;
    }

    private void playAnnouncement(@NonNull JSONObject jSONObject, boolean z) throws NullPointerException, JSONException {
        String string = jSONObject.getString("uuid");
        String announcementFilePath = new NotificationFileHelper(DependencyProvider.getContext(), string).getAnnouncementFilePath();
        int currentDistractionMode = DistractionModeProvider.getCurrentDistractionMode();
        if (currentDistractionMode == 2) {
            playAnnouncementForLowDistractionMode(string, jSONObject, z);
        } else if (currentDistractionMode == 3) {
            skipAnnouncementForSilentDistractionMode(string, jSONObject);
        } else {
            playAnnouncementForNormalDistractionMode(string, announcementFilePath, jSONObject, z);
        }
    }

    private synchronized void playAnnouncementForLowDistractionMode(String str, JSONObject jSONObject, boolean z) {
        boolean postEventMessageToRenderManager;
        String str2 = TAG;
        Log.i(str2, "playAnnouncementForLowDistractionMode - UUID: " + str);
        if (z) {
            this.currentRenderId = 9;
            postEventMessageToRenderManager = postEventMessageToRenderManager(this.currentRenderId, 1);
            if (postEventMessageToRenderManager) {
                this.didPlayLowDistractionInvitationEarcon = true;
                setCurrentAnnouncing(jSONObject);
                cancelReplayTimerAndSetLastNotification(jSONObject);
            }
        } else {
            this.currentRenderId = 8;
            this.didPlayLowDistractionInvitationEarcon = false;
            postEventMessageToRenderManager = postEventMessageToRenderManager(this.currentRenderId, 1);
            if (postEventMessageToRenderManager) {
                setCurrentAnnouncing(jSONObject);
                cancelReplayTimerAndSetLastNotification(jSONObject);
            }
        }
        if (!postEventMessageToRenderManager) {
            Log.w(TAG, "playAnnouncementForLowDistractionMode - Not able to render earcon for current notification, skipping");
            removeHeadThenTransitForNextNotification();
        }
    }

    private synchronized void playAnnouncementForNormalDistractionMode(String str, String str2, JSONObject jSONObject, boolean z) {
        String str3 = TAG;
        Log.i(str3, "playAnnouncement - UUID: " + str);
        this.didPlayLowDistractionInvitationEarcon = false;
        this.currentRenderId = z ? 105 : 103;
        if (postEventMessageToRenderManager(this.currentRenderId, 1, str, str2)) {
            setCurrentAnnouncing(jSONObject);
            cancelReplayTimerAndSetLastNotification(jSONObject);
        } else {
            Log.w(TAG, "Not able to render announcement for current notification, skipping");
            removeHeadThenTransitForNextNotification();
        }
    }

    private synchronized void playInvitationAfterUserAcceptsLowDistractionInvitation(JSONObject jSONObject) {
        this.didPlayLowDistractionInvitationEarcon = false;
        if (jSONObject == null) {
            Log.w(TAG, "playInvitationAfterUserAcceptsLowDistractionInvitation - Unexpected error - nextNotification is null");
            transitStateForNextNotification();
            return;
        }
        String optString = jSONObject.optString("uuid", "");
        if (Strings.isNullOrEmpty(optString)) {
            Log.w(TAG, "playInvitationAfterUserAcceptsLowDistractionInvitation - Unexpected error - UUID is null or empty");
            removeHeadThenTransitForNextNotification();
            return;
        }
        String announcementFilePath = new NotificationFileHelper(DependencyProvider.getContext(), optString).getAnnouncementFilePath();
        this.currentRenderId = 110;
        if (postEventMessageToRenderManager(this.currentRenderId, 1, optString, announcementFilePath)) {
            setCurrentAnnouncing(jSONObject);
            cancelReplayTimerAndSetLastNotification(jSONObject);
        } else {
            Log.w(TAG, "playInvitationAfterUserAcceptsLowDistractionInvitation - Not able to render announcement for current notification, skipping");
            removeHeadThenTransitForNextNotification();
        }
    }

    private void postEventMessageToStopAnnouncement() {
        if (this.currentAnnouncing != null) {
            Log.i(TAG, "Stop rendering announcement");
            postEventMessageToRenderManager(this.currentRenderId, 2, this.currentAnnouncing.optString("uuid"));
            setCurrentAnnouncing(null);
        }
    }

    public static synchronized void releaseInstance() {
        synchronized (AnnouncementStateHandler.class) {
            if (instance != null) {
                instance.currentAnnouncing = null;
            }
            instance = null;
        }
    }

    private synchronized void skipAnnouncementForSilentDistractionMode(String str, JSONObject jSONObject) {
        String str2 = TAG;
        Log.i(str2, "skipAnnouncement - UUID: " + str);
        this.didPlayLowDistractionInvitationEarcon = false;
        setCurrentAnnouncing(jSONObject);
        cancelReplayTimerAndSetLastNotification(jSONObject);
        removeHeadThenTransitForNextNotification(6, 18, null);
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERANNOUNCEMENT_SKIPPED);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        boolean z = DistractionModeProvider.getCurrentDistractionMode() == 4;
        boolean isAtLeastOneA2dpConnected = BluetoothA2dpConnectionHandler.getInstance().isAtLeastOneA2dpConnected();
        String str2 = TAG;
        Log.d(str2, "enterState - No distraction mode/Accessory DND enabled = " + z);
        String str3 = TAG;
        Log.d(str3, "enterState - At least one active A2DP connection = " + isAtLeastOneA2dpConnected);
        if (!z && isAtLeastOneA2dpConnected) {
            processEnterState(str, num, num2, num3);
            return;
        }
        int i = 8;
        if (z) {
            Log.i(TAG, "enterState - No distraction mode/Accessory DND is enabled, transit to Idle");
        } else {
            Log.i(TAG, "enterState - No active A2DP connection, transit to Idle");
            i = 16;
        }
        transitState(StateManager.STATE_IDLE, 6, Integer.valueOf(i), null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public synchronized void handleGestureEvent(int i, @Nullable Payload payload) {
        super.handleGestureEvent(i, payload);
        try {
            JSONObject peek = NotificationQueue.getInstance().peek();
            String str = null;
            if (payload != null) {
                str = payload.getDeviceType();
            }
            if (i == 1) {
                Log.i(TAG, "handleGestureEvent - Gesture Play");
                if (this.didPlayLowDistractionInvitationEarcon && this.currentAnnouncing != null) {
                    Log.i(TAG, "User swiped for low distraction invitation");
                    playInvitationAfterUserAcceptsLowDistractionInvitation(this.currentAnnouncing);
                } else if (this.currentAnnouncing != null) {
                    postEventMessageToStopAnnouncement();
                    if (peek.optBoolean("isInvitation")) {
                        handlePlayGestureWithInvitationAnnouncement(peek);
                    } else {
                        Log.i(TAG, "handleGestureEvent - Gesture play to transit to Content state.");
                        updateNumOfOpenedNotification(str);
                        transitToContent();
                    }
                }
            } else if (i != 2) {
                String str2 = TAG;
                Log.w(str2, "handleGestureEvent - Invalid event Id: " + i);
            } else {
                Log.i(TAG, "handleGestureEvent - Gesture Stop");
                if (this.didPlayLowDistractionInvitationEarcon && this.currentAnnouncing != null && peek != null) {
                    Log.i(TAG, "User tapped for low distraction invitation");
                    this.didPlayLowDistractionInvitationEarcon = false;
                    postEventMessageToStopAnnouncement();
                    handleStopGestureWithInvitationAnnouncement(peek, str);
                } else if (this.currentAnnouncing != null) {
                    postEventMessageToStopAnnouncement();
                    if (peek.optBoolean("isInvitation")) {
                        handleStopGestureWithInvitationAnnouncement(peek, str);
                    } else {
                        Log.i(TAG, "handleGestureEvent - Gesture Stop when playing announcement - Transit back to Idle state and set last item in the queue.");
                        handleStopGestureWithAnnouncementAndInstruction();
                    }
                }
            }
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
            Log.e(TAG, "handleGestureEvent -- Error happened.", e);
            transitState(StateManager.STATE_IDLE);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        boolean isSameNotification = isSameNotification(jSONObject, this.currentAnnouncing);
        super.handleNotificationEvent(i, jSONObject);
        if ((2 == i && isSameNotification) || 3 == i) {
            Log.i(TAG, "handleNotificationEvent - dismiss notification that is announcing - interrupt announcement");
            postEventMessageToStopAnnouncement();
            super.handleNotificationEvent(i, jSONObject);
            transitStateForNextNotification(1, Integer.valueOf(i), null);
            return;
        }
        super.handleNotificationEvent(i, jSONObject);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleRendererEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleRendererEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        String optString = jSONObject.optString("uuid");
        if (optInt != 3) {
            if (optInt == 4) {
                String str = TAG;
                Log.i(str, "handleRendererEvent - Render stopped for eventID: " + i + " uuid: " + optString);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERANNOUNCEMENT_STOPPED);
                return;
            } else if (optInt != 5) {
                GeneratedOutlineSupport1.outline151("handleRendererEvent - Invalid Event Type: ", optInt, TAG);
                return;
            } else {
                String str2 = TAG;
                Log.w(str2, "handleRendererEvent - Render error for eventID: " + i + " uuid: " + optString);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERANNOUNCEMENT_ERROR);
                transitState(StateManager.STATE_IDLE);
                return;
            }
        }
        String str3 = TAG;
        Log.i(str3, "handleRendererEvent - Render completed for eventID: " + i + " uuid: " + optString);
        String str4 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("handleRendererEvent - currentAnnouncing notification: ");
        outline107.append(this.currentAnnouncing);
        Log.d(str4, outline107.toString());
        if (i == 8) {
            if (this.currentAnnouncing != null) {
                Log.i(TAG, "handleRendererEvent - LDM Announcement Earcon - currentAnnouncing is valid");
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDER_LDM_ANNOUNCEMENT_EARCON_COMPLETED);
                if (this.currentAnnouncing.optString("type").equals(ProcessNotificationModule.NotificationType.COMMS.name())) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDER_LDM_ANNOUNCEMENT_EARCON_COMMS_COMPLETED);
                }
                startReplayTimer();
                transitState(StateManager.STATE_ANNOUNCEMENT_DELAY);
                return;
            }
            Log.w(TAG, "handleRendererEvent - LDM Announcement Earcon - currentAnnouncing is null");
            transitStateForNextNotification();
        } else if (i == 9) {
            if (this.didPlayLowDistractionInvitationEarcon && this.currentAnnouncing != null) {
                Log.i(TAG, "handleRendererEvent - LDM Invitation Earcon - currentAnnouncing is valid");
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDER_LDM_INVITATION_EARCON_COMPLETED);
                if (this.currentAnnouncing.optString("type").equals(ProcessNotificationModule.NotificationType.COMMS.name())) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDER_LDM_INVITATION_EARCON_COMMS_COMPLETED);
                }
                transitState(StateManager.STATE_ANNOUNCEMENT_DELAY, 3, 8, 3);
                return;
            }
            String str5 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("handleRendererEvent - LDM Invitation Earcon - currentAnnouncing is null or LDM invitation earcon played = ");
            outline1072.append(this.didPlayLowDistractionInvitationEarcon);
            Log.w(str5, outline1072.toString());
            transitStateForNextNotification();
        } else if (i != 103 && i != 105 && i != 110) {
            GeneratedOutlineSupport1.outline151("handleRendererEvent - Ignored Event Id: ", i, TAG);
        } else {
            JSONObject jSONObject2 = this.currentAnnouncing;
            if (jSONObject2 != null && optString.equals(jSONObject2.optString("uuid"))) {
                Log.i(TAG, "handleRendererEvent - Render completed for current announcing notification");
                if (i == 110) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDER_LDM_INVITATION_COMPLETED);
                    if (this.currentAnnouncing.optString("type").equals(ProcessNotificationModule.NotificationType.COMMS.name())) {
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDER_LDM_INVITATION_COMMS_COMPLETED);
                    }
                } else {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERANNOUNCEMENT_COMPLETED);
                    if (this.currentAnnouncing.optString("type").equals(ProcessNotificationModule.NotificationType.COMMS.name())) {
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERANNOUNCEMENT_COMMS_COMPLETED);
                    }
                }
                startReplayTimer();
                if (!this.currentAnnouncing.optBoolean("isInvitation", false) && SettingsStorageModule.getInstance().getNumOfOpenedNotification().intValue() < 3) {
                    transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 3, 3, null);
                    return;
                } else {
                    transitState(StateManager.STATE_ANNOUNCEMENT_DELAY);
                    return;
                }
            }
            Log.w(TAG, "handleRendererEvent - currentAnnouncing is null or UUID doesn't match");
            transitStateForNextNotification();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Invalid Event Type: ", optInt, TAG);
        } else if (i != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Ignored Event Id: ", i, TAG);
        } else {
            Log.i(TAG, "handleTimerEvent - Replay timer expired");
            clearLastNotificationWithAudioFile();
            clearLastNonInvitationNotificationWithAudioFile();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        this.didPlayLowDistractionInvitationEarcon = false;
        postEventMessageToStopAnnouncement();
        this.currentRenderId = 100;
    }

    public void processEnterState(@NonNull String str, Integer num, Integer num2, Integer num3) {
        JSONObject peek = NotificationQueue.getInstance().peek();
        if (peek != null) {
            try {
                if (!SettingsStorageModule.getInstance().getFocusFilterEnabledWithDefault().booleanValue()) {
                    if (!peek.getBoolean("isInvitation")) {
                        playAnnouncement(peek, false);
                        return;
                    } else {
                        removeHeadThenTransitForNextNotification();
                        return;
                    }
                }
                int ordinal = getFilterState(peek).ordinal();
                if (ordinal == 0) {
                    playAnnouncement(peek, false);
                    return;
                } else if (ordinal == 1) {
                    removeHeadThenTransitForNextNotification();
                    return;
                } else if (ordinal == 2) {
                    if (SettingsStorageModule.getInstance().getApproveInvitationOnAccessoryWithDefault().booleanValue()) {
                        if (num != null && num.intValue() == 2 && num2 != null && num2.intValue() == 1 && num3 != null && num3.intValue() == 8) {
                            Log.i(TAG, "processEnterState - User has accepted the earcon forinvitation in low distraction mode. Play full invitation TTS");
                            playInvitationAfterUserAcceptsLowDistractionInvitation(peek);
                            return;
                        }
                        playAnnouncement(peek, true);
                        return;
                    }
                    removeHeadThenTransitForNextNotification();
                    return;
                } else {
                    throw new IllegalArgumentException("Invalid filter state");
                }
            } catch (Exception e) {
                Log.e(TAG, "Error happen during enterState.", e);
                removeHeadThenTransitForNextNotification();
                return;
            }
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
        Log.e(TAG, "Queue should not be empty when entering Announcement state");
        transitState(StateManager.STATE_IDLE);
    }

    @VisibleForTesting
    void setCurrentAnnouncing(@Nullable JSONObject jSONObject) {
        this.currentAnnouncing = jSONObject;
    }

    /* renamed from: clone */
    public AnnouncementStateHandler m341clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
