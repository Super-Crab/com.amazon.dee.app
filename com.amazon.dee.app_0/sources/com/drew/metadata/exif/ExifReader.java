package com.drew.metadata.exif;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;
/* loaded from: classes2.dex */
public class ExifReader implements JpegSegmentMetadataReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String JPEG_SEGMENT_PREAMBLE = "Exif\u0000\u0000";

    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata) {
        extract(randomAccessReader, metadata, 0);
    }

    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, int i) {
        extract(randomAccessReader, metadata, i, null);
    }

    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, int i, @Nullable Directory directory) {
        ExifTiffHandler exifTiffHandler = new ExifTiffHandler(metadata, directory);
        try {
            new TiffReader().processTiff(randomAccessReader, exifTiffHandler, i);
        } catch (TiffProcessingException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception processing TIFF data: ");
            outline107.append(e.getMessage());
            exifTiffHandler.error(outline107.toString());
            e.printStackTrace(System.err);
        } catch (IOException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exception processing TIFF data: ");
            outline1072.append(e2.getMessage());
            exifTiffHandler.error(outline1072.toString());
            e2.printStackTrace(System.err);
        }
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APP1);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            if (bArr.length >= 6 && new String(bArr, 0, 6).equals(JPEG_SEGMENT_PREAMBLE)) {
                extract(new ByteArrayReader(bArr), metadata, 6);
            }
        }
    }
}
