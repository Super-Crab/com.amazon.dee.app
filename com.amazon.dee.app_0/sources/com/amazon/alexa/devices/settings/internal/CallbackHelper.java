package com.amazon.alexa.devices.settings.internal;

import android.os.Bundle;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.oobe.OOBEFlowState;
import com.amazon.alexa.devices.oobe.OOBEState;
import com.amazon.alexa.devices.sdk.ISettingsCallback;
import com.amazon.alexa.devices.settings.SettingValueNotSupportedException;
import com.amazon.alexa.devices.settings.SettingsCallback;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public final class CallbackHelper {
    public static ISettingsCallback convertToAidlCallbackForBoolean(final SettingsCallback<Boolean> settingsCallback) {
        return new ISettingsCallback.Stub() { // from class: com.amazon.alexa.devices.settings.internal.CallbackHelper.4
            @Override // com.amazon.alexa.devices.sdk.ISettingsCallback
            public void onResult(Bundle bundle) {
                if (bundle == null) {
                    SettingsCallback.this.onError(new AlexaException("API call returned unexpected output."));
                } else if (bundle.getSerializable("error") != null) {
                    SettingsCallback.this.onError((AlexaException) bundle.getSerializable("error"));
                } else {
                    try {
                        SettingsCallback.this.onSuccess(Boolean.valueOf(bundle.getBoolean("value")));
                    } catch (ClassCastException | IllegalArgumentException unused) {
                        SettingsCallback settingsCallback2 = SettingsCallback.this;
                        settingsCallback2.onError(new SettingValueNotSupportedException("API returned incorrect value: " + bundle));
                    }
                }
            }
        };
    }

    public static ISettingsCallback convertToAidlCallbackForOOBEFlowState(final SettingsCallback<OOBEFlowState> settingsCallback) {
        return new ISettingsCallback.Stub() { // from class: com.amazon.alexa.devices.settings.internal.CallbackHelper.2
            @Override // com.amazon.alexa.devices.sdk.ISettingsCallback
            public void onResult(Bundle bundle) {
                if (bundle == null) {
                    SettingsCallback.this.onError(new AlexaException("API call returned unexpected output."));
                } else if (bundle.getSerializable("error") != null) {
                    SettingsCallback.this.onError((AlexaException) bundle.getSerializable("error"));
                } else {
                    try {
                        SettingsCallback.this.onSuccess((OOBEFlowState) bundle.getSerializable("value"));
                    } catch (ClassCastException | IllegalArgumentException unused) {
                        SettingsCallback settingsCallback2 = SettingsCallback.this;
                        settingsCallback2.onError(new SettingValueNotSupportedException("API returned incorrect value: " + bundle));
                    }
                }
            }
        };
    }

    public static ISettingsCallback convertToAidlCallbackForOOBEFlowStates(final SettingsCallback<OOBEFlowState[]> settingsCallback) {
        return new ISettingsCallback.Stub() { // from class: com.amazon.alexa.devices.settings.internal.CallbackHelper.3
            @Override // com.amazon.alexa.devices.sdk.ISettingsCallback
            public void onResult(Bundle bundle) {
                if (bundle == null) {
                    SettingsCallback.this.onError(new AlexaException("API call returned unexpected output."));
                } else if (bundle.getSerializable("error") != null) {
                    SettingsCallback.this.onError((AlexaException) bundle.getSerializable("error"));
                } else {
                    try {
                        SettingsCallback.this.onSuccess((OOBEFlowState[]) bundle.getSerializable("value"));
                    } catch (ClassCastException unused) {
                        SettingsCallback settingsCallback2 = SettingsCallback.this;
                        settingsCallback2.onError(new SettingValueNotSupportedException("API returned incorrect value: " + bundle));
                    }
                }
            }
        };
    }

    public static ISettingsCallback convertToAidlCallbackForOOBEState(final SettingsCallback<OOBEState> settingsCallback) {
        return new ISettingsCallback.Stub() { // from class: com.amazon.alexa.devices.settings.internal.CallbackHelper.1
            @Override // com.amazon.alexa.devices.sdk.ISettingsCallback
            public void onResult(Bundle bundle) {
                if (bundle == null) {
                    SettingsCallback.this.onError(new AlexaException("API call returned unexpected output."));
                } else if (bundle.getSerializable("error") != null) {
                    SettingsCallback.this.onError((AlexaException) bundle.getSerializable("error"));
                } else {
                    String string = bundle.getString("value");
                    try {
                        SettingsCallback.this.onSuccess(OOBEState.valueOf(string));
                    } catch (ClassCastException | IllegalArgumentException unused) {
                        SettingsCallback.this.onError(new SettingValueNotSupportedException(GeneratedOutlineSupport1.outline72("API returned incorrect value: ", string)));
                    }
                }
            }
        };
    }
}
