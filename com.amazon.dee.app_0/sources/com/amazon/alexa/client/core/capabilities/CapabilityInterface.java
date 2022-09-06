package com.amazon.alexa.client.core.capabilities;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class CapabilityInterface implements StronglyTypedString {
    public static CapabilityInterface create(String str) {
        return new AutoValue_CapabilityInterface(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<CapabilityInterface> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<CapabilityInterface>() { // from class: com.amazon.alexa.client.core.capabilities.CapabilityInterface.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public CapabilityInterface mo1132instantiate(@NonNull String str) {
                return CapabilityInterface.create(str);
            }
        };
    }
}
