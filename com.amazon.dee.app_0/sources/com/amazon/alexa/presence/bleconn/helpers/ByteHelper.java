package com.amazon.alexa.presence.bleconn.helpers;

import androidx.exifinterface.media.ExifInterface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public final class ByteHelper {
    private static final Map<String, String> HEX_TO_BINARY_MAP = new HashMap<String, String>() { // from class: com.amazon.alexa.presence.bleconn.helpers.ByteHelper.1
        {
            put("0", "0000");
            put("1", "0001");
            put("2", "0010");
            put("3", "0011");
            put("4", "0100");
            put("5", "0101");
            put("6", "0110");
            put("7", "0111");
            put("8", "1000");
            put("9", "1001");
            put(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "1010");
            put("B", "1011");
            put("C", "1100");
            put("D", "1101");
            put(ExifInterface.LONGITUDE_EAST, "1110");
            put("F", "1111");
        }
    };

    private ByteHelper() {
    }

    public static byte[] append(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static String asBinary(byte b) {
        return String.format("%s%s", HEX_TO_BINARY_MAP.get(leadingHex(b)), HEX_TO_BINARY_MAP.get(trailingHex(b)));
    }

    public static byte[] asByteArray(int... iArr) {
        byte[] bArr = new byte[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = (byte) iArr[i];
        }
        return bArr;
    }

    public static String asHex(byte b) {
        return String.format("0x%s%s", leadingHex(b), trailingHex(b));
    }

    public static byte[] fromBoxedList(List<Byte> list) {
        byte[] bArr = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bArr[i] = list.get(i).byteValue();
        }
        return bArr;
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) (Character.digit(str.charAt(i + 1), 16) + (Character.digit(str.charAt(i), 16) << 4));
        }
        return bArr;
    }

    private static String leadingHex(byte b) {
        return Character.toString(Character.forDigit((b >>> 4) & 15, 16)).toUpperCase();
    }

    public static byte[] slice(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length - i);
        byte[] bArr2 = new byte[min];
        for (int i3 = 0; i3 < min; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return bArr2;
    }

    public static byte[] sliceToEnd(byte[] bArr, int i) {
        return slice(bArr, i, bArr.length - i);
    }

    public static List<Byte> toBoxedList(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            arrayList.add(new Byte(b));
        }
        return arrayList;
    }

    public static byte[] toIntegersToByteArray(int... iArr) {
        ByteBuffer allocate = ByteBuffer.allocate(iArr.length * 4);
        for (int i : iArr) {
            allocate.putInt(i);
        }
        return allocate.array();
    }

    private static String trailingHex(byte b) {
        return Character.toString(Character.forDigit(b & 15, 16)).toUpperCase();
    }

    public static String asHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(asHex(bArr[i]));
            if (i < bArr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String asBinary(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(asBinary(bArr[i]));
            if (i < bArr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
