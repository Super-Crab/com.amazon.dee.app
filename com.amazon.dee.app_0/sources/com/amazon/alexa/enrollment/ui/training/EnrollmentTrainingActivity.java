package com.amazon.alexa.enrollment.ui.training;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.exception.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentTrainingActivity extends AbstractEnrollmentActivity {
    public static final int REQUEST_SCRIM_LAUNCHED = 1;
    public static final int RESULT_BACK_PRESSED = 2;
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentTrainingActivity.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private EnrollmentTrainingViewFragment fragment;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = TAG;
        Log.i(str, "Request code: " + i + "Result code: " + i2);
        if (i == 1 && i2 == 2) {
            Log.i(TAG, "User pressed back button in voice scrim");
            this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.TRAINING_PAGE_SCRIM_BACK_CLICK);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.TRAINING_PAGE_BACK_CLICK);
        this.fragment.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            this.enrollmentMetricsRecorder.recordUserViewInteraction(MetricsConstants.UserInteractionMetrics.TRAINING_PAGE_VIEW);
            AlexaService.wakeUp(getApplicationContext());
            setContentView(R.layout.enrollment_act);
            ActivityUtils.setTranslucent(this);
            this.fragment = (EnrollmentTrainingViewFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            if (this.fragment != null) {
                return;
            }
            this.fragment = EnrollmentTrainingViewFragment.createInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), this.fragment, R.id.container);
        } catch (EnrollmentComponentNullException e) {
            Log.e(TAG, e.getMessage());
            finishAndRemoveTask();
        }
    }
}
