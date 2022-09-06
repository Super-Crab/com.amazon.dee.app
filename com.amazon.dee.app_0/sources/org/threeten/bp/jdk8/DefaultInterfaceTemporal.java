package org.threeten.bp.jdk8;

import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalUnit;
/* loaded from: classes5.dex */
public abstract class DefaultInterfaceTemporal extends DefaultInterfaceTemporalAccessor implements Temporal {
    /* renamed from: minus */
    public Temporal mo13057minus(TemporalAmount temporalAmount) {
        return temporalAmount.subtractFrom(this);
    }

    /* renamed from: plus */
    public Temporal mo13059plus(TemporalAmount temporalAmount) {
        return temporalAmount.addTo(this);
    }

    /* renamed from: with */
    public Temporal mo13061with(TemporalAdjuster temporalAdjuster) {
        return temporalAdjuster.adjustInto(this);
    }

    /* renamed from: minus */
    public Temporal mo13056minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? mo13060plus(Long.MAX_VALUE, temporalUnit).mo13060plus(1L, temporalUnit) : mo13060plus(-j, temporalUnit);
    }
}
