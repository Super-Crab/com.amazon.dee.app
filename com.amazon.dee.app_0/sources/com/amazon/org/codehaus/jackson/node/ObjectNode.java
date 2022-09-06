package com.amazon.org.codehaus.jackson.node;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.node.ContainerNode;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class ObjectNode extends ContainerNode {
    protected LinkedHashMap<String, JsonNode> _children;

    /* loaded from: classes13.dex */
    protected static class NoFieldsIterator implements Iterator<Map.Entry<String, JsonNode>> {
        static final NoFieldsIterator instance = new NoFieldsIterator();

        private NoFieldsIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }

        @Override // java.util.Iterator
        public Map.Entry<String, JsonNode> next() {
            throw new NoSuchElementException();
        }
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = null;
    }

    private final JsonNode _put(String str, JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new LinkedHashMap<>();
        }
        return this._children.put(str, jsonNode);
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0030  */
    @Override // com.amazon.org.codehaus.jackson.JsonNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L4
            return r0
        L4:
            r1 = 0
            if (r6 != 0) goto L8
            return r1
        L8:
            java.lang.Class r2 = r6.getClass()
            java.lang.Class<com.amazon.org.codehaus.jackson.node.ObjectNode> r3 = com.amazon.org.codehaus.jackson.node.ObjectNode.class
            if (r2 == r3) goto L11
            return r1
        L11:
            com.amazon.org.codehaus.jackson.node.ObjectNode r6 = (com.amazon.org.codehaus.jackson.node.ObjectNode) r6
            int r2 = r6.size()
            int r3 = r5.size()
            if (r2 == r3) goto L1e
            return r1
        L1e:
            java.util.LinkedHashMap<java.lang.String, com.amazon.org.codehaus.jackson.JsonNode> r2 = r5._children
            if (r2 == 0) goto L4f
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L2a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L4f
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r3 = r3.getValue()
            com.amazon.org.codehaus.jackson.JsonNode r3 = (com.amazon.org.codehaus.jackson.JsonNode) r3
            com.amazon.org.codehaus.jackson.JsonNode r4 = r6.get(r4)
            if (r4 == 0) goto L4e
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L2a
        L4e:
            return r1
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.node.ObjectNode.equals(java.lang.Object):boolean");
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                if (str.equals(entry.getKey())) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(this);
                } else {
                    list = entry.getValue().findParents(str, list);
                }
            }
        }
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return entry.getValue();
                }
                JsonNode findValue = entry.getValue().findValue(str);
                if (findValue != null) {
                    return findValue;
                }
            }
            return null;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                if (str.equals(entry.getKey())) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(entry.getValue());
                } else {
                    list = entry.getValue().findValues(str, list);
                }
            }
        }
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                if (str.equals(entry.getKey())) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(entry.getValue().asText());
                } else {
                    list = entry.getValue().findValuesAsText(str, list);
                }
            }
        }
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode get(int i) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode get(String str) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            return linkedHashMap.get(str);
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public Iterator<JsonNode> getElements() {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        return linkedHashMap == null ? ContainerNode.NoNodesIterator.instance() : linkedHashMap.values().iterator();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public Iterator<String> getFieldNames() {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        return linkedHashMap == null ? ContainerNode.NoStringsIterator.instance() : linkedHashMap.keySet().iterator();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public Iterator<Map.Entry<String, JsonNode>> getFields() {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap == null) {
            return NoFieldsIterator.instance;
        }
        return linkedHashMap.entrySet().iterator();
    }

    public int hashCode() {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap == null) {
            return -1;
        }
        return linkedHashMap.hashCode();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isObject() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        return MissingNode.getInstance();
    }

    public JsonNode put(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return _put(str, jsonNode);
    }

    public JsonNode putAll(Map<String, JsonNode> map) {
        if (this._children == null) {
            this._children = new LinkedHashMap<>(map);
        } else {
            for (Map.Entry<String, JsonNode> entry : map.entrySet()) {
                JsonNode value = entry.getValue();
                if (value == null) {
                    value = nullNode();
                }
                this._children.put(entry.getKey(), value);
            }
        }
        return this;
    }

    public ArrayNode putArray(String str) {
        ArrayNode arrayNode = arrayNode();
        _put(str, arrayNode);
        return arrayNode;
    }

    protected void putContentsTo(Map<String, JsonNode> map) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void putNull(String str) {
        _put(str, nullNode());
    }

    public ObjectNode putObject(String str) {
        ObjectNode objectNode = objectNode();
        _put(str, objectNode);
        return objectNode;
    }

    public void putPOJO(String str, Object obj) {
        _put(str, POJONode(obj));
    }

    public JsonNode remove(String str) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            return linkedHashMap.remove(str);
        }
        return null;
    }

    public ObjectNode retain(Collection<String> collection) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            Iterator<Map.Entry<String, JsonNode>> it2 = linkedHashMap.entrySet().iterator();
            while (it2.hasNext()) {
                if (!collection.contains(it2.next().getKey())) {
                    it2.remove();
                }
            }
        }
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                jsonGenerator.writeFieldName(entry.getKey());
                ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        typeSerializer.writeTypePrefixForObject(this, jsonGenerator);
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                jsonGenerator.writeFieldName(entry.getKey());
                ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForObject(this, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.JsonNode
    public int size() {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap == null) {
            return 0;
        }
        return linkedHashMap.size();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 32);
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            int i = 0;
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                if (i > 0) {
                    sb.append(",");
                }
                i++;
                TextNode.appendQuoted(sb, entry.getKey());
                sb.append(JsonReaderKt.COLON);
                sb.append(entry.getValue().toString());
            }
        }
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return sb.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    /* renamed from: findParent  reason: collision with other method in class */
    public ObjectNode mo4258findParent(String str) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap != null) {
            for (Map.Entry<String, JsonNode> entry : linkedHashMap.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return this;
                }
                JsonNode mo4258findParent = entry.getValue().mo4258findParent(str);
                if (mo4258findParent != null) {
                    return (ObjectNode) mo4258findParent;
                }
            }
            return null;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        JsonNode jsonNode;
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        return (linkedHashMap == null || (jsonNode = linkedHashMap.get(str)) == null) ? MissingNode.getInstance() : jsonNode;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode
    /* renamed from: removeAll  reason: collision with other method in class */
    public ObjectNode mo4259removeAll() {
        this._children = null;
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    /* renamed from: with  reason: collision with other method in class */
    public ObjectNode mo4260with(String str) {
        LinkedHashMap<String, JsonNode> linkedHashMap = this._children;
        if (linkedHashMap == null) {
            this._children = new LinkedHashMap<>();
        } else {
            JsonNode jsonNode = linkedHashMap.get(str);
            if (jsonNode != null) {
                if (jsonNode instanceof ObjectNode) {
                    return (ObjectNode) jsonNode;
                }
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Property '", str, "' has value that is not of type ObjectNode (but ");
                outline115.append(jsonNode.getClass().getName());
                outline115.append(")");
                throw new UnsupportedOperationException(outline115.toString());
            }
        }
        ObjectNode objectNode = objectNode();
        this._children.put(str, objectNode);
        return objectNode;
    }

    public void put(String str, int i) {
        _put(str, numberNode(i));
    }

    public ObjectNode remove(Collection<String> collection) {
        if (this._children != null) {
            for (String str : collection) {
                this._children.remove(str);
            }
        }
        return this;
    }

    public void put(String str, Integer num) {
        if (num == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(num.intValue()));
        }
    }

    public void put(String str, long j) {
        _put(str, numberNode(j));
    }

    public void put(String str, Long l) {
        if (l == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(l.longValue()));
        }
    }

    public JsonNode putAll(ObjectNode objectNode) {
        int size = objectNode.size();
        if (size > 0) {
            if (this._children == null) {
                this._children = new LinkedHashMap<>(size);
            }
            objectNode.putContentsTo(this._children);
        }
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain(Arrays.asList(strArr));
    }

    public void put(String str, float f) {
        _put(str, numberNode(f));
    }

    public void put(String str, Float f) {
        if (f == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(f.floatValue()));
        }
    }

    public void put(String str, double d) {
        _put(str, numberNode(d));
    }

    public void put(String str, Double d) {
        if (d == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(d.doubleValue()));
        }
    }

    public void put(String str, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            putNull(str);
        } else {
            _put(str, numberNode(bigDecimal));
        }
    }

    public void put(String str, String str2) {
        if (str2 == null) {
            putNull(str);
        } else {
            _put(str, textNode(str2));
        }
    }

    public void put(String str, boolean z) {
        _put(str, booleanNode(z));
    }

    public void put(String str, Boolean bool) {
        if (bool == null) {
            _put(str, nullNode());
        } else {
            _put(str, booleanNode(bool.booleanValue()));
        }
    }

    public void put(String str, byte[] bArr) {
        if (bArr == null) {
            _put(str, nullNode());
        } else {
            _put(str, binaryNode(bArr));
        }
    }
}
