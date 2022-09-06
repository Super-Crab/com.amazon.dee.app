package com.amazon.alexa.devices.presentation.internal;

import android.os.RemoteException;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.presentation.ICallStatusCallback;
import com.amazon.alexa.devices.presentation.ILocalSkillEndpoint;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class LocalSkillEndpointComponent extends AndroidAlexa.BaseProxyClient implements ILocalSkillEndpoint {
    private com.amazon.alexa.devices.sdk.presentation.ILocalSkillEndpoint mLocaLSkillEndpointComponent;

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mLocaLSkillEndpointComponent = (com.amazon.alexa.devices.sdk.presentation.ILocalSkillEndpoint) getRemoteComponent(iAlexaApiGateway, com.amazon.alexa.devices.sdk.presentation.ILocalSkillEndpoint.class, version);
        if (this.mLocaLSkillEndpointComponent != null) {
            return;
        }
        throw new AlexaException("Device does not support Local Skill Endpoint component.");
    }

    @Override // com.amazon.alexa.devices.presentation.ILocalSkillEndpoint
    public void sendAskResponse(String str, String str2, ICallStatusCallback iCallStatusCallback) throws AlexaException {
        try {
            this.mLocaLSkillEndpointComponent.sendAskResponse(str, str2, CallbackHelper.convertToAidlCallStatusCallback(iCallStatusCallback));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
