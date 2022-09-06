package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.clouddrive.model.NodeSubKindProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SubKindProperties {
    @JsonProperty("iOSBurstPhoto")
    private IOSBurstPhoto iOSBurstPhoto;
    @JsonProperty(NodeSubKindProperties.SubKindPropertyOwner.SONY_BURST_PHOTO)
    private SonyBurstPhoto sonyBurstPhoto;

    protected boolean canEqual(Object obj) {
        return obj instanceof SubKindProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubKindProperties)) {
            return false;
        }
        SubKindProperties subKindProperties = (SubKindProperties) obj;
        if (!subKindProperties.canEqual(this)) {
            return false;
        }
        IOSBurstPhoto iOSBurstPhoto = getIOSBurstPhoto();
        IOSBurstPhoto iOSBurstPhoto2 = subKindProperties.getIOSBurstPhoto();
        if (iOSBurstPhoto != null ? !iOSBurstPhoto.equals(iOSBurstPhoto2) : iOSBurstPhoto2 != null) {
            return false;
        }
        SonyBurstPhoto sonyBurstPhoto = getSonyBurstPhoto();
        SonyBurstPhoto sonyBurstPhoto2 = subKindProperties.getSonyBurstPhoto();
        return sonyBurstPhoto != null ? sonyBurstPhoto.equals(sonyBurstPhoto2) : sonyBurstPhoto2 == null;
    }

    public IOSBurstPhoto getIOSBurstPhoto() {
        return this.iOSBurstPhoto;
    }

    public SonyBurstPhoto getSonyBurstPhoto() {
        return this.sonyBurstPhoto;
    }

    public int hashCode() {
        IOSBurstPhoto iOSBurstPhoto = getIOSBurstPhoto();
        int i = 43;
        int hashCode = iOSBurstPhoto == null ? 43 : iOSBurstPhoto.hashCode();
        SonyBurstPhoto sonyBurstPhoto = getSonyBurstPhoto();
        int i2 = (hashCode + 59) * 59;
        if (sonyBurstPhoto != null) {
            i = sonyBurstPhoto.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("iOSBurstPhoto")
    public void setIOSBurstPhoto(IOSBurstPhoto iOSBurstPhoto) {
        this.iOSBurstPhoto = iOSBurstPhoto;
    }

    @JsonProperty(NodeSubKindProperties.SubKindPropertyOwner.SONY_BURST_PHOTO)
    public void setSonyBurstPhoto(SonyBurstPhoto sonyBurstPhoto) {
        this.sonyBurstPhoto = sonyBurstPhoto;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SubKindProperties(iOSBurstPhoto=");
        outline107.append(getIOSBurstPhoto());
        outline107.append(", sonyBurstPhoto=");
        outline107.append(getSonyBurstPhoto());
        outline107.append(")");
        return outline107.toString();
    }
}
