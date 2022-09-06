package com.amazon.client.metrics.common.internal.android;

import com.amazon.client.metrics.common.DataPointType;
import com.amazon.client.metrics.common.internal.IDataPoint;
import com.amazon.client.metrics.thirdparty.DataPoint;
/* loaded from: classes11.dex */
public class AndroidDataPoint implements IDataPoint {
    private final DataPoint mDelegateThirdPartyDataPoint;

    public AndroidDataPoint(String str, String str2, int i, DataPointType dataPointType) {
        this.mDelegateThirdPartyDataPoint = new DataPoint(str, str2, i, com.amazon.client.metrics.thirdparty.DataPointType.valueOf(dataPointType.name()));
    }

    public DataPoint getDelegateDataPoint() {
        return this.mDelegateThirdPartyDataPoint;
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public String getName() {
        return this.mDelegateThirdPartyDataPoint.getName();
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public int getSamples() {
        return this.mDelegateThirdPartyDataPoint.getSamples();
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public DataPointType getType() {
        return DataPointType.valueOf(this.mDelegateThirdPartyDataPoint.getType().name());
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public String getValue() {
        return this.mDelegateThirdPartyDataPoint.getValue();
    }

    public String toString() {
        return this.mDelegateThirdPartyDataPoint.toString();
    }

    public AndroidDataPoint(DataPoint dataPoint) {
        if (dataPoint != null) {
            this.mDelegateThirdPartyDataPoint = dataPoint;
            return;
        }
        throw new NullPointerException("Third Party DataPoint may not be null");
    }
}
