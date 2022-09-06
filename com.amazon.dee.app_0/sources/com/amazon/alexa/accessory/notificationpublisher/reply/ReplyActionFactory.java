package com.amazon.alexa.accessory.notificationpublisher.reply;

import android.service.notification.StatusBarNotification;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.Locale;
/* loaded from: classes.dex */
public final class ReplyActionFactory {
    private static final String REPLY_TITLE = "reply";
    private static final String TAG = "ReplyActionFactory";

    @Nullable
    private ReplyAction createReplyActionUsingDirectReply(@NonNull StatusBarNotification statusBarNotification) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createReplyActionUsingDirectReply - ");
        outline107.append(statusBarNotification.getKey());
        Log.d(str, outline107.toString());
        try {
            int actionCount = NotificationCompat.getActionCount(statusBarNotification.getNotification());
            String str2 = TAG;
            Log.d(str2, "createReplyActionUsingDirectReply - Action count = " + actionCount);
            for (int i = 0; i < actionCount; i++) {
                NotificationCompat.Action action = NotificationCompat.getAction(statusBarNotification.getNotification(), i);
                RemoteInput replyRemoteInput = getReplyRemoteInput(action);
                if (replyRemoteInput != null) {
                    Log.i(TAG, "createReplyActionUsingDirectReply - Create and return ReplyAction");
                    return new ReplyAction(action, replyRemoteInput);
                }
            }
            return null;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("createReplyActionUsingDirectReply - Exception caught - ", e, TAG);
            return null;
        }
    }

    @Nullable
    private ReplyAction createReplyActionUsingWearableExtender(@NonNull StatusBarNotification statusBarNotification) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createReplyActionUsingWearableExtender - ");
        outline107.append(statusBarNotification.getKey());
        Log.d(str, outline107.toString());
        try {
            NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender(statusBarNotification.getNotification());
            int size = wearableExtender.getActions().size();
            String str2 = TAG;
            Log.d(str2, "createReplyActionUsingWearableExtender - Action count = " + size);
            for (NotificationCompat.Action action : wearableExtender.getActions()) {
                RemoteInput replyRemoteInput = getReplyRemoteInput(action);
                if (replyRemoteInput != null) {
                    Log.i(TAG, "createReplyActionUsingWearableExtender - Create and return ReplyAction");
                    return new ReplyAction(action, replyRemoteInput);
                }
            }
            return null;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("createReplyActionUsingWearableExtender - Exception caught - ", e, TAG);
            return null;
        }
    }

    @Nullable
    private RemoteInput getReplyRemoteInput(@NonNull NotificationCompat.Action action) {
        Log.d(TAG, "getReplyRemoteInput");
        if (action.getRemoteInputs() != null) {
            int length = action.getRemoteInputs().length;
            String str = TAG;
            Log.d(str, "getReplyRemoteInput - Number of RemoteInputs = " + length);
            for (int i = 0; i < length; i++) {
                RemoteInput remoteInput = action.getRemoteInputs()[i];
                if (remoteInput != null && remoteInput.getLabel() != null && !Strings.isNullOrEmpty(remoteInput.getResultKey()) && !Strings.isNullOrEmpty(remoteInput.getLabel().toString())) {
                    Log.d(TAG, String.format(Locale.US, "getReplyRemoteInput - RemoteInput Key: %s, RemoteInput Label: %s", remoteInput.getResultKey(), remoteInput.getLabel()));
                    if (remoteInput.getResultKey().toLowerCase().contains(REPLY_TITLE) || remoteInput.getLabel().toString().toLowerCase().contains(REPLY_TITLE)) {
                        Log.i(TAG, "getReplyRemoteInput - Found reply remote input");
                        return remoteInput;
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Nullable
    public synchronized ReplyAction createReplyActionForSbn(StatusBarNotification statusBarNotification) {
        Log.d(TAG, "createReplyActionForSbn");
        Preconditions.notNull(statusBarNotification, "sbn");
        if (statusBarNotification.getNotification() != null && statusBarNotification.getNotification().extras != null) {
            String charSequence = statusBarNotification.getNotification().extras.getCharSequence(NotificationCompat.EXTRA_TITLE, "").toString();
            String charSequence2 = statusBarNotification.getNotification().extras.getCharSequence(NotificationCompat.EXTRA_TEXT, "").toString();
            if (!Strings.isNullOrEmpty(charSequence) && !Strings.isNullOrEmpty(charSequence2)) {
                ReplyAction createReplyActionUsingDirectReply = createReplyActionUsingDirectReply(statusBarNotification);
                if (createReplyActionUsingDirectReply == null) {
                    createReplyActionUsingDirectReply = createReplyActionUsingWearableExtender(statusBarNotification);
                }
                return createReplyActionUsingDirectReply;
            }
            Log.w(TAG, "createReplyActionForSbn - Text or title is invalid");
            return null;
        }
        Log.w(TAG, "createReplyActionForSbn - Notification is null or it does not have extras");
        return null;
    }
}
