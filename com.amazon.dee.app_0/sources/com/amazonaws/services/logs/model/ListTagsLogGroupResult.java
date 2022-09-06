package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class ListTagsLogGroupResult implements Serializable {
    private Map<String, String> tags;

    public ListTagsLogGroupResult addtagsEntry(String str, String str2) {
        if (this.tags == null) {
            this.tags = new HashMap();
        }
        if (!this.tags.containsKey(str)) {
            this.tags.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public ListTagsLogGroupResult cleartagsEntries() {
        this.tags = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTagsLogGroupResult)) {
            return false;
        }
        ListTagsLogGroupResult listTagsLogGroupResult = (ListTagsLogGroupResult) obj;
        if ((listTagsLogGroupResult.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return listTagsLogGroupResult.getTags() == null || listTagsLogGroupResult.getTags().equals(getTags());
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public int hashCode() {
        return 31 + (getTags() == null ? 0 : getTags().hashCode());
    }

    public void setTags(Map<String, String> map) {
        this.tags = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTags() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1072.append(getTags());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTagsLogGroupResult withTags(Map<String, String> map) {
        this.tags = map;
        return this;
    }
}
