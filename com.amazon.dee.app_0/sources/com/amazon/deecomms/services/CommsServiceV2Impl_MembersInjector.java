package com.amazon.deecomms.services;

import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.sharing.ContentSharingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsServiceV2Impl_MembersInjector implements MembersInjector<CommsServiceV2Impl> {
    private final Provider<AccessoriesHardwareIntentHandler> accessoriesHardwareIntentHandlerProvider;
    private final Provider<AudioContentManager> audioContentManagerProvider;
    private final Provider<ContentSharingService> contentSharingServiceProvider;
    private final Provider<DriveModeEventHandler> mModeSwitchedEventHandlerProvider;
    private final Provider<ProfileSelectionService> profileSelectionServiceProvider;

    public CommsServiceV2Impl_MembersInjector(Provider<AccessoriesHardwareIntentHandler> provider, Provider<AudioContentManager> provider2, Provider<ProfileSelectionService> provider3, Provider<DriveModeEventHandler> provider4, Provider<ContentSharingService> provider5) {
        this.accessoriesHardwareIntentHandlerProvider = provider;
        this.audioContentManagerProvider = provider2;
        this.profileSelectionServiceProvider = provider3;
        this.mModeSwitchedEventHandlerProvider = provider4;
        this.contentSharingServiceProvider = provider5;
    }

    public static MembersInjector<CommsServiceV2Impl> create(Provider<AccessoriesHardwareIntentHandler> provider, Provider<AudioContentManager> provider2, Provider<ProfileSelectionService> provider3, Provider<DriveModeEventHandler> provider4, Provider<ContentSharingService> provider5) {
        return new CommsServiceV2Impl_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAccessoriesHardwareIntentHandler(CommsServiceV2Impl commsServiceV2Impl, Lazy<AccessoriesHardwareIntentHandler> lazy) {
        commsServiceV2Impl.accessoriesHardwareIntentHandler = lazy;
    }

    public static void injectAudioContentManager(CommsServiceV2Impl commsServiceV2Impl, AudioContentManager audioContentManager) {
        commsServiceV2Impl.audioContentManager = audioContentManager;
    }

    public static void injectContentSharingService(CommsServiceV2Impl commsServiceV2Impl, Lazy<ContentSharingService> lazy) {
        commsServiceV2Impl.contentSharingService = lazy;
    }

    public static void injectMModeSwitchedEventHandler(CommsServiceV2Impl commsServiceV2Impl, Lazy<DriveModeEventHandler> lazy) {
        commsServiceV2Impl.mModeSwitchedEventHandler = lazy;
    }

    public static void injectProfileSelectionService(CommsServiceV2Impl commsServiceV2Impl, ProfileSelectionService profileSelectionService) {
        commsServiceV2Impl.profileSelectionService = profileSelectionService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsServiceV2Impl commsServiceV2Impl) {
        commsServiceV2Impl.accessoriesHardwareIntentHandler = DoubleCheck.lazy(this.accessoriesHardwareIntentHandlerProvider);
        commsServiceV2Impl.audioContentManager = this.audioContentManagerProvider.mo10268get();
        commsServiceV2Impl.profileSelectionService = this.profileSelectionServiceProvider.mo10268get();
        commsServiceV2Impl.mModeSwitchedEventHandler = DoubleCheck.lazy(this.mModeSwitchedEventHandlerProvider);
        commsServiceV2Impl.contentSharingService = DoubleCheck.lazy(this.contentSharingServiceProvider);
    }
}
