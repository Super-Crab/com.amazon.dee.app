package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class JpegCommentDescriptor extends TagDescriptor<JpegCommentDirectory> {
    public JpegCommentDescriptor(@NotNull JpegCommentDirectory jpegCommentDirectory) {
        super(jpegCommentDirectory);
    }

    @Nullable
    public String getJpegCommentDescription() {
        return ((JpegCommentDirectory) this._directory).getString(0);
    }
}
