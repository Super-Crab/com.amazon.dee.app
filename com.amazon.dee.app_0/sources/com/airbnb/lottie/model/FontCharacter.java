package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class FontCharacter {
    private final char character;
    private final String fontFamily;
    private final List<ShapeGroup> shapes;
    private final double size;
    private final String style;
    private final double width;

    public FontCharacter(List<ShapeGroup> list, char c, double d, double d2, String str, String str2) {
        this.shapes = list;
        this.character = c;
        this.size = d;
        this.width = d2;
        this.style = str;
        this.fontFamily = str2;
    }

    public static int hashFor(char c, String str, String str2) {
        return str2.hashCode() + GeneratedOutlineSupport1.outline7(str, (c + 0) * 31, 31);
    }

    public List<ShapeGroup> getShapes() {
        return this.shapes;
    }

    double getSize() {
        return this.size;
    }

    String getStyle() {
        return this.style;
    }

    public double getWidth() {
        return this.width;
    }

    public int hashCode() {
        return hashFor(this.character, this.fontFamily, this.style);
    }
}
