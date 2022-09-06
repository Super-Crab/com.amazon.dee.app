package com.amazon.whisperjoin.common.sharedtypes.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class OveractiveConfiguration {
    private final long mBucketLengthMs;
    private final long mMonitoringWindowMs;
    private final double mThresholdPercentage;

    public OveractiveConfiguration(long j, long j2, double d) {
        this.mBucketLengthMs = j;
        this.mMonitoringWindowMs = j2;
        this.mThresholdPercentage = d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || OveractiveConfiguration.class != obj.getClass()) {
            return false;
        }
        OveractiveConfiguration overactiveConfiguration = (OveractiveConfiguration) obj;
        return new EqualsBuilder().append(this.mBucketLengthMs, overactiveConfiguration.mBucketLengthMs).append(this.mMonitoringWindowMs, overactiveConfiguration.mMonitoringWindowMs).append(this.mThresholdPercentage, overactiveConfiguration.mThresholdPercentage).isEquals();
    }

    public long getBucketLengthMs() {
        return this.mBucketLengthMs;
    }

    public long getMonitoringWindowMs() {
        return this.mMonitoringWindowMs;
    }

    public double getThresholdPercentage() {
        return this.mThresholdPercentage;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mBucketLengthMs).append(this.mMonitoringWindowMs).append(this.mThresholdPercentage).toHashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OveractiveConfiguration{mBucketLengthMs=");
        outline107.append(this.mBucketLengthMs);
        outline107.append(", mMonitoringWindowMs=");
        outline107.append(this.mMonitoringWindowMs);
        outline107.append(", mThresholdPercentage=");
        outline107.append(this.mThresholdPercentage);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
