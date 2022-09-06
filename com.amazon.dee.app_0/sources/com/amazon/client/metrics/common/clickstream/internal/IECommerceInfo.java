package com.amazon.client.metrics.common.clickstream.internal;

import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.ECommerceInfo;
/* loaded from: classes11.dex */
public interface IECommerceInfo extends ClickStreamInfo {
    ECommerceInfo isGlanceView(Boolean bool);

    ECommerceInfo isPrimeEligibleItem(Boolean bool);
}
