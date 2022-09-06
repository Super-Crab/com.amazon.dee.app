package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.repositories.calling.CallingRepository;
import com.amazon.alexa.accessory.repositories.cbl.CblRepository;
import com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository;
import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeRepository;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsRepository;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository;
import com.amazon.alexa.accessory.repositories.inputevents.InputRepository;
import com.amazon.alexa.accessory.repositories.media.MediaRepository;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingRepository;
import com.amazon.alexa.accessory.repositories.notification.NotificationRepository;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.amazon.alexa.accessory.repositories.system.SystemRepository;
import com.amazon.alexa.accessory.repositories.transport.TransportRepository;
import com.amazon.alexa.accessoryclient.common.api.ConnectionStatus;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessorySession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u001dH&J\b\u0010\u001e\u001a\u00020\u001fH&J\b\u0010 \u001a\u00020!H&J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H&J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020&0#H&J\b\u0010'\u001a\u00020(H&¨\u0006)"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", "", "getCallingRepository", "Lcom/amazon/alexa/accessory/repositories/calling/CallingRepository;", "getCblRepository", "Lcom/amazon/alexa/accessory/repositories/cbl/CblRepository;", "getCloudPairingRepository", "Lcom/amazon/alexa/accessory/repositories/cloudpairing/CloudPairingRepository;", "getDeviceRepository", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceRepositoryV2;", "getDiagnosticsRepository", "Lcom/amazon/alexa/accessory/repositories/diagnostics/DiagnosticsRepository;", "getFirmwareRepository", "Lcom/amazon/alexa/accessory/repositories/firmware/FirmwareRepositoryV2;", "getFitnessRepository", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessRepository;", "getHearingEnhancementRepository", "Lcom/amazon/alexa/accessory/repositories/hearing/HearingEnhancementRepository;", "getInputRepository", "Lcom/amazon/alexa/accessory/repositories/inputevents/InputRepository;", "getKeyExchangeRepository", "Lcom/amazon/alexa/accessory/repositories/crypto/KeyExchangeRepository;", "getMediaRepository", "Lcom/amazon/alexa/accessory/repositories/media/MediaRepository;", "getNonHfpCallingRepository", "Lcom/amazon/alexa/accessory/repositories/nonhfpcalling/NonHfpCallingRepository;", "getNotificationRepository", "Lcom/amazon/alexa/accessory/repositories/notification/NotificationRepository;", "getStateRepository", "Lcom/amazon/alexa/accessory/repositories/state/StateRepository;", "getSystemRepository", "Lcom/amazon/alexa/accessory/repositories/system/SystemRepository;", "getTransportRepository", "Lcom/amazon/alexa/accessory/repositories/transport/TransportRepository;", "queryConnectedAccessory", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/Accessory;", "queryConnectionStatus", "Lcom/amazon/alexa/accessoryclient/common/api/ConnectionStatus;", "release", "Lio/reactivex/rxjava3/core/Completable;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AccessorySession {
    @NotNull
    CallingRepository getCallingRepository();

    @NotNull
    CblRepository getCblRepository();

    @NotNull
    CloudPairingRepository getCloudPairingRepository();

    @NotNull
    DeviceRepositoryV2 getDeviceRepository();

    @NotNull
    DiagnosticsRepository getDiagnosticsRepository();

    @NotNull
    FirmwareRepositoryV2 getFirmwareRepository();

    @NotNull
    FitnessRepository getFitnessRepository();

    @NotNull
    HearingEnhancementRepository getHearingEnhancementRepository();

    @NotNull
    InputRepository getInputRepository();

    @NotNull
    KeyExchangeRepository getKeyExchangeRepository();

    @NotNull
    MediaRepository getMediaRepository();

    @NotNull
    NonHfpCallingRepository getNonHfpCallingRepository();

    @NotNull
    NotificationRepository getNotificationRepository();

    @NotNull
    StateRepository getStateRepository();

    @NotNull
    SystemRepository getSystemRepository();

    @NotNull
    TransportRepository getTransportRepository();

    @NotNull
    Single<Accessory> queryConnectedAccessory();

    @NotNull
    Single<ConnectionStatus> queryConnectionStatus();

    @NotNull
    Completable release();
}
