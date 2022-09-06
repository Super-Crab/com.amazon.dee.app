package com.amazon.client.metrics.common.clickstream;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidEventBasedUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSEventBasedUsageInfo;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class EventBasedUsageInfo implements ClickStreamInfo {
    private final ClickStreamInfo mDelegateEventBasedUsageClickStreamInfo;

    /* loaded from: classes11.dex */
    public enum ACTION {
        INTENTION,
        DISCOVERY,
        TRANSACTION,
        CONSUMPTION
    }

    /* loaded from: classes11.dex */
    public static class EventBasedUsageInfoBuilder {
        private ACTION mAction;
        private Map<String, String> mKeys;
        private String mPrefix;
        private String mSuffix;
        private String mType;

        public EventBasedUsageInfo build() {
            return new EventBasedUsageInfo(this.mPrefix, this.mSuffix, this.mAction, this.mType, this.mKeys);
        }

        public EventBasedUsageInfoBuilder withAction(ACTION action) {
            this.mAction = action;
            return this;
        }

        public EventBasedUsageInfoBuilder withKeys(Map<String, String> map) {
            this.mKeys = map;
            return this;
        }

        public EventBasedUsageInfoBuilder withPrefix(String str) {
            this.mPrefix = str;
            return this;
        }

        public EventBasedUsageInfoBuilder withSuffix(String str) {
            this.mSuffix = str;
            return this;
        }

        public EventBasedUsageInfoBuilder withType(String str) {
            this.mType = str;
            return this;
        }
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return this.mDelegateEventBasedUsageClickStreamInfo.getDataPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClickStreamInfo getDelegateEventBasedUsageClickstreamInfo() {
        return this.mDelegateEventBasedUsageClickStreamInfo;
    }

    private EventBasedUsageInfo(String str, String str2, ACTION action, String str3, Map<String, String> map) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateEventBasedUsageClickStreamInfo = new FireOSEventBasedUsageInfo(str, str2, action, str3, map);
        } else {
            this.mDelegateEventBasedUsageClickStreamInfo = new AndroidEventBasedUsageInfo(str, str2, action, str3, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventBasedUsageInfo(ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo != null) {
            this.mDelegateEventBasedUsageClickStreamInfo = clickStreamInfo;
            return;
        }
        throw new NullPointerException("Delegate EventBasedUsageInfo may not be null");
    }
}
