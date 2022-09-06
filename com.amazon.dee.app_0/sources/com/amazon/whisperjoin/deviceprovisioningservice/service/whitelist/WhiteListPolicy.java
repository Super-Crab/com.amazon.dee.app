package com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class WhiteListPolicy {
    private static final String KEY_LAST_REFRESH = "FFSWhiteListPolicy.LastRefresh";
    private static final String KEY_MIN_BATTERY_LEVEL = "FFSWhiteListPolicy.MinBatteryLevel";
    private static final String KEY_POLICY_EXPIRES_AT = "FFSWhiteListPolicy.ExpiresAt";
    private static final String KEY_SCAN_MODE = "FFSWhiteListPolicy.ScanMode";
    private static final String PREFIX = "FFSWhiteListPolicy.";
    private static final String TAG = "WhiteListPolicy";
    private final long mLastRefresh;
    private final int mMinBatteryLevel;
    private final long mPolicyExpiresAt;
    private final ScanningMode mScanningMode;

    public WhiteListPolicy(ScanningMode scanningMode, int i, long j, long j2) {
        if (scanningMode != null) {
            if (i < 0 || i > 100) {
                throw new IllegalArgumentException("minBatteryLevel must be within 0 to 100");
            }
            if (j < 0) {
                throw new IllegalArgumentException("policyExpiresAt must be a positive number");
            }
            if (j2 >= 0) {
                this.mScanningMode = scanningMode;
                this.mMinBatteryLevel = i;
                this.mPolicyExpiresAt = j;
                this.mLastRefresh = j2;
                return;
            }
            throw new IllegalArgumentException("lastRefresh must be a positive number");
        }
        throw new IllegalArgumentException("scanningMode can not be null");
    }

    public static WhiteListPolicy readFromBundle(Bundle bundle) {
        if (bundle != null) {
            try {
                return new WhiteListPolicy(ScanningMode.values()[bundle.getInt(KEY_SCAN_MODE, 0)], bundle.getInt(KEY_MIN_BATTERY_LEVEL, -1), bundle.getLong(KEY_POLICY_EXPIRES_AT, -1L), bundle.getLong(KEY_LAST_REFRESH, -1L));
            } catch (IllegalArgumentException unused) {
                WJLog.d(TAG, "No valid WhiteListPolicy found in bundle");
                return null;
            }
        }
        throw new IllegalArgumentException("bundle can not be null");
    }

    public static WhiteListPolicy readFromSharedPreferences(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            try {
                return new WhiteListPolicy(ScanningMode.values()[sharedPreferences.getInt(KEY_SCAN_MODE, 0)], sharedPreferences.getInt(KEY_MIN_BATTERY_LEVEL, -1), sharedPreferences.getLong(KEY_POLICY_EXPIRES_AT, -1L), sharedPreferences.getLong(KEY_LAST_REFRESH, -1L));
            } catch (IllegalArgumentException unused) {
                WJLog.d(TAG, "No valid WhiteListPolicy found in SharedPreferences");
                return null;
            }
        }
        throw new IllegalArgumentException("sharedPreferences can not be null");
    }

    public static void writeToBundle(WhiteListPolicy whiteListPolicy, Bundle bundle) {
        if (whiteListPolicy != null) {
            if (bundle != null) {
                bundle.putInt(KEY_SCAN_MODE, whiteListPolicy.getScanningMode().ordinal());
                bundle.putInt(KEY_MIN_BATTERY_LEVEL, whiteListPolicy.getMinBatteryLevel());
                bundle.putLong(KEY_POLICY_EXPIRES_AT, whiteListPolicy.getPolicyExpiresAt());
                bundle.putLong(KEY_LAST_REFRESH, whiteListPolicy.getLastRefresh());
                return;
            }
            throw new IllegalArgumentException("bundle can not be null");
        }
        throw new IllegalArgumentException("whiteListPolicy can not be null");
    }

    public static void writeToSharedPreferences(WhiteListPolicy whiteListPolicy, SharedPreferences sharedPreferences) {
        if (whiteListPolicy != null) {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt(KEY_SCAN_MODE, whiteListPolicy.getScanningMode().ordinal());
                edit.putInt(KEY_MIN_BATTERY_LEVEL, whiteListPolicy.getMinBatteryLevel());
                edit.putLong(KEY_POLICY_EXPIRES_AT, whiteListPolicy.getPolicyExpiresAt());
                edit.putLong(KEY_LAST_REFRESH, whiteListPolicy.getLastRefresh());
                edit.apply();
                return;
            }
            throw new IllegalArgumentException("sharedPreferences can not be null");
        }
        throw new IllegalArgumentException("whiteListPolicy can not be null");
    }

    public boolean allowedToScan() {
        return !this.mScanningMode.equals(ScanningMode.PROHIBITED);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WhiteListPolicy.class != obj.getClass()) {
            return false;
        }
        WhiteListPolicy whiteListPolicy = (WhiteListPolicy) obj;
        return this.mMinBatteryLevel == whiteListPolicy.mMinBatteryLevel && this.mPolicyExpiresAt == whiteListPolicy.mPolicyExpiresAt && this.mLastRefresh == whiteListPolicy.mLastRefresh && this.mScanningMode == whiteListPolicy.mScanningMode;
    }

    public long getLastRefresh() {
        return this.mLastRefresh;
    }

    public int getMinBatteryLevel() {
        return this.mMinBatteryLevel;
    }

    public long getPolicyExpiresAt() {
        return this.mPolicyExpiresAt;
    }

    public ScanningMode getScanningMode() {
        return this.mScanningMode;
    }

    public int hashCode() {
        return Objects.hashCode(this.mScanningMode, Integer.valueOf(this.mMinBatteryLevel), Long.valueOf(this.mPolicyExpiresAt), Long.valueOf(this.mLastRefresh));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mScanningMode", this.mScanningMode).add("mMinBatteryLevel", this.mMinBatteryLevel).add("mPolicyExpiresAt", this.mPolicyExpiresAt).add("mLastRefresh", this.mLastRefresh).toString();
    }
}
