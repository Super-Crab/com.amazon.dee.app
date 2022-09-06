package com.amazon.clouddrive.cdasdk.cdrs;

import com.amazon.clouddrive.cdasdk.prompto.common.ResourceVersion;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
class CDRSServiceRequest {
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("resourceVersion")
    private ResourceVersion resourceVersion;

    protected boolean canEqual(Object obj) {
        return obj instanceof CDRSServiceRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDRSServiceRequest)) {
            return false;
        }
        CDRSServiceRequest cDRSServiceRequest = (CDRSServiceRequest) obj;
        if (!cDRSServiceRequest.canEqual(this)) {
            return false;
        }
        ResourceVersion resourceVersion = getResourceVersion();
        ResourceVersion resourceVersion2 = cDRSServiceRequest.getResourceVersion();
        if (resourceVersion != null ? !resourceVersion.equals(resourceVersion2) : resourceVersion2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = cDRSServiceRequest.getLang();
        if (lang != null ? !lang.equals(lang2) : lang2 != null) {
            return false;
        }
        String applicationId = getApplicationId();
        String applicationId2 = cDRSServiceRequest.getApplicationId();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDRSServiceRequest(resourceVersion=");
        outline107.append(getResourceVersion());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(", applicationId=");
        return GeneratedOutlineSupport1.outline91(outline107, getApplicationId(), ")");
    }
}
