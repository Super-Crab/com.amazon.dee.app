package com.amazon.alexa.voice.tta.dependencies;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;
@GsonTypeAdapterFactory
/* loaded from: classes11.dex */
public abstract class AutoValueAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueAdapterFactory();
    }
}
