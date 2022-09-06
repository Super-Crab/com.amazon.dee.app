package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class GroupCollection {
    private String caption;
    private String collectionId;
    private ContentAggregations contentAggregations;
    private String dateAdded;
    private List<String> eventIds;
    private String groupId;
    private String kind;
    private String name;
    private String ownerId;

    protected boolean canEqual(Object obj) {
        return obj instanceof GroupCollection;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupCollection)) {
            return false;
        }
        GroupCollection groupCollection = (GroupCollection) obj;
        if (!groupCollection.canEqual(this)) {
            return false;
        }
        String collectionId = getCollectionId();
        String collectionId2 = groupCollection.getCollectionId();
        if (collectionId != null ? !collectionId.equals(collectionId2) : collectionId2 != null) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = groupCollection.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = groupCollection.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = groupCollection.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        String caption = getCaption();
        String caption2 = groupCollection.getCaption();
        if (caption != null ? !caption.equals(caption2) : caption2 != null) {
            return false;
        }
        String name = getName();
        String name2 = groupCollection.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String dateAdded = getDateAdded();
        String dateAdded2 = groupCollection.getDateAdded();
        if (dateAdded != null ? !dateAdded.equals(dateAdded2) : dateAdded2 != null) {
            return false;
        }
        ContentAggregations contentAggregations = getContentAggregations();
        ContentAggregations contentAggregations2 = groupCollection.getContentAggregations();
        if (contentAggregations != null ? !contentAggregations.equals(contentAggregations2) : contentAggregations2 != null) {
            return false;
        }
        List<String> eventIds = getEventIds();
        List<String> eventIds2 = groupCollection.getEventIds();
        return eventIds != null ? eventIds.equals(eventIds2) : eventIds2 == null;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getCollectionId() {
        return this.collectionId;
    }

    public ContentAggregations getContentAggregations() {
        return this.contentAggregations;
    }

    public String getDateAdded() {
        return this.dateAdded;
    }

    public List<String> getEventIds() {
        return this.eventIds;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public int hashCode() {
        String collectionId = getCollectionId();
        int i = 43;
        int hashCode = collectionId == null ? 43 : collectionId.hashCode();
        String groupId = getGroupId();
        int hashCode2 = ((hashCode + 59) * 59) + (groupId == null ? 43 : groupId.hashCode());
        String ownerId = getOwnerId();
        int hashCode3 = (hashCode2 * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        String kind = getKind();
        int hashCode4 = (hashCode3 * 59) + (kind == null ? 43 : kind.hashCode());
        String caption = getCaption();
        int hashCode5 = (hashCode4 * 59) + (caption == null ? 43 : caption.hashCode());
        String name = getName();
        int hashCode6 = (hashCode5 * 59) + (name == null ? 43 : name.hashCode());
        String dateAdded = getDateAdded();
        int hashCode7 = (hashCode6 * 59) + (dateAdded == null ? 43 : dateAdded.hashCode());
        ContentAggregations contentAggregations = getContentAggregations();
        int hashCode8 = (hashCode7 * 59) + (contentAggregations == null ? 43 : contentAggregations.hashCode());
        List<String> eventIds = getEventIds();
        int i2 = hashCode8 * 59;
        if (eventIds != null) {
            i = eventIds.hashCode();
        }
        return i2 + i;
    }

    public void setCaption(String str) {
        this.caption = str;
    }

    public void setCollectionId(String str) {
        this.collectionId = str;
    }

    public void setContentAggregations(ContentAggregations contentAggregations) {
        this.contentAggregations = contentAggregations;
    }

    public void setDateAdded(String str) {
        this.dateAdded = str;
    }

    public void setEventIds(List<String> list) {
        this.eventIds = list;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setKind(String str) {
        this.kind = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupCollection(collectionId=");
        outline107.append(getCollectionId());
        outline107.append(", groupId=");
        outline107.append(getGroupId());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", caption=");
        outline107.append(getCaption());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", dateAdded=");
        outline107.append(getDateAdded());
        outline107.append(", contentAggregations=");
        outline107.append(getContentAggregations());
        outline107.append(", eventIds=");
        outline107.append(getEventIds());
        outline107.append(")");
        return outline107.toString();
    }
}
