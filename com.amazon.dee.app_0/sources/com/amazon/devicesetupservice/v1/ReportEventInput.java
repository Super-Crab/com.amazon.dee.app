package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.ErrorInfo;
/* loaded from: classes12.dex */
public class ReportEventInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ReportEventInput");
    private CredentialLockerUsageInfo credentialLockerUsageInfo;
    private ErrorInfo errorInfo;
    private String event;
    private String keyExchangeMethod;
    private String origin;
    private ProvisionableInfo provisionableInfo;
    private ProvisionerInfo provisionerInfo;
    private String provisioningMethod;
    private String radio;
    private String registrationState;
    private String reportUrl;
    private int sequenceNumber;
    private String sessionToken;
    private String state;
    private String trustMethod;
    private WifiNetworkInfo wifiNetworkInfo;

    public boolean equals(Object obj) {
        if (!(obj instanceof ReportEventInput)) {
            return false;
        }
        ReportEventInput reportEventInput = (ReportEventInput) obj;
        return Helper.equals(this.sessionToken, reportEventInput.sessionToken) && Helper.equals(this.errorInfo, reportEventInput.errorInfo) && Helper.equals(this.credentialLockerUsageInfo, reportEventInput.credentialLockerUsageInfo) && Helper.equals(this.state, reportEventInput.state) && Helper.equals(this.trustMethod, reportEventInput.trustMethod) && Helper.equals(this.registrationState, reportEventInput.registrationState) && Helper.equals(this.provisioningMethod, reportEventInput.provisioningMethod) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(reportEventInput.sequenceNumber)) && Helper.equals(this.reportUrl, reportEventInput.reportUrl) && Helper.equals(this.provisionerInfo, reportEventInput.provisionerInfo) && Helper.equals(this.event, reportEventInput.event) && Helper.equals(this.origin, reportEventInput.origin) && Helper.equals(this.keyExchangeMethod, reportEventInput.keyExchangeMethod) && Helper.equals(this.provisionableInfo, reportEventInput.provisionableInfo) && Helper.equals(this.radio, reportEventInput.radio) && Helper.equals(this.wifiNetworkInfo, reportEventInput.wifiNetworkInfo);
    }

    public CredentialLockerUsageInfo getCredentialLockerUsageInfo() {
        return this.credentialLockerUsageInfo;
    }

    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    public String getEvent() {
        return this.event;
    }

    public String getKeyExchangeMethod() {
        return this.keyExchangeMethod;
    }

    public String getOrigin() {
        return this.origin;
    }

    public ProvisionableInfo getProvisionableInfo() {
        return this.provisionableInfo;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.provisionerInfo;
    }

    public String getProvisioningMethod() {
        return this.provisioningMethod;
    }

    public String getRadio() {
        return this.radio;
    }

    public String getRegistrationState() {
        return this.registrationState;
    }

    public String getReportUrl() {
        return this.reportUrl;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public String getState() {
        return this.state;
    }

    public String getTrustMethod() {
        return this.trustMethod;
    }

    public WifiNetworkInfo getWifiNetworkInfo() {
        return this.wifiNetworkInfo;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.sessionToken, this.errorInfo, this.credentialLockerUsageInfo, this.state, this.trustMethod, this.registrationState, this.provisioningMethod, Integer.valueOf(this.sequenceNumber), this.reportUrl, this.provisionerInfo, this.event, this.origin, this.keyExchangeMethod, this.provisionableInfo, this.radio, this.wifiNetworkInfo);
    }

    public void setCredentialLockerUsageInfo(CredentialLockerUsageInfo credentialLockerUsageInfo) {
        this.credentialLockerUsageInfo = credentialLockerUsageInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public void setKeyExchangeMethod(String str) {
        this.keyExchangeMethod = str;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public void setProvisionableInfo(ProvisionableInfo provisionableInfo) {
        this.provisionableInfo = provisionableInfo;
    }

    public void setProvisionerInfo(ProvisionerInfo provisionerInfo) {
        this.provisionerInfo = provisionerInfo;
    }

    public void setProvisioningMethod(String str) {
        this.provisioningMethod = str;
    }

    public void setRadio(String str) {
        this.radio = str;
    }

    public void setRegistrationState(String str) {
        this.registrationState = str;
    }

    public void setReportUrl(String str) {
        this.reportUrl = str;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setTrustMethod(String str) {
        this.trustMethod = str;
    }

    public void setWifiNetworkInfo(WifiNetworkInfo wifiNetworkInfo) {
        this.wifiNetworkInfo = wifiNetworkInfo;
    }
}
