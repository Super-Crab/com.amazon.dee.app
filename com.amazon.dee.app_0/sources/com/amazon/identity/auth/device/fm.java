package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
import com.amazon.identity.frc.FrcCookiesManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class fm {
    private static final String TAG = "com.amazon.identity.auth.device.fm";

    private fm() {
    }

    public static void a(String str, JSONObject jSONObject) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("frc", str);
            jSONObject.put("user_context_map", jSONObject2);
        }
    }

    public static JSONObject b(String str, String str2, ej ejVar) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            if (ejVar != null) {
                ejVar.bA("DeviceMetadata:RequiredParameterNull:DeviceTypeAndDSN");
            }
            return null;
        } else if (TextUtils.isEmpty(str)) {
            if (ejVar != null) {
                ejVar.bA("DeviceMetadata:RequiredParameterNull:DeviceType");
            }
            return null;
        } else if (TextUtils.isEmpty(str2)) {
            if (ejVar != null) {
                ejVar.bA("DeviceMetadata:RequiredParameterNull:DSN");
            }
            return null;
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("device_os_family", "android");
                jSONObject.put("device_type", str);
                jSONObject.put("device_serial", str2);
                jSONObject.putOpt("manufacturer", Build.MANUFACTURER);
                jSONObject.putOpt("model", Build.MODEL);
                jSONObject.putOpt("os_version", Integer.toString(Build.VERSION.SDK_INT));
                jSONObject.putOpt(AMPDInformationProvider.DEVICE_PRODUCT_KEY, Build.PRODUCT);
                return jSONObject;
            } catch (JSONException e) {
                io.e(TAG, "JSONException happened when trying to build device metadata", e);
                return null;
            } catch (Exception e2) {
                io.e(TAG, "An unexpected error occurred while building the device metadata JSONObject");
                mq.b("MetadataCollection:UnexpectedException", "ExceptionType:" + e2.getClass().getName());
                return null;
            }
        }
    }

    static String bP(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 2);
        } catch (UnsupportedEncodingException e) {
            io.e(TAG, "This platform doesn't support UTF-8, this should never happen.", e);
            return null;
        }
    }

    private static JSONObject c(ed edVar, ej ejVar) {
        try {
            Boolean bool = Boolean.FALSE;
            JSONObject jSONObject = new JSONObject();
            if (bool.booleanValue()) {
                jSONObject.putOpt("auto_pv", 1);
            } else {
                jSONObject.putOpt("auto_pv", 0);
            }
            if (MAPRuntimePermissionHandler.H(edVar)) {
                jSONObject.putOpt("auto_pv_with_smsretriever", 1);
            } else {
                jSONObject.putOpt("auto_pv_with_smsretriever", 0);
            }
            if (MAPRuntimePermissionHandler.J(edVar)) {
                jSONObject.putOpt("smartlock_supported", 1);
            } else {
                jSONObject.putOpt("smartlock_supported", 0);
            }
            if (MAPRuntimePermissionHandler.a((Context) edVar, ejVar)) {
                jSONObject.put("permission_runtime_grant", 2);
            } else {
                jSONObject.put("permission_runtime_grant", 0);
            }
            return jSONObject;
        } catch (JSONException unused) {
            io.e(TAG, "JSONException when building app info JSON for map-md cookie");
            return null;
        }
    }

    private static JSONObject ex() {
        String gt = hv.gt();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("software_version", gt);
            return jSONObject;
        } catch (JSONException unused) {
            io.e(TAG, "JSONException when building device registration JSON for map-md cookie");
            return null;
        }
    }

    public static String h(Context context, String str) {
        return new FrcCookiesManager(context).getFrcCookies(str);
    }

    public static String a(ed edVar, String str, ej ejVar, boolean z) {
        return a(edVar, str, ejVar, z ? null : UserDictionaryHelper.af(edVar));
    }

    protected static String a(ed edVar, String str, ej ejVar, UserDictionaryHelper userDictionaryHelper) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = null;
            jSONObject.putOpt("device_data", null);
            jSONObject.putOpt("device_registration_data", ex());
            jSONObject.putOpt("app_identifier", b(edVar, str));
            jSONObject.putOpt("app_info", c(edVar, ejVar));
            if (userDictionaryHelper != null) {
                List<String> gl = userDictionaryHelper.gl();
                if (!hz.isEmpty(gl)) {
                    jSONArray = new JSONArray((Collection) gl);
                }
                if (jSONArray != null && jSONArray.length() > 0) {
                    jSONObject.putOpt("device_user_dictionary", jSONArray);
                }
            }
        } catch (JSONException unused) {
            io.e(TAG, "JSONException when adding data to map-md cookie");
        }
        String jSONObject2 = jSONObject.toString();
        GeneratedOutlineSupport1.outline161(jSONObject2, "map-md cookies: ", TAG);
        String bP = bP(jSONObject2);
        GeneratedOutlineSupport1.outline161(bP, "Base64 encoded map-md cookies: ", TAG);
        return bP;
    }

    @SuppressLint({"ServiceCast"})
    public static String b(ed edVar, ej ejVar) {
        try {
            String dY = ((ei) edVar.getSystemService("sso_telephony_service")).dY();
            if (!TextUtils.isEmpty(dY)) {
                return dY;
            }
            return null;
        } catch (Exception e) {
            String str = TAG;
            io.a(str, ejVar, "Unknown exception happened why try to read country ISO", "SimCountryISOCollection:Exception:" + e.getClass().getName(), e);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    private static JSONObject b(ed edVar, String str) {
        String str2;
        String str3;
        String str4;
        JSONArray jSONArray;
        Object obj;
        String str5 = hv.gs().kJ;
        try {
            PackageInfo a = ek.a(str, 64, edVar.getPackageManager());
            if (a != null) {
                str3 = a.versionName;
                try {
                    str4 = Integer.toString(a.versionCode);
                    try {
                        Signature[] signatureArr = a.signatures;
                        jSONArray = new JSONArray();
                        for (Signature signature : signatureArr) {
                            try {
                                jSONArray.put(signature == null ? null : jc.i(MessageDigest.getInstance("SHA-256").digest(signature.toByteArray())));
                            } catch (PackageManager.NameNotFoundException unused) {
                                str2 = null;
                            } catch (SecurityException unused2) {
                                str2 = null;
                            } catch (GeneralSecurityException unused3) {
                                str2 = null;
                            }
                        }
                        str2 = fl.a(a);
                    } catch (PackageManager.NameNotFoundException unused4) {
                        str2 = null;
                        jSONArray = 0;
                    } catch (SecurityException unused5) {
                        str2 = null;
                        jSONArray = 0;
                    } catch (GeneralSecurityException unused6) {
                        str2 = null;
                        jSONArray = 0;
                    }
                    try {
                        String str6 = TAG;
                        "sms app hash = ".concat(String.valueOf(str2));
                        io.dm(str6);
                        obj = jSONArray;
                    } catch (PackageManager.NameNotFoundException unused7) {
                        io.e(TAG, "NameNotFoundException when building app identifier JSON for map-md cookie");
                        obj = jSONArray;
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.putOpt("package", str);
                        jSONObject.putOpt("SHA-256", obj);
                        jSONObject.putOpt("app_version", str4);
                        jSONObject.putOpt("app_version_name", str3);
                        jSONObject.putOpt("app_sms_hash", str2);
                        jSONObject.putOpt("map_version", str5);
                        return jSONObject;
                    } catch (SecurityException unused8) {
                        io.e(TAG, "SecurityException when building app identifier JSON for map-md cookie");
                        obj = jSONArray;
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.putOpt("package", str);
                        jSONObject2.putOpt("SHA-256", obj);
                        jSONObject2.putOpt("app_version", str4);
                        jSONObject2.putOpt("app_version_name", str3);
                        jSONObject2.putOpt("app_sms_hash", str2);
                        jSONObject2.putOpt("map_version", str5);
                        return jSONObject2;
                    } catch (GeneralSecurityException unused9) {
                        io.e(TAG, "GeneralSecurityException when building app identifier JSON for map-md cookie");
                        obj = jSONArray;
                        JSONObject jSONObject22 = new JSONObject();
                        jSONObject22.putOpt("package", str);
                        jSONObject22.putOpt("SHA-256", obj);
                        jSONObject22.putOpt("app_version", str4);
                        jSONObject22.putOpt("app_version_name", str3);
                        jSONObject22.putOpt("app_sms_hash", str2);
                        jSONObject22.putOpt("map_version", str5);
                        return jSONObject22;
                    }
                } catch (PackageManager.NameNotFoundException unused10) {
                    str2 = null;
                    str4 = null;
                    jSONArray = str4;
                    io.e(TAG, "NameNotFoundException when building app identifier JSON for map-md cookie");
                    obj = jSONArray;
                    JSONObject jSONObject222 = new JSONObject();
                    jSONObject222.putOpt("package", str);
                    jSONObject222.putOpt("SHA-256", obj);
                    jSONObject222.putOpt("app_version", str4);
                    jSONObject222.putOpt("app_version_name", str3);
                    jSONObject222.putOpt("app_sms_hash", str2);
                    jSONObject222.putOpt("map_version", str5);
                    return jSONObject222;
                } catch (SecurityException unused11) {
                    str2 = null;
                    str4 = null;
                    jSONArray = str4;
                    io.e(TAG, "SecurityException when building app identifier JSON for map-md cookie");
                    obj = jSONArray;
                    JSONObject jSONObject2222 = new JSONObject();
                    jSONObject2222.putOpt("package", str);
                    jSONObject2222.putOpt("SHA-256", obj);
                    jSONObject2222.putOpt("app_version", str4);
                    jSONObject2222.putOpt("app_version_name", str3);
                    jSONObject2222.putOpt("app_sms_hash", str2);
                    jSONObject2222.putOpt("map_version", str5);
                    return jSONObject2222;
                } catch (GeneralSecurityException unused12) {
                    str2 = null;
                    str4 = null;
                    jSONArray = str4;
                    io.e(TAG, "GeneralSecurityException when building app identifier JSON for map-md cookie");
                    obj = jSONArray;
                    JSONObject jSONObject22222 = new JSONObject();
                    jSONObject22222.putOpt("package", str);
                    jSONObject22222.putOpt("SHA-256", obj);
                    jSONObject22222.putOpt("app_version", str4);
                    jSONObject22222.putOpt("app_version_name", str3);
                    jSONObject22222.putOpt("app_sms_hash", str2);
                    jSONObject22222.putOpt("map_version", str5);
                    return jSONObject22222;
                }
            } else {
                str2 = null;
                str3 = null;
                str4 = null;
                obj = null;
            }
        } catch (PackageManager.NameNotFoundException unused13) {
            str2 = null;
            str3 = null;
            str4 = null;
        } catch (SecurityException unused14) {
            str2 = null;
            str3 = null;
            str4 = null;
        } catch (GeneralSecurityException unused15) {
            str2 = null;
            str3 = null;
            str4 = null;
        }
        JSONObject jSONObject222222 = new JSONObject();
        try {
            jSONObject222222.putOpt("package", str);
            jSONObject222222.putOpt("SHA-256", obj);
            jSONObject222222.putOpt("app_version", str4);
            jSONObject222222.putOpt("app_version_name", str3);
            jSONObject222222.putOpt("app_sms_hash", str2);
            jSONObject222222.putOpt("map_version", str5);
            return jSONObject222222;
        } catch (JSONException unused16) {
            io.e(TAG, "JSONException when building app identifier JSON for map-md cookie");
            return null;
        }
    }
}
