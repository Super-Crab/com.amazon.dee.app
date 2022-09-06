package com.amazon.comms.instrumentation;

import com.amazon.comms.instrumentation.EventTracer.Interval;
import java.lang.Enum;
/* loaded from: classes11.dex */
public interface EventTracer<EEvent extends Enum<EEvent>, EInterval extends Enum<EInterval> & Interval<EEvent>> {

    /* loaded from: classes11.dex */
    public interface Interval<EEvent> {
        /* renamed from: getBegin */
        EEvent mo3233getBegin();

        /* renamed from: getEnd */
        EEvent mo3234getEnd();

        boolean recordMetric();
    }

    void mark(EEvent eevent);

    void mark(EEvent eevent, String str);

    void markHistoric(EEvent eevent, long j, long j2);

    void setShadow(EventTracer eventTracer);
}
