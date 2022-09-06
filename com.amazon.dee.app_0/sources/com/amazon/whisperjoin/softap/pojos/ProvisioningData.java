package com.amazon.whisperjoin.softap.pojos;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes13.dex */
public class ProvisioningData {
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("marketplace")
    private String marketplace;
    @SerializedName("utcTime")
    private String utcTime;

    /* loaded from: classes13.dex */
    public static class ProvisioningDataBuilder {
        private String countryCode;
        private String marketplace;
        private String utcTime;

        ProvisioningDataBuilder() {
        }

        public ProvisioningData build() {
            return new ProvisioningData(this.utcTime, this.marketplace, this.countryCode);
        }

        public ProvisioningDataBuilder countryCode(String str) {
            this.countryCode = str;
            return this;
        }

        public ProvisioningDataBuilder marketplace(String str) {
            this.marketplace = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningData.ProvisioningDataBuilder(utcTime=");
            outline107.append(this.utcTime);
            outline107.append(", marketplace=");
            outline107.append(this.marketplace);
            outline107.append(", countryCode=");
            return GeneratedOutlineSupport1.outline91(outline107, this.countryCode, ")");
        }

        public ProvisioningDataBuilder utcTime(String str) {
            this.utcTime = str;
            return this;
        }
    }

    ProvisioningData(String str, String str2, String str3) {
        this.utcTime = str;
        this.marketplace = str2;
        this.countryCode = str3;
    }

    public static ProvisioningDataBuilder builder() {
        return new ProvisioningDataBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ProvisioningData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProvisioningData)) {
            return false;
        }
        ProvisioningData provisioningData = (ProvisioningData) obj;
        if (!provisioningData.canEqual(this)) {
            return false;
        }
        String utcTime = getUtcTime();
        String utcTime2 = provisioningData.getUtcTime();
        if (utcTime != null ? !utcTime.equals(utcTime2) : utcTime2 != null) {
            return false;
        }
        String marketplace = getMarketplace();
        String marketplace2 = provisioningData.getMarketplace();
        if (marketplace != null ? !marketplace.equals(marketplace2) : marketplace2 != null) {
            return false;
        }
        String countryCode = getCountryCode();
        String countryCode2 = provisioningData.getCountryCode();
        return countryCode != null ? countryCode.equals(countryCode2) : countryCode2 == null;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getMarketplace() {
        return this.marketplace;
    }

    public String getUtcTime() {
        return this.utcTime;
    }

    public int hashCode() {
        String utcTime = getUtcTime();
        int i = 43;
        int hashCode = utcTime == null ? 43 : utcTime.hashCode();
        String marketplace = getMarketplace();
        int hashCode2 = ((hashCode + 59) * 59) + (marketplace == null ? 43 : marketplace.hashCode());
        String countryCode = getCountryCode();
        int i2 = hashCode2 * 59;
        if (countryCode != null) {
            i = countryCode.hashCode();
        }
        return i2 + i;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setMarketplace(String str) {
        this.marketplace = str;
    }

    public void setUtcTime(String str) {
        this.utcTime = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningData(utcTime=");
        outline107.append(getUtcTime());
        outline107.append(", marketplace=");
        outline107.append(getMarketplace());
        outline107.append(", countryCode=");
        outline107.append(getCountryCode());
        outline107.append(")");
        return outline107.toString();
    }
}
