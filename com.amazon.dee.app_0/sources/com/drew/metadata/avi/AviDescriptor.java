package com.drew.metadata.avi;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class AviDescriptor extends TagDescriptor<AviDirectory> {
    public AviDescriptor(@NotNull AviDirectory aviDirectory) {
        super(aviDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return (i == 6 || i == 7) ? getSizeDescription(i) : super.getDescription(i);
    }

    public String getSizeDescription(int i) {
        return ((AviDirectory) this._directory).getString(i) + " pixels";
    }
}
