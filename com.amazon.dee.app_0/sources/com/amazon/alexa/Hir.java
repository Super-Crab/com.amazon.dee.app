package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.Hir;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
/* compiled from: SkillToken.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Hir implements StronglyTypedString {
    public static final Hir zZm = zZm("");

    public static Hir zZm(String str) {
        return new Sot(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<Hir> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<Hir>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.SkillToken$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public Hir mo1132instantiate(@NonNull String str) {
                return Hir.zZm(str);
            }
        };
    }
}
