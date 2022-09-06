package com.fasterxml.jackson.databind.node;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public class ArrayNode extends ContainerNode<ArrayNode> implements Serializable {
    private static final long serialVersionUID = 1;
    private final List<JsonNode> _children;

    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new ArrayList();
    }

    protected ArrayNode _add(JsonNode jsonNode) {
        this._children.add(jsonNode);
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    protected JsonNode _at(JsonPointer jsonPointer) {
        return mo7195get(jsonPointer.getMatchingIndex());
    }

    protected boolean _childrenEqual(ArrayNode arrayNode) {
        return this._children.equals(arrayNode._children);
    }

    protected ArrayNode _insert(int i, JsonNode jsonNode) {
        if (i < 0) {
            this._children.add(0, jsonNode);
        } else if (i >= this._children.size()) {
            this._children.add(jsonNode);
        } else {
            this._children.add(i, jsonNode);
        }
        return this;
    }

    public ArrayNode add(JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = mo7176nullNode();
        }
        _add(jsonNode);
        return this;
    }

    public ArrayNode addAll(ArrayNode arrayNode) {
        this._children.addAll(arrayNode._children);
        return this;
    }

    public ArrayNode addArray() {
        ArrayNode arrayNode = arrayNode();
        _add(arrayNode);
        return arrayNode;
    }

    public ArrayNode addNull() {
        _add(mo7176nullNode());
        return this;
    }

    public ObjectNode addObject() {
        ObjectNode objectNode = objectNode();
        _add(objectNode);
        return objectNode;
    }

    public ArrayNode addPOJO(Object obj) {
        if (obj == null) {
            addNull();
        } else {
            _add(pojoNode(obj));
        }
        return this;
    }

    public ArrayNode addRawValue(RawValue rawValue) {
        if (rawValue == null) {
            addNull();
        } else {
            _add(rawValueNode(rawValue));
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean equals(Comparator<JsonNode> comparator, JsonNode jsonNode) {
        if (!(jsonNode instanceof ArrayNode)) {
            return false;
        }
        ArrayNode arrayNode = (ArrayNode) jsonNode;
        int size = this._children.size();
        if (arrayNode.size() != size) {
            return false;
        }
        List<JsonNode> list = this._children;
        List<JsonNode> list2 = arrayNode._children;
        for (int i = 0; i < size; i++) {
            if (!list.get(i).equals(comparator, list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        for (JsonNode jsonNode : this._children) {
            list = jsonNode.findParents(str, list);
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNode findValue(String str) {
        for (JsonNode jsonNode : this._children) {
            JsonNode findValue = jsonNode.findValue(str);
            if (findValue != null) {
                return findValue;
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        for (JsonNode jsonNode : this._children) {
            list = jsonNode.findValues(str, list);
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        for (JsonNode jsonNode : this._children) {
            list = jsonNode.findValuesAsText(str, list);
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public JsonNode mo7196get(String str) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNodeType getNodeType() {
        return JsonNodeType.ARRAY;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode
    public int hashCode() {
        return this._children.hashCode();
    }

    public ArrayNode insert(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = mo7176nullNode();
        }
        _insert(i, jsonNode);
        return this;
    }

    public ArrayNode insertArray(int i) {
        ArrayNode arrayNode = arrayNode();
        _insert(i, arrayNode);
        return arrayNode;
    }

    public ArrayNode insertNull(int i) {
        _insert(i, mo7176nullNode());
        return this;
    }

    public ObjectNode insertObject(int i) {
        ObjectNode objectNode = objectNode();
        _insert(i, objectNode);
        return objectNode;
    }

    public ArrayNode insertPOJO(int i, Object obj) {
        if (obj == null) {
            return insertNull(i);
        }
        return _insert(i, pojoNode(obj));
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public boolean isArray() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializable.Base
    public boolean isEmpty(SerializerProvider serializerProvider) {
        return this._children.isEmpty();
    }

    public JsonNode remove(int i) {
        if (i < 0 || i >= this._children.size()) {
            return null;
        }
        return this._children.remove(i);
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonNode
    public JsonNode required(int i) {
        if (i >= 0 && i < this._children.size()) {
            return this._children.get(i);
        }
        return (JsonNode) _reportRequiredViolation("No value at index #%d [0, %d) of `ArrayNode`", Integer.valueOf(i), Integer.valueOf(this._children.size()));
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<JsonNode> list = this._children;
        int size = list.size();
        jsonGenerator.writeStartArray(this, size);
        for (int i = 0; i < size; i++) {
            ((BaseJsonNode) list.get(i)).serialize(jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(this, JsonToken.START_ARRAY));
        Iterator<JsonNode> it2 = this._children.iterator();
        while (it2.hasNext()) {
            ((BaseJsonNode) it2.next()).serialize(jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    public JsonNode set(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = mo7176nullNode();
        }
        if (i >= 0 && i < this._children.size()) {
            return this._children.set(i, jsonNode);
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Illegal index ", i, ", array size ");
        outline109.append(size());
        throw new IndexOutOfBoundsException(outline109.toString());
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public int size() {
        return this._children.size();
    }

    public ArrayNode addAll(Collection<? extends JsonNode> collection) {
        for (JsonNode jsonNode : collection) {
            add(jsonNode);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    /* renamed from: deepCopy  reason: collision with other method in class */
    public ArrayNode mo7185deepCopy() {
        ArrayNode arrayNode = new ArrayNode(this._nodeFactory);
        for (JsonNode jsonNode : this._children) {
            arrayNode._children.add(jsonNode.mo7185deepCopy());
        }
        return arrayNode;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    /* renamed from: findParent  reason: collision with other method in class */
    public ObjectNode mo7194findParent(String str) {
        for (JsonNode jsonNode : this._children) {
            JsonNode mo7194findParent = jsonNode.mo7194findParent(str);
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

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.node.ContainerNode
    /* renamed from: removeAll */
    public ArrayNode mo7191removeAll() {
        this._children.clear();
        return this;
    }

    public ArrayNode(JsonNodeFactory jsonNodeFactory, int i) {
        super(jsonNodeFactory);
        this._children = new ArrayList(i);
    }

    public ArrayNode add(int i) {
        _add(mo7180numberNode(i));
        return this;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public JsonNode mo7195get(int i) {
        if (i < 0 || i >= this._children.size()) {
            return null;
        }
        return this._children.get(i);
    }

    public ArrayNode insert(int i, int i2) {
        _insert(i, mo7180numberNode(i2));
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: path  reason: collision with other method in class */
    public JsonNode mo7198path(String str) {
        return MissingNode.getInstance();
    }

    public ArrayNode add(Integer num) {
        if (num == null) {
            return addNull();
        }
        return _add(mo7180numberNode(num.intValue()));
    }

    public ArrayNode insert(int i, Integer num) {
        if (num == null) {
            insertNull(i);
        } else {
            _insert(i, mo7180numberNode(num.intValue()));
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: path  reason: collision with other method in class */
    public JsonNode mo7197path(int i) {
        if (i >= 0 && i < this._children.size()) {
            return this._children.get(i);
        }
        return MissingNode.getInstance();
    }

    public ArrayNode(JsonNodeFactory jsonNodeFactory, List<JsonNode> list) {
        super(jsonNodeFactory);
        this._children = list;
    }

    public ArrayNode add(long j) {
        return _add(mo7181numberNode(j));
    }

    public ArrayNode insert(int i, long j) {
        return _insert(i, mo7181numberNode(j));
    }

    public ArrayNode add(Long l) {
        if (l == null) {
            return addNull();
        }
        return _add(mo7181numberNode(l.longValue()));
    }

    public ArrayNode insert(int i, Long l) {
        if (l == null) {
            return insertNull(i);
        }
        return _insert(i, mo7181numberNode(l.longValue()));
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ArrayNode)) {
            return false;
        }
        return this._children.equals(((ArrayNode) obj)._children);
    }

    public ArrayNode add(float f) {
        return _add(mo7179numberNode(f));
    }

    public ArrayNode insert(int i, float f) {
        return _insert(i, mo7179numberNode(f));
    }

    public ArrayNode add(Float f) {
        if (f == null) {
            return addNull();
        }
        return _add(mo7179numberNode(f.floatValue()));
    }

    public ArrayNode insert(int i, Float f) {
        if (f == null) {
            return insertNull(i);
        }
        return _insert(i, mo7179numberNode(f.floatValue()));
    }

    public ArrayNode add(double d) {
        return _add(mo7178numberNode(d));
    }

    public ArrayNode insert(int i, double d) {
        return _insert(i, mo7178numberNode(d));
    }

    public ArrayNode add(Double d) {
        if (d == null) {
            return addNull();
        }
        return _add(mo7178numberNode(d.doubleValue()));
    }

    public ArrayNode insert(int i, Double d) {
        if (d == null) {
            return insertNull(i);
        }
        return _insert(i, mo7178numberNode(d.doubleValue()));
    }

    public ArrayNode add(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return addNull();
        }
        return _add(numberNode(bigDecimal));
    }

    public ArrayNode insert(int i, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return insertNull(i);
        }
        return _insert(i, numberNode(bigDecimal));
    }

    public ArrayNode add(BigInteger bigInteger) {
        if (bigInteger == null) {
            return addNull();
        }
        return _add(numberNode(bigInteger));
    }

    public ArrayNode insert(int i, BigInteger bigInteger) {
        if (bigInteger == null) {
            return insertNull(i);
        }
        return _insert(i, numberNode(bigInteger));
    }

    public ArrayNode add(String str) {
        if (str == null) {
            return addNull();
        }
        return _add(mo7183textNode(str));
    }

    public ArrayNode insert(int i, String str) {
        if (str == null) {
            return insertNull(i);
        }
        return _insert(i, mo7183textNode(str));
    }

    public ArrayNode add(boolean z) {
        return _add(mo7175booleanNode(z));
    }

    public ArrayNode insert(int i, boolean z) {
        return _insert(i, mo7175booleanNode(z));
    }

    public ArrayNode add(Boolean bool) {
        if (bool == null) {
            return addNull();
        }
        return _add(mo7175booleanNode(bool.booleanValue()));
    }

    public ArrayNode insert(int i, Boolean bool) {
        if (bool == null) {
            return insertNull(i);
        }
        return _insert(i, mo7175booleanNode(bool.booleanValue()));
    }

    public ArrayNode add(byte[] bArr) {
        if (bArr == null) {
            return addNull();
        }
        return _add(mo7173binaryNode(bArr));
    }

    public ArrayNode insert(int i, byte[] bArr) {
        if (bArr == null) {
            return insertNull(i);
        }
        return _insert(i, mo7173binaryNode(bArr));
    }
}
