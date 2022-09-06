package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public abstract class Command {
    private final StateDependencies mStateDependencies;

    public Command(StateDependencies stateDependencies) {
        this.mStateDependencies = stateDependencies;
    }

    public abstract void execute();

    public StateDependencies getDependencies() {
        return this.mStateDependencies;
    }
}
