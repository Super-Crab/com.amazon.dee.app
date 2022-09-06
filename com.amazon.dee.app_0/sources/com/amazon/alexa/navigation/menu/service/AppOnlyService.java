package com.amazon.alexa.navigation.menu.service;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes9.dex */
public final class AppOnlyService {
    private static AppOnlyService instance;
    private FeatureCheckService featureCheckService = FeatureCheckService.getInstance();
    private Boolean isAppOnly;
    private SharedPreferences sharedPreferences;

    private AppOnlyService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private boolean getAppOnlyStatus() {
        if (this.isAppOnly == null) {
            this.isAppOnly = Boolean.valueOf(this.sharedPreferences.getBoolean("isAppOnly", false));
        }
        return this.isAppOnly.booleanValue();
    }

    public static AppOnlyService getInstance(Context context) {
        if (instance == null) {
            instance = new AppOnlyService(context.getSharedPreferences("AppOnlyStatus", 0));
        }
        return instance;
    }

    @VisibleForTesting
    static void resetForTest() {
        instance = null;
    }

    public boolean shouldShowAppOnlyCoachMarks() {
        return getAppOnlyStatus() && this.featureCheckService.isAppOnlyCoachMarksExperimentEnabled();
    }
}
