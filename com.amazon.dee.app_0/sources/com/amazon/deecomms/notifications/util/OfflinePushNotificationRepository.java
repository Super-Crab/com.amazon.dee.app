package com.amazon.deecomms.notifications.util;

import android.content.ContentValues;
import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.database.CommsDatabaseHelper;
import com.amazon.deecomms.messaging.database.MessagingTableConstants;
import java.util.ArrayList;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes12.dex */
public class OfflinePushNotificationRepository {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OfflinePushNotificationRepository.class);
    private static final Object lockObject = new Object();

    public void addNotification(@NonNull Context context, @NonNull String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("payload", str);
        SQLiteDatabase writableDatabase = CommsDatabaseHelper.getInstance(context).getWritableDatabase();
        synchronized (lockObject) {
            if (writableDatabase.insert(MessagingTableConstants.OfflinePushNotificationCache.TABLE_NAME, null, contentValues) < 0) {
                LOG.e("Saving notification failed.");
            }
        }
    }

    public Iterable<String> getAndDeleteNotifications(@NonNull Context context) {
        SQLiteDatabase writableDatabase = CommsDatabaseHelper.getInstance(context).getWritableDatabase();
        ArrayList arrayList = new ArrayList();
        synchronized (lockObject) {
            Cursor query = writableDatabase.query(MessagingTableConstants.OfflinePushNotificationCache.TABLE_NAME, new String[]{"payload"}, null, null, null, null, null, null);
            if (query == null) {
                LOG.e("Reading notifications failed.");
                if (query != null) {
                    query.close();
                }
                return arrayList;
            }
            int columnIndex = query.getColumnIndex("payload");
            while (query.moveToNext()) {
                arrayList.add(query.getString(columnIndex));
            }
            query.close();
            writableDatabase.delete(MessagingTableConstants.OfflinePushNotificationCache.TABLE_NAME, null, null);
            return arrayList;
        }
    }
}
