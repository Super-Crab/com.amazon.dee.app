package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class QueryAndBlock implements Comparable<QueryAndBlock> {
    private List<QueryFilter> filters;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof QueryAndBlock) && compareTo((QueryAndBlock) obj) == 0;
    }

    public List<QueryFilter> getFilters() {
        return this.filters;
    }

    public int hashCode() {
        return (getFilters() == null ? 0 : getFilters().hashCode()) + 1;
    }

    public void setFilters(List<QueryFilter> list) {
        this.filters = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(QueryAndBlock queryAndBlock) {
        int compare;
        if (queryAndBlock == null) {
            return -1;
        }
        if (queryAndBlock != this && (compare = ObjectComparator.compare(getFilters(), queryAndBlock.getFilters())) != 0) {
            return compare;
        }
        return 0;
    }
}
