package com.amazon.clouddrive.cdasdk.cds.node;

import com.amazon.clouddrive.cdasdk.cds.common.DedupeContext;
import com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
/* loaded from: classes11.dex */
public class TrashRequest extends VersionedNodeRequest {
    @JsonProperty("dedupeContext")
    private DedupeContext dedupeContext;
    @JsonProperty("id")
    private String id;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("recurse")
    private Boolean recurse;

    public TrashRequest(@NonNull String str, @NonNull String str2, boolean z) {
        if (str != null) {
            if (str2 != null) {
                this.id = str;
                this.ownerId = str2;
                this.recurse = Boolean.valueOf(z);
                return;
            }
            throw new NullPointerException("ownerId is marked non-null but is null");
        }
        throw new NullPointerException("id is marked non-null but is null");
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof TrashRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TrashRequest)) {
            return false;
        }
        TrashRequest trashRequest = (TrashRequest) obj;
        if (!trashRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String id = getId();
        String id2 = trashRequest.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        Boolean recurse = getRecurse();
        Boolean recurse2 = trashRequest.getRecurse();
        if (recurse != null ? !recurse.equals(recurse2) : recurse2 != null) {
            return false;
        }
        DedupeContext dedupeContext = getDedupeContext();
        DedupeContext dedupeContext2 = trashRequest.getDedupeContext();
        if (dedupeContext != null ? !dedupeContext.equals(dedupeContext2) : dedupeContext2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = trashRequest.getOwnerId();
        return ownerId != null ? ownerId.equals(ownerId2) : ownerId2 == null;
    }

    public DedupeContext getDedupeContext() {
        return this.dedupeContext;
    }

    public String getId() {
        return this.id;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public Boolean getRecurse() {
        return this.recurse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String id = getId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (id == null ? 43 : id.hashCode());
        Boolean recurse = getRecurse();
        int hashCode3 = (hashCode2 * 59) + (recurse == null ? 43 : recurse.hashCode());
        DedupeContext dedupeContext = getDedupeContext();
        int hashCode4 = (hashCode3 * 59) + (dedupeContext == null ? 43 : dedupeContext.hashCode());
        String ownerId = getOwnerId();
        int i2 = hashCode4 * 59;
        if (ownerId != null) {
            i = ownerId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("dedupeContext")
    public void setDedupeContext(DedupeContext dedupeContext) {
        this.dedupeContext = dedupeContext;
    }

    @JsonProperty("id")
    public void setId(String str) {
        this.id = str;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("recurse")
    public void setRecurse(Boolean bool) {
        this.recurse = bool;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TrashRequest(id=");
        outline107.append(getId());
        outline107.append(", recurse=");
        outline107.append(getRecurse());
        outline107.append(", dedupeContext=");
        outline107.append(getDedupeContext());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(")");
        return outline107.toString();
    }
}
