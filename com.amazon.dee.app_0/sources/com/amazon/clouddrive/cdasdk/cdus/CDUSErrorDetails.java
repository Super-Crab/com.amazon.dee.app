package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class CDUSErrorDetails {
    @JsonProperty("conflictNodeIds")
    private List<String> conflictNodeIds;

    protected boolean canEqual(Object obj) {
        return obj instanceof CDUSErrorDetails;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDUSErrorDetails)) {
            return false;
        }
        CDUSErrorDetails cDUSErrorDetails = (CDUSErrorDetails) obj;
        if (!cDUSErrorDetails.canEqual(this)) {
            return false;
        }
        List<String> conflictNodeIds = getConflictNodeIds();
        List<String> conflictNodeIds2 = cDUSErrorDetails.getConflictNodeIds();
        return conflictNodeIds != null ? conflictNodeIds.equals(conflictNodeIds2) : conflictNodeIds2 == null;
    }

    public List<String> getConflictNodeIds() {
        return this.conflictNodeIds;
    }

    public int hashCode() {
        List<String> conflictNodeIds = getConflictNodeIds();
        return 59 + (conflictNodeIds == null ? 43 : conflictNodeIds.hashCode());
    }

    @JsonProperty("conflictNodeIds")
    public void setConflictNodeIds(List<String> list) {
        this.conflictNodeIds = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDUSErrorDetails(conflictNodeIds=");
        outline107.append(getConflictNodeIds());
        outline107.append(")");
        return outline107.toString();
    }
}
