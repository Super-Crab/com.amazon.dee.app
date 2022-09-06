package com.amazon.whisperjoin.common.sharedtypes.reporting;

import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
/* loaded from: classes13.dex */
public interface ProvisionableEventReporter {
    void reportDoneSuccess();

    void reportNetworkedSuccess(WifiNetworkInfo wifiNetworkInfo);

    void reportRegisteredSuccess(String str);
}
