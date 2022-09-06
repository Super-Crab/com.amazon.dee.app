package com.amazon.identity.auth.device.utils;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.Scope;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public final class MAPUtils {
    private static final String AMAZON_HOST = ".amazon.";
    private static final String HOST_TYPE = "host.type";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.utils.MAPUtils";
    private static SQLiteDatabase MAPdatabase = null;
    private static final String PATH_AP = "/ap/";
    private static final String PATH_AP_FORGOT_PASSWORD = "/ap/forgotpassword";
    private static final String PATH_GP = "/gp/yourstore/home";
    private static final String PROTOCOL = "http";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum SCOPE_MODIFIER {
        LOCAL,
        REMOTE,
        ALL
    }

    private MAPUtils() throws Exception {
        throw new Exception("This class is not instantiable!");
    }

    public static boolean areScopesLocalAndValid(AppInfo appInfo, String[] strArr) {
        return areScopesValid(appInfo, strArr, SCOPE_MODIFIER.LOCAL);
    }

    public static boolean areScopesRemoteAndValid(AppInfo appInfo, String[] strArr) {
        return areScopesValid(appInfo, strArr, SCOPE_MODIFIER.REMOTE);
    }

    public static boolean areScopesValid(AppInfo appInfo, String[] strArr) {
        return areScopesValid(appInfo, strArr, SCOPE_MODIFIER.ALL);
    }

    public static boolean contains(String[] strArr, String str) {
        if (strArr == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static SQLiteDatabase deleteDatabase(Context context) {
        try {
            MAPLog.d(LOG_TAG, "deleteDatabase so we can create it from scratch");
            boolean deleteDatabase = context.deleteDatabase(DatabaseHelper.MAP_DB_NAME);
            String str = LOG_TAG;
            MAPLog.d(str, "deleteDatabase was successful : " + deleteDatabase);
        } catch (SQLiteException e) {
            String str2 = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("deleteDatabase exception: ");
            outline107.append(e.getMessage());
            MAPLog.d(str2, outline107.toString());
        }
        return MAPdatabase;
    }

    public static String getAppName(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : context.getPackageName());
    }

    public static String getDeviceId() {
        return "some-device-id";
    }

    public static String getHostType(Context context, String str) {
        String str2 = "www";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(HOST_TYPE)) {
                return str2;
            }
            str2 = applicationInfo.metaData.getString(HOST_TYPE);
            String str3 = LOG_TAG;
            MAPLog.pii(str3, "Host Type", "hostType=" + str2 + " package=" + str);
            return str2;
        } catch (PackageManager.NameNotFoundException unused) {
            String str4 = LOG_TAG;
            MAPLog.d(str4, "No host type found in package " + str);
            return str2;
        }
    }

    public static synchronized SQLiteDatabase getMAPdatabase(Context context) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (MAPUtils.class) {
            if (MAPdatabase == null) {
                try {
                    MAPdatabase = new DatabaseHelper(context).getWritableDatabase();
                } catch (SQLiteException unused) {
                    deleteDatabase(context);
                    MAPdatabase = new DatabaseHelper(context).getWritableDatabase();
                }
            }
            sQLiteDatabase = MAPdatabase;
        }
        return sQLiteDatabase;
    }

    public static Set<String> getSetDifference(String[] strArr, String[] strArr2) {
        if (strArr == null) {
            return new HashSet();
        }
        if (strArr2 == null) {
            return new HashSet(Arrays.asList(strArr));
        }
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        hashSet.removeAll(Arrays.asList(strArr2));
        return hashSet;
    }

    public static String getVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to get verison info from app");
            outline107.append(e.getMessage());
            MAPLog.w(str, outline107.toString());
            return CapabilityQueryConstants.TARGET_NOT_AVAILABLE;
        }
    }

    public static boolean isDevo(Context context) {
        if (context == null) {
            return false;
        }
        return getHostType(context, context.getPackageName()).equalsIgnoreCase("development");
    }

    public static boolean isMAPUrl(String str) {
        if (str == null) {
            MAPLog.i(LOG_TAG, "URL is null");
            return false;
        }
        try {
            URL url = new URL(str);
            String protocol = url.getProtocol();
            if (!TextUtils.isEmpty(protocol) && protocol.contains("http")) {
                String host = url.getHost();
                if (!TextUtils.isEmpty(host) && host.contains(AMAZON_HOST)) {
                    String path = url.getPath();
                    boolean isEmpty = TextUtils.isEmpty(path);
                    boolean startsWith = path.startsWith(PATH_AP);
                    boolean equals = path.equals(PATH_GP);
                    boolean equals2 = path.equals(PATH_AP_FORGOT_PASSWORD);
                    String str2 = LOG_TAG;
                    MAPLog.d(str2, " isEmpty=" + isEmpty + "startsWithAP=" + startsWith + "equalsGP=" + equals + "equalsForgotPassword=" + equals2);
                    if (!isEmpty && ((startsWith && !equals2) || equals)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (MalformedURLException unused) {
            String str3 = LOG_TAG;
            MAPLog.pii(str3, "MalformedURLException", " url=" + str);
            return false;
        }
    }

    public static void resetDatabaseInstance() {
        MAPdatabase = null;
    }

    public static String toDelimitedString(String[] strArr, String str) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        int i = 0;
        String str2 = "";
        while (i < strArr.length) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str2);
            outline107.append(strArr[i].trim());
            outline107.append(i == strArr.length + (-1) ? "" : str);
            str2 = outline107.toString();
            i++;
        }
        return str2;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static String[] toStringArray(String str, String str2) {
        if (str == null || str.trim().length() <= 0) {
            return null;
        }
        String trim = str.trim();
        return trim.split("[" + str2 + "]");
    }

    private static boolean areScopesValid(AppInfo appInfo, String[] strArr, SCOPE_MODIFIER scope_modifier) {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("areScopesValid : modifier=");
        outline107.append(scope_modifier.name());
        outline107.append(" scopes=");
        outline107.append(Arrays.toString(strArr));
        MAPLog.i(str, outline107.toString());
        if (strArr != null && strArr.length != 0) {
            if (appInfo != null && appInfo.getAllowedScopes() != null) {
                HashSet hashSet = new HashSet(Arrays.asList(appInfo.getAllowedScopes()));
                String str2 = LOG_TAG;
                MAPLog.i(str2, "allowedScopeSet : " + hashSet);
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    String str3 = strArr[i];
                    if (str3 != null && hashSet.contains(str3)) {
                        if (scope_modifier == SCOPE_MODIFIER.REMOTE && Scope.isLocal(str3)) {
                            String str4 = LOG_TAG;
                            MAPLog.w(str4, "Invalid scope: " + str3 + " is local!");
                            return false;
                        }
                        if (scope_modifier == SCOPE_MODIFIER.LOCAL && !Scope.isLocal(str3)) {
                            String str5 = LOG_TAG;
                            MAPLog.w(str5, "Invalid scope: " + str3 + " is remote!");
                        }
                    } else {
                        String str6 = LOG_TAG;
                        MAPLog.w(str6, "Invalid scope: " + str3 + " (it's either null or not part of the allowed set)");
                        return false;
                    }
                }
                return true;
            }
            MAPLog.w(LOG_TAG, "Scopes are invalid: either appInfo is null or allowedScopes is null");
            return false;
        }
        MAPLog.w(LOG_TAG, "Scopes are invalid: array is either null or empty");
        return false;
    }
}
