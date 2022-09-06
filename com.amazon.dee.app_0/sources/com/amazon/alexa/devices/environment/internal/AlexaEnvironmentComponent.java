package com.amazon.alexa.devices.environment.internal;

import android.os.RemoteException;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.environment.IAlexaEnvironmentComponent;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import com.amazon.alexa.devices.sdk.ISettingsComponent;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class AlexaEnvironmentComponent extends AndroidAlexa.BaseProxyClient implements IAlexaEnvironmentComponent {
    private ISettingsComponent mSettingsComponent;

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mSettingsComponent = (ISettingsComponent) getRemoteComponent(iAlexaApiGateway, ISettingsComponent.class, version);
        if (this.mSettingsComponent != null) {
            return;
        }
        throw new RemoteException("Unable to acquire SettingsComponent");
    }

    @Override // com.amazon.alexa.devices.environment.IAlexaEnvironmentComponent
    public boolean isAccountRegistered() throws AlexaException {
        try {
            return this.mSettingsComponent.isAccountRegistered();
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.environment.IAlexaEnvironmentComponent
    public void launchAccountRegistration() throws AlexaException {
        try {
            this.mSettingsComponent.launchAccountRegistration();
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
