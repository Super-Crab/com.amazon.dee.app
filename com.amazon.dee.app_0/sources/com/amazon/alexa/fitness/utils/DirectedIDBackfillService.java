package com.amazon.alexa.fitness.utils;

import android.util.Log;
import com.amazon.alexa.fitness.api.UserPreferenceKey;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.GetPersonIdBackFillStatusResponse;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.facebook.common.util.UriUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DirectedIDBackfillService.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020(H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\u0012H\u0002J\u0010\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020-H\u0002J\u0006\u00101\u001a\u00020(J\b\u00102\u001a\u00020(H\u0002J\b\u00103\u001a\u00020(H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00064"}, d2 = {"Lcom/amazon/alexa/fitness/utils/DirectedIDBackfillService;", "", "afxBackfillMessageProcessor", "Lcom/amazon/alexa/fitness/utils/AfxBackfillMessageProcessor;", "(Lcom/amazon/alexa/fitness/utils/AfxBackfillMessageProcessor;)V", "afitsClient", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "getAfitsClient", "()Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "setAfitsClient", "(Lcom/amazon/alexa/fitness/api/afits/AfitsClient;)V", "attempts", "", "getAttempts", "()I", "setAttempts", "(I)V", "hasStartedBackfill", "", "getHasStartedBackfill", "()Z", "setHasStartedBackfill", "(Z)V", "maxBackfillAttempts", "getMaxBackfillAttempts", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "getMetrics", "()Lcom/amazon/alexa/mobilytics/Mobilytics;", "progressCheckTimer", "Lcom/amazon/alexa/fitness/utils/RepeatingTimer;", "repeatingRunnable", "Ljava/lang/Runnable;", "userPreferenceStore", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "getUserPreferenceStore", "()Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "setUserPreferenceStore", "(Lcom/amazon/alexa/fitness/api/UserPreferenceStore;)V", "checkStatusAndRetryIfRequired", "", "getBackfillStatus", "getMetric", "Lcom/amazon/alexa/fitness/utils/BackfillMetricsComponent;", UriUtil.LOCAL_RESOURCE_SCHEME, "Lcom/amazon/alexa/fitness/api/afits/GetPersonIdBackFillStatusResponse;", "hasCompletedBackfill", "onBackfillStatusResponse", "response", "processBackfillStatus", "startBackfill", "startOrContinueBackfill", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DirectedIDBackfillService {
    @NotNull
    private AfitsClient afitsClient;
    private int attempts;
    private boolean hasStartedBackfill;
    private final int maxBackfillAttempts;
    @NotNull
    private final Mobilytics metrics;
    private final RepeatingTimer progressCheckTimer;
    private final Runnable repeatingRunnable;
    @NotNull
    private UserPreferenceStore userPreferenceStore;

    @Inject
    public DirectedIDBackfillService(@NotNull AfxBackfillMessageProcessor afxBackfillMessageProcessor) {
        Intrinsics.checkParameterIsNotNull(afxBackfillMessageProcessor, "afxBackfillMessageProcessor");
        this.userPreferenceStore = (UserPreferenceStore) GeneratedOutlineSupport1.outline22(UserPreferenceStore.class, "ComponentRegistry.getIns…eStore::class.java).get()");
        this.afitsClient = (AfitsClient) GeneratedOutlineSupport1.outline22(AfitsClient.class, "ComponentRegistry.getIns…Client::class.java).get()");
        this.metrics = (Mobilytics) GeneratedOutlineSupport1.outline22(Mobilytics.class, "ComponentRegistry.getIns…lytics::class.java).get()");
        this.maxBackfillAttempts = 5;
        this.progressCheckTimer = new RepeatingTimer(afxBackfillMessageProcessor, 0L, 2, null);
        this.repeatingRunnable = new Runnable() { // from class: com.amazon.alexa.fitness.utils.DirectedIDBackfillService$repeatingRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                DirectedIDBackfillService.this.checkStatusAndRetryIfRequired();
            }
        };
        this.progressCheckTimer.setRunnable(this.repeatingRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkStatusAndRetryIfRequired() {
        int i = this.attempts;
        if (i < this.maxBackfillAttempts) {
            this.attempts = i + 1;
            getBackfillStatus();
            return;
        }
        this.progressCheckTimer.stop();
        Log.e("AFX-DirectedIDBackfillService", "Failed " + this.maxBackfillAttempts + " attempts to backfill and/or check backfill status. Will attempt on next app start-up.");
    }

    private final void getBackfillStatus() {
        this.afitsClient.getPersonIdBackFillStatus(new CoralService.Callback<GetPersonIdBackFillStatusResponse>() { // from class: com.amazon.alexa.fitness.utils.DirectedIDBackfillService$getBackfillStatus$1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(@Nullable CoralService.Call<GetPersonIdBackFillStatusResponse> call, @Nullable CoralServiceException coralServiceException) {
                Log.e("AFX-DirectedIDBackfillService", "Error checking backfill status from AFITS: ", coralServiceException);
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(@Nullable CoralService.Call<GetPersonIdBackFillStatusResponse> call, @NotNull GetPersonIdBackFillStatusResponse response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                DirectedIDBackfillService.this.onBackfillStatusResponse(response);
            }
        });
    }

    private final BackfillMetricsComponent getMetric(GetPersonIdBackFillStatusResponse getPersonIdBackFillStatusResponse) {
        String status = getPersonIdBackFillStatusResponse.getStatus();
        if (Intrinsics.areEqual(status, BackfillStatus.SUCCESS.name())) {
            return BackfillMetrics.Companion.getBackfillStatusSuccess();
        }
        if (Intrinsics.areEqual(status, BackfillStatus.UNAVAILABLE.name())) {
            return BackfillMetrics.Companion.getBackfillStatusUnavailable();
        }
        if (Intrinsics.areEqual(status, BackfillStatus.IN_PROGRESS.name())) {
            return BackfillMetrics.Companion.getBackfillStatusInProgress();
        }
        return BackfillMetrics.Companion.getBackfillStatusFailed();
    }

    private final boolean hasCompletedBackfill() {
        return this.userPreferenceStore.get(UserPreferenceKey.HasCompletedBackfill);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBackfillStatusResponse(GetPersonIdBackFillStatusResponse getPersonIdBackFillStatusResponse) {
        if (Intrinsics.areEqual(getPersonIdBackFillStatusResponse.getStatus(), BackfillStatus.SUCCESS.name())) {
            Log.i("AFX-DirectedIDBackfillService", "Backfill succeeded");
            this.userPreferenceStore.set(UserPreferenceKey.HasCompletedBackfill, true);
            MetricHelperKt.recordCounter$default(this.metrics, BackfillMetrics.Companion.getGetStatusSuccess(), 0L, 2, null);
            MetricHelperKt.recordCounter$default(this.metrics, BackfillMetrics.Companion.getBackfillStatusSuccess(), 0L, 2, null);
            MetricHelperKt.recordCounter(this.metrics, BackfillMetrics.Companion.getStatusCheckCountToGetSuccess(), this.attempts);
            this.progressCheckTimer.stop();
        } else if (!Intrinsics.areEqual(getPersonIdBackFillStatusResponse.getStatus(), BackfillStatus.UNAVAILABLE.name()) && !Intrinsics.areEqual(getPersonIdBackFillStatusResponse.getStatus(), BackfillStatus.FAILED.name())) {
            if (Intrinsics.areEqual(getPersonIdBackFillStatusResponse.getStatus(), BackfillStatus.IN_PROGRESS.name())) {
                Log.i("AFX-DirectedIDBackfillService", "Backfill still in progress, waiting for next timer tick");
                MetricHelperKt.recordCounter$default(this.metrics, BackfillMetrics.Companion.getBackfillStatusInProgress(), 0L, 2, null);
                MetricHelperKt.recordCounter$default(this.metrics, BackfillMetrics.Companion.getGetStatusSuccess(), 0L, 2, null);
                return;
            }
            Log.e("AFX-DirectedIDBackfillService", "Invalid response received from AFITS. Stopping backfill process. Response: " + getPersonIdBackFillStatusResponse);
            MetricHelperKt.recordCounter$default(this.metrics, BackfillMetrics.Companion.getGetStatusFailed(), 0L, 2, null);
            this.progressCheckTimer.stop();
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Retrying backfill as status was: ");
            outline107.append(getPersonIdBackFillStatusResponse.getStatus());
            Log.i("AFX-DirectedIDBackfillService", outline107.toString());
            if (this.attempts < this.maxBackfillAttempts) {
                startBackfill();
            }
            MetricHelperKt.recordCounter$default(this.metrics, BackfillMetrics.Companion.getGetStatusSuccess(), 0L, 2, null);
            MetricHelperKt.recordCounter$default(this.metrics, getMetric(getPersonIdBackFillStatusResponse), 0L, 2, null);
        }
    }

    private final void startBackfill() {
        this.afitsClient.startPersonIdBackFillProcess(new CoralService.Callback<Response>() { // from class: com.amazon.alexa.fitness.utils.DirectedIDBackfillService$startBackfill$1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(@Nullable CoralService.Call<Response> call, @Nullable CoralServiceException coralServiceException) {
                Log.e("AFX-DirectedIDBackfillService", "Error starting backfill from AFITS: ", coralServiceException);
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(@Nullable CoralService.Call<Response> call, @NotNull Response response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                Log.i("AFX-DirectedIDBackfillService", "Backfill process has started.");
            }
        });
    }

    private final void startOrContinueBackfill() {
        if (this.hasStartedBackfill || hasCompletedBackfill()) {
            return;
        }
        Log.i("AFX-DirectedIDBackfillService", "Initiating backfill and starting repeating timer.");
        this.hasStartedBackfill = true;
        MetricHelperKt.recordCounter$default(this.metrics, BackfillMetrics.Companion.getInitiated(), 0L, 2, null);
        checkStatusAndRetryIfRequired();
        this.progressCheckTimer.start();
    }

    @NotNull
    public final AfitsClient getAfitsClient() {
        return this.afitsClient;
    }

    public final int getAttempts() {
        return this.attempts;
    }

    public final boolean getHasStartedBackfill() {
        return this.hasStartedBackfill;
    }

    public final int getMaxBackfillAttempts() {
        return this.maxBackfillAttempts;
    }

    @NotNull
    public final Mobilytics getMetrics() {
        return this.metrics;
    }

    @NotNull
    public final UserPreferenceStore getUserPreferenceStore() {
        return this.userPreferenceStore;
    }

    public final void processBackfillStatus() {
        Log.i("AFX-DirectedIDBackfillService", "Processing backfill status...");
        if (!hasCompletedBackfill()) {
            startOrContinueBackfill();
        } else {
            Log.i("AFX-DirectedIDBackfillService", "Backfill has already been completed");
        }
    }

    public final void setAfitsClient(@NotNull AfitsClient afitsClient) {
        Intrinsics.checkParameterIsNotNull(afitsClient, "<set-?>");
        this.afitsClient = afitsClient;
    }

    public final void setAttempts(int i) {
        this.attempts = i;
    }

    public final void setHasStartedBackfill(boolean z) {
        this.hasStartedBackfill = z;
    }

    public final void setUserPreferenceStore(@NotNull UserPreferenceStore userPreferenceStore) {
        Intrinsics.checkParameterIsNotNull(userPreferenceStore, "<set-?>");
        this.userPreferenceStore = userPreferenceStore;
    }
}
