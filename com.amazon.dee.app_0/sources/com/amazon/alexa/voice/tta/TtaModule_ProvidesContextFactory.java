package com.amazon.alexa.voice.tta;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class TtaModule_ProvidesContextFactory implements Factory<Context> {
    private final TtaModule module;

    public TtaModule_ProvidesContextFactory(TtaModule ttaModule) {
        this.module = ttaModule;
    }

    public static TtaModule_ProvidesContextFactory create(TtaModule ttaModule) {
        return new TtaModule_ProvidesContextFactory(ttaModule);
    }

    public static Context provideInstance(TtaModule ttaModule) {
        return proxyProvidesContext(ttaModule);
    }

    public static Context proxyProvidesContext(TtaModule ttaModule) {
        return (Context) Preconditions.checkNotNull(ttaModule.providesContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
