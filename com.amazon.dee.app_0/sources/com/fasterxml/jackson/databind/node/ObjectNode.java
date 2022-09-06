package com.fasterxml.jackson.databind.node;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class ObjectNode extends ContainerNode<ObjectNode> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Map<String, JsonNode> _children;

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new LinkedHashMap();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    protected JsonNode _at(JsonPointer jsonPointer) {
        return mo7196get(jsonPointer.getMatchingProperty());
    }

    protected boolean _childrenEqual(ObjectNode objectNode) {
        return this._children.equals(objectNode._children);
    }

    protected ObjectNode _put(String str, JsonNode jsonNode) {
        this._children.put(str, jsonNode);
        return this;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    @Override // com.fasterxml.jackson.databind.JsonNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.util.Comparator<com.fasterxml.jackson.databind.JsonNode> r5, com.fasterxml.jackson.databind.JsonNode r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.fasterxml.jackson.databind.node.ObjectNode
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            com.fasterxml.jackson.databind.node.ObjectNode r6 = (com.fasterxml.jackson.databind.node.ObjectNode) r6
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r0 = r4._children
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r6 = r6._children
            int r2 = r0.size()
            int r3 = r6.size()
            if (r3 == r2) goto L17
            return r1
        L17:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L1f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L44
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r3 = r6.get(r3)
            com.fasterxml.jackson.databind.JsonNode r3 = (com.fasterxml.jackson.databind.JsonNode) r3
            if (r3 == 0) goto L43
            java.lang.Object r2 = r2.getValue()
            com.fasterxml.jackson.databind.JsonNode r2 = (com.fasterxml.jackson.databind.JsonNode) r2
            boolean r2 = r2.equals(r5, r3)
            if (r2 != 0) goto L1f
        L43:
            return r1
        L44:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.ObjectNode.equals(java.util.Comparator, com.fasterxml.jackson.databind.JsonNode):boolean");
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public Iterator<String> fieldNames() {
        return this._children.keySet().iterator();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return this._children.entrySet().iterator();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(this);
            } else {
                list = entry.getValue().findParents(str, list);
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNode findValue(String str) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
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

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(entry.getValue());
            } else {
                list = entry.getValue().findValues(str, list);
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(entry.getValue().asText());
            } else {
                list = entry.getValue().findValuesAsText(str, list);
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public JsonNode mo7195get(int i) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNodeType getNodeType() {
        return JsonNodeType.OBJECT;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode
    public int hashCode() {
        return this._children.hashCode();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializable.Base
    public boolean isEmpty(SerializerProvider serializerProvider) {
        return this._children.isEmpty();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public final boolean isObject() {
        return true;
    }

    @Deprecated
    public JsonNode put(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = mo7176nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    @Deprecated
    public JsonNode putAll(Map<String, ? extends JsonNode> map) {
        return setAll(map);
    }

    public ArrayNode putArray(String str) {
        ArrayNode arrayNode = arrayNode();
        _put(str, arrayNode);
        return arrayNode;
    }

    public ObjectNode putNull(String str) {
        this._children.put(str, mo7176nullNode());
        return this;
    }

    public ObjectNode putObject(String str) {
        ObjectNode objectNode = objectNode();
        _put(str, objectNode);
        return objectNode;
    }

    public ObjectNode putPOJO(String str, Object obj) {
        return _put(str, pojoNode(obj));
    }

    public ObjectNode putRawValue(String str, RawValue rawValue) {
        return _put(str, rawValueNode(rawValue));
    }

    public JsonNode remove(String str) {
        return this._children.remove(str);
    }

    public JsonNode replace(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = mo7176nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonNode
    public JsonNode required(String str) {
        JsonNode jsonNode = this._children.get(str);
        return jsonNode != null ? jsonNode : (JsonNode) _reportRequiredViolation("No value for property '%s' of `ObjectNode`", str);
    }

    public ObjectNode retain(Collection<String> collection) {
        this._children.keySet().retainAll(collection);
        return this;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        boolean z = serializerProvider != null && !serializerProvider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        jsonGenerator.writeStartObject(this);
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            BaseJsonNode baseJsonNode = (BaseJsonNode) entry.getValue();
            if (!z || !baseJsonNode.isArray() || !baseJsonNode.isEmpty(serializerProvider)) {
                jsonGenerator.writeFieldName(entry.getKey());
                baseJsonNode.serialize(jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        boolean z = serializerProvider != null && !serializerProvider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(this, JsonToken.START_OBJECT));
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            BaseJsonNode baseJsonNode = (BaseJsonNode) entry.getValue();
            if (!z || !baseJsonNode.isArray() || !baseJsonNode.isEmpty(serializerProvider)) {
                jsonGenerator.writeFieldName(entry.getKey());
                baseJsonNode.serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    public <T extends JsonNode> T set(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = mo7176nullNode();
        }
        this._children.put(str, jsonNode);
        return this;
    }

    public <T extends JsonNode> T setAll(Map<String, ? extends JsonNode> map) {
        for (Map.Entry<String, ? extends JsonNode> entry : map.entrySet()) {
            JsonNode value = entry.getValue();
            if (value == null) {
                value = mo7176nullNode();
            }
            this._children.put(entry.getKey(), value);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public int size() {
        return this._children.size();
    }

    public <T extends JsonNode> T without(String str) {
        this._children.remove(str);
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    /* renamed from: deepCopy  reason: collision with other method in class */
    public ObjectNode mo7185deepCopy() {
        ObjectNode objectNode = new ObjectNode(this._nodeFactory);
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            objectNode._children.put(entry.getKey(), entry.getValue().mo7185deepCopy());
        }
        return objectNode;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    /* renamed from: findParent  reason: collision with other method in class */
    public ObjectNode mo7194findParent(String str) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                return this;
            }
            JsonNode mo7194findParent = entry.getValue().mo7194findParent(str);
            if (mo7194findParent != null) {
                return (ObjectNode) mo7194findParent;
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean isEmpty() {
        return this._children.isEmpty();
    }

    @Deprecated
    public JsonNode putAll(ObjectNode objectNode) {
        return setAll(objectNode);
    }

    public ObjectNode remove(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode
    /* renamed from: removeAll  reason: collision with other method in class */
    public ObjectNode mo7191removeAll() {
        this._children.clear();
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain(Arrays.asList(strArr));
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    /* renamed from: with  reason: collision with other method in class */
    public ObjectNode mo7192with(String str) {
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode != null) {
            if (jsonNode instanceof ObjectNode) {
                return (ObjectNode) jsonNode;
            }
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Property '", str, "' has value that is not of type ObjectNode (but ");
            outline115.append(jsonNode.getClass().getName());
            outline115.append(")");
            throw new UnsupportedOperationException(outline115.toString());
        }
        ObjectNode objectNode = objectNode();
        this._children.put(str, objectNode);
        return objectNode;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    /* renamed from: withArray  reason: collision with other method in class */
    public ArrayNode mo7193withArray(String str) {
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode != null) {
            if (jsonNode instanceof ArrayNode) {
                return (ArrayNode) jsonNode;
            }
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Property '", str, "' has value that is not of type ArrayNode (but ");
            outline115.append(jsonNode.getClass().getName());
            outline115.append(")");
            throw new UnsupportedOperationException(outline115.toString());
        }
        ArrayNode arrayNode = arrayNode();
        this._children.put(str, arrayNode);
        return arrayNode;
    }

    public <T extends JsonNode> T without(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory, Map<String, JsonNode> map) {
        super(jsonNodeFactory);
        this._children = map;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public JsonNode mo7196get(String str) {
        return this._children.get(str);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: path  reason: collision with other method in class */
    public JsonNode mo7197path(int i) {
        return MissingNode.getInstance();
    }

    public ObjectNode put(String str, short s) {
        return _put(str, mo7182numberNode(s));
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: path  reason: collision with other method in class */
    public JsonNode mo7198path(String str) {
        JsonNode jsonNode = this._children.get(str);
        return jsonNode != null ? jsonNode : MissingNode.getInstance();
    }

    public ObjectNode put(String str, Short sh) {
        JsonNode mo7182numberNode;
        if (sh == null) {
            mo7182numberNode = mo7176nullNode();
        } else {
            mo7182numberNode = mo7182numberNode(sh.shortValue());
        }
        return _put(str, mo7182numberNode);
    }

    public <T extends JsonNode> T setAll(ObjectNode objectNode) {
        this._children.putAll(objectNode._children);
        return this;
    }

    public ObjectNode put(String str, int i) {
        return _put(str, mo7180numberNode(i));
    }

    public ObjectNode put(String str, Integer num) {
        JsonNode mo7180numberNode;
        if (num == null) {
            mo7180numberNode = mo7176nullNode();
        } else {
            mo7180numberNode = mo7180numberNode(num.intValue());
        }
        return _put(str, mo7180numberNode);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ObjectNode)) {
            return false;
        }
        return _childrenEqual((ObjectNode) obj);
    }

    public ObjectNode put(String str, long j) {
        return _put(str, mo7181numberNode(j));
    }

    public ObjectNode put(String str, Long l) {
        JsonNode mo7181numberNode;
        if (l == null) {
            mo7181numberNode = mo7176nullNode();
        } else {
            mo7181numberNode = mo7181numberNode(l.longValue());
        }
        return _put(str, mo7181numberNode);
    }

    public ObjectNode put(String str, float f) {
        return _put(str, mo7179numberNode(f));
    }

    public ObjectNode put(String str, Float f) {
        JsonNode mo7179numberNode;
        if (f == null) {
            mo7179numberNode = mo7176nullNode();
        } else {
            mo7179numberNode = mo7179numberNode(f.floatValue());
        }
        return _put(str, mo7179numberNode);
    }

    public ObjectNode put(String str, double d) {
        return _put(str, mo7178numberNode(d));
    }

    public ObjectNode put(String str, Double d) {
        JsonNode mo7178numberNode;
        if (d == null) {
            mo7178numberNode = mo7176nullNode();
        } else {
            mo7178numberNode = mo7178numberNode(d.doubleValue());
        }
        return _put(str, mo7178numberNode);
    }

    public ObjectNode put(String str, BigDecimal bigDecimal) {
        ValueNode numberNode;
        if (bigDecimal == null) {
            numberNode = mo7176nullNode();
        } else {
            numberNode = numberNode(bigDecimal);
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, BigInteger bigInteger) {
        ValueNode numberNode;
        if (bigInteger == null) {
            numberNode = mo7176nullNode();
        } else {
            numberNode = numberNode(bigInteger);
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, String str2) {
        JsonNode mo7183textNode;
        if (str2 == null) {
            mo7183textNode = mo7176nullNode();
        } else {
            mo7183textNode = mo7183textNode(str2);
        }
        return _put(str, mo7183textNode);
    }

    public ObjectNode put(String str, boolean z) {
        return _put(str, mo7175booleanNode(z));
    }

    public ObjectNode put(String str, Boolean bool) {
        JsonNode mo7175booleanNode;
        if (bool == null) {
            mo7175booleanNode = mo7176nullNode();
        } else {
            mo7175booleanNode = mo7175booleanNode(bool.booleanValue());
        }
        return _put(str, mo7175booleanNode);
    }

    public ObjectNode put(String str, byte[] bArr) {
        JsonNode mo7173binaryNode;
        if (bArr == null) {
            mo7173binaryNode = mo7176nullNode();
        } else {
            mo7173binaryNode = mo7173binaryNode(bArr);
        }
        return _put(str, mo7173binaryNode);
    }
}
