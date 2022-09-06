package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataAvailableNotification;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionByteArrayRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntListRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetFitnessSessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.FitnessDataAvailableNotificationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FitnessDataSourceResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FitnessSessionUpdateMetadataResponse;
import com.amazon.alexa.accessoryclient.common.query.response.LiveFitnessDataResponse;
import com.amazon.alexa.accessoryclient.common.query.response.StopLiveFitnessDataResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientFitnessRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\rH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0016\u0010\u0019\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016J\u0016\u0010\u001d\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientFitnessRepository;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "getFitnessData", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessDataSource;", "continuationToken", "", "observeFitnessDataAvailableNotifications", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessDataAvailableNotification;", "observeFitnessSessionUpdates", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSessionUpdate;", "observeLiveFitnessDataNotifications", "Lcom/amazon/alexa/accessory/protocol/Fitness$LiveFitnessData;", "observeStopLiveFitnessDataNotifications", "Lcom/amazon/alexa/accessory/protocol/Fitness$StopLiveFitnessData;", "setFitnessSession", "Lio/reactivex/rxjava3/core/Completable;", "fitnessSession", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession;", "startLiveFitnessData", "categories", "", "", "stopLiveFitnessData", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientFitnessRepository implements FitnessRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientFitnessRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Single<FitnessDataSource> getFitnessData() {
        Single<FitnessDataSource> map = this.client.createSingle(ApiIdentifier.GET_FITNESS_DATA, new QuerySessionRequest(this.identifier), FitnessDataSourceResponse.Transformer.INSTANCE).map(ClientFitnessRepository$getFitnessData$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …tnessDataSource\n        }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Observable<FitnessDataAvailableNotification> observeFitnessDataAvailableNotifications() {
        Observable<FitnessDataAvailableNotification> map = this.client.create(ApiIdentifier.OBSERVE_FITNESS_DATA_AVAILABLE_NOTIFICATIONS, new QuerySessionRequest(this.identifier), FitnessDataAvailableNotificationResponse.Transformer.INSTANCE).map(ClientFitnessRepository$observeFitnessDataAvailableNotifications$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…taAvailableNotification }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Observable<FitnessSessionUpdate> observeFitnessSessionUpdates() {
        Observable<FitnessSessionUpdate> map = this.client.create(ApiIdentifier.OBSERVE_FITNESS_SESSION_UPDATES, new QuerySessionRequest(this.identifier), FitnessSessionUpdateMetadataResponse.Transformer.INSTANCE).map(new Function<T, R>() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientFitnessRepository$observeFitnessSessionUpdates$1
            @Override // io.reactivex.rxjava3.functions.Function
            @NotNull
            /* renamed from: apply */
            public final FitnessSessionUpdate mo10358apply(FitnessSessionUpdateMetadataResponse fitnessSessionUpdateMetadataResponse) {
                String str;
                RxIPCClient rxIPCClient;
                FitnessSessionUpdate.Origin origin = fitnessSessionUpdateMetadataResponse.getFitnessSessionUpdateMetadata().getOrigin();
                FitnessSession fitnessSession = fitnessSessionUpdateMetadataResponse.getFitnessSessionUpdateMetadata().getFitnessSession();
                str = ClientFitnessRepository.this.identifier;
                String metadataUuid = fitnessSessionUpdateMetadataResponse.getFitnessSessionUpdateMetadata().getMetadataUuid();
                rxIPCClient = ClientFitnessRepository.this.client;
                return new FitnessSessionUpdate(origin, fitnessSession, new ClientFitnessSessionUpdateOnUpdateProcessedListener(str, metadataUuid, rxIPCClient));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…          )\n            }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Observable<Fitness.LiveFitnessData> observeLiveFitnessDataNotifications() {
        Observable<Fitness.LiveFitnessData> map = this.client.create(ApiIdentifier.OBSERVE_LIVE_FITNESS_DATA_NOTIFICATIONS, new QuerySessionRequest(this.identifier), LiveFitnessDataResponse.Transformer.INSTANCE).map(ClientFitnessRepository$observeLiveFitnessDataNotifications$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…ap { it.liveFitnessData }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Observable<Fitness.StopLiveFitnessData> observeStopLiveFitnessDataNotifications() {
        Observable<Fitness.StopLiveFitnessData> map = this.client.create(ApiIdentifier.OBSERVE_STOP_LIVE_FITNESS_DATA_NOTIFICATIONS, new QuerySessionRequest(this.identifier), StopLiveFitnessDataResponse.Transformer.INSTANCE).map(ClientFitnessRepository$observeStopLiveFitnessDataNotifications$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif… it.stopLiveFitnessData }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Completable setFitnessSession(@NotNull FitnessSession fitnessSession) {
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        return this.client.createCompletable(ApiIdentifier.SET_FITNESS_SESSION, new SetFitnessSessionRequest(this.identifier, fitnessSession));
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Completable startLiveFitnessData(@NotNull List<Integer> categories) {
        Intrinsics.checkParameterIsNotNull(categories, "categories");
        return this.client.createCompletable(ApiIdentifier.START_LIVE_FITNESS_DATA, new QuerySessionIntListRequest(this.identifier, categories));
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Completable stopLiveFitnessData(@NotNull List<Integer> categories) {
        Intrinsics.checkParameterIsNotNull(categories, "categories");
        return this.client.createCompletable(ApiIdentifier.STOP_LIVE_FITNESS_DATA, new QuerySessionIntListRequest(this.identifier, categories));
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessRepository
    @NotNull
    public Single<FitnessDataSource> getFitnessData(@NotNull byte[] continuationToken) {
        Intrinsics.checkParameterIsNotNull(continuationToken, "continuationToken");
        Single<FitnessDataSource> map = this.client.createSingle(ApiIdentifier.GET_FITNESS_DATA_WITH_TOKEN, new QuerySessionByteArrayRequest(this.identifier, continuationToken), FitnessDataSourceResponse.Transformer.INSTANCE).map(ClientFitnessRepository$getFitnessData$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …tnessDataSource\n        }");
        return map;
    }
}
