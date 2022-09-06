package com.amazon.client.metrics.common.clickstream.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo;
import com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class AndroidECommerceInfo implements IECommerceInfo {
    private final ECommerceInfo mDelegateECommerceInfo;

    public AndroidECommerceInfo(String str, Map<String, String> map) {
        this.mDelegateECommerceInfo = new ECommerceInfo(str, map);
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mDelegateECommerceInfo.getDataPoints());
    }

    public ECommerceInfo getDelegateThirdPartyECommerceInfo() {
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

    public AndroidECommerceInfo(ECommerceInfo eCommerceInfo) {
        if (eCommerceInfo != null) {
            this.mDelegateECommerceInfo = eCommerceInfo;
            return;
        }
        throw new NullPointerException("Delegate ThirdParty ECommerceInfo may not be null");
    }
}
