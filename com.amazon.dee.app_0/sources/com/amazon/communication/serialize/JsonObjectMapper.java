package com.amazon.communication.serialize;

import amazon.communication.serialize.ObjectMapper;
import amazon.communication.serialize.TypeReference;
import com.amazon.dp.logger.DPLogger;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.Version;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.DeserializationProblemHandler;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.module.SimpleModule;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class JsonObjectMapper implements ObjectMapper {
    private static final DPLogger log = new DPLogger("TComm.JsonObjectMapper");
    private static final List<String> IGNORABLE_FIELDS = new ArrayList(Arrays.asList("__type"));
    private static final com.amazon.org.codehaus.jackson.map.ObjectMapper JSON_MAPPER = new com.amazon.org.codehaus.jackson.map.ObjectMapper();
    private static volatile boolean sRegisteredModule = false;

    public JsonObjectMapper() {
        synchronized (JSON_MAPPER) {
            if (!sRegisteredModule) {
                SimpleModule simpleModule = new SimpleModule("CoralExtension", new Version(1, 0, 0, null));
                simpleModule.addSerializer(ByteBuffer.class, new ByteBufferJsonSerializer());
                simpleModule.addDeserializer(ByteBuffer.class, new ByteBufferJsonDeserializer());
                JSON_MAPPER.registerModule(simpleModule);
                JSON_MAPPER.getDeserializationConfig().addHandler(new DeserializationProblemHandler() { // from class: com.amazon.communication.serialize.JsonObjectMapper.1
                    @Override // com.amazon.org.codehaus.jackson.map.DeserializationProblemHandler
                    public boolean handleUnknownProperty(DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
                        if (!JsonObjectMapper.IGNORABLE_FIELDS.contains(str)) {
                            JsonObjectMapper.log.debug("JsonObjectMapper", "Unknown field in Json input", "propertyName", str);
                        }
                        deserializationContext.getParser().skipChildren();
                        return true;
                    }
                });
                sRegisteredModule = true;
            }
        }
    }

    @Override // amazon.communication.serialize.ObjectMapper
    public <T> T deserialize(InputStream inputStream, Class<T> cls) throws IOException {
        try {
            return (T) JSON_MAPPER.readValue(inputStream, cls);
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e);
        } catch (JsonMappingException e2) {
            throw new IllegalArgumentException(e2);
        } catch (IOException e3) {
            throw e3;
        }
    }

    @Override // amazon.communication.serialize.ObjectMapper
    public <T> ByteBuffer serialize(T t) {
        try {
            return ByteBuffer.wrap(JSON_MAPPER.writeValueAsBytes(t));
        } catch (JsonGenerationException e) {
            throw new IllegalArgumentException(e);
        } catch (JsonMappingException e2) {
            throw new IllegalArgumentException(e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    @Override // amazon.communication.serialize.ObjectMapper
    public <T> T deserialize(InputStream inputStream, final TypeReference<T> typeReference) throws IOException {
        try {
            return (T) JSON_MAPPER.readValue(inputStream, new com.amazon.org.codehaus.jackson.type.TypeReference<T>() { // from class: com.amazon.communication.serialize.JsonObjectMapper.2
                @Override // com.amazon.org.codehaus.jackson.type.TypeReference
                public Type getType() {
                    return typeReference.getType();
                }
            });
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e);
        } catch (JsonMappingException e2) {
            throw new IllegalArgumentException(e2);
        } catch (IOException e3) {
            throw e3;
        }
    }

    public JsonObjectMapper(List<String> list) {
        this();
        if (list != null) {
            IGNORABLE_FIELDS.addAll(list);
            return;
        }
        throw new IllegalArgumentException("null ignorableFields passed.");
    }
}
