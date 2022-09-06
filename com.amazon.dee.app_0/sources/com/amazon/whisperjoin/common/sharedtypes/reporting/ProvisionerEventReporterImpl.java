package com.amazon.whisperjoin.common.sharedtypes.reporting;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetup.common.v1.Event;
import com.amazon.devicesetupservice.reporting.State;
import com.amazon.devicesetupservice.v1.CredentialLockerUsageInfo;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class ProvisionerEventReporterImpl extends AbstractProvisioningEventReporter implements ProvisionerEventReporter {
    private static final String TAG = "ProvisionerEventReporterImpl";
    private final Map<String, ReportingSesssion> mDeviceIdToReportingSessionMap;
    final ProvisionerInfo mProvisionerInfo;

    public ProvisionerEventReporterImpl(DSSClient dSSClient, ProvisionerInfo provisionerInfo) {
        super(dSSClient);
        if (provisionerInfo != null) {
            this.mDeviceIdToReportingSessionMap = new HashMap();
            this.mProvisionerInfo = provisionerInfo;
            return;
        }
        throw new IllegalArgumentException("provisionerInfo can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void registerNewReportingSession(String str, String str2) {
        if (str != null) {
            if (str2 != null) {
                if (this.mDeviceIdToReportingSessionMap.containsKey(str)) {
                    String str3 = TAG;
                    WJLog.d(str3, "Replacing reporting url for deviceId: " + str);
                    this.mDeviceIdToReportingSessionMap.remove(str);
                }
                this.mDeviceIdToReportingSessionMap.put(str, new ReportingSesssion(str2));
                return;
            }
            throw new IllegalArgumentException("reportingUrl can not be null");
        }
        throw new IllegalArgumentException("deviceId can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void removeReportingSession(String str) {
        if (str != null) {
            if (this.mDeviceIdToReportingSessionMap.remove(str) != null) {
                return;
            }
            String str2 = TAG;
            WJLog.w(str2, "No session found for deviceId: " + str);
            return;
        }
        throw new IllegalArgumentException("deviceId can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportConnectedFailure(String str, String str2, String str3, String str4, String str5, ErrorInfo errorInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record connection failure event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState("CONNECTED").setEvent(Event.FAILURE).setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setTrustMethod(str3).setKeyExchangeMethod(str4).setRadio(str5).setErrorInfo(errorInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportConnectedSuccess(String str, String str2, String str3, String str4, String str5) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record connection success event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState("CONNECTED").setEvent("SUCCESS").setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setTrustMethod(str3).setKeyExchangeMethod(str4).setRadio(str5).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportDoneFailure(String str, String str2, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record done failure event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState("DONE").setEvent(Event.FAILURE).setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setProvisionableInfo(provisionableInfo).setErrorInfo(errorInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportNetworkedFailure(String str, String str2, WifiNetworkInfo wifiNetworkInfo, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record networked failure event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.NETWORKED).setEvent(Event.FAILURE).setProvisionerInfo(this.mProvisionerInfo).setProvisionableInfo(provisionableInfo).setProvisioningMethod(str2).setWifiNetworkInfo(wifiNetworkInfo).setErrorInfo(errorInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportProvisionedFailure(String str, String str2, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record provisioned failure event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.PROVISIONED).setEvent(Event.FAILURE).setErrorInfo(errorInfo).setProvisioningMethod(str2).setProvisionerInfo(this.mProvisionerInfo).setProvisionableInfo(provisionableInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportProvisionedSuccess(String str, String str2, WifiNetworkInfo wifiNetworkInfo, ProvisionableInfo provisionableInfo, CredentialLockerUsageInfo credentialLockerUsageInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record provisioned success event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.PROVISIONED).setEvent("SUCCESS").setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setProvisionableInfo(provisionableInfo).setWifiNetworkInfo(wifiNetworkInfo).setCredentialLockerUsageInfo(credentialLockerUsageInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportProvisionerComplete(String str, String str2, WifiNetworkInfo wifiNetworkInfo, ProvisionableInfo provisionableInfo, CredentialLockerUsageInfo credentialLockerUsageInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record provisioner complete event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.PROVISIONER_COMPLETE).setEvent("SUCCESS").setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setProvisionableInfo(provisionableInfo).setWifiNetworkInfo(wifiNetworkInfo).setCredentialLockerUsageInfo(credentialLockerUsageInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportRegisteredFailure(String str, String str2, String str3, ErrorInfo errorInfo, ProvisionableInfo provisionableInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record registered failure event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState("REGISTERED").setEvent(Event.FAILURE).setProvisionerInfo(this.mProvisionerInfo).setProvisionableInfo(provisionableInfo).setRegistrationState(str3).setProvisioningMethod(str2).setErrorInfo(errorInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportRetrievedProvisioningDetailsFailure(String str, String str2, ProvisionableInfo provisionableInfo, ErrorInfo errorInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record retrieved provisioning details failure event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.RETRIEVED_PROVISIONING_DETAILS).setEvent(Event.FAILURE).setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setProvisionableInfo(provisionableInfo).setErrorInfo(errorInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportRetrievedProvisioningDetailsSuccess(String str, String str2, ProvisionableInfo provisionableInfo, String str3, WifiNetworkInfo wifiNetworkInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record retrieved provisioning details success event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.RETRIEVED_PROVISIONING_DETAILS).setEvent("SUCCESS").setProvisionerInfo(this.mProvisionerInfo).setProvisionableInfo(provisionableInfo).setProvisioningMethod(str2).setWifiNetworkInfo(wifiNetworkInfo).setRegistrationState(str3).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportSecureChannelEstablishedFailure(String str, String str2, String str3, String str4, String str5, ErrorInfo errorInfo) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record connection failure event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.SECURE_CHANNEL_ESTABLISHED).setEvent(Event.FAILURE).setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setTrustMethod(str3).setKeyExchangeMethod(str4).setRadio(str5).setErrorInfo(errorInfo).createReportEventRequest());
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter
    public void reportSecureChannelEstablishedSuccess(String str, String str2, String str3, String str4, String str5) {
        ReportingSesssion reportingSesssion = this.mDeviceIdToReportingSessionMap.get(str);
        if (reportingSesssion == null) {
            WJLog.w(TAG, "No session found to record connection success event");
        } else {
            reportEvent(getBuilderForSession(reportingSesssion).setState(State.SECURE_CHANNEL_ESTABLISHED).setEvent("SUCCESS").setProvisionerInfo(this.mProvisionerInfo).setProvisioningMethod(str2).setTrustMethod(str3).setKeyExchangeMethod(str4).setRadio(str5).createReportEventRequest());
        }
    }
}
