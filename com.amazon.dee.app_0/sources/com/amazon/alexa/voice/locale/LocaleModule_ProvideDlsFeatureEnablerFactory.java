package com.amazon.alexa.voice.locale;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class LocaleModule_ProvideDlsFeatureEnablerFactory implements Factory<DlsFeatureEnabler> {
    private static final LocaleModule_ProvideDlsFeatureEnablerFactory INSTANCE = new LocaleModule_ProvideDlsFeatureEnablerFactory();

    public static LocaleModule_ProvideDlsFeatureEnablerFactory create() {
        return INSTANCE;
    }

    public static DlsFeatureEnabler provideInstance() {
        return proxyProvideDlsFeatureEnabler();
    }

    public static DlsFeatureEnabler proxyProvideDlsFeatureEnabler() {
        return (DlsFeatureEnabler) Preconditions.checkNotNull(LocaleModule.provideDlsFeatureEnabler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DlsFeatureEnabler mo10268get() {
        return provideInstance();
    }
}
