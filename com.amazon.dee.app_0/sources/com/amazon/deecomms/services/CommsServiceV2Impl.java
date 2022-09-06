package com.amazon.deecomms.services;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.InitializableComponent;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.core.CommsComponent;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.sharing.ContentSharingService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CommsServiceV2Impl implements CommsServiceV2, InitializableComponent {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsServiceV2Impl.class);
    private static final String className = CommsServiceV2Impl.class.getName();
    @Inject
    Lazy<AccessoriesHardwareIntentHandler> accessoriesHardwareIntentHandler;
    @Inject
    AudioContentManager audioContentManager;
    @Inject
    Lazy<ContentSharingService> contentSharingService;
    private final LazyComponent<DriveModeService> driveModeServiceLazy;
    private final LazyComponent<EventBus> lazyEventBus;
    @Inject
    Lazy<DriveModeEventHandler> mModeSwitchedEventHandler;
    @Inject
    ProfileSelectionService profileSelectionService;

    public CommsServiceV2Impl(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        LOG.i("CommsServiceV2Impl constructor");
        CommsDaggerWrapper.getComponent().inject(this);
        this.lazyEventBus = componentGetter.get(EventBus.class);
        this.driveModeServiceLazy = componentGetter.get(DriveModeService.class);
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public ContentSharingService contentSharingService() {
        return CommsDaggerWrapper.getComponent().getContentSharingService();
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public MessagingReceiver conversationMessagingReceiver() {
        return CommsDaggerWrapper.getComponent().getMessagingReceiver();
    }

    @Override // com.amazon.alexa.protocols.service.api.InitializableComponent
    public void initializeComponent(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        LOG.i("CommsServiceV2Impl initializeComponent");
        CommsComponent component = CommsDaggerWrapper.getComponent();
        component.getCommsManager().setupCommsUser();
        MultiFilterSubscriber subscriber = this.lazyEventBus.mo10268get().getSubscriber();
        subscriber.subscribe($$Lambda$CommsServiceV2Impl$J83B08tvv4U23GTzuwb0A0C5AU.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$oRTILjqDKbX1TV8hbxAMR3pLxzw
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                CommsServiceV2Impl.this.lambda$initializeComponent$1$CommsServiceV2Impl(message);
            }
        });
        subscriber.subscribe($$Lambda$CommsServiceV2Impl$pbuSfkWh8qM7k7TDGjIkzS0JWM.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$z6bpTXWwxmpxEpUnXHhWAYsTr4s
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                CommsServiceV2Impl.this.lambda$initializeComponent$3$CommsServiceV2Impl(message);
            }
        });
        subscriber.subscribe($$Lambda$CommsServiceV2Impl$YNgO9LqpsadwMHctienBGtdPtY.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$1VhnxXlfuyVszXE5-IK76xBBBGE
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                CommsServiceV2Impl.this.lambda$initializeComponent$5$CommsServiceV2Impl(message);
            }
        });
        subscriber.subscribe($$Lambda$CommsServiceV2Impl$noaJG5p0WFwnjTulIm4RJTHZd5g.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$n3mZPNQ2i3TfG7JRiVBjqmHeclc
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                CommsServiceV2Impl.this.lambda$initializeComponent$7$CommsServiceV2Impl(message);
            }
        });
        subscriber.subscribe($$Lambda$CommsServiceV2Impl$EBRqsyHoybXPWsU9g6pn2d3akXY.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$2MSGTSg-DcaXeWd6RPS-_hGmKug
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                CommsServiceV2Impl.this.lambda$initializeComponent$9$CommsServiceV2Impl(message);
            }
        });
        IdentityService identityService = component.getIdentityService();
        if (identityService != null && identityService.getUser(className) != null && DeviceUtils.isAMPDDevice()) {
            LOG.i("Attempt to initialize PCC for AMPD");
            CommsDaggerWrapper.getComponent().getPhoneCallControllerManager().registerPhoneCallController();
        }
        this.driveModeServiceLazy.mo10268get().addCardsProvider($$Lambda$CommsServiceV2Impl$iuI4ovFqUHL4ZB83dHs3WSfNKU.INSTANCE);
    }

    public /* synthetic */ void lambda$initializeComponent$1$CommsServiceV2Impl(Message message) {
        this.accessoriesHardwareIntentHandler.mo358get().handlePrivacyStatusEvent(message);
    }

    public /* synthetic */ void lambda$initializeComponent$3$CommsServiceV2Impl(Message message) {
        this.accessoriesHardwareIntentHandler.mo358get().handleCallVipContactEvent(message);
    }

    public /* synthetic */ void lambda$initializeComponent$5$CommsServiceV2Impl(Message message) {
        this.audioContentManager.clearExpiredMedia();
    }

    public /* synthetic */ void lambda$initializeComponent$7$CommsServiceV2Impl(Message message) {
        this.mModeSwitchedEventHandler.mo358get().handleModeSwitch(message);
    }

    public /* synthetic */ void lambda$initializeComponent$9$CommsServiceV2Impl(Message message) {
        this.profileSelectionService.handleProfileSelectionEvent(message);
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public OobeService oobeService() {
        return CommsDaggerWrapper.getComponent().getOobeService();
    }
}
