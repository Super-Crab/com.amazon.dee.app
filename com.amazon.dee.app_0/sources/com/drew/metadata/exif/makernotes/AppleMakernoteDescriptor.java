package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class AppleMakernoteDescriptor extends TagDescriptor<AppleMakernoteDirectory> {
    public AppleMakernoteDescriptor(@NotNull AppleMakernoteDirectory appleMakernoteDirectory) {
        super(appleMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 10 ? super.getDescription(i) : getHdrImageTypeDescription();
    }

    @Nullable
    public String getHdrImageTypeDescription() {
        return getIndexedDescription(10, 3, "HDR Image", "Original Image");
    }
}
