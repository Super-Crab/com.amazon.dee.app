package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.JsonSerializableWithType;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.io.IOException;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class SerializableWithTypeSerializer extends SerializerBase<JsonSerializableWithType> {
    public static final SerializableWithTypeSerializer instance = new SerializableWithTypeSerializer();

    /* JADX INFO: Access modifiers changed from: protected */
    public SerializableWithTypeSerializer() {
        super(JsonSerializableWithType.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
            if (r7 == 0) goto L40
            java.lang.Class r7 = com.amazon.org.codehaus.jackson.map.type.TypeFactory.rawClass(r7)
            java.lang.Class<com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema> r1 = com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema.class
            boolean r1 = r7.isAnnotationPresent(r1)
            if (r1 == 0) goto L40
            java.lang.Class<com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema> r1 = com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema.class
            java.lang.annotation.Annotation r7 = r7.getAnnotation(r1)
            com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema r7 = (com.amazon.org.codehaus.jackson.schema.JsonSerializableSchema) r7
            java.lang.String r1 = r7.schemaType()
            java.lang.String r2 = r7.schemaObjectPropertiesDefinition()
            java.lang.String r3 = "##irrelevant"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L30
            java.lang.String r2 = r7.schemaObjectPropertiesDefinition()
            goto L31
        L30:
            r2 = r0
        L31:
            java.lang.String r4 = r7.schemaItemDefinition()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L43
            java.lang.String r0 = r7.schemaItemDefinition()
            goto L43
        L40:
            java.lang.String r1 = "any"
            r2 = r0
        L43:
            java.lang.String r7 = "type"
            r6.put(r7, r1)
            if (r2 == 0) goto L64
            java.lang.String r7 = "properties"
            com.amazon.org.codehaus.jackson.map.ObjectMapper r1 = new com.amazon.org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L5d
            r1.<init>()     // Catch: java.io.IOException -> L5d
            java.lang.Class<com.amazon.org.codehaus.jackson.JsonNode> r3 = com.amazon.org.codehaus.jackson.JsonNode.class
            java.lang.Object r1 = r1.readValue(r2, r3)     // Catch: java.io.IOException -> L5d
            com.amazon.org.codehaus.jackson.JsonNode r1 = (com.amazon.org.codehaus.jackson.JsonNode) r1     // Catch: java.io.IOException -> L5d
            r6.put(r7, r1)     // Catch: java.io.IOException -> L5d
            goto L64
        L5d:
            r6 = move-exception
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>(r6)
            throw r7
        L64:
            if (r0 == 0) goto L80
            java.lang.String r7 = "items"
            com.amazon.org.codehaus.jackson.map.ObjectMapper r1 = new com.amazon.org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L79
            r1.<init>()     // Catch: java.io.IOException -> L79
            java.lang.Class<com.amazon.org.codehaus.jackson.JsonNode> r2 = com.amazon.org.codehaus.jackson.JsonNode.class
            java.lang.Object r0 = r1.readValue(r0, r2)     // Catch: java.io.IOException -> L79
            com.amazon.org.codehaus.jackson.JsonNode r0 = (com.amazon.org.codehaus.jackson.JsonNode) r0     // Catch: java.io.IOException -> L79
            r6.put(r7, r0)     // Catch: java.io.IOException -> L79
            goto L80
        L79:
            r6 = move-exception
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>(r6)
            throw r7
        L80:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer.getSchema(com.amazon.org.codehaus.jackson.map.SerializerProvider, java.lang.reflect.Type):com.amazon.org.codehaus.jackson.JsonNode");
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(JsonSerializableWithType jsonSerializableWithType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonSerializableWithType.serialize(jsonGenerator, serializerProvider);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(JsonSerializableWithType jsonSerializableWithType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        jsonSerializableWithType.serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
    }
}
