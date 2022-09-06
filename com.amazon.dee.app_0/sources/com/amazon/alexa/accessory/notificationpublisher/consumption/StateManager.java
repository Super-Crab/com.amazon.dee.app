package com.amazon.alexa.accessory.notificationpublisher.consumption;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.AnnouncementActDelayStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.AnnouncementStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentActDelayHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ContentStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.IdleStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.InstructionalAudioStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ReplyActDelayHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ReplyReadBackActDelayHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ReplyReadBackStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.ReplyStateHandler;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class StateManager {
    public static final String STATE_ANNOUNCEMENT = "STATE_ANNOUNCEMENT";
    public static final String STATE_ANNOUNCEMENT_DELAY = "STATE_ANNOUNCEMENT_DELAY";
    public static final String STATE_CONTENT = "STATE_CONTENT";
    public static final String STATE_CONTENT_DELAY = "STATE_CONTENT_DELAY";
    public static final String STATE_IDLE = "STATE_IDLE";
    public static final String STATE_INSTRUCTIONAL_AUDIO = "STATE_INSTRUCTIONAL_AUDIO";
    public static final String STATE_REPLY = "STATE_REPLY";
    public static final String STATE_REPLY_DELAY = "STATE_REPLY_DELAY";
    public static final String STATE_REPLY_READ_BACK = "STATE_REPLY_READ_BACK";
    public static final String STATE_REPLY_READ_BACK_ACT_DELAY = "STATE_REPLY_READ_BACK_ACT_DELAY";
    private static final String TAG = "StateManager";
    private static StateManager instance;
    private BaseStateHandler currentStateHandler;
    private final ImmutableMap<String, BaseStateHandler> stateMap = new ImmutableMap.Builder().mo7828put(STATE_ANNOUNCEMENT_DELAY, AnnouncementActDelayStateHandler.getInstance()).mo7828put(STATE_CONTENT, ContentStateHandler.getInstance()).mo7828put(STATE_CONTENT_DELAY, ContentActDelayHandler.getInstance()).mo7828put(STATE_IDLE, IdleStateHandler.getInstance()).mo7828put(STATE_ANNOUNCEMENT, AnnouncementStateHandler.getInstance()).mo7828put(STATE_INSTRUCTIONAL_AUDIO, InstructionalAudioStateHandler.getInstance()).mo7828put(STATE_REPLY, ReplyStateHandler.getInstance()).mo7828put(STATE_REPLY_DELAY, ReplyActDelayHandler.getInstance()).mo7828put(STATE_REPLY_READ_BACK, ReplyReadBackStateHandler.getInstance()).mo7828put(STATE_REPLY_READ_BACK_ACT_DELAY, ReplyReadBackActDelayHandler.getInstance()).mo7826build();

    private StateManager() {
        Log.d(TAG, "StateManager constructor");
        this.currentStateHandler = IdleStateHandler.getInstance();
    }

    public static synchronized StateManager getInstance() {
        StateManager stateManager;
        synchronized (StateManager.class) {
            if (instance == null) {
                instance = new StateManager();
            }
            stateManager = instance;
        }
        return stateManager;
    }

    private JSONObject getValidJSONPayload(@Nullable Object obj) {
        try {
            return (JSONObject) JSONObject.class.cast(obj);
        } catch (ClassCastException e) {
            Log.e(TAG, "getValidJSONPayload - Failed to cast notification payload to JSON.", e);
            return null;
        }
    }

    public static synchronized void releaseInstance() {
        synchronized (StateManager.class) {
            if (instance != null) {
                instance.currentStateHandler = null;
            }
            instance = null;
        }
    }

    public String getCurrentState() {
        return this.currentStateHandler.getState();
    }

    public void handleAudioFocusEvent(int i, @Nullable Object obj) {
        this.currentStateHandler.handleAudioFocusEvent(i);
    }

    public void handleGestureEvent(int i, @Nullable Object obj) {
        this.currentStateHandler.handleGestureEvent(i, (Payload) obj);
    }

    public void handleNotificationEvent(int i, @Nullable Object obj) {
        JSONObject validJSONPayload = getValidJSONPayload(obj);
        if (validJSONPayload == null) {
            return;
        }
        this.currentStateHandler.handleNotificationEvent(i, validJSONPayload);
    }

    public void handleRendererEvent(int i, @Nullable Object obj) {
        JSONObject validJSONPayload = getValidJSONPayload(obj);
        if (validJSONPayload == null) {
            return;
        }
        this.currentStateHandler.handleRendererEvent(i, validJSONPayload);
    }

    public void handleReplyReadBackEvent(int i, @Nullable Object obj) {
        this.currentStateHandler.handleReplyReadBackEvent(i, obj);
    }

    public void handleSpeechRecognizerEvent(int i, @Nullable Object obj) {
        this.currentStateHandler.handleSpeechRecognizerEvent(i, obj);
    }

    public void handleStatusEvent(int i, @Nullable Object obj) {
        this.currentStateHandler.handleStatusEvent(i);
    }

    public void handleTimerEvent(int i, @Nullable Object obj) {
        JSONObject validJSONPayload = getValidJSONPayload(obj);
        if (validJSONPayload == null) {
            return;
        }
        this.currentStateHandler.handleTimerEvent(i, validJSONPayload);
    }

    public synchronized void transitState(String str) {
        transitState(str, null, null, null);
    }

    public synchronized void transitState(@NonNull String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        Log.i(TAG, String.format(Locale.US, "transitState - toState: %s, componentId: %d, eventId: %d, eventType: %d", str, num, num2, num3));
        if (this.stateMap.containsKey(str)) {
            String state = this.currentStateHandler.getState();
            String str2 = TAG;
            Log.i(str2, "Transit from " + state + " to " + str);
            this.currentStateHandler.leaveState(str);
            this.currentStateHandler = this.stateMap.mo7740get(str);
            if (num != null && num2 != null) {
                this.currentStateHandler.enterState(state, num, num2, num3);
            } else {
                this.currentStateHandler.enterState(state, null, null, null);
            }
        } else {
            String str3 = TAG;
            Log.e(str3, "transitState - Invalid state " + str);
            transitState(STATE_IDLE);
        }
    }
}
