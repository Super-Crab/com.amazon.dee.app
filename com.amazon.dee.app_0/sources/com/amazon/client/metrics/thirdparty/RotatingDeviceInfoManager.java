package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import java.util.Map;
/* loaded from: classes11.dex */
public class RotatingDeviceInfoManager extends OverridingDeviceInfoManager {
    private static final long DEFAULT_EXPIRATION_PERIOD_MILLIS = 86400000;
    private static final String LAST_ROTATION_TIME_KEY = "lastDSNRotationTime";
    private long mExpirationPeriodMillis;

    public RotatingDeviceInfoManager(Context context, DeviceUtil deviceUtil, String str, String str2, String str3) {
        super(context, deviceUtil, str, str2, str3, false);
        this.mExpirationPeriodMillis = 86400000L;
    }

    private void ensureSharedPrefsRotationUpToDate() {
        if (System.currentTimeMillis() - getLastRotationTime() > this.mExpirationPeriodMillis) {
            this.mCachedDSN = null;
            SharedPreferences.Editor edit = this.mSharedPrefs.edit();
            edit.remove(this.mSharedPrefsDSNKey);
            edit.remove(this.mSharedPrefsSessionIDKey);
            edit.remove(this.mSharedPrefsCustomerIDKey);
            edit.putLong(LAST_ROTATION_TIME_KEY, System.currentTimeMillis());
            edit.apply();
        }
    }

    private long getLastRotationTime() {
        return this.mSharedPrefs.getLong(LAST_ROTATION_TIME_KEY, 0L);
    }

    @Override // com.amazon.client.metrics.thirdparty.OverridingDeviceInfoManager, com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getCustomerID() {
        ensureSharedPrefsRotationUpToDate();
        return super.getCustomerID();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.client.metrics.thirdparty.OverridingDeviceInfoManager, com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getDeviceSerialNumber() {
        ensureSharedPrefsRotationUpToDate();
        return super.getDeviceSerialNumber();
    }

    @Override // com.amazon.client.metrics.thirdparty.OverridingDeviceInfoManager, com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getSessionID() {
        ensureSharedPrefsRotationUpToDate();
        return super.getSessionID();
    }

    public void setExpirationPeriodMillis(long j) {
        this.mExpirationPeriodMillis = j;
    }

    public RotatingDeviceInfoManager(Context context, DeviceUtil deviceUtil, Map<String, String> map, String str, String str2, String str3) {
        super(context, deviceUtil, map, str, str2, str3, false);
    }
}
