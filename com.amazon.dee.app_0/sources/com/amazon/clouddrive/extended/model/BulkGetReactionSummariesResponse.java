package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class BulkGetReactionSummariesResponse extends ReactionSummaries {
    @Override // com.amazon.clouddrive.extended.model.ReactionSummaries
    protected boolean canEqual(Object obj) {
        return obj instanceof BulkGetReactionSummariesResponse;
    }

    @Override // com.amazon.clouddrive.extended.model.ReactionSummaries
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BulkGetReactionSummariesResponse) && ((BulkGetReactionSummariesResponse) obj).canEqual(this) && super.equals(obj);
    }

    @Override // com.amazon.clouddrive.extended.model.ReactionSummaries
    public int hashCode() {
        return 59 + super.hashCode();
    }

    @Override // com.amazon.clouddrive.extended.model.ReactionSummaries
    public String toString() {
        return "BulkGetReactionSummariesResponse()";
    }
}
