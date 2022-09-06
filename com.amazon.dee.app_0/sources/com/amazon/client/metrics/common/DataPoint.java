package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.internal.IDataPoint;
import com.amazon.client.metrics.common.internal.android.AndroidDataPoint;
import com.amazon.client.metrics.common.internal.fireos.FireOSDataPoint;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
/* loaded from: classes11.dex */
public class DataPoint implements IDataPoint {
    private final IDataPoint mDelegateDataPoint;

    public DataPoint(String str, String str2, int i, DataPointType dataPointType) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateDataPoint = new FireOSDataPoint(str, str2, i, dataPointType);
        } else {
            this.mDelegateDataPoint = new AndroidDataPoint(str, str2, i, dataPointType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IDataPoint getDelegateDataPoint() {
        return this.mDelegateDataPoint;
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public String getName() {
        return this.mDelegateDataPoint.getName();
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public int getSamples() {
        return this.mDelegateDataPoint.getSamples();
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public DataPointType getType() {
        return this.mDelegateDataPoint.getType();
    }

    @Override // com.amazon.client.metrics.common.internal.IDataPoint
    public String getValue() {
        return this.mDelegateDataPoint.getValue();
    }

    public String toString() {
        return this.mDelegateDataPoint.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataPoint(IDataPoint iDataPoint) {
        if (iDataPoint != null) {
            this.mDelegateDataPoint = iDataPoint;
            return;
        }
        throw new NullPointerException("DataPoint can not be null");
    }
}
