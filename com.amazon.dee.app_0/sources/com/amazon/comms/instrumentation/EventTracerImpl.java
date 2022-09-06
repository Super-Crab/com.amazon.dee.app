package com.amazon.comms.instrumentation;

import amazon.speech.simclient.SimClient;
import android.content.Context;
import android.text.TextUtils;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracer.Interval;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.metrics.MetricsDestination;
import com.amazon.comms.metrics.TachyonMetricsFactory;
import com.amazon.comms.metrics.TachyonMetricsFactoryImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class EventTracerImpl<EEvent extends Enum<EEvent>, EInterval extends Enum<EInterval> & EventTracer.Interval<EEvent>> implements EventTracer<EEvent, EInterval> {
    static final MetricsDestination DEFAULT_METRICS_DESTINATION = MetricsDestination.ALL;
    static final String ID_METRICS_METADATA_KEY = "EventTracerId";
    private final Map<EInterval, Snapshot> mBeginSnapshots;
    private final Clocks mClock;
    private final Definitions<EEvent, EInterval> mDefinitions;
    private final Map<EInterval, Snapshot> mEndSnapshots;
    private final String mId;
    private final CommsLogger mLogger;
    private final MetricsRecorder mMetricsRecorder;
    private final List<EventTracerFactory.Pivot> mPivots;
    private final EventTracerFactory.Role mRole;
    private EventTracer mShadow;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class Definitions<EEvent extends Enum<EEvent>, EInterval extends Enum<EInterval> & EventTracer.Interval<EEvent>> {
        public final ImmutableList<EEvent> events;
        public final ImmutableList<EInterval> intervals;
        public final ImmutableMultimap<EEvent, Integer> intervalsByBeginEvent;
        public final ImmutableMultimap<EEvent, Integer> intervalsByEndEvent;

        /* JADX WARN: Incorrect types in method signature: ([TEEvent;[TEInterval;)V */
        public Definitions(Enum[] enumArr, Enum[] enumArr2) {
            if (enumArr != null) {
                if (enumArr2 != null) {
                    this.events = ImmutableList.copyOf(enumArr);
                    this.intervals = ImmutableList.copyOf(enumArr2);
                    ImmutableMultimap.Builder builder = new ImmutableMultimap.Builder();
                    ImmutableMultimap.Builder builder2 = new ImmutableMultimap.Builder();
                    for (int i = 0; i < enumArr2.length; i++) {
                        EventTracer.Interval interval = (EventTracer.Interval) enumArr2[i];
                        builder.mo7805put(interval.mo3233getBegin(), Integer.valueOf(i));
                        builder2.mo7805put(interval.mo3234getEnd(), Integer.valueOf(i));
                    }
                    this.intervalsByBeginEvent = builder.mo7802build();
                    this.intervalsByEndEvent = builder2.mo7802build();
                    return;
                }
                throw new IllegalArgumentException("intervals arg must have value");
            }
            throw new IllegalArgumentException("events arg must have value");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class MetricsRecorder {
        public final String DOMAIN;
        public final String SOURCE;
        private TachyonMetricsFactory mMetricsFactory;

        public MetricsRecorder(Context context, String str, String str2) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(str2)) {
                        this.mMetricsFactory = TachyonMetricsFactoryImpl.getInstance(context);
                        this.DOMAIN = str;
                        this.SOURCE = str2;
                        return;
                    }
                    throw new IllegalArgumentException("source arg must have a value");
                }
                throw new IllegalArgumentException("domain arg must have a value");
            }
            throw new IllegalArgumentException("context arg must have a value");
        }

        public MetricEvent createMetricEvent() {
            return this.mMetricsFactory.createMetricEvent(this.DOMAIN, this.SOURCE);
        }

        public void record(MetricEvent metricEvent) {
            record(metricEvent, EventTracerImpl.DEFAULT_METRICS_DESTINATION);
        }

        private void record(MetricEvent metricEvent, MetricsDestination metricsDestination) {
            this.mMetricsFactory.record(metricEvent, metricsDestination);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class Snapshot {
        public final long ERT;
        public final long MSE;

        public Snapshot(long j, long j2) {
            this.ERT = j;
            this.MSE = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class Span {
        public final Snapshot beginSnapshot;
        public final Snapshot endSnapshot;

        public Span(Snapshot snapshot, Snapshot snapshot2) {
            this.beginSnapshot = snapshot;
            this.endSnapshot = snapshot2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventTracerImpl(Definitions<EEvent, EInterval> definitions, String str, CommsLogger commsLogger, MetricsRecorder metricsRecorder, EventTracerFactory.Role role, List<EventTracerFactory.Pivot> list) {
        this(definitions, str, commsLogger, metricsRecorder, role, list, new ClocksImpl());
    }

    private String formatEvent(String str, EEvent eevent, Snapshot snapshot) {
        return String.format(Locale.getDefault(), "%s[%s %dERT %dMSE]", str, eevent.name(), Long.valueOf(snapshot.ERT), Long.valueOf(snapshot.MSE));
    }

    private String formatId() {
        return String.format(Locale.getDefault(), "ID[%s]", this.mLogger.sensitiveCallId(this.mId));
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/String;TEInterval;JJTEEvent;Lcom/amazon/comms/instrumentation/EventTracerImpl$Snapshot;TEEvent;Lcom/amazon/comms/instrumentation/EventTracerImpl$Snapshot;)Ljava/lang/String; */
    /* JADX WARN: Multi-variable type inference failed */
    private String formatInterval(String str, Enum r5, long j, long j2, Enum r10, Snapshot snapshot, Enum r12, Snapshot snapshot2) {
        return String.format(Locale.getDefault(), "%s[%s %dERT %dMSE] %s %s", str, r5.name(), Long.valueOf(j), Long.valueOf(j2), formatEvent("BEGIN", r10, snapshot), formatEvent("END", r12, snapshot2));
    }

    private synchronized EventTracer getShadow() {
        return this.mShadow;
    }

    private void markInternal(EEvent eevent, Snapshot snapshot) {
        Map<EInterval, Span> snapshotEvent = snapshotEvent(eevent, snapshot);
        CommsLogger commsLogger = this.mLogger;
        StringBuilder sb = new StringBuilder();
        sb.append(formatEvent(SimClient.EVENT_DATA_KEY_EVENT, eevent, snapshot));
        sb.append(" ");
        GeneratedOutlineSupport1.outline177(sb, formatId(), commsLogger);
        processSpans(snapshotEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void processSpans(Map<EInterval, Span> map) {
        Iterator<Map.Entry<EInterval, Span>> it2;
        MetricsRecorder metricsRecorder;
        Iterator<Map.Entry<EInterval, Span>> it3 = map.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry<EInterval, Span> next = it3.next();
            Enum r13 = (Enum) next.getKey();
            Span value = next.getValue();
            Snapshot snapshot = value.beginSnapshot;
            if (snapshot != null) {
                Snapshot snapshot2 = value.endSnapshot;
                long j = snapshot2.ERT - snapshot.ERT;
                StringBuilder sb = new StringBuilder();
                EventTracer.Interval interval = (EventTracer.Interval) r13;
                it2 = it3;
                sb.append(formatInterval("INTERVAL", r13, j, snapshot2.MSE - snapshot.MSE, (Enum) interval.mo3233getBegin(), value.beginSnapshot, (Enum) interval.mo3234getEnd(), value.endSnapshot));
                sb.append(" ");
                sb.append(formatId());
                String sb2 = sb.toString();
                if (j >= 0) {
                    this.mLogger.i(sb2);
                    if (((EventTracer.Interval) r13).recordMetric() && (metricsRecorder = this.mMetricsRecorder) != null) {
                        MetricEvent createMetricEvent = metricsRecorder.createMetricEvent();
                        createMetricEvent.addTimer(r13.name(), j);
                        createMetricEvent.addString("Role", this.mRole.name());
                        for (EventTracerFactory.Pivot pivot : this.mPivots) {
                            createMetricEvent.addString(pivot.getName(), pivot.getValue());
                        }
                        if (!TextUtils.isEmpty(this.mId)) {
                            createMetricEvent.addString(ID_METRICS_METADATA_KEY, this.mId);
                            createMetricEvent.addString("CallId", this.mId);
                        }
                        this.mMetricsRecorder.record(createMetricEvent);
                    }
                } else {
                    CommsLogger commsLogger = this.mLogger;
                    commsLogger.e("ERROR[bad delta] " + sb2);
                }
            } else {
                it2 = it3;
                EventTracer.Interval interval2 = (EventTracer.Interval) r13;
                this.mLogger.d(String.format(Locale.getDefault(), "DEBUG[interval not reported] INTERVAL[%s ?ERT ?MSE] BEGIN[%s ?ERT ?MSE] %s %s", r13.name(), ((Enum) interval2.mo3233getBegin()).name(), formatEvent("END", (Enum) interval2.mo3234getEnd(), value.endSnapshot), formatId()));
            }
            it3 = it2;
        }
    }

    private synchronized Map<EInterval, Span> snapshotEvent(EEvent eevent, Snapshot snapshot) {
        HashMap hashMap;
        hashMap = new HashMap();
        for (Integer num : this.mDefinitions.intervalsByBeginEvent.mo8104get((ImmutableMultimap<EEvent, Integer>) eevent)) {
            Enum r2 = (Enum) this.mDefinitions.intervals.get(num.intValue());
            if (this.mEndSnapshots.containsKey(r2)) {
                hashMap.put(r2, new Span(snapshot, this.mEndSnapshots.remove(r2)));
            } else {
                this.mBeginSnapshots.put(r2, snapshot);
            }
        }
        for (Integer num2 : this.mDefinitions.intervalsByEndEvent.mo8104get((ImmutableMultimap<EEvent, Integer>) eevent)) {
            Enum r1 = (Enum) this.mDefinitions.intervals.get(num2.intValue());
            if (this.mBeginSnapshots.containsKey(r1)) {
                hashMap.put(r1, new Span(this.mBeginSnapshots.remove(r1), snapshot));
            } else {
                this.mEndSnapshots.put(r1, snapshot);
            }
        }
        return hashMap;
    }

    @Override // com.amazon.comms.instrumentation.EventTracer
    public void mark(EEvent eevent) {
        mark(eevent, null);
    }

    @Override // com.amazon.comms.instrumentation.EventTracer
    public void markHistoric(EEvent eevent, long j, long j2) {
        markInternal(eevent, new Snapshot(j, j2));
        EventTracer shadow = getShadow();
        if (shadow != null) {
            shadow.markHistoric(eevent, j, j2);
        }
    }

    @Override // com.amazon.comms.instrumentation.EventTracer
    public synchronized void setShadow(EventTracer eventTracer) {
        this.mShadow = eventTracer;
    }

    EventTracerImpl(Definitions<EEvent, EInterval> definitions, String str, CommsLogger commsLogger, MetricsRecorder metricsRecorder, EventTracerFactory.Role role, List<EventTracerFactory.Pivot> list, Clocks clocks) {
        if (definitions != null) {
            if (commsLogger != null) {
                this.mDefinitions = definitions;
                this.mLogger = commsLogger;
                this.mId = str;
                this.mClock = clocks;
                this.mMetricsRecorder = metricsRecorder;
                this.mRole = role;
                this.mPivots = list;
                this.mBeginSnapshots = new HashMap(definitions.intervals.size(), 1.0f);
                this.mEndSnapshots = new HashMap(definitions.intervals.size(), 1.0f);
                return;
            }
            throw new IllegalArgumentException("logger arg must have value");
        }
        throw new IllegalArgumentException("definitions arg must have value");
    }

    @Override // com.amazon.comms.instrumentation.EventTracer
    public void mark(EEvent eevent, String str) {
        markInternal(eevent, new Snapshot(this.mClock.getElapsedRealtime(), this.mClock.getCurrentTimeMillis()));
        EventTracer shadow = getShadow();
        if (shadow != null) {
            if (str != null) {
                shadow.mark(eevent, str);
            } else {
                shadow.mark(eevent);
            }
        }
    }
}
