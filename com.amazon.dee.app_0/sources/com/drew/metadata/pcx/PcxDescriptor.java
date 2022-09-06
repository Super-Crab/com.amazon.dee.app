package com.drew.metadata.pcx;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class PcxDescriptor extends TagDescriptor<PcxDirectory> {
    public PcxDescriptor(@NotNull PcxDirectory pcxDirectory) {
        super(pcxDirectory);
    }

    @Nullable
    public String getColorPlanesDescription() {
        return getIndexedDescription(10, 3, "24-bit color", "16 colors");
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 1 ? i != 10 ? i != 12 ? super.getDescription(i) : getPaletteTypeDescription() : getColorPlanesDescription() : getVersionDescription();
    }

    @Nullable
    public String getPaletteTypeDescription() {
        return getIndexedDescription(12, 1, "Color or B&W", "Grayscale");
    }

    @Nullable
    public String getVersionDescription() {
        return getIndexedDescription(1, "2.5 with fixed EGA palette information", null, "2.8 with modifiable EGA palette information", "2.8 without palette information (default palette)", "PC Paintbrush for Windows", "3.0 or better");
    }
}
