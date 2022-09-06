package com.amazon.alexa.mobilytics.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public final class Utils {
    private static final String APP_VERSION = "AppVersion";
    private static final String APP_VERSION_COUNTRY_CODE = "AppVersion:CountryCode";
    private static final String APP_VERSION_COUNTRY_CODE_MARKETPLACE_ID_CODE = "AppVersion:CountryCode:MarketplaceIDCode";
    private static final String APP_VERSION_MARKETPLACE_ID_CODE = "AppVersion:MarketplaceIDCode";
    private static final String APP_VERSION_OS_TYPE_CODE = "AppVersion:OSType";
    private static final String APP_VERSION_OS_TYPE_COUNTRY_CODE = "AppVersion:OSType:CountryCode";
    private static final String APP_VERSION_OS_TYPE_COUNTRY_CODE_MARKETPLACE_ID_CODE = "AppVersion:OSType:CountryCode:MarketplaceIDCode";
    private static final String APP_VERSION_OS_TYPE_MARKETPLACE_ID_CODE = "AppVersion:OSType:MarketplaceIDCode";
    private static final String COUNTRY_CODE = "CountryCode";
    private static final String COUNTRY_CODE_MARKETPLACE_ID_CODE = "CountryCode:MarketplaceIDCode";
    private static final String MARKETPLACE_ID_CODE = "MarketplaceIDCode";
    private static final String OS_TYPE = "OSType";
    private static final String OS_TYPE_COUNTRY_CODE = "OSType:CountryCode";
    private static final String OS_TYPE_COUNTRY_CODE_MARKETPLACE_ID_CODE = "OSType:CountryCode:MarketplaceIDCode";
    private static final String OS_TYPE_MARKETPLACE_ID_CODE = "OSType:MarketplaceIDCode";
    private static final String PIVOT_ATTRIBUTE_NULL_VALUE = "NULL";
    private static final String SEPARATOR = ":";
    private static final String TAG = Log.tag(Utils.class);

    private Utils() {
    }

    public static int compareDottedStrings(String str, String str2) {
        String[] split = str.split(ArcusConfig.PATH_SEPARATOR);
        String[] split2 = str2.split(ArcusConfig.PATH_SEPARATOR);
        int i = 0;
        while (true) {
            if (i < split.length || i < split2.length) {
                if (i < split.length && i < split2.length) {
                    if (Integer.parseInt(split[i]) < Integer.parseInt(split2[i])) {
                        return -1;
                    }
                    if (Integer.parseInt(split[i]) > Integer.parseInt(split2[i])) {
                        return 1;
                    }
                } else if (i < split.length) {
                    if (Integer.parseInt(split[i]) != 0) {
                        return 1;
                    }
                } else if (i < split2.length && Integer.parseInt(split2[i]) != 0) {
                    return -1;
                }
                i++;
            } else {
                return 0;
            }
        }
    }

    public static Map<String, String> computeMwsPivots(@Nullable String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        String str5 = "NULL";
        String str6 = !TextUtils.isEmpty(str) ? str : str5;
        String str7 = !TextUtils.isEmpty(str2) ? str2 : str5;
        if (!TextUtils.isEmpty(str3)) {
            str5 = str3;
        }
        HashMap hashMap = new HashMap(computeOneDimensionalPivots(str, str2, str3, str4));
        hashMap.put("AppVersion:MarketplaceIDCode", str5 + ":" + str6);
        hashMap.put("AppVersion:CountryCode", GeneratedOutlineSupport1.outline92(new StringBuilder(), str5, ":", str7));
        hashMap.put("CountryCode:MarketplaceIDCode", str7 + ":" + str6);
        hashMap.put("AppVersion:OSType", str5 + ":" + str4);
        hashMap.put("OSType:CountryCode", GeneratedOutlineSupport1.outline92(new StringBuilder(), str4, ":", str7));
        hashMap.put("OSType:MarketplaceIDCode", str4 + ":" + str6);
        StringBuilder sb = new StringBuilder();
        sb.append(str5);
        hashMap.put("AppVersion:CountryCode:MarketplaceIDCode", GeneratedOutlineSupport1.outline93(sb, ":", str7, ":", str6));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str5);
        sb2.append(":");
        hashMap.put("AppVersion:OSType:CountryCode", GeneratedOutlineSupport1.outline92(sb2, str4, ":", str7));
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str5);
        sb3.append(":");
        hashMap.put("AppVersion:OSType:MarketplaceIDCode", GeneratedOutlineSupport1.outline92(sb3, str4, ":", str6));
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str4);
        sb4.append(":");
        hashMap.put("OSType:CountryCode:MarketplaceIDCode", GeneratedOutlineSupport1.outline92(sb4, str7, ":", str6));
        StringBuilder sb5 = new StringBuilder();
        sb5.append(str5);
        sb5.append(":");
        GeneratedOutlineSupport1.outline181(sb5, str4, ":", str7, ":");
        sb5.append(str6);
        hashMap.put("AppVersion:OSType:CountryCode:MarketplaceIDCode", sb5.toString());
        return hashMap;
    }

    public static Map<String, String> computeOneDimensionalPivots(@Nullable String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        if (TextUtils.isEmpty(str)) {
            str = "NULL";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "NULL";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "NULL";
        }
        HashMap outline134 = GeneratedOutlineSupport1.outline134("AppVersion", str3, "CountryCode", str2);
        outline134.put("MarketplaceIDCode", str);
        outline134.put("OSType", str4);
        return outline134;
    }

    @Nullable
    public static File createDirectory(@NonNull Context context, @NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String packageName = context.getPackageName();
        String processName = getProcessName(context);
        if (!processName.equals(packageName)) {
            str = GeneratedOutlineSupport1.outline72(str, processName);
        }
        File file = new File(str);
        if (!file.isAbsolute()) {
            StringBuilder sb = new StringBuilder();
            sb.append(context.getFilesDir());
            file = new File(GeneratedOutlineSupport1.outline91(sb, File.separator, str));
        }
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        return file;
    }

    @NonNull
    public static String getProcessName(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "";
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    public static String getStackTrace(StackTraceElement[] stackTraceElementArr) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append(stackTraceElement.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static boolean isAppOnForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCommsMobilyticsMetric(String str) {
        return Constants.COMMS_DROP_IN_BUTTON.equals(str) || Constants.COMMS_MESSAGE_BUTTON.equals(str) || Constants.COMMS_CALL_BUTTON.equals(str) || Constants.COMMS_ANNOUNCEMENT_BUTTON.equals(str) || Constants.COMMS_SHARE_BUTTON.equals(str);
    }

    public static String trimOrPadString(String str, int i, char c) {
        if (i < 0) {
            i = 0;
        }
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() > i - 1) {
            stringBuffer.append(str.substring(str.length() - i));
        } else {
            for (int i2 = 0; i2 < i - str.length(); i2++) {
                stringBuffer.append(c);
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static String trimString(String str, int i) {
        if (i < 0) {
            i = 0;
        }
        if (str == null) {
            str = "";
        }
        return str.length() > i + (-1) ? str.substring(str.length() - i) : str;
    }
}
