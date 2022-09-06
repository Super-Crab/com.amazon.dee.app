package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.SOo;
import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_AlexaTimeZone;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: AlexaTimeZone.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class SOo implements StronglyTypedString {
    public static SOo zZm(String str) {
        return new AutoValue_AlexaTimeZone(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<SOo>() { // from class: com.amazon.alexa.client.alexaservice.system.payload.AlexaTimeZone$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public SOo mo1132instantiate(@NonNull String str) {
                return SOo.zZm(str);
            }
        };
    }
}
