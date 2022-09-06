package com.google.zxing;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
/* loaded from: classes3.dex */
public final class Dimension {
    private final int height;
    private final int width;

    public Dimension(int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            this.width = i;
            this.height = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Dimension) {
            Dimension dimension = (Dimension) obj;
            return this.width == dimension.width && this.height == dimension.height;
        }
        return false;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (this.width * 32713) + this.height;
    }

    public String toString() {
        return this.width + ReactProperties.HereMapMarker.X + this.height;
    }
}
