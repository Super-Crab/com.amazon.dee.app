package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.C0176Pce;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.gson.TypeAdapter;
/* compiled from: SpeechToken.java */
/* renamed from: com.amazon.alexa.Pce  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0176Pce extends Puy implements StronglyTypedString {
    public C0176Pce(String str) {
        super(str);
    }

    public static TypeAdapter<C0176Pce> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<C0176Pce>() { // from class: com.amazon.alexa.client.alexaservice.audio.SpeechToken$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public C0176Pce mo1132instantiate(@NonNull String str) {
                return new C0176Pce(str);
            }
        };
    }
}
