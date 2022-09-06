package com.amazon.comms.instrumentation;

import android.content.Context;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.instrumentation.EventTracer.Interval;
import com.amazon.comms.instrumentation.EventTracerImpl;
import com.amazon.comms.log.CommsLogger;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.Enum;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class EventTracerFactory<EEvent extends Enum<EEvent>, EInterval extends Enum<EInterval> & EventTracer.Interval<EEvent>> {
    private final EventTracerImpl.Definitions mDefinitions;
    private final CommsLogger mLogger;
    private final EventTracerImpl.MetricsRecorder mMetricsRecorder;

    /* loaded from: classes11.dex */
    public static class Pivot {
        private final String name;
        private final String value;

        public Pivot(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* loaded from: classes11.dex */
    public enum Role {
        CALLER,
        CALLEE,
        TEST
    }

    /* JADX WARN: Incorrect types in method signature: ([TEEvent;[TEInterval;Ljava/lang/String;)V */
    public EventTracerFactory(Enum[] enumArr, Enum[] enumArr2, String str) {
        this(new EventTracerImpl.Definitions(enumArr, enumArr2), str, (EventTracerImpl.MetricsRecorder) null);
    }

    public EventTracer<EEvent, EInterval> create(String str, Role role) {
        return new EventTracerImpl(this.mDefinitions, str, this.mLogger, this.mMetricsRecorder, role, new ArrayList());
    }

    /* JADX WARN: Incorrect types in method signature: ([TEEvent;[TEInterval;Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V */
    public EventTracerFactory(Enum[] enumArr, Enum[] enumArr2, String str, Context context, String str2, String str3) {
        this(new EventTracerImpl.Definitions(enumArr, enumArr2), str, new EventTracerImpl.MetricsRecorder(context, str2, str3));
    }

    public EventTracer<EEvent, EInterval> create(String str, Role role, Pivot pivot) {
        ArrayList arrayList = new ArrayList();
        if (pivot != null) {
            arrayList.add(pivot);
        }
        return new EventTracerImpl(this.mDefinitions, str, this.mLogger, this.mMetricsRecorder, role, arrayList);
    }

    private EventTracerFactory(EventTracerImpl.Definitions<EEvent, EInterval> definitions, String str, EventTracerImpl.MetricsRecorder metricsRecorder) {
        this.mDefinitions = definitions;
        this.mLogger = CommsLogger.getLogger(str);
        this.mMetricsRecorder = metricsRecorder;
        if (metricsRecorder != null) {
            CommsLogger commsLogger = this.mLogger;
            Locale locale = Locale.getDefault();
            EventTracerImpl.MetricsRecorder metricsRecorder2 = this.mMetricsRecorder;
            commsLogger.i(String.format(locale, "Metrics will be recorded for domain=%s, source=%s", metricsRecorder2.DOMAIN, metricsRecorder2.SOURCE));
        }
        UnmodifiableIterator<EEvent> mo8029iterator = definitions.events.mo8029iterator();
        while (mo8029iterator.hasNext()) {
            this.mLogger.i(String.format(Locale.getDefault(), "Event definition: %s", mo8029iterator.next().name()));
        }
        UnmodifiableIterator<EInterval> mo8029iterator2 = definitions.intervals.mo8029iterator();
        while (mo8029iterator2.hasNext()) {
            Enum r9 = (Enum) mo8029iterator2.next();
            CommsLogger commsLogger2 = this.mLogger;
            Locale locale2 = Locale.getDefault();
            Object[] objArr = new Object[4];
            objArr[0] = r9.name();
            EventTracer.Interval interval = (EventTracer.Interval) r9;
            objArr[1] = ((Enum) interval.mo3233getBegin()).name();
            objArr[2] = ((Enum) interval.mo3234getEnd()).name();
            objArr[3] = interval.recordMetric() ? " will record metric" : "";
            commsLogger2.i(String.format(locale2, "Interval definition: %s [%s->%s]%s", objArr));
        }
    }

    public EventTracer<EEvent, EInterval> create(String str, Role role, List<Pivot> list) {
        return new EventTracerImpl(this.mDefinitions, str, this.mLogger, this.mMetricsRecorder, role, list);
    }
}
