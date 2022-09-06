package com.amazon.alexa.enrollment.ui.privacySettings;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.exception.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentPrivacyTermsActivity extends AbstractEnrollmentActivity {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentPrivacyTermsActivity.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private EnrollmentPrivacyTermsViewFragment fragment;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(R.layout.enrollment_act);
            ActivityUtils.setTranslucent(this);
            this.enrollmentMetricsRecorder.recordUserViewInteraction(MetricsConstants.UserInteractionMetrics.PRIVACY_TERMS_PAGE_VIEW);
            this.fragment = (EnrollmentPrivacyTermsViewFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            if (this.fragment != null) {
                return;
            }
            this.fragment = EnrollmentPrivacyTermsViewFragment.createInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), this.fragment, R.id.container);
        } catch (EnrollmentComponentNullException e) {
            Log.e(TAG, e.getMessage());
            finishAndRemoveTask();
        }
    }

    public void onPrivacyTermsPageSkipButtonClicked(View view) {
        Log.i(TAG, "User clicked skip button in privacy terms page. So finishing enrollment");
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.PRIVACY_TERMS_PAGE_SKIP_CLICK);
        this.fragment.finishEnrollmentWithFailureStatus();
    }
}
