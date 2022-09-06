package com.amazon.alexa.fitness.time;

import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ISO8601.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/time/SimpleTime;", "", "hour", "", "minute", "second", "millisecond", "timeZone", "Ljava/util/TimeZone;", "(IIIILjava/util/TimeZone;)V", "getHour", "()I", "getMillisecond", "getMinute", "getSecond", "getTimeZone", "()Ljava/util/TimeZone;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SimpleTime {
    private final int hour;
    private final int millisecond;
    private final int minute;
    private final int second;
    @NotNull
    private final TimeZone timeZone;

    public SimpleTime(int i, int i2, int i3, int i4, @NotNull TimeZone timeZone) {
        Intrinsics.checkParameterIsNotNull(timeZone, "timeZone");
        this.hour = i;
        this.minute = i2;
        this.second = i3;
        this.millisecond = i4;
        this.timeZone = timeZone;
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getMillisecond() {
        return this.millisecond;
    }

    public final int getMinute() {
        return this.minute;
    }

    public final int getSecond() {
        return this.second;
    }

    @NotNull
    public final TimeZone getTimeZone() {
        return this.timeZone;
    }
}
