package com.amazon.alexa.handsfree.protocols.callback;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class CallbackErrorMetadata {
    public static final int MISSING_ERROR_CODE = -1;
    public static final String MISSING_ERROR_MESSAGE = "Error message missing";
    public static final int REMOTE_EXCEPTION_ERROR_CODE = -99;
    private static final String SEPARATOR = ":";
    private static final String TAG = "CallbackErrorMetadata";
    public static final String UVR_ENROLLER_CALL_CANCEL_ENROLLMENT = "UVREnrollerCancelEnrollment:%d%s";
    public static final String UVR_ENROLLER_CALL_CANCEL_UTTERANCE_TRAINING = "UVREnrollerCancelUtteranceTraining:%d%s";
    public static final String UVR_ENROLLER_CALL_ENABLE_UVR = "UVREnrollerEnableUVR:%d%s";
    public static final String UVR_ENROLLER_START_ENROLLMENT_ERROR = "UVREnrollerStartEnrollmentError:%d%s";
    private int errorCode;
    private String errorMessage;

    public CallbackErrorMetadata() {
        this(-1, MISSING_ERROR_MESSAGE);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getMetricName(@NonNull String str) {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112(str, ":");
        outline112.append(this.errorCode);
        return outline112.toString();
    }

    public Throwable getThrowable(@NonNull String str) {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112(str, ":");
        outline112.append(this.errorCode);
        outline112.append(":");
        outline112.append(this.errorMessage);
        return new Throwable(outline112.toString());
    }

    public CallbackErrorMetadata(int i) {
        this(i, MISSING_ERROR_MESSAGE);
    }

    public CallbackErrorMetadata(@NonNull String str) {
        this(-1, str);
    }

    public CallbackErrorMetadata(int i, @NonNull String str) {
        this.errorCode = i;
        this.errorMessage = str;
    }
}
