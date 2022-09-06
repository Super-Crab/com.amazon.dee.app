package com.amazon.alexa.protocols.marketplace;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public enum Marketplace {
    AUSTRALIA_DEVELOPMENT(MarketplaceName.AU_DEVO, CountryCode.AU, MarketplaceId.COM_AU_DEVO, Region.FAR_EAST),
    AUSTRALIA_PRODUCTION(MarketplaceName.AU, CountryCode.AU, MarketplaceId.COM_AU_PROD, Region.FAR_EAST),
    BRAZIL_DEVELOPMENT(MarketplaceName.BR_DEVO, CountryCode.BR, MarketplaceId.COM_BR_DEVO, Region.NORTH_AMERICA_AND_WORLD),
    BRAZIL_PRODUCTION(MarketplaceName.BR, CountryCode.BR, MarketplaceId.COM_BR_PROD, Region.NORTH_AMERICA_AND_WORLD),
    CANADA(MarketplaceName.CA, CountryCode.CA, MarketplaceId.CA, Region.NORTH_AMERICA_AND_WORLD),
    CHINA(MarketplaceName.CN, CountryCode.CN, MarketplaceId.CN, Region.NORTH_AMERICA_AND_WORLD),
    FRANCE(MarketplaceName.FR, CountryCode.FR, MarketplaceId.FR, Region.EUROPE),
    GERMANY(MarketplaceName.DE, CountryCode.DE, MarketplaceId.DE, Region.EUROPE),
    INDIA(MarketplaceName.IN, CountryCode.IN, MarketplaceId.IN, Region.EUROPE),
    INDONESIA_DEVELOPMENT(MarketplaceName.ID_DEVO, CountryCode.US, MarketplaceId.CO_ID_DEVO, Region.NORTH_AMERICA_AND_WORLD),
    INDONESIA_PRODUCTION(MarketplaceName.ID, CountryCode.US, MarketplaceId.CO_ID_PROD, Region.NORTH_AMERICA_AND_WORLD),
    ITALY_PRODUCTION(MarketplaceName.IT, CountryCode.IT, MarketplaceId.IT_PROD, Region.EUROPE),
    JAPAN(MarketplaceName.JP, CountryCode.JP, MarketplaceId.CO_JP, Region.FAR_EAST),
    MEXICO_DEVELOPMENT(MarketplaceName.MX_DEVO, CountryCode.MX, MarketplaceId.COM_MX_DEVO, Region.NORTH_AMERICA_AND_WORLD),
    MEXICO_PRODUCTION(MarketplaceName.MX, CountryCode.MX, MarketplaceId.COM_MX_PROD, Region.NORTH_AMERICA_AND_WORLD),
    NETHERLANDS_DEVELOPMENT(MarketplaceName.NL_DEVO, CountryCode.NL, MarketplaceId.NL_DEVO, Region.EUROPE),
    NETHERLANDS_PRODUCTION(MarketplaceName.NL, CountryCode.NL, MarketplaceId.NL_PROD, Region.EUROPE),
    RUSSIA_DEVELOPMENT(MarketplaceName.RU_DEVO, CountryCode.GB, MarketplaceId.RU_DEVO, Region.EUROPE),
    RUSSIA_PRODUCTION(MarketplaceName.RU, CountryCode.GB, MarketplaceId.RU_PROD, Region.EUROPE),
    SPAIN(MarketplaceName.ES, CountryCode.ES, MarketplaceId.ES_PROD, Region.EUROPE),
    SPAIN_DEVELOPMENT(MarketplaceName.ES_DEVO, CountryCode.ES, MarketplaceId.ES_DEVO, Region.EUROPE),
    UNITED_KINGDOM(MarketplaceName.GB, CountryCode.GB, MarketplaceId.CO_UK, Region.EUROPE),
    USA(MarketplaceName.US, CountryCode.US, MarketplaceId.COM, Region.NORTH_AMERICA_AND_WORLD);
    
    private final CountryCode countryCode;
    private final MarketplaceName marketplaceName;
    private final MarketplaceId obfuscatedId;
    private final Region region;

    Marketplace(@NonNull MarketplaceName marketplaceName, @NonNull CountryCode countryCode, @NonNull MarketplaceId marketplaceId, @NonNull Region region) {
        this.marketplaceName = marketplaceName;
        this.countryCode = countryCode;
        this.obfuscatedId = marketplaceId;
        this.region = region;
    }

    @Nullable
    public static Marketplace findMarketplaceById(@Nullable String str, @Nullable Marketplace marketplace) {
        Marketplace[] values;
        for (Marketplace marketplace2 : values()) {
            if (marketplace2.getObfuscatedId().equals(MarketplaceId.fromString(str))) {
                return marketplace2;
            }
        }
        return marketplace;
    }

    @Nullable
    public static Marketplace findMarketplaceByName(@Nullable String str, @Nullable Marketplace marketplace) {
        Marketplace[] values;
        for (Marketplace marketplace2 : values()) {
            if (marketplace2.getMarketplaceName().equals(MarketplaceName.fromString(str))) {
                return marketplace2;
            }
        }
        return "UK".equals(str) ? UNITED_KINGDOM : marketplace;
    }

    @NonNull
    public CountryCode getCountryCode() {
        return this.countryCode;
    }

    @NonNull
    public MarketplaceName getMarketplaceName() {
        return this.marketplaceName;
    }

    @NonNull
    public MarketplaceId getObfuscatedId() {
        return this.obfuscatedId;
    }

    @NonNull
    public Region getRegion() {
        return this.region;
    }
}
