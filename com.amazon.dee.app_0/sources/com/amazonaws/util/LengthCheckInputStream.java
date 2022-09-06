package com.amazonaws.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.internal.SdkFilterInputStream;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class LengthCheckInputStream extends SdkFilterInputStream {
    public static final boolean EXCLUDE_SKIPPED_BYTES = false;
    public static final boolean INCLUDE_SKIPPED_BYTES = true;
    private long dataLength;
    private final long expectedLength;
    private final boolean includeSkipped;
    private long marked;

    public LengthCheckInputStream(InputStream inputStream, long j, boolean z) {
        super(inputStream);
        if (j >= 0) {
            this.expectedLength = j;
            this.includeSkipped = z;
            return;
        }
        throw new IllegalArgumentException();
    }

    private void checkLength(boolean z) {
        if (z) {
            if (this.dataLength == this.expectedLength) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Data read (");
            outline107.append(this.dataLength);
            outline107.append(") has a different length than the expected (");
            throw new AmazonClientException(GeneratedOutlineSupport1.outline87(outline107, this.expectedLength, ")"));
        } else if (this.dataLength <= this.expectedLength) {
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("More data read (");
            outline1072.append(this.dataLength);
            outline1072.append(") than expected (");
            throw new AmazonClientException(GeneratedOutlineSupport1.outline87(outline1072, this.expectedLength, ")"));
        }
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        super.mark(i);
        this.marked = this.dataLength;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read >= 0) {
            this.dataLength++;
        }
        checkLength(read == -1);
        return read;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        super.reset();
        if (super.markSupported()) {
            this.dataLength = this.marked;
        }
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long skip = super.skip(j);
        if (this.includeSkipped && skip > 0) {
            this.dataLength += skip;
            checkLength(false);
        }
        return skip;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        this.dataLength += read >= 0 ? read : 0L;
        checkLength(read == -1);
        return read;
    }
}
