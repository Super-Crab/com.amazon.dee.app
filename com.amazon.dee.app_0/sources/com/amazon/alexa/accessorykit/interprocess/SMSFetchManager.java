package com.amazon.alexa.accessorykit.interprocess;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.imap.IMAPStore;
/* loaded from: classes6.dex */
public class SMSFetchManager {
    private static final int UNREAD_MESSAGE = 0;
    private Context mContext;
    private static final String[] SEARCH_SMS_CONVERSATION_PROJECTION = {"_id", "thread_id", IMAPStore.ID_DATE};
    private static final String[] SEARCH_MMS_CONVERSATION_PROJECTION = {"_id", "thread_id", IMAPStore.ID_DATE};
    private static final String[] MMS_ADDRESS_PROJECTION = {"address", "type"};
    private static final String[] CONVERSATION_RECIPIENT_PROJECTION = {"recipient_ids"};

    public SMSFetchManager(Context context) {
        Preconditions.notNull(context, "context");
        this.mContext = context;
    }

    public Cursor getConversationRecipient(int i) {
        return this.mContext.getContentResolver().query(Uri.parse("content://mms-sms/conversations?simple=true"), CONVERSATION_RECIPIENT_PROJECTION, GeneratedOutlineSupport1.outline49("_id = ", i), null, null);
    }

    public Cursor getMMSAddresses(int i) {
        return this.mContext.getContentResolver().query(Uri.parse("content://mms/" + i + "/addr"), MMS_ADDRESS_PROJECTION, null, null, "_id");
    }

    public Cursor getUnreadMMS() {
        return this.mContext.getContentResolver().query(Telephony.Mms.CONTENT_URI, SEARCH_MMS_CONVERSATION_PROJECTION, "msg_box = 1 and read = 0", null, "_id");
    }

    public Cursor getUnreadSMS() {
        return this.mContext.getContentResolver().query(Telephony.Sms.Inbox.CONTENT_URI, SEARCH_SMS_CONVERSATION_PROJECTION, "read = 0", null, "_id");
    }
}
