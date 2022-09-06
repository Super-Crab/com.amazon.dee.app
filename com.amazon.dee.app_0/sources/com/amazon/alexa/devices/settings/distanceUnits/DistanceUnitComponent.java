package com.amazon.alexa.devices.settings.distanceUnits;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.settings.SettingsCallback;
/* loaded from: classes6.dex */
public interface DistanceUnitComponent {
    void fetch(@NonNull SettingsCallback<DistanceUnit> settingsCallback) throws AlexaException;

    void update(@NonNull DistanceUnit distanceUnit, @Nullable SettingsCallback<DistanceUnit> settingsCallback) throws AlexaException;
}
