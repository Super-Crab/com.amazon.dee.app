package com.amazon.alexa.client.core.capabilities;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class CapabilityType implements StronglyTypedString {
    public static CapabilityType create(String str) {
        return new AutoValue_CapabilityType(str);
    }

    public static CapabilityType createAlexaInterface() {
        return create("AlexaInterface");
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<CapabilityType> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<CapabilityType>() { // from class: com.amazon.alexa.client.core.capabilities.CapabilityType.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public CapabilityType mo1132instantiate(@NonNull String str) {
                return CapabilityType.create(str);
            }
        };
    }
}
