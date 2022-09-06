package com.amazon.clouddrive.cdasdk.cdrs;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class CDRSCallUtil extends CallUtil<CDRSServiceRequest> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CDRSCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.CDRS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    public void initRequest(@NonNull CDRSServiceRequest cDRSServiceRequest) {
        cDRSServiceRequest.setApplicationId(getClientConfig().getApplicationId());
        cDRSServiceRequest.setLang(Locale.getDefault().toString());
    }
}
