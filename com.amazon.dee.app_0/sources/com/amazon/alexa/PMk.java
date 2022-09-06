package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.PMk;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_TargetIdentifier;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: TargetIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class PMk implements StronglyTypedString {
    public static PMk zZm(String str) {
        return new AutoValue_TargetIdentifier(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<PMk>() { // from class: com.amazon.alexa.client.alexaservice.launcher.payload.TargetIdentifier$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public PMk mo1132instantiate(@NonNull String str) {
                return PMk.zZm(str);
            }
        };
    }
}
