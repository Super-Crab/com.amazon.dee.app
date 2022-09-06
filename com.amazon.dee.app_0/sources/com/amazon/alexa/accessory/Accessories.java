package com.amazon.alexa.accessory;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator;
import com.amazon.alexa.accessory.capabilities.calling.CallRecipient;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsObserver;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.NegotiatedDataPersistor;
import com.amazon.alexa.accessory.crypto.PersistenceBackedCryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.persistence.KeyStoreAndSQLiteBackedPersistenceLayer;
import com.amazon.alexa.accessory.crypto.persistence.UnavailableLessThanAndroidMCryptoKeyDataStore;
import com.amazon.alexa.accessory.davs.DavsClient;
import com.amazon.alexa.accessory.davs.UnavailableDavsClient;
import com.amazon.alexa.accessory.internal.DefaultAccessorySupplier;
import com.amazon.alexa.accessory.internal.SystemPeripheralConnectivity;
import com.amazon.alexa.accessory.internal.UnavailableAccessTokenProvider;
import com.amazon.alexa.accessory.internal.UnavailableBuildStageProvider;
import com.amazon.alexa.accessory.internal.UnavailableBulkDataOrchestrator;
import com.amazon.alexa.accessory.internal.UnavailableCallRecipient;
import com.amazon.alexa.accessory.internal.UnavailableInputEventsHandler;
import com.amazon.alexa.accessory.internal.UnavailableMetricsObserver;
import com.amazon.alexa.accessory.internal.UnavailableNonHfpCallController;
import com.amazon.alexa.accessory.internal.UnavailableUserSupplier;
import com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultAccessoryExplorer;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultAccessoryScanner;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultBluetoothDeviceBonder;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultCompanionDeviceModule;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultLowEnergyScanner;
import com.amazon.alexa.accessory.internal.bluetooth.spp.DefaultSppServer;
import com.amazon.alexa.accessory.internal.bluetooth.spp.DefaultSppSocketSupplier;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppSocketSupplier;
import com.amazon.alexa.accessory.internal.http.AwsNewCertificateSslSocketFactoryProvider;
import com.amazon.alexa.accessory.internal.interactor.AccessoryServiceInteractor;
import com.amazon.alexa.accessory.internal.interactor.CompanionDeviceInteractor;
import com.amazon.alexa.accessory.internal.interactor.ConnectivityInteractor;
import com.amazon.alexa.accessory.internal.interactor.DeviceAccountInteractor;
import com.amazon.alexa.accessory.internal.interactor.DeviceInteractor;
import com.amazon.alexa.accessory.internal.interactor.RegistrationInteractor;
import com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor;
import com.amazon.alexa.accessory.internal.monitor.DefaultBluetoothBondMonitor;
import com.amazon.alexa.accessory.internal.repositories.DefaultPeripheralDeviceRepository;
import com.amazon.alexa.accessory.internal.session.AccessoryInquirySession;
import com.amazon.alexa.accessory.internal.session.DefaultAccessoryInquirySessionFactory;
import com.amazon.alexa.accessory.internal.session.DefaultAccessorySessionFactory;
import com.amazon.alexa.accessory.internal.session.DefaultSessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.PrefixSanitizer;
import com.amazon.alexa.accessory.kota.DefaultKotaDownloader;
import com.amazon.alexa.accessory.kota.FileSystemFirmwareSupplier;
import com.amazon.alexa.accessory.kota.FirmwareSupplier;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.EnvironmentMetricsReporter;
import com.amazon.alexa.accessory.metrics.LoggingMetricsCollector;
import com.amazon.alexa.accessory.metrics.MetricsCollector;
import com.amazon.alexa.accessory.metrics.MetricsCollectorMetricsReporter;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.amazon.alexa.accessory.monitor.DefaultBluetoothStateMonitor;
import com.amazon.alexa.accessory.persistence.device.DeviceDatabase;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.registration.UnavailableRegistrationSupplier;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import com.amazon.alexa.accessory.registration.deviceaccount.UnavailableDeviceAccountSupplier;
import com.amazon.alexa.accessory.repositories.central.CentralSupplier;
import com.amazon.alexa.accessory.repositories.central.DefaultCentralSupplier;
import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeInteractor;
import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeInvalidator;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import com.amazon.alexa.accessory.repositories.state.MemoryPhoneStateSupplier;
import com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier;
import com.amazon.alexa.accessory.transport.DefaultTransportFactory;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessory.utils.feature.UnavailableFeatureChecker;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.io.Closer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
/* loaded from: classes.dex */
public final class Accessories {
    public static final String INTEGRATION_BROADCAST_PERMISSION = "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION";
    private static Accessories sharedInstance;
    private final AccessTokenProvider accessTokenProvider;
    private final AccessoryExplorer accessoryExplorer;
    private final CryptoKeyDataStore accessoryKeyStore;
    private final AccessoryScanner accessoryScanner;
    private final AccessoryServiceConfigurationSupplier accessoryServiceConfigurationSupplier;
    private final AccessorySupplier accessorySupplier;
    private final BluetoothBondMonitor bluetoothBondMonitor;
    private final BluetoothStateMonitor bluetoothStateMonitor;
    private final BuildStageProvider buildStageProvider;
    private final BulkDataOrchestrator bulkDataOrchestrator;
    private final Closer closer;
    private final CompanionDeviceModule companionDeviceModule;
    private final Context context;
    private final DeviceAccountSupplier deviceAccountSupplier;
    private final BluetoothDeviceBonder deviceBonder;
    private final DeviceSupplier deviceSupplier;
    private final FeatureChecker featureChecker;
    private final FirmwareSupplier firmwareSupplier;
    private final List<Interactor> interactors;
    private final KeyExchangeInvalidator keyInvalidator;
    private final KotaDownloader kotaDownloader;
    private final RegistrationSupplier registrationSupplier;
    private final AccessorySession.Factory sessionFactory;
    private final SessionSupplier sessionSupplier;
    private final SppServer sppServer;
    private final SppSocketSupplier sppSocketSupplier;
    private final UserSupplier userSupplier;

