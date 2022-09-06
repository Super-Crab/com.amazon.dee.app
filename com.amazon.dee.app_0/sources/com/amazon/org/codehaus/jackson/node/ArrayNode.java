package com.amazon.org.codehaus.jackson.node;

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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public final class ArrayNode extends ContainerNode {
    protected ArrayList<JsonNode> _children;

    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
    }

    private void _add(JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new ArrayList<>();
        }
        this._children.add(jsonNode);
    }

    private void _insert(int i, JsonNode jsonNode) {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList == null) {
            this._children = new ArrayList<>();
            this._children.add(jsonNode);
        } else if (i < 0) {
            arrayList.add(0, jsonNode);
        } else if (i >= arrayList.size()) {
            this._children.add(jsonNode);
        } else {
            this._children.add(i, jsonNode);
        }
    }

    private boolean _sameChildren(ArrayList<JsonNode> arrayList) {
        int size = arrayList.size();
        if (size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this._children.get(i).equals(arrayList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public JsonNode _set(int i, JsonNode jsonNode) {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null && i >= 0 && i < arrayList.size()) {
            return this._children.set(i, jsonNode);
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Illegal index ", i, ", array size ");
        outline109.append(size());
        throw new IndexOutOfBoundsException(outline109.toString());
    }

    public void add(JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _add(jsonNode);
    }

    public JsonNode addAll(ArrayNode arrayNode) {
        int size = arrayNode.size();
        if (size > 0) {
            if (this._children == null) {
                this._children = new ArrayList<>(size + 2);
            }
            arrayNode.addContentsTo(this._children);
        }
        return this;
    }

    public ArrayNode addArray() {
        ArrayNode arrayNode = arrayNode();
        _add(arrayNode);
        return arrayNode;
    }

    protected void addContentsTo(List<JsonNode> list) {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                list.add(it2.next());
            }
        }
    }

    public void addNull() {
        _add(nullNode());
    }

    public ObjectNode addObject() {
        ObjectNode objectNode = objectNode();
        _add(objectNode);
        return objectNode;
    }

    public void addPOJO(Object obj) {
        if (obj == null) {
            addNull();
        } else {
            _add(POJONode(obj));
        }
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != ArrayNode.class) {
            return false;
        }
        ArrayNode arrayNode = (ArrayNode) obj;
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null && arrayList.size() != 0) {
            return arrayNode._sameChildren(this._children);
        }
        return arrayNode.size() == 0;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                list = it2.next().findParents(str, list);
            }
        }
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                JsonNode findValue = it2.next().findValue(str);
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
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                list = it2.next().findValues(str, list);
            }
        }
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                list = it2.next().findValuesAsText(str, list);
            }
        }
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode get(int i) {
        ArrayList<JsonNode> arrayList;
        if (i < 0 || (arrayList = this._children) == null || i >= arrayList.size()) {
            return null;
        }
        return this._children.get(i);
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode get(String str) {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public Iterator<JsonNode> getElements() {
        ArrayList<JsonNode> arrayList = this._children;
        return arrayList == null ? ContainerNode.NoNodesIterator.instance() : arrayList.iterator();
    }

    public int hashCode() {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList == null) {
            return 1;
        }
        int size = arrayList.size();
        Iterator<JsonNode> it2 = this._children.iterator();
        while (it2.hasNext()) {
            JsonNode next = it2.next();
            if (next != null) {
                size ^= next.hashCode();
            }
        }
        return size;
    }

    public void insert(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _insert(i, jsonNode);
    }

    public ArrayNode insertArray(int i) {
        ArrayNode arrayNode = arrayNode();
        _insert(i, arrayNode);
        return arrayNode;
    }

    public void insertNull(int i) {
        _insert(i, nullNode());
    }

    public ObjectNode insertObject(int i) {
        ObjectNode objectNode = objectNode();
        _insert(i, objectNode);
        return objectNode;
    }

    public void insertPOJO(int i, Object obj) {
        if (obj == null) {
            insertNull(i);
        } else {
            _insert(i, POJONode(obj));
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public boolean isArray() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        return MissingNode.getInstance();
    }

    public JsonNode remove(int i) {
        ArrayList<JsonNode> arrayList;
        if (i < 0 || (arrayList = this._children) == null || i >= arrayList.size()) {
            return null;
        }
        return this._children.remove(i);
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray();
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((BaseJsonNode) it2.next()).serialize(jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndArray();
    }

    @Override // com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        typeSerializer.writeTypePrefixForArray(this, jsonGenerator);
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((BaseJsonNode) it2.next()).serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForArray(this, jsonGenerator);
    }

    public JsonNode set(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return _set(i, jsonNode);
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.JsonNode
    public int size() {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 16);
        sb.append(JsonReaderKt.BEGIN_LIST);
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append(JsonReaderKt.COMMA);
                }
                sb.append(this._children.get(i).toString());
            }
        }
        sb.append(JsonReaderKt.END_LIST);
        return sb.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode, com.amazon.org.codehaus.jackson.node.BaseJsonNode, com.amazon.org.codehaus.jackson.JsonNode
    /* renamed from: findParent  reason: collision with other method in class */
    public ObjectNode mo4258findParent(String str) {
        ArrayList<JsonNode> arrayList = this._children;
        if (arrayList != null) {
            Iterator<JsonNode> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                JsonNode mo4258findParent = it2.next().mo4258findParent(str);
                if (mo4258findParent != null) {
                    return (ObjectNode) mo4258findParent;
                }
            }
            return null;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        ArrayList<JsonNode> arrayList;
        if (i >= 0 && (arrayList = this._children) != null && i < arrayList.size()) {
            return this._children.get(i);
        }
        return MissingNode.getInstance();
    }

    @Override // com.amazon.org.codehaus.jackson.node.ContainerNode
    /* renamed from: removeAll */
    public ArrayNode mo4259removeAll() {
        this._children = null;
        return this;
    }

    public void add(int i) {
        _add(numberNode(i));
    }

    public void insert(int i, int i2) {
        _insert(i, numberNode(i2));
    }

    public void add(Integer num) {
        if (num == null) {
            addNull();
        } else {
            _add(numberNode(num.intValue()));
        }
    }

    public void insert(int i, Integer num) {
        if (num == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(num.intValue()));
        }
    }

    public JsonNode addAll(Collection<JsonNode> collection) {
        if (collection.size() > 0) {
            ArrayList<JsonNode> arrayList = this._children;
            if (arrayList == null) {
                this._children = new ArrayList<>(collection);
            } else {
                arrayList.addAll(collection);
            }
        }
        return this;
    }

    public void add(long j) {
        _add(numberNode(j));
    }

    public void insert(int i, long j) {
        _insert(i, numberNode(j));
    }

    public void add(Long l) {
        if (l == null) {
            addNull();
        } else {
            _add(numberNode(l.longValue()));
        }
    }

    public void insert(int i, Long l) {
        if (l == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(l.longValue()));
        }
    }

    public void add(float f) {
        _add(numberNode(f));
    }

    public void insert(int i, float f) {
        _insert(i, numberNode(f));
    }

    public void add(Float f) {
        if (f == null) {
            addNull();
        } else {
            _add(numberNode(f.floatValue()));
        }
    }

    public void insert(int i, Float f) {
        if (f == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(f.floatValue()));
        }
    }

    public void add(double d) {
        _add(numberNode(d));
    }

    public void insert(int i, double d) {
        _insert(i, numberNode(d));
    }

    public void add(Double d) {
        if (d == null) {
            addNull();
        } else {
            _add(numberNode(d.doubleValue()));
        }
    }

    public void insert(int i, Double d) {
        if (d == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(d.doubleValue()));
        }
    }

    public void add(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            addNull();
        } else {
            _add(numberNode(bigDecimal));
        }
    }

    public void insert(int i, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(bigDecimal));
        }
    }

    public void add(String str) {
        if (str == null) {
            addNull();
        } else {
            _add(textNode(str));
        }
    }

    public void insert(int i, String str) {
        if (str == null) {
            insertNull(i);
        } else {
            _insert(i, textNode(str));
        }
    }

    public void add(boolean z) {
        _add(booleanNode(z));
    }

    public void insert(int i, boolean z) {
        _insert(i, booleanNode(z));
    }

    public void add(Boolean bool) {
        if (bool == null) {
            addNull();
        } else {
            _add(booleanNode(bool.booleanValue()));
        }
    }

    public void insert(int i, Boolean bool) {
        if (bool == null) {
            insertNull(i);
        } else {
            _insert(i, booleanNode(bool.booleanValue()));
        }
    }

    public void add(byte[] bArr) {
        if (bArr == null) {
            addNull();
        } else {
            _add(binaryNode(bArr));
        }
    }

    public void insert(int i, byte[] bArr) {
        if (bArr == null) {
            insertNull(i);
        } else {
            _insert(i, binaryNode(bArr));
        }
    }
}
