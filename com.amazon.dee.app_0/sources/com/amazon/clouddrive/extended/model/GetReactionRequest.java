package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class GetReactionRequest implements CloudDriveRequest {
    private final String groupId;
    private final String reactionId;

    /* loaded from: classes11.dex */
    public static class GetReactionRequestBuilder {
        private String groupId;
        private String reactionId;

        GetReactionRequestBuilder() {
        }

        public GetReactionRequest build() {
            return new GetReactionRequest(this.groupId, this.reactionId);
        }

        public GetReactionRequestBuilder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public GetReactionRequestBuilder reactionId(String str) {
            this.reactionId = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetReactionRequest.GetReactionRequestBuilder(groupId=");
            outline107.append(this.groupId);
            outline107.append(", reactionId=");
            return GeneratedOutlineSupport1.outline91(outline107, this.reactionId, ")");
        }
    }

    GetReactionRequest(String str, String str2) {
        this.groupId = str;
        this.reactionId = str2;
    }

    public static GetReactionRequestBuilder builder() {
        return new GetReactionRequestBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof GetReactionRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetReactionRequest)) {
            return false;
        }
        GetReactionRequest getReactionRequest = (GetReactionRequest) obj;
        if (!getReactionRequest.canEqual(this)) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = getReactionRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String reactionId = getReactionId();
        String reactionId2 = getReactionRequest.getReactionId();
        return reactionId != null ? reactionId.equals(reactionId2) : reactionId2 == null;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getReactionId() {
        return this.reactionId;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        String reactionId = getReactionId();
        int i2 = (hashCode + 59) * 59;
        if (reactionId != null) {
            i = reactionId.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetReactionRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", reactionId=");
        outline107.append(getReactionId());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this || !(cloudDriveRequest instanceof GetReactionRequest)) {
            return 0;
        }
        GetReactionRequest getReactionRequest = (GetReactionRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), getReactionRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getReactionId(), getReactionRequest.getReactionId());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
