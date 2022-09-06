package com.amazon.client.metrics.common.clickstream.internal.fireos;

import com.amazon.client.metrics.clickstream.WeblabInfo;
import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import java.util.List;
/* loaded from: classes11.dex */
public class FireOSWeblabInfo implements ClickStreamInfo {
    private final WeblabInfo mDelegateFirstPartyWeblabInfo;

    public FireOSWeblabInfo(String str, String str2) {
        this.mDelegateFirstPartyWeblabInfo = new WeblabInfo(str, str2);
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertFirstPartyToCommon(this.mDelegateFirstPartyWeblabInfo.getDataPoints());
    }

    public WeblabInfo getDelegateWeblabInfo() {
        return this.mDelegateFirstPartyWeblabInfo;
    }

    public FireOSWeblabInfo(WeblabInfo weblabInfo) {
        if (weblabInfo != null) {
            this.mDelegateFirstPartyWeblabInfo = weblabInfo;
            return;
        }
        throw new NullPointerException("Delegate FirstParty WeblabInfo may not be null");
    }
}
