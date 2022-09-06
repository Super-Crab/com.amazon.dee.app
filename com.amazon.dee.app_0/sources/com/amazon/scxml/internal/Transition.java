package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Transition.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002BA\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0011\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010$\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010%H\u0096\u0002J\u0006\u0010&\u001a\u00020\u000bJ\u0006\u0010'\u001a\u00020(J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001f0*J\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001f0*J\b\u0010,\u001a\u0004\u0018\u00010\u001fJ\b\u0010-\u001a\u0004\u0018\u00010\u001fJ\b\u0010.\u001a\u00020\rH\u0016J\u000e\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u000201J\u0015\u00102\u001a\u00020(2\u0006\u0010\u001e\u001a\u00020\u001fH\u0000¢\u0006\u0002\b3J\u000e\u00104\u001a\u00020(2\u0006\u0010 \u001a\u00020\u001fJ\b\u00105\u001a\u00020\u0005H\u0016R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010¨\u00066"}, d2 = {"Lcom/amazon/scxml/internal/Transition;", "Lcom/amazon/scxml/logging/Loggable;", "", DefaultDeliveryClient.EVENTS_DIRECTORY, "", "", "targetId", "cond", "executables", "Lcom/amazon/scxml/internal/Executable;", "isExternal", "", "documentOrderWithinSourceState", "", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZI)V", "getCond", "()Ljava/lang/String;", "getDocumentOrderWithinSourceState", "()I", "getEvents", "()Ljava/util/List;", "getExecutables", "id", "()Z", "machine", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "getMachine", "()Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "setMachine", "(Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;)V", "sourceState", "Lcom/amazon/scxml/internal/State;", "target", "getTargetId", "compareTo", "other", "equals", "", "evaluateCondition", "execute", "", "getEntrySet", "", "getExitSet", "getSourceState", "getTarget", "hashCode", "matches", "externalEvent", "Lcom/amazon/scxml/internal/Event;", "setSourceState", "setSourceState$AlexaMobileAndroidVoice_TTA_release", "setTarget", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public class Transition extends Loggable implements Comparable<Transition> {
    @NotNull
    private final String cond;
    private final int documentOrderWithinSourceState;
    @NotNull
    private final List<String> events;
    @NotNull
    private final List<Executable> executables;
    private final String id;
    private final boolean isExternal;
    @Nullable
    private SCXMLStateMachineExecutor machine;
    private State sourceState;
    private State target;
    @NotNull
    private final String targetId;

    /* JADX WARN: Multi-variable type inference failed */
    public Transition(@NotNull List<String> events, @NotNull String targetId, @NotNull String cond, @NotNull List<? extends Executable> executables, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(events, "events");
        Intrinsics.checkParameterIsNotNull(targetId, "targetId");
        Intrinsics.checkParameterIsNotNull(cond, "cond");
        Intrinsics.checkParameterIsNotNull(executables, "executables");
        this.events = events;
        this.targetId = targetId;
        this.cond = cond;
        this.executables = executables;
        this.isExternal = z;
        this.documentOrderWithinSourceState = i;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.id = uuid;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Transition)) {
            obj = null;
        }
        Transition transition = (Transition) obj;
        if (transition != null) {
            return Intrinsics.areEqual(transition.id, this.id);
        }
        return false;
    }

    public final boolean evaluateCondition() {
        SCXMLStateMachineExecutor sCXMLStateMachineExecutor = this.machine;
        boolean z = false;
        if (sCXMLStateMachineExecutor != null) {
            if (this.cond.length() == 0) {
                z = true;
            }
            if (!z) {
                return sCXMLStateMachineExecutor.evaluateCondition(this.cond);
            }
            return true;
        }
        return false;
    }

    public final void execute() {
        SCXMLStateMachineExecutor sCXMLStateMachineExecutor = this.machine;
        if (sCXMLStateMachineExecutor != null) {
            for (Executable executable : this.executables) {
                executable.execute(sCXMLStateMachineExecutor);
            }
        }
    }

    @NotNull
    public final String getCond() {
        return this.cond;
    }

    public final int getDocumentOrderWithinSourceState() {
        return this.documentOrderWithinSourceState;
    }

    @NotNull
    public final Set<State> getEntrySet() {
        Set<State> emptySet;
        Set intersect;
        State initialState;
        State state = this.target;
        if (state != null) {
            Set<State> exitSet = getExitSet();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            LinkedList linkedList = new LinkedList();
            linkedList.push(state);
            while (!linkedList.isEmpty()) {
                State next = (State) linkedList.pop();
                if (!linkedHashSet.contains(next) && (!next.isActive$AlexaMobileAndroidVoice_TTA_release() || exitSet.contains(next))) {
                    Intrinsics.checkExpressionValueIsNotNull(next, "next");
                    linkedHashSet.add(next);
                    State parent = next.getParent();
                    if (parent != null) {
                        linkedList.push(parent);
                    }
                    if (next.isParallel()) {
                        linkedList.addAll(next.getSubstates());
                    }
                    if (!next.isParallel() && !next.isAtomic()) {
                        intersect = CollectionsKt___CollectionsKt.intersect(linkedHashSet, next.getSubstateSet());
                        if (intersect.isEmpty() && (initialState = next.getInitialState()) != null) {
                            linkedList.push(initialState);
                        }
                    }
                }
            }
            return linkedHashSet;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @NotNull
    public final List<String> getEvents() {
        return this.events;
    }

    @NotNull
    public final List<Executable> getExecutables() {
        return this.executables;
    }

    @NotNull
    public final Set<State> getExitSet() {
        Set<State> emptySet;
        Set<State> emptySet2;
        Set<State> set;
        Set<State> emptySet3;
        Set<State> set2;
        Set<State> emptySet4;
        State state = this.sourceState;
        if (state != null) {
            State state2 = this.target;
            if (state2 != null) {
                if (!state.isActive$AlexaMobileAndroidVoice_TTA_release()) {
                    emptySet4 = SetsKt__SetsKt.emptySet();
                    return emptySet4;
                }
                if (!this.isExternal && !state.isParallel() && !state.isAtomic() && state.isProperAncestorOf(state2)) {
                    set2 = CollectionsKt___CollectionsKt.toSet(state.getProperActiveDescendants());
                    return set2;
                }
                State leastCommonProperCompoundAncestor = state.getLeastCommonProperCompoundAncestor(state2);
                if (leastCommonProperCompoundAncestor == null) {
                    Logger log = Loggable.Companion.getLog();
                    String tag = getTAG();
                    log.w(tag, "lcca of (" + state + ", " + state2 + ") is nil");
                    emptySet3 = SetsKt__SetsKt.emptySet();
                    return emptySet3;
                }
                set = CollectionsKt___CollectionsKt.toSet(leastCommonProperCompoundAncestor.getProperActiveDescendants());
                return set;
            }
            emptySet2 = SetsKt__SetsKt.emptySet();
            return emptySet2;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Nullable
    public final SCXMLStateMachineExecutor getMachine() {
        return this.machine;
    }

    @Nullable
    public final State getSourceState() {
        return this.sourceState;
    }

    @Nullable
    public final State getTarget() {
        return this.target;
    }

    @NotNull
    public final String getTargetId() {
        return this.targetId;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public final boolean isExternal() {
        return this.isExternal;
    }

    public final boolean matches(@NotNull Event externalEvent) {
        Set set;
        Set intersect;
        Intrinsics.checkParameterIsNotNull(externalEvent, "externalEvent");
        if (this.events.isEmpty()) {
            return false;
        }
        if (this.events.contains("*")) {
            return true;
        }
        Set<String> allMatchingEventNames$AlexaMobileAndroidVoice_TTA_release = externalEvent.getAllMatchingEventNames$AlexaMobileAndroidVoice_TTA_release();
        set = CollectionsKt___CollectionsKt.toSet(this.events);
        intersect = CollectionsKt___CollectionsKt.intersect(allMatchingEventNames$AlexaMobileAndroidVoice_TTA_release, set);
        return !intersect.isEmpty();
    }

    public final void setMachine(@Nullable SCXMLStateMachineExecutor sCXMLStateMachineExecutor) {
        this.machine = sCXMLStateMachineExecutor;
    }

    public final void setSourceState$AlexaMobileAndroidVoice_TTA_release(@NotNull State sourceState) {
        Intrinsics.checkParameterIsNotNull(sourceState, "sourceState");
        this.sourceState = sourceState;
    }

    public final void setTarget(@NotNull State target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        this.target = target;
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        String str = this.targetId;
        boolean z = true;
        if (str.length() == 0) {
            str = "null";
        }
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
        Object obj = this.sourceState;
        if (obj == null) {
            obj = "";
        }
        outline104.append(obj);
        outline104.append("] -> [");
        outline104.append(str);
        outline104.append(JsonReaderKt.END_LIST);
        String sb = outline104.toString();
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.events, " | ", null, null, 0, null, null, 62, null);
        if (this.cond.length() != 0) {
            z = false;
        }
        if (z) {
            return "on(" + joinToString$default + ") ALWAYS move(" + sb + ')';
        }
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("on(", joinToString$default, ") WHEN(");
        outline115.append(this.cond);
        outline115.append(") move(");
        outline115.append(sb);
        outline115.append(')');
        return outline115.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Transition other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        State state = this.sourceState;
        int i = 0;
        int documentOrder = state != null ? state.getDocumentOrder() : 0;
        State state2 = other.sourceState;
        if (state2 != null) {
            i = state2.getDocumentOrder();
        }
        return Intrinsics.compare(documentOrder, i);
    }
}
