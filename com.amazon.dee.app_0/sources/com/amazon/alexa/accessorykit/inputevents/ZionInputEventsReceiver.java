package com.amazon.alexa.accessorykit.inputevents;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEvent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class ZionInputEventsReceiver {
    @VisibleForTesting
    private static final String EVENT_BUS_NAMESPACE = "accessories::inputevent::zion";
    private static final int EVENT_TTL = 1000;
    @VisibleForTesting
    public static final String PAYLOAD_KEY_DEVICE_TYPE_ID = "device";
    @VisibleForTesting
    public static final String PAYLOAD_KEY_INPUT_ACTION = "action";
    @VisibleForTesting
    public static final String PAYLOAD_KEY_INPUT_SOURCE = "source";
    private static final String TAG = "ZionInputEventsReceiver:";
    private static List<Input.InputSource> supportedInputSource = new ArrayList();
    private static List<Input.InputAction> supportedInputAction = new ArrayList();

    /* renamed from: com.amazon.alexa.accessorykit.inputevents.ZionInputEventsReceiver$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction = new int[Input.InputAction.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_FORWARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_TAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static {
        supportedInputSource.add(Input.InputSource.INPUT_SOURCE_TOUCHPAD);
        supportedInputAction.add(Input.InputAction.INPUT_ACTION_SWIPE_BACK);
        supportedInputAction.add(Input.InputAction.INPUT_ACTION_SWIPE_FORWARD);
        supportedInputAction.add(Input.InputAction.INPUT_ACTION_TAP);
    }

    private ZionInputEventsReceiver() {
    }

    private static Message createMessage(InputEvent inputEvent) throws Exception {
        return new Message.Builder().setSource(Message.Source.Local).setEventType(EVENT_BUS_NAMESPACE).setPayload(generateJsonPayload(inputEvent)).setTTL(1000L).build();
    }

    private static String generateJsonPayload(InputEvent inputEvent) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("source", inputEvent.getInputSource().getNumber());
        jSONObject.put("action", inputEvent.getInputAction().getNumber());
        jSONObject.put("device", "A3IYPH06PH1HRA");
        return jSONObject.toString();
    }

    public static List<Input.InputAction> getSupportedInputAction() {
        return supportedInputAction;
    }

    public static List<Input.InputSource> getSupportedInputSource() {
        return supportedInputSource;
    }

    private static void handleSwipe(EventBus eventBus, InputEvent inputEvent) {
        try {
            eventBus.publish(createMessage(inputEvent));
        } catch (Exception e) {
            Logger.e("ZionInputEventsReceiver: - handleSwipe", e);
        }
    }

    private static void handleTap(EventBus eventBus, InputEvent inputEvent) {
        try {
            eventBus.publish(createMessage(inputEvent));
        } catch (Exception e) {
            Logger.e("ZionInputEventsReceiver: - handleTap", e);
        }
    }

    public static void onReceive(EventBus eventBus, InputEvent inputEvent) {
        int ordinal = inputEvent.getInputAction().ordinal();
        if (ordinal == 1) {
            handleTap(eventBus, inputEvent);
        } else if (ordinal != 7 && ordinal != 8) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ZionInputEventsReceiver: - onInputEventTriggered - unhandled input event ");
            outline107.append(inputEvent.getInputAction());
            outline107.append(" for device Zion.");
            Logger.d(outline107.toString());
        } else {
            handleSwipe(eventBus, inputEvent);
        }
    }
}
