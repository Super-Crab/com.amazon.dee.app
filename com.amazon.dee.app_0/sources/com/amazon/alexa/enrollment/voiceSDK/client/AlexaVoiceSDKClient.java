package com.amazon.alexa.enrollment.voiceSDK.client;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.UpdateTrainingPageUIEvent;
import com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class AlexaVoiceSDKClient {
    private static final String TAG = GeneratedOutlineSupport1.outline39(AlexaVoiceSDKClient.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private AlexaMobileFrameworkApis alexaMobileFrameworkApis = null;
    private final EnrollmentEventBus enrollmentEventBus;
    private final EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    private final EnrollmentUserSpeechProvider enrollmentUserSpeechProvider;

    @Inject
    public AlexaVoiceSDKClient(EnrollmentMetricsRecorder enrollmentMetricsRecorder, EnrollmentEventBus enrollmentEventBus, EnrollmentUserSpeechProvider enrollmentUserSpeechProvider) {
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
        this.enrollmentEventBus = enrollmentEventBus;
        this.enrollmentUserSpeechProvider = enrollmentUserSpeechProvider;
    }

    public void connect(Context context, AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        Log.i(TAG, "Attempting to connect Alexa Service");
        AlexaMobileFrameworkApis.enableAlexa(context);
        setAlexaMobileFrameworkApis(alexaMobileFrameworkApis);
        this.alexaMobileFrameworkApis.start();
        this.enrollmentUserSpeechProvider.setAlexaMobileFrameworkApisInstance(this.alexaMobileFrameworkApis);
        this.enrollmentUserSpeechProvider.register();
        Log.i(TAG, "Sending UpdateTrainingPageUIEvent to initiate enrollment session...");
        this.enrollmentEventBus.sendEvent(UpdateTrainingPageUIEvent.create(MetricsConstants.UserInteractionMetrics.ENROLLMENT_STARTED_FROM_USER_SPEECH_PROVIDER));
    }

    public void disConnect() {
        Log.i(TAG, "Disconnecting resources...!");
        this.enrollmentUserSpeechProvider.deRegister();
        this.alexaMobileFrameworkApis.stop();
    }

    public void requestDialog(String str) {
        GeneratedOutlineSupport1.outline163("Requesting dialog for current enrollment state ", str, TAG);
        this.enrollmentMetricsRecorder.recordCounter(str);
        this.enrollmentUserSpeechProvider.requestDialog();
    }

    @VisibleForTesting
    void setAlexaMobileFrameworkApis(AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        this.alexaMobileFrameworkApis = alexaMobileFrameworkApis;
    }
}
