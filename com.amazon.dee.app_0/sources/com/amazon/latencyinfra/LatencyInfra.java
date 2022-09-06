package com.amazon.latencyinfra;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface LatencyInfra {
    void abandon(String str, String str2, Integer num);

    void blockNamespace(String str);

    void disable();

    void mark(@NonNull LatencyMarker latencyMarker, @NonNull String str);

    void record(SingleLatencyAction singleLatencyAction, SingleEventInputArgument singleEventInputArgument);

    void recordTimeline(TimelineLatencyAction timelineLatencyAction, TimelineInputArgument timelineInputArgument);

    void setDefaultReporter(DefaultLatencyReporter defaultLatencyReporter);

    void setDomainReporter(DomainLatencyReporter domainLatencyReporter);

    void setIsInternalBuild(Boolean bool);

    void setTestID(String str);

    void start(String str);

    void stop(String str);

    void time(String str, Runnable runnable);

    void unblockNamespace(String str);
}
