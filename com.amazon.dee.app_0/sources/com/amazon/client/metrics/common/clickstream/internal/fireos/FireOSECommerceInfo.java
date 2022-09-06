package com.amazon.client.metrics.common.clickstream.internal.fireos;

import com.amazon.client.metrics.clickstream.ECommerceInfo;
import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class FireOSECommerceInfo implements IECommerceInfo {
    private final ECommerceInfo mDelegateECommerceInfo;

    public FireOSECommerceInfo(String str, Map<String, String> map) {
        this.mDelegateECommerceInfo = new ECommerceInfo(str, map);
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertFirstPartyToCommon(this.mDelegateECommerceInfo.getDataPoints());
    }

    public ECommerceInfo getDelegateFirstPartyECommerceInfo() {
        return this.mDelegateECommerceInfo;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo
    public com.amazon.client.metrics.common.clickstream.ECommerceInfo isGlanceView(Boolean bool) {
        this.mDelegateECommerceInfo.isGlanceView(bool);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo
    public com.amazon.client.metrics.common.clickstream.ECommerceInfo isPrimeEligibleItem(Boolean bool) {
        this.mDelegateECommerceInfo.isPrimeEligibleItem(bool);
        return null;
    }

    public FireOSECommerceInfo(ECommerceInfo eCommerceInfo) {
        if (eCommerceInfo != null) {
            this.mDelegateECommerceInfo = eCommerceInfo;
            return;
        }
        throw new NullPointerException("Delegate FirstParty ECommerceInfo may not be null");
    }
}
