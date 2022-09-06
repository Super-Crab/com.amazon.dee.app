package com.amazonaws.mobileconnectors.iot;

import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
/* loaded from: classes13.dex */
public final class AwsIotEndpointUtility {
    private static final int ENDPOINT_CN_ATS_SPLIT_SIZE = 7;
    private static final int ENDPOINT_REGION_OFFSET = 2;

    private AwsIotEndpointUtility() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Region getRegionFromIotEndpoint(String str) {
        try {
            String[] splitEndpoint = splitEndpoint(stripPort(str));
            return Region.getRegion(Regions.fromName(splitEndpoint[splitEndpoint.length == 7 ? (char) 3 : (char) 2]));
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse region from endpoint.", e);
        }
    }

    private static String[] splitEndpoint(String str) {
        return str.split(ArcusConfig.PATH_SEPARATOR);
    }

    private static String stripPort(String str) {
        return str.split(":")[0];
    }
}
