package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class VoiceChromeModule_ProvidesTypingPrimerDisplayerFactory implements Factory<TypingPrimerDisplayer> {
    private final VoiceChromeModule module;

    public VoiceChromeModule_ProvidesTypingPrimerDisplayerFactory(VoiceChromeModule voiceChromeModule) {
        this.module = voiceChromeModule;
    }

    public static VoiceChromeModule_ProvidesTypingPrimerDisplayerFactory create(VoiceChromeModule voiceChromeModule) {
        return new VoiceChromeModule_ProvidesTypingPrimerDisplayerFactory(voiceChromeModule);
    }

    public static TypingPrimerDisplayer provideInstance(VoiceChromeModule voiceChromeModule) {
        return proxyProvidesTypingPrimerDisplayer(voiceChromeModule);
    }

    public static TypingPrimerDisplayer proxyProvidesTypingPrimerDisplayer(VoiceChromeModule voiceChromeModule) {
        return (TypingPrimerDisplayer) Preconditions.checkNotNull(voiceChromeModule.providesTypingPrimerDisplayer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TypingPrimerDisplayer mo10268get() {
        return provideInstance(this.module);
    }
}
