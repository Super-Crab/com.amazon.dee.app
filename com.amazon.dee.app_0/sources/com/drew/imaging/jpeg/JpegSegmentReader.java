package com.drew.imaging.jpeg;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
/* loaded from: classes2.dex */
public class JpegSegmentReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte MARKER_EOI = -39;
    private static final byte SEGMENT_IDENTIFIER = -1;
    private static final byte SEGMENT_SOS = -38;

    private JpegSegmentReader() throws Exception {
        throw new Exception("Not intended for instantiation.");
    }

    @NotNull
    public static JpegSegmentData readSegments(@NotNull SequentialReader sequentialReader, @Nullable Iterable<JpegSegmentType> iterable) throws JpegProcessingException, IOException {
        int uInt16 = sequentialReader.getUInt16();
        if (uInt16 == 65496) {
            HashSet hashSet = null;
            if (iterable != null) {
                hashSet = new HashSet();
                for (JpegSegmentType jpegSegmentType : iterable) {
                    hashSet.add(Byte.valueOf(jpegSegmentType.byteValue));
                }
            }
            HashSet hashSet2 = hashSet;
            JpegSegmentData jpegSegmentData = new JpegSegmentData();
            while (true) {
                byte int8 = sequentialReader.getInt8();
                byte int82 = sequentialReader.getInt8();
                while (true) {
                    if (int8 == -1 && int82 != -1 && int82 != 0) {
                        break;
                    }
                    byte b = int82;
                    int82 = sequentialReader.getInt8();
                    int8 = b;
                }
                if (int82 == -38 || int82 == -39) {
                    return jpegSegmentData;
                }
                int uInt162 = sequentialReader.getUInt16() - 2;
                if (uInt162 < 0) {
                    throw new JpegProcessingException("JPEG segment size would be less than zero");
                }
                if (hashSet2 == null || hashSet2.contains(Byte.valueOf(int82))) {
                    jpegSegmentData.addSegment(int82, sequentialReader.getBytes(uInt162));
                } else if (!sequentialReader.trySkip(uInt162)) {
                    return jpegSegmentData;
                }
            }
        } else {
            throw new JpegProcessingException(GeneratedOutlineSupport1.outline32(uInt16, GeneratedOutlineSupport1.outline107("JPEG data is expected to begin with 0xFFD8 (Ã¿Ã\u0098) not 0x")));
        }
    }

    @NotNull
    public static JpegSegmentData readSegments(@NotNull File file, @Nullable Iterable<JpegSegmentType> iterable) throws JpegProcessingException, IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                JpegSegmentData readSegments = readSegments(new StreamReader(fileInputStream), iterable);
                fileInputStream.close();
                return readSegments;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }
}
