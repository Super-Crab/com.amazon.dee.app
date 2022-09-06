package com.amazon.alexa.handsfree.protocols.uservoicerecognition.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import java.util.List;
/* loaded from: classes8.dex */
public interface UVRVendorSettings {
    void disableAntiSpoofVerification(@NonNull ResponseCallback responseCallback);

    void disableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback);

    void enableAntiSpoofVerification(@NonNull ResponseCallback responseCallback);

    void enableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback);

    @NonNull
    List<UserInfo> getEnrolledUsers();

    boolean isAntiSpoofEnabled();

    boolean isEnrollmentResumeSupported();

    boolean isUVRAvailable();

    boolean isUVREnrolled(@NonNull UserInfo userInfo);

    boolean isUVRMandatory();

    void removeUVRModel(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback);

    boolean shouldShowContinueConfirmationDialog();
}
