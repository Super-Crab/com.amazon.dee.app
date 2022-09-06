package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.List;
import java.util.Objects;
/* loaded from: classes12.dex */
public class EffectCategory {
    private List<EffectIcon> icons;
    private String name;
    private String thumbnail;

    /* loaded from: classes12.dex */
    public static class Builder {
        private List<EffectIcon> icons;
        private String name;
        private String thumbnail;

        public EffectCategory build() {
            return new EffectCategory(this.name, this.thumbnail, this.icons);
        }

        public Builder icons(List<EffectIcon> list) {
            this.icons = list;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder thumbnail(String str) {
            this.thumbnail = str;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum EffectCategoryName {
        WORLD("WORLD"),
        REACTION("REACTION"),
        OPTIN("OPTIN"),
        VIDEO("VIDEO"),
        AUDIO("AUDIO"),
        CAPTION("CAPTION");
        
        private String value;

        EffectCategoryName(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return this.value;
        }
    }

    @VisibleForTesting
    EffectCategory(String str, String str2, List<EffectIcon> list) {
        this.name = str;
        this.thumbnail = str2;
        this.icons = list;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EffectCategory.class != obj.getClass()) {
            return false;
        }
        EffectCategory effectCategory = (EffectCategory) obj;
        return Objects.equals(this.name, effectCategory.name) && Objects.equals(this.thumbnail, effectCategory.thumbnail) && Objects.equals(this.icons, effectCategory.icons);
    }

    public List<EffectIcon> getIcons() {
        return this.icons;
    }

    public String getName() {
        return this.name;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.thumbnail, this.icons);
    }

    public void setIcons(List<EffectIcon> list) {
        this.icons = list;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setThumbnail(String str) {
        this.thumbnail = str;
    }
}
