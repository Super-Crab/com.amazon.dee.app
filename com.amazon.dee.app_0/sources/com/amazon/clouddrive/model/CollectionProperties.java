package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class CollectionProperties implements Comparable<CollectionProperties> {
    private Boolean areCoversDesired;
    private List<CoverObject> covers;
    private QueryObject query;

    public Boolean areCoversDesired() {
        return this.areCoversDesired;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CollectionProperties) && compareTo((CollectionProperties) obj) == 0;
    }

    public List<CoverObject> getCovers() {
        return this.covers;
    }

    public QueryObject getQuery() {
        return this.query;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getQuery() == null ? 0 : getQuery().hashCode()) + 1 + (areCoversDesired() == null ? 0 : areCoversDesired().hashCode());
        if (getCovers() != null) {
            i = getCovers().hashCode();
        }
        return hashCode + i;
    }

    public void setCovers(List<CoverObject> list) {
        this.covers = list;
    }

    public void setCoversDesired(Boolean bool) {
        this.areCoversDesired = bool;
    }

    public void setQuery(QueryObject queryObject) {
        this.query = queryObject;
    }

    @Override // java.lang.Comparable
    public int compareTo(CollectionProperties collectionProperties) {
        if (collectionProperties == null) {
            return -1;
        }
        if (collectionProperties == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getQuery(), collectionProperties.getQuery());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(areCoversDesired(), collectionProperties.areCoversDesired());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getCovers(), collectionProperties.getCovers());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
