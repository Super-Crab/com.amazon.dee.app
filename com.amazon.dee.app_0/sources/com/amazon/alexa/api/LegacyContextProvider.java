package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes6.dex */
public class LegacyContextProvider implements AlexaContextsProvider {
    private final AlexaContextProviderProxy alexaContextProviderProxy;

    public LegacyContextProvider(AlexaContextProviderProxy alexaContextProviderProxy) {
        this.alexaContextProviderProxy = alexaContextProviderProxy;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof LegacyContextProvider) && getBinder().equals(((LegacyContextProvider) obj).getBinder()));
    }

    @Override // com.amazon.alexa.api.AlexaContextsProvider
    public Set<AlexaContext> getAlexaContexts() {
        try {
            return Collections.singleton(this.alexaContextProviderProxy.getAlexaContext());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    public IBinder getBinder() {
        return this.alexaContextProviderProxy.asBinder();
    }

    public int hashCode() {
        return Objects.hashCode(getBinder());
    }
}
