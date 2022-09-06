package com.amazon.deviceevents.com.google.gson.internal.bind;

import com.amazon.deviceevents.com.google.gson.Gson;
import com.amazon.deviceevents.com.google.gson.TypeAdapter;
import com.amazon.deviceevents.com.google.gson.TypeAdapterFactory;
import com.amazon.deviceevents.com.google.gson.internal.LinkedTreeMap;
import com.amazon.deviceevents.com.google.gson.reflect.TypeToken;
import com.amazon.deviceevents.com.google.gson.stream.JsonReader;
import com.amazon.deviceevents.com.google.gson.stream.JsonToken;
import com.amazon.deviceevents.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.amazon.deviceevents.com.google.gson.internal.bind.ObjectTypeAdapter.1
        @Override // com.amazon.deviceevents.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Object.class) {
                return new ObjectTypeAdapter(gson);
            }
            return null;
        }
    };
    private final Gson gson;

    /* renamed from: com.amazon.deviceevents.com.google.gson.internal.bind.ObjectTypeAdapter$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.amazon.deviceevents.com.google.gson.TypeAdapter
    /* renamed from: read */
    public Object mo4010read(JsonReader jsonReader) throws IOException {
        int ordinal = jsonReader.peek().ordinal();
        if (ordinal == 0) {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                arrayList.add(mo4010read(jsonReader));
            }
            jsonReader.endArray();
            return arrayList;
        } else if (ordinal == 2) {
            LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                linkedTreeMap.put(jsonReader.nextName(), mo4010read(jsonReader));
            }
            jsonReader.endObject();
            return linkedTreeMap;
        } else if (ordinal == 5) {
            return jsonReader.nextString();
        } else {
            if (ordinal == 6) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            if (ordinal == 7) {
                return Boolean.valueOf(jsonReader.nextBoolean());
            }
            if (ordinal == 8) {
                jsonReader.nextNull();
                return null;
            }
            throw new IllegalStateException();
        }
    }

    @Override // com.amazon.deviceevents.com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(obj.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        adapter.write(jsonWriter, obj);
    }

    private ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }
}
