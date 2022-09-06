package com.amazon.alexa.devices.settings.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.settings.SettingsCallback;
/* loaded from: classes6.dex */
public interface DeviceLocationComponent {
    void fetch(@NonNull SettingsCallback<DeviceLocation> settingsCallback) throws AlexaException;

    void update(@NonNull DeviceLocation deviceLocation, @Nullable SettingsCallback<DeviceLocation> settingsCallback) throws AlexaException;
}
