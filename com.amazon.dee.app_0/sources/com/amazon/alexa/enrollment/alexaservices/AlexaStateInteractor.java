package com.amazon.alexa.enrollment.alexaservices;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class AlexaStateInteractor implements AlexaServicesConnection.ConnectionListener {
    private static final int ALEXA_SERVICES_CONNECTION_RETRY_TIMES_LIMIT = 2;
    private static final String TAG = GeneratedOutlineSupport1.outline39(AlexaStateInteractor.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private final AlexaServicesConnection alexaServicesConnection;
    private int alexaServicesConnectionRetryCount = 0;
    private final AlexaStateTracker alexaStateTracker;
    private final Context context;
    private final EnrollmentMetricsRecorder metricsRecorder;

    @Inject
    public AlexaStateInteractor(Context context, AlexaStateTracker alexaStateTracker, EnrollmentMetricsRecorder enrollmentMetricsRecorder, AlexaServicesConnection alexaServicesConnection) {
        this.context = context;
        this.alexaStateTracker = alexaStateTracker;
        this.metricsRecorder = enrollmentMetricsRecorder;
        this.alexaServicesConnection = alexaServicesConnection;
    }

    public void connectAlexaServices() {
        if (!this.alexaServicesConnection.isConnected()) {
            Log.i(TAG, "connecting to Alexa services");
            this.alexaServicesConnection.connect();
        }
    }

    void deregister() {
        AlexaServices.Recognizer.deregisterListener(this.alexaServicesConnection, this.alexaStateTracker);
    }

    public AlexaServicesConnection getAlexaServicesConnection() {
        return this.alexaServicesConnection;
    }

    public AlexaState getAlexaState() {
        return this.alexaStateTracker.getAlexaState();
    }

    public void initialize() {
        Log.i(TAG, "inside initialize for Alexa Services for enrollment");
        this.alexaServicesConnection.registerListener(this);
        this.alexaServicesConnection.connect();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        Log.i(TAG, "inside onConnected");
        this.alexaServicesConnectionRetryCount = 0;
        this.metricsRecorder.recordUserViewInteraction(MetricsConstants.OperationalMetrics.ALEXA_SERVICES_CONNECTED);
        AlexaServices.Recognizer.registerListener(this.alexaServicesConnection, this.alexaStateTracker);
        Log.i(TAG, "registered listener for Alexa state change");
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        String str2 = TAG;
        Log.i(str2, "inside onConnectingFailed with reason: " + alexaConnectingFailedReason + " and with message: " + str);
        if (alexaConnectingFailedReason == AlexaConnectingFailedReason.TIMEOUT || alexaConnectingFailedReason == AlexaConnectingFailedReason.UNKNOWN) {
            this.metricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.ALEXA_SERVICES_CONNECTION_RETRY);
            retryAlexaServicesConnection();
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        Log.i(TAG, "inside onDisconnected");
        this.metricsRecorder.recordUserViewInteraction(MetricsConstants.OperationalMetrics.ALEXA_SERVICES_DISCONNECTED);
    }

    public void release() {
        Log.i(TAG, "releasing the recognizers from Alexa Services");
        deregister();
        this.alexaServicesConnection.deregisterListener(this);
        this.alexaServicesConnection.disconnect();
    }

    @VisibleForTesting
    void retryAlexaServicesConnection() {
        Log.i(TAG, "inside retryAlexaServicesConnection");
        this.alexaServicesConnectionRetryCount++;
        if (this.alexaServicesConnectionRetryCount > 2 || this.alexaServicesConnection.isConnected()) {
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("retrying connection handler with retry count: ");
        outline107.append(this.alexaServicesConnectionRetryCount);
        Log.i(str, outline107.toString());
        this.alexaServicesConnection.connect();
    }
}
