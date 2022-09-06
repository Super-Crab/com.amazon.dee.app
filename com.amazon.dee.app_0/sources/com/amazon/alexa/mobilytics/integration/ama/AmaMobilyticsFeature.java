package com.amazon.alexa.mobilytics.integration.ama;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes9.dex */
public @interface AmaMobilyticsFeature {
    public static final Set<String> AMA_MOBILYTICS_FEATURE_SET = Collections.unmodifiableSet(new HashSet(Arrays.asList("MOBILYTICS_PMET_INTEGRATION_ANDROID", "MOBILYTICS_SESSIONIZATION_ANDROID", "ALEXA_ANDROID_MOBILYTICS_HIGH_PRIORITY_METRICS", "ALEXA_ANDROID_MOBILYTICS_PROTOBUF", "ALEXA_MOBILYTICS_DATA_REGIONS_ANDROID")));
    public static final String DATA_REGIONS = "ALEXA_MOBILYTICS_DATA_REGIONS_ANDROID";
    public static final String HIGH_PRIORITY_METRICS = "ALEXA_ANDROID_MOBILYTICS_HIGH_PRIORITY_METRICS";
    public static final String PMET_INTEGRATION = "MOBILYTICS_PMET_INTEGRATION_ANDROID";
    public static final String PROTOBUF = "ALEXA_ANDROID_MOBILYTICS_PROTOBUF";
    public static final String SESSIONIZATION = "MOBILYTICS_SESSIONIZATION_ANDROID";
}
