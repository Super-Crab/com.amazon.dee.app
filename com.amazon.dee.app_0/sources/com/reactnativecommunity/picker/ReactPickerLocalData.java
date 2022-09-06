package com.reactnativecommunity.picker;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes3.dex */
public class ReactPickerLocalData {
    private final int height;

    public ReactPickerLocalData(int i) {
        this.height = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ReactPickerLocalData.class == obj.getClass() && this.height == ((ReactPickerLocalData) obj).height;
    }

    public int getHeight() {
        return this.height;
    }

    public int hashCode() {
        return this.height + 31;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline85(GeneratedOutlineSupport1.outline107("RectPickerLocalData{height="), this.height, JsonReaderKt.END_OBJ);
    }
}
