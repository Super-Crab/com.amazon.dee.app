package com.amazon.org.codehaus.jackson.util;

import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class JsonParserSequence extends JsonParserDelegate {
    protected int _nextParser;
    protected final JsonParser[] _parsers;

    protected JsonParserSequence(JsonParser[] jsonParserArr) {
        super(jsonParserArr[0]);
        this._parsers = jsonParserArr;
        this._nextParser = 1;
    }

    public static JsonParserSequence createFlattened(JsonParser jsonParser, JsonParser jsonParser2) {
        boolean z = jsonParser instanceof JsonParserSequence;
        if (!z && !(jsonParser2 instanceof JsonParserSequence)) {
            return new JsonParserSequence(new JsonParser[]{jsonParser, jsonParser2});
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser);
        }
        if (jsonParser2 instanceof JsonParserSequence) {
            ((JsonParserSequence) jsonParser2).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser2);
        }
        return new JsonParserSequence((JsonParser[]) arrayList.toArray(new JsonParser[arrayList.size()]));
    }

    protected void addFlattenedActiveParsers(List<JsonParser> list) {
        int length = this._parsers.length;
        for (int i = this._nextParser - 1; i < length; i++) {
            JsonParser jsonParser = this._parsers[i];
            if (jsonParser instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(list);
            } else {
                list.add(jsonParser);
            }
        }
    }

    @Override // com.amazon.org.codehaus.jackson.util.JsonParserDelegate, com.amazon.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        do {
            this.delegate.close();
        } while (switchToNext());
    }

    public int containedParsersCount() {
        return this._parsers.length;
    }

    @Override // com.amazon.org.codehaus.jackson.util.JsonParserDelegate, com.amazon.org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken nextToken = this.delegate.nextToken();
        if (nextToken != null) {
            return nextToken;
        }
        while (switchToNext()) {
            JsonToken nextToken2 = this.delegate.nextToken();
            if (nextToken2 != null) {
                return nextToken2;
            }
        }
        return null;
    }

    protected boolean switchToNext() {
        int i = this._nextParser;
        JsonParser[] jsonParserArr = this._parsers;
        if (i >= jsonParserArr.length) {
            return false;
        }
        this._nextParser = i + 1;
        this.delegate = jsonParserArr[i];
        return true;
    }
}
