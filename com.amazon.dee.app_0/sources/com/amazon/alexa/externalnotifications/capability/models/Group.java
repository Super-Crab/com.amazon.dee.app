package com.amazon.alexa.externalnotifications.capability.models;

import com.amazon.alexa.externalnotifications.capability.models.AutoValue_Group;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Group {
    public static Group create(String str, String str2) {
        return new AutoValue_Group(str, str2);
    }

    public static TypeAdapter<Group> typeAdapter(Gson gson) {
        return new AutoValue_Group.GsonTypeAdapter(gson);
    }

    public abstract String getId();

    public abstract String getName();
}
