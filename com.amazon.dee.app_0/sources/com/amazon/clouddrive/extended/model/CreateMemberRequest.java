package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class CreateMemberRequest implements CloudDriveRequest {
    private final String groupId;
    private final String inviteTag;
    private final String inviteToken;
    private final String shareToken;

    /* loaded from: classes11.dex */
    public static class CreateMemberRequestBuilder {
        private String groupId;
        private String inviteTag;
        private String inviteToken;
        private String shareToken;

        CreateMemberRequestBuilder() {
        }

        public CreateMemberRequest build() {
            return new CreateMemberRequest(this.groupId, this.shareToken, this.inviteToken, this.inviteTag);
        }

        public CreateMemberRequestBuilder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public CreateMemberRequestBuilder inviteTag(String str) {
            this.inviteTag = str;
            return this;
        }

        public CreateMemberRequestBuilder inviteToken(String str) {
            this.inviteToken = str;
            return this;
        }

        public CreateMemberRequestBuilder shareToken(String str) {
            this.shareToken = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateMemberRequest.CreateMemberRequestBuilder(groupId=");
            outline107.append(this.groupId);
            outline107.append(", shareToken=");
            outline107.append(this.shareToken);
            outline107.append(", inviteToken=");
            outline107.append(this.inviteToken);
            outline107.append(", inviteTag=");
            return GeneratedOutlineSupport1.outline91(outline107, this.inviteTag, ")");
        }
    }

    CreateMemberRequest(String str, String str2, String str3, String str4) {
        this.groupId = str;
        this.shareToken = str2;
        this.inviteToken = str3;
        this.inviteTag = str4;
    }

    public static CreateMemberRequestBuilder builder() {
        return new CreateMemberRequestBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreateMemberRequest)) {
            return false;
        }
        CreateMemberRequest createMemberRequest = (CreateMemberRequest) obj;
        String groupId = getGroupId();
        String groupId2 = createMemberRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String shareToken = getShareToken();
        String shareToken2 = createMemberRequest.getShareToken();
        if (shareToken != null ? !shareToken.equals(shareToken2) : shareToken2 != null) {
            return false;
        }
        String inviteToken = getInviteToken();
        String inviteToken2 = createMemberRequest.getInviteToken();
        if (inviteToken != null ? !inviteToken.equals(inviteToken2) : inviteToken2 != null) {
            return false;
        }
        String inviteTag = getInviteTag();
        String inviteTag2 = createMemberRequest.getInviteTag();
        return inviteTag != null ? inviteTag.equals(inviteTag2) : inviteTag2 == null;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getInviteTag() {
        return this.inviteTag;
    }

    public String getInviteToken() {
        return this.inviteToken;
    }

    public String getShareToken() {
        return this.shareToken;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        String shareToken = getShareToken();
        int hashCode2 = ((hashCode + 59) * 59) + (shareToken == null ? 43 : shareToken.hashCode());
        String inviteToken = getInviteToken();
        int hashCode3 = (hashCode2 * 59) + (inviteToken == null ? 43 : inviteToken.hashCode());
        String inviteTag = getInviteTag();
        int i2 = hashCode3 * 59;
        if (inviteTag != null) {
            i = inviteTag.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateMemberRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", shareToken=");
        outline107.append(getShareToken());
        outline107.append(", inviteToken=");
        outline107.append(getInviteToken());
        outline107.append(", inviteTag=");
        outline107.append(getInviteTag());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof CreateMemberRequest)) {
            return CreateMemberRequest.class.getName().compareTo(cloudDriveRequest.getClass().getName());
        }
        CreateMemberRequest createMemberRequest = (CreateMemberRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), createMemberRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getShareToken(), createMemberRequest.getShareToken());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getInviteToken(), createMemberRequest.getInviteToken());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getInviteTag(), createMemberRequest.getInviteTag());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
