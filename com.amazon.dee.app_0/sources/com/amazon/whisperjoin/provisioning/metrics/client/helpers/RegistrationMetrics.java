package com.amazon.whisperjoin.provisioning.metrics.client.helpers;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.provisioning.metrics.client.MetricHelper;
import com.amazon.whisperjoin.provisioning.registration.RegistrationDetails;
/* loaded from: classes13.dex */
public class RegistrationMetrics {
    static final String REGISTRATION_FAILED_LAST_FAILURE_CAUSE = "RegistrationFailureLastCause";
    static final String REGISTRATION_FAILED_OTHER_METRIC = "RegistrationFailureOther";
    static final String REGISTRATION_FAILED_UNKNOWN_CODE_METRIC = "RegistrationFailureUnknownCode";
    static final String REGISTRATION_FAILURE_ERROR_MESSAGE = "RegistrationFailureErrorMessage";
    static final String REGISTRATION_FAILURE_HTTP_ERROR_METRIC = "RegistrationFailureHttpError";
    static final String REGISTRATION_FAILURE_SERVER_ERROR_METRIC = "RegistrationFailureServerError";
    static final String REGISTRATION_FAILURE_SERVER_NOT_REACHABLE_METRIC = "RegistrationFailureServerUnreachable";
    static final String REGISTRATION_FAILURE_TOKEN_EXPIRED_METRIC = "RegistrationFailureTokenExpired";
    static final String REGISTRATION_FAILURE_TOKEN_INVALID_METRIC = "RegistrationFailureTokenInvalid";
    static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    private final MetricHelper mMetricHelper;
    volatile boolean mOnRegistrationStarted = false;

    public RegistrationMetrics(MetricHelper metricHelper) {
        this.mMetricHelper = metricHelper;
    }

    public void onRegistrationFailure(RegistrationDetails registrationDetails) {
        if (!this.mOnRegistrationStarted) {
            return;
        }
        this.mOnRegistrationStarted = false;
        this.mMetricHelper.recordCounter(REGISTRATION_SUCCESS, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, new Object[0]);
        this.mMetricHelper.recordString(REGISTRATION_FAILED_LAST_FAILURE_CAUSE, registrationDetails.getState(), new Object[0]);
        int intValue = registrationDetails.getState().intValue();
        if (intValue == -5) {
            this.mMetricHelper.recordCounter(REGISTRATION_FAILURE_SERVER_ERROR_METRIC, 1.0d, new Object[0]);
            if (registrationDetails.getHttpCode().intValue() >= 400) {
                this.mMetricHelper.recordCounter(REGISTRATION_FAILURE_HTTP_ERROR_METRIC, 1.0d, Integer.toString(registrationDetails.getHttpCode().intValue()));
            }
        } else if (intValue == -4) {
            this.mMetricHelper.recordCounter(REGISTRATION_FAILURE_SERVER_NOT_REACHABLE_METRIC, 1.0d, new Object[0]);
        } else if (intValue == -3) {
            this.mMetricHelper.recordCounter(REGISTRATION_FAILURE_TOKEN_EXPIRED_METRIC, 1.0d, new Object[0]);
        } else if (intValue == -2) {
            this.mMetricHelper.recordCounter(REGISTRATION_FAILURE_TOKEN_INVALID_METRIC, 1.0d, new Object[0]);
        } else if (intValue != -1) {
            this.mMetricHelper.recordCounter(REGISTRATION_FAILED_UNKNOWN_CODE_METRIC, 1.0d, new Object[0]);
        } else {
            this.mMetricHelper.recordCounter(REGISTRATION_FAILED_OTHER_METRIC, 1.0d, new Object[0]);
        }
        String message = registrationDetails.getMessage();
        if (message == null || message.length() <= 0) {
            return;
        }
        this.mMetricHelper.recordString(REGISTRATION_FAILURE_ERROR_MESSAGE, message, new Object[0]);
    }

    public void onRegistrationStarted() {
        this.mOnRegistrationStarted = true;
    }

    public void onRegistrationSuccess() {
        if (!this.mOnRegistrationStarted) {
            return;
        }
        this.mOnRegistrationStarted = false;
        this.mMetricHelper.recordCounter(REGISTRATION_SUCCESS, 1.0d, new Object[0]);
    }
}
