package com.amazon.alexa.voiceui;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesContextFactory implements Factory<Context> {
    private final VoiceModule module;

    public VoiceModule_ProvidesContextFactory(VoiceModule voiceModule) {
        this.module = voiceModule;
    }

    public static VoiceModule_ProvidesContextFactory create(VoiceModule voiceModule) {
        return new VoiceModule_ProvidesContextFactory(voiceModule);
    }

    public static Context provideInstance(VoiceModule voiceModule) {
        return proxyProvidesContext(voiceModule);
    }

    public static Context proxyProvidesContext(VoiceModule voiceModule) {
        return (Context) Preconditions.checkNotNull(voiceModule.providesContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
