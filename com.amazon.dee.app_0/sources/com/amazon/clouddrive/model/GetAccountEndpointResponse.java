package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetAccountEndpointResponse implements CloudDriveResponse {
    private String contentUrl;
    private String countryAtSignup;
    private boolean customerExists;
    private String marketplaceAtSignup;
    private String metadataUrl;
    private String region;
    private String retailUrl;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountEndpointResponse) && compareTo((CloudDriveResponse) ((GetAccountEndpointResponse) obj)) == 0;
    }

    public String getContentUrl() {
        return this.contentUrl;
    }

    public String getCountryAtSignup() {
        return this.countryAtSignup;
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

    public int hashCode() {
        int i = 0;
        int hashCode = (getContentUrl() == null ? 0 : getContentUrl().hashCode()) + 1 + (getMarketplaceAtSignup() == null ? 0 : getMarketplaceAtSignup().hashCode()) + (getCountryAtSignup() == null ? 0 : getCountryAtSignup().hashCode()) + (isCustomerExists() ? 1 : 0) + (getMetadataUrl() == null ? 0 : getMetadataUrl().hashCode()) + (getRegion() == null ? 0 : getRegion().hashCode());
        if (getRetailUrl() != null) {
            i = getRetailUrl().hashCode();
        }
        return hashCode + i;
    }

    public boolean isCustomerExists() {
        return this.customerExists;
    }

    public void setContentUrl(String str) {
        this.contentUrl = str;
    }

    public void setCountryAtSignup(String str) {
        this.countryAtSignup = str;
    }

    public void setCustomerExists(boolean z) {
        this.customerExists = z;
    }

    public void setMarketplaceAtSignup(String str) {
        this.marketplaceAtSignup = str;
    }

    public void setMetadataUrl(String str) {
        this.metadataUrl = str;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public void setRetailUrl(String str) {
        this.retailUrl = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetAccountEndpointResponse)) {
            return 1;
        }
        GetAccountEndpointResponse getAccountEndpointResponse = (GetAccountEndpointResponse) cloudDriveResponse;
        String contentUrl = getContentUrl();
        String contentUrl2 = getAccountEndpointResponse.getContentUrl();
        if (contentUrl != contentUrl2) {
            if (contentUrl == null) {
                return -1;
            }
            if (contentUrl2 == null) {
                return 1;
            }
            int compareTo = contentUrl.compareTo(contentUrl2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String marketplaceAtSignup = getMarketplaceAtSignup();
        String marketplaceAtSignup2 = getAccountEndpointResponse.getMarketplaceAtSignup();
        if (marketplaceAtSignup != marketplaceAtSignup2) {
            if (marketplaceAtSignup == null) {
                return -1;
            }
            if (marketplaceAtSignup2 == null) {
                return 1;
            }
            int compareTo2 = marketplaceAtSignup.compareTo(marketplaceAtSignup2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String countryAtSignup = getCountryAtSignup();
        String countryAtSignup2 = getAccountEndpointResponse.getCountryAtSignup();
        if (countryAtSignup != countryAtSignup2) {
            if (countryAtSignup == null) {
                return -1;
            }
            if (countryAtSignup2 == null) {
                return 1;
            }
            int compareTo3 = countryAtSignup.compareTo(countryAtSignup2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        if (!isCustomerExists() && getAccountEndpointResponse.isCustomerExists()) {
            return -1;
        }
        if (isCustomerExists() && !getAccountEndpointResponse.isCustomerExists()) {
            return 1;
        }
        String metadataUrl = getMetadataUrl();
        String metadataUrl2 = getAccountEndpointResponse.getMetadataUrl();
        if (metadataUrl != metadataUrl2) {
            if (metadataUrl == null) {
                return -1;
            }
            if (metadataUrl2 == null) {
                return 1;
            }
            int compareTo4 = metadataUrl.compareTo(metadataUrl2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String region = getRegion();
        String region2 = getAccountEndpointResponse.getRegion();
        if (region != region2) {
            if (region == null) {
                return -1;
            }
            if (region2 == null) {
                return 1;
            }
            int compareTo5 = region.compareTo(region2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        String retailUrl = getRetailUrl();
        String retailUrl2 = getAccountEndpointResponse.getRetailUrl();
        if (retailUrl != retailUrl2) {
            if (retailUrl == null) {
                return -1;
            }
            if (retailUrl2 == null) {
                return 1;
            }
            int compareTo6 = retailUrl.compareTo(retailUrl2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        return 0;
    }
}
