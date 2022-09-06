package com.amazon.alexa.fitness.service;

import android.content.Context;
import com.amazon.alexa.fitness.api.AlexaFitnessManager;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.context.AlexaFitnessContextManager;
import com.amazon.alexa.fitness.dagger.StaticReleasePackageComponentHolder;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.HomeCardDataProvider;
import com.amazon.alexa.fitness.message.SpeechletEventEmitter;
import com.amazon.alexa.fitness.service.afits.WorkoutSessionDeleteHandler;
import com.amazon.alexa.fitness.service.hds.HdsRetryHandler;
import com.amazon.alexa.fitness.view.ftue.FtueAllDoneViewControllerFactory;
import com.amazon.alexa.fitness.view.ftue.FtueIntroViewControllerFactory;
import com.amazon.alexa.fitness.view.ftue.FtueLocationPermissionViewControllerFactory;
import com.amazon.alexa.fitness.view.ftue.FtueRouteMappingViewControllerFactory;
import com.amazon.alexa.fitness.view.fullscreen.FullScreenViewControllerFactory;
import com.amazon.alexa.fitness.view.settings.SettingsViewControllerFactory;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.data.RouteName;
import dagger.Subcomponent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaFitnessManagerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00072\u00020\u0001:\u0002\u0007\bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/service/AlexaFitnessManagerImpl;", "Lcom/amazon/alexa/fitness/api/AlexaFitnessManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "initialize", "", "Companion", "SubComponent", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AlexaFitnessManagerImpl implements AlexaFitnessManager {
    public static final Companion Companion = new Companion(null);
    private static boolean isInitialized;
    private final Context context;

    /* compiled from: AlexaFitnessManagerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/service/AlexaFitnessManagerImpl$Companion;", "", "()V", "isInitialized", "", "initializeComponentsIfFeatureEnabled", "", "registerFitnessRoutes", "routingRegistry", "Lcom/amazon/alexa/routing/api/RoutingRegistry;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void initializeComponentsIfFeatureEnabled() {
            if (AlexaFitnessManagerImpl.isInitialized) {
                return;
            }
            SubComponent alexaFitnessManagerSubComponent = StaticReleasePackageComponentHolder.getPackageComponent().alexaFitnessManagerSubComponent();
            ILog log = alexaFitnessManagerSubComponent.log();
            FeatureService featureService = alexaFitnessManagerSubComponent.featureService();
            if (featureService.isFitnessInitEnabled()) {
                alexaFitnessManagerSubComponent.alexaFitnessContextManager().start();
                if (!featureService.isLocationTrackingEnabled()) {
                    alexaFitnessManagerSubComponent.hdsRetryHandler().start();
                } else {
                    alexaFitnessManagerSubComponent.workoutSessionDeleteHandler().start();
                }
                alexaFitnessManagerSubComponent.homeCardDataProvider();
                alexaFitnessManagerSubComponent.speechletEventEmitter().start();
                alexaFitnessManagerSubComponent.userProfileService().start();
                alexaFitnessManagerSubComponent.instrumentedAlexaServicesConnection().start();
                alexaFitnessManagerSubComponent.afitsClient();
                alexaFitnessManagerSubComponent.userPreferenceStore();
                ILog.DefaultImpls.info$default(log, "AlexaFitnessManager", "started fitness components", null, 4, null);
                AlexaFitnessManagerImpl.Companion.registerFitnessRoutes(alexaFitnessManagerSubComponent.routingRegistry());
                ILog.DefaultImpls.info$default(log, "AlexaFitnessManager", "registered fitness routes in registry", null, 4, null);
                AlexaFitnessManagerImpl.isInitialized = true;
                ILog.DefaultImpls.info$default(log, "AlexaFitnessManager", "fitness component init completed", null, 4, null);
                return;
            }
            ILog.DefaultImpls.debug$default(log, "AlexaFitnessManager", "fitness init disabled, nothing to initialize", null, 4, null);
        }

        private final void registerFitnessRoutes(RoutingRegistry routingRegistry) {
            routingRegistry.register(new Route.Builder("fitness/track", FullScreenViewControllerFactory.class.getCanonicalName()).asRoot().withContentMode(2).withParent(RouteName.HOME).withTemplate("fitness/track").build());
            routingRegistry.register(new Route.Builder(FitnessRoutesKt.FITNESS_HISTORY_ROUTE, FullScreenViewControllerFactory.class.getCanonicalName()).asRoot().withContentMode(2).withParent(RouteName.HOME).withTemplate(FitnessRoutesKt.FITNESS_HISTORY_ROUTE).build());
            routingRegistry.register(new Route.Builder(FitnessRoutesKt.FITNESS_DETAILED_ROUTE_SESSION_ID, FullScreenViewControllerFactory.class.getCanonicalName()).asRoot().withContentMode(2).withParent(RouteName.HOME).withTemplate(FitnessRoutesKt.FITNESS_DETAILED_ROUTE_SESSION_ID).build());
            routingRegistry.register(new Route.Builder(FitnessRoutesKt.FTUE_LOCATION_PERMISSIONS_ROUTE, FtueLocationPermissionViewControllerFactory.class.getCanonicalName()).asRoot().withContentMode(2).withParent(RouteName.HOME).withTemplate(FitnessRoutesKt.FTUE_LOCATION_PERMISSIONS_ROUTE).build());
            routingRegistry.register(new Route.Builder(FitnessRoutesKt.FTUE_ALL_DONE_ROUTE, FtueAllDoneViewControllerFactory.class.getCanonicalName()).asRoot().withContentMode(2).withParent(RouteName.HOME).withTemplate(FitnessRoutesKt.FTUE_ALL_DONE_ROUTE).build());
            routingRegistry.register(new Route.Builder(FitnessRoutesKt.FTUE_WELCOME_ROUTE, FtueIntroViewControllerFactory.class.getCanonicalName()).asRoot().withContentMode(2).withParent(RouteName.HOME).withTemplate(FitnessRoutesKt.FTUE_WELCOME_ROUTE).build());
            routingRegistry.register(new Route.Builder(FitnessRoutesKt.FTUE_ROUTE_MAPPING_ROUTE, FtueRouteMappingViewControllerFactory.class.getCanonicalName()).asRoot().withContentMode(2).withParent(RouteName.HOME).withTemplate(FitnessRoutesKt.FTUE_ROUTE_MAPPING_ROUTE).build());
            routingRegistry.register(new Route.Builder(FitnessRoutesKt.FITNESS_SETTINGS, SettingsViewControllerFactory.class.getCanonicalName()).withContentMode(5).withParent(RouteName.HOME).withTemplate(FitnessRoutesKt.FITNESS_SETTINGS).build());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: AlexaFitnessManagerImpl.kt */
    @Subcomponent
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u001bH&¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/fitness/service/AlexaFitnessManagerImpl$SubComponent;", "", "afitsClient", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "alexaFitnessContextManager", "Lcom/amazon/alexa/fitness/context/AlexaFitnessContextManager;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "hdsRetryHandler", "Lcom/amazon/alexa/fitness/service/hds/HdsRetryHandler;", "homeCardDataProvider", "Lcom/amazon/alexa/fitness/message/HomeCardDataProvider;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "instrumentedAlexaServicesConnection", "Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "routingRegistry", "Lcom/amazon/alexa/routing/api/RoutingRegistry;", "speechletEventEmitter", "Lcom/amazon/alexa/fitness/message/SpeechletEventEmitter;", "userPreferenceStore", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "userProfileService", "Lcom/amazon/alexa/fitness/service/UserProfileService;", "workoutSessionDeleteHandler", "Lcom/amazon/alexa/fitness/service/afits/WorkoutSessionDeleteHandler;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public interface SubComponent {
        @NotNull
        AfitsClient afitsClient();

        @NotNull
        AlexaFitnessContextManager alexaFitnessContextManager();

        @NotNull
        FeatureService featureService();

        @NotNull
        HdsRetryHandler hdsRetryHandler();

        @NotNull
        HomeCardDataProvider homeCardDataProvider();

        @NotNull
        IdentityManager identityManager();

        @NotNull
        InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection();

        @NotNull
        ILog log();

        @NotNull
        RoutingRegistry routingRegistry();

        @NotNull
        SpeechletEventEmitter speechletEventEmitter();

        @NotNull
        UserPreferenceStore userPreferenceStore();

        @NotNull
        UserProfileService userProfileService();

        @NotNull
        WorkoutSessionDeleteHandler workoutSessionDeleteHandler();
    }

    public AlexaFitnessManagerImpl(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // com.amazon.alexa.fitness.api.AlexaFitnessManager
    /* renamed from: initialize */
    public /* bridge */ /* synthetic */ Unit mo1431initialize() {
        mo1431initialize();
        return Unit.INSTANCE;
    }

    @Override // com.amazon.alexa.fitness.api.AlexaFitnessManager
    /* renamed from: initialize  reason: collision with other method in class */
    public void mo1431initialize() {
        StaticReleasePackageComponentHolder.initializePackageComponent(this.context.getApplicationContext());
        SubComponent alexaFitnessManagerSubComponent = StaticReleasePackageComponentHolder.getPackageComponent().alexaFitnessManagerSubComponent();
        ILog log = alexaFitnessManagerSubComponent.log();
        IdentityManager identityManager = alexaFitnessManagerSubComponent.identityManager();
        identityManager.addIdentityChangedListener(new AlexaFitnessManagerImpl$initialize$1$1(Companion));
        if (identityManager.isUserLoggedIn()) {
            Companion.initializeComponentsIfFeatureEnabled();
        }
        ILog.DefaultImpls.debug$default(log, "AlexaFitnessManager", "initialize completed", null, 4, null);
    }
}
