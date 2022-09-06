package com.amazon.alexa.voice.wakeword;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class WakeWordModule_ProvideCompatibilityInterfaceFactory implements Factory<AbiCompatibilityInterface> {
    private static final WakeWordModule_ProvideCompatibilityInterfaceFactory INSTANCE = new WakeWordModule_ProvideCompatibilityInterfaceFactory();

    public static WakeWordModule_ProvideCompatibilityInterfaceFactory create() {
        return INSTANCE;
    }

    public static AbiCompatibilityInterface provideInstance() {
        return proxyProvideCompatibilityInterface();
    }

    public static AbiCompatibilityInterface proxyProvideCompatibilityInterface() {
        return (AbiCompatibilityInterface) Preconditions.checkNotNull(WakeWordModule.provideCompatibilityInterface(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AbiCompatibilityInterface mo10268get() {
        return provideInstance();
    }
}
