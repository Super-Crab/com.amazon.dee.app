package com.amazon.alexa.handsfree.uservoicerecognition.ui;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class EnrollmentResumeStateStore {
    private static final String ENROLLMENT_COMPLETE = "enrollmentComplete";
    private static final String INSTANT_OF_LAST_FOCUS = "instantOfLastFocusMillis";
    private static final String SHARED_PREFS_FILENAME = "com.amazon.alexa.handsfree.uvr.EnrollmentResumeStateStore";
    private final SharedPreferences mSharedPreferences;

    public EnrollmentResumeStateStore(@NonNull Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILENAME, 0);
    }

    public boolean getEnrollmentComplete() {
        return this.mSharedPreferences.getBoolean(ENROLLMENT_COMPLETE, false);
    }

    public long getInstantOfLastFocus() {
        return this.mSharedPreferences.getLong(INSTANT_OF_LAST_FOCUS, 0L);
    }

    public void setEnrollmentComplete() {
        GeneratedOutlineSupport1.outline143(this.mSharedPreferences, ENROLLMENT_COMPLETE, true);
    }

    public void setInstantOfLastFocus(long j) {
        this.mSharedPreferences.edit().putLong(INSTANT_OF_LAST_FOCUS, j).apply();
    }
}
