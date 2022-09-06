package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.algorithms.ActivityType;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEvent;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAlgorithmUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a$\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u001a0\u0010\u000b\u001a\u0004\u0018\u00010\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u001a\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r\u001a\u0018\u0010\u0012\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\r\u001a\u0018\u0010\u0013\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\r\u001a\u0018\u0010\u0014\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\r\u001a\u0018\u0010\u0015\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\r\u001a\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r\u001a \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d¨\u0006\u001e"}, d2 = {"createActivityEvent", "Lcom/amazon/alexa/fitness/algorithms/ActivityEvent;", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "getEventFor", "event", "Lcom/amazon/alexa/fitness/algorithms/ActivityEvent$EventType;", "time", "", "lastActivityType", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "getEventForStateChange", "previousState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "currentState", "paused", "", "newState", "recovered", Constants.TIMELINE_RESUMED_STR, "started", PlaybackStates.STARTING, "stopped", "getActivityEventsFromSession", "", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithm;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAlgorithmUtilKt {
    @NotNull
    public static final ActivityEvent createActivityEvent(@NotNull Sample.EchoBudSample sample) {
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.eventType = ActivityEvent.EventType.EVENT_TYPE_ACTIVITY;
        activityEvent.timestamp = sample.getCollectionTimestamp();
        activityEvent.activityType = StepsToDistanceAlgorithmAdapterKt.toAlgorithmActivityType(sample.getActivity());
        activityEvent.accumulatedSteps = sample.getSteps();
        activityEvent.cadenceStepsPerMinute = sample.getCadence();
        return activityEvent;
    }

    @NotNull
    public static final List<ActivityEvent> getActivityEventsFromSession(@NotNull FitnessAlgorithm getActivityEventsFromSession, @NotNull Session session, @NotNull SampleStore sampleStore) {
        List<ActivityEvent> sortedWith;
        Intrinsics.checkParameterIsNotNull(getActivityEventsFromSession, "$this$getActivityEventsFromSession");
        Intrinsics.checkParameterIsNotNull(session, "session");
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        ArrayList arrayList = new ArrayList();
        FitnessSessionState fitnessSessionState = null;
        while (true) {
            FitnessSessionState fitnessSessionState2 = fitnessSessionState;
            for (SessionEvent sessionEvent : session.getEvents()) {
                if (sessionEvent.getEventType() instanceof SessionEventType.StateChangeEvent) {
                    SessionEventType eventType = sessionEvent.getEventType();
                    if (eventType != null) {
                        SessionEventType.StateChangeEvent stateChangeEvent = (SessionEventType.StateChangeEvent) eventType;
                        ActivityEvent eventForStateChange$default = getEventForStateChange$default(fitnessSessionState2, stateChangeEvent.getState(), sessionEvent.getDate().getEpochMilli(), null, 8, null);
                        if (eventForStateChange$default != null) {
                            arrayList.add(eventForStateChange$default);
                        }
                        fitnessSessionState = stateChangeEvent.getState();
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType.StateChangeEvent");
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Sample sample : sampleStore.queryAllSamples(session.getConfiguration().getSessionId())) {
                if (sample instanceof Sample.EchoBudSample) {
                    arrayList2.add(createActivityEvent((Sample.EchoBudSample) sample));
                }
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(arrayList);
            arrayList3.addAll(arrayList2);
            sortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList3, new Comparator<T>() { // from class: com.amazon.alexa.fitness.algorithm.FitnessAlgorithmUtilKt$$special$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((ActivityEvent) t).timestamp), Long.valueOf(((ActivityEvent) t2).timestamp));
                    return compareValues;
                }
            });
            return sortedWith;
        }
    }

    @NotNull
    public static final ActivityEvent getEventFor(@NotNull ActivityEvent.EventType event, long j, @Nullable ActivityType activityType) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.timestamp = j;
        activityEvent.eventType = event;
        if (activityType == null) {
            activityType = ActivityType.ACTIVITY_TYPE_IDLE;
        }
        activityEvent.activityType = activityType;
        return activityEvent;
    }

    public static /* synthetic */ ActivityEvent getEventFor$default(ActivityEvent.EventType eventType, long j, ActivityType activityType, int i, Object obj) {
        if ((i & 2) != 0) {
            j = DateTime.Companion.now().getEpochMilli();
        }
        if ((i & 4) != 0) {
            activityType = null;
        }
        return getEventFor(eventType, j, activityType);
    }

    @Nullable
    public static final ActivityEvent getEventForStateChange(@Nullable FitnessSessionState fitnessSessionState, @NotNull FitnessSessionState currentState, long j, @Nullable ActivityType activityType) {
        Intrinsics.checkParameterIsNotNull(currentState, "currentState");
        if (started(fitnessSessionState, currentState)) {
            return getEventFor(ActivityEvent.EventType.EVENT_TYPE_START, j, activityType);
        }
        if (paused(currentState)) {
            return getEventFor(ActivityEvent.EventType.EVENT_TYPE_PAUSE, j, activityType);
        }
        if (!resumed(fitnessSessionState, currentState)) {
            return null;
        }
        return getEventFor(ActivityEvent.EventType.EVENT_TYPE_RESUME, j, activityType);
    }

    public static /* synthetic */ ActivityEvent getEventForStateChange$default(FitnessSessionState fitnessSessionState, FitnessSessionState fitnessSessionState2, long j, ActivityType activityType, int i, Object obj) {
        if ((i & 4) != 0) {
            j = DateTime.Companion.now().getEpochMilli();
        }
        if ((i & 8) != 0) {
            activityType = null;
        }
        return getEventForStateChange(fitnessSessionState, fitnessSessionState2, j, activityType);
    }

    public static final boolean paused(@NotNull FitnessSessionState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        return newState == FitnessSessionState.PAUSED;
    }

    public static final boolean recovered(@Nullable FitnessSessionState fitnessSessionState, @NotNull FitnessSessionState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        return fitnessSessionState == FitnessSessionState.RECOVERING && newState == FitnessSessionState.ACTIVE;
    }

    public static final boolean resumed(@Nullable FitnessSessionState fitnessSessionState, @NotNull FitnessSessionState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        return fitnessSessionState == FitnessSessionState.RESUMING && newState == FitnessSessionState.ACTIVE;
    }

    public static final boolean started(@Nullable FitnessSessionState fitnessSessionState, @NotNull FitnessSessionState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        return fitnessSessionState == FitnessSessionState.STARTING && newState == FitnessSessionState.ACTIVE;
    }

    public static final boolean starting(@Nullable FitnessSessionState fitnessSessionState, @NotNull FitnessSessionState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        return (fitnessSessionState == FitnessSessionState.IDLE || fitnessSessionState == null) && newState == FitnessSessionState.STARTING;
    }

    public static final boolean stopped(@NotNull FitnessSessionState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        return newState == FitnessSessionState.STOPPING;
    }
}
