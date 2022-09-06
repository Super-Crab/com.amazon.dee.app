package com.amazon.alexa.fitness.view.workoutTab;

import android.util.Log;
import com.amazon.alexa.fitness.api.afits.DeleteFitnessSessionRequest;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: DetailedView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\"\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\"\u0010\t\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u000b"}, d2 = {"com/amazon/alexa/fitness/view/workoutTab/DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1", "Lcom/dee/app/http/CoralService$Callback;", "Lcom/amazon/alexa/fitness/api/afits/DeleteFitnessSessionRequest;", "onFailure", "", "call", "Lcom/dee/app/http/CoralService$Call;", "e", "Lcom/dee/app/http/CoralServiceException;", "onResult", "request", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1 implements CoralService.Callback<DeleteFitnessSessionRequest> {
    final /* synthetic */ FitnessSessionOrchestrator $orchestrator;
    final /* synthetic */ RoutingService $routingService;
    final /* synthetic */ String $sessionId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1(String str, FitnessSessionOrchestrator fitnessSessionOrchestrator, RoutingService routingService) {
        this.$sessionId = str;
        this.$orchestrator = fitnessSessionOrchestrator;
        this.$routingService = routingService;
    }

    @Override // com.dee.app.http.CoralService.Callback
    public void onFailure(@Nullable CoralService.Call<DeleteFitnessSessionRequest> call, @Nullable CoralServiceException coralServiceException) {
        Log.e("AFX-WorkoutDetailedView", "error deleting session", coralServiceException);
    }

    @Override // com.dee.app.http.CoralService.Callback
    public void onResult(@Nullable CoralService.Call<DeleteFitnessSessionRequest> call, @Nullable DeleteFitnessSessionRequest deleteFitnessSessionRequest) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("received result for delete session: ");
        outline107.append(this.$sessionId);
        outline107.toString();
        this.$orchestrator.deleteWorkout(this.$sessionId);
        FullScreenUtil.Companion.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1$onResult$1
            @Override // java.lang.Runnable
            public final void run() {
                DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1.this.$routingService.navigateBackward();
            }
        });
    }
}
