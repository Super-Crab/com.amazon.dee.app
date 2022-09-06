package com.amazon.clouddrive.cdasdk.aps.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class APSInput {
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("devicePlatform")
    private DevicePlatform devicePlatform;
    @JsonProperty("featureFlags")
    private String featureFlags;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("resourceVersion")
    private ResourceVersion resourceVersion;

    protected boolean canEqual(Object obj) {
        return obj instanceof APSInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof APSInput)) {
            return false;
        }
        APSInput aPSInput = (APSInput) obj;
        if (!aPSInput.canEqual(this)) {
            return false;
        }
        String customerId = getCustomerId();
        String customerId2 = aPSInput.getCustomerId();
        if (customerId != null ? !customerId.equals(customerId2) : customerId2 != null) {
            return false;
        }
        ResourceVersion resourceVersion = getResourceVersion();
        ResourceVersion resourceVersion2 = aPSInput.getResourceVersion();
        if (resourceVersion != null ? !resourceVersion.equals(resourceVersion2) : resourceVersion2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = aPSInput.getLang();
        if (lang != null ? !lang.equals(lang2) : lang2 != null) {
            return false;
        }
        DevicePlatform devicePlatform = getDevicePlatform();
        DevicePlatform devicePlatform2 = aPSInput.getDevicePlatform();
        if (devicePlatform != null ? !devicePlatform.equals(devicePlatform2) : devicePlatform2 != null) {
            return false;
        }
        String featureFlags = getFeatureFlags();
        String featureFlags2 = aPSInput.getFeatureFlags();
        return featureFlags != null ? featureFlags.equals(featureFlags2) : featureFlags2 == null;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public DevicePlatform getDevicePlatform() {
        return this.devicePlatform;
    }

    public String getFeatureFlags() {
        return this.featureFlags;
    }

    public String getLang() {
        return this.lang;
    }

    public ResourceVersion getResourceVersion() {
        return this.resourceVersion;
    }

    public int hashCode() {
        String customerId = getCustomerId();
        int i = 43;
        int hashCode = customerId == null ? 43 : customerId.hashCode();
        ResourceVersion resourceVersion = getResourceVersion();
        int hashCode2 = ((hashCode + 59) * 59) + (resourceVersion == null ? 43 : resourceVersion.hashCode());
        String lang = getLang();
        int hashCode3 = (hashCode2 * 59) + (lang == null ? 43 : lang.hashCode());
        DevicePlatform devicePlatform = getDevicePlatform();
        int hashCode4 = (hashCode3 * 59) + (devicePlatform == null ? 43 : devicePlatform.hashCode());
        String featureFlags = getFeatureFlags();
        int i2 = hashCode4 * 59;
        if (featureFlags != null) {
            i = featureFlags.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String str) {
        this.customerId = str;
    }

    @JsonProperty("devicePlatform")
    public void setDevicePlatform(DevicePlatform devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    @JsonProperty("featureFlags")
    public void setFeatureFlags(String str) {
        this.featureFlags = str;
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("APSInput(customerId=");
        outline107.append(getCustomerId());
        outline107.append(", resourceVersion=");
        outline107.append(getResourceVersion());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(", devicePlatform=");
        outline107.append(getDevicePlatform());
        outline107.append(", featureFlags=");
        outline107.append(getFeatureFlags());
        outline107.append(")");
        return outline107.toString();
    }
}
