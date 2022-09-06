package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionStringIntRequest;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceConfigurationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceFeaturesResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceInformationSetResponse;
import com.amazon.alexa.accessoryclient.common.query.response.ErrorCodeResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientDeviceRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u0016J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00100\bH\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bH\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDeviceRepository;", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceRepositoryV2;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "queryDeviceConfiguration", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceConfiguration;", "queryDeviceFeatures", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/repositories/device/DeviceFeatures;", "queryDeviceInformation", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "queryDeviceInformationSet", "", "requestCompleteSetup", "Lcom/amazon/alexa/accessory/protocol/Common$ErrorCode;", "success", "", "requestOverrideAssistant", "requestStartSetup", "requestUpdateDeviceInformation", "updatedName", "updateName", "deviceId", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientDeviceRepository implements DeviceRepositoryV2 {
    private final RxIPCClient client;
    private final String identifier;

    public ClientDeviceRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    @NotNull
    public Observable<Device.DeviceConfiguration> queryDeviceConfiguration() {
        Observable<Device.DeviceConfiguration> map = this.client.create(ApiIdentifier.QUERY_DEVICE_CONFIGURATION, new QuerySessionRequest(this.identifier), DeviceConfigurationResponse.Transformer.INSTANCE).map(ClientDeviceRepository$queryDeviceConfiguration$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif… it.deviceConfiguration }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2
    @NotNull
    public Single<DeviceFeatures> queryDeviceFeatures() {
        Single<DeviceFeatures> map = this.client.createSingle(ApiIdentifier.QUERY_DEVICE_FEATURES, new QuerySessionRequest(this.identifier), DeviceFeaturesResponse.Transformer.INSTANCE).map(ClientDeviceRepository$queryDeviceFeatures$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…map { it.deviceFeatures }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    @NotNull
    public Observable<Device.DeviceInformation> queryDeviceInformation() {
        Observable map = queryDeviceInformationSet().map(ClientDeviceRepository$queryDeviceInformation$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "queryDeviceInformationSe… { it.iterator().next() }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2
    @NotNull
    public Observable<Set<Device.DeviceInformation>> queryDeviceInformationSet() {
        Observable<Set<Device.DeviceInformation>> map = this.client.create(ApiIdentifier.QUERY_DEVICE_INFORMATION_SET, new QuerySessionRequest(this.identifier), DeviceInformationSetResponse.Transformer.INSTANCE).map(ClientDeviceRepository$queryDeviceInformationSet$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…it.deviceInformationSet }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    @NotNull
    public Single<Common.ErrorCode> requestCompleteSetup(boolean z) {
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.REQUEST_COMPLETE_SETUP, new QuerySessionBooleanRequest(this.identifier, z), ErrorCodeResponse.Transformer.INSTANCE).map(ClientDeviceRepository$requestCompleteSetup$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    @NotNull
    public Single<Common.ErrorCode> requestOverrideAssistant(boolean z) {
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.REQUEST_OVERRIDE_ASSISTANT, new QuerySessionBooleanRequest(this.identifier, z), ErrorCodeResponse.Transformer.INSTANCE).map(ClientDeviceRepository$requestOverrideAssistant$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    @NotNull
    public Single<Common.ErrorCode> requestStartSetup() {
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.REQUEST_START_SETUP, new QuerySessionRequest(this.identifier), ErrorCodeResponse.Transformer.INSTANCE).map(ClientDeviceRepository$requestStartSetup$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2
    @NotNull
    public Single<Common.ErrorCode> requestUpdateDeviceInformation(@NotNull String updateName, int i) {
        Intrinsics.checkParameterIsNotNull(updateName, "updateName");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.REQUEST_UPDATE_DEVICE_INFORMATION, new QuerySessionStringIntRequest(this.identifier, updateName, i), ErrorCodeResponse.Transformer.INSTANCE).map(ClientDeviceRepository$requestUpdateDeviceInformation$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceRepository
    @NotNull
    public Single<Common.ErrorCode> requestUpdateDeviceInformation(@NotNull String updatedName) {
        Intrinsics.checkParameterIsNotNull(updatedName, "updatedName");
        return requestUpdateDeviceInformation(updatedName, 0);
    }
}
