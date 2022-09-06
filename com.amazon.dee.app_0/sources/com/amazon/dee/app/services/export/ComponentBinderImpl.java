package com.amazon.dee.app.services.export;

import android.content.Context;
import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.audiopersonalization.api.AudioPersonalizationManager;
import com.amazon.alexa.blueprints.api.BlueprintsEndpoint;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashObserverRegistrar;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DevicePowerMonitor;
import com.amazon.alexa.devicesetup.service.DeviceProvisioningCoordinator;
import com.amazon.alexa.devicesetup.softap.handler.EchoSetupHandler;
import com.amazon.alexa.dialog.api.DialogBuilderProvider;
import com.amazon.alexa.dnssd.DnssdService;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.feature.provider.api.FeatureStore;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.fitness.api.AlexaFitnessManager;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.hho.api.HHOService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.imageloader.api.ImageLoader;
import com.amazon.alexa.lifecycle.api.LifecycleManager;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.location.provider.LocationSkillsService;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.network.api.HttpClient;
import com.amazon.alexa.presence.api.PresenceService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.jobs.JobIDProvider;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.reactnative.api.ElementsViewFactory;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder;
import com.amazon.alexa.sharing.api.SharingClient;
import com.amazon.alexa.tarazed.api.AlexaTarazedService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.storage.JsonConverter;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.latencyinfra.LatencyInfra;
import com.dee.app.data.DefaultElementLocalStorage;
import com.dee.app.data.api.ElementLocalStorage;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class ComponentBinderImpl implements ComponentBinder {
    private final Lazy<DefaultApplicationLifecycleService> applicationLifecycleService;
    private final Lazy<AssetManagementService> assetManagementService;
    private final Lazy<CertificateReaderService> certificateReaderService;
    private final Lazy<CommsServiceV2> commsServiceV2;
    private final Lazy<CoralService> coralService;
    private final Lazy<CrashMetadata> crashMetadata;
    private final Lazy<CrashObserverRegistrar> crashObserverRegistrar;
    private final Lazy<CrashReporter> crashReporter;
    private final Lazy<DialogBuilderProvider> dialogBuilderProvider;
    private final Lazy<DefaultElementLocalStorage> elementsDataStore;
    private final Lazy<EnvironmentService> environmentService;
    private final Lazy<FeatureQuery> featureQuery;
    private final Lazy<FeatureServiceV2> featureServiceV2;
    private final Lazy<IdentityService> identityService;
    private final Lazy<JsonConverter> jsonConverter;
    private final Lazy<LocationService> locationService;
    private final Lazy<MainActivityLifecycleService> mainActivityLifecycleService;
    private final Lazy<MarketplaceService> marketplaceService;
    private final Lazy<MetricsService> metricsService;
    private final Lazy<MetricsServiceV2> metricsServiceV2;
    private final Lazy<Mobilytics> mobilytics;
    private final Lazy<ModeService> modeService;
    private final Lazy<PersistentStorage.Factory> persistentStorageFactory;
    private final Lazy<PersonIdProvider> personIdProvider;
    private final Lazy<com.amazon.alexa.protocols.marketplace.MarketplaceService> protocolsMarketplaceService;
    private final Lazy<RoutingService> routingService;
    private final Lazy<TTCFService> ttcfService;

    public ComponentBinderImpl(Lazy<EnvironmentService> lazy, Lazy<FeatureQuery> lazy2, Lazy<IdentityService> lazy3, Lazy<PersonIdProvider> lazy4, Lazy<MainActivityLifecycleService> lazy5, Lazy<DefaultApplicationLifecycleService> lazy6, Lazy<MarketplaceService> lazy7, Lazy<com.amazon.alexa.protocols.marketplace.MarketplaceService> lazy8, Lazy<CrashMetadata> lazy9, Lazy<CrashObserverRegistrar> lazy10, Lazy<CrashReporter> lazy11, Lazy<MetricsService> lazy12, Lazy<MetricsServiceV2> lazy13, Lazy<Mobilytics> lazy14, Lazy<ModeService> lazy15, Lazy<LocationService> lazy16, Lazy<CoralService> lazy17, Lazy<JsonConverter> lazy18, Lazy<PersistentStorage.Factory> lazy19, Lazy<RoutingService> lazy20, Lazy<TTCFService> lazy21, Lazy<CertificateReaderService> lazy22, Lazy<CommsServiceV2> lazy23, Lazy<FeatureServiceV2> lazy24, Lazy<AssetManagementService> lazy25, Lazy<DefaultElementLocalStorage> lazy26, Lazy<DialogBuilderProvider> lazy27) {
        this.environmentService = lazy;
        this.featureQuery = lazy2;
        this.identityService = lazy3;
        this.personIdProvider = lazy4;
        this.mainActivityLifecycleService = lazy5;
        this.applicationLifecycleService = lazy6;
        this.marketplaceService = lazy7;
        this.protocolsMarketplaceService = lazy8;
        this.crashMetadata = lazy9;
        this.crashObserverRegistrar = lazy10;
        this.crashReporter = lazy11;
        this.metricsService = lazy12;
        this.metricsServiceV2 = lazy13;
        this.mobilytics = lazy14;
        this.modeService = lazy15;
        this.locationService = lazy16;
        this.coralService = lazy17;
        this.jsonConverter = lazy18;
        this.persistentStorageFactory = lazy19;
        this.routingService = lazy20;
        this.ttcfService = lazy21;
        this.certificateReaderService = lazy22;
        this.commsServiceV2 = lazy23;
        this.featureServiceV2 = lazy24;
        this.assetManagementService = lazy25;
        this.elementsDataStore = lazy26;
        this.dialogBuilderProvider = lazy27;
    }

    private <T> void addDaggerBinding(Class<T> cls, ComponentRegistry.ConcreteComponentFactory<T> concreteComponentFactory, Map<Class<?>, ComponentRegistry.ConcreteComponentFactory<?>> map) {
        ComponentRegistry.getInstance().bindConcreteFactory(cls, concreteComponentFactory);
        map.put(cls, concreteComponentFactory);
    }

    public /* synthetic */ EnvironmentService lambda$publishTransitionalObjectsOwnedByDagger$0$ComponentBinderImpl(Context context) {
        return this.environmentService.mo358get();
    }

    public /* synthetic */ FeatureQuery lambda$publishTransitionalObjectsOwnedByDagger$1$ComponentBinderImpl(Context context) {
        return this.featureQuery.mo358get();
    }

    public /* synthetic */ CrashReporter lambda$publishTransitionalObjectsOwnedByDagger$10$ComponentBinderImpl(Context context) {
        return this.crashReporter.mo358get();
    }

    public /* synthetic */ MetricsService lambda$publishTransitionalObjectsOwnedByDagger$11$ComponentBinderImpl(Context context) {
        return this.metricsService.mo358get();
    }

    public /* synthetic */ ModeService lambda$publishTransitionalObjectsOwnedByDagger$12$ComponentBinderImpl(Context context) {
        return this.modeService.mo358get();
    }

    public /* synthetic */ MetricsServiceV2 lambda$publishTransitionalObjectsOwnedByDagger$13$ComponentBinderImpl(Context context) {
        return this.metricsServiceV2.mo358get();
    }

    public /* synthetic */ Mobilytics lambda$publishTransitionalObjectsOwnedByDagger$14$ComponentBinderImpl(Context context) {
        return this.mobilytics.mo358get();
    }

    public /* synthetic */ LocationService lambda$publishTransitionalObjectsOwnedByDagger$15$ComponentBinderImpl(Context context) {
        return this.locationService.mo358get();
    }

    public /* synthetic */ CoralService lambda$publishTransitionalObjectsOwnedByDagger$16$ComponentBinderImpl(Context context) {
        return this.coralService.mo358get();
    }

    public /* synthetic */ JsonConverter lambda$publishTransitionalObjectsOwnedByDagger$17$ComponentBinderImpl(Context context) {
        return this.jsonConverter.mo358get();
    }

    public /* synthetic */ PersistentStorage.Factory lambda$publishTransitionalObjectsOwnedByDagger$18$ComponentBinderImpl(Context context) {
        return this.persistentStorageFactory.mo358get();
    }

    public /* synthetic */ RoutingService lambda$publishTransitionalObjectsOwnedByDagger$19$ComponentBinderImpl(Context context) {
        return this.routingService.mo358get();
    }

    public /* synthetic */ PersonIdProvider lambda$publishTransitionalObjectsOwnedByDagger$2$ComponentBinderImpl(Context context) {
        return this.personIdProvider.mo358get();
    }

    public /* synthetic */ TTCFCheckpoint lambda$publishTransitionalObjectsOwnedByDagger$20$ComponentBinderImpl(Context context) {
        return this.ttcfService.mo358get();
    }

    public /* synthetic */ CertificateReaderService lambda$publishTransitionalObjectsOwnedByDagger$21$ComponentBinderImpl(Context context) {
        return this.certificateReaderService.mo358get();
    }

    public /* synthetic */ CommsServiceV2 lambda$publishTransitionalObjectsOwnedByDagger$22$ComponentBinderImpl(Context context) {
        return this.commsServiceV2.mo358get();
    }

    public /* synthetic */ FeatureServiceV2 lambda$publishTransitionalObjectsOwnedByDagger$23$ComponentBinderImpl(Context context) {
        return this.featureServiceV2.mo358get();
    }

    public /* synthetic */ AssetManagementService lambda$publishTransitionalObjectsOwnedByDagger$24$ComponentBinderImpl(Context context) {
        return this.assetManagementService.mo358get();
    }

    public /* synthetic */ ElementLocalStorage lambda$publishTransitionalObjectsOwnedByDagger$25$ComponentBinderImpl(Context context) {
        return this.elementsDataStore.mo358get();
    }

    public /* synthetic */ DialogBuilderProvider lambda$publishTransitionalObjectsOwnedByDagger$26$ComponentBinderImpl(Context context) {
        return this.dialogBuilderProvider.mo358get();
    }

    public /* synthetic */ IdentityService lambda$publishTransitionalObjectsOwnedByDagger$3$ComponentBinderImpl(Context context) {
        return this.identityService.mo358get();
    }

    public /* synthetic */ MainActivityLifecycleObserverRegistrar lambda$publishTransitionalObjectsOwnedByDagger$4$ComponentBinderImpl(Context context) {
        return this.mainActivityLifecycleService.mo358get();
    }

    public /* synthetic */ ApplicationLifecycleService lambda$publishTransitionalObjectsOwnedByDagger$5$ComponentBinderImpl(Context context) {
        return this.applicationLifecycleService.mo358get();
    }

    public /* synthetic */ MarketplaceService lambda$publishTransitionalObjectsOwnedByDagger$6$ComponentBinderImpl(Context context) {
        return this.marketplaceService.mo358get();
    }

    public /* synthetic */ com.amazon.alexa.protocols.marketplace.MarketplaceService lambda$publishTransitionalObjectsOwnedByDagger$7$ComponentBinderImpl(Context context) {
        return this.protocolsMarketplaceService.mo358get();
    }

    public /* synthetic */ CrashMetadata lambda$publishTransitionalObjectsOwnedByDagger$8$ComponentBinderImpl(Context context) {
        return this.crashMetadata.mo358get();
    }

    public /* synthetic */ CrashObserverRegistrar lambda$publishTransitionalObjectsOwnedByDagger$9$ComponentBinderImpl(Context context) {
        return this.crashObserverRegistrar.mo358get();
    }

    @Override // com.amazon.dee.app.services.export.ComponentBinder
    public Map<Class<?>, ComponentRegistry.ConcreteComponentFactory<?>> publishTransitionalObjectsOwnedByDagger() {
        HashMap hashMap = new HashMap();
        addDaggerBinding(EnvironmentService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$XLxw3JZVQkeAhLRk4FbIyVxL43U
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$0$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(FeatureQuery.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$AI6cMCLQIA3bXMedz5YRMbLLCeI
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$1$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(PersonIdProvider.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$Ym2Io3xByzDNwzlakchZTIwtpUs
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$2$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(IdentityService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$FuhfXN-QBWL5cmjx6PnPX4P1F0M
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$3$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(MainActivityLifecycleObserverRegistrar.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$72eQA9zur5TIeD_GZoAOl3RuJlg
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$4$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(ApplicationLifecycleService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$-EsNe7svTEk2lBMgAMuQpIs460M
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$5$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(MarketplaceService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$WedynMx5YqPuoX55j3YrGbU51Cc
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$6$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(com.amazon.alexa.protocols.marketplace.MarketplaceService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$69JvsD87SuClup2cUnS-Tzbn3CY
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$7$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(CrashMetadata.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$GFlNT8J7HabUcqwZVhwZORq8lpk
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$8$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(CrashObserverRegistrar.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$h31o2J7q1T6mGEFOU3kdLhIKQDo
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$9$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(CrashReporter.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$R4AXeFwRNY2hrtS9wZJCh3U4vU4
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$10$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(MetricsService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$r7W8OxKFShk4B1kolVyQ9mQPcTg
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$11$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(ModeService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$5p2_y1zok1y3ftZ4B-ZblDQJ0tA
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$12$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(MetricsServiceV2.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$juEmCLyBEYUFR9hXDfoUi5pIjC8
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$13$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(Mobilytics.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$zxdeKxKF7c-QQ31hoOuoM8sQmLc
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$14$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(LocationService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$Y_hgfiPODfTUe8d5Rw_1CirqJvI
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$15$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(CoralService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$AN5T86Qz5JZQloR5-dukcUDgFA4
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$16$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(JsonConverter.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$TDN2PLl2hnS0N7lP1a9WcYpyyIs
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$17$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(PersistentStorage.Factory.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$pa2FNzZAwW0X5j6lBRkc9bOo1cA
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$18$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(RoutingService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$Se93uArpUwxGjgcUUXIXMZWU248
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$19$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(TTCFCheckpoint.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$RJP59ThVOvgegWlJe5hnlbGKAAU
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$20$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(CertificateReaderService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$DUsRdLHukiCur-5tbC_R1zC6jmg
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$21$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(CommsServiceV2.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$8ARyBR4HWoRQpsZjPnRLBZrCbHE
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$22$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(FeatureServiceV2.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$JVgWTrx8zEfuNozABeMNK5hOu-8
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$23$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(AssetManagementService.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$8lFsDbXh0GAOX59FCIFn0hjQQhc
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$24$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(ElementLocalStorage.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$_OeWREi-ERJ9F2INJUbiSmgBuhI
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$25$ComponentBinderImpl(context);
            }
        }, hashMap);
        addDaggerBinding(DialogBuilderProvider.class, new ComponentRegistry.ConcreteComponentFactory() { // from class: com.amazon.dee.app.services.export.-$$Lambda$ComponentBinderImpl$_WSXIuDYhnNmUdAic2SCJK5obdI
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            public final Object create(Context context) {
                return ComponentBinderImpl.this.lambda$publishTransitionalObjectsOwnedByDagger$26$ComponentBinderImpl(context);
            }
        }, hashMap);
        return hashMap;
    }

    @Override // com.amazon.dee.app.services.export.ComponentBinder
    public Map<Class, String> registerComponents(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put(AlexaCommsCoreFeatureService.class, "com.amazon.deecomms.commscore.AlexaCommsCoreFeatureServiceImpl");
        hashMap.put(AlexaCommsCoreIdentityService.class, "com.amazon.deecomms.commscore.AlexaCommsCoreIdentityServiceImpl");
        hashMap.put(AlexaCommsCoreMetricsService.class, "com.amazon.deecomms.commscore.AlexaCommsCoreMetricsServiceImpl");
        hashMap.put(AlexaCommsCoreRemoteConfigurationService.class, "com.amazon.deecomms.commscore.AlexaCommsCoreRemoteConfigurationServiceImpl");
        hashMap.put(AlexaFitnessManager.class, "com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl");
        hashMap.put(AlexaTarazedService.class, "com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService");
        hashMap.put(AudioPersonalizationManager.class, "com.amazon.alexa.audiopersonalization.service.AudioPersonalizationManagerImpl");
        hashMap.put(BlueprintsEndpoint.class, "com.amazon.alexa.blueprints.BlueprintsEndpointImpl");
        hashMap.put(BluetoothProfileWatcher.class, "com.amazon.alexa.accessory.avsclient.context.SingleBluetoothProfileWatcher");
        hashMap.put(CoachMarkFactory.class, "com.amazon.alexa.growth.CoachMarkFactoryImpl");
        hashMap.put(CommsBridgeService.class, "com.amazon.commscore.commsbridge.dependencies.CommsBridgeServiceWrapper");
        hashMap.put(ConnectedAccessoryInquirer.class, "com.amazon.alexa.accessorykit.AccessoryPresenceNotifier");
        hashMap.put(DeviceInformation.class, "com.amazon.alexa.device.DefaultDeviceInformation");
        hashMap.put(ElementsViewFactory.class, "com.amazon.dee.app.elements.DefaultElementsViewFactory");
        hashMap.put(DevicePowerMonitor.class, "com.amazon.alexa.device.AndroidBatteryMetrics");
        hashMap.put(DeviceProvisioningCoordinator.class, "com.amazon.alexa.devicesetup.impl.DefaultDeviceProvisioningCoordinator");
        hashMap.put(DeviceSupplier.class, "com.amazon.alexa.accessory.persistence.device.DeviceDatabase");
        hashMap.put(DnssdService.class, "com.amazon.alexa.dnssd.DefaultDnssdService");
        hashMap.put(EchoSetupHandler.class, "com.amazon.alexa.devicesetup.softap.handler.EchoSoftAPWorkflowHandler");
        hashMap.put(DriveModeService.class, "com.amazon.alexa.drive.service.DefaultDriveModeService");
        hashMap.put(EventBus.class, "com.amazon.alexa.eventbus.core.LocalEventBus");
        hashMap.put(FeatureStore.class, "com.amazon.alexa.feature.provider.DefaultFeatureStore");
        hashMap.put(HHOService.class, "com.amazon.alexa.hho.api.HHOServiceImpl");
        hashMap.put(HttpClient.class, "com.amazon.dee.app.services.network.DefaultHttpClient");
        hashMap.put(ICallingAPI.class, "com.amazon.deecomms.calling.impl.CallingAPI");
        hashMap.put(ImageLoader.class, "com.amazon.alexa.imageloader.GlideImageLoader");
        hashMap.put(JobIDProvider.class, "com.amazon.dee.app.jobs.StaticJobIDProvider");
        hashMap.put(LatencyInfra.class, "com.amazon.latencyinfra.DefaultLatencyInfra");
        hashMap.put(LifecycleManager.class, "com.amazon.alexa.lifecycle.DefaultLifecycleManager");
        hashMap.put(LocationSensorAccess.class, "com.amazon.alexa.sensor.location.LocationSensorAccessor");
        hashMap.put(LocationSkillsService.class, "com.amazon.alexa.location.provider.DefaultLocationSkillsService");
        hashMap.put(NetworkService.class, "com.amazon.dee.app.services.network.DefaultNetworkService");
        hashMap.put(PresenceService.class, "com.amazon.alexa.presence.PresenceLifecycleManager");
        hashMap.put(RoutingRegistry.class, "com.amazon.alexa.routing.DefaultRoutingRegistry");
        hashMap.put(RoutingRegistryAdapter.class, "com.amazon.alexa.routing.DefaultRoutingRegistryAdapter");
        hashMap.put(ScoPrioritizer.class, "com.amazon.alexa.accessory.avsclient.IfAnyScoPrioritizer");
        hashMap.put(SensorAccessMetricsRecorder.class, "com.amazon.alexa.sensor.metrics.SensorAccessMobilyticsRecorder");
        hashMap.put(SessionSupplier.class, "com.amazon.alexa.accessory.internal.session.DefaultSessionSupplier");
        hashMap.put(SharingClient.class, "com.amazon.alexa.sharing.comms.dependencies.AlexaSharingClientComponentWrapper");
        hashMap.put(TaskManager.class, "com.amazon.alexa.tasks.BackgroundTaskManager");
        ComponentRegistry init = ComponentRegistry.init(context);
        for (Map.Entry entry : hashMap.entrySet()) {
            init.bind((Class) entry.getKey(), (String) entry.getValue());
        }
        return hashMap;
    }
}
