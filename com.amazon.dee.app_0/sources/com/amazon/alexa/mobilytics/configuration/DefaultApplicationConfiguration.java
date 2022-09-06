package com.amazon.alexa.mobilytics.configuration;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DefaultApplicationConfiguration implements ApplicationConfiguration {
    private static final String TAG = Log.tag(DefaultApplicationConfiguration.class);
    private final String appId;
    private final String packageName;
    private final String title;
    private final String versionCode;
    private final String versionName;

    @Inject
    public DefaultApplicationConfiguration(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        String str;
        String str2;
        String str3;
        PackageInfo packageInfo;
        String str4 = "Unknown";
        Context context = mobilyticsConfiguration.context();
        try {
            PackageManager packageManager = context.getPackageManager();
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            str = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageInfo.packageName, 0));
        } catch (Exception e) {
            e = e;
            str = str4;
        }
        try {
            str2 = packageInfo.packageName;
            try {
                str3 = String.valueOf(packageInfo.versionCode);
            } catch (Exception e2) {
                e = e2;
                str3 = str4;
            }
            try {
                str4 = packageInfo.versionName;
            } catch (Exception e3) {
                e = e3;
                String str5 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to determine package details for: ");
                outline107.append(mobilyticsConfiguration.context().getPackageName());
                Log.w(str5, e, outline107.toString(), new Object[0]);
                this.appId = mobilyticsConfiguration.applicationId();
                this.title = str;
                this.packageName = str2;
                this.versionName = str4;
                this.versionCode = str3;
            }
        } catch (Exception e4) {
            e = e4;
            str2 = str4;
            str3 = str2;
            String str52 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to determine package details for: ");
            outline1072.append(mobilyticsConfiguration.context().getPackageName());
            Log.w(str52, e, outline1072.toString(), new Object[0]);
            this.appId = mobilyticsConfiguration.applicationId();
            this.title = str;
            this.packageName = str2;
            this.versionName = str4;
            this.versionCode = str3;
        }
        this.appId = mobilyticsConfiguration.applicationId();
        this.title = str;
        this.packageName = str2;
        this.versionName = str4;
        this.versionCode = str3;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration
    @NonNull
    public String id() {
        return this.appId;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration
    @NonNull
    public String packageName() {
        return this.packageName;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration
    @NonNull
    public String title() {
        return this.title;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ApplicationConfiguration[");
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("applicationId: ");
        outline1072.append(this.appId);
        outline1072.append("; ");
        outline107.append(outline1072.toString());
        outline107.append("title: " + this.title + "; ");
        outline107.append("packageName: " + this.packageName + "; ");
        outline107.append("versionName: " + this.versionName + "; ");
        outline107.append("versionCode: " + this.versionCode + "; ");
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration
    @NonNull
    public String versionCode() {
        return this.versionCode;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration
    @NonNull
    public String versionName() {
        return this.versionName;
    }
}
