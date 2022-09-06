package com.drew.metadata.photoshop;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Registry;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class PsdHeaderDescriptor extends TagDescriptor<PsdHeaderDirectory> {
    public PsdHeaderDescriptor(@NotNull PsdHeaderDirectory psdHeaderDirectory) {
        super(psdHeaderDirectory);
    }

    @Nullable
    public String getBitsPerChannelDescription() {
        Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(4);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(integer);
        sb.append(" bit");
        return GeneratedOutlineSupport1.outline91(sb, integer.intValue() == 1 ? "" : "s", " per channel");
    }

    @Nullable
    public String getChannelCountDescription() {
        Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(1);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(integer);
        sb.append(" channel");
        sb.append(integer.intValue() == 1 ? "" : "s");
        return sb.toString();
    }

    @Nullable
    public String getColorModeDescription() {
        return getIndexedDescription(5, Registry.BUCKET_BITMAP, "Grayscale", "Indexed", "RGB", "CMYK", null, null, "Multichannel", "Duotone", "Lab");
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? super.getDescription(i) : getColorModeDescription() : getBitsPerChannelDescription() : getImageWidthDescription() : getImageHeightDescription() : getChannelCountDescription();
    }

    @Nullable
    public String getImageHeightDescription() {
        Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(2);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(integer);
        sb.append(" pixel");
        sb.append(integer.intValue() == 1 ? "" : "s");
        return sb.toString();
    }

    @Nullable
    public String getImageWidthDescription() {
        try {
            Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(3);
            if (integer == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(integer);
            sb.append(" pixel");
            sb.append(integer.intValue() == 1 ? "" : "s");
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
