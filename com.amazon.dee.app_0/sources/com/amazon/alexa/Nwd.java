package com.amazon.alexa;

import com.amazon.alexa.Nwd;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_PreferredLaunchTarget;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: PreferredLaunchTarget.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Nwd implements StronglyTypedString {
    public static Nwd zZm(String str) {
        return new AutoValue_PreferredLaunchTarget(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<Nwd>() { // from class: com.amazon.alexa.client.alexaservice.launcher.payload.PreferredLaunchTarget$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public Nwd mo1132instantiate(String str) {
                return Nwd.zZm(str);
            }
        };
    }
}
