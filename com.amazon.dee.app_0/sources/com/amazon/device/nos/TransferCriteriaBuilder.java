package com.amazon.device.nos;

import android.content.ComponentName;
import com.amazon.device.nos.TransferCriteria;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class TransferCriteriaBuilder {
    public static final long REPEAT_INTERVAL_12_HOURS = 43200000;
    public static final long REPEAT_INTERVAL_24_HOURS = 86400000;
    public static final long REPEAT_INTERVAL_HOUR = 3600000;
    protected ComponentName mBuilderComponentName = null;
    protected long mBuilderDataSizeKB = TransferCriteria.DataSizeBucket.LARGE.getLowerLimit();
    protected int mBuilderRegistrationId = -1;
    protected int mBuilderNetworkType = 0;
    protected long mBuilderMinTransferDelayMillis = 0;
    protected long mBuilderMaxTransferDelayMillis = 86400000;
    protected boolean mRequireBatteryCharging = false;
    protected boolean mRequireDeviceIdle = false;
    protected long mBuilderRepeatIntervalMillis = 0;

    public TransferCriteria build() {
        if (this.mBuilderComponentName != null) {
            if (this.mBuilderMinTransferDelayMillis <= this.mBuilderMaxTransferDelayMillis) {
                if (this.mBuilderNetworkType == 0) {
                    this.mBuilderNetworkType = 1;
                }
                return new TransferCriteria(this);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("minTransferDelay ");
            outline107.append(this.mBuilderMinTransferDelayMillis);
            outline107.append(" should not be greater than maxTransferDelayMillis ");
            outline107.append(this.mBuilderMaxTransferDelayMillis);
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new IllegalArgumentException("Component name must not be null.");
    }

    public TransferCriteriaBuilder setAllowAnyNetwork(boolean z) {
        if (z) {
            setAllowRoaming();
        }
        return setAllowWifi().setAllowMobile();
    }

    public TransferCriteriaBuilder setAllowMobile() {
        this.mBuilderNetworkType |= 2;
        return this;
    }

    public TransferCriteriaBuilder setAllowRoaming() {
        setAllowMobile();
        this.mBuilderNetworkType |= 4;
        return this;
    }

    public TransferCriteriaBuilder setAllowWifi() {
        this.mBuilderNetworkType |= 1;
        return this;
    }

    public TransferCriteriaBuilder setBatteryChargingRequired(boolean z) {
        this.mRequireBatteryCharging = z;
        return this;
    }

    public TransferCriteriaBuilder setComponentName(ComponentName componentName) {
        if (componentName != null) {
            this.mBuilderComponentName = componentName;
            return this;
        }
        throw new IllegalArgumentException("Component name must not be null.");
    }

    public TransferCriteriaBuilder setDataSizeKB(long j) {
        if (j >= 1) {
            this.mBuilderDataSizeKB = j;
            return this;
        }
        throw new IllegalArgumentException("dataSizeKB must be greater than 0.");
    }

    public TransferCriteriaBuilder setDeviceIdleRequired(boolean z) {
        this.mRequireDeviceIdle = z;
        return this;
    }

    public TransferCriteriaBuilder setMaxTransferDelayMillis(long j) {
        if (j >= 5000) {
            if (j <= 604800000) {
                if (this.mBuilderRepeatIntervalMillis == 0) {
                    this.mBuilderMaxTransferDelayMillis = j;
                    return this;
                }
                throw new IllegalArgumentException("Cannot set both max delay and repeat interval.");
            }
            throw new IllegalArgumentException("Max transfer delay must not be larger than 604800000 ms.");
        }
        throw new IllegalArgumentException("Max transfer delay must be at least 5000 ms.");
    }

    public TransferCriteriaBuilder setMinTransferDelayMillis(long j) {
        if (j == 0) {
            this.mBuilderMinTransferDelayMillis = 0L;
            return this;
        } else if (j < 5000) {
            throw new IllegalArgumentException("Min transfer delay must be at least 5000 ms.");
        } else {
            if (j <= 604800000) {
                if (this.mBuilderRepeatIntervalMillis == 0) {
                    this.mBuilderMinTransferDelayMillis = j;
                    return this;
                }
                throw new IllegalArgumentException("Cannot set both min delay and repeat interval.");
            }
            throw new IllegalArgumentException("Min transfer delay must not be larger than 604800000 ms.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransferCriteriaBuilder setNetworkType(int i) {
        this.mBuilderNetworkType = i;
        int i2 = this.mBuilderNetworkType;
        if ((i2 & 1) == 1 || (i2 & 2) == 2) {
            return this;
        }
        throw new IllegalArgumentException("Either wifi or mobile must be specified as network type.");
    }

    public TransferCriteriaBuilder setRegistrationId(int i) {
        this.mBuilderRegistrationId = i;
        return this;
    }

    public TransferCriteriaBuilder setRepeatIntervalMillis(long j) {
        if (j == 0) {
            this.mBuilderRepeatIntervalMillis = 0L;
            return this;
        } else if (j < 5000) {
            throw new IllegalArgumentException("Repeat interval must be at least 5000 ms.");
        } else {
            if (j <= 604800000) {
                this.mBuilderRepeatIntervalMillis = j;
                this.mBuilderMinTransferDelayMillis = 0L;
                this.mBuilderMaxTransferDelayMillis = j;
                return this;
            }
            throw new IllegalArgumentException("Repeat interval must not be larger than 604800000 ms.");
        }
    }
}
