package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.protocol.Input;
/* loaded from: classes.dex */
public interface InputEvent {
    int getDeviceId();

    Input.InputAction getInputAction();

    Input.InputBehavior getInputBehavior();

    Input.InputSource getInputSource();
}
