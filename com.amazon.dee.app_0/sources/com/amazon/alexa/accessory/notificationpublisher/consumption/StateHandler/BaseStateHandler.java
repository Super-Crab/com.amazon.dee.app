package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.AccessoryNotifier;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.ZionAudioFocusRequest;
import com.amazon.alexa.accessory.notificationpublisher.consumption.ConsumptionEngine;
import com.amazon.alexa.accessory.notificationpublisher.consumption.MessageUtils;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationEventManager;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.CommsNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GenericNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GroupNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyTransientStorage;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.timers.BaseTimer;
import com.amazon.alexa.accessory.notificationpublisher.timers.TimerManager;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class BaseStateHandler extends BaseEventHandler {
    static final String NEED_MULTIPLE_NOTIFICATION_INSTRUCTION = "needMultipleNotificationsInstruction";
    private ImmutableSet<String> appsListToSkipMultipleNotifications = new ImmutableSet.Builder().mo7849add((ImmutableSet.Builder) "com.google.android.gm").mo7852build();
    private String logTag = getClass().getSimpleName();
    private String state;

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$ProcessNotificationModule$NotificationType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState = new int[SettingsStorageModule.FilterSettingsState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.REJECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.NOT_DETERMINED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.ACCEPTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$ProcessNotificationModule$NotificationType = new int[ProcessNotificationModule.NotificationType.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$ProcessNotificationModule$NotificationType[ProcessNotificationModule.NotificationType.COMMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$ProcessNotificationModule$NotificationType[ProcessNotificationModule.NotificationType.OTHER.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseStateHandler(@NonNull String str) {
        this.state = str;
    }

    private void recordEnqueuedNotification(@NonNull JSONObject jSONObject) {
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.NOTIFICATION_ENQUEUED);
        if (jSONObject == null || !jSONObject.optString("type").equals(ProcessNotificationModule.NotificationType.COMMS.name())) {
            return;
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.NOTIFICATION_COMMS_ENQUEUED);
    }

    void cancelActiveWindowTimer() {
        Log.i(this.logTag, "cancelActiveWindowTimer");
        try {
            TimerManager.getInstance().getTimer(2).cancel();
            removeMessages(4, 2, 1);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("cancelActiveWindowTimer - Exception ", e, this.logTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancelReplayTimerAndSetLastNotification(@NonNull JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("uuid");
            String str = this.logTag;
            Log.d(str, "cancelReplayTimerAndSetLastNotification - next last notification: " + optString);
            JSONObject lastNotification = NotificationQueue.getInstance().getLastNotification();
            String str2 = this.logTag;
            Log.d(str2, "cancelReplayTimerAndSetLastNotification - current last notification: " + lastNotification);
            JSONObject lastNotInvitationNotification = NotificationQueue.getInstance().getLastNotInvitationNotification();
            String str3 = this.logTag;
            Log.d(str3, "cancelReplayTimerAndSetLastNotification - last NonInvitation: " + lastNotInvitationNotification);
            if (lastNotification != null && !lastNotification.optString("uuid").equals(optString) && (lastNotInvitationNotification == null || !lastNotification.optString("uuid").equals(lastNotInvitationNotification.optString("uuid")))) {
                clearAudioFilesForRemovedNotification(lastNotification.optString("uuid"));
            }
            TimerManager.getInstance().getTimer(3).cancel();
            removeMessages(4, 3, 1);
            NotificationQueue.getInstance().setLastNotification(jSONObject);
            if (!jSONObject.optBoolean("isInvitation")) {
                String str4 = this.logTag;
                Log.d(str4, "cancelReplayTimerAndSetLastNotification - This is not an invitation: " + optString);
                updateLastNonInvitationNotification(jSONObject, lastNotInvitationNotification);
            } else if (getFilterState(jSONObject) != SettingsStorageModule.FilterSettingsState.ACCEPTED) {
            } else {
                String str5 = this.logTag;
                Log.d(str5, "cancelReplayTimerAndSetLastNotification - This is an accepted invitation: " + optString);
                updateLastNonInvitationNotification(jSONObject, lastNotInvitationNotification);
            }
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
            Log.e(this.logTag, "Failed to cancel replay timer and update last notification", e);
        }
    }

    boolean clearAudioFilesForRemovedNotification(@NonNull String str) {
        String str2 = this.logTag;
        Log.i(str2, "clearAudioFilesForRemovedNotification uuid: " + str);
        NotificationEventManager.getInstance().removeDismissedNotification(str);
        return NotificationEventManager.getInstance().removeNotificationAudioFile(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean clearLastNonInvitationNotificationWithAudioFile() {
        String clearLastNotInvitationNotification = NotificationQueue.getInstance().clearLastNotInvitationNotification();
        if (!Strings.isNullOrEmpty(clearLastNotInvitationNotification)) {
            return clearAudioFilesForRemovedNotification(clearLastNotInvitationNotification);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean clearLastNotificationWithAudioFile() {
        String clearLastNotification = NotificationQueue.getInstance().clearLastNotification();
        if (!Strings.isNullOrEmpty(clearLastNotification)) {
            return clearAudioFilesForRemovedNotification(clearLastNotification);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean clearLastNotificationWithTimer() {
        Log.i(this.logTag, "clearLastNotificationWithTimer");
        postEventMessageToTimerManager(3, 2);
        return clearLastNotificationWithAudioFile();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearQueuedNotificationAudioFile(@Nullable String str) {
        int size = NotificationQueue.getInstance().size();
        if (!Strings.isNullOrEmpty(str) && NotificationQueue.getInstance().contains(str)) {
            GeneratedOutlineSupport1.outline166("clearQueuedNotificationAudioFile exclude ", str, this.logTag);
            while (NotificationQueue.getInstance().size() > 1) {
                JSONObject poll = NotificationQueue.getInstance().poll();
                if (poll.optString("uuid").equals(str)) {
                    NotificationQueue.getInstance().add(str, poll);
                } else {
                    clearAudioFilesForRemovedNotification(poll.optString("uuid"));
                }
            }
            NotificationQueue.getInstance().clear();
        } else {
            while (!NotificationQueue.getInstance().isEmpty()) {
                clearAudioFilesForRemovedNotification(NotificationQueue.getInstance().poll().optString("uuid"));
            }
            Log.i(this.logTag, "clearQueuedNotificationAudioFile clear all");
        }
        AccessoryNotifier.onMessageNotification(false);
        if (size > 0) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.QUEUED_NOTIFICATION_CLEARED, MetricsConstants.COMPONENT_NAME, size, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearReplyTransientStorage() {
        Log.d(this.logTag, "clearReplyTransientStorage");
        ReplyTransientStorage.getInstance().clearPayload();
    }

    public void enterState(@NonNull String str, Integer num, Integer num2, Integer num3) {
        String format = String.format(Locale.US, "enterState - fromState: %s, componentId: %d, eventId: %d, eventType: %d", str, num, num2, num3);
        if ((this instanceof AnnouncementStateHandler) || (this instanceof AnnouncementActDelayStateHandler) || (this instanceof ContentStateHandler) || (this instanceof ContentActDelayHandler) || (this instanceof IdleStateHandler)) {
            clearReplyTransientStorage();
        }
        Log.i(this.logTag, format);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SettingsStorageModule.FilterSettingsState getFilterState(@NonNull JSONObject jSONObject) throws JSONException, UnsupportedEncodingException, RxBlockingCallException {
        SettingsStorageModule settingsStorageModule = SettingsStorageModule.getInstance();
        ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
        int ordinal = notificationType.ordinal();
        if (ordinal == 0) {
            SettingsStorageModule.FilterSettingsState appFilterSettingsState = settingsStorageModule.getAppFilterSettingsState(new GenericNotificationSource(jSONObject));
            if (appFilterSettingsState == null) {
                return null;
            }
            int ordinal2 = appFilterSettingsState.ordinal();
            if (ordinal2 != 0) {
                if (ordinal2 == 1) {
                    return appFilterSettingsState;
                }
                if (ordinal2 != 2) {
                    String str = this.logTag;
                    Log.w(str, "getFilterState - invalid appState " + appFilterSettingsState);
                } else {
                    settingsStorageModule.createAndPutAppFilterSettings(jSONObject);
                }
            }
            if (GroupNotificationHelper.isGroupMessage(jSONObject)) {
                return settingsStorageModule.getGroupFilterSettingsState(new GroupNotificationSource(jSONObject));
            }
            return settingsStorageModule.getContactFilterSettingsState(new CommsNotificationSource(jSONObject));
        } else if (ordinal != 1) {
            String str2 = this.logTag;
            Log.e(str2, "getFilterState - Invalid notification type " + notificationType);
        } else {
            return settingsStorageModule.getAppFilterSettingsState(new GenericNotificationSource(jSONObject));
        }
        return SettingsStorageModule.FilterSettingsState.REJECTED;
    }

    public String getState() {
        return this.state;
    }

    JSONObject getValidJSONPayload(@Nullable Object obj) {
        try {
            return (JSONObject) JSONObject.class.cast(obj);
        } catch (ClassCastException e) {
            String str = this.logTag;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getValidJSONPayload - ClassCastException ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
            return null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleAudioFocusEvent(int i) {
        String str = this.logTag;
        Log.d(str, "handleAudioFocusEvent - eventId : " + i);
        if (i == 2 || i == 3) {
            transitState(StateManager.STATE_IDLE, 7, Integer.valueOf(i), null);
        }
    }

    void handleDismissLastNotification(@NonNull String str) {
        String str2 = this.logTag;
        Log.i(str2, "handleDismissLastNotification - dismissed uuid: " + str);
        JSONObject lastNotification = NotificationQueue.getInstance().getLastNotification();
        if (lastNotification != null && lastNotification.optString("uuid").equals(str)) {
            boolean clearLastNotificationWithTimer = clearLastNotificationWithTimer();
            String str3 = this.logTag;
            Log.i(str3, "handleDismissLastNotification - remove last notification audio: " + clearLastNotificationWithTimer);
        }
        JSONObject lastNotInvitationNotification = NotificationQueue.getInstance().getLastNotInvitationNotification();
        if (lastNotInvitationNotification == null || !lastNotInvitationNotification.optString("uuid").equals(str)) {
            return;
        }
        boolean clearLastNonInvitationNotificationWithAudioFile = clearLastNonInvitationNotificationWithAudioFile();
        String str4 = this.logTag;
        Log.i(str4, "handleDismissLastNotification - remove last non-invitation audio: " + clearLastNonInvitationNotificationWithAudioFile);
    }

    @VisibleForTesting
    void handleDismissQueuedNotification(@NonNull String str) {
        if (NotificationQueue.getInstance().contains(str)) {
            String str2 = this.logTag;
            Log.i(str2, "handleDismissQueuedNotification - Remove notification in queue: " + str);
            NotificationQueue.getInstance().remove(str);
            clearAudioFilesForRemovedNotification(str);
            if (!NotificationQueue.getInstance().isEmpty()) {
                return;
            }
            AccessoryNotifier.onMessageNotification(false);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        String str = this.logTag;
        Log.i(str, "handleGestureEvent - eventId : " + i);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        String str = this.logTag;
        Log.i(str, "handleNotificationEvent - eventId: " + i);
        String str2 = this.logTag;
        Log.d(str2, "handleNotificationEvent - notification: " + jSONObject);
        String optString = jSONObject.optString("uuid");
        if (i == 1) {
            if (!shouldEnqueue(jSONObject)) {
                return;
            }
            GeneratedOutlineSupport1.outline166("handleNotificationEvent- New notification posted, uuid: ", optString, this.logTag);
            recordEnqueuedNotification(jSONObject);
            NotificationQueue.getInstance().add(optString, jSONObject);
            if (jSONObject.optBoolean("isInvitation")) {
                return;
            }
            AccessoryNotifier.onMessageNotification(true);
        } else if (i == 2) {
            GeneratedOutlineSupport1.outline166("handleNotificationEvent- Notification dismissed , uuid: ", optString, this.logTag);
            handleDismissLastNotification(optString);
            handleDismissQueuedNotification(optString);
        } else if (i != 3) {
            GeneratedOutlineSupport1.outline151("handleNotificationEvent - invalid eventId: ", i, this.logTag);
        } else {
            Log.i(this.logTag, "handleNotificationEvent- All Notifications dismissed");
            handleDismissLastNotification();
            handleDismissQueuedNotification();
            NotificationEventManager.getInstance().clearIncomingNotifications();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handlePlayGestureWithInvitationAnnouncement(@NonNull JSONObject jSONObject) {
        Log.i(this.logTag, "handlePlayGestureWithInvitationAnnouncement - Gesture Play to accept the invitation - play accepted audio and set last notification");
        updateStorageUponPlayGesture(jSONObject);
        cancelReplayTimerAndSetLastNotification(jSONObject);
        transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, 1, null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleRendererEvent(int i, @NonNull JSONObject jSONObject) {
        String str = this.logTag;
        Log.i(str, "handleRendererEvent - eventId : " + i + " payload: " + jSONObject);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleReplyReadBackEvent(int i, @Nullable Object obj) {
        String str = this.logTag;
        Log.d(str, "handleReplyReadBackEvent - eventId : " + i + " payload: " + obj);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleSpeechRecognizerEvent(int i, @Nullable Object obj) {
        String str = this.logTag;
        Log.d(str, "handleSpeechRecognizerEvent - eventId : " + i + " payload: " + obj);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleStatusEvent(int i) {
        String str = this.logTag;
        Log.i(str, "handleStatusEvent - eventId : " + i);
        switch (i) {
            case 1:
            case 3:
            case 5:
            case 6:
            case 8:
            case 16:
            case 18:
                transitState(StateManager.STATE_IDLE, 6, Integer.valueOf(i), null);
                return;
            case 2:
            case 4:
            case 7:
            case 9:
            case 19:
                return;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                cancelActiveWindowTimer();
                return;
            case 17:
                StatusEventManager.getInstance().onPerformAudioFileCleanUp();
                return;
            default:
                GeneratedOutlineSupport1.outline151("handleStatusEvent - Invalid event Id: ", i, this.logTag);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleStopGestureWithAnnouncementAndInstruction() {
        postEventMessageToRenderManager(2, 1);
        JSONObject lastValue = NotificationQueue.getInstance().getLastValue();
        if (lastValue != null) {
            cancelReplayTimerAndSetLastNotification(lastValue);
        } else {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
            Log.e(this.logTag, "most recent notification should not be null, we have at least one item in the queue");
        }
        postEventMessageToTimerManager(2, 2);
        startReplayTimer();
        transitState(StateManager.STATE_IDLE, 2, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleStopGestureWithInvitationAnnouncement(@NonNull JSONObject jSONObject, @Nullable String str) {
        Log.i(this.logTag, "handleStopGestureWithInvitationAnnouncement - Gesture Stop to reject the invitation - play rejected audio");
        updateStorageUponStopGesture(jSONObject, str);
        transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, 2, null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        String str = this.logTag;
        Log.i(str, "handleTimerEvent - eventId: " + i);
        String str2 = this.logTag;
        Log.d(str2, "handleTimerEvent - payload: " + jSONObject);
    }

    boolean hasNotificationFromSameAppInQueue(@NonNull String str) throws JSONException {
        Iterator<JSONObject> notificationIterator = NotificationQueue.getInstance().getNotificationIterator();
        while (notificationIterator.hasNext()) {
            JSONObject next = notificationIterator.next();
            if (str.equals(next.optString("packageIdentifier"))) {
                Log.i(this.logTag, "hasNotificationFromSameAppInQueue - true");
                next.put(NEED_MULTIPLE_NOTIFICATION_INSTRUCTION, true);
                return true;
            }
        }
        Log.i(this.logTag, "hasNotificationFromSameAppInQueue - false");
        return false;
    }

    boolean isLastNotificationOrLastNonInvitation(@NonNull JSONObject jSONObject) {
        String optString = jSONObject.optString("uuid");
        JSONObject lastNotification = NotificationQueue.getInstance().getLastNotification();
        JSONObject lastNotInvitationNotification = NotificationQueue.getInstance().getLastNotInvitationNotification();
        return (lastNotification != null && optString.equals(lastNotification.optString("uuid"))) || (lastNotInvitationNotification != null && optString.equals(lastNotInvitationNotification.optString("uuid")));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSameNotification(JSONObject jSONObject, JSONObject jSONObject2) {
        return (jSONObject == null || jSONObject2 == null || !jSONObject.optString("uuid").equals(jSONObject2.optString("uuid"))) ? false : true;
    }

    public void leaveState(String str) {
        GeneratedOutlineSupport1.outline166("leaveState - toState : ", str, this.logTag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Integer mapGestureEventToRenderEvent(int i) {
        if (i != 1) {
            if (i != 2) {
                GeneratedOutlineSupport1.outline151("Invalid gesture eventId: ", i, this.logTag);
                return null;
            }
            return 2;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchRunningActiveWindow(@NonNull JSONObject jSONObject) {
        String optString = jSONObject.optString("type");
        BaseTimer timer = TimerManager.getInstance().getTimer(2);
        boolean z = false;
        if (optString.equals(ProcessNotificationModule.NotificationType.COMMS.name())) {
            try {
                if (SettingsStorageModule.getInstance().getFocusFilterEnabledWithDefault().booleanValue()) {
                    z = timer.isRunning(GroupNotificationHelper.isGroupMessage(jSONObject) ? new GroupNotificationSource(jSONObject) : new CommsNotificationSource(jSONObject));
                }
            } catch (Exception e) {
                String str = this.logTag;
                Log.e(str, "matchRunningActiveWindow - Exception: " + e);
            }
        }
        String str2 = this.logTag;
        Log.i(str2, "matchRunningActiveWindow = " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean postEventMessage(int i, int i2, Object obj) {
        return ConsumptionEngine.getInstance().postEventMessage(2, i, i2, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean postEventMessageToRenderManager(int i, int i2, @Nullable String str, @Nullable String str2) {
        try {
            return postEventMessage(3, i, new JSONObject().put("eventType", i2).put("uuid", str).put("fileToPlay", str2));
        } catch (JSONException e) {
            String str3 = this.logTag;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("postEventMessageToRenderManager - JSONException: ");
            outline107.append(e.getMessage());
            Log.e(str3, outline107.toString());
            return false;
        }
    }

    boolean postEventMessageToTimerManager(int i, int i2, NotificationSource notificationSource) {
        try {
            return postEventMessage(4, i, new JSONObject().put("eventType", i2).put("notificationSrc", notificationSource));
        } catch (JSONException e) {
            String str = this.logTag;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("postEventMessageToTimerManager - JSONException: ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseAudioFocus() {
        AudioFocusManager.getInstance().releaseAudioFocus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeHeadThenTransitForNextNotification() {
        removeHeadThenTransitForNextNotification(null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeMessages(int i, int i2, int i3) {
        try {
            ConsumptionEngine.getInstance().removeMessages(MessageUtils.generateMessageType(i, i2), i3);
        } catch (NumberFormatException e) {
            String str = this.logTag;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid messageType - ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeReplyReadBackAudioFile() {
        Log.d(this.logTag, "removeReplyReadBackAudioFile");
        new NotificationFileHelper(DependencyProvider.getContext()).removeReadBackFile();
    }

    void requestAudioFocus(int i, int i2, @Nullable String str, @Nullable String str2) {
        Log.d(this.logTag, "requestAudioFocus");
        AudioFocusManager.getInstance().requestAudioFocus(new ZionAudioFocusRequest(i, i2, str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:1|2|3|(2:5|(4:7|8|9|10))|16|8|9|10|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003c, code lost:
        r9 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r8.logTag, "shouldEnqueue - failed.", r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean shouldEnqueue(@androidx.annotation.NonNull org.json.JSONObject r9) {
        /*
            r8 = this;
            r0 = 1
            java.lang.String r1 = "packageIdentifier"
            java.lang.String r1 = r9.optString(r1)     // Catch: java.lang.Exception -> L3e
            com.google.common.collect.ImmutableSet<java.lang.String> r2 = r8.appsListToSkipMultipleNotifications     // Catch: java.lang.Exception -> L3e
            boolean r2 = r2.contains(r1)     // Catch: java.lang.Exception -> L3e
            r3 = 0
            if (r2 == 0) goto L1a
            boolean r1 = r8.hasNotificationFromSameAppInQueue(r1)     // Catch: java.lang.Exception -> L3e
            if (r1 != 0) goto L18
            goto L1a
        L18:
            r1 = r3
            goto L1b
        L1a:
            r1 = r0
        L1b:
            java.lang.String r2 = r8.logTag     // Catch: java.lang.Exception -> L3c
            java.util.Locale r4 = java.util.Locale.US     // Catch: java.lang.Exception -> L3c
            java.lang.String r5 = "shouldEnqueue: %s - notification uuid: %s"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L3c
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Exception -> L3c
            r6[r3] = r7     // Catch: java.lang.Exception -> L3c
            java.lang.String r3 = "uuid"
            java.lang.String r9 = r9.optString(r3)     // Catch: java.lang.Exception -> L3c
            r6[r0] = r9     // Catch: java.lang.Exception -> L3c
            java.lang.String r9 = java.lang.String.format(r4, r5, r6)     // Catch: java.lang.Exception -> L3c
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r2, r9)     // Catch: java.lang.Exception -> L3c
            goto L48
        L3c:
            r9 = move-exception
            goto L40
        L3e:
            r9 = move-exception
            r1 = r0
        L40:
            java.lang.String r0 = r8.logTag
            java.lang.String r2 = "shouldEnqueue - failed."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r0, r2, r9)
        L48:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler.shouldEnqueue(org.json.JSONObject):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldPlayGroupMessagesUserEducation(@NonNull JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        try {
            if (GroupNotificationHelper.isGroupMessage(jSONObject) || FeatureToggleModule.getInstance().getGroupMessagesMasterToggleState()) {
                return false;
            }
            return !SettingsStorageModule.getInstance().getGroupMessagesUserEducationComplete().booleanValue();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("shouldPlayGroupMessagesUserEducation - Exception: ", e, this.logTag);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean startReplayTimer() {
        return postEventMessageToTimerManager(3, 1);
    }

    public void transitState(String str) {
        StateManager.getInstance().transitState(str);
    }

    void transitStateBasedOnActiveWindow(@NonNull JSONObject jSONObject, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        if (matchRunningActiveWindow(jSONObject)) {
            Log.i(this.logTag, "transitStateBasedOnActiveWindow - next notification under active window");
            transitState(StateManager.STATE_CONTENT, num, num2, num3);
            return;
        }
        transitState(StateManager.STATE_ANNOUNCEMENT, num, num2, num3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transitStateForNextNotification() {
        transitStateForNextNotification(null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transitToContent() {
        startReplayTimer();
        transitState(StateManager.STATE_CONTENT, 2, 1, null);
    }

    void updateLastNonInvitationNotification(@NonNull JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && !jSONObject2.optString("uuid").equals(jSONObject.optString("uuid"))) {
            clearLastNonInvitationNotificationWithAudioFile();
        }
        NotificationQueue.getInstance().setLastNotInvitationNotification(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateNumOfOpenedNotification() {
        int intValue = SettingsStorageModule.getInstance().getNumOfOpenedNotification().intValue();
        if (intValue < 3) {
            try {
                SettingsStorageModule.getInstance().putNumOfOpenedNotification(Integer.valueOf(intValue + 1));
            } catch (Exception e) {
                Log.w(this.logTag, "updateNumOfOpenedNotification failed with error", e);
            }
        }
    }

    void updateNumOfRejectedInvitation(@Nullable String str, int i) {
        Integer numOfRejectedInvitation;
        boolean z = FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess() && str != null;
        try {
            if (z) {
                numOfRejectedInvitation = SettingsStorageModule.getInstance().getNumOfRejectedInvitation(str);
            } else {
                numOfRejectedInvitation = SettingsStorageModule.getInstance().getNumOfRejectedInvitation();
            }
            int intValue = numOfRejectedInvitation.intValue();
            if (intValue > 1 || i == 2) {
                return;
            }
            if (z) {
                SettingsStorageModule.getInstance().putNumOfRejectedInvitation(str, Integer.valueOf(intValue + 1));
            } else {
                SettingsStorageModule.getInstance().putNumOfRejectedInvitation(Integer.valueOf(intValue + 1));
            }
        } catch (Exception e) {
            String str2 = this.logTag;
            Log.e(str2, "handleGestureEvent - updateNumOfRejectedNotification error " + e);
        }
    }

    void updateStorageUponPlayGesture(@NonNull JSONObject jSONObject) {
        try {
            ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
            int ordinal = notificationType.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    String str = this.logTag;
                    Log.w(str, "updateStorageUponPlayGesture -  invalid Notification type " + notificationType);
                } else {
                    SettingsStorageModule.getInstance().updateAppFilterState(jSONObject, SettingsStorageModule.FilterSettingsState.ACCEPTED);
                }
            } else if (GroupNotificationHelper.isGroupMessage(jSONObject)) {
                SettingsStorageModule.getInstance().updateGroupFilterState(jSONObject, SettingsStorageModule.FilterSettingsState.ACCEPTED);
            } else {
                SettingsStorageModule.getInstance().updateContactFilterState(jSONObject, SettingsStorageModule.FilterSettingsState.ACCEPTED);
            }
        } catch (Exception e) {
            String str2 = this.logTag;
            Log.e(str2, "handleGestureEvent - updateStorageUponPlayGesture error " + e);
        }
    }

    void updateStorageUponStopGesture(@NonNull JSONObject jSONObject, @Nullable String str) {
        try {
            ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
            int ordinal = notificationType.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    String str2 = this.logTag;
                    Log.w(str2, "updateStorageUponStopGesture -  invalid Notification type " + notificationType);
                } else {
                    SettingsStorageModule.getInstance().updateAppFilterState(jSONObject, SettingsStorageModule.FilterSettingsState.REJECTED);
                }
            } else if (GroupNotificationHelper.isGroupMessage(jSONObject)) {
                SettingsStorageModule.getInstance().updateGroupFilterState(jSONObject, SettingsStorageModule.FilterSettingsState.REJECTED);
            } else {
                SettingsStorageModule.getInstance().updateContactFilterState(jSONObject, SettingsStorageModule.FilterSettingsState.REJECTED);
            }
            updateNumOfRejectedInvitation(str, GroupNotificationHelper.getGroupMessageType(jSONObject));
        } catch (Exception e) {
            String str3 = this.logTag;
            Log.e(str3, "handleGestureEvent - updateStorageUponStopGesture error " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean postEventMessage(int i, int i2) {
        return postEventMessage(i, i2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeHeadThenTransitForNextNotification(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        Log.i(this.logTag, "removeHeadThenTransitForNextNotification called - Remove head of queue and its audio file, transit to next state");
        JSONObject poll = NotificationQueue.getInstance().poll();
        String str = this.logTag;
        Log.d(str, "removeHeadThenTransitForNextNotification - removed notification: " + poll + " last notification: " + NotificationQueue.getInstance().getLastNotification());
        if (poll != null && !isLastNotificationOrLastNonInvitation(poll)) {
            NotificationEventManager.getInstance().removeNotificationAudioFile(poll.optString("uuid"));
        }
        transitStateForNextNotification(num, num2, num3);
    }

    public void transitState(@NonNull String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        StateManager.getInstance().transitState(str, num, num2, num3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transitStateForNextNotification(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        JSONObject peek = NotificationQueue.getInstance().peek();
        if ((num != null && num.intValue() == 1 && num2 != null && 3 == num2.intValue()) || peek == null) {
            transitState(StateManager.STATE_IDLE, num, num2, num3);
        } else {
            transitStateBasedOnActiveWindow(peek, num, num2, num3);
        }
    }

    boolean clearAudioFilesForRemovedNotification() {
        Log.i(this.logTag, "clearAudioFilesForRemovedNotification all notifications");
        NotificationEventManager.getInstance().removeDismissedAllNotification();
        return NotificationEventManager.getInstance().removeAllNotificationAudioFile();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateNumOfOpenedNotification(@Nullable String str) {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess() && str != null) {
            int intValue = SettingsStorageModule.getInstance().getNumOfOpenedNotification(str).intValue();
            if (intValue >= 3) {
                return;
            }
            try {
                SettingsStorageModule.getInstance().putNumOfOpenedNotification(str, Integer.valueOf(intValue + 1));
                return;
            } catch (Exception e) {
                Log.w(this.logTag, "updateNumOfOpenedNotification failed with error", e);
                return;
            }
        }
        updateNumOfOpenedNotification();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean postEventMessageToTimerManager(int i, int i2) {
        return postEventMessageToTimerManager(i, i2, null);
    }

    @VisibleForTesting
    void handleDismissQueuedNotification() {
        Log.i(this.logTag, "handleDismissQueuedNotification - Remove all notifications in queue");
        clearAudioFilesForRemovedNotification();
        NotificationQueue.getInstance().clear();
        AccessoryNotifier.onMessageNotification(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean postEventMessageToRenderManager(int i, int i2) {
        return postEventMessageToRenderManager(i, i2, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean postEventMessageToRenderManager(int i, int i2, @Nullable String str) {
        return postEventMessageToRenderManager(i, i2, str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class StateTransitionInfo {
        private static final String TAG = "StateTransitionInfo";
        private Integer componentId;
        private final BaseStateHandler currentStateHandler;
        private Integer eventId;
        private Integer eventType;
        private String toState;
        private final boolean transitStateBasedOnNextNotification;

        public StateTransitionInfo(@NonNull BaseStateHandler baseStateHandler, @NonNull String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
            Preconditions.notNull(baseStateHandler, "currentStateHandler");
            Preconditions.precondition(!Strings.isNullOrEmpty(str), "toState");
            this.currentStateHandler = baseStateHandler;
            this.toState = str;
            this.componentId = num;
            this.eventId = num2;
            this.eventType = num3;
            this.transitStateBasedOnNextNotification = false;
        }

        public void transit() {
            if (Strings.isNullOrEmpty(this.toState) && this.transitStateBasedOnNextNotification) {
                Log.i(TAG, "transit - Transit based on next notification");
                this.currentStateHandler.transitStateForNextNotification();
            } else if (!Strings.isNullOrEmpty(this.toState)) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transit - Transit to state = ");
                outline107.append(this.toState);
                Log.i(str, outline107.toString());
                this.currentStateHandler.transitState(this.toState, this.componentId, this.eventId, this.eventType);
            } else {
                Log.w(TAG, "transit - Unexpected state, transit to idle");
                this.currentStateHandler.transitState(StateManager.STATE_IDLE);
            }
        }

        public StateTransitionInfo(@NonNull BaseStateHandler baseStateHandler) {
            Preconditions.notNull(baseStateHandler, "currentStateHandler");
            this.currentStateHandler = baseStateHandler;
            this.transitStateBasedOnNextNotification = true;
        }
    }

    void handleDismissLastNotification() {
        if (NotificationQueue.getInstance().getLastNotification() != null) {
            boolean clearLastNotificationWithTimer = clearLastNotificationWithTimer();
            String str = this.logTag;
            Log.i(str, "handleDismissLastNotification - remove last notification audio: " + clearLastNotificationWithTimer);
        }
        if (NotificationQueue.getInstance().getLastNotInvitationNotification() != null) {
            boolean clearLastNonInvitationNotificationWithAudioFile = clearLastNonInvitationNotificationWithAudioFile();
            String str2 = this.logTag;
            Log.i(str2, "handleDismissLastNotification - remove last non-invitation audio: " + clearLastNonInvitationNotificationWithAudioFile);
        }
    }
}
