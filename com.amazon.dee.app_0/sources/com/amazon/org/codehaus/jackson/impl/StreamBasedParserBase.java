package com.amazon.org.codehaus.jackson.impl;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.io.IOContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
@Deprecated
/* loaded from: classes13.dex */
public abstract class StreamBasedParserBase extends JsonParserBase {
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;

    protected StreamBasedParserBase(IOContext iOContext, int i, InputStream inputStream, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._inputStream = inputStream;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    protected final boolean _loadToHaveAtLeast(int i) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd;
        int i3 = this._inputPtr;
        int i4 = i2 - i3;
        if (i4 > 0 && i3 > 0) {
            this._currInputProcessed += i3;
            this._currInputRowStart -= i3;
            byte[] bArr = this._inputBuffer;
            System.arraycopy(bArr, i3, bArr, 0, i4);
            this._inputEnd = i4;
        } else {
            this._inputEnd = 0;
        }
        this._inputPtr = 0;
        while (true) {
            int i5 = this._inputEnd;
            if (i5 >= i) {
                return true;
            }
            InputStream inputStream = this._inputStream;
            byte[] bArr2 = this._inputBuffer;
            int read = inputStream.read(bArr2, i5, bArr2.length - i5);
            if (read < 1) {
                _closeInput();
                if (read == 0) {
                    throw new IOException(GeneratedOutlineSupport1.outline52("InputStream.read() returned 0 characters when trying to read ", i4, " bytes"));
                }
                return false;
            }
            this._inputEnd += read;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(bArr);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected final boolean loadMore() throws IOException {
        long j = this._currInputProcessed;
        int i = this._inputEnd;
        this._currInputProcessed = j + i;
        this._currInputRowStart -= i;
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("InputStream.read() returned 0 characters when trying to read "), this._inputBuffer.length, " bytes"));
            }
        }
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i = this._inputEnd;
        int i2 = this._inputPtr;
        int i3 = i - i2;
        if (i3 < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, i2, i3);
        return i3;
    }
}
