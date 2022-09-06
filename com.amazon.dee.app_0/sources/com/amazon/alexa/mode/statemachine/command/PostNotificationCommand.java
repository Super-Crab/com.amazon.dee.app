package com.amazon.alexa.mode.statemachine.command;

import androidx.annotation.NonNull;
import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class PostNotificationCommand extends Command {
    private String mText;
    private String mTitle;

    public PostNotificationCommand(StateDependencies stateDependencies, @NonNull String str, @NonNull String str2) {
        super(stateDependencies);
        this.mTitle = str;
        this.mText = str2;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getNotificationHelper().mo358get().sendNotification(this.mTitle, this.mText);
    }

    public String getText() {
        return this.mText;
    }

    public String getTitle() {
        return this.mTitle;
    }
}
