package com.amazon.alexa.devices.donotdisturb;

import androidx.annotation.NonNull;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.settings.SettingsCallback;
/* loaded from: classes6.dex */
public interface DoNotDisturbSettingsComponent {
    void getAlexaDNDState(@NonNull SettingsCallback<AlexaDNDState> settingsCallback) throws AlexaException;

    void setAlexaDNDState(@NonNull AlexaDNDState alexaDNDState, @NonNull SettingsCallback<AlexaDNDState> settingsCallback) throws AlexaException;
}
