package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class Face {
    private FaceBoundingBox mBox;
    private Double mConfidence;
    private List<FaceFeatures> mFeature;
    private List<String> mMatches;

    public int compareTo(Face face) {
        if (face == null) {
            return -1;
        }
        if (face == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getBoundingBox(), face.getBoundingBox());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getFeatures(), face.getFeatures());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare2 = ObjectComparator.compare(getConfidence(), face.getConfidence());
        if (compare2 != 0) {
            return compare2;
        }
        int compareCollections2 = ObjectComparator.compareCollections(getMatches(), face.getMatches());
        if (compareCollections2 == 0) {
            return 0;
        }
        return compareCollections2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Face) && compareTo((Face) obj) == 0;
    }

    public FaceBoundingBox getBoundingBox() {
        return this.mBox;
    }

    public Double getConfidence() {
        return this.mConfidence;
    }

    public List<FaceFeatures> getFeatures() {
        return this.mFeature;
    }

    public List<String> getMatches() {
        return this.mMatches;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getBoundingBox() == null ? 0 : getBoundingBox().hashCode()) + 1 + (getFeatures() == null ? 0 : getFeatures().hashCode()) + (getConfidence() == null ? 0 : getConfidence().hashCode());
        if (getMatches() != null) {
            i = getMatches().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setBoundingBox(FaceBoundingBox faceBoundingBox) {
        this.mBox = faceBoundingBox;
    }

    public void setConfidence(Double d) {
        this.mConfidence = d;
    }

    public void setFeatures(List<FaceFeatures> list) {
        this.mFeature = list;
    }

    public void setMatches(List<String> list) {
        this.mMatches = list;
    }
}
