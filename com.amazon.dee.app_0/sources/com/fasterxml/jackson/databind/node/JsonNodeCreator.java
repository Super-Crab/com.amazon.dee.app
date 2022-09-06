package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.util.RawValue;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes2.dex */
public interface JsonNodeCreator {
    ArrayNode arrayNode();

    ArrayNode arrayNode(int i);

    /* renamed from: binaryNode */
    ValueNode mo7173binaryNode(byte[] bArr);

    /* renamed from: binaryNode */
    ValueNode mo7174binaryNode(byte[] bArr, int i, int i2);

    /* renamed from: booleanNode */
    ValueNode mo7175booleanNode(boolean z);

    /* renamed from: nullNode */
    ValueNode mo7176nullNode();

    /* renamed from: numberNode */
    ValueNode mo7177numberNode(byte b);

    /* renamed from: numberNode */
    ValueNode mo7178numberNode(double d);

    /* renamed from: numberNode */
    ValueNode mo7179numberNode(float f);

    /* renamed from: numberNode */
    ValueNode mo7180numberNode(int i);

    /* renamed from: numberNode */
    ValueNode mo7181numberNode(long j);

    ValueNode numberNode(Byte b);

    ValueNode numberNode(Double d);

    ValueNode numberNode(Float f);

    ValueNode numberNode(Integer num);

    ValueNode numberNode(Long l);

    ValueNode numberNode(Short sh);

    ValueNode numberNode(BigDecimal bigDecimal);

    ValueNode numberNode(BigInteger bigInteger);

    /* renamed from: numberNode */
    ValueNode mo7182numberNode(short s);

    ObjectNode objectNode();

    ValueNode pojoNode(Object obj);

    ValueNode rawValueNode(RawValue rawValue);

    /* renamed from: textNode */
    ValueNode mo7183textNode(String str);
}
