package com.amazon.alexa.sensor.location;

import androidx.annotation.NonNull;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public abstract class RequestingFeatures {
    private static final String FEATURE_LOCATION_PROVIDER = "feature_location_provider";
    private static final String FEATURE_REACT_NATIVE_BRIDGE = "feature_react_native_bridge";
    private static final String FEATURE_ELEMENTS_MAP_VIEW = "feature_elements_map_view";
    static final Map<String, Feature> AUTHORIZED_FEATURES = ImmutableMap.of(FEATURE_REACT_NATIVE_BRIDGE, new Feature(FEATURE_REACT_NATIVE_BRIDGE, ImmutableSet.of(0), false), FEATURE_ELEMENTS_MAP_VIEW, new Feature(FEATURE_ELEMENTS_MAP_VIEW, ImmutableSet.of(0), false), "feature_location_provider", new Feature("feature_location_provider", ImmutableSet.of(0, 1, 2), false));

    /* loaded from: classes10.dex */
    public static class Feature {
        public final boolean allowTrackingAfterAppKills;
        public final Set<Integer> appStateRequirements;
        @NonNull
        public final String name;

        public Feature(@NonNull String str, Set<Integer> set, boolean z) {
            this.name = str;
            this.appStateRequirements = set == null ? Collections.emptySet() : set;
            this.allowTrackingAfterAppKills = z;
        }
    }
}
