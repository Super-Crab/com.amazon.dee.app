package com.amazon.dee.app.dependencies;

import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class VoiceModule_ProvideLatencyReportingDelegateFactory implements Factory<LatencyReportingDelegate> {
    private final VoiceModule module;

    public VoiceModule_ProvideLatencyReportingDelegateFactory(VoiceModule voiceModule) {
        this.module = voiceModule;
    }

    public static VoiceModule_ProvideLatencyReportingDelegateFactory create(VoiceModule voiceModule) {
        return new VoiceModule_ProvideLatencyReportingDelegateFactory(voiceModule);
    }

    public static LatencyReportingDelegate provideInstance(VoiceModule voiceModule) {
        return proxyProvideLatencyReportingDelegate(voiceModule);
    }

    public static LatencyReportingDelegate proxyProvideLatencyReportingDelegate(VoiceModule voiceModule) {
        return (LatencyReportingDelegate) Preconditions.checkNotNull(voiceModule.provideLatencyReportingDelegate(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LatencyReportingDelegate mo10268get() {
        return provideInstance(this.module);
    }
}
