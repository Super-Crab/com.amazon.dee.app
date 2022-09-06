package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class ZigbeeArcusSettings {
    private final String mCacheExpirationPeriod;
    private final boolean mEnablePeriodicSync;
    private final boolean mEnableSyncService;
    private final String mJobScheduleInitialBackOffDuration;
    private final String mJobScheduleMaxLatencyDuration;
    private final String mJobScheduleMinLatencyDuration;
    private final String mSyncPeriod;
    private final boolean mUseV2Api;

    public ZigbeeArcusSettings(boolean z, String str, String str2, boolean z2, boolean z3, String str3, String str4, String str5) {
        this.mEnableSyncService = z;
        this.mSyncPeriod = str;
        this.mCacheExpirationPeriod = str2;
        this.mUseV2Api = z2;
        this.mEnablePeriodicSync = z3;
        this.mJobScheduleMinLatencyDuration = str3;
        this.mJobScheduleMaxLatencyDuration = str4;
        this.mJobScheduleInitialBackOffDuration = str5;
    }

    public static ZigbeeArcusSettings fromJSONObject(JSONObject jSONObject) {
        return new ZigbeeArcusSettings(jSONObject.optBoolean("enableZigbeeSyncService", false), jSONObject.optString("zigbeeSyncPeriod", "P1D"), jSONObject.optString("zigbeeCacheExpirationPeriod", "PT1H"), jSONObject.optBoolean("useV2API", false), jSONObject.optBoolean("enablePeriodicSync", false), jSONObject.optString("jobScheduleMinLatencyDuration", "PT30S"), jSONObject.optString("jobScheduleMaxLatencyDuration", "PT5M"), jSONObject.optString("jobScheduleInitialBackOffDuration", "PT15M"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ZigbeeArcusSettings.class != obj.getClass()) {
            return false;
        }
        ZigbeeArcusSettings zigbeeArcusSettings = (ZigbeeArcusSettings) obj;
        return new EqualsBuilder().append(this.mEnableSyncService, zigbeeArcusSettings.mEnableSyncService).append(this.mUseV2Api, zigbeeArcusSettings.mUseV2Api).append(this.mEnablePeriodicSync, zigbeeArcusSettings.mEnablePeriodicSync).append(this.mSyncPeriod, zigbeeArcusSettings.mSyncPeriod).append(this.mCacheExpirationPeriod, zigbeeArcusSettings.mCacheExpirationPeriod).append(this.mJobScheduleMinLatencyDuration, zigbeeArcusSettings.mJobScheduleMinLatencyDuration).append(this.mJobScheduleMaxLatencyDuration, zigbeeArcusSettings.mJobScheduleMaxLatencyDuration).append(this.mJobScheduleInitialBackOffDuration, zigbeeArcusSettings.mJobScheduleInitialBackOffDuration).isEquals();
    }

    public String getCacheExpirationPeriod() {
        return this.mCacheExpirationPeriod;
    }

    public String getJobScheduleInitialBackOffDuration() {
        return this.mJobScheduleInitialBackOffDuration;
    }

    public String getJobScheduleMaxLatencyDuration() {
        return this.mJobScheduleMaxLatencyDuration;
    }

    public String getJobScheduleMinLatencyDuration() {
        return this.mJobScheduleMinLatencyDuration;
    }

    public String getSyncPeriod() {
        return this.mSyncPeriod;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mEnableSyncService).append(this.mSyncPeriod).append(this.mCacheExpirationPeriod).append(this.mUseV2Api).append(this.mEnablePeriodicSync).append(this.mJobScheduleMinLatencyDuration).append(this.mJobScheduleMaxLatencyDuration).append(this.mJobScheduleInitialBackOffDuration).toHashCode();
    }

    public boolean isPeriodicSyncEnabled() {
        return this.mEnablePeriodicSync;
    }

    public boolean isSyncServiceEnabled() {
        return this.mEnableSyncService;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enableZigbeeSyncService", this.mEnableSyncService);
        jSONObject.put("zigbeeSyncPeriod", this.mSyncPeriod);
        jSONObject.put("zigbeeCacheExpirationPeriod", this.mCacheExpirationPeriod);
        jSONObject.put("useV2API", this.mUseV2Api);
        jSONObject.put("enablePeriodicSync", this.mEnablePeriodicSync);
        jSONObject.put("jobScheduleMinLatencyDuration", this.mJobScheduleMinLatencyDuration);
        jSONObject.put("jobScheduleMaxLatencyDuration", this.mJobScheduleMaxLatencyDuration);
        jSONObject.put("jobScheduleInitialBackOffDuration", this.mJobScheduleInitialBackOffDuration);
        return jSONObject;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ZigbeeArcusSettings{mEnableSyncService=");
        outline107.append(this.mEnableSyncService);
        outline107.append(", mSyncPeriod='");
        GeneratedOutlineSupport1.outline176(outline107, this.mSyncPeriod, Chars.QUOTE, ", mCacheExpirationPeriod='");
        GeneratedOutlineSupport1.outline176(outline107, this.mCacheExpirationPeriod, Chars.QUOTE, ", mUseV2Api=");
        outline107.append(this.mUseV2Api);
        outline107.append(", mEnablePeriodicSync=");
        outline107.append(this.mEnablePeriodicSync);
        outline107.append(", mJobScheduleMinLatencyDuration='");
        GeneratedOutlineSupport1.outline176(outline107, this.mJobScheduleMinLatencyDuration, Chars.QUOTE, ", mJobScheduleMaxLatencyDuration='");
        GeneratedOutlineSupport1.outline176(outline107, this.mJobScheduleMaxLatencyDuration, Chars.QUOTE, ", mJobScheduleInitialBackOffDuration='");
        return GeneratedOutlineSupport1.outline90(outline107, this.mJobScheduleInitialBackOffDuration, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    public boolean useV2Api() {
        return this.mUseV2Api;
    }

    public ZigbeeArcusSettings() {
        this.mEnableSyncService = false;
        this.mSyncPeriod = "P1D";
        this.mCacheExpirationPeriod = "PT1H";
        this.mUseV2Api = false;
        this.mEnablePeriodicSync = false;
        this.mJobScheduleMinLatencyDuration = "PT30S";
        this.mJobScheduleMaxLatencyDuration = "PT5M";
        this.mJobScheduleInitialBackOffDuration = "PT15M";
    }
}
