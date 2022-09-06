package com.amazon.blueshift.bluefront.android.http.part;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes11.dex */
public class InputStreamPart extends Part {
    private static final int DEFAULT_CHUCK_SIZE = 1024;
    private final int mChunkSize;
    private final InputStream mInputStream;

    public InputStreamPart(InputStream inputStream, String str, MediaType mediaType) {
        this(inputStream, str, mediaType, 1024);
    }

    @Override // com.amazon.blueshift.bluefront.android.http.part.Part
    protected void writeBody(OutputStream outputStream) throws Exception {
        byte[] bArr = new byte[this.mChunkSize];
        int read = this.mInputStream.read(bArr);
        while (read != -1) {
            outputStream.write(bArr, 0, read);
            outputStream.flush();
            read = this.mInputStream.read(bArr);
        }
    }

    public InputStreamPart(InputStream inputStream, String str, MediaType mediaType, int i) {
        super(str, mediaType);
        Preconditions.checkNotNull(inputStream, "Input stream cannot be null");
        Preconditions.checkArgument(i > 0, "Chunk size must be postive number.");
        this.mInputStream = inputStream;
        this.mChunkSize = i;
    }
}
