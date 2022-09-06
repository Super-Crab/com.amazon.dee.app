package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import android.content.SharedPreferences;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class AssociatedDeviceCredentialsSyncResult {
    private static final String SHARED_PREF_CREDENTIALS_JSON = "associatedDeviceCredentialsSyncResultEntryJSON";
    private static final String SHARED_PREF_TIMESTAMP = "associatedDeviceCredentialsSyncResultTimestamp";
    private final String mCredentialsJSON;
    private final long mTimestamp;

    public AssociatedDeviceCredentialsSyncResult(String str, long j) {
        this.mCredentialsJSON = str;
        this.mTimestamp = j;
    }

    public static AssociatedDeviceCredentialsSyncResult readFromSharedPreferences(SharedPreferences sharedPreferences) {
        if (!sharedPreferences.contains(SHARED_PREF_CREDENTIALS_JSON)) {
            return null;
        }
        return new AssociatedDeviceCredentialsSyncResult(sharedPreferences.getString(SHARED_PREF_CREDENTIALS_JSON, "{}"), sharedPreferences.getLong(SHARED_PREF_TIMESTAMP, 0L));
    }

    public static void writeToSharedPreferences(AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(SHARED_PREF_CREDENTIALS_JSON, associatedDeviceCredentialsSyncResult.getCredentialsJSON());
        edit.putLong(SHARED_PREF_TIMESTAMP, associatedDeviceCredentialsSyncResult.getTimestamp());
        edit.apply();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AssociatedDeviceCredentialsSyncResult.class != obj.getClass()) {
            return false;
        }
        AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult = (AssociatedDeviceCredentialsSyncResult) obj;
        return new EqualsBuilder().append(this.mTimestamp, associatedDeviceCredentialsSyncResult.mTimestamp).append(this.mCredentialsJSON, associatedDeviceCredentialsSyncResult.mCredentialsJSON).isEquals();
    }

    public String getCredentialsJSON() {
        return this.mCredentialsJSON;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mCredentialsJSON).append(this.mTimestamp).toHashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AssociatedDeviceCredentialsSyncResult{mCredentialsJSON='");
        GeneratedOutlineSupport1.outline176(outline107, this.mCredentialsJSON, Chars.QUOTE, ", mTimestamp=");
        outline107.append(this.mTimestamp);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
