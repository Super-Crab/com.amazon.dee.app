package com.amazon.client.metrics.common.clickstream.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.EventBasedUsageInfo;
import com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class AndroidEventBasedUsageInfo implements ClickStreamInfo {
    private final EventBasedUsageInfo mDelegateThirdPartyEventBasedUsageInfo;

    public AndroidEventBasedUsageInfo(String str, String str2, EventBasedUsageInfo.ACTION action, String str3, Map<String, String> map) {
        this.mDelegateThirdPartyEventBasedUsageInfo = new EventBasedUsageInfo.EventBasedUsageInfoBuilder().withPrefix(str).withSuffix(str2).withAction(EventBasedUsageInfo.ACTION.valueOf(action.name())).withType(str3).withKeys(map).build();
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mDelegateThirdPartyEventBasedUsageInfo.getDataPoints());
    }

    public com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo getDelegateEventBasedUsageInfo() {
        return this.mDelegateThirdPartyEventBasedUsageInfo;
    }

    public AndroidEventBasedUsageInfo(com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo eventBasedUsageInfo) {
        if (eventBasedUsageInfo != null) {
            this.mDelegateThirdPartyEventBasedUsageInfo = eventBasedUsageInfo;
            return;
        }
        throw new NullPointerException("ThirdParty delegate EventBasedUsageInfo may not be null");
    }
}
