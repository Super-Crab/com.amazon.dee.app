package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class GroupRestriction {
    private final Boolean isContentRestricted;
    private final Boolean isJoinRestricted;
    private final Boolean isReactionRestricted;

    /* loaded from: classes11.dex */
    public static class GroupRestrictionBuilder {
        private Boolean isContentRestricted;
        private Boolean isJoinRestricted;
        private Boolean isReactionRestricted;

        GroupRestrictionBuilder() {
        }

        public GroupRestriction build() {
            return new GroupRestriction(this.isContentRestricted, this.isReactionRestricted, this.isJoinRestricted);
        }

        public GroupRestrictionBuilder isContentRestricted(Boolean bool) {
            this.isContentRestricted = bool;
            return this;
        }

        public GroupRestrictionBuilder isJoinRestricted(Boolean bool) {
            this.isJoinRestricted = bool;
            return this;
        }

        public GroupRestrictionBuilder isReactionRestricted(Boolean bool) {
            this.isReactionRestricted = bool;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupRestriction.GroupRestrictionBuilder(isContentRestricted=");
            outline107.append(this.isContentRestricted);
            outline107.append(", isReactionRestricted=");
            outline107.append(this.isReactionRestricted);
            outline107.append(", isJoinRestricted=");
            outline107.append(this.isJoinRestricted);
            outline107.append(")");
            return outline107.toString();
        }
    }

    GroupRestriction(Boolean bool, Boolean bool2, Boolean bool3) {
        this.isContentRestricted = bool;
        this.isReactionRestricted = bool2;
        this.isJoinRestricted = bool3;
    }

    public static GroupRestrictionBuilder builder() {
        return new GroupRestrictionBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupRestriction)) {
            return false;
        }
        GroupRestriction groupRestriction = (GroupRestriction) obj;
        Boolean isContentRestricted = getIsContentRestricted();
        Boolean isContentRestricted2 = groupRestriction.getIsContentRestricted();
        if (isContentRestricted != null ? !isContentRestricted.equals(isContentRestricted2) : isContentRestricted2 != null) {
            return false;
        }
        Boolean isReactionRestricted = getIsReactionRestricted();
        Boolean isReactionRestricted2 = groupRestriction.getIsReactionRestricted();
        if (isReactionRestricted != null ? !isReactionRestricted.equals(isReactionRestricted2) : isReactionRestricted2 != null) {
            return false;
        }
        Boolean isJoinRestricted = getIsJoinRestricted();
        Boolean isJoinRestricted2 = groupRestriction.getIsJoinRestricted();
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

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupRestriction(isContentRestricted=");
        outline107.append(getIsContentRestricted());
        outline107.append(", isReactionRestricted=");
        outline107.append(getIsReactionRestricted());
        outline107.append(", isJoinRestricted=");
        outline107.append(getIsJoinRestricted());
        outline107.append(")");
        return outline107.toString();
    }
}
