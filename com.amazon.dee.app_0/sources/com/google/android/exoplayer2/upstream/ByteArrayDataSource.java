package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
/* loaded from: classes2.dex */
public final class ByteArrayDataSource extends BaseDataSource {
    private int bytesRemaining;
    private final byte[] data;
    private boolean opened;
    private int readPosition;
    @Nullable
    private Uri uri;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteArrayDataSource(byte[] bArr) {
        super(false);
        boolean z = false;
        Assertions.checkNotNull(bArr);
        Assertions.checkArgument(bArr.length > 0 ? true : z);
        this.data = bArr;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() {
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
        this.uri = null;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        transferInitializing(dataSpec);
        long j = dataSpec.position;
        this.readPosition = (int) j;
        long j2 = dataSpec.length;
        if (j2 == -1) {
            j2 = this.data.length - j;
        }
        this.bytesRemaining = (int) j2;
        int i = this.bytesRemaining;
        if (i > 0 && this.readPosition + i <= this.data.length) {
            this.opened = true;
            transferStarted(dataSpec);
            return this.bytesRemaining;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsatisfiable range: [");
        outline107.append(this.readPosition);
        outline107.append(", ");
        outline107.append(dataSpec.length);
        outline107.append("], length: ");
        outline107.append(this.data.length);
        throw new IOException(outline107.toString());
    }

    @Override // com.google.android.exoplayer2.upstream.DataReader
    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.bytesRemaining;
        if (i3 == 0) {
            return -1;
        }
        int min = Math.min(i2, i3);
        System.arraycopy(this.data, this.readPosition, bArr, i, min);
        this.readPosition += min;
        this.bytesRemaining -= min;
        bytesTransferred(min);
        return min;
    }
}
