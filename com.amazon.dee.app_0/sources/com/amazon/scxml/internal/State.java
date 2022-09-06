package com.amazon.scxml.internal;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: State.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002Bg\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b¢\u0006\u0002\u0010\u0013J\u0012\u00101\u001a\u0004\u0018\u00010\u00002\u0006\u00102\u001a\u00020\u0000H\u0002J\u0011\u00103\u001a\u00020\t2\u0006\u00102\u001a\u00020\u0000H\u0096\u0002J\u0013\u00104\u001a\u00020\u00072\b\u00102\u001a\u0004\u0018\u000105H\u0096\u0002J\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u000207J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0005H\u0002J\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00050,J\b\u0010=\u001a\u0004\u0018\u00010\u0000J\u0012\u0010>\u001a\u0004\u0018\u00010\f2\u0006\u0010?\u001a\u00020@H\u0002J\u0010\u0010A\u001a\u0004\u0018\u00010\f2\u0006\u0010?\u001a\u00020@J\u0012\u0010B\u001a\u0004\u0018\u00010\u00002\u0006\u00102\u001a\u00020\u0000H\u0016J\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bJ\b\u0010D\u001a\u00020\tH\u0016J\"\u0010E\u001a\u0002072\u0006\u0010%\u001a\u00020&2\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00000GJ\u000e\u0010H\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u0000J\b\u0010I\u001a\u00020\u0007H\u0016J\u0006\u0010J\u001a\u00020\u0007J\b\u0010K\u001a\u00020\u0005H\u0016R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00078@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010!R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001b\"\u0004\b*\u0010\u001dR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00000,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/amazon/scxml/internal/State;", "Lcom/amazon/scxml/logging/Loggable;", "", RouteParameter.PARENT, "id", "", "isParallel", "", "documentOrder", "", "transitions", "", "Lcom/amazon/scxml/internal/Transition;", "initialStateName", "onEnter", "Lcom/amazon/scxml/internal/OnEnter;", "onExit", "Lcom/amazon/scxml/internal/OnExit;", "substates", "(Lcom/amazon/scxml/internal/State;Ljava/lang/String;ZILjava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "_isActive", "getDocumentOrder", "()I", "getId", "()Ljava/lang/String;", "initialState", "getInitialState", "()Lcom/amazon/scxml/internal/State;", "setInitialState", "(Lcom/amazon/scxml/internal/State;)V", "value", "isActive", "isActive$AlexaMobileAndroidVoice_TTA_release", "()Z", "setActive$AlexaMobileAndroidVoice_TTA_release", "(Z)V", "isAtomic", "machine", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "myEventsPlusAncestorsEvents", "", "getParent", "setParent", "substateSet", "", "getSubstateSet", "()Ljava/util/Set;", "getSubstates", "()Ljava/util/List;", "_getLeastCommonProperAncestor", "other", "compareTo", "equals", "", "executeEntry", "", "executeExit", "fatalError", "", "message", "getAllRelevantEventsFromSelfOrAncestors", "getFirstActiveAtomicDescendantIncludingSelf", "getFirstTransitionThatCanHandleEvent", "event", "Lcom/amazon/scxml/internal/Event;", "getFirstTransitionThatCanHandleEventInSelfOrAncestors", "getLeastCommonProperCompoundAncestor", "getProperActiveDescendants", "hashCode", "initialize", "allStates", "", "isProperAncestorOf", "isRoot", "isStateValid", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public class State extends Loggable implements Comparable<State> {
    private boolean _isActive;
    private final int documentOrder;
    @NotNull
    private final String id;
    @Nullable
    private State initialState;
    private final boolean isAtomic;
    private final boolean isParallel;
    private SCXMLStateMachineExecutor machine;
    private Set<String> myEventsPlusAncestorsEvents;
    private final List<OnEnter> onEnter;
    private final List<OnExit> onExit;
    @Nullable
    private State parent;
    @NotNull
    private final Set<State> substateSet;
    @NotNull
    private final List<State> substates;
    private final List<Transition> transitions;

    /* JADX WARN: Multi-variable type inference failed */
    public State(@Nullable State state, @NotNull String id, boolean z, int i, @NotNull List<? extends Transition> transitions, @NotNull String initialStateName, @NotNull List<OnEnter> onEnter, @NotNull List<OnExit> onExit, @NotNull List<? extends State> substates) {
        Set<State> set;
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(transitions, "transitions");
        Intrinsics.checkParameterIsNotNull(initialStateName, "initialStateName");
        Intrinsics.checkParameterIsNotNull(onEnter, "onEnter");
        Intrinsics.checkParameterIsNotNull(onExit, "onExit");
        Intrinsics.checkParameterIsNotNull(substates, "substates");
        this.parent = state;
        this.id = id;
        this.isParallel = z;
        this.documentOrder = i;
        this.transitions = transitions;
        this.onEnter = onEnter;
        this.onExit = onExit;
        this.substates = substates;
        set = CollectionsKt___CollectionsKt.toSet(this.substates);
        this.substateSet = set;
        if (!this.substates.isEmpty()) {
            this.initialState = (State) CollectionsKt.first((List<? extends Object>) this.substates);
            Iterator<State> it2 = this.substates.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                State next = it2.next();
                if (Intrinsics.areEqual(next.id, initialStateName)) {
                    this.initialState = next;
                    break;
                }
            }
        }
        this.isAtomic = this.substates.isEmpty();
    }

    private final State _getLeastCommonProperAncestor(State state) {
        if (Intrinsics.areEqual(this, state)) {
            return this.parent;
        }
        ArrayList arrayList = new ArrayList();
        for (State state2 = this.parent; state2 != null; state2 = state2.parent) {
            arrayList.add(state2);
        }
        for (State state3 = state.parent; state3 != null; state3 = state3.parent) {
            if (arrayList.contains(state3)) {
                return state3;
            }
        }
        return null;
    }

    private final Void fatalError(String str) {
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.e(tag, "fatalError: " + str);
        throw new Exception(str);
    }

    private final Transition getFirstTransitionThatCanHandleEvent(Event event) {
        if (!isActive$AlexaMobileAndroidVoice_TTA_release()) {
            return null;
        }
        for (Transition transition : this.transitions) {
            if (transition.matches(event) && transition.evaluateCondition()) {
                return transition;
            }
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof State)) {
            obj = null;
        }
        State state = (State) obj;
        if (state != null) {
            return Intrinsics.areEqual(state.id, this.id);
        }
        return false;
    }

    public final void executeEntry() {
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "Executing Entry for [" + this + JsonReaderKt.END_LIST);
        SCXMLStateMachineExecutor sCXMLStateMachineExecutor = this.machine;
        if (sCXMLStateMachineExecutor != null) {
            setActive$AlexaMobileAndroidVoice_TTA_release(true);
            for (OnEnter onEnter : this.onEnter) {
                onEnter.execute(sCXMLStateMachineExecutor);
            }
        }
    }

    public final void executeExit() {
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "Executing Exit for [" + this + JsonReaderKt.END_LIST);
        SCXMLStateMachineExecutor sCXMLStateMachineExecutor = this.machine;
        if (sCXMLStateMachineExecutor != null) {
            for (OnExit onExit : this.onExit) {
                onExit.execute(sCXMLStateMachineExecutor);
            }
            setActive$AlexaMobileAndroidVoice_TTA_release(false);
        }
    }

    @NotNull
    public final Set<String> getAllRelevantEventsFromSelfOrAncestors() {
        Set union;
        Set<String> mutableSet;
        Set union2;
        Set<String> set = this.myEventsPlusAncestorsEvents;
        if (set != null) {
            return set;
        }
        Set<String> linkedHashSet = new LinkedHashSet<>();
        for (Transition transition : this.transitions) {
            union2 = CollectionsKt___CollectionsKt.union(linkedHashSet, transition.getEvents());
            linkedHashSet = CollectionsKt___CollectionsKt.toMutableSet(union2);
        }
        State state = this.parent;
        if (state == null) {
            this.myEventsPlusAncestorsEvents = linkedHashSet;
            return linkedHashSet;
        }
        union = CollectionsKt___CollectionsKt.union(linkedHashSet, state.getAllRelevantEventsFromSelfOrAncestors());
        mutableSet = CollectionsKt___CollectionsKt.toMutableSet(union);
        this.myEventsPlusAncestorsEvents = mutableSet;
        return mutableSet;
    }

    public final int getDocumentOrder() {
        return this.documentOrder;
    }

    @Nullable
    public final State getFirstActiveAtomicDescendantIncludingSelf() {
        if (!isActive$AlexaMobileAndroidVoice_TTA_release()) {
            return null;
        }
        if (this.isAtomic) {
            return this;
        }
        for (State state : this.substates) {
            if (state.isActive$AlexaMobileAndroidVoice_TTA_release()) {
                return state.getFirstActiveAtomicDescendantIncludingSelf();
            }
        }
        return null;
    }

    @Nullable
    public final Transition getFirstTransitionThatCanHandleEventInSelfOrAncestors(@NotNull Event event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (!isActive$AlexaMobileAndroidVoice_TTA_release()) {
            return null;
        }
        Transition firstTransitionThatCanHandleEvent = getFirstTransitionThatCanHandleEvent(event);
        if (firstTransitionThatCanHandleEvent != null) {
            return firstTransitionThatCanHandleEvent;
        }
        State state = this.parent;
        if (state == null) {
            return null;
        }
        return state.getFirstTransitionThatCanHandleEventInSelfOrAncestors(event);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final State getInitialState() {
        return this.initialState;
    }

    @Nullable
    public State getLeastCommonProperCompoundAncestor(@NotNull State other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        State _getLeastCommonProperAncestor = _getLeastCommonProperAncestor(other);
        while (_getLeastCommonProperAncestor != null && _getLeastCommonProperAncestor.isParallel) {
            _getLeastCommonProperAncestor = _getLeastCommonProperAncestor.parent;
        }
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "lcpca([" + this + "], [" + other + "]) = [" + _getLeastCommonProperAncestor + JsonReaderKt.END_LIST);
        return _getLeastCommonProperAncestor;
    }

    @Nullable
    public final State getParent() {
        return this.parent;
    }

    @NotNull
    public final List<State> getProperActiveDescendants() {
        List<State> emptyList;
        if (!isActive$AlexaMobileAndroidVoice_TTA_release()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(this.substates);
        ArrayList arrayList = new ArrayList();
        while (!linkedList.isEmpty()) {
            State next = (State) linkedList.pop();
            if (next.isActive$AlexaMobileAndroidVoice_TTA_release()) {
                linkedList.addAll(next.substates);
                Intrinsics.checkExpressionValueIsNotNull(next, "next");
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    @NotNull
    public final Set<State> getSubstateSet() {
        return this.substateSet;
    }

    @NotNull
    public final List<State> getSubstates() {
        return this.substates;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public final void initialize(@NotNull SCXMLStateMachineExecutor machine, @NotNull Map<String, ? extends State> allStates) {
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        Intrinsics.checkParameterIsNotNull(allStates, "allStates");
        this.machine = machine;
        for (Transition transition : this.transitions) {
            transition.setMachine(machine);
            transition.setSourceState$AlexaMobileAndroidVoice_TTA_release(this);
            if (!(transition.getTargetId().length() == 0)) {
                State state = allStates.get(transition.getTargetId());
                if (state != null) {
                    transition.setTarget(state);
                } else {
                    fatalError("transition [" + transition + "] in state [" + this + "] does not have a valid target state [" + transition.getTargetId() + JsonReaderKt.END_LIST);
                    throw null;
                }
            }
        }
        for (OnExit onExit : this.onExit) {
            onExit.setSourceState(this);
        }
        for (OnEnter onEnter : this.onEnter) {
            onEnter.setSourceState(this);
        }
    }

    public final boolean isActive$AlexaMobileAndroidVoice_TTA_release() {
        return this._isActive;
    }

    public final boolean isAtomic() {
        return this.isAtomic;
    }

    public final boolean isParallel() {
        return this.isParallel;
    }

    public final boolean isProperAncestorOf(@NotNull State other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (Intrinsics.areEqual(this, other)) {
            return false;
        }
        for (State state = other.parent; state != null; state = state.parent) {
            if (Intrinsics.areEqual(state, this)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRoot() {
        return false;
    }

    public final boolean isStateValid() {
        int i;
        if (!isRoot() || isActive$AlexaMobileAndroidVoice_TTA_release()) {
            if (this.isAtomic) {
                return true;
            }
            List<State> list = this.substates;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                i = 0;
                for (State state : list) {
                    if (state.isActive$AlexaMobileAndroidVoice_TTA_release() && (i = i + 1) < 0) {
                        CollectionsKt__CollectionsKt.throwCountOverflow();
                    }
                }
            } else {
                i = 0;
            }
            if (!isActive$AlexaMobileAndroidVoice_TTA_release()) {
                if (i != 0) {
                    return false;
                }
            } else if (this.isParallel) {
                if (i != this.substates.size()) {
                    return false;
                }
            } else if (i != 1) {
                return false;
            }
            for (State state2 : this.substates) {
                if (!state2.isStateValid()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final void setActive$AlexaMobileAndroidVoice_TTA_release(boolean z) {
        this._isActive = z;
        SCXMLStateMachineExecutor sCXMLStateMachineExecutor = this.machine;
        if (sCXMLStateMachineExecutor != null) {
            sCXMLStateMachineExecutor.registerActiveStatusChange(this);
        }
    }

    public final void setInitialState(@Nullable State state) {
        this.initialState = state;
    }

    public final void setParent(@Nullable State state) {
        this.parent = state;
    }

    @NotNull
    public String toString() {
        return this.id;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull State other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Intrinsics.compare(this.documentOrder, other.documentOrder);
    }
}
