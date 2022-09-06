package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class CreateContactBlockRequest extends AccessTokenRequest {
    private final String blockType;
    private final String blockedCustomerId;
    private final String groupId;
    private final String lang;

    /* loaded from: classes11.dex */
    public static class CreateContactBlockRequestBuilder {
        private String blockType;
        private String blockedCustomerId;
        private String groupId;
        private String lang;

        CreateContactBlockRequestBuilder() {
        }

        public CreateContactBlockRequestBuilder blockType(String str) {
            this.blockType = str;
            return this;
        }

        public CreateContactBlockRequestBuilder blockedCustomerId(String str) {
            this.blockedCustomerId = str;
            return this;
        }

        public CreateContactBlockRequest build() {
            return new CreateContactBlockRequest(this.groupId, this.blockedCustomerId, this.blockType, this.lang);
        }

        public CreateContactBlockRequestBuilder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public CreateContactBlockRequestBuilder lang(String str) {
            this.lang = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateContactBlockRequest.CreateContactBlockRequestBuilder(groupId=");
            outline107.append(this.groupId);
            outline107.append(", blockedCustomerId=");
            outline107.append(this.blockedCustomerId);
            outline107.append(", blockType=");
            outline107.append(this.blockType);
            outline107.append(", lang=");
            return GeneratedOutlineSupport1.outline91(outline107, this.lang, ")");
        }
    }

    CreateContactBlockRequest(String str, String str2, String str3, String str4) {
        this.groupId = str;
        this.blockedCustomerId = str2;
        this.blockType = str3;
        this.lang = str4;
    }

    public static CreateContactBlockRequestBuilder builder() {
        return new CreateContactBlockRequestBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CreateContactBlockRequest;
    }

    @Override // com.amazon.clouddrive.extended.model.AccessTokenRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreateContactBlockRequest)) {
            return false;
        }
        CreateContactBlockRequest createContactBlockRequest = (CreateContactBlockRequest) obj;
        if (!createContactBlockRequest.canEqual(this)) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = createContactBlockRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String blockedCustomerId = getBlockedCustomerId();
        String blockedCustomerId2 = createContactBlockRequest.getBlockedCustomerId();
        if (blockedCustomerId != null ? !blockedCustomerId.equals(blockedCustomerId2) : blockedCustomerId2 != null) {
            return false;
        }
        String blockType = getBlockType();
        String blockType2 = createContactBlockRequest.getBlockType();
        if (blockType != null ? !blockType.equals(blockType2) : blockType2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = createContactBlockRequest.getLang();
        return lang != null ? lang.equals(lang2) : lang2 == null;
    }

    public String getBlockType() {
        return this.blockType;
    }

    public String getBlockedCustomerId() {
        return this.blockedCustomerId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLang() {
        return this.lang;
    }

    @Override // com.amazon.clouddrive.extended.model.AccessTokenRequest
    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        String blockedCustomerId = getBlockedCustomerId();
        int hashCode2 = ((hashCode + 59) * 59) + (blockedCustomerId == null ? 43 : blockedCustomerId.hashCode());
        String blockType = getBlockType();
        int hashCode3 = (hashCode2 * 59) + (blockType == null ? 43 : blockType.hashCode());
        String lang = getLang();
        int i2 = hashCode3 * 59;
        if (lang != null) {
            i = lang.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateContactBlockRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", blockedCustomerId=");
        outline107.append(getBlockedCustomerId());
        outline107.append(", blockType=");
        outline107.append(getBlockType());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(")");
        return outline107.toString();
    }
}
