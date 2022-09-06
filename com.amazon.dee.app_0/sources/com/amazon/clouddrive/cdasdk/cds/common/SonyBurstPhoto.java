package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.clouddrive.model.NodeSubKindProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SonyBurstPhoto {
    @JsonProperty(NodeSubKindProperties.SubKindPropertyKeys.BURST_ID)
    private String burstId;
    @JsonProperty(NodeSubKindProperties.SubKindPropertyKeys.IS_HERO)
    private Boolean isHero;

    protected boolean canEqual(Object obj) {
        return obj instanceof SonyBurstPhoto;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SonyBurstPhoto)) {
            return false;
        }
        SonyBurstPhoto sonyBurstPhoto = (SonyBurstPhoto) obj;
        if (!sonyBurstPhoto.canEqual(this)) {
            return false;
        }
        String burstId = getBurstId();
        String burstId2 = sonyBurstPhoto.getBurstId();
        if (burstId != null ? !burstId.equals(burstId2) : burstId2 != null) {
            return false;
        }
        Boolean isHero = getIsHero();
        Boolean isHero2 = sonyBurstPhoto.getIsHero();
        return isHero != null ? isHero.equals(isHero2) : isHero2 == null;
    }

    public String getBurstId() {
        return this.burstId;
    }

    public Boolean getIsHero() {
        return this.isHero;
    }

    public int hashCode() {
        String burstId = getBurstId();
        int i = 43;
        int hashCode = burstId == null ? 43 : burstId.hashCode();
        Boolean isHero = getIsHero();
        int i2 = (hashCode + 59) * 59;
        if (isHero != null) {
            i = isHero.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty(NodeSubKindProperties.SubKindPropertyKeys.BURST_ID)
    public void setBurstId(String str) {
        this.burstId = str;
    }

    @JsonProperty(NodeSubKindProperties.SubKindPropertyKeys.IS_HERO)
    public void setIsHero(Boolean bool) {
        this.isHero = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SonyBurstPhoto(burstId=");
        outline107.append(getBurstId());
        outline107.append(", isHero=");
        outline107.append(getIsHero());
        outline107.append(")");
        return outline107.toString();
    }
}
