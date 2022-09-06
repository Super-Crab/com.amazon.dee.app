package com.amazon.alexa.featureservice.util;

import com.amazon.latencyinfra.TimelineInputArgument;
/* loaded from: classes7.dex */
public final class LatencyUtil {
    private static final String LATENCY_NAMESPACE_FEATURE_SERVICE = "FeatureService";
    private static final int LATENCY_TAG = 0;
    public static final String LATENCY_TIMELINE_PREFETCH = "prefetch";
    public static final String LATENCY_TIMELINE_PREFETCH_NAMESPACE = "prefetchNamespace";

    /* loaded from: classes7.dex */
    public @interface LatencyTimeline {
    }

    private LatencyUtil() {
    }

    public static TimelineInputArgument.Builder getTimelineArgumentBuilder(@LatencyTimeline String str) {
        return new TimelineInputArgument.Builder().withTimelineName(str).withNamespace(LATENCY_NAMESPACE_FEATURE_SERVICE).withTag(0).withLogOption(true);
    }
}
