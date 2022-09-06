package com.amazon.client.metrics.common.clickstream.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData;
import com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData;
import java.util.List;
import java.util.Map;
@Deprecated
/* loaded from: classes11.dex */
public class AndroidImpressionTrackingData implements IImpressionTrackingData {
    private final ImpressionTrackingData mDelegateThirdPartyImpressionTrackingData;

    public AndroidImpressionTrackingData() {
        this.mDelegateThirdPartyImpressionTrackingData = new ImpressionTrackingData();
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    @Deprecated
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mDelegateThirdPartyImpressionTrackingData.getDataPoints());
    }

    public ImpressionTrackingData getDelegateImpressionTrackingData() {
        return this.mDelegateThirdPartyImpressionTrackingData;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public List<String> getImpresionData() {
        return this.mDelegateThirdPartyImpressionTrackingData.getImpresionData();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public Map<String, String> getImpressionMetadata() {
        return this.mDelegateThirdPartyImpressionTrackingData.getImpressionMetadata();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public String getImpressionProgramGroup() {
        return this.mDelegateThirdPartyImpressionTrackingData.getImpressionProgramGroup();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public String getImpressionType() {
        return this.mDelegateThirdPartyImpressionTrackingData.getImpressionType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public com.amazon.client.metrics.common.clickstream.ImpressionTrackingData setImpresionData(List<String> list) {
        this.mDelegateThirdPartyImpressionTrackingData.setImpresionData(list);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public com.amazon.client.metrics.common.clickstream.ImpressionTrackingData setImpressionMetadata(Map<String, String> map) {
        this.mDelegateThirdPartyImpressionTrackingData.setImpressionMetadata(map);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public com.amazon.client.metrics.common.clickstream.ImpressionTrackingData setImpressionProgramGroup(String str) {
        this.mDelegateThirdPartyImpressionTrackingData.setImpressionProgramGroup(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IImpressionTrackingData
    @Deprecated
    public com.amazon.client.metrics.common.clickstream.ImpressionTrackingData setImpressionType(String str) {
        this.mDelegateThirdPartyImpressionTrackingData.setImpressionType(str);
        return null;
    }

    public AndroidImpressionTrackingData(ImpressionTrackingData impressionTrackingData) {
        if (impressionTrackingData != null) {
            this.mDelegateThirdPartyImpressionTrackingData = impressionTrackingData;
            return;
        }
        throw new NullPointerException("ThirdParty Delegate ImpressionTrackingData may not be null");
    }
}
