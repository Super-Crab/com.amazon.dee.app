package com.amazon.alexa.fitness.view.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.ViewCompat;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.DeleteAllFitnessSessionsRequest;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.utils.SettingsMetrics;
import com.amazon.alexa.fitness.view.startTab.CustomStopButtonViewKt;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SettingsViewController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0016H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/fitness/view/settings/SettingsViewController;", "Lcom/amazon/alexa/viewmanagement/api/ViewController;", "()V", "afitsClient", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "kotlin.jvm.PlatformType", "deleteAllFitnessSessionResultCallback", "com/amazon/alexa/fitness/view/settings/SettingsViewController$deleteAllFitnessSessionResultCallback$1", "Lcom/amazon/alexa/fitness/view/settings/SettingsViewController$deleteAllFitnessSessionResultCallback$1;", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "orchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "getTitle", "", "context", "Landroid/content/Context;", "handleDeleteAllWorkouts", "", "makeView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "setupSettingsView", "view", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SettingsViewController implements ViewController {
    private final AfitsClient afitsClient = (AfitsClient) GeneratedOutlineSupport1.outline20(AfitsClient.class);
    private final IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline22(IdentityService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    private final FitnessSessionOrchestrator orchestrator = (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline22(FitnessSessionOrchestrator.class, "ComponentRegistry.getIns…trator::class.java).get()");
    private final Mobilytics metrics = (Mobilytics) GeneratedOutlineSupport1.outline22(Mobilytics.class, "ComponentRegistry.getIns…lytics::class.java).get()");
    private final SettingsViewController$deleteAllFitnessSessionResultCallback$1 deleteAllFitnessSessionResultCallback = new CoralService.Callback<DeleteAllFitnessSessionsRequest>() { // from class: com.amazon.alexa.fitness.view.settings.SettingsViewController$deleteAllFitnessSessionResultCallback$1
        @Override // com.dee.app.http.CoralService.Callback
        public void onFailure(@Nullable CoralService.Call<DeleteAllFitnessSessionsRequest> call, @Nullable CoralServiceException coralServiceException) {
            Log.e("AFX-SettingsView", "error deleting all sessions", coralServiceException);
        }

        @Override // com.dee.app.http.CoralService.Callback
        public void onResult(@Nullable CoralService.Call<DeleteAllFitnessSessionsRequest> call, @Nullable DeleteAllFitnessSessionsRequest deleteAllFitnessSessionsRequest) {
            FitnessSessionOrchestrator fitnessSessionOrchestrator;
            fitnessSessionOrchestrator = SettingsViewController.this.orchestrator;
            fitnessSessionOrchestrator.deleteAllWorkouts();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDeleteAllWorkouts(Context context) {
        TextView textView = new TextView(context);
        textView.setTextColor(CustomStopButtonViewKt.isDarkThemeOn(context) ? -1 : ViewCompat.MEASURED_STATE_MASK);
        textView.setText(context.getString(R.string.delete_all_workouts_alert_title));
        textView.setPadding(60, 40, 60, 20);
        textView.setTypeface(null, 1);
        textView.setTextSize(18.0f);
        new AlertDialog.Builder(context).setCustomTitle(textView).setMessage(context.getString(R.string.delete_all_workouts_alert_message)).setPositiveButton(context.getString(R.string.delete_all_alert), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.fitness.view.settings.SettingsViewController$handleDeleteAllWorkouts$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                IdentityService identityService;
                String str;
                Mobilytics mobilytics;
                AfitsClient afitsClient;
                SettingsViewController$deleteAllFitnessSessionResultCallback$1 settingsViewController$deleteAllFitnessSessionResultCallback$1;
                UserProfile userProfile;
                CommsProfile commsProfile;
                identityService = SettingsViewController.this.identityService;
                UserIdentity user = identityService.getUser("AFX-SettingsView");
                if (user == null || (userProfile = user.getUserProfile()) == null || (commsProfile = userProfile.getCommsProfile()) == null || (str = commsProfile.getCommsId()) == null) {
                    str = "";
                }
                mobilytics = SettingsViewController.this.metrics;
                MetricHelperKt.recordUserInteractionEvent(mobilytics, SettingsMetrics.INSTANCE.getDeleteAllWorkouts(), EventType.TAP);
                afitsClient = SettingsViewController.this.afitsClient;
                DeleteAllFitnessSessionsRequest deleteAllFitnessSessionsRequest = new DeleteAllFitnessSessionsRequest(str);
                settingsViewController$deleteAllFitnessSessionResultCallback$1 = SettingsViewController.this.deleteAllFitnessSessionResultCallback;
                afitsClient.deleteAllFitnessSessions(deleteAllFitnessSessionsRequest, settingsViewController$deleteAllFitnessSessionResultCallback$1);
            }
        }).setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.fitness.view.settings.SettingsViewController$handleDeleteAllWorkouts$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Mobilytics mobilytics;
                mobilytics = SettingsViewController.this.metrics;
                MetricHelperKt.recordUserInteractionEvent(mobilytics, SettingsMetrics.INSTANCE.getCancelDeleteAllWorkouts(), EventType.TAP);
            }
        }).show();
    }

    private final void setupSettingsView(View view) {
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
        final Resources resources = context.getResources();
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_customText));
        TextView textView = (TextView) view.findViewById(R.id.workoutProfileListItem).findViewById(R.id.list_item_textView);
        textView.setText(resources.getString(R.string.edit_workout_profile));
        Fonts.EMBER_REGULAR.apply(textView);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.settings.SettingsViewController$setupSettingsView$$inlined$apply$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Mobilytics mobilytics;
                mobilytics = SettingsViewController.this.metrics;
                MetricHelperKt.recordUserInteractionEvent(mobilytics, SettingsMetrics.INSTANCE.getWorkoutProfile(), EventType.TAP);
                FullScreenUtil.Companion.goUserProfile();
            }
        });
        TextView textView2 = (TextView) view.findViewById(R.id.deleteWorkoutsListItem).findViewById(R.id.list_item_textView);
        textView2.setText(resources.getString(R.string.delete_all_workouts));
        Fonts.EMBER_REGULAR.apply(textView2);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.settings.SettingsViewController$setupSettingsView$$inlined$apply$lambda$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it2) {
                SettingsViewController settingsViewController = SettingsViewController.this;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                Context context2 = it2.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "it.context");
                settingsViewController.handleDeleteAllWorkouts(context2);
            }
        });
        TextView textView3 = (TextView) view.findViewById(R.id.workoutDataHeader).findViewById(R.id.section_header_textView);
        textView3.setText(resources.getString(R.string.workout_data));
        Fonts.EMBER_BOLD.apply(textView3);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NotNull
    public String getTitle(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String string = context.getString(R.string.fitness_full_screen_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…itness_full_screen_title)");
        return string;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NotNull
    public View makeView(@NotNull LayoutInflater inflater, @NotNull ViewGroup container) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(container, "container");
        View view = inflater.inflate(R.layout.settings_layout, container, false);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        setupSettingsView(view);
        return view;
    }
}
