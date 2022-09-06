package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class PanasonicRawWbInfoDescriptor extends TagDescriptor<PanasonicRawWbInfoDirectory> {
    public PanasonicRawWbInfoDescriptor(@NotNull PanasonicRawWbInfoDirectory panasonicRawWbInfoDirectory) {
        super(panasonicRawWbInfoDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return (i == 1 || i == 4 || i == 7 || i == 10 || i == 13 || i == 16 || i == 19) ? getWbTypeDescription(i) : super.getDescription(i);
    }

    @Nullable
    public String getWbTypeDescription(int i) {
        Integer integer = ((PanasonicRawWbInfoDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        return super.getLightSourceDescription(integer.shortValue());
    }
}
