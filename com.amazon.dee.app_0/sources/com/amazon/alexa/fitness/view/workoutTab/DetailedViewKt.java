package com.amazon.alexa.fitness.view.workoutTab;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.DeleteFitnessSessionRequest;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.ButtonAnimationsUtilKt;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DetailedView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001\u001a\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"TAG", "", "afitsClient", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "kotlin.jvm.PlatformType", "commsId", "handleDeleteWorkout", "", "view", "Landroid/view/View;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "setupSettingsButton", "fullScreenView", "setupSummaryFooter", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DetailedViewKt {
    private static final String TAG = "AFX-WorkoutDetailedView";
    private static final AfitsClient afitsClient = (AfitsClient) GeneratedOutlineSupport1.outline20(AfitsClient.class);
    private static String commsId;

    static {
        String str;
        UserProfile userProfile;
        CommsProfile commsProfile;
        UserIdentity user = FullScreenUtil.Companion.getIdentityService().getUser(TAG);
        if (user == null || (userProfile = user.getUserProfile()) == null || (commsProfile = userProfile.getCommsProfile()) == null || (str = commsProfile.getCommsId()) == null) {
            str = "";
        }
        commsId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.amazon.alexa.fitness.view.workoutTab.DetailedViewKt$handleDeleteWorkout$getFitnessSessionResultCallback$1] */
    public static final void handleDeleteWorkout(View view, final String str) {
        Context context = view.getContext();
        final DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1 detailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1 = new DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1(str, (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline20(FitnessSessionOrchestrator.class), (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class));
        final ?? r0 = new CoralService.Callback<FitnessSession>() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedViewKt$handleDeleteWorkout$getFitnessSessionResultCallback$1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(@Nullable CoralService.Call<FitnessSession> call, @Nullable CoralServiceException coralServiceException) {
                Log.e("AFX-WorkoutDetailedView", "error getting session", coralServiceException);
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(@Nullable CoralService.Call<FitnessSession> call, @Nullable FitnessSession fitnessSession) {
                AfitsClient afitsClient2;
                String str2;
                String version = fitnessSession != null ? fitnessSession.getVersion() : null;
                if (version != null) {
                    afitsClient2 = DetailedViewKt.afitsClient;
                    str2 = DetailedViewKt.commsId;
                    afitsClient2.deleteFitnessSession(new DeleteFitnessSessionRequest(str2, fitnessSession.getId(), version), DetailedViewKt$handleDeleteWorkout$deleteFitnessSessionResultCallback$1.this);
                    return;
                }
                Log.e("AFX-WorkoutDetailedView", "got null version for session, cannot proceed with deleting session");
                FullScreenUtil.Companion.getMainHandler().post(DetailedViewKt$handleDeleteWorkout$getFitnessSessionResultCallback$1$onResult$1.INSTANCE);
            }
        };
        new AlertDialog.Builder(context).setTitle(context.getString(R.string.delete_workout_alert_title)).setMessage(context.getString(R.string.delete_workout_alert_message)).setPositiveButton(context.getString(R.string.delete_all_caps), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedViewKt$handleDeleteWorkout$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AfitsClient afitsClient2;
                afitsClient2 = DetailedViewKt.afitsClient;
                afitsClient2.getFitnessSession(str, r0);
            }
        }).setNegativeButton(context.getString(R.string.cancel), DetailedViewKt$handleDeleteWorkout$2.INSTANCE).show();
    }

    public static final void setupSettingsButton(@NotNull View fullScreenView, @NotNull final String sessionId) {
        Intrinsics.checkParameterIsNotNull(fullScreenView, "fullScreenView");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        AppCompatImageButton appCompatImageButton = (AppCompatImageButton) fullScreenView.findViewById(R.id.btn_settings);
        if (!FullScreenUtil.Companion.getFeatureService().isWorkoutHistoryTabEnabled()) {
            if (appCompatImageButton == null) {
                return;
            }
            appCompatImageButton.setOnClickListener(DetailedViewKt$setupSettingsButton$2.INSTANCE);
            return;
        }
        if (appCompatImageButton != null) {
            appCompatImageButton.setImageResource(R.drawable.ic_delete);
        }
        if (appCompatImageButton == null) {
            return;
        }
        appCompatImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedViewKt$setupSettingsButton$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it2) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                DetailedViewKt.handleDeleteWorkout(it2, sessionId);
            }
        });
    }

    public static final void setupSummaryFooter(@NotNull View fullScreenView) {
        Intrinsics.checkParameterIsNotNull(fullScreenView, "fullScreenView");
        if (FullScreenUtil.Companion.getFeatureService().isWorkoutHistoryTabEnabled()) {
            TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_view_all_workouts);
            Fonts.EMBER_DISPLAY_BOLD.apply(textView);
            textView.setVisibility(0);
            textView.setOnClickListener(DetailedViewKt$setupSummaryFooter$1$1.INSTANCE);
            View findViewById = fullScreenView.findViewById(R.id.btn_done);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "fullScreenView.findViewById<Button>(R.id.btn_done)");
            ((Button) findViewById).setVisibility(8);
            return;
        }
        Button doneButton = (Button) fullScreenView.findViewById(R.id.btn_done);
        Fonts.EMBER_DISPLAY_BOLD.apply(doneButton);
        Intrinsics.checkExpressionValueIsNotNull(doneButton, "doneButton");
        ButtonAnimationsUtilKt.showCustomButton(doneButton);
        doneButton.setOnClickListener(DetailedViewKt$setupSummaryFooter$2.INSTANCE);
    }
}
