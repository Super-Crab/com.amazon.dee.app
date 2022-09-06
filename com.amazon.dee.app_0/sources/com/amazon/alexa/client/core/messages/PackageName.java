package com.amazon.alexa.client.core.messages;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class PackageName implements StronglyTypedString {
    public static PackageName create(String str) {
        return new AutoValue_PackageName(str);
    }

    public static TypeAdapter<PackageName> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<PackageName>() { // from class: com.amazon.alexa.client.core.messages.PackageName.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public PackageName mo1132instantiate(@NonNull String str) {
                return PackageName.create(str);
            }
        };
    }
}
