package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class PatchGroupCollectionRequest implements CloudDriveRequest {
    private final String caption;
    private final String collectionId;
    private final String groupId;
    private final String lang;
    private final String name;

    /* loaded from: classes11.dex */
    public static class PatchGroupCollectionRequestBuilder {
        private String caption;
        private String collectionId;
        private String groupId;
        private String lang;
        private String name;

        PatchGroupCollectionRequestBuilder() {
        }

        public PatchGroupCollectionRequest build() {
            return new PatchGroupCollectionRequest(this.name, this.caption, this.collectionId, this.groupId, this.lang);
        }

        public PatchGroupCollectionRequestBuilder caption(String str) {
            this.caption = str;
            return this;
        }

        public PatchGroupCollectionRequestBuilder collectionId(String str) {
            this.collectionId = str;
            return this;
        }

        public PatchGroupCollectionRequestBuilder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public PatchGroupCollectionRequestBuilder lang(String str) {
            this.lang = str;
            return this;
        }

        public PatchGroupCollectionRequestBuilder name(String str) {
            this.name = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PatchGroupCollectionRequest.PatchGroupCollectionRequestBuilder(name=");
            outline107.append(this.name);
            outline107.append(", caption=");
            outline107.append(this.caption);
            outline107.append(", collectionId=");
            outline107.append(this.collectionId);
            outline107.append(", groupId=");
            outline107.append(this.groupId);
            outline107.append(", lang=");
            return GeneratedOutlineSupport1.outline91(outline107, this.lang, ")");
        }
    }

    public PatchGroupCollectionRequest(String str, String str2, String str3, String str4, String str5) {
        this.name = str;
        this.caption = str2;
        this.collectionId = str3;
        this.groupId = str4;
        this.lang = str5;
    }

    public static PatchGroupCollectionRequestBuilder builder() {
        return new PatchGroupCollectionRequestBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PatchGroupCollectionRequest)) {
            return false;
        }
        PatchGroupCollectionRequest patchGroupCollectionRequest = (PatchGroupCollectionRequest) obj;
        String name = getName();
        String name2 = patchGroupCollectionRequest.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String caption = getCaption();
        String caption2 = patchGroupCollectionRequest.getCaption();
        if (caption != null ? !caption.equals(caption2) : caption2 != null) {
            return false;
        }
        String collectionId = getCollectionId();
        String collectionId2 = patchGroupCollectionRequest.getCollectionId();
        if (collectionId != null ? !collectionId.equals(collectionId2) : collectionId2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = patchGroupCollectionRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = patchGroupCollectionRequest.getLang();
        return lang != null ? lang.equals(lang2) : lang2 == null;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getCollectionId() {
        return this.collectionId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLang() {
        return this.lang;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        String name = getName();
        int i = 43;
        int hashCode = name == null ? 43 : name.hashCode();
        String caption = getCaption();
        int hashCode2 = ((hashCode + 59) * 59) + (caption == null ? 43 : caption.hashCode());
        String collectionId = getCollectionId();
        int hashCode3 = (hashCode2 * 59) + (collectionId == null ? 43 : collectionId.hashCode());
        String groupId = getGroupId();
        int hashCode4 = (hashCode3 * 59) + (groupId == null ? 43 : groupId.hashCode());
        String lang = getLang();
        int i2 = hashCode4 * 59;
        if (lang != null) {
            i = lang.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PatchGroupCollectionRequest(name=");
        outline107.append(getName());
        outline107.append(", caption=");
        outline107.append(getCaption());
        outline107.append(", collectionId=");
        outline107.append(getCollectionId());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest instanceof PatchGroupCollectionRequest) {
            return cloudDriveRequest.hashCode() - hashCode();
        }
        return -1;
    }
}
