package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EventTime.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0003\u001b\u001c\u001dB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000H\u0096\u0002J\u000e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0000J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\rH\u0016J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0015H\u0086\u0002J\u0011\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0015H\u0086\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "", "millisecondsSinceBoot", "", "(J)V", "systemElapsedRealtime", "getSystemElapsedRealtime", "()J", "systemTimeMilliseconds", "getSystemTimeMilliseconds", "addingMilliseconds", "deltaMilliseconds", "compareTo", "", "other", "differenceMilliseconds", "equals", "", "", "hashCode", "minus", "Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval;", "plus", "toDate", "Ljava/util/Date;", "toString", "", "DefaultClock", "Interval", "TimeProvider", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class EventTime implements Comparable<EventTime> {
    public static final DefaultClock DefaultClock = new DefaultClock(null);
    @JvmField
    @NotNull
    public static TimeProvider systemElapsedRealtime = TimeProvider.Companion.fromFunction(EventTime$DefaultClock$systemElapsedRealtime$1.INSTANCE);
    @JvmField
    @NotNull
    public static TimeProvider systemTime = TimeProvider.Companion.fromFunction(EventTime$DefaultClock$systemTime$1.INSTANCE);
    private final long millisecondsSinceBoot;

    /* compiled from: EventTime.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000bH\u0017J\b\u0010\u000e\u001a\u00020\tH\u0017R\u0018\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0018\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/EventTime$DefaultClock;", "Lcom/amazon/alexa/voice/tta/metrics/EventClock;", "()V", "systemElapsedRealtime", "Lcom/amazon/alexa/voice/tta/metrics/EventTime$TimeProvider;", "systemElapsedRealtime$annotations", "systemTime", "systemTime$annotations", "fromSystemElapsedRealtime", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "systemRealtimeMilliseconds", "", "fromSystemTimeMilliseconds", "systemTimeMilliseconds", "now", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class DefaultClock implements EventClock {
        private DefaultClock() {
        }

        @VisibleForTesting
        public static /* synthetic */ void systemElapsedRealtime$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void systemTime$annotations() {
        }

        @JvmStatic
        @NotNull
        public final EventTime fromSystemElapsedRealtime(long j) {
            return new EventTime(j, null);
        }

        @Override // com.amazon.alexa.voice.tta.metrics.EventClock
        @JvmStatic
        @NotNull
        public EventTime fromSystemTimeMilliseconds(long j) {
            now();
            return new EventTime((EventTime.systemElapsedRealtime.getMilliseconds() + j) - EventTime.systemTime.getMilliseconds(), null);
        }

        @Override // com.amazon.alexa.voice.tta.metrics.EventClock
        @JvmStatic
        @NotNull
        public EventTime now() {
            return fromSystemElapsedRealtime(EventTime.systemElapsedRealtime.getMilliseconds());
        }

        public /* synthetic */ DefaultClock(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: EventTime.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\t\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\r\u001a\u00020\bH\u0016J\u0011\u0010\u000e\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0086\u0002J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002J\t\u0010\u0011\u001a\u00020\u0000H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval;", "", "milliseconds", "", "(J)V", "getMilliseconds", "()J", "compareTo", "", "other", "equals", "", "", "hashCode", "minus", "plus", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "unaryMinus", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Interval implements Comparable<Interval> {
        public static final Companion Companion = new Companion(null);
        @NotNull
        private static final Interval ZERO = new Interval(0);
        private final long milliseconds;

        /* compiled from: EventTime.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval$Companion;", "", "()V", "ZERO", "Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval;", "getZERO", "()Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval;", "fromMilliseconds", "millis", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class Companion {
            private Companion() {
            }

            @JvmStatic
            @NotNull
            public final Interval fromMilliseconds(long j) {
                return new Interval(j, null);
            }

            @NotNull
            public final Interval getZERO() {
                return Interval.ZERO;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        private Interval(long j) {
            this.milliseconds = j;
        }

        @JvmStatic
        @NotNull
        public static final Interval fromMilliseconds(long j) {
            return Companion.fromMilliseconds(j);
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof Interval) && this.milliseconds == ((Interval) obj).milliseconds;
        }

        public final long getMilliseconds() {
            return this.milliseconds;
        }

        public int hashCode() {
            return Long.valueOf(this.milliseconds).hashCode();
        }

        @NotNull
        public final Interval minus(@NotNull Interval other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            return new Interval(this.milliseconds - other.milliseconds);
        }

        @NotNull
        public final Interval plus(@NotNull Interval other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            return new Interval(this.milliseconds + other.milliseconds);
        }

        @NotNull
        public final Interval unaryMinus() {
            return new Interval(-this.milliseconds);
        }

        public /* synthetic */ Interval(long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(j);
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull Interval other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            return (this.milliseconds > other.milliseconds ? 1 : (this.milliseconds == other.milliseconds ? 0 : -1));
        }

        @NotNull
        public final EventTime plus(@NotNull EventTime other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            return other.addingMilliseconds(this.milliseconds);
        }
    }

    /* compiled from: EventTime.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\bg\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/EventTime$TimeProvider;", "", "milliseconds", "", "getMilliseconds", "()J", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public interface TimeProvider {
        public static final Companion Companion = Companion.$$INSTANCE;

        /* compiled from: EventTime.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/EventTime$TimeProvider$Companion;", "", "()V", "fromFunction", "Lcom/amazon/alexa/voice/tta/metrics/EventTime$TimeProvider;", "func", "Lkotlin/Function0;", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }

            @NotNull
            public final TimeProvider fromFunction(@NotNull final Function0<Long> func) {
                Intrinsics.checkParameterIsNotNull(func, "func");
                return new TimeProvider() { // from class: com.amazon.alexa.voice.tta.metrics.EventTime$TimeProvider$Companion$fromFunction$1
                    @Override // com.amazon.alexa.voice.tta.metrics.EventTime.TimeProvider
                    public long getMilliseconds() {
                        return ((Number) Function0.this.mo12560invoke()).longValue();
                    }
                };
            }
        }

        long getMilliseconds();
    }

    private EventTime(long j) {
        this.millisecondsSinceBoot = j;
    }

    @JvmStatic
    @NotNull
    public static final EventTime fromSystemElapsedRealtime(long j) {
        return DefaultClock.fromSystemElapsedRealtime(j);
    }

    @JvmStatic
    @NotNull
    public static EventTime fromSystemTimeMilliseconds(long j) {
        return DefaultClock.fromSystemTimeMilliseconds(j);
    }

    @JvmStatic
    @NotNull
    public static EventTime now() {
        return DefaultClock.now();
    }

    @NotNull
    public final EventTime addingMilliseconds(long j) {
        return new EventTime(this.millisecondsSinceBoot + j);
    }

    public final long differenceMilliseconds(@NotNull EventTime other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return this.millisecondsSinceBoot - other.millisecondsSinceBoot;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof EventTime) && this.millisecondsSinceBoot == ((EventTime) obj).millisecondsSinceBoot;
    }

    public final long getSystemElapsedRealtime() {
        return this.millisecondsSinceBoot;
    }

    public final long getSystemTimeMilliseconds() {
        return (systemTime.getMilliseconds() + this.millisecondsSinceBoot) - systemElapsedRealtime.getMilliseconds();
    }

    public int hashCode() {
        return Long.valueOf(this.millisecondsSinceBoot).hashCode();
    }

    @NotNull
    public final Interval minus(@NotNull EventTime other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Interval.Companion.fromMilliseconds(this.millisecondsSinceBoot - other.millisecondsSinceBoot);
    }

    @NotNull
    public final EventTime plus(@NotNull Interval other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new EventTime(other.getMilliseconds() + this.millisecondsSinceBoot);
    }

    @NotNull
    public final Date toDate() {
        return new Date(getSystemTimeMilliseconds());
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EventTime(");
        outline107.append(this.millisecondsSinceBoot);
        outline107.append(')');
        return outline107.toString();
    }

    public /* synthetic */ EventTime(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull EventTime other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return (this.millisecondsSinceBoot > other.millisecondsSinceBoot ? 1 : (this.millisecondsSinceBoot == other.millisecondsSinceBoot ? 0 : -1));
    }

    @NotNull
    public final EventTime minus(@NotNull Interval other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new EventTime(other.getMilliseconds() + this.millisecondsSinceBoot);
    }
}
