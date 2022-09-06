package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class SmartHomeCredential implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.SmartHomeCredential");
    private String authMaterialData;
    private String authMaterialId;
    private String protocolType;

    public boolean equals(Object obj) {
        if (!(obj instanceof SmartHomeCredential)) {
            return false;
        }
        SmartHomeCredential smartHomeCredential = (SmartHomeCredential) obj;
        return Helper.equals(this.authMaterialId, smartHomeCredential.authMaterialId) && Helper.equals(this.authMaterialData, smartHomeCredential.authMaterialData) && Helper.equals(this.protocolType, smartHomeCredential.protocolType);
    }

    public String getAuthMaterialData() {
        return this.authMaterialData;
    }

    public String getAuthMaterialId() {
        return this.authMaterialId;
    }

    public String getProtocolType() {
        return this.protocolType;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.authMaterialId, this.authMaterialData, this.protocolType);
    }

    public void setAuthMaterialData(String str) {
        this.authMaterialData = str;
    }

    public void setAuthMaterialId(String str) {
        this.authMaterialId = str;
    }

    public void setProtocolType(String str) {
        this.protocolType = str;
    }
}
