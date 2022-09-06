package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.CloudPairingSeedRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.ReplaceCloudPairingKeysRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetCloudPairingKeysRequest;
import com.amazon.alexa.accessoryclient.common.query.response.CloudPairingAttributesResponse;
import com.amazon.alexa.accessoryclient.common.query.response.CloudPairingStatusResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientCloudPairingRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientCloudPairingRepository;", "Lcom/amazon/alexa/accessory/repositories/cloudpairing/CloudPairingRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "getCloudPairingAttributes", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/protocol/Cloudpairing$CloudPairingAttributes;", "getCloudPairingStatus", "Lcom/amazon/alexa/accessory/protocol/Cloudpairing$CloudPairingStatus;", "seed", "Lcom/amazon/alexa/accessory/protocol/Cloudpairing$Seed;", "removeCloudPairingKeys", "Lio/reactivex/rxjava3/core/Completable;", "replaceCloudPairingKeys", QuickTimeAtomTypes.ATOM_KEYS, "Lcom/amazon/alexa/accessory/protocol/Cloudpairing$CloudPairingKeys;", "setCloudPairingKeys", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientCloudPairingRepository implements CloudPairingRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientCloudPairingRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    @NotNull
    public Single<Cloudpairing.CloudPairingAttributes> getCloudPairingAttributes() {
        Single<Cloudpairing.CloudPairingAttributes> map = this.client.createSingle(ApiIdentifier.GET_CLOUD_PAIRING_ATTRIBUTES, new QuerySessionRequest(this.identifier), CloudPairingAttributesResponse.Transformer.INSTANCE).map(ClientCloudPairingRepository$getCloudPairingAttributes$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   ….cloudPairingAttributes }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    @NotNull
    public Single<Cloudpairing.CloudPairingStatus> getCloudPairingStatus(@NotNull Cloudpairing.Seed seed) {
        Intrinsics.checkParameterIsNotNull(seed, "seed");
        Single<Cloudpairing.CloudPairingStatus> map = this.client.createSingle(ApiIdentifier.GET_CLOUD_PAIRING_STATUS, new CloudPairingSeedRequest(this.identifier, seed), CloudPairingStatusResponse.Transformer.INSTANCE).map(ClientCloudPairingRepository$getCloudPairingStatus$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …{ it.cloudPairingStatus }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    @NotNull
    public Completable removeCloudPairingKeys(@NotNull Cloudpairing.Seed seed) {
        Intrinsics.checkParameterIsNotNull(seed, "seed");
        return this.client.createCompletable(ApiIdentifier.REMOVE_CLOUD_PAIRING_KEYS, new CloudPairingSeedRequest(this.identifier, seed));
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    @NotNull
    public Completable replaceCloudPairingKeys(@NotNull Cloudpairing.Seed seed, @NotNull Cloudpairing.CloudPairingKeys keys) {
        Intrinsics.checkParameterIsNotNull(seed, "seed");
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        return this.client.createCompletable(ApiIdentifier.REPLACE_CLOUD_PAIRING_KEYS, new ReplaceCloudPairingKeysRequest(this.identifier, seed, keys));
    }

    @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository
    @NotNull
    public Completable setCloudPairingKeys(@NotNull Cloudpairing.CloudPairingKeys keys) {
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        return this.client.createCompletable(ApiIdentifier.SET_CLOUD_PAIRING_KEYS, new SetCloudPairingKeysRequest(this.identifier, keys));
    }
}
