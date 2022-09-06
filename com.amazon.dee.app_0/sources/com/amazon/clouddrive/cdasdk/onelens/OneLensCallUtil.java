package com.amazon.clouddrive.cdasdk.onelens;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import com.amazon.clouddrive.cdasdk.onelens.common.OneLensRequest;
/* loaded from: classes11.dex */
public class OneLensCallUtil extends CallUtil<OneLensRequest> {
    public OneLensCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.OneLens;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    public void initRequest(@NonNull OneLensRequest oneLensRequest) {
    }
}
