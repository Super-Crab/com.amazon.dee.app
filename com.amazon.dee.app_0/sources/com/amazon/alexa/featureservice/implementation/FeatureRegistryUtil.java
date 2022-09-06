package com.amazon.alexa.featureservice.implementation;

import com.amazon.alexa.featureservice.data.registry.FeatureListProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
/* loaded from: classes7.dex */
public class FeatureRegistryUtil {
    private List<String> additionalFeatures = new ArrayList();

    public List<String> getInstantlyPropagatedFeatureList() {
        return Arrays.asList(FeatureListProvider.getInstantlyPropagatedFeatureList());
    }

    public List<String> getRegisteredFeatures() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(this.additionalFeatures);
        linkedHashSet.addAll(Arrays.asList(FeatureListProvider.getFeatureList()));
        return new ArrayList(linkedHashSet);
    }

    public void setAdditionalFeatures(List<String> list) {
        this.additionalFeatures = list;
    }
}
