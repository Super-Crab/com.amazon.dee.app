package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.common.util.DeviceUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesDeviceUtilsFactory implements Factory<DeviceUtils> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<SecuredSharedPreference> securedSharedPreferenceProvider;

    public LibraryModule_ProvidesDeviceUtilsFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<SecuredSharedPreference> provider2) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.securedSharedPreferenceProvider = provider2;
    }

    public static LibraryModule_ProvidesDeviceUtilsFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<SecuredSharedPreference> provider2) {
        return new LibraryModule_ProvidesDeviceUtilsFactory(libraryModule, provider, provider2);
    }

    public static DeviceUtils provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<SecuredSharedPreference> provider2) {
        return (DeviceUtils) Preconditions.checkNotNull(libraryModule.providesDeviceUtils(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceUtils proxyProvidesDeviceUtils(LibraryModule libraryModule, Context context, SecuredSharedPreference securedSharedPreference) {
        return (DeviceUtils) Preconditions.checkNotNull(libraryModule.providesDeviceUtils(context, securedSharedPreference), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceUtils mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.securedSharedPreferenceProvider);
    }
}
