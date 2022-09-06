package com.amazon.alexa.comms.mediaInsights.service.factories;

import android.content.Context;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.NetworkDetailsCollector;
/* loaded from: classes6.dex */
public class NetworkDetailsCollectorFactory {
    public static final String NETOWRK_DETAILS_SHARED_PREF_FILE_NAME = "com.amazon.alexa.comms.mediaInsights.service.NETWORK_DETAILS";

    public static NetworkDetailsCollector create(Context context) {
        return new NetworkDetailsCollector(context, NETOWRK_DETAILS_SHARED_PREF_FILE_NAME);
    }
}
