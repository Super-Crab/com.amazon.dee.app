package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class KyoceraMakernoteDescriptor extends TagDescriptor<KyoceraMakernoteDirectory> {
    public KyoceraMakernoteDescriptor(@NotNull KyoceraMakernoteDirectory kyoceraMakernoteDirectory) {
        super(kyoceraMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 1 ? super.getDescription(i) : getProprietaryThumbnailDataDescription();
    }

    @Nullable
    public String getProprietaryThumbnailDataDescription() {
        return getByteLengthDescription(1);
    }
}
