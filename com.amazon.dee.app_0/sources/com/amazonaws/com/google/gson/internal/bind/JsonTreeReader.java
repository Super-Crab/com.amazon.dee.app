package com.amazonaws.com.google.gson.internal.bind;

import com.amazonaws.com.google.gson.JsonArray;
import com.amazonaws.com.google.gson.JsonElement;
import com.amazonaws.com.google.gson.JsonNull;
import com.amazonaws.com.google.gson.JsonObject;
import com.amazonaws.com.google.gson.JsonPrimitive;
import com.amazonaws.com.google.gson.stream.JsonReader;
import com.amazonaws.com.google.gson.stream.JsonToken;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public final class JsonTreeReader extends JsonReader {
    private final List<Object> stack;
    private static final Reader UNREADABLE_READER = new Reader() { // from class: com.amazonaws.com.google.gson.internal.bind.JsonTreeReader.1
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object SENTINEL_CLOSED = new Object();

    public JsonTreeReader(JsonElement jsonElement) {
        super(UNREADABLE_READER);
        this.stack = new ArrayList();
        this.stack.add(jsonElement);
    }

    private void expect(JsonToken jsonToken) throws IOException {
        if (peek() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + peek());
    }

    private Object peekStack() {
        return GeneratedOutlineSupport1.outline24(this.stack, -1);
    }

    private Object popStack() {
        List<Object> list = this.stack;
        return list.remove(list.size() - 1);
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public void beginArray() throws IOException {
        expect(JsonToken.BEGIN_ARRAY);
        this.stack.add(((JsonArray) peekStack()).iterator());
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public void beginObject() throws IOException {
        expect(JsonToken.BEGIN_OBJECT);
        this.stack.add(((JsonObject) peekStack()).entrySet().iterator());
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.stack.clear();
        this.stack.add(SENTINEL_CLOSED);
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public void endArray() throws IOException {
        expect(JsonToken.END_ARRAY);
        popStack();
        popStack();
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public void endObject() throws IOException {
        expect(JsonToken.END_OBJECT);
        popStack();
        popStack();
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public boolean hasNext() throws IOException {
        JsonToken peek = peek();
        return (peek == JsonToken.END_OBJECT || peek == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public boolean nextBoolean() throws IOException {
        expect(JsonToken.BOOLEAN);
        return ((JsonPrimitive) popStack()).getAsBoolean();
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public double nextDouble() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER && peek != JsonToken.STRING) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected ");
            outline107.append(JsonToken.NUMBER);
            outline107.append(" but was ");
            outline107.append(peek);
            throw new IllegalStateException(outline107.toString());
        }
        double asDouble = ((JsonPrimitive) peekStack()).getAsDouble();
        if (!isLenient() && (Double.isNaN(asDouble) || Double.isInfinite(asDouble))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + asDouble);
        }
        popStack();
        return asDouble;
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public int nextInt() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER && peek != JsonToken.STRING) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected ");
            outline107.append(JsonToken.NUMBER);
            outline107.append(" but was ");
            outline107.append(peek);
            throw new IllegalStateException(outline107.toString());
        }
        int asInt = ((JsonPrimitive) peekStack()).getAsInt();
        popStack();
        return asInt;
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public long nextLong() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER && peek != JsonToken.STRING) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected ");
            outline107.append(JsonToken.NUMBER);
            outline107.append(" but was ");
            outline107.append(peek);
            throw new IllegalStateException(outline107.toString());
        }
        long asLong = ((JsonPrimitive) peekStack()).getAsLong();
        popStack();
        return asLong;
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public String nextName() throws IOException {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        this.stack.add(entry.getValue());
        return (String) entry.getKey();
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public void nextNull() throws IOException {
        expect(JsonToken.NULL);
        popStack();
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public String nextString() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.STRING && peek != JsonToken.NUMBER) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected ");
            outline107.append(JsonToken.STRING);
            outline107.append(" but was ");
            outline107.append(peek);
            throw new IllegalStateException(outline107.toString());
        }
        return ((JsonPrimitive) popStack()).getAsString();
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public JsonToken peek() throws IOException {
        if (this.stack.isEmpty()) {
            return JsonToken.END_DOCUMENT;
        }
        Object peekStack = peekStack();
        if (peekStack instanceof Iterator) {
            boolean z = GeneratedOutlineSupport1.outline24(this.stack, -2) instanceof JsonObject;
            Iterator it2 = (Iterator) peekStack;
            if (!it2.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            } else if (z) {
                return JsonToken.NAME;
            } else {
                this.stack.add(it2.next());
                return peek();
            }
        } else if (peekStack instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (peekStack instanceof JsonArray) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (peekStack instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) peekStack;
                if (jsonPrimitive.isString()) {
                    return JsonToken.STRING;
                }
                if (jsonPrimitive.isBoolean()) {
                    return JsonToken.BOOLEAN;
                }
                if (jsonPrimitive.isNumber()) {
                    return JsonToken.NUMBER;
                }
                throw new AssertionError();
            } else if (peekStack instanceof JsonNull) {
                return JsonToken.NULL;
            } else {
                if (peekStack == SENTINEL_CLOSED) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    public void promoteNameToValue() throws IOException {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        this.stack.add(entry.getValue());
        this.stack.add(new JsonPrimitive((String) entry.getKey()));
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public void skipValue() throws IOException {
        if (peek() == JsonToken.NAME) {
            nextName();
        } else {
            popStack();
        }
    }

    @Override // com.amazonaws.com.google.gson.stream.JsonReader
    public String toString() {
        return JsonTreeReader.class.getSimpleName();
    }
}
