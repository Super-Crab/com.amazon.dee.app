package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class GroupsData {
    private ReactionSummaries reactionSummaries;

    protected boolean canEqual(Object obj) {
        return obj instanceof GroupsData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupsData)) {
            return false;
        }
        GroupsData groupsData = (GroupsData) obj;
        if (!groupsData.canEqual(this)) {
            return false;
        }
        ReactionSummaries reactionSummaries = getReactionSummaries();
        ReactionSummaries reactionSummaries2 = groupsData.getReactionSummaries();
        return reactionSummaries != null ? reactionSummaries.equals(reactionSummaries2) : reactionSummaries2 == null;
    }

    public ReactionSummaries getReactionSummaries() {
        return this.reactionSummaries;
    }

    public int hashCode() {
        ReactionSummaries reactionSummaries = getReactionSummaries();
        return 59 + (reactionSummaries == null ? 43 : reactionSummaries.hashCode());
    }

    public void setReactionSummaries(ReactionSummaries reactionSummaries) {
        this.reactionSummaries = reactionSummaries;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupsData(reactionSummaries=");
        outline107.append(getReactionSummaries());
        outline107.append(")");
        return outline107.toString();
    }
}
