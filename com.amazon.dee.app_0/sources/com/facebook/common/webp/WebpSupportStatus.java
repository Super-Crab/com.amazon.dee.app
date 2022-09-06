package com.facebook.common.webp;

import android.os.Build;
import com.drew.metadata.webp.WebpDirectory;
import java.io.UnsupportedEncodingException;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class WebpSupportStatus {
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    private static final String VP8X_WEBP_BASE64 = "UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==";
    private static final byte[] WEBP_NAME_BYTES;
    private static final byte[] WEBP_RIFF_BYTES;
    private static final byte[] WEBP_VP8L_BYTES;
    private static final byte[] WEBP_VP8X_BYTES;
    private static final byte[] WEBP_VP8_BYTES;
    public static final boolean sIsExtendedWebpSupported;
    public static final boolean sIsSimpleWebpSupported;
    public static final boolean sIsWebpSupportRequired;
    @Nullable
    public static WebpBitmapFactory sWebpBitmapFactory;
    private static boolean sWebpLibraryChecked;

    static {
        int i = Build.VERSION.SDK_INT;
        sIsWebpSupportRequired = false;
        sIsSimpleWebpSupported = true;
        sIsExtendedWebpSupported = isExtendedWebpSupported();
        sWebpBitmapFactory = null;
        sWebpLibraryChecked = false;
        WEBP_RIFF_BYTES = asciiBytes("RIFF");
        WEBP_NAME_BYTES = asciiBytes(WebpDirectory.FORMAT);
        WEBP_VP8_BYTES = asciiBytes(WebpDirectory.CHUNK_VP8);
        WEBP_VP8L_BYTES = asciiBytes(WebpDirectory.CHUNK_VP8L);
        WEBP_VP8X_BYTES = asciiBytes(WebpDirectory.CHUNK_VP8X);
    }

    private static byte[] asciiBytes(String value) {
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static boolean isAnimatedWebpHeader(final byte[] imageHeaderBytes, final int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES) && ((imageHeaderBytes[offset + 20] & 2) == 2);
    }

    public static boolean isExtendedWebpHeader(final byte[] imageHeaderBytes, final int offset, final int headerSize) {
        return headerSize >= 21 && matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES);
    }

    public static boolean isExtendedWebpHeaderWithAlpha(final byte[] imageHeaderBytes, final int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES) && ((imageHeaderBytes[offset + 20] & 16) == 16);
    }

    private static boolean isExtendedWebpSupported() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isLosslessWebpHeader(final byte[] imageHeaderBytes, final int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8L_BYTES);
    }

    public static boolean isSimpleWebpHeader(final byte[] imageHeaderBytes, final int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8_BYTES);
    }

    public static boolean isWebpHeader(final byte[] imageHeaderBytes, final int offset, final int headerSize) {
        return headerSize >= 20 && matchBytePattern(imageHeaderBytes, offset, WEBP_RIFF_BYTES) && matchBytePattern(imageHeaderBytes, offset + 8, WEBP_NAME_BYTES);
    }

    public static boolean isWebpSupportedByPlatform(final byte[] imageHeaderBytes, final int offset, final int headerSize) {
        if (isSimpleWebpHeader(imageHeaderBytes, offset)) {
            return sIsSimpleWebpSupported;
        }
        if (isLosslessWebpHeader(imageHeaderBytes, offset)) {
            return sIsExtendedWebpSupported;
        }
        if (isExtendedWebpHeader(imageHeaderBytes, offset, headerSize) && !isAnimatedWebpHeader(imageHeaderBytes, offset)) {
            return sIsExtendedWebpSupported;
        }
        return false;
    }

    @Nullable
    public static WebpBitmapFactory loadWebpBitmapFactoryIfExists() {
        if (sWebpLibraryChecked) {
            return sWebpBitmapFactory;
        }
        WebpBitmapFactory webpBitmapFactory = null;
        try {
            webpBitmapFactory = (WebpBitmapFactory) Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
        } catch (Throwable unused) {
        }
        sWebpLibraryChecked = true;
        return webpBitmapFactory;
    }

    private static boolean matchBytePattern(final byte[] byteArray, final int offset, final byte[] pattern) {
        if (pattern == null || byteArray == null || pattern.length + offset > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[i + offset] != pattern[i]) {
                return false;
            }
        }
        return true;
    }
}
