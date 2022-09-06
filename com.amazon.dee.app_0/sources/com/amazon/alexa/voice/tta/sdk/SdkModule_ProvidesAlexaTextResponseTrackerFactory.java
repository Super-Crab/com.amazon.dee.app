package com.amazon.alexa.voice.tta.sdk;

import com.amazon.alexa.voice.tta.typing.TtaMessageSanitizer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SdkModule_ProvidesAlexaTextResponseTrackerFactory implements Factory<AlexaTextResponseTracker> {
    private final Provider<TtaMessageSanitizer> messageSanitizerProvider;
    private final SdkModule module;

    public SdkModule_ProvidesAlexaTextResponseTrackerFactory(SdkModule sdkModule, Provider<TtaMessageSanitizer> provider) {
        this.module = sdkModule;
        this.messageSanitizerProvider = provider;
    }

    public static SdkModule_ProvidesAlexaTextResponseTrackerFactory create(SdkModule sdkModule, Provider<TtaMessageSanitizer> provider) {
        return new SdkModule_ProvidesAlexaTextResponseTrackerFactory(sdkModule, provider);
    }

    public static AlexaTextResponseTracker provideInstance(SdkModule sdkModule, Provider<TtaMessageSanitizer> provider) {
        return proxyProvidesAlexaTextResponseTracker(sdkModule, provider.mo10268get());
    }

    public static AlexaTextResponseTracker proxyProvidesAlexaTextResponseTracker(SdkModule sdkModule, TtaMessageSanitizer ttaMessageSanitizer) {
        return (AlexaTextResponseTracker) Preconditions.checkNotNull(sdkModule.providesAlexaTextResponseTracker(ttaMessageSanitizer), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaTextResponseTracker mo10268get() {
        return provideInstance(this.module, this.messageSanitizerProvider);
    }
}
