package com.amazon.alexa.devices.settings.location.internal;

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
import com.amazon.alexa.devices.settings.location.DeviceLocation;
import com.amazon.alexa.devices.settings.location.DeviceLocationComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class DeviceLocationComponentImpl extends AndroidAlexa.BaseProxyClient implements DeviceLocationComponent {
    private static final String TAG = "DeviceLocationComponentImpl";
    private IAlexaSettingsComponent mAlexaSettingsComponent;
    private final Gson mGson = new Gson();

    private ISettingsCallback convertToAidlCallback(@Nullable final SettingsCallback<DeviceLocation> settingsCallback) {
        return new ISettingsCallback.Stub() { // from class: com.amazon.alexa.devices.settings.location.internal.DeviceLocationComponentImpl.1
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
                            settingsCallback.onSuccess(DeviceLocationComponentImpl.this.deserialize(string));
                        } catch (JsonSyntaxException unused) {
                            settingsCallback.onError(new SettingValueNotSupportedException(GeneratedOutlineSupport1.outline72("API returned incorrect value: ", string)));
                        }
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceLocation deserialize(String str) throws JsonSyntaxException {
        return (DeviceLocation) this.mGson.fromJson(str, new TypeToken<DeviceLocation>() { // from class: com.amazon.alexa.devices.settings.location.internal.DeviceLocationComponentImpl.3
        }.getType());
    }

    private String serialize(DeviceLocation deviceLocation) {
        return this.mGson.toJson(deviceLocation, new TypeToken<DeviceLocation>() { // from class: com.amazon.alexa.devices.settings.location.internal.DeviceLocationComponentImpl.2
        }.getType());
    }

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mAlexaSettingsComponent = (IAlexaSettingsComponent) getRemoteComponent(iAlexaApiGateway, IAlexaSettingsComponent.class, version);
        if (this.mAlexaSettingsComponent != null) {
            return;
        }
        throw new AlexaException("Device does not support DeviceLocation settings component.");
    }

    @Override // com.amazon.alexa.devices.settings.location.DeviceLocationComponent
    public void fetch(@NonNull SettingsCallback<DeviceLocation> settingsCallback) throws AlexaException {
        try {
            this.mAlexaSettingsComponent.getSettingState(SettingsConstants.KeyName.DEVICE_LOCATION, convertToAidlCallback(settingsCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.settings.location.DeviceLocationComponent
    public void update(@NonNull DeviceLocation deviceLocation, @Nullable SettingsCallback<DeviceLocation> settingsCallback) throws AlexaException {
        ISettingsCallback convertToAidlCallback = convertToAidlCallback(settingsCallback);
        Bundle bundle = new Bundle();
        bundle.putString("value", serialize(deviceLocation));
        try {
            this.mAlexaSettingsComponent.setSettingsState(SettingsConstants.KeyName.DEVICE_LOCATION, bundle, convertToAidlCallback);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
