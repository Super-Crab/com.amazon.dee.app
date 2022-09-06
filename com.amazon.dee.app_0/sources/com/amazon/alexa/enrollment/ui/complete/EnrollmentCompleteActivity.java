package com.amazon.alexa.enrollment.ui.complete;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.exception.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentCompleteActivity extends AbstractEnrollmentActivity {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentCompleteActivity.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private EnrollmentCompleteViewFragment fragment;

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.COMPLETION_PAGE_BACK_BTN_CLICKED);
        this.fragment.finishEnrollmentWithSuccessStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            Log.i(TAG, "Inside onCreate enrollment completion activity");
            super.onCreate(bundle);
            setContentView(R.layout.enrollment_act);
            ActivityUtils.setTranslucent(this);
            this.fragment = (EnrollmentCompleteViewFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            if (this.fragment != null) {
                return;
            }
            this.fragment = EnrollmentCompleteViewFragment.createInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), this.fragment, R.id.container);
        } catch (EnrollmentComponentNullException e) {
            Log.e(TAG, e.getMessage());
            finishAndRemoveTask();
        }
    }
}
