package com.amazon.alexa.protocols.environment;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceEndpoints;
import java.util.Map;
/* loaded from: classes9.dex */
public interface EnvironmentService {

    /* loaded from: classes9.dex */
    public enum KnownUrlType {
        AUTH_PORTAL,
        AUTH_PORTAL_SIGN_IN,
        CANTILEVER,
        MESSAGE_US,
        HMD_SURVEY,
        AMAZON_ROOT,
        AMAZON_RETAIL_NON_ROOT,
        AMAZON_FORUM,
        AMAZON_FORUM_AUTH_SALESFORCE,
        AMAZON_FORUM_RETAIL
    }

    MarketplaceEndpoints forMarketplace(@NonNull Marketplace marketplace);

    @NonNull
    default String getApiGatewayEndpoint() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    @NonNull
    default String getApiGatewayHost() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    @NonNull
    String getAuthWebAssociationHandle();

    @NonNull
    String getAuthWebHost();

    String getBuildFlavor();

    String getBuildStage();

    @NonNull
    String getCoralEndpoint();

    @NonNull
    String getCoralHost();

    String getCountryCode();

    com.amazon.alexa.device.api.DeviceInformation getDeviceInformation();

    @NonNull
    Marketplace getMarketplace();

    @Nullable
    default <T> T getMobilyticsEndpoint(@NonNull Map<String, T> map) {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    @NonNull
    String getPrefetchUrl();

    @NonNull
    default String getRetailEndpoint() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    @NonNull
    default String getRetailHost() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    @NonNull
    default String getSkillsStoreEndpoint() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    String getVersionName();

    @NonNull
    String getWebEndpoint();

    @NonNull
    String getWebHost();

    @NonNull
    String getWebIndex();

    boolean isDebugBuild();

    boolean isDevelopmentFabric();

    default boolean isKnownUrl(KnownUrlType knownUrlType, String str) {
        return false;
    }

    boolean isOutsideWebAppUrl(String str);

    boolean isProductionFabric();

    boolean isWebAppUrl(String str);

    boolean isWebSigninUrl(String str);

    default boolean isWebTivUrl(Uri uri) {
        return false;
    }

    boolean isWebWarmSeatUrl(String str);

    boolean isWithinAlexaWebViewNonIndex(String str);

    boolean isWithinExternalUIWebView(String str, String str2);

    boolean isWithinHostList(String str, String[] strArr);

    boolean isWithinWebAppUrl(String str);

    void overrideHost(String str);

    void resetValues();

    void setAuthWebAssociationHandle(@NonNull String str);

    void setAuthWebHost(@NonNull String str);

    void setCoralHost(@NonNull String str);

    void setMarketplace(@NonNull Marketplace marketplace);

    void setWebHost(@NonNull String str);

    boolean shouldOverrideHosts();
}
