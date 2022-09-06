package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.common.util.EncryptionUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesEncryptionUtilsFactory implements Factory<EncryptionUtils> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesEncryptionUtilsFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public static ApplicationModule_ProvidesEncryptionUtilsFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesEncryptionUtilsFactory(applicationModule, provider);
    }

    public static EncryptionUtils provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return (EncryptionUtils) Preconditions.checkNotNull(applicationModule.providesEncryptionUtils(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static EncryptionUtils proxyProvidesEncryptionUtils(ApplicationModule applicationModule, Context context) {
        return (EncryptionUtils) Preconditions.checkNotNull(applicationModule.providesEncryptionUtils(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EncryptionUtils mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
