package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CandidateTransitionData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\f\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/amazon/scxml/internal/CandidateTransitionData;", "Lcom/amazon/scxml/logging/Loggable;", "transition", "Lcom/amazon/scxml/internal/Transition;", "sourceState", "Lcom/amazon/scxml/internal/State;", "activeAtomic", "(Lcom/amazon/scxml/internal/Transition;Lcom/amazon/scxml/internal/State;Lcom/amazon/scxml/internal/State;)V", "getTransition", "()Lcom/amazon/scxml/internal/Transition;", "applyConflictResolution", "Lcom/amazon/scxml/internal/CandidateTransitionData$ConflictResolutionResult;", "other", "equals", "", "", "getEntrySet", "", "getExitSet", "hashCode", "", "toString", "", "ConflictResolutionResult", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CandidateTransitionData extends Loggable {
    private final State activeAtomic;
    private final State sourceState;
    @NotNull
    private final Transition transition;

    /* compiled from: CandidateTransitionData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/scxml/internal/CandidateTransitionData$ConflictResolutionResult;", "", "(Ljava/lang/String;I)V", "THIS_ONE_WINS", "THIS_ONE_LOSES", "NO_CONFLICT", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public enum ConflictResolutionResult {
        THIS_ONE_WINS,
        THIS_ONE_LOSES,
        NO_CONFLICT
    }

    public CandidateTransitionData(@NotNull Transition transition, @NotNull State sourceState, @NotNull State activeAtomic) {
        Intrinsics.checkParameterIsNotNull(transition, "transition");
        Intrinsics.checkParameterIsNotNull(sourceState, "sourceState");
        Intrinsics.checkParameterIsNotNull(activeAtomic, "activeAtomic");
        this.transition = transition;
        this.sourceState = sourceState;
        this.activeAtomic = activeAtomic;
    }

    @NotNull
    public final ConflictResolutionResult applyConflictResolution(@NotNull CandidateTransitionData other) {
        Set intersect;
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (Intrinsics.areEqual(this.transition, other.transition)) {
            return ConflictResolutionResult.NO_CONFLICT;
        }
        intersect = CollectionsKt___CollectionsKt.intersect(getExitSet(), other.getExitSet());
        if (intersect.isEmpty()) {
            return ConflictResolutionResult.NO_CONFLICT;
        }
        if (other.sourceState.isProperAncestorOf(this.sourceState)) {
            Loggable.Companion.getLog().i(getTAG(), "scxml:conflict-resolution (A) win reason: sourceState1 is a proper descendant of sourceState2");
            return ConflictResolutionResult.THIS_ONE_WINS;
        } else if (this.sourceState.isProperAncestorOf(other.sourceState)) {
            Loggable.Companion.getLog().i(getTAG(), "scxml:conflict-resolution (B) win reason: sourceState2 is a proper descendant of sourceState1");
            return ConflictResolutionResult.THIS_ONE_LOSES;
        } else if (this.activeAtomic.getDocumentOrder() <= other.activeAtomic.getDocumentOrder()) {
            Logger log = Loggable.Companion.getLog();
            String tag = getTAG();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("scxml:conflict-resolution (A) win reason: activeAtomic1's (");
            outline107.append(this.activeAtomic);
            outline107.append(") document order [");
            outline107.append(this.activeAtomic.getDocumentOrder());
            outline107.append(")] <= activeAtomic2's (");
            outline107.append(other.activeAtomic);
            outline107.append(") document order [");
            outline107.append(other.activeAtomic.getDocumentOrder());
            outline107.append(JsonReaderKt.END_LIST);
            log.i(tag, outline107.toString());
            return ConflictResolutionResult.THIS_ONE_WINS;
        } else {
            Logger log2 = Loggable.Companion.getLog();
            String tag2 = getTAG();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("scxml:conflict-resolution (B) win reason: activeAtomic1's (");
            outline1072.append(this.activeAtomic);
            outline1072.append(") document order [");
            outline1072.append(this.activeAtomic.getDocumentOrder());
            outline1072.append(")] > activeAtomic2's (");
            outline1072.append(other.activeAtomic);
            outline1072.append(") document order [");
            outline1072.append(other.activeAtomic.getDocumentOrder());
            outline1072.append(JsonReaderKt.END_LIST);
            log2.i(tag2, outline1072.toString());
            return ConflictResolutionResult.THIS_ONE_LOSES;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof CandidateTransitionData)) {
            obj = null;
        }
        CandidateTransitionData candidateTransitionData = (CandidateTransitionData) obj;
        if (candidateTransitionData != null) {
            return Intrinsics.areEqual(this.transition, candidateTransitionData.transition);
        }
        return false;
    }

    @NotNull
    public final Set<State> getEntrySet() {
        return this.transition.getEntrySet();
    }

    @NotNull
    public final Set<State> getExitSet() {
        return this.transition.getExitSet();
    }

    @NotNull
    public final Transition getTransition() {
        return this.transition;
    }

    public int hashCode() {
        return this.transition.hashCode();
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Transition(t=[");
        outline107.append(this.transition);
        outline107.append("], s=[");
        outline107.append(this.sourceState);
        outline107.append("], a=[");
        outline107.append(this.activeAtomic);
        outline107.append("])");
        return outline107.toString();
    }
}
