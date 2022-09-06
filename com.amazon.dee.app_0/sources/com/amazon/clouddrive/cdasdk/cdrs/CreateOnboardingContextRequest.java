package com.amazon.clouddrive.cdasdk.cdrs;

import com.amazon.clouddrive.cdasdk.prompto.common.ResourceVersion;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
/* loaded from: classes11.dex */
public class CreateOnboardingContextRequest extends CDRSServiceCustomerRequest {
    @JsonProperty("contextType")
    private String contextType;
    @JsonProperty("data")
    private HashMap<String, String> data;
    @JsonProperty("dataVersion")
    private String dataVersion;

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceCustomerRequest, com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof CreateOnboardingContextRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceCustomerRequest, com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreateOnboardingContextRequest)) {
            return false;
        }
        CreateOnboardingContextRequest createOnboardingContextRequest = (CreateOnboardingContextRequest) obj;
        if (!createOnboardingContextRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String contextType = getContextType();
        String contextType2 = createOnboardingContextRequest.getContextType();
        if (contextType != null ? !contextType.equals(contextType2) : contextType2 != null) {
            return false;
        }
        String dataVersion = getDataVersion();
        String dataVersion2 = createOnboardingContextRequest.getDataVersion();
        if (dataVersion != null ? !dataVersion.equals(dataVersion2) : dataVersion2 != null) {
            return false;
        }
        HashMap<String, String> data = getData();
        HashMap<String, String> data2 = createOnboardingContextRequest.getData();
        return data != null ? data.equals(data2) : data2 == null;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public /* bridge */ /* synthetic */ String getApplicationId() {
        return super.getApplicationId();
    }

    public String getContextType() {
        return this.contextType;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceCustomerRequest
    public /* bridge */ /* synthetic */ String getCustomerId() {
        return super.getCustomerId();
    }

    public HashMap<String, String> getData() {
        return this.data;
    }

    public String getDataVersion() {
        return this.dataVersion;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public /* bridge */ /* synthetic */ String getLang() {
        return super.getLang();
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public /* bridge */ /* synthetic */ ResourceVersion getResourceVersion() {
        return super.getResourceVersion();
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceCustomerRequest, com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String contextType = getContextType();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (contextType == null ? 43 : contextType.hashCode());
        String dataVersion = getDataVersion();
        int hashCode3 = (hashCode2 * 59) + (dataVersion == null ? 43 : dataVersion.hashCode());
        HashMap<String, String> data = getData();
        int i2 = hashCode3 * 59;
        if (data != null) {
            i = data.hashCode();
        }
        return i2 + i;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    @JsonProperty("applicationId")
    public /* bridge */ /* synthetic */ void setApplicationId(String str) {
        super.setApplicationId(str);
    }

    @JsonProperty("contextType")
    public void setContextType(String str) {
        this.contextType = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceCustomerRequest
    @JsonProperty("customerId")
    public /* bridge */ /* synthetic */ void setCustomerId(String str) {
        super.setCustomerId(str);
    }

    @JsonProperty("data")
    public void setData(HashMap<String, String> hashMap) {
        this.data = hashMap;
    }

    @JsonProperty("dataVersion")
    public void setDataVersion(String str) {
        this.dataVersion = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    @JsonProperty("lang")
    public /* bridge */ /* synthetic */ void setLang(String str) {
        super.setLang(str);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    @JsonProperty("resourceVersion")
    public /* bridge */ /* synthetic */ void setResourceVersion(ResourceVersion resourceVersion) {
        super.setResourceVersion(resourceVersion);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceCustomerRequest, com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateOnboardingContextRequest(contextType=");
        outline107.append(getContextType());
        outline107.append(", dataVersion=");
        outline107.append(getDataVersion());
        outline107.append(", data=");
        outline107.append(getData());
        outline107.append(")");
        return outline107.toString();
    }
}
