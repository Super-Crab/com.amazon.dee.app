package com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
/* loaded from: classes8.dex */
public class EnrollmentStateProvider {
    @VisibleForTesting
    static final String ENROLLMENT_SHARED_PREFERENCES_FILE_NAME = "com.amazon.alexa.handsfree.uservoicerecognition.Enrollment";
    private final SharedPreferences mSharedPreferences;
    private String mSharedPreferencesKey = UserInfo.DEFAULT_USER.getUserId();

    public EnrollmentStateProvider(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(ENROLLMENT_SHARED_PREFERENCES_FILE_NAME, 0);
    }

    public boolean getPreviousEnrollmentState(@NonNull UserInfo userInfo) {
        return this.mSharedPreferences.getBoolean(userInfo.getUserId(), false);
    }

    public void setEnrollmentState(boolean z) {
        this.mSharedPreferences.edit().putBoolean(this.mSharedPreferencesKey, z).apply();
    }

    public void setUserInfo(@NonNull UserInfo userInfo) {
        this.mSharedPreferencesKey = userInfo.getUserId();
    }
}
