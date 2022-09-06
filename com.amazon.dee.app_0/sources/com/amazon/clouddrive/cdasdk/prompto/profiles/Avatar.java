package com.amazon.clouddrive.cdasdk.prompto.profiles;

import com.amazon.clouddrive.cdasdk.prompto.photos.PhotoResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class Avatar {
    @JsonProperty("color")
    private String color;
    @JsonProperty("photo")
    private PhotoResponse photo;

    protected boolean canEqual(Object obj) {
        return obj instanceof Avatar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Avatar)) {
            return false;
        }
        Avatar avatar = (Avatar) obj;
        if (!avatar.canEqual(this)) {
            return false;
        }
        PhotoResponse photo = getPhoto();
        PhotoResponse photo2 = avatar.getPhoto();
        if (photo != null ? !photo.equals(photo2) : photo2 != null) {
            return false;
        }
        String color = getColor();
        String color2 = avatar.getColor();
        return color != null ? color.equals(color2) : color2 == null;
    }

    public String getColor() {
        return this.color;
    }

    public PhotoResponse getPhoto() {
        return this.photo;
    }

    public int hashCode() {
        PhotoResponse photo = getPhoto();
        int i = 43;
        int hashCode = photo == null ? 43 : photo.hashCode();
        String color = getColor();
        int i2 = (hashCode + 59) * 59;
        if (color != null) {
            i = color.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("color")
    public void setColor(String str) {
        this.color = str;
    }

    @JsonProperty("photo")
    public void setPhoto(PhotoResponse photoResponse) {
        this.photo = photoResponse;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Avatar(photo=");
        outline107.append(getPhoto());
        outline107.append(", color=");
        outline107.append(getColor());
        outline107.append(")");
        return outline107.toString();
    }
}
