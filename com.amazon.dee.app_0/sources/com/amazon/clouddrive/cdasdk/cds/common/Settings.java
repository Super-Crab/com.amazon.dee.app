package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class Settings {
    @JsonProperty(PhotoSearchCategory.FAVORITE)
    private Boolean favorite;
    @JsonProperty("hidden")
    private Boolean hidden;

    protected boolean canEqual(Object obj) {
        return obj instanceof Settings;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Settings)) {
            return false;
        }
        Settings settings = (Settings) obj;
        if (!settings.canEqual(this)) {
            return false;
        }
        Boolean favorite = getFavorite();
        Boolean favorite2 = settings.getFavorite();
        if (favorite != null ? !favorite.equals(favorite2) : favorite2 != null) {
            return false;
        }
        Boolean hidden = getHidden();
        Boolean hidden2 = settings.getHidden();
        return hidden != null ? hidden.equals(hidden2) : hidden2 == null;
    }

    public Boolean getFavorite() {
        return this.favorite;
    }

    public Boolean getHidden() {
        return this.hidden;
    }

    public int hashCode() {
        Boolean favorite = getFavorite();
        int i = 43;
        int hashCode = favorite == null ? 43 : favorite.hashCode();
        Boolean hidden = getHidden();
        int i2 = (hashCode + 59) * 59;
        if (hidden != null) {
            i = hidden.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty(PhotoSearchCategory.FAVORITE)
    public void setFavorite(Boolean bool) {
        this.favorite = bool;
    }

    @JsonProperty("hidden")
    public void setHidden(Boolean bool) {
        this.hidden = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Settings(favorite=");
        outline107.append(getFavorite());
        outline107.append(", hidden=");
        outline107.append(getHidden());
        outline107.append(")");
        return outline107.toString();
    }
}
