package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class SigmaMakernoteDescriptor extends TagDescriptor<SigmaMakernoteDirectory> {
    public SigmaMakernoteDescriptor(@NotNull SigmaMakernoteDirectory sigmaMakernoteDirectory) {
        super(sigmaMakernoteDirectory);
    }

    @Nullable
    private String getExposureModeDescription() {
        String string = ((SigmaMakernoteDirectory) this._directory).getString(8);
        if (string == null || string.length() == 0) {
            return null;
        }
        char charAt = string.charAt(0);
        return charAt != 'A' ? charAt != 'M' ? charAt != 'P' ? charAt != 'S' ? string : "Shutter Speed Priority AE" : "Program AE" : "Manual" : "Aperture Priority AE";
    }

    @Nullable
    private String getMeteringModeDescription() {
        String string = ((SigmaMakernoteDirectory) this._directory).getString(9);
        if (string == null || string.length() == 0) {
            return null;
        }
        char charAt = string.charAt(0);
        return charAt != '8' ? charAt != 'A' ? charAt != 'C' ? string : "Center Weighted Average" : "Average" : "Multi Segment";
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 8 ? i != 9 ? super.getDescription(i) : getMeteringModeDescription() : getExposureModeDescription();
    }
}
