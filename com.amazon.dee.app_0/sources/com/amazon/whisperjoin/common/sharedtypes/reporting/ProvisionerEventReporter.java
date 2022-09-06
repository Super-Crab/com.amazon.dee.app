package com.amazon.whisperjoin.common.sharedtypes.reporting;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetupservice.v1.CredentialLockerUsageInfo;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
/* loaded from: classes13.dex */
public interface ProvisionerEventReporter {
    void registerNewReportingSession(String str, String str2);

    void removeReportingSession(String str);

    void reportConnectedFailure(String str, String str2, String str3, String str4, String str5, ErrorInfo errorInfo);

    void reportConnectedSuccess(String str, String str2, String str3, String str4, String str5);

    void reportDoneFailure(String str, String str2, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo);

    void reportNetworkedFailure(String str, String str2, WifiNetworkInfo wifiNetworkInfo, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo);

    void reportProvisionedFailure(String str, String str2, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo);

    void reportProvisionedSuccess(String str, String str2, WifiNetworkInfo wifiNetworkInfo, ProvisionableInfo provisionableInfo, CredentialLockerUsageInfo credentialLockerUsageInfo);

    void reportProvisionerComplete(String str, String str2, WifiNetworkInfo wifiNetworkInfo, ProvisionableInfo provisionableInfo, CredentialLockerUsageInfo credentialLockerUsageInfo);

    void reportRegisteredFailure(String str, String str2, String str3, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo);

    void reportRetrievedProvisioningDetailsFailure(String str, String str2, ProvisionableInfo provisionableInfo, ErrorInfo errorInfo);

    void reportRetrievedProvisioningDetailsSuccess(String str, String str2, ProvisionableInfo provisionableInfo, String str3, WifiNetworkInfo wifiNetworkInfo);

    void reportSecureChannelEstablishedFailure(String str, String str2, String str3, String str4, String str5, ErrorInfo errorInfo);

    void reportSecureChannelEstablishedSuccess(String str, String str2, String str3, String str4, String str5);
}
