package com.amazon.commscore.api.identity;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class MarketplaceInfo {
    private String marketPlaceCountryCode;
    private String marketPlaceId;
    private String pfmCode;

    @Nullable
    public String getMarketPlaceCountryCode() {
        return this.marketPlaceCountryCode;
    }

    @Nullable
    public String getMarketPlaceId() {
        return this.marketPlaceId;
    }

    @Nullable
    public String getPfmCode() {
        return this.pfmCode;
    }

    public void setMarketPlaceCountryCode(String str) {
        this.marketPlaceCountryCode = str;
    }

    public void setMarketPlaceId(String str) {
        this.marketPlaceId = str;
    }

    public void setPfmCode(String str) {
        this.pfmCode = str;
    }
}
