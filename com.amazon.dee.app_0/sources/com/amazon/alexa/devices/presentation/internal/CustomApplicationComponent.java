package com.amazon.alexa.devices.presentation.internal;

import android.os.RemoteException;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.SkillEndpointContext;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.presentation.ICallStatusCallback;
import com.amazon.alexa.devices.presentation.ICustomApplication;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class CustomApplicationComponent extends AndroidAlexa.BaseProxyClient implements ICustomApplication {
    private com.amazon.alexa.devices.sdk.presentation.ICustomApplication mCustomApplicationComponent;

    @Override // com.amazon.alexa.devices.presentation.ICustomApplication
    public void addOrUpdateSkillEndpointContext(String str, SkillEndpointContext skillEndpointContext, ICallStatusCallback iCallStatusCallback) throws AlexaException {
        try {
            this.mCustomApplicationComponent.addOrUpdateSkillEndpointContext(str, skillEndpointContext, CallbackHelper.convertToAidlCallStatusCallback(iCallStatusCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mCustomApplicationComponent = (com.amazon.alexa.devices.sdk.presentation.ICustomApplication) getRemoteComponent(iAlexaApiGateway, com.amazon.alexa.devices.sdk.presentation.ICustomApplication.class, version);
        if (this.mCustomApplicationComponent != null) {
            return;
        }
        throw new AlexaException("Device does not support Custom Application component.");
    }

    @Override // com.amazon.alexa.devices.presentation.ICustomApplication
    public void removeSkillEndpointContext(String str, ICallStatusCallback iCallStatusCallback) throws AlexaException {
        try {
            this.mCustomApplicationComponent.removeSkillEndpointContext(str, CallbackHelper.convertToAidlCallStatusCallback(iCallStatusCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.presentation.ICustomApplication
    public void sendAskUserEvent(String str, String str2, ICallStatusCallback iCallStatusCallback) throws AlexaException {
        try {
            this.mCustomApplicationComponent.sendAskUserEvent(str, str2, CallbackHelper.convertToAidlCallStatusCallback(iCallStatusCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
