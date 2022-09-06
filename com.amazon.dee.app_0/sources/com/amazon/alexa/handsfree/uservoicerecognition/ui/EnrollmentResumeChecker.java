package com.amazon.alexa.handsfree.uservoicerecognition.ui;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
/* loaded from: classes8.dex */
public class EnrollmentResumeChecker {
    private final TimeInstantProvider mCurrentTimeMillisSupplier;
    private final EnrollmentResumeStateStore mEnrollmentResumeStateStore;
    private final long mResumeAllowedMaxPeriodMillis;
    private final UVRVendorSettings mUVRVendorSettings;

    /* loaded from: classes8.dex */
    public static class TimeInstantProvider {
        long getTimeInstantMillis() {
            return System.currentTimeMillis();
        }
    }

    public EnrollmentResumeChecker(@NonNull TimeInstantProvider timeInstantProvider, @NonNull EnrollmentResumeStateStore enrollmentResumeStateStore, @NonNull UVRVendorSettings uVRVendorSettings, int i) {
        this.mEnrollmentResumeStateStore = enrollmentResumeStateStore;
        this.mCurrentTimeMillisSupplier = timeInstantProvider;
        this.mUVRVendorSettings = uVRVendorSettings;
        this.mResumeAllowedMaxPeriodMillis = i * 1000;
    }

    public boolean canResumeEnrollment() {
        return this.mUVRVendorSettings.isEnrollmentResumeSupported() && this.mEnrollmentResumeStateStore.getInstantOfLastFocus() + this.mResumeAllowedMaxPeriodMillis >= this.mCurrentTimeMillisSupplier.getTimeInstantMillis();
    }

    public void resetResumeClock() {
        this.mEnrollmentResumeStateStore.setInstantOfLastFocus(this.mCurrentTimeMillisSupplier.getTimeInstantMillis());
    }
}
