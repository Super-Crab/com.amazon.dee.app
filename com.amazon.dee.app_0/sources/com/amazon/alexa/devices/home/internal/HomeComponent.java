package com.amazon.alexa.devices.home.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.home.IHomeComponent;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class HomeComponent extends AndroidAlexa.BaseProxyClient implements IHomeComponent {
    private com.amazon.alexa.devices.sdk.IHomeComponent mHomeComponent;

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mHomeComponent = (com.amazon.alexa.devices.sdk.IHomeComponent) getRemoteComponent(iAlexaApiGateway, com.amazon.alexa.devices.sdk.IHomeComponent.class, version);
        if (this.mHomeComponent != null) {
            return;
        }
        throw new RemoteException("Unable to acquire Home Compoennt");
    }

    @Override // com.amazon.alexa.devices.home.IHomeComponent
    public Intent getHomeActivityIntent() throws AlexaException {
        try {
            return this.mHomeComponent.getHomeActivityIntent();
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.home.IHomeComponent
    public Intent getSettingsActivityIntent() throws AlexaException {
        try {
            return this.mHomeComponent.getSettingsActivityIntent();
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
