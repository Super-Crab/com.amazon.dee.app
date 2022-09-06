package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class LeicaType5MakernoteDescriptor extends TagDescriptor<LeicaType5MakernoteDirectory> {
    public LeicaType5MakernoteDescriptor(@NotNull LeicaType5MakernoteDirectory leicaType5MakernoteDirectory) {
        super(leicaType5MakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 1037 ? super.getDescription(i) : getExposureModeDescription();
    }

    @Nullable
    public String getExposureModeDescription() {
        byte[] byteArray = ((LeicaType5MakernoteDirectory) this._directory).getByteArray(1037);
        if (byteArray == null || byteArray.length < 4) {
            return null;
        }
        String format = String.format("%d %d %d %d", Byte.valueOf(byteArray[0]), Byte.valueOf(byteArray[1]), Byte.valueOf(byteArray[2]), Byte.valueOf(byteArray[3]));
        return format.equals("0 0 0 0") ? "Program AE" : format.equals("1 0 0 0") ? "Aperture-priority AE" : format.equals("1 1 0 0") ? "Aperture-priority AE (1)" : format.equals("2 0 0 0") ? "Shutter speed priority AE" : format.equals("3 0 0 0") ? "Manual" : String.format("Unknown (%s)", format);
    }
}
