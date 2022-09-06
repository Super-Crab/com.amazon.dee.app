package com.amazon.alexa.externalnotifications.capability.models;

import com.amazon.alexa.externalnotifications.capability.models.AutoValue_Contact;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Contact {
    public static Contact create(String str, String str2) {
        return new AutoValue_Contact(str, str2);
    }

    public static TypeAdapter<Contact> typeAdapter(Gson gson) {
        return new AutoValue_Contact.GsonTypeAdapter(gson);
    }

    public abstract String getId();

    public abstract String getName();
}
