package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class TopicInfo implements Comparable<TopicInfo> {
    private final String nodeType;

    /* loaded from: classes11.dex */
    public static class Builder {
        private String nodeType;

        public TopicInfo build() {
            return new TopicInfo(this.nodeType);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Builder{nodeType='");
            outline107.append(this.nodeType);
            outline107.append("'");
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        public Builder withNodeType(String str) {
            this.nodeType = str;
            return this;
        }
    }

    public TopicInfo(String str) {
        this.nodeType = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof TopicInfo) && compareTo((TopicInfo) obj) == 0;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public int hashCode() {
        if (getNodeType() != null) {
            return getNodeType().hashCode();
        }
        return 0;
    }

    @Override // java.lang.Comparable
    public int compareTo(TopicInfo topicInfo) {
        int compare;
        if (topicInfo == null) {
            return -1;
        }
        if (topicInfo != this && (compare = ObjectComparator.compare(getNodeType(), topicInfo.getNodeType())) != 0) {
            return compare;
        }
        return 0;
    }
}
