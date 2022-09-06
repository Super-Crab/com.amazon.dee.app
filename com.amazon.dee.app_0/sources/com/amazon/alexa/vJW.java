package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallIdentifier;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.vJW;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import java.util.UUID;
/* compiled from: PhoneCallIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class vJW implements StronglyTypedString {
    public static TypeAdapter BIo() {
        return new StronglyTypedString.StronglyTypedStringAdapter<vJW>() { // from class: com.amazon.alexa.client.alexaservice.comms.payload.PhoneCallIdentifier$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public vJW mo1132instantiate(@NonNull String str) {
                return vJW.zZm(str);
            }
        };
    }

    public static vJW zZm() {
        return zZm(UUID.randomUUID().toString());
    }

    public static vJW zZm(String str) {
        return new AutoValue_PhoneCallIdentifier(str);
    }
}
