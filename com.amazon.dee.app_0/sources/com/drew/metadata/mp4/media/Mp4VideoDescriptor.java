package com.drew.metadata.mp4.media;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class Mp4VideoDescriptor extends TagDescriptor<Mp4VideoDirectory> {
    public Mp4VideoDescriptor(@NotNull Mp4VideoDirectory mp4VideoDirectory) {
        super(mp4VideoDirectory);
    }

    private String getColorTableDescription() {
        Integer integer = ((Mp4VideoDirectory) this._directory).getInteger(113);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue != -1) {
            return intValue != 0 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Color table within file";
        }
        Integer integer2 = ((Mp4VideoDirectory) this._directory).getInteger(109);
        return (integer2 != null && integer2.intValue() < 16) ? "Default" : "None";
    }

    private String getDepthDescription() {
        StringBuilder sb;
        String str;
        Integer integer = ((Mp4VideoDirectory) this._directory).getInteger(109);
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
        Integer integer = ((Mp4VideoDirectory) this._directory).getInteger(111);
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
        String string = ((Mp4VideoDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " pixels");
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return (i == 104 || i == 105) ? getPixelDescription(i) : i != 109 ? i != 111 ? i != 113 ? super.getDescription(i) : getColorTableDescription() : getGraphicsModeDescription() : getDepthDescription();
    }
}
