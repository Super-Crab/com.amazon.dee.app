package com.amazon.alexa.enrollment.ui.terms;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.exception.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentTermsActivity extends AbstractEnrollmentActivity {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentTermsActivity.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(R.layout.enrollment_act);
            this.enrollmentMetricsRecorder.recordUserViewInteraction(MetricsConstants.UserInteractionMetrics.TERMS_PAGE_VIEW);
            EnrollmentTermsViewFragment enrollmentTermsViewFragment = (EnrollmentTermsViewFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            Log.i(TAG, "Inside onCreate enrollment intro activity");
            if (enrollmentTermsViewFragment != null) {
                return;
            }
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), EnrollmentTermsViewFragment.createInstance(), R.id.container);
        } catch (EnrollmentComponentNullException e) {
            Log.e(TAG, e.getMessage());
            finishAndRemoveTask();
        }
    }
}
