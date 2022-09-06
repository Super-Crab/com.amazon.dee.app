package com.amazon.identity.auth.device.api;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.dh;
import com.amazon.identity.auth.device.dq;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.fo;
import com.amazon.identity.auth.device.fz;
import com.amazon.identity.auth.device.hv;
import com.amazon.identity.auth.device.hw;
import com.amazon.identity.auth.device.ib;
import com.amazon.identity.auth.device.ie;
import com.amazon.identity.auth.device.ik;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.iu;
import com.amazon.identity.auth.device.ja;
import com.amazon.identity.auth.device.jj;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.storage.LambortishClock;
import com.amazon.identity.auth.device.storage.LocalDataStorage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class MAPInformationProvider extends ContentProvider {
    private static final String TAG = MAPInformationProvider.class.getName();
    @FireOsSdk
    public static final String TOKEN_PROVIDER_AUTHORITY_PREFIX = "com.amazon.identity.auth.device.MapInfoProvider.";
    volatile hv gu;
    private LocalDataStorage gv;
    private LambortishClock gw;
    private Context mContext;
    private boolean mInitialized = false;

    private boolean a(Uri uri, ContentValues contentValues) {
        Collection<Map<String, String>> collection;
        Date date;
        Date date2;
        Date date3;
        Date date4;
        SelectionInfo b = b(uri, contentValues);
        String path = uri.getPath();
        if (AppUrl.OOBE_ACCOUNTS.equals(path)) {
            if (b.displayName == null || b.directedId == null || (date4 = b.timestamp) == null) {
                return false;
            }
            this.gw.d(date4);
            return this.gv.a(b.displayName, new fz(b.directedId, hw.L(b.userdata), null), b.timestamp, true);
        } else if ("/userdata".equals(path)) {
            if (b.directedId == null || b.key == null || (date3 = b.timestamp) == null) {
                return false;
            }
            this.gw.d(date3);
            return this.gv.a(b.directedId, b.key, b.value, b.timestamp, true);
        } else if ("/tokens".equals(path)) {
            if (b.directedId == null || b.key == null || (date2 = b.timestamp) == null) {
                return false;
            }
            this.gw.d(date2);
            return this.gv.b(b.directedId, b.key, b.value, b.timestamp, true);
        } else if ("/device_data".equals(path)) {
            if (b.namespace == null || b.key == null || (date = b.timestamp) == null) {
                return false;
            }
            this.gw.d(date);
            return this.gv.c(b.namespace, b.key, b.value, b.timestamp, true);
        } else if (!"/bulk_data".equals(path) || (collection = b.bulkData) == null) {
            return false;
        } else {
            a(collection);
            return this.gv.d(b.bulkData);
        }
    }

    private SelectionInfo b(Uri uri, ContentValues contentValues) {
        a(uri);
        SelectionInfo parseSelection = SelectionInfo.parseSelection(contentValues);
        if (parseSelection != null) {
            return parseSelection;
        }
        throw new IllegalArgumentException("Invalid selection");
    }

    private synchronized void bj() {
        if (this.mInitialized) {
            return;
        }
        MAPInit.getInstance(this.mContext).initialize();
        this.mContext = ed.M(this.mContext);
        this.gv = (LocalDataStorage) this.mContext.getSystemService("sso_local_datastorage");
        this.gw = LambortishClock.V(this.mContext);
        this.mInitialized = true;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final int delete(Uri uri, String str, String[] strArr) {
        Collection<Map<String, String>> collection;
        boolean e;
        Date date;
        Date date2;
        ja.aD(this.mContext);
        bj();
        SelectionInfo a = a(uri, str, strArr);
        String path = uri.getPath();
        if (AppUrl.OOBE_ACCOUNTS.equals(path)) {
            if (a.directedId != null && (date2 = a.timestamp) != null) {
                this.gw.d(date2);
                e = this.gv.a(a.directedId, a.timestamp, true);
            }
            e = false;
        } else if ("/tokens".equals(path)) {
            if (a.directedId != null && a.key != null && (date = a.timestamp) != null) {
                this.gw.d(date);
                e = this.gv.a(a.directedId, a.key, a.timestamp, true);
            }
            e = false;
        } else {
            if ("/bulk_data".equals(path) && (collection = a.bulkData) != null) {
                a(collection);
                e = this.gv.e(a.bulkData);
            }
            e = false;
        }
        Context context = this.mContext;
        fo.i(context, new MAPAccountManager(context).getAccount());
        int i = e ? 1 : 0;
        int i2 = e ? 1 : 0;
        int i3 = e ? 1 : 0;
        int i4 = e ? 1 : 0;
        return i;
    }

    @FireOsSdk
    public String getDsnOverrideForChildDeviceType() {
        io.dm(TAG);
        return null;
    }

    @FireOsSdk
    public String getOverrideChildDeviceType() {
        io.dm(TAG);
        return null;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final String getType(Uri uri) {
        throw new UnsupportedOperationException("getType currently not supported");
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final Uri insert(Uri uri, ContentValues contentValues) {
        ja.aD(this.mContext);
        bj();
        boolean a = a(uri, contentValues);
        Context context = this.mContext;
        fo.i(context, new MAPAccountManager(context).getAccount());
        if (a) {
            return uri;
        }
        return null;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public boolean onCreate() {
        if (this.mContext == null) {
            this.mContext = getContext();
            return true;
        }
        return true;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        String str4;
        String str5;
        ja.aD(this.mContext);
        SelectionInfo a = a(uri, str, strArr2);
        String path = uri.getPath();
        Cursor cursor = null;
        String str6 = null;
        cursor = null;
        cursor = null;
        cursor = null;
        cursor = null;
        cursor = null;
        cursor = null;
        if ("/map_info".equals(path)) {
            MAPInit.getInstance(this.mContext).initialize();
            hv hvVar = this.gu;
            if (hvVar == null) {
                hvVar = hv.gs();
                this.gu = hvVar;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("map_major_version", Integer.toString(hvVar.qT));
            hashMap.put("map_minor_version", Integer.toString(hvVar.qU));
            Context context = this.mContext;
            if (p.b(context, context.getPackageName())) {
                io.dm(TAG);
                str5 = getOverrideChildDeviceType();
            } else {
                io.dm(TAG);
                str5 = null;
            }
            if (TextUtils.isEmpty(str5)) {
                str5 = ie.ax(this.mContext);
            }
            hashMap.put("current_device_type", str5);
            Context context2 = this.mContext;
            if (p.b(context2, context2.getPackageName())) {
                str6 = getDsnOverrideForChildDeviceType();
            }
            hashMap.put("dsn_override", str6);
            hashMap.put("map_sw_version", Integer.toString(hvVar.qV));
            hashMap.put("map_brazil_version", hvVar.kJ);
            hashMap.put("map_init_version", Integer.toString(dq.G(this.mContext)));
            return ib.a(strArr, hashMap);
        }
        bj();
        if (AppUrl.OOBE_ACCOUNTS.equals(path)) {
            Set<String> accounts = this.gv.getAccounts();
            cursor = a(strArr, (String[]) accounts.toArray(new String[accounts.size()]));
        } else if ("/userdata".equals(path)) {
            String str7 = a.directedId;
            if (str7 != null && (str4 = a.key) != null) {
                cursor = a(strArr, this.gv.b(str7, str4));
            }
        } else if ("/tokens".equals(path)) {
            String str8 = a.directedId;
            if (str8 != null && (str3 = a.key) != null) {
                cursor = a(strArr, this.gv.z(str8, str3));
            }
        } else if ("/device_data".equals(path)) {
            if (a.namespace != null && a.key != null) {
                dh z = dh.z(this.mContext);
                if (TextUtils.isEmpty(this.gv.C(a.namespace, a.key))) {
                    io.i(TAG, String.format(Locale.ENGLISH, "Device data for %s is empty, generate or fetch it.", a.key));
                    z.cM();
                }
                cursor = a(strArr, this.gv.C(a.namespace, a.key));
            }
        } else if ("/all_data".equals(path)) {
            cursor = ib.a(strArr, this.gv.fs());
        } else if ("/all_deleted_data".equals(path)) {
            cursor = ib.a(strArr, this.gv.ft());
        } else if ("/generate_common_info".equals(path)) {
            dh.z(this.mContext).cM();
            cursor = a(strArr, Integer.toString(1));
        }
        Context context3 = this.mContext;
        fo.i(context3, new MAPAccountManager(context3).getAccount());
        return cursor;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        ja.aD(this.mContext);
        bj();
        boolean a = a(uri, contentValues);
        Context context = this.mContext;
        fo.i(context, new MAPAccountManager(context).getAccount());
        return a ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class SelectionInfo {
        private static final SelectionInfo gx = new SelectionInfo(null, null, null, null, null, null, null, null);
        public final Collection<Map<String, String>> bulkData;
        public final String directedId;
        public final String displayName;
        public final String key;
        public final String namespace;
        public final Date timestamp;
        public final Bundle userdata;
        public final String value;

        public SelectionInfo(String str, String str2, String str3, String str4, String str5, String str6, Date date, String str7) {
            this.directedId = str;
            this.key = str2;
            this.value = str3;
            this.namespace = str4;
            this.displayName = str5;
            this.userdata = iu.ds(str6);
            this.timestamp = date;
            this.bulkData = iu.dr(str7);
        }

        public static SelectionInfo parseSelection(ContentValues contentValues) {
            String asString = contentValues.getAsString("directedId");
            String asString2 = contentValues.getAsString("key");
            String asString3 = contentValues.getAsString("value");
            String asString4 = contentValues.getAsString("namespace");
            String asString5 = contentValues.getAsString("display_name");
            String asString6 = contentValues.getAsString("userdata_bundle_key");
            Long asLong = contentValues.getAsLong("timestamp_key");
            return new SelectionInfo(asString, asString2, asString3, asString4, asString5, asString6, asLong == null ? null : new Date(asLong.longValue()), contentValues.getAsString("bulk_data"));
        }

        public static SelectionInfo parseSelection(String str, String[] strArr) {
            String[] strArr2;
            try {
                if (str == null) {
                    return gx;
                }
                if (strArr == null) {
                    strArr2 = new String[0];
                } else {
                    String[] strArr3 = new String[strArr.length];
                    for (int i = 0; i < strArr.length; i++) {
                        strArr3[i] = JSONObject.quote(strArr[i]);
                    }
                    strArr2 = strArr3;
                }
                JSONObject jSONObject = new JSONObject(String.format(str.replace("\"?\"", "%s"), strArr2));
                return new SelectionInfo(ik.a(jSONObject, "directedId", null), ik.a(jSONObject, "key", null), ik.a(jSONObject, "value", null), ik.a(jSONObject, "namespace", null), ik.a(jSONObject, "display_name", null), ik.a(jSONObject, "userdata_bundle_key", null), jj.dH(ik.a(jSONObject, "timestamp_key", null)), ik.a(jSONObject, "bulk_data", null));
            } catch (IllegalFormatException e) {
                String str2 = MAPInformationProvider.TAG;
                io.e(str2, "Format not valid. Error: " + e.getMessage());
                return null;
            } catch (JSONException unused) {
                io.e(MAPInformationProvider.TAG, "Format was not valid JSON");
                return null;
            }
        }
    }

    private void a(Collection<Map<String, String>> collection) {
        for (Map<String, String> map : collection) {
            this.gw.d(jj.dH(map.get("timestamp_key")));
        }
    }

    private SelectionInfo a(Uri uri, String str, String[] strArr) {
        a(uri);
        SelectionInfo parseSelection = SelectionInfo.parseSelection(str, strArr);
        if (parseSelection != null) {
            return parseSelection;
        }
        throw new IllegalArgumentException("Invalid selection");
    }

    private void a(Uri uri) {
        ja.aD(this.mContext);
        if (uri.getAuthority().startsWith(TOKEN_PROVIDER_AUTHORITY_PREFIX)) {
            return;
        }
        throw new IllegalArgumentException("Unknown supported authority " + uri.getAuthority());
    }

    private Cursor a(String[] strArr, String... strArr2) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr2) {
            HashMap hashMap = new HashMap();
            hashMap.put("value", str);
            arrayList.add(hashMap);
        }
        return ib.a(strArr, arrayList);
    }
}
