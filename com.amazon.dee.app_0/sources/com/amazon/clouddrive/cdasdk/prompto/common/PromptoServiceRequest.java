package com.amazon.clouddrive.cdasdk.prompto.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PromptoServiceRequest {
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("resourceVersion")
    private ResourceVersion resourceVersion;

    protected boolean canEqual(Object obj) {
        return obj instanceof PromptoServiceRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PromptoServiceRequest)) {
            return false;
        }
        PromptoServiceRequest promptoServiceRequest = (PromptoServiceRequest) obj;
        if (!promptoServiceRequest.canEqual(this)) {
            return false;
        }
        ResourceVersion resourceVersion = getResourceVersion();
        ResourceVersion resourceVersion2 = promptoServiceRequest.getResourceVersion();
        if (resourceVersion != null ? !resourceVersion.equals(resourceVersion2) : resourceVersion2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = promptoServiceRequest.getLang();
        if (lang != null ? !lang.equals(lang2) : lang2 != null) {
            return false;
        }
        String applicationId = getApplicationId();
        String applicationId2 = promptoServiceRequest.getApplicationId();
        return applicationId != null ? applicationId.equals(applicationId2) : applicationId2 == null;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getLang() {
        return this.lang;
    }

    public ResourceVersion getResourceVersion() {
        return this.resourceVersion;
    }

    public int hashCode() {
        ResourceVersion resourceVersion = getResourceVersion();
        int i = 43;
        int hashCode = resourceVersion == null ? 43 : resourceVersion.hashCode();
        String lang = getLang();
        int hashCode2 = ((hashCode + 59) * 59) + (lang == null ? 43 : lang.hashCode());
        String applicationId = getApplicationId();
        int i2 = hashCode2 * 59;
        if (applicationId != null) {
            i = applicationId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String str) {
        this.applicationId = str;
    }

    @JsonProperty("lang")
    public void setLang(String str) {
        this.lang = str;
    }

    @JsonProperty("resourceVersion")
    public void setResourceVersion(ResourceVersion resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PromptoServiceRequest(resourceVersion=");
        outline107.append(getResourceVersion());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(", applicationId=");
        outline107.append(getApplicationId());
        outline107.append(")");
        return outline107.toString();
    }
}
