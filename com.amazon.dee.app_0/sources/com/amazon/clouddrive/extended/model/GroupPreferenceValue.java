package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class GroupPreferenceValue {
    private final String description;
    private final String id;
    private final String title;

    /* loaded from: classes11.dex */
    public static class GroupPreferenceValueBuilder {
        private String description;
        private String id;
        private String title;

        GroupPreferenceValueBuilder() {
        }

        public GroupPreferenceValue build() {
            return new GroupPreferenceValue(this.description, this.id, this.title);
        }

        public GroupPreferenceValueBuilder description(String str) {
            this.description = str;
            return this;
        }

        public GroupPreferenceValueBuilder id(String str) {
            this.id = str;
            return this;
        }

        public GroupPreferenceValueBuilder title(String str) {
            this.title = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreferenceValue.GroupPreferenceValueBuilder(description=");
            outline107.append(this.description);
            outline107.append(", id=");
            outline107.append(this.id);
            outline107.append(", title=");
            return GeneratedOutlineSupport1.outline91(outline107, this.title, ")");
        }
    }

    GroupPreferenceValue(String str, String str2, String str3) {
        this.description = str;
        this.id = str2;
        this.title = str3;
    }

    public static GroupPreferenceValueBuilder builder() {
        return new GroupPreferenceValueBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupPreferenceValue)) {
            return false;
        }
        GroupPreferenceValue groupPreferenceValue = (GroupPreferenceValue) obj;
        String description = getDescription();
        String description2 = groupPreferenceValue.getDescription();
        if (description != null ? !description.equals(description2) : description2 != null) {
            return false;
        }
        String id = getId();
        String id2 = groupPreferenceValue.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String title = getTitle();
        String title2 = groupPreferenceValue.getTitle();
        return title != null ? title.equals(title2) : title2 == null;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String description = getDescription();
        int i = 43;
        int hashCode = description == null ? 43 : description.hashCode();
        String id = getId();
        int hashCode2 = ((hashCode + 59) * 59) + (id == null ? 43 : id.hashCode());
        String title = getTitle();
        int i2 = hashCode2 * 59;
        if (title != null) {
            i = title.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreferenceValue(description=");
        outline107.append(getDescription());
        outline107.append(", id=");
        outline107.append(getId());
        outline107.append(", title=");
        outline107.append(getTitle());
        outline107.append(")");
        return outline107.toString();
    }
}
