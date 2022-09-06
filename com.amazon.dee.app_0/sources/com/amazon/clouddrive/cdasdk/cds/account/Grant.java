package com.amazon.clouddrive.cdasdk.cds.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class Grant {
    @JsonProperty("grantId")
    private String grantId;
    @JsonProperty("grantStorage")
    private Long grantStorage;
    @JsonProperty("isStackable")
    private Boolean isStackable;

    protected boolean canEqual(Object obj) {
        return obj instanceof Grant;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Grant)) {
            return false;
        }
        Grant grant = (Grant) obj;
        if (!grant.canEqual(this)) {
            return false;
        }
        String grantId = getGrantId();
        String grantId2 = grant.getGrantId();
        if (grantId != null ? !grantId.equals(grantId2) : grantId2 != null) {
            return false;
        }
        Long grantStorage = getGrantStorage();
        Long grantStorage2 = grant.getGrantStorage();
        if (grantStorage != null ? !grantStorage.equals(grantStorage2) : grantStorage2 != null) {
            return false;
        }
        Boolean isStackable = getIsStackable();
        Boolean isStackable2 = grant.getIsStackable();
        return isStackable != null ? isStackable.equals(isStackable2) : isStackable2 == null;
    }

    public String getGrantId() {
        return this.grantId;
    }

    public Long getGrantStorage() {
        return this.grantStorage;
    }

    public Boolean getIsStackable() {
        return this.isStackable;
    }

    public int hashCode() {
        String grantId = getGrantId();
        int i = 43;
        int hashCode = grantId == null ? 43 : grantId.hashCode();
        Long grantStorage = getGrantStorage();
        int hashCode2 = ((hashCode + 59) * 59) + (grantStorage == null ? 43 : grantStorage.hashCode());
        Boolean isStackable = getIsStackable();
        int i2 = hashCode2 * 59;
        if (isStackable != null) {
            i = isStackable.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("grantId")
    public void setGrantId(String str) {
        this.grantId = str;
    }

    @JsonProperty("grantStorage")
    public void setGrantStorage(Long l) {
        this.grantStorage = l;
    }

    @JsonProperty("isStackable")
    public void setIsStackable(Boolean bool) {
        this.isStackable = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Grant(grantId=");
        outline107.append(getGrantId());
        outline107.append(", grantStorage=");
        outline107.append(getGrantStorage());
        outline107.append(", isStackable=");
        outline107.append(getIsStackable());
        outline107.append(")");
        return outline107.toString();
    }
}
