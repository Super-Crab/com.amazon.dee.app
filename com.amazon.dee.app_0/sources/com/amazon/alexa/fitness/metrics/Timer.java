package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.util.PreconditionsKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MetricEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Timer;", "Lcom/amazon/alexa/fitness/metrics/DataPoint;", "metric", "", "(Ljava/lang/String;)V", "getMetric", "()Ljava/lang/String;", "Companion", "Started", "Stopped", "Lcom/amazon/alexa/fitness/metrics/Timer$Started;", "Lcom/amazon/alexa/fitness/metrics/Timer$Stopped;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class Timer implements DataPoint {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String metric;

    /* compiled from: MetricEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Timer$Companion;", "", "()V", "start", "Lcom/amazon/alexa/fitness/metrics/Timer$Started;", "metric", "", "atMilli", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Started start(@NotNull String metric, long j) {
            Intrinsics.checkParameterIsNotNull(metric, "metric");
            return new Started(metric, j);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: MetricEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Timer$Started;", "Lcom/amazon/alexa/fitness/metrics/Timer;", "metric", "", "startedAtMilli", "", "(Ljava/lang/String;J)V", "equals", "", "other", "", "hashCode", "", "stop", "Lcom/amazon/alexa/fitness/metrics/Timer$Stopped;", "atMilli", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Started extends Timer {
        private final long startedAtMilli;

        /* compiled from: MetricEvent.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
        /* renamed from: com.amazon.alexa.fitness.metrics.Timer$Started$1  reason: invalid class name */
        /* loaded from: classes8.dex */
        static final class AnonymousClass1 extends Lambda implements Function0<String> {
            AnonymousClass1() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* renamed from: invoke  reason: collision with other method in class */
            public final String mo12560invoke() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("startedAtMilli (");
                outline107.append(Started.this.startedAtMilli);
                outline107.append(") must be non-negative");
                return outline107.toString();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Started(@NotNull String metric, long j) {
            super(metric, null);
            Intrinsics.checkParameterIsNotNull(metric, "metric");
            this.startedAtMilli = j;
            PreconditionsKt.assertNotNullOrBlank$default(metric, null, 1, null);
            PreconditionsKt.assertGreaterThanOrEqualTo(Long.valueOf(this.startedAtMilli), 0L, new AnonymousClass1());
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!Intrinsics.areEqual(Started.class, obj != null ? obj.getClass() : null)) {
                return false;
            }
            if (obj != null) {
                Started started = (Started) obj;
                return Intrinsics.areEqual(getMetric(), started.getMetric()) && this.startedAtMilli == started.startedAtMilli;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.metrics.Timer.Started");
        }

        public int hashCode() {
            return Objects.hash(getMetric(), Long.valueOf(this.startedAtMilli));
        }

        @NotNull
        public final Stopped stop(long j) {
            return new Stopped(getMetric(), this.startedAtMilli, j);
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Timer.Started(metric='");
            outline107.append(getMetric());
            outline107.append("', startedAtMilli=");
            outline107.append(this.startedAtMilli);
            outline107.append(')');
            return outline107.toString();
        }
    }

    /* compiled from: MetricEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Timer$Stopped;", "Lcom/amazon/alexa/fitness/metrics/Timer;", "metric", "", "startedAtMilli", "", "stoppedAtMilli", "(Ljava/lang/String;JJ)V", "durationMilli", "getDurationMilli", "()J", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Stopped extends Timer {
        private final long durationMilli;
        private final long startedAtMilli;
        private final long stoppedAtMilli;

        /* compiled from: MetricEvent.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
        /* renamed from: com.amazon.alexa.fitness.metrics.Timer$Stopped$1  reason: invalid class name */
        /* loaded from: classes8.dex */
        static final class AnonymousClass1 extends Lambda implements Function0<String> {
            AnonymousClass1() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* renamed from: invoke  reason: collision with other method in class */
            public final String mo12560invoke() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("startedAtMilli (");
                outline107.append(Stopped.this.startedAtMilli);
                outline107.append(") must be >= 0");
                return outline107.toString();
            }
        }

        /* compiled from: MetricEvent.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
        /* renamed from: com.amazon.alexa.fitness.metrics.Timer$Stopped$2  reason: invalid class name */
        /* loaded from: classes8.dex */
        static final class AnonymousClass2 extends Lambda implements Function0<String> {
            AnonymousClass2() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* renamed from: invoke  reason: collision with other method in class */
            public final String mo12560invoke() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("stoppedAtMilli (");
                outline107.append(Stopped.this.stoppedAtMilli);
                outline107.append(") must be >= startedAtMilli (");
                outline107.append(Stopped.this.startedAtMilli);
                outline107.append(')');
                return outline107.toString();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Stopped(@NotNull String metric, long j, long j2) {
            super(metric, null);
            Intrinsics.checkParameterIsNotNull(metric, "metric");
            this.startedAtMilli = j;
            this.stoppedAtMilli = j2;
            this.durationMilli = this.stoppedAtMilli - this.startedAtMilli;
            PreconditionsKt.assertNotNullOrBlank$default(metric, null, 1, null);
            PreconditionsKt.assertGreaterThanOrEqualTo(Long.valueOf(this.startedAtMilli), 0L, new AnonymousClass1());
            PreconditionsKt.assertGreaterThanOrEqualTo(Long.valueOf(this.stoppedAtMilli), Long.valueOf(this.startedAtMilli), new AnonymousClass2());
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!Intrinsics.areEqual(Stopped.class, obj != null ? obj.getClass() : null)) {
                return false;
            }
            if (obj != null) {
                Stopped stopped = (Stopped) obj;
                return Intrinsics.areEqual(getMetric(), stopped.getMetric()) && this.startedAtMilli == stopped.startedAtMilli && this.stoppedAtMilli == stopped.stoppedAtMilli;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.metrics.Timer.Stopped");
        }

        public final long getDurationMilli() {
            return this.durationMilli;
        }

        public int hashCode() {
            return Objects.hash(getMetric(), Long.valueOf(this.startedAtMilli), Long.valueOf(this.stoppedAtMilli));
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Timer.Stopped(metric='");
            outline107.append(getMetric());
            outline107.append("', startedAtMilli=");
            outline107.append(this.startedAtMilli);
            outline107.append(", stoppedAtMilli=");
            outline107.append(this.stoppedAtMilli);
            outline107.append(", durationMilli=");
            outline107.append(this.durationMilli);
            outline107.append(')');
            return outline107.toString();
        }
    }

    private Timer(String str) {
        this.metric = str;
    }

    @Override // com.amazon.alexa.fitness.metrics.DataPoint
    @NotNull
    public String getMetric() {
        return this.metric;
    }

    public /* synthetic */ Timer(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
