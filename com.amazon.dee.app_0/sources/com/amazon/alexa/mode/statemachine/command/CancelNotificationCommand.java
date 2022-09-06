package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class CancelNotificationCommand extends Command {
    public CancelNotificationCommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getNotificationHelper().mo358get().cancelNotification();
    }
}
