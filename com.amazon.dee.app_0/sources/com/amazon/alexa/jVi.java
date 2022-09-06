package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.jVi;
import com.google.auto.value.AutoValue;
/* compiled from: CapabilityAgentVersion.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class jVi implements StronglyTypedString {
    public static jVi zZm(String str) {
        return new dRa(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<jVi> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<jVi>() { // from class: com.amazon.alexa.client.alexaservice.capabilities.v2.CapabilityAgentVersion$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public jVi mo1132instantiate(@NonNull String str) {
                return jVi.zZm(str);
            }
        };
    }
}
