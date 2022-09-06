package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.FHI;
import com.amazon.alexa.YJe;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
/* compiled from: LocalPlayerIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class FHI implements StronglyTypedString, YJe.zQM {
    static {
        zZm("UNKNOWN");
    }

    public static FHI zZm(String str) {
        return new LkP(str);
    }

    @Override // com.amazon.alexa.YJe.zQM
    public String name() {
        return getValue();
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<FHI> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<FHI>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.LocalPlayerIdentifier$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public FHI mo1132instantiate(@NonNull String str) {
                return FHI.zZm(str);
            }
        };
    }
}
