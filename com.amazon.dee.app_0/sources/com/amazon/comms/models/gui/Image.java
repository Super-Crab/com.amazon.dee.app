package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class Image {
    private String size;
    private String url;

    /* loaded from: classes11.dex */
    public static class ImageBuilder {
        private String size;
        private String url;

        ImageBuilder() {
        }

        public Image build() {
            return new Image(this.size, this.url);
        }

        public ImageBuilder size(String str) {
            this.size = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Image.ImageBuilder(size=");
            outline107.append(this.size);
            outline107.append(", url=");
            return GeneratedOutlineSupport1.outline91(outline107, this.url, ")");
        }

        public ImageBuilder url(String str) {
            this.url = str;
            return this;
        }
    }

    Image(String str, String str2) {
        this.size = str;
        this.url = str2;
    }

    public static ImageBuilder builder() {
        return new ImageBuilder();
    }

    public String getSize() {
        return this.size;
    }

    public String getUrl() {
        return this.url;
    }
}
