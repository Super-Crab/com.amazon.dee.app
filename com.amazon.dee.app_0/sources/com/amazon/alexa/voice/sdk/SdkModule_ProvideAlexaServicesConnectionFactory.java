package com.amazon.alexa.voice.sdk;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SdkModule_ProvideAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;

    public SdkModule_ProvideAlexaServicesConnectionFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static SdkModule_ProvideAlexaServicesConnectionFactory create(Provider<Context> provider) {
        return new SdkModule_ProvideAlexaServicesConnectionFactory(provider);
    }

    public static AlexaServicesConnection provideInstance(Provider<Context> provider) {
        return proxyProvideAlexaServicesConnection(provider.mo10268get());
    }

    public static AlexaServicesConnection proxyProvideAlexaServicesConnection(Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(SdkModule.provideAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
