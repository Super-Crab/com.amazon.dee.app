package com.amazon.alexa.client.core.messages;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class PayloadVersion implements StronglyTypedString {
    public static PayloadVersion create(String str) {
        return new AutoValue_PayloadVersion(str);
    }

    public static TypeAdapter<PayloadVersion> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<PayloadVersion>() { // from class: com.amazon.alexa.client.core.messages.PayloadVersion.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public PayloadVersion mo1132instantiate(@NonNull String str) {
                return PayloadVersion.create(str);
            }
        };
    }
}
