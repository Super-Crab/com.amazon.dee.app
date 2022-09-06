package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class MediaDisplays {
    private MediaDisplay smallIcon;

    /* loaded from: classes11.dex */
    public static class MediaDisplaysBuilder {
        private MediaDisplay smallIcon;

        MediaDisplaysBuilder() {
        }

        public MediaDisplays build() {
            return new MediaDisplays(this.smallIcon);
        }

        public MediaDisplaysBuilder smallIcon(MediaDisplay mediaDisplay) {
            this.smallIcon = mediaDisplay;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaDisplays.MediaDisplaysBuilder(smallIcon=");
            outline107.append(this.smallIcon);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public MediaDisplays() {
    }

    public static MediaDisplaysBuilder builder() {
        return new MediaDisplaysBuilder();
    }

    public MediaDisplay getSmallIcon() {
        return this.smallIcon;
    }

    public void setSmallIcon(MediaDisplay mediaDisplay) {
        this.smallIcon = mediaDisplay;
    }

    private MediaDisplays(MediaDisplay mediaDisplay) {
        this.smallIcon = mediaDisplay;
    }
}
