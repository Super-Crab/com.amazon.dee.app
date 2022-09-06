package com.amazon.clouddrive.cdasdk.dps;

import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsRequest;
import com.amazon.clouddrive.cdasdk.dps.common.DPSRequest;
/* loaded from: classes11.dex */
public final class DPSCallUtil extends CallUtil<DPSRequest> {
    public DPSCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @NonNull
    public String getDeviceLocale() {
        int i = Build.VERSION.SDK_INT;
        return getClientConfig().getAppContext().getResources().getConfiguration().getLocales().get(0).toString();
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.DPS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    public void initRequest(@NonNull DPSRequest dPSRequest) {
        if (dPSRequest instanceof DPSCollectionsRequest) {
            ((DPSCollectionsRequest) dPSRequest).setLocale(getDeviceLocale());
        }
    }
}
