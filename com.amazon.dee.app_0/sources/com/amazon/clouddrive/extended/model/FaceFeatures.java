package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class FaceFeatures {
    private String mType;
    private Double mXPosition;
    private Double mYPosition;

    public int compareTo(FaceFeatures faceFeatures) {
        if (faceFeatures == null) {
            return -1;
        }
        if (faceFeatures == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getxPosition(), faceFeatures.getxPosition());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getyPosition(), faceFeatures.getyPosition());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getType(), faceFeatures.getType());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof FaceFeatures) && compareTo((FaceFeatures) obj) == 0;
    }

    public String getType() {
        return this.mType;
    }

    public Double getxPosition() {
        return this.mXPosition;
    }

    public Double getyPosition() {
        return this.mYPosition;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getxPosition() == null ? 0 : getxPosition().hashCode()) + 1 + (getyPosition() == null ? 0 : getyPosition().hashCode());
        if (getType() != null) {
            i = getType().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setxPosition(Double d) {
        this.mXPosition = d;
    }

    public void setyPosition(Double d) {
        this.mYPosition = d;
    }
}
