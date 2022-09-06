package com.amazon.alexa.featureservice.constants;
/* loaded from: classes7.dex */
public final class FeatureConstants {
    public static final String APP_AUTHENTICATED_EVENT = "app:authenticated";
    public static final String CONTROL = "C";
    public static final String CONTROL_DEFAULT = "C_DEFAULT";
    public static final String FEATURES_UPDATED_EVENT = "featureServiceV2:featuresUpdated";
    public static final String FEATURES_UPDATED_INTERNAL_EVENT = "featureServiceV2:internal:featuresUpdated";
    public static final long FEATURE_NEVER_REFRESHED = -1;
    public static final String FEATURE_SERVICE_ENDPOINT = "/api/features";
    public static final String FEATURE_SERVICE_V2_TAG = "DefaultFeatureServiceV2";
    public static final String FEATURE_SERVICE_V2_WEBLAB = "FEATURE_SERVICE_V2_ANDROID";
    public static final String RECORD_TRIGGER_ENDPOINT = "/api/features/triggers";
    public static final long REFRESH_FEATURE_FREQUENCY_IN_MILLISECONDS = 3600000;
    public static final long SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS = 600000;
    public static final String T1 = "T1";
    public static final String T1_DEFAULT = "T1_DEFAULT";

    /* loaded from: classes7.dex */
    public static final class Identifiers {
        public static final String PREFETCH_NEWLY_ADDED_FEATURES = "prefetchNewlyAddedFeatures";
        public static final String REFRESH_FEATURES_IF_STALE = "refreshFeaturesIfStale";
    }

    /* loaded from: classes7.dex */
    public static final class Network {
        public static final String ALLOCATION_ID = "allocationId";
        public static final String RECORD_STATUS = "recordStatus";
        public static final String SHOULD_RECORD_TRIGGER = "shouldRecordTrigger";
        public static final String TIME_USED = "timeUsed";
        public static final String TREATMENT = "treatment";
        public static final String TREATMENTS = "treatments";
        public static final String TREATMENT_USED = "treatmentUsed";
        public static final String TRIGGER_ON_USE = "triggerOnUse";
    }

    private FeatureConstants() {
    }
}
