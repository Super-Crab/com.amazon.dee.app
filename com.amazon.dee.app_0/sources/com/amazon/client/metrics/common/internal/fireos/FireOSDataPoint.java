package com.amazon.client.metrics.common.internal.fireos;

import com.amazon.client.metrics.DataPoint;
import com.amazon.client.metrics.common.DataPointType;
import com.amazon.client.metrics.common.internal.IDataPoint;
/* loaded from: classes11.dex */
public class FireOSDataPoint implements IDataPoint {
    private final DataPoint mDelegateFirstPartyDataPoint;

    public FireOSDataPoint(String str, String str2, int i, DataPointType dataPointType) {
        this.mDelegateFirstPartyDataPoint = new DataPoint(str, str2, i, com.amazon.client.metrics.DataPointType.valueOf(dataPointType.name()));
    }

    public DataPoint getDelegateDataPoint() {
        return this.mDelegateFirstPartyDataPoint;
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public String getName() {
        return this.mDelegateFirstPartyDataPoint.getName();
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public int getSamples() {
        return this.mDelegateFirstPartyDataPoint.getSamples();
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public DataPointType getType() {
        return DataPointType.valueOf(this.mDelegateFirstPartyDataPoint.getType().name());
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public String getValue() {
        return this.mDelegateFirstPartyDataPoint.getValue();
    }

    public String toString() {
        return this.mDelegateFirstPartyDataPoint.toString();
    }

    public FireOSDataPoint(DataPoint dataPoint) {
        if (dataPoint != null) {
            this.mDelegateFirstPartyDataPoint = dataPoint;
            return;
        }
        throw new NullPointerException("First Party DataPoint may not be null");
    }
}
