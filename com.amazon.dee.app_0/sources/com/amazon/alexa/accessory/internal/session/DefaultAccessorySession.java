package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryCapabilities;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessorySupplier;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.DeviceManufacturerSupplier;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.Capability;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator;
import com.amazon.alexa.accessory.capabilities.calling.CallRecipient;
import com.amazon.alexa.accessory.capabilities.calling.CallingCapability;
import com.amazon.alexa.accessory.capabilities.cbl.CblCapability;
import com.amazon.alexa.accessory.capabilities.central.CentralCapability;
import com.amazon.alexa.accessory.capabilities.cloudpairing.CloudPairingCapability;
import com.amazon.alexa.accessory.capabilities.crypto.KeyExchangeCapability;
import com.amazon.alexa.accessory.capabilities.device.DeviceCapability;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsCapability;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsObserver;
import com.amazon.alexa.accessory.capabilities.display.DisplayContentCapability;
import com.amazon.alexa.accessory.capabilities.firmware.FirmwareCapability;
import com.amazon.alexa.accessory.capabilities.fitness.FitnessCapability;
import com.amazon.alexa.accessory.capabilities.hearing.HearingEnhancementCapability;
import com.amazon.alexa.accessory.capabilities.inputevents.DefaultInputEventHandler;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsCapability;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler;
import com.amazon.alexa.accessory.capabilities.instrumentation.DefaultDataFormatter;
import com.amazon.alexa.accessory.capabilities.instrumentation.InstrumentationCapability;
import com.amazon.alexa.accessory.capabilities.instrumentation.LoggerDataPrinter;
import com.amazon.alexa.accessory.capabilities.mediia.MediaCapability;
import com.amazon.alexa.accessory.capabilities.metrics.DefaultMetricsListener;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsCapability;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.capabilities.nonhfpcalling.NonHfpCallingCapability;
import com.amazon.alexa.accessory.capabilities.notification.NotificationCapability;
import com.amazon.alexa.accessory.capabilities.sidewalk.SidewalkCapability;
import com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider;
import com.amazon.alexa.accessory.capabilities.speech.SessionIdentifierProvider;
import com.amazon.alexa.accessory.capabilities.speech.SpeechRecognitionCapability;
import com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer;
import com.amazon.alexa.accessory.capabilities.state.StateCapability;
import com.amazon.alexa.accessory.capabilities.system.SystemCapability;
import com.amazon.alexa.accessory.capabilities.transport.TransportCapability;
import com.amazon.alexa.accessory.davs.DavsClient;
import com.amazon.alexa.accessory.internal.session.BaseAccessorySession;
import com.amazon.alexa.accessory.internal.session.SessionSystemCallback;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.FirmwareSupplier;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.metrics.MetricsReporter;
import com.amazon.alexa.accessory.metrics.SessionInformationMetricsReporter;
import com.amazon.alexa.accessory.repositories.calling.CallingRepository;
import com.amazon.alexa.accessory.repositories.calling.MemoryCallingRepository;
import com.amazon.alexa.accessory.repositories.cbl.CblRepository;
import com.amazon.alexa.accessory.repositories.cbl.MemoryCblRepository;
import com.amazon.alexa.accessory.repositories.central.CentralSupplier;
import com.amazon.alexa.accessory.repositories.cloudpairing.MemoryCloudPairingRepository;
import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeRepository;
import com.amazon.alexa.accessory.repositories.crypto.MemoryKeyExchangeRepository;
import com.amazon.alexa.accessory.repositories.device.DeviceRepository;
import com.amazon.alexa.accessory.repositories.device.MemoryDeviceRepository;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsRepository;
import com.amazon.alexa.accessory.repositories.diagnostics.MemoryDiagnosticsRepository;
import com.amazon.alexa.accessory.repositories.display.DisplayContentRepository;
import com.amazon.alexa.accessory.repositories.display.MemoryDisplayContentRepository;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepository;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.amazon.alexa.accessory.repositories.firmware.MemoryFirmwareRepository;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessory.repositories.fitness.MemoryFitnessRepository;
import com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository;
import com.amazon.alexa.accessory.repositories.hearing.MemoryHearingEnhancementRepository;
import com.amazon.alexa.accessory.repositories.inputevents.InputRepository;
import com.amazon.alexa.accessory.repositories.inputevents.MemoryInputRepository;
import com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationRepository;
import com.amazon.alexa.accessory.repositories.instrumentation.MemoryInstrumentationRepository;
import com.amazon.alexa.accessory.repositories.media.MediaRepository;
import com.amazon.alexa.accessory.repositories.media.MemoryMediaRepository;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.MemoryNonHfpCallingRepository;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingRepository;
import com.amazon.alexa.accessory.repositories.notification.MemoryNotificationRepository;
import com.amazon.alexa.accessory.repositories.notification.NotificationRepository;
import com.amazon.alexa.accessory.repositories.sidewalk.MemorySidewalkRepository;
import com.amazon.alexa.accessory.repositories.sidewalk.SidewalkRepository;
import com.amazon.alexa.accessory.repositories.speech.MemorySpeechRepository;
import com.amazon.alexa.accessory.repositories.state.MemoryStateRepository;
import com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.amazon.alexa.accessory.repositories.system.MemorySystemRepository;
import com.amazon.alexa.accessory.repositories.system.SystemRepository;
import com.amazon.alexa.accessory.repositories.transport.MemoryTransportRepository;
import com.amazon.alexa.accessory.repositories.transport.TransportRepository;
import com.amazon.alexa.accessory.transport.MemoryTransportFeaturesRepository;
import com.amazon.alexa.accessory.transport.TransportFeaturesProvider;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class DefaultAccessorySession extends BaseAccessorySession {
    private final MemoryCallingRepository callingRepository;
    private final MemoryCblRepository cblRepository;
    private final MemoryCloudPairingRepository cloudPairingRepository;
    private final MemoryDeviceRepository deviceRepository;
    private final MemoryDiagnosticsRepository diagnosticsRepository;
    private final MemoryDisplayContentRepository displayContentRepository;
    private final MemoryFirmwareRepository firmwareRepository;
    private final MemoryFitnessRepository fitnessDataRepository;
    private final MemoryHearingEnhancementRepository hearingEnhancementRepository;
    private final MemoryInputRepository inputRepository;
    private final MemoryInstrumentationRepository instrumentationRepository;
    private final MemoryKeyExchangeRepository keyExchangeRepository;
    private final MemoryMediaRepository mediaRepository;
    private final MemoryNonHfpCallingRepository nonHfpCallingRepository;
    private final MemoryNotificationRepository notificationRepository;
    private final MemorySidewalkRepository sidewalkRepository;
    private final MemorySpeechRepository speechRepository;
    private final MemoryStateRepository stateRepository;
    private final MemorySystemRepository systemRepository;
    private final MemoryTransportFeaturesRepository transportFeaturesRepository;
    private final MemoryTransportRepository transportRepository;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.internal.session.DefaultAccessorySession$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability = new int[Capability.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.SIDEWALK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.KEY_EXCHANGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.SPEECH_RECOGNITION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.BULK_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.DEVICE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.TRANSPORT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.STATE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.DIAGNOSTIC.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.CALLING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.CBL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.SYSTEM.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.CENTRAL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.METRICS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.FIRMWARE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.FITNESS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.INSTRUMENTATION.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.INPUT_EVENTS.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.DISPLAY_CONTENT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.MEDIA.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.HEARING_ENHANCEMENT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.NON_HFP_CALLING.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.NOTIFICATION.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$Capability[Capability.CLOUD_PAIRING.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder extends BaseAccessorySession.Builder<DefaultAccessorySession> {
        AccessorySupplier accessorySupplier;
        BulkDataOrchestrator bulkDataOrchestrator;
        CallRecipient callRecipient;
        CentralSupplier centralSupplier;
        DavsClient davsClient;
        DeviceManufacturerSupplier deviceManufacturerSupplier;
        DiagnosticsObserver diagnosticsObserver;
        FirmwareSupplier firmwareSupplier;
        InputEventsHandler inputEventsHandler;
        KotaDownloader kotaDownloader;
        MetricsObserver metricsObserver;
        MetricsReporter metricsReporter;
        NonHfpCallController nonHfpCallController;
        PhoneStateSupplier phoneStateSupplier;
        SpeechRecognizer speechRecognizer;
        List<Capability> supportedCapabilities;

        public Builder accessorySupplier(AccessorySupplier accessorySupplier) {
            this.accessorySupplier = accessorySupplier;
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

        public Builder davsClient(DavsClient davsClient) {
            this.davsClient = davsClient;
            return this;
        }

        public Builder deviceManufacturerSupplier(DeviceManufacturerSupplier deviceManufacturerSupplier) {
            this.deviceManufacturerSupplier = deviceManufacturerSupplier;
            return this;
        }

        public Builder diagnosticsObserver(DiagnosticsObserver diagnosticsObserver) {
            this.diagnosticsObserver = diagnosticsObserver;
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

        public Builder kotaDownloader(KotaDownloader kotaDownloader) {
            this.kotaDownloader = kotaDownloader;
            return this;
        }

        public Builder metricsObserver(MetricsObserver metricsObserver) {
            this.metricsObserver = metricsObserver;
            return this;
        }

        public Builder metricsReporter(MetricsReporter metricsReporter) {
            this.metricsReporter = metricsReporter;
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

        public Builder speechRecognizer(SpeechRecognizer speechRecognizer) {
            this.speechRecognizer = speechRecognizer;
            return this;
        }

        @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession.Builder
        public DefaultAccessorySession build() {
            Preconditions.notNull(this.accessory, ModelTransformer.KEY_ACCESSORY);
            Preconditions.notNull(this.speechRecognizer, "speechRecognizer");
            Preconditions.notNull(this.sessionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            Preconditions.notNull(this.accessorySupplier, "accessorySupplier");
            Preconditions.notNull(this.deviceSupplier, "deviceSupplier");
            Preconditions.notNull(this.centralSupplier, "centralSupplier");
            Preconditions.notNull(this.firmwareSupplier, "firmwareSupplier");
            Preconditions.notNull(this.callRecipient, "callRecipient");
            Preconditions.notNull(this.diagnosticsObserver, "diagnosticsObserver");
            Preconditions.notNull(this.metricsObserver, "metricsObserver");
            Preconditions.notNull(this.kotaDownloader, "kotaDownloader");
            Preconditions.notNull(this.transportFactory, "transportFactory");
            Preconditions.notNull(this.phoneStateSupplier, "phoneStateSupplier");
            Preconditions.notNull(this.inputEventsHandler, "inputEventsHandler");
            Preconditions.notNull(this.metricsReporter, "metricsReporter");
            Preconditions.notNull(this.accessoryKeyStore, "accessoryKeyStore");
            Preconditions.notNull(this.nonHfpCallController, "nonHfpCallController");
            return new DefaultAccessorySession(this, null);
        }
    }

    /* synthetic */ DefaultAccessorySession(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public CallingRepository getCallingRepository() {
        return this.callingRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public CblRepository getCblRepository() {
        return this.cblRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public DeviceRepository getDeviceRepository() {
        return this.deviceRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public DeviceRepositoryV2 getDeviceRepositoryV2() {
        return this.deviceRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public DiagnosticsRepository getDiagnosticsRepository() {
        return this.diagnosticsRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public DisplayContentRepository getDisplayContentRepository() {
        return this.displayContentRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    @Deprecated
    public FirmwareRepository getFirmwareRepository() {
        return this.firmwareRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public FirmwareRepositoryV2 getFirmwareRepositoryV2() {
        return this.firmwareRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public FitnessRepository getFitnessRepository() {
        return this.fitnessDataRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public HearingEnhancementRepository getHearingEnhancementRepository() {
        return this.hearingEnhancementRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public InputRepository getInputRepository() {
        return this.inputRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public InstrumentationRepository getInstrumentationRepository() {
        return this.instrumentationRepository;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public KeyExchangeRepository getKeyExchangeRepository() {
        return this.keyExchangeRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public MediaRepository getMediaRepository() {
        return this.mediaRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public NonHfpCallingRepository getNonHfpCallingRepository() {
        return this.nonHfpCallingRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public /* bridge */ /* synthetic */ NotificationRepository getNotificationRepository() {
        return super.getNotificationRepository();
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public SidewalkRepository getSidewalkRepository() {
        return this.sidewalkRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public StateRepository getStateRepository() {
        return this.stateRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public SystemRepository getSystemRepository() {
        return this.systemRepository;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public TransportFeaturesProvider getTransportFeaturesProvider() {
        return this.transportFeaturesRepository;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public TransportRepository getTransportRepository() {
        return this.transportRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    public void release() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        Logger.d("DefaultAccessorySession is being released");
        super.release();
        this.firmwareRepository.release();
        this.cblRepository.release();
        this.deviceRepository.release();
        this.stateRepository.release();
        this.inputRepository.release();
        this.systemRepository.release();
        this.mediaRepository.release();
    }

    private DefaultAccessorySession(Builder builder) {
        super(builder);
        DeviceManufacturerSupplier deviceManufacturerSupplier;
        Iterator<Capability> it2;
        NonHfpCallController nonHfpCallController;
        InputEventsHandler inputEventsHandler;
        KotaDownloader kotaDownloader;
        MetricsObserver metricsObserver;
        FirmwareSupplier firmwareSupplier;
        CentralSupplier centralSupplier;
        DavsClient davsClient;
        BulkDataOrchestrator bulkDataOrchestrator;
        Accessory accessory;
        PhoneStateSupplier phoneStateSupplier;
        DiagnosticsObserver diagnosticsObserver;
        CallRecipient callRecipient;
        AccessorySupplier accessorySupplier;
        SessionInformationMetricsReporter sessionInformationMetricsReporter;
        FeatureChecker featureChecker;
        AccessorySupplier accessorySupplier2;
        NonHfpCallController nonHfpCallController2;
        FeatureChecker featureChecker2;
        CallRecipient callRecipient2;
        AccessorySupplier accessorySupplier3;
        NonHfpCallController nonHfpCallController3;
        FeatureChecker featureChecker3;
        NonHfpCallController nonHfpCallController4;
        Preconditions.mainThread();
        DiagnosticsObserver diagnosticsObserver2 = builder.diagnosticsObserver;
        Accessory accessory2 = builder.accessory;
        AccessorySupplier accessorySupplier4 = builder.accessorySupplier;
        SpeechRecognizer speechRecognizer = builder.speechRecognizer;
        BulkDataOrchestrator bulkDataOrchestrator2 = builder.bulkDataOrchestrator;
        CallRecipient callRecipient3 = builder.callRecipient;
        CentralSupplier centralSupplier2 = builder.centralSupplier;
        FirmwareSupplier firmwareSupplier2 = builder.firmwareSupplier;
        MetricsObserver metricsObserver2 = builder.metricsObserver;
        KotaDownloader kotaDownloader2 = builder.kotaDownloader;
        PhoneStateSupplier phoneStateSupplier2 = builder.phoneStateSupplier;
        InputEventsHandler inputEventsHandler2 = builder.inputEventsHandler;
        AccessorySessionOptions accessorySessionOptions = builder.accessorySessionOptions;
        NonHfpCallController nonHfpCallController5 = builder.nonHfpCallController;
        DavsClient davsClient2 = builder.davsClient;
        BulkDataOrchestrator bulkDataOrchestrator3 = bulkDataOrchestrator2;
        FeatureChecker featureChecker4 = builder.featureChecker;
        Accessory accessory3 = accessory2;
        DeviceManufacturerSupplier deviceManufacturerSupplier2 = builder.deviceManufacturerSupplier;
        PhoneStateSupplier phoneStateSupplier3 = phoneStateSupplier2;
        this.cblRepository = new MemoryCblRepository();
        this.keyExchangeRepository = new MemoryKeyExchangeRepository();
        this.transportFeaturesRepository = new MemoryTransportFeaturesRepository();
        this.firmwareRepository = new MemoryFirmwareRepository();
        this.deviceRepository = new MemoryDeviceRepository();
        this.stateRepository = new MemoryStateRepository();
        this.diagnosticsRepository = new MemoryDiagnosticsRepository();
        this.callingRepository = new MemoryCallingRepository();
        this.systemRepository = new MemorySystemRepository();
        this.transportRepository = new MemoryTransportRepository();
        this.fitnessDataRepository = new MemoryFitnessRepository();
        this.instrumentationRepository = new MemoryInstrumentationRepository();
        this.inputRepository = new MemoryInputRepository();
        this.displayContentRepository = new MemoryDisplayContentRepository();
        this.mediaRepository = new MemoryMediaRepository();
        this.sidewalkRepository = new MemorySidewalkRepository();
        this.hearingEnhancementRepository = new MemoryHearingEnhancementRepository();
        this.nonHfpCallingRepository = new MemoryNonHfpCallingRepository();
        this.notificationRepository = new MemoryNotificationRepository();
        this.speechRepository = new MemorySpeechRepository();
        this.cloudPairingRepository = new MemoryCloudPairingRepository();
        DiagnosticsObserver diagnosticsObserver3 = diagnosticsObserver2;
        CallRecipient callRecipient4 = callRecipient3;
        AccessorySupplier accessorySupplier5 = accessorySupplier4;
        SessionInformationMetricsReporter sessionInformationMetricsReporter2 = new SessionInformationMetricsReporter(builder.metricsReporter, this.deviceRepository, this.firmwareRepository, new SessionInformationMetricsReporter.TransportSupplier() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$XTx1gJofcmmGBf5mZi9pIe3XEZ0
            @Override // com.amazon.alexa.accessory.metrics.SessionInformationMetricsReporter.TransportSupplier
            public final AccessoryTransport.Type getTransportType() {
                return DefaultAccessorySession.this.getTransport();
            }
        });
        AccessoryCapabilities capabilities = getCapabilities();
        TaskManager taskManager = getTaskManager();
        Iterator<Capability> it3 = new AccessorySessionCapabilityResolver().getRequiredCapabilities(accessorySessionOptions.getSupportedCapabilities()).iterator();
        while (it3.hasNext()) {
            Capability next = it3.next();
            switch (next.ordinal()) {
                case 0:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    capabilities.add(new KeyExchangeCapability(new AccessoryIdentifierProvider() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$gORa57KEUPI5vwBPUbheW48yGnM
                        @Override // com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider
                        public final String getIdentifier() {
                            return DefaultAccessorySession.this.getAddress();
                        }
                    }, this.keyExchangeRepository, this.transportFeaturesRepository, builder.accessoryKeyStore));
                    break;
                case 1:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    davsClient = davsClient2;
                    accessory = accessory3;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    phoneStateSupplier = phoneStateSupplier3;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    diagnosticsObserver = diagnosticsObserver3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    kotaDownloader = kotaDownloader2;
                    callRecipient = callRecipient4;
                    featureChecker = featureChecker4;
                    accessorySupplier = accessorySupplier5;
                    capabilities.add(new SpeechRecognitionCapability(taskManager, speechRecognizer, this.deviceRepository, this.firmwareRepository, this.systemRepository, new SessionIdentifierProvider() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$5wNjX0iCZKwzvz-kNvf21Ag5OEo
                        @Override // com.amazon.alexa.accessory.capabilities.speech.SessionIdentifierProvider
                        public final String getIdentifier() {
                            return DefaultAccessorySession.this.getUuid();
                        }
                    }, new AccessoryIdentifierProvider() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$gORa57KEUPI5vwBPUbheW48yGnM
                        @Override // com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider
                        public final String getIdentifier() {
                            return DefaultAccessorySession.this.getAddress();
                        }
                    }, this.speechRepository, featureChecker));
                    break;
                case 2:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    NonHfpCallController nonHfpCallController6 = nonHfpCallController5;
                    FeatureChecker featureChecker5 = featureChecker4;
                    davsClient = davsClient2;
                    accessory = accessory3;
                    accessorySupplier2 = accessorySupplier5;
                    BulkDataOrchestrator bulkDataOrchestrator4 = bulkDataOrchestrator3;
                    capabilities.add(new BulkDataCapability(taskManager, bulkDataOrchestrator4, this.deviceRepository, new AccessoryIdentifierProvider() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$gORa57KEUPI5vwBPUbheW48yGnM
                        @Override // com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider
                        public final String getIdentifier() {
                            return DefaultAccessorySession.this.getAddress();
                        }
                    }));
                    bulkDataOrchestrator = bulkDataOrchestrator4;
                    inputEventsHandler = inputEventsHandler2;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    callRecipient = callRecipient4;
                    featureChecker = featureChecker5;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    nonHfpCallController = nonHfpCallController6;
                    accessorySupplier = accessorySupplier2;
                    break;
                case 3:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController2 = nonHfpCallController5;
                    featureChecker2 = featureChecker4;
                    davsClient = davsClient2;
                    accessory = accessory3;
                    callRecipient2 = callRecipient4;
                    accessorySupplier3 = accessorySupplier5;
                    MemoryDeviceRepository memoryDeviceRepository = this.deviceRepository;
                    capabilities.add(new DeviceCapability(memoryDeviceRepository, memoryDeviceRepository, new DeviceCapability.Callback() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$W5qcJR9gcnEsaSb_LSiBuZAt0Co
                        @Override // com.amazon.alexa.accessory.capabilities.device.DeviceCapability.Callback
                        public final void onInvalidInformation() {
                            DefaultAccessorySession.this.release();
                        }
                    }));
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    callRecipient = callRecipient2;
                    featureChecker = featureChecker2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    nonHfpCallController = nonHfpCallController2;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    accessorySupplier = accessorySupplier3;
                    break;
                case 4:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController2 = nonHfpCallController5;
                    featureChecker2 = featureChecker4;
                    callRecipient2 = callRecipient4;
                    accessorySupplier3 = accessorySupplier5;
                    davsClient = davsClient2;
                    accessory = accessory3;
                    capabilities.add(new TransportCapability(this.transportRepository, accessory, this.deviceRepository, new SessionTransportUpgradeListener(this)));
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    callRecipient = callRecipient2;
                    featureChecker = featureChecker2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    nonHfpCallController = nonHfpCallController2;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    accessorySupplier = accessorySupplier3;
                    break;
                case 5:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController3 = nonHfpCallController5;
                    featureChecker3 = featureChecker4;
                    accessorySupplier2 = accessorySupplier5;
                    MemoryStateRepository memoryStateRepository = this.stateRepository;
                    PhoneStateSupplier phoneStateSupplier4 = phoneStateSupplier3;
                    capabilities.add(new StateCapability(memoryStateRepository, memoryStateRepository, phoneStateSupplier4));
                    phoneStateSupplier = phoneStateSupplier4;
                    diagnosticsObserver = diagnosticsObserver3;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    callRecipient = callRecipient4;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    nonHfpCallController = nonHfpCallController3;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker3;
                    accessorySupplier = accessorySupplier2;
                    break;
                case 6:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController3 = nonHfpCallController5;
                    featureChecker3 = featureChecker4;
                    accessorySupplier2 = accessorySupplier5;
                    DiagnosticsObserver diagnosticsObserver4 = diagnosticsObserver3;
                    capabilities.add(new DiagnosticsCapability(taskManager, this.diagnosticsRepository, new SessionDiagnosticsListener(this, diagnosticsObserver4)));
                    diagnosticsObserver = diagnosticsObserver4;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    callRecipient = callRecipient4;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    nonHfpCallController = nonHfpCallController3;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker3;
                    accessorySupplier = accessorySupplier2;
                    break;
                case 7:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController3 = nonHfpCallController5;
                    featureChecker3 = featureChecker4;
                    accessorySupplier2 = accessorySupplier5;
                    CallRecipient callRecipient5 = callRecipient4;
                    capabilities.add(new CallingCapability(this.callingRepository, callRecipient5));
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    callRecipient = callRecipient5;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    nonHfpCallController = nonHfpCallController3;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker3;
                    accessorySupplier = accessorySupplier2;
                    break;
                case 8:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController3 = nonHfpCallController5;
                    featureChecker3 = featureChecker4;
                    accessorySupplier2 = accessorySupplier5;
                    MemoryCblRepository memoryCblRepository = this.cblRepository;
                    capabilities.add(new CblCapability(memoryCblRepository, memoryCblRepository));
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    nonHfpCallController = nonHfpCallController3;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker3;
                    accessorySupplier = accessorySupplier2;
                    break;
                case 9:
                    it2 = it3;
                    nonHfpCallController3 = nonHfpCallController5;
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    featureChecker3 = featureChecker4;
                    accessorySupplier2 = accessorySupplier5;
                    SessionSystemCallback sessionSystemCallback = new SessionSystemCallback(accessorySupplier2, new SessionSystemCallback.Callback() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$nJaGrHFee9g9qUm8op6dpYD3wr4
                        @Override // com.amazon.alexa.accessory.internal.session.SessionSystemCallback.Callback
                        public final void disconnect() {
                            DefaultAccessorySession.this.release();
                        }
                    }, new SessionSystemCallback.Supplier() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$rFrTXuWVAUiHrOYqsyRoeolZ5IE
                        @Override // com.amazon.alexa.accessory.internal.session.SessionSystemCallback.Supplier
                        public final Accessory getConnectedAccessory() {
                            return DefaultAccessorySession.this.getConnectedAccessory();
                        }
                    }, this.deviceRepository);
                    MemorySystemRepository memorySystemRepository = this.systemRepository;
                    capabilities.add(new SystemCapability(sessionSystemCallback, taskManager, memorySystemRepository, memorySystemRepository, davsClient2, this.deviceRepository));
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    nonHfpCallController = nonHfpCallController3;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker3;
                    accessorySupplier = accessorySupplier2;
                    break;
                case 10:
                    it2 = it3;
                    nonHfpCallController4 = nonHfpCallController5;
                    capabilities.add(new CentralCapability(centralSupplier2));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    nonHfpCallController = nonHfpCallController4;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 11:
                    it2 = it3;
                    nonHfpCallController4 = nonHfpCallController5;
                    capabilities.add(new MetricsCapability(new DefaultMetricsListener(this, metricsObserver2)));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    nonHfpCallController = nonHfpCallController4;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 12:
                    it2 = it3;
                    nonHfpCallController4 = nonHfpCallController5;
                    MemoryFirmwareRepository memoryFirmwareRepository = this.firmwareRepository;
                    capabilities.add(new FirmwareCapability(taskManager, memoryFirmwareRepository, memoryFirmwareRepository, firmwareSupplier2, this.deviceRepository, kotaDownloader2, sessionInformationMetricsReporter2, davsClient2, featureChecker4, deviceManufacturerSupplier2, memoryFirmwareRepository));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    nonHfpCallController = nonHfpCallController4;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 13:
                    it2 = it3;
                    nonHfpCallController4 = nonHfpCallController5;
                    MemoryFitnessRepository memoryFitnessRepository = this.fitnessDataRepository;
                    capabilities.add(new FitnessCapability(taskManager, memoryFitnessRepository, memoryFitnessRepository));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    nonHfpCallController = nonHfpCallController4;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 14:
                    it2 = it3;
                    nonHfpCallController4 = nonHfpCallController5;
                    capabilities.add(new InstrumentationCapability(this.instrumentationRepository, new DefaultDataFormatter(), new LoggerDataPrinter()));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    nonHfpCallController = nonHfpCallController4;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 15:
                    MemoryInputRepository memoryInputRepository = this.inputRepository;
                    it2 = it3;
                    capabilities.add(new InputEventsCapability(memoryInputRepository, memoryInputRepository, new DefaultInputEventHandler(this, inputEventsHandler2)));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 16:
                    MemoryMediaRepository memoryMediaRepository = this.mediaRepository;
                    capabilities.add(new MediaCapability(memoryMediaRepository, memoryMediaRepository));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 17:
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    MemorySidewalkRepository memorySidewalkRepository = this.sidewalkRepository;
                    capabilities.add(new SidewalkCapability(memorySidewalkRepository, memorySidewalkRepository));
                    break;
                case 18:
                    capabilities.add(new HearingEnhancementCapability(this.hearingEnhancementRepository));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 19:
                    capabilities.add(new NonHfpCallingCapability(this.nonHfpCallingRepository, nonHfpCallController5));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 20:
                    capabilities.add(new DisplayContentCapability(this.displayContentRepository));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 21:
                    MemoryNotificationRepository memoryNotificationRepository = this.notificationRepository;
                    capabilities.add(new NotificationCapability(memoryNotificationRepository, memoryNotificationRepository));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                case 22:
                    capabilities.add(new CloudPairingCapability(this.cloudPairingRepository));
                    deviceManufacturerSupplier = deviceManufacturerSupplier2;
                    it2 = it3;
                    nonHfpCallController = nonHfpCallController5;
                    inputEventsHandler = inputEventsHandler2;
                    kotaDownloader = kotaDownloader2;
                    metricsObserver = metricsObserver2;
                    firmwareSupplier = firmwareSupplier2;
                    centralSupplier = centralSupplier2;
                    davsClient = davsClient2;
                    bulkDataOrchestrator = bulkDataOrchestrator3;
                    accessory = accessory3;
                    phoneStateSupplier = phoneStateSupplier3;
                    diagnosticsObserver = diagnosticsObserver3;
                    callRecipient = callRecipient4;
                    accessorySupplier = accessorySupplier5;
                    sessionInformationMetricsReporter = sessionInformationMetricsReporter2;
                    featureChecker = featureChecker4;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported Capability: " + next);
            }
            kotaDownloader2 = kotaDownloader;
            metricsObserver2 = metricsObserver;
            firmwareSupplier2 = firmwareSupplier;
            centralSupplier2 = centralSupplier;
            phoneStateSupplier3 = phoneStateSupplier;
            callRecipient4 = callRecipient;
            diagnosticsObserver3 = diagnosticsObserver;
            deviceManufacturerSupplier2 = deviceManufacturerSupplier;
            accessorySupplier5 = accessorySupplier;
            nonHfpCallController5 = nonHfpCallController;
            inputEventsHandler2 = inputEventsHandler;
            sessionInformationMetricsReporter2 = sessionInformationMetricsReporter;
            featureChecker4 = featureChecker;
            it3 = it2;
            accessory3 = accessory;
            davsClient2 = davsClient;
            bulkDataOrchestrator3 = bulkDataOrchestrator;
        }
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    /* renamed from: getCloudPairingRepository  reason: collision with other method in class */
    public MemoryCloudPairingRepository mo312getCloudPairingRepository() {
        return this.cloudPairingRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.BaseAccessorySession, com.amazon.alexa.accessory.AccessorySession
    /* renamed from: getSpeechRepository */
    public MemorySpeechRepository mo313getSpeechRepository() {
        return this.speechRepository;
    }
}
