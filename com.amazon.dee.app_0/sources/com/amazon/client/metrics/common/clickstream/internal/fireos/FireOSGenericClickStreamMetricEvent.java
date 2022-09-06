package com.amazon.client.metrics.common.clickstream.internal.fireos;

import com.amazon.client.metrics.common.clickstream.ClickStreamDataConverter;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent;
import java.util.Collection;
/* loaded from: classes11.dex */
public class FireOSGenericClickStreamMetricEvent extends FireOSMetricEvent implements GenericClickStreamMetricEvent {
    private final com.amazon.client.metrics.clickstream.GenericClickStreamMetricEvent mDelegateFirstPartyGenericClickStreamMetricEvent;

    public FireOSGenericClickStreamMetricEvent(com.amazon.client.metrics.clickstream.GenericClickStreamMetricEvent genericClickStreamMetricEvent) {
        super(genericClickStreamMetricEvent);
        this.mDelegateFirstPartyGenericClickStreamMetricEvent = genericClickStreamMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void addClickStreamInfo(ClickStreamInfo clickStreamInfo) {
        this.mDelegateFirstPartyGenericClickStreamMetricEvent.addClickStreamInfo(ClickStreamDataConverter.convertClickStreamInfo_fromCommonToFirstParty(clickStreamInfo));
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public Collection<ClickStreamInfo> getClickStreamInfo() {
        return ClickStreamDataConverter.convertClickStreamInfoCollection_fromFirstPartyToCommon(this.mDelegateFirstPartyGenericClickStreamMetricEvent.getClickStreamInfo());
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public String getRequestId() {
        return this.mDelegateFirstPartyGenericClickStreamMetricEvent.getRequestId();
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void removeClickStreamInfo(Class<? extends ClickStreamInfo> cls) {
        this.mDelegateFirstPartyGenericClickStreamMetricEvent.removeClickStreamInfo(ClickStreamDataConverter.convertClickStreamInfo_fromCommonToFirstParty(cls));
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public boolean validateMetricEvent() {
        return this.mDelegateFirstPartyGenericClickStreamMetricEvent.validateMetricEvent();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent
    /* renamed from: getDelegateMetricEvent  reason: collision with other method in class */
    public com.amazon.client.metrics.clickstream.GenericClickStreamMetricEvent mo2886getDelegateMetricEvent() {
        return this.mDelegateFirstPartyGenericClickStreamMetricEvent;
    }
}
