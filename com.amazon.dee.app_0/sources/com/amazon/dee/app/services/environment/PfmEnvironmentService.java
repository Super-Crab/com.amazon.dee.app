package com.amazon.dee.app.services.environment;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceEndpoints;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.marketplace.MarketplaceConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes12.dex */
public class PfmEnvironmentService implements EnvironmentService {
    private static final String TAG = EnvironmentService.class.getSimpleName();
    String apiGatewayHost;
    String authWebAssociationHandle;
    String authWebHost;
    String coralHost;
    private String countryCode;
    DeviceInformation deviceInformation;
    @NonNull
    Marketplace marketplace = Marketplace.USA;
    private boolean overrideHost;
    String retailHost;
    private Context savedContext;
    String skillsStoreEndpoint;
    String webHost;

    public PfmEnvironmentService(Context context, DeviceInformation deviceInformation, @NonNull UserIdentityStorage userIdentityStorage) {
        this.savedContext = context;
        UserIdentity userIdentity = userIdentityStorage.get();
        Marketplace effectiveMarketplace = userIdentity != null ? userIdentity.getEffectiveMarketplace() : null;
        if (effectiveMarketplace != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Marketplace taken from cached user identity: ");
            outline107.append(effectiveMarketplace.getCountryCode());
            Log.i(str, outline107.toString());
            if (context != null) {
                setDefaultCountryCode(context);
            }
            setMarketplace(effectiveMarketplace);
        } else if (context != null) {
            setDefaultValues(context);
        }
        this.deviceInformation = deviceInformation;
    }

    private void populateAttributesFromMarketplace() {
        MarketplaceConfiguration forMarketplace = MarketplaceConfiguration.forMarketplace(this.marketplace);
        if (!shouldOverrideHosts()) {
            this.coralHost = forMarketplace.getCoralHost();
            this.webHost = forMarketplace.getWebHost();
        }
        this.apiGatewayHost = forMarketplace.getApiGatewayHost();
        this.authWebHost = forMarketplace.getAuthWebHost();
        this.authWebAssociationHandle = forMarketplace.getAuthAssociationHandle();
        this.retailHost = forMarketplace.getRetailHost();
        this.skillsStoreEndpoint = forMarketplace.getSkillsStoreEndpoint();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public MarketplaceEndpoints forMarketplace(@NonNull Marketplace marketplace) {
        return MarketplaceConfiguration.forMarketplace(marketplace);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getApiGatewayEndpoint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("https://");
        outline107.append(this.apiGatewayHost);
        return outline107.toString();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getApiGatewayHost() {
        return this.apiGatewayHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getAuthWebAssociationHandle() {
        return this.authWebAssociationHandle;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getAuthWebHost() {
        return this.authWebHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getBuildFlavor() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getBuildStage() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getCoralEndpoint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("https://");
        outline107.append(this.coralHost);
        return outline107.toString();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getCoralHost() {
        return this.coralHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getCountryCode() {
        return this.countryCode;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public DeviceInformation getDeviceInformation() {
        return this.deviceInformation;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public Marketplace getMarketplace() {
        return this.marketplace;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    public <T> T getMobilyticsEndpoint(@NonNull Map<String, T> map) {
        return map.get("us-east-1");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getPrefetchUrl() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getRetailEndpoint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("https://www.");
        outline107.append(this.retailHost);
        return outline107.toString();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getRetailHost() {
        return this.retailHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getSkillsStoreEndpoint() {
        return this.skillsStoreEndpoint;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getVersionName() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getWebEndpoint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("https://");
        outline107.append(this.webHost);
        return outline107.toString();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getWebHost() {
        return this.webHost;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getWebIndex() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isDebugBuild() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isDevelopmentFabric() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isKnownUrl(EnvironmentService.KnownUrlType knownUrlType, String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isOutsideWebAppUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isProductionFabric() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebAppUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebSigninUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebTivUrl(Uri uri) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebWarmSeatUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinAlexaWebViewNonIndex(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinExternalUIWebView(String str, String str2) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinHostList(String str, String[] strArr) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinWebAppUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void overrideHost(String str) {
        this.overrideHost = true;
        this.webHost = str;
        this.coralHost = str;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void resetValues() {
        setDefaultValues(this.savedContext);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setAuthWebAssociationHandle(@NonNull String str) {
        this.authWebAssociationHandle = str;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setAuthWebHost(@NonNull String str) {
        this.authWebHost = str;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setCoralHost(@NonNull String str) {
        this.coralHost = str;
    }

    void setDefaultCountryCode(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String str = "Current locale: " + locale;
        this.countryCode = locale.getCountry();
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("countryCode set from locale: ");
        outline107.append(this.countryCode);
        Log.i(str2, outline107.toString());
    }

    void setDefaultValues(Context context) {
        setDefaultCountryCode(context);
        String str = null;
        Marketplace findMarketplaceByName = Marketplace.findMarketplaceByName(this.countryCode, null);
        if (findMarketplaceByName != null) {
            this.marketplace = findMarketplaceByName;
            str = findMarketplaceByName.getMarketplaceName().toString();
        }
        Log.i(TAG, "Potential marketplace: %s <- countryCode: %s", str, this.countryCode);
        populateAttributesFromMarketplace();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setMarketplace(@NonNull Marketplace marketplace) {
        this.marketplace = marketplace;
        populateAttributesFromMarketplace();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setWebHost(@NonNull String str) {
        this.webHost = str;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean shouldOverrideHosts() {
        return this.overrideHost;
    }
}
