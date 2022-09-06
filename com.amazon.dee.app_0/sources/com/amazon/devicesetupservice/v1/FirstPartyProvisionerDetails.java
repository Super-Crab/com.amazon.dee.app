package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisionerDetails;
/* loaded from: classes12.dex */
public class FirstPartyProvisionerDetails extends ProvisionerDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.FirstPartyProvisionerDetails");
    private String application;
    private String applicationVersion;
    private String deviceModel;
    private String firmwareVersion;
    private String manufacturer;

    @Override // com.amazon.devicesetupservice.ProvisionerDetails
    public boolean equals(Object obj) {
        if (!(obj instanceof FirstPartyProvisionerDetails)) {
            return false;
        }
        FirstPartyProvisionerDetails firstPartyProvisionerDetails = (FirstPartyProvisionerDetails) obj;
        return super.equals(obj) && Helper.equals(this.firmwareVersion, firstPartyProvisionerDetails.firmwareVersion) && Helper.equals(this.application, firstPartyProvisionerDetails.application) && Helper.equals(this.deviceModel, firstPartyProvisionerDetails.deviceModel) && Helper.equals(this.manufacturer, firstPartyProvisionerDetails.manufacturer) && Helper.equals(this.applicationVersion, firstPartyProvisionerDetails.applicationVersion);
    }

    public String getApplication() {
        return this.application;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override // com.amazon.devicesetupservice.ProvisionerDetails
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.firmwareVersion, this.application, this.deviceModel, this.manufacturer, this.applicationVersion);
    }

    public void setApplication(String str) {
        this.application = str;
    }

    public void setApplicationVersion(String str) {
        this.applicationVersion = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }
}
