package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.repositories.calling.CallingRepository;
import com.amazon.alexa.accessory.repositories.cbl.CblRepository;
import com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository;
import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeRepository;
import com.amazon.alexa.accessory.repositories.device.DeviceRepository;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsRepository;
import com.amazon.alexa.accessory.repositories.display.DisplayContentRepository;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepository;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository;
import com.amazon.alexa.accessory.repositories.inputevents.InputRepository;
import com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationRepository;
import com.amazon.alexa.accessory.repositories.media.MediaRepository;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingRepository;
import com.amazon.alexa.accessory.repositories.notification.NotificationRepository;
import com.amazon.alexa.accessory.repositories.sidewalk.SidewalkRepository;
import com.amazon.alexa.accessory.repositories.speech.SpeechRepository;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.amazon.alexa.accessory.repositories.system.SystemRepository;
import com.amazon.alexa.accessory.repositories.transport.TransportRepository;
import com.amazon.alexa.accessory.transport.TransportFeaturesProvider;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
/* loaded from: classes.dex */
public interface AccessorySession {

    /* loaded from: classes.dex */
    public interface Factory {
        AccessorySession create(Accessory accessory, SessionListener sessionListener, AccessorySessionOptions accessorySessionOptions);
    }

    void connect();

    Accessory getAccessory();

    String getAddress();

    CallingRepository getCallingRepository();

    CblRepository getCblRepository();

    /* renamed from: getCloudPairingRepository */
    CloudPairingRepository mo312getCloudPairingRepository();

    Accessory getConnectedAccessory();

    DeviceRepository getDeviceRepository();

    DeviceRepositoryV2 getDeviceRepositoryV2();

    DiagnosticsRepository getDiagnosticsRepository();

    DisplayContentRepository getDisplayContentRepository();

    FeatureChecker getFeatureChecker();

    FirmwareRepository getFirmwareRepository();

    FirmwareRepositoryV2 getFirmwareRepositoryV2();

    FitnessRepository getFitnessRepository();

    HearingEnhancementRepository getHearingEnhancementRepository();

    InputRepository getInputRepository();

    InstrumentationRepository getInstrumentationRepository();

    KeyExchangeRepository getKeyExchangeRepository();

    MediaRepository getMediaRepository();

    NonHfpCallingRepository getNonHfpCallingRepository();

    NotificationRepository getNotificationRepository();

    SidewalkRepository getSidewalkRepository();

    /* renamed from: getSpeechRepository */
    SpeechRepository mo313getSpeechRepository();

    StateRepository getStateRepository();

    SystemRepository getSystemRepository();

    AccessoryTransport.Type getTransport();

    TransportFeaturesProvider getTransportFeaturesProvider();

    TransportRepository getTransportRepository();

    String getUuid();

    boolean isConnected();

    boolean isConnecting();

    void release();
}
