package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes11.dex */
public final class Contact implements Comparable<Contact> {
    private final GroupCoverPhoto coverPhoto;
    private final String description;
    private final String id;
    private final String kind;
    private final String name;
    private final Map<String, String> tags;

    /* loaded from: classes11.dex */
    public static class ContactBuilder {
        private GroupCoverPhoto coverPhoto;
        private String description;
        private String id;
        private String kind;
        private String name;
        private Map<String, String> tags;

        ContactBuilder() {
        }

        public Contact build() {
            return new Contact(this.id, this.kind, this.name, this.description, this.coverPhoto, this.tags);
        }

        public ContactBuilder coverPhoto(GroupCoverPhoto groupCoverPhoto) {
            this.coverPhoto = groupCoverPhoto;
            return this;
        }

        public ContactBuilder description(String str) {
            this.description = str;
            return this;
        }

        public ContactBuilder id(String str) {
            this.id = str;
            return this;
        }

        public ContactBuilder kind(String str) {
            this.kind = str;
            return this;
        }

        public ContactBuilder name(String str) {
            this.name = str;
            return this;
        }

        public ContactBuilder tags(Map<String, String> map) {
            this.tags = map;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Contact.ContactBuilder(id=");
            outline107.append(this.id);
            outline107.append(", kind=");
            outline107.append(this.kind);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", description=");
            outline107.append(this.description);
            outline107.append(", coverPhoto=");
            outline107.append(this.coverPhoto);
            outline107.append(", tags=");
            outline107.append(this.tags);
            outline107.append(")");
            return outline107.toString();
        }
    }

    Contact(String str, String str2, String str3, String str4, GroupCoverPhoto groupCoverPhoto, Map<String, String> map) {
        this.id = str;
        this.kind = str2;
        this.name = str3;
        this.description = str4;
        this.coverPhoto = groupCoverPhoto;
        this.tags = map;
    }

    public static ContactBuilder builder() {
        return new ContactBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) obj;
        String id = getId();
        String id2 = contact.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = contact.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        String name = getName();
        String name2 = contact.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String description = getDescription();
        String description2 = contact.getDescription();
        if (description != null ? !description.equals(description2) : description2 != null) {
            return false;
        }
        GroupCoverPhoto coverPhoto = getCoverPhoto();
        GroupCoverPhoto coverPhoto2 = contact.getCoverPhoto();
        if (coverPhoto != null ? !coverPhoto.equals(coverPhoto2) : coverPhoto2 != null) {
            return false;
        }
        Map<String, String> tags = getTags();
        Map<String, String> tags2 = contact.getTags();
        return tags != null ? tags.equals(tags2) : tags2 == null;
    }

    public GroupCoverPhoto getCoverPhoto() {
        return this.coverPhoto;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public String getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public int hashCode() {
        String id = getId();
        int i = 43;
        int hashCode = id == null ? 43 : id.hashCode();
        String kind = getKind();
        int hashCode2 = ((hashCode + 59) * 59) + (kind == null ? 43 : kind.hashCode());
        String name = getName();
        int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
        String description = getDescription();
        int hashCode4 = (hashCode3 * 59) + (description == null ? 43 : description.hashCode());
        GroupCoverPhoto coverPhoto = getCoverPhoto();
        int hashCode5 = (hashCode4 * 59) + (coverPhoto == null ? 43 : coverPhoto.hashCode());
        Map<String, String> tags = getTags();
        int i2 = hashCode5 * 59;
        if (tags != null) {
            i = tags.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Contact(id=");
        outline107.append(getId());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", description=");
        outline107.append(getDescription());
        outline107.append(", coverPhoto=");
        outline107.append(getCoverPhoto());
        outline107.append(", tags=");
        outline107.append(getTags());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Contact contact) {
        if (contact == null) {
            return -1;
        }
        if (contact == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getId(), contact.getId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getKind(), contact.getKind());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getName(), contact.getName());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getDescription(), contact.getDescription());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getCoverPhoto(), contact.getCoverPhoto());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getTags(), contact.getTags());
        if (compare6 == 0) {
            return 0;
        }
        return compare6;
    }
}
