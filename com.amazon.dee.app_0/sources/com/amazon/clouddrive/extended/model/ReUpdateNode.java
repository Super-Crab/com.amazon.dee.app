package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import java.util.Objects;
/* loaded from: classes11.dex */
public class ReUpdateNode implements Comparable<ReUpdateNode> {
    private String id;
    private String md5;
    private String requestedTimestamp;
    private String type;

    public boolean equals(Object obj) {
        return (obj instanceof ReUpdateNode) && compareTo((ReUpdateNode) obj) == 0;
    }

    public String getId() {
        return this.id;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getRequestedTimestamp() {
        return this.requestedTimestamp;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.md5, this.requestedTimestamp, this.type);
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setRequestedTimestamp(String str) {
        this.requestedTimestamp = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(ReUpdateNode reUpdateNode) {
        if (reUpdateNode == null) {
            return -1;
        }
        if (reUpdateNode == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(this.id, reUpdateNode.getId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(this.type, reUpdateNode.getType());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(this.md5, reUpdateNode.getMd5());
        return compare3 != 0 ? compare3 : ObjectComparator.compare(this.requestedTimestamp, reUpdateNode.getRequestedTimestamp());
    }
}
