package com.drew.metadata.icc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.DateUtil;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import java.io.IOException;
import java.util.Collections;
/* loaded from: classes2.dex */
public class IccReader implements JpegSegmentMetadataReader, MetadataReader {
    public static final String JPEG_SEGMENT_PREAMBLE = "ICC_PROFILE";

    @NotNull
    public static String getStringFromInt32(int i) {
        return new String(new byte[]{(byte) (((-16777216) & i) >> 24), (byte) ((16711680 & i) >> 16), (byte) ((65280 & i) >> 8), (byte) (i & 255)});
    }

    private void set4ByteString(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        int int32 = randomAccessReader.getInt32(i);
        if (int32 != 0) {
            directory.setString(i, getStringFromInt32(int32));
        }
    }

    private void setDate(@NotNull IccDirectory iccDirectory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        int uInt16 = randomAccessReader.getUInt16(i);
        int uInt162 = randomAccessReader.getUInt16(i + 2);
        int uInt163 = randomAccessReader.getUInt16(i + 4);
        int uInt164 = randomAccessReader.getUInt16(i + 6);
        int uInt165 = randomAccessReader.getUInt16(i + 8);
        int uInt166 = randomAccessReader.getUInt16(i + 10);
        if (!DateUtil.isValidDate(uInt16, uInt162 - 1, uInt163) || !DateUtil.isValidTime(uInt164, uInt165, uInt166)) {
            iccDirectory.addError(String.format("ICC data describes an invalid date/time: year=%d month=%d day=%d hour=%d minute=%d second=%d", Integer.valueOf(uInt16), Integer.valueOf(uInt162), Integer.valueOf(uInt163), Integer.valueOf(uInt164), Integer.valueOf(uInt165), Integer.valueOf(uInt166)));
        } else {
            iccDirectory.setString(i, String.format("%04d:%02d:%02d %02d:%02d:%02d", Integer.valueOf(uInt16), Integer.valueOf(uInt162), Integer.valueOf(uInt163), Integer.valueOf(uInt164), Integer.valueOf(uInt165), Integer.valueOf(uInt166)));
        }
    }

    private void setInt32(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        int int32 = randomAccessReader.getInt32(i);
        if (int32 != 0) {
            directory.setInt(i, int32);
        }
    }

    private void setInt64(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        long int64 = randomAccessReader.getInt64(i);
        if (int64 != 0) {
            directory.setLong(i, int64);
        }
    }

    @Override // com.drew.metadata.MetadataReader
    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata) {
        extract(randomAccessReader, metadata, null);
    }

    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, @Nullable Directory directory) {
        IccDirectory iccDirectory = new IccDirectory();
        if (directory != null) {
            iccDirectory.setParent(directory);
        }
        try {
            iccDirectory.setInt(0, randomAccessReader.getInt32(0));
            set4ByteString(iccDirectory, 4, randomAccessReader);
            setInt32(iccDirectory, 8, randomAccessReader);
            set4ByteString(iccDirectory, 12, randomAccessReader);
            set4ByteString(iccDirectory, 16, randomAccessReader);
            set4ByteString(iccDirectory, 20, randomAccessReader);
            setDate(iccDirectory, 24, randomAccessReader);
            set4ByteString(iccDirectory, 36, randomAccessReader);
            set4ByteString(iccDirectory, 40, randomAccessReader);
            setInt32(iccDirectory, 44, randomAccessReader);
            set4ByteString(iccDirectory, 48, randomAccessReader);
            int int32 = randomAccessReader.getInt32(52);
            if (int32 != 0) {
                if (int32 <= 538976288) {
                    iccDirectory.setInt(52, int32);
                } else {
                    iccDirectory.setString(52, getStringFromInt32(int32));
                }
            }
            setInt32(iccDirectory, 64, randomAccessReader);
            setInt64(iccDirectory, 56, randomAccessReader);
            iccDirectory.setObject(68, new float[]{randomAccessReader.getS15Fixed16(68), randomAccessReader.getS15Fixed16(72), randomAccessReader.getS15Fixed16(76)});
            int int322 = randomAccessReader.getInt32(128);
            iccDirectory.setInt(128, int322);
            for (int i = 0; i < int322; i++) {
                int i2 = (i * 12) + 132;
                iccDirectory.setByteArray(randomAccessReader.getInt32(i2), randomAccessReader.getBytes(randomAccessReader.getInt32(i2 + 4), randomAccessReader.getInt32(i2 + 8)));
            }
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception reading ICC profile: ");
            outline107.append(e.getMessage());
            iccDirectory.addError(outline107.toString());
        }
        metadata.addDirectory(iccDirectory);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APP2);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        byte[] bArr = null;
        for (byte[] bArr2 : iterable) {
            if (bArr2.length >= 11 && JPEG_SEGMENT_PREAMBLE.equalsIgnoreCase(new String(bArr2, 0, 11))) {
                if (bArr == null) {
                    bArr = new byte[bArr2.length - 14];
                    System.arraycopy(bArr2, 14, bArr, 0, bArr2.length - 14);
                } else {
                    byte[] bArr3 = new byte[(bArr.length + bArr2.length) - 14];
                    System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                    System.arraycopy(bArr2, 14, bArr3, bArr.length, bArr2.length - 14);
                    bArr = bArr3;
                }
            }
        }
        if (bArr != null) {
            extract(new ByteArrayReader(bArr), metadata);
        }
    }
}
