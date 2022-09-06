package com.amazon.alexa.enrollment.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class AbstractEnrollmentActivity extends AppCompatActivity {
    private static final String TAG = GeneratedOutlineSupport1.outline39(AbstractEnrollmentActivity.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    @Inject
    protected DeviceInformation deviceInformation;
    @Inject
    protected EnrollmentMetricsRecorder enrollmentMetricsRecorder;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Injector.inject(this);
        this.enrollmentMetricsRecorder.initializeMetricsContext(ActivityUtils.getEnrollmentContext(this));
        if (this.deviceInformation.isPhoneFormFactor()) {
            setRequestedOrientation(1);
        }
    }
}
