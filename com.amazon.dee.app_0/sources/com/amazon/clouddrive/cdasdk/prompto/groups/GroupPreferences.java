package com.amazon.clouddrive.cdasdk.prompto.groups;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GroupPreferences {
    @JsonProperty("isContentRestricted")
    private Boolean isContentRestricted;
    @JsonProperty("isJoinRestricted")
    private Boolean isJoinRestricted;
    @JsonProperty("isReactionRestricted")
    private Boolean isReactionRestricted;

    protected boolean canEqual(Object obj) {
        return obj instanceof GroupPreferences;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupPreferences)) {
            return false;
        }
        GroupPreferences groupPreferences = (GroupPreferences) obj;
        if (!groupPreferences.canEqual(this)) {
            return false;
        }
        Boolean isContentRestricted = getIsContentRestricted();
        Boolean isContentRestricted2 = groupPreferences.getIsContentRestricted();
        if (isContentRestricted != null ? !isContentRestricted.equals(isContentRestricted2) : isContentRestricted2 != null) {
            return false;
        }
        Boolean isReactionRestricted = getIsReactionRestricted();
        Boolean isReactionRestricted2 = groupPreferences.getIsReactionRestricted();
        if (isReactionRestricted != null ? !isReactionRestricted.equals(isReactionRestricted2) : isReactionRestricted2 != null) {
            return false;
        }
        Boolean isJoinRestricted = getIsJoinRestricted();
        Boolean isJoinRestricted2 = groupPreferences.getIsJoinRestricted();
        return isJoinRestricted != null ? isJoinRestricted.equals(isJoinRestricted2) : isJoinRestricted2 == null;
    }

    public Boolean getIsContentRestricted() {
        return this.isContentRestricted;
    }

    public Boolean getIsJoinRestricted() {
        return this.isJoinRestricted;
    }

    public Boolean getIsReactionRestricted() {
        return this.isReactionRestricted;
    }

    public int hashCode() {
        Boolean isContentRestricted = getIsContentRestricted();
        int i = 43;
        int hashCode = isContentRestricted == null ? 43 : isContentRestricted.hashCode();
        Boolean isReactionRestricted = getIsReactionRestricted();
        int hashCode2 = ((hashCode + 59) * 59) + (isReactionRestricted == null ? 43 : isReactionRestricted.hashCode());
        Boolean isJoinRestricted = getIsJoinRestricted();
        int i2 = hashCode2 * 59;
        if (isJoinRestricted != null) {
            i = isJoinRestricted.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("isContentRestricted")
    public void setIsContentRestricted(Boolean bool) {
        this.isContentRestricted = bool;
    }

    @JsonProperty("isJoinRestricted")
    public void setIsJoinRestricted(Boolean bool) {
        this.isJoinRestricted = bool;
    }

    @JsonProperty("isReactionRestricted")
    public void setIsReactionRestricted(Boolean bool) {
        this.isReactionRestricted = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreferences(isContentRestricted=");
        outline107.append(getIsContentRestricted());
        outline107.append(", isReactionRestricted=");
        outline107.append(getIsReactionRestricted());
        outline107.append(", isJoinRestricted=");
        outline107.append(getIsJoinRestricted());
        outline107.append(")");
        return outline107.toString();
    }
}
