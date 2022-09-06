package com.amazon.client.metrics.thirdparty.clickstream;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class EventBasedUsageInfo implements ClickStreamInfo {
    private static String ACTION = "action";
    private static String DOT = ".";
    private static String HYPHEN = "-";
    private static String TYPE = "type";
    private ACTION action;
    private Map<String, String> keys;
    private String prefix;
    private String suffix;
    private String type;

    /* loaded from: classes11.dex */
    public enum ACTION {
        INTENTION,
        DISCOVERY,
        TRANSACTION,
        CONSUMPTION
    }

    /* loaded from: classes11.dex */
    public static class EventBasedUsageInfoBuilder {
        private ACTION _action;
        private Map<String, String> _keys;
        private String _prefix;
        private String _suffix;
        private String _type;

        public EventBasedUsageInfo build() {
            String str = this._prefix;
            if (str != null && str.length() != 0) {
                String str2 = this._suffix;
                if (str2 != null && str2.length() != 0) {
                    if (this._action != null) {
                        Map<String, String> map = this._keys;
                        if (map != null && (map.containsKey(EventBasedUsageInfo.ACTION) || this._keys.containsKey(EventBasedUsageInfo.TYPE))) {
                            throw new IllegalArgumentException("Not null keys can not contain action or type as key");
                        }
                        return new EventBasedUsageInfo(this._prefix, this._suffix, this._action, this._type, this._keys);
                    }
                    throw new IllegalArgumentException("action can not be null");
                }
                throw new IllegalArgumentException("suffix can not be null or empty");
            }
            throw new IllegalArgumentException("prefix can not be null or empty");
        }

        public EventBasedUsageInfoBuilder withAction(ACTION action) {
            this._action = action;
            return this;
        }

        public EventBasedUsageInfoBuilder withKeys(Map<String, String> map) {
            this._keys = map;
            return this;
        }

        public EventBasedUsageInfoBuilder withPrefix(String str) {
            this._prefix = str;
            return this;
        }

        public EventBasedUsageInfoBuilder withSuffix(String str) {
            this._suffix = str;
            return this;
        }

        public EventBasedUsageInfoBuilder withType(String str) {
            this._type = str;
            return this;
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        ArrayList arrayList = new ArrayList();
        ClickStreamHelper.addDatapoint(arrayList, this.prefix + HYPHEN + ACTION + DOT + this.suffix, this.action.toString());
        ClickStreamHelper.addDatapoint(arrayList, this.prefix + HYPHEN + TYPE + DOT + this.suffix, this.type);
        for (Map.Entry<String, String> entry : this.keys.entrySet()) {
            ClickStreamHelper.addDatapoint(arrayList, this.prefix + HYPHEN + entry.getKey() + DOT + this.suffix, entry.getValue());
        }
        return arrayList;
    }

    private EventBasedUsageInfo(String str, String str2, ACTION action, String str3, Map<String, String> map) {
        this.prefix = str;
        this.suffix = str2;
        this.action = action;
        this.type = str3;
        this.keys = map;
    }
}
