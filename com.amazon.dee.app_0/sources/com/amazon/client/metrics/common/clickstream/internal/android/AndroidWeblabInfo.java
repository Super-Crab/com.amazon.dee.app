package com.amazon.client.metrics.common.clickstream.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.thirdparty.clickstream.WeblabInfo;
import java.util.List;
/* loaded from: classes11.dex */
public class AndroidWeblabInfo implements ClickStreamInfo {
    private final WeblabInfo mDelegateThirdPartyWeblabInfo;

    public AndroidWeblabInfo(String str, String str2) {
        this.mDelegateThirdPartyWeblabInfo = new WeblabInfo(str, str2);
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mDelegateThirdPartyWeblabInfo.getDataPoints());
    }

    public WeblabInfo getDelegateWeblabInfo() {
        return this.mDelegateThirdPartyWeblabInfo;
    }

    public AndroidWeblabInfo(WeblabInfo weblabInfo) {
        if (weblabInfo != null) {
            this.mDelegateThirdPartyWeblabInfo = weblabInfo;
            return;
        }
        throw new NullPointerException("ThirdParty Delegate WeblabInfo may not be null");
    }
}
