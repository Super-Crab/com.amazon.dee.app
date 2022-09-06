package com.amazon.alexa.utils.marketplace;

import android.text.TextUtils;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleInfo;
/* loaded from: classes10.dex */
public enum Marketplace {
    US(LocaleInfo.ObfuscatedMarketplaceId.US_MARKETPLACE_ID),
    GB(LocaleInfo.ObfuscatedMarketplaceId.UK_MARKETPLACE_ID),
    DE(LocaleInfo.ObfuscatedMarketplaceId.DE_MARKETPLACE_ID),
    JP(LocaleInfo.ObfuscatedMarketplaceId.JP_MARKETPLACE_ID),
    IN(LocaleInfo.ObfuscatedMarketplaceId.IN_MARKETPLACE_ID),
    IN_DEVO("A2XZLSVIQ0F4JT"),
    FR(LocaleInfo.ObfuscatedMarketplaceId.FR_MARKETPLACE_ID),
    ES(LocaleInfo.ObfuscatedMarketplaceId.ES_MARKETPLACE_ID),
    ES_DEVO("AJZF8LZ1EJVJN"),
    IT(LocaleInfo.ObfuscatedMarketplaceId.IT_MARKETPLACE_ID),
    IT_DEVO("A3HOBANJMCMD83"),
    CA(LocaleInfo.ObfuscatedMarketplaceId.CA_MARKETPLACE_ID),
    CN(LocaleInfo.ObfuscatedMarketplaceId.CN_MARKETPLACE_ID),
    BR(LocaleInfo.ObfuscatedMarketplaceId.BR_MARKETPLACE_ID),
    BR_DEVO("AZXD3QD5B39HD"),
    MX(LocaleInfo.ObfuscatedMarketplaceId.MX_MARKETPLACE_ID),
    MX_DEVO("A3P3J5A7D2ZVXI"),
    AU(LocaleInfo.ObfuscatedMarketplaceId.AU_MARKETPLACE_ID),
    AU_DEVO("A1RNPCQ4K8U27I"),
    RU(LocaleInfo.ObfuscatedMarketplaceId.RU_MARKETPLACE_ID),
    RU_DEVO("A38NPJYVS5YHNH"),
    NL(LocaleInfo.ObfuscatedMarketplaceId.NL_MARKETPLACE_ID),
    NL_DEVO("A1M3WC0SJ3A38T"),
    ID("A3BUE4CVFSERTV"),
    ID_DEVO("A3AXUV5MEX8R86"),
    AE(LocaleInfo.ObfuscatedMarketplaceId.AE_MARKETPLACE_ID),
    AE_DEVO("A34GYYCZVDBSIK"),
    SA(LocaleInfo.ObfuscatedMarketplaceId.SA_MARKETPLACE_ID),
    SA_DEVO("A1MQPSGJ6U9Q54");
    
    public final String id;

    Marketplace(String str) {
        this.id = str;
    }

    @Nullable
    public static Marketplace findMarketplaceById(String str, @Nullable Marketplace marketplace) {
        Marketplace[] values;
        for (Marketplace marketplace2 : values()) {
            if (TextUtils.equals(marketplace2.id, str)) {
                return marketplace2;
            }
        }
        return marketplace;
    }
}
