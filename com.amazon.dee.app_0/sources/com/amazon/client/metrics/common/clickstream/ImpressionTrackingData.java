package com.amazon.client.metrics.common.clickstream;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSImpressionTrackingData;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.List;
import java.util.Map;
@Deprecated
/* loaded from: classes11.dex */
public class ImpressionTrackingData implements IImpressionTrackingData {
    private final IImpressionTrackingData mDelegateImpressionTrackingData;

    public ImpressionTrackingData() {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateImpressionTrackingData = new FireOSImpressionTrackingData();
        } else {
            this.mDelegateImpressionTrackingData = new AndroidImpressionTrackingData();
        }
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    @Deprecated
    public List<DataPoint> getDataPoints() {
        return this.mDelegateImpressionTrackingData.getDataPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IImpressionTrackingData getDelegateImpressionTrackingData() {
        return this.mDelegateImpressionTrackingData;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public List<String> getImpresionData() {
        return this.mDelegateImpressionTrackingData.getImpresionData();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public Map<String, String> getImpressionMetadata() {
        return this.mDelegateImpressionTrackingData.getImpressionMetadata();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public String getImpressionProgramGroup() {
        return this.mDelegateImpressionTrackingData.getImpressionProgramGroup();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public String getImpressionType() {
        return this.mDelegateImpressionTrackingData.getImpressionType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public ImpressionTrackingData setImpresionData(List<String> list) {
        this.mDelegateImpressionTrackingData.setImpresionData(list);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public ImpressionTrackingData setImpressionMetadata(Map<String, String> map) {
        this.mDelegateImpressionTrackingData.setImpressionMetadata(map);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public ImpressionTrackingData setImpressionProgramGroup(String str) {
        this.mDelegateImpressionTrackingData.setImpressionProgramGroup(str);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public ImpressionTrackingData setImpressionType(String str) {
        this.mDelegateImpressionTrackingData.setImpressionType(str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImpressionTrackingData(IImpressionTrackingData iImpressionTrackingData) {
        if (iImpressionTrackingData != null) {
            this.mDelegateImpressionTrackingData = iImpressionTrackingData;
            return;
        }
        throw new NullPointerException("Delegate ImpressionTrackingData may not be null");
    }
}
