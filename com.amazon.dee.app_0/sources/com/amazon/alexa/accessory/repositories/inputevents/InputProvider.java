package com.amazon.alexa.accessory.repositories.inputevents;

import com.amazon.alexa.accessory.protocol.Input;
/* loaded from: classes6.dex */
public interface InputProvider {
    void provideConfiguration(int i, Input.InputBehaviorConfiguration inputBehaviorConfiguration);

    void provideConfiguration(int i, Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet);
}
