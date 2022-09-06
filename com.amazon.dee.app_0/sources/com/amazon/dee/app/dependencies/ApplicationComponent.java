package com.amazon.dee.app.dependencies;

import com.amazon.alexa.component.api.ServiceLifecycle;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.enrollment.module.app.EnrollmentModule;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.mobilytics.MobilyticsReporter;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.sendtoapp.notification.SendToAppMessagingReceiverModule;
import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.dee.app.framework.MainApplicationImplementation;
import com.amazon.dee.app.services.accessibility.AccessibilityService;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.export.ComponentBinder;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.tcomm.TCommServiceManager;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity;
import com.amazon.dee.app.ui.clouddrive.ViewBoxFragment;
import com.amazon.dee.app.ui.main.RNLogPrinter;
import com.amazon.dee.app.ui.web.AlexaWebView;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.conversation.FireOSDirectiveHandlerService;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.dee.app.metrics.MetricsService;
import dagger.Component;
import okhttp3.OkHttpClient;
@Component(modules = {ApplicationModule.class, NetworkModule.class, ServiceModule.class, IdentityModule.class, GoogleApiModule.class, FeaturesModule.class, DataStoreModule.class, RoutingModule.class, LocationModule.class, AmazonMessagingModule.class, MessagingModule.class, EventBusModule.class, TCommServiceModule.class, ModeModule.class, DriveModeApplicationModule.class, CloudDriveModule.class, PhotoServiceModule.class, CommsModule.class, VoiceModule.class, EntertainmentModule.class, PreloadAttributionModule.class, EnrollmentModule.class, MetricsModule.class, KinesisMetricsModule.class, MobilyticsModule.class, TTCFModule.class, AlexaDeviceBackgroundImageModule.class, SendToAppMessagingReceiverModule.class})
@ApplicationScope
/* loaded from: classes12.dex */
public interface ApplicationComponent {
    AccessibilityService accessibilityService();

    AccountService accountService();

    DefaultApplicationLifecycleService applicationLifecycleService();

    CertificateReaderService certificateReaderService();

    CommsManager commsManager();

    AlexaCommsService commsService();

    CommsServiceV2 commsServiceV2();

    ComponentBinder componentBinder();

    ConversationService conversationService();

    CrashMetadata crashMetadata();

    EventBus eventBus();

    ServiceLifecycle eventBusService();

    OkHttpClient httpClient();

    IdentityService identityService();

    MainApplicationImplementation inject(MainApplicationImplementation mainApplicationImplementation);

    AlexaDeviceBackgroundImageActivity inject(AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity);

    ViewBoxFragment inject(ViewBoxFragment viewBoxFragment);

    AlexaWebView inject(AlexaWebView alexaWebView);

    FireOSDirectiveHandlerService inject(FireOSDirectiveHandlerService fireOSDirectiveHandlerService);

    LocationService locationService();

    MainActivityLifecycleService mainActivityLifecycleService();

    MessagingService messagingService();

    MetricsService metricsService();

    MobilyticsReporter mobilyticsReporter();

    ModeService modeService();

    PersistentStorage.Factory persistentStorageFactory();

    ExternalUIComponent plus(ExternalUIModule externalUIModule);

    MainComponent plus(MainModule mainModule, ConversationModule conversationModule, ElementsModule elementsModule);

    RNLogPrinter rnLogPrinter();

    RoutingRegistry routingRegistry();

    TCommServiceManager tCommServiceManager();

    TTCFService ttcfService();

    VoiceService voiceService();
}
