package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;
@GsonTypeAdapterFactory
/* loaded from: classes6.dex */
public abstract class AutoValueAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory zZm() {
        return new AutoValueGson_AutoValueAdapterFactory();
    }
}
