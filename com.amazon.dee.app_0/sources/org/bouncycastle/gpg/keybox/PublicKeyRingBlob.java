package org.bouncycastle.gpg.keybox;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
/* loaded from: classes4.dex */
public class PublicKeyRingBlob extends KeyBlob {
    private final KeyFingerPrintCalculator fingerPrintCalculator;

    private PublicKeyRingBlob(int i, long j, BlobType blobType, int i2, int i3, int i4, List<KeyInformation> list, byte[] bArr, int i5, List<UserID> list2, int i6, List<Long> list3, int i7, int i8, long j2, long j3, long j4, byte[] bArr2, byte[] bArr3, byte[] bArr4, KeyFingerPrintCalculator keyFingerPrintCalculator) {
        super(i, j, blobType, i2, i3, i4, list, bArr, i5, list2, i6, list3, i7, i8, j2, j3, j4, bArr2, bArr3, bArr4);
        this.fingerPrintCalculator = keyFingerPrintCalculator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Blob parseContent(int i, long j, BlobType blobType, int i2, KeyBoxByteBuffer keyBoxByteBuffer, KeyFingerPrintCalculator keyFingerPrintCalculator, BlobVerifier blobVerifier) throws IOException {
        KeyBlob.verifyDigest(i, j, keyBoxByteBuffer, blobVerifier);
        int u16 = keyBoxByteBuffer.u16();
        long u32 = keyBoxByteBuffer.u32();
        long u322 = keyBoxByteBuffer.u32();
        int u162 = keyBoxByteBuffer.u16();
        int u163 = keyBoxByteBuffer.u16();
        ArrayList arrayList = new ArrayList();
        for (int i3 = u162 - 1; i3 >= 0; i3--) {
            arrayList.add(KeyInformation.getInstance(keyBoxByteBuffer, u163, i));
        }
        byte[] bN = keyBoxByteBuffer.bN(keyBoxByteBuffer.u16());
        int u164 = keyBoxByteBuffer.u16();
        keyBoxByteBuffer.u16();
        ArrayList arrayList2 = new ArrayList();
        for (int i4 = u164 - 1; i4 >= 0; i4--) {
            arrayList2.add(UserID.getInstance(keyBoxByteBuffer, i));
        }
        int u165 = keyBoxByteBuffer.u16();
        keyBoxByteBuffer.u16();
        ArrayList arrayList3 = new ArrayList();
        int i5 = u165 - 1;
        while (i5 >= 0) {
            arrayList3.add(Long.valueOf(keyBoxByteBuffer.u32()));
            i5--;
            u165 = u165;
        }
        int i6 = u165;
        int u8 = keyBoxByteBuffer.u8();
        int u82 = keyBoxByteBuffer.u8();
        keyBoxByteBuffer.u16();
        long u323 = keyBoxByteBuffer.u32();
        long u324 = keyBoxByteBuffer.u32();
        long u325 = keyBoxByteBuffer.u32();
        long u326 = keyBoxByteBuffer.u32();
        if (u326 <= keyBoxByteBuffer.remaining()) {
            byte[] bN2 = keyBoxByteBuffer.bN((int) u326);
            long j2 = i;
            long j3 = u32 + j2;
            byte[] rangeOf = keyBoxByteBuffer.rangeOf((int) j3, (int) (j3 + u322));
            keyBoxByteBuffer.bN((int) ((j - (keyBoxByteBuffer.position() - i)) - 20));
            long j4 = j2 + j;
            byte[] rangeOf2 = keyBoxByteBuffer.rangeOf((int) (j4 - 20), (int) j4);
            keyBoxByteBuffer.consume(rangeOf2.length);
            return new PublicKeyRingBlob(i, j, blobType, i2, u16, u162, arrayList, bN, u164, arrayList2, i6, arrayList3, u8, u82, u323, u324, u325, rangeOf, bN2, rangeOf2, keyFingerPrintCalculator);
        }
        throw new IllegalStateException("sizeOfReservedSpace exceeds content remaining in buffer");
    }

    public PGPPublicKeyRing getPGPPublicKeyRing() throws IOException {
        if (this.type == BlobType.OPEN_PGP_BLOB) {
            return new PGPPublicKeyRing(getKeyBytes(), this.fingerPrintCalculator);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Blob is not PGP blob, it is ");
        outline107.append(this.type.name());
        throw new IllegalStateException(outline107.toString());
    }
}
