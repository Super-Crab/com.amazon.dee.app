package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
import java.util.List;
/* loaded from: classes12.dex */
public class ReportSmartHomeEventInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.ReportSmartHomeEventInput");
    private String authMaterialIndex;
    private ErrorInfo errorInfo;
    private String event;
    private String origin;
    private String productIndex;
    private ProvisionableInfo provisioneeInfo;
    private List<MacIdentifier> provisioneeMacIdentifiers;
    private ProvisionerInfo provisionerInfo;
    private String provisioningMethod;
    private String radio;
    private String reportEventIdentifier;
    private int sequenceNumber;
    private String sessionId;
    private String state;
    private WifiNetworkInfo wifiNetworkInfo;

    public boolean equals(Object obj) {
        if (!(obj instanceof ReportSmartHomeEventInput)) {
            return false;
        }
        ReportSmartHomeEventInput reportSmartHomeEventInput = (ReportSmartHomeEventInput) obj;
        return Helper.equals(this.errorInfo, reportSmartHomeEventInput.errorInfo) && Helper.equals(this.provisionerInfo, reportSmartHomeEventInput.provisionerInfo) && Helper.equals(this.reportEventIdentifier, reportSmartHomeEventInput.reportEventIdentifier) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(reportSmartHomeEventInput.sequenceNumber)) && Helper.equals(this.productIndex, reportSmartHomeEventInput.productIndex) && Helper.equals(this.provisioneeMacIdentifiers, reportSmartHomeEventInput.provisioneeMacIdentifiers) && Helper.equals(this.origin, reportSmartHomeEventInput.origin) && Helper.equals(this.state, reportSmartHomeEventInput.state) && Helper.equals(this.event, reportSmartHomeEventInput.event) && Helper.equals(this.provisioningMethod, reportSmartHomeEventInput.provisioningMethod) && Helper.equals(this.authMaterialIndex, reportSmartHomeEventInput.authMaterialIndex) && Helper.equals(this.sessionId, reportSmartHomeEventInput.sessionId) && Helper.equals(this.provisioneeInfo, reportSmartHomeEventInput.provisioneeInfo) && Helper.equals(this.radio, reportSmartHomeEventInput.radio) && Helper.equals(this.wifiNetworkInfo, reportSmartHomeEventInput.wifiNetworkInfo);
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    public String getEvent() {
        return this.event;
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public ProvisionableInfo getProvisioneeInfo() {
        return this.provisioneeInfo;
    }

    public List<MacIdentifier> getProvisioneeMacIdentifiers() {
        return this.provisioneeMacIdentifiers;
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

    public String getReportEventIdentifier() {
        return this.reportEventIdentifier;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getState() {
        return this.state;
    }

    public WifiNetworkInfo getWifiNetworkInfo() {
        return this.wifiNetworkInfo;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.errorInfo, this.provisionerInfo, this.reportEventIdentifier, Integer.valueOf(this.sequenceNumber), this.productIndex, this.provisioneeMacIdentifiers, this.origin, this.state, this.event, this.provisioningMethod, this.authMaterialIndex, this.sessionId, this.provisioneeInfo, this.radio, this.wifiNetworkInfo);
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setProvisioneeInfo(ProvisionableInfo provisionableInfo) {
        this.provisioneeInfo = provisionableInfo;
    }

    public void setProvisioneeMacIdentifiers(List<MacIdentifier> list) {
        this.provisioneeMacIdentifiers = list;
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

    public void setReportEventIdentifier(String str) {
        this.reportEventIdentifier = str;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setWifiNetworkInfo(WifiNetworkInfo wifiNetworkInfo) {
        this.wifiNetworkInfo = wifiNetworkInfo;
    }
}
