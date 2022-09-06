package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Iterator;
/* loaded from: classes2.dex */
public abstract class ObjectCodec extends TreeCodec implements Versioned {
    @Override // com.fasterxml.jackson.core.TreeCodec
    /* renamed from: createArrayNode */
    public abstract TreeNode mo7040createArrayNode();

    @Override // com.fasterxml.jackson.core.TreeCodec
    /* renamed from: createObjectNode */
    public abstract TreeNode mo7041createObjectNode();

    public JsonFactory getFactory() {
        return getJsonFactory();
    }

    @Deprecated
    public JsonFactory getJsonFactory() {
        return getFactory();
    }

    @Override // com.fasterxml.jackson.core.TreeCodec
    public abstract <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException;

    public abstract <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException;

    public abstract <T> T readValue(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException;

    public abstract <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException;

    /* renamed from: readValues */
    public abstract <T> Iterator<T> mo7036readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException;

    /* renamed from: readValues */
    public abstract <T> Iterator<T> mo7037readValues(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException;

    /* renamed from: readValues */
    public abstract <T> Iterator<T> mo7038readValues(JsonParser jsonParser, Class<T> cls) throws IOException;

    @Override // com.fasterxml.jackson.core.TreeCodec
    public abstract JsonParser treeAsTokens(TreeNode treeNode);

    public abstract <T> T treeToValue(TreeNode treeNode, Class<T> cls) throws JsonProcessingException;

    @Override // com.fasterxml.jackson.core.Versioned
    public abstract Version version();

    @Override // com.fasterxml.jackson.core.TreeCodec
    public abstract void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException;

    public abstract void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException;
}
