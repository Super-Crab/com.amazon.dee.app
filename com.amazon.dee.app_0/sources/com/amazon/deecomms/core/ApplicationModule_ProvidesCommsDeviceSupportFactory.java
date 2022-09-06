package com.amazon.deecomms.core;

import com.amazon.deecomms.common.util.EncryptionUtils;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesCommsDeviceSupportFactory implements Factory<CommsDeviceSupport> {
    private final Provider<EncryptionUtils> encryptionUtilsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesCommsDeviceSupportFactory(ApplicationModule applicationModule, Provider<EncryptionUtils> provider) {
        this.module = applicationModule;
        this.encryptionUtilsProvider = provider;
    }

    public static ApplicationModule_ProvidesCommsDeviceSupportFactory create(ApplicationModule applicationModule, Provider<EncryptionUtils> provider) {
        return new ApplicationModule_ProvidesCommsDeviceSupportFactory(applicationModule, provider);
    }

    public static CommsDeviceSupport provideInstance(ApplicationModule applicationModule, Provider<EncryptionUtils> provider) {
        return (CommsDeviceSupport) Preconditions.checkNotNull(applicationModule.providesCommsDeviceSupport(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsDeviceSupport proxyProvidesCommsDeviceSupport(ApplicationModule applicationModule, EncryptionUtils encryptionUtils) {
        return (CommsDeviceSupport) Preconditions.checkNotNull(applicationModule.providesCommsDeviceSupport(encryptionUtils), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDeviceSupport mo10268get() {
        return provideInstance(this.module, this.encryptionUtilsProvider);
    }
}
