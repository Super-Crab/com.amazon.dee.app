package org.bouncycastle.crypto.engines;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.amazon.communication.gw.CorpusBuilder;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import com.facebook.imageutils.JfifUtil;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import java.lang.reflect.Array;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.net.telnet.TelnetCommand;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int C0;
    private int C1;
    private int C2;
    private int C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, GenericAccessProfile.MESH_BEACON, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, -61, 24, -106, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, GenericAccessProfile.LE_SUPPORTED_FEATURES, -78, 117, 9, -125, 44, Ascii.SUB, 27, 110, 90, -96, 82, CorpusBuilder.DELIMETER, -42, -77, GenericAccessProfile.PB_ADV, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, GenericAccessProfile.SERVICE_DATA_128BIT, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, GenericAccessProfile.INFORMATION_DATA_3D, 100, 93, 25, 115, 96, -127, 79, -36, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, GenericAccessProfile.MESH_MESSAGE, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, GenericAccessProfile.URI, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, GenericAccessProfile.INDOOR_POSITIONING, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, -33, -116, -95, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, MultipartStream.DASH, 15, -80, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, 22};
    private static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, GenericAccessProfile.INFORMATION_DATA_3D, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, -39, GenericAccessProfile.URI, -78, 118, 91, -94, 73, 109, -117, -47, GenericAccessProfile.INDOOR_POSITIONING, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, Utf8.REPLACEMENT_BYTE, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, -76, -26, 115, -106, -84, 116, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, Ascii.SUB, 113, 29, GenericAccessProfile.PB_ADV, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, GenericAccessProfile.LE_SUPPORTED_FEATURES, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, -75, 74, 13, MultipartStream.DASH, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, CorpusBuilder.DELIMETER, 77, -82, GenericAccessProfile.MESH_MESSAGE, -11, -80, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, -125, 83, -103, 97, 23, GenericAccessProfile.MESH_BEACON, 4, 126, -70, 119, -42, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, -31, 105, 20, 99, 85, GenericAccessProfile.SERVICE_DATA_128BIT, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, 171, 77, 154, 47, 94, 188, 99, Opcodes.IFNULL, 151, 53, 106, 212, 179, 125, 250, TelnetCommand.EOR, 197, 145};

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = i & m4;
        int i3 = i2 ^ (i2 >>> 1);
        return (i3 >>> 5) ^ (((m5 & i) << 2) ^ (i3 >>> 2));
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.C0;
        int i2 = this.ROUNDS;
        int i3 = i ^ iArr[i2][0];
        int i4 = this.C1 ^ iArr[i2][1];
        int i5 = this.C2 ^ iArr[i2][2];
        int i6 = i2 - 1;
        int i7 = iArr[i2][3] ^ this.C3;
        while (true) {
            byte[] bArr = Si;
            int i8 = i3 & 255;
            if (i6 <= 1) {
                int inv_mcol = inv_mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
                byte[] bArr2 = Si;
                int inv_mcol2 = inv_mcol((bArr2[(i5 >> 24) & 255] << 24) ^ (((bArr2[i4 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
                byte[] bArr3 = Si;
                int inv_mcol3 = inv_mcol((bArr3[(i7 >> 24) & 255] << 24) ^ (((bArr3[i5 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
                byte[] bArr4 = Si;
                int inv_mcol4 = inv_mcol((bArr4[(i3 >> 24) & 255] << 24) ^ (((bArr4[i7 & 255] & 255) ^ ((bArr4[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
                byte[] bArr5 = Si;
                this.C0 = ((((bArr5[inv_mcol & 255] & 255) ^ ((bArr5[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol2 >> 24) & 255] << 24)) ^ iArr[0][0];
                this.C1 = ((((bArr5[inv_mcol2 & 255] & 255) ^ ((bArr5[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol3 >> 24) & 255] << 24)) ^ iArr[0][1];
                this.C2 = ((((bArr5[inv_mcol3 & 255] & 255) ^ ((bArr5[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol4 >> 24) & 255] << 24)) ^ iArr[0][2];
                this.C3 = iArr[0][3] ^ ((((bArr5[inv_mcol4 & 255] & 255) ^ ((bArr5[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol >> 24) & 255] << 24));
                return;
            }
            int inv_mcol5 = inv_mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
            byte[] bArr6 = Si;
            int inv_mcol6 = inv_mcol((bArr6[(i5 >> 24) & 255] << 24) ^ (((bArr6[i4 & 255] & 255) ^ ((bArr6[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
            byte[] bArr7 = Si;
            int inv_mcol7 = inv_mcol((bArr7[(i7 >> 24) & 255] << 24) ^ (((bArr7[i5 & 255] & 255) ^ ((bArr7[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
            byte[] bArr8 = Si;
            int i9 = i6 - 1;
            int inv_mcol8 = inv_mcol((bArr8[(i3 >> 24) & 255] << 24) ^ (((bArr8[i7 & 255] & 255) ^ ((bArr8[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr8[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
            byte[] bArr9 = Si;
            int inv_mcol9 = inv_mcol((bArr9[(inv_mcol6 >> 24) & 255] << 24) ^ (((bArr9[inv_mcol5 & 255] & 255) ^ ((bArr9[(inv_mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(inv_mcol7 >> 16) & 255] & 255) << 16))) ^ iArr[i9][0];
            byte[] bArr10 = Si;
            int inv_mcol10 = inv_mcol((bArr10[(inv_mcol7 >> 24) & 255] << 24) ^ (((bArr10[inv_mcol6 & 255] & 255) ^ ((bArr10[(inv_mcol5 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(inv_mcol8 >> 16) & 255] & 255) << 16))) ^ iArr[i9][1];
            byte[] bArr11 = Si;
            int inv_mcol11 = inv_mcol((bArr11[(inv_mcol8 >> 24) & 255] << 24) ^ (((bArr11[inv_mcol7 & 255] & 255) ^ ((bArr11[(inv_mcol6 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(inv_mcol5 >> 16) & 255] & 255) << 16))) ^ iArr[i9][2];
            byte[] bArr12 = Si;
            int inv_mcol12 = inv_mcol((((bArr12[inv_mcol8 & 255] & 255) ^ ((bArr12[(inv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr12[(inv_mcol6 >> 16) & 255] & 255) << 16)) ^ (bArr12[(inv_mcol5 >> 24) & 255] << 24));
            int i10 = i9 - 1;
            i7 = iArr[i9][3] ^ inv_mcol12;
            i3 = inv_mcol9;
            i4 = inv_mcol10;
            i5 = inv_mcol11;
            i6 = i10;
        }
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.C0 ^ iArr[0][0];
        int i2 = this.C1 ^ iArr[0][1];
        int i3 = this.C2 ^ iArr[0][2];
        int i4 = this.C3 ^ iArr[0][3];
        int i5 = i3;
        int i6 = i2;
        int i7 = i;
        int i8 = 1;
        while (i8 < this.ROUNDS - 1) {
            byte[] bArr = S;
            int mcol = mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i7 & 255] & 255) ^ ((bArr[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i8][0];
            byte[] bArr2 = S;
            int mcol2 = mcol((bArr2[(i7 >> 24) & 255] << 24) ^ (((bArr2[i6 & 255] & 255) ^ ((bArr2[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i8][1];
            byte[] bArr3 = S;
            int mcol3 = mcol((bArr3[(i6 >> 24) & 255] << 24) ^ (((bArr3[i5 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i8][2];
            byte[] bArr4 = S;
            int mcol4 = mcol(((((bArr4[(i7 >> 8) & 255] & 255) << 8) ^ (bArr4[i4 & 255] & 255)) ^ ((bArr4[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i5 >> 24) & 255] << 24));
            int i9 = i8 + 1;
            int i10 = iArr[i8][3] ^ mcol4;
            byte[] bArr5 = S;
            i7 = mcol((bArr5[(i10 >> 24) & 255] << 24) ^ (((bArr5[mcol & 255] & 255) ^ ((bArr5[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(mcol3 >> 16) & 255] & 255) << 16))) ^ iArr[i9][0];
            byte[] bArr6 = S;
            int mcol5 = mcol((bArr6[(mcol >> 24) & 255] << 24) ^ (((bArr6[mcol2 & 255] & 255) ^ ((bArr6[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i10 >> 16) & 255] & 255) << 16))) ^ iArr[i9][1];
            byte[] bArr7 = S;
            int mcol6 = mcol((bArr7[(mcol2 >> 24) & 255] << 24) ^ (((bArr7[mcol3 & 255] & 255) ^ ((bArr7[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(mcol >> 16) & 255] & 255) << 16))) ^ iArr[i9][2];
            byte[] bArr8 = S;
            int i11 = i9 + 1;
            int mcol7 = mcol((((bArr8[i10 & 255] & 255) ^ ((bArr8[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr8[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr8[(mcol3 >> 24) & 255] << 24)) ^ iArr[i9][3];
            i6 = mcol5;
            i5 = mcol6;
            i4 = mcol7;
            i8 = i11;
        }
        byte[] bArr9 = S;
        int mcol8 = mcol((bArr9[(i4 >> 24) & 255] << 24) ^ (((bArr9[i7 & 255] & 255) ^ ((bArr9[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i8][0];
        byte[] bArr10 = S;
        int mcol9 = mcol((bArr10[(i7 >> 24) & 255] << 24) ^ (((bArr10[i6 & 255] & 255) ^ ((bArr10[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i8][1];
        byte[] bArr11 = S;
        int mcol10 = mcol((bArr11[(i6 >> 24) & 255] << 24) ^ (((bArr11[i5 & 255] & 255) ^ ((bArr11[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i8][2];
        byte[] bArr12 = S;
        int mcol11 = mcol(((((bArr12[(i7 >> 8) & 255] & 255) << 8) ^ (bArr12[i4 & 255] & 255)) ^ ((bArr12[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr12[(i5 >> 24) & 255] << 24));
        int i12 = i8 + 1;
        int i13 = iArr[i8][3] ^ mcol11;
        byte[] bArr13 = S;
        this.C0 = iArr[i12][0] ^ ((((bArr13[mcol8 & 255] & 255) ^ ((bArr13[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr13[(i13 >> 24) & 255] << 24));
        this.C1 = ((((bArr13[mcol9 & 255] & 255) ^ ((bArr13[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(i13 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol8 >> 24) & 255] << 24)) ^ iArr[i12][1];
        this.C2 = ((((bArr13[mcol10 & 255] & 255) ^ ((bArr13[(i13 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol9 >> 24) & 255] << 24)) ^ iArr[i12][2];
        this.C3 = iArr[i12][3] ^ ((((bArr13[i13 & 255] & 255) ^ ((bArr13[(mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol10 >> 24) & 255] << 24));
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        this.ROUNDS = i + 6;
        int[][] iArr = (int[][]) Array.newInstance(int.class, this.ROUNDS + 1, 4);
        int i2 = 8;
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            int i3 = littleEndianToInt2;
            int i4 = littleEndianToInt;
            int i5 = littleEndianToInt4;
            for (int i6 = 1; i6 <= 10; i6++) {
                i4 ^= subWord(shift(i5, 8)) ^ rcon[i6 - 1];
                iArr[i6][0] = i4;
                i3 ^= i4;
                iArr[i6][1] = i3;
                littleEndianToInt3 ^= i3;
                iArr[i6][2] = littleEndianToInt3;
                i5 ^= littleEndianToInt3;
                iArr[i6][3] = i5;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int i7 = littleEndianToInt5;
            int i8 = littleEndianToInt8;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i9 = 1;
            int i10 = 1;
            while (true) {
                iArr[i9][0] = littleEndianToInt9;
                iArr[i9][1] = littleEndianToInt10;
                int subWord = subWord(shift(littleEndianToInt10, 8)) ^ i10;
                int i11 = i10 << 1;
                int i12 = i7 ^ subWord;
                iArr[i9][2] = i12;
                int i13 = littleEndianToInt6 ^ i12;
                iArr[i9][3] = i13;
                int i14 = littleEndianToInt7 ^ i13;
                int i15 = i9 + 1;
                iArr[i15][0] = i14;
                int i16 = i8 ^ i14;
                iArr[i15][1] = i16;
                int i17 = littleEndianToInt9 ^ i16;
                iArr[i15][2] = i17;
                int i18 = littleEndianToInt10 ^ i17;
                iArr[i15][3] = i18;
                int subWord2 = subWord(shift(i18, 8)) ^ i11;
                i10 = i11 << 1;
                i7 = i12 ^ subWord2;
                int i19 = i9 + 2;
                iArr[i19][0] = i7;
                littleEndianToInt6 = i13 ^ i7;
                iArr[i19][1] = littleEndianToInt6;
                littleEndianToInt7 = i14 ^ littleEndianToInt6;
                iArr[i19][2] = littleEndianToInt7;
                i8 = i16 ^ littleEndianToInt7;
                iArr[i19][3] = i8;
                i9 += 3;
                if (i9 >= 13) {
                    break;
                }
                littleEndianToInt9 = i17 ^ i8;
                littleEndianToInt10 = i18 ^ littleEndianToInt9;
            }
        } else if (i != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i20 = 2;
            int i21 = littleEndianToInt17;
            int i22 = littleEndianToInt16;
            int i23 = littleEndianToInt15;
            int i24 = littleEndianToInt11;
            int i25 = 1;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, i2)) ^ i25;
                i25 <<= 1;
                i24 ^= subWord3;
                iArr[i20][0] = i24;
                littleEndianToInt12 ^= i24;
                iArr[i20][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr[i20][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr[i20][3] = littleEndianToInt14;
                int i26 = i20 + 1;
                if (i26 >= 15) {
                    break;
                }
                i23 ^= subWord(littleEndianToInt14);
                iArr[i26][0] = i23;
                i22 ^= i23;
                iArr[i26][1] = i22;
                i21 ^= i22;
                iArr[i26][2] = i21;
                littleEndianToInt18 ^= i21;
                iArr[i26][3] = littleEndianToInt18;
                i20 = i26 + 1;
                i2 = 8;
            }
        }
        if (!z) {
            for (int i27 = 1; i27 < this.ROUNDS; i27++) {
                for (int i28 = 0; i28 < 4; i28++) {
                    iArr[i27][i28] = inv_mcol(iArr[i27][i28]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i19 + 1] = (byte) (i18 >> 24);
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        this.C0 = bArr[i] & 255;
        int i3 = i2 + 1;
        this.C0 |= (bArr[i2] & 255) << 8;
        int i4 = i3 + 1;
        this.C0 |= (bArr[i3] & 255) << 16;
        int i5 = i4 + 1;
        this.C0 |= bArr[i4] << 24;
        int i6 = i5 + 1;
        this.C1 = bArr[i5] & 255;
        int i7 = i6 + 1;
        this.C1 = ((bArr[i6] & 255) << 8) | this.C1;
        int i8 = i7 + 1;
        this.C1 |= (bArr[i7] & 255) << 16;
        int i9 = i8 + 1;
        this.C1 |= bArr[i8] << 24;
        int i10 = i9 + 1;
        this.C2 = bArr[i9] & 255;
        int i11 = i10 + 1;
        this.C2 = ((bArr[i10] & 255) << 8) | this.C2;
        int i12 = i11 + 1;
        this.C2 |= (bArr[i11] & 255) << 16;
        int i13 = i12 + 1;
        this.C2 |= bArr[i12] << 24;
        int i14 = i13 + 1;
        this.C3 = bArr[i13] & 255;
        int i15 = i14 + 1;
        this.C3 = ((bArr[i14] & 255) << 8) | this.C3;
        this.C3 |= (bArr[i15] & 255) << 16;
        this.C3 = (bArr[i15 + 1] << 24) | this.C3;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline102(cipherParameters, GeneratedOutlineSupport1.outline107("invalid parameter passed to AES init - ")));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey != null) {
            if (i + 16 > bArr.length) {
                throw new DataLengthException("input buffer too short");
            }
            if (i2 + 16 > bArr2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            boolean z = this.forEncryption;
            unpackBlock(bArr, i);
            int[][] iArr = this.WorkingKey;
            if (z) {
                encryptBlock(iArr);
            } else {
                decryptBlock(iArr);
            }
            packBlock(bArr2, i2);
            return 16;
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
