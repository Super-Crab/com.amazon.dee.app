package com.amazon.alexa.fitness.store;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.DeleteAllFitnessSessionsRequest;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afits.GetAllFitnessSessionResponse;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.metrics.EventMetric;
import com.amazon.alexa.fitness.metrics.MetricsEnabled;
import com.amazon.alexa.fitness.network.NetworkNotifiable;
import com.amazon.alexa.fitness.store.Result;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WorkoutHistoryStore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0002\u0010\u000bJ\u0011\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0096\u0001J\u0011\u0010&\u001a\u00020'2\u0006\u0010$\u001a\u00020%H\u0096\u0001J\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,J\u0010\u0010-\u001a\u00020)2\b\b\u0002\u0010.\u001a\u00020\u0014J\u0018\u0010-\u001a\u00020)2\u0006\u0010/\u001a\u0002002\b\b\u0002\u0010.\u001a\u00020\u0014J\u0010\u00101\u001a\u00020)2\u0006\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u00020)2\u0006\u00105\u001a\u000206H\u0002J\u0012\u00107\u001a\u00020)2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u001c\u0010:\u001a\u00020)2\b\u0010;\u001a\u0004\u0018\u00010<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0018\u0010?\u001a\u00020)2\u0006\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u000209H\u0016J\u0011\u0010B\u001a\u00020)2\u0006\u0010$\u001a\u00020%H\u0096\u0001J\u001d\u0010C\u001a\u00020)2\u0006\u0010$\u001a\u00020%2\n\u00102\u001a\u00060Dj\u0002`EH\u0096\u0001J\u0011\u0010F\u001a\u00020)2\u0006\u0010$\u001a\u00020%H\u0096\u0001J\u0011\u0010G\u001a\u00020)2\u0006\u0010$\u001a\u00020%H\u0096\u0001J\b\u0010H\u001a\u00020)H\u0016J\u0010\u0010I\u001a\u00020)2\u0006\u0010J\u001a\u00020KH\u0016R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u001aX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e0\u001a8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001b¨\u0006L"}, d2 = {"Lcom/amazon/alexa/fitness/store/WorkoutHistoryStore;", "Lcom/amazon/alexa/fitness/store/DataStore;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "Lcom/amazon/alexa/fitness/metrics/MetricsEnabled;", "Lcom/amazon/alexa/fitness/network/NetworkNotifiable;", "afitsClient", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "metricsEnabledImpl", "networkNotifiableImpl", "(Lcom/amazon/alexa/fitness/api/afits/AfitsClient;Lcom/amazon/alexa/identity/api/IdentityService;Lcom/amazon/alexa/fitness/metrics/MetricsEnabled;Lcom/amazon/alexa/fitness/network/NetworkNotifiable;)V", "_workoutHistoryList", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/alexa/fitness/store/Result;", "", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "getAfitsClient", "()Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "fetchedAllWorkouts", "", "getFetchedAllWorkouts", "()Z", "getIdentityService", "()Lcom/amazon/alexa/identity/api/IdentityService;", "isNetworkConnected", "Landroidx/lifecycle/LiveData;", "()Landroid/arch/lifecycle/LiveData;", "setNetworkConnected", "(Landroid/arch/lifecycle/LiveData;)V", "nextToken", "", "workoutHistoryList", "getWorkoutHistoryList", "createCounter", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsCounter;", "event", "Lcom/amazon/alexa/fitness/metrics/EventMetric;", "createTimer", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "deleteAllWorkouts", "", "fetchWorkout", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "fetchWorkouts", "force", JsonReportFormat.COUNT, "", "handleGetSessionsFailure", ADMRegistrationConstants.CALL_EXCEPTION, "Lcom/dee/app/http/CoralServiceException;", "handleGetSessionsSuccess", "response", "Lcom/amazon/alexa/fitness/api/afits/GetAllFitnessSessionResponse;", "onMetricsUpdated", "metrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "onSessionChanged", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "onSessionEnded", "endedSession", "finalMetrics", "recordClick", "recordError", "Ljava/lang/Exception;", "Lkotlin/Exception;", "recordOccurrence", "recordView", "reset", "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutHistoryStore implements DataStore, FitnessSessionOrchestratorDelegate, MetricsEnabled, NetworkNotifiable {
    private final /* synthetic */ MetricsEnabled $$delegate_0;
    private final /* synthetic */ NetworkNotifiable $$delegate_1;
    private MutableLiveData<Result<List<FitnessSession>>> _workoutHistoryList;
    @NotNull
    private final AfitsClient afitsClient;
    @NotNull
    private final IdentityService identityService;
    private String nextToken;

    public WorkoutHistoryStore(@NotNull AfitsClient afitsClient, @NotNull IdentityService identityService, @NotNull MetricsEnabled metricsEnabledImpl, @NotNull NetworkNotifiable networkNotifiableImpl) {
        Intrinsics.checkParameterIsNotNull(afitsClient, "afitsClient");
        Intrinsics.checkParameterIsNotNull(identityService, "identityService");
        Intrinsics.checkParameterIsNotNull(metricsEnabledImpl, "metricsEnabledImpl");
        Intrinsics.checkParameterIsNotNull(networkNotifiableImpl, "networkNotifiableImpl");
        this.$$delegate_0 = metricsEnabledImpl;
        this.$$delegate_1 = networkNotifiableImpl;
        this.afitsClient = afitsClient;
        this.identityService = identityService;
        this._workoutHistoryList = new MutableLiveData<>();
        this._workoutHistoryList.postValue(Result.Loading.INSTANCE);
    }

    public static /* synthetic */ void fetchWorkouts$default(WorkoutHistoryStore workoutHistoryStore, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        workoutHistoryStore.fetchWorkouts(z);
    }

    private final boolean getFetchedAllWorkouts() {
        return this.nextToken == null && (this._workoutHistoryList.getValue() instanceof Result.Success);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleGetSessionsFailure(CoralServiceException coralServiceException) {
        this.nextToken = null;
        this._workoutHistoryList.postValue(new Result.Error(coralServiceException));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleGetSessionsSuccess(GetAllFitnessSessionResponse getAllFitnessSessionResponse) {
        List list;
        List plus;
        list = ArraysKt___ArraysKt.toList(getAllFitnessSessionResponse.getFitnessSessions());
        Result<List<FitnessSession>> value = this._workoutHistoryList.getValue();
        List emptyList = value instanceof Result.Success ? (List) ((Result.Success) value).getData() : CollectionsKt__CollectionsKt.emptyList();
        this.nextToken = getAllFitnessSessionResponse.getNextToken();
        MutableLiveData<Result<List<FitnessSession>>> mutableLiveData = this._workoutHistoryList;
        plus = CollectionsKt___CollectionsKt.plus((Collection) emptyList, (Iterable) list);
        mutableLiveData.postValue(new Result.Success(plus));
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    @NotNull
    public MobilyticsMetricsCounter createCounter(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return this.$$delegate_0.createCounter(event);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    @NotNull
    public MobilyticsMetricsTimer createTimer(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return this.$$delegate_0.createTimer(event);
    }

    public final void deleteAllWorkouts() {
        List emptyList;
        String str;
        UserProfile userProfile;
        CommsProfile commsProfile;
        MutableLiveData<Result<List<FitnessSession>>> mutableLiveData = this._workoutHistoryList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        mutableLiveData.postValue(new Result.Success(emptyList));
        UserIdentity user = this.identityService.getUser("WorkoutHistoryStore");
        if (user == null || (userProfile = user.getUserProfile()) == null || (commsProfile = userProfile.getCommsProfile()) == null || (str = commsProfile.getCommsId()) == null) {
            str = "";
        }
        this.afitsClient.deleteAllFitnessSessions(new DeleteAllFitnessSessionsRequest(str), new CoralService.Callback<DeleteAllFitnessSessionsRequest>() { // from class: com.amazon.alexa.fitness.store.WorkoutHistoryStore$deleteAllWorkouts$1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(@Nullable CoralService.Call<DeleteAllFitnessSessionsRequest> call, @Nullable CoralServiceException coralServiceException) {
                Log.e("WorkoutHistoryStore", "Delete all Workouts request FAILED ", coralServiceException);
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(@Nullable CoralService.Call<DeleteAllFitnessSessionsRequest> call, @Nullable DeleteAllFitnessSessionsRequest deleteAllFitnessSessionsRequest) {
                Log.i("WorkoutHistoryStore", "Delete all Workouts request SUCCEEDED");
            }
        });
    }

    public final void fetchWorkout(@NotNull final UUID sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        AfitsClient afitsClient = this.afitsClient;
        String uuid = sessionId.toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "sessionId.toString()");
        afitsClient.getFitnessSession(uuid, new CoralService.Callback<FitnessSession>() { // from class: com.amazon.alexa.fitness.store.WorkoutHistoryStore$fetchWorkout$1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(@Nullable CoralService.Call<FitnessSession> call, @Nullable CoralServiceException coralServiceException) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error getting fitness session with UUID: ");
                outline107.append(sessionId);
                Log.e("WorkoutHistoryStore", outline107.toString(), coralServiceException);
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(@Nullable CoralService.Call<FitnessSession> call, @Nullable FitnessSession fitnessSession) {
                List mutableList;
                MutableLiveData mutableLiveData;
                if (fitnessSession != null) {
                    Result<List<FitnessSession>> value = WorkoutHistoryStore.this.getWorkoutHistoryList().getValue();
                    mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) (value instanceof Result.Success ? (List) ((Result.Success) value).getData() : CollectionsKt__CollectionsKt.emptyList()));
                    int i = 0;
                    Iterator it2 = mutableList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            i = -1;
                            break;
                        } else if (Intrinsics.areEqual(((FitnessSession) it2.next()).getId(), fitnessSession.getId())) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (i != -1) {
                        mutableList.set(i, fitnessSession);
                    } else {
                        mutableList.add(fitnessSession);
                    }
                    mutableLiveData = WorkoutHistoryStore.this._workoutHistoryList;
                    mutableLiveData.postValue(new Result.Success(mutableList));
                }
            }
        });
    }

    public final void fetchWorkouts(boolean z) {
        fetchWorkouts(15, z);
    }

    @NotNull
    public final AfitsClient getAfitsClient() {
        return this.afitsClient;
    }

    @NotNull
    public final IdentityService getIdentityService() {
        return this.identityService;
    }

    @NotNull
    public final LiveData<Result<List<FitnessSession>>> getWorkoutHistoryList() {
        return this._workoutHistoryList;
    }

    @Override // com.amazon.alexa.fitness.network.NetworkNotifiable
    @NotNull
    public LiveData<Boolean> isNetworkConnected() {
        return this.$$delegate_1.isNetworkConnected();
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics) {
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionChanged(@Nullable Session session, @Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError) {
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionEnded(@NotNull Session endedSession, @NotNull FitnessMetrics finalMetrics) {
        Intrinsics.checkParameterIsNotNull(endedSession, "endedSession");
        Intrinsics.checkParameterIsNotNull(finalMetrics, "finalMetrics");
        throw new NotImplementedError(GeneratedOutlineSupport1.outline72("An operation is not implemented: ", "\"\n            Using the endedSession and finalMetrics create a FitnessSession and add it in the local store\n\n            This is a pretty involved change with changes needed all 3 UI, Extension, and API packages\n            We need to move the following pieces of logic from Extension to API package\n\n            1. Logic for creating a `FitnessSession` from `Session` and `ActivitySummary` is here: https://tiny.amazon.com/rglvqphh/codeamazpackAlexblob9346src\n            2. We have our own ISO8601 implementation due to historic reasons in the Extension package: https://tiny.amazon.com/97ajm1y6/codeamazpackAlexblobdevesrc\n\n            JIRA: https://issues.labcollab.net/browse/ESXFIT-5143\n\n            Once that is done, we will implement this function.\n            \""));
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordClick(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.$$delegate_0.recordClick(event);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordError(@NotNull EventMetric event, @NotNull Exception exception) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        this.$$delegate_0.recordError(event, exception);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordOccurrence(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.$$delegate_0.recordOccurrence(event);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordView(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.$$delegate_0.recordView(event);
    }

    @Override // com.amazon.alexa.fitness.store.DataStore
    public void reset() {
        this.nextToken = null;
        this._workoutHistoryList.postValue(Result.Loading.INSTANCE);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void sensorAvailabilityChanged(@NotNull SensorAvailability availability) {
        Intrinsics.checkParameterIsNotNull(availability, "availability");
    }

    @Override // com.amazon.alexa.fitness.network.NetworkNotifiable
    public void setNetworkConnected(@NotNull LiveData<Boolean> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.$$delegate_1.setNetworkConnected(liveData);
    }

    public static /* synthetic */ void fetchWorkouts$default(WorkoutHistoryStore workoutHistoryStore, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        workoutHistoryStore.fetchWorkouts(i, z);
    }

    public final void fetchWorkouts(int i, boolean z) {
        if (!getFetchedAllWorkouts() || z) {
            if (z) {
                this.nextToken = null;
                this._workoutHistoryList.postValue(Result.Loading.INSTANCE);
            }
            this.afitsClient.getAllFitnessSessions(this.nextToken, Integer.valueOf(i), new CoralService.Callback<GetAllFitnessSessionResponse>() { // from class: com.amazon.alexa.fitness.store.WorkoutHistoryStore$fetchWorkouts$1
                @Override // com.dee.app.http.CoralService.Callback
                public void onFailure(@Nullable CoralService.Call<GetAllFitnessSessionResponse> call, @Nullable CoralServiceException coralServiceException) {
                    Log.e("WorkoutHistoryStore", "AfitsClient returned an exception when fetching workouts", coralServiceException);
                    if (coralServiceException != null) {
                        WorkoutHistoryStore.this.handleGetSessionsFailure(coralServiceException);
                    }
                }

                @Override // com.dee.app.http.CoralService.Callback
                public void onResult(@Nullable CoralService.Call<GetAllFitnessSessionResponse> call, @Nullable GetAllFitnessSessionResponse getAllFitnessSessionResponse) {
                    if (getAllFitnessSessionResponse != null) {
                        WorkoutHistoryStore.this.handleGetSessionsSuccess(getAllFitnessSessionResponse);
                    }
                }
            });
        }
    }
}
