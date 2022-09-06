package com.amazon.alexa.routing.api;

import java.util.Set;
/* loaded from: classes10.dex */
public interface RouteFeatureGroup {
    String[] getFeatures();

    String getName();

    Route[] getRoutes(Set<String> set);
}
