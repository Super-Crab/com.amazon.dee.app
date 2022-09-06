package com.amazon.alexa.client.metrics.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AppInformation {
    @VisibleForTesting
    public static final String NULL = "NULL";
    private static final String TAG = "AppInformation";
    private String hostAppVersion;
    private final PackageManager packageManager;
    private final String packageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public AppInformation(Context context, PackageManager packageManager) {
        this(context.getPackageName(), packageManager);
    }

    public synchronized String getHostAppVersion() {
        if (this.hostAppVersion == null) {
            try {
                this.hostAppVersion = this.packageManager.getPackageInfo(this.packageName, 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                String str = TAG;
                Log.e(str, "Could not find package: " + this.packageName, e);
                return NULL;
            }
        }
        return this.hostAppVersion;
    }

    @VisibleForTesting
    public AppInformation(String str, PackageManager packageManager) {
        this.packageName = str;
        this.packageManager = packageManager;
    }
}
