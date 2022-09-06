package com.amazon.device.utils.det;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class DetEndpointConfig {
    private static final Map<Domain, String> endPointMap = new HashMap();

    public static void addEndPoint(Domain domain, String str) {
        endPointMap.put(domain, str);
    }

    public static String getServiceEndpoint(Domain domain) {
        return endPointMap.get(domain);
    }
}
