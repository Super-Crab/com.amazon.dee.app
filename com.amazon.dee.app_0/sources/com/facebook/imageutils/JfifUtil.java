package com.facebook.imageutils;

import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes2.dex */
public class JfifUtil {
    public static final int APP1_EXIF_MAGIC = 1165519206;
    public static final int MARKER_APP1 = 225;
    public static final int MARKER_EOI = 217;
    public static final int MARKER_ESCAPE_BYTE = 0;
    public static final int MARKER_FIRST_BYTE = 255;
    public static final int MARKER_RST0 = 208;
    public static final int MARKER_RST7 = 215;
    public static final int MARKER_SOFn = 192;
    public static final int MARKER_SOI = 216;
    public static final int MARKER_SOS = 218;
    public static final int MARKER_TEM = 1;

    private JfifUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int orientation) {
        return TiffUtil.getAutoRotateAngleFromOrientation(orientation);
    }

    public static int getOrientation(byte[] jpeg) {
        return getOrientation(new ByteArrayInputStream(jpeg));
    }

    private static boolean isSOFn(int marker) {
        switch (marker) {
            case 192:
            case 193:
            case 194:
            case 195:
            case 197:
            case Opcodes.IFNULL /* 198 */:
            case 199:
            case 201:
            case 202:
            case 203:
            case 205:
            case HttpServletResponse.SC_PARTIAL_CONTENT /* 206 */:
            case 207:
                return true;
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
            case 200:
            case 204:
            default:
                return false;
        }
    }

    private static int moveToAPP1EXIF(InputStream is) throws IOException {
        int readPackedInt;
        if (moveToMarker(is, 225) && (readPackedInt = StreamProcessor.readPackedInt(is, 2, false) - 2) > 6) {
            int readPackedInt2 = StreamProcessor.readPackedInt(is, 4, false);
            int readPackedInt3 = StreamProcessor.readPackedInt(is, 2, false);
            int i = (readPackedInt - 4) - 2;
            if (readPackedInt2 == 1165519206 && readPackedInt3 == 0) {
                return i;
            }
        }
        return 0;
    }

    public static boolean moveToMarker(InputStream is, int markerToFind) throws IOException {
        Preconditions.checkNotNull(is);
        while (StreamProcessor.readPackedInt(is, 1, false) == 255) {
            int i = 255;
            while (i == 255) {
                i = StreamProcessor.readPackedInt(is, 1, false);
            }
            if ((markerToFind == 192 && isSOFn(i)) || i == markerToFind) {
                return true;
            }
            if (i != 216 && i != 1) {
                if (i == 217 || i == 218) {
                    break;
                }
                is.skip(StreamProcessor.readPackedInt(is, 2, false) - 2);
            }
        }
        return false;
    }

    public static int getOrientation(InputStream is) {
        try {
            int moveToAPP1EXIF = moveToAPP1EXIF(is);
            if (moveToAPP1EXIF != 0) {
                return TiffUtil.readOrientationFromTIFF(is, moveToAPP1EXIF);
            }
            return 0;
        } catch (IOException unused) {
            return 0;
        }
    }
}
