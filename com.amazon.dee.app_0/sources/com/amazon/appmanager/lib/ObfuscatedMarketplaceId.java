package com.amazon.appmanager.lib;
/* loaded from: classes11.dex */
enum ObfuscatedMarketplaceId {
    ATVPDKIKX0DER("us-20"),
    A2EUQ1WTGCTBG2("ca-20"),
    A1AM78C64UM0Y8("mx-20"),
    A2Q3Y263D00KWC("br-20"),
    A1F83G8C2ARO7P("uk-21"),
    A1PA6795UKMFR9("de-21"),
    A13V1IB3VIYZZH("fr-21"),
    APJ6JRA9NG5V4("it-21"),
    A1RKKUPIHCS9HS("es-21"),
    A21TJRUUN4KGV("in-21"),
    A1VC38T7YXB528("jp-22"),
    A39IBJ37TRP1C6("au-22"),
    A33AVAJ2PDY3EV("tr-21"),
    AAHKV2X7AFYLW("cn-23");
    
    private String regionId;

    ObfuscatedMarketplaceId(String str) {
        this.regionId = str;
    }

    public String toRegionId() {
        return this.regionId;
    }
}