    /* loaded from: classes.dex */
    public static final class Builder {
        AccessTokenProvider accessTokenProvider;
        AccessoryAttributes accessoryAttributes;
        AccessoryExplorer accessoryExplorer;
        CryptoKeyDataStore accessoryKeyStore;
        AccessoryMetricsService accessoryMetricsService;
        AccessoryServiceConfigurationSupplier accessoryServiceConfigurationSupplier;
        AccessorySupplier accessorySupplier;
        BluetoothBondMonitor bluetoothBondMonitor;
        BluetoothStateMonitor bluetoothStateMonitor;
        BuildStageProvider buildStageProvider;
        BulkDataOrchestrator bulkDataOrchestrator;
        CallRecipient callRecipient;
        CentralSupplier centralSupplier;
        Closer closer;
        CompanionDeviceModule companionDeviceModule;
        final Context context;
        DavsClient davsClient;
        DeviceAccountSupplier deviceAccountSupplier;
        BluetoothDeviceBonder deviceBonder;
        DeviceManufacturerSupplier deviceManufacturerSupplier;
        DeviceSupplier deviceSupplier;
        DiagnosticsObserver diagnosticsObserver;
        FeatureChecker featureChecker;
        FirmwareSupplier firmwareSupplier;
        InputEventsHandler inputEventsHandler;
        AccessoryInquirySession.Factory inquirySessionFactory;
        KeyExchangeInvalidator keyInvalidator;
        KotaDownloader kotaDownloader;
        UUID lowEnergyServiceId;
        MetricsCollector metricsCollector;
        MetricsObserver metricsObserver;
        NonHfpCallController nonHfpCallController;
        PhoneStateSupplier phoneStateSupplier;
        RegistrationSupplier registrationSupplier;
        AccessoryScanner scanner;
        AccessorySession.Factory sessionFactory;
        SessionSupplier sessionSupplier;
        SpeechRecognizer speechRecognizer;
        SppServer sppServer;
        SppSocketSupplier sppSocketSupplier;
        UserSupplier userSupplier;

        public Builder(Context context) {
            Preconditions.notNull(context, "context");
            this.context = context;
        }

        public Builder accessTokenProvider(AccessTokenProvider accessTokenProvider) {
            this.accessTokenProvider = accessTokenProvider;
            return this;
        }

        public Builder accessoryAttributes(AccessoryAttributes accessoryAttributes) {
            this.accessoryAttributes = accessoryAttributes;
            return this;
        }

        public Builder accessoryExplorer(AccessoryExplorer accessoryExplorer) {
            this.accessoryExplorer = accessoryExplorer;
            return this;
        }

        public Builder accessoryKeyStore(CryptoKeyDataStore cryptoKeyDataStore) {
            this.accessoryKeyStore = cryptoKeyDataStore;
            return this;
        }

        public Builder accessoryMetricsService(AccessoryMetricsService accessoryMetricsService) {
            this.accessoryMetricsService = accessoryMetricsService;
            return this;
        }

        public Builder accessoryScanner(AccessoryScanner accessoryScanner) {
            this.scanner = accessoryScanner;
            return this;
        }

