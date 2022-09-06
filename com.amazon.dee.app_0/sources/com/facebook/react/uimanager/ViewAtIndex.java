package com.facebook.react.uimanager;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Comparator;
/* loaded from: classes2.dex */
public class ViewAtIndex {
    public static Comparator<ViewAtIndex> COMPARATOR = new Comparator<ViewAtIndex>() { // from class: com.facebook.react.uimanager.ViewAtIndex.1
        @Override // java.util.Comparator
        public int compare(ViewAtIndex viewAtIndex, ViewAtIndex viewAtIndex2) {
            return viewAtIndex.mIndex - viewAtIndex2.mIndex;
        }
    };
    public final int mIndex;
    public final int mTag;

    public ViewAtIndex(int i, int i2) {
        this.mTag = i;
        this.mIndex = i2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != ViewAtIndex.class) {
            return false;
        }
        ViewAtIndex viewAtIndex = (ViewAtIndex) obj;
        return this.mIndex == viewAtIndex.mIndex && this.mTag == viewAtIndex.mTag;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(this.mTag);
        outline107.append(", ");
        return GeneratedOutlineSupport1.outline86(outline107, this.mIndex, "]");
    }
}
