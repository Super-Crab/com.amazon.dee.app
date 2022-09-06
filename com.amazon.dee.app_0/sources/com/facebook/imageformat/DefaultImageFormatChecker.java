package com.facebook.imageformat;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.facebook.common.internal.Ints;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageformat.ImageFormat;
import com.google.common.base.Ascii;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DefaultImageFormatChecker implements ImageFormat.FormatChecker {
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final int GIF_HEADER_LENGTH = 6;
    private static final int HEIF_HEADER_LENGTH = 12;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    final int MAX_HEADER_LENGTH = Ints.max(21, 20, JPEG_HEADER_LENGTH, PNG_HEADER_LENGTH, 6, BMP_HEADER_LENGTH, ICO_HEADER_LENGTH, 12);
    private boolean mUseNewOrder = false;
    private static final byte[] JPEG_HEADER = {-1, -40, -1};
    private static final int JPEG_HEADER_LENGTH = JPEG_HEADER.length;
    private static final byte[] PNG_HEADER = {-119, 80, 78, 71, 13, 10, Ascii.SUB, 10};
    private static final int PNG_HEADER_LENGTH = PNG_HEADER.length;
    private static final byte[] GIF_HEADER_87A = ImageFormatCheckerUtils.asciiBytes("GIF87a");
    private static final byte[] GIF_HEADER_89A = ImageFormatCheckerUtils.asciiBytes("GIF89a");
    private static final byte[] BMP_HEADER = ImageFormatCheckerUtils.asciiBytes("BM");
    private static final int BMP_HEADER_LENGTH = BMP_HEADER.length;
    private static final byte[] ICO_HEADER = {0, 0, 1, 0};
    private static final int ICO_HEADER_LENGTH = ICO_HEADER.length;
    private static final byte[] HEIF_HEADER_PREFIX = ImageFormatCheckerUtils.asciiBytes("ftyp");
    private static final byte[][] HEIF_HEADER_SUFFIXES = {ImageFormatCheckerUtils.asciiBytes("heic"), ImageFormatCheckerUtils.asciiBytes("heix"), ImageFormatCheckerUtils.asciiBytes("hevc"), ImageFormatCheckerUtils.asciiBytes("hevx"), ImageFormatCheckerUtils.asciiBytes("mif1"), ImageFormatCheckerUtils.asciiBytes("msf1")};
    private static final byte[] DNG_HEADER_II = {73, 73, GenericAccessProfile.MESH_MESSAGE, 0};
    private static final byte[] DNG_HEADER_MM = {77, 77, 0, GenericAccessProfile.MESH_MESSAGE};
    private static final int DNG_HEADER_LENGTH = DNG_HEADER_II.length;

    private static ImageFormat getWebpFormat(final byte[] imageHeaderBytes, final int headerSize) {
        Preconditions.checkArgument(Boolean.valueOf(WebpSupportStatus.isWebpHeader(imageHeaderBytes, 0, headerSize)));
        if (WebpSupportStatus.isSimpleWebpHeader(imageHeaderBytes, 0)) {
            return DefaultImageFormats.WEBP_SIMPLE;
        }
        if (WebpSupportStatus.isLosslessWebpHeader(imageHeaderBytes, 0)) {
            return DefaultImageFormats.WEBP_LOSSLESS;
        }
        if (WebpSupportStatus.isExtendedWebpHeader(imageHeaderBytes, 0, headerSize)) {
            if (WebpSupportStatus.isAnimatedWebpHeader(imageHeaderBytes, 0)) {
                return DefaultImageFormats.WEBP_ANIMATED;
            }
            if (WebpSupportStatus.isExtendedWebpHeaderWithAlpha(imageHeaderBytes, 0)) {
                return DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA;
            }
            return DefaultImageFormats.WEBP_EXTENDED;
        }
        return ImageFormat.UNKNOWN;
    }

    private static boolean isBmpHeader(final byte[] imageHeaderBytes, final int headerSize) {
        byte[] bArr = BMP_HEADER;
        if (headerSize < bArr.length) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, bArr);
    }

    private static boolean isDngHeader(final byte[] imageHeaderBytes, final int headerSize) {
        return headerSize >= DNG_HEADER_LENGTH && (ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, DNG_HEADER_II) || ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, DNG_HEADER_MM));
    }

    private static boolean isGifHeader(final byte[] imageHeaderBytes, final int headerSize) {
        if (headerSize < 6) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, GIF_HEADER_87A) || ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, GIF_HEADER_89A);
    }

    private static boolean isHeifHeader(final byte[] imageHeaderBytes, final int headerSize) {
        if (headerSize >= 12 && imageHeaderBytes[3] >= 8 && ImageFormatCheckerUtils.hasPatternAt(imageHeaderBytes, HEIF_HEADER_PREFIX, 4)) {
            for (byte[] bArr : HEIF_HEADER_SUFFIXES) {
                if (ImageFormatCheckerUtils.hasPatternAt(imageHeaderBytes, bArr, 8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean isIcoHeader(final byte[] imageHeaderBytes, final int headerSize) {
        byte[] bArr = ICO_HEADER;
        if (headerSize < bArr.length) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, bArr);
    }

    private static boolean isJpegHeader(final byte[] imageHeaderBytes, final int headerSize) {
        byte[] bArr = JPEG_HEADER;
        return headerSize >= bArr.length && ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, bArr);
    }

    private static boolean isPngHeader(final byte[] imageHeaderBytes, final int headerSize) {
        byte[] bArr = PNG_HEADER;
        return headerSize >= bArr.length && ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes, bArr);
    }

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    @Nullable
    public final ImageFormat determineFormat(byte[] headerBytes, int headerSize) {
        Preconditions.checkNotNull(headerBytes);
        if (!this.mUseNewOrder && WebpSupportStatus.isWebpHeader(headerBytes, 0, headerSize)) {
            return getWebpFormat(headerBytes, headerSize);
        }
        if (isJpegHeader(headerBytes, headerSize)) {
            return DefaultImageFormats.JPEG;
        }
        if (isPngHeader(headerBytes, headerSize)) {
            return DefaultImageFormats.PNG;
        }
        if (this.mUseNewOrder && WebpSupportStatus.isWebpHeader(headerBytes, 0, headerSize)) {
            return getWebpFormat(headerBytes, headerSize);
        }
        if (isGifHeader(headerBytes, headerSize)) {
            return DefaultImageFormats.GIF;
        }
        if (isBmpHeader(headerBytes, headerSize)) {
            return DefaultImageFormats.BMP;
        }
        if (isIcoHeader(headerBytes, headerSize)) {
            return DefaultImageFormats.ICO;
        }
        if (isHeifHeader(headerBytes, headerSize)) {
            return DefaultImageFormats.HEIF;
        }
        if (isDngHeader(headerBytes, headerSize)) {
            return DefaultImageFormats.DNG;
        }
        return ImageFormat.UNKNOWN;
    }

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    public int getHeaderSize() {
        return this.MAX_HEADER_LENGTH;
    }

    public void setUseNewOrder(boolean useNewOrder) {
        this.mUseNewOrder = useNewOrder;
    }
}
