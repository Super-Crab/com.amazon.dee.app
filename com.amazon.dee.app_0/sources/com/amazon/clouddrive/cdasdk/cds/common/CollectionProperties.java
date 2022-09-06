package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
/* loaded from: classes11.dex */
public class CollectionProperties {
    @JsonProperty("areCoversDesired")
    private Boolean areCoversDesired;
    @JsonProperty("covers")
    private List<CoverObject> covers;
    @JsonProperty(SearchIntents.EXTRA_QUERY)
    private CollectionQueryObject query;
    @JsonProperty("type")
    private String type;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionProperties)) {
            return false;
        }
        CollectionProperties collectionProperties = (CollectionProperties) obj;
        if (!collectionProperties.canEqual(this)) {
            return false;
        }
        Boolean areCoversDesired = getAreCoversDesired();
        Boolean areCoversDesired2 = collectionProperties.getAreCoversDesired();
        if (areCoversDesired != null ? !areCoversDesired.equals(areCoversDesired2) : areCoversDesired2 != null) {
            return false;
        }
        List<CoverObject> covers = getCovers();
        List<CoverObject> covers2 = collectionProperties.getCovers();
        if (covers != null ? !covers.equals(covers2) : covers2 != null) {
            return false;
        }
        CollectionQueryObject query = getQuery();
        CollectionQueryObject query2 = collectionProperties.getQuery();
        if (query != null ? !query.equals(query2) : query2 != null) {
            return false;
        }
        String type = getType();
        String type2 = collectionProperties.getType();
        return type != null ? type.equals(type2) : type2 == null;
    }

    public Boolean getAreCoversDesired() {
        return this.areCoversDesired;
    }

    public List<CoverObject> getCovers() {
        return this.covers;
    }

    public CollectionQueryObject getQuery() {
        return this.query;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        Boolean areCoversDesired = getAreCoversDesired();
        int i = 43;
        int hashCode = areCoversDesired == null ? 43 : areCoversDesired.hashCode();
        List<CoverObject> covers = getCovers();
        int hashCode2 = ((hashCode + 59) * 59) + (covers == null ? 43 : covers.hashCode());
        CollectionQueryObject query = getQuery();
        int hashCode3 = (hashCode2 * 59) + (query == null ? 43 : query.hashCode());
        String type = getType();
        int i2 = hashCode3 * 59;
        if (type != null) {
            i = type.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("areCoversDesired")
    public void setAreCoversDesired(Boolean bool) {
        this.areCoversDesired = bool;
    }

    @JsonProperty("covers")
    public void setCovers(List<CoverObject> list) {
        this.covers = list;
    }

    @JsonProperty(SearchIntents.EXTRA_QUERY)
    public void setQuery(CollectionQueryObject collectionQueryObject) {
        this.query = collectionQueryObject;
    }

    @JsonProperty("type")
    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CollectionProperties(areCoversDesired=");
        outline107.append(getAreCoversDesired());
        outline107.append(", covers=");
        outline107.append(getCovers());
        outline107.append(", query=");
        outline107.append(getQuery());
        outline107.append(", type=");
        outline107.append(getType());
        outline107.append(")");
        return outline107.toString();
    }
}
