package com.amazon.alexa.accessorykit.inputevents;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEvent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class HKInputEventsReceiver {
    private static final String EVENT_BUS_CALL_VIP_CONTACT = "accessory::call_vip_contact";
    private static final int EVENT_TTL_MS = 1000;
    private static final String TAG = "HKInputEventsReceiver:";
    private static List<Input.InputSource> supportedInputSource = new ArrayList();
    private static List<Input.InputAction> supportedInputAction = new ArrayList();

    static {
        supportedInputSource.add(Input.InputSource.INPUT_SOURCE_ACTION);
        supportedInputAction.add(Input.InputAction.INPUT_ACTION_DOUBLE_TAP);
    }

    private HKInputEventsReceiver() {
    }

    public static List<Input.InputAction> getSupportedInputAction() {
        return supportedInputAction;
    }

    public static List<Input.InputSource> getSupportedInputSource() {
        return supportedInputSource;
    }

    @SuppressLint({"MissingPermission"})
    private static void handleDoubleTap(EventBus eventBus) {
        try {
            eventBus.publish(new Message.Builder().setSource(Message.Source.Local).setEventType(EVENT_BUS_CALL_VIP_CONTACT).setTTL(1000L).build());
        } catch (Exception e) {
            Logger.e("HKInputEventsReceiver: Failed to submit the VIP calling Message to the EventBus", e);
        }
    }

    public static void onReceive(EventBus eventBus, InputEvent inputEvent) {
        if (Input.InputAction.INPUT_ACTION_DOUBLE_TAP == inputEvent.getInputAction()) {
            Logger.d("HKInputEventsReceiver: Received a Double Tap InputEvent");
            handleDoubleTap(eventBus);
        }
    }
}
