package com.amazon.deecomms.api;

import java.util.Objects;
/* loaded from: classes12.dex */
public class MarketplaceInfo {
    private boolean isPFMset;
    private String marketplaceId;
    private String marketplaceIdCode;
    private String pfmCode;

    public MarketplaceInfo() {
        this.isPFMset = false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MarketplaceInfo.class != obj.getClass()) {
            return false;
        }
        MarketplaceInfo marketplaceInfo = (MarketplaceInfo) obj;
        return Objects.equals(this.marketplaceId, marketplaceInfo.marketplaceId) && Objects.equals(this.marketplaceIdCode, marketplaceInfo.marketplaceIdCode) && Objects.equals(this.pfmCode, marketplaceInfo.pfmCode) && this.isPFMset == marketplaceInfo.isPFMset;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMarketplaceIdCode() {
        return this.marketplaceIdCode;
    }

    public String getPfmCode() {
        return this.pfmCode;
    }

    public int hashCode() {
        return Objects.hash(this.marketplaceId, this.marketplaceIdCode, this.pfmCode, Boolean.valueOf(this.isPFMset));
    }

    public boolean isPFMset() {
        return this.isPFMset;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMarketplaceIdCode(String str) {
        this.marketplaceIdCode = str;
    }

    public void setPfmCode(String str) {
        this.pfmCode = str;
    }

    public MarketplaceInfo(String str, String str2, String str3, boolean z) {
        this.isPFMset = false;
        this.marketplaceId = str;
        this.marketplaceIdCode = str2;
        this.pfmCode = str3;
        this.isPFMset = z;
    }
}
