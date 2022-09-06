package com.amazon.alexa.fitness.api;

import android.content.Context;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.CreateFitnessSessionRequest;
import com.amazon.alexa.fitness.api.afits.DeleteAllFitnessSessionsRequest;
import com.amazon.alexa.fitness.api.afits.DeleteFitnessSessionRequest;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afits.GetAllFitnessSessionResponse;
import com.amazon.alexa.fitness.api.afits.GetPersonIdBackFillStatusResponse;
import com.amazon.alexa.fitness.util.AfitsHeaderUtils;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.http.CoralService;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsServiceV2;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsClientImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016J\u001e\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001bH\u0016J\u001e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\"0\u001bH\u0016J/\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010\u000e2\b\u0010%\u001a\u0004\u0018\u00010&2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020'0\u001bH\u0016¢\u0006\u0002\u0010(J\u001e\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u000e2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0016J\u0016\u0010+\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020,0\u001bH\u0016J\u0016\u0010-\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006."}, d2 = {"Lcom/amazon/alexa/fitness/api/AfitsClientImpl;", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "metricsServiceV2", "Lcom/dee/app/metrics/MetricsServiceV2;", "coralService", "Lcom/dee/app/http/HttpCoralService;", "afitsHeaderUtils", "Lcom/amazon/alexa/fitness/util/AfitsHeaderUtils;", "(Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;Lcom/dee/app/metrics/MetricsServiceV2;Lcom/dee/app/http/HttpCoralService;Lcom/amazon/alexa/fitness/util/AfitsHeaderUtils;)V", "getAfitsHeaderUtils", "()Lcom/amazon/alexa/fitness/util/AfitsHeaderUtils;", "basePathBackfill", "", "basePathWorkouts", "getComponentRegistry", "()Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "getCoralService", "()Lcom/dee/app/http/HttpCoralService;", "getMetricsServiceV2", "()Lcom/dee/app/metrics/MetricsServiceV2;", "createFitnessSession", "", "fitnessSession", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "completion", "Lcom/dee/app/http/CoralService$Callback;", "Lokhttp3/Response;", "deleteAllFitnessSessions", "deleteAllFitnessSessionsRequest", "Lcom/amazon/alexa/fitness/api/afits/DeleteAllFitnessSessionsRequest;", "deleteFitnessSession", "deleteFitnessSessionRequest", "Lcom/amazon/alexa/fitness/api/afits/DeleteFitnessSessionRequest;", "getAllFitnessSessions", "nextToken", "maxResults", "", "Lcom/amazon/alexa/fitness/api/afits/GetAllFitnessSessionResponse;", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/dee/app/http/CoralService$Callback;)V", "getFitnessSession", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "getPersonIdBackFillStatus", "Lcom/amazon/alexa/fitness/api/afits/GetPersonIdBackFillStatusResponse;", "startPersonIdBackFillProcess", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AfitsClientImpl implements AfitsClient {
    @NotNull
    private final AfitsHeaderUtils afitsHeaderUtils;
    private final String basePathBackfill;
    private final String basePathWorkouts;
    @NotNull
    private final ComponentRegistry componentRegistry;
    @NotNull
    private final HttpCoralService coralService;
    @NotNull
    private final MetricsServiceV2 metricsServiceV2;

    @Inject
    public AfitsClientImpl(@NotNull ComponentRegistry componentRegistry, @NotNull MetricsServiceV2 metricsServiceV2, @NotNull HttpCoralService coralService, @NotNull AfitsHeaderUtils afitsHeaderUtils) {
        Intrinsics.checkParameterIsNotNull(componentRegistry, "componentRegistry");
        Intrinsics.checkParameterIsNotNull(metricsServiceV2, "metricsServiceV2");
        Intrinsics.checkParameterIsNotNull(coralService, "coralService");
        Intrinsics.checkParameterIsNotNull(afitsHeaderUtils, "afitsHeaderUtils");
        this.componentRegistry = componentRegistry;
        this.metricsServiceV2 = metricsServiceV2;
        this.coralService = coralService;
        this.afitsHeaderUtils = afitsHeaderUtils;
        this.componentRegistry.bindConcreteFactory(AfitsClient.class, new ComponentRegistry.ConcreteComponentFactory<AfitsClient>() { // from class: com.amazon.alexa.fitness.api.AfitsClientImpl.1
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            @NotNull
            /* renamed from: create */
            public final AfitsClient create2(Context context) {
                return AfitsClientImpl.this;
            }
        });
        this.basePathWorkouts = "/api/fitness/workouts";
        this.basePathBackfill = "/api/fitness/backfill/workouts";
    }

    @Override // com.amazon.alexa.fitness.api.afits.AfitsClient
    public void createFitnessSession(@NotNull FitnessSession fitnessSession, @NotNull CoralService.Callback<Response> completion) {
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CreateFitnessSessionRequest createFitnessSessionRequest = new CreateFitnessSessionRequest(fitnessSession);
        String str = this.basePathWorkouts;
        AfitsHeaderUtils afitsHeaderUtils = this.afitsHeaderUtils;
        CoralService.Request request = this.coralService.request(str);
        Intrinsics.checkExpressionValueIsNotNull(request, "coralService.request(url)");
        afitsHeaderUtils.addHeaders(request).post(GsonUtilsKt.toJsonWithNulls(createFitnessSessionRequest)).asRaw().enqueue(completion);
    }

    @Override // com.amazon.alexa.fitness.api.afits.AfitsClient
    public void deleteAllFitnessSessions(@NotNull DeleteAllFitnessSessionsRequest deleteAllFitnessSessionsRequest, @NotNull CoralService.Callback<DeleteAllFitnessSessionsRequest> completion) {
        Intrinsics.checkParameterIsNotNull(deleteAllFitnessSessionsRequest, "deleteAllFitnessSessionsRequest");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AfitsHeaderUtils afitsHeaderUtils = this.afitsHeaderUtils;
        CoralService.Request request = this.coralService.request(this.basePathWorkouts);
        Intrinsics.checkExpressionValueIsNotNull(request, "coralService.request(basePathWorkouts)");
        afitsHeaderUtils.addHeaders(request).delete(GsonUtilsKt.toJsonWithNulls(deleteAllFitnessSessionsRequest)).as(DeleteAllFitnessSessionsRequest.class).enqueue(completion);
    }

    @Override // com.amazon.alexa.fitness.api.afits.AfitsClient
    public void deleteFitnessSession(@NotNull DeleteFitnessSessionRequest deleteFitnessSessionRequest, @NotNull CoralService.Callback<DeleteFitnessSessionRequest> completion) {
        Intrinsics.checkParameterIsNotNull(deleteFitnessSessionRequest, "deleteFitnessSessionRequest");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AfitsHeaderUtils afitsHeaderUtils = this.afitsHeaderUtils;
        CoralService.Request request = this.coralService.request(this.basePathWorkouts + '/' + deleteFitnessSessionRequest.getSessionId());
        Intrinsics.checkExpressionValueIsNotNull(request, "coralService.request(url)");
        afitsHeaderUtils.addHeaders(request).delete(GsonUtilsKt.toJsonWithNulls(deleteFitnessSessionRequest)).as(DeleteFitnessSessionRequest.class).enqueue(completion);
    }

    @NotNull
    public final AfitsHeaderUtils getAfitsHeaderUtils() {
        return this.afitsHeaderUtils;
    }

    @Override // com.amazon.alexa.fitness.api.afits.AfitsClient
    public void getAllFitnessSessions(@Nullable String str, @Nullable Integer num, @NotNull CoralService.Callback<GetAllFitnessSessionResponse> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        String addUrlParameters = this.afitsHeaderUtils.addUrlParameters(str, num);
        AfitsHeaderUtils afitsHeaderUtils = this.afitsHeaderUtils;
        CoralService.Request request = this.coralService.request(addUrlParameters);
        Intrinsics.checkExpressionValueIsNotNull(request, "coralService.request(url)");
        afitsHeaderUtils.addHeaders(request).get().as(GetAllFitnessSessionResponse.class).enqueue(completion);
    }

    @NotNull
    public final ComponentRegistry getComponentRegistry() {
        return this.componentRegistry;
    }

    @NotNull
    public final HttpCoralService getCoralService() {
        return this.coralService;
    }

    @Override // com.amazon.alexa.fitness.api.afits.AfitsClient
    public void getFitnessSession(@NotNull String sessionId, @NotNull CoralService.Callback<FitnessSession> completion) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AfitsHeaderUtils afitsHeaderUtils = this.afitsHeaderUtils;
        CoralService.Request request = this.coralService.request(this.basePathWorkouts + '/' + sessionId);
        Intrinsics.checkExpressionValueIsNotNull(request, "coralService.request(url)");
        afitsHeaderUtils.addHeaders(request).get().as(FitnessSession.class).enqueue(completion);
    }

    @NotNull
    public final MetricsServiceV2 getMetricsServiceV2() {
        return this.metricsServiceV2;
    }

    @Override // com.amazon.alexa.fitness.api.afits.AfitsClient
    public void getPersonIdBackFillStatus(@NotNull CoralService.Callback<GetPersonIdBackFillStatusResponse> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        String str = this.basePathBackfill;
        AfitsHeaderUtils afitsHeaderUtils = this.afitsHeaderUtils;
        CoralService.Request request = this.coralService.request(str);
        Intrinsics.checkExpressionValueIsNotNull(request, "coralService.request(url)");
        afitsHeaderUtils.addHeaders(request).get().as(GetPersonIdBackFillStatusResponse.class).enqueue(completion);
    }

    @Override // com.amazon.alexa.fitness.api.afits.AfitsClient
    public void startPersonIdBackFillProcess(@NotNull CoralService.Callback<Response> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        String str = this.basePathBackfill;
        AfitsHeaderUtils afitsHeaderUtils = this.afitsHeaderUtils;
        CoralService.Request request = this.coralService.request(str);
        Intrinsics.checkExpressionValueIsNotNull(request, "coralService.request(url)");
        afitsHeaderUtils.addHeaders(request).post("").asRaw().enqueue(completion);
    }
}
