package org.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes5.dex */
public class RTCStatsReport {
    private final Map<String, RTCStats> stats;
    private final long timestampUs;

    public RTCStatsReport(long j, Map<String, RTCStats> map) {
        this.timestampUs = j;
        this.stats = map;
    }

    public Map<String, RTCStats> getStatsMap() {
        return this.stats;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{ timestampUs: ");
        outline107.append(this.timestampUs);
        outline107.append(", stats: [\n");
        boolean z = true;
        for (RTCStats rTCStats : this.stats.values()) {
            if (!z) {
                outline107.append(",\n");
            }
            outline107.append(rTCStats);
            z = false;
        }
        outline107.append(" ] }");
        return outline107.toString();
    }
}
