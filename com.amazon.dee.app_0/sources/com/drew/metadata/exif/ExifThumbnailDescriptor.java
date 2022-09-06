package com.drew.metadata.exif;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
/* loaded from: classes2.dex */
public class ExifThumbnailDescriptor extends ExifDescriptorBase<ExifThumbnailDirectory> {
    public ExifThumbnailDescriptor(@NotNull ExifThumbnailDirectory exifThumbnailDirectory) {
        super(exifThumbnailDirectory);
    }

    @Override // com.drew.metadata.exif.ExifDescriptorBase, com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 513 ? i != 514 ? super.getDescription(i) : getThumbnailLengthDescription() : getThumbnailOffsetDescription();
    }

    @Nullable
    public String getThumbnailLengthDescription() {
        String string = ((ExifThumbnailDirectory) this._directory).getString(514);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " bytes");
    }

    @Nullable
    public String getThumbnailOffsetDescription() {
        String string = ((ExifThumbnailDirectory) this._directory).getString(513);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " bytes");
    }
}
