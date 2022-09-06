package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;

import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class ThrottleSettings {
    private final String overactiveBucketLengthPeriod;
    private final String overactiveMonitoringWindowPeriod;
    private final double overactiveThresholdPercentage;
    private final int retryBackoffAttemptCount;
    private final String retryBackoffBaseDuration;
    private final String ztwDssFailedMonitorWindowPeriod;
    private final long ztwDssFailedThreshold;
    private final String ztwFailedMonitorWindowPeriod;
    private final long ztwFailedThreshold;

    /* loaded from: classes13.dex */
    public static final class ThrottleSettingsBuilder {
        private String overactiveBucketLengthPeriod = "PT10S";
        private String overactiveMonitoringWindowPeriod = "PT3M";
        private double overactiveThresholdPercentage = 50.0d;
        private String ztwFailedMonitorWindowPeriod = "PT1M";
        private long ztwFailedThreshold = 3;
        private String ztwDssFailedMonitorWindowPeriod = "PT1M";
        private long ztwDssFailedThreshold = 4;
        private int retryBackoffAttemptCount = 5;
        private String retryBackoffBaseDuration = "PT5M";

        public ThrottleSettings build() {
            return new ThrottleSettings(this.overactiveBucketLengthPeriod, this.overactiveMonitoringWindowPeriod, this.overactiveThresholdPercentage, this.ztwFailedMonitorWindowPeriod, this.ztwFailedThreshold, this.ztwDssFailedMonitorWindowPeriod, this.ztwDssFailedThreshold, this.retryBackoffAttemptCount, this.retryBackoffBaseDuration);
        }

        public ThrottleSettingsBuilder withOveractiveBucketLengthPeriod(String str) {
            this.overactiveBucketLengthPeriod = str;
            return this;
        }

        public ThrottleSettingsBuilder withOveractiveMonitoringWindowPeriod(String str) {
            this.overactiveMonitoringWindowPeriod = str;
            return this;
        }

        public ThrottleSettingsBuilder withOveractiveThresholdPercentage(double d) {
            this.overactiveThresholdPercentage = d;
            return this;
        }

        public ThrottleSettingsBuilder withRetryBackoffAttemptCount(int i) {
            this.retryBackoffAttemptCount = i;
            return this;
        }

        public ThrottleSettingsBuilder withRetryBackoffBaseDuration(String str) {
            this.retryBackoffBaseDuration = str;
            return this;
        }

        public ThrottleSettingsBuilder withZtwDssFailedMonitorWindowPeriod(String str) {
            this.ztwDssFailedMonitorWindowPeriod = str;
            return this;
        }

        public ThrottleSettingsBuilder withZtwDssFailedThreshold(long j) {
            this.ztwDssFailedThreshold = j;
            return this;
        }

        public ThrottleSettingsBuilder withZtwFailedMonitorWindowPeriod(String str) {
            this.ztwFailedMonitorWindowPeriod = str;
            return this;
        }

        public ThrottleSettingsBuilder withZtwFailedThreshold(long j) {
            this.ztwFailedThreshold = j;
            return this;
        }
    }

    public static ThrottleSettings fromJSONObject(JSONObject jSONObject) {
        return new ThrottleSettings(jSONObject);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ThrottleSettings.class != obj.getClass()) {
            return false;
        }
        ThrottleSettings throttleSettings = (ThrottleSettings) obj;
        return new EqualsBuilder().append(this.overactiveThresholdPercentage, throttleSettings.overactiveThresholdPercentage).append(this.ztwFailedThreshold, throttleSettings.ztwFailedThreshold).append(this.ztwDssFailedThreshold, throttleSettings.ztwDssFailedThreshold).append(this.retryBackoffAttemptCount, throttleSettings.retryBackoffAttemptCount).append(this.overactiveBucketLengthPeriod, throttleSettings.overactiveBucketLengthPeriod).append(this.overactiveMonitoringWindowPeriod, throttleSettings.overactiveMonitoringWindowPeriod).append(this.ztwFailedMonitorWindowPeriod, throttleSettings.ztwFailedMonitorWindowPeriod).append(this.ztwDssFailedMonitorWindowPeriod, throttleSettings.ztwDssFailedMonitorWindowPeriod).append(this.retryBackoffBaseDuration, throttleSettings.retryBackoffBaseDuration).isEquals();
    }

    public String getOveractiveBucketLengthPeriod() {
        return this.overactiveBucketLengthPeriod;
    }

    public OveractiveConfiguration getOveractiveConfiguration() {
        return new OveractiveConfiguration(Iso8601TimeConverter.convertDurationToMs(getOveractiveBucketLengthPeriod()), Iso8601TimeConverter.convertDurationToMs(getOveractiveMonitoringWindowPeriod()), getOveractiveThresholdPercentage());
    }

    public String getOveractiveMonitoringWindowPeriod() {
        return this.overactiveMonitoringWindowPeriod;
    }

    public double getOveractiveThresholdPercentage() {
        return this.overactiveThresholdPercentage;
    }

    public int getRetryBackoffAttemptCount() {
        return this.retryBackoffAttemptCount;
    }

    public String getRetryBackoffBaseDuration() {
        return this.retryBackoffBaseDuration;
    }

    public String getZtwDssFailedMonitorWindowPeriod() {
        return this.ztwDssFailedMonitorWindowPeriod;
    }

    public long getZtwDssFailedThreshold() {
        return this.ztwDssFailedThreshold;
    }

    public String getZtwFailedMonitorWindowPeriod() {
        return this.ztwFailedMonitorWindowPeriod;
    }

    public long getZtwFailedThreshold() {
        return this.ztwFailedThreshold;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.overactiveBucketLengthPeriod).append(this.overactiveMonitoringWindowPeriod).append(this.overactiveThresholdPercentage).append(this.ztwFailedMonitorWindowPeriod).append(this.ztwFailedThreshold).append(this.ztwDssFailedMonitorWindowPeriod).append(this.ztwDssFailedThreshold).append(this.retryBackoffAttemptCount).append(this.retryBackoffBaseDuration).toHashCode();
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        jSONObject.put("overactive", jSONObject2);
        jSONObject.put("zeroTouchWorkflow", jSONObject3);
        jSONObject.put("retryBackoff", jSONObject4);
        jSONObject2.put("bucketLengthPeriod", this.overactiveBucketLengthPeriod);
        jSONObject2.put("monitoringWindowPeriod", this.overactiveMonitoringWindowPeriod);
        jSONObject2.put("thresholdPercentage", this.overactiveThresholdPercentage);
        jSONObject3.put("failedMonitorWindowPeriod", this.ztwFailedMonitorWindowPeriod);
        jSONObject3.put("failedThreshold", this.ztwFailedThreshold);
        jSONObject3.put("dssFailedMonitorWindowPeriod", this.ztwDssFailedMonitorWindowPeriod);
        jSONObject3.put("dssFailedThreshold", this.ztwDssFailedThreshold);
        jSONObject4.put("backoffAttemptCount", this.retryBackoffAttemptCount);
        jSONObject4.put("backoffBaseDuration", this.retryBackoffBaseDuration);
        return jSONObject;
    }

    public String toString() {
        return new ToStringBuilder(this).append("overactiveBucketLengthPeriod", this.overactiveBucketLengthPeriod).append("overactiveMonitoringWindowPeriod", this.overactiveMonitoringWindowPeriod).append("overactiveThresholdPercentage", this.overactiveThresholdPercentage).append("ztwFailedMonitorWindowPeriod", this.ztwFailedMonitorWindowPeriod).append("ztwFailedThreshold", this.ztwFailedThreshold).append("ztwDssFailedMonitorWindowPeriod", this.ztwDssFailedMonitorWindowPeriod).append("ztwDssFailedThreshold", this.ztwDssFailedThreshold).append("retryBackoffAttemptCount", this.retryBackoffAttemptCount).append("retryBackoffBaseDuration", this.retryBackoffBaseDuration).toString();
    }

    private ThrottleSettings(String str, String str2, double d, String str3, long j, String str4, long j2, int i, String str5) {
        this.overactiveBucketLengthPeriod = str;
        this.overactiveMonitoringWindowPeriod = str2;
        this.overactiveThresholdPercentage = d;
        this.ztwFailedMonitorWindowPeriod = str3;
        this.ztwFailedThreshold = j;
        this.ztwDssFailedMonitorWindowPeriod = str4;
        this.ztwDssFailedThreshold = j2;
        this.retryBackoffAttemptCount = i;
        this.retryBackoffBaseDuration = str5;
    }

    public ThrottleSettings() {
        this.overactiveBucketLengthPeriod = "PT10S";
        this.overactiveMonitoringWindowPeriod = "PT3M";
        this.overactiveThresholdPercentage = 50.0d;
        this.ztwFailedMonitorWindowPeriod = "PT1M";
        this.ztwFailedThreshold = 3L;
        this.ztwDssFailedMonitorWindowPeriod = "PT1M";
        this.ztwDssFailedThreshold = 4L;
        this.retryBackoffAttemptCount = 5;
        this.retryBackoffBaseDuration = "PT5M";
    }

    public ThrottleSettings(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.has("overactive") ? jSONObject.optJSONObject("overactive") : new JSONObject();
        this.overactiveBucketLengthPeriod = optJSONObject.optString("bucketLengthPeriod", "PT10S");
        this.overactiveMonitoringWindowPeriod = optJSONObject.optString("monitoringWindowPeriod", "PT3M");
        this.overactiveThresholdPercentage = optJSONObject.optDouble("thresholdPercentage", 50.0d);
        JSONObject optJSONObject2 = jSONObject.has("zeroTouchWorkflow") ? jSONObject.optJSONObject("zeroTouchWorkflow") : new JSONObject();
        this.ztwFailedMonitorWindowPeriod = optJSONObject2.optString("failedMonitorWindowPeriod", "PT1M");
        this.ztwFailedThreshold = optJSONObject2.optLong("failedThreshold", 3L);
        this.ztwDssFailedMonitorWindowPeriod = optJSONObject2.optString("dssFailedMonitorWindowPeriod", "PT1M");
        this.ztwDssFailedThreshold = optJSONObject2.optLong("dssFailedThreshold", 4L);
        JSONObject optJSONObject3 = jSONObject.has("retryBackoff") ? jSONObject.optJSONObject("retryBackoff") : new JSONObject();
        this.retryBackoffAttemptCount = optJSONObject3.optInt("backoffAttemptCount", 5);
        this.retryBackoffBaseDuration = optJSONObject3.optString("backoffBaseDuration", "PT5M");
    }
}
