package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class EventParent {
    private String id;
    private String kind;
    private GroupCollection parentCollection;
    private Reaction parentReaction;

    protected boolean canEqual(Object obj) {
        return obj instanceof EventParent;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventParent)) {
            return false;
        }
        EventParent eventParent = (EventParent) obj;
        if (!eventParent.canEqual(this)) {
            return false;
        }
        String id = getId();
        String id2 = eventParent.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = eventParent.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        Reaction parentReaction = getParentReaction();
        Reaction parentReaction2 = eventParent.getParentReaction();
        if (parentReaction != null ? !parentReaction.equals(parentReaction2) : parentReaction2 != null) {
            return false;
        }
        GroupCollection parentCollection = getParentCollection();
        GroupCollection parentCollection2 = eventParent.getParentCollection();
        return parentCollection != null ? parentCollection.equals(parentCollection2) : parentCollection2 == null;
    }

    public String getId() {
        return this.id;
    }

    public String getKind() {
        return this.kind;
    }

    public GroupCollection getParentCollection() {
        return this.parentCollection;
    }

    public Reaction getParentReaction() {
        return this.parentReaction;
    }

    public int hashCode() {
        String id = getId();
        int i = 43;
        int hashCode = id == null ? 43 : id.hashCode();
        String kind = getKind();
        int hashCode2 = ((hashCode + 59) * 59) + (kind == null ? 43 : kind.hashCode());
        Reaction parentReaction = getParentReaction();
        int hashCode3 = (hashCode2 * 59) + (parentReaction == null ? 43 : parentReaction.hashCode());
        GroupCollection parentCollection = getParentCollection();
        int i2 = hashCode3 * 59;
        if (parentCollection != null) {
            i = parentCollection.hashCode();
        }
        return i2 + i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setKind(String str) {
        this.kind = str;
    }

    public void setParentCollection(GroupCollection groupCollection) {
        this.parentCollection = groupCollection;
    }

    public void setParentReaction(Reaction reaction) {
        this.parentReaction = reaction;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EventParent(id=");
        outline107.append(getId());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", parentReaction=");
        outline107.append(getParentReaction());
        outline107.append(", parentCollection=");
        outline107.append(getParentCollection());
        outline107.append(")");
        return outline107.toString();
    }
}
