package com.drew.metadata.ico;

import com.amazon.alexa.redesign.utils.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class IcoDescriptor extends TagDescriptor<IcoDirectory> {
    public IcoDescriptor(@NotNull IcoDirectory icoDirectory) {
        super(icoDirectory);
    }

    @Nullable
    public String getColourPaletteSizeDescription() {
        Integer integer = ((IcoDirectory) this._directory).getInteger(4);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "No palette";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(integer);
        sb.append(" colour");
        sb.append(integer.intValue() == 1 ? "" : "s");
        return sb.toString();
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? super.getDescription(i) : getColourPaletteSizeDescription() : getImageHeightDescription() : getImageWidthDescription() : getImageTypeDescription();
    }

    @Nullable
    public String getImageHeightDescription() {
        Integer integer = ((IcoDirectory) this._directory).getInteger(3);
        if (integer == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline86(new StringBuilder(), integer.intValue() == 0 ? 256 : integer.intValue(), " pixels");
    }

    @Nullable
    public String getImageTypeDescription() {
        return getIndexedDescription(1, 1, Constants.IMAGE_TYPE_ICON, "Cursor");
    }

    @Nullable
    public String getImageWidthDescription() {
        Integer integer = ((IcoDirectory) this._directory).getInteger(2);
        if (integer == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline86(new StringBuilder(), integer.intValue() == 0 ? 256 : integer.intValue(), " pixels");
    }
}
