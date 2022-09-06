package com.amazon.org.codehaus.jackson.map.deser.impl;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.amazon.org.codehaus.jackson.util.TokenBuffer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class ExternalTypeHandler {
    private final HashMap<String, Integer> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    /* loaded from: classes13.dex */
    public static class Builder {
        private final ArrayList<ExtTypedProperty> _properties = new ArrayList<>();
        private final HashMap<String, Integer> _nameToPropertyIndex = new HashMap<>();

        public void addExternal(SettableBeanProperty settableBeanProperty, String str) {
            Integer valueOf = Integer.valueOf(this._properties.size());
            this._properties.add(new ExtTypedProperty(settableBeanProperty, str));
            this._nameToPropertyIndex.put(settableBeanProperty.getName(), valueOf);
            this._nameToPropertyIndex.put(str, valueOf);
        }

        public ExternalTypeHandler build() {
            ArrayList<ExtTypedProperty> arrayList = this._properties;
            return new ExternalTypeHandler((ExtTypedProperty[]) arrayList.toArray(new ExtTypedProperty[arrayList.size()]), this._nameToPropertyIndex, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty settableBeanProperty, String str) {
            this._property = settableBeanProperty;
            this._typePropertyName = str;
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public boolean hasTypePropertyName(String str) {
            return str.equals(this._typePropertyName);
        }
    }

    protected ExternalTypeHandler(ExtTypedProperty[] extTypedPropertyArr, HashMap<String, Integer> hashMap, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = hashMap;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    protected final void _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(this._typeIds[i]);
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        asParser.nextToken();
        tokenBuffer.copyCurrentStructure(asParser);
        tokenBuffer.writeEndArray();
        JsonParser asParser2 = tokenBuffer.asParser(jsonParser);
        asParser2.nextToken();
        this._properties[i].getProperty().deserializeAndSet(asParser2, deserializationContext, obj);
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        int length = this._properties.length;
        for (int i = 0; i < length; i++) {
            if (this._typeIds[i] == null) {
                if (this._tokens[i] != null) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Missing external type id property '");
                    outline107.append(this._properties[i].getTypePropertyName());
                    outline107.append("'");
                    throw deserializationContext.mappingException(outline107.toString());
                }
            } else if (this._tokens[i] != null) {
                _deserialize(jsonParser, deserializationContext, obj, i);
            } else {
                SettableBeanProperty property = this._properties[i].getProperty();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Missing property '");
                outline1072.append(property.getName());
                outline1072.append("' for external type id '");
                outline1072.append(this._properties[i].getTypePropertyName());
                throw deserializationContext.mappingException(outline1072.toString());
            }
        }
        return obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002c, code lost:
        if (r4._tokens[r0] != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0046, code lost:
        if (r4._typeIds[r0] != null) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean handleToken(com.amazon.org.codehaus.jackson.JsonParser r5, com.amazon.org.codehaus.jackson.map.DeserializationContext r6, java.lang.String r7, java.lang.Object r8) throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonProcessingException {
        /*
            r4 = this;
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r4._nameToPropertyIndex
            java.lang.Object r0 = r0.get(r7)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r1 = 0
            if (r0 != 0) goto Lc
            return r1
        Lc:
            int r0 = r0.intValue()
            com.amazon.org.codehaus.jackson.map.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r2 = r4._properties
            r2 = r2[r0]
            boolean r7 = r2.hasTypePropertyName(r7)
            r2 = 1
            if (r7 == 0) goto L30
            java.lang.String[] r7 = r4._typeIds
            java.lang.String r3 = r5.getText()
            r7[r0] = r3
            r5.skipChildren()
            if (r8 == 0) goto L49
            com.amazon.org.codehaus.jackson.util.TokenBuffer[] r7 = r4._tokens
            r7 = r7[r0]
            if (r7 == 0) goto L49
        L2e:
            r1 = r2
            goto L49
        L30:
            com.amazon.org.codehaus.jackson.util.TokenBuffer r7 = new com.amazon.org.codehaus.jackson.util.TokenBuffer
            com.amazon.org.codehaus.jackson.ObjectCodec r3 = r5.getCodec()
            r7.<init>(r3)
            r7.copyCurrentStructure(r5)
            com.amazon.org.codehaus.jackson.util.TokenBuffer[] r3 = r4._tokens
            r3[r0] = r7
            if (r8 == 0) goto L49
            java.lang.String[] r7 = r4._typeIds
            r7 = r7[r0]
            if (r7 == 0) goto L49
            goto L2e
        L49:
            if (r1 == 0) goto L57
            r4._deserialize(r5, r6, r8, r0)
            java.lang.String[] r5 = r4._typeIds
            r6 = 0
            r5[r0] = r6
            com.amazon.org.codehaus.jackson.util.TokenBuffer[] r5 = r4._tokens
            r5[r0] = r6
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.map.deser.impl.ExternalTypeHandler.handleToken(com.amazon.org.codehaus.jackson.JsonParser, com.amazon.org.codehaus.jackson.map.DeserializationContext, java.lang.String, java.lang.Object):boolean");
    }

    public boolean handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException, JsonProcessingException {
        Integer num = this._nameToPropertyIndex.get(str);
        boolean z = false;
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (!this._properties[intValue].hasTypePropertyName(str)) {
            return false;
        }
        this._typeIds[intValue] = jsonParser.getText();
        if (obj != null && this._tokens[intValue] != null) {
            z = true;
        }
        if (z) {
            _deserialize(jsonParser, deserializationContext, obj, intValue);
            this._typeIds[intValue] = null;
            this._tokens[intValue] = null;
        }
        return true;
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    protected ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        this._properties = externalTypeHandler._properties;
        this._nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        int length = this._properties.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }
}
