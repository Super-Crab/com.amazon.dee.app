package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEvent;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler;
import com.amazon.alexa.accessory.internal.util.Logger;
/* loaded from: classes.dex */
public class UnavailableInputEventsHandler implements InputEventsHandler {
    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler
    public void onInputEventTriggered(AccessorySession accessorySession, InputEvent inputEvent, InputEventHandler.Callback callback) {
        Logger.e("UnavailableInputEventsHandler received input event for session with %s: %s", accessorySession.getConnectedAccessory(), inputEvent);
        callback.onResult(InputEventHandler.Result.UNSUPPORTED);
    }
}