        public Builder accessoryServiceConfigurationSupplier(AccessoryServiceConfigurationSupplier accessoryServiceConfigurationSupplier) {
            this.accessoryServiceConfigurationSupplier = accessoryServiceConfigurationSupplier;
            return this;
        }

        public Builder accessorySupplier(AccessorySupplier accessorySupplier) {
            this.accessorySupplier = accessorySupplier;
            return this;
        }

        public Builder bluetoothBondMonitor(BluetoothBondMonitor bluetoothBondMonitor) {
            this.bluetoothBondMonitor = bluetoothBondMonitor;
            return this;
        }

        public Accessories build() {
            Preconditions.mainThread();
            Preconditions.notNull(this.speechRecognizer, "speechRecognizer");
            Preconditions.notNull(this.diagnosticsObserver, "diagnosticsObserver");
            if (this.closer == null) {
                this.closer = Closer.create();
            }
            if (this.accessoryServiceConfigurationSupplier == null) {
                this.accessoryServiceConfigurationSupplier = new DefaultAccessoryServiceConfigurationSupplier(this.context);
            }
            if (this.metricsCollector == null) {
                this.metricsCollector = new LoggingMetricsCollector();
            }
            EnvironmentMetricsReporter environmentMetricsReporter = new EnvironmentMetricsReporter(new MetricsCollectorMetricsReporter(this.metricsCollector));
            if (this.sessionSupplier == null) {
                this.sessionSupplier = new DefaultSessionSupplier();
            }
            if (this.callRecipient == null) {
                this.callRecipient = new UnavailableCallRecipient();
            }
            if (this.deviceSupplier == null) {
                this.deviceSupplier = new DeviceDatabase(this.context);
            }
            if (this.deviceBonder == null) {
                this.deviceBonder = new DefaultBluetoothDeviceBonder(this.context);
            }
            if (this.accessoryAttributes == null) {
                this.accessoryAttributes = new DefaultAccessoryAttributes();
            }
            if (this.lowEnergyServiceId == null) {
                this.lowEnergyServiceId = this.accessoryAttributes.lowEnergyServiceAdvertisingId();
            }
            if (this.centralSupplier == null) {
                this.centralSupplier = new DefaultCentralSupplier();
            }
            CryptoKeyDataStore cryptoKeyDataStore = this.accessoryKeyStore;
            if (cryptoKeyDataStore == null) {
                int i = Build.VERSION.SDK_INT;
                this.accessoryKeyStore = new PersistenceBackedCryptoKeyDataStore((NegotiatedDataPersistor) this.closer.register(new KeyStoreAndSQLiteBackedPersistenceLayer(this.context.getApplicationContext())), new PersistenceBackedCryptoKeyDataStore.AndroidKeyStoreCipherTransformer());
            } else if (cryptoKeyDataStore == null) {
                this.accessoryKeyStore = new UnavailableLessThanAndroidMCryptoKeyDataStore();
            }
            if (this.keyInvalidator == null) {
                this.keyInvalidator = new KeyExchangeInvalidator(this.accessoryKeyStore);
            }
            if (this.accessorySupplier == null) {
                this.accessorySupplier = new DefaultAccessorySupplier(this.deviceSupplier, this.keyInvalidator, this.sessionSupplier);
            }
            if (this.firmwareSupplier == null) {
                this.firmwareSupplier = new FileSystemFirmwareSupplier(this.context);
            }
            if (this.accessTokenProvider == null) {
                this.accessTokenProvider = new UnavailableAccessTokenProvider();
            }
            if (this.buildStageProvider == null) {
                this.buildStageProvider = new UnavailableBuildStageProvider();
            }
            if (this.userSupplier == null) {
                this.userSupplier = new UnavailableUserSupplier();
            }
            if (this.davsClient == null) {
                this.davsClient = new UnavailableDavsClient();
            }
            if (this.kotaDownloader == null) {
                Context context = this.context;
                this.kotaDownloader = new DefaultKotaDownloader(context, this.firmwareSupplier, this.userSupplier, new AwsNewCertificateSslSocketFactoryProvider(context));
            }
            if (this.metricsObserver == null) {
                this.metricsObserver = new UnavailableMetricsObserver();
            }
            if (this.registrationSupplier == null) {
                this.registrationSupplier = new UnavailableRegistrationSupplier();
            }
            if (this.phoneStateSupplier == null) {
                this.phoneStateSupplier = new MemoryPhoneStateSupplier.Builder().build();
            }
            if (this.inputEventsHandler == null) {
                this.inputEventsHandler = new UnavailableInputEventsHandler();
            }
            if (this.bulkDataOrchestrator == null) {
                this.bulkDataOrchestrator = new UnavailableBulkDataOrchestrator();
            }
            this.bluetoothStateMonitor = new DefaultBluetoothStateMonitor(this.context);
            if (this.sppServer == null) {
                this.sppServer = new DefaultSppServer(this.context, this.bluetoothStateMonitor, this.accessoryAttributes.rfcommChannelId());
            }
            if (this.sppSocketSupplier == null) {
                this.sppSocketSupplier = new DefaultSppSocketSupplier(this.context, this.sppServer);
            }
            if (this.featureChecker == null) {
                this.featureChecker = new UnavailableFeatureChecker();
            }
            if (this.deviceManufacturerSupplier == null) {
                this.deviceManufacturerSupplier = new UnavailableDeviceManufacturerSupplier();
            }
            if (this.nonHfpCallController == null) {
                this.nonHfpCallController = new UnavailableNonHfpCallController();
            }
            if (this.inquirySessionFactory == null) {
                this.inquirySessionFactory = new DefaultAccessoryInquirySessionFactory(new DefaultTransportFactory(this.context, this.lowEnergyServiceId, this.deviceBonder, this.accessoryAttributes, this.sppSocketSupplier), this.featureChecker);
            }
            if (this.sessionFactory == null) {
                this.sessionFactory = new DefaultAccessorySessionFactory.Builder(this.context).callRecipient(this.callRecipient).accessorySupplier(this.accessorySupplier).speechRecognizer(this.speechRecognizer).phoneStateSupplier(this.phoneStateSupplier).centralSupplier(this.centralSupplier).diagnosticsObserver(this.diagnosticsObserver).deviceSupplier(this.deviceSupplier).kotaDownloader(this.kotaDownloader).lowEnergyServiceId(this.lowEnergyServiceId).metricsObserver(this.metricsObserver).firmwareSupplier(this.firmwareSupplier).deviceBonder(this.deviceBonder).inputEventsHandler(this.inputEventsHandler).metricsReporter(environmentMetricsReporter).accessoryKeyStore(this.accessoryKeyStore).bulkDataOrchestrator(this.bulkDataOrchestrator).accessoryAttributes(this.accessoryAttributes).sppSocketProducer(this.sppSocketSupplier).featureChecker(this.featureChecker).nonHfpCallController(this.nonHfpCallController).davsClient(this.davsClient).deviceManufacturerSupplier(this.deviceManufacturerSupplier).build();
            }
            if (this.accessoryExplorer == null) {
                this.accessoryExplorer = new DefaultAccessoryExplorer.Builder().context(this.context).deviceSupplier(this.deviceSupplier).sessionSupplier(this.sessionSupplier).inquirySessionFactory(this.inquirySessionFactory).accessorySupplier(this.accessorySupplier).accessoryAttributes(this.accessoryAttributes).sppSocketSupplier(this.sppSocketSupplier).shouldExcludeInquirySession(this.accessoryServiceConfigurationSupplier.shouldExcludeInquirySession()).blockedNamePrefixes(this.accessoryServiceConfigurationSupplier.blockedRfcommNamePrefixes()).build();
            }
            if (this.scanner == null) {
                this.scanner = new DefaultAccessoryScanner(new DefaultLowEnergyScanner(this.context, this.lowEnergyServiceId, 60000L), this.deviceSupplier, new PrefixSanitizer("LE(-|_)"), this.accessoryExplorer);
            }
            if (this.bluetoothBondMonitor == null) {
                this.bluetoothBondMonitor = new DefaultBluetoothBondMonitor(this.context);
            }
            if (this.companionDeviceModule == null) {
                this.companionDeviceModule = new DefaultCompanionDeviceModule(this.context, this.bluetoothBondMonitor);
            }
            if (this.deviceAccountSupplier == null) {
                this.deviceAccountSupplier = new UnavailableDeviceAccountSupplier();
            }
            return new Accessories(this);
        }

