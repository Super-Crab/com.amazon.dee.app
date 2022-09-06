package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyClientHelper;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyTransientStorage;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ReplyStateHandler extends BaseStateHandler {
    public static final int MAX_NUM_OF_RETRY_FOR_REPLY = 3;
    private static final String TAG = "ReplyStateHandler";
    private static ReplyStateHandler instance;
    private AtomicBoolean hasAudioFocus;
    private boolean previousRecordingWasLowConfidence;
    private SettingsStorageModule settingsStorageModule;
    private BaseStateHandler.StateTransitionInfo stateTransitionInfo;
    private AtomicBoolean waitingForAudioFocusBeforeTransit;

    private ReplyStateHandler() {
        super(StateManager.STATE_REPLY);
        this.previousRecordingWasLowConfidence = false;
        this.hasAudioFocus = new AtomicBoolean(false);
        this.waitingForAudioFocusBeforeTransit = new AtomicBoolean(false);
        this.settingsStorageModule = SettingsStorageModule.getInstance();
    }

    private void cancelTranscribe() {
        Log.d(TAG, "cancelTranscribe");
        postEventMessage(8, 2);
    }

    public static synchronized ReplyStateHandler getInstance() {
        ReplyStateHandler replyStateHandler;
        synchronized (ReplyStateHandler.class) {
            if (instance == null) {
                instance = new ReplyStateHandler();
            }
            replyStateHandler = instance;
        }
        return replyStateHandler;
    }

    private String getStringFromPayload(Object obj) {
        return obj instanceof String ? (String) obj : "";
    }

    private void handleReplySuccessful() {
        Log.d(TAG, "handleReplySuccessful");
        clearReplyTransientStorage();
        removeReplyReadBackAudioFile();
        JSONObject lastNotification = NotificationQueue.getInstance().getLastNotification();
        JSONObject lastNotInvitationNotification = NotificationQueue.getInstance().getLastNotInvitationNotification();
        NotificationQueue.getInstance().poll();
        clearLastNotificationWithAudioFile();
        if (lastNotification != null && lastNotInvitationNotification != null && lastNotification.optString("uuid").equalsIgnoreCase(lastNotInvitationNotification.optString("uuid"))) {
            Log.d(TAG, "handleReplySuccessful - Also clear last non-invitation notification");
            NotificationQueue.getInstance().clearLastNotInvitationNotification();
            clearLastNonInvitationNotificationWithAudioFile();
        }
        transitWithReplySuccess();
    }

    public static synchronized void releaseInstance() {
        synchronized (ReplyStateHandler.class) {
            instance = null;
        }
    }

    private void sendReply() {
        Log.i(TAG, "sendReply");
        JSONObject peek = NotificationQueue.getInstance().peek();
        if (peek == null) {
            Log.w(TAG, "sendReply - No notifications in queue, error out and transit");
            transitWithReplyError(8);
            return;
        }
        String optString = peek.optString(Constants.BUNDLE_KEY_NOTIFICATION_ID);
        String optString2 = peek.optString("uuid");
        if (!Strings.isNullOrEmpty(optString) && !Strings.isNullOrEmpty(optString2)) {
            try {
                if (ReplyTransientStorage.getInstance().isPayloadValid(optString2)) {
                    if (ReplyClientHelper.sendReply(optString, ReplyTransientStorage.getInstance().getTranscribedText())) {
                        Log.i(TAG, "sendReply - Reply sent successfully");
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_SEND_SUCCESS);
                        handleReplySuccessful();
                    } else {
                        Log.e(TAG, "sendReply - Error sending reply");
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_SEND_ERROR_GENERIC);
                        transitWithReplyError(10);
                    }
                } else {
                    Log.w(TAG, "sendReply - Payload for reply is invalid, error out and transit");
                    transitWithReplyError(8);
                }
                return;
            } catch (Exception e) {
                String str = TAG;
                Log.e(str, "sendReply - Exception: " + e);
                MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MetricsConstants.REPLY_SEND_EXCEPTION);
                outline107.append(e.getClass().getSimpleName());
                metricsRecorder.recordCounter(outline107.toString(), MetricsRecorder.customAttributesForException(e, 512));
                transitWithReplyError(8);
                return;
            }
        }
        Log.w(TAG, "sendReply - Notification ID or UUID is empty, error out and transit");
        transitWithReplyError(8);
    }

    private void signalManualSpeechEndPointing() {
        Log.d(TAG, "signalManualSpeechEndPointing");
        postEventMessage(8, 3);
    }

    private void startTranscribe() {
        Log.d(TAG, "startTranscribe");
        postEventMessage(8, 1);
    }

    private void transitWithReplyError(int i) {
        String str = TAG;
        Log.d(str, "transitWithReplyError - eventId = " + i);
        clearReplyTransientStorage();
        removeReplyReadBackAudioFile();
        if (i != 7 && i != 9) {
            Log.i(TAG, "transitWithReplyError - Poll notification queue");
            NotificationQueue.getInstance().poll();
        }
        waitForAudioFocusBeforeTransitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 8, Integer.valueOf(i), null);
    }

    private void transitWithReplySuccess() {
        Log.d(TAG, "transitWithReplySuccess");
        waitForAudioFocusBeforeTransitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 8, 11, null);
    }

    private void transitWithTranscribeConfidenceLow() {
        Log.d(TAG, "transitWithTranscribeConfidenceLow");
        waitForAudioFocusBeforeTransitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 8, 12, null);
    }

    @VisibleForTesting
    boolean didEnterForReadBackError(@NonNull String str, Integer num, Integer num2) {
        try {
            if (Strings.isNullOrEmpty(str) || !str.equalsIgnoreCase(StateManager.STATE_REPLY_READ_BACK) || num == null || num.intValue() != 9 || num2 == null) {
                return false;
            }
            if (num2.intValue() == 3) {
                return true;
            }
            return num2.intValue() == 4;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("didEnterForReadBackError - Exception: ", e, TAG);
            return false;
        }
    }

    @VisibleForTesting
    boolean didEnterForSendingReply(@NonNull String str, Integer num, Integer num2) {
        try {
            if (Strings.isNullOrEmpty(str)) {
                return false;
            }
            if ((!str.equalsIgnoreCase(StateManager.STATE_REPLY_READ_BACK) && !str.equalsIgnoreCase(StateManager.STATE_REPLY_READ_BACK_ACT_DELAY)) || num == null || num.intValue() != 9 || num2 == null) {
                return false;
            }
            return num2.intValue() == 6;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("didEnterForSendingReply - Exception: ", e, TAG);
            return false;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(@NonNull String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        this.hasAudioFocus.set(true);
        this.waitingForAudioFocusBeforeTransit.set(false);
        if (didEnterForSendingReply(str, num, num2)) {
            Log.i(TAG, "enterState - didEnterForSendingReply is true");
            ReplyTransientStorage.getInstance().resetCounter();
            sendReply();
            return;
        }
        clearReplyTransientStorage();
        removeReplyReadBackAudioFile();
        this.previousRecordingWasLowConfidence = false;
        int i = 8;
        if (didEnterForReadBackError(str, num, num2)) {
            Log.i(TAG, "enterState - didEnterForReadBackError is true");
            if (num2.intValue() == 4) {
                Log.i(TAG, "enterState - didEnterForReadBackError error retry available");
                i = 7;
            }
            transitBasedOnRetryLimit(Integer.valueOf(i));
        } else if (!str.equals(StateManager.STATE_INSTRUCTIONAL_AUDIO)) {
            String str2 = TAG;
            Log.e(str2, "enterState - Invalid from state - " + str);
            waitForAudioFocusAndTransitStateForNextNotification();
        } else {
            if (num != null && num.intValue() == 8 && num2.intValue() == 12) {
                Log.i(TAG, "enterState - Previous speech recognized with low confidence, try again");
                this.previousRecordingWasLowConfidence = true;
            }
            startTranscribe();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleAudioFocusEvent(int i) {
        String str = TAG;
        Log.i(str, "handleAudioFocusEvent - eventId : " + i);
        if (i == 1) {
            Log.i(TAG, "handleAudioFocusEvent - Acquired audio focus, transit out of state");
            this.hasAudioFocus.set(true);
            BaseStateHandler.StateTransitionInfo stateTransitionInfo = this.stateTransitionInfo;
            if (stateTransitionInfo != null) {
                stateTransitionInfo.transit();
                return;
            }
            Log.w(TAG, "handleAudioFocusEvent - Acquired audio focus - Unexpected state");
            transitState(StateManager.STATE_IDLE);
        } else if (i != 2 && i != 3) {
            String str2 = TAG;
            Log.w(str2, "handleAudioFocusEvent - Unhandled event - " + i);
            transitState(StateManager.STATE_IDLE, 7, 4, null);
        } else {
            this.hasAudioFocus.set(false);
            Log.i(TAG, "handleAudioFocusEvent - Lost audio focus or failed to acquire audio focus");
            if (!this.waitingForAudioFocusBeforeTransit.get()) {
                Log.i(TAG, "handleAudioFocusEvent - Expected to lose audio focus - Ignore and do not transit out");
                return;
            }
            Log.i(TAG, "handleAudioFocusEvent - Transit to idle on audio focus loss");
            transitState(StateManager.STATE_IDLE, 7, Integer.valueOf(i), null);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        super.handleGestureEvent(i, payload);
        if (i == 2) {
            Log.i(TAG, "handleGestureEvent - User canceled reply.");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_RECORDING_CANCELLED);
            cancelTranscribe();
            waitForAudioFocusBeforeTransitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, Integer.valueOf(i), null);
        } else if (i == 1) {
            Log.i(TAG, "handleGestureEvent - User indicated manual end-pointing");
            signalManualSpeechEndPointing();
        } else {
            String str = TAG;
            Log.d(str, "handleGestureEvent - Unsupported gesture - " + i);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        boolean isSameNotification = isSameNotification(jSONObject, NotificationQueue.getInstance().peek());
        super.handleNotificationEvent(i, jSONObject);
        if ((2 != i || !isSameNotification) && 3 != i) {
            return;
        }
        Log.i(TAG, "handleNotificationEvent - Remove notification that user is currently replying to");
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_CANCELLED_NOTIF_DISMISSED);
        cancelTranscribe();
        waitForAudioFocusBeforeTransitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 1, Integer.valueOf(i), null);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public synchronized void handleSpeechRecognizerEvent(int i, @Nullable Object obj) {
        super.handleSpeechRecognizerEvent(i, obj);
        switch (i) {
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
                String str = TAG;
                Log.i(str, "handleSpeechRecognizerEvent - Transcription error - " + i);
                if (i == 5) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_NO_MICROPHONE_PERMISSION);
                }
                transitWithReplyError(i);
                break;
            case 7:
                String str2 = TAG;
                Log.i(str2, "handleSpeechRecognizerEvent - Transcription error retry available - " + i);
                transitBasedOnRetryLimit(Integer.valueOf(i));
                break;
            case 11:
                Log.d(TAG, "handleSpeechRecognizerEvent - transcribe was successful");
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_TRANSCRIPTION_SUCCESS);
                this.previousRecordingWasLowConfidence = false;
                ReplyTransientStorage.getInstance().resetCounter();
                String stringFromPayload = getStringFromPayload(obj);
                if (Strings.isNullOrEmpty(stringFromPayload)) {
                    Log.e(TAG, "handleSpeechRecognizerEvent - Invalid transcribed text");
                    transitWithReplyError(8);
                    break;
                } else {
                    Log.i(TAG, "handleSpeechRecognizerEvent - Valid response from transcriber");
                    try {
                        ReplyTransientStorage.getInstance().setPayload(NotificationQueue.getInstance().peek().getString("uuid"), stringFromPayload);
                        if (this.settingsStorageModule.getReplyReadBackSettingsWithDefault().booleanValue()) {
                            Log.i(TAG, "handleSpeechRecognizerEvent - Requesting read back");
                            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_WILL_READBACK, 1.0d);
                            waitForAudioFocusBeforeTransitState(StateManager.STATE_REPLY_READ_BACK, 9, 1, null);
                        } else {
                            Log.i(TAG, "handleSpeechRecognizerEvent - No read back needed");
                            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_WILL_READBACK, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                            sendReply();
                        }
                        break;
                    } catch (Exception e) {
                        String str3 = TAG;
                        Log.e(str3, "handleSpeechRecognizerEvent - Exception: " + e);
                        transitWithReplyError(8);
                        break;
                    }
                }
            case 12:
                String str4 = TAG;
                Log.i(str4, "handleSpeechRecognizerEvent - Low confidence transcribe. Previous Reply was low confidence? " + this.previousRecordingWasLowConfidence);
                if (!this.previousRecordingWasLowConfidence) {
                    transitWithTranscribeConfidenceLow();
                    break;
                } else {
                    transitWithReplyError(8);
                    break;
                }
            default:
                String str5 = TAG;
                Log.w(str5, "handleSpeechRecognizerEvent - Unhandled eventId - " + i);
                transitWithReplyError(i);
                break;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        this.previousRecordingWasLowConfidence = false;
    }

    @VisibleForTesting
    void transitBasedOnRetryLimit(Integer num) {
        Log.d(TAG, "transitBasedOnRetryLimit");
        ReplyTransientStorage replyTransientStorage = ReplyTransientStorage.getInstance();
        if (replyTransientStorage.getRetryCount() >= 3) {
            Log.i(TAG, "transitBasedOnRetryLimit - max number of retries attempted: 3");
            replyTransientStorage.resetCounter();
            transitWithReplyError(13);
            return;
        }
        replyTransientStorage.incrementCounter();
        transitWithReplyError(num.intValue());
    }

    @VisibleForTesting
    void waitForAudioFocusAndTransitStateForNextNotification() {
        Log.d(TAG, "waitForAudioFocusAndTransitStateForNextNotification");
        this.waitingForAudioFocusBeforeTransit.set(false);
        if (this.hasAudioFocus.get()) {
            Log.i(TAG, "waitForAudioFocusAndTransitStateForNextNotification - Already has audio focus");
            transitStateForNextNotification();
            return;
        }
        Log.i(TAG, "waitForAudioFocusAndTransitStateForNextNotification - Request audio focus");
        this.waitingForAudioFocusBeforeTransit.set(true);
        this.stateTransitionInfo = new BaseStateHandler.StateTransitionInfo(this);
        AudioFocusManager.getInstance().requestAudioFocus();
    }

    @VisibleForTesting
    void waitForAudioFocusBeforeTransitState(@NonNull String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        Log.d(TAG, "waitForAudioFocusBeforeTransitState");
        this.waitingForAudioFocusBeforeTransit.set(false);
        if (this.hasAudioFocus.get()) {
            Log.i(TAG, "waitForAudioFocusBeforeTransitState - Already has audio focus");
            transitState(str, num, num2, num3);
            return;
        }
        Log.i(TAG, "waitForAudioFocusBeforeTransitState - Request audio focus");
        this.waitingForAudioFocusBeforeTransit.set(true);
        this.stateTransitionInfo = new BaseStateHandler.StateTransitionInfo(this, str, num, num2, num3);
        AudioFocusManager.getInstance().requestAudioFocus();
    }

    /* renamed from: clone */
    public ReplyStateHandler m349clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
