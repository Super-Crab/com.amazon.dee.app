package org.bouncycastle.crypto.engines;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.amazon.communication.gw.CorpusBuilder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private final int[] X = new int[4];
    private int[] rk;
    private static final byte[] Sbox = {-42, -112, -23, -2, -52, -31, GenericAccessProfile.INFORMATION_DATA_3D, -73, 22, -74, 20, -62, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, -5, 44, 5, GenericAccessProfile.MESH_BEACON, 103, -102, 118, GenericAccessProfile.MESH_MESSAGE, -66, 4, -61, -86, 68, 19, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, 73, -122, 6, -103, -100, 66, 80, -12, -111, ByteSourceJsonBootstrapper.UTF8_BOM_1, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, Utf8.REPLACEMENT_BYTE, -90, 71, 7, -89, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, 15, 75, 112, 86, -99, 53, 30, GenericAccessProfile.URI, 14, 94, 99, 88, -47, -94, GenericAccessProfile.INDOOR_POSITIONING, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 124, CorpusBuilder.DELIMETER, 1, GenericAccessProfile.SERVICE_DATA_128BIT, 120, -121, -44, 0, 70, 87, -97, -45, GenericAccessProfile.LE_SUPPORTED_FEATURES, 82, 76, 54, 2, -25, -96, -60, -56, -98, -22, ByteSourceJsonBootstrapper.UTF8_BOM_3, -118, -46, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, -95, -32, -82, 93, -92, -101, 52, Ascii.SUB, 85, -83, -109, 50, 48, -11, -116, -79, -29, 29, -10, -30, 46, -126, 102, -54, 96, -64, GenericAccessProfile.PB_ADV, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, -85, 13, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, ByteSourceJsonBootstrapper.UTF8_BOM_2, -35, PSSSigner.TRAILER_IMPLICIT, Byte.MAX_VALUE, 17, -39, 92, 65, 31, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, MultipartStream.DASH, 116, -48, 18, -72, -27, -76, -80, -119, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};
    private static final int[] CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
    private static final int[] FK = {-1548633402, 1453994832, 1736282519, -1301273892};

    private int F0(int[] iArr, int i) {
        return T((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    private int F1(int[] iArr, int i) {
        return T((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    private int F2(int[] iArr, int i) {
        return T((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    private int F3(int[] iArr, int i) {
        return T((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    private int L(int i) {
        return rotateLeft(i, 24) ^ (((rotateLeft(i, 2) ^ i) ^ rotateLeft(i, 10)) ^ rotateLeft(i, 18));
    }

    private int L_ap(int i) {
        return rotateLeft(i, 23) ^ (rotateLeft(i, 13) ^ i);
    }

    private int T(int i) {
        return L(tau(i));
    }

    private int T_ap(int i) {
        return L_ap(tau(i));
    }

    private int[] expandKey(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), Pack.bigEndianToInt(bArr, 12)};
        int i = iArr2[0];
        int[] iArr3 = FK;
        int[] iArr4 = {i ^ iArr3[0], iArr2[1] ^ iArr3[1], iArr2[2] ^ iArr3[2], iArr2[3] ^ iArr3[3]};
        if (z) {
            iArr[0] = iArr4[0] ^ T_ap(((iArr4[1] ^ iArr4[2]) ^ iArr4[3]) ^ CK[0]);
            iArr[1] = iArr4[1] ^ T_ap(((iArr4[2] ^ iArr4[3]) ^ iArr[0]) ^ CK[1]);
            iArr[2] = iArr4[2] ^ T_ap(((iArr4[3] ^ iArr[0]) ^ iArr[1]) ^ CK[2]);
            iArr[3] = iArr4[3] ^ T_ap(((iArr[0] ^ iArr[1]) ^ iArr[2]) ^ CK[3]);
            for (int i2 = 4; i2 < 32; i2++) {
                iArr[i2] = iArr[i2 - 4] ^ T_ap(((iArr[i2 - 3] ^ iArr[i2 - 2]) ^ iArr[i2 - 1]) ^ CK[i2]);
            }
        } else {
            iArr[31] = iArr4[0] ^ T_ap(((iArr4[1] ^ iArr4[2]) ^ iArr4[3]) ^ CK[0]);
            iArr[30] = iArr4[1] ^ T_ap(((iArr4[2] ^ iArr4[3]) ^ iArr[31]) ^ CK[1]);
            iArr[29] = iArr4[2] ^ T_ap(((iArr4[3] ^ iArr[31]) ^ iArr[30]) ^ CK[2]);
            iArr[28] = iArr4[3] ^ T_ap(((iArr[30] ^ iArr[31]) ^ iArr[29]) ^ CK[3]);
            for (int i3 = 27; i3 >= 0; i3--) {
                iArr[i3] = iArr[i3 + 4] ^ T_ap(((iArr[i3 + 3] ^ iArr[i3 + 2]) ^ iArr[i3 + 1]) ^ CK[31 - i3]);
            }
        }
        return iArr;
    }

    private int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private int tau(int i) {
        byte[] bArr = Sbox;
        return (bArr[i & 255] & 255) | ((bArr[(i >> 24) & 255] & 255) << 24) | ((bArr[(i >> 16) & 255] & 255) << 16) | ((bArr[(i >> 8) & 255] & 255) << 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            if (key.length != 16) {
                throw new IllegalArgumentException("SM4 requires a 128 bit key");
            }
            this.rk = expandKey(z, key);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline102(cipherParameters, GeneratedOutlineSupport1.outline107("invalid parameter passed to SM4 init - ")));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.rk != null) {
            if (i + 16 > bArr.length) {
                throw new DataLengthException("input buffer too short");
            }
            if (i2 + 16 > bArr2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            this.X[0] = Pack.bigEndianToInt(bArr, i);
            this.X[1] = Pack.bigEndianToInt(bArr, i + 4);
            this.X[2] = Pack.bigEndianToInt(bArr, i + 8);
            this.X[3] = Pack.bigEndianToInt(bArr, i + 12);
            for (int i3 = 0; i3 < 32; i3 += 4) {
                int[] iArr = this.X;
                iArr[0] = F0(iArr, this.rk[i3]);
                int[] iArr2 = this.X;
                iArr2[1] = F1(iArr2, this.rk[i3 + 1]);
                int[] iArr3 = this.X;
                iArr3[2] = F2(iArr3, this.rk[i3 + 2]);
                int[] iArr4 = this.X;
                iArr4[3] = F3(iArr4, this.rk[i3 + 3]);
            }
            Pack.intToBigEndian(this.X[3], bArr2, i2);
            Pack.intToBigEndian(this.X[2], bArr2, i2 + 4);
            Pack.intToBigEndian(this.X[1], bArr2, i2 + 8);
            Pack.intToBigEndian(this.X[0], bArr2, i2 + 12);
            return 16;
        }
        throw new IllegalStateException("SM4 not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
