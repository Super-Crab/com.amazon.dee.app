package com.amazon.identity.auth.device.framework;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;
import com.amazon.identity.auth.device.dr;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.fm;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.it;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.jn;
import com.amazon.identity.auth.device.mz;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPRuntimePermissionHandler {
    private static final String TAG;
    private static final ConcurrentHashMap<Integer, MAPRuntimePermissionHandler> kj = new ConcurrentHashMap<>();
    private static final Map<String, PermissionAction> kk;
    private static Integer kl;
    private final Integer km;
    private final String kn;
    private final String ko;
    private final String[] kp;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    enum PermissionAction {
        ACTION_READ_MOBILE_NUMBER(1, new String[]{"android.permission.READ_PHONE_STATE"});
        
        final String[] mPermissions;
        final int mRequestId;

        PermissionAction(int i, String[] strArr) {
            this.mRequestId = i;
            this.mPermissions = strArr;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        kk = hashMap;
        hashMap.put("read_mobile_number", PermissionAction.ACTION_READ_MOBILE_NUMBER);
        TAG = MAPRuntimePermissionHandler.class.getName();
    }

    public MAPRuntimePermissionHandler(String str, String str2) {
        PermissionAction permissionAction = kk.get(str);
        this.km = Integer.valueOf(permissionAction.mRequestId);
        this.kp = permissionAction.mPermissions;
        this.ko = str2;
        this.kn = str;
    }

    public static boolean H(Context context) {
        try {
            if (!mz.bb(context)) {
                return false;
            }
            Class.forName("com.google.android.gms.auth.api.phone.SmsRetriever");
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
            String str = TAG;
            "playServiceAvailability: ".concat(String.valueOf(isGooglePlayServicesAvailable));
            io.dm(str);
            if (isGooglePlayServicesAvailable != 0 && isGooglePlayServicesAvailable != 2) {
                return false;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            String str2 = TAG;
            new StringBuilder("versionCode:").append(packageInfo.versionCode);
            io.dm(str2);
            return ((long) packageInfo.versionCode) >= 10200000;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            io.w(TAG, "error on play service check", th);
            return false;
        }
    }

    public static boolean I(Context context) {
        try {
            if (!mz.bb(context)) {
                return false;
            }
            Class.forName("androidx.browser.customtabs.CustomTabsIntent");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean J(Context context) {
        try {
            if (!mz.bb(context)) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            Class.forName("com.google.android.gms.auth.api.credentials.HintRequest");
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
            String str = TAG;
            "playServiceAvailability: ".concat(String.valueOf(isGooglePlayServicesAvailable));
            io.dm(str);
            return isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            io.w(TAG, "error on play service check", th);
            return false;
        }
    }

    private static Integer K(Context context) {
        if (kl == null) {
            kl = it.aB(context);
        }
        return kl;
    }

    public static MAPRuntimePermissionHandler bt(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("callback");
                String string2 = jSONObject.getString("action");
                if (string != null && string2 != null && !string2.equals("auto_phone_verification")) {
                    return new MAPRuntimePermissionHandler(string2, string);
                }
                return null;
            } catch (JSONException e) {
                io.e(TAG, "Unable to parse action json string", e);
                return null;
            }
        }
        return null;
    }

    public static boolean e(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    public static MAPRuntimePermissionHandler i(int i) {
        return kj.get(Integer.valueOf(i));
    }

    public void a(final ed edVar, final dr drVar, final WebView webView, final ej ejVar, final boolean z) {
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject a = MAPRuntimePermissionHandler.this.a(edVar, ejVar);
                MAPRuntimePermissionHandler.this.a(edVar, webView, a, ejVar, z);
                String str = MAPRuntimePermissionHandler.TAG;
                io.i(str, "MAP is going to callback javascript function: " + MAPRuntimePermissionHandler.this.ko);
                String str2 = MAPRuntimePermissionHandler.TAG;
                new StringBuilder("MAP javascript callback data: ").append(a.toString());
                io.dm(str2);
                drVar.a(webView, MAPRuntimePermissionHandler.this.ko, a.toString());
                MAPRuntimePermissionHandler.kj.remove(MAPRuntimePermissionHandler.this.km);
            }
        });
    }

    protected void a(final ed edVar, final WebView webView, JSONObject jSONObject, ej ejVar, boolean z) {
        try {
            if (!TextUtils.equals(jSONObject.getString("result"), "grant")) {
                return;
            }
            final String a = fm.a(edVar, edVar.getPackageName(), ejVar, z);
            if (TextUtils.isEmpty(a)) {
                return;
            }
            ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    io.i(MAPRuntimePermissionHandler.TAG, "Refreshing cookie to indicate latest permission and metadata information.");
                    jn.a(edVar, webView.getUrl(), "map-md", a, "/ap", null, true);
                }
            });
        } catch (JSONException e) {
            io.e(TAG, "JSONException happened. Probably due to no result being set in callback JSON", e);
        }
    }

    @SuppressLint({"NewApi"})
    public void a(Activity activity, dr drVar, WebView webView, ej ejVar, boolean z) {
        String[] strArr;
        String[] strArr2;
        if (kj.putIfAbsent(this.km, this) != null) {
            io.w(TAG, "Permission request is already in flight, do nothing. Request code: " + this.km.toString());
            return;
        }
        kj.put(this.km, this);
        if (a(activity, ejVar) && (strArr = this.kp) != null && strArr.length > 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.kp) {
                if (!e(activity, str)) {
                    arrayList.add(str);
                }
            }
            if (!arrayList.isEmpty()) {
                io.i(TAG, "Some permissions are not granted. Rendering system dialog for the permission");
                activity.requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), this.km.intValue());
                return;
            }
            io.i(TAG, "All requested permissions are already granted. Calling back with success result");
            a(ed.M(activity.getApplicationContext()), drVar, webView, ejVar, z);
            return;
        }
        kj.remove(this.km);
    }

    protected boolean a(Activity activity, ej ejVar) {
        return a((Context) activity, ejVar);
    }

    public static boolean a(Context context, ej ejVar) {
        int i = Build.VERSION.SDK_INT;
        Integer K = K(context);
        if (K == null) {
            io.w(TAG, "Unable to determine target SDK version. Will not show permission dialog.");
            ejVar.bA("MAPRuntimePermissionError:CannotGetBuildTargetVersion");
            return false;
        }
        String str = TAG;
        new StringBuilder("The current apk build target sdk version is:").append(K.toString());
        io.dm(str);
        if (K.intValue() >= 23) {
            return true;
        }
        io.w(TAG, "The app build target sdk version is below 23. Runtime permission is not needed.");
        return false;
    }

    JSONObject a(ed edVar, ej ejVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", this.kn);
            String[] strArr = this.kp;
            int length = strArr.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = true;
                    break;
                } else if (!e(edVar, strArr[i])) {
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                if (TextUtils.equals(this.kn, "read_mobile_number")) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject.put("result", "error");
                    io.i(TAG, "Shouldn't get phone number from the device.");
                    if (!TextUtils.isEmpty(fm.b(edVar, ejVar))) {
                        jSONObject2.put("country_code", fm.b(edVar, ejVar));
                    } else {
                        io.e(TAG, "Can't get sim country iso from the device.");
                        ejVar.bA("MAPRuntimePermissionError:CannotGetCountryISO");
                    }
                    jSONObject.put("extra_data", jSONObject2);
                } else {
                    io.e(TAG, "MAP can't understand the action: " + this.kn);
                    jSONObject.put("result", "error");
                }
            } else {
                jSONObject.put("result", "deny");
                if (TextUtils.equals(this.kn, "read_mobile_number")) {
                    ejVar.bA("MAPRuntimePermission:ReadPhoneStateDeny");
                }
            }
        } catch (JSONException e) {
            io.e(TAG, "JSONException while building the callback json", e);
        }
        return jSONObject;
    }
}
