package com.drew.metadata.mov.media;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.mov.QuickTimeDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
/* loaded from: classes2.dex */
public class QuickTimeVideoDescriptor extends QuickTimeDescriptor {
    public QuickTimeVideoDescriptor(@NotNull QuickTimeVideoDirectory quickTimeVideoDirectory) {
        super(quickTimeVideoDirectory);
    }

    private String getColorTableDescription(int i) {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != -1 ? intValue != 0 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Color table within file" : ((QuickTimeDirectory) this._directory).getInteger(9).intValue() < 16 ? "Default" : "None";
    }

    private String getDepthDescription(int i) {
        StringBuilder sb;
        String str;
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 34 || intValue == 36 || intValue == 40) {
            sb = new StringBuilder();
            sb.append(integer.intValue() - 32);
            str = "-bit grayscale";
        } else {
            sb = new StringBuilder();
            sb.append("Unknown (");
            sb.append(integer);
            str = ")";
        }
        sb.append(str);
        return sb.toString();
    }

    private String getGraphicsModeDescription() {
        Integer integer = ((QuickTimeDirectory) this._directory).getInteger(11);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 0) {
            return "Copy";
        }
        if (intValue == 32) {
            return "Blend";
        }
        if (intValue == 36) {
            return "Transparent";
        }
        if (intValue == 64) {
            return "Dither copy";
        }
        switch (intValue) {
            case 256:
                return "Straight alpha";
            case 257:
                return "Premul white alpha";
            case 258:
                return "Premul black alpha";
            case 259:
                return "Composition (dither copy)";
            case 260:
                return "Straight alpha blend";
            default:
                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
        }
    }

    private String getPixelDescription(int i) {
        String string = ((QuickTimeDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " pixels");
    }

    @Override // com.drew.metadata.mov.QuickTimeDescriptor, com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return (i == 4 || i == 5) ? getPixelDescription(i) : i != 9 ? i != 11 ? i != 13 ? super.getDescription(i) : getColorTableDescription(i) : getGraphicsModeDescription() : getDepthDescription(i);
    }
}
