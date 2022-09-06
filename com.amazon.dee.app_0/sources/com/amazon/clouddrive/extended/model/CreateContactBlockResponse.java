package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class CreateContactBlockResponse {
    private final String blockType;
    private final String customerId;
    private final String name;

    /* loaded from: classes11.dex */
    public static class CreateContactBlockResponseBuilder {
        private String blockType;
        private String customerId;
        private String name;

        CreateContactBlockResponseBuilder() {
        }

        public CreateContactBlockResponseBuilder blockType(String str) {
            this.blockType = str;
            return this;
        }

        public CreateContactBlockResponse build() {
            return new CreateContactBlockResponse(this.customerId, this.blockType, this.name);
        }

        public CreateContactBlockResponseBuilder customerId(String str) {
            this.customerId = str;
            return this;
        }

        public CreateContactBlockResponseBuilder name(String str) {
            this.name = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateContactBlockResponse.CreateContactBlockResponseBuilder(customerId=");
            outline107.append(this.customerId);
            outline107.append(", blockType=");
            outline107.append(this.blockType);
            outline107.append(", name=");
            return GeneratedOutlineSupport1.outline91(outline107, this.name, ")");
        }
    }

    CreateContactBlockResponse(String str, String str2, String str3) {
        this.customerId = str;
        this.blockType = str2;
        this.name = str3;
    }

    public static CreateContactBlockResponseBuilder builder() {
        return new CreateContactBlockResponseBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreateContactBlockResponse)) {
            return false;
        }
        CreateContactBlockResponse createContactBlockResponse = (CreateContactBlockResponse) obj;
        String customerId = getCustomerId();
        String customerId2 = createContactBlockResponse.getCustomerId();
        if (customerId != null ? !customerId.equals(customerId2) : customerId2 != null) {
            return false;
        }
        String blockType = getBlockType();
        String blockType2 = createContactBlockResponse.getBlockType();
        if (blockType != null ? !blockType.equals(blockType2) : blockType2 != null) {
            return false;
        }
        String name = getName();
        String name2 = createContactBlockResponse.getName();
        return name != null ? name.equals(name2) : name2 == null;
    }

    public String getBlockType() {
        return this.blockType;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        String customerId = getCustomerId();
        int i = 43;
        int hashCode = customerId == null ? 43 : customerId.hashCode();
        String blockType = getBlockType();
        int hashCode2 = ((hashCode + 59) * 59) + (blockType == null ? 43 : blockType.hashCode());
        String name = getName();
        int i2 = hashCode2 * 59;
        if (name != null) {
            i = name.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateContactBlockResponse(customerId=");
        outline107.append(getCustomerId());
        outline107.append(", blockType=");
        outline107.append(getBlockType());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(")");
        return outline107.toString();
    }
}
