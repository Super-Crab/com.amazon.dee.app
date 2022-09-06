package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import android.content.SharedPreferences;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class ZigbeeSyncResult {
    private static final String SHARED_PREF_TIMESTAMP = "zigbeeSyncResultTimestamp";
    private static final String SHARED_PREF_ZIGBEE_ENTRY_JSON = "zigbeeSyncResultEntryJSON";
    private final long mTimestamp;
    private final ZigbeeFFSEntry mZigbeeFFSEntry;

    public ZigbeeSyncResult(ZigbeeFFSEntry zigbeeFFSEntry, long j) {
        this.mZigbeeFFSEntry = zigbeeFFSEntry;
        this.mTimestamp = j;
    }

    public static ZigbeeSyncResult readFromSharedPreferences(SharedPreferences sharedPreferences) {
        if (!sharedPreferences.contains(SHARED_PREF_ZIGBEE_ENTRY_JSON)) {
            return null;
        }
        try {
            return new ZigbeeSyncResult(ZigbeeFFSEntry.fromJson(new JSONObject(sharedPreferences.getString(SHARED_PREF_ZIGBEE_ENTRY_JSON, "{}"))), sharedPreferences.getLong(SHARED_PREF_TIMESTAMP, 0L));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void writeToSharedPreferences(ZigbeeSyncResult zigbeeSyncResult, SharedPreferences sharedPreferences) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(SHARED_PREF_ZIGBEE_ENTRY_JSON, zigbeeSyncResult.getZigbeeFFSEntry().toJSON().toString());
            edit.putLong(SHARED_PREF_TIMESTAMP, zigbeeSyncResult.getTimestamp());
            edit.apply();
        } catch (JSONException e) {
            throw new RuntimeException("An error occurred while persisting ZigbeeEntry", e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZigbeeSyncResult)) {
            return false;
        }
        ZigbeeSyncResult zigbeeSyncResult = (ZigbeeSyncResult) obj;
        return this.mTimestamp == zigbeeSyncResult.mTimestamp && Objects.equal(this.mZigbeeFFSEntry, zigbeeSyncResult.mZigbeeFFSEntry);
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public ZigbeeFFSEntry getZigbeeFFSEntry() {
        return this.mZigbeeFFSEntry;
    }

    public int hashCode() {
        return Objects.hashCode(this.mZigbeeFFSEntry, Long.valueOf(this.mTimestamp));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mZigbeeFFSEntry", this.mZigbeeFFSEntry).add("mTimestamp", this.mTimestamp).toString();
    }
}
