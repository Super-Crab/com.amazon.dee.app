package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class AhfModule_ProvideApplicationContextFactory implements Factory<Context> {
    private final AhfModule module;

    public AhfModule_ProvideApplicationContextFactory(AhfModule ahfModule) {
        this.module = ahfModule;
    }

    public static AhfModule_ProvideApplicationContextFactory create(AhfModule ahfModule) {
        return new AhfModule_ProvideApplicationContextFactory(ahfModule);
    }

    public static Context provideInstance(AhfModule ahfModule) {
        return proxyProvideApplicationContext(ahfModule);
    }

    public static Context proxyProvideApplicationContext(AhfModule ahfModule) {
        return (Context) Preconditions.checkNotNull(ahfModule.provideApplicationContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
