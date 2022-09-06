package com.amazon.alexa.devices.settings.temperature.internal;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import com.amazon.alexa.devices.sdk.IAlexaSettingsComponent;
import com.amazon.alexa.devices.sdk.ISettingsCallback;
import com.amazon.alexa.devices.settings.SettingValueNotSupportedException;
import com.amazon.alexa.devices.settings.SettingsAPIException;
import com.amazon.alexa.devices.settings.SettingsCallback;
import com.amazon.alexa.devices.settings.internal.SettingsConstants;
import com.amazon.alexa.devices.settings.temperature.TemperatureUnit;
import com.amazon.alexa.devices.settings.temperature.TemperatureUnitComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class TemperatureUnitComponentImpl extends AndroidAlexa.BaseProxyClient implements TemperatureUnitComponent {
    private static final String TAG = "TemperatureUnitComponentImpl";
    private IAlexaSettingsComponent mAlexaSettingsComponent;

    private ISettingsCallback convertToAidlCallback(@Nullable final SettingsCallback<TemperatureUnit> settingsCallback) {
        return new ISettingsCallback.Stub() { // from class: com.amazon.alexa.devices.settings.temperature.internal.TemperatureUnitComponentImpl.1
            @Override // com.amazon.alexa.devices.sdk.ISettingsCallback
            public void onResult(Bundle bundle) {
                SettingsCallback settingsCallback2 = settingsCallback;
                if (settingsCallback2 != null) {
                    if (bundle == null) {
                        settingsCallback2.onError(new SettingsAPIException("API call returned unexpected output."));
                    } else if (bundle.getSerializable("error") != null) {
                        settingsCallback.onError((AlexaException) bundle.getSerializable("error"));
                    } else {
                        String string = bundle.getString("value");
                        try {
                            settingsCallback.onSuccess(TemperatureUnit.valueOf(string));
                        } catch (IllegalArgumentException unused) {
                            settingsCallback.onError(new SettingValueNotSupportedException(GeneratedOutlineSupport1.outline72("API returned incorrect value: ", string)));
                        }
                    }
                }
            }
        };
    }

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mAlexaSettingsComponent = (IAlexaSettingsComponent) getRemoteComponent(iAlexaApiGateway, IAlexaSettingsComponent.class, version);
        if (this.mAlexaSettingsComponent != null) {
            return;
        }
        throw new AlexaException("Device does not support TemperatureUnit settings component.");
    }

    @Override // com.amazon.alexa.devices.settings.temperature.TemperatureUnitComponent
    public void fetch(@NonNull SettingsCallback<TemperatureUnit> settingsCallback) throws AlexaException {
        try {
            this.mAlexaSettingsComponent.getSettingState(SettingsConstants.KeyName.TEMPERATURE_UNIT, convertToAidlCallback(settingsCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.settings.temperature.TemperatureUnitComponent
    public void update(@NonNull TemperatureUnit temperatureUnit, @Nullable SettingsCallback<TemperatureUnit> settingsCallback) throws AlexaException {
        ISettingsCallback convertToAidlCallback = convertToAidlCallback(settingsCallback);
        Bundle bundle = new Bundle();
        bundle.putString("value", temperatureUnit.name());
        try {
            this.mAlexaSettingsComponent.setSettingsState(SettingsConstants.KeyName.TEMPERATURE_UNIT, bundle, convertToAidlCallback);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
