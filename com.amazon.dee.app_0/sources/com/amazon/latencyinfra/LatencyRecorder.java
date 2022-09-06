package com.amazon.latencyinfra;

import android.util.Log;
import com.amazon.latencyinfra.LatencyReporterArgument;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes12.dex */
class LatencyRecorder {
    private static final int MAX_NUM_OF_TIMELINE = 100;
    private LatencyReporterSet defaultReporterSet;
    private Boolean isInternalBuild;
    private Long originDateInMills;
    private Map<String, LatencyTimeline> timelineCache = Collections.synchronizedMap(new LinkedHashMap<String, LatencyTimeline>(100, 0.75f, true) { // from class: com.amazon.latencyinfra.LatencyRecorder.1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, LatencyTimeline> entry) {
            return size() > 100;
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    public LatencyRecorder(Long l, LatencyReporterSet latencyReporterSet) {
        this.originDateInMills = l;
        this.defaultReporterSet = latencyReporterSet;
    }

    private LatencyReporterArgument buildReporterArgument(String str, LatencyType latencyType, String str2, String str3, Map<String, Long> map, Long l) {
        return new LatencyReporterArgument.Builder().withType(latencyType).withName(str2).withTimeInterval(l).withNamespace(str).withEvents(map).withMetaData(str3).build();
    }

    private Map<String, Long> calculateRelativeEvents(Map<String, Long> map, Long l) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), Long.valueOf(entry.getValue().longValue() - l.longValue()));
            }
        }
        return hashMap;
    }

    private Long getDurationFromOrigin() {
        return Long.valueOf(Long.valueOf(System.currentTimeMillis()).longValue() - this.originDateInMills.longValue());
    }

    public void abandon(String str, String str2, Integer num) {
        String str3 = str + str2 + num;
        synchronized (this.timelineCache) {
            if (this.timelineCache.get(str3) != null) {
                this.timelineCache.remove(str3);
            }
        }
    }

    public void endTimeline(String str, String str2, Integer num, String str3, LatencyRecorderOption latencyRecorderOption) {
        String str4 = str + str2 + num;
        synchronized (this.timelineCache) {
            LatencyTimeline latencyTimeline = this.timelineCache.get(str4);
            if (latencyTimeline == null) {
                Log.w("[Latency Infra]:", "timeline with " + str4 + " has not been created yet.");
                return;
            }
            this.timelineCache.remove(str4);
            LatencyRecorderOption options = latencyTimeline.getOptions();
            if (latencyRecorderOption != null) {
                options = latencyRecorderOption;
            }
            Map<String, Long> events = latencyTimeline.getEvents();
            Long stop = latencyTimeline.stop();
            if (str3 == null) {
                str3 = latencyTimeline.getDefaultMetaData();
            }
            reportWithOptions(buildReporterArgument(str, LatencyType.TIMELINE, str2, str3, events, stop), options);
        }
    }

    public void record(String str, String str2, String str3, LatencyRecorderOption latencyRecorderOption) {
        reportWithOptions(buildReporterArgument(str, LatencyType.SINGLE, str2, str3, null, getDurationFromOrigin()), latencyRecorderOption);
    }

    public void recordEvents(String str, String str2, Integer num, Map<String, Long> map) {
        String str3 = str + str2 + num;
        LatencyTimeline latencyTimeline = this.timelineCache.get(str3);
        if (latencyTimeline == null) {
            Log.w("[Latency Infra]:", "timeline with " + str3 + " has not been created yet.");
            return;
        }
        latencyTimeline.recordEvents(map);
    }

    public void recordTimeline(String str, String str2, Long l, Map<String, Long> map, Long l2, String str3, LatencyRecorderOption latencyRecorderOption) {
        Map<String, Long> calculateRelativeEvents = calculateRelativeEvents(map, l);
        Long valueOf = Long.valueOf(l2.longValue() - l.longValue());
        if (valueOf.longValue() < 0) {
            Log.w("[Latency Infra]: ", GeneratedOutlineSupport1.outline77("Illegal argument for recording timeline event: ", str2, " in namespace: ", str, " end timestamp is smaller than the start timestamp."));
        } else {
            reportWithOptions(buildReporterArgument(str, LatencyType.TIMELINE, str2, str3, calculateRelativeEvents, valueOf), latencyRecorderOption);
        }
    }

    public void reportWithOptions(LatencyReporterArgument latencyReporterArgument, LatencyRecorderOption latencyRecorderOption) {
        if (this.isInternalBuild.booleanValue() || latencyRecorderOption.isReportingForCustomerBuild()) {
            this.defaultReporterSet.reportWithOptions(latencyReporterArgument, latencyRecorderOption);
        }
    }

    public void setIsInternalBuild(boolean z) {
        this.isInternalBuild = Boolean.valueOf(z);
    }

    public void startTimeline(String str, String str2, Integer num, String str3, LatencyRecorderOption latencyRecorderOption) {
        String str4 = str + str2 + num;
        synchronized (this.timelineCache) {
            if (this.timelineCache.get(str4) != null) {
                Log.w("[Latency Infra]:", "timeline with tag: " + str2 + num + " has already been created.");
                return;
            }
            this.timelineCache.put(str4, new LatencyTimeline(str3, latencyRecorderOption));
        }
    }

    public void record(String str, String str2, Long l, String str3, LatencyRecorderOption latencyRecorderOption) {
        reportWithOptions(buildReporterArgument(str, LatencyType.SINGLE, str2, str3, null, Long.valueOf(l.longValue() - this.originDateInMills.longValue())), latencyRecorderOption);
    }

    public void record(String str, String str2, String str3, Integer num) {
        String str4 = str + str3 + num;
        LatencyTimeline latencyTimeline = this.timelineCache.get(str4);
        if (latencyTimeline == null) {
            Log.w("[Latency Infra]:", "timeline with " + str4 + " has not been created yet.");
            return;
        }
        latencyTimeline.recordEvent(str2);
    }
}
