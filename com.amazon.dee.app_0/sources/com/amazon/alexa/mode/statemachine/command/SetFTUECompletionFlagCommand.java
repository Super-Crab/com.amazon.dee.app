package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class SetFTUECompletionFlagCommand extends Command {
    public SetFTUECompletionFlagCommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getPrefsDialogHelper().setDriveModeConfiguredPref(1);
    }
}
