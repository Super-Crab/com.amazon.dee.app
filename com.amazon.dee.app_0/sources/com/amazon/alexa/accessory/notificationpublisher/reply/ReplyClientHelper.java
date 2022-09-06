package com.amazon.alexa.accessory.notificationpublisher.reply;

import android.service.notification.StatusBarNotification;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationIdConverter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes.dex */
public final class ReplyClientHelper {
    private static final String TAG = "ReplyClientHelper";

    private ReplyClientHelper() {
    }

    private static boolean doesNotificationHaveReplyAction(String str) {
        try {
            return DependencyProvider.getNotificationServiceCommunicator().safeCallDoesNotificationSupportReply(str);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("doesNotificationHaveReplyAction - Exception caught - ", e, TAG);
            return false;
        }
    }

    private static boolean doesNotificationSupportReplyByNotificationId(String str) {
        GeneratedOutlineSupport1.outline165("doesNotificationSupportReplyByNotificationId - ", str, TAG);
        try {
            if (!isNotificationActive(str)) {
                return false;
            }
            return doesNotificationHaveReplyAction(str);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("doesNotificationSupportReplyByNotificationId - Exception - ", e, TAG);
            return false;
        }
    }

    public static boolean doesNotificationSupportReplyByUuid(String str) {
        String str2 = TAG;
        Log.d(str2, "doesNotificationSupportReplyByUuid - " + str);
        return doesNotificationSupportReplyByNotificationId(NotificationIdConverter.toNotificationId(str));
    }

    private static boolean isNotificationActive(String str) {
        GeneratedOutlineSupport1.outline165("isNotificationActive - SBN Key: ", str, TAG);
        boolean z = true;
        try {
            List<StatusBarNotification> safeCallGetActiveNotificationsWithKeys = DependencyProvider.getNotificationServiceCommunicator().safeCallGetActiveNotificationsWithKeys(new String[]{str});
            if (safeCallGetActiveNotificationsWithKeys == null || safeCallGetActiveNotificationsWithKeys.size() <= 0) {
                z = false;
            }
            Log.d(TAG, "isNotificationActive - Going to return " + z);
            return z;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("isNotificationActive - Exception caught - ", e, TAG);
            return false;
        }
    }

    public static boolean isReplyNotification(String str, String str2, String str3) {
        GeneratedOutlineSupport1.outline165("isReplyNotification - ", str, TAG);
        try {
            return DependencyProvider.getNotificationServiceCommunicator().safeCallIsReplyNotification(str, str2, str3);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("isReplyNotification - Exception caught - ", e, TAG);
            return false;
        }
    }

    public static boolean sendReply(String str, String str2) {
        GeneratedOutlineSupport1.outline165("sendReply - ", str, TAG);
        try {
            return DependencyProvider.getNotificationServiceCommunicator().safeCallSendReply(str, str2);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("sendReply - Exception caught - ", e, TAG);
            return false;
        }
    }

    public static boolean sendReplyWithUuid(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, "sendReplyWithUuid - " + str);
        return sendReply(NotificationIdConverter.toNotificationId(str), str2);
    }
}
