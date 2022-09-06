package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.IdentityRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceRegistrationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceRegistrationSetResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientRegistrationSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientRegistrationSupplier;", "Lcom/amazon/alexa/accessoryclient/client/accessories/RegistrationSupplier;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "deregister", "Lio/reactivex/rxjava3/core/Completable;", "identifier", "", "getDeviceRegistration", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/registration/DeviceRegistration;", "getOrCreateDeviceRegistration", "queryRegistrations", "Lio/reactivex/rxjava3/core/Observable;", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientRegistrationSupplier implements RegistrationSupplier {
    private final RxIPCClient client;

    public ClientRegistrationSupplier(@NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.client = client;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.RegistrationSupplier
    @NotNull
    public Completable deregister(@NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return this.client.createCompletable(ApiIdentifier.DEREGISTER, new QuerySessionRequest(identifier));
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.RegistrationSupplier
    @NotNull
    public Single<DeviceRegistration> getDeviceRegistration(@NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Single<DeviceRegistration> map = this.client.createSingle(ApiIdentifier.GET_DEVICE_REGISTRATION, new QuerySessionRequest(identifier), DeviceRegistrationResponse.Transformer.INSTANCE).map(ClientRegistrationSupplier$getDeviceRegistration$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…{ it.deviceRegistration }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.RegistrationSupplier
    @NotNull
    public Single<DeviceRegistration> getOrCreateDeviceRegistration(@NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Single<DeviceRegistration> map = this.client.createSingle(ApiIdentifier.GET_OR_CREATE_DEVICE_REGISTRATION, new QuerySessionRequest(identifier), DeviceRegistrationResponse.Transformer.INSTANCE).map(ClientRegistrationSupplier$getOrCreateDeviceRegistration$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…{ it.deviceRegistration }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.RegistrationSupplier
    @NotNull
    public Observable<Set<DeviceRegistration>> queryRegistrations() {
        Observable<Set<DeviceRegistration>> map = this.client.create(ApiIdentifier.QUERY_REGISTRATIONS, new IdentityRequest(), DeviceRegistrationSetResponse.Transformer.INSTANCE).map(ClientRegistrationSupplier$queryRegistrations$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…t.deviceRegistrationSet }");
        return map;
    }
}
