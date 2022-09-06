package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetEndpointResponse extends CloudDriveResponse {
    @JsonProperty("contentUrl")
    private String contentUrl;
    @JsonProperty("countryAtSignup")
    private String countryAtSignup;
    @JsonProperty("customerExists")
    private Boolean customerExists;
    @JsonProperty("marketplaceAtSignup")
    private String marketplaceAtSignup;
    @JsonProperty("metadataUrl")
    private String metadataUrl;
    @JsonProperty("region")
    private String region;
    @JsonProperty("retailUrl")
    private String retailUrl;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GetEndpointResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetEndpointResponse)) {
            return false;
        }
        GetEndpointResponse getEndpointResponse = (GetEndpointResponse) obj;
        if (!getEndpointResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String retailUrl = getRetailUrl();
        String retailUrl2 = getEndpointResponse.getRetailUrl();
        if (retailUrl != null ? !retailUrl.equals(retailUrl2) : retailUrl2 != null) {
            return false;
        }
        String metadataUrl = getMetadataUrl();
        String metadataUrl2 = getEndpointResponse.getMetadataUrl();
        if (metadataUrl != null ? !metadataUrl.equals(metadataUrl2) : metadataUrl2 != null) {
            return false;
        }
        Boolean customerExists = getCustomerExists();
        Boolean customerExists2 = getEndpointResponse.getCustomerExists();
        if (customerExists != null ? !customerExists.equals(customerExists2) : customerExists2 != null) {
            return false;
        }
        String countryAtSignup = getCountryAtSignup();
        String countryAtSignup2 = getEndpointResponse.getCountryAtSignup();
        if (countryAtSignup != null ? !countryAtSignup.equals(countryAtSignup2) : countryAtSignup2 != null) {
            return false;
        }
        String marketplaceAtSignup = getMarketplaceAtSignup();
        String marketplaceAtSignup2 = getEndpointResponse.getMarketplaceAtSignup();
        if (marketplaceAtSignup != null ? !marketplaceAtSignup.equals(marketplaceAtSignup2) : marketplaceAtSignup2 != null) {
            return false;
        }
        String contentUrl = getContentUrl();
        String contentUrl2 = getEndpointResponse.getContentUrl();
        if (contentUrl != null ? !contentUrl.equals(contentUrl2) : contentUrl2 != null) {
            return false;
        }
        String region = getRegion();
        String region2 = getEndpointResponse.getRegion();
        return region != null ? region.equals(region2) : region2 == null;
    }

    public String getContentUrl() {
        return this.contentUrl;
    }

    public String getCountryAtSignup() {
        return this.countryAtSignup;
    }

    public Boolean getCustomerExists() {
        return this.customerExists;
    }

    public String getMarketplaceAtSignup() {
        return this.marketplaceAtSignup;
    }

    public String getMetadataUrl() {
        return this.metadataUrl;
    }

    public String getRegion() {
        return this.region;
    }

    public String getRetailUrl() {
        return this.retailUrl;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        String retailUrl = getRetailUrl();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (retailUrl == null ? 43 : retailUrl.hashCode());
        String metadataUrl = getMetadataUrl();
        int hashCode3 = (hashCode2 * 59) + (metadataUrl == null ? 43 : metadataUrl.hashCode());
        Boolean customerExists = getCustomerExists();
        int hashCode4 = (hashCode3 * 59) + (customerExists == null ? 43 : customerExists.hashCode());
        String countryAtSignup = getCountryAtSignup();
        int hashCode5 = (hashCode4 * 59) + (countryAtSignup == null ? 43 : countryAtSignup.hashCode());
        String marketplaceAtSignup = getMarketplaceAtSignup();
        int hashCode6 = (hashCode5 * 59) + (marketplaceAtSignup == null ? 43 : marketplaceAtSignup.hashCode());
        String contentUrl = getContentUrl();
        int hashCode7 = (hashCode6 * 59) + (contentUrl == null ? 43 : contentUrl.hashCode());
        String region = getRegion();
        int i2 = hashCode7 * 59;
        if (region != null) {
            i = region.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("contentUrl")
    public void setContentUrl(String str) {
        this.contentUrl = str;
    }

    @JsonProperty("countryAtSignup")
    public void setCountryAtSignup(String str) {
        this.countryAtSignup = str;
    }

    @JsonProperty("customerExists")
    public void setCustomerExists(Boolean bool) {
        this.customerExists = bool;
    }

    @JsonProperty("marketplaceAtSignup")
    public void setMarketplaceAtSignup(String str) {
        this.marketplaceAtSignup = str;
    }

    @JsonProperty("metadataUrl")
    public void setMetadataUrl(String str) {
        this.metadataUrl = str;
    }

    @JsonProperty("region")
    public void setRegion(String str) {
        this.region = str;
    }

    @JsonProperty("retailUrl")
    public void setRetailUrl(String str) {
        this.retailUrl = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetEndpointResponse(retailUrl=");
        outline107.append(getRetailUrl());
        outline107.append(", metadataUrl=");
        outline107.append(getMetadataUrl());
        outline107.append(", customerExists=");
        outline107.append(getCustomerExists());
        outline107.append(", countryAtSignup=");
        outline107.append(getCountryAtSignup());
        outline107.append(", marketplaceAtSignup=");
        outline107.append(getMarketplaceAtSignup());
        outline107.append(", contentUrl=");
        outline107.append(getContentUrl());
        outline107.append(", region=");
        outline107.append(getRegion());
        outline107.append(")");
        return outline107.toString();
    }
}
