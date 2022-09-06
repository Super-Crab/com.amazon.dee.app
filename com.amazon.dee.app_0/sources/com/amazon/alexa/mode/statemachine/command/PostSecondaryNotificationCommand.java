package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class PostSecondaryNotificationCommand extends Command {
    public PostSecondaryNotificationCommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getNotificationHelper().mo358get().sendSecondaryNotification();
    }
}
