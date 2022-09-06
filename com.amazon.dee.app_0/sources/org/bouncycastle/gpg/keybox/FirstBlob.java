package org.bouncycastle.gpg.keybox;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public class FirstBlob extends Blob {
    private final long fileCreatedAt;
    private final int headerFlags;
    private final long lastMaintenanceRun;

    private FirstBlob(int i, long j, BlobType blobType, int i2, int i3, long j2, long j3) {
        super(i, j, blobType, i2);
        this.headerFlags = i3;
        this.fileCreatedAt = j2;
        this.lastMaintenanceRun = j3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FirstBlob parseContent(int i, long j, BlobType blobType, int i2, KeyBoxByteBuffer keyBoxByteBuffer) throws IOException {
        int u16 = keyBoxByteBuffer.u16();
        byte[] bN = keyBoxByteBuffer.bN(4);
        if (!Arrays.areEqual(bN, Blob.magicBytes)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Incorrect magic expecting ");
            outline107.append(Hex.toHexString(Blob.magicBytes));
            outline107.append(" but got ");
            outline107.append(Hex.toHexString(bN));
            throw new IOException(outline107.toString());
        }
        keyBoxByteBuffer.u32();
        long u32 = keyBoxByteBuffer.u32();
        long u322 = keyBoxByteBuffer.u32();
        keyBoxByteBuffer.u32();
        keyBoxByteBuffer.u32();
        return new FirstBlob(i, j, blobType, i2, u16, u32, u322);
    }

    public long getFileCreatedAt() {
        return this.fileCreatedAt;
    }

    public int getHeaderFlags() {
        return this.headerFlags;
    }

    public long getLastMaintenanceRun() {
        return this.lastMaintenanceRun;
    }
}
