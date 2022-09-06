package com.amazon.alexa.fitness.sdk.stateMachine;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StateMachinePlan.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001\u0018B-\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u001e\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u00060\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ!\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u00060\u0006HÆ\u0003JF\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002 \b\u0002\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u00060\u0006HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R)\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "State", "Transition", "", "initialState", "edges", "", "(Ljava/lang/Object;Ljava/util/Map;)V", "getEdges", "()Ljava/util/Map;", "getInitialState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/util/Map;)Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "equals", "", "other", "hashCode", "", "toString", "", "Builder", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class StateMachinePlan<State, Transition> {
    @NotNull
    private final Map<State, Map<Transition, State>> edges;
    private final State initialState;

    /* compiled from: StateMachinePlan.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0002\u0010\u0005J#\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00022\u0006\u0010\u0013\u001a\u00028\u00032\u0006\u0010\u0014\u001a\u00028\u0002¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0017R2\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00028\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00020\u00070\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u00028\u0002X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0005¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan$Builder;", "State", "Transition", "", "initialState", "(Ljava/lang/Object;)V", "edges", "", "getEdges", "()Ljava/util/Map;", "setEdges", "(Ljava/util/Map;)V", "getInitialState", "()Ljava/lang/Object;", "setInitialState", "Ljava/lang/Object;", BulkOperationType.add, "", ADMConstants.EXTRA_FROM, "withTransition", "destination", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Builder<State, Transition> {
        @NotNull
        private Map<State, Map<Transition, State>> edges = new LinkedHashMap();
        private State initialState;

        public Builder(State state) {
            this.initialState = state;
        }

        public final void add(State state, Transition transition, State state2) {
            Map<Transition, State> map = this.edges.get(state);
            if (map == null) {
                map = new LinkedHashMap<>();
            }
            map.put(transition, state2);
            this.edges.put(state, map);
        }

        @NotNull
        public final StateMachinePlan<State, Transition> build() {
            return new StateMachinePlan<>(this.initialState, this.edges);
        }

        @NotNull
        public final Map<State, Map<Transition, State>> getEdges() {
            return this.edges;
        }

        public final State getInitialState() {
            return this.initialState;
        }

        public final void setEdges(@NotNull Map<State, Map<Transition, State>> map) {
            Intrinsics.checkParameterIsNotNull(map, "<set-?>");
            this.edges = map;
        }

        public final void setInitialState(State state) {
            this.initialState = state;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StateMachinePlan(State state, @NotNull Map<State, ? extends Map<Transition, ? extends State>> edges) {
        Intrinsics.checkParameterIsNotNull(edges, "edges");
        this.initialState = state;
        this.edges = edges;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StateMachinePlan copy$default(StateMachinePlan stateMachinePlan, Object obj, Map map, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = stateMachinePlan.initialState;
        }
        if ((i & 2) != 0) {
            map = stateMachinePlan.edges;
        }
        return stateMachinePlan.copy(obj, map);
    }

    public final State component1() {
        return this.initialState;
    }

    @NotNull
    public final Map<State, Map<Transition, State>> component2() {
        return this.edges;
    }

    @NotNull
    public final StateMachinePlan<State, Transition> copy(State state, @NotNull Map<State, ? extends Map<Transition, ? extends State>> edges) {
        Intrinsics.checkParameterIsNotNull(edges, "edges");
        return new StateMachinePlan<>(state, edges);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof StateMachinePlan)) {
                return false;
            }
            StateMachinePlan stateMachinePlan = (StateMachinePlan) obj;
            return Intrinsics.areEqual(this.initialState, stateMachinePlan.initialState) && Intrinsics.areEqual(this.edges, stateMachinePlan.edges);
        }
        return true;
    }

    @NotNull
    public final Map<State, Map<Transition, State>> getEdges() {
        return this.edges;
    }

    public final State getInitialState() {
        return this.initialState;
    }

    public int hashCode() {
        State state = this.initialState;
        int i = 0;
        int hashCode = (state != null ? state.hashCode() : 0) * 31;
        Map<State, Map<Transition, State>> map = this.edges;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StateMachinePlan(initialState=");
        outline107.append(this.initialState);
        outline107.append(", edges=");
        outline107.append(this.edges);
        outline107.append(")");
        return outline107.toString();
    }
}
