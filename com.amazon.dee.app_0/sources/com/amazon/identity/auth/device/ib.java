package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ib {
    private static final String TAG = "com.amazon.identity.auth.device.ib";

    private ib() {
    }

    public static String a(ec ecVar, final Uri uri, final String str) throws RemoteMAPException {
        return (String) ecVar.a(uri, new dj<String>() { // from class: com.amazon.identity.auth.device.ib.1
            final /* synthetic */ String rb = null;
            final /* synthetic */ String[] lj = null;
            final /* synthetic */ String rc = null;

            @Override // com.amazon.identity.auth.device.dj
            /* renamed from: c */
            public String b(ContentProviderClient contentProviderClient) throws Exception {
                Cursor query = contentProviderClient.query(uri, new String[]{str}, this.rb, this.lj, this.rc);
                if (query == null) {
                    return null;
                }
                try {
                    return ib.g(query, str);
                } finally {
                    query.close();
                }
            }
        });
    }

    public static String ar(String str, String str2) {
        return String.format("%s.%s", str, str2);
    }

    public static long b(Cursor cursor, String str) {
        return cursor.getLong(cursor.getColumnIndex(str));
    }

    public static Date c(Cursor cursor, String str) {
        return new Date(b(cursor, str));
    }

    public static boolean d(Cursor cursor, String str) {
        return b(cursor, str) == 1;
    }

    public static String e(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndex(str));
    }

    public static boolean f(Cursor cursor, String str) {
        return cursor.getColumnIndex(str) >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String g(Cursor cursor, String str) {
        if (!cursor.moveToFirst()) {
            return null;
        }
        return e(cursor, str);
    }

    public static String v(String str, String str2, String str3) {
        return String.format("%s.%s as %s", str, str2, str3);
    }

    public static String a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) {
        return b(sQLiteDatabase, str, str2, str3, strArr);
    }

    public static Cursor a(String[] strArr, Map<String, String> map) {
        if (strArr == null) {
            return null;
        }
        return a(strArr, Collections.singletonList(map));
    }

    public static String b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) {
        Cursor query = sQLiteDatabase.query(str, new String[]{str2}, str3, strArr, null, null, null);
        if (query == null) {
            return null;
        }
        try {
            return g(query, str2);
        } finally {
            query.close();
        }
    }

    public static Cursor a(String[] strArr, Collection<Map<String, String>> collection) {
        MatrixCursor matrixCursor = null;
        if (strArr != null && collection != null) {
            if (strArr.length >= 10000) {
                io.e(TAG, String.format(Locale.ENGLISH, "The number of columns %d is too large to be handled. Issue tracked in SSO-160.", Integer.valueOf(strArr.length)));
                return null;
            }
            matrixCursor = new MatrixCursor(strArr);
            for (Map<String, String> map : collection) {
                String[] strArr2 = new String[strArr.length];
                for (int i = 0; i < strArr.length; i++) {
                    String str = strArr[i];
                    if (map.containsKey(str)) {
                        strArr2[i] = map.get(str);
                    } else {
                        String str2 = TAG;
                        String.format("Column %s not supported", str);
                        io.dm(str2);
                    }
                }
                matrixCursor.addRow(strArr2);
            }
        }
        return matrixCursor;
    }

    public static void b(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        cursor.close();
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            if (sQLiteDatabase.update(str, contentValues, str2, strArr) > 0) {
                return true;
            }
            return sQLiteDatabase.insertOrThrow(str, null, contentValues) >= 0;
        } catch (SQLException unused) {
            io.w(TAG, "Error inserting into database in ifCannotUpdateThenInsert");
            io.dn(TAG);
            return false;
        }
    }
}
