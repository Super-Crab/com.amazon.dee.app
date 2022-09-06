package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class Aggregation {
    private Integer count;
    private SearchMetadata metadata;
    private String value;

    protected boolean canEqual(Object obj) {
        return obj instanceof Aggregation;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Aggregation)) {
            return false;
        }
        Aggregation aggregation = (Aggregation) obj;
        if (!aggregation.canEqual(this)) {
            return false;
        }
        Integer count = getCount();
        Integer count2 = aggregation.getCount();
        if (count != null ? !count.equals(count2) : count2 != null) {
            return false;
        }
        String value = getValue();
        String value2 = aggregation.getValue();
        if (value != null ? !value.equals(value2) : value2 != null) {
            return false;
        }
        SearchMetadata metadata = getMetadata();
        SearchMetadata metadata2 = aggregation.getMetadata();
        return metadata != null ? metadata.equals(metadata2) : metadata2 == null;
    }

    public Integer getCount() {
        return this.count;
    }

    public SearchMetadata getMetadata() {
        return this.metadata;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        Integer count = getCount();
        int i = 43;
        int hashCode = count == null ? 43 : count.hashCode();
        String value = getValue();
        int hashCode2 = ((hashCode + 59) * 59) + (value == null ? 43 : value.hashCode());
        SearchMetadata metadata = getMetadata();
        int i2 = hashCode2 * 59;
        if (metadata != null) {
            i = metadata.hashCode();
        }
        return i2 + i;
    }

    public void setCount(Integer num) {
        this.count = num;
    }

    public void setMetadata(SearchMetadata searchMetadata) {
        this.metadata = searchMetadata;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Aggregation(count=");
        outline107.append(getCount());
        outline107.append(", value=");
        outline107.append(getValue());
        outline107.append(", metadata=");
        outline107.append(getMetadata());
        outline107.append(")");
        return outline107.toString();
    }
}
