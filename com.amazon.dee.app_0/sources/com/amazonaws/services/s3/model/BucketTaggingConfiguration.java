package com.amazonaws.services.s3.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class BucketTaggingConfiguration implements Serializable {
    private List<TagSet> tagSets;

    public BucketTaggingConfiguration() {
        this.tagSets = null;
        this.tagSets = new ArrayList(1);
    }

    public List<TagSet> getAllTagSets() {
        return this.tagSets;
    }

    public TagSet getTagSet() {
        return this.tagSets.get(0);
    }

    public TagSet getTagSetAtIndex(int i) {
        return this.tagSets.get(i);
    }

    public void setTagSets(Collection<TagSet> collection) {
        this.tagSets.clear();
        this.tagSets.addAll(collection);
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TagSets: ");
        outline107.append(getAllTagSets());
        outline103.append(outline107.toString());
        outline103.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline103.toString();
    }

    public BucketTaggingConfiguration withTagSets(TagSet... tagSetArr) {
        this.tagSets.clear();
        for (TagSet tagSet : tagSetArr) {
            this.tagSets.add(tagSet);
        }
        return this;
    }

    public BucketTaggingConfiguration(Collection<TagSet> collection) {
        this.tagSets = null;
        this.tagSets = new ArrayList(1);
        this.tagSets.addAll(collection);
    }
}
