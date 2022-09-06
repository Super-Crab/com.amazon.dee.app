package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes11.dex */
public class ReactionSummaries {
    private Map<String, String> errorMap;
    private Map<String, ReactionSummary> summaryMap;

    protected boolean canEqual(Object obj) {
        return obj instanceof ReactionSummaries;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReactionSummaries)) {
            return false;
        }
        ReactionSummaries reactionSummaries = (ReactionSummaries) obj;
        if (!reactionSummaries.canEqual(this)) {
            return false;
        }
        Map<String, String> errorMap = getErrorMap();
        Map<String, String> errorMap2 = reactionSummaries.getErrorMap();
        if (errorMap != null ? !errorMap.equals(errorMap2) : errorMap2 != null) {
            return false;
        }
        Map<String, ReactionSummary> summaryMap = getSummaryMap();
        Map<String, ReactionSummary> summaryMap2 = reactionSummaries.getSummaryMap();
        return summaryMap != null ? summaryMap.equals(summaryMap2) : summaryMap2 == null;
    }

    public Map<String, String> getErrorMap() {
        return this.errorMap;
    }

    public Map<String, ReactionSummary> getSummaryMap() {
        return this.summaryMap;
    }

    public int hashCode() {
        Map<String, String> errorMap = getErrorMap();
        int i = 43;
        int hashCode = errorMap == null ? 43 : errorMap.hashCode();
        Map<String, ReactionSummary> summaryMap = getSummaryMap();
        int i2 = (hashCode + 59) * 59;
        if (summaryMap != null) {
            i = summaryMap.hashCode();
        }
        return i2 + i;
    }

    public void setErrorMap(Map<String, String> map) {
        this.errorMap = map;
    }

    public void setSummaryMap(Map<String, ReactionSummary> map) {
        this.summaryMap = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReactionSummaries(errorMap=");
        outline107.append(getErrorMap());
        outline107.append(", summaryMap=");
        outline107.append(getSummaryMap());
        outline107.append(")");
        return outline107.toString();
    }
}
