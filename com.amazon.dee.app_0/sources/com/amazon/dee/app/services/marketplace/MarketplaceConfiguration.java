package com.amazon.dee.app.services.marketplace;

import androidx.annotation.NonNull;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceEndpoints;
import com.amazon.dee.app.BuildConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class MarketplaceConfiguration implements MarketplaceEndpoints {
    private final String apiGatewayHost;
    private final String authAssociationHandle;
    private final String authWebHost;
    private final String coralHost;
    private final String retailHost;
    private final String skillsStoreEndpoint;
    private final String webHost;
    public static final MarketplaceConfiguration AUSTRALIA = new MarketplaceConfiguration(BuildConfig.HOST_AU, BuildConfig.HOST_AU, BuildConfig.AUTH_HOST_AU, BuildConfig.AUTH_ASSOCIATION_HANDLE_AU, "api.fe.amazonalexa.com", BuildConfig.RETAIL_HOST_AU, BuildConfig.SKILLS_STORE_ENDPOINT_AU);
    public static final MarketplaceConfiguration BRAZIL = new MarketplaceConfiguration(BuildConfig.HOST_BR, BuildConfig.HOST_BR, BuildConfig.AUTH_HOST_BR, BuildConfig.AUTH_ASSOCIATION_HANDLE_BR, "api.amazonalexa.com", BuildConfig.RETAIL_HOST_BR, BuildConfig.SKILLS_STORE_ENDPOINT_BR);
    public static final MarketplaceConfiguration CANADA = new MarketplaceConfiguration(BuildConfig.HOST_CA, BuildConfig.HOST_CA, BuildConfig.AUTH_HOST_CA, BuildConfig.AUTH_ASSOCIATION_HANDLE_CA, "api.amazonalexa.com", BuildConfig.RETAIL_HOST_CA, BuildConfig.SKILLS_STORE_ENDPOINT_CA);
    public static final MarketplaceConfiguration CHINA = new MarketplaceConfiguration(BuildConfig.HOST_CN, BuildConfig.HOST_CN, BuildConfig.AUTH_HOST_CN, BuildConfig.AUTH_ASSOCIATION_HANDLE_CN, "", BuildConfig.RETAIL_HOST_CN, "");
    public static final MarketplaceConfiguration FRANCE = new MarketplaceConfiguration(BuildConfig.HOST_FR, BuildConfig.HOST_FR, BuildConfig.AUTH_HOST_FR, BuildConfig.AUTH_ASSOCIATION_HANDLE_FR, "api.eu.amazonalexa.com", BuildConfig.RETAIL_HOST_FR, BuildConfig.SKILLS_STORE_ENDPOINT_FR);
    public static final MarketplaceConfiguration SPAIN = new MarketplaceConfiguration(BuildConfig.HOST_ES, BuildConfig.HOST_ES, BuildConfig.AUTH_HOST_ES, BuildConfig.AUTH_ASSOCIATION_HANDLE_ES, "api.eu.amazonalexa.com", BuildConfig.RETAIL_HOST_ES, BuildConfig.SKILLS_STORE_ENDPOINT_ES);
    public static final MarketplaceConfiguration GERMANY = new MarketplaceConfiguration(BuildConfig.HOST_DE, BuildConfig.HOST_DE, BuildConfig.AUTH_HOST_DE, BuildConfig.AUTH_ASSOCIATION_HANDLE_DE, "api.eu.amazonalexa.com", BuildConfig.RETAIL_HOST_DE, BuildConfig.SKILLS_STORE_ENDPOINT_DE);
    public static final MarketplaceConfiguration INDIA = new MarketplaceConfiguration(BuildConfig.HOST_IN, BuildConfig.HOST_IN, BuildConfig.AUTH_HOST_IN, BuildConfig.AUTH_ASSOCIATION_HANDLE_IN, "api.eu.amazonalexa.com", BuildConfig.RETAIL_HOST_IN, BuildConfig.SKILLS_STORE_ENDPOINT_IN);
    public static final MarketplaceConfiguration ITALY = new MarketplaceConfiguration(BuildConfig.HOST_IT, BuildConfig.HOST_IT, BuildConfig.AUTH_HOST_IT, BuildConfig.AUTH_ASSOCIATION_HANDLE_IT, "api.eu.amazonalexa.com", BuildConfig.RETAIL_HOST_IT, BuildConfig.SKILLS_STORE_ENDPOINT_IT);
    public static final MarketplaceConfiguration JAPAN = new MarketplaceConfiguration(BuildConfig.HOST_JP, BuildConfig.HOST_JP, BuildConfig.AUTH_HOST_JP, BuildConfig.AUTH_ASSOCIATION_HANDLE_JP, "api.fe.amazonalexa.com", BuildConfig.RETAIL_HOST_JP, BuildConfig.SKILLS_STORE_ENDPOINT_JP);
    public static final MarketplaceConfiguration MEXICO = new MarketplaceConfiguration(BuildConfig.HOST_MX, BuildConfig.HOST_MX, BuildConfig.AUTH_HOST_MX, BuildConfig.AUTH_ASSOCIATION_HANDLE_MX, "api.amazonalexa.com", BuildConfig.RETAIL_HOST_MX, BuildConfig.SKILLS_STORE_ENDPOINT_MX);
    public static final MarketplaceConfiguration NETHERLANDS = new MarketplaceConfiguration(BuildConfig.HOST_NL, BuildConfig.HOST_NL, BuildConfig.AUTH_HOST_NL, BuildConfig.AUTH_ASSOCIATION_HANDLE_NL, "api.eu.amazonalexa.com", BuildConfig.RETAIL_HOST_NL, BuildConfig.SKILLS_STORE_ENDPOINT_NL);
    public static final MarketplaceConfiguration UNITED_KINGDOM = new MarketplaceConfiguration(BuildConfig.HOST_UK, BuildConfig.HOST_UK, BuildConfig.AUTH_HOST_UK, BuildConfig.AUTH_ASSOCIATION_HANDLE_UK, "api.eu.amazonalexa.com", BuildConfig.RETAIL_HOST_UK, BuildConfig.SKILLS_STORE_ENDPOINT_UK);
    public static final MarketplaceConfiguration USA = new MarketplaceConfiguration(BuildConfig.HOST, BuildConfig.HOST, BuildConfig.AUTH_HOST, BuildConfig.AUTH_ASSOCIATION_HANDLE, "api.amazonalexa.com", BuildConfig.RETAIL_HOST, BuildConfig.SKILLS_STORE_ENDPOINT);

    /* renamed from: com.amazon.dee.app.services.marketplace.MarketplaceConfiguration$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace = new int[Marketplace.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.USA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.INDONESIA_PRODUCTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.INDONESIA_DEVELOPMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.UNITED_KINGDOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.RUSSIA_PRODUCTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.RUSSIA_DEVELOPMENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.GERMANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.FRANCE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.SPAIN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.SPAIN_DEVELOPMENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.INDIA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.ITALY_PRODUCTION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.JAPAN.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.CANADA.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.CHINA.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.BRAZIL_PRODUCTION.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.BRAZIL_DEVELOPMENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.MEXICO_PRODUCTION.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.MEXICO_DEVELOPMENT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.AUSTRALIA_PRODUCTION.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.AUSTRALIA_DEVELOPMENT.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.NETHERLANDS_PRODUCTION.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Marketplace[Marketplace.NETHERLANDS_DEVELOPMENT.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    private MarketplaceConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.coralHost = str;
        this.webHost = str2;
        this.authWebHost = str3;
        this.authAssociationHandle = str4;
        this.apiGatewayHost = str5;
        this.retailHost = str6;
        this.skillsStoreEndpoint = str7;
    }

    public static MarketplaceConfiguration forMarketplace(Marketplace marketplace) {
        switch (marketplace.ordinal()) {
            case 0:
            case 1:
                return AUSTRALIA;
            case 2:
            case 3:
                return BRAZIL;
            case 4:
                return CANADA;
            case 5:
                return CHINA;
            case 6:
                return FRANCE;
            case 7:
                return GERMANY;
            case 8:
                return INDIA;
            case 9:
            case 10:
            case 22:
                return USA;
            case 11:
                return ITALY;
            case 12:
                return JAPAN;
            case 13:
            case 14:
                return MEXICO;
            case 15:
            case 16:
                return NETHERLANDS;
            case 17:
            case 18:
            case 21:
                return UNITED_KINGDOM;
            case 19:
            case 20:
                return SPAIN;
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown marketplace: ");
                outline107.append(marketplace.toString());
                throw new IllegalArgumentException(outline107.toString());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MarketplaceConfiguration.class != obj.getClass()) {
            return false;
        }
        MarketplaceConfiguration marketplaceConfiguration = (MarketplaceConfiguration) obj;
        if (!this.coralHost.equals(marketplaceConfiguration.coralHost) || !this.webHost.equals(marketplaceConfiguration.webHost) || !this.authAssociationHandle.equals(marketplaceConfiguration.authAssociationHandle) || !this.apiGatewayHost.equals(marketplaceConfiguration.apiGatewayHost) || !this.retailHost.equals(marketplaceConfiguration.retailHost) || !this.skillsStoreEndpoint.equals(marketplaceConfiguration.skillsStoreEndpoint)) {
            return false;
        }
        return this.authWebHost.equals(marketplaceConfiguration.authWebHost);
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceEndpoints
    @NonNull
    public String getApiGatewayHost() {
        return this.apiGatewayHost;
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceEndpoints
    public String getAuthAssociationHandle() {
        return this.authAssociationHandle;
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceEndpoints
    @NonNull
    public String getAuthWebHost() {
        return this.authWebHost;
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceEndpoints
    @NonNull
    public String getCoralHost() {
        return this.coralHost;
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceEndpoints
    @NonNull
    public String getRetailHost() {
        return this.retailHost;
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceEndpoints
    @NonNull
    public String getSkillsStoreEndpoint() {
        return this.skillsStoreEndpoint;
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceEndpoints
    @NonNull
    public String getWebHost() {
        return this.webHost;
    }

    public int hashCode() {
        return this.skillsStoreEndpoint.hashCode() + GeneratedOutlineSupport1.outline7(this.retailHost, GeneratedOutlineSupport1.outline7(this.apiGatewayHost, GeneratedOutlineSupport1.outline7(this.authAssociationHandle, GeneratedOutlineSupport1.outline7(this.authWebHost, GeneratedOutlineSupport1.outline7(this.webHost, this.coralHost.hashCode() * 31, 31), 31), 31), 31), 31);
    }
}
