package amazon.speech.simclient.metrics.util;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class HighPriorityMetricsWhiteList {
    private static Set<String> whiteList = new HashSet();

    static {
        whiteList.add(AccessoryMetricsConstants.USER_PERCEIVED_LATENCY_AUDIO);
        whiteList.add("UserPerceivedLatency.Visual");
        whiteList.add("UserPerceivedLatency.Audio.DeviceProcessing");
    }

    public static boolean isHighPriorityMetric(String str) {
        return whiteList.contains(str);
    }
}
