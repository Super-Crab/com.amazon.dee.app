package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.notifications.PushNotificationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesPushNotificationManagerFactory implements Factory<PushNotificationManager> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceUtils> deviceUtilsProvider;
    private final Provider<ModeSwitchHelper> modeSwitchHelperProvider;
    private final LibraryModule module;
    private final Provider<SecuredSharedPreference> securedSharedPreferenceProvider;

    public LibraryModule_ProvidesPushNotificationManagerFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<SecuredSharedPreference> provider2, Provider<CommsIdentityManager> provider3, Provider<ModeSwitchHelper> provider4, Provider<CapabilitiesManager> provider5, Provider<DeviceUtils> provider6) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.securedSharedPreferenceProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
        this.modeSwitchHelperProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
        this.deviceUtilsProvider = provider6;
    }

    public static LibraryModule_ProvidesPushNotificationManagerFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<SecuredSharedPreference> provider2, Provider<CommsIdentityManager> provider3, Provider<ModeSwitchHelper> provider4, Provider<CapabilitiesManager> provider5, Provider<DeviceUtils> provider6) {
        return new LibraryModule_ProvidesPushNotificationManagerFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static PushNotificationManager provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<SecuredSharedPreference> provider2, Provider<CommsIdentityManager> provider3, Provider<ModeSwitchHelper> provider4, Provider<CapabilitiesManager> provider5, Provider<DeviceUtils> provider6) {
        return (PushNotificationManager) Preconditions.checkNotNull(libraryModule.providesPushNotificationManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PushNotificationManager proxyProvidesPushNotificationManager(LibraryModule libraryModule, Context context, SecuredSharedPreference securedSharedPreference, CommsIdentityManager commsIdentityManager, ModeSwitchHelper modeSwitchHelper, CapabilitiesManager capabilitiesManager, DeviceUtils deviceUtils) {
        return (PushNotificationManager) Preconditions.checkNotNull(libraryModule.providesPushNotificationManager(context, securedSharedPreference, commsIdentityManager, modeSwitchHelper, capabilitiesManager, deviceUtils), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PushNotificationManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.securedSharedPreferenceProvider, this.commsIdentityManagerProvider, this.modeSwitchHelperProvider, this.capabilitiesManagerProvider, this.deviceUtilsProvider);
    }
}
