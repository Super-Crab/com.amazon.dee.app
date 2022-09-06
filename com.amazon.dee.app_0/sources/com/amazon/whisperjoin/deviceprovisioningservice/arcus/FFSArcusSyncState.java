package com.amazon.whisperjoin.deviceprovisioningservice.arcus;

import android.content.SharedPreferences;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/* loaded from: classes13.dex */
public class FFSArcusSyncState {
    private static final String SHARED_PREF_SYNC_BLOCKED_UNTIL_TIMESTAMP_MILLIS = "arcusSyncStateSyncBlockedUntilTimestampMillis";
    private final long mSyncBlockedUntilTimestampMillis;

    public FFSArcusSyncState(long j) {
        this.mSyncBlockedUntilTimestampMillis = j;
    }

    public static FFSArcusSyncState readFromSharedPreferences(SharedPreferences sharedPreferences) {
        return new FFSArcusSyncState(sharedPreferences.getLong(SHARED_PREF_SYNC_BLOCKED_UNTIL_TIMESTAMP_MILLIS, 0L));
    }

    public static void writeToSharedPreferences(FFSArcusSyncState fFSArcusSyncState, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(SHARED_PREF_SYNC_BLOCKED_UNTIL_TIMESTAMP_MILLIS, fFSArcusSyncState.getSyncBlockedUntilTimestampMillis());
        edit.apply();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && FFSArcusSyncState.class == obj.getClass()) {
            return new EqualsBuilder().append(this.mSyncBlockedUntilTimestampMillis, ((FFSArcusSyncState) obj).mSyncBlockedUntilTimestampMillis).isEquals();
        }
        return false;
    }

    public long getSyncBlockedUntilTimestampMillis() {
        return this.mSyncBlockedUntilTimestampMillis;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mSyncBlockedUntilTimestampMillis).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("mSyncBlockedUntilTimestampMillis", this.mSyncBlockedUntilTimestampMillis).toString();
    }
}
