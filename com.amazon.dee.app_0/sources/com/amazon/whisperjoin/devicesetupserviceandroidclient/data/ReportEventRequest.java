package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetupservice.v1.CredentialLockerUsageInfo;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ReportEventRequest {
    private final CredentialLockerUsageInfo mCredentialLockerUsageInfo;
    private final ErrorInfo mErrorInfo;
    private final String mEvent;
    private final String mKeyExchangeMethod;
    private final ProvisionableInfo mProvisionableInfo;
    private final ProvisionerInfo mProvisionerInfo;
    private final String mProvisioningMethod;
    private final String mRadio;
    private final String mRegistrationState;
    private final String mReportingUrl;
    private final int mSequenceNumber;
    private final String mState;
    private final String mTrustMethod;
    private final WifiNetworkInfo mWifiNetworkInfo;

    /* JADX INFO: Access modifiers changed from: protected */
    public ReportEventRequest(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, String str8, WifiNetworkInfo wifiNetworkInfo, ProvisionableInfo provisionableInfo, ProvisionerInfo provisionerInfo, ErrorInfo errorInfo, CredentialLockerUsageInfo credentialLockerUsageInfo) {
        this.mReportingUrl = str;
        this.mState = str2;
        this.mEvent = str3;
        this.mSequenceNumber = i;
        this.mProvisioningMethod = str4;
        this.mTrustMethod = str5;
        this.mKeyExchangeMethod = str6;
        this.mRadio = str7;
        this.mRegistrationState = str8;
        this.mWifiNetworkInfo = wifiNetworkInfo;
        this.mProvisionableInfo = provisionableInfo;
        this.mProvisionerInfo = provisionerInfo;
        this.mErrorInfo = errorInfo;
        this.mCredentialLockerUsageInfo = credentialLockerUsageInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ReportEventRequest.class != obj.getClass()) {
            return false;
        }
        ReportEventRequest reportEventRequest = (ReportEventRequest) obj;
        return this.mSequenceNumber == reportEventRequest.mSequenceNumber && Objects.equal(this.mReportingUrl, reportEventRequest.mReportingUrl) && Objects.equal(this.mState, reportEventRequest.mState) && Objects.equal(this.mEvent, reportEventRequest.mEvent) && Objects.equal(this.mProvisioningMethod, reportEventRequest.mProvisioningMethod) && Objects.equal(this.mTrustMethod, reportEventRequest.mTrustMethod) && Objects.equal(this.mKeyExchangeMethod, reportEventRequest.mKeyExchangeMethod) && Objects.equal(this.mRadio, reportEventRequest.mRadio) && Objects.equal(this.mRegistrationState, reportEventRequest.mRegistrationState) && Objects.equal(this.mWifiNetworkInfo, reportEventRequest.mWifiNetworkInfo) && Objects.equal(this.mProvisionableInfo, reportEventRequest.mProvisionableInfo) && Objects.equal(this.mProvisionerInfo, reportEventRequest.mProvisionerInfo) && Objects.equal(this.mErrorInfo, reportEventRequest.mErrorInfo) && Objects.equal(this.mCredentialLockerUsageInfo, reportEventRequest.mCredentialLockerUsageInfo);
    }

    public CredentialLockerUsageInfo getCredentialLockerUsageInfo() {
        return this.mCredentialLockerUsageInfo;
    }

    public ErrorInfo getErrorInfo() {
        return this.mErrorInfo;
    }

    public String getEvent() {
        return this.mEvent;
    }

    public String getKeyExchangeMethod() {
        return this.mKeyExchangeMethod;
    }

    public ProvisionableInfo getProvisionableInfo() {
        return this.mProvisionableInfo;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.mProvisionerInfo;
    }

    public String getProvisioningMethod() {
        return this.mProvisioningMethod;
    }

    public String getRadio() {
        return this.mRadio;
    }

    public String getRegistrationState() {
        return this.mRegistrationState;
    }

    public String getReportingUrl() {
        return this.mReportingUrl;
    }

    public int getSequenceNumber() {
        return this.mSequenceNumber;
    }

    public String getState() {
        return this.mState;
    }

    public String getTrustMethod() {
        return this.mTrustMethod;
    }

    public WifiNetworkInfo getWifiNetworkInfo() {
        return this.mWifiNetworkInfo;
    }

    public int hashCode() {
        return Objects.hashCode(this.mReportingUrl, this.mState, this.mEvent, Integer.valueOf(this.mSequenceNumber), this.mProvisioningMethod, this.mTrustMethod, this.mKeyExchangeMethod, this.mRadio, this.mRegistrationState, this.mWifiNetworkInfo, this.mProvisionableInfo, this.mProvisionerInfo, this.mErrorInfo, this.mCredentialLockerUsageInfo);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mReportingUrl", this.mReportingUrl).add("mState", this.mState).add("mEvent", this.mEvent).add("mSequenceNumber", this.mSequenceNumber).add("mProvisioningMethod", this.mProvisioningMethod).add("mTrustMethod", this.mTrustMethod).add("mKeyExchangeMethod", this.mKeyExchangeMethod).add("mRadio", this.mRadio).add("mRegistrationState", this.mRegistrationState).add("mWifiNetworkInfo", this.mWifiNetworkInfo).add("mProvisionableInfo", this.mProvisionableInfo).add("mProvisionerInfo", this.mProvisionerInfo).add("mErrorInfo", this.mErrorInfo).add("mCredentialLockerUsageInfo", this.mCredentialLockerUsageInfo).toString();
    }
}
