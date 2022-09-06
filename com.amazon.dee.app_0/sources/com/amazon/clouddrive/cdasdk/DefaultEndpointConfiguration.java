package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class DefaultEndpointConfiguration implements EndpointConfiguration {
    private static final String DEFAULT_APS_API_URL = "https://drive.amazonaws.com/photoapp/";
    private static final String DEFAULT_CDP_API_URL = "https://content-na.drive.amazonaws.com/";
    private static final String DEFAULT_CDRS_API_URL = "https://drive.amazonaws.com/drive/v2/";
    private static final String DEFAULT_CDS_API_URL = "https://drive.amazonaws.com/drive/v1/";
    private static final String DEFAULT_DPS_API_URL = "https://drive.amazonaws.com/drive/v2/device-personalization/";
    private static final String DEFAULT_PROMPTO_API_URL = "https://drive.amazonaws.com/drive/v2/photosGroups/";

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    @NonNull
    public String getApsServiceUrl() {
        return DEFAULT_APS_API_URL;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    @NonNull
    public String getCdrsServiceUrl() {
        return DEFAULT_CDRS_API_URL;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    @NonNull
    public String getContentUrl() {
        return DEFAULT_CDP_API_URL;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    @NonNull
    public String getDpsServiceUrl() {
        return DEFAULT_DPS_API_URL;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    @NonNull
    public String getMetadataUrl() {
        return DEFAULT_CDS_API_URL;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    @NonNull
    public String getPromptoServiceUrl() {
        return DEFAULT_PROMPTO_API_URL;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    @NonNull
    public String getThumbnailUrl() {
        return getContentUrl();
    }
}
