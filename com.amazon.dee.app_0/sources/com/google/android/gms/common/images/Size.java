package com.google.android.gms.common.images;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public final class Size {
    private final int zane;
    private final int zanf;

    public Size(int i, int i2) {
        this.zane = i;
        this.zanf = i2;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (str != null) {
            int indexOf = str.indexOf(42);
            if (indexOf < 0) {
                indexOf = str.indexOf(120);
            }
            if (indexOf >= 0) {
                try {
                    return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
                } catch (NumberFormatException unused) {
                    throw zah(str);
                }
            }
            throw zah(str);
        }
        throw new IllegalArgumentException("string must not be null");
    }

    private static NumberFormatException zah(String str) {
        throw new NumberFormatException(GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(str, 16), "Invalid Size: \"", str, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED));
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.zane == size.zane && this.zanf == size.zanf) {
                return true;
            }
        }
        return false;
    }

    public final int getHeight() {
        return this.zanf;
    }

    public final int getWidth() {
        return this.zane;
    }

    public final int hashCode() {
        int i = this.zanf;
        int i2 = this.zane;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    public final String toString() {
        int i = this.zane;
        int i2 = this.zanf;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append(ReactProperties.HereMapMarker.X);
        sb.append(i2);
        return sb.toString();
    }
}
