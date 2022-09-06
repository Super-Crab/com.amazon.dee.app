package com.fasterxml.jackson.databind;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class JsonNode extends JsonSerializable.Base implements TreeNode, Iterable<JsonNode> {

    /* renamed from: com.fasterxml.jackson.databind.JsonNode$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType = new int[JsonNodeType.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[JsonNodeType.ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[JsonNodeType.OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[JsonNodeType.MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected abstract JsonNode _at(JsonPointer jsonPointer);

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T _reportRequiredViolation(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected <T extends JsonNode> T _this() {
        return this;
    }

    public boolean asBoolean() {
        return asBoolean(false);
    }

    public boolean asBoolean(boolean z) {
        return z;
    }

    public double asDouble() {
        return asDouble(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    public double asDouble(double d) {
        return d;
    }

    public int asInt() {
        return asInt(0);
    }

    public int asInt(int i) {
        return i;
    }

    public long asLong() {
        return asLong(0L);
    }

    public long asLong(long j) {
        return j;
    }

    public abstract String asText();

    public String asText(String str) {
        String asText = asText();
        return asText == null ? str : asText;
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.ZERO;
    }

    public byte[] binaryValue() throws IOException {
        return null;
    }

    public boolean booleanValue() {
        return false;
    }

    public boolean canConvertToExactIntegral() {
        return isIntegralNumber();
    }

    public boolean canConvertToInt() {
        return false;
    }

    public boolean canConvertToLong() {
        return false;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.ZERO;
    }

    /* renamed from: deepCopy */
    public abstract <T extends JsonNode> T mo7185deepCopy();

    public double doubleValue() {
        return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public Iterator<JsonNode> elements() {
        return ClassUtil.emptyIterator();
    }

    public abstract boolean equals(Object obj);

    public boolean equals(Comparator<JsonNode> comparator, JsonNode jsonNode) {
        return comparator.compare(this, jsonNode) == 0;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public Iterator<String> fieldNames() {
        return ClassUtil.emptyIterator();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return ClassUtil.emptyIterator();
    }

    /* renamed from: findParent */
    public abstract JsonNode mo7194findParent(String str);

    public final List<JsonNode> findParents(String str) {
        List<JsonNode> findParents = findParents(str, null);
        return findParents == null ? Collections.emptyList() : findParents;
    }

    public abstract List<JsonNode> findParents(String str, List<JsonNode> list);

    public abstract JsonNode findPath(String str);

    public abstract JsonNode findValue(String str);

    public final List<JsonNode> findValues(String str) {
        List<JsonNode> findValues = findValues(str, null);
        return findValues == null ? Collections.emptyList() : findValues;
    }

    public abstract List<JsonNode> findValues(String str, List<JsonNode> list);

    public final List<String> findValuesAsText(String str) {
        List<String> findValuesAsText = findValuesAsText(str, null);
        return findValuesAsText == null ? Collections.emptyList() : findValuesAsText;
    }

    public abstract List<String> findValuesAsText(String str, List<String> list);

    public float floatValue() {
        return 0.0f;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public abstract JsonNode mo7195get(int i);

    @Override // com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public JsonNode mo7196get(String str) {
        return null;
    }

    public abstract JsonNodeType getNodeType();

    public boolean has(String str) {
        return mo7196get(str) != null;
    }

    public boolean hasNonNull(String str) {
        JsonNode mo7196get = mo7196get(str);
        return mo7196get != null && !mo7196get.isNull();
    }

    public int intValue() {
        return 0;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public boolean isArray() {
        return false;
    }

    public boolean isBigDecimal() {
        return false;
    }

    public boolean isBigInteger() {
        return false;
    }

    public final boolean isBinary() {
        return getNodeType() == JsonNodeType.BINARY;
    }

    public final boolean isBoolean() {
        return getNodeType() == JsonNodeType.BOOLEAN;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public final boolean isContainerNode() {
        JsonNodeType nodeType = getNodeType();
        return nodeType == JsonNodeType.OBJECT || nodeType == JsonNodeType.ARRAY;
    }

    public boolean isDouble() {
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFloat() {
        return false;
    }

    public boolean isFloatingPointNumber() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isIntegralNumber() {
        return false;
    }

    public boolean isLong() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public boolean isMissingNode() {
        return false;
    }

    public final boolean isNull() {
        return getNodeType() == JsonNodeType.NULL;
    }

    public final boolean isNumber() {
        return getNodeType() == JsonNodeType.NUMBER;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public boolean isObject() {
        return false;
    }

    public final boolean isPojo() {
        return getNodeType() == JsonNodeType.POJO;
    }

    public boolean isShort() {
        return false;
    }

    public final boolean isTextual() {
        return getNodeType() == JsonNodeType.STRING;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public final boolean isValueNode() {
        int ordinal = getNodeType().ordinal();
        return (ordinal == 0 || ordinal == 3 || ordinal == 6) ? false : true;
    }

    @Override // java.lang.Iterable
    public final Iterator<JsonNode> iterator() {
        return elements();
    }

    public long longValue() {
        return 0L;
    }

    public Number numberValue() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    /* renamed from: path  reason: collision with other method in class */
    public abstract JsonNode mo7197path(int i);

    @Override // com.fasterxml.jackson.core.TreeNode
    /* renamed from: path  reason: collision with other method in class */
    public abstract JsonNode mo7198path(String str);

    public <T extends JsonNode> T require() throws IllegalArgumentException {
        return (T) _this();
    }

    public <T extends JsonNode> T requireNonNull() throws IllegalArgumentException {
        return (T) _this();
    }

    public JsonNode required(String str) throws IllegalArgumentException {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no fields", getClass().getName());
    }

    public JsonNode requiredAt(String str) throws IllegalArgumentException {
        return requiredAt(JsonPointer.compile(str));
    }

    public short shortValue() {
        return (short) 0;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public int size() {
        return 0;
    }

    public String textValue() {
        return null;
    }

    public String toPrettyString() {
        return toString();
    }

    public abstract String toString();

    /* renamed from: with */
    public <T extends JsonNode> T mo7192with(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JsonNode not of type ObjectNode (but ");
        outline107.append(getClass().getName());
        outline107.append("), cannot call with() on it");
        throw new UnsupportedOperationException(outline107.toString());
    }

    /* renamed from: withArray */
    public <T extends JsonNode> T mo7193withArray(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JsonNode not of type ObjectNode (but ");
        outline107.append(getClass().getName());
        outline107.append("), cannot call withArray() on it");
        throw new UnsupportedOperationException(outline107.toString());
    }

    public boolean has(int i) {
        return mo7195get(i) != null;
    }

    public JsonNode required(int i) throws IllegalArgumentException {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no indexed values", getClass().getName());
    }

    public final JsonNode requiredAt(JsonPointer jsonPointer) throws IllegalArgumentException {
        JsonNode jsonNode = this;
        for (JsonPointer jsonPointer2 = jsonPointer; !jsonPointer2.matches(); jsonPointer2 = jsonPointer2.tail()) {
            jsonNode = jsonNode._at(jsonPointer2);
            if (jsonNode == null) {
                _reportRequiredViolation("No node at '%s' (unmatched part: '%s')", jsonPointer, jsonPointer2);
            }
        }
        return jsonNode;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    /* renamed from: at  reason: collision with other method in class */
    public final JsonNode mo7025at(JsonPointer jsonPointer) {
        if (jsonPointer.matches()) {
            return this;
        }
        JsonNode _at = _at(jsonPointer);
        if (_at == null) {
            return MissingNode.getInstance();
        }
        return _at.mo7025at(jsonPointer.tail());
    }

    public boolean hasNonNull(int i) {
        JsonNode mo7195get = mo7195get(i);
        return mo7195get != null && !mo7195get.isNull();
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    /* renamed from: at  reason: collision with other method in class */
    public final JsonNode mo7026at(String str) {
        return mo7025at(JsonPointer.compile(str));
    }
}
