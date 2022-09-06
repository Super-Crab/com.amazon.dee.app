package com.amazon.alexa.voiceui.chrome;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromePresenter_Factory implements Factory<VoiceChromePresenter> {
    private final Provider<VoiceChromeInteractor> voiceChromeInteractorProvider;
    private final Provider<VoiceChromeView> voiceChromeViewProvider;

    public VoiceChromePresenter_Factory(Provider<VoiceChromeInteractor> provider, Provider<VoiceChromeView> provider2) {
        this.voiceChromeInteractorProvider = provider;
        this.voiceChromeViewProvider = provider2;
    }

    public static VoiceChromePresenter_Factory create(Provider<VoiceChromeInteractor> provider, Provider<VoiceChromeView> provider2) {
        return new VoiceChromePresenter_Factory(provider, provider2);
    }

    public static VoiceChromePresenter newVoiceChromePresenter(VoiceChromeInteractor voiceChromeInteractor, VoiceChromeView voiceChromeView) {
        return new VoiceChromePresenter(voiceChromeInteractor, voiceChromeView);
    }

    public static VoiceChromePresenter provideInstance(Provider<VoiceChromeInteractor> provider, Provider<VoiceChromeView> provider2) {
        return new VoiceChromePresenter(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceChromePresenter mo10268get() {
        return provideInstance(this.voiceChromeInteractorProvider, this.voiceChromeViewProvider);
    }
}
