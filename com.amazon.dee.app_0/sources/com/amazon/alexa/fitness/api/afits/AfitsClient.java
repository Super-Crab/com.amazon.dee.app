package com.amazon.alexa.fitness.api.afits;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.dee.app.http.CoralService;
import kotlin.Metadata;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u001e\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007H&J\u001e\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007H&J3\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007H&¢\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00112\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J\u0016\u0010\u0018\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007H&J\u0016\u0010\u001a\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "", "createFitnessSession", "", "fitnessSession", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "completion", "Lcom/dee/app/http/CoralService$Callback;", "Lokhttp3/Response;", "deleteAllFitnessSessions", "deleteAllFitnessSessionsRequest", "Lcom/amazon/alexa/fitness/api/afits/DeleteAllFitnessSessionsRequest;", "deleteFitnessSession", "deleteFitnessSessionRequest", "Lcom/amazon/alexa/fitness/api/afits/DeleteFitnessSessionRequest;", "getAllFitnessSessions", "nextToken", "", "maxResults", "", "Lcom/amazon/alexa/fitness/api/afits/GetAllFitnessSessionResponse;", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/dee/app/http/CoralService$Callback;)V", "getFitnessSession", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "getPersonIdBackFillStatus", "Lcom/amazon/alexa/fitness/api/afits/GetPersonIdBackFillStatusResponse;", "startPersonIdBackFillProcess", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface AfitsClient {

    /* compiled from: AfitsClient.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void getAllFitnessSessions$default(AfitsClient afitsClient, String str, Integer num, CoralService.Callback callback, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = null;
                }
                if ((i & 2) != 0) {
                    num = 50;
                }
                afitsClient.getAllFitnessSessions(str, num, callback);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAllFitnessSessions");
        }
    }

    void createFitnessSession(@NotNull FitnessSession fitnessSession, @NotNull CoralService.Callback<Response> callback);

    void deleteAllFitnessSessions(@NotNull DeleteAllFitnessSessionsRequest deleteAllFitnessSessionsRequest, @NotNull CoralService.Callback<DeleteAllFitnessSessionsRequest> callback);

    void deleteFitnessSession(@NotNull DeleteFitnessSessionRequest deleteFitnessSessionRequest, @NotNull CoralService.Callback<DeleteFitnessSessionRequest> callback);

    void getAllFitnessSessions(@Nullable String str, @Nullable Integer num, @NotNull CoralService.Callback<GetAllFitnessSessionResponse> callback);

    void getFitnessSession(@NotNull String str, @NotNull CoralService.Callback<FitnessSession> callback);

    void getPersonIdBackFillStatus(@NotNull CoralService.Callback<GetPersonIdBackFillStatusResponse> callback);

    void startPersonIdBackFillProcess(@NotNull CoralService.Callback<Response> callback);
}
