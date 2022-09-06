package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisionerDetails;
/* loaded from: classes12.dex */
public class ScapProvisionerDetails extends ProvisionerDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ScapProvisionerDetails");
    private String countryCode;
    private String firmwareVersion;
    private String languageLocale;
    private String softwareVersion;

    @Override // com.amazon.devicesetupservice.ProvisionerDetails
    public boolean equals(Object obj) {
        if (!(obj instanceof ScapProvisionerDetails)) {
            return false;
        }
        ScapProvisionerDetails scapProvisionerDetails = (ScapProvisionerDetails) obj;
        return super.equals(obj) && Helper.equals(this.languageLocale, scapProvisionerDetails.languageLocale) && Helper.equals(this.countryCode, scapProvisionerDetails.countryCode) && Helper.equals(this.firmwareVersion, scapProvisionerDetails.firmwareVersion) && Helper.equals(this.softwareVersion, scapProvisionerDetails.softwareVersion);
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getLanguageLocale() {
        return this.languageLocale;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    @Override // com.amazon.devicesetupservice.ProvisionerDetails
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.languageLocale, this.countryCode, this.firmwareVersion, this.softwareVersion);
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setLanguageLocale(String str) {
        this.languageLocale = str;
    }

    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }
}
