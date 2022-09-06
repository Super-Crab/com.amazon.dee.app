package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class PrintIMDescriptor extends TagDescriptor<PrintIMDirectory> {
    public PrintIMDescriptor(@NotNull PrintIMDirectory printIMDirectory) {
        super(printIMDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 0) {
            Integer integer = ((PrintIMDirectory) this._directory).getInteger(i);
            if (integer != null) {
                return String.format("0x%08x", integer);
            }
            return null;
        }
        return super.getDescription(i);
    }
}
