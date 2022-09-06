package com.amazon.alexa.voiceui;

import android.app.Activity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesActivityFactory implements Factory<Activity> {
    private final VoiceModule module;

    public VoiceModule_ProvidesActivityFactory(VoiceModule voiceModule) {
        this.module = voiceModule;
    }

    public static VoiceModule_ProvidesActivityFactory create(VoiceModule voiceModule) {
        return new VoiceModule_ProvidesActivityFactory(voiceModule);
    }

    public static Activity provideInstance(VoiceModule voiceModule) {
        return proxyProvidesActivity(voiceModule);
    }

    public static Activity proxyProvidesActivity(VoiceModule voiceModule) {
        return (Activity) Preconditions.checkNotNull(voiceModule.providesActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Activity mo10268get() {
        return provideInstance(this.module);
    }
}
