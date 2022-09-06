package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.algorithms.ActivityType;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CaloriesActivityBuffer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0002&'B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001e\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\t0\u00060\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0019\u001a\u00020\tH\u0002J\u000e\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0016J)\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0013H\u0000¢\u0006\u0002\b!J\u0010\u0010\"\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u0016H\u0002J\u0006\u0010$\u001a\u00020\tJ\f\u0010%\u001a\u00020\u001e*\u00020\u001eH\u0002R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\t0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityBuffer;", "", "bufferLimitSeconds", "", "onSummariesAvailable", "Ljava/lang/ref/WeakReference;", "Lkotlin/Function1;", "", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityWindowSummary;", "", "logger", "Lcom/amazon/alexa/fitness/logs/ILog;", "(DLjava/lang/ref/WeakReference;Lcom/amazon/alexa/fitness/logs/ILog;)V", "availableSummaries", "", "currentActivityState", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityBuffer$ActivityState;", "currentWindowSummary", "lastSeenTimestampMillis", "", "Ljava/lang/Long;", "subIntervalEventQueue", "Lcom/amazon/alexa/fitness/algorithms/ActivityEvent;", "workoutSessionTotalSteps", "", "addCurrentWindowToAvailableSummaries", "addEvent", "event", "bisectInterval", "Lkotlin/Pair;", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityBuffer$ActivityInterval;", "interval", "bisectionTimestamp", "bisectInterval$AlexaMobileAndroidFitnessExtension_release", "consumeActivityInterval", "createIntervalFromEvent", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "applySubIntervalEvents", "ActivityInterval", CommsFocusConstants.ACTIVITY_STATE, "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CaloriesActivityBuffer {
    private List<CaloriesActivityWindowSummary> availableSummaries;
    private final double bufferLimitSeconds;
    private ActivityState currentActivityState;
    private CaloriesActivityWindowSummary currentWindowSummary;
    private Long lastSeenTimestampMillis;
    private final ILog logger;
    private final WeakReference<Function1<List<CaloriesActivityWindowSummary>, Unit>> onSummariesAvailable;
    private List<ActivityEvent> subIntervalEventQueue;
    private int workoutSessionTotalSteps;

    /* compiled from: CaloriesActivityBuffer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityBuffer$ActivityInterval;", "", "timestampStartMillis", "", "timestampEndMillis", "incrementalSteps", "", "algorithmActivityType", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "(JJILcom/amazon/alexa/fitness/algorithms/ActivityType;)V", "getAlgorithmActivityType", "()Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "durationSeconds", "", "getDurationSeconds", "()D", "getIncrementalSteps", "()I", "getTimestampEndMillis", "()J", "getTimestampStartMillis", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class ActivityInterval {
        @Nullable
        private final ActivityType algorithmActivityType;
        private final int incrementalSteps;
        private final long timestampEndMillis;
        private final long timestampStartMillis;

        public ActivityInterval(long j, long j2, int i, @Nullable ActivityType activityType) {
            this.timestampStartMillis = j;
            this.timestampEndMillis = j2;
            this.incrementalSteps = i;
            this.algorithmActivityType = activityType;
        }

        public static /* synthetic */ ActivityInterval copy$default(ActivityInterval activityInterval, long j, long j2, int i, ActivityType activityType, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = activityInterval.timestampStartMillis;
            }
            long j3 = j;
            if ((i2 & 2) != 0) {
                j2 = activityInterval.timestampEndMillis;
            }
            long j4 = j2;
            if ((i2 & 4) != 0) {
                i = activityInterval.incrementalSteps;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                activityType = activityInterval.algorithmActivityType;
            }
            return activityInterval.copy(j3, j4, i3, activityType);
        }

        public final long component1() {
            return this.timestampStartMillis;
        }

        public final long component2() {
            return this.timestampEndMillis;
        }

        public final int component3() {
            return this.incrementalSteps;
        }

        @Nullable
        public final ActivityType component4() {
            return this.algorithmActivityType;
        }

        @NotNull
        public final ActivityInterval copy(long j, long j2, int i, @Nullable ActivityType activityType) {
            return new ActivityInterval(j, j2, i, activityType);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof ActivityInterval)) {
                    return false;
                }
                ActivityInterval activityInterval = (ActivityInterval) obj;
                return this.timestampStartMillis == activityInterval.timestampStartMillis && this.timestampEndMillis == activityInterval.timestampEndMillis && this.incrementalSteps == activityInterval.incrementalSteps && Intrinsics.areEqual(this.algorithmActivityType, activityInterval.algorithmActivityType);
            }
            return true;
        }

        @Nullable
        public final ActivityType getAlgorithmActivityType() {
            return this.algorithmActivityType;
        }

        public final double getDurationSeconds() {
            return (this.timestampEndMillis - this.timestampStartMillis) / 1000.0d;
        }

        public final int getIncrementalSteps() {
            return this.incrementalSteps;
        }

        public final long getTimestampEndMillis() {
            return this.timestampEndMillis;
        }

        public final long getTimestampStartMillis() {
            return this.timestampStartMillis;
        }

        public int hashCode() {
            long j = this.timestampStartMillis;
            long j2 = this.timestampEndMillis;
            int i = ((((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + this.incrementalSteps) * 31;
            ActivityType activityType = this.algorithmActivityType;
            return i + (activityType != null ? activityType.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ActivityInterval(timestampStartMillis=");
            outline107.append(this.timestampStartMillis);
            outline107.append(", timestampEndMillis=");
            outline107.append(this.timestampEndMillis);
            outline107.append(", incrementalSteps=");
            outline107.append(this.incrementalSteps);
            outline107.append(", algorithmActivityType=");
            outline107.append(this.algorithmActivityType);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CaloriesActivityBuffer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityBuffer$ActivityState;", "", "(Ljava/lang/String;I)V", "PAUSED", "ACTIVE", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum ActivityState {
        PAUSED,
        ACTIVE
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActivityEvent.EventType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[ActivityEvent.EventType.EVENT_TYPE_ACTIVITY.ordinal()] = 1;
            $EnumSwitchMapping$0[ActivityEvent.EventType.EVENT_TYPE_STOP.ordinal()] = 2;
            $EnumSwitchMapping$0[ActivityEvent.EventType.EVENT_TYPE_PAUSE.ordinal()] = 3;
            $EnumSwitchMapping$0[ActivityEvent.EventType.EVENT_TYPE_RESUME.ordinal()] = 4;
            $EnumSwitchMapping$0[ActivityEvent.EventType.EVENT_TYPE_START.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[ActivityEvent.EventType.values().length];
            $EnumSwitchMapping$1[ActivityEvent.EventType.EVENT_TYPE_PAUSE.ordinal()] = 1;
            $EnumSwitchMapping$1[ActivityEvent.EventType.EVENT_TYPE_RESUME.ordinal()] = 2;
        }
    }

    public CaloriesActivityBuffer(double d, @NotNull WeakReference<Function1<List<CaloriesActivityWindowSummary>, Unit>> onSummariesAvailable, @NotNull ILog logger) {
        Intrinsics.checkParameterIsNotNull(onSummariesAvailable, "onSummariesAvailable");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.bufferLimitSeconds = d;
        this.onSummariesAvailable = onSummariesAvailable;
        this.logger = logger;
        this.currentWindowSummary = new CaloriesActivityWindowSummary(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 0, 3, null);
        this.availableSummaries = new ArrayList();
        this.subIntervalEventQueue = new ArrayList();
        this.currentActivityState = ActivityState.ACTIVE;
    }

    private final void addCurrentWindowToAvailableSummaries() {
        List<CaloriesActivityWindowSummary> list;
        if (this.currentWindowSummary.getDurationSeconds() > 0) {
            ILog iLog = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Adding current window to available summaries: ");
            outline107.append(this.currentWindowSummary);
            outline107.append('.');
            ILog.DefaultImpls.info$default(iLog, "CaloriesActivityBuffer", outline107.toString(), null, 4, null);
            this.availableSummaries.add(this.currentWindowSummary);
            this.currentWindowSummary = new CaloriesActivityWindowSummary(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 0, 3, null);
        } else {
            ILog.DefaultImpls.warn$default(this.logger, "CaloriesActivityBuffer", "Attempt made to add 0-duration current window to available summaries, ignoring.", null, 4, null);
        }
        if (this.availableSummaries.size() > 0) {
            ILog iLog2 = this.logger;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Available summaries contains ");
            outline1072.append(this.availableSummaries.size());
            outline1072.append(" records, firing onSummariesAvailable event.");
            ILog.DefaultImpls.debug$default(iLog2, "CaloriesActivityBuffer", outline1072.toString(), null, 4, null);
            Function1<List<CaloriesActivityWindowSummary>, Unit> function1 = this.onSummariesAvailable.get();
            if (function1 != null) {
                list = CollectionsKt___CollectionsKt.toList(this.availableSummaries);
                function1.mo12165invoke(list);
            }
            this.availableSummaries = new ArrayList();
        }
    }

    private final ActivityInterval applySubIntervalEvents(@NotNull ActivityInterval activityInterval) {
        Pair<ActivityInterval, ActivityInterval> pair;
        ActivityInterval activityInterval2 = activityInterval;
        for (ActivityEvent activityEvent : this.subIntervalEventQueue) {
            try {
                pair = bisectInterval$AlexaMobileAndroidFitnessExtension_release(activityInterval2, activityEvent.timestamp);
            } catch (Exception e) {
                ILog.DefaultImpls.warn$default(this.logger, "CaloriesActivityBuffer", GeneratedOutlineSupport1.outline68("Bisection error: ", e), null, 4, null);
                pair = null;
            }
            if (pair != null) {
                ActivityEvent.EventType eventType = activityEvent.eventType;
                if (eventType != null) {
                    int i = WhenMappings.$EnumSwitchMapping$1[eventType.ordinal()];
                    if (i == 1) {
                        if (this.currentActivityState == ActivityState.ACTIVE) {
                            ILog iLog = this.logger;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Processing sub-interval pause event: ");
                            outline107.append(GsonUtilsKt.toJson(activityEvent));
                            outline107.append('.');
                            ILog.DefaultImpls.debug$default(iLog, "CaloriesActivityBuffer", outline107.toString(), null, 4, null);
                            consumeActivityInterval(pair.getFirst());
                        } else {
                            ILog.DefaultImpls.warn$default(this.logger, "CaloriesActivityBuffer", "Back-to-back pause activity events received, ignoring.", null, 4, null);
                        }
                        this.currentActivityState = ActivityState.PAUSED;
                    } else if (i == 2) {
                        ILog iLog2 = this.logger;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Processing sub-interval resume event: ");
                        outline1072.append(GsonUtilsKt.toJson(activityEvent));
                        outline1072.append('.');
                        ILog.DefaultImpls.debug$default(iLog2, "CaloriesActivityBuffer", outline1072.toString(), null, 4, null);
                        this.currentActivityState = ActivityState.ACTIVE;
                    }
                    activityInterval2 = pair.getSecond();
                    ILog iLog3 = this.logger;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Interval after sub-interval processing: ");
                    outline1073.append(GsonUtilsKt.toJson(activityInterval2));
                    outline1073.append('.');
                    ILog.DefaultImpls.debug$default(iLog3, "CaloriesActivityBuffer", outline1073.toString(), null, 4, null);
                }
                ILog iLog4 = this.logger;
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Unsupported eventType (");
                outline1074.append(activityEvent.eventType);
                outline1074.append(") found in sub-interval queue.");
                ILog.DefaultImpls.error$default(iLog4, "CaloriesActivityBuffer", outline1074.toString(), null, 4, null);
                activityInterval2 = pair.getSecond();
                ILog iLog32 = this.logger;
                StringBuilder outline10732 = GeneratedOutlineSupport1.outline107("Interval after sub-interval processing: ");
                outline10732.append(GsonUtilsKt.toJson(activityInterval2));
                outline10732.append('.');
                ILog.DefaultImpls.debug$default(iLog32, "CaloriesActivityBuffer", outline10732.toString(), null, 4, null);
            }
        }
        this.subIntervalEventQueue.clear();
        ILog.DefaultImpls.debug$default(this.logger, "CaloriesActivityBuffer", "Sub-interval processing completed, cleared sub-interval queue.", null, 4, null);
        return activityInterval2;
    }

    private final void consumeActivityInterval(ActivityInterval activityInterval) {
        this.lastSeenTimestampMillis = Long.valueOf(activityInterval.getTimestampEndMillis());
        this.workoutSessionTotalSteps = activityInterval.getIncrementalSteps() + this.workoutSessionTotalSteps;
        ILog iLog = this.logger;
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("Updated session tracking data: ", "lastSeenTimestampMillis=");
        outline113.append(this.lastSeenTimestampMillis);
        outline113.append(", workoutSessionTotalSteps=");
        outline113.append(this.workoutSessionTotalSteps);
        ILog.DefaultImpls.debug$default(iLog, "CaloriesActivityBuffer", outline113.toString(), null, 4, null);
        ILog iLog2 = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Adding interval to current window summary: ");
        outline107.append(GsonUtilsKt.toJson(activityInterval));
        outline107.append('.');
        ILog.DefaultImpls.debug$default(iLog2, "CaloriesActivityBuffer", outline107.toString(), null, 4, null);
        CaloriesActivityWindowSummary caloriesActivityWindowSummary = this.currentWindowSummary;
        caloriesActivityWindowSummary.setDurationSeconds(activityInterval.getDurationSeconds() + caloriesActivityWindowSummary.getDurationSeconds());
        caloriesActivityWindowSummary.setAccumulatedSteps(activityInterval.getIncrementalSteps() + caloriesActivityWindowSummary.getAccumulatedSteps());
        if (activityInterval.getAlgorithmActivityType() != null) {
            caloriesActivityWindowSummary.addToActivityClassification(activityInterval.getAlgorithmActivityType());
        }
        ILog iLog3 = this.logger;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Current window summary (after): ");
        outline1072.append(GsonUtilsKt.toJson(this.currentWindowSummary));
        outline1072.append('.');
        ILog.DefaultImpls.debug$default(iLog3, "CaloriesActivityBuffer", outline1072.toString(), null, 4, null);
        if (this.currentWindowSummary.getDurationSeconds() >= this.bufferLimitSeconds) {
            ILog iLog4 = this.logger;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Buffer limit duration reached (");
            outline1073.append(this.currentWindowSummary.getDurationSeconds());
            outline1073.append(" >= ");
            outline1073.append(this.bufferLimitSeconds);
            outline1073.append(')');
            ILog.DefaultImpls.debug$default(iLog4, "CaloriesActivityBuffer", outline1073.toString(), null, 4, null);
            addCurrentWindowToAvailableSummaries();
        }
    }

    private final ActivityInterval createIntervalFromEvent(ActivityEvent activityEvent) {
        if (activityEvent.eventType == ActivityEvent.EventType.EVENT_TYPE_ACTIVITY && activityEvent.accumulatedSteps < this.workoutSessionTotalSteps) {
            this.workoutSessionTotalSteps = 0;
        }
        Long l = this.lastSeenTimestampMillis;
        ActivityInterval activityInterval = new ActivityInterval(l != null ? l.longValue() : activityEvent.timestamp, activityEvent.timestamp, activityEvent.accumulatedSteps - this.workoutSessionTotalSteps, activityEvent.activityType);
        ILog iLog = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Created base interval: ");
        outline107.append(GsonUtilsKt.toJson(activityInterval));
        outline107.append('.');
        ILog.DefaultImpls.debug$default(iLog, "CaloriesActivityBuffer", outline107.toString(), null, 4, null);
        return activityInterval;
    }

    public final void addEvent(@NotNull ActivityEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        ILog iLog = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Adding event: ");
        outline107.append(GsonUtilsKt.toJson(event));
        ILog.DefaultImpls.debug$default(iLog, "CaloriesActivityBuffer", outline107.toString(), null, 4, null);
        ActivityEvent.EventType eventType = event.eventType;
        if (eventType != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[eventType.ordinal()];
            if (i == 1) {
                ActivityInterval applySubIntervalEvents = applySubIntervalEvents(createIntervalFromEvent(event));
                if (this.currentActivityState != ActivityState.ACTIVE) {
                    return;
                }
                consumeActivityInterval(applySubIntervalEvents);
                return;
            } else if (i == 2) {
                ActivityEvent activityEvent = new ActivityEvent();
                activityEvent.activityType = event.activityType;
                activityEvent.timestamp = event.timestamp;
                activityEvent.accumulatedSteps = this.workoutSessionTotalSteps;
                activityEvent.cadenceStepsPerMinute = event.cadenceStepsPerMinute;
                applySubIntervalEvents(createIntervalFromEvent(activityEvent));
                flush();
                return;
            } else if (i == 3 || i == 4) {
                ILog.DefaultImpls.debug$default(this.logger, "CaloriesActivityBuffer", "Adding sub-interval event to queue.", null, 4, null);
                this.subIntervalEventQueue.add(event);
                return;
            } else if (i != 5) {
                return;
            }
        }
        ILog iLog2 = this.logger;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unsupported ActivityEvent.EventType (");
        outline1072.append(event.eventType);
        outline1072.append(") received.");
        ILog.DefaultImpls.error$default(iLog2, "CaloriesActivityBuffer", outline1072.toString(), null, 4, null);
    }

    @NotNull
    public final Pair<ActivityInterval, ActivityInterval> bisectInterval$AlexaMobileAndroidFitnessExtension_release(@NotNull ActivityInterval interval, long j) {
        Intrinsics.checkParameterIsNotNull(interval, "interval");
        ILog iLog = this.logger;
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Bisecting interval at ", j, ". Interval: ");
        outline111.append(GsonUtilsKt.toJson(interval));
        outline111.append('.');
        ILog.DefaultImpls.debug$default(iLog, "CaloriesActivityBuffer", outline111.toString(), null, 4, null);
        if (j >= interval.getTimestampStartMillis()) {
            if (j <= interval.getTimestampEndMillis()) {
                if (interval.getTimestampStartMillis() != interval.getTimestampEndMillis()) {
                    double timestampStartMillis = (j - interval.getTimestampStartMillis()) / (interval.getTimestampEndMillis() - interval.getTimestampStartMillis());
                    int incrementalSteps = (int) (interval.getIncrementalSteps() * timestampStartMillis);
                    int incrementalSteps2 = interval.getIncrementalSteps() - incrementalSteps;
                    ILog iLog2 = this.logger;
                    ILog.DefaultImpls.debug$default(iLog2, "CaloriesActivityBuffer", "Interpolation data: leftPercentage=" + timestampStartMillis + ", leftIntervalSteps=" + incrementalSteps + ", rightIntervalSteps=" + incrementalSteps2, null, 4, null);
                    ActivityInterval activityInterval = new ActivityInterval(interval.getTimestampStartMillis(), j, incrementalSteps, interval.getAlgorithmActivityType());
                    ActivityInterval activityInterval2 = new ActivityInterval(1 + j, interval.getTimestampEndMillis(), incrementalSteps2, interval.getAlgorithmActivityType());
                    ILog iLog3 = this.logger;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bisected pair: leftInterval=");
                    outline107.append(GsonUtilsKt.toJson(activityInterval));
                    outline107.append(", rightInterval=");
                    outline107.append(GsonUtilsKt.toJson(activityInterval2));
                    outline107.append('.');
                    ILog.DefaultImpls.debug$default(iLog3, "CaloriesActivityBuffer", outline107.toString(), null, 4, null);
                    return new Pair<>(activityInterval, activityInterval2);
                }
                throw new IllegalArgumentException("interval cannot represent a 0-second duration");
            }
            throw new IllegalArgumentException("bisectionTimestamp must be <= interval.timestampEnd");
        }
        throw new IllegalArgumentException("bisectionTimestamp must be >= interval.timestampStart");
    }

    public final void flush() {
        ILog.DefaultImpls.info$default(this.logger, "CaloriesActivityBuffer", "Flush requested.", null, 4, null);
        if (this.currentWindowSummary.getDurationSeconds() > 0) {
            ILog iLog = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current window covers ");
            outline107.append(this.currentWindowSummary.getDurationSeconds());
            outline107.append(" second(s).");
            outline107.append(" Flushing current window to available summaries.");
            ILog.DefaultImpls.debug$default(iLog, "CaloriesActivityBuffer", outline107.toString(), null, 4, null);
            addCurrentWindowToAvailableSummaries();
        }
    }
}
