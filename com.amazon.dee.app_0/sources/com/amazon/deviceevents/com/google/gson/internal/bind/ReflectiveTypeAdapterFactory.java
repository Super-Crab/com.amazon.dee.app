package com.amazon.deviceevents.com.google.gson.internal.bind;

import com.amazon.deviceevents.com.google.gson.FieldNamingStrategy;
import com.amazon.deviceevents.com.google.gson.Gson;
import com.amazon.deviceevents.com.google.gson.JsonSyntaxException;
import com.amazon.deviceevents.com.google.gson.TypeAdapter;
import com.amazon.deviceevents.com.google.gson.TypeAdapterFactory;
import com.amazon.deviceevents.com.google.gson.annotations.JsonAdapter;
import com.amazon.deviceevents.com.google.gson.annotations.SerializedName;
import com.amazon.deviceevents.com.google.gson.internal.C$Gson$Types;
import com.amazon.deviceevents.com.google.gson.internal.ConstructorConstructor;
import com.amazon.deviceevents.com.google.gson.internal.Excluder;
import com.amazon.deviceevents.com.google.gson.internal.ObjectConstructor;
import com.amazon.deviceevents.com.google.gson.internal.Primitives;
import com.amazon.deviceevents.com.google.gson.reflect.TypeToken;
import com.amazon.deviceevents.com.google.gson.stream.JsonReader;
import com.amazon.deviceevents.com.google.gson.stream.JsonToken;
import com.amazon.deviceevents.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;

    /* loaded from: classes12.dex */
    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        @Override // com.amazon.deviceevents.com.google.gson.TypeAdapter
        /* renamed from: read */
        public T mo4010read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            T construct = this.constructor.construct();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    BoundField boundField = this.boundFields.get(jsonReader.nextName());
                    if (boundField != null && boundField.deserialized) {
                        boundField.read(jsonReader, construct);
                    }
                    jsonReader.skipValue();
                }
                jsonReader.endObject();
                return construct;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.amazon.deviceevents.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.writeField(t)) {
                        jsonWriter.name(boundField.name);
                        boundField.write(jsonWriter, t);
                    }
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        private Adapter(ObjectConstructor<T> objectConstructor, Map<String, BoundField> map) {
            this.constructor = objectConstructor;
            this.boundFields = map;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        protected BoundField(String str, boolean z, boolean z2) {
            this.name = str;
            this.serialized = z;
            this.deserialized = z2;
        }

        abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        abstract boolean writeField(Object obj) throws IOException, IllegalAccessException;
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder;
    }

    private BoundField createBoundField(final Gson gson, final Field field, String str, final TypeToken<?> typeToken, boolean z, boolean z2) {
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        return new BoundField(str, z, z2) { // from class: com.amazon.deviceevents.com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.1
            final TypeAdapter<?> typeAdapter;

            {
                this.typeAdapter = ReflectiveTypeAdapterFactory.this.getFieldAdapter(gson, field, typeToken);
            }

            @Override // com.amazon.deviceevents.com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
                Object mo4010read = this.typeAdapter.mo4010read(jsonReader);
                if (mo4010read != null || !isPrimitive) {
                    field.set(obj, mo4010read);
                }
            }

            @Override // com.amazon.deviceevents.com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
                new TypeAdapterRuntimeTypeWrapper(gson, this.typeAdapter, typeToken.getType()).write(jsonWriter, field.get(obj));
            }

            @Override // com.amazon.deviceevents.com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            public boolean writeField(Object obj) throws IOException, IllegalAccessException {
                return this.serialized && field.get(obj) != obj;
            }
        };
    }

    private Map<String, BoundField> getBoundFields(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        Field[] declaredFields;
        BoundField boundField;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = typeToken.getType();
        TypeToken<?> typeToken2 = typeToken;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            for (Field field : cls2.getDeclaredFields()) {
                boolean excludeField = excludeField(field, true);
                boolean excludeField2 = excludeField(field, false);
                if (excludeField || excludeField2) {
                    field.setAccessible(true);
                    BoundField createBoundField = createBoundField(gson, field, getFieldName(field), TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), cls2, field.getGenericType())), excludeField, excludeField2);
                    if (((BoundField) linkedHashMap.put(createBoundField.name, createBoundField)) != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + boundField.name);
                    }
                }
            }
            typeToken2 = TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = typeToken2.getRawType();
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TypeAdapter<?> getFieldAdapter(Gson gson, Field field, TypeToken<?> typeToken) {
        TypeAdapter<?> typeAdapter;
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        return (jsonAdapter == null || (typeAdapter = JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter)) == null) ? gson.getAdapter(typeToken) : typeAdapter;
    }

    private String getFieldName(Field field) {
        return getFieldName(this.fieldNamingPolicy, field);
    }

    @Override // com.amazon.deviceevents.com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        return new Adapter(this.constructorConstructor.get(typeToken), getBoundFields(gson, typeToken, rawType));
    }

    public boolean excludeField(Field field, boolean z) {
        return excludeField(field, z, this.excluder);
    }

    static boolean excludeField(Field field, boolean z, Excluder excluder) {
        return !excluder.excludeClass(field.getType(), z) && !excluder.excludeField(field, z);
    }

    static String getFieldName(FieldNamingStrategy fieldNamingStrategy, Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        return serializedName == null ? fieldNamingStrategy.translateName(field) : serializedName.value();
    }
}
