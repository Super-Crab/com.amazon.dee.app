package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class PanasonicRawIFD0Descriptor extends TagDescriptor<PanasonicRawIFD0Directory> {
    public PanasonicRawIFD0Descriptor(@NotNull PanasonicRawIFD0Directory panasonicRawIFD0Directory) {
        super(panasonicRawIFD0Directory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 1 ? i != 274 ? super.getDescription(i) : getOrientationDescription(274) : getVersionBytesDescription(1, 2);
    }
}
