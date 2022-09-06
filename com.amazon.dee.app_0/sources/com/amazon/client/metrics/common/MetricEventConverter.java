package com.amazon.client.metrics.common;

import com.amazon.client.metrics.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidGenericClickStreamMetricEvent;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSGenericClickStreamMetricEvent;
import com.amazon.client.metrics.common.internal.android.AndroidClickStreamMetricsEvent;
import com.amazon.client.metrics.common.internal.android.AndroidMetricEvent;
import com.amazon.client.metrics.common.internal.android.AndroidNullClickStreamMetricEvent;
import com.amazon.client.metrics.common.internal.android.AndroidNullMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSClickStreamMetricsEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSNullClickStreamMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSNullMetricEvent;
/* loaded from: classes11.dex */
public class MetricEventConverter {
    public static com.amazon.client.metrics.ClickStreamMetricsEvent convertClickStreamMetricsEvent_fromCommonToFirstParty(ClickStreamMetricsEvent clickStreamMetricsEvent) {
        if (clickStreamMetricsEvent != null && (clickStreamMetricsEvent instanceof FireOSClickStreamMetricsEvent)) {
            return ((FireOSClickStreamMetricsEvent) clickStreamMetricsEvent).mo2886getDelegateMetricEvent();
        }
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent convertClickStreamMetricsEvent_fromCommonToThirdParty(ClickStreamMetricsEvent clickStreamMetricsEvent) {
        if (clickStreamMetricsEvent != null && (clickStreamMetricsEvent instanceof AndroidClickStreamMetricsEvent)) {
            return ((AndroidClickStreamMetricsEvent) clickStreamMetricsEvent).mo2883getDelegateMetricEvent();
        }
        return null;
    }

    public static ClickStreamMetricsEvent convertClickStreamMetricsEvent_fromFirstPartyToCommon(com.amazon.client.metrics.ClickStreamMetricsEvent clickStreamMetricsEvent) {
        if (clickStreamMetricsEvent == null) {
            return null;
        }
        return new FireOSClickStreamMetricsEvent(clickStreamMetricsEvent);
    }

    public static ClickStreamMetricsEvent convertClickStreamMetricsEvent_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent clickStreamMetricsEvent) {
        if (clickStreamMetricsEvent == null) {
            return null;
        }
        return new AndroidClickStreamMetricsEvent(clickStreamMetricsEvent);
    }

    public static GenericClickStreamMetricEvent convertGenericClickStreamMetricEvent_fromCommonToFirstParty(com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent genericClickStreamMetricEvent) {
        if (genericClickStreamMetricEvent != null && (genericClickStreamMetricEvent instanceof FireOSGenericClickStreamMetricEvent)) {
            return ((FireOSGenericClickStreamMetricEvent) genericClickStreamMetricEvent).mo2886getDelegateMetricEvent();
        }
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent convertGenericClickStreamMetricEvent_fromCommonToThirdParty(com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent genericClickStreamMetricEvent) {
        if (genericClickStreamMetricEvent != null && (genericClickStreamMetricEvent instanceof AndroidGenericClickStreamMetricEvent)) {
            return ((AndroidGenericClickStreamMetricEvent) genericClickStreamMetricEvent).mo2883getDelegateMetricEvent();
        }
        return null;
    }

    public static com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent convertGenericClickStreamMetricEvent_fromFirstPartyToCommon(GenericClickStreamMetricEvent genericClickStreamMetricEvent) {
        if (genericClickStreamMetricEvent == null) {
            return null;
        }
        return new FireOSGenericClickStreamMetricEvent(genericClickStreamMetricEvent);
    }

    public static com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent convertGenericClickStreamMetricEvent_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent genericClickStreamMetricEvent) {
        if (genericClickStreamMetricEvent == null) {
            return null;
        }
        return new AndroidGenericClickStreamMetricEvent(genericClickStreamMetricEvent);
    }

    public static com.amazon.client.metrics.MetricEvent convertMetricEvent_fromCommonToFirstParty(MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        if (metricEvent instanceof FireOSMetricEvent) {
            return ((FireOSMetricEvent) metricEvent).mo2886getDelegateMetricEvent();
        }
        if (!(metricEvent instanceof NullMetricEvent)) {
            return null;
        }
        return convertNullMetricEvent_fromCommonToFirstParty((NullMetricEvent) metricEvent);
    }

