package org.bouncycastle.crypto.digests;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.amazon.communication.gw.CorpusBuilder;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.lang.reflect.Array;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class Haraka256Digest extends HarakaBase {
    private static final byte[][] RC = {new byte[]{6, -124, 112, 76, -26, 32, -64, 10, -78, -59, -2, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, 117, -127, 123, -99}, new byte[]{-117, 102, -76, -31, -120, -13, -96, 107, 100, 15, 107, -92, 47, 8, -9, 23}, new byte[]{52, 2, -34, MultipartStream.DASH, 83, -14, -124, -104, -49, 2, -99, 96, -97, 2, -111, 20}, new byte[]{14, -42, -22, -26, 46, 123, 79, 8, ByteSourceJsonBootstrapper.UTF8_BOM_2, -13, PSSSigner.TRAILER_IMPLICIT, -81, -3, 91, 79, 121}, new byte[]{-53, -49, -80, -53, 72, 114, 68, -117, 121, -18, -51, 28, -66, 57, 112, 68}, new byte[]{126, -22, -51, -18, 110, -112, 50, -73, -115, 83, 53, -19, GenericAccessProfile.MESH_BEACON, -118, 5, 123}, new byte[]{103, -62, -113, 67, 94, 46, 124, -48, -30, 65, GenericAccessProfile.LE_SUPPORTED_FEATURES, 97, -38, 79, ByteSourceJsonBootstrapper.UTF8_BOM_1, 27}, new byte[]{GenericAccessProfile.PB_ADV, GenericAccessProfile.URI, -39, -80, -81, -54, -52, 7, 103, 95, -3, -30, 31, -57, 11, CorpusBuilder.DELIMETER}, new byte[]{-85, 77, 99, -15, -26, -122, Byte.MAX_VALUE, -23, -20, -37, -113, -54, -71, -44, 101, -18}, new byte[]{28, 48, ByteSourceJsonBootstrapper.UTF8_BOM_3, -124, -44, -73, -51, 100, 91, GenericAccessProfile.MESH_MESSAGE, 64, 79, -83, 3, 126, 51}, new byte[]{-78, -52, 11, -71, -108, 23, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, ByteSourceJsonBootstrapper.UTF8_BOM_3, 105, 2, -117, 46, -115, -10, -104, 0}, new byte[]{-6, 4, 120, -90, -34, 111, 85, 114, 74, -86, -98, -56, 92, -99, MultipartStream.DASH, -118}, new byte[]{-33, -76, -97, GenericAccessProfile.MESH_BEACON, 107, 119, GenericAccessProfile.MESH_MESSAGE, 18, 14, -6, 79, 46, GenericAccessProfile.PB_ADV, 18, -97, -44}, new byte[]{30, -95, 3, 68, -12, 73, -94, 54, 50, -42, 17, -82, ByteSourceJsonBootstrapper.UTF8_BOM_2, 106, 18, -18}, new byte[]{-81, 4, 73, -120, 75, 5, 0, -124, 95, -106, 0, -55, -100, -88, -20, -90}, new byte[]{GenericAccessProfile.SERVICE_DATA_128BIT, 2, 94, -40, -99, 25, -100, 79, 120, -94, -57, -29, GenericAccessProfile.LE_SUPPORTED_FEATURES, -27, -109, -20}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_3, 58, -86, -8, -89, 89, -55, -73, -71, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, 46, -51, -126, -44, 1, 115}, new byte[]{98, 96, 112, 13, 97, -122, -80, 23, 55, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 16, 48, 125, 107}, new byte[]{90, -54, 69, -62, GenericAccessProfile.SERVICE_DATA_128BIT, 48, 4, 67, -127, -62, -111, 83, -10, -4, -102, -58}, new byte[]{-110, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, -105, 60, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 107, 104, ByteSourceJsonBootstrapper.UTF8_BOM_2, 44, -81, -110, -24, 54, -47, -108, 58}};
    private final byte[] buffer;
    private int off;

    public Haraka256Digest() {
        this.buffer = new byte[32];
    }

    public Haraka256Digest(Haraka256Digest haraka256Digest) {
        this.buffer = Arrays.clone(haraka256Digest.buffer);
        this.off = haraka256Digest.off;
    }

    private int haraka256256(byte[] bArr, byte[] bArr2, int i) {
        byte[][] bArr3 = (byte[][]) Array.newInstance(byte.class, 2, 16);
        byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, 2, 16);
        System.arraycopy(bArr, 0, bArr3[0], 0, 16);
        System.arraycopy(bArr, 16, bArr3[1], 0, 16);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[0]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[1]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[2]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[3]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[4]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[5]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[6]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[7]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[8]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[9]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[10]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[11]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[12]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[13]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[14]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[15]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[16]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[17]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[18]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[19]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.xor(bArr4[0], bArr, 0);
        bArr3[1] = HarakaBase.xor(bArr4[1], bArr, 16);
        System.arraycopy(bArr3[0], 0, bArr2, i, 16);
        System.arraycopy(bArr3[1], 0, bArr2, i + 16, 16);
        return 32;
    }

    private void mix256(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 0, bArr2[0], 0, 4);
        System.arraycopy(bArr[1], 0, bArr2[0], 4, 4);
        System.arraycopy(bArr[0], 4, bArr2[0], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[0], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[1], 0, 4);
        System.arraycopy(bArr[1], 8, bArr2[1], 4, 4);
        System.arraycopy(bArr[0], 12, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 12, bArr2[1], 12, 4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        if (this.off == 32) {
            if (bArr.length - i < 32) {
                throw new IllegalArgumentException("output too short to receive digest");
            }
            int haraka256256 = haraka256256(this.buffer, bArr, i);
            reset();
            return haraka256256;
        }
        throw new IllegalStateException("input must be exactly 32 bytes");
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Haraka-256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.off;
        if (i + 1 <= 32) {
            byte[] bArr = this.buffer;
            this.off = i + 1;
            bArr[i] = b;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 32 bytes");
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 + i2 <= 32) {
            System.arraycopy(bArr, i, this.buffer, i3, i2);
            this.off += i2;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 32 bytes");
    }
}
