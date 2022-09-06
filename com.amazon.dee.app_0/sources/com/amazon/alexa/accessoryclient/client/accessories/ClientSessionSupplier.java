package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessoryclient.common.api.AccessoryTransportChange;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.AccessoryRequest;
import com.amazon.alexa.accessoryclient.common.query.request.CreateSessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.IdentityRequest;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryListResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryResponse;
import com.amazon.alexa.accessoryclient.common.query.response.TransportChangedResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.amazon.alexa.accessorykit.ModelTransformer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientSessionSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\bH\u0016J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00130\bH\u0016J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\bH\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001bH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001bH\u0016J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001bH\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001bH\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001bH\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\""}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientSessionSupplier;", "Lcom/amazon/alexa/accessoryclient/client/accessories/SessionSupplier;", "rxIPCClient", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "getRxIPCClient", "()Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "createAndConnectSession", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "createAndConnectSessionAwaitConnection", "Lio/reactivex/rxjava3/core/Completable;", "createAndConnectSessionWithOptions", "options", "Lcom/amazon/alexa/accessory/AccessorySessionOptions;", "createAndConnectSessionWithOptionsAwaitConnection", "getActiveAccessories", "", "getActiveSessions", "getSession", "identifier", "", "hasActiveSessions", "", "observeSessionConnected", "Lio/reactivex/rxjava3/core/Observable;", "observeSessionCreated", "observeSessionDisconnected", "observeSessionFailed", "observeSessionReleased", "observeSessionTransportChanged", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryTransportChange;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientSessionSupplier implements SessionSupplier {
    @NotNull
    private final RxIPCClient rxIPCClient;

    public ClientSessionSupplier(@NotNull RxIPCClient rxIPCClient) {
        Intrinsics.checkParameterIsNotNull(rxIPCClient, "rxIPCClient");
        this.rxIPCClient = rxIPCClient;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Single<AccessorySession> createAndConnectSession(@NotNull final Accessory accessory) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Single<AccessorySession> single = this.rxIPCClient.createCompletable(ApiIdentifier.CREATE_AND_CONNECT_SESSION, new AccessoryRequest(accessory)).toSingle(new Supplier<AccessorySession>() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientSessionSupplier$createAndConnectSession$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            @NotNull
            /* renamed from: get */
            public final AccessorySession mo10365get() {
                String address = accessory.getAddress();
                Intrinsics.checkExpressionValueIsNotNull(address, "accessory.address");
                return new ClientAccessorySession(address, ClientSessionSupplier.this.getRxIPCClient());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(single, "rxIPCClient.createComple…y.address, rxIPCClient) }");
        return single;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Completable createAndConnectSessionAwaitConnection(@NotNull Accessory accessory) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        return this.rxIPCClient.createCompletable(ApiIdentifier.CREATE_AND_CONNECT_SESSION_AWAIT_CONNECTION, new AccessoryRequest(accessory));
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Single<AccessorySession> createAndConnectSessionWithOptions(@NotNull final Accessory accessory, @NotNull AccessorySessionOptions options) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(options, "options");
        Single<AccessorySession> single = this.rxIPCClient.createCompletable(ApiIdentifier.CREATE_AND_CONNECT_SESSION_WITH_OPTIONS, new CreateSessionRequest(accessory, options)).toSingle(new Supplier<AccessorySession>() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientSessionSupplier$createAndConnectSessionWithOptions$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            @NotNull
            /* renamed from: get */
            public final AccessorySession mo10365get() {
                String address = accessory.getAddress();
                Intrinsics.checkExpressionValueIsNotNull(address, "accessory.address");
                return new ClientAccessorySession(address, ClientSessionSupplier.this.getRxIPCClient());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(single, "rxIPCClient.createComple…y.address, rxIPCClient) }");
        return single;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Single<AccessorySession> createAndConnectSessionWithOptionsAwaitConnection(@NotNull final Accessory accessory, @NotNull AccessorySessionOptions options) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(options, "options");
        Single<AccessorySession> single = this.rxIPCClient.createCompletable(ApiIdentifier.CREATE_AND_CONNECT_SESSION_WITH_OPTIONS_AWAIT_CONNECTION, new CreateSessionRequest(accessory, options)).toSingle(new Supplier<AccessorySession>() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientSessionSupplier$createAndConnectSessionWithOptionsAwaitConnection$1
            @Override // io.reactivex.rxjava3.functions.Supplier
            @NotNull
            /* renamed from: get */
            public final AccessorySession mo10365get() {
                String address = accessory.getAddress();
                Intrinsics.checkExpressionValueIsNotNull(address, "accessory.address");
                return new ClientAccessorySession(address, ClientSessionSupplier.this.getRxIPCClient());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(single, "rxIPCClient.createComple…y.address, rxIPCClient) }");
        return single;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Single<List<Accessory>> getActiveAccessories() {
        Single<List<Accessory>> map = this.rxIPCClient.createSingle(ApiIdentifier.GET_ACTIVE_ACCESSORIES, new IdentityRequest(), AccessoryListResponse.Transformer.INSTANCE).map(ClientSessionSupplier$getActiveAccessories$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "rxIPCClient.createSingle….map { it.accessoryList }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Single<List<AccessorySession>> getActiveSessions() {
        Single map = getActiveAccessories().map(new Function<T, R>() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientSessionSupplier$getActiveSessions$1
            @Override // io.reactivex.rxjava3.functions.Function
            @NotNull
            /* renamed from: apply */
            public final List<ClientAccessorySession> mo10358apply(List<Accessory> accessoryList) {
                int collectionSizeOrDefault;
                Intrinsics.checkExpressionValueIsNotNull(accessoryList, "accessoryList");
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(accessoryList, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (Accessory accessory : accessoryList) {
                    String address = accessory.getAddress();
                    Intrinsics.checkExpressionValueIsNotNull(address, "it.address");
                    arrayList.add(new ClientAccessorySession(address, ClientSessionSupplier.this.getRxIPCClient()));
                }
                return arrayList;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "getActiveAccessories()\n …          }\n            }");
        return map;
    }

    @NotNull
    public final RxIPCClient getRxIPCClient() {
        return this.rxIPCClient;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public AccessorySession getSession(@NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new ClientAccessorySession(identifier, this.rxIPCClient);
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Single<Boolean> hasActiveSessions() {
        Single map = getActiveAccessories().map(ClientSessionSupplier$hasActiveSessions$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "getActiveAccessories()\n … .map { it.isNotEmpty() }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Observable<Accessory> observeSessionConnected() {
        Observable<Accessory> map = this.rxIPCClient.create(ApiIdentifier.OBSERVE_SESSION_CONNECTED, new IdentityRequest(), AccessoryResponse.Transformer.INSTANCE).map(ClientSessionSupplier$observeSessionConnected$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "rxIPCClient.create(ApiId…    .map { it.accessory }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Observable<Accessory> observeSessionCreated() {
        Observable<Accessory> map = this.rxIPCClient.create(ApiIdentifier.OBSERVE_SESSION_CREATED, new IdentityRequest(), AccessoryResponse.Transformer.INSTANCE).map(ClientSessionSupplier$observeSessionCreated$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "rxIPCClient.create(ApiId…    .map { it.accessory }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Observable<Accessory> observeSessionDisconnected() {
        Observable<Accessory> map = this.rxIPCClient.create(ApiIdentifier.OBSERVE_SESSION_DISCONNECTED, new IdentityRequest(), AccessoryResponse.Transformer.INSTANCE).map(ClientSessionSupplier$observeSessionDisconnected$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "rxIPCClient.create(ApiId…    .map { it.accessory }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Observable<Accessory> observeSessionFailed() {
        Observable<Accessory> map = this.rxIPCClient.create(ApiIdentifier.OBSERVE_SESSION_FAILED, new IdentityRequest(), AccessoryResponse.Transformer.INSTANCE).map(ClientSessionSupplier$observeSessionFailed$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "rxIPCClient.create(ApiId…    .map { it.accessory }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Observable<Accessory> observeSessionReleased() {
        Observable<Accessory> map = this.rxIPCClient.create(ApiIdentifier.OBSERVE_SESSION_RELEASED, new IdentityRequest(), AccessoryResponse.Transformer.INSTANCE).map(ClientSessionSupplier$observeSessionReleased$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "rxIPCClient.create(ApiId…    .map { it.accessory }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier
    @NotNull
    public Observable<AccessoryTransportChange> observeSessionTransportChanged() {
        Observable<AccessoryTransportChange> map = this.rxIPCClient.create(ApiIdentifier.OBSERVE_SESSION_TRANSPORT_CHANGED, new IdentityRequest(), TransportChangedResponse.Transformer.INSTANCE).map(ClientSessionSupplier$observeSessionTransportChanged$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "rxIPCClient.create(ApiId…ccessoryTransportChange }");
        return map;
    }
}
