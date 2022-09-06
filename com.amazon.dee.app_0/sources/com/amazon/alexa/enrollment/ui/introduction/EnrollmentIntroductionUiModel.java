package com.amazon.alexa.enrollment.ui.introduction;
/* loaded from: classes7.dex */
public class EnrollmentIntroductionUiModel {
    private final boolean isAudioPermissionGranted;
    private final boolean isPersonEnrolled;

    public EnrollmentIntroductionUiModel(boolean z, boolean z2) {
        this.isPersonEnrolled = z;
        this.isAudioPermissionGranted = z2;
    }

    public boolean isAudioPermissionGranted() {
        return this.isAudioPermissionGranted;
    }

    public boolean isUserEligibleForEnrollment() {
        return !this.isPersonEnrolled;
    }
}
