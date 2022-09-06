package org.bouncycastle.crypto.digests;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.amazon.communication.gw.CorpusBuilder;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import okio.Utf8;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;
/* loaded from: classes4.dex */
public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;
    private static final byte[] S = {GenericAccessProfile.PB_ADV, 46, 67, -55, -94, -40, 124, 1, GenericAccessProfile.INFORMATION_DATA_3D, 54, 84, -95, -20, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, GenericAccessProfile.MESH_BEACON, -39, PSSSigner.TRAILER_IMPLICIT, 76, -126, -54, 30, -101, 87, 60, -3, -44, -32, 22, 103, 66, 111, 24, -118, 23, -27, 18, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, ByteSourceJsonBootstrapper.UTF8_BOM_2, 47, -18, 122, -87, 104, 121, -111, 21, -78, 7, Utf8.REPLACEMENT_BYTE, -108, -62, 16, -119, 11, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 95, GenericAccessProfile.SERVICE_DATA_128BIT, Byte.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, -112, 50, GenericAccessProfile.LE_SUPPORTED_FEATURES, 53, 62, -52, -25, ByteSourceJsonBootstrapper.UTF8_BOM_3, -9, -105, 3, -1, 25, 48, -77, 72, -91, -75, -47, -41, 94, -110, GenericAccessProfile.MESH_MESSAGE, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, MultipartStream.DASH, -88, 2, 27, 96, GenericAccessProfile.INDOOR_POSITIONING, -83, -82, -80, -71, -10, 28, 70, 97, 105, 52, 64, 126, 15, 85, 71, -93, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, 44, 83, 13, 110, -123, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, GenericAccessProfile.URI, -31, 123, 8, 12, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, CorpusBuilder.DELIMETER, 0, 29, 57, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, -73, 14, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, 80, -76, -113, -19, 31, Ascii.SUB, -37, -103, -115, 51, -97, 17, -125, 20};
    private byte[] C;
    private int COff;
    private byte[] M;
    private byte[] X;
    private int mOff;
    private int xOff;

    public MD2Digest() {
        this.X = new byte[48];
        this.M = new byte[16];
        this.C = new byte[16];
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.X = new byte[48];
        this.M = new byte[16];
        this.C = new byte[16];
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.X;
        System.arraycopy(bArr, 0, this.X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.M;
        System.arraycopy(bArr2, 0, this.M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.C;
        System.arraycopy(bArr3, 0, this.C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int length = this.M.length;
        int i2 = this.mOff;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.M;
            if (i2 >= bArr2.length) {
                processCheckSum(bArr2);
                processBlock(this.M);
                processBlock(this.C);
                System.arraycopy(this.X, this.xOff, bArr, i, 16);
                reset();
                return 16;
            }
            bArr2[i2] = b;
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return MessageDigestAlgorithms.MD2;
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    protected void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.X;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            int i4 = i2;
            for (int i5 = 0; i5 < 48; i5++) {
                byte[] bArr3 = this.X;
                byte b = (byte) (S[i4] ^ bArr3[i5]);
                bArr3[i5] = b;
                i4 = b & 255;
            }
            i2 = (i4 + i3) % 256;
        }
    }

    protected void processCheckSum(byte[] bArr) {
        byte b = this.C[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.C;
            bArr2[i] = (byte) (S[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            b = bArr2[i];
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.xOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.X;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.mOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.M;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.COff = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.C;
            if (i3 != bArr3.length) {
                bArr3[i3] = 0;
                i3++;
            } else {
                return;
            }
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.M;
        int i = this.mOff;
        this.mOff = i + 1;
        bArr[i] = b;
        if (this.mOff == 16) {
            processCheckSum(bArr);
            processBlock(this.M);
            this.mOff = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.mOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 16) {
            System.arraycopy(bArr, i, this.M, 0, 16);
            processCheckSum(this.M);
            processBlock(this.M);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
