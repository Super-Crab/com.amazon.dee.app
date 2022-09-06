package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler;
/* loaded from: classes.dex */
public interface InputEventsHandler {
    void onInputEventTriggered(AccessorySession accessorySession, InputEvent inputEvent, InputEventHandler.Callback callback);
}
