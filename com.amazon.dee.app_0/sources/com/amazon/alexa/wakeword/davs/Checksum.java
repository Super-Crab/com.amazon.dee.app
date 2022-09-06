package com.amazon.alexa.wakeword.davs;

import com.amazon.alexa.wakeword.davs.AutoValue_Checksum;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes11.dex */
public abstract class Checksum {
    public static Checksum create(String str) {
        return new AutoValue_Checksum(str);
    }

    public static TypeAdapter<Checksum> typeAdapter(Gson gson) {
        return new AutoValue_Checksum.GsonTypeAdapter(gson);
    }

    public abstract String getMd5();
}
