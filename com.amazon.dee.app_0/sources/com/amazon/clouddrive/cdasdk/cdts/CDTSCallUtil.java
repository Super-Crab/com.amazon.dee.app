package com.amazon.clouddrive.cdasdk.cdts;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
/* loaded from: classes11.dex */
public class CDTSCallUtil extends CallUtil<ThumbnailRequest> {
    public CDTSCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.CDTS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    public void initRequest(@NonNull ThumbnailRequest thumbnailRequest) {
    }
}
