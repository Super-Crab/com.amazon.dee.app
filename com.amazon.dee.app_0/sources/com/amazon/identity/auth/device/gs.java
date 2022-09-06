package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gs {
    private final ec dr;
    private final Context mContext;
    private final du oR;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static final class a {
        public final String selection;
        public final String[] selectionArgs;

        a(String str, String[] strArr) {
            this.selection = str;
            this.selectionArgs = strArr;
        }

        private static void a(LinkedHashMap<String, String> linkedHashMap, List<String> list, String str, Date date) {
            a(linkedHashMap, list, str, jj.h(date));
        }

        public static a d(String str, String str2, String str3, Date date) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            ArrayList arrayList = new ArrayList();
            a(linkedHashMap, arrayList, "directedId", str);
            a(linkedHashMap, arrayList, "key", str2);
            a(linkedHashMap, arrayList, "value", (String) null);
            a(linkedHashMap, arrayList, "namespace", str3);
            a(linkedHashMap, arrayList, "timestamp_key", date);
            return new a(ik.j(linkedHashMap), (String[]) arrayList.toArray(new String[0]));
        }

        static void a(LinkedHashMap<String, String> linkedHashMap, List<String> list, String str, String str2) {
            if (str2 == null) {
                return;
            }
            linkedHashMap.put(str, WebConstants.UriConstants.QUESTIONMARK_KEY);
            list.add(str2);
        }
    }

    public gs(Context context, du duVar) {
        this.mContext = context;
        this.dr = new ec(context);
        this.oR = duVar;
    }

    private Collection<Map<String, String>> c(final Uri uri) throws RemoteMAPException {
        return (Collection) this.dr.a(uri, new dj<Collection<Map<String, String>>>() { // from class: com.amazon.identity.auth.device.gs.1
            @Override // com.amazon.identity.auth.device.dj
            /* renamed from: e */
            public Collection<Map<String, String>> b(ContentProviderClient contentProviderClient) throws Exception {
                Uri uri2 = uri;
                List<String> list = gq.oO;
                Cursor query = contentProviderClient.query(uri2, (String[]) list.toArray(new String[list.size()]), null, null, null);
                if (query == null) {
                    return null;
                }
                try {
                    LinkedList linkedList = new LinkedList();
                    if (!query.moveToFirst()) {
                        return linkedList;
                    }
                    do {
                        HashMap hashMap = new HashMap();
                        for (String str : gq.oO) {
                            String e = ib.e(query, str);
                            if (str != null) {
                                hashMap.put(str, e);
                            }
                        }
                        linkedList.add(hashMap);
                    } while (query.moveToNext());
                    return linkedList;
                } finally {
                    ib.b(query);
                }
            }
        });
    }

    public String C(String str, String str2) {
        Cursor cursor;
        Uri cA = gq.cA(this.oR.getProviderAuthority());
        a d = a.d(null, str2, str, null);
        try {
            cursor = this.mContext.getContentResolver().query(cA, new String[]{"value"}, d.selection, d.selectionArgs, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String e = ib.e(cursor, "value");
                        "Fetch remote device data: ".concat(String.valueOf(e));
                        io.dm("RemoteAmazonDataStorage");
                        ib.b(cursor);
                        return e;
                    }
                } catch (Throwable th) {
                    th = th;
                    ib.b(cursor);
                    throw th;
                }
            }
            ib.b(cursor);
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public boolean a(String str, fz fzVar, Date date) {
        String directedId = fzVar.getDirectedId();
        if (str == null || directedId == null || date == null) {
            return false;
        }
        ArrayList<Map> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("display_name", str);
        hashMap.put("directedId", directedId);
        arrayList.add(hashMap);
        for (Map.Entry<String, String> entry : fzVar.eQ().entrySet()) {
            HashMap outline133 = GeneratedOutlineSupport1.outline133("userdata_account", directedId);
            outline133.put("userdata_key", entry.getKey());
            outline133.put("userdata_value", entry.getValue());
            arrayList.add(outline133);
        }
        for (Map.Entry<String, String> entry2 : fzVar.eP().entrySet()) {
            HashMap outline1332 = GeneratedOutlineSupport1.outline133("token_account", directedId);
            outline1332.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, entry2.getKey());
            outline1332.put("token_value", entry2.getValue());
            arrayList.add(outline1332);
        }
        String valueOf = String.valueOf(date.getTime());
        for (Map map : arrayList) {
            map.put("timestamp_key", valueOf);
            map.put("deleted_key", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
        }
        return d(arrayList);
    }

    public boolean b(String str, String str2, String str3, Date date) {
        Uri cz = gq.cz(this.oR.getProviderAuthority());
        ContentValues contentValues = new ContentValues();
        contentValues.put("directedId", str);
        contentValues.put("key", str2);
        contentValues.put("value", str3);
        if (date != null) {
            contentValues.put("timestamp_key", Long.valueOf(date.getTime()));
        }
        boolean z = this.mContext.getContentResolver().insert(cz, contentValues) != null;
        if (z) {
            String.format("set token was successful with package %s.", this.oR.getPackageName());
            io.dm("RemoteAmazonDataStorage");
        } else {
            io.i("RemoteAmazonDataStorage", String.format("set token was not successful with package %s.", this.oR.getPackageName()));
        }
        return z;
    }

    public boolean d(Collection<Map<String, String>> collection) {
        Uri cE = gq.cE(this.oR.getProviderAuthority());
        ContentValues contentValues = new ContentValues();
        contentValues.put("bulk_data", iu.f(collection));
        try {
            boolean z = this.dr.insert(cE, contentValues) != null;
            if (z) {
                String.format("set bulk data was successful with package %s.", this.oR.getPackageName());
                io.dm("RemoteAmazonDataStorage");
            } else {
                io.i("RemoteAmazonDataStorage", String.format("set bulk data was not successful with package %s.", this.oR.getPackageName()));
            }
            return z;
        } catch (RemoteMAPException e) {
            io.i("RemoteAmazonDataStorage", String.format("set bulk data was not successful with package %s.", this.oR.getPackageName()), e);
            return false;
        }
    }

    public boolean e(Collection<Map<String, String>> collection) {
        Uri cE = gq.cE(this.oR.getProviderAuthority());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        a.a(linkedHashMap, arrayList, "bulk_data", iu.f(collection));
        a aVar = new a(ik.j(linkedHashMap), (String[]) arrayList.toArray(new String[0]));
        try {
            boolean z = this.dr.delete(cE, aVar.selection, aVar.selectionArgs) > 0;
            if (z) {
                String.format("clear bulk data was successful with package %s.", this.oR.getPackageName());
                io.dm("RemoteAmazonDataStorage");
            } else {
                io.i("RemoteAmazonDataStorage", String.format("clear bulk data was not successful with package %s.", this.oR.getPackageName()));
            }
            return z;
        } catch (RemoteMAPException e) {
            io.i("RemoteAmazonDataStorage", String.format("clear bulk data was not successful with package %s.", this.oR.getPackageName()), e);
            return false;
        }
    }

    public Collection<Map<String, String>> fF() throws RemoteMAPException {
        return c(gq.cC(this.oR.getProviderAuthority()));
    }

    public Collection<Map<String, String>> fG() throws RemoteMAPException {
        return c(gq.cD(this.oR.getProviderAuthority()));
    }

    public boolean i(String str, String str2, Date date) {
        Uri cz = gq.cz(this.oR.getProviderAuthority());
        a d = a.d(str, str2, null, date);
        int delete = this.mContext.getContentResolver().delete(cz, d.selection, d.selectionArgs);
        String.format("Expired %d tokens from package %s", Integer.valueOf(delete), this.oR.getPackageName());
        io.dm("RemoteAmazonDataStorage");
        return delete != 0;
    }

    public boolean c(String str, Date date) {
        Uri cx = gq.cx(this.oR.getProviderAuthority());
        a d = a.d(str, null, null, date);
        try {
            int delete = this.dr.delete(cx, d.selection, d.selectionArgs);
            String.format("Removed %d accounts from package %s", Integer.valueOf(delete), this.oR.getPackageName());
            io.dm("RemoteAmazonDataStorage");
            return delete != 0;
        } catch (RemoteMAPException e) {
            io.i("RemoteAmazonDataStorage", String.format("Failed to remove accounts from package %s", this.oR.getPackageName()), e);
            return false;
        }
    }

    public boolean c(String str, String str2, String str3, Date date) {
        Uri cA = gq.cA(this.oR.getProviderAuthority());
        ContentValues contentValues = new ContentValues();
        contentValues.put("namespace", str);
        contentValues.put("key", str2);
        contentValues.put("value", str3);
        if (date != null) {
            contentValues.put("timestamp_key", Long.valueOf(date.getTime()));
        }
        boolean z = this.mContext.getContentResolver().insert(cA, contentValues) != null;
        if (z) {
            String.format("set device data was successful with package %s.", this.oR.getPackageName());
            io.dm("RemoteAmazonDataStorage");
        } else {
            io.i("RemoteAmazonDataStorage", String.format("set device data was not successful with package %s.", this.oR.getPackageName()));
        }
        return z;
    }

    public boolean a(String str, String str2, String str3, Date date) {
        Uri cy = gq.cy(this.oR.getProviderAuthority());
        ContentValues contentValues = new ContentValues();
        contentValues.put("directedId", str);
        contentValues.put("key", str2);
        contentValues.put("value", str3);
        if (date != null) {
            contentValues.put("timestamp_key", Long.valueOf(date.getTime()));
        }
        boolean z = this.mContext.getContentResolver().insert(cy, contentValues) != null;
        if (z) {
            String.format("set userdata was successful with package %s.", this.oR.getPackageName());
            io.dm("RemoteAmazonDataStorage");
        } else {
            io.i("RemoteAmazonDataStorage", String.format("set userdata was not successful with package %s.", this.oR.getPackageName()));
        }
        return z;
    }
}
