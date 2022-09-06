package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class CollectionQueryObject {
    @JsonProperty("excludedIds")
    private List<String> excludedIds;
    @JsonProperty("include")
    private List<CollectionFilterObject> include;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionQueryObject;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionQueryObject)) {
            return false;
        }
        CollectionQueryObject collectionQueryObject = (CollectionQueryObject) obj;
        if (!collectionQueryObject.canEqual(this)) {
            return false;
        }
        List<CollectionFilterObject> include = getInclude();
        List<CollectionFilterObject> include2 = collectionQueryObject.getInclude();
        if (include != null ? !include.equals(include2) : include2 != null) {
            return false;
        }
        List<String> excludedIds = getExcludedIds();
        List<String> excludedIds2 = collectionQueryObject.getExcludedIds();
        return excludedIds != null ? excludedIds.equals(excludedIds2) : excludedIds2 == null;
    }

    public List<String> getExcludedIds() {
        return this.excludedIds;
    }

    public List<CollectionFilterObject> getInclude() {
        return this.include;
    }

    public int hashCode() {
        List<CollectionFilterObject> include = getInclude();
        int i = 43;
        int hashCode = include == null ? 43 : include.hashCode();
        List<String> excludedIds = getExcludedIds();
        int i2 = (hashCode + 59) * 59;
        if (excludedIds != null) {
            i = excludedIds.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("excludedIds")
    public void setExcludedIds(List<String> list) {
        this.excludedIds = list;
    }

    @JsonProperty("include")
    public void setInclude(List<CollectionFilterObject> list) {
        this.include = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CollectionQueryObject(include=");
        outline107.append(getInclude());
        outline107.append(", excludedIds=");
        outline107.append(getExcludedIds());
        outline107.append(")");
        return outline107.toString();
    }
}
