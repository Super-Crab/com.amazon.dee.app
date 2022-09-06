package com.amazon.client.metrics.common.clickstream;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidWeblabInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSWeblabInfo;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.List;
/* loaded from: classes11.dex */
public class WeblabInfo implements ClickStreamInfo {
    private final ClickStreamInfo mDelegateClickstreamWeblabInfo;

    public WeblabInfo(String str, String str2) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateClickstreamWeblabInfo = new FireOSWeblabInfo(str, str2);
        } else {
            this.mDelegateClickstreamWeblabInfo = new AndroidWeblabInfo(str, str2);
        }
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return this.mDelegateClickstreamWeblabInfo.getDataPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClickStreamInfo getDelegateClickstreamWeblabInfo() {
        return this.mDelegateClickstreamWeblabInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WeblabInfo(ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo != null) {
            this.mDelegateClickstreamWeblabInfo = clickStreamInfo;
            return;
        }
        throw new NullPointerException("Delegate WeblabInfo may not be null");
    }
}
