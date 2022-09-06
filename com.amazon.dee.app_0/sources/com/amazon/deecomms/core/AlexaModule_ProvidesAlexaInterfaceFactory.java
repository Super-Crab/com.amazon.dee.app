package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.alexa.AlexaInterface;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaModule_ProvidesAlexaInterfaceFactory implements Factory<AlexaInterface> {
    private final Provider<Context> contextProvider;
    private final AlexaModule module;

    public AlexaModule_ProvidesAlexaInterfaceFactory(AlexaModule alexaModule, Provider<Context> provider) {
        this.module = alexaModule;
        this.contextProvider = provider;
    }

    public static AlexaModule_ProvidesAlexaInterfaceFactory create(AlexaModule alexaModule, Provider<Context> provider) {
        return new AlexaModule_ProvidesAlexaInterfaceFactory(alexaModule, provider);
    }

    public static AlexaInterface provideInstance(AlexaModule alexaModule, Provider<Context> provider) {
        return (AlexaInterface) Preconditions.checkNotNull(alexaModule.providesAlexaInterface(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AlexaInterface proxyProvidesAlexaInterface(AlexaModule alexaModule, Context context) {
        return (AlexaInterface) Preconditions.checkNotNull(alexaModule.providesAlexaInterface(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaInterface mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
