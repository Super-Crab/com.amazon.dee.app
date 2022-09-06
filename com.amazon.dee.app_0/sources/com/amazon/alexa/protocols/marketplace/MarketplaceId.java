package com.amazon.alexa.protocols.marketplace;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleInfo;
/* loaded from: classes9.dex */
public enum MarketplaceId {
    CA(LocaleInfo.ObfuscatedMarketplaceId.CA_MARKETPLACE_ID),
    CN(LocaleInfo.ObfuscatedMarketplaceId.CN_MARKETPLACE_ID),
    COM(LocaleInfo.ObfuscatedMarketplaceId.US_MARKETPLACE_ID),
    COM_AU_DEVO("A1RNPCQ4K8U27I"),
    COM_AU_PROD(LocaleInfo.ObfuscatedMarketplaceId.AU_MARKETPLACE_ID),
    COM_BR_DEVO("AZXD3QD5B39HD"),
    COM_BR_PROD(LocaleInfo.ObfuscatedMarketplaceId.BR_MARKETPLACE_ID),
    COM_MX_DEVO("A3P3J5A7D2ZVXI"),
    COM_MX_PROD(LocaleInfo.ObfuscatedMarketplaceId.MX_MARKETPLACE_ID),
    CO_ID_DEVO("A3AXUV5MEX8R86"),
    CO_ID_PROD("A3BUE4CVFSERTV"),
    CO_JP(LocaleInfo.ObfuscatedMarketplaceId.JP_MARKETPLACE_ID),
    CO_UK(LocaleInfo.ObfuscatedMarketplaceId.UK_MARKETPLACE_ID),
    DE(LocaleInfo.ObfuscatedMarketplaceId.DE_MARKETPLACE_ID),
    ES_DEVO("AJZF8LZ1EJVJN"),
    ES_PROD(LocaleInfo.ObfuscatedMarketplaceId.ES_MARKETPLACE_ID),
    FR(LocaleInfo.ObfuscatedMarketplaceId.FR_MARKETPLACE_ID),
    IN(LocaleInfo.ObfuscatedMarketplaceId.IN_MARKETPLACE_ID),
    IT_PROD(LocaleInfo.ObfuscatedMarketplaceId.IT_MARKETPLACE_ID),
    NL_DEVO("A1M3WC0SJ3A38T"),
    NL_PROD(LocaleInfo.ObfuscatedMarketplaceId.NL_MARKETPLACE_ID),
    RU_DEVO("A38NPJYVS5YHNH"),
    RU_PROD(LocaleInfo.ObfuscatedMarketplaceId.RU_MARKETPLACE_ID);
    
    private final String marketplaceId;

    MarketplaceId(@NonNull String str) {
        this.marketplaceId = str;
    }

    @Nullable
    public static MarketplaceId fromString(@Nullable String str) {
        MarketplaceId[] values;
        for (MarketplaceId marketplaceId : values()) {
            if (marketplaceId.toString().equals(str)) {
                return marketplaceId;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.marketplaceId;
    }
}
