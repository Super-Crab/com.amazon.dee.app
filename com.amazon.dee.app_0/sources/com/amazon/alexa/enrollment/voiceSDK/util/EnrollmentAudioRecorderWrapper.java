package com.amazon.alexa.enrollment.voiceSDK.util;

import com.amazon.alexa.enrollment.voiceSDK.audio.EnrollmentAudioRecordingRunnable;
import com.amazon.alexa.enrollment.voiceSDK.audio.EnrollmentAudioSinkWrapper;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
/* loaded from: classes7.dex */
public class EnrollmentAudioRecorderWrapper {
    public EnrollmentAudioRecordingRunnable getRecordingRunnable(EnrollmentAudioSinkWrapper enrollmentAudioSinkWrapper, EnrollmentEventBus enrollmentEventBus) {
        return new EnrollmentAudioRecordingRunnable(enrollmentAudioSinkWrapper, enrollmentEventBus);
    }
}
