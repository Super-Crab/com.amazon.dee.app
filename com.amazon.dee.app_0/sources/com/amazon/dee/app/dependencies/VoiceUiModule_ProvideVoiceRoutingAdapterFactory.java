package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceUiModule_ProvideVoiceRoutingAdapterFactory implements Factory<VoiceRoutingAdapter> {
    private final Provider<Activity> activityProvider;
    private final VoiceUiModule module;

    public VoiceUiModule_ProvideVoiceRoutingAdapterFactory(VoiceUiModule voiceUiModule, Provider<Activity> provider) {
        this.module = voiceUiModule;
        this.activityProvider = provider;
    }

    public static VoiceUiModule_ProvideVoiceRoutingAdapterFactory create(VoiceUiModule voiceUiModule, Provider<Activity> provider) {
        return new VoiceUiModule_ProvideVoiceRoutingAdapterFactory(voiceUiModule, provider);
    }

    public static VoiceRoutingAdapter provideInstance(VoiceUiModule voiceUiModule, Provider<Activity> provider) {
        return proxyProvideVoiceRoutingAdapter(voiceUiModule, provider.mo10268get());
    }

    public static VoiceRoutingAdapter proxyProvideVoiceRoutingAdapter(VoiceUiModule voiceUiModule, Activity activity) {
        return (VoiceRoutingAdapter) Preconditions.checkNotNull(voiceUiModule.provideVoiceRoutingAdapter(activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
