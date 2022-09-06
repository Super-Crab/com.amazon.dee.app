package com.amazon.alexa.presence;

import com.amazon.alexa.protocols.features.FeatureQuery;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class FeatureGating {
    private static final Logger LOG = LoggerFactory.getLogger(FeatureGating.class);
    private static final FeatureGating INSTANCE = new FeatureGating();

    public static boolean isEnabled(String str) {
        return INSTANCE.isFeatureEnabled(str);
    }

    public boolean isFeatureEnabled(String str) {
        FeatureQuery featureQuery = (FeatureQuery) GeneratedOutlineSupport1.outline21(FeatureQuery.class);
        if (featureQuery == null) {
            LOG.info("Unable to check feature status {}, FeatureQuery is currently unavailable.", str);
            return false;
        }
        boolean isActive = featureQuery.isActive(str);
        LOG.debug("Feature {} is {}.", str, isActive ? "active" : "inactive");
        return isActive;
    }
}
