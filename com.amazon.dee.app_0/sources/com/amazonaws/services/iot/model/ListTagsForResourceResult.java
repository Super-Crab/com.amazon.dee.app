package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListTagsForResourceResult implements Serializable {
    private String nextToken;
    private List<Tag> tags;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTagsForResourceResult)) {
            return false;
        }
        ListTagsForResourceResult listTagsForResourceResult = (ListTagsForResourceResult) obj;
        if ((listTagsForResourceResult.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (listTagsForResourceResult.getTags() != null && !listTagsForResourceResult.getTags().equals(getTags())) {
            return false;
        }
        if ((listTagsForResourceResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTagsForResourceResult.getNextToken() == null || listTagsForResourceResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTags() == null ? 0 : getTags().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTags() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1072.append(getTags());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTagsForResourceResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListTagsForResourceResult withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public ListTagsForResourceResult withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }
}
