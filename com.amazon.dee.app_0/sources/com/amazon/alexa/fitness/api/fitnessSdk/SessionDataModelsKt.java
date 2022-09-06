package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.metrics.Metrics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\u0006*\u00020\u0002\u001a\f\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u0014\u0010\t\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "duration", "", "relativeDate", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "lastTouched", "previousState", Metrics.PROCESSING_DURATION, "AlexaMobileAndroidFitnessAPI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionDataModelsKt {
    @NotNull
    public static final FitnessSessionState currentState(@NotNull Session currentState) {
        Intrinsics.checkParameterIsNotNull(currentState, "$this$currentState");
        FitnessSessionState mo12560invoke = new SessionDataModelsKt$currentState$1(currentState).mo12560invoke();
        return mo12560invoke != null ? mo12560invoke : FitnessSessionState.STARTING;
    }

    public static final int duration(@NotNull Session duration, @NotNull DateTime relativeDate) {
        Intrinsics.checkParameterIsNotNull(duration, "$this$duration");
        Intrinsics.checkParameterIsNotNull(relativeDate, "relativeDate");
        DateTime createdAt = duration.getCreatedAt();
        long j = 0;
        DateTime dateTime = createdAt;
        boolean z = false;
        for (SessionEvent sessionEvent : duration.getEvents()) {
            if (sessionEvent.getEventType() instanceof SessionEventType.StateChangeEvent) {
                FitnessSessionState state = ((SessionEventType.StateChangeEvent) sessionEvent.getEventType()).getState();
                if (state == FitnessSessionState.ACTIVE) {
                    if (!z) {
                        dateTime = sessionEvent.getDate();
                    }
                    z = true;
                } else if (state == FitnessSessionState.PAUSED || state == FitnessSessionState.STOPPING) {
                    if (z) {
                        z = false;
                        j = dateTime.until(sessionEvent.getDate()).getMilli() + j;
                    }
                }
            }
        }
        if (z) {
            j += dateTime.until(relativeDate).getMilli();
        }
        return (int) (j / 1000);
    }

    public static /* synthetic */ int duration$default(Session session, DateTime dateTime, int i, Object obj) {
        if ((i & 1) != 0) {
            dateTime = DateTime.Companion.now();
        }
        return duration(session, dateTime);
    }

    @NotNull
    public static final DateTime lastTouched(@NotNull Session lastTouched) {
        DateTime date;
        Intrinsics.checkParameterIsNotNull(lastTouched, "$this$lastTouched");
        SessionEvent sessionEvent = (SessionEvent) CollectionsKt.lastOrNull((List<? extends Object>) lastTouched.getEvents());
        return (sessionEvent == null || (date = sessionEvent.getDate()) == null) ? lastTouched.getCreatedAt() : date;
    }

    @Nullable
    public static final FitnessSessionState previousState(@NotNull Session previousState) {
        List dropLast;
        Intrinsics.checkParameterIsNotNull(previousState, "$this$previousState");
        List<SessionEvent> events = previousState.getEvents();
        ArrayList arrayList = new ArrayList();
        for (Object obj : events) {
            if (((SessionEvent) obj).getEventType() instanceof SessionEventType.StateChangeEvent) {
                arrayList.add(obj);
            }
        }
        dropLast = CollectionsKt___CollectionsKt.dropLast(arrayList, 1);
        SessionEvent sessionEvent = (SessionEvent) CollectionsKt.lastOrNull((List<? extends Object>) dropLast);
        SessionEventType eventType = sessionEvent != null ? sessionEvent.getEventType() : null;
        if (eventType != null) {
            return ((SessionEventType.StateChangeEvent) eventType).getState();
        }
        return null;
    }

    public static final int processingDuration(@NotNull Session processingDuration, @NotNull DateTime relativeDate) {
        Intrinsics.checkParameterIsNotNull(processingDuration, "$this$processingDuration");
        Intrinsics.checkParameterIsNotNull(relativeDate, "relativeDate");
        DateTime createdAt = processingDuration.getCreatedAt();
        FitnessSessionState fitnessSessionState = FitnessSessionState.STARTING;
        long j = 0;
        for (SessionEvent sessionEvent : processingDuration.getEvents()) {
            if (sessionEvent.getEventType() instanceof SessionEventType.StateChangeEvent) {
                FitnessSessionState state = ((SessionEventType.StateChangeEvent) sessionEvent.getEventType()).getState();
                if ((state == FitnessSessionState.PAUSED || state == FitnessSessionState.STOPPING || state == FitnessSessionState.RECOVERING) && fitnessSessionState == FitnessSessionState.ACTIVE) {
                    j += createdAt.until(sessionEvent.getDate()).getMilli();
                }
                createdAt = sessionEvent.getDate();
                fitnessSessionState = state;
            }
        }
        if (fitnessSessionState == FitnessSessionState.ACTIVE) {
            j += createdAt.until(relativeDate).getMilli();
        }
        return (int) (j / 1000);
    }

    public static /* synthetic */ int processingDuration$default(Session session, DateTime dateTime, int i, Object obj) {
        if ((i & 1) != 0) {
            dateTime = DateTime.Companion.now();
        }
        return processingDuration(session, dateTime);
    }
}
