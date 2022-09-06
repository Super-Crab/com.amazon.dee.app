package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
class TiffUtil {
    private static final Class<?> TAG = TiffUtil.class;
    public static final int TIFF_BYTE_ORDER_BIG_END = 1296891946;
    public static final int TIFF_BYTE_ORDER_LITTLE_END = 1229531648;
    public static final int TIFF_TAG_ORIENTATION = 274;
    public static final int TIFF_TYPE_SHORT = 3;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TiffHeader {
        int byteOrder;
        int firstIfdOffset;
        boolean isLittleEndian;

        private TiffHeader() {
        }
    }

    TiffUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int orientation) {
        if (orientation == 0 || orientation == 1) {
            return 0;
        }
        if (orientation == 3) {
            return 180;
        }
        if (orientation == 6) {
            return 90;
        }
        return orientation != 8 ? 0 : 270;
    }

    private static int getOrientationFromTiffEntry(InputStream is, int length, boolean isLittleEndian) throws IOException {
        if (length >= 10 && StreamProcessor.readPackedInt(is, 2, isLittleEndian) == 3 && StreamProcessor.readPackedInt(is, 4, isLittleEndian) == 1) {
            return StreamProcessor.readPackedInt(is, 2, isLittleEndian);
        }
        return 0;
    }

    private static int moveToTiffEntryWithTag(InputStream is, int length, boolean isLittleEndian, int tagToFind) throws IOException {
        if (length < 14) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(is, 2, isLittleEndian);
        int i = length - 2;
        while (true) {
            int i2 = readPackedInt - 1;
            if (readPackedInt <= 0 || i < 12) {
                break;
            }
            int i3 = i - 2;
            if (StreamProcessor.readPackedInt(is, 2, isLittleEndian) == tagToFind) {
                return i3;
            }
            is.skip(10L);
            i = i3 - 10;
            readPackedInt = i2;
        }
        return 0;
    }

    public static int readOrientationFromTIFF(InputStream is, int length) throws IOException {
        TiffHeader tiffHeader = new TiffHeader();
        int readTiffHeader = readTiffHeader(is, length, tiffHeader);
        int i = tiffHeader.firstIfdOffset - 8;
        if (readTiffHeader == 0 || i > readTiffHeader) {
            return 0;
        }
        is.skip(i);
        return getOrientationFromTiffEntry(is, moveToTiffEntryWithTag(is, readTiffHeader - i, tiffHeader.isLittleEndian, 274), tiffHeader.isLittleEndian);
    }

    private static int readTiffHeader(InputStream is, int length, TiffHeader tiffHeader) throws IOException {
        if (length <= 8) {
            return 0;
        }
        tiffHeader.byteOrder = StreamProcessor.readPackedInt(is, 4, false);
        int i = length - 4;
        int i2 = tiffHeader.byteOrder;
        if (i2 != 1229531648 && i2 != 1296891946) {
            FLog.e(TAG, "Invalid TIFF header");
            return 0;
        }
        tiffHeader.isLittleEndian = tiffHeader.byteOrder == 1229531648;
        tiffHeader.firstIfdOffset = StreamProcessor.readPackedInt(is, 4, tiffHeader.isLittleEndian);
        int i3 = i - 4;
        int i4 = tiffHeader.firstIfdOffset;
        if (i4 >= 8 && i4 - 8 <= i3) {
            return i3;
        }
        FLog.e(TAG, "Invalid offset");
        return 0;
    }
}
