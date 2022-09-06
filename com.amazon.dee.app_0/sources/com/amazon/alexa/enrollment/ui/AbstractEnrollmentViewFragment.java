package com.amazon.alexa.enrollment.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.dialogs.AlertDialogFragment;
import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import com.amazon.alexa.enrollment.dialogs.ProgressDialogFragment;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.amazon.alexa.enrollment.utils.EnrollmentUtil;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.Disposable;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class AbstractEnrollmentViewFragment extends Fragment implements AlertDialogFragment.OnDialogActionCallback {
    private static final int PROGRESS_DIALOG_REQUEST_CODE = 1;
    private static final String TAG = GeneratedOutlineSupport1.outline39(AbstractEnrollmentViewFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static final String VOICE_ENROLLMENT_COMPLETED_MESSAGE = "voice:enrollment:completed";
    private static final String VOICE_ENROLLMENT_FAILED_MESSAGE = "voice:enrollment:completed:failure";
    private static final String VOICE_ENROLLMENT_SUCCESS_MESSAGE = "voice:enrollment:completed:success";
    @Inject
    EnrollmentTrainingDialogHelper dialogHelper;
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EventBus eventBus;
    @Inject
    RoutingService routingService;
    private boolean isActivityInForeground = false;
    public boolean isTrainingCompleted = false;
    private Boolean isRepeatBackEnabled = null;

    private boolean isRepeatBackEnabledInternal() {
        if (isKidsEnrollmentContext() && (!isKidsEnrollmentContext() || isFeatureEnabledForUser("VOX_ENROLLMENT_REPEAT_BACK_ANDROID"))) {
            Log.i(TAG, "Repeat back enabled");
            return true;
        }
        Log.i(TAG, "Repeat back not enabled");
        return false;
    }

    private void navigate(String str, String str2) {
        String str3 = "Route name: " + str + " route uri: " + str2;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.routingService.route(str).with(RouteParameter.ROUTE, str2).forceNavigate();
        } else if (!TextUtils.isEmpty(str)) {
            this.routingService.route(str).forceNavigate();
        } else if (EnrollmentUtil.isOOBE(getEnrollmentContext())) {
        } else {
            navigateBackward();
        }
    }

    private void navigateBackward() {
        try {
            String uri = this.routingService.getCurrentRoute().toUri();
            String str = " route uri: " + uri;
            this.routingService.match(uri).forceNavigate();
        } catch (Exception unused) {
            Log.w(TAG, "Exception when getting the route for uri");
            this.routingService.navigateBackward();
        }
    }

    private void navigateBackwardDirect() {
        Log.i(TAG, "inside navigateBackwardDirect");
        this.routingService.navigateBackward();
    }

    private void refreshRoute() {
        Log.i(TAG, "Refresh the calling screen once after the enrollment flow is complete");
        this.routingService.refresh();
    }

    public void finishEnrollmentWithFailureStatus() {
        Log.i(TAG, "Inside finish enrollment");
        String enrollmentContext = getEnrollmentContext();
        String str = TAG;
        Log.i(str, "Enrollment Context: " + enrollmentContext);
        if (ActivityConstants.KIDS_ENROLLMENT_CONTEXT.equals(enrollmentContext)) {
            getActivity().finish();
            navigateBackwardDirect();
            return;
        }
        Message build = new Message.Builder().setEventType(VOICE_ENROLLMENT_FAILED_MESSAGE).build();
        Message build2 = new Message.Builder().setEventType(VOICE_ENROLLMENT_COMPLETED_MESSAGE).build();
        try {
            this.eventBus.publish(build);
            this.eventBus.publish(build2);
        } catch (EventBusException unused) {
            Log.i(TAG, "EventBusException in publishing completed with failure event");
        }
        getActivity().finish();
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            navigate(extras.getString(ActivityConstants.FAILURE_ROUTE_NAME), extras.getString(ActivityConstants.FAILURE_ROUTE_URI));
        } else {
            refreshRoute();
        }
    }

    public void finishEnrollmentWithSuccessStatus() {
        Log.i(TAG, "Inside finish enrollment with success status");
        Message build = new Message.Builder().setEventType(VOICE_ENROLLMENT_SUCCESS_MESSAGE).build();
        Message build2 = new Message.Builder().setEventType(VOICE_ENROLLMENT_COMPLETED_MESSAGE).build();
        try {
            this.eventBus.publish(build);
            this.eventBus.publish(build2);
        } catch (EventBusException unused) {
            Log.i(TAG, "EventBusException in publishing completed with success event");
        }
        getActivity().finish();
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            navigate(extras.getString(ActivityConstants.SUCCESS_ROUTE_NAME), extras.getString(ActivityConstants.SUCCESS_ROUTE_URI));
        } else {
            refreshRoute();
        }
    }

    @VisibleForTesting
    public String getEnrollmentContext() {
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            return extras.getString(ActivityConstants.ENROLLMENT_CONTEXT);
        }
        return null;
    }

    protected void hideLoading(Object obj, Throwable th) {
        hideLoading(th);
    }

    public boolean isActivityInForeground() {
        return this.isActivityInForeground;
    }

    public boolean isFeatureEnabledForUser(String str) {
        String str2 = TAG;
        Log.i(str2, "isFeatureEnabledForUser: " + str);
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) ComponentRegistry.getInstance().get(FeatureServiceV2.class).get();
        if (featureServiceV2 != null && featureServiceV2.hasAccess(str, false)) {
            GeneratedOutlineSupport1.outline163(str, ": Feature enabled", TAG);
            return true;
        }
        GeneratedOutlineSupport1.outline163(str, ": Feature not enabled", TAG);
        return false;
    }

    public boolean isKidsEnrollmentContext() {
        if (ActivityConstants.KIDS_ENROLLMENT_CONTEXT.equals(getEnrollmentContext())) {
            Log.i(TAG, "KidsEnrollment context: true");
            return true;
        }
        Log.i(TAG, "KidsEnrollmentContext: false");
        return false;
    }

    public boolean isRepeatBackEnabled() {
        if (this.isRepeatBackEnabled == null) {
            Log.i(TAG, "calculating whether Repeat back is enabled");
            this.isRepeatBackEnabled = Boolean.valueOf(isRepeatBackEnabledInternal());
        }
        return this.isRepeatBackEnabled.booleanValue();
    }

    @Override // com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onNegativeButtonTap(String str, int i) {
        String str2 = "Inside onNegativeButtonTap for Tag: " + str + " requestCode: " + i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        Log.i(TAG, "Inside onPause");
        super.onPause();
    }

    @Override // com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onPositiveButtonTap(String str, int i) {
        String str2 = "Inside onPositiveButtonTap for Tag: " + str + " requestCode: " + i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        Log.i(TAG, "Inside onResume");
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        Log.i(TAG, "Inside onStart");
        this.isActivityInForeground = true;
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        Log.i(TAG, "Inside onStop");
        this.isActivityInForeground = false;
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoading(Disposable disposable) {
        if (!isAdded()) {
            Log.e(TAG, "Fragment is not attached to an activity");
        } else if (getActivity().getSupportFragmentManager().findFragmentByTag(DialogConstants.PROGRESS_DIALOG_TAG) != null) {
        } else {
            ProgressDialogFragment createInstance = ProgressDialogFragment.createInstance(getString(R.string.loading_title), getString(R.string.loading_msg));
            createInstance.setTargetFragment(this, 1);
            createInstance.show(getActivity().getSupportFragmentManager(), DialogConstants.PROGRESS_DIALOG_TAG);
        }
    }

    public void showSkipDialogPopup() {
        this.dialogHelper.showSkipDialog(this);
    }

    @VisibleForTesting
    public void hideLoading(Throwable th) {
        if (!isAdded()) {
            Log.e(TAG, "Fragment is not attached to an activity");
            return;
        }
        Fragment findFragmentByTag = getActivity().getSupportFragmentManager().findFragmentByTag(DialogConstants.PROGRESS_DIALOG_TAG);
        if (findFragmentByTag == null) {
            return;
        }
        getActivity().getSupportFragmentManager().beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
    }
}