        public Builder buildStageProvider(BuildStageProvider buildStageProvider) {
            this.buildStageProvider = buildStageProvider;
            return this;
        }

        public Builder bulkDataOrchestrator(BulkDataOrchestrator bulkDataOrchestrator) {
            this.bulkDataOrchestrator = bulkDataOrchestrator;
            return this;
        }

        public Builder callRecipient(CallRecipient callRecipient) {
            this.callRecipient = callRecipient;
            return this;
        }

        public Builder centralSupplier(CentralSupplier centralSupplier) {
            this.centralSupplier = centralSupplier;
            return this;
        }

        public Builder closer(Closer closer) {
            this.closer = closer;
            return this;
        }

        public Builder companionDeviceModule(CompanionDeviceModule companionDeviceModule) {
            this.companionDeviceModule = companionDeviceModule;
            return this;
        }

        public Builder davsClient(DavsClient davsClient) {
            this.davsClient = davsClient;
            return this;
        }

        public Builder deviceAccountSupplier(DeviceAccountSupplier deviceAccountSupplier) {
            this.deviceAccountSupplier = deviceAccountSupplier;
            return this;
        }

        public Builder deviceBonder(BluetoothDeviceBonder bluetoothDeviceBonder) {
            this.deviceBonder = bluetoothDeviceBonder;
            return this;
        }

