package com.amazon.alexa.handsfree.settings.voxsettings;

import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoxSettingsJobIntentService_MembersInjector implements MembersInjector<VoxSettingsJobIntentService> {
    private final Provider<HandsFreeUserIdentityProvider> mHandsFreeUserIdentityProvider;
    private final Provider<Initializer> mInitializerProvider;

    public VoxSettingsJobIntentService_MembersInjector(Provider<Initializer> provider, Provider<HandsFreeUserIdentityProvider> provider2) {
        this.mInitializerProvider = provider;
        this.mHandsFreeUserIdentityProvider = provider2;
    }

    public static MembersInjector<VoxSettingsJobIntentService> create(Provider<Initializer> provider, Provider<HandsFreeUserIdentityProvider> provider2) {
        return new VoxSettingsJobIntentService_MembersInjector(provider, provider2);
    }

    public static void injectMHandsFreeUserIdentityProvider(VoxSettingsJobIntentService voxSettingsJobIntentService, HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        voxSettingsJobIntentService.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
    }

    public static void injectMInitializer(VoxSettingsJobIntentService voxSettingsJobIntentService, Initializer initializer) {
        voxSettingsJobIntentService.mInitializer = initializer;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoxSettingsJobIntentService voxSettingsJobIntentService) {
        injectMInitializer(voxSettingsJobIntentService, this.mInitializerProvider.mo10268get());
        injectMHandsFreeUserIdentityProvider(voxSettingsJobIntentService, this.mHandsFreeUserIdentityProvider.mo10268get());
    }
}
