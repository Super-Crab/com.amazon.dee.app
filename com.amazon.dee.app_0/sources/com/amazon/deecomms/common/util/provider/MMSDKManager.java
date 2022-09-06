package com.amazon.deecomms.common.util.provider;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public final class MMSDKManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MMSDKManager.class);

    private MMSDKManager() {
    }

    public static boolean isCommsOnLenovoSmartTabletEnabled(@NonNull Context context) {
        Uri.Builder builder = new Uri.Builder();
        builder.authority("amazon.speech.sim.comms").scheme("content").path("comms_enabled");
        Cursor query = context.getContentResolver().query(builder.build(), new String[]{"comms_enabled"}, null, new String[]{""}, null);
        boolean z = false;
        if (query != null) {
            try {
                if (query.getCount() >= 1) {
                    query.moveToFirst();
                    int i = query.getInt(query.getColumnIndex("comms_enabled"));
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Comms on Lenovo Smart Tablet enabled : ");
                    sb.append(i);
                    commsLogger.i(sb.toString());
                    if (i == 1) {
                        z = true;
                    }
                    query.close();
                    return z;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        LOG.i("MMSDK cursor is null");
        if (query != null) {
            query.close();
        }
        return false;
    }
}
