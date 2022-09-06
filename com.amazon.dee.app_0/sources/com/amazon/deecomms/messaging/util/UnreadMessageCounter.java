package com.amazon.deecomms.messaging.util;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
/* loaded from: classes12.dex */
public class UnreadMessageCounter {
    private Context mContext;

    public UnreadMessageCounter(Context context) {
        this.mContext = context;
    }

    public void countUnreadMessagesAndNotify() {
        Cursor query = this.mContext.getContentResolver().query(MessagingProviderContract.Conversations.getCountUnreadMessagesUri(this.mContext), null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    final int i = query.getInt(query.getColumnIndex(MessagingProviderContract.Conversations.UNREAD_COUNT));
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.deecomms.messaging.util.UnreadMessageCounter.1
                        @Override // java.lang.Runnable
                        public void run() {
                            CommsDaggerWrapper.getComponent().getApplicationManager().setIndicatorIconVisible(i > 0);
                        }
                    });
                }
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

    public boolean isUnreadMessagesAvailable() {
        Cursor query = this.mContext.getContentResolver().query(MessagingProviderContract.Conversations.getCountUnreadMessagesUri(this.mContext), null, null, null, null);
        boolean z = false;
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    if (query.getInt(query.getColumnIndex(MessagingProviderContract.Conversations.UNREAD_COUNT)) > 0) {
                        z = true;
                    }
                    query.close();
                    return z;
                }
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
        return false;
    }
}
