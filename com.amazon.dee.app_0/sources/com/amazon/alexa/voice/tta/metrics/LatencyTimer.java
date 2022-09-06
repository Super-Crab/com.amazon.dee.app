package com.amazon.alexa.voice.tta.metrics;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LatencyTimer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0006\b\u0001\u0010\u0002 \u00002\u00020\u0003:\u0002\u001a\u001bB\u001b\b\u0016\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006Bw\u0012p\u0010\u0004\u001al\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0010¢\u0006\u0002\u0010\u0011J\u001b\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00028\u00012\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0002\u0010\u0017J\u0006\u0010\u0019\u001a\u00020\u000fR{\u0010\u0004\u001al\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015X\u0088\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer;", "Start", PCCConstants.STOP_DIRECTIVE, "", "recordLatency", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$RecordLatency;", "(Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$RecordLatency;)V", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "startEvent", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "startTime", "stopEvent", "stopTime", "", "Lcom/amazon/alexa/voice/tta/metrics/RecordLatencyFun;", "(Lkotlin/jvm/functions/Function4;)V", "getRecordLatency", "()Lkotlin/jvm/functions/Function4;", "state", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State;", "begin", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)V", "end", "reset", "RecordLatency", "State", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class LatencyTimer<Start, Stop> {
    @NotNull
    private final Function4<Start, EventTime, Stop, EventTime, Unit> recordLatency;
    private State<? extends Start, ? extends Stop> state;

    /* compiled from: LatencyTimer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u0000\"\u0006\b\u0001\u0010\u0003 \u00002\u0015\u0010\u0004\u001a\u0011H\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00072\u0015\u0010\b\u001a\u00110\t¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n2\u0015\u0010\u000b\u001a\u0011H\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\f2\u0015\u0010\r\u001a\u00110\t¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e¢\u0006\u0004\b\u000f\u0010\u0010"}, d2 = {"<anonymous>", "", "Start", PCCConstants.STOP_DIRECTIVE, "p1", "Lkotlin/ParameterName;", "name", "startEvent", "p2", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "startTime", "p3", "stopEvent", "p4", "stopTime", "invoke", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)V"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.voice.tta.metrics.LatencyTimer$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function4<Start, EventTime, Stop, EventTime, Unit> {
        AnonymousClass1(RecordLatency recordLatency) {
            super(4, recordLatency);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "recordLatency";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(RecordLatency.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "recordLatency(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)V";
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Object obj, EventTime eventTime, Object obj2, EventTime eventTime2) {
            invoke2((AnonymousClass1) obj, eventTime, (EventTime) obj2, eventTime2);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Start start, @NotNull EventTime p2, Stop stop, @NotNull EventTime p4) {
            Intrinsics.checkParameterIsNotNull(p2, "p2");
            Intrinsics.checkParameterIsNotNull(p4, "p4");
            ((RecordLatency) this.receiver).recordLatency(start, p2, stop, p4);
        }
    }

    /* compiled from: LatencyTimer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0006\b\u0002\u0010\u0001 \u0000*\u0006\b\u0003\u0010\u0002 \u00002\u00020\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00032\u0006\u0010\n\u001a\u00020\bH&¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$RecordLatency;", "Start", PCCConstants.STOP_DIRECTIVE, "", "recordLatency", "", "startEvent", "startTime", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "stopEvent", "stopTime", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)V", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public interface RecordLatency<Start, Stop> {
        void recordLatency(Start start, @NotNull EventTime eventTime, Stop stop, @NotNull EventTime eventTime2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LatencyTimer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u0000*\u0006\b\u0002\u0010\u0001 \u0001*\u0006\b\u0003\u0010\u0002 \u00012\u00020\u0003:\u0003\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0004\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State;", "Start", PCCConstants.STOP_DIRECTIVE, "", "()V", "Pending", "StartRecorded", "StopRecorded", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$Pending;", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$StartRecorded;", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$StopRecorded;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static abstract class State<Start, Stop> {

        /* compiled from: LatencyTimer.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$Pending;", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State;", "", "()V", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class Pending extends State {
            public static final Pending INSTANCE = new Pending();

            private Pending() {
                super(null);
            }
        }

        /* compiled from: LatencyTimer.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0004\u0010\u0001 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00028\u0004HÆ\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00040\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$StartRecorded;", "Start", "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State;", "", "event", "time", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)V", "getEvent", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTime", "()Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "component1", "component2", "copy", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$StartRecorded;", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class StartRecorded<Start> extends State {
            private final Start event;
            @NotNull
            private final EventTime time;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StartRecorded(Start start, @NotNull EventTime time) {
                super(null);
                Intrinsics.checkParameterIsNotNull(time, "time");
                this.event = start;
                this.time = time;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ StartRecorded copy$default(StartRecorded startRecorded, Object obj, EventTime eventTime, int i, Object obj2) {
                if ((i & 1) != 0) {
                    obj = startRecorded.event;
                }
                if ((i & 2) != 0) {
                    eventTime = startRecorded.time;
                }
                return startRecorded.copy(obj, eventTime);
            }

            public final Start component1() {
                return this.event;
            }

            @NotNull
            public final EventTime component2() {
                return this.time;
            }

            @NotNull
            public final StartRecorded<Start> copy(Start start, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(time, "time");
                return new StartRecorded<>(start, time);
            }

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    if (!(obj instanceof StartRecorded)) {
                        return false;
                    }
                    StartRecorded startRecorded = (StartRecorded) obj;
                    return Intrinsics.areEqual(this.event, startRecorded.event) && Intrinsics.areEqual(this.time, startRecorded.time);
                }
                return true;
            }

            public final Start getEvent() {
                return this.event;
            }

            @NotNull
            public final EventTime getTime() {
                return this.time;
            }

            public int hashCode() {
                Start start = this.event;
                int i = 0;
                int hashCode = (start != null ? start.hashCode() : 0) * 31;
                EventTime eventTime = this.time;
                if (eventTime != null) {
                    i = eventTime.hashCode();
                }
                return hashCode + i;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StartRecorded(event=");
                outline107.append(this.event);
                outline107.append(", time=");
                outline107.append(this.time);
                outline107.append(")");
                return outline107.toString();
            }
        }

        /* compiled from: LatencyTimer.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0004\u0010\u0001 \u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00028\u0004HÆ\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00040\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$StopRecorded;", PCCConstants.STOP_DIRECTIVE, "Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State;", "", "event", "time", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)V", "getEvent", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTime", "()Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "component1", "component2", "copy", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)Lcom/amazon/alexa/voice/tta/metrics/LatencyTimer$State$StopRecorded;", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class StopRecorded<Stop> extends State {
            private final Stop event;
            @NotNull
            private final EventTime time;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StopRecorded(Stop stop, @NotNull EventTime time) {
                super(null);
                Intrinsics.checkParameterIsNotNull(time, "time");
                this.event = stop;
                this.time = time;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ StopRecorded copy$default(StopRecorded stopRecorded, Object obj, EventTime eventTime, int i, Object obj2) {
                if ((i & 1) != 0) {
                    obj = stopRecorded.event;
                }
                if ((i & 2) != 0) {
                    eventTime = stopRecorded.time;
                }
                return stopRecorded.copy(obj, eventTime);
            }

            public final Stop component1() {
                return this.event;
            }

            @NotNull
            public final EventTime component2() {
                return this.time;
            }

            @NotNull
            public final StopRecorded<Stop> copy(Stop stop, @NotNull EventTime time) {
                Intrinsics.checkParameterIsNotNull(time, "time");
                return new StopRecorded<>(stop, time);
            }

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    if (!(obj instanceof StopRecorded)) {
                        return false;
                    }
                    StopRecorded stopRecorded = (StopRecorded) obj;
                    return Intrinsics.areEqual(this.event, stopRecorded.event) && Intrinsics.areEqual(this.time, stopRecorded.time);
                }
                return true;
            }

            public final Stop getEvent() {
                return this.event;
            }

            @NotNull
            public final EventTime getTime() {
                return this.time;
            }

            public int hashCode() {
                Stop stop = this.event;
                int i = 0;
                int hashCode = (stop != null ? stop.hashCode() : 0) * 31;
                EventTime eventTime = this.time;
                if (eventTime != null) {
                    i = eventTime.hashCode();
                }
                return hashCode + i;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StopRecorded(event=");
                outline107.append(this.event);
                outline107.append(", time=");
                outline107.append(this.time);
                outline107.append(")");
                return outline107.toString();
            }
        }

        private State() {
        }

        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LatencyTimer(@NotNull Function4<? super Start, ? super EventTime, ? super Stop, ? super EventTime, Unit> recordLatency) {
        Intrinsics.checkParameterIsNotNull(recordLatency, "recordLatency");
        this.recordLatency = recordLatency;
        this.state = State.Pending.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void begin(Start start, @NotNull EventTime startTime) {
        State.StartRecorded startRecorded;
        String unused;
        String unused2;
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        State<? extends Start, ? extends Stop> state = this.state;
        if (state instanceof State.Pending) {
            this.state = new State.StartRecorded(start, startTime);
        } else if (state instanceof State.StartRecorded) {
            if (startTime.compareTo(((State.StartRecorded) state).getTime()) < 0) {
                return;
            }
            this.state = new State.StartRecorded(start, startTime);
            unused = LatencyTimerKt.TAG;
            String str = "begin: latency not recorded for startEvent " + startRecorded.getEvent() + " at " + startRecorded.getTime().toDate() + "; another startEvent " + start + " before stopEvent";
        } else if (!(state instanceof State.StopRecorded)) {
        } else {
            State.StopRecorded stopRecorded = (State.StopRecorded) state;
            Object component1 = stopRecorded.component1();
            EventTime component2 = stopRecorded.component2();
            if (startTime.compareTo(component2) < 0) {
                this.state = State.Pending.INSTANCE;
                this.recordLatency.invoke(start, startTime, component1, component2);
                return;
            }
            this.state = new State.StartRecorded(start, startTime);
            unused2 = LatencyTimerKt.TAG;
            String str2 = "begin: latency not recorded; no startEvent before stopEvent " + component1 + " at " + component2.toDate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void end(Stop stop, @NotNull EventTime stopTime) {
        String unused;
        String unused2;
        Intrinsics.checkParameterIsNotNull(stopTime, "stopTime");
        State<? extends Start, ? extends Stop> state = this.state;
        if (state instanceof State.Pending) {
            this.state = new State.StopRecorded(stop, stopTime);
        } else if (state instanceof State.StartRecorded) {
            State.StartRecorded startRecorded = (State.StartRecorded) state;
            Object component1 = startRecorded.component1();
            EventTime component2 = startRecorded.component2();
            if (component2.compareTo(stopTime) >= 0) {
                unused = LatencyTimerKt.TAG;
                String str = "end: latency not recorded; stopEvent " + stop + " at " + stopTime.toDate() + " happened before startEvent " + component1 + " at " + component2.toDate();
                return;
            }
            this.state = State.Pending.INSTANCE;
            this.recordLatency.invoke(component1, component2, stop, stopTime);
        } else if (!(state instanceof State.StopRecorded)) {
        } else {
            if (stopTime.compareTo(((State.StopRecorded) state).getTime()) >= 0) {
                this.state = new State.StopRecorded(stop, stopTime);
            }
            unused2 = LatencyTimerKt.TAG;
            String str2 = "end: latency not recorded; no startEvent for stopEvent " + stop + " at " + stopTime.toDate();
        }
    }

    @NotNull
    public final Function4<Start, EventTime, Stop, EventTime, Unit> getRecordLatency() {
        return this.recordLatency;
    }

    public final void reset() {
        this.state = State.Pending.INSTANCE;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LatencyTimer(@NotNull RecordLatency<? super Start, ? super Stop> recordLatency) {
        this(new AnonymousClass1(recordLatency));
        Intrinsics.checkParameterIsNotNull(recordLatency, "recordLatency");
    }
}
