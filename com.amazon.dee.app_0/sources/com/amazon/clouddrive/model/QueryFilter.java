package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class QueryFilter implements Comparable<QueryFilter> {
    private final String filterType;
    private List<String> values;

    public QueryFilter(String str) {
        this.filterType = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof QueryFilter) && compareTo((QueryFilter) obj) == 0;
    }

    public String getFilterType() {
        return this.filterType;
    }

    public List<String> getValues() {
        return this.values;
    }

    public int hashCode() {
        return this.filterType.hashCode() + (getValues() == null ? 0 : getValues().hashCode());
    }

    public void setValues(List<String> list) {
        this.values = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(QueryFilter queryFilter) {
        if (queryFilter == null) {
            return -1;
        }
        if (queryFilter == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getFilterType(), queryFilter.getFilterType());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getValues(), queryFilter.getValues());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
