package com.amazon.alexa.growth.metrics;

import com.amazon.alexa.growth.coachmark.Utils;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.TimelineInputArgument;
import com.amazon.latencyinfra.TimelineLatencyAction;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public class OEMetricsRecorder {
    private static final String APP_COMPONENT = "AndroidCoachMarks";
    private static final String RENDER_TIME_EVENT_NAME_SUFFIX = "RenderTime";
    private final Provider<LatencyInfra> latencyInfraProvider;

    public OEMetricsRecorder(Provider<LatencyInfra> provider) {
        this.latencyInfraProvider = provider;
    }

    public static TimelineInputArgument createRenderTimeEvent(String[] strArr) {
        String generateMetricEventName = Utils.generateMetricEventName(strArr);
        TimelineInputArgument.Builder withNamespace = new TimelineInputArgument.Builder().withMetricsOption(true).withLogOption(true).withCustomerOption(true).withNamespace(APP_COMPONENT);
        return withNamespace.withTimelineName(generateMetricEventName + "." + RENDER_TIME_EVENT_NAME_SUFFIX).build();
    }

    public void endEventTimer(TimelineInputArgument timelineInputArgument) {
        this.latencyInfraProvider.mo10268get().recordTimeline(TimelineLatencyAction.END_TIMELINE, timelineInputArgument);
    }

    public void startEventTimer(TimelineInputArgument timelineInputArgument) {
        this.latencyInfraProvider.mo10268get().recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, timelineInputArgument);
    }
}
