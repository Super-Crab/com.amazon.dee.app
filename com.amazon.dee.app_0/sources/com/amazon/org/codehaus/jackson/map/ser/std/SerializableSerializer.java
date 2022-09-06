package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.JsonSerializable;
import com.amazon.org.codehaus.jackson.map.JsonSerializableWithType;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.io.IOException;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class SerializableSerializer extends com.amazon.org.codehaus.jackson.map.ser.SerializerBase<JsonSerializable> {
    public static final SerializableSerializer instance = new SerializableSerializer();

    /* JADX INFO: Access modifiers changed from: protected */
    public SerializableSerializer() {
        super(JsonSerializable.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.org.codehaus.jackson.JsonNode getSchema(com.amazon.org.codehaus.jackson.map.SerializerProvider r6, java.lang.reflect.Type r7) throws com.amazon.org.codehaus.jackson.map.JsonMappingException {
        /*
            r5 = this;
            com.amazon.org.codehaus.jackson.node.ObjectNode r6 = r5.createObjectNode()
            r0 = 0
            if (r7 == 0) goto L44
            com.amazon.org.codehaus.jackson.type.JavaType r7 = com.amazon.org.codehaus.jackson.map.type.TypeFactory.type(r7)
            java.lang.Class r7 = r7.getRawClass()
            java.lang.Class<com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema> r1 = com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema.class
            boolean r1 = r7.isAnnotationPresent(r1)
            if (r1 == 0) goto L44
            java.lang.Class<com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema> r1 = com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema.class
            java.lang.annotation.Annotation r7 = r7.getAnnotation(r1)
            com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema r7 = (com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema) r7
            java.lang.String r1 = r7.schemaType()
            java.lang.String r2 = r7.schemaObjectPropertiesDefinition()
            java.lang.String r3 = "##irrelevant"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L34
            java.lang.String r2 = r7.schemaObjectPropertiesDefinition()
            goto L35
        L34:
            r2 = r0
        L35:
            java.lang.String r4 = r7.schemaItemDefinition()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L47
            java.lang.String r0 = r7.schemaItemDefinition()
            goto L47
        L44:
            java.lang.String r1 = "any"
            r2 = r0
        L47:
            java.lang.String r7 = "type"
            r6.put(r7, r1)
            if (r2 == 0) goto L68
            java.lang.String r7 = "properties"
            com.amazon.org.codehaus.jackson.map.ObjectMapper r1 = new com.amazon.org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L61
            r1.<init>()     // Catch: java.io.IOException -> L61
            java.lang.Class<com.amazon.org.codehaus.jackson.JsonNode> r3 = com.amazon.org.codehaus.jackson.JsonNode.class
            java.lang.Object r1 = r1.readValue(r2, r3)     // Catch: java.io.IOException -> L61
            com.amazon.org.codehaus.jackson.JsonNode r1 = (com.amazon.org.codehaus.jackson.JsonNode) r1     // Catch: java.io.IOException -> L61
            r6.put(r7, r1)     // Catch: java.io.IOException -> L61
            goto L68
        L61:
            r6 = move-exception
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>(r6)
            throw r7
        L68:
            if (r0 == 0) goto L84
            java.lang.String r7 = "items"
            com.amazon.org.codehaus.jackson.map.ObjectMapper r1 = new com.amazon.org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L7d
            r1.<init>()     // Catch: java.io.IOException -> L7d
            java.lang.Class<com.amazon.org.codehaus.jackson.JsonNode> r2 = com.amazon.org.codehaus.jackson.JsonNode.class
            java.lang.Object r0 = r1.readValue(r0, r2)     // Catch: java.io.IOException -> L7d
            com.amazon.org.codehaus.jackson.JsonNode r0 = (com.amazon.org.codehaus.jackson.JsonNode) r0     // Catch: java.io.IOException -> L7d
            r6.put(r7, r0)     // Catch: java.io.IOException -> L7d
            goto L84
        L7d:
            r6 = move-exception
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>(r6)
            throw r7
        L84:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.map.ser.std.SerializableSerializer.getSchema(com.amazon.org.codehaus.jackson.map.SerializerProvider, java.lang.reflect.Type):com.amazon.org.codehaus.jackson.JsonNode");
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonSerializable.serialize(jsonGenerator, serializerProvider);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        if (jsonSerializable instanceof JsonSerializableWithType) {
            ((JsonSerializableWithType) jsonSerializable).serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
        } else {
            serialize(jsonSerializable, jsonGenerator, serializerProvider);
        }
    }
}
