package com.amazon.client.metrics.common.clickstream.internal.android;

import com.amazon.client.metrics.common.clickstream.ClickStreamDataConverter;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.common.internal.android.AndroidMetricEvent;
import java.util.Collection;
/* loaded from: classes11.dex */
public class AndroidGenericClickStreamMetricEvent extends AndroidMetricEvent implements GenericClickStreamMetricEvent {
    private final com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent mDelegateThirdPartyGenericClickStreamMetricEvent;

    public AndroidGenericClickStreamMetricEvent(com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent genericClickStreamMetricEvent) {
        super(genericClickStreamMetricEvent);
        this.mDelegateThirdPartyGenericClickStreamMetricEvent = genericClickStreamMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void addClickStreamInfo(ClickStreamInfo clickStreamInfo) {
        this.mDelegateThirdPartyGenericClickStreamMetricEvent.addClickStreamInfo(ClickStreamDataConverter.convertClickStreamInfo_fromCommonToThirdParty(clickStreamInfo));
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public Collection<ClickStreamInfo> getClickStreamInfo() {
        return ClickStreamDataConverter.convertClickStreamInfoCollection_fromThirdPartyToCommon(this.mDelegateThirdPartyGenericClickStreamMetricEvent.getClickStreamInfo());
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public String getRequestId() {
        return this.mDelegateThirdPartyGenericClickStreamMetricEvent.getRequestId();
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void removeClickStreamInfo(Class<? extends ClickStreamInfo> cls) {
        this.mDelegateThirdPartyGenericClickStreamMetricEvent.removeClickStreamInfo(ClickStreamDataConverter.convertClickStreamInfo_fromCommonToThirdParty(cls));
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public boolean validateMetricEvent() {
        return this.mDelegateThirdPartyGenericClickStreamMetricEvent.validateMetricEvent();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent
    /* renamed from: getDelegateMetricEvent  reason: collision with other method in class */
    public com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent mo2883getDelegateMetricEvent() {
        return this.mDelegateThirdPartyGenericClickStreamMetricEvent;
    }
}
