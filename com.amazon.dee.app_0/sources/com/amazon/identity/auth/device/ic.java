package com.amazon.identity.auth.device;

import android.database.Cursor;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ic {
    public static String e(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1) {
            return null;
        }
        return cursor.getString(columnIndex);
    }
}
