package com.amazon.alexa.client.core.messages;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import java.util.HashSet;
import java.util.Set;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Namespace implements StronglyTypedString {
    public static Namespace create(String str) {
        return new AutoValue_Namespace(str);
    }

    public static Set<Namespace> createMultiple(Set<String> set) {
        HashSet hashSet = new HashSet();
        for (String str : set) {
            if (!TextUtils.isEmpty(str)) {
                hashSet.add(create(str));
            }
        }
        return hashSet;
    }

    public static TypeAdapter<Namespace> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<Namespace>() { // from class: com.amazon.alexa.client.core.messages.Namespace.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public Namespace mo1132instantiate(@NonNull String str) {
                return Namespace.create(str);
            }
        };
    }
}
