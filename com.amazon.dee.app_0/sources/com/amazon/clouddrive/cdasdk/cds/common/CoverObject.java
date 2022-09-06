package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class CoverObject {
    @JsonProperty("id")
    private String id;
    @JsonProperty("isDefault")
    private Boolean isDefault;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("tempLink")
    private String tempLink;

    protected boolean canEqual(Object obj) {
        return obj instanceof CoverObject;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CoverObject)) {
            return false;
        }
        CoverObject coverObject = (CoverObject) obj;
        if (!coverObject.canEqual(this)) {
            return false;
        }
        String id = getId();
        String id2 = coverObject.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String tempLink = getTempLink();
        String tempLink2 = coverObject.getTempLink();
        if (tempLink != null ? !tempLink.equals(tempLink2) : tempLink2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = coverObject.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        Boolean isDefault = getIsDefault();
        Boolean isDefault2 = coverObject.getIsDefault();
        return isDefault != null ? isDefault.equals(isDefault2) : isDefault2 == null;
    }

    public String getId() {
        return this.id;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getTempLink() {
        return this.tempLink;
    }

    public int hashCode() {
        String id = getId();
        int i = 43;
        int hashCode = id == null ? 43 : id.hashCode();
        String tempLink = getTempLink();
        int hashCode2 = ((hashCode + 59) * 59) + (tempLink == null ? 43 : tempLink.hashCode());
        String ownerId = getOwnerId();
        int hashCode3 = (hashCode2 * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        Boolean isDefault = getIsDefault();
        int i2 = hashCode3 * 59;
        if (isDefault != null) {
            i = isDefault.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("id")
    public void setId(String str) {
        this.id = str;
    }

    @JsonProperty("isDefault")
    public void setIsDefault(Boolean bool) {
        this.isDefault = bool;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("tempLink")
    public void setTempLink(String str) {
        this.tempLink = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CoverObject(id=");
        outline107.append(getId());
        outline107.append(", tempLink=");
        outline107.append(getTempLink());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", isDefault=");
        outline107.append(getIsDefault());
        outline107.append(")");
        return outline107.toString();
    }
}
