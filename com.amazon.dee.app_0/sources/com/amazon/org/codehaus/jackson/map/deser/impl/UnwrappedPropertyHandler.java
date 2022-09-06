package com.amazon.org.codehaus.jackson.map.deser.impl;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.amazon.org.codehaus.jackson.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes13.dex */
public class UnwrappedPropertyHandler {
    protected final ArrayList<SettableBeanProperty> _properties = new ArrayList<>();

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        this._properties.add(settableBeanProperty);
    }

    public Object processUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        int size = this._properties.size();
        for (int i = 0; i < size; i++) {
            JsonParser asParser = tokenBuffer.asParser();
            asParser.nextToken();
            this._properties.get(i).deserializeAndSet(asParser, deserializationContext, obj);
        }
        return obj;
    }
}
