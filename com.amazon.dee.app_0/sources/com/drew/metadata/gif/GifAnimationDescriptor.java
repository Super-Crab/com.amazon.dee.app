package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class GifAnimationDescriptor extends TagDescriptor<GifAnimationDirectory> {
    public GifAnimationDescriptor(@NotNull GifAnimationDirectory gifAnimationDirectory) {
        super(gifAnimationDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 1 ? super.getDescription(i) : getIterationCountDescription();
    }

    @Nullable
    public String getIterationCountDescription() {
        Integer integer = ((GifAnimationDirectory) this._directory).getInteger(1);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "Infinite";
        }
        if (integer.intValue() == 1) {
            return "Once";
        }
        if (integer.intValue() == 2) {
            return "Twice";
        }
        return integer.toString() + " times";
    }
}
