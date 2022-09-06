package com.drew.metadata.adobe;

import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.here.sdk.search.PlaceCategory;
/* loaded from: classes2.dex */
public class AdobeJpegDescriptor extends TagDescriptor<AdobeJpegDirectory> {
    public AdobeJpegDescriptor(AdobeJpegDirectory adobeJpegDirectory) {
        super(adobeJpegDirectory);
    }

    @Nullable
    private String getColorTransformDescription() {
        return getIndexedDescription(3, "Unknown (RGB or CMYK)", "YCbCr", "YCCK");
    }

    @Nullable
    private String getDctEncodeVersionDescription() {
        Integer integer = ((AdobeJpegDirectory) this._directory).getInteger(0);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 100 ? PlaceCategory.EAT_AND_DRINK : Integer.toString(integer.intValue());
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 0 ? i != 3 ? super.getDescription(i) : getColorTransformDescription() : getDctEncodeVersionDescription();
    }
}
