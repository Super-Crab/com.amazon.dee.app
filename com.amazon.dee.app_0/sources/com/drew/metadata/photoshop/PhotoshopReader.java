package com.drew.metadata.photoshop;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.xmp.XmpReader;
import java.util.Arrays;
import java.util.Collections;
/* loaded from: classes2.dex */
public class PhotoshopReader implements JpegSegmentMetadataReader {
    @NotNull
    private static final String JPEG_SEGMENT_PREAMBLE = "Photoshop 3.0";

    public void extract(@NotNull SequentialReader sequentialReader, int i, @NotNull Metadata metadata) {
        int i2;
        PhotoshopDirectory photoshopDirectory = new PhotoshopDirectory();
        metadata.addDirectory(photoshopDirectory);
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            try {
                String string = sequentialReader.getString(4);
                int uInt16 = sequentialReader.getUInt16();
                short uInt8 = sequentialReader.getUInt8();
                int i5 = i3 + 4 + 2 + 1;
                if (uInt8 < 0 || (i2 = uInt8 + i5) > i) {
                    throw new ImageProcessingException("Invalid string length");
                }
                StringBuilder sb = new StringBuilder();
                short s = (short) i2;
                while (i5 < s) {
                    sb.append((char) sequentialReader.getUInt8());
                    i5++;
                }
                if (i5 % 2 != 0) {
                    sequentialReader.skip(1L);
                    i5++;
                }
                int int32 = sequentialReader.getInt32();
                byte[] bytes = sequentialReader.getBytes(int32);
                int i6 = i5 + 4 + int32;
                if (i6 % 2 != 0) {
                    sequentialReader.skip(1L);
                    i6++;
                }
                int i7 = i6;
                if (string.equals("8BIM")) {
                    if (uInt16 == 1028) {
                        new IptcReader().extract(new SequentialByteArrayReader(bytes), metadata, bytes.length, photoshopDirectory);
                    } else if (uInt16 == 1039) {
                        new IccReader().extract(new ByteArrayReader(bytes), metadata, photoshopDirectory);
                    } else {
                        if (uInt16 != 1058 && uInt16 != 1059) {
                            if (uInt16 == 1060) {
                                new XmpReader().extract(bytes, metadata, photoshopDirectory);
                            } else if (uInt16 < 2000 || uInt16 > 2998) {
                                photoshopDirectory.setByteArray(uInt16, bytes);
                            } else {
                                i4++;
                                byte[] copyOf = Arrays.copyOf(bytes, bytes.length + sb.length() + 1);
                                for (int length = (copyOf.length - sb.length()) - 1; length < copyOf.length; length++) {
                                    if (length % (((copyOf.length - sb.length()) - 1) + sb.length()) == 0) {
                                        copyOf[length] = (byte) sb.length();
                                    } else {
                                        copyOf[length] = (byte) sb.charAt(length - ((copyOf.length - sb.length()) - 1));
                                    }
                                }
                                int i8 = i4 + 1999;
                                PhotoshopDirectory._tagNameMap.put(Integer.valueOf(i8), "Path Info " + i4);
                                photoshopDirectory.setByteArray(i8, copyOf);
                            }
                        }
                        new ExifReader().extract(new ByteArrayReader(bytes), metadata, 0, photoshopDirectory);
                    }
                    if (uInt16 >= 4000 && uInt16 <= 4999) {
                        PhotoshopDirectory._tagNameMap.put(Integer.valueOf(uInt16), String.format("Plug-in %d Data", Integer.valueOf((uInt16 - 4000) + 1)));
                    }
                }
                i3 = i7;
            } catch (Exception e) {
                photoshopDirectory.addError(e.getMessage());
                return;
            }
        }
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APPD);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            if (bArr.length >= 14 && JPEG_SEGMENT_PREAMBLE.equals(new String(bArr, 0, 13))) {
                extract(new SequentialByteArrayReader(bArr, 14), (bArr.length - 13) - 1, metadata);
            }
        }
    }
}
