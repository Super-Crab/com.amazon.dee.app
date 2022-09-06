package com.amazon.deecomms.ndt.model;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public enum SupportedFeature {
    InboundDropIn("inbound_drop_in"),
    Unknown("unknown");
    
    private static final Map<String, SupportedFeature> lookupMap = new HashMap();
    private final String featureLabel;

    static {
        SupportedFeature[] values;
        for (SupportedFeature supportedFeature : values()) {
            lookupMap.put(supportedFeature.getFeatureLabel(), supportedFeature);
        }
    }

    SupportedFeature(String str) {
        this.featureLabel = str;
    }

    public static SupportedFeature lookup(String str) {
        SupportedFeature supportedFeature = Unknown;
        return (str == null || !lookupMap.containsKey(str.toLowerCase())) ? supportedFeature : lookupMap.get(str);
    }

    public String getFeatureLabel() {
        return this.featureLabel;
    }
}
