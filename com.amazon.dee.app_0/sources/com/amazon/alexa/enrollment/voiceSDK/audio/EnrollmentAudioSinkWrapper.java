package com.amazon.alexa.enrollment.voiceSDK.audio;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.EnrollmentTerminateEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes7.dex */
public class EnrollmentAudioSinkWrapper {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentAudioSinkWrapper.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private AlexaAudioSink alexaAudioSink;
    private OutputStream alexaAudioSinkStream;
    private EnrollmentEventBus enrollmentEventBus;

    public EnrollmentAudioSinkWrapper(EnrollmentEventBus enrollmentEventBus) {
        try {
            this.alexaAudioSink = new AlexaAudioSink();
            this.enrollmentEventBus = enrollmentEventBus;
        } catch (IOException unused) {
            enrollmentEventBus.sendEvent(EnrollmentTerminateEvent.create(MetricsConstants.UserInteractionMetrics.AUDIO_SINK_CREATION_FAILED));
        }
    }

    private boolean isAlexaAudioSinkAvailable() {
        AlexaAudioSink alexaAudioSink = this.alexaAudioSink;
        return alexaAudioSink != null && alexaAudioSink.isWriteable();
    }

    @NonNull
    public AlexaAudioSink getAudioSink() {
        return this.alexaAudioSink;
    }

    public synchronized void recordEnrollmentAudio(byte[] bArr, int i) {
        if (!isAlexaAudioSinkAvailable()) {
            return;
        }
        try {
            this.alexaAudioSinkStream = this.alexaAudioSink.openForWriting();
            this.alexaAudioSinkStream.write(bArr, 0, i);
        } catch (IOException e) {
            this.alexaAudioSink.abandon();
            Log.e(TAG, "Exception in recordEnrollmentAudio: ", e);
            this.enrollmentEventBus.sendEvent(EnrollmentTerminateEvent.create(MetricsConstants.UserInteractionMetrics.AUDIO_SINK_STREAM_WRITE_FAILED));
        }
    }

    public synchronized void resetAudioSink() {
        try {
            this.alexaAudioSinkStream.close();
            this.alexaAudioSink.abandon();
            this.alexaAudioSink = new AlexaAudioSink();
        } catch (IOException unused) {
            this.enrollmentEventBus.sendEvent(EnrollmentTerminateEvent.create(MetricsConstants.UserInteractionMetrics.AUDIO_SINK_RESET_FAILED));
        }
    }
}
