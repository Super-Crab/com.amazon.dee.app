package com.drew.metadata;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
/* loaded from: classes2.dex */
public class Face {
    @Nullable
    private final Age _age;
    private final int _height;
    @Nullable
    private final String _name;
    private final int _width;
    private final int _x;
    private final int _y;

    public Face(int i, int i2, int i3, int i4, @Nullable String str, @Nullable Age age) {
        this._x = i;
        this._y = i2;
        this._width = i3;
        this._height = i4;
        this._name = str;
        this._age = age;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Face.class != obj.getClass()) {
            return false;
        }
        Face face = (Face) obj;
        if (this._height != face._height || this._width != face._width || this._x != face._x || this._y != face._y) {
            return false;
        }
        Age age = this._age;
        if (age == null ? face._age != null : !age.equals(face._age)) {
            return false;
        }
        String str = this._name;
        String str2 = face._name;
        return str == null ? str2 == null : str.equals(str2);
    }

    @Nullable
    public Age getAge() {
        return this._age;
    }

    public int getHeight() {
        return this._height;
    }

    @Nullable
    public String getName() {
        return this._name;
    }

    public int getWidth() {
        return this._width;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public int hashCode() {
        int i = ((((((this._x * 31) + this._y) * 31) + this._width) * 31) + this._height) * 31;
        String str = this._name;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        Age age = this._age;
        if (age != null) {
            i2 = age.hashCode();
        }
        return hashCode + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("x: ");
        outline107.append(this._x);
        outline107.append(" y: ");
        outline107.append(this._y);
        outline107.append(" width: ");
        outline107.append(this._width);
        outline107.append(" height: ");
        outline107.append(this._height);
        if (this._name != null) {
            outline107.append(" name: ");
            outline107.append(this._name);
        }
        if (this._age != null) {
            outline107.append(" age: ");
            outline107.append(this._age.toFriendlyString());
        }
        return outline107.toString();
    }
}
