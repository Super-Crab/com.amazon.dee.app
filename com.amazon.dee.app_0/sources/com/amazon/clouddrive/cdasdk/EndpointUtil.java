package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.account.GetEndpointResponse;
import com.amazon.clouddrive.cdasdk.dagger.component.ApplicationComponent;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
/* loaded from: classes11.dex */
public final class EndpointUtil {
    private EndpointUtil() {
    }

    @NonNull
    public static SpecificEndpointConfiguration convertResponseToEndpoints(@NonNull GetEndpointResponse getEndpointResponse) throws IllegalStateException {
        ApplicationComponent appComponent = CDClientImpl.getAppComponent();
        if (appComponent != null) {
            SystemUtil systemUtil = appComponent.getSystemUtil();
            return new SpecificEndpointConfiguration(getEndpointResponse.getMetadataUrl(), getEndpointResponse.getContentUrl(), getEndpointResponse.getContentUrl(), systemUtil.replaceUriPath(getEndpointResponse.getMetadataUrl(), "drive/v2/photosGroups"), systemUtil.replaceUriPath(getEndpointResponse.getMetadataUrl(), "photoapp"), systemUtil.replaceUriPath(getEndpointResponse.getMetadataUrl(), "drive/v2"), systemUtil.replaceUriPath(getEndpointResponse.getMetadataUrl(), "drive/v2/device-personalization"));
        }
        throw new IllegalStateException("Failed to call CDClient.createClient prior to this call");
    }
}
