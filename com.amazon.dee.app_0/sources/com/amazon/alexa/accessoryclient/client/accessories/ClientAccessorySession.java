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
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryResponse;
import com.amazon.alexa.accessoryclient.common.query.response.QueryConnectionStatusResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientAccessorySession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020>H\u0016J\b\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020DH\u0016J\b\u0010E\u001a\u00020FH\u0016J\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00020I0HH\u0016J\u000e\u0010J\u001a\b\u0012\u0004\u0012\u00020K0HH\u0016J\b\u0010L\u001a\u00020MH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientAccessorySession;", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", "identifier", "", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Ljava/lang/String;Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "callingRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientCallingRepository;", "cblRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientCblRepository;", "cloudPairingRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientCloudPairingRepository;", "deviceRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDeviceRepository;", "diagnosticsRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDiagnosticsRepository;", "firmwareRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientFirmwareRepository;", "fitnessRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientFitnessRepository;", "hearingEnhancementRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientHearingEnhancementRepository;", "inputRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientInputRepository;", "keyExchangeRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientKeyExchangeRepository;", "mediaRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientMediaRepository;", "nonHfpCallingRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientNonHfpCallingRepository;", "notificationRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientNotificationRepository;", "stateRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientStateRepository;", "systemRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientSystemRepository;", "transportRepository", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientTransportRepository;", "getCallingRepository", "Lcom/amazon/alexa/accessory/repositories/calling/CallingRepository;", "getCblRepository", "Lcom/amazon/alexa/accessory/repositories/cbl/CblRepository;", "getCloudPairingRepository", "Lcom/amazon/alexa/accessory/repositories/cloudpairing/CloudPairingRepository;", "getDeviceRepository", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceRepositoryV2;", "getDiagnosticsRepository", "Lcom/amazon/alexa/accessory/repositories/diagnostics/DiagnosticsRepository;", "getFirmwareRepository", "Lcom/amazon/alexa/accessory/repositories/firmware/FirmwareRepositoryV2;", "getFitnessRepository", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessRepository;", "getHearingEnhancementRepository", "Lcom/amazon/alexa/accessory/repositories/hearing/HearingEnhancementRepository;", "getInputRepository", "Lcom/amazon/alexa/accessory/repositories/inputevents/InputRepository;", "getKeyExchangeRepository", "Lcom/amazon/alexa/accessory/repositories/crypto/KeyExchangeRepository;", "getMediaRepository", "Lcom/amazon/alexa/accessory/repositories/media/MediaRepository;", "getNonHfpCallingRepository", "Lcom/amazon/alexa/accessory/repositories/nonhfpcalling/NonHfpCallingRepository;", "getNotificationRepository", "Lcom/amazon/alexa/accessory/repositories/notification/NotificationRepository;", "getStateRepository", "Lcom/amazon/alexa/accessory/repositories/state/StateRepository;", "getSystemRepository", "Lcom/amazon/alexa/accessory/repositories/system/SystemRepository;", "getTransportRepository", "Lcom/amazon/alexa/accessory/repositories/transport/TransportRepository;", "queryConnectedAccessory", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/Accessory;", "queryConnectionStatus", "Lcom/amazon/alexa/accessoryclient/common/api/ConnectionStatus;", "release", "Lio/reactivex/rxjava3/core/Completable;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientAccessorySession implements AccessorySession {
    private final ClientCallingRepository callingRepository;
    private final ClientCblRepository cblRepository;
    private final RxIPCClient client;
    private final ClientCloudPairingRepository cloudPairingRepository;
    private final ClientDeviceRepository deviceRepository;
    private final ClientDiagnosticsRepository diagnosticsRepository;
    private final ClientFirmwareRepository firmwareRepository;
    private final ClientFitnessRepository fitnessRepository;
    private final ClientHearingEnhancementRepository hearingEnhancementRepository;
    private final String identifier;
    private final ClientInputRepository inputRepository;
    private final ClientKeyExchangeRepository keyExchangeRepository;
    private final ClientMediaRepository mediaRepository;
    private final ClientNonHfpCallingRepository nonHfpCallingRepository;
    private final ClientNotificationRepository notificationRepository;
    private final ClientStateRepository stateRepository;
    private final ClientSystemRepository systemRepository;
    private final ClientTransportRepository transportRepository;

    public ClientAccessorySession(@NotNull String identifier, @NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.identifier = identifier;
        this.client = client;
        this.stateRepository = new ClientStateRepository(this.client, this.identifier);
        this.deviceRepository = new ClientDeviceRepository(this.client, this.identifier);
        this.firmwareRepository = new ClientFirmwareRepository(this.client, this.identifier);
        this.inputRepository = new ClientInputRepository(this.client, this.identifier);
        this.transportRepository = new ClientTransportRepository(this.client, this.identifier);
        this.systemRepository = new ClientSystemRepository(this.client, this.identifier);
        this.keyExchangeRepository = new ClientKeyExchangeRepository(this.client, this.identifier);
        this.callingRepository = new ClientCallingRepository(this.client, this.identifier);
        this.nonHfpCallingRepository = new ClientNonHfpCallingRepository(this.client, this.identifier);
        this.cblRepository = new ClientCblRepository(this.client, this.identifier);
        this.fitnessRepository = new ClientFitnessRepository(this.client, this.identifier);
        this.diagnosticsRepository = new ClientDiagnosticsRepository(this.client, this.identifier);
        this.mediaRepository = new ClientMediaRepository(this.client, this.identifier);
        this.hearingEnhancementRepository = new ClientHearingEnhancementRepository(this.client, this.identifier);
        this.notificationRepository = new ClientNotificationRepository(this.client, this.identifier);
        this.cloudPairingRepository = new ClientCloudPairingRepository(this.client, this.identifier);
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public CallingRepository getCallingRepository() {
        return this.callingRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public CblRepository getCblRepository() {
        return this.cblRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public CloudPairingRepository getCloudPairingRepository() {
        return this.cloudPairingRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public DeviceRepositoryV2 getDeviceRepository() {
        return this.deviceRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public DiagnosticsRepository getDiagnosticsRepository() {
        return this.diagnosticsRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public FirmwareRepositoryV2 getFirmwareRepository() {
        return this.firmwareRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public FitnessRepository getFitnessRepository() {
        return this.fitnessRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public HearingEnhancementRepository getHearingEnhancementRepository() {
        return this.hearingEnhancementRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public InputRepository getInputRepository() {
        return this.inputRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public KeyExchangeRepository getKeyExchangeRepository() {
        return this.keyExchangeRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public MediaRepository getMediaRepository() {
        return this.mediaRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public NonHfpCallingRepository getNonHfpCallingRepository() {
        return this.nonHfpCallingRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public NotificationRepository getNotificationRepository() {
        return this.notificationRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public StateRepository getStateRepository() {
        return this.stateRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public SystemRepository getSystemRepository() {
        return this.systemRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public TransportRepository getTransportRepository() {
        return this.transportRepository;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public Single<Accessory> queryConnectedAccessory() {
        Single<Accessory> map = this.client.createSingle(ApiIdentifier.QUERY_CONNECTED_ACCESSORY, new QuerySessionRequest(this.identifier), AccessoryResponse.Transformer.INSTANCE).map(ClientAccessorySession$queryConnectedAccessory$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client\n            .crea…    .map { it.accessory }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public Single<ConnectionStatus> queryConnectionStatus() {
        Single<ConnectionStatus> map = this.client.createSingle(ApiIdentifier.QUERY_CONNECTION_STATUS, new QuerySessionRequest(this.identifier), QueryConnectionStatusResponse.Transformer.INSTANCE).map(ClientAccessorySession$queryConnectionStatus$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client\n            .crea…p { it.connectionStatus }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessorySession
    @NotNull
    public Completable release() {
        return this.client.createCompletable(ApiIdentifier.RELEASE_SESSION, new QuerySessionRequest(this.identifier));
    }
}
