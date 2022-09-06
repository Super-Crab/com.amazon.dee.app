package com.amazon.alexa.client.core.messages;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class CorrelationToken implements StronglyTypedString {
    public static CorrelationToken create(String str) {
        return new AutoValue_CorrelationToken(str);
    }

    public static TypeAdapter<CorrelationToken> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<CorrelationToken>() { // from class: com.amazon.alexa.client.core.messages.CorrelationToken.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public CorrelationToken mo1132instantiate(@NonNull String str) {
                return CorrelationToken.create(str);
            }
        };
    }
}
