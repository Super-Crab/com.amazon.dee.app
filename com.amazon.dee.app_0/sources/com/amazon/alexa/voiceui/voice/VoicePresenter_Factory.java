package com.amazon.alexa.voiceui.voice;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoicePresenter_Factory implements Factory<VoicePresenter> {
    private final Provider<VoiceInteractor> voiceInteractorProvider;
    private final Provider<VoiceView> voiceViewProvider;

    public VoicePresenter_Factory(Provider<VoiceInteractor> provider, Provider<VoiceView> provider2) {
        this.voiceInteractorProvider = provider;
        this.voiceViewProvider = provider2;
    }

    public static VoicePresenter_Factory create(Provider<VoiceInteractor> provider, Provider<VoiceView> provider2) {
        return new VoicePresenter_Factory(provider, provider2);
    }

    public static VoicePresenter newVoicePresenter(VoiceInteractor voiceInteractor, VoiceView voiceView) {
        return new VoicePresenter(voiceInteractor, voiceView);
    }

    public static VoicePresenter provideInstance(Provider<VoiceInteractor> provider, Provider<VoiceView> provider2) {
        return new VoicePresenter(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoicePresenter mo10268get() {
        return provideInstance(this.voiceInteractorProvider, this.voiceViewProvider);
    }
}
