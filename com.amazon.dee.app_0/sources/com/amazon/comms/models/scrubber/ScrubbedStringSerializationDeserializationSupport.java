package com.amazon.comms.models.scrubber;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes11.dex */
public class ScrubbedStringSerializationDeserializationSupport {

    /* loaded from: classes11.dex */
    public static class GsonSerializerDeserializer extends TypeAdapter<ScrubbedString> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ScrubbedString mo8353read(JsonReader jsonReader) throws IOException {
            return ScrubbedString.unscrub(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ScrubbedString scrubbedString) throws IOException {
            jsonWriter.value(scrubbedString.scrubbed());
        }
    }

    /* loaded from: classes11.dex */
    public static class JacksonDeserializer extends JsonDeserializer<ScrubbedString> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /* renamed from: deserialize */
        public ScrubbedString mo7111deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return ScrubbedString.unscrub((String) jsonParser.readValueAs(String.class));
        }
    }

    /* loaded from: classes11.dex */
    public static class JacksonSerializer extends JsonSerializer<ScrubbedString> {
        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(ScrubbedString scrubbedString, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeString(scrubbedString.scrubbed());
        }
    }
}
