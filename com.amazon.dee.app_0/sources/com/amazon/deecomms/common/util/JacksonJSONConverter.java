package com.amazon.deecomms.common.util;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
/* loaded from: classes12.dex */
public class JacksonJSONConverter implements IHttpClient.JSONConverter {
    private CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, JacksonJSONConverter.class);

    /* loaded from: classes12.dex */
    private static class MapperHolder {
        static final ObjectMapper MAPPER = computeMapper();

        private MapperHolder() {
        }

        private static ObjectMapper computeMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper;
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.JSONConverter
    public <T> T fromJson(String str, Class<T> cls) {
        try {
            return (T) MapperHolder.MAPPER.readValue(str, cls);
        } catch (IOException e) {
            this.LOG.e("Exception occurred while parsing JSON to object", e);
            return null;
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.JSONConverter
    public String toJson(Object obj) {
        try {
            return MapperHolder.MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            this.LOG.e("Error occurred in parsing object to JSON", e);
            return null;
        }
    }

    public <T> T fromJson(String str, TypeReference<T> typeReference) {
        try {
            return (T) MapperHolder.MAPPER.readValue(str, typeReference);
        } catch (IOException e) {
            this.LOG.e("Exception occurred while parsing JSON to object", e);
            return null;
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.JSONConverter
    public <T> T fromJson(Reader reader, Class<T> cls) {
        try {
            return (T) MapperHolder.MAPPER.readValue(reader, cls);
        } catch (IOException e) {
            this.LOG.e("Exception occurred while parsing JSON to object", e);
            return null;
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.JSONConverter
    @Deprecated
    public <T> T fromJson(String str) {
        try {
            this.LOG.e("[DEPRECATED]  This is not the .fromJson() you are looking for");
            return (T) MapperHolder.MAPPER.readValue(str, new TypeReference<T>() { // from class: com.amazon.deecomms.common.util.JacksonJSONConverter.1
            });
        } catch (IOException e) {
            this.LOG.e("Exception occurred while parsing string to JSON", e);
            return null;
        }
    }
}
