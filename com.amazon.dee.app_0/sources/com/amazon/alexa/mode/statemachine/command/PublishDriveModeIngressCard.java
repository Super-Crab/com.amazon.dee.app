package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class PublishDriveModeIngressCard extends Command {
    public PublishDriveModeIngressCard(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getHomeChannelInteractor().publishDriveModeIngressCard();
    }
}
