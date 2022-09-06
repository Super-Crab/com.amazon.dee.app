package com.amazon.alexa.accessory.capabilities.inputevents;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes.dex */
public final class InputEventsUtil {
    private static final int DEFAULT = -1;
    @VisibleForTesting
    static final String KEY_INPUT_ACTION = "action";
    @VisibleForTesting
    static final String KEY_INPUT_BEHAVIOR = "behavior";
    @VisibleForTesting
    static final String KEY_INPUT_DEVICE_ID = "deviceId";
    @VisibleForTesting
    static final String KEY_INPUT_SOURCE = "source";

    private InputEventsUtil() {
    }

    public static Bundle getInputEventBundle(InputEvent inputEvent) {
        Preconditions.notNull(inputEvent, "inputEvent");
        Bundle bundle = new Bundle();
        bundle.putInt("action", inputEvent.getInputAction().getNumber());
        bundle.putInt("source", inputEvent.getInputSource().getNumber());
        bundle.putInt("behavior", inputEvent.getInputBehavior().getNumber());
        bundle.putInt("deviceId", inputEvent.getDeviceId());
        return bundle;
    }

    public static InputEvent getInputEventFromIntent(Intent intent) {
        Preconditions.notNull(intent, MAPAccountManager.KEY_INTENT);
        return new SimpleInputEvent(Input.InputAction.forNumber(intent.getIntExtra("action", -1)), Input.InputSource.forNumber(intent.getIntExtra("source", -1)), Input.InputBehavior.forNumber(intent.getIntExtra("behavior", -1)), intent.getIntExtra("deviceId", -1));
    }
}
