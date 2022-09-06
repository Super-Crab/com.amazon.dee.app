package com.amazon.alexa.accessory.internal.session;

import android.content.Context;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryAttributes;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessorySupplier;
import com.amazon.alexa.accessory.DeviceManufacturerSupplier;
import com.amazon.alexa.accessory.SessionListener;
import com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator;
import com.amazon.alexa.accessory.capabilities.calling.CallRecipient;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsObserver;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.capabilities.speech.SpeechRecognizer;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.davs.DavsClient;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppSocketProducer;
import com.amazon.alexa.accessory.internal.session.DefaultAccessorySession;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.FirmwareSupplier;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.metrics.MetricsReporter;
import com.amazon.alexa.accessory.repositories.central.CentralSupplier;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier;
import com.amazon.alexa.accessory.transport.DefaultTransportFactory;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.UUID;
/* loaded from: classes.dex */
public final class DefaultAccessorySessionFactory implements AccessorySession.Factory {
    private final AccessoryAttributes accessoryAttributes;
    private final CryptoKeyDataStore accessoryKeyStore;
    private final AccessorySupplier accessorySupplier;
    private final BulkDataOrchestrator bulkDataOrchestrator;
    private final CallRecipient callRecipient;
    private final CentralSupplier centralSupplier;
    private final Context context;
    private final DavsClient davsClient;
    private final BluetoothDeviceBonder deviceBonder;
    private final DeviceManufacturerSupplier deviceManufacturerSupplier;
    private final DeviceSupplier deviceSupplier;
    private final DiagnosticsObserver diagnosticsObserver;
    private final FeatureChecker featureChecker;
    private final FirmwareSupplier firmwareSupplier;
    private final InputEventsHandler inputEventsHandler;
    private final KotaDownloader kotaDownloader;
    private final UUID lowEnergyServiceId;
    private final MetricsObserver metricsObserver;
    private final MetricsReporter metricsReporter;
    private final NonHfpCallController nonHfpCallController;
    private final PhoneStateSupplier phoneStateSupplier;
    private final SpeechRecognizer speechRecognizer;
    private final SppSocketProducer sppSocketProducer;

    /* loaded from: classes.dex */
    public static final class Builder {
        AccessoryAttributes accessoryAttributes;
        CryptoKeyDataStore accessoryKeyStore;
        AccessorySupplier accessorySupplier;
        BulkDataOrchestrator bulkDataOrchestrator;
        CallRecipient callRecipient;
        CentralSupplier centralSupplier;
        Context context;
        DavsClient davsClient;
        BluetoothDeviceBonder deviceBonder;
        DeviceManufacturerSupplier deviceManufacturerSupplier;
        DeviceSupplier deviceSupplier;
        DiagnosticsObserver diagnosticsObserver;
        FeatureChecker featureChecker;
        FirmwareSupplier firmwareSupplier;
        InputEventsHandler inputEventsHandler;
        KotaDownloader kotaDownloader;
        UUID lowEnergyServiceId;
        MetricsObserver metricsObserver;
        MetricsReporter metricsReporter;
        NonHfpCallController nonHfpCallController;
        PhoneStateSupplier phoneStateSupplier;
        SpeechRecognizer speechRecognizer;
        SppSocketProducer sppSocketProducer;

        public Builder(Context context) {
            Preconditions.notNull(context, "context");
            this.context = context;
        }

        public Builder accessoryAttributes(AccessoryAttributes accessoryAttributes) {
            this.accessoryAttributes = accessoryAttributes;
            return this;
        }

        public Builder accessoryKeyStore(CryptoKeyDataStore cryptoKeyDataStore) {
            this.accessoryKeyStore = cryptoKeyDataStore;
            return this;
        }

        public Builder accessorySupplier(AccessorySupplier accessorySupplier) {
            this.accessorySupplier = accessorySupplier;
            return this;
        }

