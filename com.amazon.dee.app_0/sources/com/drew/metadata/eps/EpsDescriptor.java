package com.drew.metadata.eps;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class EpsDescriptor extends TagDescriptor<EpsDirectory> {
    public EpsDescriptor(@NotNull EpsDirectory epsDirectory) {
        super(epsDirectory);
    }

    public String getByteSizeDescription(int i) {
        return ((EpsDirectory) this._directory).getString(i) + " bytes";
    }

    @Nullable
    public String getColorTypeDescription() {
        return getIndexedDescription(30, 1, "Grayscale", "Lab", "RGB", "CMYK");
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        switch (i) {
            case 28:
            case 29:
                return getPixelDescription(i);
            case 30:
                return getColorTypeDescription();
            case 31:
            default:
                return ((EpsDirectory) this._directory).getString(i);
            case 32:
            case 33:
                return getByteSizeDescription(i);
        }
    }

    public String getPixelDescription(int i) {
        return ((EpsDirectory) this._directory).getString(i) + " pixels";
    }
}
