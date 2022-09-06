package com.amazon.alexa.enrollment.ui;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.dialogs.AlertDialogFragment;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class AbstractBottomSheetFragment extends BottomSheetDialogFragment implements AlertDialogFragment.OnDialogActionCallback {
    private static final String TAG = GeneratedOutlineSupport1.outline39(AbstractBottomSheetFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EventBus eventBus;
    @Inject
    RoutingService routingService;

    public AbstractBottomSheetFragment() {
    }

    @VisibleForTesting
    void finishActivity() {
        getActivity().finish();
    }

    public void finishEnrollmentWithFailureStatus() {
        Log.i(TAG, "inside finishEnrollmentWithFailureStatus");
        finishActivity();
        navigateBackward();
    }

    @VisibleForTesting
    void navigateBackward() {
        Log.i(TAG, "inside navigateBackward");
        this.routingService.navigateBackward();
    }

    @Override // com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onNegativeButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "inside onNegativeButtonTap with tag: " + str + " and requestCode: " + i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        Log.i(TAG, "Inside onPause");
        super.onPause();
    }

    @Override // com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onPositiveButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "inside onPositiveButtonTap with tag: " + str + " and requestCode: " + i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        Log.i(TAG, "Inside onResume");
        super.onResume();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Log.i(TAG, "Inside onStart");
        super.onStart();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        Log.i(TAG, "Inside onStop");
        super.onStop();
    }

    public AbstractBottomSheetFragment(EventBus eventBus, RoutingService routingService, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        this.eventBus = eventBus;
        this.routingService = routingService;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }
}
