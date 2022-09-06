package com.amazon.alexa.featureservice.data.registry;

import java.util.Arrays;
import java.util.HashSet;
/* loaded from: classes7.dex */
public final class FeatureListProvider {
    private FeatureListProvider() {
    }

    public static String[] getFeatureList() {
        HashSet hashSet = new HashSet(Arrays.asList(NativeFeatureRegistry.NATIVE_FEATURES));
        hashSet.addAll(Arrays.asList(ElementsFeatureRegistry.ELEMENTS_FEATURES));
        return (String[]) hashSet.toArray(new String[0]);
    }

    public static String[] getInstantlyPropagatedFeatureList() {
        return (String[]) new HashSet(Arrays.asList(NativeFeatureRegistry.INSTANTLY_PROPAGATE_FEATURES)).toArray(new String[0]);
    }
}
