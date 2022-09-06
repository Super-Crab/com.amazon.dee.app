package com.amazon.alexa.navigation.menu.service;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes9.dex */
public final class FeatureCheckService {
    private static FeatureCheckService instance;
    FeatureServiceV2 featureServiceV2;

    private FeatureCheckService(FeatureServiceV2 featureServiceV2) {
        this.featureServiceV2 = featureServiceV2;
    }

    public static FeatureCheckService getInstance() {
        if (instance == null) {
            instance = new FeatureCheckService((FeatureServiceV2) ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class).mo10268get());
        }
        return instance;
    }

    @VisibleForTesting
    static void resetForTest() {
        instance = null;
    }

    public String getAppOnlyCoachMarksTreatment() {
        return this.featureServiceV2.getTreatmentAndRecordTrigger("ALEXA_GROWTH_COACH_MARKS_APP_ONLY_ANDROID_EXP", "C_DEFAULT");
    }

    public boolean isAppOnlyCoachMarksExperimentEnabled() {
        return this.featureServiceV2.hasAccess("ALEXA_GROWTH_COACH_MARKS_APP_ONLY_ANDROID_LAUNCH", false);
    }

    public boolean isSeeMoreCoachMarkEnabled() {
        return this.featureServiceV2.hasAccess("COACH_MARKS_ANDROID", false);
    }
}