    public static com.amazon.client.metrics.thirdparty.MetricEvent convertMetricEvent_fromCommonToThirdParty(MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        if (metricEvent instanceof AndroidMetricEvent) {
            return ((AndroidMetricEvent) metricEvent).mo2883getDelegateMetricEvent();
        }
        if (!(metricEvent instanceof NullMetricEvent)) {
            return null;
        }
        return convertNullMetricEvent_fromCommonToThirdParty((NullMetricEvent) metricEvent);
    }

    public static MetricEvent convertMetricEvent_fromFirstPartyToCommon(com.amazon.client.metrics.MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        return new FireOSMetricEvent(metricEvent);
    }

    public static MetricEvent convertMetricEvent_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        return new AndroidMetricEvent(metricEvent);
    }

    public static com.amazon.client.metrics.NullClickStreamMetricEvent convertNullClickStreamMetricEvent_fromCommonToFirstParty(NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        if (nullClickStreamMetricEvent == null) {
            return null;
        }
        ClickStreamMetricsEvent mo2877getDelegateMetricEvent = nullClickStreamMetricEvent.mo2877getDelegateMetricEvent();
        if (!(mo2877getDelegateMetricEvent instanceof FireOSNullClickStreamMetricEvent)) {
            return null;
        }
        return ((FireOSNullClickStreamMetricEvent) mo2877getDelegateMetricEvent).mo2886getDelegateMetricEvent();
    }

    public static com.amazon.client.metrics.thirdparty.NullClickStreamMetricEvent convertNullClickStreamMetricEvent_fromCommonToThirdParty(NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        if (nullClickStreamMetricEvent == null) {
            return null;
        }
        ClickStreamMetricsEvent mo2877getDelegateMetricEvent = nullClickStreamMetricEvent.mo2877getDelegateMetricEvent();
        if (!(mo2877getDelegateMetricEvent instanceof AndroidNullClickStreamMetricEvent)) {
            return null;
        }
        return ((AndroidNullClickStreamMetricEvent) mo2877getDelegateMetricEvent).mo2883getDelegateMetricEvent();
    }

    public static NullClickStreamMetricEvent convertNullClickStreamMetricEvent_fromFirstPartyToCommon(com.amazon.client.metrics.NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        if (nullClickStreamMetricEvent == null) {
            return null;
        }
        return new NullClickStreamMetricEvent(nullClickStreamMetricEvent);
    }

    public static NullClickStreamMetricEvent convertNullClickStreamMetricEvent_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        if (nullClickStreamMetricEvent == null) {
            return null;
        }
        return new NullClickStreamMetricEvent(nullClickStreamMetricEvent);
    }

    public static com.amazon.client.metrics.NullMetricEvent convertNullMetricEvent_fromCommonToFirstParty(NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent == null) {
            return null;
        }
        MetricEvent mo2877getDelegateMetricEvent = nullMetricEvent.mo2877getDelegateMetricEvent();
        if (!(mo2877getDelegateMetricEvent instanceof FireOSNullMetricEvent)) {
            return null;
        }
        return ((FireOSNullMetricEvent) mo2877getDelegateMetricEvent).mo2886getDelegateMetricEvent();
    }

    public static com.amazon.client.metrics.thirdparty.NullMetricEvent convertNullMetricEvent_fromCommonToThirdParty(NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent == null) {
            return null;
        }
        MetricEvent mo2877getDelegateMetricEvent = nullMetricEvent.mo2877getDelegateMetricEvent();
        if (!(mo2877getDelegateMetricEvent instanceof AndroidNullMetricEvent)) {
            return null;
        }
        return ((AndroidNullMetricEvent) mo2877getDelegateMetricEvent).mo2883getDelegateMetricEvent();
    }

    public static NullMetricEvent convertNullMetricEvent_fromFirstPartyToCommon(com.amazon.client.metrics.NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent == null) {
            return null;
        }
        return new NullMetricEvent(nullMetricEvent);
    }

    public static NullMetricEvent convertNullMetricEvent_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent == null) {
            return null;
        }
        return new NullMetricEvent(nullMetricEvent);
    }
}
