package com.amazon.dee.app.services.environment;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceEndpoints;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.util.WebUtils;
import dagger.Lazy;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class DefaultEnvironmentService implements EnvironmentService {
    @VisibleForTesting
    static final String AMAZON_BASE_URL_REGEX = "https://((au|br|ca|cn|de|es|fr|id|in|it|jp|mx|nl|ru|uk)-development|development|gamma|pre-prod|www).amazon.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)";
    private static final String AMAZON_DOMAIN_SUFFIXES_REGEX = "(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)";
    private static final String AMAZON_FORUM_RETAIL_REGEX = "^https?://(|[\\w\\.-]+\\.)amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)(/help/.*/forum.*|/gp/redirect\\.html.*|/ap.*|/gp/help/customer/display\\.html.*)";
    private static final String AMAZON_FORUM_SALESFORCE_AUTH_REGEX = "^https?://[\\w\\.-]+\\.salesforce\\.com/services/authcallback/.*";
    private static final String AMAZON_FORUM_URL_REGEX = "^https?://[\\w\\.-]+\\.amazonforum\\.com/.*";
    private static final String AMAZON_RETAIL_URL_REGEX = "^(|https?://)(|[\\w\\.-]+\\.)amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)($|/.*)";
    private static final String AMAZON_ROOT_URL_REGEX = "^https?://[\\w\\.-]+\\.amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/$";
    private static final String AMAZON_TIV_DOMAIN_REGEX = "(|www\\.)amazon\\.(ae|ca|co.jp|co.uk|com|com.au|com.br|com.mx|com.sg|com.tr|de|eg|es|fr|in|it|jp|nl|sa|se|sg)";
    private static final String AMAZON_TIV_DOMAIN_SUFFIXES_REGEX = "(ae|ca|co.jp|co.uk|com|com.au|com.br|com.mx|com.sg|com.tr|de|eg|es|fr|in|it|jp|nl|sa|se|sg)";
    private static final String AMAZON_URL_REGEX_TOKEN = "{{amazon}}";
    private static final String AUTH_PORTAL_BASE_URL_REGEX = "^https://((au|br|ca|cn|de|es|fr|id|in|it|jp|mx|nl|ru|uk)-development|development|gamma|pre-prod|www).amazon.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/ap.*";
    private static final String AUTH_PORTAL_SIGN_IN_STANDALONE_URL_REGEX = "^https://((au|br|ca|cn|de|es|fr|id|in|it|jp|mx|nl|ru|uk)-development|development|gamma|pre-prod|www).amazon.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/ap/signin$";
    private static final String AUTH_PORTAL_SIGN_IN_URL_REGEX = "^https://((au|br|ca|cn|de|es|fr|id|in|it|jp|mx|nl|ru|uk)-development|development|gamma|pre-prod|www).amazon.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/ap/signin.*";
    private static final String CANTILEVER_HF_URL_REGEX = "^https?://[\\w\\.-]+\\.amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/csad/.*";
    private static final String FRAGMENT_INDEX = "/?fragment=";
    private static final String HMD_SURVEY_URL_REGEX = "^https?://[\\w\\.-]+\\.amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/gp/help/survey/.*";
    private static final String HOST_INDEX = "/spa/index.vox.html";
    private static final String HOST_INDEX_DEBUG = "/spa/debug.vox.html";
    private static final String HTTPS_PREFIX = "https://";
    private static final String HTTPS_SCHEME = "https";
    private static final String HTTP_PREFIX = "http://";
    private static final String LOGIN_URL = "/login";
    private static final String MESSAGE_US_URL_REGEX = "^https?://[\\w\\.-]+\\.amazon\\.(com|com.au|com.br|ca|cn|de|es|fr|co.id|in|it|co.jp|com.mx|nl|ru|co.uk)/message-us.*";
    private static final String PREFETCH_DEBUG_URL = "/spa/prefetch.debug.html";
    private static final String PREFETCH_URL = "/spa/prefetch.html";
    private static final String TAG = "DefaultEnvironmentService";
    private static final String TIV_LAUNCH_PATH = "/a/c/r";
    private Pattern amazonForumAuthUrlPattern;
    private Pattern amazonForumRetailUrlPattern;
    private Pattern amazonForumUrlPattern;
    private Pattern amazonRetailNonRootUrlPattern;
    private Pattern amazonRootUrlPattern;
    private Pattern authPortalBaseUrlPattern;
    private Pattern authPortalSignInStandaloneUrlPattern;
    private Pattern authPortalSignInUrlPattern;
    private Pattern cantileverUrlPattern;
    @VisibleForTesting
    DataRegionEnvironmentService dataRegionEnvironmentService;
    private Pattern hmdSurveyUrlPattern;
    private Lazy<IdentityService> identityService;
    private Pattern messageUsUrlPattern;
    @VisibleForTesting
    PfmEnvironmentService pfmEnvironmentService;
    private Pattern tivDomainPattern;
    private static final String[] WARM_SEAT_URLS = {"development.amazon.com/myh/preinviteAgreement", "pre-prod.amazon.com/myh/preinviteAgreement", "www.amazon.com/myh/preinviteAgreement", "alexa.amazon.com/spa/index.vox.html#settings/myh/invite"};
    private static final String[] ADDITIONAL_VALID_APP_URLS = {"pitangui.amazon.com", "guiana.amazon.com", "echo.amazon.com", BuildConfig.HOST, "projectdee-ui-dev.aka.amazon.com", "projectdee-ui-dev.integ.amazon.com", "projectdee-ui.aka.amazon.com", "projectdee-ui.integ.amazon.com", "projectdee-ui-gamma.aka.amazon.com", "projectdee-ui-gamma.integ.amazon.com", "projectdee-ui-pre-prod.aka.amazon.com", "projectdee-ui-pre-prod.integ.amazon.com", "projectdee-ui-uk-beta.aka.amazon.com", "projectdee-ui-pico-beta.aka.amazon.com", "projectdee-ui-gb.aka.amazon.com", "projectdee-ui-gb-registration.aka.amazon.com", "projectdee-ui-uk-gamma.aka.amazon.com", "projectdee-ui-pico-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.co.uk", BuildConfig.HOST_UK, "pico.amazon.com", "pico.amazon.co.uk", "seamus.amazon.co.uk", "layla.amazon.co.uk", "projectdee-ui-de-beta.aka.amazon.com", "projectdee-ui-brodie-beta.aka.amazon.com", "projectdee-ui-de.aka.amazon.com", "projectdee-ui-de-registration.aka.amazon.com", "projectdee-ui-de-gamma.aka.amazon.com", "projectdee-ui-brodie-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.de", BuildConfig.HOST_DE, "brodie.amazon.com", "brodie.amazon.de", "seamus.amazon.de", "layla.amazon.de", "projectdee-ui-eu.integ.amazon.com", "seamus.amazon.com", "layla.amazon.com", "projectdee-ui-fr.aka.amazon.com", "projectdee-ui-fr-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.fr", BuildConfig.HOST_FR, "projectdee-ui-es.aka.amazon.com", "projectdee-ui-es-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.es", BuildConfig.HOST_ES, "projectdee-ui-in.aka.amazon.com", "projectdee-ui-in-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.in", BuildConfig.HOST_IN, "projectdee-ui-it.aka.amazon.com", "projectdee-ui-it-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.it", BuildConfig.HOST_IT, "projectdee-ui-nl.aka.amazon.com", "projectdee-ui-nl-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.nl", BuildConfig.HOST_NL, "projectdee-ui-ru.aka.amazon.com", "projectdee-ui-ru-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.ru", BuildConfig.HOST_RU, "projectdee-ui-br.aka.amazon.com", "projectdee-ui-br-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.com.br", BuildConfig.HOST_BR, "projectdee-ui-ca.aka.amazon.com", "projectdee-ui-ca-gamma.aka.amazon.com", BuildConfig.HOST_CA, "projectdee-ui-mx.aka.amazon.com", "projectdee-ui-mx-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.com.mx", BuildConfig.HOST_MX, "projectdee-ui-cn.aka.amazon.com", "projectdee-ui-cn-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.ca", BuildConfig.HOST_CN, "projectdee-ui-au.aka.amazon.com", "projectdee-ui-au-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.com.au", BuildConfig.HOST_AU, "projectdee-ui-id.aka.amazon.com", "projectdee-ui-id-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.co.id", "alexa.amazon.co.id", "projectdee-ui-jp.aka.amazon.com", "projectdee-ui-jp-gamma.aka.amazon.com", "projectdee-ui-gamma.amazon.co.jp", BuildConfig.HOST_JP};

    /* renamed from: com.amazon.dee.app.services.environment.DefaultEnvironmentService$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType = new int[EnvironmentService.KnownUrlType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.AUTH_PORTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.AUTH_PORTAL_SIGN_IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.CANTILEVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.MESSAGE_US.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.HMD_SURVEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.AMAZON_ROOT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.AMAZON_RETAIL_NON_ROOT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.AMAZON_FORUM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.AMAZON_FORUM_AUTH_SALESFORCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$protocols$environment$EnvironmentService$KnownUrlType[EnvironmentService.KnownUrlType.AMAZON_FORUM_RETAIL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public DefaultEnvironmentService(@NonNull PfmEnvironmentService pfmEnvironmentService, @NonNull DataRegionEnvironmentService dataRegionEnvironmentService, @NonNull Lazy<IdentityService> lazy) {
        this.pfmEnvironmentService = pfmEnvironmentService;
        this.dataRegionEnvironmentService = dataRegionEnvironmentService;
        this.identityService = lazy;
    }

    private Pattern getAmazonForumAuthUrlPattern() {
        if (this.amazonForumAuthUrlPattern == null) {
            this.amazonForumAuthUrlPattern = Pattern.compile(AMAZON_FORUM_SALESFORCE_AUTH_REGEX);
        }
        return this.amazonForumAuthUrlPattern;
    }

    private Pattern getAmazonForumRetailUrlPattern() {
        if (this.amazonForumRetailUrlPattern == null) {
            this.amazonForumRetailUrlPattern = Pattern.compile(AMAZON_FORUM_RETAIL_REGEX);
        }
        return this.amazonForumRetailUrlPattern;
    }

    private Pattern getAmazonForumUrlPattern() {
        if (this.amazonForumUrlPattern == null) {
            this.amazonForumUrlPattern = Pattern.compile(AMAZON_FORUM_URL_REGEX);
        }
        return this.amazonForumUrlPattern;
    }

    private Pattern getAmazonRetailNonRootUrlPattern() {
        if (this.amazonRetailNonRootUrlPattern == null) {
            this.amazonRetailNonRootUrlPattern = Pattern.compile(AMAZON_RETAIL_URL_REGEX);
        }
        return this.amazonRetailNonRootUrlPattern;
    }

    private Pattern getAmazonRootUrlPattern() {
        if (this.amazonRootUrlPattern == null) {
            this.amazonRootUrlPattern = Pattern.compile(AMAZON_ROOT_URL_REGEX);
        }
        return this.amazonRootUrlPattern;
    }

    private Pattern getAuthPortalBaseUrlPattern() {
        if (this.authPortalBaseUrlPattern == null) {
            this.authPortalBaseUrlPattern = Pattern.compile(AUTH_PORTAL_BASE_URL_REGEX);
        }
        return this.authPortalBaseUrlPattern;
    }

    private Pattern getAuthPortalSignInStandaloneUrlPattern() {
        if (this.authPortalSignInStandaloneUrlPattern == null) {
            this.authPortalSignInStandaloneUrlPattern = Pattern.compile(AUTH_PORTAL_SIGN_IN_STANDALONE_URL_REGEX);
        }
        return this.authPortalSignInStandaloneUrlPattern;
    }

    private Pattern getAuthPortalSignInUrlPattern() {
        if (this.authPortalSignInUrlPattern == null) {
            this.authPortalSignInUrlPattern = Pattern.compile(AUTH_PORTAL_SIGN_IN_URL_REGEX);
        }
        return this.authPortalSignInUrlPattern;
    }

    private Pattern getCantileverUrlPattern() {
        if (this.cantileverUrlPattern == null) {
            this.cantileverUrlPattern = Pattern.compile(CANTILEVER_HF_URL_REGEX);
        }
        return this.cantileverUrlPattern;
    }

    private Pattern getHmdSurveyUrlPattern() {
        if (this.hmdSurveyUrlPattern == null) {
            this.hmdSurveyUrlPattern = Pattern.compile(HMD_SURVEY_URL_REGEX);
        }
        return this.hmdSurveyUrlPattern;
    }

    private Pattern getMessageUsUrlPattern() {
        if (this.messageUsUrlPattern == null) {
            this.messageUsUrlPattern = Pattern.compile(MESSAGE_US_URL_REGEX);
        }
        return this.messageUsUrlPattern;
    }

    private Pattern getTivDomainPattern() {
        if (this.tivDomainPattern == null) {
            this.tivDomainPattern = Pattern.compile(AMAZON_TIV_DOMAIN_REGEX);
        }
        return this.tivDomainPattern;
    }

    private boolean isAuthPortalReturningToApp(String str) {
        return getAuthPortalSignInUrlPattern().matcher(str).matches() && isMatchForAnyHostUrl(getReturnToQueryParam(str), ADDITIONAL_VALID_APP_URLS, "");
    }

    private boolean isMatchForAnyHostUrl(String str, String[] strArr, String str2) {
        if (str != null) {
            for (String str3 : strArr) {
                if (urlMatches(str, str3, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isStrictMatchForAnyHostUrl(String str, String[] strArr, String str2) {
        if (str != null) {
            for (String str3 : strArr) {
                if (urlStrictlyMatches(str, str3, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean urlMatches(String str, String str2, String str3) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        String lowerCase2 = str2.toLowerCase(Locale.getDefault());
        if (!lowerCase.startsWith(HTTPS_PREFIX + lowerCase2 + str3)) {
            if (!lowerCase.startsWith(HTTP_PREFIX + lowerCase2 + str3)) {
                return false;
            }
        }
        return true;
    }

    private boolean urlMatchesHost(String str, String str2) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        String lowerCase2 = str2.toLowerCase(Locale.getDefault());
        if (!lowerCase.startsWith(HTTPS_PREFIX + lowerCase2)) {
            if (!lowerCase.startsWith(HTTP_PREFIX + lowerCase2)) {
                return false;
            }
        }
        return true;
    }

    private boolean urlStrictlyMatches(String str, String str2, String str3) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        String lowerCase2 = str2.toLowerCase(Locale.getDefault());
        if (!lowerCase.equals(HTTPS_PREFIX + lowerCase2 + str3)) {
            if (!lowerCase.equals(HTTP_PREFIX + lowerCase2 + str3)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public MarketplaceEndpoints forMarketplace(@NonNull Marketplace marketplace) {
        return this.pfmEnvironmentService.forMarketplace(marketplace);
    }

    @NonNull
    @VisibleForTesting
    EnvironmentService getActiveEnvironmentService() {
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user != null && user.hasFeature("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID")) {
            return this.dataRegionEnvironmentService;
        }
        return this.pfmEnvironmentService;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getApiGatewayEndpoint() {
        String apiGatewayEndpoint = getActiveEnvironmentService().getApiGatewayEndpoint();
        return apiGatewayEndpoint == null ? this.pfmEnvironmentService.getApiGatewayEndpoint() : apiGatewayEndpoint;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getApiGatewayHost() {
        String apiGatewayHost = getActiveEnvironmentService().getApiGatewayHost();
        return apiGatewayHost == null ? this.pfmEnvironmentService.getApiGatewayHost() : apiGatewayHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getAuthWebAssociationHandle() {
        return this.pfmEnvironmentService.getAuthWebAssociationHandle();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getAuthWebHost() {
        return this.pfmEnvironmentService.getAuthWebHost();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getBuildFlavor() {
        return "prod";
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getBuildStage() {
        return BuildConfig.STAGE;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getCoralEndpoint() {
        String coralEndpoint = getActiveEnvironmentService().getCoralEndpoint();
        return coralEndpoint == null ? this.pfmEnvironmentService.getCoralEndpoint() : coralEndpoint;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getCoralHost() {
        String coralHost = getActiveEnvironmentService().getCoralHost();
        return coralHost == null ? this.pfmEnvironmentService.getCoralHost() : coralHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getCountryCode() {
        return this.pfmEnvironmentService.getCountryCode();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public DeviceInformation getDeviceInformation() {
        return this.pfmEnvironmentService.getDeviceInformation();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public Marketplace getMarketplace() {
        return this.pfmEnvironmentService.getMarketplace();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    public <T> T getMobilyticsEndpoint(@NonNull Map<String, T> map) {
        return (T) getActiveEnvironmentService().getMobilyticsEndpoint(map);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getPrefetchUrl() {
        return getWebEndpoint() + PREFETCH_URL;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getRetailEndpoint() {
        String retailEndpoint = getActiveEnvironmentService().getRetailEndpoint();
        return retailEndpoint == null ? this.pfmEnvironmentService.getRetailEndpoint() : retailEndpoint;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getRetailHost() {
        String retailHost = getActiveEnvironmentService().getRetailHost();
        return retailHost == null ? this.pfmEnvironmentService.getRetailHost() : retailHost;
    }

    protected String getReturnToQueryParam(String str) {
        return new UrlQuerySanitizer(str).getValue("openid.return_to");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getSkillsStoreEndpoint() {
        String skillsStoreEndpoint = getActiveEnvironmentService().getSkillsStoreEndpoint();
        return skillsStoreEndpoint == null ? this.pfmEnvironmentService.getSkillsStoreEndpoint() : skillsStoreEndpoint;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getWebEndpoint() {
        String webEndpoint = getActiveEnvironmentService().getWebEndpoint();
        return webEndpoint == null ? this.pfmEnvironmentService.getWebEndpoint() : webEndpoint;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getWebHost() {
        String webHost = getActiveEnvironmentService().getWebHost();
        return webHost == null ? this.pfmEnvironmentService.getWebHost() : webHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getWebIndex() {
        return getWebEndpoint() + HOST_INDEX;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isDebugBuild() {
        return false;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isDevelopmentFabric() {
        return BuildConfig.IS_LOCAL_ENVIRONMENT || BuildConfig.IS_ALPHA_ENVIRONMENT || BuildConfig.IS_BETA_ENVIRONMENT;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isKnownUrl(EnvironmentService.KnownUrlType knownUrlType, String str) {
        if (str == null) {
            return false;
        }
        switch (knownUrlType.ordinal()) {
            case 0:
                return getAuthPortalBaseUrlPattern().matcher(str).matches();
            case 1:
                return getAuthPortalSignInUrlPattern().matcher(str).matches();
            case 2:
                return getCantileverUrlPattern().matcher(str).matches();
            case 3:
                return getMessageUsUrlPattern().matcher(str).matches();
            case 4:
                return getHmdSurveyUrlPattern().matcher(str).matches();
            case 5:
                return getAmazonRootUrlPattern().matcher(str).matches();
            case 6:
                return getAmazonRetailNonRootUrlPattern().matcher(str).matches();
            case 7:
                return getAmazonForumUrlPattern().matcher(str).matches();
            case 8:
                return getAmazonForumAuthUrlPattern().matcher(str).matches();
            case 9:
                return getAmazonForumRetailUrlPattern().matcher(str).matches();
            default:
                return false;
        }
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isOutsideWebAppUrl(String str) {
        return str != null && !isWithinWebAppUrl(str) && !isWebSigninUrl(str) && !getAuthPortalSignInUrlPattern().matcher(str).matches();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isProductionFabric() {
        return BuildConfig.IS_GAMMA_ENVIRONMENT || BuildConfig.IS_PRE_PROD_ENVIRONMENT || BuildConfig.IS_PROD_ENVIRONMENT;
    }

    @VisibleForTesting
    boolean isUrlSupportedByRegex(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Pattern.compile(str2.replace(AMAZON_URL_REGEX_TOKEN, AMAZON_BASE_URL_REGEX)).matcher(str).matches();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebAppUrl(String str) {
        return urlMatches(str, getWebHost(), HOST_INDEX) || urlMatches(str, getWebHost(), HOST_INDEX_DEBUG) || urlMatches(str, getWebHost(), FRAGMENT_INDEX) || isStrictMatchForAnyHostUrl(str, ADDITIONAL_VALID_APP_URLS, "") || isMatchForAnyHostUrl(str, ADDITIONAL_VALID_APP_URLS, HOST_INDEX) || isMatchForAnyHostUrl(str, ADDITIONAL_VALID_APP_URLS, HOST_INDEX_DEBUG) || isMatchForAnyHostUrl(str, ADDITIONAL_VALID_APP_URLS, FRAGMENT_INDEX);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebSigninUrl(String str) {
        return isAuthPortalReturningToApp(str) || isMatchForAnyHostUrl(str, ADDITIONAL_VALID_APP_URLS, LOGIN_URL) || getAuthPortalSignInStandaloneUrlPattern().matcher(str).matches();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebTivUrl(Uri uri) {
        return "https".equals(uri.getScheme()) && uri.getPath().contains(TIV_LAUNCH_PATH) && getTivDomainPattern().matcher(uri.getAuthority()).matches();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebWarmSeatUrl(String str) {
        if (str == null || str.equals(WebUtils.ABOUT_BLANK_PAGE)) {
            return false;
        }
        return isWithinHostList(str, WARM_SEAT_URLS);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinAlexaWebViewNonIndex(String str) {
        return str == null || str.equals(WebUtils.ABOUT_BLANK_PAGE) || urlMatchesHost(str, getWebHost()) || isWithinHostList(str, ADDITIONAL_VALID_APP_URLS) || isWithinHostList(str, WARM_SEAT_URLS);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinExternalUIWebView(String str, String str2) {
        return str == null || str.equals(WebUtils.ABOUT_BLANK_PAGE) || isWebSigninUrl(str) || isUrlSupportedByRegex(str, str2) || getAuthPortalSignInUrlPattern().matcher(str).matches() || isWithinHostList(str, WARM_SEAT_URLS);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinHostList(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (urlMatchesHost(str, str2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinWebAppUrl(String str) {
        return str == null || str.equals(WebUtils.ABOUT_BLANK_PAGE) || isWebAppUrl(str) || isWithinHostList(str, WARM_SEAT_URLS);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void overrideHost(String str) {
        this.pfmEnvironmentService.overrideHost(str);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void resetValues() {
        this.pfmEnvironmentService.resetValues();
        this.dataRegionEnvironmentService.resetValues();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setAuthWebAssociationHandle(@NonNull String str) {
        this.pfmEnvironmentService.setAuthWebAssociationHandle(str);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setAuthWebHost(@NonNull String str) {
        this.pfmEnvironmentService.setAuthWebHost(str);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setCoralHost(@NonNull String str) {
        this.pfmEnvironmentService.setCoralHost(str);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setMarketplace(@NonNull Marketplace marketplace) {
        this.pfmEnvironmentService.setMarketplace(marketplace);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setWebHost(@NonNull String str) {
        this.pfmEnvironmentService.setWebHost(str);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean shouldOverrideHosts() {
        return this.pfmEnvironmentService.shouldOverrideHosts();
    }
}
