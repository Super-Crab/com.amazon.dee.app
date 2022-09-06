package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
/* loaded from: classes2.dex */
public interface TreeNode {
    JsonToken asToken();

    /* renamed from: at */
    TreeNode mo7025at(JsonPointer jsonPointer);

    /* renamed from: at */
    TreeNode mo7026at(String str) throws IllegalArgumentException;

    Iterator<String> fieldNames();

    /* renamed from: get */
    TreeNode mo7195get(int i);

    /* renamed from: get */
    TreeNode mo7196get(String str);

    boolean isArray();

    boolean isContainerNode();

    boolean isMissingNode();

    boolean isObject();

    boolean isValueNode();

    JsonParser.NumberType numberType();

    /* renamed from: path */
    TreeNode mo7197path(int i);

    /* renamed from: path */
    TreeNode mo7198path(String str);

    int size();

    JsonParser traverse();

    JsonParser traverse(ObjectCodec objectCodec);
}
