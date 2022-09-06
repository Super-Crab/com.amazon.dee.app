package com.fasterxml.jackson.databind.deser;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes2.dex */
public class DataFormatReaders {
    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final int _maxInputLookahead;
    protected final MatchStrength _minimalMatch;
    protected final MatchStrength _optimalMatch;
    protected final ObjectReader[] _readers;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class AccessorForReader extends InputAccessor.Std {
        public AccessorForReader(InputStream inputStream, byte[] bArr) {
            super(inputStream, bArr);
        }

        public Match createMatcher(ObjectReader objectReader, MatchStrength matchStrength) {
            InputStream inputStream = this._in;
            byte[] bArr = this._buffer;
            int i = this._bufferedStart;
            return new Match(inputStream, bArr, i, this._bufferedEnd - i, objectReader, matchStrength);
        }

        public AccessorForReader(byte[] bArr) {
            super(bArr);
        }

        public AccessorForReader(byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
        }
    }

    /* loaded from: classes2.dex */
    public static class Match {
        protected final byte[] _bufferedData;
        protected final int _bufferedLength;
        protected final int _bufferedStart;
        protected final ObjectReader _match;
        protected final MatchStrength _matchStrength;
        protected final InputStream _originalStream;

        protected Match(InputStream inputStream, byte[] bArr, int i, int i2, ObjectReader objectReader, MatchStrength matchStrength) {
            this._originalStream = inputStream;
            this._bufferedData = bArr;
            this._bufferedStart = i;
            this._bufferedLength = i2;
            this._match = objectReader;
            this._matchStrength = matchStrength;
        }

        public JsonParser createParserWithMatch() throws IOException {
            ObjectReader objectReader = this._match;
            if (objectReader == null) {
                return null;
            }
            JsonFactory factory = objectReader.getFactory();
            if (this._originalStream == null) {
                return factory.createParser(this._bufferedData, this._bufferedStart, this._bufferedLength);
            }
            return factory.createParser(getDataStream());
        }

        public InputStream getDataStream() {
            InputStream inputStream = this._originalStream;
            if (inputStream == null) {
                return new ByteArrayInputStream(this._bufferedData, this._bufferedStart, this._bufferedLength);
            }
            return new MergedStream(null, inputStream, this._bufferedData, this._bufferedStart, this._bufferedLength);
        }

        public MatchStrength getMatchStrength() {
            MatchStrength matchStrength = this._matchStrength;
            return matchStrength == null ? MatchStrength.INCONCLUSIVE : matchStrength;
        }

        public String getMatchedFormatName() {
            return this._match.getFactory().getFormatName();
        }

        public ObjectReader getReader() {
            return this._match;
        }

        public boolean hasMatch() {
            return this._match != null;
        }
    }

    public DataFormatReaders(ObjectReader... objectReaderArr) {
        this(objectReaderArr, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }

    private Match _findFormat(AccessorForReader accessorForReader) throws IOException {
        ObjectReader[] objectReaderArr = this._readers;
        int length = objectReaderArr.length;
        ObjectReader objectReader = null;
        int i = 0;
        MatchStrength matchStrength = null;
        while (true) {
            if (i >= length) {
                break;
            }
            ObjectReader objectReader2 = objectReaderArr[i];
            accessorForReader.reset();
            MatchStrength hasFormat = objectReader2.getFactory().hasFormat(accessorForReader);
            if (hasFormat != null && hasFormat.ordinal() >= this._minimalMatch.ordinal() && (objectReader == null || matchStrength.ordinal() < hasFormat.ordinal())) {
                if (hasFormat.ordinal() >= this._optimalMatch.ordinal()) {
                    objectReader = objectReader2;
                    matchStrength = hasFormat;
                    break;
                }
                objectReader = objectReader2;
                matchStrength = hasFormat;
            }
            i++;
        }
        return accessorForReader.createMatcher(objectReader, matchStrength);
    }

    public Match findFormat(InputStream inputStream) throws IOException {
        return _findFormat(new AccessorForReader(inputStream, new byte[this._maxInputLookahead]));
    }

    public String toString() {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
        ObjectReader[] objectReaderArr = this._readers;
        int length = objectReaderArr.length;
        if (length > 0) {
            outline104.append(objectReaderArr[0].getFactory().getFormatName());
            for (int i = 1; i < length; i++) {
                outline104.append(", ");
                outline104.append(this._readers[i].getFactory().getFormatName());
            }
        }
        outline104.append(JsonReaderKt.END_LIST);
        return outline104.toString();
    }

    public DataFormatReaders with(ObjectReader[] objectReaderArr) {
        return new DataFormatReaders(objectReaderArr, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
    }

    public DataFormatReaders withMaxInputLookahead(int i) {
        return i == this._maxInputLookahead ? this : new DataFormatReaders(this._readers, this._optimalMatch, this._minimalMatch, i);
    }

    public DataFormatReaders withMinimalMatch(MatchStrength matchStrength) {
        return matchStrength == this._minimalMatch ? this : new DataFormatReaders(this._readers, this._optimalMatch, matchStrength, this._maxInputLookahead);
    }

    public DataFormatReaders withOptimalMatch(MatchStrength matchStrength) {
        return matchStrength == this._optimalMatch ? this : new DataFormatReaders(this._readers, matchStrength, this._minimalMatch, this._maxInputLookahead);
    }

    public DataFormatReaders withType(JavaType javaType) {
        int length = this._readers.length;
        ObjectReader[] objectReaderArr = new ObjectReader[length];
        for (int i = 0; i < length; i++) {
            objectReaderArr[i] = this._readers[i].forType(javaType);
        }
        return new DataFormatReaders(objectReaderArr, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
    }

    public DataFormatReaders(Collection<ObjectReader> collection) {
        this((ObjectReader[]) collection.toArray(new ObjectReader[collection.size()]));
    }

    public Match findFormat(byte[] bArr) throws IOException {
        return _findFormat(new AccessorForReader(bArr));
    }

    public DataFormatReaders with(DeserializationConfig deserializationConfig) {
        int length = this._readers.length;
        ObjectReader[] objectReaderArr = new ObjectReader[length];
        for (int i = 0; i < length; i++) {
            objectReaderArr[i] = this._readers[i].with(deserializationConfig);
        }
        return new DataFormatReaders(objectReaderArr, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
    }

    private DataFormatReaders(ObjectReader[] objectReaderArr, MatchStrength matchStrength, MatchStrength matchStrength2, int i) {
        this._readers = objectReaderArr;
        this._optimalMatch = matchStrength;
        this._minimalMatch = matchStrength2;
        this._maxInputLookahead = i;
    }

    public Match findFormat(byte[] bArr, int i, int i2) throws IOException {
        return _findFormat(new AccessorForReader(bArr, i, i2));
    }
}
