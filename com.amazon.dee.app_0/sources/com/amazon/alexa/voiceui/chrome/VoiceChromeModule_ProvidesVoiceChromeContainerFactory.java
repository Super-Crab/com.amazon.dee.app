package com.amazon.alexa.voiceui.chrome;

import android.view.ViewGroup;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class VoiceChromeModule_ProvidesVoiceChromeContainerFactory implements Factory<ViewGroup> {
    private final VoiceChromeModule module;

    public VoiceChromeModule_ProvidesVoiceChromeContainerFactory(VoiceChromeModule voiceChromeModule) {
        this.module = voiceChromeModule;
    }

    public static VoiceChromeModule_ProvidesVoiceChromeContainerFactory create(VoiceChromeModule voiceChromeModule) {
        return new VoiceChromeModule_ProvidesVoiceChromeContainerFactory(voiceChromeModule);
    }

    public static ViewGroup provideInstance(VoiceChromeModule voiceChromeModule) {
        return proxyProvidesVoiceChromeContainer(voiceChromeModule);
    }

    public static ViewGroup proxyProvidesVoiceChromeContainer(VoiceChromeModule voiceChromeModule) {
        return (ViewGroup) Preconditions.checkNotNull(voiceChromeModule.providesVoiceChromeContainer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewGroup mo10268get() {
        return provideInstance(this.module);
    }
}
