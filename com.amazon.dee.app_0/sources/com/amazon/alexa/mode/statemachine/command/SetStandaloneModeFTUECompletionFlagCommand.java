package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class SetStandaloneModeFTUECompletionFlagCommand extends Command {
    public SetStandaloneModeFTUECompletionFlagCommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getPrefsDialogHelper().setStandAloneModeConfiguredPref(1);
    }
}
