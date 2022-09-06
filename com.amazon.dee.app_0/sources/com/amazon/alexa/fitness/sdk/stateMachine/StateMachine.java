package com.amazon.alexa.fitness.sdk.stateMachine;

import com.amazon.alexa.fitness.sdk.stateMachine.StateMachinePlan;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0014\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00028\u0002¢\u0006\u0002\u0010\u0007J\u0013\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u0014R\u0013\u0010\u0006\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR \u0010\f\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachine;", "State", "Transition", "Plan", "Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "", "plan", "(Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;)V", "getPlan", "()Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "<set-?>", "state", "getState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "process", "transition", "(Ljava/lang/Object;)Ljava/lang/Object;", "reset", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class StateMachine<State, Transition, Plan extends StateMachinePlan<State, Transition>> {
    @NotNull
    private final Plan plan;
    private State state;

    public StateMachine(@NotNull Plan plan) {
        Intrinsics.checkParameterIsNotNull(plan, "plan");
        this.plan = plan;
        this.state = (State) this.plan.getInitialState();
    }

    @NotNull
    public final Plan getPlan() {
        return this.plan;
    }

    public final State getState() {
        return this.state;
    }

    public final State process(Transition transition) throws StateMachineException {
        Map<Transition, State> map = this.plan.getEdges().get(this.state);
        if (map != null) {
            State state = map.get(transition);
            if (state != null) {
                this.state = state;
                return this.state;
            }
            throw new StateMachineException("No edges from current state with transition.");
        }
        throw new StateMachineException("No transitions from current state.");
    }

    public final void reset() {
        this.state = (State) this.plan.getInitialState();
    }
}
