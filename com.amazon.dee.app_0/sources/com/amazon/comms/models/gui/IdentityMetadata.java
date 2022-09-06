package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class IdentityMetadata {
    private String identityId;
    private MediaDisplays mediaDisplays;
    private String name;

    /* loaded from: classes11.dex */
    public static class IdentityMetadataBuilder {
        private String identityId;
        private MediaDisplays mediaDisplays;
        private String name;

        IdentityMetadataBuilder() {
        }

        public IdentityMetadata build() {
            return new IdentityMetadata(this.identityId, this.name, this.mediaDisplays);
        }

        public IdentityMetadataBuilder identityId(String str) {
            this.identityId = str;
            return this;
        }

        public IdentityMetadataBuilder mediaDisplays(MediaDisplays mediaDisplays) {
            this.mediaDisplays = mediaDisplays;
            return this;
        }

        public IdentityMetadataBuilder name(String str) {
            this.name = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IdentityMetadata.IdentityMetadataBuilder(identityId=");
            outline107.append(this.identityId);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", mediaDisplays=");
            outline107.append(this.mediaDisplays);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public IdentityMetadata() {
    }

    public static IdentityMetadataBuilder builder() {
        return new IdentityMetadataBuilder();
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public MediaDisplays getMediaDisplays() {
        return this.mediaDisplays;
    }

    public String getName() {
        return this.name;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setMediaDisplays(MediaDisplays mediaDisplays) {
        this.mediaDisplays = mediaDisplays;
    }

    public void setName(String str) {
        this.name = str;
    }

    private IdentityMetadata(String str, String str2, MediaDisplays mediaDisplays) {
        this.identityId = str;
        this.name = str2;
        this.mediaDisplays = mediaDisplays;
    }
}
