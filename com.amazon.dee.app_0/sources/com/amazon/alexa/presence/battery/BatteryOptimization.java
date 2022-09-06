package com.amazon.alexa.presence.battery;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.utils.DevicePlatformUtil;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.dee.app.metrics.MetricsServiceV2;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class BatteryOptimization {
    private static final String TAG = Presence.tag();
    private final Context mContext;
    private final MetricsServiceV2 mMetricsServiceV2;
    private final PowerManager mPowerManager;

    @Inject
    public BatteryOptimization(Context context, PowerManager powerManager, MetricsServiceV2 metricsServiceV2) {
        this.mContext = context;
        this.mPowerManager = powerManager;
        this.mMetricsServiceV2 = metricsServiceV2;
    }

    @RequiresApi(api = 23)
    protected boolean checkBatteryOptimization(String str) {
        return !this.mPowerManager.isIgnoringBatteryOptimizations(str);
    }

    public boolean isBatteryOptimized() {
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.CHECK_BATTERY_OPTIMIZATION, "");
        if (!DevicePlatformUtil.isAndroidMOrLater()) {
            return false;
        }
        MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.CHECK_BATTERY_OPTIMIZATION, "");
        return checkBatteryOptimization(this.mContext.getPackageName());
    }

    public void requestDisable() {
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.REQUEST_DISABLE, "");
        if (isBatteryOptimized() && DevicePlatformUtil.isAndroidMOrLater()) {
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.REQUEST_DISABLE, "");
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS");
            this.mContext.startActivity(intent);
        }
    }
}
