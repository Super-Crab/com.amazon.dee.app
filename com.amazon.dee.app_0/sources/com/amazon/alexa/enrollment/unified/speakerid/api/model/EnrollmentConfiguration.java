package com.amazon.alexa.enrollment.unified.speakerid.api.model;

import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public final class EnrollmentConfiguration {
    private static final int GET_ENROLLMENT_API_MAX_RETRY_COUNT = 4;
    private static final long GET_ENROLLMENT_STATUS_API_MAX_TIMEOUT = 5000;
    private static final int START_ENROLLMENT_API_MAX_RETRY_COUNT = 2;
    private static final long START_ENROLLMENT_API_TIMEOUT = 15000;
    private static final String TAG = "EnrollmentConfiguration";
    private static EnrollmentConfiguration enrollmentConfiguration;
    private int maxRetriesPerTrainingPhrase = 4;
    private long getEnrollmentStatusApiTimeout = 5000;

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

    public int getStartEnrollmentApiMaxRetryCount() {
        return 2;
    }

    public long getStartEnrollmentApiTimeout() {
        return START_ENROLLMENT_API_TIMEOUT;
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
