package com.amazon.client.metrics.common.clickstream;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidECommerceInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSECommerceInfo;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class ECommerceInfo implements IECommerceInfo {
    private final IECommerceInfo mDelegateECommerceInfo;

    public ECommerceInfo(String str, Map<String, String> map) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateECommerceInfo = new FireOSECommerceInfo(str, map);
        } else {
            this.mDelegateECommerceInfo = new AndroidECommerceInfo(str, map);
        }
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return this.mDelegateECommerceInfo.getDataPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IECommerceInfo getDelegateECommerceInfo() {
        return this.mDelegateECommerceInfo;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo
    public ECommerceInfo isGlanceView(Boolean bool) {
        this.mDelegateECommerceInfo.isGlanceView(bool);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IECommerceInfo
    public ECommerceInfo isPrimeEligibleItem(Boolean bool) {
        this.mDelegateECommerceInfo.isPrimeEligibleItem(bool);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECommerceInfo(IECommerceInfo iECommerceInfo) {
        if (iECommerceInfo != null) {
            this.mDelegateECommerceInfo = iECommerceInfo;
            return;
        }
        throw new NullPointerException("Delegate ECommerceInfo may not be null");
    }
}
