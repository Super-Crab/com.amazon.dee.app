package com.amazon.devicesetup.provisioning.ble;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class WhisperJoinBlePacket {
    private static final int MAX_PACKET_PAYLOAD_SIZE = 494;
    private static final int PACKET_OVERHEAD = 18;
    private final boolean mAdditionalChunks;
    private final int mChunkIndex;
    private final int mPacketKey;
    private final byte[] mPayload;

    public WhisperJoinBlePacket(int i, int i2, boolean z, byte[] bArr) {
        if (i2 >= 0) {
            if (bArr != null) {
                if (bArr.length != 0) {
                    this.mPacketKey = i;
                    this.mChunkIndex = i2;
                    this.mAdditionalChunks = z;
                    this.mPayload = (byte[]) bArr.clone();
                    return;
                }
                throw new IllegalArgumentException("payload cannot be empty.");
            }
            throw new IllegalArgumentException("payload cannot be null.");
        }
        throw new IllegalArgumentException("chunkIndex cannot be negative.");
    }

    private int copyData(byte[] bArr, byte[] bArr2, int i) {
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        return i + bArr2.length;
    }

    public static WhisperJoinBlePacket createNextResponsePacket(WhisperJoinBlePacket whisperJoinBlePacket) {
        return new WhisperJoinBlePacket(whisperJoinBlePacket.getPacketKey(), whisperJoinBlePacket.getChunkIndex(), whisperJoinBlePacket.hasAdditionalChunks(), new byte[1]);
    }

    public static List<WhisperJoinBlePacket> createPackets(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length != 0) {
                byte[] bArr2 = new byte[4];
                new SecureRandom().nextBytes(bArr2);
                int i = ByteBuffer.wrap(bArr2).order(ByteOrder.LITTLE_ENDIAN).getInt();
                ArrayList arrayList = new ArrayList();
                int i2 = 0;
                int i3 = 0;
                while (i2 < bArr.length) {
                    byte[] copyOfRange = Arrays.copyOfRange(bArr, i2, Math.min(i2 + MAX_PACKET_PAYLOAD_SIZE, bArr.length));
                    i2 += copyOfRange.length;
                    int i4 = i3 + 1;
                    arrayList.add(new WhisperJoinBlePacket(i, i3, i2 < bArr.length, copyOfRange));
                    i3 = i4;
                }
                return arrayList;
            }
            throw new IllegalArgumentException("payload cannot be empty.");
        }
        throw new IllegalArgumentException("payload cannot be null.");
    }

    public int getChunkIndex() {
        return this.mChunkIndex;
    }

    public int getPacketKey() {
        return this.mPacketKey;
    }

    public byte[] getPayload() {
        return (byte[]) this.mPayload.clone();
    }

    public boolean hasAdditionalChunks() {
        return this.mAdditionalChunks;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WhisperJoinBlePacket [key=");
        outline107.append(this.mPacketKey);
        outline107.append(", chunk=");
        outline107.append(this.mChunkIndex);
        outline107.append(", additional-chunks=");
        return GeneratedOutlineSupport1.outline97(outline107, this.mAdditionalChunks, "]");
    }
}
