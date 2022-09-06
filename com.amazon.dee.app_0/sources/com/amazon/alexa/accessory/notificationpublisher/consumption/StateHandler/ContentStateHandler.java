package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import android.media.AudioRecord;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.CommsNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GenericNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GroupNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyClientHelper;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.timers.TimerManager;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ContentStateHandler extends BaseStateHandler {
    private static final int AUDIO_FORMAT = 2;
    private static final int CHANNEL_CONFIG = 16;
    private static final int SAMPLE_RATE = 16000;
    private static final String TAG = "ContentStateHandler";
    private static ContentStateHandler instance;
    private boolean backToBackKeepPrefix;
    private long backToBackNotificationTimeGapThreshold;
    private JSONObject currentContent;
    private AtomicInteger currentDistractionMode;
    private int currentRenderId;
    private String replayNotificationUuid;
    private String requestIdSuffix;

    private ContentStateHandler() {
        super(StateManager.STATE_CONTENT);
        this.currentContent = null;
        this.requestIdSuffix = UUID.randomUUID().toString();
        this.currentRenderId = 100;
        this.replayNotificationUuid = "";
        this.currentDistractionMode = new AtomicInteger(4);
        this.backToBackNotificationTimeGapThreshold = TimeUnit.SECONDS.toMillis(5L);
        this.backToBackKeepPrefix = false;
    }

    private void checkAndStopMatchingActiveWindow() {
        Log.d(TAG, "checkAndStopMatchingActiveWindow called");
        try {
            if (!matchRunningActiveWindow(this.currentContent)) {
                return;
            }
            postEventMessageToTimerManager(2, 2);
        } catch (Exception e) {
            Log.w(TAG, "Failed to checkAndStopMatchingActiveWindow.", e);
        }
    }

    @VisibleForTesting
    private boolean checkIfShouldKeepPrefix(boolean z, boolean z2) {
        boolean z3 = z2 && !z;
        String str = TAG;
        Log.d(str, "checkIfShouldKeepPrefix - keepNextPrefix = " + z3);
        return z3;
    }

    private void dequeueNotification(@NonNull String str) {
        Log.d(TAG, "dequeueNotification called");
        JSONObject peek = NotificationQueue.getInstance().peek();
        if (peek != null) {
            String optString = peek.optString("uuid");
            String str2 = TAG;
            Log.i(str2, "dequeueNotification - currentUuid = " + str + " nextUuid = " + optString);
            if (!str.equals(optString)) {
                return;
            }
            Log.i(TAG, "dequeueNotification - Polling notification as it is same as current notification");
            NotificationQueue.getInstance().poll();
        }
    }

    public static synchronized ContentStateHandler getInstance() {
        ContentStateHandler contentStateHandler;
        synchronized (ContentStateHandler.class) {
            if (instance == null) {
                instance = new ContentStateHandler();
            }
            contentStateHandler = instance;
        }
        return contentStateHandler;
    }

    private String getRenderRequestUuid(String str) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "_");
        outline113.append(this.requestIdSuffix);
        return outline113.toString();
    }

    private boolean handleReplay(@NonNull String str, Integer num, Integer num2) {
        JSONObject lastNotification = NotificationQueue.getInstance().getLastNotification();
        if (num == null || num2 == null || num.intValue() != 2 || num2.intValue() != 1 || lastNotification == null || !isFromStateForReplay(str)) {
            return false;
        }
        this.replayNotificationUuid = lastNotification.optString("uuid");
        return playEarconForPlayGestureAndRenderContent(lastNotification);
    }

    private boolean isFromStateForReplay(@NonNull String str) {
        return str.equals(StateManager.STATE_IDLE) || str.equals(StateManager.STATE_CONTENT_DELAY) || str.equals(StateManager.STATE_CONTENT);
    }

    private boolean isTransitionReasonGroupMessagesUserEducationOrMultiNotificationsInstructionComplete(@NonNull String str, Integer num, Integer num2, Integer num3) {
        try {
            if (!str.equalsIgnoreCase(StateManager.STATE_INSTRUCTIONAL_AUDIO) || num.intValue() != 3 || num2.intValue() != 3) {
                return false;
            }
            if (num3.intValue() == 11) {
                return true;
            }
            return num3.intValue() == 12;
        } catch (Exception unused) {
            return false;
        }
    }

    private void onContentRenderCompleted(int i, String str) {
        Log.i(TAG, "onContentRenderCompleted");
        if (i != 101 && i != 103 && i != 104) {
            GeneratedOutlineSupport1.outline151("onContentRenderCompleted - Ignored Event Id: ", i, TAG);
            return;
        }
        String str2 = TAG;
        Log.i(str2, "onContentRenderCompleted - TTS Completed for uuid: " + str);
        NotificationQueue notificationQueue = NotificationQueue.getInstance();
        JSONObject jSONObject = this.currentContent;
        if (jSONObject == null || !str.equals(getRenderRequestUuid(jSONObject.optString("uuid")))) {
            return;
        }
        String optString = this.currentContent.optString("uuid");
        ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent = DependencyProvider.getExternalNotificationsCapabilityAgent();
        if (externalNotificationsCapabilityAgent != null && !TextUtils.isEmpty(optString)) {
            externalNotificationsCapabilityAgent.onNotificationRead(NotificationId.create(optString));
        }
        String str3 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onContentRenderCompleted - Current render completed. Notification queue size = ");
        outline107.append(notificationQueue.size());
        Log.i(str3, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERCONTENT_COMPLETED);
        if (this.currentContent.optString("type").equals(ProcessNotificationModule.NotificationType.COMMS.name())) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERCONTENT_COMMS_COMPLETED);
        }
        startReplayTimer();
        if (DistractionModeProvider.getCurrentDistractionMode() == this.currentDistractionMode.get()) {
            Log.i(TAG, "onContentRenderCompleted - No change to distraction mode, update active window");
            updateActiveWindow();
        }
        if (shouldOfferToReply(str)) {
            Log.i(TAG, "onContentRenderCompleted - Render complete and start Reply flow.");
            JSONObject lastNotification = notificationQueue.getLastNotification();
            if (!Strings.isNullOrEmpty(this.replayNotificationUuid) && this.replayNotificationUuid.equalsIgnoreCase(str) && lastNotification != null && this.replayNotificationUuid.equalsIgnoreCase(getRenderRequestUuid(lastNotification.optString("uuid")))) {
                notificationQueue.insertToQueueHead(lastNotification.optString("uuid"), lastNotification);
            }
            transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 3, 3, null);
        } else if (shouldPlayMultipleNotificationsInstruction(this.currentContent)) {
            Log.i(TAG, "onContentRenderCompleted - Render completed - Begin multiple notifications instruction");
            transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 3, 3, 12);
        } else if (shouldPlayGroupMessagesUserEducation(this.currentContent)) {
            Log.i(TAG, "onContentRenderCompleted - Render completed - Begin group message user education flow");
            transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 3, 3, 11);
        } else {
            transitStateAfterContentRenderComplete();
        }
    }

    private void onContentRenderStarted(@NonNull JSONObject jSONObject) {
        Log.d(TAG, "onContentRenderStarted called");
        this.currentContent = jSONObject;
        cancelReplayTimerAndSetLastNotification(jSONObject);
    }

    private boolean playDelimiterEarconAndContent(@NonNull JSONObject jSONObject) {
        Log.d(TAG, "playDelimiterEarconAndContent called");
        return playSpecifiedEarconAndContent(104, jSONObject);
    }

    private boolean playEarconForPlayGestureAndRenderContent(@NonNull JSONObject jSONObject) {
        Log.d(TAG, "playEarconForPlayGestureAndRenderContent called");
        return playSpecifiedEarconAndContent(101, jSONObject);
    }

    private boolean playIncomingEarconAndContent(@NonNull JSONObject jSONObject) {
        Log.d(TAG, "playIncomingEarconAndContent called");
        return playSpecifiedEarconAndContent(103, jSONObject);
    }

    private void postEventMessageToStopContentPlayback() {
        JSONObject jSONObject = this.currentContent;
        if (jSONObject != null) {
            String renderRequestUuid = getRenderRequestUuid(jSONObject.optString("uuid"));
            Log.i(TAG, String.format(Locale.US, "postEventMessageToStopContentPlayback - Stop rendering content: %s, with currentRenderId: %s", renderRequestUuid, Integer.valueOf(this.currentRenderId)));
            postEventMessageToRenderManager(this.currentRenderId, 2, renderRequestUuid);
            this.currentContent = null;
        }
    }

    private void recordPlayNotificationContent(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        HashMap outline133 = GeneratedOutlineSupport1.outline133(MetricsConstants.CUSTOM_VALUES_KEY, jSONObject.optString("packageIdentifier", "ErrorGettingPkgId"));
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.PLAY_NOTIFICATION_CONTENT, outline133);
        if (!ProcessNotificationModule.getNotificationType(jSONObject).equals(ProcessNotificationModule.NotificationType.COMMS)) {
            return;
        }
        MetricsRecorder.getInstance().recordCounter("FocusFilter_play_notification_content_comms", outline133);
    }

    private void releaseAudioRecord(@Nullable AudioRecord audioRecord) {
        try {
            audioRecord.release();
        } catch (Exception e) {
            String str = TAG;
            Log.i(str, "releaseAudioRecord - Exception: " + e);
        }
    }

    public static synchronized void releaseInstance() {
        synchronized (ContentStateHandler.class) {
            if (instance != null) {
                instance.currentContent = null;
            }
            instance = null;
        }
    }

    private void transitStateAfterContentRenderComplete() {
        try {
            dequeueNotification(this.currentContent.optString("uuid"));
            JSONObject peek = NotificationQueue.getInstance().peek();
            if (peek != null && (matchRunningActiveWindow(peek) || isNotificationFromSameSource(this.currentContent, peek))) {
                Log.i(TAG, "onContentRenderCompleted - Render complete and next notification matches active window");
                transitState(StateManager.STATE_CONTENT, 3, 3, null);
                return;
            }
            Log.i(TAG, "onContentRenderCompleted - Render completed and next notification is either null or not matching active window");
            transitState(StateManager.STATE_CONTENT_DELAY, 3, 3, null);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "transitStateAfterContentRenderComplete - Unlikely exception - " + e);
            transitState(StateManager.STATE_IDLE);
        }
    }

    private void updateActiveWindow() {
        NotificationSource commsNotificationSource;
        Log.d(TAG, "updateActiveWindow called");
        try {
            if (GroupNotificationHelper.isGroupMessage(this.currentContent)) {
                commsNotificationSource = new GroupNotificationSource(this.currentContent);
            } else {
                commsNotificationSource = new CommsNotificationSource(this.currentContent);
            }
            TimerManager.getInstance().getTimer(2).restart(commsNotificationSource, TimerManager.getInstance().createTimerExpiredCallback());
            removeMessages(4, 2, 1);
        } catch (IllegalArgumentException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateActiveWindow - IllegalArgumentException. currentContent = ");
            outline107.append(this.currentContent);
            Log.d(str, outline107.toString());
            Log.e(TAG, "updateActiveWindow - IllegalArgumentException.", e);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to updateActiveWindow.", e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean canCreateAudioRecorder() {
        /*
            r12 = this;
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentStateHandler.TAG
            java.lang.String r1 = "canCreateAudioRecorder"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r0, r1)
            boolean r0 = com.amazon.alexa.accessory.notificationpublisher.utils.PermissionChecker.hasRecordAudioPermission()
            r1 = 1
            if (r0 != 0) goto L16
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentStateHandler.TAG
            java.lang.String r2 = "canCreateAudioRecorder - Microphone permission not granted. Return true for the user to hear error TTS to make them aware of microphone permission"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r0, r2)
            return r1
        L16:
            r0 = 0
            r2 = 0
            r3 = 16000(0x3e80, float:2.2421E-41)
            r4 = 16
            r5 = 2
            int r11 = android.media.AudioRecord.getMinBufferSize(r3, r4, r5)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            android.media.AudioRecord r3 = new android.media.AudioRecord     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r7 = 6
            r8 = 16000(0x3e80, float:2.2421E-41)
            r9 = 16
            r10 = 2
            r6 = r3
            r6.<init>(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            int r2 = r3.getState()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
            if (r2 == r1) goto L3b
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentStateHandler.TAG     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
            java.lang.String r2 = "canCreateAudioRecorder - Cannot initialize AudioRecord"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r1, r2)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
            goto L43
        L3b:
            java.lang.String r2 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentStateHandler.TAG     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
            java.lang.String r4 = "canCreateAudioRecorder - AudioRecord initialized"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r2, r4)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
            r0 = r1
        L43:
            r12.releaseAudioRecord(r3)
            goto L69
        L47:
            r0 = move-exception
            r2 = r3
            goto L75
        L4a:
            r1 = move-exception
            r2 = r3
            goto L50
        L4d:
            r0 = move-exception
            goto L75
        L4f:
            r1 = move-exception
        L50:
            java.lang.String r3 = com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentStateHandler.TAG     // Catch: java.lang.Throwable -> L4d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4d
            r4.<init>()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r5 = "canCreateAudioRecorder - Exception: "
            r4.append(r5)     // Catch: java.lang.Throwable -> L4d
            r4.append(r1)     // Catch: java.lang.Throwable -> L4d
            java.lang.String r1 = r4.toString()     // Catch: java.lang.Throwable -> L4d
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r3, r1)     // Catch: java.lang.Throwable -> L4d
            r12.releaseAudioRecord(r2)
        L69:
            if (r0 != 0) goto L74
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r1 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r2 = "VipFilter_reply_no_background_recording"
            r1.recordCounter(r2)
        L74:
            return r0
        L75:
            r12.releaseAudioRecord(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentStateHandler.canCreateAudioRecorder():boolean");
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(@NonNull String str, Integer num, Integer num2, Integer num3) {
        boolean playDelimiterEarconAndContent;
        super.enterState(str, num, num2, num3);
        this.requestIdSuffix = UUID.randomUUID().toString();
        this.replayNotificationUuid = "";
        this.currentDistractionMode.set(DistractionModeProvider.getCurrentDistractionMode());
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("enterState - currentDistractionMode: ");
        outline107.append(this.currentDistractionMode.get());
        Log.i(str2, outline107.toString());
        if (isTransitionReasonGroupMessagesUserEducationOrMultiNotificationsInstructionComplete(str, num, num2, num3)) {
            Log.i(TAG, "enterState - Entered after group messages user education or multiple notifications instruction is complete. Transit to next notification");
            this.currentContent = NotificationQueue.getInstance().peek();
            transitStateAfterContentRenderComplete();
        } else if (handleReplay(str, num, num2)) {
            Log.i(TAG, "enterState - Replay content playback started successfully");
            onContentRenderStarted(NotificationQueue.getInstance().getLastNotification());
        } else {
            JSONObject peek = NotificationQueue.getInstance().peek();
            if (peek != null && num != null && num2 != null) {
                if (this.currentDistractionMode.get() == 3) {
                    Log.i(TAG, "enterState - Notification event - SKIP Render next notification content as SDM is ON");
                    onContentRenderStarted(peek);
                    removeHeadThenTransitForNextNotification(6, 18, null);
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERCONTENT_SKIPPED);
                    return;
                }
                if (num.intValue() == 2 && num2.intValue() == 1 && (str.equals(StateManager.STATE_ANNOUNCEMENT) || str.equals(StateManager.STATE_ANNOUNCEMENT_DELAY) || str.equals(StateManager.STATE_INSTRUCTIONAL_AUDIO))) {
                    Log.i(TAG, "enterState - Play gesture - Render next notification content");
                    playDelimiterEarconAndContent = playEarconForPlayGestureAndRenderContent(peek);
                } else if (num.intValue() == 1) {
                    Log.i(TAG, "enterState - Notification event - Render next notification content");
                    playDelimiterEarconAndContent = playIncomingEarconAndContent(peek);
                } else if (num.intValue() == 4 && num2.intValue() == 1 && num3 != null && num3.intValue() == 3) {
                    Log.i(TAG, "enterState - Action delay timer expired - Render next notification content");
                    playDelimiterEarconAndContent = playIncomingEarconAndContent(peek);
                } else if (num.intValue() == 3 && num2.intValue() == 3 && (str.equalsIgnoreCase(StateManager.STATE_CONTENT) || str.equalsIgnoreCase(StateManager.STATE_INSTRUCTIONAL_AUDIO))) {
                    Log.i(TAG, "enterState - Content render complete or Reply flow ended - Render next notification content");
                    playDelimiterEarconAndContent = playDelimiterEarconAndContent(peek);
                } else {
                    Log.w(TAG, "enterState - No matching condition found for component ID and event ID");
                    removeHeadThenTransitForNextNotification();
                    return;
                }
                if (playDelimiterEarconAndContent) {
                    Log.i(TAG, "enterState - Content playback started successfully");
                    onContentRenderStarted(peek);
                    return;
                }
                Log.w(TAG, "enterState - Not able to render content for current notification, skipping");
                removeHeadThenTransitForNextNotification();
                return;
            }
            Log.w(TAG, "enterState - Next notification is null or Component ID or event ID is null: transit state to idle state");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
            transitState(StateManager.STATE_IDLE);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        String str;
        super.handleGestureEvent(i, payload);
        if (this.currentContent == null) {
            Log.i(TAG, "handleGestureEvent - currentContent is null - Don't respond to gestures");
        } else if (i == 1) {
            Log.i(TAG, "handleGestureEvent - User swiped when content was playing. Stop playback and transit to Content state for Replay");
            postEventMessageToStopContentPlayback();
            startReplayTimer();
            transitState(StateManager.STATE_CONTENT, 2, 1, null);
        } else if (i != 2) {
        } else {
            Log.i(TAG, "handleGestureEvent - User stopped content playback. Stop playback and transit state");
            JSONObject peek = NotificationQueue.getInstance().peek();
            String optString = this.currentContent.optString("uuid");
            startReplayTimer();
            checkAndStopMatchingActiveWindow();
            postEventMessageToStopContentPlayback();
            postEventMessageToRenderManager(2, 1);
            if (peek != null && optString.equals(peek.optString("uuid"))) {
                NotificationQueue.getInstance().poll();
            }
            if (NotificationQueue.getInstance().isEmpty()) {
                Log.i(TAG, "handleGestureEvent - Queue is empty - Go to Idle");
                str = StateManager.STATE_IDLE;
            } else {
                str = StateManager.STATE_ANNOUNCEMENT;
            }
            transitState(str, 2, 2, null);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        boolean isSameNotification = isSameNotification(jSONObject, this.currentContent);
        super.handleNotificationEvent(i, jSONObject);
        if ((2 != i || !isSameNotification) && 3 != i) {
            return;
        }
        if (DistractionModeProvider.getCurrentDistractionMode() == this.currentDistractionMode.get()) {
            Log.i(TAG, "handleNotificationEvent - No change to distraction mode, update active window");
            updateActiveWindow();
        }
        transitStateForNextNotification(1, Integer.valueOf(i), null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleRendererEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleRendererEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        String optString = jSONObject.optString("uuid");
        if (optInt == 3) {
            String str = TAG;
            Log.i(str, "handleRendererEvent - Render completed for eventID: " + i + " uuid: " + optString);
            boolean z = DistractionModeProvider.getCurrentDistractionMode() == 4;
            String str2 = TAG;
            Log.d(str2, "handleRendererEvent - No distraction/Accessory DND state = " + z);
            if (z && !optString.equals(RenderManager.CONTACT_ACCEPTED_UUID) && !optString.equals(RenderManager.APP_ACCEPTED_UUID)) {
                Log.i(TAG, "handleRendererEvent - No distraction mode/Accessory DND is enabled, transit to idle");
                transitState(StateManager.STATE_IDLE, 6, 8, null);
                return;
            }
            onContentRenderCompleted(i, optString);
        } else if (optInt == 4) {
            String str3 = TAG;
            Log.d(str3, "handleRendererEvent - Render stopped for eventID: " + i + " uuid: " + optString);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERCONTENT_STOPPED);
        } else if (optInt != 5) {
            GeneratedOutlineSupport1.outline151("handleRendererEvent - Invalid Event Type: ", optInt, TAG);
        } else {
            String str4 = TAG;
            Log.w(str4, "handleRendererEvent - Render error for eventID: " + i + " uuid: " + optString);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERCONTENT_ERROR);
            transitState(StateManager.STATE_IDLE);
        }
    }

    @VisibleForTesting
    boolean isNotificationFromSameSource(@NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2) {
        ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
        if (notificationType.equals(ProcessNotificationModule.getNotificationType(jSONObject2))) {
            if (notificationType.equals(ProcessNotificationModule.NotificationType.COMMS)) {
                boolean isGroupMessage = GroupNotificationHelper.isGroupMessage(jSONObject);
                boolean isGroupMessage2 = GroupNotificationHelper.isGroupMessage(jSONObject2);
                if (isGroupMessage && isGroupMessage2) {
                    return new GroupNotificationSource(jSONObject).equals(new GroupNotificationSource(jSONObject2));
                }
                if (!isGroupMessage && !isGroupMessage2) {
                    return new CommsNotificationSource(jSONObject).equals(new CommsNotificationSource(jSONObject2));
                }
                return false;
            }
            return new GenericNotificationSource(jSONObject).equals(new GenericNotificationSource(jSONObject2));
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        postEventMessageToStopContentPlayback();
        this.currentRenderId = 100;
    }

    @VisibleForTesting
    boolean playSpecifiedEarconAndContent(int i, @NonNull JSONObject jSONObject) {
        boolean z;
        Log.d(TAG, "playSpecifiedEarconAndContent - earconEventId = " + i);
        try {
            this.currentRenderId = i;
            if (this.currentRenderId >= 100) {
                String string = jSONObject.getString("uuid");
                String renderRequestUuid = getRenderRequestUuid(string);
                if (string.equalsIgnoreCase(this.replayNotificationUuid)) {
                    this.replayNotificationUuid = renderRequestUuid;
                }
                NotificationFileHelper notificationFileHelper = new NotificationFileHelper(DependencyProvider.getContext(), string);
                String contentFilePath = notificationFileHelper.getContentFilePath();
                String mergedContentFilePath = notificationFileHelper.doesFileExists(notificationFileHelper.getMergedContentFilePath()) ? notificationFileHelper.getMergedContentFilePath() : null;
                recordPlayNotificationContent(jSONObject);
                JSONObject lastNotification = NotificationQueue.getInstance().getLastNotification();
                long timeElapsedInMillis = TimerManager.getInstance().getTimer(3).timeElapsedInMillis();
                if (jSONObject.optBoolean(NotificationConstants.IS_DETAILED_CONTENT_WITHOUT_PREFIX, false) && mergedContentFilePath != null) {
                    if (lastNotification != null && lastNotification.optString("uuid").equals(jSONObject.optString("uuid"))) {
                        Log.i(TAG, "playSpecifiedEarconAndContent - lastNotification uuid is the same with notification uuid");
                    } else if (lastNotification != null && isNotificationFromSameSource(lastNotification, jSONObject) && timeElapsedInMillis < this.backToBackNotificationTimeGapThreshold && !this.backToBackKeepPrefix) {
                        Log.i(TAG, String.format("playSpecifiedEarconAndContent - lastNotification uuid is NOT the same with notification uuid and time gap less than %s milliseconds", Long.valueOf(this.backToBackNotificationTimeGapThreshold)));
                        Log.i(TAG, "playSpecifiedEarconAndContent - back to back notifications, play content without contact says prefix");
                        z = true;
                    }
                    z = false;
                    contentFilePath = mergedContentFilePath;
                } else {
                    if (jSONObject.optBoolean(NotificationConstants.IS_DETAILED_CONTENT_WITHOUT_PREFIX, false) && mergedContentFilePath == null) {
                        Log.i(TAG, "playSpecifiedEarconAndContent - merge content file does not exist butisDetailContentWithoutPrefix is true");
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.MERGE_CONTENT_IS_NULL_DETAIL_CONTENT_WITHOUT_PREFIX);
                    }
                    z = false;
                }
                this.backToBackKeepPrefix = checkIfShouldKeepPrefix(z, jSONObject.optBoolean(NotificationConstants.PREFIX_REMOVED, false));
                return postEventMessageToRenderManager(i, 1, renderRequestUuid, contentFilePath);
            }
            throw new IllegalArgumentException("Invalid currentRenderId");
        } catch (Exception e) {
            Log.e(TAG, "Failed to playSpecifiedEarconAndContent.", e);
            return false;
        }
    }

    @VisibleForTesting
    boolean shouldOfferToReply(@NonNull String str) {
        if (!AccessoryProvider.getAccessoryDeviceType().equalsIgnoreCase("A3IYPH06PH1HRA")) {
            Log.i(TAG, "shouldOfferToReply' - Zion is not the latest connected device type. Return false");
            return false;
        } else if (!FeatureAccessChecker.hasVipFilterReplyFeatureAccess()) {
            Log.i(TAG, "shouldOfferToReply' - No weblab access for the user. Return false");
            return false;
        } else if (!SettingsStorageModule.getInstance().getReplySettingsWithDefault().booleanValue()) {
            Log.i(TAG, "shouldOfferToReply' - Reply is turned off. Return false");
            return false;
        } else if (!Strings.isNullOrEmpty(str) && this.currentContent != null) {
            JSONObject secondInQueue = NotificationQueue.getInstance().getSecondInQueue();
            if (secondInQueue != null && (isNotificationFromSameSource(this.currentContent, secondInQueue) || matchRunningActiveWindow(secondInQueue))) {
                Log.i(TAG, "shouldOfferToReply - false");
                return false;
            } else if (ReplyClientHelper.doesNotificationSupportReplyByUuid(str) && canCreateAudioRecorder()) {
                Log.i(TAG, "shouldOfferToReply - true");
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_FLOW_STARTED);
                return true;
            } else {
                Log.i(TAG, "shouldOfferToReply - Fall through - false");
                return false;
            }
        } else {
            Log.w(TAG, "shouldOfferToReply - Returning false because Empty UUID or current content is null UUID");
            return false;
        }
    }

    @VisibleForTesting
    boolean shouldPlayMultipleNotificationsInstruction(@NonNull JSONObject jSONObject) {
        return jSONObject.optBoolean("needMultipleNotificationsInstruction", false);
    }

    /* renamed from: clone */
    public ContentStateHandler m343clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton - ContentStateHandler");
    }
}