        public Builder deviceManufacturerSupplier(DeviceManufacturerSupplier deviceManufacturerSupplier) {
            this.deviceManufacturerSupplier = deviceManufacturerSupplier;
            return this;
        }

        public Builder deviceSupplier(DeviceSupplier deviceSupplier) {
            this.deviceSupplier = deviceSupplier;
            return this;
        }

        public Builder diagnosticsObserver(DiagnosticsObserver diagnosticsObserver) {
            this.diagnosticsObserver = diagnosticsObserver;
            return this;
        }

        public Builder featureChecker(FeatureChecker featureChecker) {
            this.featureChecker = featureChecker;
            return this;
        }

        public Builder firmwareSupplier(FirmwareSupplier firmwareSupplier) {
            this.firmwareSupplier = firmwareSupplier;
            return this;
        }

        public Builder inputEventsHandler(InputEventsHandler inputEventsHandler) {
            this.inputEventsHandler = inputEventsHandler;
            return this;
        }

        public Builder inquirySessionFactory(AccessoryInquirySession.Factory factory) {
            this.inquirySessionFactory = factory;
            return this;
        }

        public Builder keyInvalidator(KeyExchangeInvalidator keyExchangeInvalidator) {
            this.keyInvalidator = keyExchangeInvalidator;
            return this;
        }

        public Builder kotaDownloader(KotaDownloader kotaDownloader) {
            this.kotaDownloader = kotaDownloader;
            return this;
        }

        @Deprecated
        public Builder lowEnergyServiceId(UUID uuid) {
            this.lowEnergyServiceId = uuid;
            return this;
        }

        public Builder metricsCollector(MetricsCollector metricsCollector) {
            this.metricsCollector = metricsCollector;
            return this;
        }

        public Builder metricsObserver(MetricsObserver metricsObserver) {
            this.metricsObserver = metricsObserver;
            return this;
        }

        public Builder nonHfpCallController(NonHfpCallController nonHfpCallController) {
            this.nonHfpCallController = nonHfpCallController;
            return this;
        }

        public Builder phoneStateSupplier(PhoneStateSupplier phoneStateSupplier) {
            this.phoneStateSupplier = phoneStateSupplier;
            return this;
        }

        public Builder registrationSupplier(RegistrationSupplier registrationSupplier) {
            this.registrationSupplier = registrationSupplier;
            return this;
        }

        public Builder sessionFactory(AccessorySession.Factory factory) {
            this.sessionFactory = factory;
            return this;
        }

        public Builder sessionSupplier(SessionSupplier sessionSupplier) {
            this.sessionSupplier = sessionSupplier;
            return this;
        }

        public Builder speechRecognizer(SpeechRecognizer speechRecognizer) {
            this.speechRecognizer = speechRecognizer;
            return this;
        }

        public Builder sppServer(SppServer sppServer) {
            this.sppServer = sppServer;
            return this;
        }

        public Builder sppSocketSupplier(SppSocketSupplier sppSocketSupplier) {
            this.sppSocketSupplier = sppSocketSupplier;
            return this;
        }

