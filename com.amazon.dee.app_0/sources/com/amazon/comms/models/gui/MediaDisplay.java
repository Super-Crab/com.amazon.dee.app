package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class MediaDisplay {
    private String mimeType;
    private String uri;

    /* loaded from: classes11.dex */
    public static class MediaDisplayBuilder {
        private String mimeType;
        private String uri;

        MediaDisplayBuilder() {
        }

        public MediaDisplay build() {
            return new MediaDisplay(this.uri, this.mimeType);
        }

        public MediaDisplayBuilder mimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaDisplay.MediaDisplayBuilder(uri=");
            outline107.append(this.uri);
            outline107.append(", mimeType=");
            return GeneratedOutlineSupport1.outline91(outline107, this.mimeType, ")");
        }

        public MediaDisplayBuilder uri(String str) {
            this.uri = str;
            return this;
        }
    }

    public MediaDisplay() {
    }

    public static MediaDisplayBuilder builder() {
        return new MediaDisplayBuilder();
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getUri() {
        return this.uri;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    private MediaDisplay(String str, String str2) {
        this.uri = str;
        this.mimeType = str2;
    }
}
