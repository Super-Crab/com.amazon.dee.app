package com.amazon.communication;

import java.nio.ByteBuffer;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes12.dex */
public final class RFC1071LikeDigest {
    private RFC1071LikeDigest() {
    }

    private static void checkMessageInput(ByteBuffer[] byteBufferArr) {
        if (byteBufferArr == null || byteBufferArr.length == 0) {
            throw new IllegalArgumentException("Message should not be null.");
        }
    }

    public static int computeDigest(ByteBuffer[] byteBufferArr) {
        checkMessageInput(byteBufferArr);
        return generateDigest(byteBufferArr, -1, -1);
    }

    private static int generateDigest(ByteBuffer[] byteBufferArr, int i, int i2) {
        byte[] bArr;
        int i3;
        long j = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < byteBufferArr.length) {
            int i6 = i4 + 1;
            ByteBuffer byteBuffer = byteBufferArr[i4];
            int remaining = byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                bArr = byteBuffer.array();
                i3 = byteBuffer.position() + byteBuffer.arrayOffset();
            } else {
                bArr = new byte[byteBuffer.remaining()];
                byteBuffer.mark();
                byteBuffer.get(bArr);
                byteBuffer.reset();
                i3 = 0;
            }
            int i7 = i5;
            long j2 = j;
            int i8 = 0;
            while (i8 < remaining) {
                if (i7 >= i2 || i7 < i) {
                    j2 += (bArr[i8 + i3] & 255) << (((i7 & 3) ^ 3) << 3);
                }
                i8++;
                i7++;
            }
            j = j2;
            i4 = i6;
            i5 = i7;
        }
        long j3 = j >> 32;
        while (true) {
            long j4 = j & BodyPartID.bodyIdMax;
            if (j3 != 0) {
                j = j3 + j4;
                j3 = j >> 32;
            } else {
                return (int) j;
            }
        }
    }

    private static void checkMessageInput(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            return;
        }
        throw new IllegalArgumentException("Message should not be null.");
    }

    public static int computeDigest(ByteBuffer byteBuffer) {
        return computeDigest(byteBuffer, -1, -1);
    }

    public static int computeDigest(ByteBuffer[] byteBufferArr, int i, int i2) {
        checkMessageInput(byteBufferArr);
        if (i2 >= i) {
            return generateDigest(byteBufferArr, i, i2);
        }
        throw new IllegalArgumentException("End index is smaller than start index.");
    }

    public static int computeDigest(ByteBuffer byteBuffer, int i, int i2) {
        checkMessageInput(byteBuffer);
        if (i2 >= i) {
            return generateDigest(new ByteBuffer[]{byteBuffer}, i, i2);
        }
        throw new IllegalArgumentException("End index is smaller than start index.");
    }
}
