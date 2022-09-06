package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionOrchestrator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/UIState;", "", "activeSession", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", ErrorBundle.SUMMARY_ENTRY, "Lcom/amazon/alexa/fitness/api/afx/UISessionSummary;", "history", "Lcom/amazon/alexa/fitness/api/afx/WorkoutHistory;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;Lcom/amazon/alexa/fitness/api/afx/UISessionSummary;Lcom/amazon/alexa/fitness/api/afx/WorkoutHistory;)V", "getActiveSession", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "getHistory", "()Lcom/amazon/alexa/fitness/api/afx/WorkoutHistory;", "getSummary", "()Lcom/amazon/alexa/fitness/api/afx/UISessionSummary;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UIState {
    @Nullable
    private final Session activeSession;
    @NotNull
    private final WorkoutHistory history;
    @Nullable
    private final UISessionSummary summary;

    public UIState(@Nullable Session session, @Nullable UISessionSummary uISessionSummary, @NotNull WorkoutHistory history) {
        Intrinsics.checkParameterIsNotNull(history, "history");
        this.activeSession = session;
        this.summary = uISessionSummary;
        this.history = history;
    }

    public static /* synthetic */ UIState copy$default(UIState uIState, Session session, UISessionSummary uISessionSummary, WorkoutHistory workoutHistory, int i, Object obj) {
        if ((i & 1) != 0) {
            session = uIState.activeSession;
        }
        if ((i & 2) != 0) {
            uISessionSummary = uIState.summary;
        }
        if ((i & 4) != 0) {
            workoutHistory = uIState.history;
        }
        return uIState.copy(session, uISessionSummary, workoutHistory);
    }

    @Nullable
    public final Session component1() {
        return this.activeSession;
    }

    @Nullable
    public final UISessionSummary component2() {
        return this.summary;
    }

    @NotNull
    public final WorkoutHistory component3() {
        return this.history;
    }

    @NotNull
    public final UIState copy(@Nullable Session session, @Nullable UISessionSummary uISessionSummary, @NotNull WorkoutHistory history) {
        Intrinsics.checkParameterIsNotNull(history, "history");
        return new UIState(session, uISessionSummary, history);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UIState)) {
                return false;
            }
            UIState uIState = (UIState) obj;
            return Intrinsics.areEqual(this.activeSession, uIState.activeSession) && Intrinsics.areEqual(this.summary, uIState.summary) && Intrinsics.areEqual(this.history, uIState.history);
        }
        return true;
    }

    @Nullable
    public final Session getActiveSession() {
        return this.activeSession;
    }

    @NotNull
    public final WorkoutHistory getHistory() {
        return this.history;
    }

    @Nullable
    public final UISessionSummary getSummary() {
        return this.summary;
    }

    public int hashCode() {
        Session session = this.activeSession;
        int i = 0;
        int hashCode = (session != null ? session.hashCode() : 0) * 31;
        UISessionSummary uISessionSummary = this.summary;
        int hashCode2 = (hashCode + (uISessionSummary != null ? uISessionSummary.hashCode() : 0)) * 31;
        WorkoutHistory workoutHistory = this.history;
        if (workoutHistory != null) {
            i = workoutHistory.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UIState(activeSession=");
        outline107.append(this.activeSession);
        outline107.append(", summary=");
        outline107.append(this.summary);
        outline107.append(", history=");
        outline107.append(this.history);
        outline107.append(")");
        return outline107.toString();
    }
}
