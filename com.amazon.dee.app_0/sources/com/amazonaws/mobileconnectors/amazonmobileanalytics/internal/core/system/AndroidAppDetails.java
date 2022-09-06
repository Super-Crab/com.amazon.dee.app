package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
@Deprecated
/* loaded from: classes13.dex */
public class AndroidAppDetails implements AppDetails {
    private static final String TAG = "AndroidAppDetails";
    private String appId;
    private String appTitle;
    private Context applicationContext;
    private String packageName;
    private String versionCode;
    private String versionName;

    public AndroidAppDetails(Context context, String str) {
        this.applicationContext = context.getApplicationContext();
        try {
            PackageManager packageManager = this.applicationContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.applicationContext.getPackageName(), 0);
            this.appTitle = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageInfo.packageName, 0));
            this.packageName = packageInfo.packageName;
            this.versionCode = String.valueOf(packageInfo.versionCode);
            this.versionName = packageInfo.versionName;
            this.appId = str;
        } catch (PackageManager.NameNotFoundException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to get details for package ");
            outline107.append(this.applicationContext.getPackageName());
            Log.w(TAG, outline107.toString());
            this.appTitle = "Unknown";
            this.packageName = "Unknown";
            this.versionCode = "Unknown";
            this.versionName = "Unknown";
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AppDetails
    public String getAppId() {
        return this.appId;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AppDetails
    public String getAppTitle() {
        return this.appTitle;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AppDetails
    public String packageName() {
        return this.packageName;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AppDetails
    public String versionCode() {
        return this.versionCode;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AppDetails
    public String versionName() {
        return this.versionName;
    }

    public AndroidAppDetails(String str, String str2, String str3, String str4, String str5) {
        this.packageName = str;
        this.versionCode = str2;
        this.versionName = str3;
        this.appTitle = str4;
        this.appId = str5;
    }
}
