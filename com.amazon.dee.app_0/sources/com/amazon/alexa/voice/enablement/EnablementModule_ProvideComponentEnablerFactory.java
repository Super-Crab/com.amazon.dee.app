package com.amazon.alexa.voice.enablement;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class EnablementModule_ProvideComponentEnablerFactory implements Factory<ComponentEnabler> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<Context> contextProvider;

    public EnablementModule_ProvideComponentEnablerFactory(Provider<Context> provider, Provider<AlexaServicesConnection> provider2) {
        this.contextProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
    }

    public static EnablementModule_ProvideComponentEnablerFactory create(Provider<Context> provider, Provider<AlexaServicesConnection> provider2) {
        return new EnablementModule_ProvideComponentEnablerFactory(provider, provider2);
    }

    public static ComponentEnabler provideInstance(Provider<Context> provider, Provider<AlexaServicesConnection> provider2) {
        return proxyProvideComponentEnabler(provider.mo10268get(), provider2.mo10268get());
    }

    public static ComponentEnabler proxyProvideComponentEnabler(Context context, AlexaServicesConnection alexaServicesConnection) {
        return (ComponentEnabler) Preconditions.checkNotNull(EnablementModule.provideComponentEnabler(context, alexaServicesConnection), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ComponentEnabler mo10268get() {
        return provideInstance(this.contextProvider, this.alexaServicesConnectionProvider);
    }
}