        public DefaultAccessorySessionFactory build() {
            Preconditions.notNull(this.context, "context");
            Preconditions.notNull(this.speechRecognizer, "speechRecognizer");
            Preconditions.notNull(this.bulkDataOrchestrator, "bulkDataOrchestrator");
            Preconditions.notNull(this.firmwareSupplier, "firmwareSupplier");
            Preconditions.notNull(this.kotaDownloader, "kotaDownloader");
            Preconditions.notNull(this.deviceSupplier, "deviceSupplier");
            Preconditions.notNull(this.callRecipient, "callRecipient");
            Preconditions.notNull(this.deviceBonder, "deviceBonder");
            Preconditions.notNull(this.diagnosticsObserver, "diagnosticsObserver");
            Preconditions.notNull(this.centralSupplier, "centralSupplier");
            Preconditions.notNull(this.metricsObserver, "metricsObserver");
            Preconditions.notNull(this.accessorySupplier, "accessorySupplier");
            Preconditions.notNull(this.lowEnergyServiceId, "lowEnergyServiceId");
            Preconditions.notNull(this.phoneStateSupplier, "phoneStateSupplier");
            Preconditions.notNull(this.inputEventsHandler, "inputEventsHandler");
            Preconditions.notNull(this.metricsReporter, "metricsReporter");
            Preconditions.notNull(this.accessoryKeyStore, "accessoryKeyStore");
            Preconditions.notNull(this.accessoryAttributes, "accessoryAttributes");
            Preconditions.notNull(this.sppSocketProducer, "sppSocketProducer");
            Preconditions.notNull(this.featureChecker, "featureChecker");
            Preconditions.notNull(this.nonHfpCallController, "nonHfpCallController");
            Preconditions.notNull(this.davsClient, "davsClient");
            return new DefaultAccessorySessionFactory(this);
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

        public Builder kotaDownloader(KotaDownloader kotaDownloader) {
            this.kotaDownloader = kotaDownloader;
            return this;
        }

        public Builder lowEnergyServiceId(UUID uuid) {
            this.lowEnergyServiceId = uuid;
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

        public Builder sppSocketProducer(SppSocketProducer sppSocketProducer) {
            this.sppSocketProducer = sppSocketProducer;
            return this;
        }
    }

    public DefaultAccessorySessionFactory(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.context = builder.context;
        this.speechRecognizer = builder.speechRecognizer;
        this.bulkDataOrchestrator = builder.bulkDataOrchestrator;
        this.firmwareSupplier = builder.firmwareSupplier;
        this.kotaDownloader = builder.kotaDownloader;
        this.deviceSupplier = builder.deviceSupplier;
        this.callRecipient = builder.callRecipient;
        this.deviceBonder = builder.deviceBonder;
        this.diagnosticsObserver = builder.diagnosticsObserver;
        this.centralSupplier = builder.centralSupplier;
        this.metricsObserver = builder.metricsObserver;
        this.lowEnergyServiceId = builder.lowEnergyServiceId;
        this.accessoryAttributes = builder.accessoryAttributes;
        this.phoneStateSupplier = builder.phoneStateSupplier;
        this.accessorySupplier = builder.accessorySupplier;
        this.inputEventsHandler = builder.inputEventsHandler;
        this.metricsReporter = builder.metricsReporter;
        this.accessoryKeyStore = builder.accessoryKeyStore;
        this.sppSocketProducer = builder.sppSocketProducer;
        this.featureChecker = builder.featureChecker;
        this.nonHfpCallController = builder.nonHfpCallController;
        this.davsClient = builder.davsClient;
        this.deviceManufacturerSupplier = builder.deviceManufacturerSupplier;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession.Factory
    public AccessorySession create(Accessory accessory, SessionListener sessionListener, AccessorySessionOptions accessorySessionOptions) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(sessionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.notNull(accessorySessionOptions, "accessorySessionOptions");
        return new DefaultAccessorySession.Builder().kotaDownloader(this.kotaDownloader).speechRecognizer(this.speechRecognizer).metricsReporter(this.metricsReporter).bulkDataOrchestrator(this.bulkDataOrchestrator).accessorySupplier(this.accessorySupplier).phoneStateSupplier(this.phoneStateSupplier).centralSupplier(this.centralSupplier).firmwareSupplier(this.firmwareSupplier).callRecipient(this.callRecipient).diagnosticsObserver(this.diagnosticsObserver).inputEventsHandler(this.inputEventsHandler).metricsObserver(this.metricsObserver).nonHfpCallController(this.nonHfpCallController).davsClient(this.davsClient).deviceManufacturerSupplier(this.deviceManufacturerSupplier).accessoryKeyStore(this.accessoryKeyStore).deviceSupplier(this.deviceSupplier).accessory(accessory).sessionListener(sessionListener).accessorySessionOptions(accessorySessionOptions).featureChecker(this.featureChecker).transportFactory(new DefaultTransportFactory(this.context, this.lowEnergyServiceId, this.deviceBonder, this.accessoryAttributes, this.sppSocketProducer)).build();
    }
}
