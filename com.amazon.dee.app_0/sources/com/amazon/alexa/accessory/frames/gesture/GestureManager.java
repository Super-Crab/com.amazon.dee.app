package com.amazon.alexa.accessory.frames.gesture;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.alexa.accessory.frames.eventbus.EventBusManager;
import com.amazon.alexa.accessory.frames.metrics.MetricsConstants;
import com.amazon.alexa.accessory.frames.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.eventbus.api.EventBus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes.dex */
public class GestureManager {
    private static final String KEY_DEVICE_TYPE = "deviceType";
    private static final String KEY_INPUT_ACTION = "action";
    private static final String KEY_INPUT_SOURCE = "source";
    private static final String TAG = "GestureManager";
    private static final String ZION_DEVICE_TYPE_ID = "A3IYPH06PH1HRA";
    private static GestureManager gestureManagerInstance;

    /* renamed from: com.amazon.alexa.accessory.frames.gesture.GestureManager$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction = new int[Input.InputAction.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_TAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_LONG_PRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static synchronized GestureManager getInstance() {
        GestureManager gestureManager;
        synchronized (GestureManager.class) {
            if (gestureManagerInstance == null) {
                gestureManagerInstance = new GestureManager();
            }
            gestureManager = gestureManagerInstance;
        }
        return gestureManager;
    }

    public static boolean isAppForeground() {
        return ProcessLifecycleOwner.get().getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    private void startResetTopContactFlowTimer() {
        new Timer().schedule(new TimerTask() { // from class: com.amazon.alexa.accessory.frames.gesture.GestureManager.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                TopContactManager.topContactFlowInprogress.set(false);
            }
        }, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onGestureEventReceived(@NonNull Intent intent) {
        Input.InputSource.forNumber(intent.getIntExtra("source", -1));
        Input.InputAction forNumber = Input.InputAction.forNumber(intent.getIntExtra("action", -1));
        String stringExtra = intent.getStringExtra("deviceType");
        EventBus eventBus = DependencyProvider.getEventBus();
        if (!Strings.isNullOrEmpty(stringExtra) && stringExtra.equalsIgnoreCase("A3IYPH06PH1HRA")) {
            int ordinal = forNumber.ordinal();
            if (ordinal == 1) {
                Log.d(TAG, "processInputEvent - tap gesture.");
                if (TopContactManager.topContactFlowInprogress.get()) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_CANCELLED);
                }
                AudioFilePlayer.getInstance().stopAllAudio();
                return;
            } else if (ordinal != 5) {
                Log.w(TAG, "processInputEvent - Unhandled Input Event action.");
                return;
            } else {
                Log.d(TAG, "processInputEvent - long press gesture.");
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onGestureEventReceived - TouchpadCustomizationState: ");
                outline107.append(TopContactManager.getInstance().getTouchpadCustomizationState());
                Log.i(str, outline107.toString());
                String str2 = TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onGestureEventReceived - TopContactFlowInprogress: ");
                outline1072.append(TopContactManager.topContactFlowInprogress.get());
                Log.i(str2, outline1072.toString());
                if (TopContactManager.getInstance().getTouchpadCustomizationState() != TopContactManager.TouchpadCustomizationState.CALLING || TopContactManager.topContactFlowInprogress.get()) {
                    return;
                }
                TopContactManager.topContactFlowInprogress.set(true);
                startResetTopContactFlowTimer();
                eventBus.publish(EventBusManager.createMessage("Zion:EventBus:HigherPriorityAudioFlowStarted"));
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_GESTURE_RECEIVED);
                if (isAppForeground()) {
                    TopContactManager.getInstance().requestContactDetails();
                    return;
                } else {
                    TopContactManager.getInstance().requestAudioFocusIfRequired();
                    return;
                }
            }
        }
        Log.w(TAG, "processInputEvent - Abort processing, device type ID is invalid");
    }
}
