package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.StateRequest;
import com.amazon.alexa.accessoryclient.common.query.response.StateResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientStateRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientStateRepository;", "Lcom/amazon/alexa/accessory/repositories/state/StateRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", SearchIntents.EXTRA_QUERY, "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/amazon/alexa/accessory/protocol/StateOuterClass$State;", "stateFeature", "Lcom/amazon/alexa/accessory/repositories/state/StateFeature;", "set", "Lio/reactivex/rxjava3/core/Completable;", "state", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientStateRepository implements StateRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientStateRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.state.StateRepository
    @NotNull
    public Flowable<StateOuterClass.State> query(@NotNull StateFeature stateFeature) {
        Intrinsics.checkParameterIsNotNull(stateFeature, "stateFeature");
        RxIPCClient rxIPCClient = this.client;
        ApiIdentifier apiIdentifier = ApiIdentifier.QUERY_STATE;
        StateOuterClass.State mo10084build = StateOuterClass.State.newBuilder().setFeature(stateFeature.toInteger()).mo10084build();
        Intrinsics.checkExpressionValueIsNotNull(mo10084build, "StateOuterClass.State.ne…                 .build()");
        Flowable<StateOuterClass.State> map = rxIPCClient.create(apiIdentifier, new StateRequest(mo10084build, this.identifier), StateResponse.Transformer.INSTANCE).toFlowable(BackpressureStrategy.BUFFER).map(ClientStateRepository$query$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…        .map { it.state }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.state.StateRepository
    @NotNull
    public Completable set(@NotNull StateOuterClass.State state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        return this.client.createCompletable(ApiIdentifier.SET_STATE, new StateRequest(state, this.identifier));
    }
}
