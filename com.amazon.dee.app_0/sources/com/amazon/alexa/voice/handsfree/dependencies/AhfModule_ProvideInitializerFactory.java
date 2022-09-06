package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.handsfree.protocols.Initializer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class AhfModule_ProvideInitializerFactory implements Factory<Initializer> {
    private final AhfModule module;

    public AhfModule_ProvideInitializerFactory(AhfModule ahfModule) {
        this.module = ahfModule;
    }

    public static AhfModule_ProvideInitializerFactory create(AhfModule ahfModule) {
        return new AhfModule_ProvideInitializerFactory(ahfModule);
    }

    public static Initializer provideInstance(AhfModule ahfModule) {
        return proxyProvideInitializer(ahfModule);
    }

    public static Initializer proxyProvideInitializer(AhfModule ahfModule) {
        return (Initializer) Preconditions.checkNotNull(ahfModule.provideInitializer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Initializer mo10268get() {
        return provideInstance(this.module);
    }
}
