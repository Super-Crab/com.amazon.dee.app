package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class QueryObject implements Comparable<QueryObject> {
    private List<String> excludedIds;
    private List<QueryAndBlock> include;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof QueryObject) && compareTo((QueryObject) obj) == 0;
    }

    public List<String> getExcludedIds() {
        return this.excludedIds;
    }

    public List<QueryAndBlock> getInclude() {
        return this.include;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getExcludedIds() == null ? 0 : getExcludedIds().hashCode()) + 1;
        if (getInclude() != null) {
            i = getInclude().hashCode();
        }
        return hashCode + i;
    }

    public void setExcludedIds(List<String> list) {
        this.excludedIds = list;
    }

    public void setInclude(List<QueryAndBlock> list) {
        this.include = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(QueryObject queryObject) {
        if (queryObject == null) {
            return -1;
        }
        if (queryObject == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getExcludedIds(), queryObject.getExcludedIds());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getInclude(), queryObject.getInclude());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
