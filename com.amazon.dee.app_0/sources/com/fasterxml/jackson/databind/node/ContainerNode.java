package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes2.dex */
public abstract class ContainerNode<T extends ContainerNode<T>> extends BaseJsonNode implements JsonNodeCreator {
    private static final long serialVersionUID = 1;
    protected final JsonNodeFactory _nodeFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return "";
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.core.TreeNode
    public abstract JsonToken asToken();

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public abstract JsonNode mo7195get(int i);

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    /* renamed from: get  reason: collision with other method in class */
    public abstract JsonNode mo7196get(String str);

    public JsonNode missingNode() {
        return this._nodeFactory.missingNode();
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode pojoNode(Object obj) {
        return this._nodeFactory.pojoNode(obj);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode rawValueNode(RawValue rawValue) {
        return this._nodeFactory.rawValueNode(rawValue);
    }

    /* renamed from: removeAll */
    public abstract T mo7191removeAll();

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public abstract int size();

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ArrayNode arrayNode(int i) {
        return this._nodeFactory.arrayNode(i);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: booleanNode */
    public final BooleanNode mo7175booleanNode(boolean z) {
        return this._nodeFactory.mo7175booleanNode(z);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: nullNode */
    public final NullNode mo7176nullNode() {
        return this._nodeFactory.mo7176nullNode();
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: textNode */
    public final TextNode mo7183textNode(String str) {
        return this._nodeFactory.mo7183textNode(str);
    }

    protected ContainerNode() {
        this._nodeFactory = null;
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: binaryNode */
    public final BinaryNode mo7173binaryNode(byte[] bArr) {
        return this._nodeFactory.mo7173binaryNode(bArr);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: binaryNode */
    public final BinaryNode mo7174binaryNode(byte[] bArr, int i, int i2) {
        return this._nodeFactory.mo7174binaryNode(bArr, i, i2);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: numberNode */
    public final NumericNode mo7177numberNode(byte b) {
        return this._nodeFactory.mo7177numberNode(b);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: numberNode */
    public final NumericNode mo7182numberNode(short s) {
        return this._nodeFactory.mo7182numberNode(s);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: numberNode */
    public final NumericNode mo7180numberNode(int i) {
        return this._nodeFactory.mo7180numberNode(i);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: numberNode */
    public final NumericNode mo7181numberNode(long j) {
        return this._nodeFactory.mo7181numberNode(j);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: numberNode */
    public final NumericNode mo7179numberNode(float f) {
        return this._nodeFactory.mo7179numberNode(f);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    /* renamed from: numberNode */
    public final NumericNode mo7178numberNode(double d) {
        return this._nodeFactory.mo7178numberNode(d);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(BigInteger bigInteger) {
        return this._nodeFactory.numberNode(bigInteger);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(BigDecimal bigDecimal) {
        return this._nodeFactory.numberNode(bigDecimal);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(Byte b) {
        return this._nodeFactory.numberNode(b);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(Short sh) {
        return this._nodeFactory.numberNode(sh);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(Integer num) {
        return this._nodeFactory.numberNode(num);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(Long l) {
        return this._nodeFactory.numberNode(l);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(Float f) {
        return this._nodeFactory.numberNode(f);
    }

    @Override // com.fasterxml.jackson.databind.node.JsonNodeCreator
    public final ValueNode numberNode(Double d) {
        return this._nodeFactory.numberNode(d);
    }
}
