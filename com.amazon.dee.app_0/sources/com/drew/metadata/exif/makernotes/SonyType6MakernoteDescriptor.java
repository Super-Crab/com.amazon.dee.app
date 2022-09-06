package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class SonyType6MakernoteDescriptor extends TagDescriptor<SonyType6MakernoteDirectory> {
    public SonyType6MakernoteDescriptor(@NotNull SonyType6MakernoteDirectory sonyType6MakernoteDirectory) {
        super(sonyType6MakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 8192 ? super.getDescription(i) : getMakernoteThumbVersionDescription();
    }

    @Nullable
    public String getMakernoteThumbVersionDescription() {
        return getVersionBytesDescription(8192, 2);
    }
}
