package com.amazon.identity.auth.device.datastore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.AbstractDataObject;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes12.dex */
public abstract class AbstractDataSource<K extends AbstractDataObject> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DELETE_SUCCEEDED = 1;
    public static final int INSERT_FAILED = -1;
    private static final String LOG_TAG = "com.amazon.identity.auth.device.datastore.AbstractDataSource";
    private static final int UPDATE_SUCCEEDED = 1;
    protected SQLiteDatabase database;

    public AbstractDataSource(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            this.database = sQLiteDatabase;
            return;
        }
        throw new IllegalArgumentException("database can't be null!");
    }

    private static String getWhereClause(String[] strArr, String[] strArr2) throws IllegalArgumentException {
        if (strArr == null && strArr2 == null) {
            return null;
        }
        if (strArr != null && strArr2 != null) {
            if (strArr.length == strArr2.length) {
                int i = 0;
                String str = "";
                while (i < strArr.length) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
                    outline107.append(strArr[i]);
                    outline107.append(strArr2[i] == null ? " IS NULL" : GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(" = '"), strArr2[i], "'"));
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(outline107.toString());
                    outline1072.append(i != strArr.length + (-1) ? " AND " : "");
                    str = outline1072.toString();
                    i++;
                }
                return str;
            }
            throw new IllegalArgumentException("selectionFields and selectionValues differ in length!");
        }
        throw new IllegalArgumentException("Both arguments have to be either null or not null!");
    }

    public abstract K cursorToObject(Cursor cursor);

    public int deleteAllRows() {
        return deleteRows(null, null);
    }

    public boolean deleteRow(long j) {
        return this.database.delete(getTableName(), GeneratedOutlineSupport1.outline56("rowid = ", j), null) == 1;
    }

    public int deleteRows(String[] strArr, String[] strArr2) {
        try {
            return this.database.delete(getTableName(), getWhereClause(strArr, strArr2), null);
        } catch (IllegalArgumentException e) {
            String logTag = getLogTag();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
            outline107.append(e.getMessage());
            MAPLog.e(logTag, outline107.toString(), e);
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int deleteRowsBySingleColumn(String str, String str2) {
        return deleteRows(new String[]{str}, new String[]{str2});
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0064, code lost:
        if (r1 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0067, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<K> findAllRows(java.lang.String[] r11, java.lang.String[] r12) {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.String r5 = getWhereClause(r11, r12)     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            android.database.sqlite.SQLiteDatabase r2 = r10.database     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            java.lang.String r3 = r10.getTableName()     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            java.lang.String[] r4 = r10.getAllColumns()     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            if (r1 == 0) goto L3c
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
        L21:
            boolean r11 = r1.isAfterLast()     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            if (r11 != 0) goto L3c
            com.amazon.identity.auth.device.dataobject.AbstractDataObject r11 = r10.cursorToObject(r1)     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            if (r11 == 0) goto L34
            r0.add(r11)     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            r1.moveToNext()     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            goto L21
        L34:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            java.lang.String r12 = "cursor contains invalid object!"
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
            throw r11     // Catch: java.lang.Throwable -> L42 java.lang.IllegalArgumentException -> L44
        L3c:
            if (r1 == 0) goto L67
        L3e:
            r1.close()
            goto L67
        L42:
            r11 = move-exception
            goto L68
        L44:
            r11 = move-exception
            java.lang.String r12 = r10.getLogTag()     // Catch: java.lang.Throwable -> L42
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L42
            r2.<init>()     // Catch: java.lang.Throwable -> L42
            java.lang.String r3 = ""
            r2.append(r3)     // Catch: java.lang.Throwable -> L42
            java.lang.String r3 = r11.getMessage()     // Catch: java.lang.Throwable -> L42
            r2.append(r3)     // Catch: java.lang.Throwable -> L42
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L42
            com.amazon.identity.auth.map.device.utils.MAPLog.e(r12, r2, r11)     // Catch: java.lang.Throwable -> L42
            r0.clear()     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L67
            goto L3e
        L67:
            return r0
        L68:
            if (r1 == 0) goto L6d
            r1.close()
        L6d:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.datastore.AbstractDataSource.findAllRows(java.lang.String[], java.lang.String[]):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<K> findAllRowsBySingleColumn(String str, String str2) {
        return findAllRows(new String[]{str}, new String[]{str2});
    }

    public K findByRowId(long j) {
        return findOneRow(new String[]{"rowid"}, new String[]{GeneratedOutlineSupport1.outline56("", j)});
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004a, code lost:
        if (r10 == null) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (r10 != null) goto L6;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public K findOneRow(java.lang.String[] r10, java.lang.String[] r11) {
        /*
            r9 = this;
            r0 = 0
            java.lang.String r4 = getWhereClause(r10, r11)     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            android.database.sqlite.SQLiteDatabase r1 = r9.database     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            java.lang.String r2 = r9.getTableName()     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            java.lang.String[] r3 = r9.getAllColumns()     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            if (r10 == 0) goto L23
            r10.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L21 java.lang.Throwable -> L4e
            com.amazon.identity.auth.device.dataobject.AbstractDataObject r0 = r9.cursorToObject(r10)     // Catch: java.lang.IllegalArgumentException -> L21 java.lang.Throwable -> L4e
            goto L23
        L21:
            r11 = move-exception
            goto L2e
        L23:
            if (r10 == 0) goto L4d
        L25:
            r10.close()
            goto L4d
        L29:
            r11 = move-exception
            r10 = r0
            goto L4f
        L2c:
            r11 = move-exception
            r10 = r0
        L2e:
            java.lang.String r1 = r9.getLogTag()     // Catch: java.lang.Throwable -> L4e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e
            r2.<init>()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r3 = ""
            r2.append(r3)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r3 = r11.getMessage()     // Catch: java.lang.Throwable -> L4e
            r2.append(r3)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L4e
            com.amazon.identity.auth.map.device.utils.MAPLog.e(r1, r2, r11)     // Catch: java.lang.Throwable -> L4e
            if (r10 == 0) goto L4d
            goto L25
        L4d:
            return r0
        L4e:
            r11 = move-exception
        L4f:
            if (r10 == 0) goto L54
            r10.close()
        L54:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.datastore.AbstractDataSource.findOneRow(java.lang.String[], java.lang.String[]):com.amazon.identity.auth.device.dataobject.AbstractDataObject");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public K findOneRowBySingleColumn(String str, String str2) {
        return findOneRow(new String[]{str}, new String[]{str2});
    }

    public abstract String[] getAllColumns();

    public int getColumnIndex(Cursor cursor, int i) throws IllegalArgumentException {
        if (i >= 0 && i < getAllColumns().length) {
            return cursor.getColumnIndexOrThrow(getAllColumns()[i]);
        }
        throw new IllegalArgumentException("colIndex is out of bound!");
    }

    public abstract String getLogTag();

    public abstract String getTableName();

    public long insertRow(K k) {
        if (k == null) {
            return -1L;
        }
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Insert Row table=");
        outline107.append(getTableName());
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("vals=");
        outline1072.append(k.getValuesForInsert());
        MAPLog.pii(str, sb, outline1072.toString());
        long insert = this.database.insert(getTableName(), null, k.getValuesForInsert());
        k.setRowId(insert);
        return insert;
    }

    public boolean updateRow(long j, ContentValues contentValues) {
        return contentValues != null && this.database.update(getTableName(), contentValues, GeneratedOutlineSupport1.outline56("rowid = ", j), null) == 1;
    }

    public List<K> findAllRows() {
        return findAllRows(null, null);
    }
}
