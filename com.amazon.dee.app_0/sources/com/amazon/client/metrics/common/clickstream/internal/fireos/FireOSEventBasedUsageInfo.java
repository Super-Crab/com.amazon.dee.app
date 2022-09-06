package com.amazon.client.metrics.common.clickstream.internal.fireos;

import com.amazon.client.metrics.clickstream.EventBasedUsageInfo;
import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.EventBasedUsageInfo;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class FireOSEventBasedUsageInfo implements ClickStreamInfo {
    private final EventBasedUsageInfo mDelegateEventBasedUsageInfo;

    public FireOSEventBasedUsageInfo(String str, String str2, EventBasedUsageInfo.ACTION action, String str3, Map<String, String> map) {
        this.mDelegateEventBasedUsageInfo = null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return null;
    }

    public com.amazon.client.metrics.clickstream.EventBasedUsageInfo getDelegateEventBasedUsageInfo() {
        return this.mDelegateEventBasedUsageInfo;
    }

    public FireOSEventBasedUsageInfo(com.amazon.client.metrics.clickstream.EventBasedUsageInfo eventBasedUsageInfo) {
        if (eventBasedUsageInfo != null) {
            this.mDelegateEventBasedUsageInfo = eventBasedUsageInfo;
            return;
        }
        throw new NullPointerException("Delegate FirstParty EventBasedUsageInfo may not be null");
    }
}
