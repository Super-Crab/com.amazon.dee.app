package com.amazon.alexa.presence.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.text.SimpleDateFormat;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideSimpleDateFormatFactory implements Factory<SimpleDateFormat> {
    private final PresenceModule module;

    public PresenceModule_ProvideSimpleDateFormatFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvideSimpleDateFormatFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvideSimpleDateFormatFactory(presenceModule);
    }

    public static SimpleDateFormat provideInstance(PresenceModule presenceModule) {
        return proxyProvideSimpleDateFormat(presenceModule);
    }

    public static SimpleDateFormat proxyProvideSimpleDateFormat(PresenceModule presenceModule) {
        return (SimpleDateFormat) Preconditions.checkNotNull(presenceModule.provideSimpleDateFormat(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public SimpleDateFormat mo10268get() {
        return provideInstance(this.module);
    }
}
