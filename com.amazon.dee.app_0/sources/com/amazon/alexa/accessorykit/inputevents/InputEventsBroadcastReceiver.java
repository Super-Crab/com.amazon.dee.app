package com.amazon.alexa.accessorykit.inputevents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEvent;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsUtil;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessorykit.AlexaDeviceManufacturerSupplier;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes6.dex */
public final class InputEventsBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "InputEventsBroadcastReceiver:";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        char c;
        Logger.d("InputEventsBroadcastReceiver: onReceive for event " + intent);
        EventBus eventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).get();
        InputEvent inputEventFromIntent = InputEventsUtil.getInputEventFromIntent(intent);
        String stringExtra = intent.getStringExtra("deviceType");
        int hashCode = stringExtra.hashCode();
        if (hashCode != 203551326) {
            if (hashCode == 1486296343 && stringExtra.equals(AlexaDeviceManufacturerSupplier.HK)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (stringExtra.equals("A3IYPH06PH1HRA")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            HKInputEventsReceiver.onReceive(eventBus, inputEventFromIntent);
        } else if (c != 1) {
            Logger.d("InputEventsBroadcastReceiver: No InputEventsReceiver for deviceType: " + stringExtra);
        } else {
            ZionInputEventsReceiver.onReceive(eventBus, inputEventFromIntent);
        }
    }
}
