package com.amazon.org.codehaus.jackson.format;

import com.amazon.org.codehaus.jackson.JsonFactory;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.io.MergedStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes13.dex */
public class DataFormatMatcher {
    protected final byte[] _bufferedData;
    protected final int _bufferedLength;
    protected final JsonFactory _match;
    protected final MatchStrength _matchStrength;
    protected final InputStream _originalStream;

    /* JADX INFO: Access modifiers changed from: protected */
    public DataFormatMatcher(InputStream inputStream, byte[] bArr, int i, JsonFactory jsonFactory, MatchStrength matchStrength) {
        this._originalStream = inputStream;
        this._bufferedData = bArr;
        this._bufferedLength = i;
        this._match = jsonFactory;
        this._matchStrength = matchStrength;
    }

    public JsonParser createParserWithMatch() throws IOException {
        JsonFactory jsonFactory = this._match;
        if (jsonFactory == null) {
            return null;
        }
        if (this._originalStream == null) {
            return jsonFactory.createJsonParser(this._bufferedData, 0, this._bufferedLength);
        }
        return jsonFactory.createJsonParser(getDataStream());
    }

    public InputStream getDataStream() {
        InputStream inputStream = this._originalStream;
        if (inputStream == null) {
            return new ByteArrayInputStream(this._bufferedData, 0, this._bufferedLength);
        }
        return new MergedStream(null, inputStream, this._bufferedData, 0, this._bufferedLength);
    }

    public JsonFactory getMatch() {
        return this._match;
    }

    public MatchStrength getMatchStrength() {
        MatchStrength matchStrength = this._matchStrength;
        return matchStrength == null ? MatchStrength.INCONCLUSIVE : matchStrength;
    }

    public String getMatchedFormatName() {
        return this._match.getFormatName();
    }

    public boolean hasMatch() {
        return this._match != null;
    }
}
