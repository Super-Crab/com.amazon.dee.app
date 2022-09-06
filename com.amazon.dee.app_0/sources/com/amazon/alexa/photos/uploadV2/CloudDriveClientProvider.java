package com.amazon.alexa.photos.uploadV2;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.photos.PhotosAppInfoProvider;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.DefaultEndpointConfiguration;
import com.amazon.clouddrive.cdasdk.EndpointConfiguration;
import com.amazon.clouddrive.cdasdk.RequestLoggingConfig;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import javax.annotation.Nullable;
/* loaded from: classes9.dex */
public class CloudDriveClientProvider {
    @NonNull
    private final PhotosAppInfoProvider appInfoProvider;
    @Nullable
    private CDClient cdClient;
    @NonNull
    private SdkMetrics cdaSdkMetrics;
    @NonNull
    private CdaSdkPreferences cdaSdkPreferences;
    @NonNull
    private Context context;
    private EndpointsHelper endpointsHelper;
    @NonNull
    private LazyComponent<IdentityService> identityService;
    @NonNull
    private Metrics metrics;

    public CloudDriveClientProvider(@NonNull Context context, @NonNull PhotosAppInfoProvider photosAppInfoProvider, @NonNull LazyComponent<IdentityService> lazyComponent, @NonNull CdaSdkPreferences cdaSdkPreferences, @NonNull CdaSdkMetrics cdaSdkMetrics, @NonNull Metrics metrics) {
        this.context = context;
        this.appInfoProvider = photosAppInfoProvider;
        this.identityService = lazyComponent;
        this.cdaSdkPreferences = cdaSdkPreferences;
        this.cdaSdkMetrics = cdaSdkMetrics;
        this.metrics = metrics;
    }

    public void destroy() {
        EndpointsHelper endpointsHelper = this.endpointsHelper;
        if (endpointsHelper != null) {
            endpointsHelper.removeEndpointConfiguration();
        }
        this.cdClient = null;
    }

    public CDClient getCDClient() {
        if (this.cdClient == null) {
            RequestLoggingConfig requestLoggingConfig = new RequestLoggingConfig();
            EndpointConfiguration endpointConfiguration = this.cdaSdkPreferences.getEndpointConfiguration();
            if (endpointConfiguration == null) {
                endpointConfiguration = new DefaultEndpointConfiguration();
            }
            this.cdClient = CDClient.createClient(new ClientConfig.Builder(this.context, new MAPTokenProvider(this.identityService.mo10268get()), this.appInfoProvider.getUserAgent(), this.appInfoProvider.getApplicationId(), this.appInfoProvider.getApplicationName()).withSdkMetrics(this.cdaSdkMetrics).withRequestLoggingConfig(requestLoggingConfig).withEndpointConfiguration(endpointConfiguration).build());
            this.endpointsHelper = new EndpointsHelper(this.cdClient, this.identityService, this.metrics, this.cdaSdkPreferences);
            this.endpointsHelper.prepareEndpointConfigAsync();
        }
        return this.cdClient;
    }
}
