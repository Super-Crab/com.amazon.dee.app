package com.amazon.alexa.externalnotifications.capability.models;

import com.amazon.alexa.externalnotifications.capability.models.AutoValue_Application;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Application {
    public static Application create(String str, String str2) {
        return new AutoValue_Application(str, str2);
    }

    public static TypeAdapter<Application> typeAdapter(Gson gson) {
        return new AutoValue_Application.GsonTypeAdapter(gson);
    }

    public abstract String getId();

    public abstract String getName();
}
