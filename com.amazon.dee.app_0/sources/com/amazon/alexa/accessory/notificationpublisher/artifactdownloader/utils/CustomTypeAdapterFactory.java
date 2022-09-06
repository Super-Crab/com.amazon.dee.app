package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.utils;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;
@GsonTypeAdapterFactory
/* loaded from: classes.dex */
public abstract class CustomTypeAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_CustomTypeAdapterFactory();
    }
}
