package com.drew.metadata.jfif;

import androidx.core.view.MotionEventCompat;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class JfifDescriptor extends TagDescriptor<JfifDirectory> {
    public JfifDescriptor(@NotNull JfifDirectory jfifDirectory) {
        super(jfifDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 5 ? i != 10 ? i != 7 ? i != 8 ? super.getDescription(i) : getImageResXDescription() : getImageResUnitsDescription() : getImageResYDescription() : getImageVersionDescription();
    }

    @Nullable
    public String getImageResUnitsDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(7);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 2 ? "unit" : "centimetre" : "inch" : "none";
    }

    @Nullable
    public String getImageResXDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(8);
        if (integer == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = integer;
        objArr[1] = integer.intValue() == 1 ? "" : "s";
        return String.format("%d dot%s", objArr);
    }

    @Nullable
    public String getImageResYDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(10);
        if (integer == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = integer;
        objArr[1] = integer.intValue() == 1 ? "" : "s";
        return String.format("%d dot%s", objArr);
    }

    @Nullable
    public String getImageVersionDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        return String.format("%d.%d", Integer.valueOf((integer.intValue() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8), Integer.valueOf(integer.intValue() & 255));
    }
}
