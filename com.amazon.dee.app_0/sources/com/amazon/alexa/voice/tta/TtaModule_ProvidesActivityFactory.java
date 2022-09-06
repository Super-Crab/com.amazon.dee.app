package com.amazon.alexa.voice.tta;

import android.app.Activity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class TtaModule_ProvidesActivityFactory implements Factory<Activity> {
    private final TtaModule module;

    public TtaModule_ProvidesActivityFactory(TtaModule ttaModule) {
        this.module = ttaModule;
    }

    public static TtaModule_ProvidesActivityFactory create(TtaModule ttaModule) {
        return new TtaModule_ProvidesActivityFactory(ttaModule);
    }

    public static Activity provideInstance(TtaModule ttaModule) {
        return proxyProvidesActivity(ttaModule);
    }

    public static Activity proxyProvidesActivity(TtaModule ttaModule) {
        return (Activity) Preconditions.checkNotNull(ttaModule.providesActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Activity mo10268get() {
        return provideInstance(this.module);
    }
}
