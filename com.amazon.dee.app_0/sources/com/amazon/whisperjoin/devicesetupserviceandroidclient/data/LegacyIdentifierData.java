package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class LegacyIdentifierData {
    private final String mDeviceType;
    private final String mDsn;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mDeviceType;
        private String mDsn;

        public LegacyIdentifierData build() {
            return new LegacyIdentifierData(this);
        }

        public Builder setDeviceType(String str) {
            this.mDeviceType = str;
            return this;
        }

        public Builder setDsn(String str) {
            this.mDsn = str;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LegacyIdentifierData(Builder builder) {
        this.mDsn = builder.mDsn;
        this.mDeviceType = builder.mDeviceType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LegacyIdentifierData legacyIdentifierData = (LegacyIdentifierData) obj;
        return Objects.equal(this.mDsn, legacyIdentifierData.mDsn) && Objects.equal(this.mDeviceType, legacyIdentifierData.mDeviceType);
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public String getDsn() {
        return this.mDsn;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDsn, this.mDeviceType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mDsn", this.mDsn).add("mDeviceType", this.mDeviceType).toString();
    }
}
