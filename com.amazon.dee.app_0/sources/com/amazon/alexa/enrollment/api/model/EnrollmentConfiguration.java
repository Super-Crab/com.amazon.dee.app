package com.amazon.alexa.enrollment.api.model;

import android.util.Log;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public final class EnrollmentConfiguration {
    private static final int GET_ENROLLMENT_API_MAX_RETRY_COUNT = 4;
    private static final long GET_ENROLLMENT_STATUS_API_MAX_TIMEOUT = 5000;
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentConfiguration.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static EnrollmentConfiguration enrollmentConfiguration;
    private int maxRetriesPerTrainingPhrase = 4;
    private long getEnrollmentStatusApiTimeout = 5000;
    public final long startEnrollmentApiTimeout = 15000;
    public final int startEnrollmentApiMaxRetryCount = 2;
    public final long eligibilityEnrollmentApiTimeout = 4000;

    private EnrollmentConfiguration() {
    }

    public static synchronized EnrollmentConfiguration getInstance() {
        EnrollmentConfiguration enrollmentConfiguration2;
        synchronized (EnrollmentConfiguration.class) {
            if (enrollmentConfiguration == null) {
                enrollmentConfiguration = new EnrollmentConfiguration();
            }
            enrollmentConfiguration2 = enrollmentConfiguration;
        }
        return enrollmentConfiguration2;
    }

    public long getGetEnrollmentStatusApiTimeout() {
        return this.getEnrollmentStatusApiTimeout;
    }

    public int getMaxRetriesPerTrainingPhrase() {
        return this.maxRetriesPerTrainingPhrase;
    }

    public void resetConfig() {
        this.getEnrollmentStatusApiTimeout = 5000L;
        this.maxRetriesPerTrainingPhrase = 4;
    }

    public void updateConfig(VoiceEnrollmentGUIConfiguration voiceEnrollmentGUIConfiguration) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetEnrollmentStatusApiTimeout ");
        outline107.append(voiceEnrollmentGUIConfiguration.getVoiceEnrollmentStatusApiTimeout());
        outline107.append("MaxRetriesPerTrainingPhrase ");
        outline107.append(voiceEnrollmentGUIConfiguration.getMaxRetriesPerTrainingPhrase());
        Log.i(str, outline107.toString());
        this.getEnrollmentStatusApiTimeout = voiceEnrollmentGUIConfiguration.getVoiceEnrollmentStatusApiTimeout();
        this.maxRetriesPerTrainingPhrase = voiceEnrollmentGUIConfiguration.getMaxRetriesPerTrainingPhrase();
    }
}