        public Builder userSupplier(UserSupplier userSupplier) {
            this.userSupplier = userSupplier;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class SimpleAccessoryLinkListener implements AccessoryLinkListener {
        @Override // com.amazon.alexa.accessory.AccessoryLinkListener
        public void onAccessoryLinkFailed(Accessory accessory, Throwable th) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryLinkListener
        public void onAccessoryLinked(Accessory accessory) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryLinkListener
        public void onAccessoryUnlinkFailed(Accessory accessory, Throwable th) {
        }

        @Override // com.amazon.alexa.accessory.AccessoryLinkListener
        public void onAccessoryUnlinked(Accessory accessory) {
        }
    }

    Accessories(Builder builder) {
        this.context = builder.context;
        this.bulkDataOrchestrator = builder.bulkDataOrchestrator;
        this.firmwareSupplier = builder.firmwareSupplier;
        this.kotaDownloader = builder.kotaDownloader;
        this.accessTokenProvider = builder.accessTokenProvider;
        this.deviceSupplier = builder.deviceSupplier;
        this.deviceBonder = builder.deviceBonder;
        this.sessionSupplier = builder.sessionSupplier;
        this.accessoryScanner = builder.scanner;
        this.accessoryExplorer = builder.accessoryExplorer;
        this.accessorySupplier = builder.accessorySupplier;
        this.buildStageProvider = builder.buildStageProvider;
        this.sessionFactory = builder.sessionFactory;
        this.userSupplier = builder.userSupplier;
        this.registrationSupplier = builder.registrationSupplier;
        this.accessoryServiceConfigurationSupplier = builder.accessoryServiceConfigurationSupplier;
        this.accessoryKeyStore = builder.accessoryKeyStore;
        this.keyInvalidator = builder.keyInvalidator;
        AccessoryMetricsServiceHolder.getInstance().set(builder.accessoryMetricsService);
        this.companionDeviceModule = builder.companionDeviceModule;
        this.bluetoothBondMonitor = builder.bluetoothBondMonitor;
        this.closer = builder.closer;
        this.deviceAccountSupplier = builder.deviceAccountSupplier;
        this.bluetoothStateMonitor = builder.bluetoothStateMonitor;
        this.sppServer = builder.sppServer;
        this.sppSocketSupplier = builder.sppSocketSupplier;
        this.featureChecker = builder.featureChecker;
        this.interactors = createInteractors(this.context, this.sessionFactory, this.sessionSupplier, this.accessorySupplier, this.accessoryExplorer, this.deviceSupplier, this.registrationSupplier, this.userSupplier, this.accessoryServiceConfigurationSupplier, this.companionDeviceModule, this.bluetoothBondMonitor, this.deviceAccountSupplier, this.bluetoothStateMonitor, this.featureChecker, this.keyInvalidator);
    }

    private void activateInteractors() {
        Preconditions.mainThread();
        for (Interactor interactor : this.interactors) {
            interactor.activate();
        }
    }

    private static List<Interactor> createInteractors(Context context, AccessorySession.Factory factory, SessionSupplier sessionSupplier, AccessorySupplier accessorySupplier, AccessoryExplorer accessoryExplorer, DeviceSupplierV2 deviceSupplierV2, RegistrationSupplier registrationSupplier, UserSupplier userSupplier, AccessoryServiceConfigurationSupplier accessoryServiceConfigurationSupplier, CompanionDeviceModule companionDeviceModule, BluetoothBondMonitor bluetoothBondMonitor, DeviceAccountSupplier deviceAccountSupplier, BluetoothStateMonitor bluetoothStateMonitor, FeatureChecker featureChecker, KeyExchangeInvalidator keyExchangeInvalidator) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(factory, "sessionFactory");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(accessorySupplier, "accessorySupplier");
        Preconditions.notNull(accessoryExplorer, "accessoryExplorer");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        Preconditions.notNull(registrationSupplier, "registrationSupplier");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(accessoryServiceConfigurationSupplier, "accessoryServiceConfigurationSupplier");
        Preconditions.notNull(companionDeviceModule, "companionDeviceModule");
        Preconditions.notNull(bluetoothBondMonitor, "bluetoothBondMonitor");
        Preconditions.notNull(deviceAccountSupplier, "deviceAccountSupplier");
        Preconditions.notNull(bluetoothStateMonitor, "bluetoothStateMonitor");
        Preconditions.notNull(featureChecker, "featureChecker");
        Preconditions.notNull(keyExchangeInvalidator, "keyExchangeInvalidator");
        ConnectivityInteractor connectivityInteractor = new ConnectivityInteractor(factory, sessionSupplier, accessorySupplier, accessoryExplorer, new SystemPeripheralConnectivity(new DefaultPeripheralDeviceRepository(context)), bluetoothStateMonitor, deviceSupplierV2, userSupplier, featureChecker);
        return Arrays.asList(new AccessoryServiceInteractor(sessionSupplier, context, featureChecker), new RegistrationInteractor(sessionSupplier, userSupplier, deviceSupplierV2, registrationSupplier), new CompanionDeviceInteractor(sessionSupplier, deviceSupplierV2, context, accessoryServiceConfigurationSupplier, companionDeviceModule), new DeviceInteractor(deviceSupplierV2, sessionSupplier, bluetoothBondMonitor, bluetoothStateMonitor), connectivityInteractor, new DeviceAccountInteractor(deviceAccountSupplier, registrationSupplier, sessionSupplier, userSupplier), new KeyExchangeInteractor(keyExchangeInvalidator, bluetoothBondMonitor));
    }

