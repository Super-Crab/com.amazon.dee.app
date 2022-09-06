package com.amazon.clouddrive.cdasdk.prompto;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class PromptoCallUtil extends CallUtil<PromptoServiceRequest> {
    public PromptoCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.Prompto;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    public void initRequest(@NonNull PromptoServiceRequest promptoServiceRequest) {
        promptoServiceRequest.setApplicationId(getClientConfig().getApplicationId());
        promptoServiceRequest.setLang(Locale.getDefault().toString());
    }
}
