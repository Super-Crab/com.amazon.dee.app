package com.amazon.alexa.accessory.notificationpublisher.timers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class TimerManager extends BaseComponent {
    public static final int ACTIVE_EVENT_TIMER_CANCEL = 2;
    public static final int ACTIVE_EVENT_TIMER_START = 1;
    public static final int CALLBACK_EVENT_TIMER_EXPIRED = 3;
    public static final int EVENT_TIMER_ACTION_DELAY = 1;
    public static final int EVENT_TIMER_ACTIVE_WINDOW = 2;
    public static final int EVENT_TIMER_EXTEND_ACTION_DELAY_DEFAULT_DURATION = 99;
    public static final int EVENT_TIMER_INVITATION_ACTION_DELAY = 4;
    public static final int EVENT_TIMER_READ_BACK_ACT_DELAY = 5;
    public static final int EVENT_TIMER_READ_BACK_ERROR = 6;
    public static final int EVENT_TIMER_REPLAY = 3;
    private static final String TAG = "TimerManager";
    public static final int TIMER_TYPE_ACTION_DELAY = 1;
    public static final int TIMER_TYPE_ACTIVE_WINDOW = 2;
    public static final int TIMER_TYPE_INVITATION_ACTION_DELAY = 4;
    public static final int TIMER_TYPE_READ_BACK_ACT_DELAY = 5;
    public static final int TIMER_TYPE_READ_BACK_ERROR = 6;
    public static final int TIMER_TYPE_REPLAY = 3;
    private static TimerManager timerManagerInstance;
    private BaseTimer actionDelayTimer;
    private BaseTimer activeWindowTimer;
    private BaseTimer invitationActionDelayTimer;
    private BaseTimer readBackActDelayTimer;
    private BaseTimer readBackErrorTimer;
    private BaseTimer replayTimer;

    private TimerManager() {
        super(4);
        this.actionDelayTimer = new ActionDelayTimer();
        this.invitationActionDelayTimer = new InvitationActionDelayTimer();
        this.activeWindowTimer = new ActiveWindowTimer();
        this.replayTimer = new ReplayTimer();
        this.readBackActDelayTimer = new ReadBackActDelayTimer();
        this.readBackErrorTimer = new ReadBackErrorTimer();
    }

    public static synchronized TimerManager getInstance() {
        TimerManager timerManager;
        synchronized (TimerManager.class) {
            if (timerManagerInstance == null) {
                timerManagerInstance = new TimerManager();
            }
            timerManager = timerManagerInstance;
        }
        return timerManager;
    }

    private void handleTimerEvent(@NonNull BaseTimer baseTimer, @NonNull Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) JSONObject.class.cast(obj);
            if (jSONObject == null) {
                return;
            }
            int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
            NotificationSource notificationSource = (NotificationSource) NotificationSource.class.cast(jSONObject.opt("notificationSrc"));
            if (optInt == 1) {
                baseTimer.restart(notificationSource, createTimerExpiredCallback());
            } else if (optInt != 2) {
                String str = TAG;
                Log.w(str, "handleTimerEvent - Invalid eventType: " + optInt);
            } else {
                baseTimer.cancel();
            }
        } catch (Exception e) {
            Log.e(TAG, "handleTimerEvent - Exception.", e);
        }
    }

    public static synchronized void releaseInstance() {
        synchronized (TimerManager.class) {
            if (timerManagerInstance != null) {
                timerManagerInstance.replayTimer = null;
                timerManagerInstance.actionDelayTimer = null;
                timerManagerInstance.invitationActionDelayTimer = null;
                timerManagerInstance.activeWindowTimer = null;
                timerManagerInstance.readBackActDelayTimer = null;
                timerManagerInstance.readBackErrorTimer = null;
            }
            timerManagerInstance = null;
        }
    }

    public TimerExpiredCallback createTimerExpiredCallback() {
        return new TimerExpiredCallback() { // from class: com.amazon.alexa.accessory.notificationpublisher.timers.TimerManager.1
            @Override // com.amazon.alexa.accessory.notificationpublisher.timers.TimerExpiredCallback
            public void onTimerExpired(int i, @Nullable NotificationSource notificationSource) {
                try {
                    TimerManager.this.postEventMessage(i, new JSONObject().put("eventType", 3).put("notificationSrc", notificationSource));
                } catch (JSONException e) {
                    Log.e(TimerManager.TAG, "onTimerExpired - JSONException.", e);
                }
            }
        };
    }

    public BaseTimer getTimer(int i) {
        switch (i) {
            case 1:
                return this.actionDelayTimer;
            case 2:
                return this.activeWindowTimer;
            case 3:
                return this.replayTimer;
            case 4:
                return this.invitationActionDelayTimer;
            case 5:
                return this.readBackActDelayTimer;
            case 6:
                return this.readBackErrorTimer;
            default:
                return null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent
    public void handleEventMessage(int i, @Nullable Object obj) {
        String str = TAG;
        Log.i(str, "handleEventMessage - eventId: " + i);
        String str2 = TAG;
        Log.d(str2, "handleEventMessage - payload: " + obj);
        if (i != 99) {
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    if (obj == null) {
                        GeneratedOutlineSupport1.outline151("handleEventMessage - missing payload, discard. eventId: ", i, TAG);
                        return;
                    }
                    BaseTimer timer = getTimer(i);
                    if (timer == null) {
                        return;
                    }
                    handleTimerEvent(timer, obj);
                    return;
                default:
                    GeneratedOutlineSupport1.outline151("Invalid event ID: ", i, TAG);
                    return;
            }
        }
        this.actionDelayTimer.updateDefaultTimeDuration();
    }

    /* renamed from: clone */
    public TimerManager m361clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
