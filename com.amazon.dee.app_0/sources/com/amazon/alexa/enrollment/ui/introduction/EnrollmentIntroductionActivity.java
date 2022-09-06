package com.amazon.alexa.enrollment.ui.introduction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.exception.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.amazon.alexa.enrollment.utils.EnrollmentUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentIntroductionActivity extends AbstractEnrollmentActivity {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentIntroductionActivity.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private EnrollmentIntroductionViewFragment fragment;

    public void onBackButtonClicked(View view) {
        Log.i(TAG, "User clicked back button. Finishing enrollment");
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_BACK_CLICK);
        this.fragment.finishEnrollmentWithFailureStatus();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!EnrollmentUtil.isOOBE(getIntent().getStringExtra(ActivityConstants.ENROLLMENT_CONTEXT))) {
            this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_BACK_BTN_CLICKED);
            this.fragment.finishEnrollmentWithFailureStatus();
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(R.layout.enrollment_act);
            ActivityUtils.setTranslucent(this);
            AlexaService.wakeUp(getApplicationContext());
            this.fragment = (EnrollmentIntroductionViewFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            Log.i(TAG, "Inside onCreate enrollment intro activity");
            if (this.fragment != null) {
                return;
            }
            this.fragment = EnrollmentIntroductionViewFragment.createInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), this.fragment, R.id.container);
        } catch (EnrollmentComponentNullException e) {
            Log.e(TAG, e.getMessage());
            finishAndRemoveTask();
        }
    }

    public void onProminentSkipButtonClicked(View view) {
        Log.i(TAG, "User clicked prominent skip button in footer. So opening skip dialog box");
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_PROMINENT_SKIP_CLICK);
        this.fragment.showSkipDialogPopup();
    }

    public void onSkipButtonClicked(View view) {
        Log.i(TAG, "User clicked skip button. So finishing enrollment");
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_SKIP_CLICK);
        this.fragment.showSkipDialogPopup();
    }
}
