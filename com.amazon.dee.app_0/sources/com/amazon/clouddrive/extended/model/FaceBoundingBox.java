package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class FaceBoundingBox {
    private Double mHeight;
    private Double mTopLeftCoordX;
    private Double mTopLeftCoordY;
    private Double mWidth;

    public int compareTo(FaceBoundingBox faceBoundingBox) {
        if (faceBoundingBox == null) {
            return -1;
        }
        if (faceBoundingBox == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getTopLeftCoordY(), faceBoundingBox.getTopLeftCoordY());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getTopLeftCoordX(), faceBoundingBox.getTopLeftCoordX());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getHeight(), faceBoundingBox.getHeight());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getWidth(), faceBoundingBox.getWidth());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof FaceBoundingBox) && compareTo((FaceBoundingBox) obj) == 0;
    }

    public Double getHeight() {
        return this.mHeight;
    }

    public Double getTopLeftCoordX() {
        return this.mTopLeftCoordX;
    }

    public Double getTopLeftCoordY() {
        return this.mTopLeftCoordY;
    }

    public Double getWidth() {
        return this.mWidth;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getTopLeftCoordY() == null ? 0 : getTopLeftCoordY().hashCode()) + 1 + (getTopLeftCoordX() == null ? 0 : getTopLeftCoordX().hashCode()) + (getHeight() == null ? 0 : getHeight().hashCode());
        if (getWidth() != null) {
            i = getWidth().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setHeight(Double d) {
        this.mHeight = d;
    }

    public void setTopLeftCoordX(Double d) {
        this.mTopLeftCoordX = d;
    }

    public void setTopLeftCoordY(Double d) {
        this.mTopLeftCoordY = d;
    }

    public void setWidth(Double d) {
        this.mWidth = d;
    }
}
