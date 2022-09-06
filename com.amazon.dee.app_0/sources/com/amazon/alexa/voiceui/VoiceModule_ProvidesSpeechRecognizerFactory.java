package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesSpeechRecognizerFactory implements Factory<SpeechRecognizer> {
    private final VoiceModule module;
    private final Provider<DefaultSpeechRecognizer> speechRecognizerProvider;

    public VoiceModule_ProvidesSpeechRecognizerFactory(VoiceModule voiceModule, Provider<DefaultSpeechRecognizer> provider) {
        this.module = voiceModule;
        this.speechRecognizerProvider = provider;
    }

    public static VoiceModule_ProvidesSpeechRecognizerFactory create(VoiceModule voiceModule, Provider<DefaultSpeechRecognizer> provider) {
        return new VoiceModule_ProvidesSpeechRecognizerFactory(voiceModule, provider);
    }

    public static SpeechRecognizer provideInstance(VoiceModule voiceModule, Provider<DefaultSpeechRecognizer> provider) {
        return proxyProvidesSpeechRecognizer(voiceModule, provider.mo10268get());
    }

    public static SpeechRecognizer proxyProvidesSpeechRecognizer(VoiceModule voiceModule, Object obj) {
        return (SpeechRecognizer) Preconditions.checkNotNull(voiceModule.providesSpeechRecognizer((DefaultSpeechRecognizer) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SpeechRecognizer mo10268get() {
        return provideInstance(this.module, this.speechRecognizerProvider);
    }
}
