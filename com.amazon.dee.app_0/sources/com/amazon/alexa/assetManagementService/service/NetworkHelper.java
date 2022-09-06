package com.amazon.alexa.assetManagementService.service;

import android.content.Context;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceConstants;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceMetricsConstants;
import okhttp3.Request;
/* loaded from: classes6.dex */
class NetworkHelper {
    private static final String LOGTAG = "GetAssetURLService";
    private Context context;
    private String currentEtag;
    private String currentResolution;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetworkHelper(Context context, String str) {
        this.context = context;
        this.currentResolution = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request createRequest(boolean z) {
        this.currentEtag = this.context.getSharedPreferences(GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, 0).getString("ETag", null);
        Request.Builder url = new Request.Builder().url(String.format(z ? GetAssetURLServiceConstants.PROD_NETWORK_CALL_CLOUDFRONT_URL : GetAssetURLServiceConstants.STAGING_NETWORK_CALL_CLOUDFRONT_URL, this.currentResolution));
        Request build = url.build();
        String str = this.currentEtag;
        return str != null ? url.addHeader("If-None-Match", str).build() : build;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getCurrentEtag() {
        return this.currentEtag;
    }
}
