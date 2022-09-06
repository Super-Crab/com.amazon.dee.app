package com.amazon.alexa.accessory.notificationpublisher.reply;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONLoader;
import com.amazon.alexa.accessory.notificationpublisher.utils.LRUHashMap;
import com.google.common.base.Strings;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class ReplyServiceHelper {
    private static final int MAX_REPLY_ACTION_ENTRIES = 500;
    private static final int REPLIED_NOTIFICATION_SET_SIZE = 500;
    private static final String REPLY_ALLOWLIST_FILENAME = "reply_allowlist.json";
    private static final String REPLY_NOTIFICATION_TITLE = "You";
    private static final String TAG = "ReplyServiceHelper";
    private static final LinkedHashSet<RepliedNotification> REPLIED_NOTIFICATIONS = new LinkedHashSet<>(500);
    private static Set<String> replyAllowlist = new HashSet();
    private static LRUHashMap<String, ReplyAction> replyActionLRUHashMap = new LRUHashMap<>(500);
    private static ReplyActionFactory replyActionFactory = new ReplyActionFactory();

    private ReplyServiceHelper() {
    }

    public static boolean doesNotificationSupportReply(String str) {
        String str2 = "doesNotificationSupportReply - " + str;
        return !Strings.isNullOrEmpty(str) && replyActionLRUHashMap.get(str) != null;
    }

    public static void init(Context context) {
        try {
            replyAllowlist = JSONLoader.loadJsonAsSetFromAsset(context, REPLY_ALLOWLIST_FILENAME);
            String str = "Loaded Reply allowlist - " + replyAllowlist;
        } catch (Exception e) {
            Log.e(TAG, "Failed to load Reply allowlist.", e);
        }
    }

    private static boolean isReplyAllowlisted(@NonNull StatusBarNotification statusBarNotification) {
        return replyAllowlist.contains(statusBarNotification.getPackageName());
    }

    public static boolean isReplyNotification(String str, String str2, String str3) {
        return REPLIED_NOTIFICATIONS.contains(new RepliedNotification(str, str2, str3));
    }

    private static void recordSuccessRepliedNotification(String str, String str2) {
        if (REPLIED_NOTIFICATIONS.size() >= 500) {
            REPLIED_NOTIFICATIONS.remove(REPLIED_NOTIFICATIONS.iterator().next());
        }
        REPLIED_NOTIFICATIONS.add(new RepliedNotification(str, REPLY_NOTIFICATION_TITLE, str2));
    }

    public static void removeRepliedNotification(String str, String str2, String str3) {
        REPLIED_NOTIFICATIONS.remove(new RepliedNotification(str, str2, str3));
    }

    public static synchronized boolean sendReply(Context context, String str, String str2) {
        synchronized (ReplyServiceHelper.class) {
            String str3 = "sendReply - " + str;
            if (!Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(str2) && replyActionLRUHashMap.get(str) != null) {
                try {
                    replyActionLRUHashMap.get(str).send(context, str2);
                    recordSuccessRepliedNotification(str, str2);
                    return true;
                } catch (Exception e) {
                    Log.w(TAG, "sendReply - Exception when sending a reply - ", e);
                    return false;
                }
            }
            Log.w(TAG, "sendReply - Cannot send reply");
            return false;
        }
    }

    public static synchronized void updateReplyActionMap(StatusBarNotification statusBarNotification) {
        ReplyAction createReplyActionForSbn;
        synchronized (ReplyServiceHelper.class) {
            String str = "updateReplyActionMap - " + statusBarNotification.getKey();
            if (isReplyAllowlisted(statusBarNotification) && (createReplyActionForSbn = replyActionFactory.createReplyActionForSbn(statusBarNotification)) != null) {
                Log.i(TAG, "updateReplyActionMap - Got valid ReplyAction, update Map");
                replyActionLRUHashMap.put(statusBarNotification.getKey(), createReplyActionForSbn);
            }
        }
    }
}
