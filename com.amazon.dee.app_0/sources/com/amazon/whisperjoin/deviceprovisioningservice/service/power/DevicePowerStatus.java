package com.amazon.whisperjoin.deviceprovisioningservice.service.power;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DevicePowerStatus {
    private static final String TAG = "DevicePowerStatus";
    private final boolean mAcChargePlug;
    private final double mBatteryPercentage;
    private final boolean mIsCharging;
    private final boolean mUsbChargePlug;

    public DevicePowerStatus(double d, boolean z, boolean z2, boolean z3) {
        if (d < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR || d > 1.0d) {
            String str = TAG;
            WJLog.w(str, "batteryPercentage outside of expected range: " + d);
        }
        this.mBatteryPercentage = d;
        this.mIsCharging = z;
        this.mUsbChargePlug = z2;
        this.mAcChargePlug = z3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DevicePowerStatus.class != obj.getClass()) {
            return false;
        }
        DevicePowerStatus devicePowerStatus = (DevicePowerStatus) obj;
        return this.mBatteryPercentage == devicePowerStatus.mBatteryPercentage && this.mIsCharging == devicePowerStatus.mIsCharging && this.mUsbChargePlug == devicePowerStatus.mUsbChargePlug && this.mAcChargePlug == devicePowerStatus.mAcChargePlug;
    }

    public double getBatteryPercentage() {
        return this.mBatteryPercentage;
    }

    public int hashCode() {
        return Objects.hashCode(Double.valueOf(this.mBatteryPercentage), Boolean.valueOf(this.mIsCharging), Boolean.valueOf(this.mUsbChargePlug), Boolean.valueOf(this.mAcChargePlug));
    }

    public boolean isAcChargePlug() {
        return this.mAcChargePlug;
    }

    public boolean isCharging() {
        return this.mIsCharging;
    }

    public boolean isUsbChargePlug() {
        return this.mUsbChargePlug;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mBatteryPercentage", this.mBatteryPercentage).add("mIsCharging", this.mIsCharging).add("mUsbChargePlug", this.mUsbChargePlug).add("mAcChargePlug", this.mAcChargePlug).toString();
    }
}
