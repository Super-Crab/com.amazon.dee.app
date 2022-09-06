package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.Csx;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
/* compiled from: NavigationIdentifierType.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Csx implements StronglyTypedString {
    public static Csx zZm(String str) {
        return new qQS(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<Csx> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<Csx>() { // from class: com.amazon.alexa.client.alexaservice.applicationmanager.payload.NavigationIdentifierType$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public Csx mo1132instantiate(@NonNull String str) {
                return Csx.zZm(str);
            }
        };
    }
}
