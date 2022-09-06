package com.amazon.dee.app.services.features;

import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureConstraints;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class DefaultFeatureConstraints implements FeatureConstraints {
    private final Map<String, FeatureFilter> constraints;
    private final EnvironmentService environment;

    public DefaultFeatureConstraints(Set<FeatureFilter> set, EnvironmentService environmentService) {
        this.constraints = initializeConstraintsMap(set);
        this.environment = environmentService;
    }

    private void assertNoFilterForFeature(Map<String, FeatureFilter> map, String str) {
        if (!map.containsKey(str)) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("There is already a filter registered for ", str));
    }

    private boolean hasAccessTo(UserIdentity userIdentity, String str, Set<String> set) {
        FeatureFilter featureFilter = this.constraints.get(str);
        if (featureFilter != null) {
            return featureFilter.hasAccess(userIdentity, str, set);
        }
        return true;
    }

    private Map<String, FeatureFilter> initializeConstraintsMap(Set<FeatureFilter> set) {
        HashMap hashMap = new HashMap();
        for (FeatureFilter featureFilter : set) {
            for (String str : featureFilter.targetedFeatures()) {
                assertNoFilterForFeature(hashMap, str);
                hashMap.put(str, featureFilter);
            }
        }
        return hashMap;
    }

    @Override // com.amazon.alexa.protocols.features.FeatureConstraints
    public Set<String> apply(UserIdentity userIdentity, String[] strArr) {
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        hashSet.add("COMMS_AVAILABILITY");
        HashSet hashSet2 = new HashSet();
        for (String str : hashSet) {
            if (hasAccessTo(userIdentity, str, hashSet)) {
                hashSet2.add(str);
            }
        }
        if (this.environment.isDevelopmentFabric()) {
            hashSet2.add("ALEXA_BETA");
            hashSet2.add("ALEXA_FRIENDS_AND_FAMILY");
            hashSet2.add("ALEXA_MOBILE_BETA");
        }
        return hashSet2;
    }
}
