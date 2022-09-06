package com.amazon.alexa.devices.directive.internal;

import android.os.RemoteException;
import android.os.ResultReceiver;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.directive.IDirectiveComponent;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class DirectiveComponent extends AndroidAlexa.BaseProxyClient implements IDirectiveComponent {
    private com.amazon.alexa.devices.sdk.IDirectiveComponent mDirectiveComponent;

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mDirectiveComponent = (com.amazon.alexa.devices.sdk.IDirectiveComponent) getRemoteComponent(iAlexaApiGateway, com.amazon.alexa.devices.sdk.IDirectiveComponent.class, version);
        if (this.mDirectiveComponent != null) {
            return;
        }
        throw new RemoteException("Unable to acquire Directive Component");
    }

    @Override // com.amazon.alexa.devices.directive.IDirectiveComponent
    public void deregisterListener(int i) throws AlexaException {
        try {
            this.mDirectiveComponent.deregisterListener(i);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.directive.IDirectiveComponent
    public int registerListener(ResultReceiver resultReceiver) throws AlexaException {
        try {
            return this.mDirectiveComponent.registerListener(resultReceiver);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
