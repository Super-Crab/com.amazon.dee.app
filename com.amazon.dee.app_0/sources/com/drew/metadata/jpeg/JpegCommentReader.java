package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import java.util.Collections;
/* loaded from: classes2.dex */
public class JpegCommentReader implements JpegSegmentMetadataReader {
    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.COM);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            JpegCommentDirectory jpegCommentDirectory = new JpegCommentDirectory();
            metadata.addDirectory(jpegCommentDirectory);
            jpegCommentDirectory.setStringValue(0, new StringValue(bArr, null));
        }
    }
}
