package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.SFx;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
/* compiled from: NavigationIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class SFx implements StronglyTypedString {
    public static SFx zZm(String str) {
        return new CNo(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<SFx> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<SFx>() { // from class: com.amazon.alexa.client.alexaservice.applicationmanager.payload.NavigationIdentifier$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public SFx mo1132instantiate(@NonNull String str) {
                return SFx.zZm(str);
            }
        };
    }
}
