package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
public final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {
    private final JsonWriter jsonWriter;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;
    private JsonValueObjectEncoderContext childContext = null;
    private boolean active = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonValueObjectEncoderContext(@NonNull Writer writer, @NonNull Map<Class<?>, ObjectEncoder<?>> map, @NonNull Map<Class<?>, ValueEncoder<?>> map2) {
        this.jsonWriter = new JsonWriter(writer);
        this.objectEncoders = map;
        this.valueEncoders = map2;
    }

    private void maybeUnNest() throws IOException {
        if (this.active) {
            JsonValueObjectEncoderContext jsonValueObjectEncoderContext = this.childContext;
            if (jsonValueObjectEncoderContext == null) {
                return;
            }
            jsonValueObjectEncoderContext.maybeUnNest();
            this.childContext.active = false;
            this.childContext = null;
            this.jsonWriter.endObject();
            return;
        }
        throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() throws IOException {
        maybeUnNest();
        this.jsonWriter.flush();
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext nested(@NonNull String str) throws IOException {
        maybeUnNest();
        this.childContext = new JsonValueObjectEncoderContext(this);
        this.jsonWriter.name(str);
        this.jsonWriter.beginObject();
        return this.childContext;
    }

    private JsonValueObjectEncoderContext(JsonValueObjectEncoderContext jsonValueObjectEncoderContext) {
        this.jsonWriter = jsonValueObjectEncoderContext.jsonWriter;
        this.objectEncoders = jsonValueObjectEncoderContext.objectEncoders;
        this.valueEncoders = jsonValueObjectEncoderContext.valueEncoders;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8300add(@NonNull String str, @Nullable Object obj) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.name(str);
        if (obj == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        return add(obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8297add(@NonNull String str, double d) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return mo8293add(d);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8298add(@NonNull String str, int i) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return mo8294add(i);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8299add(@NonNull String str, long j) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return mo8295add(j);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8301add(@NonNull String str, boolean z) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return mo8302add(z);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8296add(@Nullable String str) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.value(str);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8293add(double d) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.value(d);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8294add(int i) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.value(i);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8295add(long j) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.value(j);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8302add(boolean z) throws IOException, EncodingException {
        maybeUnNest();
        this.jsonWriter.value(z);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: add  reason: collision with other method in class */
    public JsonValueObjectEncoderContext mo8303add(@Nullable byte[] bArr) throws IOException, EncodingException {
        maybeUnNest();
        if (bArr == null) {
            this.jsonWriter.nullValue();
        } else {
            this.jsonWriter.value(Base64.encodeToString(bArr, 2));
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public JsonValueObjectEncoderContext add(@Nullable Object obj) throws IOException, EncodingException {
        if (obj == null) {
            this.jsonWriter.nullValue();
            return this;
        } else if (obj instanceof Number) {
            this.jsonWriter.value((Number) obj);
            return this;
        } else {
            int i = 0;
            if (obj.getClass().isArray()) {
                if (obj instanceof byte[]) {
                    return mo8303add((byte[]) obj);
                }
                this.jsonWriter.beginArray();
                if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    int length = iArr.length;
                    while (i < length) {
                        this.jsonWriter.value(iArr[i]);
                        i++;
                    }
                } else if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    int length2 = jArr.length;
                    while (i < length2) {
                        mo8295add(jArr[i]);
                        i++;
                    }
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    int length3 = dArr.length;
                    while (i < length3) {
                        this.jsonWriter.value(dArr[i]);
                        i++;
                    }
                } else if (obj instanceof boolean[]) {
                    boolean[] zArr = (boolean[]) obj;
                    int length4 = zArr.length;
                    while (i < length4) {
                        this.jsonWriter.value(zArr[i]);
                        i++;
                    }
                } else if (obj instanceof Number[]) {
                    Number[] numberArr = (Number[]) obj;
                    int length5 = numberArr.length;
                    while (i < length5) {
                        add(numberArr[i]);
                        i++;
                    }
                } else {
                    Object[] objArr = (Object[]) obj;
                    int length6 = objArr.length;
                    while (i < length6) {
                        add(objArr[i]);
                        i++;
                    }
                }
                this.jsonWriter.endArray();
                return this;
            } else if (obj instanceof Collection) {
                this.jsonWriter.beginArray();
                for (Object obj2 : (Collection) obj) {
                    add(obj2);
                }
                this.jsonWriter.endArray();
                return this;
            } else if (obj instanceof Map) {
                this.jsonWriter.beginObject();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    Object key = entry.getKey();
                    try {
                        mo8300add((String) key, entry.getValue());
                    } catch (ClassCastException e) {
                        throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e);
                    }
                }
                this.jsonWriter.endObject();
                return this;
            } else {
                ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
                if (objectEncoder != null) {
                    this.jsonWriter.beginObject();
                    objectEncoder.encode(obj, this);
                    this.jsonWriter.endObject();
                    return this;
                }
                ValueEncoder<?> valueEncoder = this.valueEncoders.get(obj.getClass());
                if (valueEncoder != null) {
                    valueEncoder.encode(obj, this);
                    return this;
                } else if (obj instanceof Enum) {
                    mo8296add(((Enum) obj).name());
                    return this;
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Couldn't find encoder for type ");
                    outline107.append(obj.getClass().getCanonicalName());
                    throw new EncodingException(outline107.toString());
                }
            }
        }
    }
}
