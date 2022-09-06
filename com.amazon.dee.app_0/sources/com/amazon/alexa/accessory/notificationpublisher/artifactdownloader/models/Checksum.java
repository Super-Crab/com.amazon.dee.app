package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.AutoValue_Checksum;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes.dex */
public abstract class Checksum {
    public static Checksum create(String str) {
        return new AutoValue_Checksum(str);
    }

    public static TypeAdapter<Checksum> typeAdapter(Gson gson) {
        return new AutoValue_Checksum.GsonTypeAdapter(gson);
    }

    public abstract String getMd5();
}
