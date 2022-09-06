package com.amazon.alexa.drive.navigation;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;
@GsonTypeAdapterFactory
/* loaded from: classes7.dex */
public abstract class AutoValueAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueAdapterFactory();
    }
}
