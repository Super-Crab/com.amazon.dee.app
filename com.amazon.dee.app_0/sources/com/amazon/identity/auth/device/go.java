package com.amazon.identity.auth.device;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class go {
    private static final String TAG = "com.amazon.identity.auth.device.go";
    private static go oE;
    private final Context mContext;
    private Map<String, Map<String, String>> oC;
    private final SQLiteOpenHelper oF;
    private Map<String, fy> oG;
    private final gp oH;
    private cr oI;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a extends SQLiteOpenHelper {
        private final gp oJ;

        public a(Context context, String str, gp gpVar) {
            super(context, GeneratedOutlineSupport1.outline72(str, ".db"), (SQLiteDatabase.CursorFactory) null, 2);
            io.i(go.TAG, "Constructing LocalDataStorageDBHelper");
            File databasePath = context.getDatabasePath(str + ".db");
            String str2 = go.TAG;
            io.i(str2, "Database " + str + ".db exists: " + databasePath.exists());
            this.oJ = gpVar;
        }

        private static dz fD() {
            io.i(go.TAG, "Creating EncryptionSecret table in LocalDataStorageV2 database");
            return new dz("encryption_data").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("encryption_data_key", "TEXT NOT NULL").p("encryption_data_value", "TEXT NOT NULL").bv(String.format("UNIQUE(%s)", "encryption_data_key"));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            io.i(go.TAG, "Creating LocalDataStorageV2 Database");
            io.i(go.TAG, "Creating Accounts table in LocalDataStorageV2 database");
            sQLiteDatabase.execSQL(new dz("accounts").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("directed_id", "TEXT UNIQUE NOT NULL").p("display_name", "TEXT NOT NULL").toString());
            io.i(go.TAG, "Creating AccountData table in LocalDataStorageV2 database");
            sQLiteDatabase.execSQL(new dz("account_data").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("account_data_directed_id", "TEXT NOT NULL").p("account_data_key", "TEXT NOT NULL").p("account_data_value", "BLOB").bv(String.format("UNIQUE(%s,%s)", "account_data_directed_id", "account_data_key")).toString());
            io.i(go.TAG, "Creating DeviceData table in LocalDataStorageV2 database");
            sQLiteDatabase.execSQL(new dz("device_data").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("device_data_namespace", "TEXT NOT NULL").p("device_data_key", "TEXT NOT NULL").p("device_data_value", "BLOB").bv(String.format("UNIQUE(%s,%s)", "device_data_namespace", "device_data_key")).toString());
            sQLiteDatabase.execSQL(fD().toString());
            String cp = cr.cp();
            go.f(sQLiteDatabase, cp);
            this.oJ.U("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key", cp);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            if (!sQLiteDatabase.isReadOnly()) {
                sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
            }
            String str = go.TAG;
            io.i(str, "MAP database version: " + sQLiteDatabase.getVersion());
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            io.i(go.TAG, String.format(Locale.ENGLISH, "MAP database upgrades from version %d to %d", Integer.valueOf(i), Integer.valueOf(i2)));
            if (i == 1) {
                String str = go.TAG;
                io.i(str, "SharedPreference to database migrating on " + Build.VERSION.SDK_INT);
                mq.incrementCounterAndRecord("MAPCentralDBSharePrefMigration" + Build.VERSION.SDK_INT, new String[0]);
                sQLiteDatabase.execSQL(fD().dQ());
                if (TextUtils.isEmpty(go.g(sQLiteDatabase))) {
                    io.i(go.TAG, "Secret is not available on database, migrating..");
                    go.f(sQLiteDatabase, this.oJ.cs("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key"));
                    io.i(go.TAG, "Successfully migrate the shared preference.");
                    return;
                }
                io.dm(go.TAG);
            }
        }
    }

    private go(Context context) {
        this(context, "map_data_storage_v2");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized go X(Context context) {
        go goVar;
        synchronized (go.class) {
            if (oE == null) {
                oE = new go(context.getApplicationContext());
            }
            goVar = oE;
        }
        return goVar;
    }

    private boolean b(SQLiteDatabase sQLiteDatabase, String str) {
        if (str != null && c(sQLiteDatabase, str)) {
            Map<String, fy> map = this.oG;
            if (map == null) {
                return true;
            }
            map.remove(str);
            return true;
        }
        return false;
    }

    private fy cq(String str) {
        Map<String, fy> map = this.oG;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    private byte[] cr(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            cr i = i(false);
            if (i != null && bytes != null) {
                byte[] d = i.d(bytes);
                if (d == null) {
                    io.e(TAG, "The encrypt result is null. This should not happen!");
                    mq.b("EncryptionFailure", "encryptCBCModeReturnNull");
                }
                return d;
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, fy> d(SQLiteDatabase sQLiteDatabase) {
        if (this.oG == null) {
            this.oG = e(sQLiteDatabase);
        }
        return this.oG;
    }

    private Map<String, String> e(SQLiteDatabase sQLiteDatabase, String str) {
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("account_data", new String[]{"account_data_directed_id", "account_data_key", "account_data_value"}, String.format("%s = ?", "account_data_directed_id"), new String[]{str}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndexOrThrow = cursor.getColumnIndexOrThrow("account_data_key");
                int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("account_data_value");
                do {
                    String string = cursor.getString(columnIndexOrThrow);
                    if (string != null) {
                        hashMap.put(string, g(cursor.getBlob(columnIndexOrThrow2)));
                    }
                } while (cursor.moveToNext());
                return hashMap;
            }
            return hashMap;
        } finally {
            ib.b(cursor);
        }
    }

    private Map<String, fy> fA() {
        if (this.oG == null) {
            this.oG = fC();
        }
        return this.oG;
    }

    private Map<String, Map<String, String>> fB() {
        if (this.oC == null) {
            this.oC = fy();
        }
        return this.oC;
    }

    private Map<String, fy> fC() {
        try {
            return e(this.oF.getReadableDatabase());
        } finally {
            this.oF.close();
        }
    }

    private Map<String, Map<String, String>> fy() {
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            cursor = this.oF.getReadableDatabase().query("device_data", new String[]{"device_data_namespace", "device_data_key", "device_data_value"}, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndexOrThrow = cursor.getColumnIndexOrThrow("device_data_namespace");
                int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("device_data_key");
                int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("device_data_value");
                do {
                    String string = cursor.getString(columnIndexOrThrow);
                    Map map = (Map) hashMap.get(string);
                    if (map == null) {
                        map = new HashMap();
                        hashMap.put(string, map);
                    }
                    String string2 = cursor.getString(columnIndexOrThrow2);
                    if (string2 != null) {
                        map.put(string2, g(cursor.getBlob(columnIndexOrThrow3)));
                    }
                } while (cursor.moveToNext());
            }
            return hashMap;
        } finally {
            ib.b(cursor);
            this.oF.close();
        }
    }

    private byte[] h(byte[] bArr) {
        cr i = i(true);
        byte[] bArr2 = null;
        if (i == null || bArr == null) {
            return null;
        }
        try {
            bArr2 = i.e(bArr);
        } catch (BadPaddingException unused) {
            io.e(TAG, "The decrypt throw BadPaddingException. This should not happen in LocalDataStorageV2!");
        }
        if (bArr2 != null) {
            return bArr2;
        }
        io.e(TAG, "The decrypt result is null. This should not happen!");
        mq.b("DecryptionFailure", "decryptCBCModeReturnNull");
        k(this.mContext, "map_data_storage_v2");
        throw new IllegalStateException("decryptCBCMode returns null. Something wrong with the decryption");
    }

    private synchronized cr i(boolean z) {
        if (this.oI == null) {
            String cs = this.oH.cs("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key");
            if (cs == null) {
                if (!z) {
                    io.e(TAG, "Cannot get the encryption key from SharedPreferences before encrypt. Gonna create a new key. This should not happen!");
                    mq.b("EncryptionFailure", "EncryptionKeyNotFound");
                    if (this.oH.U("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key", cr.cp())) {
                        cs = this.oH.cs("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key");
                    } else {
                        io.e(TAG, "Try to re-generate the encryption key and save it into shared preferences failed!");
                        mq.b("EncryptionFailure", "TryToRegenerateEncryptionKeyFailure");
                        return null;
                    }
                } else {
                    io.e(TAG, "Cannot get the encryption key from SharedPreferences before decrypt. This should not happen!");
                    mq.b("DecryptionFailure", "EncryptionKeyNotFound");
                    k(this.mContext, "map_data_storage_v2");
                    throw new IllegalStateException("The encryption key is null!");
                }
            }
            this.oI = new cr(Base64.decode(cs, 0));
        }
        return this.oI;
    }

    public static void k(Context context, String str) {
        if (context == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".db");
        io.i(TAG, "cleanDb ".concat(context.deleteDatabase(sb.toString()) ? "successful" : "failed"));
    }

    private void r(String str, String str2, String str3) {
        Map<String, Map<String, String>> map = this.oC;
        if (map == null || str2 == null || str3 == null) {
            return;
        }
        Map<String, String> map2 = map.get(str);
        if (map2 == null) {
            map2 = new HashMap<>();
            this.oC.put(str, map2);
        }
        map2.put(str2, str3);
    }

    public synchronized String C(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        Map<String, String> map = fB().get(str);
        if (map == null) {
            return null;
        }
        return map.get(str2);
    }

    public synchronized String S(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        fy fyVar = fA().get(str);
        if (fyVar == null) {
            return null;
        }
        return fyVar.ns.get(str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        r0.setTransactionSuccessful();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean T(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            android.database.sqlite.SQLiteOpenHelper r1 = r7.oF     // Catch: java.lang.Throwable -> L54
            android.database.sqlite.SQLiteDatabase r0 = com.amazon.identity.auth.device.gn.a(r1)     // Catch: java.lang.Throwable -> L54
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L54
            r1 = 1
            r2 = 0
            if (r8 == 0) goto L4a
            if (r9 != 0) goto L12
            goto L4a
        L12:
            boolean r3 = r7.a(r0, r8)     // Catch: java.lang.Throwable -> L54
            if (r3 != 0) goto L19
            goto L4a
        L19:
            java.lang.String r3 = "%s = ? and %s = ?"
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L54
            java.lang.String r6 = "account_data_directed_id"
            r5[r2] = r6     // Catch: java.lang.Throwable -> L54
            java.lang.String r6 = "account_data_key"
            r5[r1] = r6     // Catch: java.lang.Throwable -> L54
            java.lang.String r3 = java.lang.String.format(r3, r5)     // Catch: java.lang.Throwable -> L54
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L54
            r4[r2] = r8     // Catch: java.lang.Throwable -> L54
            r4[r1] = r9     // Catch: java.lang.Throwable -> L54
            java.lang.String r5 = "account_data"
            int r3 = r0.delete(r5, r3, r4)     // Catch: java.lang.Throwable -> L54
            if (r3 <= 0) goto L3a
            r3 = r1
            goto L3b
        L3a:
            r3 = r2
        L3b:
            if (r3 != 0) goto L3e
            goto L4a
        L3e:
            com.amazon.identity.auth.device.fy r8 = r7.cq(r8)     // Catch: java.lang.Throwable -> L54
            if (r8 == 0) goto L49
            java.util.Map<java.lang.String, java.lang.String> r8 = r8.ns     // Catch: java.lang.Throwable -> L54
            r8.remove(r9)     // Catch: java.lang.Throwable -> L54
        L49:
            r2 = r1
        L4a:
            if (r2 == 0) goto L4f
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L54
        L4f:
            r7.f(r0)     // Catch: java.lang.Throwable -> L59
            monitor-exit(r7)
            return r2
        L54:
            r8 = move-exception
            r7.f(r0)     // Catch: java.lang.Throwable -> L59
            throw r8     // Catch: java.lang.Throwable -> L59
        L59:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.go.T(java.lang.String, java.lang.String):boolean");
    }

    public synchronized Map<String, String> a(String str, List<String> list) {
        HashMap hashMap = new HashMap();
        if (str != null && list != null && !list.isEmpty()) {
            Map<String, String> map = fB().get(str);
            if (map == null) {
                return hashMap;
            }
            for (String str2 : list) {
                hashMap.put(str2, map.get(str2));
            }
            return hashMap;
        }
        return hashMap;
    }

    public synchronized boolean c(String str, String str2, Map<String, String> map) {
        boolean a2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = gn.a(this.oF);
            sQLiteDatabase.beginTransaction();
            a2 = a(sQLiteDatabase, str, str2, map);
            if (a2) {
                sQLiteDatabase.setTransactionSuccessful();
            }
            f(sQLiteDatabase);
        } catch (SQLiteConstraintException e) {
            io.e(TAG, "Cannot add account due to ", e);
            f(sQLiteDatabase);
            return false;
        }
        return a2;
    }

    public synchronized Set<String> cc(String str) {
        HashSet hashSet = new HashSet();
        if (str == null) {
            return hashSet;
        }
        fy fyVar = fA().get(str);
        if (fyVar == null) {
            return hashSet;
        }
        for (Map.Entry<String, String> entry : fyVar.ns.entrySet()) {
            if (entry.getKey().startsWith("actor/")) {
                hashSet.add(entry.getValue());
            }
        }
        return hashSet;
    }

    public Set<String> cf(String str) {
        fy fyVar;
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(str) && (fyVar = fA().get(str)) != null) {
            for (String str2 : fyVar.ns.keySet()) {
                if (str2.contains("token") || str2.contains("cookie")) {
                    hashSet.add(str2);
                }
            }
            return hashSet;
        }
        return hashSet;
    }

    public synchronized boolean cp(String str) {
        boolean b;
        SQLiteDatabase a2 = gn.a(this.oF);
        a2.beginTransaction();
        b = b(a2, str);
        if (b) {
            a2.setTransactionSuccessful();
        }
        f(a2);
        return b;
    }

    public synchronized Set<String> eT() {
        HashSet hashSet;
        Map<String, fy> fA = fA();
        hashSet = new HashSet();
        for (fy fyVar : fA.values()) {
            hashSet.add(fyVar.displayName);
        }
        return hashSet;
    }

    public synchronized boolean f(String str, Map<String, String> map) {
        boolean z;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = gn.a(this.oF);
            sQLiteDatabase.beginTransaction();
            z = true;
            Iterator<Map.Entry<String, String>> it2 = map.entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it2.next();
                if (!a(sQLiteDatabase, str, next.getKey(), next.getValue())) {
                    z = false;
                    break;
                }
            }
            if (z) {
                sQLiteDatabase.setTransactionSuccessful();
            }
            f(sQLiteDatabase);
        } catch (SQLiteConstraintException e) {
            io.e(TAG, "Cannot set data due to: ", e);
            f(sQLiteDatabase);
            return false;
        }
        return z;
    }

    public synchronized boolean g(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            return false;
        }
        try {
            SQLiteDatabase a2 = gn.a(this.oF);
            a2.beginTransaction();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!c(a2, str, entry.getKey(), entry.getValue())) {
                    throw new SQLiteException("Failed to update db!");
                }
            }
            a2.setTransactionSuccessful();
            for (Map.Entry<String, String> entry2 : map.entrySet()) {
                r(str, entry2.getKey(), entry2.getValue());
            }
            f(a2);
            return true;
        } catch (SQLiteException e) {
            io.e(TAG, "Cannot set device data!", e);
            f(null);
            return false;
        }
    }

    public synchronized Set<String> getAccounts() {
        HashSet hashSet;
        Map<String, fy> fA = fA();
        hashSet = new HashSet();
        for (Map.Entry<String, fy> entry : fA.entrySet()) {
            hashSet.add(entry.getKey());
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public synchronized boolean p(String str, String str2, String str3) {
        boolean a2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = gn.a(this.oF);
            sQLiteDatabase.beginTransaction();
            a2 = a(sQLiteDatabase, str, str2, str3);
            if (a2) {
                sQLiteDatabase.setTransactionSuccessful();
            }
            f(sQLiteDatabase);
        } catch (SQLiteConstraintException e) {
            io.e(TAG, "Cannot set data due to: ", e);
            f(sQLiteDatabase);
            return false;
        }
        return a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020 A[Catch: all -> 0x0028, SQLiteConstraintException -> 0x002a, TRY_LEAVE, TryCatch #1 {, blocks: (B:15:0x0023, B:21:0x0031, B:4:0x0003, B:8:0x0011, B:11:0x0018, B:14:0x0020, B:20:0x002a), top: B:29:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean q(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteOpenHelper r2 = r3.oF     // Catch: java.lang.Throwable -> L28 android.database.sqlite.SQLiteConstraintException -> L2a
            android.database.sqlite.SQLiteDatabase r0 = com.amazon.identity.auth.device.gn.a(r2)     // Catch: java.lang.Throwable -> L28 android.database.sqlite.SQLiteConstraintException -> L2a
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L28 android.database.sqlite.SQLiteConstraintException -> L2a
            if (r4 == 0) goto L1d
            if (r5 != 0) goto L11
            goto L1d
        L11:
            boolean r2 = r3.c(r0, r4, r5, r6)     // Catch: java.lang.Throwable -> L28 android.database.sqlite.SQLiteConstraintException -> L2a
            if (r2 != 0) goto L18
            goto L1d
        L18:
            r3.r(r4, r5, r6)     // Catch: java.lang.Throwable -> L28 android.database.sqlite.SQLiteConstraintException -> L2a
            r4 = 1
            goto L1e
        L1d:
            r4 = r1
        L1e:
            if (r4 == 0) goto L23
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L28 android.database.sqlite.SQLiteConstraintException -> L2a
        L23:
            r3.f(r0)     // Catch: java.lang.Throwable -> L3a
            monitor-exit(r3)
            return r4
        L28:
            r4 = move-exception
            goto L36
        L2a:
            java.lang.String r4 = com.amazon.identity.auth.device.go.TAG     // Catch: java.lang.Throwable -> L28
            java.lang.String r5 = "Cannot set device data since it violated a uniqueness constraint"
            com.amazon.identity.auth.device.io.e(r4, r5)     // Catch: java.lang.Throwable -> L28
            r3.f(r0)     // Catch: java.lang.Throwable -> L3a
            monitor-exit(r3)
            return r1
        L36:
            r3.f(r0)     // Catch: java.lang.Throwable -> L3a
            throw r4     // Catch: java.lang.Throwable -> L3a
        L3a:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.go.q(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    go(Context context, String str) {
        io.i(TAG, "Constructing LocalDataStorageV2");
        this.mContext = context;
        this.oH = new gp(this.mContext, "com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encryption.namespace");
        this.oF = new a(context, str, this.oH);
        this.oI = null;
    }

    private fy b(SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("directed_id", str);
        contentValues.put("display_name", str2);
        if (!ib.a(sQLiteDatabase, "accounts", contentValues, String.format("%s = ?", "directed_id"), new String[]{str})) {
            io.e(TAG, "Failed to add account");
            return null;
        }
        Map<String, String> e = e(sQLiteDatabase, str);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!b(sQLiteDatabase, str, entry.getKey(), entry.getValue())) {
                    io.e(TAG, "Failed to save account because saving token was unsuccessful");
                    return null;
                }
                e.put(entry.getKey(), entry.getValue());
            }
        }
        return new fy(str, str2, e);
    }

    private void d(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.delete("account_data", String.format("%s = ?", "account_data_directed_id"), new String[]{str});
    }

    public synchronized boolean a(String str, String str2, Map<String, String> map, List<String> list) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = gn.a(this.oF);
            sQLiteDatabase.beginTransaction();
            for (String str3 : list) {
                if (!b(sQLiteDatabase, str3)) {
                    f(sQLiteDatabase);
                    return false;
                }
            }
            boolean a2 = a(sQLiteDatabase, str, str2, map);
            if (a2) {
                sQLiteDatabase.setTransactionSuccessful();
            }
            f(sQLiteDatabase);
            return a2;
        } catch (SQLiteConstraintException e) {
            io.e(TAG, "Cannot replace accounts due to ", e);
            f(sQLiteDatabase);
            return false;
        }
    }

    private boolean c(SQLiteDatabase sQLiteDatabase, String str) {
        boolean z = true;
        if (sQLiteDatabase.delete("accounts", String.format("%s = ?", "directed_id"), new String[]{str}) <= 0) {
            z = false;
        }
        d(sQLiteDatabase, str);
        return z;
    }

    private Map<String, fy> e(SQLiteDatabase sQLiteDatabase) {
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("accounts LEFT OUTER JOIN account_data ON (" + ib.ar("accounts", "directed_id") + " = " + ib.ar("account_data", "account_data_directed_id") + ")");
            HashMap hashMap2 = new HashMap();
            a(hashMap2, "accounts", "_id", "_id");
            a(hashMap2, "accounts", "directed_id", "directed_id");
            a(hashMap2, "accounts", "display_name", "display_name");
            a(hashMap2, "account_data", "account_data_key", "account_data_key");
            a(hashMap2, "account_data", "account_data_value", "account_data_value");
            sQLiteQueryBuilder.setProjectionMap(hashMap2);
            cursor = sQLiteDatabase.rawQuery(sQLiteQueryBuilder.buildQuery((String[]) hashMap2.keySet().toArray(new String[0]), null, null, null, null, null, null), null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndexOrThrow = cursor.getColumnIndexOrThrow("directed_id");
                int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("display_name");
                int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("account_data_key");
                int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("account_data_value");
                do {
                    String string = cursor.getString(columnIndexOrThrow);
                    fy fyVar = (fy) hashMap.get(string);
                    if (fyVar == null) {
                        fy fyVar2 = new fy(string, cursor.getString(columnIndexOrThrow2));
                        hashMap.put(string, fyVar2);
                        fyVar = fyVar2;
                    }
                    String string2 = cursor.getString(columnIndexOrThrow3);
                    if (string2 != null) {
                        fyVar.ns.put(string2, g(cursor.getBlob(columnIndexOrThrow4)));
                    }
                } while (cursor.moveToNext());
                return hashMap;
            }
            return hashMap;
        } finally {
            ib.b(cursor);
        }
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        try {
            if (sQLiteDatabase == null) {
                return;
            }
            try {
                if (sQLiteDatabase.inTransaction()) {
                    sQLiteDatabase.endTransaction();
                }
            } catch (SQLiteException e) {
                io.e(TAG, "Database exception, it shouldn't happen, might be a bug in OS", e);
            }
        } finally {
            this.oF.close();
        }
    }

    private boolean c(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_data_namespace", str);
        contentValues.put("device_data_key", str2);
        contentValues.put("device_data_value", cr(str3));
        return ib.a(sQLiteDatabase, "device_data", contentValues, String.format("%s = ? and %s = ?", "device_data_namespace", "device_data_key"), new String[]{str, str2});
    }

    private boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_data_directed_id", str);
        contentValues.put("account_data_key", str2);
        contentValues.put("account_data_value", cr(str3));
        return ib.a(sQLiteDatabase, "account_data", contentValues, String.format("%s = ? and %s = ?", "account_data_directed_id", "account_data_key"), new String[]{str, str2});
    }

    private String g(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(h(bArr), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    static void f(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("encryption_data_key", "key_encryption_secret");
            contentValues.put("encryption_data_value", str);
            if (ib.a(sQLiteDatabase, "encryption_data", contentValues, String.format("%s = ?", "encryption_data_key"), new String[]{"key_encryption_secret"})) {
                io.i(TAG, "Encryption key prepared.");
            } else {
                io.e(TAG, "Failed to set encryption key. This should never happen!");
            }
        } catch (Exception e) {
            mq.incrementCounterAndRecord("MAPFailedSetEncryptionKeyToDB", new String[0]);
            io.e(TAG, "Failed to set encryption key in db", e);
        }
    }

    static String g(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        try {
            cursor = sQLiteDatabase.query("encryption_data", new String[]{"encryption_data_key", "encryption_data_value"}, String.format("%s = ?", "encryption_data_key"), new String[]{"key_encryption_secret"}, null, null, null);
            if (cursor != null) {
                try {
                    try {
                        if (cursor.moveToFirst()) {
                            String string = cursor.getString(cursor.getColumnIndexOrThrow("encryption_data_value"));
                            ib.b(cursor);
                            return string;
                        }
                    } catch (Exception e) {
                        e = e;
                        mq.incrementCounterAndRecord("MAPFailedGetEncryptionKeyFromDB", new String[0]);
                        io.e(TAG, "Failed to get encryption key from db", e);
                        ib.b(cursor);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    ib.b(cursor);
                    throw th;
                }
            }
            ib.b(cursor);
            return null;
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            ib.b(cursor);
            throw th;
        }
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        if (str == null || str2 == null || !a(sQLiteDatabase, str) || !b(sQLiteDatabase, str, str2, str3)) {
            return false;
        }
        fy cq = cq(str);
        if (cq == null) {
            return true;
        }
        cq.ns.put(str2, str3);
        return true;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) {
        fy b;
        if (str2 == null || str == null || (b = b(sQLiteDatabase, str2, str, map)) == null) {
            return false;
        }
        Map<String, fy> map2 = this.oG;
        if (map2 == null) {
            return true;
        }
        map2.put(str2, b);
        return true;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        return d(sQLiteDatabase).get(str) != null;
    }

    private void a(Map<String, String> map, String str, String str2, String str3) {
        map.put(str3, ib.v(str, str2, str3));
    }
}
