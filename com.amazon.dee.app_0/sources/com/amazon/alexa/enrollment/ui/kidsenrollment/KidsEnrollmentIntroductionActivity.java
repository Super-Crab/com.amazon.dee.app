package com.amazon.alexa.enrollment.ui.kidsenrollment;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.exception.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class KidsEnrollmentIntroductionActivity extends AbstractEnrollmentActivity {
    private static final String TAG = GeneratedOutlineSupport1.outline39(KidsEnrollmentIntroductionActivity.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private KidsEnrollmentPrimerViewFragment fragment;

    private KidsEnrollmentPrimerViewFragment getFragmentInstance() {
        this.fragment = (KidsEnrollmentPrimerViewFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (this.fragment == null) {
            this.fragment = KidsEnrollmentPrimerViewFragment.createInstance();
        }
        return this.fragment;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_INTRO_PAGE_BACK_BTN_CLICK);
        this.fragment = getFragmentInstance();
        Log.i(TAG, "user pressed back button");
        this.fragment.finishEnrollmentWithFailureStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            Injector.inject(this);
            setContentView(R.layout.enrollment_act);
            ActivityUtils.setTranslucent(this);
            this.enrollmentMetricsRecorder.recordUserViewInteraction(MetricsConstants.UserInteractionMetrics.KIDS_INTRO_PAGE_VIEW);
            AlexaService.wakeUp(getApplicationContext());
            this.fragment = (KidsEnrollmentPrimerViewFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            Log.i(TAG, "Inside onCreate of Kids Enrollment intro activity");
            if (this.fragment != null) {
                return;
            }
            this.fragment = KidsEnrollmentPrimerViewFragment.createInstance();
            this.fragment.setArguments(getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), this.fragment, R.id.container);
        } catch (EnrollmentComponentNullException e) {
            Log.e(TAG, e.getMessage());
            finishAndRemoveTask();
        }
    }
}
