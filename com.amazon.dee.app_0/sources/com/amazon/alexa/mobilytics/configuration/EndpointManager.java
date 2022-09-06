package com.amazon.alexa.mobilytics.configuration;

import com.amazonaws.regions.Regions;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public interface EndpointManager {
    List<Endpoint> dcmEndpoints();

    List<Map<Regions, Endpoint>> endpoints();

    void initialize();
}
