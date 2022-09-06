package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class CredentialLockerBaseRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.CredentialLockerBaseRequest");
    private String clientManufacturer;
    private String clientModel;
    private String clientName;
    private String clientOs;
    private String clientOsVersion;
    private String clientProduct;
    private String clientVersion;
    private String deviceManufacturer;
    private String deviceName;
    private String deviceVersion;

    public boolean equals(Object obj) {
        if (!(obj instanceof CredentialLockerBaseRequest)) {
            return false;
        }
        CredentialLockerBaseRequest credentialLockerBaseRequest = (CredentialLockerBaseRequest) obj;
        return Helper.equals(this.clientManufacturer, credentialLockerBaseRequest.clientManufacturer) && Helper.equals(this.clientOsVersion, credentialLockerBaseRequest.clientOsVersion) && Helper.equals(this.clientName, credentialLockerBaseRequest.clientName) && Helper.equals(this.deviceName, credentialLockerBaseRequest.deviceName) && Helper.equals(this.clientModel, credentialLockerBaseRequest.clientModel) && Helper.equals(this.clientVersion, credentialLockerBaseRequest.clientVersion) && Helper.equals(this.clientOs, credentialLockerBaseRequest.clientOs) && Helper.equals(this.clientProduct, credentialLockerBaseRequest.clientProduct) && Helper.equals(this.deviceManufacturer, credentialLockerBaseRequest.deviceManufacturer) && Helper.equals(this.deviceVersion, credentialLockerBaseRequest.deviceVersion);
    }

    public String getClientManufacturer() {
        return this.clientManufacturer;
    }

    public String getClientModel() {
        return this.clientModel;
    }

    public String getClientName() {
        return this.clientName;
    }

    public String getClientOs() {
        return this.clientOs;
    }

    public String getClientOsVersion() {
        return this.clientOsVersion;
    }

    public String getClientProduct() {
        return this.clientProduct;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.clientManufacturer, this.clientOsVersion, this.clientName, this.deviceName, this.clientModel, this.clientVersion, this.clientOs, this.clientProduct, this.deviceManufacturer, this.deviceVersion);
    }

    public void setClientManufacturer(String str) {
        this.clientManufacturer = str;
    }

    public void setClientModel(String str) {
        this.clientModel = str;
    }

    public void setClientName(String str) {
        this.clientName = str;
    }

    public void setClientOs(String str) {
        this.clientOs = str;
    }

    public void setClientOsVersion(String str) {
        this.clientOsVersion = str;
    }

    public void setClientProduct(String str) {
        this.clientProduct = str;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public void setDeviceManufacturer(String str) {
        this.deviceManufacturer = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }
}
