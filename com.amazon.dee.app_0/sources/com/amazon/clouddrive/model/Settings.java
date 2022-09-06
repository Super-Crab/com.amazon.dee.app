package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class Settings implements Comparable<Settings> {
    private Boolean favorite;
    private Boolean hidden;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Settings) && compareTo((Settings) obj) == 0;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (isFavorite() == null ? 0 : isFavorite().hashCode()) + 1;
        if (isHidden() != null) {
            i = isHidden().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isFavorite() {
        return this.favorite;
    }

    public Boolean isHidden() {
        return this.hidden;
    }

    public void setFavorite(Boolean bool) {
        this.favorite = bool;
    }

    public void setHidden(Boolean bool) {
        this.hidden = bool;
    }

    @Override // java.lang.Comparable
    public int compareTo(Settings settings) {
        if (settings == null) {
            return -1;
        }
        if (settings == this) {
            return 0;
        }
        Boolean isFavorite = isFavorite();
        Boolean isFavorite2 = settings.isFavorite();
        if (isFavorite != isFavorite2) {
            if (isFavorite == null) {
                return -1;
            }
            if (isFavorite2 == null) {
                return 1;
            }
            int compareTo = isFavorite.compareTo(isFavorite2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        Boolean isHidden = isHidden();
        Boolean isHidden2 = settings.isHidden();
        if (isHidden != isHidden2) {
            if (isHidden == null) {
                return -1;
            }
            if (isHidden2 == null) {
                return 1;
            }
            int compareTo2 = isHidden.compareTo(isHidden2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
