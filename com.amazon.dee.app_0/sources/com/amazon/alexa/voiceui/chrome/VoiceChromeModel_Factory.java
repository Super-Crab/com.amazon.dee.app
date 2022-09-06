package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voiceui.AlexaServicesApis;
import com.amazon.alexa.voiceui.cards.DefaultLocaleAuthority;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeModel_Factory implements Factory<VoiceChromeModel> {
    private final Provider<AlexaServicesApis> alexaServicesApisProvider;
    private final Provider<DefaultLocaleAuthority> localeAuthorityProvider;

    public VoiceChromeModel_Factory(Provider<AlexaServicesApis> provider, Provider<DefaultLocaleAuthority> provider2) {
        this.alexaServicesApisProvider = provider;
        this.localeAuthorityProvider = provider2;
    }

    public static VoiceChromeModel_Factory create(Provider<AlexaServicesApis> provider, Provider<DefaultLocaleAuthority> provider2) {
        return new VoiceChromeModel_Factory(provider, provider2);
    }

    public static VoiceChromeModel newVoiceChromeModel(AlexaServicesApis alexaServicesApis, DefaultLocaleAuthority defaultLocaleAuthority) {
        return new VoiceChromeModel(alexaServicesApis, defaultLocaleAuthority);
    }

    public static VoiceChromeModel provideInstance(Provider<AlexaServicesApis> provider, Provider<DefaultLocaleAuthority> provider2) {
        return new VoiceChromeModel(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceChromeModel mo10268get() {
        return provideInstance(this.alexaServicesApisProvider, this.localeAuthorityProvider);
    }
}
