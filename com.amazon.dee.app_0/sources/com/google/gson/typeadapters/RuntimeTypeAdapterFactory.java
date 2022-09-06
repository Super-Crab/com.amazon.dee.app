package com.google.gson.typeadapters;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final Map<String, Class<?>> labelToSubtype = new LinkedHashMap();
    private final Map<Class<?>, String> subtypeToLabel = new LinkedHashMap();
    private final String typeFieldName;

    private RuntimeTypeAdapterFactory(Class<?> cls, String str) {
        if (str != null && cls != null) {
            this.baseType = cls;
            this.typeFieldName = str;
            return;
        }
        throw new NullPointerException();
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> cls, String str) {
        return new RuntimeTypeAdapterFactory<>(cls, str);
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> typeToken) {
        if (typeToken.getRawType() != this.baseType) {
            return null;
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<String, Class<?>> entry : this.labelToSubtype.entrySet()) {
            TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get((Class) entry.getValue()));
            linkedHashMap.put(entry.getKey(), delegateAdapter);
            linkedHashMap2.put(entry.getValue(), delegateAdapter);
        }
        return new TypeAdapter<R>() { // from class: com.google.gson.typeadapters.RuntimeTypeAdapterFactory.1
            /* JADX WARN: Type inference failed for: r4v4, types: [R, java.lang.Object] */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public R mo8353read(JsonReader jsonReader) throws IOException {
                JsonElement parse = Streams.parse(jsonReader);
                JsonElement remove = parse.getAsJsonObject().remove(RuntimeTypeAdapterFactory.this.typeFieldName);
                if (remove == null) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot deserialize ");
                    outline107.append(RuntimeTypeAdapterFactory.this.baseType);
                    outline107.append(" because it does not define a field named ");
                    outline107.append(RuntimeTypeAdapterFactory.this.typeFieldName);
                    throw new JsonParseException(outline107.toString());
                }
                String asString = remove.getAsString();
                TypeAdapter typeAdapter = (TypeAdapter) linkedHashMap.get(asString);
                if (typeAdapter == null) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("cannot deserialize ");
                    outline1072.append(RuntimeTypeAdapterFactory.this.baseType);
                    outline1072.append(" subtype named ");
                    outline1072.append(asString);
                    outline1072.append("; did you forget to register a subtype?");
                    throw new JsonParseException(outline1072.toString());
                }
                return typeAdapter.fromJsonTree(parse);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, R r) throws IOException {
                Class<?> cls = r.getClass();
                String str = (String) RuntimeTypeAdapterFactory.this.subtypeToLabel.get(cls);
                TypeAdapter typeAdapter = (TypeAdapter) linkedHashMap2.get(cls);
                if (typeAdapter != null) {
                    JsonObject asJsonObject = typeAdapter.toJsonTree(r).getAsJsonObject();
                    if (asJsonObject.has(RuntimeTypeAdapterFactory.this.typeFieldName)) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot serialize ");
                        GeneratedOutlineSupport1.outline146(cls, outline107, " because it already defines a field named ");
                        outline107.append(RuntimeTypeAdapterFactory.this.typeFieldName);
                        throw new JsonParseException(outline107.toString());
                    }
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add(RuntimeTypeAdapterFactory.this.typeFieldName, new JsonPrimitive(str));
                    for (Map.Entry<String, JsonElement> entry2 : asJsonObject.entrySet()) {
                        jsonObject.add(entry2.getKey(), entry2.getValue());
                    }
                    Streams.write(jsonObject, jsonWriter);
                    return;
                }
                throw new JsonParseException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("cannot serialize "), "; did you forget to register a subtype?"));
            }
        }.nullSafe();
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> cls, String str) {
        if (cls != null && str != null) {
            if (!this.subtypeToLabel.containsKey(cls) && !this.labelToSubtype.containsKey(str)) {
                this.labelToSubtype.put(str, cls);
                this.subtypeToLabel.put(cls, str);
                return this;
            }
            throw new IllegalArgumentException("types and labels must be unique");
        }
        throw new NullPointerException();
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> cls) {
        return new RuntimeTypeAdapterFactory<>(cls, "type");
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> cls) {
        return registerSubtype(cls, cls.getSimpleName());
    }
}
