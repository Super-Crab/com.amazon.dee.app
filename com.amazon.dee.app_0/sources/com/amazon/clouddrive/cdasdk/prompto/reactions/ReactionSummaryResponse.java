package com.amazon.clouddrive.cdasdk.prompto.reactions;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ReactionSummaryResponse {
    @JsonProperty("count")
    private Integer count;

    protected boolean canEqual(Object obj) {
        return obj instanceof ReactionSummaryResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReactionSummaryResponse)) {
            return false;
        }
        ReactionSummaryResponse reactionSummaryResponse = (ReactionSummaryResponse) obj;
        if (!reactionSummaryResponse.canEqual(this)) {
            return false;
        }
        Integer count = getCount();
        Integer count2 = reactionSummaryResponse.getCount();
        return count != null ? count.equals(count2) : count2 == null;
    }

    public Integer getCount() {
        return this.count;
    }

    public int hashCode() {
        Integer count = getCount();
        return 59 + (count == null ? 43 : count.hashCode());
    }

    @JsonProperty("count")
    public void setCount(Integer num) {
        this.count = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReactionSummaryResponse(count=");
        outline107.append(getCount());
        outline107.append(")");
        return outline107.toString();
    }
}
