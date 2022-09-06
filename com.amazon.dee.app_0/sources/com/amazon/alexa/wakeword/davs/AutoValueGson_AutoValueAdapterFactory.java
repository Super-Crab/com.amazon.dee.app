package com.amazon.alexa.wakeword.davs;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
/* loaded from: classes11.dex */
public final class AutoValueGson_AutoValueAdapterFactory extends AutoValueAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (ArtifactManifest.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) ArtifactManifest.typeAdapter(gson);
        }
        if (ArtifactRequest.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) ArtifactRequest.typeAdapter(gson);
        }
        if (!Checksum.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) Checksum.typeAdapter(gson);
    }
}
