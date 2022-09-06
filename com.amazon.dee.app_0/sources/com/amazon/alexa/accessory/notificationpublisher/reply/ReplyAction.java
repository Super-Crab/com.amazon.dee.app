package com.amazon.alexa.accessory.notificationpublisher.reply;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
public class ReplyAction {
    private final RemoteInput remoteInput;
    private final NotificationCompat.Action replyAction;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReplyAction(NotificationCompat.Action action, RemoteInput remoteInput) {
        Preconditions.notNull(action, "replyAction");
        Preconditions.notNull(remoteInput, "remoteInput");
        this.replyAction = action;
        this.remoteInput = remoteInput;
    }

    NotificationCompat.Action getReplyAction() {
        return this.replyAction;
    }

    RemoteInput getReplyRemoteInput() {
        return this.remoteInput;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void send(Context context, String str) throws PendingIntent.CanceledException {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putCharSequence(this.remoteInput.getResultKey(), str);
        RemoteInput.addResultsToIntent(new RemoteInput[]{this.remoteInput}, intent, bundle);
        this.replyAction.actionIntent.send(context, 0, intent);
    }
}
