package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListTagsForStreamResult implements Serializable {
    private Boolean hasMoreTags;
    private List<Tag> tags = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTagsForStreamResult)) {
            return false;
        }
        ListTagsForStreamResult listTagsForStreamResult = (ListTagsForStreamResult) obj;
        if ((listTagsForStreamResult.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (listTagsForStreamResult.getTags() != null && !listTagsForStreamResult.getTags().equals(getTags())) {
            return false;
        }
        if ((listTagsForStreamResult.getHasMoreTags() == null) ^ (getHasMoreTags() == null)) {
            return false;
        }
        return listTagsForStreamResult.getHasMoreTags() == null || listTagsForStreamResult.getHasMoreTags().equals(getHasMoreTags());
    }

    public Boolean getHasMoreTags() {
        return this.hasMoreTags;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTags() == null ? 0 : getTags().hashCode()) + 31) * 31;
        if (getHasMoreTags() != null) {
            i = getHasMoreTags().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isHasMoreTags() {
        return this.hasMoreTags;
    }

    public void setHasMoreTags(Boolean bool) {
        this.hasMoreTags = bool;
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
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Tags: ");
            outline1072.append(getTags());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getHasMoreTags() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("HasMoreTags: ");
            outline1073.append(getHasMoreTags());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTagsForStreamResult withHasMoreTags(Boolean bool) {
        this.hasMoreTags = bool;
        return this;
    }

    public ListTagsForStreamResult withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public ListTagsForStreamResult withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }
}
