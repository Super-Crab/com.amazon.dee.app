package com.amazon.tarazed.type;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleInfo;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum Marketplace {
    US(LocaleInfo.ObfuscatedMarketplaceId.US_MARKETPLACE_ID),
    UK(LocaleInfo.ObfuscatedMarketplaceId.UK_MARKETPLACE_ID),
    DE(LocaleInfo.ObfuscatedMarketplaceId.DE_MARKETPLACE_ID),
    FR(LocaleInfo.ObfuscatedMarketplaceId.FR_MARKETPLACE_ID),
    ES(LocaleInfo.ObfuscatedMarketplaceId.ES_MARKETPLACE_ID, "AJZF8LZ1EJVJN"),
    IN(LocaleInfo.ObfuscatedMarketplaceId.IN_MARKETPLACE_ID, "A2XZLSVIQ0F4JT"),
    IT(LocaleInfo.ObfuscatedMarketplaceId.IT_MARKETPLACE_ID, "A3HOBANJMCMD83"),
    JP(LocaleInfo.ObfuscatedMarketplaceId.JP_MARKETPLACE_ID),
    CA(LocaleInfo.ObfuscatedMarketplaceId.CA_MARKETPLACE_ID),
    AU(LocaleInfo.ObfuscatedMarketplaceId.AU_MARKETPLACE_ID, "A1RNPCQ4K8U27I");
    
    private static final Map<String, Marketplace> MARKETPLACE_ID_MAP = new HashMap();
    private final String devoMarketplaceId;
    private final String prodMarketplaceId;

    static {
        Marketplace[] values;
        for (Marketplace marketplace : values()) {
            MARKETPLACE_ID_MAP.put(marketplace.getProdMarketplaceId(), marketplace);
            MARKETPLACE_ID_MAP.put(marketplace.getDevoMarketplaceId(), marketplace);
        }
    }

    Marketplace(String str) {
        this.prodMarketplaceId = str;
        this.devoMarketplaceId = str;
    }

    public static Marketplace fromMarketplaceId(String str) {
        Marketplace marketplace = MARKETPLACE_ID_MAP.get(str);
        if (marketplace != null) {
            return marketplace;
        }
        throw new UnknownMarketplaceException("Unknown marketplaceId requested");
    }

    public String getDevoMarketplaceId() {
        return this.devoMarketplaceId;
    }

    public String getProdMarketplaceId() {
        return this.prodMarketplaceId;
    }

    Marketplace(String str, String str2) {
        this.prodMarketplaceId = str;
        this.devoMarketplaceId = str2;
    }
}
