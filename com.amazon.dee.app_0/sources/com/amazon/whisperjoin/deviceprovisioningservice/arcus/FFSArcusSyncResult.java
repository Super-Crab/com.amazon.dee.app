package com.amazon.whisperjoin.deviceprovisioningservice.arcus;

import android.content.SharedPreferences;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class FFSArcusSyncResult {
    private static final String SHARED_PREF_MODIFIED = "arcusSyncResultModified";
    private static final String SHARED_PREF_TIMESTAMP = "arcusSyncResultTimestamp";
    private final FFSArcusSettings mFFSArcusSettings;
    private final boolean mModified;
    private final long mTimestamp;

    public FFSArcusSyncResult(FFSArcusSettings fFSArcusSettings, boolean z, long j) {
        this.mFFSArcusSettings = fFSArcusSettings;
        this.mModified = z;
        this.mTimestamp = j;
    }

    public static FFSArcusSyncResult readFromSharedPreferences(SharedPreferences sharedPreferences) {
        return new FFSArcusSyncResult(FFSArcusSettings.readFromSharedPreferences(sharedPreferences), sharedPreferences.getBoolean(SHARED_PREF_MODIFIED, false), sharedPreferences.getLong(SHARED_PREF_TIMESTAMP, 0L));
    }

    public static void writeToSharedPreferences(FFSArcusSyncResult fFSArcusSyncResult, SharedPreferences sharedPreferences) {
        FFSArcusSettings.writeToSharedPreferences(fFSArcusSyncResult.mFFSArcusSettings, sharedPreferences);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(SHARED_PREF_MODIFIED, fFSArcusSyncResult.isModified());
        edit.putLong(SHARED_PREF_TIMESTAMP, fFSArcusSyncResult.getTimestamp());
        edit.apply();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FFSArcusSyncResult)) {
            return false;
        }
        FFSArcusSyncResult fFSArcusSyncResult = (FFSArcusSyncResult) obj;
        return this.mModified == fFSArcusSyncResult.mModified && Objects.equal(this.mFFSArcusSettings, fFSArcusSyncResult.mFFSArcusSettings) && Objects.equal(Long.valueOf(this.mTimestamp), Long.valueOf(fFSArcusSyncResult.mTimestamp));
    }

    public FFSArcusSettings getFFSArcusSettings() {
        return this.mFFSArcusSettings;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public int hashCode() {
        return Objects.hashCode(this.mFFSArcusSettings, Boolean.valueOf(this.mModified), Long.valueOf(this.mTimestamp));
    }

    public boolean isModified() {
        return this.mModified;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mFFSArcusSettings", this.mFFSArcusSettings).add("mModified", this.mModified).add("mTimestamp", this.mTimestamp).toString();
    }
}
