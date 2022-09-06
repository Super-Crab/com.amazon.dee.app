package com.amazon.alexa;

import com.amazon.alexa.IUU;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_Token;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: Token.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class IUU implements StronglyTypedString {
    public static IUU zZm(String str) {
        return new AutoValue_Token(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<IUU>() { // from class: com.amazon.alexa.client.alexaservice.launcher.payload.Token$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public IUU mo1132instantiate(String str) {
                return IUU.zZm(str);
            }
        };
    }
}
