package com.amazon.client.metrics.thirdparty.internal;

import com.amazon.client.metrics.thirdparty.clickstream.WeblabInfo;
/* loaded from: classes11.dex */
public class ClickStreamWeblabTrigger extends AbstractClickStreamMetricEvent {
    public ClickStreamWeblabTrigger(String str, String str2) {
        super(str, str2);
    }

    public void setWeblabInfo(WeblabInfo weblabInfo) {
        if (weblabInfo == null) {
            removeClickStreamInfo(WeblabInfo.class);
        } else {
            addClickStreamInfo(weblabInfo);
        }
    }

    public ClickStreamWeblabTrigger(String str, String str2, String str3, String str4) {
        super(str, str2);
        setWeblabInfo(new WeblabInfo(str3, str4));
    }
}
