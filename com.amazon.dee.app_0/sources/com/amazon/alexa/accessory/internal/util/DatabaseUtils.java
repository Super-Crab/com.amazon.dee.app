package com.amazon.alexa.accessory.internal.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class DatabaseUtils {
    private DatabaseUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean containsTable(SQLiteDatabase sQLiteDatabase, String str) {
        Preconditions.notNull(str, "name");
        boolean z = true;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{str});
        try {
            if (rawQuery.getCount() != 1) {
                z = false;
            }
            rawQuery.close();
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (rawQuery != null) {
                    try {
                        rawQuery.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static String[] prependColumnsWithTable(String str, String[] strArr) {
        Preconditions.notNull(strArr, "columns");
        Preconditions.notNull(str, "table");
        Preconditions.precondition(!str.isEmpty(), "Table name is empty!");
        String[] strArr2 = new String[strArr.length];
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ".");
            outline113.append(strArr[i]);
            strArr2[i] = outline113.toString();
        }
        return strArr2;
    }
}
