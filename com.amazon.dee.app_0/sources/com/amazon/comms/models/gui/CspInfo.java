package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class CspInfo {
    private String cspId;
    private Image cspImage;
    private String displayName;

    /* loaded from: classes11.dex */
    public static class CspInfoBuilder {
        private String cspId;
        private Image cspImage;
        private String displayName;

        CspInfoBuilder() {
        }

        public CspInfo build() {
            return new CspInfo(this.displayName, this.cspId, this.cspImage);
        }

        public CspInfoBuilder cspId(String str) {
            this.cspId = str;
            return this;
        }

        public CspInfoBuilder cspImage(Image image) {
            this.cspImage = image;
            return this;
        }

        public CspInfoBuilder displayName(String str) {
            this.displayName = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CspInfo.CspInfoBuilder(displayName=");
            outline107.append(this.displayName);
            outline107.append(", cspId=");
            outline107.append(this.cspId);
            outline107.append(", cspImage=");
            outline107.append(this.cspImage);
            outline107.append(")");
            return outline107.toString();
        }
    }

    CspInfo(String str, String str2, Image image) {
        this.displayName = str;
        this.cspId = str2;
        this.cspImage = image;
    }

    public static CspInfoBuilder builder() {
        return new CspInfoBuilder();
    }

    public String getCspId() {
        return this.cspId;
    }

    public Image getCspImage() {
        return this.cspImage;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
