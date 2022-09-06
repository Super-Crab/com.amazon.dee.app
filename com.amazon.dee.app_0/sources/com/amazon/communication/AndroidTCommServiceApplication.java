package com.amazon.communication;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.android.codahalemetricreporter.StandardPerfMetrics;
import com.amazon.communication.time.AndroidTimeSource;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPInit;
import com.codahale.metrics.SharedMetricRegistries;
/* loaded from: classes12.dex */
public class AndroidTCommServiceApplication extends Application {
    private static final DPLogger log = new DPLogger("TComm.AndroidTCommServiceApplication");

    @Override // android.app.Application
    public void onCreate() {
        int i;
        super.onCreate();
        String str = null;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            str = packageInfo.versionName;
            i = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            i = -1;
        }
        log.info("onCreate", "creating application object", ModelTransformer.KEY_FIRMWARE_VERSION_NAME, str, "versionCode", Integer.valueOf(i));
        StandardPerfMetrics.add(SharedMetricRegistries.getOrCreate(RouteName.MAIN));
        GlobalTimeSource.INSTANCE = AndroidTimeSource.INSTANCE;
        MAPInit.getInstance(this).initialize();
        startService(new Intent(this, AndroidTCommService.class));
    }
}
