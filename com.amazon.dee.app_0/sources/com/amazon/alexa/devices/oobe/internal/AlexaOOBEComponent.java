package com.amazon.alexa.devices.oobe.internal;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.notification.internal.NotificationCapableComponent;
import com.amazon.alexa.devices.oobe.OOBEComponent;
import com.amazon.alexa.devices.oobe.OOBEFlowState;
import com.amazon.alexa.devices.oobe.OOBEState;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import com.amazon.alexa.devices.sdk.IOOBEComponent;
import com.amazon.alexa.devices.settings.SettingsCallback;
import com.amazon.alexa.devices.settings.internal.CallbackHelper;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class AlexaOOBEComponent extends NotificationCapableComponent implements OOBEComponent {
    private static final String TAG = "AlexaOOBEComponent";
    private IOOBEComponent mOOBEComponent;

    @Override // com.amazon.alexa.devices.notification.internal.NotificationCapableComponent, com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        super.connect(iAlexaApiGateway, version);
        this.mOOBEComponent = (IOOBEComponent) getRemoteComponent(iAlexaApiGateway, IOOBEComponent.class, version);
        if (this.mOOBEComponent != null) {
            return;
        }
        Log.e(TAG, "Device does not support OOBE component.");
        throw new AlexaException("Device does not support OOBE component.");
    }

    @Override // com.amazon.alexa.devices.oobe.OOBEComponent
    public void getOOBEFlowState(String str, SettingsCallback<OOBEFlowState> settingsCallback) throws AlexaException {
        try {
            this.mOOBEComponent.getOOBEFlowState(str, CallbackHelper.convertToAidlCallbackForOOBEFlowState(settingsCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.oobe.OOBEComponent
    public void getOOBEFlowsState(SettingsCallback<OOBEFlowState[]> settingsCallback) throws AlexaException {
        try {
            this.mOOBEComponent.getOOBEFlowsState(CallbackHelper.convertToAidlCallbackForOOBEFlowStates(settingsCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.oobe.OOBEComponent
    public void getOOBEState(SettingsCallback<OOBEState> settingsCallback) throws AlexaException {
        try {
            this.mOOBEComponent.getOOBEState(CallbackHelper.convertToAidlCallbackForOOBEState(settingsCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.oobe.OOBEComponent
    public void launchAlexaOOBE(String str, SettingsCallback settingsCallback) throws AlexaException {
        try {
            this.mOOBEComponent.launchAlexaOOBE(str, settingsCallback != null ? CallbackHelper.convertToAidlCallbackForBoolean(settingsCallback) : null);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.oobe.OOBEComponent
    public void resetOOBE(SettingsCallback<Boolean> settingsCallback) throws AlexaException {
        try {
            this.mOOBEComponent.resetOOBE(settingsCallback != null ? CallbackHelper.convertToAidlCallbackForBoolean(settingsCallback) : null);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.oobe.OOBEComponent
    public void getOOBEFlowState(SettingsCallback<OOBEFlowState> settingsCallback) throws AlexaException {
        getOOBEFlowState(OOBEComponent.DEFAULT_FLOW, settingsCallback);
    }

    @Override // com.amazon.alexa.devices.oobe.OOBEComponent
    public void launchAlexaOOBE(SettingsCallback settingsCallback) throws AlexaException {
        launchAlexaOOBE(OOBEComponent.DEFAULT_FLOW, settingsCallback);
    }
}
