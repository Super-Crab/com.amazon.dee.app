package com.amazon.org.codehaus.jackson;

import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.type.TypeReference;
import java.io.IOException;
import java.util.Iterator;
/* loaded from: classes13.dex */
public abstract class ObjectCodec {
    /* renamed from: createArrayNode */
    public abstract JsonNode mo4113createArrayNode();

    /* renamed from: createObjectNode */
    public abstract JsonNode mo4114createObjectNode();

    public abstract JsonNode readTree(JsonParser jsonParser) throws IOException, JsonProcessingException;

    public abstract <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException;

    public abstract <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException;

    public abstract <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException;

    /* renamed from: readValues */
    public abstract <T> Iterator<T> mo4115readValues(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException;

    /* renamed from: readValues */
    public abstract <T> Iterator<T> mo4116readValues(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException;

    /* renamed from: readValues */
    public abstract <T> Iterator<T> mo4117readValues(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException;

    public abstract JsonParser treeAsTokens(JsonNode jsonNode);

    public abstract <T> T treeToValue(JsonNode jsonNode, Class<T> cls) throws IOException, JsonProcessingException;

    public abstract void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) throws IOException, JsonProcessingException;

    public abstract void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonProcessingException;
}
