package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.detection.BeaconFormatLogic;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.text.SimpleDateFormat;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideBeaconFormatLogicFactory implements Factory<BeaconFormatLogic> {
    private final PresenceModule module;
    private final Provider<SimpleDateFormat> simpleDateFormatProvider;

    public PresenceModule_ProvideBeaconFormatLogicFactory(PresenceModule presenceModule, Provider<SimpleDateFormat> provider) {
        this.module = presenceModule;
        this.simpleDateFormatProvider = provider;
    }

    public static PresenceModule_ProvideBeaconFormatLogicFactory create(PresenceModule presenceModule, Provider<SimpleDateFormat> provider) {
        return new PresenceModule_ProvideBeaconFormatLogicFactory(presenceModule, provider);
    }

    public static BeaconFormatLogic provideInstance(PresenceModule presenceModule, Provider<SimpleDateFormat> provider) {
        return proxyProvideBeaconFormatLogic(presenceModule, provider.mo10268get());
    }

    public static BeaconFormatLogic proxyProvideBeaconFormatLogic(PresenceModule presenceModule, SimpleDateFormat simpleDateFormat) {
        return (BeaconFormatLogic) Preconditions.checkNotNull(presenceModule.provideBeaconFormatLogic(simpleDateFormat), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BeaconFormatLogic mo10268get() {
        return provideInstance(this.module, this.simpleDateFormatProvider);
    }
}