    @RequiresApi(api = 26)
    private void createNotificationChannel(NotificationManager notificationManager) {
        Preconditions.notNull(notificationManager, "notificationManager");
        NotificationChannel notificationChannel = new NotificationChannel(this.accessoryServiceConfigurationSupplier.lowPriorityChannelId(), this.accessoryServiceConfigurationSupplier.lowPriorityChannelName(), 1);
        notificationChannel.setDescription(this.accessoryServiceConfigurationSupplier.lowPriorityChannelDescription().toString());
        notificationChannel.setShowBadge(this.accessoryServiceConfigurationSupplier.shouldShowLowPriorityChannelBadge());
        notificationManager.createNotificationChannel(notificationChannel);
    }

    @RequiresApi(api = 26)
    private void createNotificationChannelHigh(NotificationManager notificationManager) {
        Preconditions.notNull(notificationManager, "notificationManager");
        NotificationChannel notificationChannel = new NotificationChannel(this.accessoryServiceConfigurationSupplier.highPriorityChannelId(), this.accessoryServiceConfigurationSupplier.highPriorityChannelName(), 4);
        notificationChannel.setDescription(this.accessoryServiceConfigurationSupplier.highPriorityChannelDescription().toString());
        notificationChannel.setShowBadge(this.accessoryServiceConfigurationSupplier.shouldShowHighPriorityChannelBadge());
        notificationManager.createNotificationChannel(notificationChannel);
    }

    @RequiresApi(api = 26)
    private void createNotificationsChannels(NotificationManager notificationManager) {
        Preconditions.notNull(notificationManager, "notificationManager");
        createNotificationChannel(notificationManager);
        createNotificationChannelHigh(notificationManager);
    }

    @NonNull
    public static Intent createRequestEnableBluetoothStatic() {
        return new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
    }

    @NonNull
    @SuppressLint({"BatteryLife"})
    @TargetApi(23)
    public static Intent createRequestIgnoreBatteryOptimizations(Context context) {
        Preconditions.notNull(context, "context");
        Intent action = new Intent().setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
        outline107.append(context.getPackageName());
        return action.setData(Uri.parse(outline107.toString()));
    }

    private void deactiveInteractors() {
        Preconditions.mainThread();
        for (int size = this.interactors.size() - 1; size >= 0; size--) {
            this.interactors.get(size).deactivate();
        }
    }

    @Deprecated
    public static Accessories getSharedInstance() {
        Preconditions.mainThread();
        Accessories accessories = sharedInstance;
        if (accessories != null) {
            return accessories;
        }
        throw new IllegalStateException("Shared instance of Accessories is not available!");
    }

    public static boolean hasSharedInstance() {
        Preconditions.mainThread();
        return sharedInstance != null;
    }

    private void removeSession(@NonNull Accessory accessory) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        this.sessionSupplier.removeSession(accessory);
    }

    public static boolean shouldRequestEnableBluetooth(Context context) {
        Preconditions.notNull(context, "context");
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        BluetoothAdapter adapter = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        return adapter != null && !adapter.isEnabled();
    }

