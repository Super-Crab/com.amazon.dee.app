package com.amazon.alexa.devices.settings.temperature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.settings.SettingsCallback;
/* loaded from: classes6.dex */
public interface TemperatureUnitComponent {
    void fetch(@NonNull SettingsCallback<TemperatureUnit> settingsCallback) throws AlexaException;

    void update(@NonNull TemperatureUnit temperatureUnit, @Nullable SettingsCallback<TemperatureUnit> settingsCallback) throws AlexaException;
}
