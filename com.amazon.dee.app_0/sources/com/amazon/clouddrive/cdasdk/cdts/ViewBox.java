package com.amazon.clouddrive.cdasdk.cdts;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ViewBox {
    private int height;
    private int width;

    /* loaded from: classes11.dex */
    public static class ViewBoxBuilder {
        private int height;
        private int width;

        ViewBoxBuilder() {
        }

        public ViewBox build() {
            return new ViewBox(this.width, this.height);
        }

        public ViewBoxBuilder height(int i) {
            this.height = i;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ViewBox.ViewBoxBuilder(width=");
            outline107.append(this.width);
            outline107.append(", height=");
            return GeneratedOutlineSupport1.outline86(outline107, this.height, ")");
        }

        public ViewBoxBuilder width(int i) {
            this.width = i;
            return this;
        }
    }

    ViewBox(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public static ViewBoxBuilder builder() {
        return new ViewBoxBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ViewBox;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewBox)) {
            return false;
        }
        ViewBox viewBox = (ViewBox) obj;
        return viewBox.canEqual(this) && getWidth() == viewBox.getWidth() && getHeight() == viewBox.getHeight();
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return getHeight() + ((getWidth() + 59) * 59);
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ViewBox(width=");
        outline107.append(getWidth());
        outline107.append(", height=");
        outline107.append(getHeight());
        outline107.append(")");
        return outline107.toString();
    }
}
