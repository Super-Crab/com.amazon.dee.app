package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyTransientStorage;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ReplyReadBackStateHandler extends BaseStateHandler {
    private static final String TAG = "ReplyReadBackStateHandler";
    private static ReplyReadBackStateHandler instance;
    private String currentRenderRequestId;

    private ReplyReadBackStateHandler() {
        super(StateManager.STATE_REPLY_READ_BACK);
        this.currentRenderRequestId = "";
    }

    public static synchronized ReplyReadBackStateHandler getInstance() {
        ReplyReadBackStateHandler replyReadBackStateHandler;
        synchronized (ReplyReadBackStateHandler.class) {
            if (instance == null) {
                instance = new ReplyReadBackStateHandler();
            }
            replyReadBackStateHandler = instance;
        }
        return replyReadBackStateHandler;
    }

    private boolean isReplyPayloadValid(String str, String str2) {
        Log.d(TAG, "isReplyPayloadValid");
        try {
            String string = NotificationQueue.getInstance().peek().getString("uuid");
            if (!Strings.isNullOrEmpty(str2) && !Strings.isNullOrEmpty(ReplyTransientStorage.getInstance().getTranscribedText()) && str2.equalsIgnoreCase(ReplyTransientStorage.getInstance().getTranscribedText())) {
                if (!Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(string) && !Strings.isNullOrEmpty(ReplyTransientStorage.getInstance().getNotificationUuid()) && str.equalsIgnoreCase(string) && str.equalsIgnoreCase(ReplyTransientStorage.getInstance().getNotificationUuid())) {
                    return true;
                }
                Log.w(TAG, "isReplyPayloadValid - Read back notification ID is invalid");
                transitToReplyStateWithError(3);
                return false;
            }
            Log.w(TAG, "isReplyPayloadValid - Read back reply text is invalid");
            return false;
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "isReplyPayloadValid - Exception: " + e);
            return false;
        }
    }

    public static synchronized void releaseInstance() {
        synchronized (ReplyReadBackStateHandler.class) {
            instance = null;
        }
    }

    private void transitToReadBackActDelayState() {
        Log.i(TAG, "transitToReadBackActDelayState");
        transitState(StateManager.STATE_REPLY_READ_BACK_ACT_DELAY, 9, 5, null);
    }

    private void transitToReplyStateForRecordingReply() {
        Log.i(TAG, "transitToReplyStateForRecordingReply");
        transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 9, 7, null);
    }

    private void transitToReplyStateForSendingReply() {
        Log.i(TAG, "transitToReplyStateForSendingReply");
        transitState(StateManager.STATE_REPLY, 9, 6, null);
    }

    private void transitToReplyStateWithError(int i) {
        String str = TAG;
        Log.i(str, "transitToReplyStateWithError - eventId = " + i);
        transitState(StateManager.STATE_REPLY, 9, Integer.valueOf(i), null);
    }

    @VisibleForTesting
    void cancelReadBackErrorTimer() {
        Log.i(TAG, "cancelReadBackErrorTimer");
        postEventMessageToTimerManager(6, 2);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(@NonNull String str, Integer num, Integer num2, Integer num3) {
        super.enterState(str, num, num2, num3);
        try {
            if (str.equalsIgnoreCase(StateManager.STATE_REPLY) && num.intValue() == 9 && num2.intValue() == 1) {
                Log.i(TAG, "enterState - Wait for read back response");
                postEventMessage(9, 1, ReplyTransientStorage.getInstance().getPayload());
                playReadBackProcessingCue();
                startReadBackErrorTimer();
            } else {
                Log.w(TAG, "enterState - Incorrect transition, transit to Idle");
                transitState(StateManager.STATE_IDLE);
            }
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "enterState - Exception, transit to idle - " + e);
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MetricsConstants.REPLY_READBACK_EXCEPTION);
            outline107.append(e.getClass().getSimpleName());
            metricsRecorder.recordCounter(outline107.toString(), MetricsRecorder.customAttributesForException(e, 512));
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
            return;
        }
        String optString = peek.optString("uuid");
        if (Strings.isNullOrEmpty(optString)) {
            Log.w(TAG, "handleGestureEvent - Error: Notification UUID is empty");
            transitState(StateManager.STATE_IDLE);
        } else if (Strings.isNullOrEmpty(this.currentRenderRequestId)) {
            Log.i(TAG, "handleGestureEvent - Gesture received when no audio was being played. Ignore");
        } else if (i == 1) {
            if (!this.currentRenderRequestId.equalsIgnoreCase(optString)) {
                return;
            }
            Log.i(TAG, "handleGestureEvent - Gesture Play - Stop read back TTS and send reply");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_SKIPPED);
            stopPlayingReadBackTts(Integer.valueOf(i));
            transitToReplyStateForSendingReply();
        } else if (i != 2) {
        } else {
            if (this.currentRenderRequestId.equalsIgnoreCase(optString)) {
                Log.i(TAG, "handleGestureEvent - Gesture Stop - Stop read back TTS and open transcriber");
                stopPlayingReadBackTts(Integer.valueOf(i));
            } else if (this.currentRenderRequestId.equalsIgnoreCase(RenderManager.REPLY_READ_BACK_PROCESSING_UUID)) {
                Log.i(TAG, "handleGestureEvent - Gesture Stop - Stop read back processing cue and open transcriber");
                stopReadBackProcessingCue();
            }
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_RETRY);
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
    public void handleRendererEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleRendererEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        String optString = jSONObject.optString("uuid");
        if (optInt == 3) {
            String str = TAG;
            Log.i(str, "handleRendererEvent - Render completed for eventID: " + i + " uuid: " + optString);
            if (Strings.isNullOrEmpty(this.currentRenderRequestId)) {
                Log.w(TAG, "handleRendererEvent - Render completed but current render request ID is empty. Error out of Reply flow as something unexpected happened.");
                transitToReplyStateWithError(3);
            } else if (optString.equalsIgnoreCase(RenderManager.REPLY_READ_BACK_PROCESSING_UUID) && this.currentRenderRequestId.equalsIgnoreCase(optString)) {
                Log.w(TAG, "handleRendererEvent - Render completed for read back processing, offeruser to retry reply");
                transitToReplyStateWithError(4);
            } else if (!optString.equalsIgnoreCase(this.currentRenderRequestId)) {
            } else {
                Log.i(TAG, "handleRendererEvent - Render completed for read back");
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_COMPLETE);
                transitToReadBackActDelayState();
            }
        } else if (optInt == 4) {
            String str2 = TAG;
            Log.d(str2, "handleRendererEvent - Render stopped for eventID: " + i + " uuid: " + optString);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDERCONTENT_STOPPED);
        } else if (optInt != 5) {
            GeneratedOutlineSupport1.outline151("handleRendererEvent - Invalid Event Type: ", optInt, TAG);
        } else {
            String str3 = TAG;
            Log.w(str3, "handleRendererEvent - Render error for eventID: " + i + " uuid: " + optString);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.RENDER_READ_BACK_ERROR);
            transitToReplyStateWithError(3);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public synchronized void handleReplyReadBackEvent(int i, @Nullable Object obj) {
        super.handleReplyReadBackEvent(i, obj);
        if (i == 2) {
            Log.i(TAG, "handleReplyReadBackEvent - Read back successful");
            stopReadBackProcessingCue();
            cancelReadBackErrorTimer();
            try {
                Map map = (Map) obj;
                String str = (String) map.get(ReplyTransientStorage.NOTIFICATION_UUID);
                if (!isReplyPayloadValid(str, (String) map.get(ReplyTransientStorage.REPLY_TEXT))) {
                    Log.w(TAG, "handleReplyReadBackEvent - Notification payload is invalid");
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_INVALID_DOWNLOAD_RESULT);
                    transitToReplyStateWithError(3);
                    return;
                }
                Log.d(TAG, "handleReplyReadBackEvent - Play read back file");
                if (!playReadBackFile(str)) {
                    Log.w(TAG, "handleReplyReadBackEvent - Error playing read back file");
                    transitToReplyStateWithError(3);
                }
            } catch (Exception e) {
                String str2 = TAG;
                Log.e(str2, "handleReplyReadBackEvent - Exception getting payload: " + e);
                transitToReplyStateWithError(3);
            }
        } else if (i != 3 && i != 4) {
            String str3 = TAG;
            Log.i(str3, "handleReplyReadBackEvent - Unhandled event: " + i);
        } else {
            String str4 = TAG;
            Log.i(str4, "handleReplyReadBackEvent - Read back error: " + i);
            transitToReplyStateWithError(i);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public synchronized void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt != 3) {
            String str = TAG;
            Log.w(str, "handleTimerEvent - Invalid Event Type " + optInt);
        } else if (i != 6) {
            String str2 = TAG;
            Log.w(str2, "handleTimerEvent - Ignored Event Id during reply state " + i);
        } else {
            Log.w(TAG, "handleTimerEvent - Read back error timer expired,  transit out with retry-able error");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_TIMEOUT_ERROR);
            transitToReplyStateWithError(4);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        cancelReadBackErrorTimer();
        stopPlayingReadBackTts(null);
        removeReplyReadBackAudioFile();
    }

    @VisibleForTesting
    boolean playReadBackFile(String str) {
        Log.i(TAG, "playReadBackFile");
        try {
            this.currentRenderRequestId = str;
            return postEventMessageToRenderManager(100, 1, this.currentRenderRequestId, new NotificationFileHelper(DependencyProvider.getContext()).getReadBackFilePath());
        } catch (Exception e) {
            Log.e(TAG, "Exception in playReadBackFile: ", e);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_EXCEPTION_PLAYBACK);
            return false;
        }
    }

    @VisibleForTesting
    void playReadBackProcessingCue() {
        boolean z;
        String str = RenderManager.REPLY_READ_BACK_PROCESSING_UUID;
        Log.i(TAG, "playReadBackProcessingCue");
        try {
            z = postEventMessageToRenderManager(100, 1, str, RenderManager.REPLY_READ_BACK_PROCESSING_FILE);
        } catch (Exception e) {
            Log.e(TAG, "Exception in playReadBackProcessingCue: ", e);
            z = false;
        }
        if (!z) {
            str = "";
        }
        this.currentRenderRequestId = str;
    }

    @VisibleForTesting
    void startReadBackErrorTimer() {
        Log.i(TAG, "startReadBackErrorTimer");
        postEventMessageToTimerManager(6, 1);
    }

    @VisibleForTesting
    boolean stopPlayingReadBackTts(@Nullable Integer num) {
        Log.i(TAG, "stopPlayingReadBackTts");
        if (Strings.isNullOrEmpty(this.currentRenderRequestId)) {
            return false;
        }
        int i = 100;
        if (num != null && num.intValue() == 1) {
            i = 101;
        } else if (num != null && num.intValue() == 2) {
            i = 102;
        }
        String str = this.currentRenderRequestId;
        this.currentRenderRequestId = "";
        return postEventMessageToRenderManager(i, 2, str);
    }

    @VisibleForTesting
    boolean stopReadBackProcessingCue() {
        Log.i(TAG, "stopReadBackProcessingCue");
        return stopPlayingReadBackTts(null);
    }

    /* renamed from: clone */
    public ReplyReadBackStateHandler m348clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
