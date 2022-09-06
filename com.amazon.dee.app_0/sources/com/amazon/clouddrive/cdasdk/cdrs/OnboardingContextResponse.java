package com.amazon.clouddrive.cdasdk.cdrs;

import com.amazon.alexa.sharing.api.models.Message;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
/* loaded from: classes11.dex */
public class OnboardingContextResponse {
    @JsonProperty("contextId")
    private String contextId;
    @JsonProperty("contextType")
    private String contextType;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("data")
    private HashMap<String, String> data;
    @JsonProperty("dataVersion")
    private String dataVersion;
    @JsonProperty(Message.SERIALIZED_NAME_MODIFIED_DATE)
    private String modifiedDate;
    @JsonProperty("state")
    private String state;
    @JsonProperty("version")
    private Long version;

    protected boolean canEqual(Object obj) {
        return obj instanceof OnboardingContextResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OnboardingContextResponse)) {
            return false;
        }
        OnboardingContextResponse onboardingContextResponse = (OnboardingContextResponse) obj;
        if (!onboardingContextResponse.canEqual(this)) {
            return false;
        }
        String contextId = getContextId();
        String contextId2 = onboardingContextResponse.getContextId();
        if (contextId != null ? !contextId.equals(contextId2) : contextId2 != null) {
            return false;
        }
        String contextType = getContextType();
        String contextType2 = onboardingContextResponse.getContextType();
        if (contextType != null ? !contextType.equals(contextType2) : contextType2 != null) {
            return false;
        }
        String dataVersion = getDataVersion();
        String dataVersion2 = onboardingContextResponse.getDataVersion();
        if (dataVersion != null ? !dataVersion.equals(dataVersion2) : dataVersion2 != null) {
            return false;
        }
        HashMap<String, String> data = getData();
        HashMap<String, String> data2 = onboardingContextResponse.getData();
        if (data != null ? !data.equals(data2) : data2 != null) {
            return false;
        }
        String state = getState();
        String state2 = onboardingContextResponse.getState();
        if (state != null ? !state.equals(state2) : state2 != null) {
            return false;
        }
        String createdDate = getCreatedDate();
        String createdDate2 = onboardingContextResponse.getCreatedDate();
        if (createdDate != null ? !createdDate.equals(createdDate2) : createdDate2 != null) {
            return false;
        }
        String modifiedDate = getModifiedDate();
        String modifiedDate2 = onboardingContextResponse.getModifiedDate();
        if (modifiedDate != null ? !modifiedDate.equals(modifiedDate2) : modifiedDate2 != null) {
            return false;
        }
        Long version = getVersion();
        Long version2 = onboardingContextResponse.getVersion();
        return version != null ? version.equals(version2) : version2 == null;
    }

    public String getContextId() {
        return this.contextId;
    }

    public String getContextType() {
        return this.contextType;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public HashMap<String, String> getData() {
        return this.data;
    }

    public String getDataVersion() {
        return this.dataVersion;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public String getState() {
        return this.state;
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        String contextId = getContextId();
        int i = 43;
        int hashCode = contextId == null ? 43 : contextId.hashCode();
        String contextType = getContextType();
        int hashCode2 = ((hashCode + 59) * 59) + (contextType == null ? 43 : contextType.hashCode());
        String dataVersion = getDataVersion();
        int hashCode3 = (hashCode2 * 59) + (dataVersion == null ? 43 : dataVersion.hashCode());
        HashMap<String, String> data = getData();
        int hashCode4 = (hashCode3 * 59) + (data == null ? 43 : data.hashCode());
        String state = getState();
        int hashCode5 = (hashCode4 * 59) + (state == null ? 43 : state.hashCode());
        String createdDate = getCreatedDate();
        int hashCode6 = (hashCode5 * 59) + (createdDate == null ? 43 : createdDate.hashCode());
        String modifiedDate = getModifiedDate();
        int hashCode7 = (hashCode6 * 59) + (modifiedDate == null ? 43 : modifiedDate.hashCode());
        Long version = getVersion();
        int i2 = hashCode7 * 59;
        if (version != null) {
            i = version.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("contextId")
    public void setContextId(String str) {
        this.contextId = str;
    }

    @JsonProperty("contextType")
    public void setContextType(String str) {
        this.contextType = str;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    @JsonProperty("data")
    public void setData(HashMap<String, String> hashMap) {
        this.data = hashMap;
    }

    @JsonProperty("dataVersion")
    public void setDataVersion(String str) {
        this.dataVersion = str;
    }

    @JsonProperty(Message.SERIALIZED_NAME_MODIFIED_DATE)
    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    @JsonProperty("state")
    public void setState(String str) {
        this.state = str;
    }

    @JsonProperty("version")
    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OnboardingContextResponse(contextId=");
        outline107.append(getContextId());
        outline107.append(", contextType=");
        outline107.append(getContextType());
        outline107.append(", dataVersion=");
        outline107.append(getDataVersion());
        outline107.append(", data=");
        outline107.append(getData());
        outline107.append(", state=");
        outline107.append(getState());
        outline107.append(", createdDate=");
        outline107.append(getCreatedDate());
        outline107.append(", modifiedDate=");
        outline107.append(getModifiedDate());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(")");
        return outline107.toString();
    }
}
