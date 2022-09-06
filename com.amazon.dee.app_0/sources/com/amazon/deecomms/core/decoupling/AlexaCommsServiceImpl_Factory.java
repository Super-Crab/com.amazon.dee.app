package com.amazon.deecomms.core.decoupling;

import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.calling.phonecallcontroller.PhoneCallControllerManager;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.drivemode.cards.CommsDriveModeCardProvider;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.sharing.ContentSharingService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaCommsServiceImpl_Factory implements Factory<AlexaCommsServiceImpl> {
    private final Provider<AccessoriesHardwareIntentHandler> accessoriesHardwareIntentHandlerLazyProvider;
    private final Provider<AudioContentManager> audioContentManagerLazyProvider;
    private final Provider<CommsDeviceSupport> commsDeviceSupportLazyProvider;
    private final Provider<CommsDriveModeCardProvider> commsDriveModeCardProviderLazyProvider;
    private final Provider<CommsDynamicFeatureService> commsDynamicFeatureServiceLazyProvider;
    private final Provider<CommsManager> commsManagerLazyProvider;
    private final Provider<ContentSharingService> contentSharingServiceLazyProvider;
    private final Provider<ConversationService> conversationServiceLazyProvider;
    private final Provider<DriveModeEventHandler> driveModeEventHandlerLazyProvider;
    private final Provider<DriveModeService> driveModeServiceLazyProvider;
    private final Provider<EventBus> eventBusLazyProvider;
    private final Provider<FeatureFilter> featureFilterLazyProvider;
    private final Provider<IdentityService> identityServiceLazyProvider;
    private final Provider<MainActivityLoader> mainActivityLoaderLazyProvider;
    private final Provider<MessagingReceiver> messagingReceiverLazyProvider;
    private final Provider<OobeService> oobeServiceLazyProvider;
    private final Provider<PhoneCallControllerManager> phoneCallControllerManagerLazyProvider;
    private final Provider<ProfileSelectionService> profileSelectionServiceLazyProvider;

    public AlexaCommsServiceImpl_Factory(Provider<IdentityService> provider, Provider<EventBus> provider2, Provider<OobeService> provider3, Provider<MessagingReceiver> provider4, Provider<ContentSharingService> provider5, Provider<ConversationService> provider6, Provider<CommsDeviceSupport> provider7, Provider<CommsDynamicFeatureService> provider8, Provider<CommsManager> provider9, Provider<FeatureFilter> provider10, Provider<AccessoriesHardwareIntentHandler> provider11, Provider<AudioContentManager> provider12, Provider<DriveModeService> provider13, Provider<DriveModeEventHandler> provider14, Provider<CommsDriveModeCardProvider> provider15, Provider<PhoneCallControllerManager> provider16, Provider<MainActivityLoader> provider17, Provider<ProfileSelectionService> provider18) {
        this.identityServiceLazyProvider = provider;
        this.eventBusLazyProvider = provider2;
        this.oobeServiceLazyProvider = provider3;
        this.messagingReceiverLazyProvider = provider4;
        this.contentSharingServiceLazyProvider = provider5;
        this.conversationServiceLazyProvider = provider6;
        this.commsDeviceSupportLazyProvider = provider7;
        this.commsDynamicFeatureServiceLazyProvider = provider8;
        this.commsManagerLazyProvider = provider9;
        this.featureFilterLazyProvider = provider10;
        this.accessoriesHardwareIntentHandlerLazyProvider = provider11;
        this.audioContentManagerLazyProvider = provider12;
        this.driveModeServiceLazyProvider = provider13;
        this.driveModeEventHandlerLazyProvider = provider14;
        this.commsDriveModeCardProviderLazyProvider = provider15;
        this.phoneCallControllerManagerLazyProvider = provider16;
        this.mainActivityLoaderLazyProvider = provider17;
        this.profileSelectionServiceLazyProvider = provider18;
    }

    public static AlexaCommsServiceImpl_Factory create(Provider<IdentityService> provider, Provider<EventBus> provider2, Provider<OobeService> provider3, Provider<MessagingReceiver> provider4, Provider<ContentSharingService> provider5, Provider<ConversationService> provider6, Provider<CommsDeviceSupport> provider7, Provider<CommsDynamicFeatureService> provider8, Provider<CommsManager> provider9, Provider<FeatureFilter> provider10, Provider<AccessoriesHardwareIntentHandler> provider11, Provider<AudioContentManager> provider12, Provider<DriveModeService> provider13, Provider<DriveModeEventHandler> provider14, Provider<CommsDriveModeCardProvider> provider15, Provider<PhoneCallControllerManager> provider16, Provider<MainActivityLoader> provider17, Provider<ProfileSelectionService> provider18) {
        return new AlexaCommsServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18);
    }

    public static AlexaCommsServiceImpl newAlexaCommsServiceImpl(Lazy<IdentityService> lazy, Lazy<EventBus> lazy2, Lazy<OobeService> lazy3, Lazy<MessagingReceiver> lazy4, Lazy<ContentSharingService> lazy5, Lazy<ConversationService> lazy6, Lazy<CommsDeviceSupport> lazy7, Lazy<CommsDynamicFeatureService> lazy8, Lazy<CommsManager> lazy9, Lazy<FeatureFilter> lazy10, Lazy<AccessoriesHardwareIntentHandler> lazy11, Lazy<AudioContentManager> lazy12, Lazy<DriveModeService> lazy13, Lazy<DriveModeEventHandler> lazy14, Lazy<CommsDriveModeCardProvider> lazy15, Lazy<PhoneCallControllerManager> lazy16, Lazy<MainActivityLoader> lazy17, Lazy<ProfileSelectionService> lazy18) {
        return new AlexaCommsServiceImpl(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18);
    }

    public static AlexaCommsServiceImpl provideInstance(Provider<IdentityService> provider, Provider<EventBus> provider2, Provider<OobeService> provider3, Provider<MessagingReceiver> provider4, Provider<ContentSharingService> provider5, Provider<ConversationService> provider6, Provider<CommsDeviceSupport> provider7, Provider<CommsDynamicFeatureService> provider8, Provider<CommsManager> provider9, Provider<FeatureFilter> provider10, Provider<AccessoriesHardwareIntentHandler> provider11, Provider<AudioContentManager> provider12, Provider<DriveModeService> provider13, Provider<DriveModeEventHandler> provider14, Provider<CommsDriveModeCardProvider> provider15, Provider<PhoneCallControllerManager> provider16, Provider<MainActivityLoader> provider17, Provider<ProfileSelectionService> provider18) {
        return new AlexaCommsServiceImpl(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider16), DoubleCheck.lazy(provider17), DoubleCheck.lazy(provider18));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsServiceImpl mo10268get() {
        return provideInstance(this.identityServiceLazyProvider, this.eventBusLazyProvider, this.oobeServiceLazyProvider, this.messagingReceiverLazyProvider, this.contentSharingServiceLazyProvider, this.conversationServiceLazyProvider, this.commsDeviceSupportLazyProvider, this.commsDynamicFeatureServiceLazyProvider, this.commsManagerLazyProvider, this.featureFilterLazyProvider, this.accessoriesHardwareIntentHandlerLazyProvider, this.audioContentManagerLazyProvider, this.driveModeServiceLazyProvider, this.driveModeEventHandlerLazyProvider, this.commsDriveModeCardProviderLazyProvider, this.phoneCallControllerManagerLazyProvider, this.mainActivityLoaderLazyProvider, this.profileSelectionServiceLazyProvider);
    }
}
