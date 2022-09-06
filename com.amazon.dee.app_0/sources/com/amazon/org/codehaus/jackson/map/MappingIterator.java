package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes13.dex */
public class MappingIterator<T> implements Iterator<T> {
    protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator<>(null, null, null, null, false, null);
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer<T> _deserializer;
    protected boolean _hasNextChecked;
    protected JsonParser _parser;
    protected final JavaType _type;
    protected final T _updatedValue;

    protected MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer) {
        this(javaType, jsonParser, deserializationContext, jsonDeserializer, true, null);
    }

    protected static <T> MappingIterator<T> emptyIterator() {
        return (MappingIterator<T>) EMPTY_ITERATOR;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        try {
            return hasNextValue();
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public boolean hasNextValue() throws IOException {
        JsonParser jsonParser = this._parser;
        if (jsonParser == null) {
            return false;
        }
        if (!this._hasNextChecked) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            this._hasNextChecked = true;
            if (currentToken == null) {
                JsonToken nextToken = this._parser.nextToken();
                if (nextToken == null) {
                    JsonParser jsonParser2 = this._parser;
                    this._parser = null;
                    if (this._closeParser) {
                        jsonParser2.close();
                    }
                    return false;
                } else if (nextToken == JsonToken.END_ARRAY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            return nextValue();
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public T nextValue() throws IOException {
        T t;
        if (!this._hasNextChecked && !hasNextValue()) {
            throw new NoSuchElementException();
        }
        JsonParser jsonParser = this._parser;
        if (jsonParser != null) {
            this._hasNextChecked = false;
            T t2 = this._updatedValue;
            if (t2 == null) {
                t = this._deserializer.mo4206deserialize(jsonParser, this._context);
            } else {
                this._deserializer.deserialize(jsonParser, this._context, t2);
                t = this._updatedValue;
            }
            this._parser.clearCurrentToken();
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, boolean z, Object obj) {
        this._type = javaType;
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._deserializer = jsonDeserializer;
        if (jsonParser != null && jsonParser.getCurrentToken() == JsonToken.START_ARRAY && !jsonParser.mo4092getParsingContext().inRoot()) {
            jsonParser.clearCurrentToken();
        }
        this._closeParser = z;
        if (obj == 0) {
            this._updatedValue = null;
        } else {
            this._updatedValue = obj;
        }
    }
}
