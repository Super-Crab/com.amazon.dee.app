package com.amazon.clouddrive.cdasdk.prompto.common;

import com.amazon.alexa.sharing.api.models.Message;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PromptoServiceResponse {
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty(Message.SERIALIZED_NAME_MODIFIED_DATE)
    private String modifiedDate;
    @JsonProperty("version")
    private Long version;

    protected boolean canEqual(Object obj) {
        return obj instanceof PromptoServiceResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PromptoServiceResponse)) {
            return false;
        }
        PromptoServiceResponse promptoServiceResponse = (PromptoServiceResponse) obj;
        if (!promptoServiceResponse.canEqual(this)) {
            return false;
        }
        Long version = getVersion();
        Long version2 = promptoServiceResponse.getVersion();
        if (version != null ? !version.equals(version2) : version2 != null) {
            return false;
        }
        String modifiedDate = getModifiedDate();
        String modifiedDate2 = promptoServiceResponse.getModifiedDate();
        if (modifiedDate != null ? !modifiedDate.equals(modifiedDate2) : modifiedDate2 != null) {
            return false;
        }
        String createdDate = getCreatedDate();
        String createdDate2 = promptoServiceResponse.getCreatedDate();
        return createdDate != null ? createdDate.equals(createdDate2) : createdDate2 == null;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        Long version = getVersion();
        int i = 43;
        int hashCode = version == null ? 43 : version.hashCode();
        String modifiedDate = getModifiedDate();
        int hashCode2 = ((hashCode + 59) * 59) + (modifiedDate == null ? 43 : modifiedDate.hashCode());
        String createdDate = getCreatedDate();
        int i2 = hashCode2 * 59;
        if (createdDate != null) {
            i = createdDate.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    @JsonProperty(Message.SERIALIZED_NAME_MODIFIED_DATE)
    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    @JsonProperty("version")
    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PromptoServiceResponse(version=");
        outline107.append(getVersion());
        outline107.append(", modifiedDate=");
        outline107.append(getModifiedDate());
        outline107.append(", createdDate=");
        outline107.append(getCreatedDate());
        outline107.append(")");
        return outline107.toString();
    }
}
