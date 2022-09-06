package com.amazon.comms.util;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
/* loaded from: classes12.dex */
public class Size {
    private int height;
    private int width;

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public String toString() {
        return this.width + ReactProperties.HereMapMarker.X + this.height;
    }
}
