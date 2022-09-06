package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ConnectionModule_ProvideAlexaAppSignInContractFactory implements Factory<AlexaAppSignInContract> {
    private final ConnectionModule module;

    public ConnectionModule_ProvideAlexaAppSignInContractFactory(ConnectionModule connectionModule) {
        this.module = connectionModule;
    }

    public static ConnectionModule_ProvideAlexaAppSignInContractFactory create(ConnectionModule connectionModule) {
        return new ConnectionModule_ProvideAlexaAppSignInContractFactory(connectionModule);
    }

    public static AlexaAppSignInContract provideInstance(ConnectionModule connectionModule) {
        return proxyProvideAlexaAppSignInContract(connectionModule);
    }

    public static AlexaAppSignInContract proxyProvideAlexaAppSignInContract(ConnectionModule connectionModule) {
        return (AlexaAppSignInContract) Preconditions.checkNotNull(connectionModule.provideAlexaAppSignInContract(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaAppSignInContract mo10268get() {
        return provideInstance(this.module);
    }
}
