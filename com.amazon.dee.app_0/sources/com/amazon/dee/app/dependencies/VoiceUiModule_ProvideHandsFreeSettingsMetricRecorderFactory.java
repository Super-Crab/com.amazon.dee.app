package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.voice.handsfree.HandsFreeSettingsMetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceUiModule_ProvideHandsFreeSettingsMetricRecorderFactory implements Factory<HandsFreeSettingsMetricRecorder> {
    private final Provider<Context> contextProvider;
    private final VoiceUiModule module;

    public VoiceUiModule_ProvideHandsFreeSettingsMetricRecorderFactory(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        this.module = voiceUiModule;
        this.contextProvider = provider;
    }

    public static VoiceUiModule_ProvideHandsFreeSettingsMetricRecorderFactory create(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return new VoiceUiModule_ProvideHandsFreeSettingsMetricRecorderFactory(voiceUiModule, provider);
    }

    public static HandsFreeSettingsMetricRecorder provideInstance(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return proxyProvideHandsFreeSettingsMetricRecorder(voiceUiModule, provider.mo10268get());
    }

    public static HandsFreeSettingsMetricRecorder proxyProvideHandsFreeSettingsMetricRecorder(VoiceUiModule voiceUiModule, Context context) {
        return (HandsFreeSettingsMetricRecorder) Preconditions.checkNotNull(voiceUiModule.provideHandsFreeSettingsMetricRecorder(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsFreeSettingsMetricRecorder mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
