package com.amazon.alexa.voiceui.voice;

import com.amazon.alexa.voiceui.cards.CardView;
import com.amazon.alexa.voiceui.chrome.VoiceChromeView;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceView_Factory implements Factory<VoiceView> {
    private final Provider<CardView> cardViewProvider;
    private final Provider<VoiceChromeView> voiceChromeViewProvider;

    public VoiceView_Factory(Provider<VoiceChromeView> provider, Provider<CardView> provider2) {
        this.voiceChromeViewProvider = provider;
        this.cardViewProvider = provider2;
    }

    public static VoiceView_Factory create(Provider<VoiceChromeView> provider, Provider<CardView> provider2) {
        return new VoiceView_Factory(provider, provider2);
    }

    public static VoiceView newVoiceView(VoiceChromeView voiceChromeView, CardView cardView) {
        return new VoiceView(voiceChromeView, cardView);
    }

    public static VoiceView provideInstance(Provider<VoiceChromeView> provider, Provider<CardView> provider2) {
        return new VoiceView(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceView mo10268get() {
        return provideInstance(this.voiceChromeViewProvider, this.cardViewProvider);
    }
}
