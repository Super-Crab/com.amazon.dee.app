package com.amazon.alexa.mode.statemachine.command;

import androidx.annotation.NonNull;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.state.BaseState;
/* loaded from: classes9.dex */
public class StateChangeCommand extends Command {
    private final BaseState mDestinationState;
    private final StateDependencies mStateDependenceis;

    public StateChangeCommand(@NonNull StateDependencies stateDependencies, @NonNull BaseState baseState) {
        super(stateDependencies);
        this.mStateDependenceis = stateDependencies;
        this.mDestinationState = baseState;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        this.mStateDependenceis.getStateTransitionHelper().transition(this.mDestinationState);
    }

    public BaseState getState() {
        return this.mDestinationState;
    }
}
