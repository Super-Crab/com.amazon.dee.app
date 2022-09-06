package com.amazon.latencyinfra;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes12.dex */
public class LogReporter extends DefaultLatencyReporter {
    public LogReporter() {
        super(LatencyReporterType.LOG);
    }

    @Override // com.amazon.latencyinfra.LatencyReporter
    public void report(LatencyReporterArgument latencyReporterArgument) {
        String str;
        Map<String, Long> events = latencyReporterArgument.getEvents();
        String metaData = latencyReporterArgument.getMetaData();
        String str2 = "";
        if (events == null || events.size() == 0) {
            str = str2;
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" events: ");
            outline107.append(events.toString());
            str = outline107.toString();
        }
        if (metaData != null) {
            str2 = GeneratedOutlineSupport1.outline72(" metaData: ", metaData);
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(" ");
        outline1072.append(latencyReporterArgument.getName());
        String sb = outline1072.toString();
        Log.i("[PERF PROFILE]", latencyReporterArgument.getNamespace() + str + str2 + sb + " " + latencyReporterArgument.getTimeInterval());
    }
}
