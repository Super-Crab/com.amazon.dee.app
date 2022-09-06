package com.amazon.latencyinfra;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes12.dex */
public class DefaultLatencyInfra implements LatencyInfra {
    private static final String TAG = "DefaultLatencyInfra";
    private Set<String> blockedNamespace;
    private LatencyReporterSet defaultReporterSet;
    private boolean disabled;
    private Boolean isInternalBuild;
    private Long originDateInMills;
    private LatencyRecorder recorder;
    private long previousTimeFromOrigin = 0;
    private ConcurrentHashMap<String, Long> nameToStartTime = new ConcurrentHashMap<>();

    /* renamed from: com.amazon.latencyinfra.DefaultLatencyInfra$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$latencyinfra$SingleLatencyAction;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$latencyinfra$TimelineLatencyAction = new int[TimelineLatencyAction.values().length];

        static {
            try {
                $SwitchMap$com$amazon$latencyinfra$TimelineLatencyAction[TimelineLatencyAction.START_RECORD_TIMELINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$latencyinfra$TimelineLatencyAction[TimelineLatencyAction.RECORD_EVENT_IN_TIMELINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$latencyinfra$TimelineLatencyAction[TimelineLatencyAction.RECORD_CACHED_EVENTS_IN_TIMELINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$latencyinfra$TimelineLatencyAction[TimelineLatencyAction.END_TIMELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$latencyinfra$TimelineLatencyAction[TimelineLatencyAction.RECORD_TIMELINE_WITH_CACHED_START_AND_END_TIMESTAMP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$latencyinfra$SingleLatencyAction = new int[SingleLatencyAction.values().length];
            try {
                $SwitchMap$com$amazon$latencyinfra$SingleLatencyAction[SingleLatencyAction.RECORD_DURATION_FROM_APP_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$latencyinfra$SingleLatencyAction[SingleLatencyAction.RECORD_DURATION_FROM_CACHED_TIMESTAMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public DefaultLatencyInfra() {
        setOrigin(Long.valueOf(System.currentTimeMillis()));
        this.defaultReporterSet = new LatencyReporterSet();
        this.recorder = new LatencyRecorder(this.originDateInMills, this.defaultReporterSet);
        setDefaultReporter(new LogReporter());
        this.blockedNamespace = new HashSet();
    }

    private long getTimeFromLastSnapshot(LatencySnapshot latencySnapshot) {
        Long valueOf;
        if (this.previousTimeFromOrigin == 0) {
            valueOf = 0L;
        } else {
            valueOf = Long.valueOf((latencySnapshot.getTimeFromOrigin() - latencySnapshot.getDuration()) - this.previousTimeFromOrigin);
        }
        this.previousTimeFromOrigin = latencySnapshot.getTimeFromOrigin();
        return valueOf.longValue();
    }

    private void setOrigin(Long l) {
        if (this.originDateInMills == null) {
            this.originDateInMills = l;
        }
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void abandon(String str, String str2, Integer num) {
        this.recorder.abandon(str, str2, num);
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void blockNamespace(String str) {
        this.blockedNamespace.add(str);
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void disable() {
        this.disabled = true;
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void mark(LatencyMarker latencyMarker, String str) {
        if (!TextUtils.isEmpty(str)) {
            record(SingleLatencyAction.RECORD_DURATION_FROM_APP_START, latencyMarker.getEvent(str));
            return;
        }
        throw new IllegalArgumentException(latencyMarker.getMetric() + " must be reported with a valid namespace");
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void record(SingleLatencyAction singleLatencyAction, SingleEventInputArgument singleEventInputArgument) {
        if (this.disabled) {
            Log.i("[Latency Infra]:", "Latency Infra has been disabled");
            return;
        }
        String namespace = singleEventInputArgument.getNamespace();
        if (this.blockedNamespace.contains(namespace)) {
            GeneratedOutlineSupport1.outline163("Latency infra has been blocked for ", namespace, "[Latency Infra]:");
            return;
        }
        int ordinal = singleLatencyAction.ordinal();
        if (ordinal == 0) {
            this.recorder.record(namespace, singleEventInputArgument.getEventName(), singleEventInputArgument.getMetaData(), singleEventInputArgument.getOptions());
        } else if (ordinal != 1) {
            Log.w("[Latency Infra]:", "Invalid single latency action.");
        } else if (singleEventInputArgument.getEndTimestamp() == null) {
            Log.w("[Latency Infra]:", "end timestamp is not provided when recordingwith data.");
        } else {
            this.recorder.record(namespace, singleEventInputArgument.getEventName(), singleEventInputArgument.getEndTimestamp(), singleEventInputArgument.getMetaData(), singleEventInputArgument.getOptions());
        }
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void recordTimeline(TimelineLatencyAction timelineLatencyAction, TimelineInputArgument timelineInputArgument) {
        if (this.disabled) {
            Log.i("[Latency Infra]:", "Latency Infra has been disabled");
            return;
        }
        String namespace = timelineInputArgument.getNamespace();
        if (this.blockedNamespace.contains(namespace)) {
            GeneratedOutlineSupport1.outline163("Latency infra has been blocked for ", namespace, "[Latency Infra]:");
            return;
        }
        String timelineName = timelineInputArgument.getTimelineName();
        Integer tag = timelineInputArgument.getTag();
        int ordinal = timelineLatencyAction.ordinal();
        if (ordinal == 0) {
            this.recorder.startTimeline(namespace, timelineName, tag, timelineInputArgument.getMetaData(), timelineInputArgument.getOptions());
        } else if (ordinal == 1) {
            if (timelineInputArgument.getEventName() == null) {
                Log.w("[Latency Infra]:", "event name for timeline " + timelineName + " is not provided.");
                return;
            }
            this.recorder.record(namespace, timelineInputArgument.getEventName(), timelineName, tag);
        } else if (ordinal == 2) {
            if (timelineInputArgument.getEvents() == null) {
                Log.w("[Latency Infra]: ", "events should be provided for timeline" + timelineName + "when calling with RecordEvents action.");
                return;
            }
            this.recorder.recordEvents(namespace, timelineName, tag, timelineInputArgument.getEvents());
        } else if (ordinal == 3) {
            this.recorder.endTimeline(namespace, timelineName, tag, timelineInputArgument.getMetaData(), timelineInputArgument.getOptions());
        } else if (ordinal != 4) {
            Log.w("[Latency Infra]:", "Invalid timeline latency action.");
        } else if (timelineInputArgument.getEndTimestamp() != null && timelineInputArgument.getStartTimestamp() != null) {
            this.recorder.recordTimeline(namespace, timelineName, timelineInputArgument.getStartTimestamp(), timelineInputArgument.getEvents(), timelineInputArgument.getEndTimestamp(), timelineInputArgument.getMetaData(), timelineInputArgument.getOptions());
        } else {
            Log.w("[Latency Infra]: ", "start and end timestamp should be provided for timeline" + timelineName + "when recording with data.");
        }
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void setDefaultReporter(DefaultLatencyReporter defaultLatencyReporter) {
        this.defaultReporterSet.addDefaultReporter(defaultLatencyReporter);
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void setDomainReporter(DomainLatencyReporter domainLatencyReporter) {
        this.defaultReporterSet.addDomainReporter(domainLatencyReporter);
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void setIsInternalBuild(Boolean bool) {
        if (this.isInternalBuild == null) {
            this.isInternalBuild = bool;
            this.recorder.setIsInternalBuild(bool.booleanValue());
        }
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void setTestID(String str) {
        this.defaultReporterSet.setTestID(str);
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void start(String str) {
        if (this.nameToStartTime.putIfAbsent(str, Long.valueOf(SystemClock.elapsedRealtime())) != null) {
            Log.i("[PERF PROFILE]", "Nested sections are not yet supported");
        }
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void stop(String str) {
        Long valueOf = Long.valueOf(SystemClock.elapsedRealtime());
        Long remove = this.nameToStartTime.remove(str);
        if (remove == null) {
            Log.i("[PERF PROFILE]", "Unmatched sections are no-ops");
        } else {
            Log.i("[PERF PROFILE]", String.format("%s %d", str, Long.valueOf(valueOf.longValue() - remove.longValue())));
        }
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void time(String str, Runnable runnable) {
        long currentTimeMillis = System.currentTimeMillis();
        runnable.run();
        long currentTimeMillis2 = System.currentTimeMillis();
        LatencySnapshot latencySnapshot = new LatencySnapshot(str, currentTimeMillis2 - currentTimeMillis, currentTimeMillis2 - this.originDateInMills.longValue());
        latencySnapshot.setTimeFromLastSnapshot(getTimeFromLastSnapshot(latencySnapshot));
        Log.i("[SNAPSHOT]", latencySnapshot.toString());
    }

    @Override // com.amazon.latencyinfra.LatencyInfra
    public void unblockNamespace(String str) {
        this.blockedNamespace.remove(str);
    }
}
