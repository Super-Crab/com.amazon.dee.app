package com.amazon.deecomms.core.decoupling;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.calling.phonecallcontroller.PhoneCallControllerManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.drivemode.cards.CommsDriveModeCardProvider;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.sharing.ContentSharingService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class AlexaCommsServiceImpl implements AlexaCommsService {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AlexaCommsServiceImpl.class);
    private static final String className = AlexaCommsServiceImpl.class.getName();
    private Lazy<AccessoriesHardwareIntentHandler> accessoriesHardwareIntentHandlerLazy;
    private Lazy<AudioContentManager> audioContentManagerLazy;
    private Lazy<CommsDeviceSupport> commsDeviceSupportLazy;
    private Lazy<CommsDriveModeCardProvider> commsDriveModeCardProviderLazy;
    private Lazy<CommsDynamicFeatureService> commsDynamicFeatureServiceLazy;
    private Lazy<CommsManager> commsManagerLazy;
    private Lazy<ContentSharingService> contentSharingServiceLazy;
    private Lazy<ConversationService> conversationServiceLazy;
    private Lazy<DriveModeEventHandler> driveModeEventHandlerLazy;
    private Lazy<DriveModeService> driveModeServiceLazy;
    private Lazy<EventBus> eventBusLazy;
    private Lazy<FeatureFilter> featureFilterLazy;
    private Lazy<IdentityService> identityServiceLazy;
    private Lazy<MainActivityLoader> mainActivityLoaderLazy;
    private Lazy<MessagingReceiver> messagingReceiverLazy;
    private Lazy<OobeService> oobeServiceLazy;
    private Lazy<PhoneCallControllerManager> phoneCallControllerManagerLazy;
    private Lazy<ProfileSelectionService> profileSelectionServiceLazy;

    @Inject
    public AlexaCommsServiceImpl(@NonNull Lazy<IdentityService> lazy, @NonNull Lazy<EventBus> lazy2, @NonNull Lazy<OobeService> lazy3, @NonNull Lazy<MessagingReceiver> lazy4, @NonNull Lazy<ContentSharingService> lazy5, @NonNull Lazy<ConversationService> lazy6, @NonNull Lazy<CommsDeviceSupport> lazy7, @NonNull Lazy<CommsDynamicFeatureService> lazy8, @NonNull Lazy<CommsManager> lazy9, @NonNull Lazy<FeatureFilter> lazy10, @NonNull Lazy<AccessoriesHardwareIntentHandler> lazy11, @NonNull Lazy<AudioContentManager> lazy12, @NonNull Lazy<DriveModeService> lazy13, @NonNull Lazy<DriveModeEventHandler> lazy14, @NonNull Lazy<CommsDriveModeCardProvider> lazy15, @NonNull Lazy<PhoneCallControllerManager> lazy16, @NonNull Lazy<MainActivityLoader> lazy17, @NonNull Lazy<ProfileSelectionService> lazy18) {
        this.identityServiceLazy = lazy;
        this.eventBusLazy = lazy2;
        this.oobeServiceLazy = lazy3;
        this.messagingReceiverLazy = lazy4;
        this.contentSharingServiceLazy = lazy5;
        this.conversationServiceLazy = lazy6;
        this.commsDeviceSupportLazy = lazy7;
        this.commsDynamicFeatureServiceLazy = lazy8;
        this.commsManagerLazy = lazy9;
        this.featureFilterLazy = lazy10;
        this.accessoriesHardwareIntentHandlerLazy = lazy11;
        this.audioContentManagerLazy = lazy12;
        this.driveModeServiceLazy = lazy13;
        this.driveModeEventHandlerLazy = lazy14;
        this.commsDriveModeCardProviderLazy = lazy15;
        this.phoneCallControllerManagerLazy = lazy16;
        this.mainActivityLoaderLazy = lazy17;
        this.profileSelectionServiceLazy = lazy18;
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public ContentSharingService contentSharingService() {
        return this.contentSharingServiceLazy.mo358get();
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public MessagingReceiver conversationMessagingReceiver() {
        return this.messagingReceiverLazy.mo358get();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public CommsDeviceSupport getCommsDeviceSupport() {
        return this.commsDeviceSupportLazy.mo358get();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public CommsDynamicFeatureService getCommsDynamicFeatureService() {
        return this.commsDynamicFeatureServiceLazy.mo358get();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public FeatureFilter getCommsFeatureFilter() {
        return this.featureFilterLazy.mo358get();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public CommsManager getCommsManager() {
        return this.commsManagerLazy.mo358get();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public ConversationService getConversationService() {
        return this.conversationServiceLazy.mo358get();
    }

    @Override // com.amazon.deecomms.core.decoupling.AlexaCommsService
    public void initialize() {
        LOG.i("Initialize AlexaCommsServiceImpl");
        this.commsManagerLazy.mo358get().setupCommsUser();
        MultiFilterSubscriber subscriber = this.eventBusLazy.mo358get().getSubscriber();
        subscriber.subscribe($$Lambda$AlexaCommsServiceImpl$kjdNSFGVT50qCxF0txRqF78Gcok.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$cnUAZI3TolRcUWqsdJn5w5qUh8s
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaCommsServiceImpl.this.lambda$initialize$1$AlexaCommsServiceImpl(message);
            }
        });
        subscriber.subscribe($$Lambda$AlexaCommsServiceImpl$ywzsjv0cIcta9GUpR4meyJatOI.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$ZrGRkEAYqJwAEsMd1RfN_Rbn580
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaCommsServiceImpl.this.lambda$initialize$3$AlexaCommsServiceImpl(message);
            }
        });
        subscriber.subscribe($$Lambda$AlexaCommsServiceImpl$ZiR1XbodiEkodSYmaSDOT4uTeMQ.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$RCUauO6T-lDd-bdDWHU1gwj8XyY
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaCommsServiceImpl.this.lambda$initialize$5$AlexaCommsServiceImpl(message);
            }
        });
        subscriber.subscribe($$Lambda$AlexaCommsServiceImpl$r2Px88sxdm9gkUowKOKKIRfGD1E.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$_mx12ZIMx0A_GZ11q0H4LRyBoMs
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaCommsServiceImpl.this.lambda$initialize$7$AlexaCommsServiceImpl(message);
            }
        });
        subscriber.subscribe($$Lambda$AlexaCommsServiceImpl$uHfRAxVoAEYe3wZXoJfWE1nRXR8.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$vUOQuvcetGgZThfsLIGkxMHblE8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaCommsServiceImpl.this.lambda$initialize$9$AlexaCommsServiceImpl(message);
            }
        });
        if (this.identityServiceLazy.mo358get() != null && this.identityServiceLazy.mo358get().getUser(className) != null && DeviceUtils.isAMPDDevice()) {
            LOG.i("Attempt to initialize PCC for AMPD");
            this.phoneCallControllerManagerLazy.mo358get().registerPhoneCallController();
        }
        this.driveModeServiceLazy.mo358get().addCardsProvider(new Lazy() { // from class: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceImpl$NXDrywU4kRR5CQqg2yvaNbSZQdI
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return AlexaCommsServiceImpl.this.lambda$initialize$12$AlexaCommsServiceImpl();
            }
        });
    }

    public /* synthetic */ void lambda$initialize$1$AlexaCommsServiceImpl(Message message) {
        this.accessoriesHardwareIntentHandlerLazy.mo358get().handlePrivacyStatusEvent(message);
    }

    public /* synthetic */ DriveModeCardsProvider lambda$initialize$12$AlexaCommsServiceImpl() {
        return this.commsDriveModeCardProviderLazy.mo358get();
    }

    public /* synthetic */ void lambda$initialize$3$AlexaCommsServiceImpl(Message message) {
        this.accessoriesHardwareIntentHandlerLazy.mo358get().handleCallVipContactEvent(message);
    }

    public /* synthetic */ void lambda$initialize$5$AlexaCommsServiceImpl(Message message) {
        this.audioContentManagerLazy.mo358get().clearExpiredMedia();
    }

    public /* synthetic */ void lambda$initialize$7$AlexaCommsServiceImpl(Message message) {
        this.driveModeEventHandlerLazy.mo358get().handleModeSwitch(message);
    }

    public /* synthetic */ void lambda$initialize$9$AlexaCommsServiceImpl(Message message) {
        this.profileSelectionServiceLazy.mo358get().handleProfileSelectionEvent(message);
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public OobeService oobeService() {
        return this.oobeServiceLazy.mo358get();
    }

    public boolean startMainActivity(@NonNull Context context, @NonNull CommsView commsView) {
        return this.mainActivityLoaderLazy.mo358get().startMainActivity(context, commsView);
    }
}
