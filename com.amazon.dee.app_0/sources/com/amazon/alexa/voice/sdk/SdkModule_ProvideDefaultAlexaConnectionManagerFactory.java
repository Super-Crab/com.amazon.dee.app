package com.amazon.alexa.voice.sdk;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.voice.enablement.VoiceEnablement;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SdkModule_ProvideDefaultAlexaConnectionManagerFactory implements Factory<DefaultAlexaConnectionManager> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<ApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<VoiceEnablement> voiceEnablementProvider;

    public SdkModule_ProvideDefaultAlexaConnectionManagerFactory(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<ApplicationLifecycleService> provider3, Provider<VoiceEnablement> provider4) {
        this.contextProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
        this.applicationLifecycleServiceProvider = provider3;
        this.voiceEnablementProvider = provider4;
    }

    public static SdkModule_ProvideDefaultAlexaConnectionManagerFactory create(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<ApplicationLifecycleService> provider3, Provider<VoiceEnablement> provider4) {
        return new SdkModule_ProvideDefaultAlexaConnectionManagerFactory(provider, provider2, provider3, provider4);
    }

    public static DefaultAlexaConnectionManager provideInstance(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<ApplicationLifecycleService> provider3, Provider<VoiceEnablement> provider4) {
        return proxyProvideDefaultAlexaConnectionManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static DefaultAlexaConnectionManager proxyProvideDefaultAlexaConnectionManager(Context context, AlexaServicesConnection alexaServicesConnection, ApplicationLifecycleService applicationLifecycleService, VoiceEnablement voiceEnablement) {
        return (DefaultAlexaConnectionManager) Preconditions.checkNotNull(SdkModule.provideDefaultAlexaConnectionManager(context, alexaServicesConnection, applicationLifecycleService, voiceEnablement), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultAlexaConnectionManager mo10268get() {
        return provideInstance(this.contextProvider, this.alexaServicesConnectionProvider, this.applicationLifecycleServiceProvider, this.voiceEnablementProvider);
    }
}
