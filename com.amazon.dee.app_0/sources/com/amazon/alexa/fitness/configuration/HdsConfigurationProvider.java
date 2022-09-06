package com.amazon.alexa.fitness.configuration;

import android.content.res.Resources;
import com.amazon.alexa.blueprints.BlueprintsEndpointConstants;
import com.amazon.alexa.fitness.R;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.marketplace.Marketplace;
import com.amazon.alexa.protocols.marketplace.MarketplaceService;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalResourcesConfigurationProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/configuration/HdsConfigurationProvider;", "Lcom/amazon/alexa/fitness/configuration/HdsClientConfigurationProvider;", "environmentService", "Lcom/amazon/alexa/protocols/environment/EnvironmentService;", "marketplaceService", "Lcom/amazon/alexa/protocols/marketplace/MarketplaceService;", "resources", "Landroid/content/res/Resources;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/protocols/environment/EnvironmentService;Lcom/amazon/alexa/protocols/marketplace/MarketplaceService;Landroid/content/res/Resources;Lcom/amazon/alexa/fitness/logs/ILog;)V", "getEndpointKeyForMarketplace", "", "marketplace", "Lcom/amazon/alexa/protocols/marketplace/Marketplace;", "getHdsEndpoint", "", "getTimeshiftKey", "getTimeshiftKeyForMarketplace", "provideHdsClientConfiguration", "Lcom/amazon/alexa/fitness/configuration/HdsClientConfiguration;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HdsConfigurationProvider implements HdsClientConfigurationProvider {
    private final EnvironmentService environmentService;
    private final ILog log;
    private final MarketplaceService marketplaceService;
    private final Resources resources;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Marketplace.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[Marketplace.AUSTRALIA_DEVELOPMENT.ordinal()] = 1;
            $EnumSwitchMapping$0[Marketplace.BRAZIL_DEVELOPMENT.ordinal()] = 2;
            $EnumSwitchMapping$0[Marketplace.INDONESIA_DEVELOPMENT.ordinal()] = 3;
            $EnumSwitchMapping$0[Marketplace.MEXICO_DEVELOPMENT.ordinal()] = 4;
            $EnumSwitchMapping$0[Marketplace.NETHERLANDS_DEVELOPMENT.ordinal()] = 5;
            $EnumSwitchMapping$0[Marketplace.RUSSIA_DEVELOPMENT.ordinal()] = 6;
            $EnumSwitchMapping$0[Marketplace.SPAIN_DEVELOPMENT.ordinal()] = 7;
            $EnumSwitchMapping$0[Marketplace.AUSTRALIA_PRODUCTION.ordinal()] = 8;
            $EnumSwitchMapping$0[Marketplace.BRAZIL_PRODUCTION.ordinal()] = 9;
            $EnumSwitchMapping$0[Marketplace.CANADA.ordinal()] = 10;
            $EnumSwitchMapping$0[Marketplace.CHINA.ordinal()] = 11;
            $EnumSwitchMapping$0[Marketplace.FRANCE.ordinal()] = 12;
            $EnumSwitchMapping$0[Marketplace.GERMANY.ordinal()] = 13;
            $EnumSwitchMapping$0[Marketplace.INDIA.ordinal()] = 14;
            $EnumSwitchMapping$0[Marketplace.INDONESIA_PRODUCTION.ordinal()] = 15;
            $EnumSwitchMapping$0[Marketplace.ITALY_PRODUCTION.ordinal()] = 16;
            $EnumSwitchMapping$0[Marketplace.JAPAN.ordinal()] = 17;
            $EnumSwitchMapping$0[Marketplace.MEXICO_PRODUCTION.ordinal()] = 18;
            $EnumSwitchMapping$0[Marketplace.NETHERLANDS_PRODUCTION.ordinal()] = 19;
            $EnumSwitchMapping$0[Marketplace.RUSSIA_PRODUCTION.ordinal()] = 20;
            $EnumSwitchMapping$0[Marketplace.SPAIN.ordinal()] = 21;
            $EnumSwitchMapping$0[Marketplace.UNITED_KINGDOM.ordinal()] = 22;
            $EnumSwitchMapping$0[Marketplace.USA.ordinal()] = 23;
            $EnumSwitchMapping$1 = new int[Marketplace.values().length];
            $EnumSwitchMapping$1[Marketplace.AUSTRALIA_DEVELOPMENT.ordinal()] = 1;
            $EnumSwitchMapping$1[Marketplace.BRAZIL_DEVELOPMENT.ordinal()] = 2;
            $EnumSwitchMapping$1[Marketplace.INDONESIA_DEVELOPMENT.ordinal()] = 3;
            $EnumSwitchMapping$1[Marketplace.MEXICO_DEVELOPMENT.ordinal()] = 4;
            $EnumSwitchMapping$1[Marketplace.NETHERLANDS_DEVELOPMENT.ordinal()] = 5;
            $EnumSwitchMapping$1[Marketplace.RUSSIA_DEVELOPMENT.ordinal()] = 6;
            $EnumSwitchMapping$1[Marketplace.SPAIN_DEVELOPMENT.ordinal()] = 7;
            $EnumSwitchMapping$1[Marketplace.AUSTRALIA_PRODUCTION.ordinal()] = 8;
            $EnumSwitchMapping$1[Marketplace.BRAZIL_PRODUCTION.ordinal()] = 9;
            $EnumSwitchMapping$1[Marketplace.CANADA.ordinal()] = 10;
            $EnumSwitchMapping$1[Marketplace.CHINA.ordinal()] = 11;
            $EnumSwitchMapping$1[Marketplace.FRANCE.ordinal()] = 12;
            $EnumSwitchMapping$1[Marketplace.GERMANY.ordinal()] = 13;
            $EnumSwitchMapping$1[Marketplace.INDIA.ordinal()] = 14;
            $EnumSwitchMapping$1[Marketplace.INDONESIA_PRODUCTION.ordinal()] = 15;
            $EnumSwitchMapping$1[Marketplace.ITALY_PRODUCTION.ordinal()] = 16;
            $EnumSwitchMapping$1[Marketplace.JAPAN.ordinal()] = 17;
            $EnumSwitchMapping$1[Marketplace.MEXICO_PRODUCTION.ordinal()] = 18;
            $EnumSwitchMapping$1[Marketplace.NETHERLANDS_PRODUCTION.ordinal()] = 19;
            $EnumSwitchMapping$1[Marketplace.RUSSIA_PRODUCTION.ordinal()] = 20;
            $EnumSwitchMapping$1[Marketplace.SPAIN.ordinal()] = 21;
            $EnumSwitchMapping$1[Marketplace.UNITED_KINGDOM.ordinal()] = 22;
            $EnumSwitchMapping$1[Marketplace.USA.ordinal()] = 23;
        }
    }

    @Inject
    public HdsConfigurationProvider(@NotNull EnvironmentService environmentService, @NotNull MarketplaceService marketplaceService, @NotNull Resources resources, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(environmentService, "environmentService");
        Intrinsics.checkParameterIsNotNull(marketplaceService, "marketplaceService");
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.environmentService = environmentService;
        this.marketplaceService = marketplaceService;
        this.resources = resources;
        this.log = log;
    }

    private final int getEndpointKeyForMarketplace(Marketplace marketplace) {
        switch (WhenMappings.$EnumSwitchMapping$0[marketplace.ordinal()]) {
            case 1:
                return R.string.hds_client_endpoint_beta;
            case 2:
                return R.string.hds_client_endpoint_beta;
            case 3:
                return R.string.hds_client_endpoint_beta;
            case 4:
                return R.string.hds_client_endpoint_beta;
            case 5:
                return R.string.hds_client_endpoint_beta;
            case 6:
                return R.string.hds_client_endpoint_beta;
            case 7:
                return R.string.hds_client_endpoint_beta;
            case 8:
                return R.string.hds_client_endpoint_FE;
            case 9:
                return R.string.hds_client_endpoint_NA;
            case 10:
                return R.string.hds_client_endpoint_NA;
            case 11:
                return R.string.hds_client_endpoint_NA;
            case 12:
                return R.string.hds_client_endpoint_EU;
            case 13:
                return R.string.hds_client_endpoint_EU;
            case 14:
                return R.string.hds_client_endpoint_EU;
            case 15:
                return R.string.hds_client_endpoint_FE;
            case 16:
                return R.string.hds_client_endpoint_EU;
            case 17:
                return R.string.hds_client_endpoint_FE;
            case 18:
                return R.string.hds_client_endpoint_NA;
            case 19:
                return R.string.hds_client_endpoint_EU;
            case 20:
                return R.string.hds_client_endpoint_EU;
            case 21:
                return R.string.hds_client_endpoint_EU;
            case 22:
                return R.string.hds_client_endpoint_EU;
            case 23:
                return R.string.hds_client_endpoint_NA;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final String getHdsEndpoint(Resources resources) {
        int endpointKeyForMarketplace;
        Marketplace marketplace = this.marketplaceService.getEffectivePFM().toBlocking().singleOrDefault(Marketplace.USA);
        String buildStage = this.environmentService.getBuildStage();
        if (buildStage != null) {
            switch (buildStage.hashCode()) {
                case -318354310:
                    if (buildStage.equals(BlueprintsEndpointConstants.PREPROD)) {
                        endpointKeyForMarketplace = R.string.hds_client_endpoint_gamma;
                        break;
                    }
                    break;
                case 3020272:
                    if (buildStage.equals("beta")) {
                        endpointKeyForMarketplace = R.string.hds_client_endpoint_beta;
                        break;
                    }
                    break;
                case 3449687:
                    if (buildStage.equals("prod")) {
                        Intrinsics.checkExpressionValueIsNotNull(marketplace, "marketplace");
                        endpointKeyForMarketplace = getEndpointKeyForMarketplace(marketplace);
                        break;
                    }
                    break;
                case 92909918:
                    if (buildStage.equals("alpha")) {
                        endpointKeyForMarketplace = R.string.hds_client_endpoint_alpha;
                        break;
                    }
                    break;
                case 98120615:
                    if (buildStage.equals("gamma")) {
                        endpointKeyForMarketplace = R.string.hds_client_endpoint_gamma;
                        break;
                    }
                    break;
                case 103145323:
                    if (buildStage.equals("local")) {
                        endpointKeyForMarketplace = R.string.hds_client_endpoint_alpha;
                        break;
                    }
                    break;
            }
            String string = resources.getString(endpointKeyForMarketplace);
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "LocalResourcesConfigurationProvider", "HDS endpoint configuration provided for Marketplace: " + marketplace + ", Endpoint: " + string, null, 4, null);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(endp…Endpoint: $it\")\n        }");
            return string;
        }
        Intrinsics.checkExpressionValueIsNotNull(marketplace, "marketplace");
        endpointKeyForMarketplace = getEndpointKeyForMarketplace(marketplace);
        String string2 = resources.getString(endpointKeyForMarketplace);
        ILog iLog2 = this.log;
        ILog.DefaultImpls.debug$default(iLog2, "LocalResourcesConfigurationProvider", "HDS endpoint configuration provided for Marketplace: " + marketplace + ", Endpoint: " + string2, null, 4, null);
        Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(endp…Endpoint: $it\")\n        }");
        return string2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final String getTimeshiftKey(Resources resources) {
        int timeshiftKeyForMarketplace;
        Marketplace marketplace = this.marketplaceService.getEffectivePFM().toBlocking().singleOrDefault(Marketplace.USA);
        String buildStage = this.environmentService.getBuildStage();
        if (buildStage != null) {
            switch (buildStage.hashCode()) {
                case -318354310:
                    if (buildStage.equals(BlueprintsEndpointConstants.PREPROD)) {
                        timeshiftKeyForMarketplace = R.string.hds_client_api_key_gamma_NA;
                        break;
                    }
                    break;
                case 3020272:
                    if (buildStage.equals("beta")) {
                        timeshiftKeyForMarketplace = R.string.hds_client_api_key_beta;
                        break;
                    }
                    break;
                case 3449687:
                    if (buildStage.equals("prod")) {
                        Intrinsics.checkExpressionValueIsNotNull(marketplace, "marketplace");
                        timeshiftKeyForMarketplace = getTimeshiftKeyForMarketplace(marketplace);
                        break;
                    }
                    break;
                case 92909918:
                    if (buildStage.equals("alpha")) {
                        timeshiftKeyForMarketplace = R.string.hds_client_api_key_alpha;
                        break;
                    }
                    break;
                case 98120615:
                    if (buildStage.equals("gamma")) {
                        timeshiftKeyForMarketplace = R.string.hds_client_api_key_gamma_NA;
                        break;
                    }
                    break;
                case 103145323:
                    if (buildStage.equals("local")) {
                        timeshiftKeyForMarketplace = R.string.hds_client_api_key_alpha;
                        break;
                    }
                    break;
            }
            String string = resources.getString(timeshiftKeyForMarketplace);
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "LocalResourcesConfigurationProvider", "HDS timeshift API key configuration provided for Marketplace: " + marketplace + ", Timeshift key: " + string, null, 4, null);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(time…hift key: $it\")\n        }");
            return string;
        }
        Intrinsics.checkExpressionValueIsNotNull(marketplace, "marketplace");
        timeshiftKeyForMarketplace = getTimeshiftKeyForMarketplace(marketplace);
        String string2 = resources.getString(timeshiftKeyForMarketplace);
        ILog iLog2 = this.log;
        ILog.DefaultImpls.debug$default(iLog2, "LocalResourcesConfigurationProvider", "HDS timeshift API key configuration provided for Marketplace: " + marketplace + ", Timeshift key: " + string2, null, 4, null);
        Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(time…hift key: $it\")\n        }");
        return string2;
    }

    private final int getTimeshiftKeyForMarketplace(Marketplace marketplace) {
        switch (WhenMappings.$EnumSwitchMapping$1[marketplace.ordinal()]) {
            case 1:
                return R.string.hds_client_api_key_beta;
            case 2:
                return R.string.hds_client_api_key_beta;
            case 3:
                return R.string.hds_client_api_key_beta;
            case 4:
                return R.string.hds_client_api_key_beta;
            case 5:
                return R.string.hds_client_api_key_beta;
            case 6:
                return R.string.hds_client_api_key_beta;
            case 7:
                return R.string.hds_client_api_key_beta;
            case 8:
                return R.string.hds_client_api_key_prod_FE;
            case 9:
                return R.string.hds_client_api_key_prod_NA;
            case 10:
                return R.string.hds_client_api_key_prod_NA;
            case 11:
                return R.string.hds_client_api_key_prod_NA;
            case 12:
                return R.string.hds_client_api_key_prod_EU;
            case 13:
                return R.string.hds_client_api_key_prod_EU;
            case 14:
                return R.string.hds_client_api_key_prod_EU;
            case 15:
                return R.string.hds_client_api_key_prod_FE;
            case 16:
                return R.string.hds_client_api_key_prod_EU;
            case 17:
                return R.string.hds_client_api_key_prod_FE;
            case 18:
                return R.string.hds_client_api_key_prod_NA;
            case 19:
                return R.string.hds_client_api_key_prod_EU;
            case 20:
                return R.string.hds_client_api_key_prod_EU;
            case 21:
                return R.string.hds_client_api_key_prod_EU;
            case 22:
                return R.string.hds_client_api_key_prod_EU;
            case 23:
                return R.string.hds_client_api_key_prod_NA;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Override // com.amazon.alexa.fitness.configuration.HdsClientConfigurationProvider
    @NotNull
    public HdsClientConfiguration provideHdsClientConfiguration() {
        Resources resources = this.resources;
        return new HdsClientConfiguration(getHdsEndpoint(resources), getTimeshiftKey(resources), resources.getInteger(R.integer.hds_client_connection_timeout), resources.getInteger(R.integer.hds_client_read_timeout), resources.getInteger(R.integer.hds_client_max_retries));
    }
}
