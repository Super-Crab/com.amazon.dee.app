package com.amazonaws.com.google.gson.internal.bind;

import com.amazonaws.com.google.gson.Gson;
import com.amazonaws.com.google.gson.JsonElement;
import com.amazonaws.com.google.gson.JsonPrimitive;
import com.amazonaws.com.google.gson.JsonSyntaxException;
import com.amazonaws.com.google.gson.TypeAdapter;
import com.amazonaws.com.google.gson.TypeAdapterFactory;
import com.amazonaws.com.google.gson.internal.C$Gson$Types;
import com.amazonaws.com.google.gson.internal.ConstructorConstructor;
import com.amazonaws.com.google.gson.internal.JsonReaderInternalAccess;
import com.amazonaws.com.google.gson.internal.ObjectConstructor;
import com.amazonaws.com.google.gson.internal.Streams;
import com.amazonaws.com.google.gson.reflect.TypeToken;
import com.amazonaws.com.google.gson.stream.JsonReader;
import com.amazonaws.com.google.gson.stream.JsonToken;
import com.amazonaws.com.google.gson.stream.JsonWriter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes13.dex */
public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    private final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;

    /* loaded from: classes13.dex */
    private final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {
        private final ObjectConstructor<? extends Map<K, V>> constructor;
        private final TypeAdapter<K> keyTypeAdapter;
        private final TypeAdapter<V> valueTypeAdapter;

        public Adapter(Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, ObjectConstructor<? extends Map<K, V>> objectConstructor) {
            this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, type2);
            this.constructor = objectConstructor;
        }

        private String keyToString(JsonElement jsonElement) {
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isNumber()) {
                    return String.valueOf(asJsonPrimitive.getAsNumber());
                }
                if (asJsonPrimitive.isBoolean()) {
                    return Boolean.toString(asJsonPrimitive.getAsBoolean());
                }
                if (asJsonPrimitive.isString()) {
                    return asJsonPrimitive.getAsString();
                }
                throw new AssertionError();
            } else if (!jsonElement.isJsonNull()) {
                throw new AssertionError();
            } else {
                return "null";
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) throws IOException {
            write(jsonWriter, (Map) ((Map) obj));
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public Map<K, V> mo6687read(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Map<K, V> construct = this.constructor.construct();
            if (peek == JsonToken.BEGIN_ARRAY) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginArray();
                    K mo6687read = this.keyTypeAdapter.mo6687read(jsonReader);
                    if (construct.put(mo6687read, this.valueTypeAdapter.mo6687read(jsonReader)) == null) {
                        jsonReader.endArray();
                    } else {
                        throw new JsonSyntaxException(GeneratedOutlineSupport1.outline70("duplicate key: ", mo6687read));
                    }
                }
                jsonReader.endArray();
            } else {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonReader);
                    K mo6687read2 = this.keyTypeAdapter.mo6687read(jsonReader);
                    if (construct.put(mo6687read2, this.valueTypeAdapter.mo6687read(jsonReader)) != null) {
                        throw new JsonSyntaxException(GeneratedOutlineSupport1.outline70("duplicate key: ", mo6687read2));
                    }
                }
                jsonReader.endObject();
            }
            return construct;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void write(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
            if (map != null) {
                if (!MapTypeAdapterFactory.this.complexMapKeySerialization) {
                    jsonWriter.beginObject();
                    for (Map.Entry<K, V> entry : map.entrySet()) {
                        jsonWriter.name(String.valueOf(entry.getKey()));
                        this.valueTypeAdapter.write(jsonWriter, entry.getValue());
                    }
                    jsonWriter.endObject();
                    return;
                }
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    JsonElement jsonTree = this.keyTypeAdapter.toJsonTree(entry2.getKey());
                    arrayList.add(jsonTree);
                    arrayList2.add(entry2.getValue());
                    z |= jsonTree.isJsonArray() || jsonTree.isJsonObject();
                }
                if (z) {
                    jsonWriter.beginArray();
                    while (i < arrayList.size()) {
                        jsonWriter.beginArray();
                        Streams.write((JsonElement) arrayList.get(i), jsonWriter);
                        this.valueTypeAdapter.write(jsonWriter, arrayList2.get(i));
                        jsonWriter.endArray();
                        i++;
                    }
                    jsonWriter.endArray();
                    return;
                }
                jsonWriter.beginObject();
                while (i < arrayList.size()) {
                    jsonWriter.name(keyToString((JsonElement) arrayList.get(i)));
                    this.valueTypeAdapter.write(jsonWriter, arrayList2.get(i));
                    i++;
                }
                jsonWriter.endObject();
                return;
            }
            jsonWriter.nullValue();
        }
    }

    public MapTypeAdapterFactory(ConstructorConstructor constructorConstructor, boolean z) {
        this.constructorConstructor = constructorConstructor;
        this.complexMapKeySerialization = z;
    }

    private TypeAdapter<?> getKeyAdapter(Gson gson, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? TypeAdapters.BOOLEAN_AS_STRING : gson.getAdapter(TypeToken.get(type));
    }

    @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        if (!Map.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        Type[] mapKeyAndValueTypes = C$Gson$Types.getMapKeyAndValueTypes(type, C$Gson$Types.getRawType(type));
        return new Adapter(gson, mapKeyAndValueTypes[0], getKeyAdapter(gson, mapKeyAndValueTypes[0]), mapKeyAndValueTypes[1], gson.getAdapter(TypeToken.get(mapKeyAndValueTypes[1])), this.constructorConstructor.get(typeToken));
    }
}
