package com.amazon.whisperjoin.deviceprovisioningservice.service;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class CBLGenerationConfiguration {
    private static final String CUSTOMER_ID_KEY = "CBLGenerationConfiguration.CustomerID";
    private static final String PREFIX = "CBLGenerationConfiguration.";
    private static final String PROFILE_ID_KEY = "CBLGenerationConfiguration.ProfileID";
    private static final String USE_GIVEN_CID_AND_PID_KEY = "CBLGenerationConfiguration.useGivenPidAndCid";
    private final String mCustomerID;
    private final String mProfileID;
    private final Boolean mUseGivenCidAndPid;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mCustomerID;
        private String mProfileID;
        private Boolean mUseGivenCidAndPid;

        public CBLGenerationConfiguration build() {
            Boolean bool = this.mUseGivenCidAndPid;
            if (bool != null) {
                String str = this.mCustomerID;
                if (str != null) {
                    String str2 = this.mProfileID;
                    if (str2 != null) {
                        return new CBLGenerationConfiguration(bool, str, str2);
                    }
                    throw new IllegalArgumentException("mProfileID can not be null");
                }
                throw new IllegalArgumentException("mCustomerID can not be null");
            }
            throw new IllegalArgumentException("mUseGivenCidAndPid can not be null");
        }

        public Builder setCustomerID(String str) {
            this.mCustomerID = str;
            return this;
        }

        public Builder setProfileID(String str) {
            this.mProfileID = str;
            return this;
        }

        public Builder setUseGivenCidAndPid(Boolean bool) {
            this.mUseGivenCidAndPid = bool;
            return this;
        }
    }

    public static CBLGenerationConfiguration Default() {
        return new CBLGenerationConfiguration(false, "", "");
    }

    public static CBLGenerationConfiguration fromBundle(Bundle bundle) {
        if (bundle != null) {
            boolean z = bundle.getBoolean(USE_GIVEN_CID_AND_PID_KEY, false);
            return new CBLGenerationConfiguration(Boolean.valueOf(z), bundle.getString(CUSTOMER_ID_KEY, null), bundle.getString(PROFILE_ID_KEY, null));
        }
        throw new IllegalArgumentException("bundle can not be null");
    }

    public static CBLGenerationConfiguration fromSharedPreferences(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            boolean z = sharedPreferences.getBoolean(USE_GIVEN_CID_AND_PID_KEY, false);
            return new CBLGenerationConfiguration(Boolean.valueOf(z), sharedPreferences.getString(CUSTOMER_ID_KEY, null), sharedPreferences.getString(PROFILE_ID_KEY, null));
        }
        throw new IllegalArgumentException("sharedPreferences can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CBLGenerationConfiguration.class != obj.getClass()) {
            return false;
        }
        CBLGenerationConfiguration cBLGenerationConfiguration = (CBLGenerationConfiguration) obj;
        return Objects.equal(this.mUseGivenCidAndPid, cBLGenerationConfiguration.mUseGivenCidAndPid) && Objects.equal(this.mCustomerID, cBLGenerationConfiguration.mCustomerID) && Objects.equal(this.mProfileID, cBLGenerationConfiguration.mProfileID);
    }

    public String getCustomerID() {
        return this.mCustomerID;
    }

    public String getProfileID() {
        return this.mProfileID;
    }

    public Boolean getUseGivenCidAndPid() {
        return this.mUseGivenCidAndPid;
    }

    public int hashCode() {
        return Objects.hashCode(this.mUseGivenCidAndPid, this.mCustomerID, this.mProfileID);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mUseGivenCidAndPid", this.mUseGivenCidAndPid).add("mCustomerID", this.mCustomerID).add("mProfileID", this.mProfileID).toString();
    }

    public void writeToBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.putBoolean(USE_GIVEN_CID_AND_PID_KEY, this.mUseGivenCidAndPid.booleanValue());
            bundle.putString(CUSTOMER_ID_KEY, this.mCustomerID);
            bundle.putString(PROFILE_ID_KEY, this.mProfileID);
            return;
        }
        throw new IllegalArgumentException("bundle can not be null");
    }

    public void writeToSharedPreferences(SharedPreferences.Editor editor) {
        if (editor != null) {
            editor.putBoolean(USE_GIVEN_CID_AND_PID_KEY, this.mUseGivenCidAndPid.booleanValue());
            editor.putString(CUSTOMER_ID_KEY, this.mCustomerID);
            editor.putString(PROFILE_ID_KEY, this.mProfileID);
            editor.apply();
            return;
        }
        throw new IllegalArgumentException("editor can not be null");
    }

    private CBLGenerationConfiguration(Boolean bool, String str, String str2) {
        this.mUseGivenCidAndPid = bool;
        this.mCustomerID = str;
        this.mProfileID = str2;
    }
}