    public static boolean shouldRequestIgnoreBatteryOptimizations(Context context) {
        Preconditions.notNull(context, "context");
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager != null) {
            int i = Build.VERSION.SDK_INT;
            return !powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
        }
        return false;
    }

    public void addSessionListener(@NonNull AccessorySessionListener accessorySessionListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessorySessionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.sessionSupplier.addSessionListener(accessorySessionListener);
    }

    public void bind(@NonNull Application application) {
        Preconditions.mainThread();
        Preconditions.notNull(application, "application");
        Accessories accessories = sharedInstance;
        if (accessories != null) {
            if (accessories == this) {
                return;
            }
            accessories.unbind();
        }
        sharedInstance = this;
        int i = Build.VERSION.SDK_INT;
        createNotificationsChannels((NotificationManager) this.context.getSystemService("notification"));
        activateInteractors();
    }

    @NonNull
    public Intent createRequestEnableBluetooth() {
        Preconditions.mainThread();
        return createRequestEnableBluetoothStatic();
    }

    public AccessorySession createSession(@NonNull Accessory accessory) {
        return createSession(accessory, null);
    }

    public AccessTokenProvider getAccessTokenProvider() {
        return this.accessTokenProvider;
    }

    public String getAccessoriesVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public AccessoryServiceConfigurationSupplier getAccessoryServiceConfigurationSupplier() {
        return this.accessoryServiceConfigurationSupplier;
    }

    public AccessorySupplier getAccessorySupplier() {
        return this.accessorySupplier;
    }

    @NonNull
    public List<AccessorySession> getActiveSessions() {
        Preconditions.mainThread();
        return this.sessionSupplier.getActiveSessions();
    }

    public BuildStageProvider getBuildStageProvider() {
        return this.buildStageProvider;
    }

    @NonNull
    public CompanionDeviceModule getCompanionDeviceModule() {
        return this.companionDeviceModule;
    }

    public DeviceAccountSupplier getDeviceAccountSupplier() {
        return this.deviceAccountSupplier;
    }

    public BluetoothDeviceBonder getDeviceBonder() {
        return this.deviceBonder;
    }

    public DeviceSupplier getDeviceSupplier() {
        return this.deviceSupplier;
    }

    public AccessoryExplorer getExplorer() {
        return this.accessoryExplorer;
    }

    public FirmwareSupplier getFirmwareSupplier() {
        return this.firmwareSupplier;
    }

    public KeyExchangeInvalidator getKeyInvalidator() {
        return this.keyInvalidator;
    }

    public KotaDownloader getKotaDownloader() {
        return this.kotaDownloader;
    }

    public RegistrationSupplier getRegistrationSupplier() {
        return this.registrationSupplier;
    }

    public AccessoryScanner getScanner() {
        return this.accessoryScanner;
    }

    @Nullable
    public AccessorySession getSession(@NonNull Accessory accessory) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        return this.sessionSupplier.getSession(accessory);
    }

    public AccessorySession.Factory getSessionFactory() {
        return this.sessionFactory;
    }

    public SessionSupplier getSessionSupplier() {
        return this.sessionSupplier;
    }

    public UserSupplier getUserSupplier() {
        return this.userSupplier;
    }

    public boolean hasActiveSession(@NonNull Accessory accessory) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        return this.sessionSupplier.hasActiveSession(accessory);
    }

    public boolean hasActiveSessions() {
        Preconditions.mainThread();
        return this.sessionSupplier.hasActiveSessions();
    }

    public void linkAccessory(@NonNull Accessory accessory) {
        linkAccessory(accessory, new SimpleAccessoryLinkListener());
    }

    public void release() {
        Preconditions.mainThread();
        try {
            this.closer.close();
        } catch (IOException e) {
            Logger.e("[Accessories] Error closing open resources", e);
        }
        for (AccessorySession accessorySession : getActiveSessions()) {
            accessorySession.release();
            this.sessionSupplier.removeSession(accessorySession.getAccessory());
            AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_ACCESSORIES_UNBIND, true, null);
        }
        unbind();
    }

    public void removeSessionListener(@NonNull AccessorySessionListener accessorySessionListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessorySessionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.sessionSupplier.removeSessionListener(accessorySessionListener);
    }

    public void unbind() {
        Preconditions.mainThread();
        deactiveInteractors();
        if (sharedInstance == this) {
            sharedInstance = null;
        }
    }

    public void unlinkAccessory(Accessory accessory) {
        unlinkAccessory(accessory, new SimpleAccessoryLinkListener());
    }

    public AccessorySession createSession(@NonNull Accessory accessory, SessionListener sessionListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        return this.sessionSupplier.createSession(accessory, this.sessionFactory, sessionListener);
    }

    public void linkAccessory(@NonNull Accessory accessory, @NonNull AccessoryLinkListener accessoryLinkListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(accessoryLinkListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.accessorySupplier.link(accessory, accessoryLinkListener);
    }

    public void unlinkAccessory(@NonNull Accessory accessory, @NonNull AccessoryLinkListener accessoryLinkListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(accessoryLinkListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.accessorySupplier.unlink(accessory, accessoryLinkListener);
    }

    @NonNull
    @SuppressLint({"BatteryLife"})
    @TargetApi(23)
    public Intent createRequestIgnoreBatteryOptimizations() {
        Preconditions.mainThread();
        return createRequestIgnoreBatteryOptimizations(this.context);
    }

    public AccessorySession createSession(@NonNull Accessory accessory, SessionListener sessionListener, AccessorySessionOptions accessorySessionOptions) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(accessorySessionOptions, "accessorySessionOptions");
        return this.sessionSupplier.createSession(accessory, this.sessionFactory, sessionListener, accessorySessionOptions);
    }

    public boolean shouldRequestEnableBluetooth() {
        Preconditions.mainThread();
        return shouldRequestEnableBluetooth(this.context);
    }

    public boolean shouldRequestIgnoreBatteryOptimizations() {
        Preconditions.mainThread();
        return shouldRequestIgnoreBatteryOptimizations(this.context);
    }
}
