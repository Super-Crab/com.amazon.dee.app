package com.amazon.alexa.handsfree.settings.voxsettings;

import com.amazon.alexa.handsfree.protocols.Initializer;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoxSettingsSetRetryAttemptReceiver_MembersInjector implements MembersInjector<VoxSettingsSetRetryAttemptReceiver> {
    private final Provider<VoxSettingsEnqueuer> mEnqueuerProvider;
    private final Provider<Initializer> mInitializerProvider;

    public VoxSettingsSetRetryAttemptReceiver_MembersInjector(Provider<VoxSettingsEnqueuer> provider, Provider<Initializer> provider2) {
        this.mEnqueuerProvider = provider;
        this.mInitializerProvider = provider2;
    }

    public static MembersInjector<VoxSettingsSetRetryAttemptReceiver> create(Provider<VoxSettingsEnqueuer> provider, Provider<Initializer> provider2) {
        return new VoxSettingsSetRetryAttemptReceiver_MembersInjector(provider, provider2);
    }

    public static void injectMEnqueuer(VoxSettingsSetRetryAttemptReceiver voxSettingsSetRetryAttemptReceiver, VoxSettingsEnqueuer voxSettingsEnqueuer) {
        voxSettingsSetRetryAttemptReceiver.mEnqueuer = voxSettingsEnqueuer;
    }

    public static void injectMInitializer(VoxSettingsSetRetryAttemptReceiver voxSettingsSetRetryAttemptReceiver, Initializer initializer) {
        voxSettingsSetRetryAttemptReceiver.mInitializer = initializer;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoxSettingsSetRetryAttemptReceiver voxSettingsSetRetryAttemptReceiver) {
        injectMEnqueuer(voxSettingsSetRetryAttemptReceiver, this.mEnqueuerProvider.mo10268get());
        injectMInitializer(voxSettingsSetRetryAttemptReceiver, this.mInitializerProvider.mo10268get());
    }
}
