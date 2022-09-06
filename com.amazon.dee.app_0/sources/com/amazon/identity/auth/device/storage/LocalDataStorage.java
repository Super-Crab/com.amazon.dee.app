package com.amazon.identity.auth.device.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.device.dz;
import com.amazon.identity.auth.device.fx;
import com.amazon.identity.auth.device.fz;
import com.amazon.identity.auth.device.gj;
import com.amazon.identity.auth.device.gn;
import com.amazon.identity.auth.device.ib;
import com.amazon.identity.auth.device.in;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jj;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class LocalDataStorage {
    private static LocalDataStorage oA;
    private static final String[] oz = {AccountConstants.KEY_ACCOUNT_STATUS, "has.notified.server.of.deregister"};
    private final LambortishClock gw;
    private final Context mContext;
    private final a oB;
    private Map<String, Map<String, gj<String>>> oC;
    private Map<String, gj<fx>> oD;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum GetDataOptions {
        DirtyOnly,
        NotDirtyOnly,
        Deleted,
        NotDeleted
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a extends SQLiteOpenHelper {
        public a(Context context, String str) {
            super(context, GeneratedOutlineSupport1.outline72(str, ".db"), (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            io.i("LocalDataStorage", "Creating Local DataStore");
            sQLiteDatabase.execSQL(new dz("accounts").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("directed_id", "TEXT UNIQUE NOT NULL").p("display_name", "TEXT UNIQUE").p("account_timestamp", "INTEGER NOT NULL").p("account_deleted", "INTEGER NOT NULL").p("account_dirty", "INTEGER NOT NULL").toString());
            sQLiteDatabase.execSQL(new dz("userdata").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("userdata_account_id", "TEXT NOT NULL").p("userdata_key", "TEXT NOT NULL").p("userdata_value", Constants.Calling.MEDIA_STREAM_TYPE_TEXT).p("userdata_timestamp", "INTEGER NOT NULL").p("userdata_deleted", "INTEGER NOT NULL").p("userdata_dirty", "INTEGER NOT NULL").bv(String.format("UNIQUE(%s,%s)", "userdata_account_id", "userdata_key")).toString());
            sQLiteDatabase.execSQL(new dz("tokens").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("token_account_id", "TEXT NOT NULL").p(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "TEXT NOT NULL").p("token_value", Constants.Calling.MEDIA_STREAM_TYPE_TEXT).p("token_timestamp", "INTEGER NOT NULL").p("token_deleted", "INTEGER NOT NULL").p("token_dirty", "INTEGER NOT NULL").bv(String.format("UNIQUE(%s,%s)", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN)).toString());
            sQLiteDatabase.execSQL(new dz("device_data").p("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").p("device_data_namespace", "TEXT NOT NULL").p("device_data_key", "TEXT NOT NULL").p("device_data_value", Constants.Calling.MEDIA_STREAM_TYPE_TEXT).p("device_data_timestamp", "INTEGER NOT NULL").p("device_data_deleted", "INTEGER NOT NULL").p("device_data_dirty", "INTEGER NOT NULL").bv(String.format("UNIQUE(%s,%s)", "device_data_namespace", "device_data_key")).toString());
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            if (!sQLiteDatabase.isReadOnly()) {
                sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            throw new IllegalStateException(String.format("Cannot upgrade from version %d to %d", Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    LocalDataStorage(Context context) {
        this(context, new a(context, "map_data_storage"), LambortishClock.V(context));
    }

    public static synchronized LocalDataStorage W(Context context) {
        LocalDataStorage localDataStorage;
        synchronized (LocalDataStorage.class) {
            if (oA == null) {
                oA = new LocalDataStorage(context.getApplicationContext());
            }
            localDataStorage = oA;
        }
        return localDataStorage;
    }

    private void b(String str, Date date, boolean z) {
        boolean z2 = !z;
        gj<fx> co = co(str);
        if (co != null) {
            fx fxVar = new fx(co.getValue().directedId, null);
            gj<fx> gjVar = new gj<>(fxVar, date, z2, true);
            for (Map.Entry<String, gj<String>> entry : co.getValue().nr.entrySet()) {
                fxVar.nr.put(entry.getKey(), new gj<>(null, date, z2, true));
            }
            for (Map.Entry<String, gj<String>> entry2 : co.getValue().tokens.entrySet()) {
                fxVar.tokens.put(entry2.getKey(), new gj<>(null, date, z2, true));
            }
            fv().put(str, gjVar);
        }
    }

    private gj<fx> cn(String str) {
        return fv().get(str);
    }

    private gj<fx> co(String str) {
        Map<String, gj<fx>> map = this.oD;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    private Map<String, gj<fx>> fv() {
        if (this.oD == null) {
            this.oD = fx();
        }
        return this.oD;
    }

    private Map<String, Map<String, gj<String>>> fw() {
        if (this.oC == null) {
            this.oC = fy();
        }
        return this.oC;
    }

    private Map<String, gj<fx>> fx() {
        try {
            return b(this.oB.getReadableDatabase());
        } finally {
            this.oB.close();
        }
    }

    private Map<String, Map<String, gj<String>>> fy() {
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            cursor = this.oB.getReadableDatabase().query("device_data", new String[]{"device_data_namespace", "device_data_key", "device_data_value", "device_data_timestamp", "device_data_dirty", "device_data_deleted"}, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String e = ib.e(cursor, "device_data_namespace");
                    Map map = (Map) hashMap.get(e);
                    if (map == null) {
                        map = new HashMap();
                        hashMap.put(e, map);
                    }
                    map.put(ib.e(cursor, "device_data_key"), new gj(ib.e(cursor, "device_data_value"), ib.c(cursor, "device_data_timestamp"), ib.d(cursor, "device_data_dirty"), ib.d(cursor, "device_data_deleted")));
                } while (cursor.moveToNext());
            }
            return hashMap;
        } finally {
            ib.b(cursor);
            this.oB.close();
        }
    }

    private boolean g(Map<String, String> map) {
        return map.get("namespace") != null;
    }

    private int h(boolean z) {
        return z ^ true ? 1 : 0;
    }

    private synchronized void h(String str, String str2, Date date) {
        SQLiteDatabase a2 = gn.a(this.oB);
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_data_dirty", (Integer) 0);
        a2.update("device_data", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 0 and %s = 1", "device_data_namespace", "device_data_key", "device_data_timestamp", "device_data_deleted", "device_data_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
        this.oB.close();
    }

    public synchronized String C(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("namespace: ");
        sb.append(str);
        sb.append(", key: ");
        sb.append(str2);
        io.dm("LocalDataStorage");
        Map<String, gj<String>> map = fw().get(str);
        if (map == null) {
            return null;
        }
        gj<String> gjVar = map.get(str2);
        if (gjVar != null && !gjVar.fi()) {
            for (Map.Entry<String, gj<String>> entry : map.entrySet()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(entry.getKey());
                sb2.append(", ");
                sb2.append(entry.getValue().toString());
                io.dm("LocalDataStorage");
            }
            return gjVar.getValue();
        }
        return null;
    }

    public synchronized boolean a(String str, fz fzVar, Date date, boolean z) {
        boolean a2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = gn.a(this.oB);
            sQLiteDatabase.beginTransaction();
            a2 = a(sQLiteDatabase, str, fzVar, date, z);
            if (a2) {
                sQLiteDatabase.setTransactionSuccessful();
            }
            c(sQLiteDatabase);
            this.oB.close();
        } catch (SQLiteConstraintException unused) {
            io.e("LocalDataStorage", "Cannot add account since it violated a uniqueness constraint");
            if (sQLiteDatabase == null) {
                return false;
            }
            c(sQLiteDatabase);
            this.oB.close();
            return false;
        }
        return a2;
    }

    public synchronized void c(String str, String str2, Date date) {
        in.a(str, "directedId");
        in.a(str2, "key");
        in.a(date, "dateTime");
        g(str, str2, date);
        gj<fx> co = co(str);
        if (co == null) {
            return;
        }
        gj<String> gjVar = co.getValue().tokens.get(str2);
        if (gjVar == null) {
            return;
        }
        if (gjVar.getValue() != null) {
            return;
        }
        gjVar.a(date);
    }

    public synchronized Set<String> cc(String str) {
        HashSet hashSet = new HashSet();
        if (TextUtils.isEmpty(str)) {
            return hashSet;
        }
        gj<fx> cn = cn(str);
        if (cn != null && !cn.fi()) {
            for (Map.Entry<String, gj<String>> entry : cn.getValue().nr.entrySet()) {
                if (entry.getKey().startsWith("actor/")) {
                    hashSet.add(entry.getValue().getValue());
                }
            }
            return hashSet;
        }
        return hashSet;
    }

    public synchronized Set<String> cf(String str) {
        HashSet hashSet = new HashSet();
        Cursor query = this.oB.getReadableDatabase().query("tokens", new String[]{"token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_deleted"}, String.format("%s = ? and %s = 0", "token_account_id", "token_deleted"), new String[]{str}, null, null, null);
        if (query != null && query.moveToFirst()) {
            while (query.moveToNext()) {
                String e = ib.e(query, CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN);
                if (!TextUtils.isEmpty(e)) {
                    hashSet.add(e);
                }
            }
            ib.b(query);
            this.oB.close();
            return hashSet;
        }
        ib.b(query);
        this.oB.close();
        return hashSet;
    }

    public synchronized void d(String str, String str2, Date date) {
        in.a(str, "namespace");
        in.a(str2, "key");
        in.a(date, "dateTime");
        h(str, str2, date);
        if (this.oC == null) {
            return;
        }
        Map<String, gj<String>> map = this.oC.get(str);
        if (map == null) {
            return;
        }
        gj<String> gjVar = map.get(str2);
        if (gjVar == null) {
            return;
        }
        gjVar.a(date);
    }

    public synchronized Collection<Map<String, String>> e(Date date) {
        return a(date, EnumSet.of(GetDataOptions.DirtyOnly));
    }

    public synchronized Set<String> eT() {
        HashSet hashSet;
        Map<String, gj<fx>> fv = fv();
        hashSet = new HashSet();
        for (gj<fx> gjVar : fv.values()) {
            if (!gjVar.fi()) {
                hashSet.add(gjVar.getValue().displayName);
            }
        }
        return hashSet;
    }

    public synchronized boolean f(Date date) {
        if (date == null) {
            return false;
        }
        g(date);
        if (this.oD != null) {
            for (gj<fx> gjVar : this.oD.values()) {
                gjVar.b(date);
                for (gj<String> gjVar2 : gjVar.getValue().nr.values()) {
                    gjVar2.b(date);
                }
                for (gj<String> gjVar3 : gjVar.getValue().tokens.values()) {
                    gjVar3.b(date);
                }
            }
        }
        if (this.oC != null) {
            for (Map<String, gj<String>> map : this.oC.values()) {
                for (gj<String> gjVar4 : map.values()) {
                    gjVar4.b(date);
                }
            }
        }
        return true;
    }

    public synchronized Collection<Map<String, String>> fs() {
        return a((Date) null, EnumSet.noneOf(GetDataOptions.class));
    }

    public synchronized Collection<Map<String, String>> ft() {
        return a((Date) null, EnumSet.of(GetDataOptions.NotDirtyOnly, GetDataOptions.Deleted));
    }

    public synchronized void fu() {
        Context context = this.mContext;
        if (context != null) {
            context.deleteDatabase("map_data_storage.db");
        }
        this.oD = null;
        this.oC = null;
    }

    public synchronized String fz() {
        StringBuilder sb;
        sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, gj<fx>> entry : fv().entrySet()) {
            gj<fx> value = entry.getValue();
            fx value2 = value.getValue();
            if (!arrayList.contains(value2.directedId)) {
                arrayList.add(value2.directedId);
            }
            int indexOf = arrayList.indexOf(value2.directedId);
            a(sb, Integer.valueOf(indexOf));
            a(sb, "");
            a(sb, (gj<?>) value);
            for (Map.Entry<String, gj<String>> entry2 : value2.nr.entrySet()) {
                a(sb, String.valueOf(indexOf), entry2.getKey(), entry2.getValue());
            }
            for (Map.Entry<String, gj<String>> entry3 : value2.tokens.entrySet()) {
                a(sb, String.valueOf(indexOf), entry3.getKey(), entry3.getValue());
            }
        }
        for (Map.Entry<String, Map<String, gj<String>>> entry4 : fw().entrySet()) {
            for (Map.Entry<String, gj<String>> entry5 : entry4.getValue().entrySet()) {
                a(sb, entry4.getKey(), entry5.getKey(), entry5.getValue());
            }
        }
        return sb.toString();
    }

    public synchronized Set<String> getAccounts() {
        HashSet hashSet;
        Map<String, gj<fx>> fv = fv();
        hashSet = new HashSet();
        for (Map.Entry<String, gj<fx>> entry : fv.entrySet()) {
            if (!entry.getValue().fi()) {
                hashSet.add(entry.getKey());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public synchronized String z(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        gj<fx> cn = cn(str);
        if (cn != null && !cn.fi()) {
            gj<String> gjVar = cn.getValue().tokens.get(str2);
            if (gjVar != null && !gjVar.fi()) {
                return gjVar.getValue();
            }
            return null;
        }
        return null;
    }

    private synchronized void g(String str, String str2, Date date) {
        SQLiteDatabase a2 = gn.a(this.oB);
        ContentValues contentValues = new ContentValues();
        contentValues.put("token_dirty", (Integer) 0);
        a2.update("tokens", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 1 and %s = 1", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted", "token_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
        this.oB.close();
    }

    public synchronized boolean e(Collection<Map<String, String>> collection) {
        Map<String, gj<String>> map;
        if (collection == null) {
            return true;
        }
        SQLiteDatabase a2 = gn.a(this.oB);
        a2.beginTransaction();
        boolean z = true;
        for (Map<String, String> map2 : collection) {
            Date dH = jj.dH(map2.get("timestamp_key"));
            if (!Boolean.parseBoolean(map2.get("deleted_key"))) {
                io.e("LocalDataStorage", "Given a row that is not marked deleted. Cannot remove from the database!");
            } else {
                boolean z2 = false;
                if (d(map2)) {
                    String str = map2.get("directedId");
                    if (str == null) {
                        z &= z2;
                    } else {
                        a2.delete("accounts", String.format("%s = ? and %s = ? and %s = 1", "directed_id", "account_timestamp", "account_deleted"), new String[]{str, Long.toString(dH.getTime())});
                        a2.delete("userdata", String.format("%s = ? and %s = ? and %s = 1", "userdata_account_id", "userdata_timestamp", "userdata_deleted"), new String[]{str, Long.toString(dH.getTime())});
                        a2.delete("tokens", String.format("%s = ? and %s = ? and %s = 1", "token_account_id", "token_timestamp", "token_deleted"), new String[]{str, Long.toString(dH.getTime())});
                        if (this.oD != null) {
                            this.oD.remove(str);
                        }
                        z2 = true;
                        z &= z2;
                    }
                } else if (e(map2)) {
                    String str2 = map2.get("userdata_account");
                    if (str2 == null) {
                        z &= z2;
                    } else {
                        String str3 = map2.get("userdata_key");
                        a2.delete("userdata", String.format("%s = ? and %s = ? and %s = ? and %s = 1", "userdata_account_id", "userdata_key", "userdata_timestamp", "userdata_deleted"), new String[]{str2, str3, Long.toString(dH.getTime())});
                        gj<fx> co = co(str2);
                        if (co != null) {
                            co.getValue().nr.remove(str3);
                        }
                        z2 = true;
                        z &= z2;
                    }
                } else if (f(map2)) {
                    String str4 = map2.get("token_account");
                    if (str4 == null) {
                        z &= z2;
                    } else {
                        String str5 = map2.get(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN);
                        a2.delete("tokens", String.format("%s = ? and %s = ? and %s = ? and %s = 1", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted"), new String[]{str4, str5, Long.toString(dH.getTime())});
                        gj<fx> co2 = co(str4);
                        if (co2 != null) {
                            co2.getValue().tokens.remove(str5);
                        }
                        z2 = true;
                        z &= z2;
                    }
                } else if (g(map2)) {
                    String str6 = map2.get("namespace");
                    if (str6 == null) {
                        z &= z2;
                    } else {
                        a2.delete("device_data", String.format("%s = ? and %s = ? and %s = ?  and %s = 1", "device_data_namespace", "device_data_key", "device_data_timestamp", "device_data_deleted"), new String[]{str6, map2.get("device_data_key"), Long.toString(dH.getTime())});
                        if (this.oC != null && (map = this.oC.get(str6)) != null) {
                            map.remove(str6);
                        }
                        z2 = true;
                        z &= z2;
                    }
                }
            }
        }
        a2.setTransactionSuccessful();
        c(a2);
        this.oB.close();
        return z;
    }

    LocalDataStorage(Context context, a aVar, LambortishClock lambortishClock) {
        this.mContext = context;
        this.oB = aVar;
        this.gw = lambortishClock;
    }

    public synchronized String b(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        gj<fx> cn = cn(str);
        if (cn != null && !cn.fi()) {
            gj<String> gjVar = cn.getValue().nr.get(str2);
            if (gjVar != null && !gjVar.fi()) {
                return gjVar.getValue();
            }
            return null;
        }
        return null;
    }

    public Date h(Map<String, gj<fx>> map) {
        Date date = null;
        for (gj<fx> gjVar : map.values()) {
            for (gj<String> gjVar2 : gjVar.getValue().nr.values()) {
                date = a(date, gjVar2.fh());
            }
            for (gj<String> gjVar3 : gjVar.getValue().tokens.values()) {
                date = a(date, gjVar3.fh());
            }
            date = a(date, gjVar.fh());
        }
        return date;
    }

    private synchronized boolean g(Date date) {
        SQLiteDatabase a2 = gn.a(this.oB);
        a2.beginTransaction();
        a(a2, "accounts", "account_timestamp", "account_dirty", date);
        a(a2, "userdata", "userdata_timestamp", "userdata_dirty", date);
        a(a2, "tokens", "token_timestamp", "token_dirty", date);
        a(a2, "device_data", "device_data_timestamp", "device_data_dirty", date);
        a2.setTransactionSuccessful();
        c(a2);
        this.oB.close();
        return true;
    }

    public synchronized boolean c(String str, String str2, String str3, Date date, boolean z) {
        boolean b;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = gn.a(this.oB);
            sQLiteDatabase.beginTransaction();
            b = b(sQLiteDatabase, str, str2, str3, false, date, z);
            if (b) {
                sQLiteDatabase.setTransactionSuccessful();
            }
            c(sQLiteDatabase);
            this.oB.close();
        } catch (SQLiteConstraintException unused) {
            io.e("LocalDataStorage", "Cannot set device data since it violated a uniqueness constraint");
            if (sQLiteDatabase == null) {
                return false;
            }
            c(sQLiteDatabase);
            this.oB.close();
            return false;
        }
        return b;
    }

    public synchronized boolean d(Collection<Map<String, String>> collection) {
        SQLiteDatabase sQLiteDatabase;
        if (collection == null) {
            return true;
        }
        try {
            sQLiteDatabase = gn.a(this.oB);
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            sQLiteDatabase.beginTransaction();
            boolean z = true;
            for (Map<String, String> map : collection) {
                Date dH = jj.dH(map.get("timestamp_key"));
                boolean parseBoolean = Boolean.parseBoolean(map.get("deleted_key"));
                boolean z2 = false;
                if (d(map)) {
                    String str = map.get("directedId");
                    if (str != null) {
                        if (!parseBoolean) {
                            z2 = a(sQLiteDatabase, map.get("display_name"), new fz(str, null, null), dH, true);
                        } else {
                            z2 = a(sQLiteDatabase, str, dH, true);
                        }
                    }
                } else if (e(map)) {
                    String str2 = map.get("userdata_account");
                    if (str2 != null) {
                        z2 = a(sQLiteDatabase, str2, map.get("userdata_key"), map.get("userdata_value"), parseBoolean, dH, true);
                    }
                } else if (f(map)) {
                    String str3 = map.get("token_account");
                    if (str3 != null) {
                        String str4 = map.get(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN);
                        if (!parseBoolean) {
                            z2 = a(sQLiteDatabase, str3, str4, map.get("token_value"), dH, true);
                        } else {
                            z2 = a(sQLiteDatabase, str3, str4, dH, true);
                        }
                    }
                } else if (g(map)) {
                    String str5 = map.get("namespace");
                    if (str5 != null) {
                        z2 = b(sQLiteDatabase, str5, map.get("device_data_key"), map.get("device_data_value"), parseBoolean, dH, true);
                    }
                }
                z &= z2;
            }
            sQLiteDatabase.setTransactionSuccessful();
            c(sQLiteDatabase);
            this.oB.close();
            return z;
        } catch (Throwable th2) {
            th = th2;
            if (sQLiteDatabase != null) {
                c(sQLiteDatabase);
                this.oB.close();
            }
            throw th;
        }
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, fz fzVar, Date date, boolean z) {
        if (TextUtils.isEmpty(str) || date == null) {
            return false;
        }
        gj<fx> b = b(sQLiteDatabase, str, fzVar, date, z);
        if (b == null) {
            return z;
        }
        Map<String, gj<fx>> map = this.oD;
        if (map == null) {
            return true;
        }
        map.put(fzVar.getDirectedId(), b);
        return true;
    }

    private boolean f(Map<String, String> map) {
        return map.get("token_account") != null;
    }

    private synchronized void f(String str, String str2, Date date) {
        b(gn.a(this.oB), str, str2, date);
        this.oB.close();
    }

    public synchronized boolean b(String str, String str2, String str3, Date date, boolean z) {
        return a(new fz(str, null, Collections.singletonMap(str2, str3)), date, z);
    }

    public synchronized void b(String str, String str2, Date date) {
        in.a(str, "directedId");
        in.a(str2, "key");
        in.a(date, "dateTime");
        f(str, str2, date);
        gj<fx> co = co(str);
        if (co == null) {
            return;
        }
        gj<String> gjVar = co.getValue().tokens.get(str2);
        if (gjVar == null) {
            return;
        }
        gjVar.a(date);
    }

    public synchronized void a(fz fzVar, Date date) {
        in.a(date, "dateTime");
        b(fzVar, date);
        gj<fx> co = co(fzVar.getDirectedId());
        if (co == null) {
            return;
        }
        co.a(date);
        for (Map.Entry<String, gj<String>> entry : co.getValue().nr.entrySet()) {
            entry.getValue().a(date);
        }
        for (Map.Entry<String, gj<String>> entry2 : co.getValue().tokens.entrySet()) {
            entry2.getValue().a(date);
        }
    }

    private Map<String, String> c(String str, String str2, gj<String> gjVar) {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("namespace", str, "device_data_key", str2);
        outline134.put("device_data_value", gjVar.getValue());
        a(outline134, gjVar);
        return outline134;
    }

    private boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        if (str == null || str2 == null || date == null) {
            return false;
        }
        gj<String> e = e(sQLiteDatabase, str, str2, str3, z, date, z2);
        if (e == null) {
            return z2;
        }
        Map<String, Map<String, gj<String>>> map = this.oC;
        if (map == null) {
            return true;
        }
        Map<String, gj<String>> map2 = map.get(str);
        if (map2 == null) {
            map2 = new HashMap<>();
            this.oC.put(str, map2);
        }
        map2.put(str2, e);
        return true;
    }

    public synchronized boolean a(String str, Date date, boolean z) {
        boolean a2;
        SQLiteDatabase a3 = gn.a(this.oB);
        a3.beginTransaction();
        a2 = a(a3, str, date, z);
        if (a2) {
            a3.setTransactionSuccessful();
        }
        c(a3);
        this.oB.close();
        return a2;
    }

    public synchronized boolean c(Collection<Map<String, String>> collection) {
        fu();
        return d(collection);
    }

    private void c(SQLiteDatabase sQLiteDatabase, String str, Date date, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("userdata_value");
        contentValues.put("userdata_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("userdata_dirty", Integer.valueOf(h(z)));
        contentValues.put("userdata_deleted", (Integer) 1);
        sQLiteDatabase.update("userdata", contentValues, String.format("%s = ? and %s < ? and %s = 0", "userdata_account_id", "userdata_timestamp", "userdata_deleted"), new String[]{str, Long.toString(date.getTime())});
    }

    private Map<String, String> b(String str, String str2, gj<String> gjVar) {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("token_account", str, CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, str2);
        outline134.put("token_value", gjVar.getValue());
        a(outline134, gjVar);
        return outline134;
    }

    private gj<fx> b(SQLiteDatabase sQLiteDatabase, String str, fz fzVar, Date date, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("directed_id", fzVar.getDirectedId());
        contentValues.put("display_name", str);
        contentValues.put("account_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("account_dirty", Integer.valueOf(h(z)));
        contentValues.put("account_deleted", (Integer) 0);
        String str2 = "LocalDataStorage";
        if (!ib.a(sQLiteDatabase, "accounts", contentValues, String.format("%s = ? and %s < ?", "directed_id", "account_timestamp"), new String[]{fzVar.getDirectedId(), Long.toString(date.getTime())})) {
            io.e(str2, "Failed to add account");
            return null;
        }
        Map<String, gj<String>> a2 = a(sQLiteDatabase, fzVar.getDirectedId(), date);
        for (Map.Entry<String, String> entry : fzVar.eQ().entrySet()) {
            Map<String, gj<String>> map = a2;
            String str3 = str2;
            gj<String> d = d(sQLiteDatabase, fzVar.getDirectedId(), entry.getKey(), entry.getValue(), false, date, z);
            if (d == null) {
                io.e(str3, "Failed to save account because saving userdata was unsuccessful");
                return null;
            }
            map.put(entry.getKey(), d);
            a2 = map;
            str2 = str3;
        }
        Map<String, gj<String>> map2 = a2;
        String str4 = str2;
        Map<String, gj<String>> b = b(sQLiteDatabase, fzVar.getDirectedId(), date);
        for (Map.Entry<String, String> entry2 : fzVar.eP().entrySet()) {
            gj<String> b2 = b(sQLiteDatabase, fzVar.getDirectedId(), entry2.getKey(), entry2.getValue(), date, z);
            if (b2 == null) {
                io.e(str4, "Failed to save account because saving token was unsuccessful");
                return null;
            }
            b.put(entry2.getKey(), b2);
        }
        return new gj<>(new fx(fzVar.getDirectedId(), str, map2, b), date, true ^ z, false);
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, Date date, boolean z) {
        if (str == null || date == null) {
            return false;
        }
        if (!b(sQLiteDatabase, str, date, z)) {
            return z;
        }
        b(str, date, z);
        return true;
    }

    private gj<String> c(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        return d(sQLiteDatabase, str, str2, str3, z || c(sQLiteDatabase, str, date), date, z2);
    }

    public synchronized void a(String str, Date date) {
        in.a(str, "directedId");
        in.a(date, "dateTime");
        b(str, date);
        gj<fx> co = co(str);
        if (co == null) {
            return;
        }
        co.a(date);
        for (gj<String> gjVar : co.getValue().nr.values()) {
            gjVar.a(date);
        }
        for (gj<String> gjVar2 : co.getValue().tokens.values()) {
            gjVar2.a(date);
        }
    }

    private boolean c(SQLiteDatabase sQLiteDatabase, String str, Date date) {
        return ib.a(sQLiteDatabase, "accounts", "directed_id", String.format("%s = ? and %s > ?", "directed_id", "account_timestamp"), new String[]{str, Long.toString(date.getTime())}) != null;
    }

    void c(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase.inTransaction()) {
            sQLiteDatabase.endTransaction();
        }
    }

    private boolean d(Map<String, String> map) {
        return map.get("directedId") != null;
    }

    private void d(SQLiteDatabase sQLiteDatabase, String str, Date date, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("token_value");
        contentValues.put("token_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("token_dirty", Integer.valueOf(h(z)));
        contentValues.put("token_deleted", (Integer) 1);
        sQLiteDatabase.update("tokens", contentValues, String.format("%s = ? and %s < ? and %s = 0", "token_account_id", "token_timestamp", "token_deleted"), new String[]{str, Long.toString(date.getTime())});
    }

    public synchronized boolean a(String str, String str2, String str3, Date date, boolean z) {
        return a(new fz(str, Collections.singletonMap(str2, str3), null), date, z);
    }

    public synchronized boolean a(fz fzVar, Date date, boolean z) {
        boolean z2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = gn.a(this.oB);
            sQLiteDatabase.beginTransaction();
            z2 = true;
            Iterator<Map.Entry<String, String>> it2 = fzVar.eQ().entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it2.next();
                if (!a(sQLiteDatabase, fzVar.getDirectedId(), next.getKey(), next.getValue(), false, date, z)) {
                    z2 = false;
                    break;
                }
            }
            Iterator<Map.Entry<String, String>> it3 = fzVar.eP().entrySet().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next2 = it3.next();
                if (!a(sQLiteDatabase, fzVar.getDirectedId(), next2.getKey(), next2.getValue(), date, z)) {
                    z2 = false;
                    break;
                }
            }
            if (z2) {
                sQLiteDatabase.setTransactionSuccessful();
            }
            c(sQLiteDatabase);
            this.oB.close();
        } catch (SQLiteConstraintException unused) {
            io.e("LocalDataStorage", "Cannot set token since it violated a uniqueness constraint");
            if (sQLiteDatabase != null) {
                c(sQLiteDatabase);
                this.oB.close();
            }
            return false;
        }
        return z2;
    }

    private boolean e(Map<String, String> map) {
        return map.get("userdata_account") != null;
    }

    private synchronized void e(String str, String str2, Date date) {
        SQLiteDatabase a2 = gn.a(this.oB);
        a2.beginTransaction();
        a(a2, str, str2, date);
        a2.setTransactionSuccessful();
        c(a2);
        this.oB.close();
    }

    private gj<String> d(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userdata_account_id", str);
        contentValues.put("userdata_key", str2);
        contentValues.put("userdata_value", str3);
        contentValues.put("userdata_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("userdata_dirty", Integer.valueOf(h(z2)));
        contentValues.put("userdata_deleted", Integer.valueOf(z ? 1 : 0));
        if (!ib.a(sQLiteDatabase, "userdata", contentValues, String.format("%s = ? and %s = ? and %s < ?", "userdata_account_id", "userdata_key", "userdata_timestamp"), new String[]{str, str2, Long.toString(date.getTime())})) {
            return null;
        }
        return new gj<>(str3, date, !z2, z);
    }

    private gj<String> e(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_data_namespace", str);
        contentValues.put("device_data_key", str2);
        contentValues.put("device_data_value", str3);
        contentValues.put("device_data_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("device_data_deleted", Integer.valueOf(z ? 1 : 0));
        contentValues.put("device_data_dirty", Integer.valueOf(h(z2)));
        if (!ib.a(sQLiteDatabase, "device_data", contentValues, String.format("%s = ? and %s = ? and %s < ?", "device_data_namespace", "device_data_key", "device_data_timestamp"), new String[]{str, str2, Long.toString(date.getTime())})) {
            return null;
        }
        return new gj<>(str3, date, !z2, z);
    }

    private synchronized void b(fz fzVar, Date date) {
        SQLiteDatabase a2 = gn.a(this.oB);
        a2.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_dirty", (Integer) 0);
        a2.update("accounts", contentValues, String.format("%s = ? and %s = ? and %s = 1 and %s = 0", "directed_id", "account_timestamp", "account_dirty", "account_deleted"), new String[]{fzVar.getDirectedId(), Long.toString(date.getTime())});
        for (Map.Entry<String, String> entry : fzVar.eQ().entrySet()) {
            a(a2, fzVar.getDirectedId(), entry.getKey(), date);
        }
        for (Map.Entry<String, String> entry2 : fzVar.eP().entrySet()) {
            b(a2, fzVar.getDirectedId(), entry2.getKey(), date);
        }
        a2.setTransactionSuccessful();
        c(a2);
        this.oB.close();
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        if (str == null || str2 == null || date == null) {
            return false;
        }
        if ((!z2) && !a(sQLiteDatabase, str)) {
            return false;
        }
        gj<String> c = c(sQLiteDatabase, str, str2, str3, z, date, z2);
        if (c == null) {
            return z2;
        }
        gj<fx> co = co(str);
        if (co == null) {
            return true;
        }
        co.getValue().nr.put(str2, c);
        return true;
    }

    public synchronized void a(String str, String str2, Date date) {
        in.a(str, "directedId");
        in.a(str2, "key");
        in.a(date, "dateTime");
        e(str, str2, date);
        gj<fx> co = co(str);
        if (co == null) {
            return;
        }
        gj<String> gjVar = co.getValue().nr.get(str2);
        if (gjVar == null) {
            return;
        }
        gjVar.a(date);
    }

    private boolean b(SQLiteDatabase sQLiteDatabase, String str, Date date, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("directed_id", str);
        contentValues.putNull("display_name");
        contentValues.put("account_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("account_dirty", Integer.valueOf(h(z)));
        boolean z2 = true;
        contentValues.put("account_deleted", (Integer) 1);
        String format = String.format("%s = ? and %s < ? and %s = 0", "directed_id", "account_timestamp", "account_deleted");
        String[] strArr = {str, Long.toString(date.getTime())};
        if (z) {
            z2 = ib.a(sQLiteDatabase, "accounts", contentValues, format, strArr);
        } else if (sQLiteDatabase.update("accounts", contentValues, format, strArr) <= 0) {
            z2 = false;
        }
        c(sQLiteDatabase, str, date, z);
        d(sQLiteDatabase, str, date, z);
        return z2;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Date date, boolean z) {
        if (str == null || str2 == null || date == null) {
            return false;
        }
        if ((!z) && !a(sQLiteDatabase, str)) {
            return false;
        }
        gj<String> b = b(sQLiteDatabase, str, str2, str3, date, z);
        if (b == null) {
            return z;
        }
        gj<fx> co = co(str);
        if (co == null) {
            return true;
        }
        co.getValue().tokens.put(str2, b);
        return true;
    }

    public synchronized boolean a(String str, String str2, Date date, boolean z) {
        boolean a2;
        SQLiteDatabase a3 = gn.a(this.oB);
        a3.beginTransaction();
        a2 = a(a3, str, str2, date, z);
        if (a2) {
            a3.setTransactionSuccessful();
        }
        c(a3);
        this.oB.close();
        return a2;
    }

    private synchronized void b(String str, Date date) {
        SQLiteDatabase a2 = gn.a(this.oB);
        a2.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_dirty", (Integer) 0);
        a2.update("accounts", contentValues, String.format("%s = ? and %s = ? and %s = 1 and %s = 1", "directed_id", "account_timestamp", "account_deleted", "account_dirty"), new String[]{str, Long.toString(date.getTime())});
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("userdata_dirty", (Integer) 0);
        a2.update("userdata", contentValues2, String.format("%s = ? and %s = ? and %s = 1 and %s = 1", "userdata_account_id", "userdata_timestamp", "userdata_deleted", "userdata_dirty"), new String[]{str, Long.toString(date.getTime())});
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("token_dirty", (Integer) 0);
        a2.update("tokens", contentValues3, String.format("%s = ? and %s = ? and %s = 1 and %s = 1", "token_account_id", "token_timestamp", "token_deleted", "token_dirty"), new String[]{str, Long.toString(date.getTime())});
        a2.setTransactionSuccessful();
        c(a2);
        this.oB.close();
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, Date date, boolean z) {
        if (str == null || str2 == null || date == null) {
            return false;
        }
        gj<String> b = b(sQLiteDatabase, str, str2, date, z);
        if (b == null) {
            return z;
        }
        gj<fx> co = co(str);
        if (co == null) {
            return true;
        }
        co.getValue().tokens.put(str2, b);
        return true;
    }

    private Collection<Map<String, String>> a(Date date, EnumSet<GetDataOptions> enumSet) {
        LinkedList linkedList = new LinkedList();
        for (Map.Entry<String, gj<fx>> entry : fv().entrySet()) {
            gj<fx> value = entry.getValue();
            fx value2 = value.getValue();
            if (a(enumSet, date, value)) {
                linkedList.add(a(value));
            }
            for (Map.Entry<String, gj<String>> entry2 : value2.nr.entrySet()) {
                if (a(enumSet, date, entry2.getValue())) {
                    linkedList.add(a(value2.directedId, entry2.getKey(), entry2.getValue()));
                }
            }
            for (Map.Entry<String, gj<String>> entry3 : value2.tokens.entrySet()) {
                if (a(enumSet, date, entry3.getValue())) {
                    linkedList.add(b(value2.directedId, entry3.getKey(), entry3.getValue()));
                }
            }
        }
        for (Map.Entry<String, Map<String, gj<String>>> entry4 : fw().entrySet()) {
            for (Map.Entry<String, gj<String>> entry5 : entry4.getValue().entrySet()) {
                if (a(enumSet, date, entry5.getValue())) {
                    linkedList.add(c(entry4.getKey(), entry5.getKey(), entry5.getValue()));
                }
            }
        }
        return Collections.unmodifiableCollection(linkedList);
    }

    private gj<String> b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Date date, boolean z) {
        boolean c = c(sQLiteDatabase, str, date);
        ContentValues contentValues = new ContentValues();
        contentValues.put("token_account_id", str);
        contentValues.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, str2);
        contentValues.put("token_value", str3);
        contentValues.put("token_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("token_dirty", Integer.valueOf(h(z)));
        contentValues.put("token_deleted", Integer.valueOf(c ? 1 : 0));
        if (!ib.a(sQLiteDatabase, "tokens", contentValues, String.format("%s = ? and %s < ? and %s = ?", "token_account_id", "token_timestamp", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN), new String[]{str, Long.toString(date.getTime()), str2})) {
            return null;
        }
        return new gj<>(str3, date, !z, c);
    }

    private boolean a(EnumSet<GetDataOptions> enumSet, Date date, gj<?> gjVar) {
        return a(enumSet, gjVar) && a(date, gjVar);
    }

    private boolean a(EnumSet<GetDataOptions> enumSet, gj<?> gjVar) {
        if (!enumSet.contains(GetDataOptions.DirtyOnly) || gjVar.isDirty()) {
            if (enumSet.contains(GetDataOptions.NotDirtyOnly) && gjVar.isDirty()) {
                return false;
            }
            if (enumSet.contains(GetDataOptions.Deleted) && !gjVar.fi()) {
                return false;
            }
            return !enumSet.contains(GetDataOptions.NotDeleted) || !gjVar.fi();
        }
        return false;
    }

    private boolean a(Date date, gj<?> gjVar) {
        return date == null || !gjVar.c(date);
    }

    private Map<String, String> a(gj<fx> gjVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("directedId", gjVar.getValue().directedId);
        hashMap.put("display_name", gjVar.getValue().displayName);
        a(hashMap, gjVar);
        return hashMap;
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str, String str2, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("token_dirty", (Integer) 0);
        sQLiteDatabase.update("tokens", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 0 and %s = 1", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted", "token_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
    }

    private Map<String, String> a(String str, String str2, gj<String> gjVar) {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("userdata_account", str, "userdata_key", str2);
        outline134.put("userdata_value", gjVar.getValue());
        a(outline134, gjVar);
        return outline134;
    }

    private gj<String> b(SQLiteDatabase sQLiteDatabase, String str, String str2, Date date, boolean z) {
        boolean z2;
        ContentValues contentValues = new ContentValues();
        contentValues.put("token_account_id", str);
        contentValues.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, str2);
        contentValues.putNull("token_value");
        contentValues.put("token_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("token_deleted", (Integer) 1);
        contentValues.put("token_dirty", Integer.valueOf(h(z)));
        String format = String.format("%s = ? and %s = ? and %s < ? and %s = 0", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted");
        String[] strArr = {str, str2, Long.toString(date.getTime())};
        if (z) {
            z2 = ib.a(sQLiteDatabase, "tokens", contentValues, format, strArr);
        } else {
            z2 = sQLiteDatabase.update("tokens", contentValues, format, strArr) > 0;
        }
        if (!z2) {
            return null;
        }
        return new gj<>(null, date, !z, true);
    }

    private void a(Map<String, String> map, gj<?> gjVar) {
        map.put("timestamp_key", jj.h(gjVar.fh()));
        map.put("dirty_key", Boolean.toString(gjVar.isDirty()));
        map.put("deleted_key", Boolean.toString(gjVar.fi()));
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        gj<fx> gjVar = a(sQLiteDatabase).get(str);
        return gjVar != null && !gjVar.fi();
    }

    private Map<String, gj<fx>> a(SQLiteDatabase sQLiteDatabase) {
        if (this.oD == null) {
            this.oD = b(sQLiteDatabase);
        }
        return this.oD;
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, String str2, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userdata_dirty", (Integer) 0);
        sQLiteDatabase.update("userdata", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 1", "userdata_account_id", "userdata_key", "userdata_timestamp", "userdata_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
    }

    private Map<String, gj<String>> b(SQLiteDatabase sQLiteDatabase, String str, Date date) {
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("tokens", new String[]{"token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_value", "token_timestamp", "token_deleted", "token_dirty"}, String.format("%s = ? and %s >= ? and %s = 0", "token_account_id", "token_timestamp", "token_deleted"), new String[]{str, Long.toString(date.getTime())}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    b(cursor, hashMap);
                } while (cursor.moveToNext());
                return hashMap;
            }
            return hashMap;
        } finally {
            ib.b(cursor);
        }
    }

    private Map<String, gj<String>> a(SQLiteDatabase sQLiteDatabase, String str, Date date) {
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("userdata", new String[]{"userdata_account_id", "userdata_key", "userdata_value", "userdata_timestamp", "userdata_deleted", "userdata_dirty"}, String.format("%s = ? and %s >= ? and %s = 0", "userdata_account_id", "userdata_timestamp", "userdata_deleted"), new String[]{str, Long.toString(date.getTime())}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    a(cursor, hashMap);
                } while (cursor.moveToNext());
                return hashMap;
            }
            return hashMap;
        } finally {
            ib.b(cursor);
        }
    }

    private Map<String, gj<fx>> b(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        Map<String, gj<fx>> map;
        Map<String, gj<fx>> hashMap = new HashMap<>();
        try {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("accounts LEFT OUTER JOIN userdata ON (" + ib.ar("accounts", "directed_id") + " = " + ib.ar("userdata", "userdata_account_id") + ") LEFT OUTER JOIN tokens ON (" + ib.ar("accounts", "directed_id") + " = " + ib.ar("tokens", "token_account_id") + ")");
            HashMap hashMap2 = new HashMap();
            a(hashMap2, "accounts", "_id", "_id");
            a(hashMap2, "accounts", "directed_id", "directed_id");
            a(hashMap2, "accounts", "display_name", "display_name");
            a(hashMap2, "accounts", "account_timestamp", "account_timestamp");
            a(hashMap2, "accounts", "account_dirty", "account_dirty");
            a(hashMap2, "accounts", "account_deleted", "account_deleted");
            a(hashMap2, "userdata", "userdata_key", "userdata_key");
            a(hashMap2, "userdata", "userdata_value", "userdata_value");
            a(hashMap2, "userdata", "userdata_timestamp", "userdata_timestamp");
            a(hashMap2, "userdata", "userdata_dirty", "userdata_dirty");
            a(hashMap2, "userdata", "userdata_deleted", "userdata_deleted");
            a(hashMap2, "tokens", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN);
            a(hashMap2, "tokens", "token_value", "token_value");
            a(hashMap2, "tokens", "token_timestamp", "token_timestamp");
            a(hashMap2, "tokens", "token_dirty", "token_dirty");
            a(hashMap2, "tokens", "token_deleted", "token_deleted");
            sQLiteQueryBuilder.setProjectionMap(hashMap2);
            cursor = null;
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery(sQLiteQueryBuilder.buildQuery((String[]) hashMap2.keySet().toArray(new String[0]), null, null, null, null, null, null), null);
                if (rawQuery != null) {
                    try {
                        if (rawQuery.moveToFirst()) {
                            while (true) {
                                String e = ib.e(rawQuery, "directed_id");
                                map = hashMap;
                                gj<fx> gjVar = map.get(e);
                                if (gjVar == null) {
                                    String e2 = ib.e(rawQuery, "display_name");
                                    gjVar = new gj<>(new fx(e, e2), ib.c(rawQuery, "account_timestamp"), ib.d(rawQuery, "account_dirty"), ib.d(rawQuery, "account_deleted"));
                                    map.put(e, gjVar);
                                }
                                a(rawQuery, gjVar.getValue().nr);
                                b(rawQuery, gjVar.getValue().tokens);
                                if (!rawQuery.moveToNext()) {
                                    break;
                                }
                                hashMap = map;
                            }
                            if (this.gw.d(h(map))) {
                                mq.b("LamportTimestampUpdatedBasedOnDBSnapshot", new String[0]);
                            }
                            ib.b(rawQuery);
                            return map;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        ib.b(cursor);
                        throw th;
                    }
                }
                ib.b(rawQuery);
                return hashMap;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str3, (Integer) 0);
        String.format("Update %d items not dirty in table %s.", Integer.valueOf(sQLiteDatabase.update(str, contentValues, String.format("%s <= ?", str2), new String[]{Long.toString(date.getTime())})), str);
        io.dm("LocalDataStorage");
    }

    public Date a(Date date, Date date2) {
        return (date != null && !date2.after(date)) ? date : date2;
    }

    private void a(Cursor cursor, Map<String, gj<String>> map) {
        String e = ib.e(cursor, "userdata_key");
        if (e == null) {
            return;
        }
        map.put(e, new gj<>(ib.e(cursor, "userdata_value"), ib.c(cursor, "userdata_timestamp"), ib.d(cursor, "userdata_dirty"), ib.d(cursor, "userdata_deleted")));
    }

    private void a(Map<String, String> map, String str, String str2, String str3) {
        map.put(str3, ib.v(str, str2, str3));
    }

    private void a(StringBuilder sb, String str, String str2, gj<String> gjVar) {
        a(sb, str);
        a(sb, str2);
        if (a(oz, str2)) {
            a(sb, gjVar.getValue());
        } else {
            a(sb, "");
        }
        a(sb, (gj<?>) gjVar);
    }

    private void a(StringBuilder sb, gj<?> gjVar) {
        a(sb, jj.h(gjVar.fh()));
        a(sb, String.valueOf(gjVar.fi()));
        a(sb, String.valueOf(gjVar.isDirty()));
        sb.append("\n");
    }

    private void a(StringBuilder sb, Object obj) {
        sb.append(obj);
        sb.append(",");
    }

    private boolean a(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.endsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private void b(Cursor cursor, Map<String, gj<String>> map) {
        String e = ib.e(cursor, CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN);
        if (e == null) {
            return;
        }
        map.put(e, new gj<>(ib.e(cursor, "token_value"), ib.c(cursor, "token_timestamp"), ib.d(cursor, "token_dirty"), ib.d(cursor, "token_deleted")));
    }
}
