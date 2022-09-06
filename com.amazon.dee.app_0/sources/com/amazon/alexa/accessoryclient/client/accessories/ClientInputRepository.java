package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.repositories.inputevents.InputRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetInputConfigurationRequest;
import com.amazon.alexa.accessoryclient.common.query.response.InputBehaviorConfigurationSetResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientInputRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientInputRepository;", "Lcom/amazon/alexa/accessory/repositories/inputevents/InputRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "queryConfiguration", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/amazon/alexa/accessory/protocol/Input$InputBehaviorConfigurationSet;", "deviceId", "", "resetConfiguration", "Lio/reactivex/rxjava3/core/Completable;", "setConfiguration", "inputBehaviorConfiguration", "Lcom/amazon/alexa/accessory/protocol/Input$InputBehaviorConfiguration;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientInputRepository implements InputRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientInputRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputRepository
    @NotNull
    public Flowable<Input.InputBehaviorConfigurationSet> queryConfiguration(int i) {
        Flowable<Input.InputBehaviorConfigurationSet> flowable = this.client.create(ApiIdentifier.QUERY_INPUT_CONFIGURATION, new QuerySessionIntRequest(this.identifier, i), InputBehaviorConfigurationSetResponse.Transformer.INSTANCE).map(ClientInputRepository$queryConfiguration$1.INSTANCE).toFlowable(BackpressureStrategy.BUFFER);
        Intrinsics.checkExpressionValueIsNotNull(flowable, "client\n            .crea…kpressureStrategy.BUFFER)");
        return flowable;
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputRepository
    @NotNull
    public Completable resetConfiguration(int i) {
        return this.client.createCompletable(ApiIdentifier.RESET_INPUT_CONFIGURATION, new QuerySessionIntRequest(this.identifier, i));
    }

    @Override // com.amazon.alexa.accessory.repositories.inputevents.InputRepository
    @NotNull
    public Completable setConfiguration(int i, @NotNull Input.InputBehaviorConfiguration inputBehaviorConfiguration) {
        Intrinsics.checkParameterIsNotNull(inputBehaviorConfiguration, "inputBehaviorConfiguration");
        return this.client.createCompletable(ApiIdentifier.SET_INPUT_CONFIGURATION, new SetInputConfigurationRequest(this.identifier, i, inputBehaviorConfiguration));
    }
}
