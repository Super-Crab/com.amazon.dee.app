package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
/* loaded from: classes2.dex */
public interface JsonMapFormatVisitor extends JsonFormatVisitorWithSerializerProvider {

    /* loaded from: classes2.dex */
    public static class Base implements JsonMapFormatVisitor {
        protected SerializerProvider _provider;

        public Base() {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider
        public SerializerProvider getProvider() {
            return this._provider;
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor
        public void keyFormat(JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider
        public void setProvider(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor
        public void valueFormat(JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException {
        }

        public Base(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }
    }

    void keyFormat(JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException;

    void valueFormat(JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException;
}
