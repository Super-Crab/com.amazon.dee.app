package com.amazon.alexa.client.core.messages;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Name implements StronglyTypedString {
    public static Name create(String str) {
        return new AutoValue_Name(str);
    }

    public static TypeAdapter<Name> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<Name>() { // from class: com.amazon.alexa.client.core.messages.Name.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public Name mo1132instantiate(@NonNull String str) {
                return Name.create(str);
            }
        };
    }
}
