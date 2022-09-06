package com.amazon.org.codehaus.jackson.impl;

import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.io.IOContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
@Deprecated
/* loaded from: classes13.dex */
public abstract class ReaderBasedParserBase extends JsonParserBase {
    protected char[] _inputBuffer;
    protected Reader _reader;

    protected ReaderBasedParserBase(IOContext iOContext, int i, Reader reader) {
        super(iOContext, i);
        this._reader = reader;
        this._inputBuffer = iOContext.allocTokenBuffer();
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    protected final boolean _matchToken(String str, int i) throws IOException, JsonParseException {
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && Character.isJavaIdentifierPart(this._inputBuffer[this._inputPtr])) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        char[] cArr = this._inputBuffer;
        if (cArr != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(cArr);
        }
    }

    protected void _reportInvalidToken(String str, String str2) throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this._inputPtr++;
            sb.append(c);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized token '");
        outline107.append(sb.toString());
        outline107.append("': was expecting ");
        _reportError(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this._reader;
    }

    protected char getNextChar(String str) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(str);
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return cArr[i];
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserBase
    protected final boolean loadMore() throws IOException {
        long j = this._currInputProcessed;
        int i = this._inputEnd;
        this._currInputProcessed = j + i;
        this._currInputRowStart -= i;
        Reader reader = this._reader;
        if (reader != null) {
            char[] cArr = this._inputBuffer;
            int read = reader.read(cArr, 0, cArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reader returned 0 characters when trying to read ");
                outline107.append(this._inputEnd);
                throw new IOException(outline107.toString());
            }
        }
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int releaseBuffered(Writer writer) throws IOException {
        int i = this._inputEnd;
        int i2 = this._inputPtr;
        int i3 = i - i2;
        if (i3 < 1) {
            return 0;
        }
        writer.write(this._inputBuffer, i2, i3);
        return i3;
    }
}
