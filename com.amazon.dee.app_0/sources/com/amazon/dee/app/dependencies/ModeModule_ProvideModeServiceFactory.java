package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.mode.ModeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ModeModule_ProvideModeServiceFactory implements Factory<ModeService> {
    private final Provider<Context> contextProvider;
    private final ModeModule module;

    public ModeModule_ProvideModeServiceFactory(ModeModule modeModule, Provider<Context> provider) {
        this.module = modeModule;
        this.contextProvider = provider;
    }

    public static ModeModule_ProvideModeServiceFactory create(ModeModule modeModule, Provider<Context> provider) {
        return new ModeModule_ProvideModeServiceFactory(modeModule, provider);
    }

    public static ModeService provideInstance(ModeModule modeModule, Provider<Context> provider) {
        return proxyProvideModeService(modeModule, provider.mo10268get());
    }

    public static ModeService proxyProvideModeService(ModeModule modeModule, Context context) {
        return (ModeService) Preconditions.checkNotNull(modeModule.provideModeService(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ModeService mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
