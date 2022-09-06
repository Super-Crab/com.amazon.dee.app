package com.amazon.alexa;

import com.amazon.alexa.LZG;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_FallbackPolicy;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: FallbackPolicy.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class LZG implements StronglyTypedString {
    public static LZG zZm(String str) {
        return new AutoValue_FallbackPolicy(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<LZG>() { // from class: com.amazon.alexa.client.alexaservice.launcher.payload.FallbackPolicy$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public LZG mo1132instantiate(String str) {
                return LZG.zZm(str);
            }
        };
    }
}
