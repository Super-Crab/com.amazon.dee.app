package com.amazon.deecomms.messaging.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.amazon.deecomms.messaging.sync.MessageReadStatusMarkerService;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public final class MessageUtils {
    private static final String HEADER_CONTENT_TYPE = "content-type";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG + MessageUtils.class);

    /* loaded from: classes12.dex */
    public static final class MessageRecipientExtraInfo {
        public boolean canIDropInOnThem;
        public boolean isHomeGroup;
    }

    private MessageUtils() {
    }

    @NonNull
    public static MessageRecipientExtraInfo checkIfRecipientIsHomegroupAndDropInPermission(String str, String str2, String str3) {
        boolean z;
        MessageRecipientExtraInfo messageRecipientExtraInfo = new MessageRecipientExtraInfo();
        List<String> splitConcatenatedString = splitConcatenatedString(str);
        List<String> splitConcatenatedString2 = splitConcatenatedString(str2);
        List<String> splitConcatenatedString3 = splitConcatenatedString(str3);
        if (splitConcatenatedString.size() > 2) {
            LOG.w(" Database returned more than 2 commsIDs in a conversation whengroup concat is done");
            return messageRecipientExtraInfo;
        } else if (splitConcatenatedString.size() == splitConcatenatedString2.size() && splitConcatenatedString.size() == splitConcatenatedString3.size()) {
            boolean z2 = false;
            int indexOf = splitConcatenatedString.indexOf(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("checkIfRecipientIsHomegroupAndDropInPermission", false));
            if (indexOf >= 0) {
                z = Integer.parseInt(splitConcatenatedString3.get(0)) == 1;
                splitConcatenatedString.remove(indexOf);
                splitConcatenatedString2.remove(indexOf);
                splitConcatenatedString3.remove(indexOf);
            } else {
                z = false;
            }
            int size = splitConcatenatedString.size();
            if (size == 0) {
                LOG.i("Self conversation");
                messageRecipientExtraInfo.canIDropInOnThem = z;
            } else if (size == 1) {
                messageRecipientExtraInfo.isHomeGroup = Integer.parseInt(splitConcatenatedString2.get(0)) == 1;
                if (Integer.parseInt(splitConcatenatedString3.get(0)) == 1) {
                    z2 = true;
                }
                messageRecipientExtraInfo.canIDropInOnThem = z2;
            } else if (size != 2) {
                LOG.e("More than two commsIDs unexpectedly");
            } else {
                int i = 0;
                while (true) {
                    if (i >= splitConcatenatedString.size()) {
                        break;
                    } else if (!Utils.isHomeGroupId(splitConcatenatedString.get(i))) {
                        messageRecipientExtraInfo.isHomeGroup = Integer.parseInt(splitConcatenatedString2.get(i)) == 1;
                        if (Integer.parseInt(splitConcatenatedString3.get(i)) == 1) {
                            z2 = true;
                        }
                        messageRecipientExtraInfo.canIDropInOnThem = z2;
                    } else {
                        i++;
                    }
                }
            }
            return messageRecipientExtraInfo;
        } else {
            LOG.e(" Database returned unequal commids and associated details when group concat is done");
            return messageRecipientExtraInfo;
        }
    }

    public static String getContentType(IHttpClient.Response response) {
        List<String> list = response.getHeaders().get(HEADER_CONTENT_TYPE);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @NonNull
    public static String getSelectQueryForFilteredAndDeprovisionedUsers(@NonNull List<Long> list) {
        String sb;
        if (list.isEmpty()) {
            sb = "last_modified_timestamp IS NOT NULL";
        } else {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("last_modified_timestamp IS NOT NULL AND conversations._id NOT IN ('");
            outline1.append(TextUtils.join("', '", list));
            outline1.append("')");
            sb = outline1.toString();
        }
        return GeneratedOutlineSupport.outline0(sb, " AND (conversations.recipient_id IS NOT NULL OR LENGTH(conversations.recipient_id) > 0) AND conversations.recipient_id NOT IN (SELECT UngettableCommsIds.commsId FROM UngettableCommsIds)");
    }

    public static void markMessageAsRead(Context context, String str, String str2) {
        LOG.d("Marking message as read");
        Intent intent = new Intent(context, MessageReadStatusMarkerService.class);
        intent.putExtra(Constants.BUNDLE_KEY_CONVERSATION_ID, str);
        intent.putExtra(Constants.BUNDLE_KEY_RECIPIENT_ID, str2);
        MessageReadStatusMarkerService.enqueueWork(context, intent);
    }

    public static void markMessageReadForLocallyOnlyReadMessages(Context context) {
        LOG.i("Marking locally only read messages as read in server");
        Cursor query = context.getContentResolver().query(MessagingProviderContract.Conversations.getConversationContentUri(context), null, "read_message_locally_only = ?", new String[]{Integer.toString(1)}, null);
        while (query != null) {
            try {
                if (!query.moveToNext()) {
                    break;
                }
                markMessageAsRead(context, query.getString(query.getColumnIndex("conversation_id")), query.getString(query.getColumnIndex("recipient_id")));
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
    }

    private static List<String> splitConcatenatedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        return new ArrayList(Arrays.asList(str.split(Constants.GROUP_CONCAT_SEPARATOR, -1)));
    }
}
