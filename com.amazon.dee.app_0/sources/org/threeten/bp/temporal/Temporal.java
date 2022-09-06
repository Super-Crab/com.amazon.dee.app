package org.threeten.bp.temporal;
/* loaded from: classes5.dex */
public interface Temporal extends TemporalAccessor {
    boolean isSupported(TemporalUnit temporalUnit);

    /* renamed from: minus */
    Temporal mo13056minus(long j, TemporalUnit temporalUnit);

    /* renamed from: minus */
    Temporal mo13057minus(TemporalAmount temporalAmount);

    /* renamed from: plus */
    Temporal mo13060plus(long j, TemporalUnit temporalUnit);

    /* renamed from: plus */
    Temporal mo13059plus(TemporalAmount temporalAmount);

    long until(Temporal temporal, TemporalUnit temporalUnit);

    /* renamed from: with */
    Temporal mo13061with(TemporalAdjuster temporalAdjuster);

    /* renamed from: with */
    Temporal mo13062with(TemporalField temporalField, long j);
}
