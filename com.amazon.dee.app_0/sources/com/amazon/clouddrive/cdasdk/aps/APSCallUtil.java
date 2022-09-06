package com.amazon.clouddrive.cdasdk.aps;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import com.amazon.clouddrive.cdasdk.aps.common.APSInput;
import com.amazon.clouddrive.cdasdk.aps.common.ResourceVersion;
import java.util.Locale;
/* loaded from: classes11.dex */
public class APSCallUtil extends CallUtil<APSInput> {
    @NonNull
    static final ResourceVersion DEFAULT_VERSION = ResourceVersion.V3;

    public APSCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.APS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    public void initRequest(@NonNull APSInput aPSInput) {
        if (aPSInput.getLang() == null) {
            aPSInput.setLang(Locale.getDefault().toString());
        }
        if (aPSInput.getResourceVersion() == null) {
            aPSInput.setResourceVersion(DEFAULT_VERSION);
        }
        if (aPSInput.getDevicePlatform() == null) {
            aPSInput.setDevicePlatform(getClientConfig().getApsDevicePlatform());
        }
    }
}
